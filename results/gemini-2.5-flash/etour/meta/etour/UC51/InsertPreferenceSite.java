// InsertPreferenceSite.java
package com.etour.insertpreferencesite;

import java.util.Scanner;

/**
 * Main class to demonstrate the "Insert Preference Site" use case.
 * This class orchestrates the interaction between Tourist, Site, BookmarkList, and ETOURServer
 * to simulate the process of a tourist adding a site to their bookmarks.
 */
public class InsertPreferenceSite {

    public static void main(String[] args) {
        // Initialize components
        Scanner scanner = new Scanner(System.in);
        BookmarkList touristBookmarks = new BookmarkList();
        ETOURServer etourServer = new ETOURServer();

        // Create a tourist
        Tourist alex = new Tourist("Alex", touristBookmarks);

        // Define some sites
        Site eiffelTower = new Site("Eiffel Tower", "Iconic wrought-iron lattice tower on the Champ de Mars in Paris.");
        Site louvreMuseum = new Site("Louvre Museum", "World's largest art museum and a historic monument in Paris.");
        Site colosseum = new Site("Colosseum", "An oval amphitheatre in the centre of the city of Rome, Italy.");
        Site sagradaFamilia = new Site("Sagrada Familia", "A large unfinished Roman Catholic minor basilica in Barcelona, Catalonia, Spain.");

        System.out.println("--- ETOUR System Simulation Started ---");

        // Scenario 1: Successful insertion
        System.out.println("\n--- Scenario 1: Successful Site Insertion ---");
        alex.setCurrentLocation(eiffelTower); // Entry condition: Tourist card is in a particular site
        alex.activateInsertPreferenceSiteFeature(scanner, etourServer);
        alex.viewBookmarks();

        // Scenario 2: Attempt to add the same site again (should not add duplicate)
        System.out.println("\n--- Scenario 2: Attempt to add an already bookmarked site ---");
        alex.setCurrentLocation(eiffelTower);
        alex.activateInsertPreferenceSiteFeature(scanner, etourServer);
        alex.viewBookmarks();

        // Scenario 3: Add a different site
        System.out.println("\n--- Scenario 3: Adding a different site ---");
        alex.setCurrentLocation(louvreMuseum);
        alex.activateInsertPreferenceSiteFeature(scanner, etourServer);
        alex.viewBookmarks();

        // Scenario 4: Server connection interruption
        System.out.println("\n--- Scenario 4: Server Connection Interruption ---");
        etourServer.setServerConnected(false); // Simulate server interruption
        alex.setCurrentLocation(colosseum);
        alex.activateInsertPreferenceSiteFeature(scanner, etourServer);
        alex.viewBookmarks();
        etourServer.setServerConnected(true); // Restore server connection

        // Scenario 5: Tourist not at a specific site
        System.out.println("\n--- Scenario 5: Tourist not at a specific site ---");
        alex.setCurrentLocation(null); // Tourist card not in a particular site
        alex.activateInsertPreferenceSiteFeature(scanner, etourServer);
        alex.viewBookmarks();

        // Scenario 6: User cancels during prompt
        System.out.println("\n--- Scenario 6: User cancels during prompt ---");
        alex.setCurrentLocation(sagradaFamilia);
        // Simulate user typing 'no' at the first prompt
        // For a real interactive system, this would require user input.
        // For this simulation, we'll just describe the outcome.
        System.out.println("Simulating user input 'no' at the first prompt for Sagrada Familia.");
        // In a real run, the user would type 'no' here.
        // alex.activateInsertPreferenceSiteFeature(scanner, etourServer);
        // To avoid blocking, we'll just print the expected outcome.
        System.out.println("Insertion cancelled by user (simulated).");
        alex.viewBookmarks(); // Bookmarks should remain unchanged

        System.out.println("\n--- ETOUR System Simulation Finished ---");

        scanner.close(); // Close the scanner to release system resources
    }
}