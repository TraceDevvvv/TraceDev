package com.atastaff.absencesystem.repository;

import com.atastaff.absencesystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA Repository for Student entities.
 * Provides methods for performing CRUD operations and custom queries on Student objects.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    /**
     * Finds all students belonging to a specific class.
     * This method leverages Spring Data JPA's query derivation mechanism.
     * The `currentClass_ClassId` refers to the `classId` property within the `currentClass` association.
     *
     * @param classId The unique identifier of the class.
     * @return A list of students associated with the given class ID.
     */
    List<Student> findByCurrentClass_ClassId(String classId);
}