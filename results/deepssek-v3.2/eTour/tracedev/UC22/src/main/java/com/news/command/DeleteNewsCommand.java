package com.news.command;

import com.news.repository.NewsRepository;
import com.news.exception.ConnectionException;

/**
 * Command class for deleting news as per class diagram.
 * Implements the execute pattern and holds status/error messages.
 */
public class DeleteNewsCommand {
    private Long newsId;
    private NewsRepository repository;
    private String statusMessage;
    private String lastError;

    public DeleteNewsCommand(Long newsId, NewsRepository repository) {
        this.newsId = newsId;
        this.repository = repository;
        this.statusMessage = "";
        this.lastError = "";
    }

    public boolean execute() {
        try {
            boolean success = repository.delete(newsId);
            if (success) {
                statusMessage = "Deletion successful";
                return true;
            } else {
                statusMessage = "Deletion failed";
                return false;
            }
        } catch (ConnectionException e) {
            statusMessage = "Connection error";
            lastError = e.getMessage();
            return false;
        }
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public String getLastError() {
        return lastError;
    }
}