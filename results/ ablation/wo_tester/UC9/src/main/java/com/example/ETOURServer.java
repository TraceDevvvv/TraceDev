package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * External system server that provides PointOfRest data.
 * Exit condition: Connection to server ETOUR is interrupted.
 */
public class ETOURServer {
    private boolean connected;

    public ETOURServer(boolean initiallyConnected) {
        this.connected = initiallyConnected;
    }

    /**
     * Simulates querying data from the server.
     * @param specification the specification to filter (not used in this simple simulation)
     * @return list of PointOfRest objects
     */
    public List<PointOfRest> queryData(PointOfRestSpecification specification) {
        if (!connected) {
            return new ArrayList<>();
        }
        // Simulate a database of points
        List<PointOfRest> allPoints = new ArrayList<>();
        allPoints.add(new PointOfRest("1", "Rest Stop Alpha", "Highway 101", Arrays.asList("WiFi", "Restrooms")));
        allPoints.add(new PointOfRest("2", "Beta Lounge", "Highway 202", Arrays.asList("Coffee", "Parking")));
        allPoints.add(new PointOfRest("3", "Gamma Oasis", "Highway 303", Arrays.asList("Food", "WiFi", "Showers")));
        return allPoints;
    }

    /**
     * Checks if the server is connected.
     * @return true if connected, false otherwise
     */
    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    /**
     * Alias method for sequence diagram message "queryResults".
     * @param specification the specification
     * @return list of PointOfRest objects
     */
    public List<PointOfRest> queryResults(PointOfRestSpecification specification) {
        return queryData(specification);
    }

    /**
     * Alias method for sequence diagram message "connectionError".
     * @return empty list to indicate connection error
     */
    public List<PointOfRest> connectionError() {
        return new ArrayList<>();
    }
}