package com.example.exception;

import java.util.Map;

/**
 * Represents a system state with data.
 */
public class State {
    private Map<String, Object> data;

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}