package com.example;

import java.util.List;

/**
 * Main class to demonstrate the system operation.
 */
public class Main {
    public static void main(String[] args) {
        // Create data source and repository
        DataSource dataSource = new DataSource();
        StudentRepository repository = new StudentRepository(dataSource);
        // Create facade and controller
        StudentMonitoringFacade facade = new StudentMonitoringFacade(repository);
        MonitoringController controller = new MonitoringController(facade);
        // Create an administrator
        Administrator admin = new Administrator("admin1", "Administrator");
        // Create a concrete threshold criteria (anonymous inner class for demonstration)
        ThresholdCriteria criteria = new ThresholdCriteria("2023", 5, 3) {
            // This class is concrete for instantiation.
        };
        // Administrator retrieves student monitoring data
        admin.authenticate();
        admin.retrieveStudentMonitoring(criteria);

        // Alternatively, directly use controller as per sequence diagram:
        List<StudentMonitoringDto> results = controller.retrieveStudentMonitoring(criteria);
        System.out.println("Found " + results.size() + " students exceeding thresholds.");
        for (StudentMonitoringDto dto : results) {
            System.out.println(dto);
        }
    }
}