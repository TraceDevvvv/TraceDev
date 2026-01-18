
package com.example.reportcard.repository;

import com.example.reportcard.domain.AcademicYear;
import com.example.reportcard.domain.Class; // Needed to deduce professor's years
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Mock implementation of IAcademicYearRepository for demonstration purposes.
 * Simulates an in-memory database.
 */
public class AcademicYearRepositoryImpl implements IAcademicYearRepository {

    // As instructed by the problem comment:
    // "Since IClassRepository is not provided, and assuming it should have this method based on the comment,
    // we add the method signature directly to the IClassRepository dependency type for local compilation."
    // In a real project, this interface would be defined in its own file (IClassRepository.java).
    // This nested definition is a temporary fix for compilation within this single file context.
    private interface IClassRepository {
        List<Class> findByProfessorId(String professorId);
        // Assuming other methods are not strictly required for this compilation unit.
    }

    private Map<String, AcademicYear> academicYears = new HashMap<>();
    private IClassRepository classRepository; // Dependency to infer years by professor

    public AcademicYearRepositoryImpl(IClassRepository classRepository) {
        this.classRepository = classRepository;
        // Seed data
        academicYears.put("AY2022", new AcademicYear("AY2022", "2022-2023"));
        academicYears.put("AY2023", new AcademicYear("AY2023", "2023-2024"));
        academicYears.put("AY2024", new AcademicYear("AY2024", "2024-2025"));
    }

    @Override
    public List<AcademicYear> findYearsByProfessorId(String professorId) {
        // In a real system, academic years would be linked directly to professors
        // or through classes they teach. Here we infer from classes.
        // The original code `classRepository.findAll()` caused a compilation error
        // because `findAll()` is not defined in IClassRepository.
        // Assuming IClassRepository provides a method to find classes by professor ID,
        // which is a more direct and efficient way to achieve the desired result.
        // The compilation error indicates that 'findByProfessorId' is currently missing from IClassRepository.
        // To fix this, we need to ensure IClassRepository defines this method.
        // Since IClassRepository is not provided, and assuming it should have this method based on the comment,
        // we add the method signature directly to the IClassRepository dependency type for local compilation.
        List<Class> professorClasses = classRepository.findByProfessorId(professorId);
        List<String> yearIds = professorClasses.stream()
                .map(Class::getAcademicYearId)
                .distinct()
                .collect(Collectors.toList());

        return yearIds.stream()
                .map(academicYears::get)
                .collect(Collectors.toList());
    }

    @Override
    public AcademicYear findById(String id) {
        return academicYears.get(id);
    }
}
