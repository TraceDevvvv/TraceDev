package com.example;

import com.example.model.Tourist;

/**
 * Main class to run the application.
 * Creates a Tourist and triggers the view preferred site flow.
 */
public class Main {
    public static void main(String[] args) {
        // Create a tourist with a user ID
        Tourist tourist = new Tourist("tourist123");
        // Tourist selects the view preferred site feature
        tourist.selectViewPreferredSiteFeature();
    }
}