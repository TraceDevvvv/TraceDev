package com.example.agency;

import java.util.List;

/**
 * View for displaying list of news articles.
 */
public class NewsListView {
    private EditNewsController controller;
    private List<NewsDTO> newsList;

    public NewsListView(EditNewsController controller) {
        this.controller = controller;
    }

    // Step 2: View all news
    public void displayAllNews() {
        newsList = controller.getNewsList();
        if (newsList != null && !newsList.isEmpty()) {
            System.out.println("=== News List ===");
            for (NewsDTO news : newsList) {
                System.out.println("ID: " + news.getId() + " | Title: " + news.getTitle());
            }
            System.out.println("=================");
        } else {
            System.out.println("No news articles found.");
        }
    }

    // Step 3: Select news for editing
    public void selectNews(int newsId) {
        System.out.println("Selected news with ID: " + newsId);
        NewsDTO news = controller.loadNews(newsId);
        if (news != null) {
            System.out.println("Loading news: " + news.getTitle());
            // In a real application, this would trigger the edit view
        } else {
            System.out.println("Failed to load news with ID: " + newsId);
        }
    }
}