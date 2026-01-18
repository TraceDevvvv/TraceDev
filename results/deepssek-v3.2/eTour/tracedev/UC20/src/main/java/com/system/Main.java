package com.system;

import com.system.controller.BannerController;
import com.system.usecase.InsertBannerUseCase;
import com.system.BannerManager;
import com.system.repository.BannerRepository;
import com.system.repository.BannerRepositoryImpl;
import com.system.repository.RefreshmentPointRepository;
import com.system.repository.RefreshmentPointRepositoryImpl;

/**
 * Main class to demonstrate the system flow as per sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies
        BannerManager bannerManager = new BannerManager();
        BannerRepository bannerRepository = new BannerRepositoryImpl();
        RefreshmentPointRepository pointRepository = new RefreshmentPointRepositoryImpl();
        ETOURService etourService = new ETOURService();

        InsertBannerUseCase useCase = new InsertBannerUseCase(
                bannerManager, bannerRepository, pointRepository, etourService);
        BannerController controller = new BannerController(useCase);

        // Simulate actor: Agency Operator
        AgencyOperator operator = new AgencyOperator("OP001", "John Doe");

        // Step 1: Login
        boolean loggedIn = operator.login();
        System.out.println("Login successful: " + loggedIn);

        // Step 2: Get refreshment points (optional in diagram, but included)
        var points = controller.getRefreshmentPoints();
        System.out.println("Available points: " + points.size());

        // Step 3: Show insert form for a point
        String pointId = "RP001";
        FormData form = controller.showInsertForm(pointId);
        System.out.println("Form data for point: " + form.getPointName());

        // Step 4: Operator selects an image (simulate image data)
        byte[] imageData = new byte[500000]; // 500KB dummy image
        operator.selectImage(new Image(imageData));

        // Step 5: Upload image (system validates)
        Response uploadResponse = controller.uploadImage(imageData, pointId);
        System.out.println("Upload response: " + uploadResponse.getMessage());

        // Step 6: If validation passes, operator confirms
        if ("SUCCESS".equals(uploadResponse.getStatus())) {
            boolean confirmed = operator.confirmOperation();
            if (confirmed) {
                Response confirmResponse = controller.confirmInsertion(pointId, imageData);
                System.out.println("Confirm response: " + confirmResponse.getMessage());
            } else {
                Response cancelResponse = controller.cancelOperation();
                System.out.println("Cancel response: " + cancelResponse.getMessage());
            }
        } else {
            // Handle error
            System.out.println("Error: " + uploadResponse.getMessage());
        }

        // Simulate connection interruption (optional)
        etourService.setConnected(false);
        Response errorResponse = controller.handleError("Connection lost");
        System.out.println("Error handling: " + errorResponse.getMessage());
    }
}