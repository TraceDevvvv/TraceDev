package com.example.smos.application;

import com.example.smos.domain.AcademicYear;
import com.example.smos.domain.Class;
import com.example.smos.infrastructure.AcademicYearRepository;
import com.example.smos.infrastructure.ClassRepository;
import java.util.List;

/**
 * Application Use Case for viewing classes.
 * Orchestrates fetching academic years and classes using repositories.
 */
public class ViewClassesUseCase {
    private final AcademicYearRepository academicYearRepository;
    private final ClassRepository classRepository;

    /**
     * Constructs a ViewClassesUseCase with necessary repository dependencies.
     * @param academicYearRepository The repository for academic year data.
     * @param classRepository The repository for class data.
     */
    public ViewClassesUseCase(AcademicYearRepository academicYearRepository, ClassRepository classRepository) {
        this.academicYearRepository = academicYearRepository;
        this.classRepository = classRepository;
    }

    /**
     * Retrieves all available academic years from the repository.
     * @return A list of AcademicYear objects.
     * @throws com.example.smos.exception.SMOSConnectionException if repository connection fails.
     */
    public List<AcademicYear> getAvailableAcademicYears() {
        System.out.println("[ViewClassesUseCase] Getting available academic years.");
        return academicYearRepository.findAll();
    }

    /**
     * Retrieves a list of classes for a given academic year ID from the repository.
     * @param academicYearId The ID of the academic year.
     * @return A list of Class objects for the specified academic year.
     * @throws com.example.smos.exception.SMOSConnectionException if repository connection fails.
     */
    public List<Class> getClassesByAcademicYear(String academicYearId) {
        System.out.println("[ViewClassesUseCase] Getting classes for academic year: " + academicYearId);
        return classRepository.findByAcademicYearId(academicYearId);
    }
}