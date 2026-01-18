package com.example.feedback;

import java.util.List;
import java.util.Scanner;

public class AgencyOperator {
    private FeedbackService feedbackService;
    private Scanner scanner;

    public AgencyOperator() {
        this.feedbackService = new FeedbackService();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("=== Agency Operator Feedback Management ===");
        boolean loggedIn = login();

        if (!loggedIn) {
            System.out.println("Login failed. Exiting.");
            return;
        }

        boolean running = true;
        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Search and Select Site");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    searchAndSelectSite();
                    break;
                case "2":
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private boolean login() {
        System.out.print("Enter username (demo: operator): ");
        String username = scanner.nextLine();
        System.out.print("Enter password (demo: password): ");
        String password = scanner.nextLine();

        // Dummy authentication
        if ("operator".equals(username) && "password".equals(password)) {
            System.out.println("Login successful.");
            return true;
        } else {
            System.out.println("Invalid credentials.");
            return false;
        }
    }

    private void searchAndSelectSite() {
        System.out.print("Enter site name or location to search (or press Enter for all): ");
        String query = scanner.nextLine();
        List<Site> sites = feedbackService.searchSites(query);

        if (sites.isEmpty()) {
            System.out.println("No sites found.");
            return;
        }

        System.out.println("\n--- Search Results ---");
        for (int i = 0; i < sites.size(); i++) {
            Site site = sites.get(i);
            System.out.println((i + 1) + ". " + site.getName() + " - " + site.getLocation());
        }

        System.out.print("Select a site by number: ");
        int siteIndex;
        try {
            siteIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (siteIndex < 0 || siteIndex >= sites.size()) {
                System.out.println("Invalid selection.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        Site selectedSite = sites.get(siteIndex);
        System.out.println("Selected site: " + selectedSite.getName());
        viewFeedbacksAndEdit(selectedSite.getId());
    }

    private void viewFeedbacksAndEdit(String siteId) {
        List<Feedback> feedbacks = feedbackService.getFeedbacksBySite(siteId);
        if (feedbacks.isEmpty()) {
            System.out.println("No feedbacks for this site.");
            return;
        }

        System.out.println("\n--- Feedbacks for Site ---");
        for (int i = 0; i < feedbacks.size(); i++) {
            Feedback feedback = feedbacks.get(i);
            System.out.println((i + 1) + ". [" + feedback.getId() + "] " + feedback.getComment());
        }

        System.out.print("Select a feedback to edit (by number): ");
        int feedbackIndex;
        try {
            feedbackIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (feedbackIndex < 0 || feedbackIndex >= feedbacks.size()) {
                System.out.println("Invalid selection.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        Feedback selectedFeedback = feedbacks.get(feedbackIndex);
        System.out.println("Selected feedback: " + selectedFeedback.getComment());

        System.out.println("Activate change function? (yes/no): ");
        String confirm = scanner.nextLine();
        if (!"yes".equalsIgnoreCase(confirm)) {
            System.out.println("Operation cancelled.");
            return;
        }

        System.out.print("Enter new comment: ");
        String newComment = scanner.nextLine();

        // System verifies data and asks for confirmation
        if (newComment == null || newComment.trim().isEmpty()) {
            System.out.println("Error: Comment cannot be empty. Operation aborted.");
            return;
        }

        System.out.print("Confirm change? (yes/no): ");
        String finalConfirm = scanner.nextLine();
        if (!"yes".equalsIgnoreCase(finalConfirm)) {
            System.out.println("Change cancelled.");
            return;
        }

        boolean success = feedbackService.updateFeedbackComment(selectedFeedback.getId(), newComment);
        if (success) {
            System.out.println("Feedback comment updated successfully.");
        } else {
            System.out.println("Failed to update feedback comment.");
        }
    }

    public static void main(String[] args) {
        AgencyOperator operator = new AgencyOperator();
        operator.start();
    }
}