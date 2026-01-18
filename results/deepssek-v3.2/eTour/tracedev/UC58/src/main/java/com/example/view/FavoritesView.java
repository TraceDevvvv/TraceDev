package com.example.view;

/**
 * Context view for favorite sites.
 * Another possible entry point.
 */
public class FavoritesView {
    private Object viewModel; // FavoritesViewModel in real app

    public void displaySiteList() {
        System.out.println("FavoritesView: Displaying favorite sites list.");
    }

    public void navigateToSiteDetails(String siteId) {
        System.out.println("FavoritesView: Navigating to site details for ID: " + siteId);
        SiteDetailsView detailsView = new SiteDetailsView();
        detailsView.selectSite(siteId);
    }

    public void selectSite(String siteId) {
        System.out.println("FavoritesView: Site selected: " + siteId);
        navigateToSiteDetails(siteId);
    }
}