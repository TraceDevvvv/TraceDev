'''
Simulates interaction with an SMOS (School Management Operating System) server
to fetch student data. In a real application, this would involve network
requests, database queries, and error handling.
'''
import java.util.ArrayList;
import java.util.List;
public class SMOSService {
    // A static list to simulate a database of student records
    private static final List<StudentData> mockStudentDataBase = new ArrayList<>();
    private boolean connected = false;
    /**
     * Initializes the mock database with some sample student data.
     * This static block ensures the data is populated only once when the class is loaded.
     */
    static {
        // Populate with dummy data for a specific student (e.g., for demonstration purposes).
        // In a real scenario, this would be retrieved from a dedicated data source or database
        // and filtered by student ID.
        mockStudentDataBase.add(new StudentData("2023-01-10", 0, "Excellent participation", 0, "N/A"));
        mockStudentDataBase.add(new StudentData("2023-01-11", 1, "Left early due to medical appointment", 0, "Medical certificate provided"));
        mockStudentDataBase.add(new StudentData("2023-01-12", 0, "No issues", 1, "Late due to traffic"));
        mockStudentDataBase.add(new StudentData("2023-01-13", 0, "Received a warning for talking during class", 0, "N/A"));
        mockStudentDataBase.add(new StudentData("2023-01-14", 0, "Participated actively", 0, "N/A"));
        mockStudentDataBase.add(new StudentData("2023-01-17", 0, "No issues", 2, "Public transport delays"));
    }
    /**
     * Constructs a new SMOSService instance.
     * Simulates establishing a connection to the SMOS server for a data retrieval session.
     */
    public SMOSService() {
        System.out.println("Connecting to SMOS server for data retrieval session...");
        this.connected = true;
    }
    /**
     * Simulates searching the archive for a specific student's class register data.
     * Assumes a specific student ID (e.g., for the currently logged-in student).
     *
     * @param studentId The ID of the student whose data is to be fetched.
     * @return A list of StudentData objects belonging to the specified student.
     *         Returns an empty list if not connected or no data is found.
     */
    public List<StudentData> fetchStudentData(String studentId) {
        if (!connected) {
            System.err.println("Error: Not connected to SMOS server. Cannot fetch data.");
            return new ArrayList<>();
        }
        // Simulate a delay for network latency
        try {
            Thread.sleep(500); // 0.5 seconds delay
        } catch (InterruptedException e) {
            // Restore the interrupted status and log the error
            Thread.currentThread().interrupt();
            System.err.println("SMOSService data fetching interrupted: " + e.getMessage());
            return new ArrayList<>(); // Return empty list on interruption
        }
        System.out.println("Fetching data for student ID: " + studentId);
        // In this mock, we return all data from the static database as if it belongs to one student
        // A real implementation would filter by studentId, e.g., using streams:
        // return mockStudentDataBase.stream().filter(data -> data.getStudentId().equals(studentId)).collect(Collectors.toList());
        return new ArrayList<>(mockStudentDataBase);
    }
    /**
     * Simulates the interruption of the connection to the SMOS server.
     * This method is intended to be called after a data retrieval operation
     * to signify that the specific session's connection has concluded.
     */
    public void interruptConnection() {
        this.connected = false;
        System.out.println("Connecting to the SMOS server interrupted for this session.");
    }
    /**
     * Checks if the service is currently connected to the SMOS server.
     * @return true if connected, false otherwise.
     */
    public boolean isConnected() {
        return connected;
    }
}