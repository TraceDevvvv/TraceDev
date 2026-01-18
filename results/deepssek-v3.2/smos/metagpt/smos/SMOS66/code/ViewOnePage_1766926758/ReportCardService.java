import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service class that provides business logic for retrieving academic years, classes, students, semesters, and report cards.
 * Includes dummy data generation for simulation.
 */
public class ReportCardService {
    private final List<AcademicYear> academicYears;
    private final List<Class> classes;
    private final List<Student> students;
    private final List<Semester> semesters;
    private final List<ReportCard> reportCards;

    /**
     * Constructor that initializes the service with dummy data.
     */
    public ReportCardService() {
        this.academicYears = new ArrayList<>();
        this.classes = new ArrayList<>();
        this.students = new ArrayList<>();
        this.semesters = new ArrayList<>();
        this.reportCards = new ArrayList<>();
        
        initializeDummyData();
    }

    /**
     * Initializes the dummy data for the simulation.
     * Creates academic years, classes, students, semesters, and report cards.
     */
    private void initializeDummyData() {
        // Create academic years
        AcademicYear year2023_2024 = new AcademicYear("AY2023", Year.of(2023), Year.of(2024));
        AcademicYear year2024_2025 = new AcademicYear("AY2024", Year.of(2024), Year.of(2025));
        academicYears.add(year2023_2024);
        academicYears.add(year2024_2025);

        // Create semesters for each academic year
        Semester sem1_2023 = new Semester("S1_2023", "First Quadrimestre", year2023_2024, 1);
        Semester sem2_2023 = new Semester("S2_2023", "Second Quadrimestre", year2023_2024, 2);
        Semester sem1_2024 = new Semester("S1_2024", "First Quadrimestre", year2024_2025, 1);
        Semester sem2_2024 = new Semester("S2_2024", "Second Quadrimestre", year2024_2025, 2);
        semesters.add(sem1_2023);
        semesters.add(sem2_2023);
        semesters.add(sem1_2024);
        semesters.add(sem2_2024);

        // Create classes for each academic year
        Class class10A_2023 = new Class("C10A_2023", "10A", year2023_2024);
        Class class10B_2023 = new Class("C10B_2023", "10B", year2023_2024);
        Class class11A_2024 = new Class("C11A_2024", "11A", year2024_2025);
        Class class11B_2024 = new Class("C11B_2024", "11B", year2024_2025);
        classes.add(class10A_2023);
        classes.add(class10B_2023);
        classes.add(class11A_2024);
        classes.add(class11B_2024);

        // Create students for each class
        Student student1 = new Student("S1001", "John", "Doe", "10A", year2023_2024);
        Student student2 = new Student("S1002", "Jane", "Smith", "10A", year2023_2024);
        Student student3 = new Student("S1003", "Robert", "Johnson", "10B", year2023_2024);
        Student student4 = new Student("S1004", "Emily", "Williams", "10B", year2023_2024);
        Student student5 = new Student("S1005", "Michael", "Brown", "11A", year2024_2025);
        Student student6 = new Student("S1006", "Sarah", "Davis", "11A", year2024_2025);
        Student student7 = new Student("S1007", "David", "Miller", "11B", year2024_2025);
        Student student8 = new Student("S1008", "Lisa", "Wilson", "11B", year2024_2025);
        
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        students.add(student6);
        students.add(student7);
        students.add(student8);

        // Add students to their respective classes
        class10A_2023.addStudent(student1);
        class10A_2023.addStudent(student2);
        class10B_2023.addStudent(student3);
        class10B_2023.addStudent(student4);
        class11A_2024.addStudent(student5);
        class11A_2024.addStudent(student6);
        class11B_2024.addStudent(student7);
        class11B_2024.addStudent(student8);

        // Create report cards for students
        // John Doe's report cards
        Map<String, Double> johnGrades1 = new HashMap<>();
        johnGrades1.put("Math", 18.5);
        johnGrades1.put("Physics", 16.0);
        johnGrades1.put("Chemistry", 17.0);
        johnGrades1.put("English", 15.5);
        reportCards.add(new ReportCard(student1, sem1_2023, johnGrades1, "Good performance, needs improvement in English."));

        Map<String, Double> johnGrades2 = new HashMap<>();
        johnGrades2.put("Math", 19.0);
        johnGrades2.put("Physics", 17.5);
        johnGrades2.put("Chemistry", 18.0);
        johnGrades2.put("English", 16.0);
        reportCards.add(new ReportCard(student1, sem2_2023, johnGrades2, "Excellent improvement, keep up the good work!"));

        // Jane Smith's report cards
        Map<String, Double> janeGrades1 = new HashMap<>();
        janeGrades1.put("Math", 14.0);
        janeGrades1.put("Physics", 15.5);
        janeGrades1.put("Chemistry", 13.5);
        janeGrades1.put("English", 18.0);
        reportCards.add(new ReportCard(student2, sem1_2023, janeGrades1, "Strong in languages, needs to work on science subjects."));

        // Robert Johnson's report cards
        Map<String, Double> robertGrades1 = new HashMap<>();
        robertGrades1.put("Math", 12.5);
        robertGrades1.put("Physics", 11.0);
        robertGrades1.put("Chemistry", 13.0);
        robertGrades1.put("English", 14.5);
        reportCards.add(new ReportCard(student3, sem1_2023, robertGrades1, "Needs to focus more on studies."));

        // Emily Williams' report cards
        Map<String, Double> emilyGrades1 = new HashMap<>();
        emilyGrades1.put("Math", 20.0);
        emilyGrades1.put("Physics", 19.5);
        emilyGrades1.put("Chemistry", 20.0);
        emilyGrades1.put("English", 18.5);
        reportCards.add(new ReportCard(student4, sem1_2023, emilyGrades1, "Outstanding performance in all subjects!"));

        // Michael Brown's report cards (2024-2025)
        Map<String, Double> michaelGrades1 = new HashMap<>();
        michaelGrades1.put("Math", 16.5);
        michaelGrades1.put("Physics", 17.0);
        michaelGrades1.put("Chemistry", 15.5);
        michaelGrades1.put("English", 14.0);
        reportCards.add(new ReportCard(student5, sem1_2024, michaelGrades1, "Good start to the academic year."));

        // Sarah Davis' report cards
        Map<String, Double> sarahGrades1 = new HashMap<>();
        sarahGrades1.put("Math", 13.0);
        sarahGrades1.put("Physics", 12.5);
        sarahGrades1.put("Chemistry", 14.0);
        sarahGrades1.put("English", 16.5);
        reportCards.add(new ReportCard(student6, sem1_2024, sarahGrades1, "Showing improvement in English."));

        // David Miller's report cards
        Map<String, Double> davidGrades1 = new HashMap<>();
        davidGrades1.put("Math", 18.0);
        davidGrades1.put("Physics", 16.5);
        davidGrades1.put("Chemistry", 17.5);
        davidGrades1.put("English", 15.0);
        reportCards.add(new ReportCard(student7, sem1_2024, davidGrades1, "Consistent performance across subjects."));

        // Lisa Wilson's report cards
        Map<String, Double> lisaGrades1 = new HashMap<>();
        lisaGrades1.put("Math", 17.5);
        lisaGrades1.put("Physics", 18.0);
        lisaGrades1.put("Chemistry", 16.5);
        lisaGrades1.put("English", 19.0);
        reportCards.add(new ReportCard(student8, sem1_2024, lisaGrades1, "Excellent in English, good overall performance."));
    }

