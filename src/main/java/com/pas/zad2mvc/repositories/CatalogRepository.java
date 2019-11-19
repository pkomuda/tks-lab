package com.pas.zad2mvc.repositories;

import com.pas.zad2mvc.data.Book;
import com.pas.zad2mvc.data.Catalog;
import com.pas.zad2mvc.data.Movie;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class CatalogRepository
{
    private List<Catalog> catalogs;

    public CatalogRepository()
    {
        catalogs = new ArrayList<>();
    }

    public List<Catalog> getCatalogs()
    {
        return catalogs;
    }

    public void addBook(int id, String title, String author, int releaseYear)
    {
        if (get(id) == null)
            catalogs.add(new Book(id, title, author, Year.of(releaseYear)));
    }

    public void addMovie(int id, String title, String director, int releaseYear)
    {
        if (get(id) == null)
            catalogs.add(new Movie(id, title, director, Year.of(releaseYear)));
    }

    public Catalog get(int id)
    {
        for (Catalog catalog : catalogs)
        {
            if (catalog.getId() == id)
                return catalog;
        }
        return null;
    }

    public void updateBook(int id, String title, String author, int releaseYear)
    {
        if (get(id) != null && get(id).getClass().getName().equals("com.pas.zad2mvc.data.Book"))
        {
            get(id).setTitle(title);
            ((Book) get(id)).setAuthor(author);
            get(id).setReleaseYear(releaseYear);
        }
    }

    public void updateMovie(int id, String title, String director, int releaseYear)
    {
        if (get(id) != null && get(id).getClass().getName().equals("com.pas.zad2mvc.data.Movie"))
        {
            get(id).setTitle(title);
            ((Movie) get(id)).setDirector(director);
            get(id).setReleaseYear(releaseYear);
        }
    }

    public boolean remove(int id)
    {
        for (Catalog catalog : catalogs)
        {
            if (catalog.getId() == id)
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
        for (int i = 0; i < catalogs.size(); i++)
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
