package com.pas.zad2mvc;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.time.Year;

@Named(value = "book")
@ApplicationScoped
public class Book extends Catalog
{
    private String author;

    //region constructors
    public Book()
    {
        super();
    }
    public Book(String title, String author, Year releaseYear)
    {
        super(title, releaseYear);
        this.author = author;
    }
    //endregion

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    @Override
    public String toString()
    {
        return "Book(id: " + getId() + ", title: " + getTitle() + ", author: " + author + ", release year: " + getReleaseYear() + ")";
    }
}
