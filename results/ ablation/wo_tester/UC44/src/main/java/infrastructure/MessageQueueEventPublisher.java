package infrastructure;

import application.EventPublisher;

/**
 * Event publisher that sends events to a message queue.
 */
public class MessageQueueEventPublisher implements EventPublisher {
    private MessageQueue messageQueue;

    public MessageQueueEventPublisher(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void publish(Object event) {
        // Simulate publishing to message queue
        System.out.println("Event published to message queue: " + event);
    }
}