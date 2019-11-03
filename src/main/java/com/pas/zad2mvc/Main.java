package com.pas.zad2mvc;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        CatalogList catalogList = new CatalogList();
//        Book book = new Book();
//        book.setTitle(scanner.nextLine());
//        book.setAuthor(scanner.nextLine());
//        book.setReleaseYear(scanner.nextInt());
//        System.out.println(book);
//        catalogList.addBook(book.getTitle(), book.getAuthor(), book.getReleaseYear());
        catalogList.addBook("tytul1", "autor1", 2000);
        catalogList.addBook("tytul2", "autor2", 2010);
        System.out.println(catalogList);
    }
}
