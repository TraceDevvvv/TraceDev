package com.example.schoolreports;

import java.util.List;

/**
 * Interface for the Data Access Layer (Repository Pattern).
 * Defines the contract for fetching raw report data from a source (e.g., SMOS).
 */
public interface IReportRepository {

    /**
     * Fetches a list of raw student report summaries for a given parent.
     *
     * @param parentId The unique identifier of the parent.
     * @return A list of SMOSRawReportData objects, which are the raw data from the external system.
     * @throws SMOSConnectionException If there's an issue connecting to the underlying data source.
     */
    List<SMOSRawReportData> fetchStudentReports(String parentId) throws SMOSConnectionException;

    /**
     * Fetches raw detailed report card data for a specific report card ID.
     *
     * @param reportCardId The unique identifier of the report card.
     * @return SMOSRawReportCardData object containing the raw details.
     * @throws SMOSConnectionException If there's an issue connecting to the underlying data source.
     */
    SMOSRawReportCardData fetchReportCardDetails(String reportCardId) throws SMOSConnectionException;
}