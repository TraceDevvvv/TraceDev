package com.example;

import com.example.controller.DeleteBannerController;
import com.example.model.AgencyOperator;
import com.example.repository.BannerRepository;
import com.example.repository.BannerRepositoryImpl;
import com.example.server.ETOURServerConnection;
import com.example.service.DeleteBannerService;
import com.example.ui.DeleteBannerUI;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Main class that simulates the Delete Banner flow according to the sequence diagram.
 * This demonstrates the complete interaction.
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies
        DataSource dummyDataSource = null; // In a real app, this would be configured
        BannerRepository bannerRepository = new BannerRepositoryImpl(dummyDataSource);
        ETOURServerConnection serverConnection = new ETOURServerConnection();
        DeleteBannerService deleteBannerService = new DeleteBannerService(bannerRepository, serverConnection);
        DeleteBannerController controller = new DeleteBannerController(bannerRepository, deleteBannerService);
        DeleteBannerUI ui = new DeleteBannerUI(controller);

        // Actor: Agency Operator
        AgencyOperator operator = new AgencyOperator();
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "admin");
        credentials.put("password", "pass123");
        boolean loggedIn = operator.login(credentials);
        if (!loggedIn) {
            ui.showErrorNotification("Login required.");
            return;
        }

        System.out.println("=== Starting Delete Banner Flow ===\n");

        // 1. Request refreshment points list
        System.out.println("1. Agency Operator requests refreshment points list.");
        var points = controller.searchRefreshmentPoints("some criteria");
        ui.displayRefreshmentPoints(points);

        // 2. Select refreshment point and access banner removal function
        System.out.println("2. Operator selects a refreshment point (ID 101).");
        long selectedPointId = 101L;
        var banners = controller.getBannersForPoint(selectedPointId);
        ui.displayBanners(banners);

        // 3. Select banner and trigger deletion
        System.out.println("3. Operator selects banner (ID 1) and triggers deletion.");
        long selectedBannerId = 1L;
        boolean success = controller.deleteBanner(selectedBannerId);

        if (success) {
            System.out.println("4. Deletion successful. Showing confirmation dialog.");
            boolean confirmed = ui.showConfirmationDialog();
            if (confirmed) {
                controller.confirmDeletion();
                ui.showSuccessNotification();
            } else {
                ui.showErrorNotification("Deletion cancelled by user.");
            }
        } else {
            ui.showErrorNotification("Deletion failed.");
        }

        System.out.println("\n=== Flow completed ===");
    }
}