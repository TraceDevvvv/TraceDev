package com.news.controller;

import com.news.entity.News;
import com.news.repository.NewsRepository;
import com.news.command.DeleteNewsCommand;
import java.util.List;

/**
 * Controller class that orchestrates news listing and deletion.
 * Uses NewsRepository and creates DeleteNewsCommand as per diagrams.
 */
public class NewsController {
    private NewsRepository newsRepository;

    public NewsController(NewsRepository repository) {
        this.newsRepository = repository;
    }

    public List<News> listAllNews() {
        return newsRepository.findAll();
    }

    public String confirmAndDelete(Long newsId) {
        DeleteNewsCommand command = new DeleteNewsCommand(newsId, newsRepository);
        boolean success = command.execute();
        if (success) {
            return "News deleted successfully";
        } else {
            return "Error: " + command.getStatusMessage() + ". " + command.getLastError();
        }
    }
}