package com.example.infrastructure.events;

import com.example.application.ports.EventPublisher;
import com.example.domain.events.AbsenceModifiedEvent;
import com.example.notification.EmailNotificationHandler;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple event publisher implementation that notifies subscribers.
 */
public class EventPublisherImpl implements EventPublisher {
    private List<EmailNotificationHandler> subscribers = new ArrayList<>();

    public void addSubscriber(EmailNotificationHandler handler) {
        subscribers.add(handler);
    }

    @Override
    public void publish(AbsenceModifiedEvent event) {
        // In a real system, this would be asynchronous (e.g., via messaging).
        // For simplicity, we call handlers directly.
        System.out.println("EventPublisherImpl: publishing AbsenceModifiedEvent for absence " + event.getAbsenceId());
        for (EmailNotificationHandler handler : subscribers) {
            // In production you might want to run this in a separate thread.
            handler.handle(event);
        }
    }
}