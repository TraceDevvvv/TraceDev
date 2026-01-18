import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * DisciplinaryNoteForm class for managing disciplinary notes for students.
 * Allows administrators to add, view, and manage disciplinary notes as per the use case requirements.
 * 
 * This form provides functionality for administrators to handle student
 * disciplinary issues including behavioral problems, rule violations, etc.
 */
public class DisciplinaryNoteForm {
    private final List<Student> students;
    private final Administrator administrator;
    private final Map<Student, List<DisciplinaryNote>> studentNotes;
    private boolean isOpen;
    
    /**
     * Constructor to create a new DisciplinaryNoteForm.
     * 
     * @param students The list of students in the class register
     * @param administrator The administrator managing the form
     * @throws IllegalArgumentException if students list or administrator is null
     */
    public DisciplinaryNoteForm(List<Student> students, Administrator administrator) {
        if (students == null) {
            throw new IllegalArgumentException("Students list cannot be null");
        }
        if (administrator == null) {
            throw new IllegalArgumentException("Administrator cannot be null");
        }
        
        this.students = students;
        this.administrator = administrator;
        this.studentNotes = new HashMap<>();
        this.isOpen = true;
        
        // Log the form creation
        administrator.logAction("Opened Disciplinary Note Form", 
            "Form initialized for " + students.size() + " students");
        
        System.out.println("Disciplinary Note Form created for administrator: " + administrator.getUsername());
    }
    
    /**
     * Adds a disciplinary note for a student.
     * 
     * @param student The student to add the note for
     * @param description The description of the disciplinary issue
     * @param date The date when the incident occurred
     * @return true if note was added successfully, false otherwise
     */
    public boolean addNote(Student student, String description, LocalDate date) {
        return addNote(student, description, date, "General", "Medium");
    }
    
    /**
     * Adds a disciplinary note for a student with severity and category.
     * 
     * @param student The student to add the note for
     * @param description The description of the disciplinary issue
     * @param date The date when the incident occurred
     * @param category The category of the disciplinary issue (e.g., "Behavior", "Academic")
     * @param severity The severity level (e.g., "Low", "Medium", "High")
     * @return true if note was added successfully, false otherwise
     */
    public boolean addNote(Student student, String description, LocalDate date, 
                          String category, String severity) {
        if (!isOpen) {
            System.out.println("Cannot add disciplinary note: Form is closed");
            return false;
        }
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (!students.contains(student)) {
            System.out.println("Cannot add disciplinary note: Student not found in class register");
            return false;
        }
        
        // Check if administrator has permission
        if (!administrator.canManageDisciplinaryNotes()) {
            System.out.println("Permission denied: Administrator cannot manage disciplinary notes");
            return false;
        }
        
        // Create new disciplinary note
        DisciplinaryNote note = new DisciplinaryNote(student, description, date, 
                                                    administrator, category, severity);
        
        // Add to student's note list
        studentNotes.computeIfAbsent(student, k -> new ArrayList<>()).add(note);
        
        // Log the action
        administrator.logAction("Added Disciplinary Note", 
            "Student: " + student.getName() + 
            ", Category: " + category + 
            ", Severity: " + severity + 
            ", Date: " + date);
        
        System.out.println("Disciplinary note added for " + student.getName() + 
                          " for date " + date + ": " + description);
        
        return true;
    }
    
    /**
     * Displays all disciplinary notes for all students in a formatted table.
     */
    public void displayNotes() {
        if (!isOpen) {
            System.out.println("Cannot display disciplinary notes: Form is closed");
            return;
        }
        
        System.out.println("\n=== Disciplinary Notes List ===");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf("%-20s %-12s %-15s %-10s %s%n", 
            "Student Name", "Date", "Category", "Severity", "Description");
        System.out.println("----------------------------------------------------------------------------------------");
        
        boolean hasNotes = false;
        
        for (Student student : students) {
            List<DisciplinaryNote> notes = studentNotes.get(student);
            if (notes != null && !notes.isEmpty()) {
                hasNotes = true;
                for (DisciplinaryNote note : notes) {
                    System.out.printf("%-20s %-12s %-15s %-10s %s%n", 
                        student.getName(),
                        note.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        note.getCategory(),
                        note.getSeverity(),
                        note.getDescription());
                }
            }
        }
        
        if (!hasNotes) {
            System.out.println("No disciplinary notes found.");
        }
        
        System.out.println("----------------------------------------------------------------------------------------");
        
        // Display summary statistics
        displayNotesSummary();
    }
    
