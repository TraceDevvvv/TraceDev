package com.example;

import java.util.List;

/**
 * Represents an administrator user who can retrieve student monitoring data.
 */
public class Administrator {
    public String userId;
    public String userType;

    /**
     * Constructor for Administrator.
     * @param userId the administrator's user ID
     * @param userType the administrator's user type
     */
    public Administrator(String userId, String userType) {
        this.userId = userId;
        this.userType = userType;
    }

    /**
     * Authenticates the administrator.
     * @return true if authentication succeeds (simplified for example).
     */
    public boolean authenticate() {
        // In a real system, this would validate credentials.
        return true;
    }

    /**
     * Searches for students based on threshold criteria.
     * @param threshold the threshold criteria for filtering students
     * @return list of StudentMonitoringDto objects
     */
    public List<StudentMonitoringDto> searchStudents(ThresholdCriteria threshold) {
        StudentMonitoringFacade facade = new StudentMonitoringFacade(new StudentRepository(new DataSource()));
        MonitoringController controller = new MonitoringController(facade);
        return controller.retrieveStudentMonitoring(threshold);
    }

    /**
     * Retrieves student monitoring data based on given criteria.
     * This method demonstrates the interaction with MonitoringController as per sequence diagram.
     * @param criteria the threshold criteria for filtering students
     */
    @Deprecated
    public void retrieveStudentMonitoring(ThresholdCriteria criteria) {
        // In a real application, the administrator would likely use a controller.
        // For this example, we instantiate the controller and call the method.
        StudentMonitoringFacade facade = new StudentMonitoringFacade(new StudentRepository(new DataSource()));
        MonitoringController controller = new MonitoringController(facade);
        var results = controller.retrieveStudentMonitoring(criteria);
        // Typically results would be displayed; here we just print.
        System.out.println("Administrator retrieved " + results.size() + " student records.");
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}