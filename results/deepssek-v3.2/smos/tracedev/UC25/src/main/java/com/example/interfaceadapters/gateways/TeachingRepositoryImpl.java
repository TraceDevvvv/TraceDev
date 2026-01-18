package com.example.interfaceadapters.gateways;

import com.example.domain.Teaching;
import com.example.frameworksdrivers.SMOSServerConnection;
import com.example.frameworksdrivers.ConnectionException;

import java.util.List;

/**
 * Implementation of TeachingRepository that delegates to SMOSServerConnection.
 */
public class TeachingRepositoryImpl implements TeachingRepository {
    private final SMOSServerConnection smosServerConnection;

    public TeachingRepositoryImpl(SMOSServerConnection connection) {
        this.smosServerConnection = connection;
    }

    @Override
    public Teaching findById(String id) {
        try {
            return smosServerConnection.fetchTeaching(id);
        } catch (ConnectionException e) {
            // If connection fails, return null (as per sequence diagram).
            return null;
        }
    }

    @Override
    public void save(Teaching teaching) {
        boolean success = smosServerConnection.updateTeaching(teaching);
        if (!success) {
            throw new ConnectionException("Save failed: Connection lost");
        }
    }

    @Override
    public List<Teaching> findAll() {
        return smosServerConnection.fetchAllTeachings();
    }
}