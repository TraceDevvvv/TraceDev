package com.newsagency.system.dto;

import com.newsagency.system.entity.News;
import java.text.SimpleDateFormat;

/**
 * Data Transfer Object for returning news data.
 */
public class NewsDTO {
    private Long id;
    private String title;
    private String content;
    private String createdAt;
    private String author;

    public NewsDTO() {
    }

    /**
     * Constructs a NewsDTO from a News entity.
     * @param news the entity
     */
    public NewsDTO(News news) {
        this.id = news.getId();
        this.title = news.getTitle();
        this.content = news.getContent();
        // Format date as string
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.createdAt = sdf.format(news.getCreatedAt());
        this.author = news.getAuthor();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}