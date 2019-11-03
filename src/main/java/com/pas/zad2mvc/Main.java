package com.pas.zad2mvc;

public class Main
{
    public static void main(String[] args)
    {
        CatalogList catalogList = new CatalogList();
        catalogList.addBook("title1", "author1", 1990);
        catalogList.addBook("title2", "author2", 2000);
        System.out.println(catalogList + "\n");

        String bookId = catalogList.getCatalogs().get(0).getId();
        catalogList.updateBook(bookId, catalogList.get(bookId).getTitle(), "new", catalogList.get(bookId).getReleaseYear());
        System.out.println(catalogList + "\n\n");

        UserList userList = new UserList();
        userList.addAdmin("admin1", true);
        userList.addManager("manager1", false);
        userList.addClient("client1", false);
        System.out.println(userList + "\n");

        String clientId = userList.getUsers().get(2).getId();
        userList.update(clientId, "new", userList.get(clientId).isActive());
        userList.activate(clientId);
        System.out.println(userList);
    }
}
