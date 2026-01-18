import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * JustificationForm class for managing justifications for student absences.
 * Allows administrators to add, view, and manage justifications as per the use case requirements.
 * 
 * This form provides functionality for administrators to handle student
 * absence justifications including medical appointments, family emergencies, etc.
 */
public class JustificationForm {
    private final List<Student> students;
    private final Administrator administrator;
    private final Map<Student, List<Justification>> studentJustifications;
    private boolean isOpen;
    
    /**
     * Constructor to create a new JustificationForm.
     * 
     * @param students The list of students in the class register
     * @param administrator The administrator managing the form
     * @throws IllegalArgumentException if students list or administrator is null
     */
    public JustificationForm(List<Student> students, Administrator administrator) {
        if (students == null) {
            throw new IllegalArgumentException("Students list cannot be null");
        }
        if (administrator == null) {
            throw new IllegalArgumentException("Administrator cannot be null");
        }
        
        this.students = students;
        this.administrator = administrator;
        this.studentJustifications = new HashMap<>();
        this.isOpen = true;
        
        // Log the form creation
        administrator.logAction("Opened Justification Form", "Form initialized for " + students.size() + " students");
        
        System.out.println("Justification Form created for administrator: " + administrator.getUsername());
    }
    
    /**
     * Adds a justification for a student's absence.
     * 
     * @param student The student to add justification for
     * @param reason The reason for the justification
     * @param date The date of the absence being justified
     * @return true if justification was added successfully, false otherwise
     */
    public boolean addJustification(Student student, String reason, LocalDate date) {
        if (!isOpen) {
            System.out.println("Cannot add justification: Form is closed");
            return false;
        }
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        if (reason == null || reason.trim().isEmpty()) {
            throw new IllegalArgumentException("Justification reason cannot be null or empty");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (!students.contains(student)) {
            System.out.println("Cannot add justification: Student not found in class register");
            return false;
        }
        
        // Check if administrator has permission
        if (!administrator.canManageJustifications()) {
            System.out.println("Permission denied: Administrator cannot manage justifications");
            return false;
        }
        
        // Create new justification
        Justification justification = new Justification(student, reason, date, administrator);
        
        // Add to student's justification list
        studentJustifications.computeIfAbsent(student, k -> new ArrayList<>()).add(justification);
        
        // Log the action
        administrator.logAction("Added Justification", 
            "Student: " + student.getName() + ", Reason: " + reason + ", Date: " + date);
        
        System.out.println("Justification added for " + student.getName() + 
                          " for date " + date + ": " + reason);
        
        return true;
    }
    
    /**
     * Displays all justifications for all students in a formatted table.
     */
    public void displayJustifications() {
        if (!isOpen) {
            System.out.println("Cannot display justifications: Form is closed");
            return;
        }
        
        System.out.println("\n=== Justifications List ===");
        System.out.println("----------------------------------------------------------------");
        System.out.printf("%-20s %-12s %-15s %s%n", "Student Name", "Date", "Status", "Reason");
        System.out.println("----------------------------------------------------------------");
        
        boolean hasJustifications = false;
        
        for (Student student : students) {
            List<Justification> justifications = studentJustifications.get(student);
            if (justifications != null && !justifications.isEmpty()) {
                hasJustifications = true;
                for (Justification justification : justifications) {
                    System.out.printf("%-20s %-12s %-15s %s%n", 
                        student.getName(),
                        justification.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        justification.getStatus().toString(),
                        justification.getReason());
                }
            }
        }
        
        if (!hasJustifications) {
            System.out.println("No justifications found.");
        }
        
        System.out.println("----------------------------------------------------------------");
        
        // Display summary statistics
        displayJustificationSummary();
    }
    
    /**
     * Displays summary statistics for justifications.
     */
    private void displayJustificationSummary() {
        int totalJustifications = 0;
        int approved = 0;
        int pending = 0;
        int rejected = 0;
        
        for (List<Justification> justList : studentJustifications.values()) {
            if (justList != null) {
                totalJustifications += justList.size();
                for (Justification justification : justList) {
                    switch (justification.getStatus()) {
                        case APPROVED:
                            approved++;
                            break;
                        case PENDING:
                            pending++;
                            break;
                        case REJECTED:
                            rejected++;
                            break;
                    }
                }
            }
        }
        
        System.out.println("\nJustification Summary:");
        System.out.println("  Total Justifications: " + totalJustifications);
        System.out.println("  Approved: " + approved);
        System.out.println("  Pending: " + pending);
        System.out.println("  Rejected: " + rejected);
    }
    
    /**
     * Gets all justifications for a specific student.
     * 
     * @param student The student to get justifications for
     * @return List of justifications for the student, or empty list if none
     */
    public List<Justification> getJustificationsForStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        
        return studentJustifications.getOrDefault(student, new ArrayList<>());
    }
    
