package com.example.agency;

import java.util.List;

/**
 * Represents the ListView from the sequence diagram.
 */
public class ListView {
    private EditNewsController controller;
    private AgencyOperator currentOperator;

    public ListView(EditNewsController controller) {
        this.controller = controller;
    }

    public void setCurrentOperator(AgencyOperator operator) {
        this.currentOperator = operator;
    }

    // 1. activate editing functionality
    public void activateEditingFunctionality() {
        System.out.println("Editing functionality activated for operator: " + 
                           (currentOperator != null ? currentOperator.getName() : "unknown"));
    }

    // 2. view all news
    public void viewAllNews() {
        System.out.println("Viewing all news...");
        List<NewsDTO> newsList = controller.getAllNews();
        if (newsList != null && !newsList.isEmpty()) {
            System.out.println("=== All News ===");
            for (NewsDTO news : newsList) {
                System.out.println("ID: " + news.getId() + " | Title: " + news.getTitle());
            }
            System.out.println("================");
        } else {
            System.out.println("No news articles found.");
        }
    }

    // display news list
    public void displayNewsList() {
        System.out.println("Displaying news list.");
    }

    // 3. select news (newsId)
    public void selectNews(int newsId) {
        System.out.println("Selecting news with ID: " + newsId);
        NewsDTO news = controller.loadNews(newsId);
        if (news != null) {
            System.out.println("Selected: " + news.getTitle());
        } else {
            System.out.println("News selection failed for ID: " + newsId);
        }
    }
}