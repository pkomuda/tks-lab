package com.pas.zad2mvc.data;

public class Movie extends Catalog {
    private String director;
    private String format;

    public Movie(int id, String title, String director, int releaseYear, String format) {
        super(id, title, releaseYear);
        this.director = director;
        this.format = format;
    }

    //region getters
    public String getDirector() {
        return director;
    }

    public String getFormat() {
        return format;
    }
    //endregion

    //region setters
    public void setDirector(String director) {
        this.director = director;
    }

    public void setFormat(String format) {
        this.format = format;
    }
    //endregion

    @Override
    public String toString() {
        return "Movie(id: " + getId() + ", title: " + getTitle() + ", director: " + director + ", release year: " + getReleaseYear() + ", format: " + format + ")";
    }
}
