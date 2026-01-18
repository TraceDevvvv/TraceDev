package com.example;

/**
 * Interface for site repository operations.
 */
public interface SiteRepository {
    /**
     * Inserts a site bookmark.
     * Returns true if successful.
     */
    boolean insertSite(String siteId);

    /**
     * Returns the current connection status.
     */
    boolean getConnectionStatus();

    /**
     * Establishes a connection.
     */
    void establishConnection();
}