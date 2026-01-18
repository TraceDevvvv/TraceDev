package com.system;

import java.util.Map;
import java.util.HashMap;

/**
 * SystemStateStore class for storing and retrieving system states.
 * Methods: save, retrieve.
 */
public class SystemStateStore {
    private Map<String, SystemState> states = new HashMap<>();

    public void save(SystemState state) {
        states.put(state.getStateId(), state);
        System.out.println("State saved: " + state.getStateId());
    }

    public SystemState retrieve(String stateId) {
        System.out.println("Retrieving state: " + stateId);
        return states.get(stateId);
    }

    // Method for message m20: StateStore -> StateMgr, "previous SystemState"
    public SystemState returnPreviousSystemState(String stateId) {
        return retrieve(stateId);
    }
}