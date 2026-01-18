package com.etour.interfaceadapters;

import java.util.Map;

/**
 * Generic response DTO returned by the controller.
 */
public class ResponseDTO {
    private final String status;
    private final Map<String, Object> data;
    private final String message;

    public ResponseDTO(String status, Map<String, Object> data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}