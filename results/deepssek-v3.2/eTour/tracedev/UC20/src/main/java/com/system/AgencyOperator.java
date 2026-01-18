package com.system;

/**
 * Represents an agency operator user.
 */
public class AgencyOperator {
    private String id;
    private String name;

    public AgencyOperator(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    /**
     * Simulates a login operation.
     * @return true if login successful (assumed always true for simplicity)
     */
    public boolean login() {
        // In a real system, credentials would be validated.
        return true;
    }

    /**
     * Operator selects a refreshment point to work with.
     * @param point the selected refreshment point
     */
    public void selectRefreshmentPoint(RefreshmentPoint point) {
        System.out.println("Operator selected point: " + point.getName());
    }

    /**
     * Operator selects an image to upload.
     * @param image the selected image
     */
    public void selectImage(Image image) {
        System.out.println("Operator selected image with size: " + image.getSize());
    }

    /**
     * Operator confirms the current operation.
     * @return true if confirmation successful (always true for simulation)
     */
    public boolean confirmOperation() {
        System.out.println("Operator confirmed the operation.");
        return true;
    }

    /**
     * Operator cancels the current operation.
     * @return true if cancellation successful (always true for simulation)
     */
    public boolean cancelOperation() {
        System.out.println("Operator cancelled the operation.");
        return true;
    }
}