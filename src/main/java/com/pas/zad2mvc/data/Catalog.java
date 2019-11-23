package com.pas.zad2mvc.data;

import java.util.Objects;

public abstract class Catalog
{
    private int id;
    private CatalogInfo info;

    public Catalog(int id, String title, int releaseYear)
    {
        this.id = id;
        this.info = new CatalogInfo(title, releaseYear);
    }

    //region getters
    public int getId() { return id; }
    public String getTitle() { return info.getTitle(); }
    public int getReleaseYear() { return info.getReleaseYear(); }
    //endregion

    //region setters
    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { info.setTitle(title); }
    public void setReleaseYear(int releaseYear) { info.setReleaseYear(releaseYear); }
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
