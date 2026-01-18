package com.example.domain.events;

/**
 * Marker interface for domain events.
 */
public interface DomainEvent {
    String getEventType();
}