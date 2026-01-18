package application;

/**
 * Interface for publishing events (Domain Events or Integration Events).
 */
public interface EventPublisher {
    void publish(Object event);
}