
package com.example.news.dto;

import com.example.news.model.News;

/**
 * Data Transfer Object for news submission forms.
 * Used to transfer data from the view to the controller and serv.
 */
public class NewsFormRequest {
    private String title;
    private String content;
    private String authorId;

    public NewsFormRequest() {
    }

    public NewsFormRequest(String title, String content, String authorId) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }

    /**
     * Converts this DTO to a News entity.
     * Note: While the CD suggests this method, the SD explicitly uses `News.create(request)`
     * from the service. This method might be used for other conversion scenarios,
     * but for the "Insert News" flow, `News.create` is prioritized as per SD.
     * This method is kept for completeness as per CD.
     *
     * @return A News entity populated with data from this request.
     */
    public News toNewsEntity() {
        News news = new News();
        news.title = this.title; // Directly access public field
        news.content = this.content; // Directly access public field
        news.authorId = this.authorId; // Directly access public field
        // publicationDate and status are typically set by the system/service
        return news;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthorId() {
        return authorId;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "NewsFormRequest{" +
               "title='" + title + '\'' +
               ", content='" + content + '\'' +
               ", authorId='" + authorId + '\'' +
               '}';
    }
}
