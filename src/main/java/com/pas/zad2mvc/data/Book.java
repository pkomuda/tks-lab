package com.pas.zad2mvc.data;

import java.time.Year;

public class Book extends Catalog
{
    private String author;

    //region constructors
    public Book()
    {
        super();
    }
    public Book(int id, String title, String author, Year releaseYear)
    {
        super(id, title, releaseYear);
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
