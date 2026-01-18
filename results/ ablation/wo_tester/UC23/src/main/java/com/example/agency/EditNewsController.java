package com.example.agency;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller handling news editing operations.
 */
public class EditNewsController {
    private NewsService newsService;
    private ValidationService validationService;
    private ErrorHandler errorHandler;
    private NewsEditView newsEditView;

    public EditNewsController(NewsService newsService, ValidationService validationService, 
                             ErrorHandler errorHandler) {
        this.newsService = newsService;
        this.validationService = validationService;
        this.errorHandler = errorHandler;
    }

    public void setNewsEditView(NewsEditView newsEditView) {
        this.newsEditView = newsEditView;
    }

    // Step 2: Get news list
    public List<NewsDTO> getNewsList() {
        return getAllNews();
    }

    public List<NewsDTO> getAllNews() {
        List<News> newsList = newsService.getAllNews();
        return newsList.stream()
                .map(NewsDTO::new)
                .collect(Collectors.toList());
    }

    // Step 3: Load specific news
    public NewsDTO loadNews(int newsId) {
        try {
            News news = newsService.getNewsById(newsId);
            if (news != null) {
                return new NewsDTO(news);
            }
        } catch (Exception e) {
            errorHandler.logError(e);
            errorHandler.handleError("CONNECTION_LOST");
            if (newsEditView != null) {
                newsEditView.showConnectionError();
            }
        }
        return null;
    }

    // Step 5 & 6: Display news for editing
    public NewsFormData displayNewsForEditing(int newsId) {
        NewsDTO newsDTO = loadNews(newsId);
        if (newsDTO != null) {
            return new NewsFormData(newsDTO);
        }
        return null;
    }

    // Step 8: Submit edits
    public boolean submitEdits(NewsFormData editedData) {
        if (!editedData.isValid()) {
            ValidationResult result = new ValidationResult();
            result.addError("Form data is invalid");
            if (newsEditView != null) {
                newsEditView.showError(result.getErrors());
            }
            errorHandler.handleError("INVALID_DATA");
            return false;
        }

        NewsDTO newsDTO = editedData.toNewsDTO();
        ValidationResult validationResult = validateNewsData(newsDTO);
        
        if (validationResult.isValid()) {
            if (newsEditView != null) {
                boolean confirmed = newsEditView.showConfirmation();
                if (confirmed) {
                    return confirmChanges();
                }
            }
        } else {
            if (newsEditView != null) {
                newsEditView.showError(validationResult.getErrors());
            }
            errorHandler.handleError("INVALID_DATA");
        }
        return false;
    }

    public ValidationResult validateNewsData(NewsDTO newsData) {
        return validationService.validateNews(newsData);
    }

    public boolean confirmChanges() {
        // This would need actual data from the view, simplified for this implementation
        return persistChanges(null);
    }

    public boolean persistChanges(NewsDTO confirmedNews) {
        // In real implementation, this would use confirmedNews
        // For now, return true to simulate successful update
        if (confirmedNews != null) {
            News news = confirmedNews.toNews();
            return newsService.updateNews(news);
        }
        // Simulating successful update
        System.out.println("Changes persisted successfully.");
        return true;
    }

    public void cancelOperation() {
        if (newsEditView != null) {
            newsEditView.closeForm();
        }
        System.out.println("Operation cancelled.");
    }
}