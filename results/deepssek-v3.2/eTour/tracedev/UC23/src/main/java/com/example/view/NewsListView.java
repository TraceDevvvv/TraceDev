package com.example.view;

import com.example.dto.NewsDTO;
import java.util.List;

/**
 * Boundary class for displaying a list of news.
 */
public class NewsListView {

    /**
     * Displays a list of news DTOs.
     */
    public void displayNewsList(List<NewsDTO> newsList) {
        System.out.println("=== News List ===");
        for (NewsDTO dto : newsList) {
            System.out.println("ID: " + dto.getId() + ", Title: " + dto.getTitle());
        }
        System.out.println("=================");
    }

    /**
     * Shows an error message.
     */
    public void showError(String message) {
        System.err.println("Error: " + message);
    }

    /**
     * Simulates form submission (as per requirement REQ-Flow-001).
     * @param id the news ID submitted.
     */
    public void submitForm(Long id) {
        System.out.println("Form submitted for news ID: " + id);
        // In a real application, this would trigger controller actions.
    }

    /**
     * Activates editing functionality (as per sequence diagram).
     */
    public void activateEditingFunctionality() {
        System.out.println("Editing functionality activated from ListView.");
    }

    /**
     * Displays news list form (as per sequence diagram).
     */
    public void displayNewsListForm(List<NewsDTO> newsList) {
        displayNewsList(newsList);
    }

    /**
     * Selects news (id) (as per sequence diagram).
     */
    public void selectNews(Long id) {
        System.out.println("News selected with ID: " + id);
    }

    /**
     * Displays edit form with data (as per sequence diagram). This is a placeholder.
     */
    public void displayEditFormWithData(NewsEditView editView, NewsDTO newsDTO) {
        editView.displayNewsForEdit(newsDTO);
    }

    /**
     * Shows error "News not found" (as per sequence diagram).
     */
    public void showErrorNewsNotFound() {
        showError("News not found");
    }
}