    /**
     * Displays summary statistics for disciplinary notes.
     */
    private void displayNotesSummary() {
        int totalNotes = 0;
        Map<String, Integer> categoryCount = new HashMap<>();
        Map<String, Integer> severityCount = new HashMap<>();
        
        for (List<DisciplinaryNote> noteList : studentNotes.values()) {
            if (noteList != null) {
                totalNotes += noteList.size();
                for (DisciplinaryNote note : noteList) {
                    // Count by category
                    String category = note.getCategory();
                    categoryCount.put(category, categoryCount.getOrDefault(category, 0) + 1);
                    
                    // Count by severity
                    String severity = note.getSeverity();
                    severityCount.put(severity, severityCount.getOrDefault(severity, 0) + 1);
                }
            }
        }
        
        System.out.println("\nDisciplinary Notes Summary:");
        System.out.println("  Total Notes: " + totalNotes);
        
        if (!categoryCount.isEmpty()) {
            System.out.println("  By Category:");
            for (Map.Entry<String, Integer> entry : categoryCount.entrySet()) {
                System.out.println("    " + entry.getKey() + ": " + entry.getValue());
            }
        }
        
        if (!severityCount.isEmpty()) {
            System.out.println("  By Severity:");
            for (Map.Entry<String, Integer> entry : severityCount.entrySet()) {
                System.out.println("    " + entry.getKey() + ": " + entry.getValue());
            }
        }
    }
    
