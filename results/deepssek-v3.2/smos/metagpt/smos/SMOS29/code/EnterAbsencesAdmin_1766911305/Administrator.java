import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Administrator class representing an admin user who logs in and manages absence records.
 * This class handles administrator authentication, data entry for absences, and interaction
 * with the email service for notifications.
 */
public class Administrator {
    private static final Logger LOGGER = Logger.getLogger(Administrator.class.getName());
    
    private String username;
    private String password;
    private boolean isLoggedIn;
    private EmailService emailService;
    
    // Store absence records by date for quick lookup
    private Map<LocalDate, List<AbsenceRecord>> absenceRecordsByDate;
    
    /**
     * Constructor for creating an Administrator with credentials.
     * 
     * @param username Administrator username
     * @param password Administrator password
     */
    public Administrator(String username, String password) {
        this.username = username;
        this.password = password;
        this.isLoggedIn = false;
        this.emailService = new EmailService();
        this.absenceRecordsByDate = new HashMap<>();
        
        LOGGER.info("Administrator object created for user: " + username);
    }
    
    /**
     * Attempts to log in the administrator with provided credentials.
     * 
     * @param inputUsername The username to check
     * @param inputPassword The password to check
     * @return true if login successful, false otherwise
     */
    public boolean login(String inputUsername, String inputPassword) {
        if (inputUsername == null || inputPassword == null) {
            LOGGER.warning("Login failed: username or password is null");
            return false;
        }
        
        // In a real system, this would validate against a database or authentication service
        boolean credentialsValid = this.username.equals(inputUsername) && 
                                   this.password.equals(inputPassword);
        
        if (credentialsValid) {
            isLoggedIn = true;
            LOGGER.info("Administrator " + username + " logged in successfully");
            
            // Attempt to connect to SMOS server upon successful login
            boolean serverConnected = emailService.connectToSmoSServer();
            if (!serverConnected) {
                LOGGER.warning("Login successful but could not connect to SMOS server");
                // Continue anyway - emails will be queued or retried
            }
            
            return true;
        } else {
            LOGGER.warning("Login failed for username: " + inputUsername);
            return false;
        }
    }
    
    /**
     * Logs out the administrator and disconnects from the SMOS server.
     */
    public void logout() {
        if (isLoggedIn) {
            LOGGER.info("Logging out administrator: " + username);
            emailService.disconnectFromSmoSServer();
            isLoggedIn = false;
            LOGGER.info("Administrator " + username + " logged out successfully");
        } else {
            LOGGER.warning("Cannot logout: administrator is not logged in");
        }
    }
    
    /**
     * Checks if the administrator is currently logged in.
     * 
     * @return true if logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    /**
     * Updates the screen display according to the selected date.
     * This simulates retrieving and displaying absence records for a specific date.
     * 
     * @param date The date to display records for
     * @return List of absence records for the specified date, or empty list if none
     * @throws IllegalStateException if administrator is not logged in
     */
    public List<AbsenceRecord> updateScreenForDate(LocalDate date) {
        if (!isLoggedIn) {
            throw new IllegalStateException("Administrator must be logged in to update screen");
        }
        
        if (date == null) {
            LOGGER.warning("Cannot update screen: date is null");
            return new ArrayList<>();
        }
        
        LOGGER.info("Updating screen for date: " + date);
        
        // Retrieve existing records for this date
        List<AbsenceRecord> records = absenceRecordsByDate.getOrDefault(date, new ArrayList<>());
        
        LOGGER.info("Found " + records.size() + " absence records for " + date);
        
        // In a real system, this would update the UI screen
        // For simulation, we just return the records
        return new ArrayList<>(records); // Return a copy to prevent modification
    }
    
