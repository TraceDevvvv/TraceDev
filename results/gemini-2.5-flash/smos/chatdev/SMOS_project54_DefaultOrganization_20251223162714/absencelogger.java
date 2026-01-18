/**
 * Utility class for simulating the logging or persistence of absence data within the system.
 * This class represents the "Absence data has been entered in the system" postcondition.
 * In a real application, this would interact with a database or file system for persistent storage.
 */
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
public class AbsenceLogger {
    /**
     * Simulates logging absence data into the system.
     * This acts as the final step of data entry, beyond just server communication.
     *
     * @param className The name of the class for which data is being logged.
     * @param students  A list of {@link Student} objects with their final presence status for the session.
     */
    public static void logAbsenceData(String className, List<Student> students) {
        System.out.println("--- LOGGING ABSENCE DATA TO LOCAL SYSTEM ---");
        System.out.println("Timestamp: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("Class: " + className);
        System.out.println("Absence entries:");
        boolean anyAbsences = false;
        for (Student student : students) {
            if (!student.isPresent()) {
                System.out.printf("  - Student ID: %s, Name: %s (ABSENT)\n", student.getId(), student.getName());
                anyAbsences = true;
            }
        }
        if (!anyAbsences) {
            System.out.println("  No absences recorded for this class today. All students present.");
        }
        System.out.println("--- ABSENCE DATA LOGGED ---\n");
        // In a real system, this would involve database inserts/updates
    }
}