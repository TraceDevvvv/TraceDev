package com.example.viewmodel;

import com.example.dto.SiteDTO;
import com.example.usecase.GetSiteDetailsUseCase;

/**
 * ViewModel for MVVM pattern.
 * Manages UI‑related data and business logic for SiteDetailsView.
 */
public class SiteDetailsViewModel {
    private final GetSiteDetailsUseCase useCase;
    private SiteDTO selectedSite;

    public SiteDetailsViewModel(GetSiteDetailsUseCase useCase) {
        this.useCase = useCase;
    }

    // Load site details as per sequence diagram
    public void loadSiteDetails(String siteId) {
        // Execute use case
        SiteDTO result = useCase.execute(siteId);
        if (result != null) {
            updateSelectedSite(result);
        } else {
            // Simulate error passing via null result (in real app use callback/exception)
            // Error handling is done via callbacks in the sequence diagram
        }
    }

    // Getter for site details
    public SiteDTO getSiteDetails() {
        return selectedSite;
    }

    // Updates the selected site (called after successful load)
    public void updateSelectedSite(SiteDTO siteDTO) {
        this.selectedSite = siteDTO;
    }
}