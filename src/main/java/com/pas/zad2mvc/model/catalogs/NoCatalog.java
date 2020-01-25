package com.pas.zad2mvc.model.catalogs;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS)
public class NoCatalog extends Catalog implements Serializable {
    public NoCatalog() {
        super(0, "", 0);
    }

    @Override
    public String toString() {
        return "NoCatalog(id: " + getId() + ", title: " + getTitle() + ", release year: " + getReleaseYear() + ")";
    }
}
