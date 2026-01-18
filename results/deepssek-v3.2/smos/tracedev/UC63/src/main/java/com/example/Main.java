package com.example;

import com.example.view.StudentMonitoringView;
import com.example.controller.StudentMonitoringController;
import com.example.service.StudentServiceImpl;
import com.example.repository.StudentRepositoryImpl;
import com.example.infrastructure.DataSource;
import com.example.infrastructure.SMOSConnection;
import com.example.user.User;

/**
 * Main class to run the student monitoring application.
 * Sets up dependencies and starts the view.
 */
public class Main {
    public static void main(String[] args) {
        // Infrastructure
        DataSource dataSource = new DataSource();
        SMOSConnection smosConnection = new SMOSConnection();

        // Repository
        StudentRepositoryImpl repository = new StudentRepositoryImpl(dataSource, smosConnection);

        // Service
        StudentServiceImpl service = new StudentServiceImpl(repository);

        // Controller
        StudentMonitoringController controller = new StudentMonitoringController(service);

        // User (simulate logged in)
        User user = new User(true);

        // View
        StudentMonitoringView view = new StudentMonitoringView(controller, user);

        // Start monitoring
        view.startMonitoring();
    }
}