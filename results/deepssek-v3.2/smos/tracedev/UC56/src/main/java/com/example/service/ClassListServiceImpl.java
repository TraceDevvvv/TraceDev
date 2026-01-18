package com.example.service;

import com.example.dto.AcademicYearDTO;
import com.example.dto.ClassDTO;
import com.example.repository.AcademicYearRepository;
import com.example.repository.ClassRepository;
import com.example.model.AcademicYear;
import com.example.model.Class;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of ClassListService.
 */
public class ClassListServiceImpl implements ClassListService {
    private AcademicYearRepository academicYearRepository;
    private ClassRepository classRepository;

    public ClassListServiceImpl(AcademicYearRepository academicYearRepository, ClassRepository classRepository) {
        this.academicYearRepository = academicYearRepository;
        this.classRepository = classRepository;
    }

    @Override
    public List<AcademicYearDTO> getAcademicYearsForProfessor(String professorId) {
        // Fetch academic years from repository
        List<AcademicYear> years = academicYearRepository.findYearsByProfessorId(professorId);
        // Transform to DTOs
        return years.stream()
                .map(AcademicYear::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClassDTO> getClassesForProfessorAndYear(String professorId, String academicYearId) {
        // Fetch classes from repository
        List<Class> classes = classRepository.findClassesByProfessorAndYear(professorId, academicYearId);
        // Transform to DTOs
        return classes.stream()
                .map(Class::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Method corresponding to sequence diagram message "transform to DTOs".
     * This is already performed inside getAcademicYearsForProfessor and getClassesForProfessorAndYear.
     * Providing a separate method for clarity.
     */
    public List<AcademicYearDTO> transformToDTOs(List<AcademicYear> years) {
        return years.stream()
                .map(AcademicYear::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Overloaded method for transforming Class entities to DTOs.
     */
    public List<ClassDTO> transformToDTOsClasses(List<Class> classes) {
        return classes.stream()
                .map(Class::toDTO)
                .collect(Collectors.toList());
    }
}