    /**
     * Gets all disciplinary notes for a specific student.
     * 
     * @param student The student to get notes for
     * @return List of disciplinary notes for the student, or empty list if none
     */
    public List<DisciplinaryNote> getNotesForStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        
        return studentNotes.getOrDefault(student, new ArrayList<>());
    }
    
    /**
     * Gets disciplinary notes for a specific date range.
     * 
     * @param startDate The start date of the range (inclusive)
     * @param endDate The end date of the range (inclusive)
     * @return List of disciplinary notes within the date range
     */
    public List<DisciplinaryNote> getNotesByDateRange(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start date and end date cannot be null");
        }
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }
        
        List<DisciplinaryNote> result = new ArrayList<>();
        
        for (List<DisciplinaryNote> noteList : studentNotes.values()) {
            if (noteList != null) {
                for (DisciplinaryNote note : noteList) {
                    LocalDate noteDate = note.getDate();
                    if (!noteDate.isBefore(startDate) && !noteDate.isAfter(endDate)) {
                        result.add(note);
                    }
                }
            }
        }
        
        return result;
    }
    
    /**
     * Gets disciplinary notes by severity level.
     * 
     * @param severity The severity level to filter by
     * @return List of disciplinary notes with the specified severity
     */
    public List<DisciplinaryNote> getNotesBySeverity(String severity) {
        if (severity == null || severity.trim().isEmpty()) {
            throw new IllegalArgumentException("Severity cannot be null or empty");
        }
        
        List<DisciplinaryNote> result = new ArrayList<>();
        String searchSeverity = severity.trim();
        
        for (List<DisciplinaryNote> noteList : studentNotes.values()) {
            if (noteList != null) {
                for (DisciplinaryNote note : noteList) {
                    if (note.getSeverity().equalsIgnoreCase(searchSeverity)) {
                        result.add(note);
                    }
                }
            }
        }
        
        return result;
    }
    
    /**
     * Updates the status of a disciplinary note.
     * 
     * @param note The note to update
     * @param newStatus The new status for the note
     * @param resolutionNotes Additional notes about the resolution
     * @return true if update successful, false otherwise
     */
    public boolean updateNoteStatus(DisciplinaryNote note, String newStatus, String resolutionNotes) {
        if (note == null) {
            throw new IllegalArgumentException("Note cannot be null");
        }
        if (newStatus == null || newStatus.trim().isEmpty()) {
            throw new IllegalArgumentException("New status cannot be null or empty");
        }
        
        note.setStatus(newStatus.trim());
        if (resolutionNotes != null && !resolutionNotes.trim().isEmpty()) {
            note.setResolutionNotes(resolutionNotes.trim());
        }
        
        administrator.logAction("Updated Disciplinary Note Status", 
            "Student: " + note.getStudent().getName() + 
            ", New Status: " + newStatus + 
            ", Date: " + note.getDate());
        
        System.out.println("Disciplinary note status updated for " + note.getStudent().getName());
        return true;
    }
    
    /**
     * Closes the disciplinary note form.
     * No further modifications can be made after closing.
     */
    public void closeForm() {
        if (isOpen) {
            isOpen = false;
            administrator.logAction("Closed Disciplinary Note Form", 
                "Form closed with " + studentNotes.size() + " students having disciplinary notes");
            System.out.println("Disciplinary Note Form closed.");
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
     * Returns a string representation of the disciplinary note form.
     * 
     * @return String containing form information
     */
    @Override
    public String toString() {
        return String.format("DisciplinaryNoteForm{students=%d, notes=%d, open=%s}", 
            students.size(), 
            studentNotes.values().stream().mapToInt(List::size).sum(),
            isOpen);
    }
    
    /**
     * Inner class representing a single disciplinary note for a student.
     */
    private static class DisciplinaryNote {
        private final Student student;
        private final String description;
        private final LocalDate date;
        private final Administrator createdBy;
        private final String category;
        private final String severity;
        private String status;
        private String resolutionNotes;
        
        /**
         * Constructor for DisciplinaryNote.
         * 
         * @param student The student the note is about
         * @param description The description of the disciplinary issue
         * @param date The date when the incident occurred
         * @param createdBy The administrator who created the note
         * @param category The category of the disciplinary issue
         * @param severity The severity level
         */
        public DisciplinaryNote(Student student, String description, LocalDate date, 
                               Administrator createdBy, String category, String severity) {
            if (student == null) {
                throw new IllegalArgumentException("Student cannot be null");
            }
            if (description == null || description.trim().isEmpty()) {
                throw new IllegalArgumentException("Description cannot be null or empty");
            }
            if (date == null) {
                throw new IllegalArgumentException("Date cannot be null");
            }
            if (createdBy == null) {
                throw new IllegalArgumentException("Created by administrator cannot be null");
            }
            if (category == null || category.trim().isEmpty()) {
                throw new IllegalArgumentException("Category cannot be null or empty");
            }
            if (severity == null || severity.trim().isEmpty()) {
                throw new IllegalArgumentException("Severity cannot be null or empty");
            }
            
            this.student = student;
            this.description = description.trim();
            this.date = date;
            this.createdBy = createdBy;
            this.category = category.trim();
            this.severity = severity.trim();
            this.status = "Open";
            this.resolutionNotes = "";
        }
        
        public Student getStudent() {
            return student;
        }
        
        public String getDescription() {
            return description;
        }
        
        public LocalDate getDate() {
            return date;
        }
        
        public Administrator getCreatedBy() {
            return createdBy;
        }
        
        public String getCategory() {
            return category;
        }
        
        public String getSeverity() {
            return severity;
        }
        
        public String getStatus() {
            return status;
        }
        
        public void setStatus(String status) {
            if (status == null || status.trim().isEmpty()) {
                throw new IllegalArgumentException("Status cannot be null or empty");
            }
            this.status = status.trim();
        }
        
        public String getResolutionNotes() {
            return resolutionNotes;
        }
        
        public void setResolutionNotes(String resolutionNotes) {
            this.resolutionNotes = (resolutionNotes != null) ? resolutionNotes.trim() : "";
        }
        
        /**
         * Checks if the disciplinary note is resolved.
         * 
         * @return true if status is "Resolved" or "Closed", false otherwise
         */
        public boolean isResolved() {
            return "Resolved".equalsIgnoreCase(status) || "Closed".equalsIgnoreCase(status);
        }
        
        /**
         * Returns a formatted string representation of the disciplinary note.
         * 
         * @return Formatted string with note details
         */
        public String toFormattedString() {
            return String.format("DisciplinaryNote{student=%s, date=%s, category=%s, severity=%s, status=%s}", 
                student.getName(), date, category, severity, status);
        }
    }
}