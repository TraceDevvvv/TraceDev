/**
 * Simulates server connection for saving absence data.
 */
public class ServerConnection {
    /**
     * Send modified absence data to server.
     */
    public void saveAbsenceData(Absence absence) {
        // Simulate server communication
        System.out.println("Saving absence data for student " + 
                          absence.getStudent().getName() + 
                          " on " + absence.getDate() + 
                          " to server...");
        // Simulate potential server interruption
        if (Math.random() < 0.1) { // 10% chance of interruption
            throw new RuntimeException("Connection to the SMOS server interrupted");
        }
        System.out.println("Data saved successfully.");
    }
}