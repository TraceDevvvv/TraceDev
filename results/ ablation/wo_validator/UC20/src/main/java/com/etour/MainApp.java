
package com.etour;

import com.etour.agency.*;
import com.etour.banner.*;
import com.etour.image.*;
import com.etour.restpoint.*;
import java.io.File;

/**
 * MainApp - demonstrates the flow described in the sequence diagram.
 */
public class MainApp {
    public static void main(String[] args) {
        // Setup serv
        BannerRepository repository = new BannerRepositoryImpl();
        ImageStorageService storage = new LocalImageStorageService();
        BannerInsertionValidator validator = new BannerInsertionValidator(repository, storage);
        BannerInsertionHandler handler = new BannerInsertionHandler();
        BannerService service = new BannerService(validator, handler, storage, repository);

        // Create a rest point
        RestPoint restPoint = new RestPoint("RP-001", "Highway Rest Area", 5);

        // Create an operator
        AgencyOperator operator = new AgencyOperator("OP-001", "John Doe");
        operator.login();
        operator.selectRestPoint(restPoint);

        // Simulate inserting a banner (success case)
        File imageFile = new File("banner.jpg"); // Assume this file exists
        operator.insertBanner(imageFile, service, restPoint.getId());

        // Simulate alternative flow: invalid image
        File invalidImage = new File("banner.exe");
        System.out.println("\n--- Trying invalid image ---");
        operator.insertBanner(invalidImage, service, restPoint.getId());

        // Simulate cancellation
        System.out.println("\n--- Simulating cancellation ---");
        service.cancelOperation();
    }
}
