package com.pas.zad2mvc;

public class Main
{
    public static void main(String[] args)
    {
        CatalogList catalogList = new CatalogList();
        catalogList.addBook(1, "title1", "author1", 1990);
        catalogList.addBook(2, "title2", "author2", 2000);
        System.out.println(catalogList + "\n");

        UserList userList = new UserList();
        userList.addAdmin("admin1", true);
        userList.addManager("manager1", true);
        userList.addClient("client1", true);
        System.out.println(userList + "\n");
    }
}
