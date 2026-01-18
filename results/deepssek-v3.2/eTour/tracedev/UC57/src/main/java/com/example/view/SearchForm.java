package com.example.view;

import com.example.model.Site;
import java.util.List;

/**
 * SearchForm class referenced in sequence diagram.
 */
public class SearchForm {

    public void displayForm() {
        System.out.println("SearchForm displayed.");
    }

    public void displayResults(List<Site> sites) {
        System.out.println("SearchForm displaying results:");
        for (Site site : sites) {
            System.out.println(" - " + site.getName());
        }
    }

    public void showSiteList(List<Site> sites) {
        displayResults(sites);
    }
}