    /**
     * Enters absence data for multiple students on a specific date.
     * This simulates filling out the form and clicking "Save".
     * 
     * @param date The date of the absence records
     * @param studentRecords List of students with their attendance status
     * @param notes Optional notes for the absence records (applied to all)
     * @return List of created absence records
     * @throws IllegalStateException if administrator is not logged in
     * @throws IllegalArgumentException if date or studentRecords is null
     */
    public List<AbsenceRecord> enterAbsenceData(LocalDate date, 
                                                List<StudentAttendance> studentRecords, 
                                                String notes) {
        if (!isLoggedIn) {
            throw new IllegalStateException("Administrator must be logged in to enter absence data");
        }
        
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        
        if (studentRecords == null || studentRecords.isEmpty()) {
            LOGGER.warning("No student records provided for date: " + date);
            return new ArrayList<>();
        }
        
        LOGGER.info("Entering absence data for " + studentRecords.size() + 
                   " students on date: " + date);
        
        List<AbsenceRecord> newRecords = new ArrayList<>();
        String processedNotes = (notes == null) ? "" : notes.trim();
        
        for (StudentAttendance studentAttendance : studentRecords) {
            if (studentAttendance == null || studentAttendance.getStudent() == null) {
                LOGGER.warning("Skipping invalid student attendance record");
                continue;
            }
            
            Student student = studentAttendance.getStudent();
            AbsenceRecord.AttendanceStatus status = studentAttendance.getStatus();
            
            // Create absence record
            AbsenceRecord record = new AbsenceRecord(date, student, status, processedNotes);
            newRecords.add(record);
            
            LOGGER.fine("Created record: " + record.toSummaryString());
        }
        
        // Store the records
        storeAbsenceRecords(date, newRecords);
        
        LOGGER.info("Successfully entered " + newRecords.size() + 
                   " absence records for date: " + date);
        
        return newRecords;
    }
    
    /**
     * Sends absence data to server and triggers email notifications for absent students.
     * This simulates the server processing and email sending.
     * 
     * @param date The date of the absence records to send
     * @return List of students for whom notifications were successfully sent
     * @throws IllegalStateException if administrator is not logged in
     */
    public List<Student> sendDataToServer(LocalDate date) {
        if (!isLoggedIn) {
            throw new IllegalStateException("Administrator must be logged in to send data to server");
        }
        
        if (date == null) {
            LOGGER.warning("Cannot send data to server: date is null");
            return new ArrayList<>();
        }
        
        LOGGER.info("Sending absence data to server for date: " + date);
        
        // Get absence records for the specified date
        List<AbsenceRecord> records = absenceRecordsByDate.get(date);
        if (records == null || records.isEmpty()) {
            LOGGER.info("No absence records found for date: " + date);
            return new ArrayList<>();
        }
        
        // Filter for absent students only
        List<AbsenceRecord> absentRecords = new ArrayList<>();
        for (AbsenceRecord record : records) {
            if (record.isAbsent()) {
                absentRecords.add(record);
            }
        }
        
        if (absentRecords.isEmpty()) {
            LOGGER.info("No absent students found for date: " + date);
            return new ArrayList<>();
        }
        
        LOGGER.info("Sending notifications for " + absentRecords.size() + " absent students");
        
        // Use EmailService to send notifications
        List<Student> notifiedStudents = emailService.sendBulkAbsenceNotifications(absentRecords);
        
        LOGGER.info("Email notifications sent to " + notifiedStudents.size() + 
                   " parents for date: " + date);
        
        return notifiedStudents;
    }
    
    /**
     * Displays updated log data after processing.
     * This simulates refreshing the display with updated information.
     * 
     * @param date The date to display log data for
     * @return Formatted log data as a string
     * @throws IllegalStateException if administrator is not logged in
     */
    public String displayUpdatedLogData(LocalDate date) {
        if (!isLoggedIn) {
            throw new IllegalStateException("Administrator must be logged in to display log data");
        }
        
        if (date == null) {
            return "Error: Date is null";
        }
        
        LOGGER.info("Displaying updated log data for date: " + date);
        
        List<AbsenceRecord> records = absenceRecordsByDate.get(date);
        if (records == null || records.isEmpty()) {
            return "No absence records found for " + date;
        }
        
        StringBuilder logData = new StringBuilder();
        logData.append("=== ABSENCE LOG FOR ").append(date).append(" ===\n");
        logData.append("Total records: ").append(records.size()).append("\n\n");
        
        int absentCount = 0;
        int presentCount = 0;
        int lateCount = 0;
        
        for (AbsenceRecord record : records) {
            if (record.isAbsent()) {
                absentCount++;
            } else if (record.isPresent()) {
                presentCount++;
            } else if (record.isLate()) {
                lateCount++;
            }
            
            logData.append(record.toSummaryString()).append("\n");
        }
        
        logData.append("\n=== SUMMARY ===\n");
        logData.append("Present: ").append(presentCount).append("\n");
        logData.append("Absent: ").append(absentCount).append("\n");
        logData.append("Late: ").append(lateCount).append("\n");
        
        LOGGER.info("Log data displayed for " + records.size() + " records");
        
        return logData.toString();
    }
    
