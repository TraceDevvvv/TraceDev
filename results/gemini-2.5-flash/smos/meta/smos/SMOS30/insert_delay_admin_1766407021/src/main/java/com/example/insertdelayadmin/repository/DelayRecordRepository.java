package com.example.insertdelayadmin.repository;

import com.example.insertdelayadmin.model.DelayRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for managing {@link DelayRecord} entities.
 * Extends JpaRepository to provide standard CRUD operations and custom query methods.
 */
@Repository
public interface DelayRecordRepository extends JpaRepository<DelayRecord, String> {

    /**
     * Finds all {@link DelayRecord} entities for a specific date.
     * This method is used to retrieve all delay entries made on a given day,
     * regardless of which administrator entered them.
     *
     * @param date The date for which to retrieve delay records.
     * @return A {@link List} of {@link DelayRecord} objects found for the specified date.
     */
    List<DelayRecord> findByDate(LocalDate date);

    /**
     * Finds all {@link DelayRecord} entities for a specific date and entered by a specific administrator.
     * This method is useful for administrators to view only the delay records they have entered
     * on a particular day.
     *
     * @param date The date for which to retrieve delay records.
     * @param adminId The ID of the administrator who entered the records.
     * @return A {@link List} of {@link DelayRecord} objects found for the specified date and administrator.
     */
    List<DelayRecord> findByDateAndEnteredByAdminId(LocalDate date, String adminId);
}