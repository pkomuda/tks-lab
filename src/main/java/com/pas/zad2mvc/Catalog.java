package com.pas.zad2mvc;

import java.time.Year;
import java.util.Objects;
import java.util.UUID;

public abstract class Catalog
{
    private String id;
    private String title;
    private Year releaseYear;

    //region constructors
    public Catalog()
    {
        this.id = UUID.randomUUID().toString().replace("-", "");
    }
    public Catalog(String title, Year releaseYear)
    {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.title = title;
        this.releaseYear = releaseYear;
    }
    //endregion

    //region getters
    public String getId()
    {
        return id;
    }
    public String getTitle()
    {
        return title;
    }
    public int getReleaseYear()
    {
        if (releaseYear != null)
            return releaseYear.getValue();
        return 0;
    }
    //endregion

    //region setters
    public void setId(String id)
    {
        this.id = id;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public void setReleaseYear(int releaseYear)
    {
        this.releaseYear = Year.of(releaseYear);
    }
    //endregion

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Catalog catalog = (Catalog) o;
        return id.equals(catalog.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
