// Tourist.java
package com.etour.insertpreferencesite;

import java.util.Scanner;

/**
 * Represents a tourist who can interact with sites and bookmark them.
 * This class simulates the user's actions in the "InsertPreferenceSite" use case.
 */
public class Tourist {
    private String name;
    private BookmarkList bookmarkList;
    private Site currentLocation; // Represents the site where the tourist card is currently located.

    /**
     * Constructs a new Tourist object.
     *
     * @param name The name of the tourist.
     * @param bookmarkList The BookmarkList associated with this tourist.
     */
    public Tourist(String name, BookmarkList bookmarkList) {
        this.name = name;
        this.bookmarkList = bookmarkList;
        this.currentLocation = null; // Tourist starts without a specific location
    }

    /**
     * Returns the name of the tourist.
     *
     * @return The name of the tourist.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the current location of the tourist.
     * This simulates the "Entry conditions: The tourist card is in a particular site."
     *
     * @param site The Site object representing the tourist's current location.
     */
    public void setCurrentLocation(Site site) {
        this.currentLocation = site;
        System.out.println(name + " is now at: " + (site != null ? site.getName() : "No specific location"));
    }

    /**
     * Activates the feature for inserting the selected site from bookmarks.
     * This method guides the user through the process of adding the current site to bookmarks.
     *
     * @param scanner A Scanner object to read user input.
     * @param etourServer The ETOURServer instance to check for connection status.
     * @return true if the site was successfully added, false otherwise (e.g., server interruption, user cancellation).
     */
    public boolean activateInsertPreferenceSiteFeature(Scanner scanner, ETOURServer etourServer) {
        // Entry condition check
        if (currentLocation == null) {
            System.out.println("Error: " + name + "'s card is not in a particular site. Cannot add to bookmarks.");
            return false;
        }

        System.out.println("\n--- Activating 'Insert Preference Site' feature ---");
        System.out.println("Current location: " + currentLocation.getName());

        // 1. Activate the feature for the insertion of the selected site from the bookmarks.
        // (This step is represented by calling this method)

        // 2. Prompt the inclusion
        System.out.print("Do you want to add '" + currentLocation.getName() + "' to your bookmarks? (yes/no): ");
        String input = scanner.nextLine().trim().toLowerCase();

        if (!input.equals("yes")) {
            System.out.println("Insertion cancelled by user.");
            return false;
        }

        // 3. Confirm the input.
        System.out.print("Confirm adding '" + currentLocation.getName() + "' to bookmarks? (yes/no): ");
        input = scanner.nextLine().trim().toLowerCase();

        if (!input.equals("yes")) {
            System.out.println("Insertion cancelled by user confirmation.");
            return false;
        }

        // Check for server connection before attempting to insert
        if (!etourServer.isServerConnected()) {
            System.out.println("Interruption of connection to the server ETOUR. Cannot add bookmark.");
            return false;
        }

        // 4. Inserts the selected site in the list of bookmarks.
        boolean added = bookmarkList.addBookmark(currentLocation);

        // Exit conditions: The notification about the insertion site to favorites
        if (added) {
            System.out.println("Notification: Site '" + currentLocation.getName() + "' successfully inserted into favorites!");
        } else {
            System.out.println("Notification: Site '" + currentLocation.getName() + "' was already in favorites or could not be added.");
        }
        return added;
    }

    /**
     * Displays the tourist's current bookmark list.
     */
    public void viewBookmarks() {
        System.out.println("\n--- " + name + "'s Bookmarks ---");
        if (bookmarkList.isEmpty()) {
            System.out.println("No sites bookmarked yet.");
        } else {
            System.out.println(bookmarkList.toString());
        }
        System.out.println("--------------------------");
    }
}