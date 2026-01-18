package com.example.model;

/**
 * Raw data read from GPS sensor.
 */
public class GPSRawData {
    private String rawLatitude;
    private String rawLongitude;
    private int signalStrength;
    private int satellites;

    public GPSRawData(String rawLatitude, String rawLongitude, int signalStrength, int satellites) {
        this.rawLatitude = rawLatitude;
        this.rawLongitude = rawLongitude;
        this.signalStrength = signalStrength;
        this.satellites = satellites;
    }

    public String getRawLatitude() {
        return rawLatitude;
    }

    public String getRawLongitude() {
        return rawLongitude;
    }

    public int getSignalStrength() {
        return signalStrength;
    }

    public int getSatellites() {
        return satellites;
    }

    /**
     * Validates raw data: checks if latitude and longitude are not null/empty,
     * signal strength positive, satellites count positive.
     */
    public boolean isValid() {
        return rawLatitude != null && !rawLatitude.trim().isEmpty() &&
               rawLongitude != null && !rawLongitude.trim().isEmpty() &&
               signalStrength > 0 &&
               satellites > 0;
    }
}