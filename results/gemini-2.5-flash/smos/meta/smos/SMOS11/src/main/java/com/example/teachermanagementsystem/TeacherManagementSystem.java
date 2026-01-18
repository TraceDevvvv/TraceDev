```java
package com.example.teachermanagementsystem;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Main class for the Teacher Management System.
 * This class orchestrates the application flow for the "Assign/Remove Teachings to a Teacher" use case.
 * It simulates user interaction (Administrator) and utilizes various service and repository classes
 * to manage academic years, classes, teachings, and teacher assignments.
 */
public class TeacherManagementSystem {

    private final AcademicYearService academicYearService;
    private final ClassService classService;
    private final TeachingService teachingService;
    private final TeacherService teacherService;
    private Administrator loggedInAdministrator; // Represents the currently logged-in administrator

    /**
     * Constructor to initialize all repositories and serv.
     * This sets up the dependencies for the application.
     */
    public TeacherManagementSystem() {
        // Initialize repositories (simulated data access layers)
        AcademicYearRepository academicYearRepository = new AcademicYearRepository();
        ClassRepository classRepository = new ClassRepository();
        TeachingRepository teachingRepository = new TeachingRepository();
        TeacherRepository teacherRepository = new TeacherRepository();

        // Initialize serv with their respective repositories
        this.academicYearService = new AcademicYearService(academicYearRepository);
        this.classService = new ClassService(classRepository);
        this.teachingService = new TeachingService(teachingRepository);
        // TeacherService requires both TeacherRepository and TeachingRepository for its operations
        this.teacherService = new TeacherService(teacherRepository, teachingRepository);
    }

    /**
     * Simulates the administrator login process.
     * In a real application, this would involve a more robust authentication mechanism
     * (e.g., checking against a database, password hashing).
     * For this simulation, a hardcoded username and password are used.
     *
     * @param scanner Scanner object for reading user input.
     * @return true if login is successful, false otherwise.
     */
    private boolean loginAdministrator(Scanner scanner) {
        System.out.println("\n--- Administrator Login ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Simulate a valid administrator for the use case
        if ("admin".equals(username) && "password".equals(password)) {
            this.loggedInAdministrator = new Administrator("ADM001", "admin", "password");
            System.out.println("Administrator '" + username + "' logged in successfully.");
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }

    /**
     * Simulates the "viewdetTailsente" use case, where the system displays
     * details of a teacher and the administrator selects one.
     *
     * @param scanner Scanner object for reading user input.
     * @return The selected Teacher object, or null if no teacher is selected or found.
     */
    private Teacher selectTeacher(Scanner scanner) {
        System.out.println("\n--- Select Teacher for Teaching Management ---");
        List<Teacher> allTeachers = teacherService.getAllTeachers();

        if (allTeachers.isEmpty()) {
            System.out.println("No teachers available in the system.");
            return null;
        }

        System.out.println("Available Teachers:");
        for (int i = 0; i < allTeachers.size(); i++) {
            Teacher teacher = allTeachers.get(i);
            System.out.println((i + 1) + ". " + teacher.getName() + " (ID: " + teacher.getId() + ")");
        }

        