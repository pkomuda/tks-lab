package com.pas.zad2mvc;

import com.pas.zad2mvc.repositories.CatalogRepository;
import com.pas.zad2mvc.repositories.UserRepository;

public class Main
{
    public static void main(String[] args)
    {
        CatalogRepository catalogRepo = new CatalogRepository();
        catalogRepo.addBook(1, "title1", "author1", 1990);
        catalogRepo.addBook(2, "title2", "author2", 2000);
        System.out.println(catalogRepo + "\n");

        UserRepository userRepo = new UserRepository();
        userRepo.addAdmin("admin1", true);
        userRepo.addManager("manager1", true);
        userRepo.addClient("client1", true);
        System.out.println(userRepo + "\n");
    }
}
