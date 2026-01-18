package com.example.delaysystem;

import java.util.Date;

/**
 * Interface for interacting with the SMOS Server Gateway.
 * This gateway handles communication with the external SMOS server for scheduling and delay updates.
 */
public interface ISMOSServerGateway {

    /**
     * Retrieves scheduling information from the SMOS server for a given date.
     *
     * @param date The date for which to get scheduling information.
     * @return A SchedulingInfoDTO containing the scheduling details, or null if retrieval fails.
     */
    SchedulingInfoDTO getSchedulingInfo(Date date);

    /**
     * Sends an update request for delay data to the SMOS server.
     *
     * @param delayDto The DelayDTO containing the updated delay information.
     * @return true if the update request was successful, false otherwise.
     */
    boolean sendUpdateDelayRequest(DelayDTO delayDto);

    /**
     * Checks the connection status to the SMOS server.
     *
     * @return true if the connection is active, false otherwise.
     */
    boolean checkConnection();
}