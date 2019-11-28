package com.pas.zad2mvc;

import com.pas.zad2mvc.data.*;
import com.pas.zad2mvc.repositories.CatalogRepository;
import com.pas.zad2mvc.repositories.UserRepository;

public class Main {
    public static void main(String[] args) {
        CatalogRepository catalogRepo = new CatalogRepository();
        catalogRepo.addBook(1, "The Shining", "Stephen King", 1997);
        catalogRepo.addBook(2, "The Lord of the Rings", "J.R.R. Tolkien", 2015);
        catalogRepo.addBook(3, "What Could Possibly Go Wrong", "Jeremy Clarkson", 2015);
        catalogRepo.addMovie(4, "Trainspotting", "Danny Boyle", 1996, "DVD");
        catalogRepo.addMovie(5, "Pulp Fiction", "Quentin Tarantino", 1994, "Blu-ray");
        catalogRepo.addMovie(6, "The Graduate", "Mike Nichols", 1967, "VHS");
        System.out.println(catalogRepo + "\n");

        UserRepository userRepo = new UserRepository();
        userRepo.addAdmin("admin1", true, "Walter", "White");
        userRepo.addAdmin("admin2", true, "Jesse", "Pinkman");
        userRepo.addManager("manager1", true, "Marie", "Schrader");
        userRepo.addManager("manager2", true,"Jimmy", "McGill");
        userRepo.addClient("client1", true, "Kim", "Wexler");
        userRepo.addClient("client2", true, "Gustavo", "Fring");
        System.out.println(userRepo + "\n");

        Rent r1 = new Rent((Client) userRepo.getUser("client1"), catalogRepo.getCatalog(1));
        r1.setRentDateTime(2019, 11, 28, 18, 42);
        System.out.println(r1);
    }
}
