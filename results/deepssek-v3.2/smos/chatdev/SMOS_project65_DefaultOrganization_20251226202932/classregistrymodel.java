/**
 * Model class that manages the data for the class registry system.
 * Maintains lists of students, dates, and attendance records.
 */
import java.util.*;
public class ClassRegistryModel {
    private List<Student> students;
    private List<Date> dates;
    private Map<Date, List<AttendanceRecord>> attendanceByDate;
    private boolean smosConnected;
    public ClassRegistryModel(List<Student> students, List<Date> dates) {
        this.students = new ArrayList<>(students);
        this.dates = new ArrayList<>(dates);
        this.attendanceByDate = new HashMap<>();
        this.smosConnected = true;
        // Initialize attendance records for all dates and students
        initializeAttendanceRecords();
    }
    private void initializeAttendanceRecords() {
        for (Date date : dates) {
            List<AttendanceRecord> records = new ArrayList<>();
            for (Student student : students) {
                records.add(new AttendanceRecord(student, date));
            }
            attendanceByDate.put(date, records);
        }
    }
    public List<Date> getDates() {
        return new ArrayList<>(dates);
    }
    public List<AttendanceRecord> getAttendanceForDate(Date date) {
        return attendanceByDate.getOrDefault(date, new ArrayList<>());
    }
    public void updateAttendanceRecord(Date date, Student student, 
                                       String status, String justification, 
                                       String disciplinaryNotes) {
        List<AttendanceRecord> records = attendanceByDate.get(date);
        if (records != null) {
            for (AttendanceRecord record : records) {
                if (record.getStudent().equals(student)) {
                    record.setStatus(status);
                    record.setJustification(justification);
                    record.setDisciplinaryNotes(disciplinaryNotes);
                    break;
                }
            }
        }
    }
    // SMOS server connection methods
    public boolean isSmosConnected() { return smosConnected; }
    public void simulateConnectionInterruption() {
        smosConnected = false;
        // In a real application, this would trigger error handling and reconnection logic
    }
    public void reconnectToSmos() {
        smosConnected = true;
    }
}