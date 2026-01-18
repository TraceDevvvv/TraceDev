package ViewClassListTeacher_1766400150;

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Main application class for the "View Class List Teacher" use case.
 * This class handles user interaction, retrieves data from the simulated SMOS server,
 * and displays the list of academic years and courses for a logged-in professor.
 */
public class ViewClassListTeacherApp {

    private static Scanner scanner = new Scanner(System.in);
    private static Professor loggedInProfessor = null; // Represents the currently logged-in professor

    public static void main(String[] args) {
        System.out.println("--- Welcome to the Digital Log System ---");

        // Precondition: The user is logged in to the system as a teacher
        // Simulate login
        if (!simulateLogin()) {
            System.out.println("Login failed or no professor found. Exiting application.");
            SMOSServer.interruptConnection(); // Fulfill postcondition even on failed login
            return;
        }

        System.out.println("\nLogged in as: " + loggedInProfessor.getName() + " (ID: " + loggedInProfessor.getId() + ")");

        // Precondition: The user clicks the "Digital Log" button
        // Simulate clicking "Digital Log" by proceeding with the use case
        System.out.println("\n--- Digital Log ---");

        // Event Sequence Step 1: System displays the list of academic years
        // in which at least one class in which the teacher teaches.
        List<AcademicYear> academicYears = SMOSServer.getAcademicYearsForProfessor(loggedInProfessor);

        if (academicYears.isEmpty()) {
            System.out.println("No academic years found where " + loggedInProfessor.getName() + " teaches any classes.");
            System.out.println("Use case finished.");
            SMOSServer.interruptConnection(); // Fulfill postcondition
            return;
        }

        System.out.println("\nAcademic Years where you teach classes:");
        for (int i = 0; i < academicYears.size(); i++) {
            System.out.println((i + 1) + ". " + academicYears.get(i).getName());
        }

        // Event Sequence Step 2: User selects the academic year of interest.
        AcademicYear selectedAcademicYear = selectAcademicYear(academicYears);

        if (selectedAcademicYear == null) {
            System.out.println("No valid academic year selected. Exiting application.");
            SMOSServer.interruptConnection(); // Fulfill postcondition
            return;
        }

        System.out.println("\nYou selected: " + selectedAcademicYear.getName());

        // Event Sequence Step 3: View the classes associated with the selected school year.
        displayClassesForSelectedYear(selectedAcademicYear);

        // Postcondition: Connection to the interrupted SMOS server.
        SMOSServer.interruptConnection();
        System.out.println("\n--- Application Finished ---");
        scanner.close();
    }

    /**
     * Simulates the login process for a professor.
     * Prompts the user for a professor ID and attempts to retrieve the professor from the SMOS server.
     *
     * @return true if a professor is successfully logged in, false otherwise.
     */
    private static boolean simulateLogin() {
        System.out.print("Please enter your Professor ID to log in: ");
        String professorId = scanner.nextLine();

        loggedInProfessor = SMOSServer.getProfessorById(professorId);

        if (loggedInProfessor == null) {
            System.out.println("Error: Professor with ID '" + professorId + "' not found.");
            return false;
        }
        return true;
    }

    /**
     * Prompts the user to select an academic year from a given list.
     * Handles invalid input and ensures a valid selection is made.
     *
     * @param academicYears The list of academic years to choose from.
     * @return The selected AcademicYear object, or null if the user chooses to exit.
     */
    private static AcademicYear selectAcademicYear(List<AcademicYear> academicYears) {
        int choice = -1;
        AcademicYear selectedYear = null;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter the number corresponding to the academic year you want to view (or 0 to exit): ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (choice == 0) {
                    return null; // User chose to exit
                }

                if (choice > 0 && choice <= academicYears.size()) {
                    selectedYear = academicYears.get(choice - 1);
                    validInput = true;
                } else {
                    System.out.println("Invalid selection. Please enter a number between 1 and " + academicYears.size() + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        return selectedYear;
    }

    /**
     * Retrieves and displays the list of courses taught by the logged-in professor
     * for the specified academic year.
     *
     * @param academicYear The academic year for which to display courses.
     */
    private static void displayClassesForSelectedYear(AcademicYear academicYear) {
        System.out.println("\n--- Classes for " + loggedInProfessor.getName() + " in " + academicYear.getName() + " ---");

        List<Course> courses = SMOSServer.getCoursesForProfessorAndAcademicYear(loggedInProfessor, academicYear);

        if (courses.isEmpty()) {
            System.out.println("No classes found for " + loggedInProfessor.getName() + " in " + academicYear.getName() + ".");
        } else {
            // Postcondition: The list of all classes in which teaches the teacher is shown
            for (Course course : courses) {
                System.out.println("- " + course.getName() + " (ID: " + course.getId() + ")");
            }
        }
    }
}