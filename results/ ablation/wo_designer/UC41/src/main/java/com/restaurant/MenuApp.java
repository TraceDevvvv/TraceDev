package com.restaurant;

/**
 * Main application class to run the menu editing system.
 */
public class MenuApp {
    public static void main(String[] args) {
        // Entry point for the application.
        // In a real system, authentication would happen here.
        // Per entry conditions: operator is already authenticated.
        System.out.println("=== Restaurant Daily Menu Editor ===");
        MenuManager menuManager = new MenuManager();
        menuManager.startMenuEditing();
    }
}