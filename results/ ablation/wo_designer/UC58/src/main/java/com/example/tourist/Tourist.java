package com.example.tourist;

/**
 * Represents the Tourist actor.
 */
public class Tourist {
    private String id;
    private String name;
    private boolean authenticated;
    private String currentLocation; // e.g., "Research Results", "List of Sites Visited", "List of Favorites"

    public Tourist(String id, String name) {
        this.id = id;
        this.name = name;
        this.authenticated = true; // Assume authenticated as per entry conditions
        this.currentLocation = "Research Results"; // Default location
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    /**
     * Tourist selects a site to view its details.
     * @param siteId the ID of the site to view
     * @param service the SiteCardService to use
     */
    public void viewSiteDetails(String siteId, SiteCardService service) {
        if (!isAuthenticated()) {
            System.out.println("Tourist is not authenticated. Cannot view site details.");
            return;
        }

        // Check if Tourist is in a valid location (as per entry conditions)
        String location = getCurrentLocation();
        if (!location.equals("Research Results") && !location.equals("List of Sites Visited") && !location.equals("List of Favorites")) {
            System.out.println("Tourist is not in a valid location. Cannot view site details.");
            return;
        }

        System.out.println("Tourist selecting site with ID: " + siteId);
        try {
            long startTime = System.currentTimeMillis();
            Site site = service.displaySiteCard(siteId);
            long endTime = System.currentTimeMillis();

            if (site != null) {
                System.out.println("Site details displayed:");
                System.out.println(site);
                System.out.println("Response time: " + (endTime - startTime) + " ms");
                if ((endTime - startTime) > 2000) {
                    System.out.println("Warning: Response time exceeds 2 seconds.");
                }
            } else {
                System.out.println("Site not found with ID: " + siteId);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (SiteDisplayException e) {
            System.out.println("Error displaying site: " + e.getMessage());
        }
    }
}