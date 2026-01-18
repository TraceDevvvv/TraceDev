package com.example;

/**
 * ConventionHistoryManager handles the business logic for managing convention history.
 * It ensures that the system meets the quality requirement of displaying history within 2 seconds.
 */
public class ConventionHistoryManager {
    private boolean agencyDesignated;
    private String selectedPointOfRest;

    /**
     * Constructor to initialize the manager with agency designation and selected point of rest.
     * @param agencyDesignated true if agency is designated, false otherwise.
     * @param selectedPointOfRest the selected point of rest.
     */
    public ConventionHistoryManager(boolean agencyDesignated, String selectedPointOfRest) {
        this.agencyDesignated = agencyDesignated;
        this.selectedPointOfRest = selectedPointOfRest;
    }

    /**
     * Checks if entry conditions are satisfied.
     * @return true if agency is designated and point of rest is selected, false otherwise.
     */
    public boolean areEntryConditionsMet() {
        return agencyDesignated && selectedPointOfRest != null && !selectedPointOfRest.isEmpty();
    }

    /**
     * Simulates uploading convention data from the selected point of rest.
     * @return a list of conventions related to the point of rest.
     * @throws IllegalStateException if entry conditions are not met.
     */
    public java.util.List<Convention> uploadConventionData() {
        if (!areEntryConditionsMet()) {
            throw new IllegalStateException("Entry conditions not met: Agency must be designated and point of rest selected.");
        }

        // Simulate a delay that is within the 2-second quality requirement.
        // In a real application, this would involve fetching from a database or external service.
        try {
            Thread.sleep(1500); // 1.5 seconds delay (within 2 seconds)
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Upload interrupted", e);
        }

        // Return simulated data
        java.util.List<Convention> conventions = new java.util.ArrayList<>();
        conventions.add(new Convention(1, "Convention Alpha", "2023-10-01", "Active", "Annual meeting for " + selectedPointOfRest));
        conventions.add(new Convention(2, "Convention Beta", "2023-09-15", "Completed", "Quarterly review for " + selectedPointOfRest));
        conventions.add(new Convention(3, "Convention Gamma", "2023-08-20", "Pending", "Upcoming event for " + selectedPointOfRest));
        conventions.add(new Convention(4, "Convention Delta", "2023-07-05", "Active", "Training session at " + selectedPointOfRest));
        conventions.add(new Convention(5, "Convention Epsilon", "2023-06-12", "Cancelled", "Cancelled due to logistic issues at " + selectedPointOfRest));
        return conventions;
    }

    /**
     * Simulates checking server connection to ETOUR.
     * @return true if connected, false otherwise.
     */
    public boolean checkServerConnection() {
        // Simulate connection check with 80% chance of success
        return Math.random() > 0.2;
    }

    /**
     * Entry point for a simple console-based test of the manager.
     */
    public static void main(String[] args) {
        // Simulate entry conditions
        boolean agencyDesignated = true;
        String selectedPointOfRest = "Restaurant A";

        ConventionHistoryManager manager = new ConventionHistoryManager(agencyDesignated, selectedPointOfRest);
        System.out.println("Entry conditions met: " + manager.areEntryConditionsMet());

        // Check server connection
        boolean connected = manager.checkServerConnection();
        System.out.println("Server connected: " + connected);
        if (!connected) {
            System.out.println("Connection to server ETOUR IS interrupted");
            return;
        }

        // Upload and display conventions
        try {
            System.out.println("Uploading convention data for " + selectedPointOfRest + "...");
            java.util.List<Convention> conventions = manager.uploadConventionData();
            System.out.println("Conventions history IS displayed:");
            for (Convention c : conventions) {
                System.out.println(c.getId() + "\t" + c.getName() + "\t" + c.getDate() + "\t" + c.getStatus() + "\t" + c.getDescription());
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

class Convention {
    private int id;
    private String name;
    private String date;
    private String status;
    private String description;

    public Convention(int id, String name, String date, String status, String description) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.status = status;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}