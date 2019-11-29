package com.pas.zad2mvc.model;

public class Book extends Catalog {
    private String author;

    public Book(int id, String title, String author, int releaseYear) {
        super(id, title, releaseYear);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book(id: " + getId() + ", title: " + getTitle() + ", author: " + author + ", release year: " + getReleaseYear() + ")";
    }
}
