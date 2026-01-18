package com.cultural.infrastructure.service;

import com.cultural.application.service.ETOURServerService;
import com.cultural.application.service.ConnectionException;

/**
 * Simulated implementation of ETOUR server service.
 */
public class ETOURServerServiceImpl implements ETOURServerService {
    private String serverUrl;
    private boolean simulateConnectionFailure = false; // for testing

    public ETOURServerServiceImpl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    @Override
    public boolean isConnected() {
        // Simulate connection check
        return !simulateConnectionFailure;
    }

    @Override
    public boolean notifyInclusion(String culturalObjectId) {
        System.out.println("Notifying ETOUR server at " + serverUrl + " about inclusion of object " + culturalObjectId);
        // Simulate successful notification
        return true;
    }

    @Override
    public void checkConnection() throws ConnectionException {
        if (!isConnected()) {
            throw new ConnectionException("Cannot connect to ETOUR server", "ETOUR_CONN_ERR");
        }
    }

    // For testing: allow simulation