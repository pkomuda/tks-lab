package com.pas.zad2mvc.data;

public class CatalogInfo
{
    private String title;
    private int releaseYear;

    public CatalogInfo(String title, int releaseYear)
    {
        this.title = title;
        this.releaseYear = releaseYear;
    }

    //region getters
    public String getTitle() { return title; }
    public int getReleaseYear() { return releaseYear; }
    //endregion

    //region setters
    public void setTitle(String title) { this.title = title; }
    public void setReleaseYear(int releaseYear) { this.releaseYear = releaseYear; }
    //endregion
}
