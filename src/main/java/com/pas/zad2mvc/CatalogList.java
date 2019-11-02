package com.pas.zad2mvc;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@Named(value = "catalogList")
@ApplicationScoped
public class CatalogList
{
    private List<Catalog> catalogs;

    public CatalogList()
    {
        this.catalogs = new ArrayList<>();
    }

    public List<Catalog> getCatalogs()
    {
        return catalogs;
    }

    public void setCatalogs(List<Catalog> catalogs)
    {
        this.catalogs = catalogs;
    }
}
