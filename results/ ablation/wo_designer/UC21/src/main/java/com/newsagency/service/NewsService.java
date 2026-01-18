package com.newsagency.service;

import com.newsagency.dao.NewsDAO;
import com.newsagency.model.News;
import java.time.LocalDateTime;

/**
 * Service layer for news operations.
 * Handles business logic, validation, and transaction flow.
 */
public class NewsService {
    private NewsDAO newsDAO = new NewsDAO();

    /**
     * Validates the news data.
     * @param news the news to validate
     * @return true if valid, false otherwise
     */
    private boolean validateNewsData(News news) {
        if (news == null) {
            return false;
        }
        if (news.getTitle() == null || news.getTitle().trim().isEmpty()) {
            return false;
        }
        if (news.getContent() == null || news.getContent().trim().isEmpty()) {
            return false;
        }
        if (news.getAuthor() == null || news.getAuthor().trim().isEmpty()) {
            return false;
        }
        if (news.getCategory() == null || news.getCategory().trim().isEmpty()) {
            return false;
        }
        // publicationDate can be null, will be set automatically if needed.
        return true;
    }

    /**
     * Simulates asking for confirmation.
     * @return true if confirmed, false otherwise
     */
    private boolean askForConfirmation() {
        // In a real UI, this would show a dialog and await user input.
        // For simulation, we assume confirmation is granted.
        return true;
    }

    /**
     * Main method to insert a news item following the use case steps.
     * @param news the news data entered by the operator (without ID and possibly without publicationDate)
     * @return an InsertResult indicating success or failure with a message
     */
    public InsertResult insertNews(News news) {
        long startTime = System.currentTimeMillis();

        // Step 5: System verifies the data entered.
        if (!validateNewsData(news)) {
            return new InsertResult(false, "Data validation failed. All fields (title, content, author, category) are required.", null);
        }

        // Ensure publication date is set to current time if not provided.
        if (news.getPublicationDate() == null) {
            news.setPublicationDate(LocalDateTime.now());
        }

        // Check server connection (simulating ETOUR server).
        if (!newsDAO.isConnectionActive()) {
            return new InsertResult(false, "Connection to the server ETOUR interrupts. Operation cancelled.", null);
        }

        // Step 6: System asks for confirmation of the transaction.
        // Step 7: Agency Operator confirms the operation of insertion.
        if (!askForConfirmation()) {
            return new InsertResult(false, "Operation cancelled by Agency Operator.", null);
        }

        try {
            // Step 8: System stores the data of the new news.
            News insertedNews = newsDAO.insert(news);
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            // Quality Requirement: Data validation and transaction confirmation within 2 seconds.
            if (duration > 2000) {
                return new InsertResult(true, "News inserted successfully, but quality requirement (2 seconds) not met. Time taken: " + duration + " ms.", insertedNews);
            }
            return new InsertResult(true, "News inserted successfully within " + duration + " ms.", insertedNews);
        } catch (Exception e) {
            return new InsertResult(false, "An error occurred while storing the news: " + e.getMessage(), null);
        }
    }

    /**
     * Result of the insert operation.
     */
    public static class InsertResult {
        private final boolean success;
        private final String message;
        private final News insertedNews;

        public InsertResult(boolean success, String message, News insertedNews) {
            this.success = success;
            this.message = message;
            this.insertedNews = insertedNews;
        }

        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
        public News getInsertedNews() { return insertedNews; }
    }
}