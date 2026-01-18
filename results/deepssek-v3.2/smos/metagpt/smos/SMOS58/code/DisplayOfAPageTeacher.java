import java.util.*;

/**
 * Main class for the DisplayOfAPageTeacher use case.
 * This program simulates a teacher viewing report cards for students.
 * It follows the sequence: select academic year -> select class -> select student -> display report card.
 */
public class DisplayOfAPageTeacher {
    
    /**
     * Represents an Academic Year with a name (e.g., "2023-2024").
     */
    static class AcademicYear {
        private String year;
        private List<Class> classes;

        public AcademicYear(String year) {
            this.year = year;
            this.classes = new ArrayList<>();
        }

        public String getYear() {
            return year;
        }

        public List<Class> getClasses() {
            return classes;
        }

        public void addClass(Class c) {
            classes.add(c);
        }

        @Override
        public String toString() {
            return year;
        }
    }

    /**
     * Represents a Class (e.g., "Math 101") belonging to an academic year.
     */
    static class Class {
        private String className;
        private List<Student> students;

        public Class(String className) {
            this.className = className;
            this.students = new ArrayList<>();
        }

        public String getClassName() {
            return className;
        }

        public List<Student> getStudents() {
            return students;
        }

        public void addStudent(Student s) {
            students.add(s);
        }

        @Override
        public String toString() {
            return className;
        }
    }

    /**
     * Represents a Student with a name and a list of report cards per quadrimestre.
     */
    static class Student {
        private String name;
        private Map<Integer, ReportCard> reportCards; // key: quadrimestre (1,2,3)

        public Student(String name) {
            this.name = name;
            this.reportCards = new HashMap<>();
        }

        public String getName() {
            return name;
        }

        public void addReportCard(int quadrimestre, ReportCard rc) {
            reportCards.put(quadrimestre, rc);
        }

        public ReportCard getReportCard(int quadrimestre) {
            return reportCards.get(quadrimestre);
        }

        @Override
        public String toString() {
            return name;
        }
    }

    /**
     * Represents a Report Card for a specific quadrimestre, containing grades.
     */
    static class ReportCard {
        private int quadrimestre;
        private Map<String, Double> grades; // subject -> grade

        public ReportCard(int quadrimestre) {
            this.quadrimestre = quadrimestre;
            this.grades = new HashMap<>();
        }

        public int getQuadrimestre() {
            return quadrimestre;
        }

        public void addGrade(String subject, double grade) {
            grades.put(subject, grade);
        }

        public Map<String, Double> getGrades() {
            return grades;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Quadrimestre: ").append(quadrimestre).append("\n");
            for (Map.Entry<String, Double> entry : grades.entrySet()) {
                sb.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
            return sb.toString();
        }
    }

    /**
     * Represents the Teacher who is logged in and has a list of academic years they teach.
     */
    static class Teacher {
        private String name;
        private List<AcademicYear> academicYears;

        public Teacher(String name) {
            this.name = name;
            this.academicYears = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public List<AcademicYear> getAcademicYears() {
            return academicYears;
        }

        public void addAcademicYear(AcademicYear ay) {
            academicYears.add(ay);
        }
    }

    /**
     * Simulates the SMOS Server connection.
     * In a real system, this would handle network communication.
     */
    static class SmosServer {
        private boolean connected;

        public SmosServer() {
            this.connected = false;
        }

        public void connect() {
            this.connected = true;
            System.out.println("Connected to SMOS server.");
        }

        public void disconnect() {
            this.connected = false;
            System.out.println("Disconnected from SMOS server.");
        }

        public boolean isConnected() {
            return connected;
        }
    }

