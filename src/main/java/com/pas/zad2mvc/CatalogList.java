package com.pas.zad2mvc;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Named(value = "catalogList")
@ApplicationScoped
public class CatalogList
{
    private List<Catalog> catalogs;

    public CatalogList()
    {
        catalogs = new ArrayList<>();
    }

    public List<Catalog> getCatalogs()
    {
        return catalogs;
    }

    public void addBook(String title, String author, int releaseYear)
    {
        catalogs.add(new Book(title, author, Year.of(releaseYear)));
    }

    public void addMovie(String title, String director, int releaseYear)
    {
        catalogs.add(new Movie(title, director, Year.of(releaseYear)));
    }

    public Catalog get(String id)
    {
        for (Catalog catalog : catalogs)
        {
            if (catalog.getId().equals(id))
                return catalog;
        }
        throw new NoSuchElementException("No catalog with id: " + id + " found.");
    }

    public void updateBook(String id, String title, String author, int releaseYear)
    {
        get(id).setTitle(title);
        ((Book)get(id)).setAuthor(author);
        get(id).setReleaseYear(releaseYear);
    }

    public void updateMovie(String id, String title, String director, int releaseYear)
    {
        get(id).setTitle(title);
        ((Movie)get(id)).setDirector(director);
        get(id).setReleaseYear(releaseYear);
    }

    public boolean remove(String id)
    {
        for (Catalog catalog : catalogs)
        {
            if (catalog.getId().equals(id))
            {
                catalogs.remove(catalog);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString()
    {
        String str = "";
        for (int i=0; i<catalogs.size(); i++)
        {
            if (i == 0)
                str = str.concat(catalogs.get(i).toString() + "\n");
            else
            {
                str = str.concat("\t\t\t" + catalogs.get(i).toString());
                if (i != catalogs.size() - 1)
                    str = str.concat("\n");
            }
        }
        return "CatalogList[" + str + "]";
    }
}
