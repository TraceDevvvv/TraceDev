/**
 * A simple console‑based UI representing the search form.
 * In a real application, this would be a GUI or web form.
 */
package com.example.touristagency;

import java.util.List;
import java.util.Scanner;

public class TouristSearchForm {
    private TouristService touristService;

    public TouristSearchForm(TouristService touristService) {
        this.touristService = touristService;
    }

    /**
     * Displays the search form and processes the operator's input.
     */
    public void showForm() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Tourist Account Search ===");

        TouristSearchCriteria criteria = new TouristSearchCriteria();

        System.out.print("First Name (leave blank to ignore): ");
        String firstName = scanner.nextLine().trim();
        if (!firstName.isEmpty()) {
            criteria.setFirstName(firstName);
        }

        System.out.print("Last Name (leave blank to ignore): ");
        String lastName = scanner.nextLine().trim();
        if (!lastName.isEmpty()) {
            criteria.setLastName(lastName);
        }

        System.out.print("Email (leave blank to ignore): ");
        String email = scanner.nextLine().trim();
        if (!email.isEmpty()) {
            criteria.setEmail(email);
        }

        System.out.print("Phone (leave blank to ignore): ");
        String phone = scanner.nextLine().trim();
        if (!phone.isEmpty()) {
            criteria.setPhone(phone);
        }

        System.out.print("Nationality (leave blank to ignore): ");
        String nationality = scanner.nextLine().trim();
        if (!nationality.isEmpty()) {
            criteria.setNationality(nationality);
        }

        System.out.print("Passport Number (leave blank to ignore): ");
        String passportNumber = scanner.nextLine().trim();
        if (!passportNumber.isEmpty()) {
            criteria.setPassportNumber(passportNumber);
        }

        // Simulate form submission and processing
        System.out.println("\nProcessing search request...");
        List<Tourist> results = touristService.searchTourists(criteria);

        // Display results
        if (results.isEmpty()) {
            System.out.println("No tourist accounts found matching the criteria.");
        } else {
            System.out.println("Found " + results.size() + " tourist account(s):");
            for (Tourist t : results) {
                System.out.println(t);
            }
        }
    }
}