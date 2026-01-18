package com.example.infrastructure;

import com.example.domain.Banner;

/**
 * Interface for integration with the ETOUR system.
 * Part of the Infrastructure Layer.
 */
public interface IETourService {
    /**
     * Notifies the ETOUR system about a change in a banner.
     * @param banner The Banner object that has been changed.
     * @return True if the notification was successful, false otherwise.
     */
    boolean notifyBannerChange(Banner banner);

    /**
     * Checks the connection status to the ETOUR system.
     * @return True if the connection is active, false otherwise.
     */
    boolean checkConnection();
}