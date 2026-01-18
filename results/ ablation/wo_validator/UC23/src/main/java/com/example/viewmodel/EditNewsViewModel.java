package com.example.viewmodel;

import com.example.model.News;

/**
 * ViewModel for editing news.
 */
public class EditNewsViewModel {
    private String newsId;
    private String title;
    private String content;
    private String author;

    public EditNewsViewModel() {}

    public EditNewsViewModel(News news) {
        if (news != null) {
            this.newsId = news.getId();
            this.title = news.getTitle();
            this.content = news.getContent();
            this.author = news.getAuthor();
        }
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String id) {
        this.newsId = id;
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