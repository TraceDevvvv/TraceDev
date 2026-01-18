package com.example;

import com.example.datasource.ETourDatabase;
import com.example.datasource.IDataSource;
import com.example.repository.ISiteRepository;
import com.example.repository.SiteRepository;
import com.example.usecase.GetSiteDetailsUseCase;
import com.example.view.*;
import com.example.viewmodel.SiteDetailsViewModel;

/**
 * Main class to demonstrate the flow.
 * Simulates the sequence diagram interactions.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting Archaeological Site Details Use Case ===\n");
        
        // Setup dependency chain
        IDataSource dataSource = new ETourDatabase();
        ISiteRepository repository = new SiteRepository(dataSource);
        GetSiteDetailsUseCase useCase = new GetSiteDetailsUseCase(repository);
        SiteDetailsViewModel viewModel = new SiteDetailsViewModel(useCase);
        
        // Create SiteDetailsView and set its ViewModel
        SiteDetailsView detailsView = new SiteDetailsView();
        detailsView.setViewModel(viewModel);
        
        // Simulate entry from ResearchResultsView (as per sequence diagram)
        System.out.println("--- Entry from ResearchResultsView ---");
        ResearchResultsView rrView = new ResearchResultsView();
        rrView.displaySiteList();
        // Tourist selects a site
        rrView.selectSite("site123");
        
        System.out.println("\n--- Entry from VisitedSitesView ---");
        VisitedSitesView vsView = new VisitedSitesView();
        vsView.displaySiteList();
        vsView.selectSite("site456");
        
        System.out.println("\n--- Entry from FavoritesView ---");
        FavoritesView fView = new FavoritesView();
        fView.displaySiteList();
        fView.selectSite("unknown"); // This will result in empty data (connection interrupted simulation)
        
        System.out.println("\n=== Use Case Completed ===");
    }
}