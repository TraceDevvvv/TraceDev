package com.restaurant;

import com.restaurant.operator.PointOfRestaurantOperator;

/**
 * Main class to run the banner deletion use case.
 */
public class Main {
    public static void main(String[] args) {
        // This main class is an alternative entry point.
        // The primary runnable class is PointOfRestaurantOperator.
        System.out.println("=== Restaurant Banner Management System ===");
        PointOfRestaurantOperator operator = new PointOfRestaurantOperator(500, 101);
        if (operator.authenticate()) {
            operator.deleteBannerFlow();
        }
        operator.close();
    }
}