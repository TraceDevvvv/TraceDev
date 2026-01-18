
package com.example;

import com.example.application.ChangeBannerImageService;
import com.example.domain.ImageValidator;
import com.example.infrastructure.*;
import com.example.presentation.AgencyOperatorController;

import java.util.Arrays;
import java.util.List;

/**
 * Main application class to demonstrate the "Change Banner Image" use case.
 * This sets up the dependency injection and simulates the user interaction flow
 * as described in the sequence diagram.
 */
public class MainApplication {

    public static void main(String[] args) {
        // 1. Set up Infrastructure Layer dependencies
        DbTurningPointRepository turningPointRepository = new DbTurningPointRepository();
        DbBannerRepository bannerRepository = new DbBannerRepository(turningPointRepository); // Initialize with data from TP repo
        LocalStorageImageService imageStorageService = new LocalStorageImageService(); // Changed from ImageService to LocalStorageImageService
        ETourSystemAdapter etourService = new ETourSystemAdapter();

        // 2. Set up Domain Layer dependencies
        ImageValidator imageValidator = new ImageValidator();

        // Final approach for circular dependency in demo:
        // Create an instance of AgencyOperatorController first, passing a partial CBIS or proxy.
        // Then create CBIS with the *actual* AOC.
        // Then set the actual CBIS in AOC.

        AgencyOperatorController finalAoc = new AgencyOperatorController(null); // Pass null for service initially
        ChangeBannerImageService changeBannerImageService = new ChangeBannerImageService(
                turningPointRepository,
                bannerRepository,
                imageValidator,
                imageStorageService,
                etourService,
                finalAoc // Now pass the *actual* AOC instance
        );
        // Since AgencyOperatorController's constructor requires ChangeBannerImageService,
        // we create a new instance with the now-available service to complete the wiring.
        // In a real DI framework, this circular dependency is managed automatically.
        finalAoc = new AgencyOperatorController(changeBannerImageService); // This is the fully wired controller

        // Now, simulate the sequence diagram flow using `finalAoc`
        System.out.println("\n--- Starting Change Banner Image Use Case Simulation ---\n");

        // 1. Agency Operator requests turning points
        System.out.println("AO: Requesting turning points.");
        List<com.example.domain.TurningPoint> turningPoints = finalAoc.requestTurningPoints();
        String selectedTurningPointId = null;
        if (!turningPoints.isEmpty()) {
            selectedTurningPointId = turningPoints.get(0).getId(); // Select the first one for demo
        }
        System.out.println("AO: Selected turning point ID: " + selectedTurningPointId);

        // 2. Agency Operator selects a turning point
        List<com.example.domain.Banner> banners = List.of();
        if (selectedTurningPointId != null) {
            banners = finalAoc.selectTurningPoint(selectedTurningPointId);
        }

        // 5. Agency Operator selects a banner
        String selectedBannerId = null;
        if (!banners.isEmpty()) {
            selectedBannerId = banners.get(0).getId(); // Select the first banner
        }
        System.out.println("AO: Selected banner ID: " + selectedBannerId);

        com.example.domain.Banner bannerDetails = null;
        if (selectedBannerId != null) {
            bannerDetails = finalAoc.getBannerDetails(selectedBannerId); // Changed from selectBanner to getBannerDetails
        }

        // 7. System displays form for image selection (implicit)
        // 9. Agency Operator sends request to change
        byte[] validImageData = "JVBERi0xLjQKJdPr6eEKMSAwIG9iago8PC9UeXBlL0NhdGFsb2cvUGFnZXMgMiAwIFIvTGFuZyhlbi1VUykgPj4KZW5kb2JqCjIgMCBvYmoKPDwvVHlwZS9QYWdlcy9Db3VudCAxL0sgWzMgMCBSXSA+PgplbmRvYmoKMyAwIG9iago8PC9UeXBlL1BhZ2UvUGFyZW50IDIgMCBSL1Jlc291cmNlcyAzMiAwIFIvTWVkaWFCb3ggWzAgMCA2MTIgNzkyXS9Db250ZW50cyA0IDAgUi9UaHVtYiAzMyAwIFI+PgplbmRvYmoKNDAgMCBvYmoKPDwvQ2EgMS9jYSAxL0MxIDU2IDAgUi9GaWx0ZXIvRmxhdGVEZWNvZGUvRm9ybS9YLU9iamVjdC9MZW5ndGggMjA3MC9BOSA1MiAwIFI+PgplbmRvYmoKNDQgMCBvYmoKPDwvQSA0NiAwIFIvQURPRGUgWzQ1IDAgUiAxMDJdL0NEaXAgNDggMCBSL0NTIDQ2IDAgUi9EIDQ1IDAgUi9GaWx0ZXIvRmxhdGVEZWNvZGUvRjEgNDkgMCBSL0YzIDUwIDAgUi9GKXAgNTUgMCBSL0ZMZW4gNTIgMCBSL0ZPciA1NSAwIFIvRk9ya3UgNTUgMCBSL0ZpbHRlci9GbGF0ZURlY29kZS9Gb250IDU4IDAgUi9GaWx0ZXIvRmxhdGVEZWNvZGUvRm9ybVN0eWxlL05vcm1hbC9GaWx0ZXIvRmxhdGVEZWNvZGUvTCA1OSAwIFIvTGVuZ3RoIDI2NzcvTSA2MiAwIFIvTWF0cml4IFsxIDAgMCAxIDAgMF0vTmFtZSA2NCAwIFIvTCA2NiAwIFIvTGVuZ3RoIDIwNzcvTyA2NiAwIFIvT2JqRGVjIDY2IDAgUi9PcmRlcnY2OCAwIFIvUFBvYmQgNzAgMCBSL1BBIDcwIDAgUi9QQm9yZG9zIDcyIDAgUi9QQWNoYWNrIDczIDAgUi9QcmV2IDc1IDAgUi9QY2FsdSA3NiAwIFIvUU91dCA3OCAwIFIvUVByaW50IDc5IDAgUi9RRGV2IDgwIDAgUi9SZXBhaXIgODEgMCBSL1N0eWxlL05vcm1hbC9TaWduYXR1cmUgODUgMCBSL1N1YiA4NiAwIFIvVHlwZS9Gb3JtL1ZhbHVlczw8L0ExIDg3IDAgUi9BMiA4OCAwIFIvQjIgOTAgMCBSL0M3IDkxIDAgUi9FMyA5MyAwIFIvRjcgOTQgMCBSL0g0IDk2IDAgUi9JOiA5NyAwIFIvSjIgOTkgMCBSL0w0IDEwMCAwIFIvTTEgMTAyIDAgUi9OIDEwMyAwIFIvTTEgMTA0IDAgUi9PIDEwNiAwIFIvUjYgMTA3IDAgUi9SMyAxMDkgMCBSL1I0IDE0MCAwIFIvU1IxIDE0MiAwIFIvU1I0IDE0MyAwIFIvVFcxIDE0NCAwIFIvVDIgMTQ2IDAgUi9VIDE0NyAwIFIvVjMgMTQ5IDAgUi9WMiAxNTEgMCBSL1cxIDE1MiAwIFIvWTIgMTUzIDAgUi9aMiAxNTUgMCBSL1oyIDE1NiAwIFI+PgplbmRvYmoKMTc5IDAgb2JqCjw8L0ZpbHRlci9GbGF0ZURlY29kZS9Gb3JtL1gtT2JqZWN0L0xlbmd0aCAxNzAvQjEgMTgwIDAgUi9CaXRzICwgdGhpcyBpcyBhIG1vY2sganBlZyBkYXRhClthc2JpZ2JpZ2JpZ2JdcmVtb3ZlZCBmb3IgY29uY2lzZW5lc3kK".getBytes(); // A mock JPEG header + some content
        byte[] invalidImageData = "This is not an image".getBytes();
        byte[] tooSmallImageData = "abc".getBytes(); // Less than 1KB for mock
        byte[] tooLargeImageData = new byte[6 * 1024 * 1024]; // More than 5MB for mock
        Arrays.fill(tooLargeImageData, (byte)0x41); // Fill with 'A'

        System.out.println("\n--- Testing with VALID image data ---");
        if (selectedBannerId != null) {
            finalAoc.uploadNewImage(selectedBannerId, validImageData); // Changed from uploadImage to uploadNewImage
            // Simulate AO confirming
            finalAoc.confirmImageChange(selectedBannerId);
        } else {
            System.out.println("No banner selected to upload image.");
        }

        System.out.println("\n--- Testing with INVALID (non-image) data ---");
        if (selectedBannerId != null) {
            finalAoc.uploadNewImage(selectedBannerId, invalidImageData); // Changed from uploadImage to uploadNewImage
            // No confirmation expected for invalid image, as per SD alt block.
        }

        System.out.println("\n--- Testing with TOO SMALL image data ---");
        if (selectedBannerId != null) {
            finalAoc.uploadNewImage(selectedBannerId, tooSmallImageData); // Changed from uploadImage to uploadNewImage
        }

        System.out.println("\n--- Testing with TOO LARGE image data (mock validation) ---");
        if (selectedBannerId != null) {
            finalAoc.uploadNewImage(selectedBannerId, tooLargeImageData); // Changed from uploadImage to uploadNewImage
        }

        System.out.println("\n--- End of Change Banner Image Use Case Simulation ---\n");
    }
}