    /**
     * Interrupts the current operation and handles connection to SMOS server.
     * This simulates the administrator interrupting the operation.
     */
    public void interruptOperation() {
        LOGGER.warning("Administrator " + username + " interrupted the operation");
        
        // Handle interrupted SMOS server connection
        if (isLoggedIn) {
            LOGGER.info("Checking SMOS server connection status...");
            boolean reconnected = emailService.connectToSmoSServer();
            if (reconnected) {
                LOGGER.info("Successfully reconnected to SMOS server after interruption");
            } else {
                LOGGER.warning("Could not reconnect to SMOS server. Emails may be queued.");
            }
        }
        
        // In a real system, this would handle rollback or save state
        LOGGER.info("Operation interrupted. System remains on registry screen.");
    }
    
    /**
     * Gets the username of the administrator.
     * 
     * @return The administrator username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Gets the email service instance used by this administrator.
     * 
     * @return The EmailService instance
     */
    public EmailService getEmailService() {
        return emailService;
    }
    
    /**
     * Gets all absence records for a specific date.
     * 
     * @param date The date to retrieve records for
     * @return List of absence records for the date, or empty list if none
     */
    public List<AbsenceRecord> getAbsenceRecordsForDate(LocalDate date) {
        if (date == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(absenceRecordsByDate.getOrDefault(date, new ArrayList<>()));
    }
    
    /**
     * Gets all absence records across all dates.
     * 
     * @return List of all absence records
     */
    public List<AbsenceRecord> getAllAbsenceRecords() {
        List<AbsenceRecord> allRecords = new ArrayList<>();
        for (List<AbsenceRecord> records : absenceRecordsByDate.values()) {
            allRecords.addAll(records);
        }
        return allRecords;
    }
    
    /**
     * Clears all absence records (for testing/reset purposes).
     */
    public void clearAllRecords() {
        LOGGER.info("Clearing all absence records");
        absenceRecordsByDate.clear();
    }
    
    /**
     * Stores absence records for a specific date.
     * 
     * @param date The date of the records
     * @param records The records to store
     */
    private void storeAbsenceRecords(LocalDate date, List<AbsenceRecord> records) {
        if (date == null || records == null || records.isEmpty()) {
            return;
        }
        
        // Get existing records for this date or create new list
        List<AbsenceRecord> existingRecords = absenceRecordsByDate.getOrDefault(date, new ArrayList<>());
        
        // Remove any existing records for the same students (update in place)
        for (AbsenceRecord newRecord : records) {
            // Remove existing record for this student if present
            existingRecords.removeIf(record -> 
                record.getStudent().getId().equals(newRecord.getStudent().getId()));
            
            // Add the new record
            existingRecords.add(newRecord);
        }
        
        // Store back in map
        absenceRecordsByDate.put(date, existingRecords);
        
        LOGGER.fine("Stored " + records.size() + " records for date: " + date);
    }
    
    /**
     * Inner class representing a student's attendance status for data entry.
     * This is used when entering absence data through the form.
     */
    public static class StudentAttendance {
        private Student student;
        private AbsenceRecord.AttendanceStatus status;
        
        /**
         * Constructor for StudentAttendance.
         * 
         * @param student The student
         * @param status The attendance status
         */
        public StudentAttendance(Student student, AbsenceRecord.AttendanceStatus status) {
            if (student == null) {
                throw new IllegalArgumentException("Student cannot be null");
            }
            if (status == null) {
                throw new IllegalArgumentException("Attendance status cannot be null");
            }
            
            this.student = student;
            this.status = status;
        }
        
        /**
         * Gets the student.
         * 
         * @return The student
         */
        public Student getStudent() {
            return student;
        }
        
        /**
         * Gets the attendance status.
         * 
         * @return The attendance status
         */
        public AbsenceRecord.AttendanceStatus getStatus() {
            return status;
        }
        
        /**
         * Sets the attendance status.
         * 
         * @param status The new attendance status
         */
        public void setStatus(AbsenceRecord.AttendanceStatus status) {
            if (status == null) {
                throw new IllegalArgumentException("Attendance status cannot be null");
            }
            this.status = status;
        }
        
        @Override
        public String toString() {
            return "StudentAttendance[Student: " + student.getName() + 
                   ", Status: " + status.getDisplayName() + "]";
        }
    }
}