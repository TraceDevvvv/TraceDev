package com.news.presentation;

import com.news.domain.News;
import com.news.service.NewsService;
import java.util.List;

/**
 * Presentation layer controller.
 * Uses NewsService (as per class diagram).
 * Implements the interactions defined in the sequence diagram.
 */
public class NewsController {
    private NewsService service;

    public NewsController(NewsService service) {
        this.service = service;
    }

    /**
     * Retrieves all news (listNews from sequence diagram).
     * @return list of all news
     */
    public List<News> listNews() {
        return service.getAllNews();
    }

    /**
     * Deletes a news item by id.
     * @param newsId the id of the news to delete
     * @return a message indicating the result
     */
    public String deleteNews(int newsId) {
        try {
            boolean deleted = service.deleteNewsById(newsId);
            if (deleted) {
                return "Deletion successful";
            } else {
                return "News not found";
            }
        } catch (RuntimeException e) {
            // Handles database connection interruption or other runtime errors
            // as shown in the sequence diagram's alternative flow.
            return "Operation failed: Connection error";
        }
    }
}