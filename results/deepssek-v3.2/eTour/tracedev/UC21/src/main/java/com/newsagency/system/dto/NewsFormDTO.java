package com.newsagency.system.dto;

/**
 * Data Transfer Object for the news form.
 */
public class NewsFormDTO {
    private String title;
    private String content;
    private String author;

    public NewsFormDTO() {
    }

    public NewsFormDTO(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}