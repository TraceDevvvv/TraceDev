package com.example;

import com.example.controller.DeleteSiteFromPreferencesController;
import com.example.model.SiteFeatures;
import com.example.repository.BookmarkRepository;
import com.example.repository.IBookmarkRepository;
import com.example.serv.*;
import java.util.List;

/**
 * Main class to demonstrate the flow.
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies
        IDataSource dataSource = new DummyDataSource();
        IConnectionMonitor connectionMonitor = new ConnectionMonitorImpl();
        IBookmarkRepository repository = new BookmarkRepository(dataSource, connectionMonitor);
        SiteSelectionService selectionService = new SiteSelectionService();
        INotificationService notificationService = new NotificationService();

        // Create controller
        DeleteSiteFromPreferencesController controller = new DeleteSiteFromPreferencesController(
                repository, selectionService, notificationService);

        // Simulate tourist
        String touristId = "tourist123";
        SiteFeatures features = new SiteFeatures("cultural", List.of("historic"), 4);

        // Execute deletion flow
        boolean success = controller.executeDeletionFlow(touristId, features);
        System.out.println("Deletion flow result: " + success);
    }
}