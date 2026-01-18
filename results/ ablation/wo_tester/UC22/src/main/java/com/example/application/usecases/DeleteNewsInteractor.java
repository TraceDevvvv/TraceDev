package com.example.application.usecases;

import com.example.application.common.Result;
import com.example.core.domain.News;
import com.example.core.ports.repository.NewsRepository;
import com.example.infrastructure.database.DatabaseConnection;

import java.util.Optional;

/**
 * Interactor implementing the delete news use case.
 * Includes transaction handling as per sequence diagram.
 */
public class DeleteNewsInteractor implements DeleteNewsUseCase {
    private final NewsRepository newsRepository;
    // Dependency for atomic operation (transaction) as per class diagram.
    private final DatabaseConnection databaseConnection;

    public DeleteNewsInteractor(NewsRepository repository, DatabaseConnection connection) {
        this.newsRepository = repository;
        this.databaseConnection = connection;
    }

    @Override
    public Result execute(DeleteNewsCommand command) {
        // Validate news exists before deletion.
        Optional<News> newsOpt = newsRepository.findById(command.getNewsId());
        if (newsOpt.isEmpty()) {
            return Result.failure("News with id " + command.getNewsId() + " not found.", null);
        }

        try {
            // Execute delete within a transaction as per sequence diagram.
            databaseConnection.executeTransaction(() -> {
                newsRepository.delete(command.getNewsId());
                // Additional operations can be added here for atomicity.
            });
            return Result.success("News deleted successfully.");
        } catch (Exception e) {
            // Handle connection errors or other exceptions.
            return Result.failure("Failed to delete news due to: " + e.getMessage(), e);
        }
    }
}