package com.example.application.ports;

import com.example.domain.events.AbsenceModifiedEvent;

/**
 * Publisher for domain events.
 */
public interface EventPublisher {
    void publish(AbsenceModifiedEvent event);
}