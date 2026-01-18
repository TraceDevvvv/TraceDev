package com.example;

import com.example.serv.RefreshmentPointServiceImpl;
import com.example.views.RefreshmentPointCardView;

/**
 * Main application class simulating the Agency Operator's actions.
 */
public class Main {
    public static void main(String[] args) {
        // Simulate entry condition: Agency Operator HAS logged in
        System.out.println("Agency Operator logged in.");

        // Assume the operator has already viewed the list of points of rest
        // (result of use case SearchRefreshmentPoint)
        System.out.println("List of refreshment points displayed.");

        // Create service and view
        RefreshmentPointServiceImpl service = new RefreshmentPointServiceImpl();
        RefreshmentPointCardView view = new RefreshmentPointCardView(service);

        // Simulate Flow of Events:
        // Step 2 & 3: Operator selects a point and activates view function
        view.interactiveSelectAndView();

        view.close();
        System.out.println("\nOperation completed.");
    }
}