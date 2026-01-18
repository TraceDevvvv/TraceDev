package com.example.studentdelay.dataaccess;

import com.example.studentdelay.domain.DelayRecord;
import com.example.studentdelay.domain.Student;
import com.example.studentdelay.util.ConnectionException;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface for the Student Delay Repository.
 * Defines the contract for data access operations related to student delays and student information.
 */
public interface IStudentDelayRepository {

    /**
     * Saves a new or updates an existing DelayRecord.
     * The returned DelayRecord should contain any generated IDs or updated timestamps.
     *
     * @param delayRecord The DelayRecord to save.
     * @return The saved DelayRecord, possibly with updated ID.
     * @throws ConnectionException if there's an issue connecting to the data source.
     */
    DelayRecord save(DelayRecord delayRecord) throws ConnectionException;

    /**
     * Finds all DelayRecords for a specific date.
     *
     * @param date The date to query for.
     * @return A list of DelayRecord entities for the given date.
     * @throws ConnectionException if there's an issue connecting to the data source.
     */
    List<DelayRecord> findByDate(LocalDate date) throws ConnectionException;

    /**
     * Finds a Student by their ID.
     *
     * @param studentId The ID of the student to find.
     * @return The Student object if found, otherwise null.
     * @throws ConnectionException if there's an issue connecting to the data source.
     */
    Student findStudentById(String studentId) throws ConnectionException;
}