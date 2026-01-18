package com.example;

import com.example.view.LoginForm;

/**
 * Main class to demonstrate the logout flow as per the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Starting logout sequence demo...");
        // Simulate a registered user clicking the logout button on the LoginForm
        LoginForm form = new LoginForm();
        form.clicksLogoutButton(); // Use the exact sequence diagram message name
        System.out.println("Logout sequence demo completed.");
        // Simulate the note from sequence diagram
        System.out.println("Note: User can now re-login to the system");
    }
}