    /**
     * Main program flow.
     * Precondition: teacher is logged in and clicks "On-line report cards".
     * Steps:
     * 1. System shows academic years where teacher has at least one class.
     * 2. Teacher selects academic year.
     * 3. System shows classes for that year.
     * 4. Teacher selects a class.
     * 5. System shows students in that class.
     * 6. Teacher selects a student and quadrimestre.
     * 7. System shows the report card.
     * 8. Disconnect from SMOS server.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SmosServer server = new SmosServer();
        
        // Simulate preconditions: teacher is logged in and clicked the button
        System.out.println("Teacher logged in. Clicked 'On-line report cards'.");
        server.connect();
        
        // Create sample data
        Teacher teacher = createSampleTeacher();
        
        // Step 1: View list of academic years
        System.out.println("\n--- Step 1: List of Academic Years ---");
        List<AcademicYear> academicYears = teacher.getAcademicYears();
        if (academicYears.isEmpty()) {
            System.out.println("No academic years found for this teacher.");
            server.disconnect();
            return;
        }
        for (int i = 0; i < academicYears.size(); i++) {
            System.out.println((i + 1) + ". " + academicYears.get(i));
        }
        
        // Step 2: Teacher selects academic year
        System.out.print("\nSelect academic year (enter number): ");
        int yearIndex = readIntInput(scanner, 1, academicYears.size()) - 1;
        AcademicYear selectedYear = academicYears.get(yearIndex);
        System.out.println("Selected: " + selectedYear);
        
        // Step 3: View classes associated with selected year
        System.out.println("\n--- Step 3: Classes for " + selectedYear + " ---");
        List<Class> classes = selectedYear.getClasses();
        if (classes.isEmpty()) {
            System.out.println("No classes found for this academic year.");
            server.disconnect();
            return;
        }
        for (int i = 0; i < classes.size(); i++) {
            System.out.println((i + 1) + ". " + classes.get(i));
        }
        
        // Step 4: Teacher selects a class
        System.out.print("\nSelect a class (enter number): ");
        int classIndex = readIntInput(scanner, 1, classes.size()) - 1;
        Class selectedClass = classes.get(classIndex);
        System.out.println("Selected: " + selectedClass);
        
        // Step 5: Display list of students in the selected class
        System.out.println("\n--- Step 5: Students in " + selectedClass + " ---");
        List<Student> students = selectedClass.getStudents();
        if (students.isEmpty()) {
            System.out.println("No students found in this class.");
            server.disconnect();
            return;
        }
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i));
        }
        
        // Step 6: Teacher selects a student and quadrimestre
        System.out.print("\nSelect a student (enter number): ");
        int studentIndex = readIntInput(scanner, 1, students.size()) - 1;
        Student selectedStudent = students.get(studentIndex);
        System.out.println("Selected student: " + selectedStudent);
        
        System.out.print("Enter quadrimestre (1, 2, or 3): ");
        int quadrimestre = readIntInput(scanner, 1, 3);
        
        // Step 7: Display the report card for selected student and quadrimestre
        System.out.println("\n--- Step 7: Report Card ---");
        ReportCard report = selectedStudent.getReportCard(quadrimestre);
        if (report == null) {
            System.out.println("No report card found for quadrimestre " + quadrimestre);
        } else {
            System.out.println("Student: " + selectedStudent.getName());
            System.out.println(report);
        }
        
        // Postcondition: disconnect from SMOS server
        System.out.println("\n--- Ending session ---");
        server.disconnect();
        scanner.close();
    }
    
    /**
     * Helper method to read and validate integer input within a range.
     * Keeps prompting until valid input is received.
     */
    private static int readIntInput(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int value = scanner.nextInt();
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.print("Please enter a number between " + min + " and " + max + ": ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next(); // consume invalid token
            }
        }
    }
    
    /**
     * Creates a sample teacher with sample academic years, classes, students, and report cards.
     * In a real application, this data would come from a database or server.
     */
    private static Teacher createSampleTeacher() {
        Teacher teacher = new Teacher("Dr. Smith");
        
        // Academic Year 2023-2024
        AcademicYear year2023 = new AcademicYear("2023-2024");
        Class mathClass = new Class("Mathematics 10A");
        Class scienceClass = new Class("Science 10B");
        
        Student alice = new Student("Alice Johnson");
        Student bob = new Student("Bob Williams");
        Student charlie = new Student("Charlie Brown");
        
        // Add report cards for Alice
        ReportCard aliceQ1 = new ReportCard(1);
        aliceQ1.addGrade("Math", 8.5);
        aliceQ1.addGrade("Science", 9.0);
        alice.addReportCard(1, aliceQ1);
        
        ReportCard aliceQ2 = new ReportCard(2);
        aliceQ2.addGrade("Math", 9.0);
        aliceQ2.addGrade("Science", 8.0);
        alice.addReportCard(2, aliceQ2);
        
        // Add report cards for Bob
        ReportCard bobQ1 = new ReportCard(1);
        bobQ1.addGrade("Math", 7.5);
        bobQ1.addGrade("Science", 8.5);
        bob.addReportCard(1, bobQ1);
        
        // Add report cards for Charlie
        ReportCard charlieQ1 = new ReportCard(1);
        charlieQ1.addGrade("Math", 9.5);
        charlieQ1.addGrade("Science", 9.0);
        charlie.addReportCard(1, charlieQ1);
        
        mathClass.addStudent(alice);
        mathClass.addStudent(bob);
        scienceClass.addStudent(charlie);
        
        year2023.addClass(mathClass);
        year2023.addClass(scienceClass);
        
        // Academic Year 2022-2023
        AcademicYear year2022 = new AcademicYear("2022-2023");
        Class historyClass = new Class("History 9A");
        
        Student diana = new Student("Diana Miller");
        ReportCard dianaQ1 = new ReportCard(1);
        dianaQ1.addGrade("History", 8.0);
        diana.addReportCard(1, dianaQ1);
        
        historyClass.addStudent(diana);
        year2022.addClass(historyClass);
        
        teacher.addAcademicYear(year2023);
        teacher.addAcademicYear(year2022);
        
        return teacher;
    }
}