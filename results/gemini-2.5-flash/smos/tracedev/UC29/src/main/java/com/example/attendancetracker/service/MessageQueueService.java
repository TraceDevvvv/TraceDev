package com.example.attendancetracker.service;

import com.example.attendancetracker.model.NotificationEvent;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Simulates a message queue for asynchronous event processing.
 * <<Queue>> service.
 */
public class MessageQueueService {
    private final Queue<NotificationEvent> eventQueue = new ConcurrentLinkedQueue<>();
    private final AtomicInteger processedEvents = new AtomicInteger(0);
    private final AtomicInteger failedEvents = new AtomicInteger(0);

    /**
     * Publishes a notification event to the queue.
     * @param event The event to publish.
     */
    public void publish(NotificationEvent event) {
        eventQueue.offer(event);
        System.out.println("[MessageQueueService] Published event: " + event);
    }

    /**
     * Consumes (retrieves and removes) the next available notification event from the queue.
     * @return The next NotificationEvent, or null if the queue is empty.
     */
    public NotificationEvent consume() {
        NotificationEvent event = eventQueue.poll();
        if (event != null) {
            System.out.println("[MessageQueueService] Consumed event: " + event);
        }
        return event;
    }

    /**
     * Acknowledges the successful processing of an event.
     * @param eventId The ID of the event that was processed.
     */
    public void acknowledgeProcessing(String eventId) {
        processedEvents.incrementAndGet();
        System.out.println("[MessageQueueService] Acknowledged processing for event ID: " + eventId);
    }

    /**
     * Reports a failure in processing an event.
     * @param eventId The ID of the event that failed.
     * @param reason The reason for the failure.
     */
    public void reportProcessingFailure(String eventId, String reason) {
        failedEvents.incrementAndGet();
        System.out.println("[MessageQueueService] Reported processing failure for event ID: " + eventId + ". Reason: " + reason);
        // In a real system, this might re-queue the message, move it to a dead-letter queue, or trigger alerts.
    }

    /**
     * Checks if the message queue is empty.
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return eventQueue.isEmpty();
    }

    public int getProcessedEventsCount() {
        return processedEvents.get();
    }

    public int getFailedEventsCount() {
        return failedEvents.get();
    }
}