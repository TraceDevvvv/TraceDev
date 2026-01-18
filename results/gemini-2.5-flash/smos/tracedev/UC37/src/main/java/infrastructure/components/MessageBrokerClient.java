package infrastructure.components;

/**
 * Infrastructure Component that simulates interaction with a Message Broker.
 * This class handles the low-level connection and message sending to a messaging system.
 */
public class MessageBrokerClient {

    private boolean connected = false;

    public MessageBrokerClient() {
        System.out.println("MessageBrokerClient: Initialized.");
    }

    /**
     * Simulates connecting to the message broker.
     */
    public void connect() {
        System.out.println("MessageBrokerClient: Attempting to connect to message broker...");
        // Simulate connection logic. Can introduce random failures for testing.
        try {
            Thread.sleep(100); // Simulate network latency
            this.connected = true;
            System.out.println("MessageBrokerClient: Successfully connected.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("MessageBrokerClient: Connection interrupted.");
        }
    }

    /**
     * Simulates disconnecting from the message broker.
     */
    public void disconnect() {
        if (connected) {
            System.out.println("MessageBrokerClient: Disconnecting from message broker...");
            connected = false;
            System.out.println("MessageBrokerClient: Disconnected.");
        }
    }

    /**
     * Simulates sending a message to a specific topic in the message broker.
     *
     * @param topic The topic to which the message will be published.
     * @param payload The message payload (e.g., JSON string).
     */
    public void send(String topic, String payload) {
        if (!connected) {
            System.err.println("MessageBrokerClient: Not connected to broker. Cannot send message to topic '" + topic + "'.");
            throw new IllegalStateException("Not connected to message broker.");
        }
        System.out.println("MessageBrokerClient: Sending message to topic '" + topic + "': " + payload);
        // In a real scenario, this would use a messaging library (e.g., KafkaProducer, RabbitMQPublisher).
    }

    public boolean isConnected() {
        return connected;
    }
}