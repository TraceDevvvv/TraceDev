package com.example.controller;

import com.example.auth.AuthenticationService;
import com.example.dto.NewsDTO;
import com.example.models.News;
import com.example.repository.NewsRepository;
import com.example.validation.NewsValidator;
import com.example.validation.ValidationResult;
import com.example.view.NewsEditView;
import com.example.view.NewsListView;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for news operations.
 * Quality Requirement: The system shall respond to user interactions within 2 seconds.
 * Note: This class must ensure response time ≤ 2s.
 */
public class NewsController {
    private NewsRepository newsRepository;
    private NewsValidator newsValidator;
    private AuthenticationService authService;

    public NewsController(NewsRepository repository, NewsValidator validator, AuthenticationService auth) {
        this.newsRepository = repository;
        this.newsValidator = validator;
        this.authService = auth;
    }

    /**
     * Retrieves all news and converts them to DTOs.
     * @return list of NewsDTO.
     */
    public List<NewsDTO> listNews() {
        long startTime = System.currentTimeMillis();
        List<News> newsList = newsRepository.findAll();
        List<NewsDTO> dtoList = newsList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("listNews took " + duration + " ms");
        if (duration > 2000) {
            // Log warning if response time exceeds 2 seconds.
            System.err.println("Warning: Response time exceeded 2 seconds.");
        }
        return dtoList;
    }

    /**
     * Loads a single news item by ID and returns it as DTO.
     * @param id the news ID.
     * @return the NewsDTO, or null if not found.
     */
    public NewsDTO loadNews(Long id) {
        // Assume authentication already performed as per sequence diagram.
        return newsRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    /**
     * Processes the edit form submission.
     * @param newsDTO the updated news data.
     * @return a status message (simplified).
     */
    public String submitEditForm(NewsDTO newsDTO) {
        long startTime = System.currentTimeMillis();
        // Validate DTO
        ValidationResult validationResult = newsValidator.validate(newsDTO);
        if (!validationResult.isValid()) {
            // In a real scenario, we would pass errors to the view.
            return "Validation failed: " + String.join(", ", validationResult.getErrors());
        }

        // Convert DTO to entity
        News news = convertToEntity(newsDTO);
        news.setLastModified(new Date());

        // Update via repository
        try {
            News updated = newsRepository.update(news);
            long duration = System.currentTimeMillis() - startTime;
            System.out.println("submitEditForm took " + duration + " ms");
            if (duration > 2000) {
                System.err.println("Warning: Response time exceeded 2 seconds.");
            }
            return "News updated successfully, ID: " + updated.getId();
        } catch (RuntimeException e) {
            return "Update failed: " + e.getMessage();
        }
    }

    /**
     * Converts News entity to NewsDTO.
     */
    private NewsDTO convertToDTO(News news) {
        NewsDTO dto = new NewsDTO();
        dto.setId(news.getId());
        dto.setTitle(news.getTitle());
        dto.setContent(news.getContent());
        return dto;
    }

    /**
     * Converts NewsDTO to News entity.
     */
    private News convertToEntity(NewsDTO dto) {
        News news = new News();
        news.setId(dto.getId());
        news.setTitle(dto.getTitle());
        news.setContent(dto.getContent());
        news.setLastModified(new Date());
        return news;
    }

    // Helper methods for interaction with views (as per sequence diagram)
    public void showValidationErrors(NewsEditView view, List<String> errors) {
        view.showValidationErrors(errors);
    }

    public boolean showConfirmationDialog(NewsEditView view) {
        return view.showConfirmationDialog();
    }

    public void showSuccessMessage(NewsEditView view) {
        view.showSuccessMessage();
    }

    /**
     * Converts News to NewsDTO (named as per sequence diagram).
     */
    public NewsDTO convertNewsToNewsDTO(News news) {
        return convertToDTO(news);
    }

    /**
     * Converts a list of News to NewsDTO list (named as per sequence diagram).
     */
    public List<NewsDTO> convertToNewsDTOList(List<News> newsList) {
        return newsList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Converts NewsDTO to News (named as per sequence diagram).
     */
    public News convertNewsDTOToNews(NewsDTO dto) {
        return convertToEntity(dto);
    }

    /**
     * Activates editing functionality (as per sequence diagram).
     */
    public void activateEditingFunctionality() {
        System.out.println("Editing functionality activated.");
    }

    /**
     * Displays news list form (as per sequence diagram).
     */
    public void displayNewsListForm(NewsListView view, List<NewsDTO> newsList) {
        view.displayNewsList(newsList);
    }

    /**
     * Selects news by ID (as per sequence diagram).
     */
    public NewsDTO selectNews(Long id) {
        return loadNews(id);
    }

    /**
     * Displays edit form with data (as per sequence diagram).
     */
    public void displayEditFormWithData(NewsEditView view, NewsDTO newsDTO) {
        view.displayNewsForEdit(newsDTO);
    }

    /**
     * Shows news data for editing (as per sequence diagram).
     */
    public void showNewsDataForEditing(NewsEditView view, NewsDTO newsDTO) {
        view.displayNewsForEdit(newsDTO);
    }

    /**
     * Shows error "News not found" (as per sequence diagram).
     */
    public void showErrorNewsNotFound(NewsListView view) {
        view.showError("News not found");
    }

    /**
     * Modifies data in form (as per sequence diagram). This is a placeholder.
     */
    public void modifyDataInForm() {
        System.out.println("Data modified in form.");
    }

    /**
     * Submits form (as per sequence diagram). This is a placeholder.
     */
    public void submitForm() {
        System.out.println("Form submitted.");
    }

    /**
     * Creates NewsDTO from form data (as per sequence diagram). This is a placeholder.
     */
    public NewsDTO createNewsDTOFromFormData() {
        NewsDTO dto = new NewsDTO();
        dto.setId(1L);
        dto.setTitle("Form Title");
        dto.setContent("Form Content");
        return dto;
    }

    /**
     * Checks title and content rules (as per sequence diagram).
     */
    public ValidationResult checkTitleContentRules(NewsDTO dto) {
        return newsValidator.validate(dto);
    }

    /**
     * Displays validation errors (as per sequence diagram).
     */
    public void displayValidationErrors(NewsEditView view, List<String> errors) {
        view.showValidationErrors(errors);
    }

    /**
     * Confirms changes? (as per sequence diagram).
     */
    public boolean confirmChanges(NewsEditView view) {
        return view.showConfirmationDialog();
    }

    /**
     * Confirm operation (as per sequence diagram).
     */
    public void confirmOperation() {
        System.out.println("Operation confirmed.");
    }

    /**
     * Operation cancelled (as per sequence diagram).
     */
    public void operationCancelled(NewsEditView view) {
        view.cancelEdit();
    }

    /**
     * Displays error message (as per sequence diagram).
     */
    public void displayErrorMessage(NewsEditView view, String message) {
        view.showValidationErrors(java.util.Collections.singletonList(message));
    }

    /**
     * Handles database exception (as per sequence diagram).
     */
    public void handleDatabaseException(Exception e) {
        System.err.println("Database exception: " + e.getMessage());
    }
}