import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Main class for the ViewOnePage use case.
 * Simulates the flow of selecting academic year, class, student, and semester to display a report card.
 * Uses dummy data and console input.
 */
public class ViewOnePage {
    private static ReportCardService reportCardService;
    private static Scanner scanner;

    public static void main(String[] args) {
        // Initialize the service with dummy data
        reportCardService = new ReportCardService();
        scanner = new Scanner(System.in);

        System.out.println("=== Online Report Cards System ===");
        System.out.println("User logged in as Direction.");

        // Step 1: Select academic year
        AcademicYear selectedYear = selectAcademicYear();
        if (selectedYear == null) {
            System.out.println("No academic year selected. Exiting.");
            return;
        }

        // Step 2 & 3: Display classes and choose one
        Class selectedClass = selectClass(selectedYear);
        if (selectedClass == null) {
            System.out.println("No class selected. Exiting.");
            return;
        }

        // Step 4 & 5: Display students and choose one with semester
        Student selectedStudent = selectStudent(selectedClass);
        if (selectedStudent == null) {
            System.out.println("No student selected. Exiting.");
            return;
        }

        Semester selectedSemester = selectSemester();
        if (selectedSemester == null) {
            System.out.println("No semester selected. Exiting.");
            return;
        }

        // Step 6: Display the student's report card for the selected semester
        displayReportCard(selectedStudent, selectedSemester);

        // Postcondition: User stops the connection
        System.out.println("\nConnection to the SMOS server interrupted.");
        scanner.close();
    }

    /**
     * Step 1: Let the user select an academic year from available years.
     * @return The selected AcademicYear, or null if invalid selection.
     */
    private static AcademicYear selectAcademicYear() {
        List<AcademicYear> academicYears = reportCardService.getAvailableAcademicYears();
        if (academicYears.isEmpty()) {
            System.out.println("No academic years available.");
            return null;
        }

        System.out.println("\n--- Select Academic Year ---");
        for (int i = 0; i < academicYears.size(); i++) {
            System.out.println((i + 1) + ". " + academicYears.get(i).getDisplayName());
        }

        System.out.print("Enter the number of the academic year: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice >= 1 && choice <= academicYears.size()) {
                AcademicYear selected = academicYears.get(choice - 1);
                System.out.println("Selected academic year: " + selected.getDisplayName());
                return selected;
            } else {
                System.out.println("Invalid selection. Please try again.");
                return selectAcademicYear(); // Recursive retry
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return selectAcademicYear(); // Recursive retry
        }
    }

    /**
     * Step 2 & 3: Display classes for the selected academic year and let user choose one.
     * @param academicYear The selected academic year.
     * @return The selected Class, or null if invalid selection.
     */
    private static Class selectClass(AcademicYear academicYear) {
        List<Class> classes = reportCardService.getClassesByAcademicYear(academicYear);
        if (classes.isEmpty()) {
            System.out.println("No classes available for the selected academic year.");
            return null;
        }

        System.out.println("\n--- Classes with Report Cards Button ---");
        for (int i = 0; i < classes.size(); i++) {
            System.out.println((i + 1) + ". " + classes.get(i).getName() + " [Report Cards]");
        }

        System.out.print("Enter the number of the class to view report cards: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice >= 1 && choice <= classes.size()) {
                Class selected = classes.get(choice - 1);
                System.out.println("Selected class: " + selected.getName());
                return selected;
            } else {
                System.out.println("Invalid selection. Please try again.");
                return selectClass(academicYear); // Recursive retry
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return selectClass(academicYear); // Recursive retry
        }
    }

    /**
     * Step 4 & 5: Display students in the selected class and let user choose one.
     * @param selectedClass The selected class.
     * @return The selected Student, or null if invalid selection.
     */
    private static Student selectStudent(Class selectedClass) {
        List<Student> students = reportCardService.getStudentsByClass(selectedClass);
        if (students.isEmpty()) {
            System.out.println("No students available in the selected class.");
            return null;
        }

        System.out.println("\n--- Students in Class " + selectedClass.getName() + " ---");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i).getFullName());
        }

        System.out.print("Enter the number of the student to view report card: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice >= 1 && choice <= students.size()) {
                Student selected = students.get(choice - 1);
                System.out.println("Selected student: " + selected.getFullName());
                return selected;
            } else {
                System.out.println("Invalid selection. Please try again.");
                return selectStudent(selectedClass); // Recursive retry
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return selectStudent(selectedClass); // Recursive retry
        }
    }

    /**
     * Step 5 (continued): Let the user select a semester (quadrimestre).
     * @return The selected Semester, or null if invalid selection.
     */
    private static Semester selectSemester() {
        List<Semester> semesters = reportCardService.getAvailableSemesters();
        if (semesters.isEmpty()) {
            System.out.println("No semesters available.");
            return null;
        }

        System.out.println("\n--- Select Semester (Quadrimestre) ---");
        for (int i = 0; i < semesters.size(); i++) {
            System.out.println((i + 1) + ". " + semesters.get(i).getName());
        }

        System.out.print("Enter the number of the semester: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice >= 1 && choice <= semesters.size()) {
                Semester selected = semesters.get(choice - 1);
                System.out.println("Selected semester: " + selected.getName());
                return selected;
            } else {
                System.out.println("Invalid selection. Please try again.");
                return selectSemester(); // Recursive retry
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return selectSemester(); // Recursive retry
        }
    }

    /**
     * Step 6: Display the student's report card for the selected semester.
     * @param student The selected student.
     * @param semester The selected semester.
     */
    private static void displayReportCard(Student student, Semester semester) {
        ReportCard reportCard = reportCardService.getReportCard(student, semester);
        if (reportCard == null) {
            System.out.println("\nNo report card available for " + student.getFullName() +
                               " in " + semester.getName() + ".");
            return;
        }

        System.out.println("\n=== REPORT CARD ===");
        System.out.println("Student: " + student.getFullName());
        System.out.println("Class: " + student.getClassName());
        System.out.println("Semester: " + semester.getName());
        System.out.println("Academic Year: " + semester.getAcademicYear().getDisplayName());
        System.out.println("----------------------------------------");
        System.out.println("Subjects and Grades:");
        reportCard.getSubjectGrades().forEach((subject, grade) ->
            System.out.println("  " + subject + ": " + grade)
        );
        System.out.println("----------------------------------------");
        System.out.println("Average Grade: " + reportCard.getAverageGrade());
        System.out.println("Teacher Comments: " + reportCard.getTeacherComments());
        System.out.println("========================================");
    }
}