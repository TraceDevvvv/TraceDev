import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Student class representing a student in the system.
 * Contains personal information, student ID, and a list of associated reports.
 * This class follows the JavaBean pattern with getters and setters.
 */
public class Student {
    
    // Student personal information
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String program;
    private int yearOfStudy;
    
    // List of reports associated with this student
    private List<Report> reports;
    
    /**
     * Default constructor.
     * Generates a unique student ID and initializes an empty report list.
     */
    public Student() {
        this.studentId = generateStudentId();
        this.reports = new ArrayList<>();
    }
    
    /**
     * Parameterized constructor for creating a student with basic information.
     * 
     * @param firstName Student's first name
     * @param lastName Student's last name
     * @param email Student's email address
     * @param program Student's academic program
     * @param yearOfStudy Student's year of study
     */
    public Student(String firstName, String lastName, String email, 
                   String program, int yearOfStudy) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.program = program;
        this.yearOfStudy = yearOfStudy;
    }
    
    /**
     * Full parameterized constructor including student ID.
     * 
     * @param studentId Unique identifier for the student (if null, generates new)
     * @param firstName Student's first name
     * @param lastName Student's last name
     * @param email Student's email address
     * @param program Student's academic program
     * @param yearOfStudy Student's year of study
     * @param reports List of reports associated with the student (if null, creates empty list)
     */
    public Student(String studentId, String firstName, String lastName, 
                   String email, String program, int yearOfStudy, 
                   List<Report> reports) {
        this.studentId = (studentId != null) ? studentId : generateStudentId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.program = program;
        setYearOfStudy(yearOfStudy); // Use setter for validation
        
        if (reports != null) {
            this.reports = new ArrayList<>(reports);
            // Ensure all reports have this student's ID
            for (Report report : this.reports) {
                report.setStudentId(this.studentId);
            }
        } else {
            this.reports = new ArrayList<>();
        }
    }
    
    /**
     * Generates a unique student ID in format "STU-XXXXX"
     * where XXXXX is a UUID segment.
     * 
     * @return Generated student ID
     */
    private String generateStudentId() {
        String uuid = UUID.randomUUID().toString().toUpperCase();
        return "STU-" + uuid.substring(0, 8);
    }
    
    // Getters and Setters
    
    /**
     * @return Unique student identifier
     */
    public String getStudentId() {
        return studentId;
    }
    
    /**
     * Sets the student ID.
     * 
     * @param studentId The student ID to set
     */
    public void setStudentId(String studentId) {
        if (studentId != null && !studentId.trim().isEmpty()) {
            this.studentId = studentId.trim();
            // Update student ID in all associated reports
            for (Report report : reports) {
                report.setStudentId(this.studentId);
            }
        }
    }
    
    /**
     * @return Student's first name
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * @param firstName First name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * @return Student's last name
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * @param lastName Last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /**
     * Gets the student's full name.
     * 
     * @return Full name in format "FirstName LastName"
     */
    public String getFullName() {
        return (firstName != null ? firstName : "") + " " + (lastName != null ? lastName : "");
    }
    
    /**
     * @return Student's email address
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * @param email Email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * @return Student's academic program
     */
    public String getProgram() {
        return program;
    }
    
    /**
     * @param program Academic program to set
     */
    public void setProgram(String program) {
        this.program = program;
    }
    
    /**
     * @return Student's year of study
     */
    public int getYearOfStudy() {
        return yearOfStudy;
    }
    
    /**
     * Sets the year of study with validation.
     * 
     * @param yearOfStudy Year to set (1-10)
     * @throws IllegalArgumentException if year is outside valid range
     */
    public void setYearOfStudy(int yearOfStudy) {
        if (yearOfStudy < 1 || yearOfStudy > 10) {
            throw new IllegalArgumentException("Year of study must be between 1 and 10");
        }
        this.yearOfStudy = yearOfStudy;
    }
    
    /**
     * @return List of reports associated with this student (defensive copy)
     */
    public List<Report> getReports() {
        return new ArrayList<>(reports);
    }
    
    /**
     * Sets the list of reports for this student.
     * Replaces existing reports and updates student ID in all reports.
     * 
     * @param reports List of reports to set (creates defensive copy)
     */
    public void setReports(List<Report> reports) {
        if (reports != null) {
            this.reports = new ArrayList<>(reports);
            for (Report report : this.reports) {
                report.setStudentId(this.studentId);
            }
        } else {
            this.reports = new ArrayList<>();
        }
    }
    
    // Report management methods
    
    /**
     * Adds a report to the student's report list.
     * Sets the student ID in the report.
     * 
     * @param report Report to add
     * @return true if report was added successfully
     */
    public boolean addReport(Report report) {
        if (report != null) {
            report.setStudentId(this.studentId);
            return reports.add(report);
        }
        return false;
    }
    
    /**
     * Removes a report from the student's report list.
     * 
     * @param report Report to remove
     * @return true if report was removed
     */
    public boolean removeReport(Report report) {
        if (report != null) {
            return reports.remove(report);
        }
        return false;
    }
    
    /**
     * Removes a report by its ID.
     * 
     * @param reportId ID of the report to remove
     * @return The removed report, or null if not found
     */
    public Report removeReportById(String reportId) {
        for (int i = 0; i < reports.size(); i++) {
            Report report = reports.get(i);
            if (report.getReportId().equals(reportId)) {
                return reports.remove(i);
            }
        }
        return null;
    }
    
    /**
     * Gets a report by its ID.
     * 
     * @param reportId ID of the report to find
     * @return The report, or null if not found
     */
    public Report getReportById(String reportId) {
        for (Report report : reports) {
            if (report.getReportId().equals(reportId)) {
                return report;
            }
        }
        return null;
    }
    
    /**
     * Gets reports for a specific semester.
     * 
     * @param semester Semester to filter by
     * @return List of reports for the given semester
     */
    public List<Report> getReportsBySemester(String semester) {
        List<Report> semesterReports = new ArrayList<>();
        for (Report report : reports) {
            if (report.getSemester() != null && 
                report.getSemester().equalsIgnoreCase(semester)) {
                semesterReports.add(report);
            }
        }
        return semesterReports;
    }
    
    /**
     * Gets all report titles for quick reference.
     * 
     * @return List of report titles
     */
    public List<String> getReportTitles() {
        List<String> titles = new ArrayList<>();
        for (Report report : reports) {
            titles.add(report.getTitle());
        }
        return titles;
    }
    
    /**
     * Calculates the student's overall average across all reports.
     * 
     * @return Average overall grade, or 0 if no reports
     */
    public double calculateOverallAverage() {
        if (reports.isEmpty()) {
            return 0.0;
        }
        
        double sum = 0.0;
        for (Report report : reports) {
            sum += report.getOverallGrade();
        }
        return sum / reports.size();
    }
    
    /**
     * Calculates the student's average for a specific subject across all reports.
     * 
     * @param subjectName Name of the subject
     * @return Average grade for the subject, or 0 if subject not found in any report
     */
    public double calculateSubjectAverage(String subjectName) {
        if (reports.isEmpty()) {
            return 0.0;
        }
        
        double sum = 0.0;
        int count = 0;
        
        for (Report report : reports) {
            Double grade = report.getSubjectGrade(subjectName);
            if (grade != null) {
                sum += grade;
                count++;
            }
        }
        
        return count > 0 ? sum / count : 0.0;
    }
    
    /**
     * Checks if the student has any reports with failing grades (below 60).
     * 
     * @return true if any report has failing grades, false otherwise
     */
    public boolean hasAnyFailingGrades() {
        for (Report report : reports) {
            if (report.hasFailingGrades()) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Gets the most recent report based on generation date.
     * 
     * @return Most recent report, or null if no reports
     */
    public Report getMostRecentReport() {
        if (reports.isEmpty()) {
            return null;
        }
        
        Report mostRecent = reports.get(0);
        for (int i = 1; i < reports.size(); i++) {
            Report current = reports.get(i);
            if (current.getGenerationDate().after(mostRecent.getGenerationDate())) {
                mostRecent = current;
            }
        }
        return mostRecent;
    }
    
    /**
     * Gets the report with the highest overall grade.
     * 
     * @return Report with highest grade, or null if no reports
     */
    public Report getBestReport() {
        if (reports.isEmpty()) {
            return null;
        }
        
        Report best = reports.get(0);
        for (int i = 1; i < reports.size(); i++) {
            Report current = reports.get(i);
            if (current.getOverallGrade() > best.getOverallGrade()) {
                best = current;
            }
        }
        return best;
    }
    
    /**
     * Validates the student data.
     * 
     * @return true if student data is valid, false otherwise
     */
    public boolean isValid() {
        if (studentId == null || studentId.trim().isEmpty()) {
            return false;
        }
        if (firstName == null || firstName.trim().isEmpty()) {
            return false;
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            return false;
        }
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            return false;
        }
        if (program == null || program.trim().isEmpty()) {
            return false;
        }
        if (yearOfStudy < 1 || yearOfStudy > 10) {
            return false;
        }
        return true;
    }
    
    /**
     * Returns a string representation of the student.
     * 
     * @return Formatted string with student details
     */
    @Override
    public String toString() {
        return String.format("Student[ID=%s, Name=%s %s, Program=%s, Year=%d, Reports=%d]", 
                studentId, firstName, lastName, program, yearOfStudy, reports.size());
    }
    
    /**
     * Creates a display summary for the student.
     * 
     * @return Formatted summary string
     */
    public String getDisplaySummary() {
        return String.format("%s %s (%s) - %s, Year %d", 
                firstName, lastName, studentId, program, yearOfStudy);
    }
}