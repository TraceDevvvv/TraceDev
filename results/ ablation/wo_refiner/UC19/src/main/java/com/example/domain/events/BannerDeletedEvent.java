package com.example.domain.events;

import java.time.LocalDateTime;

/**
 * Domain event emitted when a banner is deleted.
 */
public class BannerDeletedEvent implements DomainEvent {
    private String bannerId;
    private String deletedBy;
    private LocalDateTime occurredOn;
    
    public BannerDeletedEvent(String bannerId, String deletedBy) {
        this.bannerId = bannerId;
        this.deletedBy = deletedBy;
        this.occurredOn = LocalDateTime.now();
    }
    
    public String getBannerId() {
        return bannerId;
    }
    
    public String getDeletedBy() {
        return deletedBy;
    }
    
    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }
    
    @Override
    public String getEventType() {
        return "BannerDeletedEvent";
    }
}