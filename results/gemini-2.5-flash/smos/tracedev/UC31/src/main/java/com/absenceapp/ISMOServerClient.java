package com.absenceapp;

/**
 * Interface for interacting with the SMO Server.
 * REQ-005: Specifies functionality to send modified data to an external system.
 */
public interface ISMOServerClient {
    /**
     * Sends modified data to the SMO Server.
     * The type of data (Absence object, ID for deletion, etc.) is generic.
     *
     * @param data The data object to send to the SMO Server.
     * @throws ConnectionException if there's a problem connecting to the SMO Server.
     */
    void sendModifiedData(Object data) throws ConnectionException;
}