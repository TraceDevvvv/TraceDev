package com.smos.service;

import com.smos.model.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for managing teacher-related operations, such as retrieving academic years,
 * classes taught by a teacher, students in a class, and student report cards.
 * This class simulates data access using in-memory collections.
 */
public class TeacherService {

    // Mock database for academic years a teacher teaches in
    // Key: Teacher, Value: Set of AcademicYears
    private final Map<Teacher, Set<AcademicYear>> teacherAcademicYears;

    // Mock database for classes a teacher teaches in a specific academic year
    // Key: AcademicYear, Value: Map (Key: Teacher, Value: Set of Classrooms)
    private final Map<AcademicYear, Map<Teacher, Set<Classroom>>> academicYearTeacherClasses;

    // Mock database for report cards
    // Key: Student, Value: Map (Key: AcademicYear, Value: Map (Key: Quadrimestre, Value: ReportCard))
    private final Map<Student, Map<AcademicYear, Map<Integer, ReportCard>>> studentReportCards;

    /**
     * Constructs a new TeacherService and initializes it with mock data.
     */
    public TeacherService() {
        this.teacherAcademicYears = new HashMap<>();
        this.academicYearTeacherClasses = new HashMap<>();
        this.studentReportCards = new HashMap<>();
        initializeMockData();
    }

    /**
     * Initializes mock data for teachers, academic years, classes, students, and report cards.
     * This simulates a backend data store.
     */
    private void initializeMockData() {
        // 1. Create Teachers
        Teacher profRossi = new Teacher("T001", "Prof. Rossi");
        Teacher profBianchi = new Teacher("T002", "Prof. Bianchi");
        Teacher profVerdi = new Teacher("T003", "Prof. Verdi");

        // 2. Create Academic Years
        AcademicYear year2022_2023 = new AcademicYear("2022-2023");
        AcademicYear year2023_2024 = new AcademicYear("2023-2024");
        AcademicYear year2024_2025 = new AcademicYear("2024-2025");

        // 3. Create Students
        Student student1 = new Student("S001", "Alice Smith");
        Student student2 = new Student("S002", "Bob Johnson");
        Student student3 = new Student("S003", "Charlie Brown");
        Student student4 = new Student("S004", "Diana Prince");
        Student student5 = new Student("S005", "Eve Adams");
        Student student6 = new Student("S006", "Frank White");

        // 4. Create Classrooms and add students
        Classroom class1A = new Classroom("1A");
        class1A.addStudent(student1);
        class1A.addStudent(student2);

        Classroom class2B = new Classroom("2B");
        class2B.addStudent(student3);
        class2B.addStudent(student4);

        Classroom class3C = new Classroom("3C");
        class3C.addStudent(student5);
        class3C.addStudent(student6);

        Classroom class1B = new Classroom("1B"); // Another class for a different teacher/year
        class1B.addStudent(student4);
        class1B.addStudent(student5);

        // 5. Populate teacherAcademicYears
        teacherAcademicYears.computeIfAbsent(profRossi, k -> new HashSet<>()).add(year2022_2023);
        teacherAcademicYears.computeIfAbsent(profRossi, k -> new HashSet<>()).add(year2023_2024);
        teacherAcademicYears.computeIfAbsent(profBianchi, k -> new HashSet<>()).add(year2023_2024);
        teacherAcademicYears.computeIfAbsent(profVerdi, k -> new HashSet<>()).add(year2024_2025);

        // 6. Populate academicYearTeacherClasses
        // Prof. Rossi in 2022-2023 teaches 1A
        academicYearTeacherClasses.computeIfAbsent(year2022_2023, k -> new HashMap<>())
                .computeIfAbsent(profRossi, k -> new HashSet<>()).add(class1A);

        // Prof. Rossi in 2023-2024 teaches 2B
        academicYearTeacherClasses.computeIfAbsent(year2023_2024, k -> new HashMap<>())
                .computeIfAbsent(profRossi, k -> new HashSet<>()).add(class2B);

        // Prof. Bianchi in 2023-2024 teaches 1A and 1B
        academicYearTeacherClasses.computeIfAbsent(year2023_2024, k -> new HashMap<>())
                .computeIfAbsent(profBianchi, k -> new HashSet<>()).add(class1A);
        academicYearTeacherClasses.computeIfAbsent(year2023_2024, k -> new HashMap<>())
                .computeIfAbsent(profBianchi, k -> new HashSet<>()).add(class1B);

        // Prof. Verdi in 2024-2025 teaches 3C
        academicYearTeacherClasses.computeIfAbsent(year2024_2025, k -> new HashMap<>())
                .computeIfAbsent(profVerdi, k -> new HashSet<>()).add(class3C);


        // 7. Populate studentReportCards
        // Student 1 (Alice Smith) - 2022-2023, Quad 1
        ReportCard rc1_q1 = new ReportCard(student1, year2022_2023, 1);
        rc1_q1.addGrade(new Grade("Math", 7.5));
        rc1_q1.addGrade(new Grade("Science", 8.0));
        rc1_q1.addGrade(new Grade("History", 6.5));
        studentReportCards.computeIfAbsent(student1, k -> new HashMap<>())
                .computeIfAbsent(year2022_2023, k -> new HashMap<>())
                .put(1, rc1_q1);

        // Student 1 (Alice Smith) - 2022-2023, Quad 2
        ReportCard rc1_q2 = new ReportCard(student1, year2022_2023, 2);
        rc1_q2.addGrade(new Grade("Math", 7.0));
        rc1_q2.addGrade(new Grade("Science", 8.5));
        rc1_q2.addGrade(new Grade("History", 7.0));
        studentReportCards.computeIfAbsent(student1, k -> new HashMap<>())
                .computeIfAbsent(year2022_2023, k -> new HashMap<>())
                .put(2, rc1_q2);

        // Student 3 (Charlie Brown) - 2023-2024, Quad 1
        ReportCard rc3_q1 = new ReportCard(student3, year2023_2024, 1);
        rc3_q1.addGrade(new Grade("Math", 6.0));
        rc3_q1.addGrade(new Grade("Physics", 7.0));
        studentReportCards.computeIfAbsent(student3, k -> new HashMap<>())
                .computeIfAbsent(year2023_2024, k -> new HashMap<>())
                .put(1, rc3_q1);

        // Student 5 (Eve Adams) - 2024-2025, Quad 1
        ReportCard rc5_q1 = new ReportCard(student5, year2024_2025, 1);
        rc5_q1.addGrade(new Grade("Art", 9.0));
        rc5_q1.addGrade(new Grade("Music", 8.5));
        studentReportCards.computeIfAbsent(student5, k -> new HashMap<>())
                .computeIfAbsent(year2024_2025, k -> new HashMap<>())
                .put(1, rc5_q1);
    }

