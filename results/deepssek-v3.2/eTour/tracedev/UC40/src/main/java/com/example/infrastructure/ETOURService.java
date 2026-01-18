package com.example.infrastructure;

/**
 * Interface for communicating with external ETOUR system.
 */
public interface ETOURService {
    /**
     * Authenticates the operator with ETOUR.
     */
    boolean authenticate(String token);

    /**
     * Removes a banner from the remote ETOUR system.
     */
    boolean removeRemoteBanner(String bannerId);

    /**
     * Notifies ETOUR about a successful banner deletion.
     */
    void notifyDeletion(String bannerId, String pointOfRestId);
}