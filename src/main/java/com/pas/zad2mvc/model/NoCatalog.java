package com.pas.zad2mvc.model;

import java.io.Serializable;

public class NoCatalog extends Catalog implements Serializable {
    public NoCatalog() {
        super(0, "", 0);
    }

    @Override
    public String toString() {
        return "NoCatalog(id: " + getId() + ", title: " + getTitle() + ", release year: " + getReleaseYear() + ")";
    }
}
