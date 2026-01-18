package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.model.News;
import com.example.model.OperationResult;
import com.example.model.ValidationResult;
import com.example.dto.NewsDTO;
import com.example.repository.NewsRepository;
import com.example.repository.DatabaseException;

/**
 * Application Service coordinating the use case.
 * Implements business workflow and delegates to domain and infrastructure.
 */
public class NewsApplicationService {
    private NewsRepository newsRepository;
    private ErrorHandlerService errorHandler;

    public NewsApplicationService(NewsRepository repository, ErrorHandlerService errorHandler) {
        this.newsRepository = repository;
        this.errorHandler = errorHandler;
    }

    public List<NewsDTO> getAllNews() {
        List<News> allNews = newsRepository.findAll();
        return allNews.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public NewsDTO getNewsForEditing(int newsId) {
        return newsRepository.findById(newsId)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public OperationResult modifyNews(NewsDTO newsDto) {
        try {
            // Convert DTO to Entity
            News news = convertToEntity(newsDto);
            // Validate
            ValidationResult validationResult = news.validate();
            if (!validationResult.getIsValid()) {
                // Invoke error handling for invalid data
                String errorMsg = String.join(", ", validationResult.getErrors());
                errorHandler.invokeErroredUseCase(errorMsg);
                return new OperationResult(false, "Validation Failed", "VALIDATION_ERROR");
            }
            // Save
            News saved = newsRepository.save(news);
            // Return success
            return new OperationResult(true, "News updated successfully.");
        } catch (DatabaseException e) {
            // Handle database exception via error handler
            OperationResult errorResult = errorHandler.handleError(e);
            return errorResult;
        } catch (Exception e) {
            // Handle other exceptions
            OperationResult errorResult = errorHandler.handleError(e);
            return errorResult;
        }
    }

    private NewsDTO convertToDTO(News news) {
        NewsDTO dto = new NewsDTO();
        dto.setId(news.getId());
        dto.setTitle(news.getTitle());
        dto.setContent(news.getContent());
        dto.setAuthor(news.getAuthor());
        dto.setPublishDate(news.getPublishDate());
        dto.setLastModified(news.getLastModified());
        return dto;
    }

    private News convertToEntity(NewsDTO dto) {
        News news = new News(dto.getId(), dto.getTitle(), dto.getContent(), dto.getAuthor());
        news.setPublishDate(dto.getPublishDate());
        news.setLastModified(dto.getLastModified());
        return news;
    }
}