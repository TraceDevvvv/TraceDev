/**
 * A service class responsible for simulating the retrieval and persistence
 * of class register data. In a real application, this would interact with
 * a database or a backend API (like SMOS).
 */
package com.chatdev.register.service;
import com.chatdev.register.model.AttendanceEntry;
import com.chatdev.register.model.AttendanceStatus;
import com.chatdev.register.model.ClassRegisterDetails;
import com.chatdev.register.model.Student;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
public class RegisterService {
    // Represents the mock SMOS connection status for the postcondition
    private static boolean smosConnectionInterrupted = false;
    // Internal mutable store for mock data, simulating a database.
    // Key: registerId, Value: Map (Key: date, Value: ClassRegisterDetails containing mutable AttendanceEntry objects)
    private final Map<String, Map<LocalDate, ClassRegisterDetails>> mockDatabase = new ConcurrentHashMap<>();
    private final Map<String, Student> allStudents; // Store all mock students once
    /**
     * Constructs a new RegisterService and initializes mock data.
     */
    public RegisterService() {
        this.allStudents = createMockStudents();
        // Populate initial mock data when the service is created
        populateInitialMockData();
    }
    /**
     * Populates the mock database with some initial data for demonstration.
     */
    private void populateInitialMockData() {
        String registerId = "CLASS_5B_MATH_2023";
        LocalDate today = LocalDate.now();
        LocalDate yesterday = LocalDate.now().minusDays(1);
        // Data for today
        Map<String, AttendanceEntry> attendanceToday = new HashMap<>();
        attendanceToday.put("S001", new AttendanceEntry(allStudents.get("S001"), AttendanceStatus.PRESENT, false, "", ""));
        attendanceToday.put("S002", new AttendanceEntry(allStudents.get("S002"), AttendanceStatus.ABSENT, false, "Flu symptoms", ""));
        attendanceToday.put("S003", new AttendanceEntry(allStudents.get("S003"), AttendanceStatus.LATE, true, "Traffic delay", "First time late, warning issued."));
        attendanceToday.put("S004", new AttendanceEntry(allStudents.get("S004"), AttendanceStatus.PRESENT, false, "", ""));
        attendanceToday.put("S005", new AttendanceEntry(allStudents.get("S005"), AttendanceStatus.ABSENT, false, "", "Unjustified absence."));
        attendanceToday.put("S006", new AttendanceEntry(allStudents.get("S006"), AttendanceStatus.PRESENT, false, "", ""));
        mockDatabase.computeIfAbsent(registerId, k -> new ConcurrentHashMap<>())
                    .put(today, new ClassRegisterDetails(registerId, today, attendanceToday));
        // Data for yesterday
        Map<String, AttendanceEntry> attendanceYesterday = new HashMap<>();
        attendanceYesterday.put("S001", new AttendanceEntry(allStudents.get("S001"), AttendanceStatus.PRESENT, false, "", ""));
        attendanceYesterday.put("S002", new AttendanceEntry(allStudents.get("S002"), AttendanceStatus.PRESENT, false, "", ""));
        attendanceYesterday.put("S003", new AttendanceEntry(allStudents.get("S003"), AttendanceStatus.PRESENT, false, "", ""));
        attendanceYesterday.put("S004", new AttendanceEntry(allStudents.get("S004"), AttendanceStatus.ABSENT, false, "Family emergency", ""));
        attendanceYesterday.put("S005", new AttendanceEntry(allStudents.get("S005"), AttendanceStatus.PRESENT, false, "", ""));
        attendanceYesterday.put("S006", new AttendanceEntry(allStudents.get("S006"), AttendanceStatus.LATE, true, "Overslept", "Second lateness, formal warning."));
        mockDatabase.get(registerId).put(yesterday, new ClassRegisterDetails(registerId, yesterday, attendanceYesterday));
    }
    /**
     * Creates a map of mock students.
     * In a real application, these would be fetched from a student management system.
     */
    private Map<String, Student> createMockStudents() {
        Map<String, Student> students = new HashMap<>();
        students.put("S001", new Student("S001", "Alice Smith"));
        students.put("S002", new Student("S002", "Bob Johnson"));
        students.put("S003", new Student("S003", "Charlie Brown"));
        students.put("S004", new Student("S004", "Diana Prince"));
        students.put("S005", new Student("S005", "Eve Adams"));
        students.put("S006", new Student("S006", "Frank White"));
        return students;
    }
    /**
     * Sets the mock SMOS connection status.
     * This method is used to simulate the "Connection to the interrupted SMOS server" postcondition.
     * @param interrupted True if the connection should be considered interrupted, false otherwise.
     */
    public void setSmosConnectionInterrupted(boolean interrupted) {
        smosConnectionInterrupted = interrupted;
        System.out.println("Mock SMOS Connection status set to: " + (interrupted ? "Interrupted" : "Active"));
    }
    /**
     * Checks the mock SMOS connection status.
     * @return True if the connection is considered interrupted, false otherwise.
     */
    public boolean isSmosConnectionInterrupted() {
        return smosConnectionInterrupted;
    }
    /**
     * Simulates fetching class register details for a given register ID and date.
     * This method provides mock data for demonstration purposes from an internal store.
     *
     * @param registerId The ID of the class register to retrieve.
     * @param date       The specific date for which to retrieve attendance.
     * @return A ClassRegisterDetails object containing mocked data, or null if register ID is invalid.
     */
    public ClassRegisterDetails getRegisterDetails(String registerId, LocalDate date) {
        // Simulate a delay for a more realistic feel (optional)
        try {
            Thread.sleep(500); // Simulate network/DB latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (registerId == null || registerId.trim().isEmpty()) {
            return null;
        }
        Map<LocalDate, ClassRegisterDetails> registerDates = mockDatabase.get(registerId);
        if (registerDates != null) {
            ClassRegisterDetails details = registerDates.get(date);
            if (details != null) {
                // Return a new ClassRegisterDetails object with a copy of the attendance entries
                // to maintain the immutable map property in ClassRegisterDetails external view
                return new ClassRegisterDetails(details.getRegisterId(), details.getDate(), details.getAttendanceEntries());
            }
        }
        // If no data found for the given registerId/date, return an empty one
        return new ClassRegisterDetails(registerId, date, new HashMap<>());
    }
    /**
     * Simulates updating an attendance entry in the "database".
     *
     * @param registerId The ID of the register.
     * @param date The date of the register entry.
     * @param studentId The ID of the student.
     * @param updatedStatus The new attendance status.
     * @param updatedIsLate The new lateness flag.
     * @param updatedJustification The new justification.
     * @param updatedDisciplinaryNote The new disciplinary note.
     * @return True if update was successful, false otherwise.
     */
    public boolean updateAttendanceEntry(String registerId, LocalDate date, String studentId,
                                         AttendanceStatus updatedStatus, boolean updatedIsLate,
                                         String updatedJustification, String updatedDisciplinaryNote) {
        try {
            Thread.sleep(200); // Simulate network/DB write latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
        Map<LocalDate, ClassRegisterDetails> registerDates = mockDatabase.get(registerId);
        if (registerDates != null) {
            ClassRegisterDetails details = registerDates.get(date);
            if (details != null && details.getAttendanceEntries().containsKey(studentId)) {
                // To update, we must work with a mutable version of the entries.
                // Since ClassRegisterDetails makes its map unmodifiable, we get its content,
                // modify the specific AttendanceEntry, and then reconstruct a new ClassRegisterDetails
                // to update in our mock database.
                // We need to retrieve the actual mutable AttendanceEntry object from original creation to update it directly.
                // The ClassRegisterDetails returned by getRegisterDetails has an unmodifiable map.
                // We need to access the mutable internal entry here.
                // This means the initial entries map needs to be mutable in our mock store, or entries need to be mutable objects.
                // Current `AttendanceEntry` has setters, so it is mutable. We need to get the actual instance stored.
                // Find the original, mutable AttendanceEntry object if we have kept a mutable reference
                // The ClassRegisterDetails in mockDatabase holds the map. To modify an *entry*, we need to fetch that specific object.
                // The attendanceEntries map within ClassRegisterDetails is unmodifiable, but the AttendanceEntry objects within it *are* mutable.
                // Get the mutable map used to construct the ClassRegisterDetails
                // This requires a minor adjustment to how ClassRegisterDetails is stored internally or fetched,
                // or we need to manage mutable entries within the service directly.
                // For direct update, let's assume the map retrieved from details.getAttendanceEntries()
                // will give us references to the *same* mutable AttendanceEntry objects,
                // even if the map wrapper itself is unmodifiable. This is true for Java collections.
                AttendanceEntry entryToUpdate = details.getAttendanceEntryForStudent(studentId);
                if (entryToUpdate != null) {
                    entryToUpdate.setStatus(updatedStatus);
                    entryToUpdate.setLate(updatedIsLate);
                    entryToUpdate.setJustification(updatedJustification);
                    entryToUpdate.setDisciplinaryNote(updatedDisciplinaryNote);
                    // No need to create a new ClassRegisterDetails object in this case if we assume
                    // the original ClassRegisterDetails object's unmodifiable map holds references
                    // to these mutable AttendanceEntry objects directly.
                    // The `getAttendanceEntries()` method of ClassRegisterDetails should ideally
                    // return the same objects for an update to persist;
                    // otherwise, we'd need to rebuild the ClassRegisterDetails with potentially
                    // updated entries.
                    // The current implementation of ClassRegisterDetails constructor copies the map,
                    // then makes it unmodifiable. So, to really persist, we need to replace the CDD object in mockDatabase
                    // with a new one that contains all (potentially modified) entries.
                    // Create a mutable copy of the underlying map to modify the entry (if not done already)
                    // Then, create a new ClassRegisterDetails and replace it.
                    Map<String, AttendanceEntry> mutableEntriesCopy = new HashMap<>(details.getAttendanceEntries());
                    mutableEntriesCopy.put(studentId, entryToUpdate); // Replace with the updated entry
                    ClassRegisterDetails updatedDetails = new ClassRegisterDetails(registerId, date, mutableEntriesCopy);
                    registerDates.put(date, updatedDetails); // Replace the old ClassRegisterDetails object
                    return true;
                }
            }
        }
        System.err.println("Failed to update attendance entry for student " + studentId + " on " + date + " in register " + registerId);
        return false;
    }
}