package com.example.delaysystem.repository;

import com.example.delaysystem.connector.SmosServerConnector;
import com.example.delaysystem.model.DelayData;
import java.util.List;

/**
 * Concrete implementation of IDelayRepository that interacts with the SMOS server.
 */
public class SmpsDelayRepository implements IDelayRepository {
    // Connector to the SMOS server for actual data transmission
    protected SmosServerConnector smosServerConnector;

    /**
     * Constructs a new SmpsDelayRepository.
     *
     * @param smosServerConnector The connector instance to communicate with SMOS.
     */
    public SmpsDelayRepository(SmosServerConnector smosServerConnector) {
        this.smosServerConnector = smosServerConnector;
    }

    /**
     * Saves a list of DelayData objects by sending them to the SMOS server.
     * Implements part of the "Register Delays" sequence, specifically the interaction with the external system.
     *
     * @param delayDataList A list of DelayData objects to be saved.
     * @return true if the data was successfully sent to SMOS, false otherwise.
     */
    @Override
    public boolean saveDelayData(List<DelayData> delayDataList) {
        System.out.println("[SmpsDelayRepo] Attempting to save " + delayDataList.size() + " DelayData entries via SMOS connector.");
        // As per sequence diagram: SmpsDelayRepo -> SMOSConnector : sendDelayData()
        boolean success = smosServerConnector.sendDelayData(delayDataList);
        if (success) {
            System.out.println("[SmpsDelayRepo] Successfully sent delay data to SMOS server.");
        } else {
            System.err.println("[SmpsDelayRepo] Failed to send delay data to SMOS server.");
        }
        return success;
    }
}