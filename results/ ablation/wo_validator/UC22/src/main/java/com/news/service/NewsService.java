package com.news.service;

import com.news.data.NewsRepository;
import com.news.domain.News;
import java.util.List;

/**
 * Service layer class that contains business logic.
 * Depends on NewsRepository (as per class diagram).
 */
public class NewsService {
    private NewsRepository newsRepository;

    public NewsService(NewsRepository repository) {
        this.newsRepository = repository;
    }

    /**
     * Retrieves all news via the repository.
     * @return list of all news
     */
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    /**
     * Deletes a news item by id.
     * @param id the news id
     * @return true if deletion succeeded, false if news not found
     * @throws RuntimeException if a database error occurs (e.g., connection lost)
     */
    public boolean deleteNewsById(int id) {
        // The repository will throw an exception on database error,
        // which will propagate up as per the sequence diagram's alternative flow.
        return newsRepository.deleteById(id);
    }
}