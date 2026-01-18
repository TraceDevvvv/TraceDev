package com.system;

import com.system.controller.UseCaseController;
import com.system.serv.BannerVerificationService;
import com.system.serv.NotificationService;
import com.system.serv.StateRecoveryService;
import com.system.repositories.ConventionRepositoryImpl;
import com.system.repositories.BannerRepositoryImpl;
import com.system.cache.ConventionCache;
import com.system.network.ETOURService;
import com.system.entities.Banner;
import com.system.entities.BannerRequest;
import com.system.entities.VerificationResult;

/**
 * Main class to demonstrate the runnable system.
 * Simulates the sequence diagram scenario.
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies
        ConventionRepositoryImpl conventionRepo = new ConventionRepositoryImpl();
        BannerRepositoryImpl bannerRepo = new BannerRepositoryImpl();
        ConventionCache cache = new ConventionCache();
        ETOURService etourService = new ETOURService();
        NotificationService notificationService = new NotificationService();
        StateRecoveryService recoveryService = new StateRecoveryService();

        BannerVerificationService verificationService = new BannerVerificationService(
                bannerRepo, conventionRepo, cache, etourService);

        UseCaseController controller = new UseCaseController(
                verificationService, notificationService, recoveryService);

        // Simulate Entry Operator request
        Banner banner = new Banner("banner123", "Summer Promotion", "convention456");
        BannerRequest request = new BannerRequest("agency789", "convention456", banner);

        System.out.println("=== Scenario 1: Banner addition allowed (current < max) ===");
        VerificationResult result1 = controller.checkBannerCompliance(request);
        System.out.println("Result: " + result1.isCompliant() + " - " + result1.getMessage());

        System.out.println("\n=== Scenario 2: Banner addition rejected (limit reached) ===");
        // To simulate limit reached, we would need to adjust the dummy count in BannerRepositoryImpl.
        // For demonstration, we just run the same request; the dummy count is 3 and max is 5,
        // so it will still be allowed. To see rejection, change dummy count to >=5.
        VerificationResult result2 = controller.checkBannerCompliance(request);
        System.out.println("Result: " + result2.isCompliant() + " - " + result2.getMessage());

        System.out.println("\n=== Scenario 3: Server connection failure ===");
        etourService.setConnected(false);
        VerificationResult result3 = controller.checkBannerCompliance(request);
        System.out.println("Result: " + result3.isCompliant() + " - " + result3.getMessage());
    }
}