package com.pas.zad2mvc.repositories;

import com.pas.zad2mvc.data.Book;
import com.pas.zad2mvc.data.Catalog;
import com.pas.zad2mvc.data.Movie;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class CatalogRepository
{
    private List<Catalog> catalogs = new ArrayList<>();

    public List<Catalog> getCatalogs() { return new ArrayList<>(catalogs); }

    public Catalog get(int id)
    {
        for (Catalog catalog : catalogs)
        {
            if (catalog.getId() == id)
                return catalog;
        }
        return null;
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

    public void addBook(int id, String title, String author, int releaseYear)
    {
        if (get(id) == null)
            catalogs.add(new Book(id, title, author, releaseYear));
    }

    public void addMovie(int id, String title, String director, int releaseYear, String format)
    {
        if (get(id) == null)
            catalogs.add(new Movie(id, title, director, releaseYear, format));
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

    public void updateMovie(int id, String title, String director, int releaseYear, String format)
    {
        if (get(id) != null && get(id).getClass().getName().equals("com.pas.zad2mvc.data.Movie"))
        {
            get(id).setTitle(title);
            ((Movie) get(id)).setDirector(director);
            get(id).setReleaseYear(releaseYear);
            ((Movie) get(id)).setFormat(format);
        }
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
        return "CatalogRepo[" + str + "]";
    }

    @PostConstruct
    private void addCatalogPool()
    {
        addBook(1, "The Shining", "Stephen King", 1997);
        addBook(2, "The Lord of the Rings", "J.R.R. Tolkien", 2015);
        addBook(3, "What Could Possibly Go Wrong", "Jeremy Clarkson", 2015);
        addMovie(4, "Trainspotting", "Danny Boyle", 1996, "DVD");
        addMovie(5, "Pulp Fiction", "Quentin Tarantino", 1994, "Blu-ray");
        addMovie(6, "The Graduate", "Mike Nichols", 1967, "VHS");
    }
}
