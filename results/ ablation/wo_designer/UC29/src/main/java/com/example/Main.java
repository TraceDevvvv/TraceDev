package com.example;

import com.example.system.SearchTagSystem;

/**
 * Entry point for the Search Tag System application.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Search Tag System ===\n");
        SearchTagSystem system = new SearchTagSystem();
        system.startTagEntry();
    }
}