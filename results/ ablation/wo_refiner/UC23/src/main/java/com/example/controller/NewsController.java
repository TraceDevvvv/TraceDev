package com.example.controller;

import java.util.List;

import com.example.model.OperationResult;
import com.example.service.NewsApplicationService;
import com.example.session.Session;
import com.example.dto.NewsDTO;

/**
 * Controller in the presentation layer (MVC).
 * Handles user interactions and delegates to application service.
 */
public class NewsController {
    private NewsApplicationService newsService;
    private Session session;

    public NewsController(NewsApplicationService service, Session session) {
        this.newsService = service;
        this.session = session;
    }

    public boolean checkSession() {
        return session.isLoggedIn();
    }

    public void activateEditing() {
        // Typically would prepare UI state, but for simplicity just ensure logged in
        if (!checkSession()) {
            throw new SecurityException("Operator not logged in.");
        }
    }

    public List<NewsDTO> loadAllNews() {
        if (!checkSession()) {
            throw new SecurityException("Operator not logged in.");
        }
        return newsService.getAllNews();
    }

    public NewsDTO selectNews(int newsId) {
        if (!checkSession()) {
            throw new SecurityException("Operator not logged in.");
        }
        return newsService.getNewsForEditing(newsId);
    }

    public OperationResult saveNews(NewsDTO modifiedNewsDto) {
        if (!checkSession()) {
            return new OperationResult(false, "Operator not logged in.", "AUTH_ERROR");
        }
        return newsService.modifyNews(modifiedNewsDto);
    }

    public OperationResult confirmSave(NewsDTO newsDto) {
        // In this implementation, confirmation is just a pass-through to save.
        // Additional confirmation logic could be added here.
        return saveNews(newsDto);
    }

    public void cancelEdit() {
        // Clean up any temporary state, etc.
        System.out.println("Edit cancelled by operator.");
    }
}