    /**
     * Gets all available academic years.
     * @return List of AcademicYear objects.
     */
    public List<AcademicYear> getAvailableAcademicYears() {
        return new ArrayList<>(academicYears); // Return defensive copy
    }

    /**
     * Gets classes for a specific academic year.
     * @param academicYear The academic year to filter classes by.
     * @return List of Class objects for the given academic year.
     * @throws NullPointerException if academicYear is null.
     */
    public List<Class> getClassesByAcademicYear(AcademicYear academicYear) {
        if (academicYear == null) {
            throw new NullPointerException("Academic year cannot be null");
        }
        
        List<Class> result = new ArrayList<>();
        for (Class c : classes) {
            if (c.getAcademicYear().equals(academicYear)) {
                result.add(c);
            }
        }
        return result;
    }

    /**
     * Gets students in a specific class.
     * @param classObj The class to get students from.
     * @return List of Student objects in the given class.
     * @throws NullPointerException if classObj is null.
     */
    public List<Student> getStudentsByClass(Class classObj) {
        if (classObj == null) {
            throw new NullPointerException("Class cannot be null");
        }
        
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getClassName().equals(classObj.getName()) && 
                s.getAcademicYear().equals(classObj.getAcademicYear())) {
                result.add(s);
            }
        }
        return result;
    }

    /**
     * Gets all available semesters.
     * @return List of Semester objects.
     */
    public List<Semester> getAvailableSemesters() {
        return new ArrayList<>(semesters); // Return defensive copy
    }

    /**
     * Gets a report card for a specific student and semester.
     * @param student The student to get the report card for.
     * @param semester The semester to get the report card for.
     * @return ReportCard object for the given student and semester, or null if not found.
     * @throws NullPointerException if student or semester is null.
     */
    public ReportCard getReportCard(Student student, Semester semester) {
        if (student == null) {
            throw new NullPointerException("Student cannot be null");
        }
        if (semester == null) {
            throw new NullPointerException("Semester cannot be null");
        }
        
        for (ReportCard rc : reportCards) {
            if (rc.getStudent().equals(student) && rc.getSemester().equals(semester)) {
                return rc;
            }
        }
        return null; // No report card found for this student and semester
    }

    /**
     * Gets all report cards for a specific student.
     * @param student The student to get report cards for.
     * @return List of ReportCard objects for the given student.
     * @throws NullPointerException if student is null.
     */
    public List<ReportCard> getReportCardsByStudent(Student student) {
        if (student == null) {
            throw new NullPointerException("Student cannot be null");
        }
        
        List<ReportCard> result = new ArrayList<>();
        for (ReportCard rc : reportCards) {
            if (rc.getStudent().equals(student)) {
                result.add(rc);
            }
        }
        return result;
    }

    /**
     * Gets all report cards for a specific class and semester.
     * @param classObj The class to filter by.
     * @param semester The semester to filter by.
     * @return List of ReportCard objects for the given class and semester.
     * @throws NullPointerException if classObj or semester is null.
     */
    public List<ReportCard> getReportCardsByClassAndSemester(Class classObj, Semester semester) {
        if (classObj == null) {
            throw new NullPointerException("Class cannot be null");
        }
        if (semester == null) {
            throw new NullPointerException("Semester cannot be null");
        }
        
        List<ReportCard> result = new ArrayList<>();
        for (ReportCard rc : reportCards) {
            Student s = rc.getStudent();
            if (s.getClassName().equals(classObj.getName()) && 
                s.getAcademicYear().equals(classObj.getAcademicYear()) &&
                rc.getSemester().equals(semester)) {
                result.add(rc);
            }
        }
        return result;
    }
}