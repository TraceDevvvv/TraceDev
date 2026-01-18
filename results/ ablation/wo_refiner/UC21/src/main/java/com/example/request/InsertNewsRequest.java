package com.example.request;

/**
 * Request object for inserting news.
 */
public class InsertNewsRequest {

    private String title;
    private String content;
    private String author;

    public InsertNewsRequest(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // Getters
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getAuthor() { return author; }
}