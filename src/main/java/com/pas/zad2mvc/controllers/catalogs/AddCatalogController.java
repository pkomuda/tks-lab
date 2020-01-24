package com.pas.zad2mvc.controllers.catalogs;

import com.pas.zad2mvc.services.CatalogService;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ConversationScoped
public class AddCatalogController implements Serializable {
    @Inject
    private CatalogService catalogService;
    @Inject
    private Conversation conversation;
    private int id;
    private String title;
    private String author;
    private int releaseYear;
    private String director;
    private String format;

    public String addBook() {
        beginConversation();
        return "addBook";
    }

    public String addMovie() {
        beginConversation();
        return "addMovie";
    }

    public String confirmBook() {
        catalogService.addBook(id, title, author, releaseYear);
        endConversation();
        return "manager";
    }

    public String confirmMovie() {
        catalogService.addMovie(id, title, director, releaseYear, format);
        endConversation();
        return "manager";
    }

    public String cancel() {
        return "cancel";
    }

    public String cancelConfirm() {
        return "cancelConfirm";
    }

    //region conversation
    private void beginConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
        conversation.begin();
    }

    private void endConversation() {
        conversation.end();
    }
    //endregion

    //region getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getDirector() {
        return director;
    }

    public String getFormat() {
        return format;
    }
    //endregion

    //region setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setFormat(String format) {
        this.format = format;
    }
    //endregion
}
