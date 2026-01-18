/*
 * DOCSTRING:
 * This class simulates the service layer responsible for handling convention requests.
 * In a real application, this would interact with a backend system, database, or external APIs.
 * For this example, it simply prints the request details and simulates success/failure.
 */
class ConventionService {
    /**
     * Simulates sending a convention request to the agency.
     * This method represents the "Send the request to the Convention" step.
     * It randomly simulates success or failure, mimicking potential network issues or
     * backend processing errors for the "Interruption of the connection to the server ETOUR"
     * quality requirement.
     * @param data The ConventionData object containing the request details.
     * @return true if the request was "sent" successfully, false otherwise.
     */
    public boolean sendConventionRequest(ConventionData data) {
        System.out.println("Processing convention request for:");
        System.out.println(data.toString());
        // Simulate network delay or processing time
        try {
            Thread.sleep(1500); // Simulate 1.5 seconds of processing
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Convention sending interrupted unexpectedly.");
            return false;
        }
        // Simulate success/failure randomly to cover "Interruption of the connection to the server ETOUR"
        // and other potential backend issues.
        // Approximately 80% chance of success, 20% chance of failure (simulated error).
        if (Math.random() < 0.8) {
            System.out.println("Convention request successfully sent to the Agency!");
            // Exit condition: "The notification about the call for the Convention to the Agency."
            return true;
        } else {
            System.err.println("Failed to send convention request. Server ETOUR connection interrupted or processing error.");
            return false; // Simulates an error case (e.g., server ETOUR interruption)
        }
    }
}