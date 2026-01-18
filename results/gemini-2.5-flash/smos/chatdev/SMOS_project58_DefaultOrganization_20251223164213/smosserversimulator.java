import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
/*
 * Simulates a backend server (SMOS Server) for fetching academic data.
 * This class provides mock data for academic years, classes, students, and report cards,
 * enabling the front-end application to function without a real database.
 * It now also simulates teacher-specific data access.
 */
class SMOSServerSimulator {
    // Mock data structures
    private final List<AcademicYear> allAcademicYears;
    private final Map<AcademicYear, List<Classroom>> allClassesByYear;
    private final Map<Classroom, List<Student>> studentsByClass;
    private final Map<String, Map<String, ReportCard>> reportCardsByStudentAndQuad; // StudentId -> Quadrimestre -> ReportCard
    // New structures to simulate teacher-specific data access
    private final Map<Teacher, Set<AcademicYear>> teacherToAcademicYears;
    private final Map<Teacher, Map<AcademicYear, Set<Classroom>>> teacherToClassesByYear;
    private Teacher currentTeacher; // Represents the "logged-in" teacher
    /**
     * Initializes the simulator with predefined mock data.
     * A mock teacher "Professor Rossi" is pre-set as the logged-in teacher
     * and assigned all generated academic data for demonstration purposes.
     */
    public SMOSServerSimulator() {
        allAcademicYears = new ArrayList<>();
        allClassesByYear = new HashMap<>(); // Global list of classes by year
        studentsByClass = new HashMap<>();
        reportCardsByStudentAndQuad = new HashMap<>();
        teacherToAcademicYears = new HashMap<>();
        teacherToClassesByYear = new HashMap<>();
        initializeData();
    }
    /**
     * Populates the mock data structures with sample academic information.
     * This method creates academic years, classrooms, students, their report cards,
     * and associates them with a mock teacher.
     */
    private void initializeData() {
        // --- Academic Years ---
        AcademicYear year2022_2023 = new AcademicYear("2022-2023");
        AcademicYear year2023_2024 = new AcademicYear("2023-2024");
        allAcademicYears.addAll(Arrays.asList(year2022_2023, year2023_2024));
        // --- Classrooms ---
        Classroom class5A_22_23 = new Classroom("5A", year2022_2023);
        Classroom class5B_22_23 = new Classroom("5B", year2022_2023);
        Classroom class4C_22_23 = new Classroom("4C", year2022_2023);
        Classroom class5A_23_24 = new Classroom("5A", year2023_2024);
        Classroom class3B_23_24 = new Classroom("3B", year2023_2024);
        Classroom class4C_23_24 = new Classroom("4C", year2023_2024);
        // Globally available classes by year (not teacher-specific)
        allClassesByYear.put(year2022_2023, Arrays.asList(class5A_22_23, class5B_22_23, class4C_22_23));
        allClassesByYear.put(year2023_2024, Arrays.asList(class5A_23_24, class3B_23_24, class4C_23_24));
        // --- Students for each class ---
        Student s1 = new Student("S001", "Alice Smith");
        Student s2 = new Student("S002", "Bob Johnson");
        Student s3 = new Student("S003", "Charlie Brown");
        Student s4 = new Student("S004", "Diana Prince");
        Student s5 = new Student("S005", "Eve Adams");
        Student s6 = new Student("S006", "Frank White");
        studentsByClass.put(class5A_22_23, Arrays.asList(s1, s2));
        studentsByClass.put(class5B_22_23, Arrays.asList(s3));
        studentsByClass.put(class4C_22_23, Arrays.asList(s4));
        studentsByClass.put(class5A_23_24, Arrays.asList(s1, s2)); // Alice & Bob moved to 5A 23-24
        studentsByClass.put(class3B_23_24, Arrays.asList(s5));
        studentsByClass.put(class4C_23_24, Arrays.asList(s6));
        // --- Report Cards ---
        // s1: Alice Smith
        Map<String, String> grades_s1_Q1_22_23 = new HashMap<>();
        grades_s1_Q1_22_23.put("Math", "8"); grades_s1_Q1_22_23.put("Science", "7"); grades_s1_Q1_22_23.put("History", "9");
        addReportCard(s1, year2022_2023.getYear(), class5A_22_23.getName(), "1st Quadrimestre", grades_s1_Q1_22_23, "Good progress.");
        Map<String, String> grades_s1_Q2_22_23 = new HashMap<>();
        grades_s1_Q2_22_23.put("Math", "9"); grades_s1_Q2_22_23.put("Science", "8"); grades_s1_Q2_22_23.put("History", "8");
        addReportCard(s1, year2022_2023.getYear(), class5A_22_23.getName(), "2nd Quadrimestre", grades_s1_Q2_22_23, "Excellent work.");
        Map<String, String> grades_s1_Q1_23_24 = new HashMap<>();
        grades_s1_Q1_23_24.put("Math", "7"); grades_s1_Q1_23_24.put("Physics", "6"); grades_s1_Q1_23_24.put("Chemistry", "8");
        addReportCard(s1, year2023_2024.getYear(), class5A_23_24.getName(), "1st Quadrimestre", grades_s1_Q1_23_24, "Needs to focus more on Physics.");
        // s2: Bob Johnson
        Map<String, String> grades_s2_Q1_22_23 = new HashMap<>();
        grades_s2_Q1_22_23.put("Math", "6"); grades_s2_Q1_22_23.put("Science", "5"); grades_s2_Q1_22_23.put("History", "7");
        addReportCard(s2, year2022_2023.getYear(), class5A_22_23.getName(), "1st Quadrimestre", grades_s2_Q1_22_23, "Attention required in Science.");
        // s3: Charlie Brown
        Map<String, String> grades_s3_Q1_22_23 = new HashMap<>();
        grades_s3_Q1_22_23.put("Math", "7"); grades_s3_Q1_22_23.put("Art", "9");
        addReportCard(s3, year2022_2023.getYear(), class5B_22_23.getName(), "1st Quadrimestre", grades_s3_Q1_22_23, "Creative and consistent.");
        // s4: Diana Prince
        Map<String, String> grades_s4_Q1_22_23 = new HashMap<>();
        grades_s4_Q1_22_23.put("PE", "10"); grades_s4_Q1_22_23.put("English", "9");
        addReportCard(s4, year2022_2023.getYear(), class4C_22_23.getName(), "1st Quadrimestre", grades_s4_Q1_22_23, "Outstanding performance.");
        // s5: Eve Adams
        Map<String, String> grades_s5_Q1_23_24 = new HashMap<>();
        grades_s5_Q1_23_24.put("Biology", "8"); grades_s5_Q1_23_24.put("Geography", "7");
        addReportCard(s5, year2023_2024.getYear(), class3B_23_24.getName(), "1st Quadrimestre", grades_s5_Q1_23_24, "Shows keen interest in subjects.");
        // s6: Frank White
        Map<String, String> grades_s6_Q1_23_24 = new HashMap<>();
        grades_s6_Q1_23_24.put("Computer Science", "9"); grades_s6_Q1_23_24.put("Design", "8");
        addReportCard(s6, year2023_2024.getYear(), class4C_23_24.getName(), "1st Quadrimestre", grades_s6_Q1_23_24, "Strong analytical skills.");
        // --- Teacher-specific Data Assignment ---
        // For demonstration, we'll assume "Professor Rossi" is the logged-in teacher (T001)
        currentTeacher = new Teacher("T001", "Professor Rossi");
        // Explicitly define which academic years Professor Rossi teaches in
        teacherToAcademicYears.computeIfAbsent(currentTeacher, k -> new HashSet<>())
                              .addAll(Arrays.asList(year2022_2023, year2023_2024));
        // Explicitly define which classes Professor Rossi teaches in each year to demonstrate filtering
        Map<AcademicYear, Set<Classroom>> teacherClasses = teacherToClassesByYear
            .computeIfAbsent(currentTeacher, k -> new HashMap<>());
        // Assign classes for 2022-2023: Professor Rossi teaches 5A and 4C
        teacherClasses.computeIfAbsent(year2022_2023, k -> new HashSet<>())
                      .addAll(Arrays.asList(class5A_22_23, class4C_22_23));
        // Assign classes for 2023-2024: Professor Rossi teaches only 5A
        teacherClasses.computeIfAbsent(year2023_2024, k -> new HashSet<>())
                      .addAll(Arrays.asList(class5A_23_24));
    }
    /**
     * Helper method to add a report card to the internal mock data.
     *
     * @param student The student for whom the report card is.
     * @param academicYear The academic year of the report.
     * @param className The class name for the report.
     * @param quadrimestre The quadrimestre.
     * @param grades Grades for subjects.
     * @param comments Comments on the report.
     */
    private void addReportCard(Student student, String academicYear, String className,
                               String quadrimestre, Map<String, String> grades, String comments) {
        ReportCard rc = new ReportCard(student.getId(), student.getName(), academicYear, className, quadrimestre, grades, comments);
        reportCardsByStudentAndQuad.computeIfAbsent(student.getId(), k -> new HashMap<>()).put(quadrimestre, rc);
    }
    /**
     * Retrieves the list of academic years in which the currently logged-in teacher teaches.
     * This simulates System Event 1: "View the list of academic years in which at least one class in which the teacher teaches."
     *
     * @return A list of AcademicYear objects relevant to the current teacher.
     *         Returns an empty list if none are found or no teacher is logged in.
     */
    public List<AcademicYear> getAcademicYearsForTeacher() {
        if (currentTeacher == null) {
            return Collections.emptyList();
        }
        // Return a new ArrayList to allow modification without affecting the internal Set
        return new ArrayList<>(teacherToAcademicYears.getOrDefault(currentTeacher, Collections.emptySet()));
    }
    /**
     * Retrieves the list of classes for a given academic year that the currently logged-in teacher teaches.
     * This simulates System Event 3: "View the classes associated with the selected school year."
     *
     * @param year The AcademicYear object selected by the user.
     * @return A list of Classroom objects for the specified year, taught by the current teacher.
     *         Returns an empty list if no classes are found or no teacher is logged in.
     */
    public List<Classroom> getClassesForAcademicYear(AcademicYear year) {
        if (currentTeacher == null) {
            return Collections.emptyList();
        }
        // Return a new ArrayList to allow modification without affecting the internal Set
        return new ArrayList<>(teacherToClassesByYear
                                .getOrDefault(currentTeacher, Collections.emptyMap())
                                .getOrDefault(year, Collections.emptySet()));
    }
    /**
     * Retrieves the list of students belonging to a specific classroom.
     * This simulates System Event 5: "Displays the list of class students chosen by the user."
     * Note: This method implicitly assumes the selected classroom is one the teacher has access to.
     *
     * @param classroom The Classroom object selected by the user.
     * @return A list of Student objects for the specified classroom. Returns an empty list if no students are found.
     */
    public List<Student> getStudentsForClass(Classroom classroom) {
        return studentsByClass.getOrDefault(classroom, Collections.emptyList());
    }
    /**
     * Retrieves a student's report card for a specific quadrimestre.
     * This simulates System Event 7: "Displays the chosen student report referred to the specified quarter."
     * Note: This method implicitly assumes the selected student is from a class the teacher has access to.
     *
     * @param student The Student object selected by the user.
     * @param quadrimestre The quadrimestre string (e.g., "1st Quadrimestre").
     * @return An Optional containing the ReportCard object if found, otherwise an empty Optional.
     */
    public Optional<ReportCard> getReportCard(Student student, String quadrimestre) {
        Map<String, ReportCard> studentReports = reportCardsByStudentAndQuad.get(student.getId());
        if (studentReports != null) {
            return Optional.ofNullable(studentReports.get(quadrimestre));
        }
        return Optional.empty();
    }
}