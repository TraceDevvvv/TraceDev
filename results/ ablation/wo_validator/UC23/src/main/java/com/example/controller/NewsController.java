package com.example.controller;

import com.example.model.News;
import com.example.service.NewsService;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controller for handling news-related requests.
 */
public class NewsController {
    private NewsService newsService;

    public NewsController(NewsService service) {
        this.newsService = service;
    }

    public List<News> showAllNews() {
        return newsService.getAllNews();
    }

    public News showEditForm(String newsId) {
        Optional<News> newsOpt = newsService.getNewsById(newsId);
        // Return the news if found, otherwise throw exception or return null.
        // For simplicity, we return null if not found (handled by caller).
        return newsOpt.orElse(null);
    }

    /**
     * Processes the edit form submission.
     * @param newsId The ID of the news to edit.
     * @param formData Map containing form fields.
     * @return A status message (e.g., "success" or "error").
     */
    public String processEditForm(String newsId, Map<String, String> formData) {
        try {
            // Get existing news.
            Optional<News> existingOpt = newsService.getNewsById(newsId);
            if (!existingOpt.isPresent()) {
                return "error:News not found";
            }
            News existing = existingOpt.get();
            // Create updated news object.
            News updatedNews = new News(
                    existing.getId(),
                    formData.getOrDefault("title", existing.getTitle()),
                    formData.getOrDefault("content", existing.getContent()),
                    formData.getOrDefault("author", existing.getAuthor())
            );
            // Validate data.
            if (!newsService.validateNewsData(updatedNews)) {
                return "error:Invalid data";
            }
            // Request confirmation is handled by UI, so we assume confirmation is given.
            // For this flow, we proceed directly to update.
            newsService.updateNews(newsId, updatedNews);
            return "success";
        } catch (Exception e) {
            return "error:" + e.getMessage();
        }
    }

    /**
     * Requests confirmation from the user (UI).
     * This method is a placeholder; actual confirmation is handled by UI.
     * @return true if confirmed, false otherwise.
     */
    public boolean requestConfirmation() {
        // In a real system, this would trigger a UI dialog and wait for response.
        // For simplicity, we assume confirmation is always given.
        return true;
    }

    /**
     * Confirms the update operation.
     * This method is a placeholder; actual confirmation logic is elsewhere.
     * @return true if confirmed.
     */
    public boolean confirmUpdate() {
        return true;
    }
}