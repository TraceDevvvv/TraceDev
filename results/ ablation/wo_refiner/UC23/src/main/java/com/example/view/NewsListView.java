package com.example.view;

import java.util.List;

import com.example.controller.NewsController;
import com.example.dto.NewsDTO;

/**
 * Boundary class representing the list view UI.
 */
public class NewsListView {
    private NewsController controller;

    public NewsListView(NewsController controller) {
        this.controller = controller;
    }

    public void displayAllNews(List<NewsDTO> newsList) {
        System.out.println("=== All News ===");
        for (NewsDTO dto : newsList) {
            System.out.println("ID: " + dto.getId() + ", Title: " + dto.getTitle() + ", Author: " + dto.getAuthor());
        }
    }

    public void onActivateEditing() {
        // UI event handler for activating editing
        System.out.println("NewsListView: Editing functionality activated.");
        controller.activateEditing();
    }

    public void onNewsSelected(int newsId) {
        // UI event handler when a news item is selected
        System.out.println("NewsListView: News selected with ID " + newsId);
        NewsDTO dto = controller.selectNews(newsId);
        if (dto != null) {
            System.out.println("Selected news: " + dto.getTitle());
        } else {
            System.out.println("News not found.");
        }
    }
}