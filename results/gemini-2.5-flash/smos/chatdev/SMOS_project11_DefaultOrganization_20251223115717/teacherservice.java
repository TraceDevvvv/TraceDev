'''
This service class acts as a mock database/data repository for teacher,
academic year, class, teaching, and their assignments.
In a real application, this would interact with a database.
'''
import java.util.*;
import java.util.stream.Collectors;
public class TeacherService {
    // Mock Data Storage
    private List<AcademicYear> academicYears = new ArrayList<>();
    private List<ClassEntity> classes = new ArrayList<>();
    private List<Teaching> teachings = new ArrayList<>();
    // Stores assignments: Teacher ID -> (AcademicYear ID -> Set of Teaching IDs)
    private Map<Integer, Map<Integer, Set<Integer>>> teacherAssignments = new HashMap<>();
    /**
     * Initializes mock data for academic years, classes, and teachings.
     */
    public TeacherService() {
        // Populate Academic Years
        academicYears.add(new AcademicYear(2023, "2023-2024"));
        academicYears.add(new AcademicYear(2022, "2022-2023"));
        academicYears.add(new AcademicYear(2021, "2021-2022"));
        // Populate Classes for 2023-2024 (ID:2023)
        classes.add(new ClassEntity(101, 2023, "Grade 1A"));
        classes.add(new ClassEntity(102, 2023, "Grade 1B"));
        classes.add(new ClassEntity(201, 2023, "Grade 2A"));
        classes.add(new ClassEntity(301, 2023, "Grade 3A"));
        // Populate Classes for 2022-2023 (ID:2022)
        classes.add(new ClassEntity(103, 2022, "Grade 1C"));
        classes.add(new ClassEntity(202, 2022, "Grade 2B"));
        // Populate Teachings for Class 101 (Grade 1A)
        teachings.add(new Teaching(1, 101, "Mathematics (G1A)"));
        teachings.add(new Teaching(2, 101, "Reading (G1A)"));
        teachings.add(new Teaching(3, 101, "Art (G1A)"));
        teachings.add(new Teaching(12, 101, "Science (G1A)")); // Added for more variety
        // Populate Teachings for Class 102 (Grade 1B)
        teachings.add(new Teaching(4, 102, "Mathematics (G1B)"));
        teachings.add(new Teaching(5, 102, "Reading (G1B)"));
        teachings.add(new Teaching(6, 102, "Music (G1B)"));
        // Populate Teachings for Class 201 (Grade 2A)
        teachings.add(new Teaching(7, 201, "Science (G2A)"));
        teachings.add(new Teaching(8, 201, "History (G2A)"));
        teachings.add(new Teaching(9, 201, "Physical Ed (G2A)"));
        // Populate Teachings for Class 301 (Grade 3A)
        teachings.add(new Teaching(10, 301, "Advanced Math (G3A)"));
        teachings.add(new Teaching(11, 301, "Literature (G3A)"));
    }
    /**
     * Retrieves all available academic years.
     * @return A list of AcademicYear objects.
     */
    public List<AcademicYear> getAllAcademicYears() {
        return new ArrayList<>(academicYears);
    }
    /**
     * Retrieves classes for a given academic year.
     * @param academicYearId The ID of the academic year.
     * @return A list of ClassEntity objects belonging to the specified year.
     */
    public List<ClassEntity> getClassesByAcademicYear(int academicYearId) {
        return classes.stream()
                .filter(c -> c.getAcademicYearId() == academicYearId)
                .collect(Collectors.toList());
    }
    /**
     * Retrieves all teachings associated with a given class.
     * @param classId The ID of the class.
     * @return A list of Teaching objects belonging to the specified class.
     */
    public List<Teaching> getTeachingsByClass(int classId) {
        return teachings.stream()
                .filter(t -> t.getClassId() == classId)
                .collect(Collectors.toList());
    }
    /**
     * Retrieves the teachings already assigned to a specific teacher for a given academic year.
     * If no assignments exist for the teacher or year, an empty set is returned (and created).
     * @param teacherId The ID of the teacher.
     * @param academicYearId The ID of the academic year.
     * @return A set of Teaching IDs currently assigned to the teacher for that year.
     */
    public Set<Integer> getAssignedTeachingsForTeacher(int teacherId, int academicYearId) {
        return teacherAssignments.computeIfAbsent(teacherId, k -> new HashMap<>())
                .computeIfAbsent(academicYearId, k -> new HashSet<>());
    }
    /**
     * Assigns a teaching to a teacher for a specific academic year.
     * @param teacherId The ID of the teacher.
     * @param academicYearId The ID of the academic year.
     * @param teachingId The ID of the teaching to assign.
     * @return true if the teaching was successfully assigned, false if it was already assigned.
     */
    public boolean assignTeachingToTeacher(int teacherId, int academicYearId, int teachingId) {
        Set<Integer> assignments = teacherAssignments
                .computeIfAbsent(teacherId, k -> new HashMap<>())
                .computeIfAbsent(academicYearId, k -> new HashSet<>());
        boolean added = assignments.add(teachingId);
        if (added) {
            System.out.println("Assigned Teaching ID " + teachingId + " to Teacher " + teacherId + " for Year " + academicYearId);
        }
        return added;
    }
    /**
     * Removes a teaching from a teacher for a specific academic year.
     * @param teacherId The ID of the teacher.
     * @param academicYearId The ID of the academic year.
     * @param teachingId The ID of the teaching to remove.
     * @return true if the teaching was successfully removed, false if it was not assigned.
     */
    public boolean removeTeachingFromTeacher(int teacherId, int academicYearId, int teachingId) {
        Map<Integer, Set<Integer>> yearAssignments = teacherAssignments.get(teacherId);
        if (yearAssignments != null) {
            Set<Integer> assignments = yearAssignments.get(academicYearId);
            if (assignments != null) {
                boolean removed = assignments.remove(teachingId);
                if (removed) {
                    System.out.println("Removed Teaching ID " + teachingId + " from Teacher " + teacherId + " for Year " + academicYearId);
                }
                return removed;
            }
        }
        return false;
    }
    /**
     * Retrieves a Teaching object by its ID.
     * @param teachingId The ID of the teaching.
     * @return The Teaching object, or null if not found.
     */
    public Teaching getTeachingById(int teachingId) {
        return teachings.stream()
                .filter(t -> t.getId() == teachingId)
                .findFirst()
                .orElse(null);
    }
}