package com.example.school.infrastructure;

import com.example.school.domain.ReportCard;

/**
 * Interface for operations related to ReportCard persistence.
 */
public interface IReportCardRepository {
    /**
     * Saves a report card to the persistence store.
     * @param reportCard The ReportCard object to save.
     * @return true if the save operation was successful, false otherwise.
     */
    boolean save(ReportCard reportCard);
}