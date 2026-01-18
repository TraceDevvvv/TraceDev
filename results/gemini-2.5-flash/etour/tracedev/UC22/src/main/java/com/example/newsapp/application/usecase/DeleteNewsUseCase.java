package com.example.newsapp.application.usecase;

import com.example.newsapp.data.repository.INewsRepository;
import com.example.newsapp.domain.model.News;
import com.example.newsapp.infrastructure.exception.NetworkException;

import java.util.List;

/**
 * Application layer use case for deleting news.
 * Orchestrates interaction between the presentation layer and the data layer.
 */
public class DeleteNewsUseCase {
    private INewsRepository newsRepository;

    /**
     * Constructs a DeleteNewsUseCase with a dependency on INewsRepository.
     * @param newsRepository The repository responsible for news data access.
     */
    public DeleteNewsUseCase(INewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    /**
     * Retrieves a list of all available news articles.
     * Delegates the call to the news repository.
     * @return A list of News objects.
     */
    public List<News> getNewsList() {
        System.out.println("DeleteNewsUseCase: Requesting news list.");
        return newsRepository.findAll();
    }

    /**
     * Deletes a specific news article by its ID.
     * Supports REQ-EX-3 (NetworkException handling).
     * @param newsId The ID of the news article to delete.
     * @return true if deletion was successful, false otherwise.
     * @throws NetworkException if a network error occurs during the deletion process.
     */
    public boolean deleteNews(String newsId) throws NetworkException {
        System.out.println("DeleteNewsUseCase: Attempting to delete news with ID: " + newsId);
        // The repository's delete method can throw NetworkException
        // This use case re-throws it to the caller (e.g., NewsController)
        return newsRepository.delete(newsId);
    }
}