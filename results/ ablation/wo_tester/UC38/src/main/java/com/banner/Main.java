package com.banner;

import com.banner.auth.Authenticator;
import com.banner.auth.Credentials;
import com.banner.controller.BannerController;
import com.banner.dto.InsertBannerRequest;
import com.banner.dto.InsertBannerResponse;
import com.banner.handler.ErrorHandler;
import com.banner.interactor.InsertBannerInteractor;
import com.banner.notification.NotificationService;
import com.banner.repository.BannerRepository;
import com.banner.repository.BannerRepositoryImpl;
import com.banner.ui.ImageSelectionForm;
import com.banner.validation.ImageValidator;
import javax.sql.DataSource;
import javax.sql.DataSource;

/**
 * Main class to demonstrate the flow.
 * Simulates the sequence diagram interactions.
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies
        DataSource dataSource = null; // In a real app this would be a real DataSource
        BannerRepository repo = new BannerRepositoryImpl(dataSource);
        ImageValidator validator = new ImageValidator();
        ErrorHandler handler = new ErrorHandler();
        NotificationService notifier = new NotificationService();
        int maxBanners = 5;
        InsertBannerInteractor interactor = new InsertBannerInteractor(repo, validator, handler, maxBanners);
        Authenticator auth = new Authenticator();
        BannerController controller = new BannerController(interactor, auth, handler, notifier);

        // 1. Authentication (entry condition)
        Credentials creds = new Credentials("user123", "token456");
        if (!controller.authenticate(creds)) {
            System.out.println("Authentication failed.");
            return;
        }

        // 2. Request form (system displays form)
        ImageSelectionForm form = controller.requestBannerForm();

        // 3. Operator selects image (simulate setting data)
        byte[] dummyImage = new byte[]{1,2,3,4,5};
        form.setImageData(dummyImage);
        form.setImageFormat("PNG");

        // 4. Create request
        InsertBannerRequest request = new InsertBannerRequest("restaurant-001",
                                                              form.getImageData(),
                                                              form.getImageFormat());

        // 5. Insert banner (includes confirmation, validation, count check, persistence, notification)
        InsertBannerResponse response = controller.insertBanner(request);

        // 6. Output result
        System.out.println("Success: " + response.isSuccess());
        System.out.println("Banner ID: " + response.getBannerId());
        System.out.println("Message: " + response.getMessage());

        // Optional: demonstrate cancellation
        InsertBannerResponse cancelResponse = controller.cancelOperation();
        System.out.println("Cancellation result: " + cancelResponse.getMessage());
    }
}