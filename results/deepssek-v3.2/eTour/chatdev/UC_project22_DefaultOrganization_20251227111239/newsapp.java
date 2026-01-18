'''
Main application entry point for News Deletion System
This class launches the application and handles authentication flow
'''
package com.chatdev.newsapp;
import javax.swing.SwingUtilities;
public class NewsApp {
    public static void main(String[] args) {
        // Use SwingUtilities to ensure thread safety for GUI
        SwingUtilities.invokeLater(() -> {
            try {
                // Initialize simulated database with sample data (shared instance)
                DatabaseManager dbManager = new DatabaseManager();
                dbManager.initializeDatabase();
                // First show login screen to satisfy entry condition
                LoginGUI loginGUI = new LoginGUI(dbManager);
                loginGUI.setVisible(true);
            } catch (Exception e) {
                System.err.println("Failed to start application: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}