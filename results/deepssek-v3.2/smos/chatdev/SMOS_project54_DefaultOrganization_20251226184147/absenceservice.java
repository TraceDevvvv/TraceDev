/**
 * Service layer for handling absence-related business logic.
 * Coordinates between GUI, database, and email serv.
 */
import java.util.ArrayList;
import java.util.List;
public class AbsenceService {
    private DatabaseService databaseService;
    private EmailService emailService;
    /**
     * Constructor for AbsenceService.
     * @param databaseService service for database operations
     * @param emailService service for sending email notifications
     */
    public AbsenceService(DatabaseService databaseService, EmailService emailService) {
        this.databaseService = databaseService;
        this.emailService = emailService;
    }
    /**
     * Retrieves all students for a given class.
     * In a real application, this would query a database.
     * @param className name of the class to get students for
     * @return list of students in the specified class
     */
    public List<Student> getStudentsByClass(String className) {
        // In a real application, this would query a database
        // For demo purposes, we'll create some sample data
        List<Student> students = new ArrayList<>();
        // Sample data for Class A
        if ("Class A".equals(className)) {
            students.add(new Student("S001", "John", "Doe", "Class A", "john.doe.parent@example.com"));
            students.add(new Student("S002", "Jane", "Smith", "Class A", "jane.smith.parent@example.com"));
            students.add(new Student("S003", "Bob", "Johnson", "Class A", "bob.johnson.parent@example.com"));
            students.add(new Student("S004", "Alice", "Williams", "Class A", "alice.williams.parent@example.com"));
            students.add(new Student("S005", "Charlie", "Brown", "Class A", "charlie.brown.parent@example.com"));
        } 
        // Sample data for Class B
        else if ("Class B".equals(className)) {
            students.add(new Student("S006", "David", "Miller", "Class B", "david.miller.parent@example.com"));
            students.add(new Student("S007", "Emma", "Davis", "Class B", "emma.davis.parent@example.com"));
            students.add(new Student("S008", "Frank", "Wilson", "Class B", "frank.wilson.parent@example.com"));
            students.add(new Student("S009", "Grace", "Moore", "Class B", "grace.moore.parent@example.com"));
            students.add(new Student("S010", "Henry", "Taylor", "Class B", "henry.taylor.parent@example.com"));
        }
        // Default sample data
        else {
            students.add(new Student("S011", "Test", "Student", className, "test.parent@example.com"));
        }
        return students;
    }
    /**
     * Saves absence data for students and sends email notifications.
     * @param students list of students with their absence status
     * @param className name of the class
     * @throws Exception if there's an error during saving or email sending
     */
    public void saveAbsences(List<Student> students, String className) throws Exception {
        List<Student> absentStudents = new ArrayList<>();
        // Separate absent students for email notification
        for (Student student : students) {
            if (!student.isPresent()) {
                absentStudents.add(student);
            }
        }
        // Save to database
        databaseService.saveAbsences(students, className);
        // Send email notifications for absent students
        for (Student student : absentStudents) {
            emailService.sendAbsenceNotification(student);
        }
        // Log the operation
        System.out.println("Saved absences for " + className + 
                           ". Absent students: " + absentStudents.size());
    }
}