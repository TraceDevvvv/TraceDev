/**
 * Utility class to simulate communication with a remote server.
 * This handles sending absence data to the server.
 * Includes a simulated failure mechanism to demonstrate "Connection to the interrupted SMOS server" postcondition.
 */
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
public class ServerCommunicator {
    private static Random random = new Random();
    /**
     * Simulates sending absence data for a list of students to the server.
     * Introduces a small chance of simulated server communication failure.
     *
     * @param className  The name of the class for which absence data is being sent.
     * @param students   A list of {@link Student} objects with their updated presence status.
     * @return true if data was successfully "sent" to the server, false if a simulated error occurred.
     */
    public static boolean sendAbsenceDataToServer(String className, List<Student> students) {
        System.out.println("--- SIMULATING SERVER DATA TRANSMISSION ---");
        System.out.println("Class: " + className);
        System.out.println("Date: " + LocalDate.now());
        // Simulate a 10% chance of server connection interruption for demonstration
        if (random.nextInt(10) == 0) { // 1 in 10 chance of failure
            System.err.println("ERROR: Simulated server connection interrupted (SMOS server offline).");
            return false; // Simulate connection failure
        }
        StringBuilder dataSent = new StringBuilder();
        for (Student student : students) {
            dataSent.append(String.format("  Student ID: %s, Name: %s, Status: %s\n",
                    student.getId(), student.getName(), student.isPresent() ? "PRESENT" : "ABSENT"));
        }
        System.out.println("Data successfully prepared and sent to server:");
        System.out.println(dataSent.toString());
        System.out.println("--- SERVER COMMUNICATION COMPLETE ---\n");
        return true; // Simulate successful communication
    }
}