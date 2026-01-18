package com.news.data;

import com.news.domain.News;
import java.util.List;

/**
 * Repository interface as per the class diagram.
 * Defines the contract for data access operations.
 */
public interface NewsRepository {
    /**
     * Retrieves all news from the data store.
     * @return a list of all news
     */
    List<News> findAll();

    /**
     * Deletes a news item by its id.
     * @param id the news id
     * @return true if deletion was successful, false otherwise
     */
    boolean deleteById(int id);
}