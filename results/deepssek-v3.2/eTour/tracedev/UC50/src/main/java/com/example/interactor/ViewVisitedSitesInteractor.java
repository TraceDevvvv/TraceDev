package com.example.interactor;

import com.example.dto.ViewVisitedSitesRequestDTO;
import com.example.dto.VisitedSitesResponseDTO;
import com.example.dto.SiteDTO;
import com.example.usecase.ViewVisitedSitesUseCase;
import com.example.repository.SiteRepository;
import com.example.entity.Site;
import com.example.mapper.SiteMapper;
import java.util.List;
import com.example.exception.ConnectionLostException;

/**
 * Interactor implementing the use case.
 */
public class ViewVisitedSitesInteractor implements ViewVisitedSitesUseCase {
    private SiteRepository siteRepository;

    public ViewVisitedSitesInteractor(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    @Override
    public VisitedSitesResponseDTO execute(ViewVisitedSitesRequestDTO request) {
        // R3-Flow of Events (step 2): System uploads the list of sites for which the tourist has issued feedback
        List<Site> sites;
        try {
            sites = siteRepository.findSitesWithFeedbackByTouristId(request.getTouristId());
        } catch (ConnectionLostException e) {
            // R5-Exit Conditions: Connection to the server ETOUR is interrupted
            throw e;
        }
        List<SiteDTO> siteDTOs = SiteMapper.toDTOList(sites);
        // Create VisitedSitesResponseDTO as per sequence diagram message m19
        return new VisitedSitesResponseDTO(siteDTOs);
    }
}