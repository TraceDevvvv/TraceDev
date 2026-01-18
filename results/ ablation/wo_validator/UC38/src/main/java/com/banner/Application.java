
package com.banner;

import com.banner.handler.AddBannerCommandHandler;
import com.banner.controller.BannerController;
import com.banner.model.PointOfRestaurant;
import com.banner.repository.BannerRepository;
import com.banner.repository.BannerRepositoryImpl;
import com.banner.service.ImageValidatorService;

/**
 * Main Spring Boot application.
 */
public class Application {
    public static void main(String[] args) {
        // SpringApplication.run(Application.class, args);
    }

    public BannerRepository bannerRepository() {
        return new BannerRepositoryImpl();
    }

    public ImageValidatorService imageValidatorService() {
        return new ImageValidatorService();
    }

    public PointOfRestaurant pointOfRestaurant() {
        // Assume a default point of restaurant; in real app, fetch from DB.
        return new PointOfRestaurant("restaurant123", 5);
    }

    public AddBannerCommandHandler addBannerCommandHandler(BannerRepository bannerRepository,
                                                           ImageValidatorService imageValidatorService,
                                                           PointOfRestaurant pointOfRestaurant) {
        return new AddBannerCommandHandler(bannerRepository, imageValidatorService, pointOfRestaurant);
    }

    public BannerController bannerController(AddBannerCommandHandler addBannerCommandHandler) {
        return new BannerController(addBannerCommandHandler);
    }
}
