package com.example.view;

/**
 * Context view for visited sites.
 * Another possible entry point.
 */
public class VisitedSitesView {
    private Object viewModel; // VisitedSitesViewModel in real app

    public void displaySiteList() {
        System.out.println("VisitedSitesView: Displaying visited sites list.");
    }

    public void navigateToSiteDetails(String siteId) {
        System.out.println("VisitedSitesView: Navigating to site details for ID: " + siteId);
        SiteDetailsView detailsView = new SiteDetailsView();
        detailsView.selectSite(siteId);
    }

    public void selectSite(String siteId) {
        System.out.println("VisitedSitesView: Site selected: " + siteId);
        navigateToSiteDetails(siteId);
    }
}