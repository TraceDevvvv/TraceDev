/**
 * studentdataservice.java
 * Service class that simulates fetching student data from the SMOS server.
 * Implements the data retrieval functionality as described in the use case:
 * 1. Searches the archive for class register data
 * 2. Simulates connection to SMOS server with interruption
 */
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
public class StudentDataService {
    private boolean isConnected = false;
    private int currentStudentId = -1;
    /**
     * Simulates searching the archive for class register data.
     * Fetches data for the specified student ID.
     * 
     * @param studentId The ID of the student to fetch data for
     * @return List of student data records
     * @throws DataRetrievalException if data cannot be retrieved from server
     */
    public List<Student> getStudentData(int studentId) throws DataRetrievalException {
        List<Student> studentRecords = new ArrayList<>();
        Random random = new Random();
        Calendar calendar = Calendar.getInstance();
        if (!connectToSMOS()) {
            throw new DataRetrievalException("Failed to connect to SMOS server");
        }
        this.currentStudentId = studentId;
        try {
            for (int i = 30; i >= 0; i--) {
                calendar.setTime(new Date());
                calendar.add(Calendar.DAY_OF_MONTH, -i);
                Date recordDate = calendar.getTime();
                int absences = random.nextInt(3);
                int delays = random.nextInt(5);
                String disciplinaryNotes = "";
                if (random.nextDouble() < 0.2) {
                    String[] notes = {"Late submission", "Classroom disturbance", 
                                      "Incomplete homework", "Unprepared for class"};
                    disciplinaryNotes = notes[random.nextInt(notes.length)];
                }
                String justification = "";
                if (absences > 0 || delays > 0) {
                    if (random.nextDouble() < 0.7) {
                        String[] justifications = {"Medical appointment", "Family emergency", 
                                                 "Transport issues", "Weather conditions"};
                        justification = justifications[random.nextInt(justifications.length)];
                    }
                }
                Student student = new Student(
                    studentId,
                    "John Smith",
                    "Grade 10-A",
                    recordDate,
                    absences,
                    disciplinaryNotes,
                    delays,
                    justification
                );
                studentRecords.add(student);
                if (random.nextDouble() < 0.05) {
                    throw new DataRetrievalException("SMOS server connection lost during data retrieval");
                }
            }
            return studentRecords;
        } finally {
            /* Postcondition: Connecting to the SMOS server interrupted */
            interruptSMOSConnection();
        }
    }
    /**
     * Simulates connecting to the SMOS server.
     * 
     * @return true if connection successful, false otherwise
     */
    private boolean connectToSMOS() {
        System.out.println("Connecting to SMOS server...");
        try {
            Thread.sleep(500);
            isConnected = true;
            System.out.println("Connected to SMOS server successfully.");
            return true;
        } catch (InterruptedException e) {
            System.out.println("Connection to SMOS server interrupted.");
            isConnected = false;
            return false;
        }
    }
    /**
     * Simulates interrupting the SMOS server connection.
     * This matches the postcondition in the use case.
     */
    private void interruptSMOSConnection() {
        if (isConnected) {
            System.out.println("Retrieved data for student ID: " + currentStudentId);
            System.out.println("Interrupting SMOS server connection...");
            isConnected = false;
            currentStudentId = -1;
            System.out.println("SMOS server connection interrupted.");
        }
    }
    public boolean isStudentLoggedIn() {
        return true;
    }
    public boolean clickDigitalLogButton() {
        System.out.println("Digital Log button clicked by student.");
        return true;
    }
    public boolean isConnected() {
        return isConnected;
    }
}
/**
 * Custom exception for data retrieval failures
 */
class DataRetrievalException extends Exception {
    public DataRetrievalException(String message) {
        super(message);
    }
    public DataRetrievalException(String message, Throwable cause) {
        super(message, cause);
    }
}