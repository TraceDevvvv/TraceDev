package com.example.news;

import com.example.news.repository.NewsRepository;
import com.example.news.service.NewsService;
import com.example.news.ui.NewsEditorUI;

/**
 * Main application entry point.
 * Run this class to execute the news editing system.
 */
public class Main {
    public static void main(String[] args) {
        // Initialize components
        NewsRepository newsRepository = new NewsRepository();
        NewsService newsService = new NewsService(newsRepository);
        NewsEditorUI ui = new NewsEditorUI(newsService);
        
        // Start the application
        ui.start();
    }
}