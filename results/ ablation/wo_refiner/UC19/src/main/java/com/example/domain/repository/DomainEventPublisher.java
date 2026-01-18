package com.example.domain.repository;

import com.example.domain.events.DomainEvent;

/**
 * Port for publishing domain events.
 */
public interface DomainEventPublisher {
    void publish(DomainEvent event);
}