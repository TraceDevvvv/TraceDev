'''
This class simulates the service layer for retrieving academic registers.
It acts as a mock "archive" and "SMOS server" by holding sample data
and providing a method to search registers by academic year.
It also includes logic to simulate connection interruptions.
'''
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Random;
import java.util.Collections;
public class RegisterService {
    private List<AcademicRegister> mockDatabase; // Simulates the archive/database
    private Random random; // For simulating connection disruptions
    /**
     * Constructs the RegisterService and initializes the mock database with sample data.
     */
    public RegisterService() {
        mockDatabase = new ArrayList<>();
        random = new Random();
        populateMockData();
    }
    /**
     * Populates the mock database with various academic registers for different years and classes.
     */
    private void populateMockData() {
        // Academic Year 2022
        mockDatabase.add(new AcademicRegister("REG001", 2022, "10A", "Mathematics", "Mr. Smith"));
        mockDatabase.add(new AcademicRegister("REG002", 2022, "10A", "Science", "Ms. Jones"));
        mockDatabase.add(new AcademicRegister("REG003", 2022, "11B", "History", "Mr. Davis"));
        mockDatabase.add(new AcademicRegister("REG004", 2022, "12C", "Literature", "Ms. White"));
        mockDatabase.add(new AcademicRegister("REG005", 2022, "10A", "Art", "Mr. Brown"));
        // Academic Year 2023
        mockDatabase.add(new AcademicRegister("REG006", 2023, "10A", "Mathematics", "Mr. Smith"));
        mockDatabase.add(new AcademicRegister("REG007", 2023, "11A", "Physics", "Dr. Green"));
        mockDatabase.add(new AcademicRegister("REG008", 2023, "12D", "Chemistry", "Ms. Black"));
        mockDatabase.add(new AcademicRegister("REG009", 2023, "10B", "Geography", "Mr. Clark"));
        mockDatabase.add(new AcademicRegister("REG010", 2023, "11A", "Biology", "Dr. Green"));
        // Academic Year 2024 (less data for variety)
        mockDatabase.add(new AcademicRegister("REG011", 2024, "10A", "Mathematics", "Mr. Smith"));
        mockDatabase.add(new AcademicRegister("REG012", 2024, "11C", "Computer Science", "Mr. Evans"));
        mockDatabase.add(new AcademicRegister("REG013", 2024, "12A", "Economics", "Ms. Hall"));
    }
    /**
     * Searches the archive for digital registers belonging to a specific academic year.
     * This method simulates a data retrieval operation and can randomly throw a
     * SMOSConnectionException to simulate server interruption.
     *
     * @param year The academic year to search for.
     * @return A list of AcademicRegister objects found for the specified year.
     * @throws SMOSConnectionException If a simulated connection disruption occurs.
     */
    public List<AcademicRegister> searchRegistersByAcademicYear(int year) throws SMOSConnectionException {
        // Simulate network delay and potential connection issues
        try {
            Thread.sleep(500 + random.nextInt(1000)); // Simulate 0.5 to 1.5 seconds delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupt status
            throw new SMOSConnectionException("Search operation interrupted due to thread interruption.", e);
        }
        // Simulate a 15% chance of connection disruption
        if (simulateConnectionDisruption()) {
            throw new SMOSConnectionException("Connection to the SMOS server interrupted.");
        }
        // Filter registers by the specified academic year
        return mockDatabase.stream()
                .filter(register -> register.getAcademicYear() == year)
                .collect(Collectors.toList());
    }
    /**
     * Randomly decides whether to simulate a connection disruption.
     *
     * @return true if a disruption should be simulated, false otherwise.
     */
    private boolean simulateConnectionDisruption() {
        return random.nextInt(100) < 15; // 15% chance of disruption
    }
    /**
     * Retrieves a list of all unique academic years present in the mock database.
     * This is useful for populating a dropdown menu for year selection.
     *
     * @return A sorted list of unique academic years.
     */
    public List<Integer> getAvailableAcademicYears() {
        return mockDatabase.stream()
                .map(AcademicRegister::getAcademicYear)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}