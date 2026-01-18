/*
studentservice.java
Manages student data within the application. This class simulates a backend
data store, providing methods to retrieve and update student information.
*/
package service;
import model.ReportCard;
import model.Student;
import model.SubjectGrade;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service class for managing student data.
 * This class simulates data storage and operations.
 */
public class StudentService {
    private List<Student> students; // In-memory list to store student objects
    /**
     * Initializes the StudentService with some dummy student data.
     */
    public StudentService() {
        this.students = new ArrayList<>();
        // Populate with dummy data
        initializeDummyData();
    }
    /**
     * Populates the student list with some predefined students and their report cards.
     */
    private void initializeDummyData() {
        // Student 1
        ReportCard rc1 = new ReportCard(Arrays.asList(
                new SubjectGrade("Math", 85.0),
                new SubjectGrade("Physics", 78.5),
                new SubjectGrade("Chemistry", 92.0)
        ));
        students.add(new Student("S001", "Alice Smith", rc1));
        // Student 2
        ReportCard rc2 = new ReportCard(Arrays.asList(
                new SubjectGrade("Math", 70.0),
                new SubjectGrade("Physics", 65.0),
                new SubjectGrade("Literature", 88.0)
        ));
        students.add(new Student("S002", "Bob Johnson", rc2));
        // Student 3 (with fewer grades)
        ReportCard rc3 = new ReportCard(Arrays.asList(
                new SubjectGrade("History", 95.0),
                new SubjectGrade("Geography", 80.0)
        ));
        students.add(new Student("S003", "Charlie Brown", rc3));
    }
    /**
     * Retrieves all registered students.
     * @return A list of all student objects.
     */
    public List<Student> getAllStudents() {
        // Return a new ArrayList to prevent external modifications to the internal list directly
        return new ArrayList<>(students);
    }
    /**
     * Finds a student by their unique student ID.
     * @param studentId The ID of the student to find.
     * @return An Optional containing the Student object if found, or an empty Optional if not found.
     */
    public Optional<Student> getStudentById(String studentId) {
        return students.stream()
                .filter(s -> s.getStudentId().equals(studentId))
                .findFirst();
    }
    /**
     * Updates the report card for a specific student by updating individual grades.
     * This method iterates through the provided grades and updates or adds them
     * to the student's existing report card, thus preserving any grades not
     * explicitly passed in the `gradesToUpdate` list.
     *
     * @param studentId The ID of the student whose report card is to be updated.
     * @param gradesToUpdate A list of SubjectGrade objects with the new or updated grades.
     * @return true if the student was found and their report card was updated, false otherwise.
     */
    public boolean updateStudentReportCard(String studentId, List<SubjectGrade> gradesToUpdate) {
        Optional<Student> studentOpt = getStudentById(studentId);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            ReportCard currentReportCard = student.getReportCard(); // Get the student's existing report card
            // For each grade provided in the update list, add or update it in the current report card.
            // This preserves existing grades not in 'gradesToUpdate' and handles updates efficiently.
            // We need to re-set the grades list to ensure subjects removed from the UI are removed from the model.
            // A simpler approach for the UI where it only adds/updates means we iterate.
            // If the UI implicitly allows removing (e.g. by setting grade to empty), this service method needs to know that.
            // Given the current GUI design, it only adds/updates shown grades.
            // To ensure only the grades present in 'gradesToUpdate' are in the final report card (and implicitly remove others if they were not in gradesToUpdate):
            currentReportCard.setGrades(new ArrayList<>()); // Clear existing grades
            for (SubjectGrade updatedGrade : gradesToUpdate) {
                currentReportCard.addOrUpdateGrade(updatedGrade); // Add/update only those from the UI
            }
            return true;
        }
        return false;
    }
    /**
     * Adds a new student to the system.
     * @param student The Student object to add.
     * @return true if the student was added, false if a student with the same ID already exists.
     */
    public boolean addStudent(Student student) {
        boolean studentExists = students.stream().anyMatch(s -> s.getStudentId().equals(student.getStudentId()));
        if (!studentExists) {
            students.add(student);
            return true;
        }
        return false;
    }
}