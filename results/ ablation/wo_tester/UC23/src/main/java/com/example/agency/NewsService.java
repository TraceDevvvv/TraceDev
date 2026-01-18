package com.example.agency;

import java.util.List;

/**
 * Interface for news service operations.
 */
public interface NewsService {
    News getNewsById(int newsId);
    boolean updateNews(News updatedNews);
    List<News> getAllNews();
}