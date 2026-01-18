package com.example.delaysystem;

import java.util.Date;
import java.util.ArrayList; // For empty lists

/**
 * Concrete implementation of IDelayRepository.
 * This class handles data access operations for delays, often delegating to a gateway.
 */
public class DelayRepository implements IDelayRepository {

    private ISMOSServerGateway smosServerGateway; // CD-TRACE: smosServerGateway

    /**
     * Constructor for DelayRepository.
     *
     * @param smosServerGateway The gateway to communicate with the SMOS server.
     */
    public DelayRepository(ISMOSServerGateway smosServerGateway) {
        this.smosServerGateway = smosServerGateway;
    }

    /**
     * Fetches scheduling information by date from the SMOS server.
     * Includes connection check as per sequence diagram R13.
     *
     * @param date The date for which to fetch scheduling information.
     * @return A DTO containing scheduling information, or null if connection/fetch fails.
     */
    @Override
    public SchedulingInfoDTO fetchSchedulingInfoByDate(Date date) {
        System.out.println("[DelayRepository] Fetching scheduling info for date: " + date);

        // R13: Check connection to SMOS server first
        if (!smosServerGateway.checkConnection()) {
            System.err.println("[DelayRepository] Connection to SMOS server interrupted. Cannot fetch scheduling data.");
            return null; // Return null to indicate connection failure
        }

        // If connection is successful, proceed to get scheduling info
        SchedulingInfoDTO schedulingInfoDTO = smosServerGateway.getSchedulingInfo(date);

        if (schedulingInfoDTO == null) {
            System.err.println("[DelayRepository] Failed to retrieve scheduling info from SMOS server for date: " + date);
        } else {
            System.out.println("[DelayRepository] Successfully retrieved scheduling info for date: " + date);
        }
        return schedulingInfoDTO;
    }

    /**
     * Saves delay data to the SMOS server.
     * Includes connection check as per sequence diagram R13.
     *
     * @param delayDto The DelayDTO containing the data to save.
     * @return true if the delay data was saved successfully, false otherwise.
     */
    @Override
    public boolean saveDelayData(DelayDTO delayDto) {
        System.out.println("[DelayRepository] Saving delay data: " + delayDto);

        // R13: Check connection to SMOS server first
        if (!smosServerGateway.checkConnection()) {
            System.err.println("[DelayRepository] Connection to SMOS server interrupted. Cannot save delay data.");
            return false; // Return false to indicate connection failure
        }

        // If connection is successful, proceed to send update request
        boolean success = smosServerGateway.sendUpdateDelayRequest(delayDto);

        if (!success) {
            System.err.println("[DelayRepository] Failed to save delay data to SMOS server for delay: " + delayDto);
        } else {
            System.out.println("[DelayRepository] Successfully saved delay data: " + delayDto);
        }
        return success;
    }
}