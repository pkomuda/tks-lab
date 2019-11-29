package com.pas.zad2mvc.controllers;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class AddRentController {
    @Inject
    private ClientPageController clientPageController;
    @Inject
    private LoginController loginController;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public String confirmRent(int catalogId) {
        clientPageController.getRentService().addRent(loginController.getUsername(), catalogId, year, month, day, hour, minute);
        clientPageController.endConversation();
        return "manager";
    }

    //region getters
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
    //endregion

    //region setters
    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
    //endregion

    @PostConstruct
    public void loadCatalogInfo() {
//        catalogId = clientPageController.getSelectedCatalogId();
    }
}
