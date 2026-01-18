import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Simulates interactions with an external SMOS server.
 * This class provides placeholder methods for connecting, disconnecting,
 * and fetching academic register data. It can also simulate connection interruptions
 * and provides dummy data for demonstration purposes.
 */
public class SMOSServer {

    // Static flag to track the simulated connection status to the SMOS server.
    private static boolean isConnected = false;
    // Random number generator to simulate connection failures or other non-deterministic behaviors.
    private static final Random random = new Random();

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private SMOSServer() {
        // This is a utility class, so it should not be instantiated.
    }

    /**
     * Simulates establishing a connection to the SMOS server.
     * There's a 50% chance of connection failure for demonstration purposes.
     *
     * @return true if the connection is successfully established, false if it fails.
     */
    public static boolean connect() {
        System.out.println("Attempting to connect to SMOS server...");
        // Simulate connection success or failure.
        if (random.nextBoolean()) { // 50% chance of connection failure
            isConnected = false;
            System.out.println("SMOS server connection failed. Please try again.");
            return false;
        } else {
            isConnected = true;
            System.out.println("Successfully connected to SMOS server.");
            return true;
        }
    }

    /**
     * Simulates disconnecting from the SMOS server.
     * This method updates the internal connection status.
     */
    public static void disconnect() {
        if (isConnected) {
            System.out.println("Disconnecting from SMOS server.");
            isConnected = false;
        } else {
            System.out.println("SMOS server was not connected, no disconnection needed.");
        }
    }

    /**
     * Checks the current simulated connection status to the SMOS server.
     *
     * @return true if currently connected, false otherwise.
     */
    public static boolean isConnected() {
        return isConnected;
    }

    /**
     * Simulates fetching a list of registers for a given academic year from the SMOS server.
     * This method returns pre-defined dummy data for demonstration.
     *
     * @param academicYear The academic year for which to retrieve registers (e.g., "2023-2024").
     *                     This parameter is currently used for logging but not for data filtering
     *                     in this dummy implementation.
     * @return A map where keys are {@link LocalDate} and values are {@link Register} objects,
     *         representing the registers for the specified academic year.
     * @throws IllegalStateException if the application is not connected to the SMOS server.
     */
    public static Map<LocalDate, Register> getRegistersForAcademicYear(String academicYear) {
        if (!isConnected) {
            throw new IllegalStateException("Not connected to SMOS server. Cannot fetch data.");
        }
        System.out.println("Fetching registers for academic year: " + academicYear + " from SMOS server...");

        Map<LocalDate, Register> academicYearRegisters = new HashMap<>();

        // --- Simulate some register data for the academic year ---

        // Register for today
        LocalDate today = LocalDate.now();
        Register registerToday = new Register(today);
        // Student 1: Present
        Student student1_today = new Student("S001", "Alice Smith");
        student1_today.setAttendanceStatus(Student.AttendanceStatus.PRESENT);
        registerToday.addStudent(student1_today);

        // Student 2: Absent with justification
        Student student2_today = new Student("S002", "Bob Johnson");
        student2_today.setAttendanceStatus(Student.AttendanceStatus.ABSENT);
        student2_today.setJustification("Sick leave due to flu.");
        registerToday.addStudent(student2_today);

        // Student 3: Delayed with justification and disciplinary note
        Student student3_today = new Student("S003", "Charlie Brown");
        student3_today.setAttendanceStatus(Student.AttendanceStatus.DELAYED);
        student3_today.setJustification("Traffic jam on the way to school.");
        student3_today.addDisciplinaryNote("Late for 15 minutes, disrupted first class.");
        registerToday.addStudent(student3_today);
        academicYearRegisters.put(today, registerToday);

        // Register for yesterday
        LocalDate yesterday = today.minusDays(1);
        Register registerYesterday = new Register(yesterday);
        // Student 1: Absent with justification
        Student student1_yesterday = new Student("S001", "Alice Smith");
        student1_yesterday.setAttendanceStatus(Student.AttendanceStatus.ABSENT);
        student1_yesterday.setJustification("Family emergency, informed by parent.");
        registerYesterday.addStudent(student1_yesterday);

        // Student 2: Present
        Student student2_yesterday = new Student("S002", "Bob Johnson");
        student2_yesterday.setAttendanceStatus(Student.AttendanceStatus.PRESENT);
        registerYesterday.addStudent(student2_yesterday);

        // Student 3: Present
        Student student3_yesterday = new Student("S003", "Charlie Brown");
        student3_yesterday.setAttendanceStatus(Student.AttendanceStatus.PRESENT);
        registerYesterday.addStudent(student3_yesterday);
        academicYearRegisters.put(yesterday, registerYesterday);

        // Register for a week ago
        LocalDate aWeekAgo = today.minusWeeks(1);
        Register registerAWeekAgo = new Register(aWeekAgo);
        // Student 1: Present
        Student student1_weekago = new Student("S001", "Alice Smith");
        student1_weekago.setAttendanceStatus(Student.AttendanceStatus.PRESENT);
        registerAWeekAgo.addStudent(student1_weekago);

        // Student 2: Delayed with justification
        Student student2_weekago = new Student("S002", "Bob Johnson");
        student2_weekago.setAttendanceStatus(Student.AttendanceStatus.DELAYED);
        student2_weekago.setJustification("Overslept, no alarm.");
        registerAWeekAgo.addStudent(student2_weekago);

        // Student 3: Absent with justification
        Student student3_weekago = new Student("S003", "Charlie Brown");
        student3_weekago.setAttendanceStatus(Student.AttendanceStatus.ABSENT);
        student3_weekago.setJustification("Dental appointment.");
        registerAWeekAgo.addStudent(student3_weekago);
        academicYearRegisters.put(aWeekAgo, registerAWeekAgo);

        System.out.println("Successfully fetched " + academicYearRegisters.size() + " registers for " + academicYear + ".");
        return academicYearRegisters;
    }
}