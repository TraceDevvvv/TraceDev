package com.newsagency;

import com.newsagency.ui.NewsInsertForm;

/**
 * Main application entry point.
 * Runs the news insertion simulation.
 */
public class MainApplication {
    public static void main(String[] args) {
        System.out.println("Starting News Agency System...");
        System.out.println("Simulating logged-in Agency Operator.\n");
        NewsInsertForm form = new NewsInsertForm();
        form.displayForm();
    }
}