'''
Simulates a data service responsible for fetching academic class information.
This service mimics interaction with an external system (like the "SMOS server")
by providing hardcoded data and the ability to simulate connection interruptions.
'''
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
public class ClassDataService {
    // Hardcoded list of classes for demonstration purposes
    private static final List<AcademicClass> ALL_CLASSES = Arrays.asList(
            new AcademicClass("CS101", "Intro to Programming", "2022-2023", "Dr. Smith"),
            new AcademicClass("MA201", "Calculus I", "2022-2023", "Prof. Johnson"),
            new AcademicClass("PH100", "Physics Fundamentals", "2022-2023", "Dr. Lee"),
            new AcademicClass("CS101", "Intro to Programming", "2023-2024", "Dr. Smith"),
            new AcademicClass("DS300", "Data Structures", "2023-2024", "Prof. Davis"),
            new AcademicClass("AL105", "Algebra II", "2023-2024", "Ms. Chen"),
            new AcademicClass("SE400", "Software Engineering", "2023-2024", "Dr. Miller"),
            new AcademicClass("CS101", "Intro to Programming", "2024-2025", "Dr. Garcia"),
            new AcademicClass("AI500", "Artificial Intelligence", "2024-2025", "Prof. Williams"),
            new AcademicClass("CYB200", "Cybersecurity Basics", "2024-2025", "Mr. Brown")
    );
    private boolean connectionInterrupted = false; // Flag to simulate SMOS server connection interruption
    /**
     * Fetches a list of academic classes for a specified academic year.
     * This method simulates network latency and potential connection interruptions.
     *
     * @param academicYear The academic year for which to retrieve classes.
     * @return A list of AcademicClass objects if the connection is active, otherwise throws a RuntimeException.
     * @throws RuntimeException if the connection to the SMOS server is interrupted.
     */
    public List<AcademicClass> fetchClassesByYear(String academicYear) {
        // Simulate network latency
        try {
            Thread.sleep(500); // 0.5 second delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Check for simulated connection interruption
        if (connectionInterrupted) {
            throw new RuntimeException("SMOS server connection interrupted. Could not retrieve class data.");
        }
        // Filter classes by the given academic year
        return ALL_CLASSES.stream()
                .filter(c -> c.getAcademicYear().equals(academicYear))
                .collect(Collectors.toList());
    }
    /**
     * Sets the status of the connection interruption flag.
     * This allows simulating a connection loss to the "SMOS server".
     *
     * @param interrupted true to simulate connection interruption, false to restore connection.
     */
    public void setConnectionInterrupted(boolean interrupted) {
        this.connectionInterrupted = interrupted;
    }
    /**
     * Checks if the connection to the "SMOS server" is currently interrupted.
     *
     * @return true if the connection is interrupted, false otherwise.
     */
    public boolean isConnectionInterrupted() {
        return connectionInterrupted;
    }
    /**
     * Returns a list of all unique academic years available in the data service.
     *
     * @return A list of strings representing the unique academic years.
     */
    public List<String> getAvailableAcademicYears() {
        return ALL_CLASSES.stream()
                .map(AcademicClass::getAcademicYear)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}