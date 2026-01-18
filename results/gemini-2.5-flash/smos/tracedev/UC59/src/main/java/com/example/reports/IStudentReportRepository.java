package com.example.reports;

import java.util.List;

/**
 * Interface defining the contract for retrieving student report data.
 * Added to satisfy requirement ExC2.
 */
public interface IStudentReportRepository {

    /**
     * Retrieves a list of all student reports for a given student ID.
     * @param studentId The ID of the student.
     * @return A list of StudentReport objects.
     * @throws ServerConnectionError If there is an issue connecting to the report server.
     */
    List<StudentReport> getReportsByStudentId(String studentId) throws ServerConnectionError;

    /**
     * Retrieves a specific student report by its unique report ID.
     * @param reportId The unique ID of the report.
     * @return The StudentReport object corresponding to the given ID.
     * @throws ServerConnectionError If there is an issue connecting to the report server.
     */
    StudentReport getReportById(String reportId) throws ServerConnectionError;
}