    /**
     * Retrieves a list of academic years in which the given teacher teaches at least one class.
     *
     * @param teacher The teacher for whom to retrieve academic years.
     * @return An unmodifiable list of AcademicYear objects, or an empty list if the teacher is null
     *         or teaches in no academic years.
     */
    public List<AcademicYear> getAcademicYearsForTeacher(Teacher teacher) {
        if (teacher == null) {
            System.err.println("Error: Teacher cannot be null when requesting academic years.");
            return Collections.emptyList();
        }
        Set<AcademicYear> years = teacherAcademicYears.get(teacher);
        return years != null ? new ArrayList<>(years) : Collections.emptyList();
    }

    /**
     * Retrieves a list of classrooms associated with a specific teacher and academic year.
     *
     * @param teacher The teacher for whom to retrieve classes.
     * @param academicYear The academic year of interest.
     * @return An unmodifiable list of Classroom objects, or an empty list if the teacher or academic year is null,
     *         or no classes are found for the given criteria.
     */
    public List<Classroom> getClassesForTeacherAndAcademicYear(Teacher teacher, AcademicYear academicYear) {
        if (teacher == null || academicYear == null) {
            System.err.println("Error: Teacher and AcademicYear cannot be null when requesting classes.");
            return Collections.emptyList();
        }
        Map<Teacher, Set<Classroom>> teacherClassesInYear = academicYearTeacherClasses.get(academicYear);
        if (teacherClassesInYear != null) {
            Set<Classroom> classes = teacherClassesInYear.get(teacher);
            return classes != null ? new ArrayList<>(classes) : Collections.emptyList();
        }
        return Collections.emptyList();
    }

    /**
     * Retrieves a list of students enrolled in a specific classroom.
     *
     * @param classroom The classroom from which to retrieve students.
     * @return An unmodifiable list of Student objects, or an empty list if the classroom is null
     *         or contains no students.
     */
    public List<Student> getStudentsInClassroom(Classroom classroom) {
        if (classroom == null) {
            System.err.println("Error: Classroom cannot be null when requesting students.");
            return Collections.emptyList();
        }
        return classroom.getStudents(); // Classroom object already holds its students
    }

    /**
     * Retrieves the report card for a specific student, academic year, and quadrimestre.
     *
     * @param student The student whose report card is to be retrieved.
     * @param academicYear The academic year of the report card.
     * @param quadrimestre The quadrimestre number (1 or 2).
     * @return The ReportCard object if found, or null if the student, academic year, or quadrimestre is invalid,
     *         or no report card exists for the given criteria.
     */
    public ReportCard getReportCardForStudent(Student student, AcademicYear academicYear, int quadrimestre) {
        if (student == null || academicYear == null || (quadrimestre != 1 && quadrimestre != 2)) {
            System.err.println("Error: Invalid input for retrieving report card. Student, AcademicYear cannot be null, and Quadrimestre must be 1 or 2.");
            return null;
        }

        Map<AcademicYear, Map<Integer, ReportCard>> studentYears = studentReportCards.get(student);
        if (studentYears != null) {
            Map<Integer, ReportCard> yearQuadrimestres = studentYears.get(academicYear);
            if (yearQuadrimestres != null) {
                return yearQuadrimestres.get(quadrimestre);
            }
        }
        return null; // Report card not found
    }

    /**
     * Authenticates a teacher. In a real system, this would involve checking credentials.
     * For this simulation, it just checks if the teacher exists in our mock data.
     *
     * @param teacherId The ID of the teacher.
     * @param teacherName The name of the teacher.
     * @return A Teacher object if authenticated, otherwise null.
     */
    public Teacher authenticateTeacher(String teacherId, String teacherName) {
        // In a real application, this would involve a secure authentication mechanism.
        // For this mock, we'll just find a teacher by ID and name.
        for (Teacher teacher : teacherAcademicYears.keySet()) {
            if (teacher.getId().equals(teacherId) && teacher.getName().equals(teacherName)) {
                return teacher;
            }
        }
        // Also check teachers who might not have academic years assigned yet but exist
        // This part is simplified for the mock data, assuming all teachers have some academic years.
        // A more robust mock would iterate through all possible teachers.
        // For now, we rely on teacherAcademicYears.keySet() containing all known teachers.
        return null;
    }
}