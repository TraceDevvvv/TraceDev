package com.example;

import java.util.List;

/**
 * Boundary class for displaying results.
 */
public class Results {
    private List<Site> sites;

    public void render() {
        if (sites == null || sites.isEmpty()) {
            System.out.println("No results to display.");
        } else {
            System.out.println("=== Search Results ===");
            for (Site site : sites) {
                System.out.println(site.toString());
            }
            System.out.println("======================");
        }
    }

    public void update(List<Site> sites) {
        this.sites = sites;
    }

    public void displayResults(List<Site> sites) {
        update(sites);
        render();
    }

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }
}