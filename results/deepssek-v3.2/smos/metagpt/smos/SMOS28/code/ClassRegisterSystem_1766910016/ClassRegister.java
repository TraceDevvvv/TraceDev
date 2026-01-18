import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 * ClassRegister class representing a class register.
 * Manages students, attendance records, and class information.
 * This is the central class for the ViewDetailsSingleRegister use case.
 */
public class ClassRegister {
    private final String className;
    private final String teacherName;
    private final String academicYear;
    private final String roomNumber;
    private final List<Student> students;
    private final Map<LocalDate, AttendanceRecord> attendanceRecords;
    
    /**
     * Constructor to create a new class register.
     * 
     * @param className The name of the class
     * @param teacherName The name of the teacher
     * @param academicYear The academic year (e.g., "2023-2024")
     * @param roomNumber The room number where the class is held
     */
    public ClassRegister(String className, String teacherName, String academicYear, String roomNumber) {
        if (className == null || className.trim().isEmpty()) {
            throw new IllegalArgumentException("Class name cannot be null or empty");
        }
        if (teacherName == null || teacherName.trim().isEmpty()) {
            throw new IllegalArgumentException("Teacher name cannot be null or empty");
        }
        if (academicYear == null || academicYear.trim().isEmpty()) {
            throw new IllegalArgumentException("Academic year cannot be null or empty");
        }
        
        this.className = className.trim();
        this.teacherName = teacherName.trim();
        this.academicYear = academicYear.trim();
        this.roomNumber = (roomNumber != null) ? roomNumber.trim() : "";
        this.students = new ArrayList<>();
        this.attendanceRecords = new HashMap<>();
    }
    
    /**
     * Gets the name of the class.
     * 
     * @return The class name
     */
    public String getClassName() {
        return className;
    }
    
    /**
     * Gets the name of the teacher.
     * 
     * @return The teacher name
     */
    public String getTeacherName() {
        return teacherName;
    }
    
    /**
     * Gets the academic year.
     * 
     * @return The academic year
     */
    public String getAcademicYear() {
        return academicYear;
    }
    
    /**
     * Gets the room number.
     * 
     * @return The room number, or empty string if not set
     */
    public String getRoomNumber() {
        return roomNumber;
    }
    
