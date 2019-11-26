package com.pas.zad2mvc.data;

import java.util.Objects;

public abstract class Catalog {
    private int id;
    private String title;
    private int releaseYear;

    public Catalog(int id, String title, int releaseYear) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
    }

    //region getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }
    //endregion

    //region setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
    //endregion

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Catalog catalog = (Catalog) o;
        return id == catalog.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
