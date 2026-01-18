package com.example.reportcard.service;

import com.example.reportcard.exception.NetworkException;

/**
 * Interface for checking network connectivity.
 */
public interface INetworkConnectivityService {
    /**
     * Checks if there is an active network connection.
     * @throws NetworkException if the connection is interrupted.
     */
    void checkConnection() throws NetworkException;
}