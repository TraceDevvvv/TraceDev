package com.example;

import java.util.List;

/**
 * Controller that handles student monitoring requests.
 */
public class MonitoringController {
    private StudentMonitoringFacade facade;

    /**
     * Constructor with dependency injection.
     * @param facade the facade to delegate operations
     */
    public MonitoringController(StudentMonitoringFacade facade) {
        this.facade = facade;
    }

    /**
     * Retrieves student monitoring data based on criteria.
     * @param criteria the threshold criteria
     * @return a list of StudentMonitoringDto objects
     */
    public List<StudentMonitoringDto> retrieveStudentMonitoring(ThresholdCriteria criteria) {
        // Delegate to facade as per sequence diagram.
        return facade.getStudentsExceedingThreshold(criteria);
    }
}