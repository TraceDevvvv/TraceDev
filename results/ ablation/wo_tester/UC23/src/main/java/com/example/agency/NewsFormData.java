package com.example.agency;

/**
 * Form data for editing news.
 */
public class NewsFormData {
    private int newsId;
    private String title;
    private String content;
    private String author;

    public NewsFormData() {}

    public NewsFormData(NewsDTO newsDTO) {
        this.newsId = newsDTO.getId();
        this.title = newsDTO.getTitle();
        this.content = newsDTO.getContent();
        this.author = newsDTO.getAuthor();
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
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

    /**
     * Converts form data to NewsDTO.
     */
    public NewsDTO toNewsDTO() {
        NewsDTO dto = new NewsDTO();
        dto.setId(this.newsId);
        dto.setTitle(this.title);
        dto.setContent(this.content);
        dto.setAuthor(this.author);
        // publishDate remains unchanged from original news
        return dto;
    }

    /**
     * Basic validation of form data.
     */
    public boolean isValid() {
        return title != null && !title.trim().isEmpty() &&
               content != null && !content.trim().isEmpty() &&
               author != null && !author.trim().isEmpty();
    }
}