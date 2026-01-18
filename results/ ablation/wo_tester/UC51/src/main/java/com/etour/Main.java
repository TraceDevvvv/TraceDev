package com.etour;

import com.etour.model.Tourist;
import com.etour.model.Site;
import com.etour.datasource.DataSource;
import com.etour.repository.SiteRepository;
import com.etour.repository.SiteRepositoryImpl;
import com.etour.controller.InsertSiteController;
import com.etour.ui.MobileAppUI;

/**
 * Main class to run the application.
 * Simulates the sequence diagram flow.
 */
public class Main {
    public static void main(String[] args) {
        // Setup data source and repository
        DataSource dataSource = new DataSource("jdbc:etour:localhost:3306/etourdb");
        SiteRepository repository = new SiteRepositoryImpl(dataSource);
        InsertSiteController controller = new InsertSiteController(repository);
        MobileAppUI ui = new MobileAppUI(controller);

        // Create a tourist and a site
        Tourist tourist = new Tourist("TOUR123");
        Site site = new Site("SITE456", "Eiffel Tower", "Paris");

        // Simulate Tourist activates "Add to Bookmarks" feature
        System.out.println("=== Starting Insert Site to Favorites Sequence ===");
        ui.activateAddToBookmarks();

        // UI calls controller
        boolean success = controller.insertSiteToFavorites(tourist, site);
        if (success) {
            ui.displaySuccessMessage();
        } else {
            ui.displayErrorMessage();
        }
        System.out.println("=== Sequence completed ===");
    }
}