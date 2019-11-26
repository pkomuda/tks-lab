package com.pas.zad2mvc;

import com.pas.zad2mvc.services.CatalogService;
import com.pas.zad2mvc.services.UserService;

public class Main {
    public static void main(String[] args) {
        CatalogService catalogService = new CatalogService();
        catalogService.addBook(1, "The Shining", "Stephen King", 1997);
        catalogService.addBook(2, "The Lord of the Rings", "J.R.R. Tolkien", 2015);
        catalogService.addBook(3, "What Could Possibly Go Wrong", "Jeremy Clarkson", 2015);
        catalogService.addMovie(4, "Trainspotting", "Danny Boyle", 1996, "DVD");
        catalogService.addMovie(5, "Pulp Fiction", "Quentin Tarantino", 1994, "Blu-ray");
        catalogService.addMovie(6, "The Graduate", "Mike Nichols", 1967, "VHS");
        System.out.println(catalogService + "\n");

        UserService userService = new UserService();
        userService.addAdmin("admin1", true, "Walter", "White");
        userService.addAdmin("admin2", true, "Jesse", "Pinkman");
        userService.addManager("manager1", true, "Marie", "Schrader");
        userService.addManager("manager2", true,"Jimmy", "McGill");
        userService.addClient("client1", true, "Kim", "Wexler");
        userService.addClient("client2", true, "Gustavo", "Fring");
        System.out.println(userService + "\n");
    }
}
