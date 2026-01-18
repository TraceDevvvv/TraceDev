package com.example;

import com.example.boundary.SearchForm;

/**
 * Main application class to simulate the sequence diagram flow.
 */
public class MainApp {
    public static void main(String[] args) {
        System.out.println("=== Starting Cultural Heritage Site Search Demo ===\n");

        SearchForm form = new SearchForm();
        // Simulate actor (Guest User) activating search
        form.activateSearch();
        // Simulate actor filling and submitting the form
        form.fillAndSubmitForm();

        System.out.println("\n=== Demo Completed ===");
    }
}