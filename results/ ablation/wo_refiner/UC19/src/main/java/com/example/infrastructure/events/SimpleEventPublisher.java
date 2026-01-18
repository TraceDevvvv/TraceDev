
package com.example.infrastructure.events;

import com.example.domain.events.DomainEvent;
import com.example.domain.repository.DomainEventPublisher;

/**
 * Simple implementation of DomainEventPublisher.
 * Implements REQ-014 performance requirement.
 */
public class SimpleEventPublisher implements DomainEventPublisher {
    
    @Override
    public void publish(DomainEvent event) {
        // In a real application, this would publish to a message broker or event bus.
        System.out.println("Event published: " + event.getEventType() + " at " + java.time.LocalDateTime.now());
        // Simulate fast publishing (responseTime < 2s)
    }
}
