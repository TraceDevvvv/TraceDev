package com.example.adapters.models;

import com.example.core.domain.News;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Model holding a list of news items, supporting selection.
 */
public class NewsListModel {
    private final List<News> newsItems;

    public NewsListModel(List<News> newsList) {
        this.newsItems = newsList;
    }

    public List<News> getNewsItems() {
        return newsItems;
    }

    /**
     * Selects a news item by its id.
     *
     * @param newsId the id to search for
     * @return the selected News
     * @throws NoSuchElementException if not found
     */
    public News selectItem(String newsId) {
        return newsItems.stream()
                .filter(news -> news.getId().equals(newsId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("News not found: " + newsId));
    }
}