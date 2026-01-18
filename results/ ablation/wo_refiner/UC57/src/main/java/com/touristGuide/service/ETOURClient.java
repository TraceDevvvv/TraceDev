package com.touristGuide.service;

import com.touristGuide.model.Location;
import com.touristGuide.model.TouristEvent;
import java.util.ArrayList;
import java.util.List;

public class ETOURClient {
    private boolean connection = true; // Simulated connection state

    public List<TouristEvent> fetchTouristEvents(Location location) {
        // Simulated fetch; returns empty list for simplicity.
        return new ArrayList<>();
    }

    public boolean isConnected() {
        return connection;
    }

    // For testing, allow setting connection state
    public void setConnection(boolean connection) {
        this.connection = connection;
    }
}