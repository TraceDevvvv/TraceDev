package com.example;

import com.example.service.LoginService;
import java.util.Scanner;

/**
 * Main class to demonstrate handling incorrect login data.
 * This program simulates the use case described.
 */
public class Main {
    public static void main(String[] args) {
        LoginService loginService = new LoginService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Login System ===");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Handle login attempt.
        String result = loginService.handleIncorrectLogin(username, password);
        System.out.println(result);

        // For demonstration, try with correct credentials.
        System.out.println("\n--- Trying with correct credentials ---");
        loginService.addUser("test_user", "test_pass");
        result = loginService.handleIncorrectLogin("test_user", "test_pass");
        System.out.println(result);

        scanner.close();
    }
}