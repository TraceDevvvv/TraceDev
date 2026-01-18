import java.util.*;
import java.net.*;
import java.io.*;

/**
 * This program implements the "ViewClassDetails" use case for administrators.
 * It allows viewing details of a selected class (name, address, school year),
 * assuming the user has already logged in and viewed the list of classes.
 * After displaying details, the connection to the SMOS server is interrupted
 * (simulated by closing the socket).
 *
 * The program includes:
 * 1. Class model representing a class with name, address, and school year.
 * 2. Mock data for demonstration (in real scenario, data would come from SMOS server).
 * 3. Connection handling to simulate SMOS server interaction.
 * 4. User interaction via console (mimicking GUI button click).
 * 5. Proper resource cleanup and error handling.
 */
public class ViewClassDetails {

    /**
     * Represents a Class with its details: name, address, and school year.
     * This is a simple POJO (Plain Old Java Object) with getters and setters.
     */
    public static class ClassDetails {
        private String name;
        private String address;
        private String schoolYear;

        public ClassDetails(String name, String address, String schoolYear) {
            this.name = name;
            this.address = address;
            this.schoolYear = schoolYear;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getSchoolYear() {
            return schoolYear;
        }

        public void setSchoolYear(String schoolYear) {
            this.schoolYear = schoolYear;
        }

        @Override
        public String toString() {
            return "Class Details:\n" +
                   "  Name: " + name + "\n" +
                   "  Address: " + address + "\n" +
                   "  School Year: " + schoolYear + "\n";
        }
    }

    /**
     * Simulates a connection to the SMOS server.
     * In a real application, this would manage socket connections,
     * send/receive data, etc. Here we just simulate for demonstration.
     */
    private static class SMOSConnection {
        private Socket socket;
        private boolean connected;

        /**
         * Establish a connection to the SMOS server (mock).
         * @param serverAddress Server address (not used in mock)
         * @param port Server port (not used in mock)
         * @throws IOException if connection fails (simulated)
         */
        public void connect(String serverAddress, int port) throws IOException {
            // Simulate connection attempt
            System.out.println("[DEBUG] Attempting to connect to SMOS server...");
            // In reality, you would create a socket:
            // socket = new Socket(serverAddress, port);
            // For mock, we just set a flag and simulate a short delay.
            try {
                Thread.sleep(100); // Simulate network latency
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            connected = true;
            System.out.println("[DEBUG] Connected to SMOS server.");
        }

        /**
         * Fetch class details from the server by class ID.
         * In a real scenario, this would send a request and parse the response.
         * @param classId The identifier of the class
         * @return ClassDetails object
         * @throws IOException if communication fails
         */
        public ClassDetails fetchClassDetails(String classId) throws IOException {
            if (!connected) {
                throw new IOException("Not connected to SMOS server.");
            }
            // Simulate fetching data from server.
            // In reality, you'd send a request over the socket and read response.
            System.out.println("[DEBUG] Fetching details for class ID: " + classId);
            // Mock data based on classId. In real app, this would come from server.
            switch (classId) {
                case "CS101":
                    return new ClassDetails("Introduction to Computer Science",
                                            "Room 101, Science Building",
                                            "2024-2025");
                case "MATH202":
                    return new ClassDetails("Advanced Mathematics",
                                            "Room 205, Math Building",
                                            "2024-2025");
                case "ENG150":
                    return new ClassDetails("English Composition",
                                            "Room 150, Humanities Building",
                                            "2024-2025");
                default:
                    // If classId not found, return a generic one.
                    return new ClassDetails("Class " + classId,
                                            "Address not specified",
                                            "2024-2025");
            }
        }

        /**
         * Interrupt (close) the connection to the SMOS server.
         * This simulates the postcondition "Connection to the SMOS server interrupted".
         */
        public void interruptConnection() {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.err.println("Error closing socket: " + e.getMessage());
                }
            }
            connected = false;
            System.out.println("[DEBUG] Connection to SMOS server interrupted.");
        }
    }

    /**
     * Main application logic.
     * Steps:
     * 1. Simulate preconditions: user logged in, list of classes displayed.
     * 2. Allow user to select a class (simulate button click).
     * 3. Connect to SMOS server, fetch details, display them.
     * 4. Interrupt connection as per postcondition.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SMOSConnection connection = new SMOSConnection();

        // Precondition: The user has already logged in and viewed the list of classes.
        System.out.println("=== Administrator Class Details Viewer ===");
        System.out.println("Preconditions satisfied: User logged in, class list displayed.");
        System.out.println("Available class IDs: CS101, MATH202, ENG150");
        System.out.println("(In a real GUI, you'd click 'Show class details' button.)");

        // Simulate user selecting a class (input mimics button click).
        System.out.print("Enter the Class ID to view details: ");
        String classId = scanner.nextLine().trim();

        // Edge case: empty input
        if (classId.isEmpty()) {
            System.out.println("Error: Class ID cannot be empty.");
            scanner.close();
            return;
        }

        try {
            // Simulate connecting to SMOS server.
            // In a real system, connection parameters would come from configuration.
            connection.connect("smos.example.com", 8080);

            // Fetch class details from server.
            ClassDetails details = connection.fetchClassDetails(classId);

            // Display the details (as per use case step 1).
            System.out.println("\n=== Class Details ===");
            System.out.println(details.toString());

            // Postcondition: Connection to SMOS server interrupted.
            connection.interruptConnection();

            System.out.println("Use case completed successfully.");
        } catch (IOException e) {
            System.err.println("Error communicating with SMOS server: " + e.getMessage());
            // Ensure connection is interrupted even on error.
            connection.interruptConnection();
        } finally {
            scanner.close();
        }
    }
}