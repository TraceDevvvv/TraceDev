package com.example;

import com.example.agency.AgencyOperator;
import com.example.banner.Banner;
import com.example.boundary.ModifyBannerForm;
import com.example.connection.ETOURConnectionHandler;
import com.example.connection.ConnectionInterruptedException;
import com.example.controller.ModifyBannerController;
import com.example.notification.NotificationServiceImpl;
import com.example.repository.BannerRepositoryImpl;
import com.example.security.ImageValidator;
import com.example.point.RefreshmentPoint;
import java.util.List;

/**
 * Main class to demonstrate the flow described in the sequence diagram.
 */
public class Main {
    
    public static void main(String[] args) {
        // Setup dependencies
        BannerRepositoryImpl repository = new BannerRepositoryImpl();
        ImageValidator validator = new ImageValidator();
        ETOURConnectionHandler connectionHandler = new ETOURConnectionHandler();
        NotificationServiceImpl notificationService = new NotificationServiceImpl();
        ModifyBannerController controller = new ModifyBannerController(repository, validator, connectionHandler);
        ModifyBannerForm form = new ModifyBannerForm(controller, notificationService);
        
        // Agency operator logs in
        AgencyOperator operator = new AgencyOperator(1, "John Doe");
        operator.login();
        
        // Entry Condition: Agency has logged in (m1)
        System.out.println("Entry Condition: Agency has logged in");
        
        // Simulate select refreshment point (from list) (m2)
        List<RefreshmentPoint> points = controller.searchRefreshmentPoints();
        form.displayRefreshmentPointList(points);
        int selectedPointId = 1;
        form.selectRefreshmentPoint(selectedPointId);
        
        // Query banners by point (m5)
        List<Banner> banners = controller.queryBannersByPoint(selectedPointId);
        // Returns banner data (m6)
        form.displayBannerList(banners);
        // Display banner list (m9)
        System.out.println("Display banner list");
        
        // Select banner for editing (m10)
        int selectedBannerId = 101;
        form.selectBannerForEditing(selectedBannerId);
        
        // Retrieve banner data (m13)
        Banner banner = controller.retrieveBannerData(selectedBannerId);
        // Banner data (m14)
        // Banner object (m15)
        // Banner object (m16)
        // Display edit form (m17)
        form.displayEditForm(banner);
        
        // Request image selection (m18)
        form.requestImageSelectionForm();
        // Display image selection form (m19)
        System.out.println("Display image selection form");
        
        // Select image file (m20)
        byte[] newImage = new byte[] {0x40, 0x50, 0x60};
        form.selectImageFile(newImage);
        // Upload image (client-side) (m21)
        form.uploadImage(newImage);
        
        // Validate image
        boolean valid = controller.modifyBannerImage(selectedBannerId, newImage);
        if (!valid) {
            // Display error (invalid image) (m36)
            form.displayErrorInvalidImage();
            // Interruption: Errored use case triggered (m37)
            System.out.println("Interruption: Errored use case triggered");
            return;
        }
        
        // Display confirmation prompt (m25)
        form.displayConfirmationPrompt(selectedBannerId, newImage);
        // Confirm modification (m26)
        form.confirmModification(selectedBannerId, newImage);
        // confirm modification (m27)
        try {
            boolean success = controller.confirmModification(selectedBannerId, newImage);
            if (success) {
                // Display success notification (m33)
                form.displaySuccessNotification();
                // Exit Condition: System notifies successful modification (m38)
                System.out.println("Exit Condition: System notifies successful modification");
            } else {
                form.displayError();
            }
        } catch (ConnectionInterruptedException e) {
            System.err.println(e.getMessage());
            form.displayError();
        }
        
        operator.logout();
    }
}