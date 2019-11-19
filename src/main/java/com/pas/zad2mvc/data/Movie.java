package com.pas.zad2mvc.data;

import java.time.Year;

public class Movie extends Catalog
{
    private String director;

    //region constructors
    public Movie()
    {
        super();
    }
    public Movie(int id, String title, String director, Year releaseYear)
    {
        super(id, title, releaseYear);
        this.director = director;
    }
    //endregion

    public String getDirector()
    {
        return director;
    }

    public void setDirector(String director)
    {
        this.director = director;
    }

    @Override
    public String toString()
    {
        return "Movie(id: " + getId() + ", title: " + getTitle() + ", director: " + director + ", release year: " + getReleaseYear() + ")";
    }
}
