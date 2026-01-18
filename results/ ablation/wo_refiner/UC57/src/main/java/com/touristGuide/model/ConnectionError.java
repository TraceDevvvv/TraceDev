package com.touristGuide.model;

public class ConnectionError extends ValidationError {
    private String serverName;

    public ConnectionError(String errorCode, String message, String serverName) {
        super(errorCode, message);
        this.serverName = serverName;
    }

    public String getServerName() {
        return serverName;
    }
}