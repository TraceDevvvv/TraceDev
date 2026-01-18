
package com.example.banner;

import com.example.banner.application.command.DeleteBannerCommand;
import com.example.banner.application.usecase.DeleteBannerInteractor;
import com.example.banner.application.usecase.DeleteBannerUseCase;
import com.example.banner.domain.model.Banner;
import com.example.banner.domain.port.BannerRepository;
import com.example.banner.domain.port.NotificationPort;
import com.example.banner.infrastructure.notification.EmailNotificationAdapter;
import com.example.banner.infrastructure.persistence.BannerRepositoryImpl;
import com.example.banner.interfaceadapters.controller.BannerController;

/**
 * Main class to demonstrate the flow.
 */
public class MainApplication {
    public static void main(String[] args) {
        // Setup dependencies
        BannerRepository repository = new BannerRepositoryImpl(null);
        NotificationPort notifier = new EmailNotificationAdapter(null);
        DeleteBannerUseCase interactor = new DeleteBannerInteractor(repository, notifier);
        BannerController controller = new BannerController(interactor);

        // Simulate a pre‑existing banner
        Banner banner = new Banner("banner123", "rest456", "http://example.com/banner.jpg", true, new java.util.Date());
        // Note: In a real scenario the banner would be persisted via repository.

        // Simulate operator request
        System.out.println("=== Operator requests deletion ===");
        Object response = controller.deleteBanner("banner123", "rest456", "operator789");
        System.out.println("Controller response: " + response);
    }
}
