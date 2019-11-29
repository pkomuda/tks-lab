package com.pas.zad2mvc.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Rent {
    private String id;
    private Client client;
    private Catalog catalog;
    private LocalDateTime rentDateTime;
    private LocalDateTime returnDateTime;

    //region constructors
    public Rent(String id) {
        this.id = id;
    }

    public Rent(Client client, Catalog catalog) {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.client = client;
        this.catalog = catalog;
    }
    //endregion

    //region getters
    public String getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public LocalDateTime getRentDateTime() {
        return rentDateTime;
    }

    public LocalDateTime getReturnDateTime() {
        return returnDateTime;
    }
    //endregion

    //region setters
    public void setId(String id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public void setRentDateTime(LocalDateTime rentDateTime) {
        this.rentDateTime = rentDateTime;
    }

    public void setRentDateTime(int year, int month, int day, int hour, int minute) {
        this.rentDateTime = LocalDateTime.of(year, month, day, hour, minute);
    }

    public void setReturnDateTime(LocalDateTime returnDateTime) {
        this.returnDateTime = returnDateTime;
    }

    public void setReturnDateTime(int year, int month, int day, int hour, int minute) {
        this.returnDateTime = LocalDateTime.of(year, month, day, hour, minute);
    }
    //endregion

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rent rent = (Rent) o;
        return id.equals(rent.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Rent(id: " + id + ", client: " + client.getUsername() + ", catalog: " + catalog + ", rent date: " + rentDateTime + ", return date: " + returnDateTime + ")";
    }
}
