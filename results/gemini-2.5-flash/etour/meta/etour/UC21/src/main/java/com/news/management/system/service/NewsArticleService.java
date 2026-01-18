package com.news.management.system.service;

import com.news.management.system.dao.NewsArticleDAO;
import com.news.management.system.exception.DatabaseOperationException;
import com.news.management.system.exception.InvalidNewsDataException;
import com.news.management.system.model.NewsArticle;
import com.news.management.system.util.ValidationUtil;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for managing NewsArticle operations, including validation and persistence.
 * This class orchestrates business logic, interacts with the DAO layer, and applies validation rules.
 */
public class NewsArticleService {

    private final NewsArticleDAO newsArticleDAO;

    /**
     * Constructs a NewsArticleService with a given NewsArticleDAO.
     *
     * @param newsArticleDAO The Data Access Object for NewsArticle persistence.
     */
    public NewsArticleService(NewsArticleDAO newsArticleDAO) {
        this.newsArticleDAO = newsArticleDAO;
    }

    /**
     * Inserts a new news article into the system.
     * This method performs validation and then delegates to the DAO for persistence.
     *
     * @param newsArticle The NewsArticle object to be inserted.
     * @throws InvalidNewsDataException if the news article data is invalid or insufficient.
     * @throws DatabaseOperationException if a database error occurs during the save operation.
     */
    public void insertNews(NewsArticle newsArticle) throws InvalidNewsDataException, DatabaseOperationException {
        // 1. Validate the news article data
        validateNewsArticle(newsArticle);

        // 2. Store the data of the new news
        try {
            newsArticleDAO.save(newsArticle);
            System.out.println("News article successfully inserted: " + newsArticle.getTitle());
        } catch (DatabaseOperationException e) {
            // Re-throw as a service-level exception or wrap if necessary
            throw new DatabaseOperationException("Failed to save news article to database: " + e.getMessage(), e);
        }
    }

    /**
     * Validates the fields of a NewsArticle object.
     * This method uses ValidationUtil to apply specific validation rules.
     *
     * @param newsArticle The NewsArticle object to validate.
     * @throws InvalidNewsDataException if any validation rule is violated.
     */
    public void validateNewsArticle(NewsArticle newsArticle) throws InvalidNewsDataException {
        if (newsArticle == null) {
            throw new InvalidNewsDataException("News article cannot be null.");
        }

        // Validate individual fields using ValidationUtil
        ValidationUtil.validateString(newsArticle.getTitle(), "Title", 5, 255, true);
        ValidationUtil.validateString(newsArticle.getContent(), "Content", 20, 65535, true);
        ValidationUtil.validateString(newsArticle.getAuthor(), "Author", 3, 100, true);
        ValidationUtil.validateDate(newsArticle.getPublicationDate(), "Publication Date", true);
        ValidationUtil.validateList(newsArticle.getCategories(), "Categories", false); // Categories can be empty
        ValidationUtil.validateList(newsArticle.getTags(), "Tags", false); // Tags can be empty

        // Additional business logic validation can be added here, e.g., checking for duplicate titles
        // For now, we assume UUID handles uniqueness for ID.
    }

    /**
     * Retrieves a news article by its ID.
     *
     * @param id The ID of the news article to retrieve.
     * @return The NewsArticle object if found.
     * @throws DatabaseOperationException if a database error occurs.
     * @throws InvalidNewsDataException if the news article with the given ID is not found.
     */
    public NewsArticle getNewsById(String id) throws DatabaseOperationException, InvalidNewsDataException {
        if (id == null || id.trim().isEmpty()) {
            throw new InvalidNewsDataException("News article ID cannot be null or empty.");
        }
        Optional<NewsArticle> newsArticle = newsArticleDAO.findById(id);
        return newsArticle.orElseThrow(() -> new InvalidNewsDataException("News article with ID " + id + " not found."));
    }

    /**
     * Retrieves all news articles in the system.
     *
     * @return A list of all NewsArticle objects.
     * @throws DatabaseOperationException if a database error occurs.
     */
    public List<NewsArticle> getAllNews() throws DatabaseOperationException {
        return newsArticleDAO.findAll();
    }
}