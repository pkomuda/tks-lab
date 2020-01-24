package com.pas.zad2mvc.repositories;

import com.pas.zad2mvc.model.*;
import com.pas.zad2mvc.model.catalogs.Book;
import com.pas.zad2mvc.model.catalogs.Movie;
import com.pas.zad2mvc.model.catalogs.NoCatalog;
import com.pas.zad2mvc.model.users.Client;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class RentRepository {
    private final String url = "jdbc:derby://localhost:1527/PasDB";

    public synchronized void addRent(Rent rent) {
        try (Connection connection = DriverManager.getConnection(url)) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO RENTTABLE(ID, CLIENT, CATALOG, RENTDATE) VALUES(?,?,?,?)");
            preparedStatement.setString(1, rent.getId());
            preparedStatement.setString(2, rent.getClient().getUsername());
            preparedStatement.setInt(3, rent.getCatalog().getId());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(rent.getRentDateTime()));
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized Rent getRent(String id) {
        Rent rent = null;
        String username, firstName, lastName, catalogType, title, author, director, format;
        int catalogId, releaseYear;
        boolean activity;
        LocalDateTime rentDateTime, returnDateTime = null;
        try (Connection connection = DriverManager.getConnection(url)) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT R.CLIENT, R.CATALOG, R.RENTDATE, R.RETURNDATE, " +
                            "U.ACTIVITY, U.FIRSTNAME, U.LASTNAME, " +
                            "C.TITLE, C.RELEASEYEAR, C.CATALOGTYPE, C.AUTHOR, C.DIRECTOR, C.FORMAT FROM RENTTABLE AS R " +
                            "INNER JOIN USERTABLE AS U on U.USERNAME = R.CLIENT " +
                            "INNER JOIN CATALOGTABLE AS C on C.ID = R.CATALOG WHERE R.ID = ?");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                username = resultSet.getString("CLIENT");
                firstName = resultSet.getString("FIRSTNAME");
                lastName = resultSet.getString("LASTNAME");
                catalogType = resultSet.getString("CATALOGTYPE");
                title = resultSet.getString("TITLE");
                catalogId = resultSet.getInt("CATALOG");
                releaseYear = resultSet.getInt("RELEASEYEAR");
                activity = resultSet.getBoolean("ACTIVITY");
                rentDateTime = resultSet.getTimestamp("RENTDATE").toLocalDateTime();
                if (resultSet.getTimestamp("RETURNDATE") != null) {
                    returnDateTime = resultSet.getTimestamp("RENTDATE").toLocalDateTime();
                }
                switch (catalogType) {
                    case "BOOK":
                        author = resultSet.getString("AUTHOR");
                        rent = new Rent(new Client(username, activity, firstName, lastName), new Book(catalogId, title, author, releaseYear));
                        rent.setId(id);
                        rent.setRentDateTime(rentDateTime);
                        rent.setReturnDateTime(returnDateTime);
                        break;
                    case "MOVIE":
                        director = resultSet.getString("DIRECTOR");
                        format = resultSet.getString("FORMAT");
                        rent = new Rent(new Client(username, activity, firstName, lastName), new Movie(catalogId, title, director, releaseYear, format));
                        rent.setId(id);
                        rent.setRentDateTime(rentDateTime);
                        rent.setReturnDateTime(returnDateTime);
                        break;
                    case "NOCATALOG":
                        rent = new Rent(new Client(username, activity, firstName, lastName), new NoCatalog());
                        rent.setId(id);
                        rent.setRentDateTime(rentDateTime);
                        rent.setReturnDateTime(returnDateTime);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rent;
    }

    public synchronized void updateRent(String id, Rent rent) {
        try (Connection connection = DriverManager.getConnection(url)) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE RENTTABLE SET CLIENT=?, CATALOG=?, RENTDATE=?, RETURNDATE=? WHERE ID=?");
            preparedStatement.setString(1, rent.getClient().getUsername());
            preparedStatement.setInt(2, rent.getCatalog().getId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(rent.getRentDateTime()));
            if (rent.getReturnDateTime() != null) {
                preparedStatement.setTimestamp(4, Timestamp.valueOf(rent.getReturnDateTime()));
            } else {
                preparedStatement.setTimestamp(4, null);
            }
            preparedStatement.setString(5, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized void removeRent(String id) {
        try (Connection connection = DriverManager.getConnection(url)) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM RENTTABLE WHERE ID=?");
            preparedStatement.setString(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized List<Rent> getRents() {
        List<Rent> rents = new ArrayList<>();
        String id, username, firstName, lastName, catalogType, title, author, director, format;
        int catalogId, releaseYear;
        boolean activity;
        LocalDateTime rentDateTime, returnDateTime;
        try (Connection connection = DriverManager.getConnection(url)) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT R.ID, R.CLIENT, R.CATALOG, R.RENTDATE, R.RETURNDATE, " +
                            "U.ACTIVITY, U.FIRSTNAME, U.LASTNAME, " +
                            "C.TITLE, C.RELEASEYEAR, C.CATALOGTYPE, C.AUTHOR, C.DIRECTOR, C.FORMAT FROM RENTTABLE AS R " +
                            "INNER JOIN USERTABLE AS U on U.USERNAME = R.CLIENT " +
                            "INNER JOIN CATALOGTABLE AS C on C.ID = R.CATALOG");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getString("ID");
                username = resultSet.getString("CLIENT");
                firstName = resultSet.getString("FIRSTNAME");
                lastName = resultSet.getString("LASTNAME");
                catalogType = resultSet.getString("CATALOGTYPE");
                title = resultSet.getString("TITLE");
                catalogId = resultSet.getInt("CATALOG");
                releaseYear = resultSet.getInt("RELEASEYEAR");
                activity = resultSet.getBoolean("ACTIVITY");
                rentDateTime = resultSet.getTimestamp("RENTDATE").toLocalDateTime();
                returnDateTime = null;
                if (resultSet.getTimestamp("RETURNDATE") != null) {
                    returnDateTime = resultSet.getTimestamp("RENTDATE").toLocalDateTime();
                }
                switch (catalogType) {
                    case "BOOK":
                        author = resultSet.getString("AUTHOR");
                        rents.add(new Rent(new Client(username, activity, firstName, lastName), new Book(catalogId, title, author, releaseYear)));
                        rents.get(rents.size()-1).setId(id);
                        rents.get(rents.size()-1).setRentDateTime(rentDateTime);
                        rents.get(rents.size()-1).setReturnDateTime(returnDateTime);
                        break;
                    case "MOVIE":
                        director = resultSet.getString("DIRECTOR");
                        format = resultSet.getString("FORMAT");
                        rents.add(new Rent(new Client(username, activity, firstName, lastName), new Movie(catalogId, title, director, releaseYear, format)));
                        rents.get(rents.size()-1).setId(id);
                        rents.get(rents.size()-1).setRentDateTime(rentDateTime);
                        rents.get(rents.size()-1).setReturnDateTime(returnDateTime);
                        break;
                    case "NOCATALOG":
                        rents.add(new Rent(new Client(username, activity, firstName, lastName), new NoCatalog()));
                        rents.get(rents.size()-1).setId(id);
                        rents.get(rents.size()-1).setRentDateTime(rentDateTime);
                        rents.get(rents.size()-1).setReturnDateTime(returnDateTime);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rents;
    }

    public List<Rent> getUnfinishedRents() {
        return getRents()
                .stream()
                .filter(rent -> rent.getReturnDateTime() == null)
                .collect(Collectors.toList());
    }

    public List<Rent> getFinishedRents() {
        return getRents()
                .stream()
                .filter(rent -> rent.getReturnDateTime() != null)
                .collect(Collectors.toList());
    }

    public List<Rent> getRentsForClient(String username) {
        return getRents()
                .stream()
                .filter(rent -> rent.getClient().getUsername().equals(username))
                .collect(Collectors.toList());
    }

    public List<Rent> getUnfinishedRentsForClient(String username) {
        return getRentsForClient(username)
                .stream()
                .filter(rent -> rent.getReturnDateTime() == null)
                .collect(Collectors.toList());
    }

    public List<Rent> getFinishedRentsForClient(String username) {
        return getRentsForClient(username)
                .stream()
                .filter(rent -> rent.getReturnDateTime() != null)
                .collect(Collectors.toList());
    }

    public List<Rent> getRentsForCatalog(int id) {
        return getRents()
                .stream()
                .filter(rent -> rent.getCatalog().getId() == id)
                .collect(Collectors.toList());
    }

    public List<Rent> getUnfinishedRentsForCatalog(int id) {
        return getRentsForCatalog(id)
                .stream()
                .filter(rent -> rent.getReturnDateTime() == null)
                .collect(Collectors.toList());
    }

    public List<Rent> getFinishedRentsForCatalog(int id) {
        return getRentsForCatalog(id)
                .stream()
                .filter(rent -> rent.getReturnDateTime() != null)
                .collect(Collectors.toList());
    }

    public List<Rent> filterRents(String rentFilter) {
        return getRents()
                .stream()
                .filter(rent -> StringUtils.containsIgnoreCase(rent.toString(), rentFilter))
                .collect(Collectors.toList());
    }

    public List<Rent> filterUnfinishedRents(String rentFilter) {
        return getUnfinishedRents()
                .stream()
                .filter(rent -> StringUtils.containsIgnoreCase(rent.toString(), rentFilter))
                .collect(Collectors.toList());
    }

    public List<Rent> filterFinishedRents(String rentFilter) {
        return getFinishedRents()
                .stream()
                .filter(rent -> StringUtils.containsIgnoreCase(rent.toString(), rentFilter))
                .collect(Collectors.toList());
    }

    public List<Rent> filterRentsForClient(String username, String rentFilter) {
        return getRentsForClient(username)
                .stream()
                .filter(rent -> StringUtils.containsIgnoreCase(rent.toString(), rentFilter))
                .collect(Collectors.toList());
    }

    public List<Rent> filterUnfinishedRentsForClient(String username, String rentFilter) {
        return getUnfinishedRentsForClient(username)
                .stream()
                .filter(rent -> StringUtils.containsIgnoreCase(rent.toString(), rentFilter))
                .collect(Collectors.toList());
    }

    public List<Rent> filterFinishedRentsForClient(String username, String rentFilter) {
        return getFinishedRentsForClient(username)
                .stream()
                .filter(rent -> StringUtils.containsIgnoreCase(rent.toString(), rentFilter))
                .collect(Collectors.toList());
    }

    public List<Rent> filterRentsForCatalog(int id, String rentFilter) {
        return getRentsForCatalog(id)
                .stream()
                .filter(rent -> StringUtils.containsIgnoreCase(rent.toString(), rentFilter))
                .collect(Collectors.toList());
    }

    public List<Rent> filterUnfinishedRentsForCatalog(int id, String rentFilter) {
        return getUnfinishedRentsForCatalog(id)
                .stream()
                .filter(rent -> StringUtils.containsIgnoreCase(rent.toString(), rentFilter))
                .collect(Collectors.toList());
    }

    public List<Rent> filterFinishedRentsForCatalog(int id, String rentFilter) {
        return getFinishedRentsForCatalog(id)
                .stream()
                .filter(rent -> StringUtils.containsIgnoreCase(rent.toString(), rentFilter))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < getRents().size(); i++) {
            if (i == 0) {
                str = str.concat(getRents().get(i).toString() + "\n");
            } else {
                str = str.concat("\t   " + getRents().get(i).toString());
                if (i != getRents().size() - 1) {
                    str = str.concat("\n");
                }
            }
        }
        return "RentRepo[" + str + "]";
    }
}