    /**
     * Gets the list of students in this class.
     * 
     * @return Unmodifiable list of students
     */
    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }
    
    /**
     * Gets the attendance records organized by date.
     * 
     * @return Unmodifiable map of attendance records by date
     */
    public Map<LocalDate, AttendanceRecord> getAttendanceRecords() {
        return Collections.unmodifiableMap(attendanceRecords);
    }
    
    /**
     * Gets the total number of students in the class.
     * 
     * @return The number of students
     */
    public int getTotalStudents() {
        return students.size();
    }
    
    /**
     * Adds a student to the class register.
     * 
     * @param student The student to add
     * @throws IllegalArgumentException if student is null or already exists
     */
    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        
        // Check if student with same ID already exists
        for (Student existing : students) {
            if (existing.getStudentId().equals(student.getStudentId())) {
                throw new IllegalArgumentException("Student with ID " + student.getStudentId() + " already exists");
            }
        }
        
        students.add(student);
        System.out.println("Added student: " + student.getName() + " (ID: " + student.getStudentId() + ")");
    }
    
    /**
     * Removes a student from the class register.
     * 
     * @param studentId The ID of the student to remove
     * @return true if student was removed, false if not found
     */
    public boolean removeStudent(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty");
        }
        
        for (Iterator<Student> iterator = students.iterator(); iterator.hasNext();) {
            Student student = iterator.next();
            if (student.getStudentId().equals(studentId.trim())) {
                iterator.remove();
                System.out.println("Removed student: " + student.getName() + " (ID: " + studentId + ")");
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Adds an attendance record for a specific date.
     * 
     * @param record The attendance record to add
     * @throws IllegalArgumentException if record is null or record already exists for the date
     */
    public void addAttendanceRecord(AttendanceRecord record) {
        if (record == null) {
            throw new IllegalArgumentException("Attendance record cannot be null");
        }
        
        LocalDate date = record.getDate();
        if (attendanceRecords.containsKey(date)) {
            throw new IllegalArgumentException("Attendance record already exists for date: " + date);
        }
        
        attendanceRecords.put(date, record);
        System.out.println("Added attendance record for " + date);
    }
    
    /**
     * Updates an existing attendance record for a specific date.
     * 
     * @param record The updated attendance record
     * @throws IllegalArgumentException if record is null or no record exists for the date
     */
    public void updateAttendanceRecord(AttendanceRecord record) {
        if (record == null) {
            throw new IllegalArgumentException("Attendance record cannot be null");
        }
        
        LocalDate date = record.getDate();
        if (!attendanceRecords.containsKey(date)) {
            throw new IllegalArgumentException("No attendance record exists for date: " + date);
        }
        
        attendanceRecords.put(date, record);
        System.out.println("Updated attendance record for " + date);
    }
    
    /**
     * Gets the attendance record for a specific date.
     * 
     * @param date The date to get the record for
     * @return The attendance record, or null if no record exists
     */
    public AttendanceRecord getAttendanceRecord(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        
        return attendanceRecords.get(date);
    }
    
    /**
     * Gets today's attendance record.
     * 
     * @return Today's attendance record, or null if no record exists
     */
    public AttendanceRecord getTodaysAttendanceRecord() {
        return getAttendanceRecord(LocalDate.now());
    }
    
    /**
     * Takes attendance for all students on a specific date.
     * This method updates each student's attendance and the class attendance record.
     * 
     * @param date The date for which to take attendance
     * @param attendanceMap Map of student IDs to their attendance status for the date
     * @param arrivalTimeMap Map of student IDs to their arrival times (null for absent)
     * @param notesMap Map of student IDs to notes for the date
     * @return The created attendance record
     */
    public AttendanceRecord takeAttendance(LocalDate date, 
                                          Map<String, AttendanceStatus> attendanceMap,
                                          Map<String, LocalTime> arrivalTimeMap,
                                          Map<String, String> notesMap) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (attendanceMap == null) {
            throw new IllegalArgumentException("Attendance map cannot be null");
        }
        
        // Set attendance for each student
        for (Student student : students) {
            String studentId = student.getStudentId();
            AttendanceStatus status = attendanceMap.get(studentId);
            
            if (status != null) {
                LocalTime arrivalTime = arrivalTimeMap != null ? arrivalTimeMap.get(studentId) : null;
                String notes = notesMap != null ? notesMap.get(studentId) : "";
                
                student.setAttendance(date, status, arrivalTime, notes);
            }
        }
        
        // Create or update attendance record
        AttendanceRecord record = attendanceRecords.get(date);
        if (record == null) {
            record = new AttendanceRecord(date);
            attendanceRecords.put(date, record);
        }
        
        // Update counts
        record.updateCounts(students, date);
        
        System.out.println("Attendance taken for " + date + ": " + record.getPresentCount() + 
                          " present, " + record.getAbsentCount() + " absent, " + 
                          record.getLateCount() + " late");
        
        return record;
    }
    
    /**
     * Gets the attendance statistics for the class.
     * 
     * @return Map containing overall attendance statistics
     */
    public Map<String, Object> getAttendanceStatistics() {
        Map<String, Object> stats = new HashMap<>();
        
        int totalDays = attendanceRecords.size();
        int totalPresent = 0;
        int totalAbsent = 0;
        int totalLate = 0;
        
        for (AttendanceRecord record : attendanceRecords.values()) {
            totalPresent += record.getPresentCount();
            totalAbsent += record.getAbsentCount();
            totalLate += record.getLateCount();
        }
        
        int totalRecords = totalPresent + totalAbsent + totalLate;
        double overallPercentage = totalRecords > 0 ? 
            ((double)(totalPresent + totalLate) / totalRecords) * 100.0 : 0.0;
        
        stats.put("totalDays", totalDays);
        stats.put("totalPresent", totalPresent);
        stats.put("totalAbsent", totalAbsent);
        stats.put("totalLate", totalLate);
        stats.put("totalRecords", totalRecords);
        stats.put("attendancePercentage", overallPercentage);
        
        return Collections.unmodifiableMap(stats);
    }
    
    /**
     * Finds a student by their ID.
     * 
     * @param studentId The student ID to search for
     * @return The student with the matching ID, or null if not found
     */
    public Student findStudentById(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            return null;
        }
        
        for (Student student : students) {
            if (student.getStudentId().equals(studentId.trim())) {
                return student;
            }
        }
        
        return null;
    }
    
    /**
     * Finds students by name (case-insensitive partial match).
     * 
     * @param namePart The part of the name to search for
     * @return List of students whose names contain the search term
     */
    public List<Student> findStudentsByName(String namePart) {
        List<Student> results = new ArrayList<>();
        
        if (namePart == null || namePart.trim().isEmpty()) {
            return results;
        }
        
        String searchTerm = namePart.trim().toLowerCase();
        
        for (Student student : students) {
            if (student.getName().toLowerCase().contains(searchTerm)) {
                results.add(student);
            }
        }
        
        return results;
    }
    
    /**
     * Gets dates with attendance records in chronological order.
     * 
     * @return List of dates with attendance records, sorted ascending
     */
    public List<LocalDate> getAttendanceDates() {
        List<LocalDate> dates = new ArrayList<>(attendanceRecords.keySet());
        Collections.sort(dates);
        return dates;
    }
    
    /**
     * Returns a string representation of the class register.
     * 
     * @return String containing class information
     */
    @Override
    public String toString() {
        return String.format("ClassRegister{className='%s', teacherName='%s', academicYear='%s', students=%d}", 
            className, teacherName, academicYear, students.size());
    }
    
    /**
     * Creates a detailed summary of the class register.
     * 
     * @return Detailed summary string
     */
    public String getDetailedSummary() {
        Map<String, Object> stats = getAttendanceStatistics();
        
        StringBuilder summary = new StringBuilder();
        summary.append("=== Class Register Summary ===\n");
        summary.append("Class: ").append(className).append("\n");
        summary.append("Teacher: ").append(teacherName).append("\n");
        summary.append("Academic Year: ").append(academicYear).append("\n");
        summary.append("Room: ").append(roomNumber.isEmpty() ? "Not specified" : roomNumber).append("\n");
        summary.append("Total Students: ").append(students.size()).append("\n");
        summary.append("Days with Attendance: ").append(stats.get("totalDays")).append("\n");
        summary.append("Overall Attendance: ").append(String.format("%.1f%%", stats.get("attendancePercentage"))).append("\n");
        summary.append("Total Records: ").append(stats.get("totalRecords")).append("\n");
        summary.append("  Present: ").append(stats.get("totalPresent")).append("\n");
        summary.append("  Absent: ").append(stats.get("totalAbsent")).append("\n");
        summary.append("  Late: ").append(stats.get("totalLate")).append("\n");
        
        return summary.toString();
    }
}