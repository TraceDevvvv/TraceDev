package com.example;

/**
 * Represents a connection to the SMOS server.
 * Supports requirement R2 - Exit Condition: connection interruption.
 */
public class SMOSConnection {
    private Session session;

    /**
     * Constructs an SMOSConnection with the given session.
     *
     * @param session the session associated with this connection
     */
    public SMOSConnection(Session session) {
        this.session = session;
    }

    /**
     * Disconnects the connection and terminates the associated session.
     */
    public void disconnect() {
        // Requirement R2: connection interruption leads to session termination
        if (session != null) {
            session.terminate();
        }
        System.out.println("SMOSConnection disconnected.");
    }

    /**
     * Gets the session associated with this connection.
     *
     * @return the session
     */
    public Session getSession() {
        return session;
    }
}