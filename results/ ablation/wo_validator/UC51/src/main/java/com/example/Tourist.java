package com.example;

/**
 * Represents a tourist who can bookmark sites.
 */
public class Tourist {
    private String touristId;
    private String name;

    public Tourist(String touristId, String name) {
        this.touristId = touristId;
        this.name = name;
    }

    public String getTouristId() {
        return touristId;
    }

    public String getName() {
        return name;
    }

    /**
     * Initiates bookmark insertion by calling the controller.
     * This method is called by the tourist to bookmark a site.
     */
    public void insertSiteToBookmarks(String siteId) {
        UseCaseController controller = new UseCaseController();
        boolean success = controller.insertBookmark(siteId);
        if (success) {
            System.out.println("Tourist " + name + " initiated bookmark for site " + siteId);
        } else {
            System.out.println("Bookmark operation failed or was cancelled.");
        }
    }

    /**
     * Confirms the insertion when prompted by the controller.
     * Returns true to simulate confirmation.
     */
    public void confirmInsertion() {
        System.out.println("Tourist " + name + " confirmed the bookmark insertion.");
    }
}