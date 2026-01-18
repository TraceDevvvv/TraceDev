package com.example.network;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Dummy class representing data returned from the server.
 * In a real application, this would be a structured response.
 */
public class ServerData {
    private Map<String, Object> fields;

    public ServerData() {
        this.fields = new HashMap<>();
    }

    public void setField(String key, String value) {
        fields.put(key, value);
    }

    public void setDateField(String key, Date value) {
        fields.put(key, value);
    }

    public String getField(String key) {
        return (String) fields.get(key);
    }

    public Date getDateField(String key) {
        return (Date) fields.get(key);
    }
}