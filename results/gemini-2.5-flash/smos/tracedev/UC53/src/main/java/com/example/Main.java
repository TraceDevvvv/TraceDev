package com.example;

import com.example.repository.ClassRepositoryImpl;
import com.example.repository.IClassRepository;
import com.example.repository.IUserRepository;
import com.example.repository.UserRepositoryImpl;
import com.example.service.AuthenticationService;
import com.example.service.ClassService;
import com.example.view.ClassListView;

/**
 * Main application class to demonstrate the interaction defined by the sequence diagram.
 * This acts as the entry point and sets up the dependencies.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Application Starting...\n");

        // --- Dependency Injection / Object Creation ---
        // Repositories
        IClassRepository classRepository = new ClassRepositoryImpl();
        IUserRepository userRepository = new UserRepositoryImpl();

        // Serv
        ClassService classService = new ClassService(classRepository);
        AuthenticationService authService = new AuthenticationService(userRepository);

        // View
        ClassListView classListView = new ClassListView(classService, authService);

        // --- Simulate User Interactions (based on Sequence Diagram) ---

        // Scenario 1: ATA Staff requests to view classes (successful path)
        System.out.println("--- Scenario 1: ATA Staff (staff123) requests class list ---");
        classListView.requestClassList("staff123");
        System.out.println("\n--- End of Scenario 1 ---\n");

        // Scenario 2: Student (student456) requests to view classes (authenticated but not authorized)
        System.out.println("--- Scenario 2: Student (student456) requests class list ---");
        classListView.requestClassList("student456");
        System.out.println("\n--- End of Scenario 2 ---\n");

        // Scenario 3: Non-existent user (unauthenticated) requests to view classes
        System.out.println("--- Scenario 3: Non-existent user (nonUser) requests class list ---");
        classListView.requestClassList("nonUser");
        System.out.println("\n--- End of Scenario 3 ---\n");

        System.out.println("Application Finished.");
    }
}