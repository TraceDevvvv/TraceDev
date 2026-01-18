package com.example.newsapp.service;

import com.example.newsapp.common.ConnectionException;
import com.example.newsapp.domain.News;
import com.example.newsapp.dto.NewsDTO;
import com.example.newsapp.repo.INewsRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Application service for managing news-related business logic.
 * It orchestrates interactions between the presentation layer (via DTOs)
 * and the data access layer (via repository) and applies business rules (via validator).
 * It also handles DTO to Entity and Entity to DTO conversions.
 */
public class NewsService {
    private final INewsRepository newsRepository;
    private final NewsValidator newsValidator;

    /**
     * Constructs a NewsService with necessary dependencies.
     *
     * @param newsRepository The data repository for News entities.
     * @param newsValidator The validator for NewsDTOs.
     */
    public NewsService(INewsRepository newsRepository, NewsValidator newsValidator) {
        this.newsRepository = newsRepository;
        this.newsValidator = newsValidator;
    }

    /**
     * Retrieves all news items, converting them from entities to DTOs.
     *
     * @return A list of NewsDTOs.
     * @throws ConnectionException if the repository encounters a connection issue.
     */
    public List<NewsDTO> getAllNews() throws ConnectionException {
        System.out.println("[Service] Calling getAllNews()");
        List<News> newsEntities = newsRepository.findAll();
        // Convert News entities to NewsDTOs
        return newsEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves details for a specific news item by ID, converting it to a DTO.
     *
     * @param id The ID of the news item to retrieve.
     * @return A NewsDTO containing the details, or null if not found.
     * @throws ConnectionException if the repository encounters a connection issue.
     */
    public NewsDTO getNewsDetails(String id) throws ConnectionException {
        System.out.println("[Service] Calling getNewsDetails(id: " + id + ")");
        News newsEntity = newsRepository.findById(id);
        if (newsEntity != null) {
            // Convert News entity to NewsDTO
            return convertToDTO(newsEntity);
        }
        return null;
    }

    /**
     * Validates a NewsDTO. This method is specifically for the initial validation check
     * before prompting for user confirmation, as implied by the sequence diagram.
     *
     * @param newsDTO The NewsDTO to validate.
     * @return true if the NewsDTO is valid according to business rules, false otherwise.
     */
    public boolean validateNewsData(NewsDTO newsDTO) {
        System.out.println("[Service] Calling validateNewsData() for NewsDTO: " + newsDTO.getId());
        return newsValidator.isValid(newsDTO);
    }

    /**
     * Updates an existing news item in the data store.
     * This method assumes validation has already passed and user confirmation has been obtained.
     * It converts the DTO to an entity and calls the repository's update method.
     *
     * @param newsDTO The NewsDTO containing the updated news data.
     * @return true if the update was successful, false otherwise (e.g., due to connection error or not found).
     * @throws ConnectionException if the repository encounters a connection issue during the update.
     */
    public boolean updateNews(NewsDTO newsDTO) throws ConnectionException {
        System.out.println("[Service] Calling updateNews() to persist for NewsDTO: " + newsDTO.getId());
        // Convert NewsDTO to News entity
        News newsEntity = convertToEntity(newsDTO);
        News updatedEntity = newsRepository.update(newsEntity);
        return updatedEntity != null;
    }

    /**
     * Helper method to convert a News entity to a NewsDTO.
     *
     * @param news The News entity to convert.
     * @return The corresponding NewsDTO.
     */
    private NewsDTO convertToDTO(News news) {
        if (news == null) return null;
        return new NewsDTO(
                news.getId(),
                news.getTitle(),
                news.getContent(),
                news.getPublicationDate(),
                news.getAuthor(),
                news.getStatus()
        );
    }

    /**
     * Helper method to convert a NewsDTO to a News entity.
     *
     * @param newsDTO The NewsDTO to convert.
     * @return The corresponding News entity.
     */
    private News convertToEntity(NewsDTO newsDTO) {
        if (newsDTO == null) return null;
        return new News(
                newsDTO.getId(),
                newsDTO.getTitle(),
                newsDTO.getContent(),
                newsDTO.getPublicationDate(),
                newsDTO.getAuthor(),
                newsDTO.getStatus()
        );
    }
}