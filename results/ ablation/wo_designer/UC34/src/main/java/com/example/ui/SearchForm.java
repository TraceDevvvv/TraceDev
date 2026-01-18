package com.example.ui;

import com.example.model.SearchCriteria;
import java.util.Scanner;

/**
 * Represents the search form displayed to the user.
 */
public class SearchForm {
    private Scanner scanner;

    public SearchForm() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the form and captures user input.
     * @return SearchCriteria object filled with user inputs.
     */
    public SearchCriteria displayAndCapture() {
        System.out.println("=== Cultural Heritage Search ===");
        System.out.print("Enter keyword (or press Enter to skip): ");
        String keyword = scanner.nextLine().trim();

        System.out.print("Enter category (e.g., Museum, Historical, Archaeological, Religious) or press Enter: ");
        String category = scanner.nextLine().trim();
        if (category.isEmpty()) category = null;

        Integer maxDistance = null;
        System.out.print("Maximum distance from you in km (optional, press Enter to skip): ");
        String distInput = scanner.nextLine().trim();
        if (!distInput.isEmpty()) {
            try {
                maxDistance = Integer.parseInt(distInput);
                if (maxDistance < 0) {
                    System.out.println("Invalid distance, setting to null.");
                    maxDistance = null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, distance filter will be ignored.");
            }
        }

        return new SearchCriteria(keyword, category, maxDistance);
    }

    public void close() {
        scanner.close();
    }
}