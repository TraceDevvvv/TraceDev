
package com.example.application;

import com.example.domain.Banner;
import com.example.domain.events.DomainEvent;
import com.example.domain.events.BannerDeletedEvent;
import com.example.domain.repository.BannerRepository;
import com.example.domain.repository.DomainEventPublisher;

/**
 * Service implementing the DeleteBannerUseCase.
 * Implements REQ-014 performance requirement (responseTime < 2s).
 */
public class DeleteBannerService implements DeleteBannerUseCase {
    private BannerRepository bannerRepository;
    private DomainEventPublisher eventPublisher;
    
    public DeleteBannerService(BannerRepository repository, DomainEventPublisher publisher) {
        this.bannerRepository = repository;
        this.eventPublisher = publisher;
    }
    
    @Override
    public DeleteBannerResult execute(DeleteBannerCommand command) {
        // Find banner by ID
        Banner banner = bannerRepository.findById(command.getBannerId())
                .orElse(null);
        
        if (banner == null) {
            // Banner not found
            return new DeleteBannerResult(false, "Banner not found", command.getBannerId());
        }
        
        // Delete banner
        bannerRepository.delete(banner);
        
        // Publish domain event
        DomainEvent event = new BannerDeletedEvent(banner.getId(), command.getAgencyOperatorId());
        eventPublisher.publish(event);
        
        // Return success result
        return new DeleteBannerResult(true, "Banner deleted successfully", banner.getId());
    }
}
