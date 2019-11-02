package com.pas.zad2mvc;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.time.Year;

@Named(value = "movie")
@ApplicationScoped
public class Movie extends Catalog
{
    private String director;

    //region constructors
    public Movie()
    {
        super();
    }
    public Movie(String title, String director, Year releaseYear)
    {
        super(title, releaseYear);
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
