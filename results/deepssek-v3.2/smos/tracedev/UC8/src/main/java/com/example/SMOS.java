package com.example;

/**
 * SMOS matching sequence diagram participant "SMOS".
 */
public class SMOS {
    private SMOSClient smosClient;
    private ErrorHandler errorHandler;

    public SMOS(SMOSClient smosClient, ErrorHandler errorHandler) {
        this.smosClient = smosClient;
        this.errorHandler = errorHandler;
    }

    /**
     * ConnectionException (message m35).
     */
    public void connectionException(Exception e) {
        errorHandler.handleSystemError(e);
    }

    /**
     * Data sent successfully (message m45).
     */
    public String dataSentSuccessfully() {
        return "data sent successfully";
    }

    /**
     * Send data.
     */
    public void sendData(Object data) {
        smosClient.sendData(data);
    }
}