    /**
     * Approves a specific justification.
     * 
     * @param justification The justification to approve
     * @return true if approval successful, false otherwise
     */
    public boolean approveJustification(Justification justification) {
        if (justification == null) {
            throw new IllegalArgumentException("Justification cannot be null");
        }
        
        justification.setStatus(JustificationStatus.APPROVED);
        administrator.logAction("Approved Justification", 
            "Student: " + justification.getStudent().getName() + 
            ", Date: " + justification.getDate() + 
            ", Reason: " + justification.getReason());
        
        System.out.println("Justification approved for " + justification.getStudent().getName());
        return true;
    }
    
    /**
     * Rejects a specific justification.
     * 
     * @param justification The justification to reject
     * @return true if rejection successful, false otherwise
     */
    public boolean rejectJustification(Justification justification) {
        if (justification == null) {
            throw new IllegalArgumentException("Justification cannot be null");
        }
        
        justification.setStatus(JustificationStatus.REJECTED);
        administrator.logAction("Rejected Justification", 
            "Student: " + justification.getStudent().getName() + 
            ", Date: " + justification.getDate() + 
            ", Reason: " + justification.getReason());
        
        System.out.println("Justification rejected for " + justification.getStudent().getName());
        return true;
    }
    
    /**
     * Closes the justification form.
     * No further modifications can be made after closing.
     */
    public void closeForm() {
        if (isOpen) {
            isOpen = false;
            administrator.logAction("Closed Justification Form", 
                "Form closed with " + studentJustifications.size() + " students having justifications");
            System.out.println("Justification Form closed.");
        }
    }
    
    /**
     * Checks if the form is currently open.
     * 
     * @return true if form is open, false otherwise
     */
    public boolean isFormOpen() {
        return isOpen;
    }
    
    /**
     * Gets the administrator managing this form.
     * 
     * @return The administrator instance
     */
    public Administrator getAdministrator() {
        return administrator;
    }
    
    /**
     * Returns a string representation of the justification form.
     * 
     * @return String containing form information
     */
    @Override
    public String toString() {
        return String.format("JustificationForm{students=%d, justifications=%d, open=%s}", 
            students.size(), 
            studentJustifications.values().stream().mapToInt(List::size).sum(),
            isOpen);
    }
    
    /**
     * Inner class representing a single justification for a student's absence.
     */
    private static class Justification {
        private final Student student;
        private final String reason;
        private final LocalDate date;
        private final Administrator createdBy;
        private JustificationStatus status;
        
        /**
         * Constructor for Justification.
         * 
         * @param student The student being justified
         * @param reason The reason for the justification
         * @param date The date of the absence
         * @param createdBy The administrator who created the justification
         */
        public Justification(Student student, String reason, LocalDate date, Administrator createdBy) {
            if (student == null) {
                throw new IllegalArgumentException("Student cannot be null");
            }
            if (reason == null || reason.trim().isEmpty()) {
                throw new IllegalArgumentException("Justification reason cannot be null or empty");
            }
            if (date == null) {
                throw new IllegalArgumentException("Date cannot be null");
            }
            if (createdBy == null) {
                throw new IllegalArgumentException("Created by administrator cannot be null");
            }
            
            this.student = student;
            this.reason = reason.trim();
            this.date = date;
            this.createdBy = createdBy;
            this.status = JustificationStatus.PENDING;
        }
        
        public Student getStudent() {
            return student;
        }
        
        public String getReason() {
            return reason;
        }
        
        public LocalDate getDate() {
            return date;
        }
        
        public Administrator getCreatedBy() {
            return createdBy;
        }
        
        public JustificationStatus getStatus() {
            return status;
        }
        
        public void setStatus(JustificationStatus status) {
            if (status == null) {
                throw new IllegalArgumentException("Justification status cannot be null");
            }
            
            this.status = status;
        }
    }
    
    /**
     * Enumeration defining possible statuses for justifications.
     */
    private enum JustificationStatus {
        PENDING("Pending Review"),
        APPROVED("Approved"),
        REJECTED("Rejected");
        
        private final String displayName;
        
        JustificationStatus(String displayName) {
            this.displayName = displayName;
        }
        
        @Override
        public String toString() {
            return displayName;
        }
    }
}