package com.example.view;

import com.example.model.Site;

import java.util.List;
import java.util.function.Consumer; // Using Consumer for simplicity in place of Function<String>

/**
 * View component responsible for displaying a list of sites and handling site selection.
 */
public class SiteListView {

    private Consumer<String> onSiteSelectedHandler;

    /**
     * Displays a list of sites to the operator.
     * @param sites The list of sites to display.
     */
    public void displaySites(List<Site> sites) {
        System.out.println("\n--- Site List ---");
        if (sites == null || sites.isEmpty()) {
            System.out.println("No sites available to display.");
            return;
        }
        sites.forEach(site -> System.out.println(site.getSiteId() + ": " + site.getSiteName() + " (" + site.getLocation() + ")"));
        System.out.println("-----------------\n");
    }

    /**
     * Registers a handler to be called when a site is selected.
     * This simulates the UI element raising an event when a site is chosen.
     * @param handler A Consumer that takes the selected siteId as input.
     */
    public void onSiteSelected(Consumer<String> handler) {
        this.onSiteSelectedHandler = handler;
        System.out.println("SiteListView: Site selection handler registered.");
    }

    /**
     * Simulates an operator selecting a site from the UI.
     * In a real UI, this would be triggered by a button click or list selection.
     * @param siteId The ID of the site selected by the operator.
     */
    public void simulateSiteSelection(String siteId) {
        System.out.println("SiteListView: Operator simulated selecting site: " + siteId);
        if (onSiteSelectedHandler != null) {
            onSiteSelectedHandler.accept(siteId);
        } else {
            System.out.println("SiteListView: No handler registered for site selection.");
        }
    }
}