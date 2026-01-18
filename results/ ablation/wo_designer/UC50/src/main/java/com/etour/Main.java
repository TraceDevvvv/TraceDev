// Main application to simulate the use case
package com.etour;

import com.etour.controller.SiteController;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Welcome to ETOUR System ===");

        // Simulating authentication - tourist must be authenticated
        System.out.print("Enter Tourist ID: ");
        int touristId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        // Simulating authenticated session
        System.out.println("Tourist authenticated successfully.");

        // Tourist selects feature to display list of sites visited personal
        System.out.print("Do you want to view your visited sites? (yes/no): ");
        String choice = scanner.nextLine().trim().toLowerCase();

        if (choice.equals("yes") || choice.equals("y")) {
            SiteController controller = new SiteController(touristId);
            controller.displayVisitedSites();
        } else {
            System.out.println("Operation cancelled.");
        }

        scanner.close();
        System.out.println("Thank you for using ETOUR.");
    }
}