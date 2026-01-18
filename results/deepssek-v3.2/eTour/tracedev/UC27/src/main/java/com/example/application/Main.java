package com.example.application;

import com.example.presentation.SearchForm;
import com.example.controller.SearchSiteController;
import com.example.repository.FileSystemSiteRepository;
import com.example.repository.SiteRepository;
import com.example.service.AuthenticationService;
import com.example.service.IndexService;
import com.example.service.SystemStatus;

/**
 * Main application class to demonstrate the flow.
 */
public class Main {
    public static void main(String[] args) {
        // Create the serv and repository
        IndexService indexService = new IndexService();
        SiteRepository siteRepository = new FileSystemSiteRepository(indexService);
        AuthenticationService authService = new AuthenticationService();
        SystemStatus systemStatus = new SystemStatus();

        // Create the controller
        SearchSiteController controller = new SearchSiteController(siteRepository, authService, systemStatus);

        // Create the search form
        SearchForm form = new SearchForm(controller);

        // Simulate the user interaction as per sequence diagram
        // User activates the form (Flow 1)
        form.activateSearch();

        // Form shows (Flow 2)
        form.show();

        // User fills the form (Flow 3)
        form.fillForm("example query");

        // User submits the form (Flow 4)
        form.submit();

        // For demonstration of error case:
        System.out.println("\n--- Testing error case ---");
        SearchForm errorForm = new SearchForm(controller);
        errorForm.activateSearch();
        errorForm.show();
        errorForm.fillForm("error"); // This query will cause IndexService to throw an exception
        errorForm.submit();
    }
}