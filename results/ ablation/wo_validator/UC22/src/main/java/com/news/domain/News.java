package com.news.domain;

import java.util.Date;

/**
 * Domain class representing a News entity.
 * Corresponds to the News class in the class diagram.
 */
public class News {
    private int id;
    private String title;
    private String content;
    private Date publicationDate;

    // Constructors
    public News() {}

    public News(int id, String title, String content, Date publicationDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.publicationDate = publicationDate;
    }

    // Getters and setters as per class diagram
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date date) {
        this.publicationDate = date;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", publicationDate=" + publicationDate +
                '}';
    }
}