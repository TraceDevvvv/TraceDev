'''
A mock service to simulate fetching student data.
In a real application, this would interact with a database or a backend API.
It also simulates the "logged in" and "SMOS server" aspects.
'''
package service;
import model.ParentUser;
import model.Student;
import model.StudentRecord;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * A mock service to simulate fetching student data.
 * In a real application, this would interact with a database or a backend API.
 * It also simulates the "logged in" and "SMOS server" aspects.
 */
public class StudentDataService {
    private ParentUser loggedInParent;
    private boolean smosServerConnected;
    /**
     * Constructs the StudentDataService and initializes with mock data.
     * By default, a mock parent is "logged in" with associated children data.
     * The SMOS server is initially assumed to be connected to fetch this data.
     */
    public StudentDataService() {
        this.loggedInParent = createMockParentUser();
        this.smosServerConnected = true; // Assume initial connection is successful
    }
    /**
     * Simulates a parent logging into the system.
     * For simplification in this use case, we assume a specific parent is immediately available.
     * In a real system, this would involve authentication logic.
     *
     * @param username The username for login.
     * @param password The password for login.
     * @return True if login is successful (mocked), false otherwise.
     */
    public boolean login(String username, String password) {
        // For this mock, any non-empty username/password combination will "succeed".
        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            this.loggedInParent = createMockParentUser(); // Re-create or load specific parent for simplicity
            this.smosServerConnected = true; // Assume connection upon successful "login"
            return true;
        }
        return false;
    }
    /**
     * Gets the currently logged-in parent user.
     * @return The ParentUser object, or null if no parent is logged in.
     */
    public ParentUser getLoggedInParent() {
        return loggedInParent;
    }
    /**
     * Creates a mock ParentUser with some sample student data.
     * This simulates data that would typically come from a database or a backend system.
     * @return A mock ParentUser object with two children and their respective records.
     */
    private ParentUser createMockParentUser() {
        // Create first student and records
        Student student1 = new Student("S001", "Alice Smith");
        student1.addRecord(new StudentRecord(LocalDate.of(2023, 10, 1), false, "", 0, ""));
        student1.addRecord(new StudentRecord(LocalDate.of(2023, 10, 2), true, "", 0, "Fever"));
        student1.addRecord(new StudentRecord(LocalDate.of(2023, 10, 3), false, "Talked during class", 1, "Traffic delay"));
        student1.addRecord(new StudentRecord(LocalDate.of(2023, 10, 4), false, "", 0, ""));
        student1.addRecord(new StudentRecord(LocalDate.of(2023, 10, 5), true, "", 0, "Family trip"));
        student1.addRecord(new StudentRecord(LocalDate.of(2023, 10, 6), false, "", 2, "Overslept"));
        // Create second student and records
        Student student2 = new Student("S002", "Bob Smith");
        student2.addRecord(new StudentRecord(LocalDate.of(2023, 10, 1), false, "", 0, ""));
        student2.addRecord(new StudentRecord(LocalDate.of(2023, 10, 2), false, "", 0, ""));
        student2.addRecord(new StudentRecord(LocalDate.of(2023, 10, 3), true, "", 0, "Dentist appointment"));
        student2.addRecord(new StudentRecord(LocalDate.of(2023, 10, 4), false, "Did not submit homework", 0, ""));
        student2.addRecord(new StudentRecord(LocalDate.of(2023, 10, 5), false, "", 1, "Bus delay"));
        student2.addRecord(new StudentRecord(LocalDate.of(2023, 10, 6), false, "", 0, ""));
        List<Student> children = Arrays.asList(student1, student2);
        return new ParentUser("john.smith", children);
    }
    /**
     * Checks if the SMOS server is currently connected.
     * @return True if connected, false otherwise.
     */
    public boolean isSmosServerConnected() {
        return smosServerConnected;
    }
    /**
     * Simulates disconnecting from the SMOS server.
     * This method updates the internal status of the SMOS server connection.
     */
    public void disconnectSmosServer() {
        this.smosServerConnected = false;
        System.out.println("SMOS server connection interrupted.");
    }
}