'''
Simulates a service layer for fetching and filtering student data from an archive.
In a real application, this would interact with a database or an external API.
'''
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class StudentMonitorService {
    /**
     * Simulates retrieving all student data for the current school year from an archive.
     * In a real system, this would query a database. For this example, it returns mock data.
     *
     * @return A list of all students.
     */
    public List<Student> getStudentsForCurrentSchoolYear() {
        // Mock data representing students in the system for the current school year
        // In a real application, this would fetch data from a database or API
        List<Student> allStudents = new ArrayList<>();
        allStudents.add(new Student(101, "Alice Smith", 5, "Late for class multiple times."));
        allStudents.add(new Student(102, "Bob Johnson", 12, "Frequent absences, parent contacted."));
        allStudents.add(new Student(103, "Charlie Brown", 2, "")); // No significant notes
        allStudents.add(new Student(104, "Diana Miller", 8, "Missed several assignments."));
        allStudents.add(new Student(105, "Eve Davis", 15, "Very poor attendance, truancy warning issued."));
        allStudents.add(new Student(106, "Frank White", 3, "Partially missed one exam due to illness."));
        allStudents.add(new Student(107, "Grace Black", 10, "Required counseling, frequent tardiness."));
        allStudents.add(new Student(108, "Henry Green", 6, "Suspension for disciplinary issue."));
        return allStudents;
    }
    /**
     * Filters a list of students based on predetermined thresholds for absences and notes.
     * Students are included if their absences are strictly greater than the absenceThreshold
     * AND they have non-empty notes AND the length of the note string is strictly greater than
     * the noteThreshold (representing detailed notes).
     *
     * @param allStudents The complete list of students to filter.
     * @param absenceThreshold The minimum number of absences (exclusive) for a student to be included.
     * @param noteThreshold The minimum length of notes (exclusive) for a student to be included. If a student has
     *                      an empty note string, they are not included based on notes, regardless of this threshold.
     * @return A list of students who meet both absence and note criteria.
     */
    public List<Student> filterStudentsByThreshold(List<Student> allStudents, int absenceThreshold, int noteThreshold) {
        if (allStudents == null) {
            return new ArrayList<>(); // Return an empty list if input is null
        }
        // Filters students where:
        // 1. absences are greater than absenceThreshold
        // 2. notes string is not empty AND its length is greater than noteThreshold
        return allStudents.stream()
                .filter(student -> student.getAbsences() > absenceThreshold)
                .filter(student -> student.getNotes() != null && !student.getNotes().trim().isEmpty() && student.getNotes().length() > noteThreshold)
                .collect(Collectors.toList());
    }
}