package com.example;

import com.example.data.ETOURServerDataSource;
import com.example.repository.PointOfRestRepository;
import com.example.controller.ViewRefreshmentPointUseCaseController;
import com.example.view.PointOfRestViewController;
import com.example.data.DataAccessException;

import java.util.Arrays;

/**
 * Main class to simulate the use case execution as per sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies as per architecture
        ETOURServerDataSource dataSource = new ETOURServerDataSource();
        PointOfRestRepository repository = new PointOfRestRepository(dataSource);
        ViewRefreshmentPointUseCaseController controller = new ViewRefreshmentPointUseCaseController(repository);
        PointOfRestViewController view = new PointOfRestViewController(controller);

        // Simulate the entry condition: Operator is logged in and views list (from previous use case)
        PointOfRestViewController.PointOfRestSummaryDTO p1 = new PointOfRestViewController.PointOfRestSummaryDTO("101", "Point Alpha");
        PointOfRestViewController.PointOfRestSummaryDTO p2 = new PointOfRestViewController.PointOfRestSummaryDTO("102", "Point Beta");
        view.displayList(Arrays.asList(p1, p2));

        // Simulate successful selection
        System.out.println("\nAgent selects point with ID 101");
        view.onPointSelected("101");

        // Simulate selection with connection failure (as per sequence diagram alt path)
        System.out.println("\nAgent selects point with ID 999 (simulates connection interruption)");
        view.onPointSelected("999");
    }
}