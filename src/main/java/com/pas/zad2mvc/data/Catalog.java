package com.pas.zad2mvc.data;

import java.time.Year;
import java.util.Objects;

public abstract class Catalog
{
    private int id;
    private String title;
    private Year releaseYear;

    public Catalog(int id, String title, Year releaseYear)
    {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
    }

    //region getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getReleaseYear()
    {
        if (releaseYear != null)
            return releaseYear.getValue();
        return 0;
    }
    //endregion

    //region setters
    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setReleaseYear(int releaseYear) { this.releaseYear = Year.of(releaseYear); }
    //endregion

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Catalog catalog = (Catalog) o;
        return id == catalog.id;
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
