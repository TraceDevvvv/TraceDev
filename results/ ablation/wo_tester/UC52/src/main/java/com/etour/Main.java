package com.etour;

import com.etour.model.Tourist;
import com.etour.model.Bookmark;
import com.etour.model.Site;
import com.etour.repository.BookmarkRepository;
import com.etour.repository.SiteRepository;
import com.etour.connection.ETourServerConnection;
import com.etour.controller.BookmarkManagementController;

/**
 * Main class to demonstrate the system.
 * Simulates the sequence diagram scenarios.
 */
public class Main {
    public static void main(String[] args) {
        // Setup repositories
        BookmarkRepository bookmarkRepo = new BookmarkRepository();
        SiteRepository siteRepo = new SiteRepository();

        // Setup server connection
        ETourServerConnection serverConn = new ETourServerConnection();
        serverConn.setConnected(true);

        // Create controller
        BookmarkManagementController controller = new BookmarkManagementController(
                bookmarkRepo, siteRepo, serverConn);

        // Create a tourist
        Tourist tourist = new Tourist("tourist1", "JohnDoe");
        tourist.setCurrentSiteId("site1");

        // Add a bookmark for the tourist
        Bookmark bookmark = new Bookmark("bookmark1", "site1", "tourist1", new java.util.Date());
        bookmarkRepo.addBookmark(bookmark);

        // Simulate main success scenario
        System.out.println("=== Main Success Scenario ===");
        controller.setConfirmation(true); // Tourist confirms
        try {
            boolean result = controller.requestRemoval("tourist1", "site1");
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // Simulate alternative flow: tourist cancels
        System.out.println("\n=== Alternative Flow: Tourist Cancels ===");
        controller.setConfirmation(false); // Tourist cancels
        try {
            boolean result = controller.requestRemoval("tourist1", "site2");
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // Simulate alternative flow: server disconnected (initial check)
        System.out.println("\n=== Alternative Flow: Server Disconnected (Initial) ===");
        serverConn.setConnected(false);
        controller.setConfirmation(true);
        try {
            boolean result = controller.requestRemoval("tourist1", "site1");
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // Simulate alternative flow: server disconnects during operation
        System