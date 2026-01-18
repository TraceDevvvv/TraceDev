package com.example.login;

import java.util.Scanner;

/**
 * Main class to demonstrate the login system using the defined classes
 * and simulating user interactions based on the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        // 1. Setup the application context (dependency injection)
        LoginView loginView = new LoginView();
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);
        LoginController loginController = new LoginController(loginView, userService);

        Scanner scanner = new Scanner(System.in);
        String username;
        String password;
        int choice;

        System.out.println("--- Login System Demonstration ---");

        do {
            System.out.println("\nChoose an action:");
            System.out.println("1. Activate Login (Successful)");
            System.out.println("2. Activate Login (Failed - Bad Credentials)");
            System.out.println("3. Activate Login (Failed - Connection Error)");
            System.out.println("4. Activate Login (Cancel)");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                choice = -1; // Invalid input
            }

            switch (choice) {
                case 1: // Successful login
                    loginView.activateLogin();
                    System.out.println("Simulating user input for successful login:");
                    // User -> LoginView : fillAndSubmitForm(username, password)
                    loginView.fillAndSubmitForm("admin", "password123");
                    break;
                case 2: // Failed login (bad credentials)
                    loginView.activateLogin();
                    System.out.println("Simulating user input for failed login (bad credentials):");
                    // User -> LoginView : fillAndSubmitForm(username, password)
                    loginView.fillAndSubmitForm("admin", "wrongpass");
                    break;
                case 3: // Failed login (connection error)
                    loginView.activateLogin();
                    System.out.println("Simulating user input for failed login (connection error):");
                    // To simulate connection error, we'll use a special username "network_error"
                    loginView.fillAndSubmitForm("network_error", "anypass");
                    break;
                case 4: // Cancel login
                    loginView.activateLogin();
                    System.out.println("Simulating user cancelling login:");
                    // User -> LoginView : cancelLogin()
                    loginView.cancelLogin();
                    break;
                case 0:
                    System.out.println("Exiting application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println("\n------------------------------------");
        } while (choice != 0);

        scanner.close();
    }
}