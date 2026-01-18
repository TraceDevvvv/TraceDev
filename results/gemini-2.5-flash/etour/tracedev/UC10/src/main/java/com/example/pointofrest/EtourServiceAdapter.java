package com.example.pointofrest;

/**
 * Adapter for communicating with the external ETOUR service.
 * It's responsible for making requests to ETOUR and receiving raw data.
 */
public class EtourServiceAdapter {

    // Flag to simulate ETOUR server connection issues
    private boolean simulateConnectionFailure = false;

    /**
     * Retrieves raw PointOfRest data from the ETOUR server for a given ID.
     * Corresponds to message 'requestPointOfRest' in sequence diagram.
     *
     * @param pointOfRestId The ID of the PointOfRest in the ETOUR system.
     * @return RawEtourData containing the details.
     * @throws Exception if connection to ETOUR fails or data cannot be retrieved.
     */
    public RawEtourData requestPointOfRest(String pointOfRestId) throws Exception {
        System.out.println("[EtourServiceAdapter] Requesting raw data for ID: " + pointOfRestId + " from ETOUR server.");

        if (simulateConnectionFailure) {
            System.out.println("[EtourServiceAdapter] Simulating connection failure to ETOUR server.");
            throw new Exception("Connection to ETOUR Server failed.");
        }

        // Simulate fetching data from ETOUR server
        // In a real application, this would involve HTTP calls, parsing XML/JSON, etc.
        if ("POR123".equals(pointOfRestId)) {
            RawEtourData rawData = new RawEtourData(
                pointOfRestId,
                "Etour Cafe Central",
                "123 Main St, Anytown",
                "Cafe",
                "A cozy cafe with free Wi-Fi.",
                "info@etourcafe.com"
            );
            System.out.println("[EtourServiceAdapter] Successfully fetched raw data for ID: " + pointOfRestId);
            return rawData;
        } else if ("POR456".equals(pointOfRestId)) {
            RawEtourData rawData = new RawEtourData(
                pointOfRestId,
                "Etour Hotel Rest",
                "456 Oak Ave, Bigcity",
                "Hotel",
                "Luxury hotel with fine dining.",
                "reservations@etourhotel.com"
            );
            System.out.println("[EtourServiceAdapter] Successfully fetched raw data for ID: " + pointOfRestId);
            return rawData;
        } else {
            System.out.println("[EtourServiceAdapter] No data found in ETOUR for ID: " + pointOfRestId);
            throw new Exception("Point of Rest with ID " + pointOfRestId + " not found in ETOUR.");
        }
    }

    /**
     * Sets the flag to simulate connection failure for testing.
     * @param simulateConnectionFailure True to simulate failure, false otherwise.
     */
    public void setSimulateConnectionFailure(boolean simulateConnectionFailure) {
        this.simulateConnectionFailure = simulateConnectionFailure;
    }
}