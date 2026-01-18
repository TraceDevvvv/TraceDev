package com.example;

/**
 * Represents the user's work area UI after successful login.
 */
public class UserWorkArea {

    /**
     * Displays the work area for the given user.
     * @param user the authenticated user
     */
    public void display(User user) {
        System.out.println("=== User Work Area ===");
        System.out.println("Welcome, " + user.getUsername() + "!");
        System.out.println("Role: " + user.getRole());
        System.out.println("Work area displayed successfully.");
    }
}