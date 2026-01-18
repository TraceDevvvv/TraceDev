package com.etour.view;

import com.etour.controller.PointOfRestController;
import com.etour.model.RefreshmentPoint;
import com.etour.model.RefreshmentPointDetails;
import com.etour.exception.ConnectionException;
import com.etour.exception.NotFoundException;
import java.util.List;

/**
 * View component for displaying refreshment points and details
 * No specific quality constraints defined for this use case
 */
public class RefreshmentPointView {
    private PointOfRestController pointController;

    public RefreshmentPointView(PointOfRestController pointController) {
        this.pointController = pointController;
    }

    public void displayRefreshmentPoints() {
        // Flow of Events 1: Displays list of refreshment points
        List<RefreshmentPoint> points = pointController.requestDisplayPoints();
        System.out.println("=== Refreshment Points ===");
        for (RefreshmentPoint point : points) {
            System.out.println(point.getPointId() + ": " + point.getName() + " (" + point.getType() + ")");
        }
        System.out.println("=========================");
    }

    public void displayPointDetails(RefreshmentPointDetails details) {
        System.out.println("=== Refreshment Point Details ===");
        System.out.println(details);
        System.out.println("===============================");
    }

    public void onPointSelected(String pointId) {
        // Flow of Events 2: Handles point selection
        System.out.println("Point selected: " + pointId);
        try {
            RefreshmentPoint point = pointController.selectPointOfRest(pointId);
            System.out.println("Retrieved point: " + point.getName());

            // Then view details (Flow of Events 4)
            RefreshmentPointDetails details = pointController.viewRefreshmentPointDetails(pointId);
            // Note: updateView is called by controller, so we don't need to call displayPointDetails here
        } catch (ConnectionException e) {
            System.out.println("Error: Connection to server interrupted - " + e.getMessage());
        } catch (NotFoundException e) {
            System.out.println("Error: " + e.getMessage() + " - Resource ID: " + e.getResourceId());
        }
    }

    public void updateView(RefreshmentPointDetails details) {
        // Flow of Events 4: Updates view with retrieved details
        displayPointDetails(details);
    }
}