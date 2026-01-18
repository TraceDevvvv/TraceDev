package com.example.ui;

import com.example.dto.RestPointSearchCriteria;
import java.util.Scanner;

/**
 * Simulates the user interface for searching points of rest.
 */
public class SearchForm {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the search form and collects user input.
     * @return populated search criteria
     */
    public RestPointSearchCriteria showForm() {
        System.out.println("=== Search for Points of Rest ===");
        RestPointSearchCriteria criteria = new RestPointSearchCriteria();

        System.out.print("Name (or part of name, press Enter to skip): ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) {
            criteria.setName(name);
        }

        System.out.print("Location (or part of location, press Enter to skip): ");
        String location = scanner.nextLine().trim();
        if (!location.isEmpty()) {
            criteria.setLocation(location);
        }

        System.out.print("Must have shelter? (yes/no/any): ");
        String shelterInput = scanner.nextLine().trim().toLowerCase();
        if (shelterInput.equals("yes")) {
            criteria.setHasShelter(true);
        } else if (shelterInput.equals("no")) {
            criteria.setHasShelter(false);
        }

        System.out.print("Must have water? (yes/no/any): ");
        String waterInput = scanner.nextLine().trim().toLowerCase();
        if (waterInput.equals("yes")) {
            criteria.setHasWater(true);
        } else if (waterInput.equals("no")) {
            criteria.setHasWater(false);
        }

        System.out.print("Minimum rating (1-5, press Enter to skip): ");
        String ratingInput = scanner.nextLine().trim();
        if (!ratingInput.isEmpty()) {
            try {
                int rating = Integer.parseInt(ratingInput);
                if (rating >= 1 && rating <= 5) {
                    criteria.setMinRating(rating);
                } else {
                    System.out.println("Invalid rating. Using default.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Using default.");
            }
        }

        return criteria;
    }
}