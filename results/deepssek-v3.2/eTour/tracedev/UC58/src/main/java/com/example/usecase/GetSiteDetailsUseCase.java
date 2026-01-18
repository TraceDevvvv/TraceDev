package com.example.usecase;

import com.example.dto.SiteDTO;
import com.example.repository.ISiteRepository;

/**
 * Use case class for getting site details.
 * Encapsulates the business logic.
 */
public class GetSiteDetailsUseCase {
    private final ISiteRepository repository;

    public GetSiteDetailsUseCase(ISiteRepository repository) {
        this.repository = repository;
    }

    // Executes the use case as per UML
    public SiteDTO execute(String siteId) {
        // Delegate to repository
        return repository.getSiteById(siteId);
    }
}