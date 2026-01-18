package com.example.convention;

/**
 * Adapter interface for communication with the external ETOUR server.
 */
public interface ETOURServerAdapter {
    /**
     * Checks the connection to the ETOUR server.
     * @return true if connection is available, false otherwise.
     */
    boolean checkConnection();

    /**
     * Notifies the ETOUR server about a convention activation.
     * @param conventionId the ID of the activated convention.
     */
    void notifyActivation(String conventionId);
}