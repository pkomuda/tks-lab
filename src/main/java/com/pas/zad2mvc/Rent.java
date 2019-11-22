package com.pas.zad2mvc;

import com.pas.zad2mvc.data.Catalog;
import com.pas.zad2mvc.data.Client;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.util.UUID;

@Named
@ApplicationScoped
public class Rent
{
    private String id;
    private Client client;
    private Catalog catalog;
    private LocalDateTime rentDateTime;
    private LocalDateTime returnDateTime;

    //region constructors
    public Rent() { this.id = UUID.randomUUID().toString().replace("-", ""); }
    public Rent(Client client, Catalog catalog, LocalDateTime rentDateTime, LocalDateTime returnDateTime)
    {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.client = client;
        this.catalog = catalog;
        this.rentDateTime = rentDateTime;
        this.returnDateTime = returnDateTime;
    }
    //endregion

    //region getters
    public String getId() { return id; }
    public Client getClient() { return client; }
    public Catalog getCatalog() { return catalog; }
    public LocalDateTime getRentDateTime() { return rentDateTime; }
    public LocalDateTime getReturnDateTime() { return returnDateTime; }
    //endregion

    //region setters
    public void setId(String id) { this.id = id; }
    public void setClient(Client client) { this.client = client; }
    public void setCatalog(Catalog catalog) { this.catalog = catalog; }
    public void setRentDateTime(LocalDateTime rentDateTime) { this.rentDateTime = rentDateTime; }
    public void setRentDateTime(int year, int month, int day, int hour, int minute)
    {
        this.rentDateTime = LocalDateTime.of(year, month, day, hour, minute);
    }
    public void setReturnDateTime(LocalDateTime returnDateTime) { this.returnDateTime = returnDateTime; }
    public void setReturnDateTime(int year, int month, int day, int hour, int minute)
    {
        this.returnDateTime = LocalDateTime.of(year, month, day, hour, minute);
    }
    //endregion
}
