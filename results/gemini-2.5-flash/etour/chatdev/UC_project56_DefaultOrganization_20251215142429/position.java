/**
 * Represents a geographical position with latitude and longitude,
 * along with a status message and an indication of success.
 */
public class Position {
    private final double latitude;
    private final double longitude;
    private final String statusMessage;
    private final boolean success;
    /**
     * Private constructor to create a Position object.
     * Use static factory methods for better control (e.g., createSuccess, createFailed).
     *
     * @param latitude       The latitude coordinate.
     * @param longitude      The longitude coordinate.
     * @param statusMessage  A descriptive message about the localization attempt.
     * @param success        True if localization was successful, false otherwise.
     */
    private Position(double latitude, double longitude, String statusMessage, boolean success) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.statusMessage = statusMessage;
        this.success = success;
    }
    /**
     * Factory method to create a successful Position.
     * @param latitude  The detected latitude.
     * @param longitude The detected longitude.
     * @return A Position object indicating success.
     */
    public static Position createSuccess(double latitude, double longitude) {
        return new Position(latitude, longitude, "Localization successful.", true);
    }
    /**
     * Factory method to create a failed Position.
     * @param message A message explaining why localization failed.
     * @return A Position object indicating failure.
     */
    public static Position createFailed(String message) {
        // Use default 'not a number' values for coordinates when localization fails.
        return new Position(Double.NaN, Double.NaN, message, false);
    }
    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public String getStatusMessage() {
        return statusMessage;
    }
    public boolean isSuccess() {
        return success;
    }
    @Override
    public String toString() {
        if (success) {
            return String.format("Status: Success\nLatitude: %.6f\nLongitude: %.6f\nMessage: %s",
                    latitude, longitude, statusMessage);
        } else {
            return String.format("Status: Failed\nMessage: %s", statusMessage);
        }
    }
}