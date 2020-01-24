package com.pas.zad2mvc.repositories;

import com.pas.zad2mvc.model.catalogs.Book;
import com.pas.zad2mvc.model.catalogs.Catalog;
import com.pas.zad2mvc.model.catalogs.Movie;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class CatalogRepository {
    private final String url = "jdbc:derby://localhost:1527/PasDB";

    public synchronized void addCatalog(Catalog catalog) {
        try (Connection connection = DriverManager.getConnection(url)) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO CATALOGTABLE VALUES(?,?,?,?,?,?,?)");
            preparedStatement.setInt(1, catalog.getId());
            preparedStatement.setString(2, catalog.getTitle());
            preparedStatement.setInt(3, catalog.getReleaseYear());
            if (catalog instanceof Book) {
                preparedStatement.setString(4, "BOOK");
                preparedStatement.setString(5, ((Book) catalog).getAuthor());
                preparedStatement.setString(6, null);
                preparedStatement.setString(7, null);
            } else if (catalog instanceof Movie) {
                preparedStatement.setString(4, "MOVIE");
                preparedStatement.setString(5, null);
                preparedStatement.setString(6, ((Movie) catalog).getDirector());
                preparedStatement.setString(7, ((Movie) catalog).getFormat());
            }
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized Catalog getCatalog(int id) {
        Catalog catalog = null;
        String catalogType, title, author, director, format;
        int releaseYear;
        try (Connection connection = DriverManager.getConnection(url)) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT TITLE, RELEASEYEAR, CATALOGTYPE, AUTHOR, DIRECTOR, FORMAT " +
                            "FROM CATALOGTABLE WHERE ID=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                title = resultSet.getString("TITLE");
                releaseYear = resultSet.getInt("RELEASEYEAR");
                catalogType = resultSet.getString("CATALOGTYPE");
                switch (catalogType) {
                    case "BOOK":
                        author = resultSet.getString("AUTHOR");
                        catalog = new Book(id, title, author, releaseYear);
                        break;
                    case "MOVIE":
                        director = resultSet.getString("DIRECTOR");
                        format = resultSet.getString("FORMAT");
                        catalog = new Movie(id, title, director, releaseYear, format);
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return catalog;
    }

    public synchronized void updateCatalog(int id, Catalog catalog) {
        try (Connection connection = DriverManager.getConnection(url)) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE CATALOGTABLE SET TITLE=?, RELEASEYEAR=?, AUTHOR=?, DIRECTOR=?, FORMAT=? WHERE ID=?");
            preparedStatement.setString(1, catalog.getTitle());
            preparedStatement.setInt(2, catalog.getReleaseYear());
            if (catalog instanceof Book) {
                preparedStatement.setString(3, ((Book) catalog).getAuthor());
                preparedStatement.setString(4, null);
                preparedStatement.setString(5, null);
            } else if (catalog instanceof Movie) {
                preparedStatement.setString(3, null);
                preparedStatement.setString(4, ((Movie) catalog).getDirector());
                preparedStatement.setString(5, ((Movie) catalog).getFormat());
            }
            preparedStatement.setInt(6, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized void removeCatalog(int id) {
        try (Connection connection = DriverManager.getConnection(url)) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM CATALOGTABLE WHERE ID=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized List<Catalog> getCatalogs() {
        List<Catalog> catalogs = new ArrayList<>();
        String catalogType, title, author, director, format;
        int id, releaseYear;
        try (Connection connection = DriverManager.getConnection(url)) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT ID, TITLE, RELEASEYEAR, CATALOGTYPE, AUTHOR, DIRECTOR, FORMAT FROM CATALOGTABLE");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("ID");
                title = resultSet.getString("TITLE");
                releaseYear = resultSet.getInt("RELEASEYEAR");
                catalogType = resultSet.getString("CATALOGTYPE");
                switch (catalogType) {
                    case "BOOK":
                        author = resultSet.getString("AUTHOR");
                        catalogs.add(new Book(id, title, author, releaseYear));
                        break;
                    case "MOVIE":
                        director = resultSet.getString("DIRECTOR");
                        format = resultSet.getString("FORMAT");
                        catalogs.add(new Movie(id, title, director, releaseYear, format));
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return catalogs;
    }

    public List<Book> getBooks() {
        return getCatalogs()
                .stream()
                .filter(catalog -> catalog instanceof Book)
                .map(catalog -> (Book) catalog)
                .collect(Collectors.toList());
    }

    public List<Movie> getMovies() {
        return getCatalogs()
                .stream()
                .filter(catalog -> catalog instanceof Movie)
                .map(catalog -> (Movie) catalog)
                .collect(Collectors.toList());
    }

    public List<Catalog> filterCatalogs(String catalogFilter) {
        return getCatalogs()
                .stream()
                .filter(catalog -> StringUtils.containsIgnoreCase(catalog.toString(), catalogFilter))
                .collect(Collectors.toList());
    }
    
    public List<Book> filterBooks(String bookFilter) {
        return getBooks()
                .stream()
                .filter(book -> StringUtils.containsIgnoreCase(book.toString(), bookFilter))
                .collect(Collectors.toList());
    }
    
    public List<Movie> filterMovies(String movieFilter) {
        return getMovies()
                .stream()
                .filter(movie -> StringUtils.containsIgnoreCase(movie.toString(), movieFilter))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < getCatalogs().size(); i++) {
            if (i == 0) {
                str = str.concat(getCatalogs().get(i).toString() + "\n");
            } else {
                str = str.concat("\t      " + getCatalogs().get(i).toString());
                if (i != getCatalogs().size() - 1) {
                    str = str.concat("\n");
                }
            }
        }
        return "CatalogRepo[" + str + "]";
    }
}
