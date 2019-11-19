package com.pas.zad2mvc;

import com.pas.zad2mvc.repositories.CatalogRepository;
import com.pas.zad2mvc.repositories.UserRepository;

public class Main
{
    public static void main(String[] args)
    {
        CatalogRepository catalogList = new CatalogRepository();
        catalogList.addBook(1, "title1", "author1", 1990);
        catalogList.addBook(2, "title2", "author2", 2000);
        System.out.println(catalogList + "\n");

        UserRepository userRepository = new UserRepository();
        userRepository.addAdmin("admin1", true);
        userRepository.addManager("manager1", true);
        userRepository.addClient("client1", true);
        System.out.println(userRepository + "\n");
    }
}
