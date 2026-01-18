package com.example;

import java.util.List;

/**
 * Interface for student repository, defining operations for retrieving monitoring data.
 */
public interface IStudentRepository {
    /**
     * Retrieves student monitoring data based on threshold criteria.
     * @param criteria the threshold criteria
     * @return a list of StudentMonitoringDto objects
     */
    List<StudentMonitoringDto> getStudentMonitoringData(ThresholdCriteria criteria);

    /**
     * Finds students whose absence count exceeds the given threshold.
     * @param threshold the absence threshold
     * @return list of Student objects
     */
    List<Student> findStudentsExceedingAbsenceThreshold(int threshold);

    /**
     * Finds students whose note count exceeds the given threshold.
     * @param threshold the note threshold
     * @return list of Student objects
     */
    List<Student> findStudentsExceedingNoteThreshold(int threshold);

    /**
     * Validates the accuracy of the data source.
     * @return true if data is accurate (simplified for example).
     */
    boolean validateDataAccuracy();
}