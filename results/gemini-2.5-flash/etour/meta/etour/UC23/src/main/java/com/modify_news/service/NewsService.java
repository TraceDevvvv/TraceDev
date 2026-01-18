package com.modify_news.service;

import com.modify_news.model.News;
import com.modify_news.repository.NewsRepository;
import com.modify_news.util.InputValidator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for managing news operations.
 * This class encapsulates the business logic for modifying, retrieving,
 * and validating news data. It interacts with the NewsRepository for data persistence.
 */
public class NewsService {
    private final NewsRepository newsRepository;

    /**
     * Constructs a NewsService with a given NewsRepository.
     *
     * @param newsRepository The repository to use for news data access.
     */
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    /**
     * Retrieves all news articles.
     *
     * @return A list of all News objects.
     */
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    /**
     * Finds a news article by its ID.
     *
     * @param newsId The ID of the news article to find.
     * @return An Optional containing the News object if found, or an empty Optional.
     */
    public Optional<News> getNewsById(String newsId) {
        return newsRepository.findById(newsId);
    }

    /**
     * Updates an existing news article with new data.
     * This method performs validation on the new data before attempting to save.
     *
     * @param newsId The ID of the news article to update.
     * @param newTitle The new title for the news.
     * @param newContent The new content for the news.
     * @param newAuthor The new author for the news.
     * @param newPublicationDate The new publication date for the news.
     * @return An Optional containing the updated News object if successful, or an empty Optional if validation fails or news not found.
     */
    public Optional<News> updateNews(String newsId, String newTitle, String newContent, String newAuthor, LocalDateTime newPublicationDate) {
        // 1. Validate input data
        if (!InputValidator.isValidNewsData(newTitle, newContent, newAuthor, newPublicationDate)) {
            System.err.println("Error: Invalid or insufficient data provided for news update.");
            return Optional.empty();
        }

        // 2. Retrieve the existing news
        Optional<News> existingNewsOptional = newsRepository.findById(newsId);

        if (existingNewsOptional.isEmpty()) {
            System.err.println("Error: News with ID '" + newsId + "' not found.");
            return Optional.empty();
        }

        News existingNews = existingNewsOptional.get();

        // 3. Update the news object with new data
        existingNews.setTitle(newTitle);
        existingNews.setContent(newContent);
        existingNews.setAuthor(newAuthor);
        existingNews.setPublicationDate(newPublicationDate);

        // 4. Save the updated news (repository handles update if ID exists)
        News updatedNews = newsRepository.save(existingNews);
        return Optional.of(updatedNews);
    }

    /**
     * Validates the provided news data.
     *
     * @param title The title of the news.
     * @param content The content of the news.
     * @param author The author of the news.
     * @param publicationDate The publication date of the news.
     * @return true if the data is valid, false otherwise.
     */
    public boolean validateNewsData(String title, String content, String author, LocalDateTime publicationDate) {
        return InputValidator.isValidNewsData(title, content, author, publicationDate);
    }
}