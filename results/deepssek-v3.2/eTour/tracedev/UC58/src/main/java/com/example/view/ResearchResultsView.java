package com.example.view;

/**
 * Context view for research results.
 * One of the possible entry points for the use case.
 */
public class ResearchResultsView {
    // ViewModel is present per UML (simplified here)
    private Object viewModel; // In real app would be ResearchResultsViewModel

    // Display site list (as per sequence diagram)
    public void displaySiteList() {
        System.out.println("ResearchResultsView: Displaying site list.");
    }

    // Navigate to site details (as per sequence diagram)
    public void navigateToSiteDetails(String siteId) {
        System.out.println("ResearchResultsView: Navigating to site details for ID: " + siteId);
        // In a real Android app, this would start an Intent/Activity
        SiteDetailsView detailsView = new SiteDetailsView();
        detailsView.selectSite(siteId);
    }

    // Select site (trigger from Tourist)
    public void selectSite(String siteId) {
        System.out.println("ResearchResultsView: Site selected: " + siteId);
        navigateToSiteDetails(siteId);
    }
}