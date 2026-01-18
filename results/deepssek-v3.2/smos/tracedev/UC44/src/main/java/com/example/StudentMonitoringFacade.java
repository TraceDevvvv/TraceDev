package com.example;

import java.util.List;

/**
 * Facade class that provides a simplified interface for retrieving student monitoring data.
 */
public class StudentMonitoringFacade {
    private IStudentRepository studentRepository;

    /**
     * Constructor with dependency injection.
     * @param repository the repository implementation
     */
    public StudentMonitoringFacade(IStudentRepository repository) {
        this.studentRepository = repository;
    }

    /**
     * Retrieves students whose absence and note counts exceed the given thresholds.
     * @param criteria the threshold criteria containing year and thresholds
     * @return a list of StudentMonitoringDto objects
     */
    public List<StudentMonitoringDto> getStudentsExceedingThreshold(ThresholdCriteria criteria) {
        // Delegate to the repository to get the data.
        return studentRepository.getStudentMonitoringData(criteria);
    }
}