'''
A service class that simulates interaction with a backend system (e.g., SMOS server)
to retrieve digital register data. It includes a mock database for demonstration
purposes and simulates connection status.
'''
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class RegisterService {
    // A mock database to store dummy register data, keyed by academic year
    private Map<Integer, List<Register>> mockDatabase;
    private boolean isSMOSConnected; // Simulates the connection status to the SMOS server
    /**
     * Constructs the RegisterService.
     * Initializes the mock database with some sample data and sets the initial
     * SMOS server connection status. It's assumed to be connected at startup
     * based on preconditions implying login for an administrator.
     */
    public RegisterService() {
        mockDatabase = new HashMap<>();
        this.isSMOSConnected = false; // Start disconnected, consistent with needing explicit connect.
        initMockData(); // Populate mock database with dummy data
    }
    /**
     * Initializes the mock database with sample data for various academic years.
     */
    private void initMockData() {
        // Year 2023
        mockDatabase.put(2023, Arrays.asList(
            new Register(1, 2023, "10A", "Math", "2023-09-01", "Chapter 1 review. All present."),
            new Register(2, 2023, "10A", "Science", "2023-09-02", "Experiment on photosynthesis. John Doe absent."),
            new Register(3, 2023, "11B", "History", "2023-09-03", "World War I lecture. 2 students late."),
            new Register(4, 2023, "10A", "Math", "2023-09-04", "New topic on algebra. Mary Smith absent.")
        ));
        // Year 2022
        mockDatabase.put(2022, Arrays.asList(
            new Register(101, 2022, "9C", "English", "2022-09-05", "Grammar lesson. Full attendance."),
            new Register(102, 2022, "12A", "Physics", "2022-09-06", "Optics practical. All present."),
            new Register(103, 2022, "9C", "English", "2022-09-07", "Literature discussion.")
        ));
        // Year 2021
        mockDatabase.put(2021, Arrays.asList(
            new Register(201, 2021, "8D", "Art", "2021-10-10", "Sketching fundamentals. All present.")
        ));
        // Year 2020 (no data)
        mockDatabase.put(2020, new ArrayList<>());
    }
    /**
     * Simulates connecting to the SMOS server.
     */
    public void connectSMOS() {
        if (!isSMOSConnected) {
            System.out.println("Attempting to connect to SMOS server...");
            try {
                Thread.sleep(500); // Simulate network delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            this.isSMOSConnected = true;
            System.out.println("SMOS server connected.");
        } else {
            System.out.println("SMOS server already connected.");
        }
    }
    /**
     * Simulates disconnecting from the SMOS server.
     */
    public void disconnectSMOS() {
        if (isSMOSConnected) {
            System.out.println("Disconnecting from SMOS server...");
            try {
                Thread.sleep(300); // Simulate network delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            this.isSMOSConnected = false;
            System.out.println("SMOS server disconnected.");
        } else {
            System.out.println("SMOS server already disconnected.");
        }
    }
    /**
     * Checks if the service is currently connected to the simulated SMOS server.
     *
     * @return true if connected, false otherwise.
     */
    public boolean isConnected() {
        return isSMOSConnected;
    }
    /**
     * Retrieves a list of digital registers for a specific academic year.
     * Simulates searching in an archive (mock database).
     *
     * @param academicYear The year for which to retrieve registers.
     * @return A list of Register objects for the specified year. Returns an empty list if no registers are found.
     * @throws IllegalStateException If the SMOS server is not connected, reflecting a key precondition.
     */
    public List<Register> getRegistersByAcademicYear(int academicYear) {
        if (!isSMOSConnected) {
            throw new IllegalStateException("SMOS server is not connected.");
        }
        System.out.println("Searching for registers for academic year: " + academicYear);
        // Simulate database lookup delay
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            // Propagate as a runtime exception or handle more gracefully
            throw new RuntimeException("Interrupted while fetching registers", e);
        }
        // Return a new ArrayList to ensure the original mock data isn't modified directly if passed out
        return new ArrayList<>(mockDatabase.getOrDefault(academicYear, new ArrayList<>()));
    }
}