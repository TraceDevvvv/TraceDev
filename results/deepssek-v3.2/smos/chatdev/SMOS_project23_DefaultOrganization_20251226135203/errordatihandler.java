/**
 * This class handles the "Errodati" (Error Data) use case.
 * It provides methods for error reporting and recovery when invalid data is entered.
 */
public class ErrodatiHandler {
    /**
     * Reports an error related to invalid teaching data.
     * This method would typically log the error and notify administrators.
     *
     * @param teachingName The invalid teaching name that was entered
     * @param errorMessage A description of the validation error
     */
    public static void reportInvalidTeachingData(String teachingName, String errorMessage) {
        System.err.println("Errodati: Invalid teaching data detected");
        System.err.println("Teaching Name Attempted: " + teachingName);
        System.err.println("Error: " + errorMessage);
        System.err.println("Timestamp: " + java.time.LocalDateTime.now());
        // In a real application, this might:
        // - Log to a file or database
        // - Send notification to administrators
        // - Trigger recovery procedures
    }
    /**
     * Provides recovery suggestions for invalid teaching data.
     *
     * @return A list of suggestions for valid teaching names
     */
    public static String[] getRecoverySuggestions() {
        return new String[] {
            "Ensure teaching name is not empty",
            "Teaching name should be 1-100 characters",
            "Avoid special characters except spaces and hyphens",
            "Make sure teaching name is descriptive and clear"
        };
    }
}