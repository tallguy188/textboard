package org.example.domain;

public class Quote {

    public  int id;
    public String comment;
    public String author;

    public Quote(int id, String comment, String author) {
        this.id = id;
        this.comment = comment;
        this.author = author;
    }
}
