package com.example.view;

import com.example.dto.SiteDTO;
import com.example.service.AuthenticationService;
import com.example.viewmodel.SiteDetailsViewModel;

/**
 * View/Activity/Fragment for displaying site details.
 * Implements the sequence diagram interactions.
 */
public class SiteDetailsView {
    private SiteDetailsViewModel viewModel;

    // Constructor that injects ViewModel (simplified for example)
    public SiteDetailsView() {
        // In a real app, ViewModel would be provided via ViewModelFactory or DI
        // For this example, we create a dummy one
        this.viewModel = null; // Will be set via setViewModel
    }

    public void setViewModel(SiteDetailsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    // Trigger method called by context views (ResearchResultsView, etc.)
    public void selectSite(String siteId) {
        System.out.println("SiteDetailsView: selectSite called with ID: " + siteId);
        
        // First verify authentication (as per requirement REQ-005 and sequence diagram)
        if (!verifyAuthentication()) {
            showError("Authentication required");
            return;
        }
        
        // Show loading indicator
        showLoading();
        
        // Load site details via ViewModel
        if (viewModel != null) {
            viewModel.loadSiteDetails(siteId);
            // In a real reactive setup, we would observe LiveData/Flow
            // For simplicity, we simulate the callbacks:
            SiteDTO site = viewModel.getSiteDetails();
            if (site != null) {
                onSiteLoaded(site);
            } else {
                onError("Connection interrupted");
            }
        } else {
            showError("ViewModel not initialized");
            hideLoading();
        }
    }

    // Display site details on UI
    public void displaySite(SiteDTO site) {
        System.out.println("SiteDetailsView: Displaying site - " + site.getName());
        // In real app, update TextView, ImageView, etc.
    }

    // Show loading indicator
    public void showLoading() {
        System.out.println("SiteDetailsView: Showing loading indicator.");
    }

    // Hide loading indicator
    public void hideLoading() {
        System.out.println("SiteDetailsView: Hiding loading indicator.");
    }

    // Show error message
    public void showError(String message) {
        System.out.println("SiteDetailsView: ERROR - " + message);
    }

    // Callback when site is loaded (called by ViewModel or observer)
    public void onSiteLoaded(SiteDTO siteDTO) {
        System.out.println("SiteDetailsView: Site loaded successfully.");
        displaySite(siteDTO);
        hideLoading();
    }

    // Callback on error (called by ViewModel or observer)
    public void onError(String message) {
        System.out.println("SiteDetailsView: Error received - " + message);
        showError(message);
        hideLoading();
    }

    // Verify authentication (as per requirement REQ-005)
    public boolean verifyAuthentication() {
        AuthenticationService authService = AuthenticationService.getInstance();
        boolean authenticated = authService.isAuthenticated();
        System.out.println("SiteDetailsView: verifyAuthentication = " + authenticated);
        return authenticated;
    }
}