package presentation;

import domain.dto.AcademicYearDTO;
import domain.dto.ClassSummaryDTO;
import domain.dto.QuadrimestreDTO;
import domain.dto.ReportCardDTO;
import domain.dto.StudentSummaryDTO;

import java.util.List;

/**
 * The view component for displaying report card related information to the user.
 * It's responsible for presenting data in a human-readable format, typically to the console
 * or a graphical user interface.
 */
public class ReportCardView {

    /**
     * Displays a list of academic years.
     * @param years The list of AcademicYearDTOs to display.
     */
    public void displayAcademicYears(List<AcademicYearDTO> years) {
        System.out.println("\n--- Available Academic Years ---");
        if (years == null || years.isEmpty()) {
            System.out.println("No academic years available.");
            return;
        }
        for (int i = 0; i < years.size(); i++) {
            System.out.println((i + 1) + ". ID: " + years.get(i).getId() + ", Year: " + years.get(i).getYear());
        }
        System.out.println("--------------------------------");
    }

    /**
     * Displays a list of classes.
     * @param classes The list of ClassSummaryDTOs to display.
     */
    public void displayClasses(List<ClassSummaryDTO> classes) {
        System.out.println("\n--- Classes for Selected Academic Year ---");
        if (classes == null || classes.isEmpty()) {
            System.out.println("No classes available for this academic year.");
            return;
        }
        for (int i = 0; i < classes.size(); i++) {
            System.out.println((i + 1) + ". ID: " + classes.get(i).getId() + ", Name: " + classes.get(i).getName());
        }
        System.out.println("------------------------------------------");
    }

    /**
     * Displays a list of students.
     * @param students The list of StudentSummaryDTOs to display.
     */
    public void displayStudents(List<StudentSummaryDTO> students) {
        System.out.println("\n--- Students in Selected Class ---");
        if (students == null || students.isEmpty()) {
            System.out.println("No students available in this class.");
            return;
        }
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". ID: " + students.get(i).getId() + ", Name: " + students.get(i).getName());
        }
        System.out.println("----------------------------------");
    }

    /**
     * Displays a list of quadrimestres.
     * @param quadrimestres The list of QuadrimestreDTOs to display.
     */
    public void displayQuadrimestres(List<QuadrimestreDTO> quadrimestres) {
        System.out.println("\n--- Available Quadrimestres ---");
        if (quadrimestres == null || quadrimestres.isEmpty()) {
            System.out.println("No quadrimestres available.");
            return;
        }
        for (int i = 0; i < quadrimestres.size(); i++) {
            System.out.println((i + 1) + ". ID: " + quadrimestres.get(i).getId() + ", Name: " + quadrimestres.get(i).getName());
        }
        System.out.println("---------------------------------");
    }

    /**
     * Displays a student's report card.
     * @param reportCard The ReportCardDTO to display.
     */
    public void displayReportCard(ReportCardDTO reportCard) {
        System.out.println("\n--- Student Report Card ---");
        if (reportCard == null) {
            System.out.println("No report card found or could not be generated.");
            System.out.println("---------------------------");
            return;
        }
        System.out.println("Student: " + reportCard.getStudentName());
        System.out.println("Class: " + reportCard.getClassName());
        System.out.println("Academic Year: " + reportCard.getAcademicYear());
        System.out.println("Quadrimestre: " + reportCard.getQuadrimestre());
        System.out.println("\nGrades:");
        reportCard.getGrades().forEach((subject, grade) -> System.out.println("  " + subject + ": " + grade));
        System.out.println("\nComments: " + reportCard.getComments());
        System.out.println("---------------------------");
    }
}