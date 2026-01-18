package application;

import ui.BannerManagementUI;
import controller.BannerController;
import usecase.DeleteBannerInteractor;
import repository.BannerRepositoryImpl;
import datasource.BannerDataSource;
import entity.Banner;
import dto.DeleteBannerRequest;
import dto.DeleteBannerResponse;

/**
 * Main class to demonstrate the runnable system.
 * Simulates the entire flow with a mock data source.
 */
public class Main {
    public static void main(String[] args) {
        // Setup the data source (mock implementation for demonstration)
        BannerDataSource dataSource = new BannerDataSource() {
            @Override
            public Banner getBanner(String bannerId) {
                // Simulate successful retrieval
                return new Banner(bannerId, "rest-123", "http://example.com/banner.jpg", true);
            }

            @Override
            public boolean updateBanner(Banner banner) {
                System.out.println("[DataSource] Updating banner: " + banner.getBannerId());
                return true;
            }

            @Override
            public boolean removeBanner(String bannerId) {
                System.out.println("[DataSource] Removing banner: " + bannerId);
                return true;
            }
        };

        // Assemble the components (Clean Architecture dependency injection)
        BannerRepositoryImpl repository = new BannerRepositoryImpl(dataSource);
        DeleteBannerInteractor interactor = new DeleteBannerInteractor(repository);
        BannerController controller = new BannerController(interactor);
        BannerManagementUI ui = new BannerManagementUI(controller);

        // Simulate the use case
        ui.loadBannerList("rest-123");
        ui.triggerDeleteBanner("operator-456", "banner-789");

        // Alternative flow example: cancellation
        BannerManagementUI ui2 = new BannerManagementUI(controller);
        ui2.loadBannerList("rest-123");
        // For cancellation, we would need to modify showConfirmationDialog to return false.
        // Instead, we directly call cancelOperation for demonstration.
        controller.cancelOperation("req-dummy");
    }
}