package com.example.usecase;

import com.example.dto.ViewVisitedSitesRequestDTO;
import com.example.dto.VisitedSitesResponseDTO;

/**
 * Interface for the ViewVisitedSites use case.
 */
public interface ViewVisitedSitesUseCase {
    VisitedSitesResponseDTO execute(ViewVisitedSitesRequestDTO request);
}