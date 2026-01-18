package com.cultural.application.service;

/**
 * External service interface for ETOUR server communication.
 */
public interface ETOURServerService {
    boolean isConnected();
    boolean notifyInclusion(String culturalObjectId);
    void checkConnection() throws ConnectionException;
}