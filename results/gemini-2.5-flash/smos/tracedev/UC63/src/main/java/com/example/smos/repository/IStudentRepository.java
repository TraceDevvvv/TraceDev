package com.example.smos.repository;

import com.example.smos.model.Student;
import com.example.smos.exception.SMOSServerConnectionException;

import java.util.List;

/**
 * Interface for data access operations related to students.
 * This corresponds to the 'IStudentRepository' interface in the UML diagram.
 */
public interface IStudentRepository {

    /**
     * Retrieves a list of all students from the data store.
     *
     * @return A list of Student objects.
     * @throws SMOSServerConnectionException If there is a problem connecting to the SMOS server.
     *                                       This satisfies requirement REQ-008.
     */
    List<Student> findStudents() throws SMOSServerConnectionException;
}