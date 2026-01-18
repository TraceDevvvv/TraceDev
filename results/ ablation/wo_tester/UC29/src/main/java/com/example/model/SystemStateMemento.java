package com.example.model;

import java.util.Map;
import java.util.HashMap;

/**
 * Abstract memento class for storing system state.
 * Part of the Memento pattern.
 */
public abstract class SystemStateMemento {
    protected Map<String, Object> stateData;

    public SystemStateMemento() {
        this.stateData = new HashMap<>();
    }

    public Map<String, Object> getStateData() {
        return stateData;
    }

    public void setStateData(Map<String, Object> data) {
        this.stateData = data;
    }
}