package com.example;

import com.example.client.SmosServerClient;
import com.example.client.SmosServerClientImpl;
import com.example.controller.SchoolDataController;
import com.example.model.DataSource;
import com.example.repository.StudentRepository;
import com.example.repository.StudentRepositoryImpl;
import com.example.security.AuthenticationService;
import com.example.security.AuthenticationServiceImpl;
import com.example.view.SchoolDataView;

public class Main {
    public static void main(String[] args) {
        // Initialize all dependencies
        DataSource dataSource = new DataSource();
        StudentRepository repository = new StudentRepositoryImpl(dataSource);
        SmosServerClient smosClient = new SmosServerClientImpl();
        AuthenticationService authService = new AuthenticationServiceImpl();
        
        // Create controller
        SchoolDataController controller = new SchoolDataController(repository, smosClient, authService);
        
        // Create view (UI)
        SchoolDataView view = new SchoolDataView(controller, authService);
        
        // Simulate user interaction: student clicks "Digital Log" button
        String testStudentId = "12345";
        System.out.println("Simulating student click on 'Digital Log' button for student ID: " + testStudentId);
        view.onDigitalLogButtonClick(testStudentId);
    }
}