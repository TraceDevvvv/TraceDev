package com.newsapp.controller;

import com.newsapp.dto.NewsFormData;
import com.newsapp.entity.News;
import com.newsapp.repository.NewsRepository;
import com.newsapp.boundary.NewsForm;

/**
 * Controller for the "Insert News" use case.
 * Corresponds to the InsertNewsController control class in the class diagram.
 */
public class InsertNewsController {
    private NewsForm newsForm; // for interaction with the boundary
    private NewsRepository newsRepository;

    // Constructor with dependency injection
    public InsertNewsController(NewsForm newsForm, NewsRepository newsRepository) {
        this.newsForm = newsForm;
        this.newsRepository = newsRepository;
    }

    /**
     * Main execution method as per sequence diagram.
     * @param formData the data collected from the form
     */
    public void execute(NewsFormData formData) {
        // Step: validate data
        boolean isValid = validateData(formData);
        if (!isValid) {
            newsForm.showErrorNotification("Invalid data");
            return;
        }

        // Step: request confirmation
        boolean confirmed = requestConfirmation();
        if (!confirmed) {
            newsForm.showSuccessNotification("Cancelled");
            return;
        }

        // Step: create and save news
        News news = createNews(formData);
        boolean newsValid = news.validate();
        if (!newsValid) {
            newsForm.showErrorNotification("Created news entity is invalid");
            return;
        }

        // Persist via repository
        News savedNews = newsRepository.save(news);
        if (savedNews != null) {
            newsForm.showSuccessNotification("News placed successfully.");
        } else {
            newsForm.showErrorNotification("Failed to save news.");
        }
    }

    /**
     * Validates the form data (as per validateData method in class diagram).
     * @param formData the form data to validate
     * @return true if valid, false otherwise
     */
    private boolean validateData(NewsFormData formData) {
        // Simple validation: all fields must be non‑empty and publication date not null
        if (formData.getTitle() == null || formData.getTitle().trim().isEmpty()) {
            return false;
        }
        if (formData.getContent() == null || formData.getContent().trim().isEmpty()) {
            return false;
        }
        if (formData.getAuthor() == null || formData.getAuthor().trim().isEmpty()) {
            return false;
        }
        if (formData.getPublicationDate() == null) {
            return false;
        }
        return true;
    }

    /**
     * Requests user confirmation through the boundary (as per sequence diagram).
     * @return true if user confirms, false if cancels
     */
    private boolean requestConfirmation() {
        return newsForm.showConfirmationPrompt("Confirm insertion?");
    }

    /**
     * Creates a News entity from form data (as per createNews method in class diagram).
     * @param formData the form data
     * @return the created News entity
     */
    News createNews(NewsFormData formData) {
        // Create news entity with the provided data
        News news = new News(
                formData.getTitle(),
                formData.getContent(),
                formData.getAuthor(),
                formData.getPublicationDate()
        );
        // Additional initialization if needed
        news.setStatus("PUBLISHED");
        return news;
    }
}