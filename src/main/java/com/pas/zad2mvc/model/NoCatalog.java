package com.pas.zad2mvc.model;

public class NoCatalog extends Catalog {
    public NoCatalog() {
        super(0, "", 0);
    }

    @Override
    public String toString() {
        return "NoCatalog(id: " + getId() + ", title: " + getTitle() + ", release year: " + getReleaseYear() + ")";
    }
}
