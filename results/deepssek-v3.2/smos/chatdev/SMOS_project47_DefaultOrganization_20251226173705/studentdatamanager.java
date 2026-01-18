/**
 * Manages student data operations (CRUD).
 * Uses in-memory list for demonstration.
 */
import java.util.ArrayList;
import java.util.List;
public class StudentDataManager {
    private List<Student> students;
    public StudentDataManager() {
        students = new ArrayList<>();
    }
    /**
     * Initializes with sample data for demonstration.
     */
    public void initializeSampleData() {
        students.add(new Student("S001", "Alice Johnson", "10", "A", "B+", "A-", "B", "A"));
        students.add(new Student("S002", "Bob Smith", "11", "B", "A", "B+", "A-", "C+"));
        students.add(new Student("S003", "Carol Davis", "9", "A-", "B", "A", "B+", "A"));
    }
    /**
     * Returns list of all students.
     * @return copy of student list
     */
    public List<Student> getAllStudents() {
        return new ArrayList<>(students); // Return copy
    }
    /**
     * Finds student by ID.
     * @param id student ID to search
     * @return student object or null if not found
     */
    public Student getStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }
    /**
     * Updates existing student information.
     * @param updatedStudent student with updated info
     * @return true if successful, false if student not found
     */
    public boolean updateStudent(Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(updatedStudent.getId())) {
                students.set(i, updatedStudent);
                return true;
            }
        }
        return false; // Student not found
    }
    /**
     * Adds new student to system.
     * @param student new student to add
     * @return true if successful, false if ID already exists
     */
    public boolean addStudent(Student student) {
        // Check for duplicate ID
        for (Student s : students) {
            if (s.getId().equals(student.getId())) {
                return false;
            }
        }
        students.add(student);
        return true;
    }
    /**
     * Deletes student from system.
     * @param id ID of student to delete
     * @return true if successful, false if student not found
     */
    public boolean deleteStudent(String id) {
        return students.removeIf(student -> student.getId().equals(id));
    }
    /**
     * Returns number of students.
     * @return student count
     */
    public int getStudentCount() {
        return students.size();
    }
}