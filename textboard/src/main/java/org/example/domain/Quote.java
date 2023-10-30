package org.example.domain;

public class Quote {

    private  int id;
    private String comment;
    private String author;

    public Quote(int id, String comment, String author) {
        this.id = id;
        this.comment = comment;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment){
        this.comment  = comment;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
