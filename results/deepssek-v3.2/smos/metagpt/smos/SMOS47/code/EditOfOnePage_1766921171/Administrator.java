import java.util.ArrayList;
import java.util.List;

/**
 * Administrator class representing an administrator user who can edit student report cards.
 * Includes authentication functionality and methods to manage students and their report cards.
 */
public class Administrator {
    private String username;
    private String password;
    private boolean isLoggedIn;
    private List<Student> students;
    
    /**
     * Constructor for Administrator class
     * 
     * @param username Administrator username
     * @param password Administrator password
     */
    public Administrator(String username, String password) {
        this.username = username;
        this.password = password;
        this.isLoggedIn = false;
        this.students = new ArrayList<>();
    }
    
    /**
     * Attempt to log in with provided credentials
     * 
     * @param inputUsername Username to check
     * @param inputPassword Password to check
     * @return true if login successful, false otherwise
     */
    public boolean login(String inputUsername, String inputPassword) {
        if (username.equals(inputUsername) && password.equals(inputPassword)) {
            isLoggedIn = true;
            return true;
        }
        return false;
    }
    
    /**
     * Log out the administrator
     */
    public void logout() {
        isLoggedIn = false;
    }
    
    /**
     * Check if administrator is currently logged in
     * 
     * @return true if logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    /**
     * Add a student to the system
     * Only allowed if administrator is logged in
     * 
     * @param student Student to add
     * @return true if student added successfully, false if not logged in
     */
    public boolean addStudent(Student student) {
        if (!isLoggedIn) {
            return false;
        }
        
        // Check if student already exists
        for (Student s : students) {
            if (s.getStudentId().equals(student.getStudentId())) {
                return false; // Student ID already exists
            }
        }
        
        students.add(student);
        return true;
    }
    
    /**
     * Find a student by their ID
     * Only allowed if administrator is logged in
     * 
     * @param studentId ID of the student to find
     * @return Student object if found, null if not found or not logged in
     */
    public Student findStudentById(String studentId) {
        if (!isLoggedIn) {
            return null;
        }
        
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }
    
    /**
     * Edit a student's report card by updating grades for specific subjects
     * Only allowed if administrator is logged in
     * 
     * @param studentId ID of the student whose report card to edit
     * @param subjectGrades Map of subject names and new grades
     * @return true if edit successful, false if student not found, not logged in, or invalid grades
     */
    public boolean editStudentReportCard(String studentId, java.util.Map<String, Double> subjectGrades) {
        if (!isLoggedIn) {
            return false;
        }
        
        Student student = findStudentById(studentId);
        if (student == null) {
            return false;
        }
        
        ReportCard reportCard = student.getReportCard();
        boolean allGradesValid = true;
        
        // Validate all grades before making any changes
        for (java.util.Map.Entry<String, Double> entry : subjectGrades.entrySet()) {
            if (!ReportCard.isValidGrade(entry.getValue())) {
                allGradesValid = false;
                break;
            }
        }
        
        if (!allGradesValid) {
            return false;
        }
        
        // Update grades
        for (java.util.Map.Entry<String, Double> entry : subjectGrades.entrySet()) {
            reportCard.addOrUpdateGrade(entry.getKey(), entry.getValue());
        }
        
        return true;
    }
    
    /**
     * Get all students in the system
     * Only allowed if administrator is logged in
     * 
     * @return List of all students, or empty list if not logged in
     */
    public List<Student> getAllStudents() {
        if (!isLoggedIn) {
            return new ArrayList<>();
        }
        return new ArrayList<>(students);
    }
    
    /**
     * Remove a student from the system
     * Only allowed if administrator is logged in
     * 
     * @param studentId ID of the student to remove
     * @return true if student removed successfully, false if not found or not logged in
     */
    public boolean removeStudent(String studentId) {
        if (!isLoggedIn) {
            return false;
        }
        
        Student studentToRemove = null;
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                studentToRemove = student;
                break;
            }
        }
        
        if (studentToRemove != null) {
            students.remove(studentToRemove);
            return true;
        }
        
        return false;
    }
    
    /**
     * Get the username of the administrator
     * 
     * @return Administrator username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Get the number of students in the system
     * 
     * @return Count of students
     */
    public int getStudentCount() {
        return students.size();
    }
    
    /**
     * Clear all students from the system
     * Only allowed if administrator is logged in
     * 
     * @return true if cleared successfully, false if not logged in
     */
    public boolean clearAllStudents() {
        if (!isLoggedIn) {
            return false;
        }
        students.clear();
        return true;
    }
    
    /**
     * String representation of the Administrator
     * Shows username and login status
     * 
     * @return Formatted string with administrator information
     */
    @Override
    public String toString() {
        return String.format("Administrator: %s (Logged in: %s, Students: %d)", 
                           username, isLoggedIn ? "Yes" : "No", students.size());
    }
}