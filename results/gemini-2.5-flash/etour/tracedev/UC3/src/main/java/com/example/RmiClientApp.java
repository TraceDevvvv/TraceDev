package com.example;

import com.example.service.ICulturalHeritageAgencyManager;
import com.example.service.SessionManager;
import com.example.ui.CulturalHeritage;
import com.example.util.ErrorMessage;

import javax.swing.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Client application for RMI.
 * This class connects to the RMI server, retrieves the remote service,
 * and launches the CulturalHeritage UI.
 */
public class RmiClientApp {

    public static void main(String[] args) {
        // Set Nimbus Look and Feel for a nicer UI (optional)
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
            System.err.println("Could not set Nimbus LookAndFeel: " + e.getMessage());
        }

        // Run UI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                // 1. Connect to RMI Registry
                Registry registry = LocateRegistry.getRegistry(Home.HOST, Home.RMI_PORT);
                System.out.println("Client connected to RMI Registry at " + Home.HOST + ":" + Home.RMI_PORT);

                // 2. Lookup the remote object
                ICulturalHeritageAgencyManager manager = (ICulturalHeritageAgencyManager) registry.lookup(Home.RMI_SERVICE_NAME);
                System.out.println("Successfully looked up " + Home.RMI_SERVICE_NAME);

                // For demo purposes, ensure a user is "logged in"
                SessionManager sessionManager = new SessionManager();
                if (!sessionManager.isUserLoggedIn()) {
                    System.out.println("User not logged in. Simulating login for demo.");
                    SessionManager.setLoggedInUser(new com.example.service.AgencyOperator("admin", "admin")); // Mock login
                }

                // 3. Create main application frame and desktop pane
                JFrame mainFrame = new JFrame("Cultural Heritage Client Application");
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainFrame.setSize(1000, 700);
                mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Start maximized

                JDesktopPane desktopPane = new JDesktopPane();
                mainFrame.setContentPane(desktopPane);

                // 4. Create and display the CulturalHeritage internal frame
                CulturalHeritage culturalHeritageFrame = new CulturalHeritage(manager, desktopPane);
                desktopPane.add(culturalHeritageFrame);
                culturalHeritageFrame.setVisible(true);
                culturalHeritageFrame.setMaximum(true); // Maximize the internal frame
                culturalHeritageFrame.toFront();

                mainFrame.setVisible(true);

            } catch (java.rmi.ConnectException | java.rmi.NoSuchObjectException | java.rmi.UnmarshalException e) {
                // Specific RMI connection errors
                JOptionPane.showMessageDialog(null,
                        ErrorMessage.ERROR_RMI_CONNECTION + "\nEnsure the RMI Server is running on " + Home.HOST + ":" + Home.RMI_PORT + "\nDetails: " + e.getMessage(),
                        "RMI Connection Error",
                        JOptionPane.ERROR_MESSAGE);
                System.err.println("RMI Connection Error: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        ErrorMessage.ERROR_UNKNOWN + "\nAn unexpected error occurred during client startup: " + e.getMessage(),
                        "Application Error",
                        JOptionPane.ERROR_MESSAGE);
                System.err.println("Client startup exception: " + e.toString());
                e.printStackTrace();
            }
        });
    }
}