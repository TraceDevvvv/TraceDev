package com.example;

import com.example.controller.BannerController;
import com.example.dto.BannerInsertionRequest;
import com.example.dto.BannerInsertionResponse;
import com.example.repository.*;
import com.example.service.*;
import java.util.Scanner;

/**
 * Main application class to demonstrate the banner insertion workflow.
 */
public class MainApplication {
    public static void main(String[] args) {
        // Initialize all dependencies
        BannerRepository bannerRepository = new BannerRepositoryImpl();
        PointRepository pointRepository = new PointRepositoryImpl();
        ImageValidator imageValidator = new ImageValidator();
        BannerLimitValidator bannerLimitValidator = new BannerLimitValidator(bannerRepository, pointRepository);
        NotificationService notificationService = new NotificationService();
        com.example.service.SecurityManager securityManager = new com.example.service.SecurityManager();
        AuthenticationService authenticationService = new AuthenticationService(securityManager);
        
        BannerController controller = new BannerController(
            imageValidator, bannerLimitValidator, bannerRepository,
            pointRepository, notificationService, authenticationService
        );
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Banner Insertion System ===");
        System.out.println("Operator ID: ");
        String operatorId = scanner.nextLine();
        
        System.out.println("Point ID (point1 or point2): ");
        String pointId = scanner.nextLine();
        
        System.out.println("Enter image file path or type 'sample' for test: ");
        String imageInput = scanner.nextLine();
        
        byte[] imageData;
        if (imageInput.equals("sample")) {
            // Provide sample image data for testing
            imageData = new byte[]{0x12, 0x34, 0x56};
        } else {
            // In a real application, you would read the file from the given path
            imageData = new byte[0];
        }
        
        BannerInsertionRequest request = new BannerInsertionRequest(operatorId, imageData, pointId);
        BannerInsertionResponse response = controller.handleInsertBanner(request);
        
        System.out.println("Response: " + response.toString());
        scanner.close();
    }
}
