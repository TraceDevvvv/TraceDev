package com.example.ui;

import com.example.model.News;
import com.example.controller.NewsController;
import com.example.viewmodel.EditNewsViewModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UI form for news operations.
 * This is a console simulation for demonstration.
 */
public class NewsForm {
    private EditNewsViewModel viewModel;
    private NewsController controller;

    public NewsForm(NewsController controller) {
        this.controller = controller;
    }

    /**
     * Displays a list of news.
     * @param newsList The list of news to display.
     */
    public void displayNewsList(List<News> newsList) {
        System.out.println("=== All News ===");
        for (News news : newsList) {
            System.out.println("ID: " + news.getId() + ", Title: " + news.getTitle());
        }
        System.out.println("================");
    }

    /**
     * Displays the edit form for a specific news.
     * @param news The news to edit.
     */
    public void displayEditForm(News news) {
        if (news == null) {
            System.out.println("News not found.");
            return;
        }
        viewModel = new EditNewsViewModel(news);
        System.out.println("=== Edit News ===");
        System.out.println("ID: " + viewModel.getNewsId());
        System.out.println("Title: " + viewModel.getTitle());
        System.out.println("Content: " + viewModel.getContent());
        System.out.println("Author: " + viewModel.getAuthor());
        System.out.println("================");
    }

    /**
     * Simulates getting form data from user input.
     * @return Map of form data.
     */
    public Map<String, String> getFormData() {
        // In a real UI, this would collect data from input fields.
        // For simulation, we return current viewModel as map.
        Map<String, String> data = new HashMap<>();
        if (viewModel != null) {
            data.put("title", viewModel.getTitle());
            data.put("content", viewModel.getContent());
            data.put("author", viewModel.getAuthor());
        }
        return data;
    }

    /**
     * Shows a confirmation dialog.
     * @return true if user confirms.
     */
    public boolean showConfirmationDialog() {
        System.out.println("Confirm update? (yes/no)");
        // Simulate user confirmation.
        // In real UI, this would be a modal dialog.
        return true; // Assume yes for simulation.
    }

    public void showSuccessMessage() {
        System.out.println("Success: News updated successfully.");
    }

    public void showErrorMessage(String message) {
        System.out.println("Error: " + message);
    }
}