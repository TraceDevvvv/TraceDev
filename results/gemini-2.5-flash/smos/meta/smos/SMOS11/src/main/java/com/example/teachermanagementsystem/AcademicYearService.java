package com.example.teachermanagementsystem;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service layer for managing AcademicYear data.
 * This class provides business logic and acts as an intermediary between the UI/controller
 * and the AcademicYearRepository. It encapsulates operations related to academic years.
 */
public class AcademicYearService {
    private final AcademicYearRepository academicYearRepository;

    /**
     * Constructs a new AcademicYearService with a given AcademicYearRepository.
     * @param academicYearRepository The repository to use for data access.
     */
    public AcademicYearService(AcademicYearRepository academicYearRepository) {
        this.academicYearRepository = academicYearRepository;
    }

    /**
     * Retrieves a list of all available academic years.
     * @return A list of {@code AcademicYear} objects. Returns an empty list if no academic years are found.
     */
    public List<AcademicYear> getAllAcademicYears() {
        return academicYearRepository.findAll();
    }

    /**
     * Finds an academic year by its unique identifier.
     * @param academicYearId The ID of the academic year to find.
     * @return An {@code Optional} containing the {@code AcademicYear} if found,
     *         or an empty {@code Optional} if no academic year with the given ID exists.
     */
    public Optional<AcademicYear> getAcademicYearById(String academicYearId) {
        return academicYearRepository.findById(academicYearId);
    }

    /**
     * Retrieves a list of academic year names.
     * This can be useful for displaying options in a user interface.
     * @return A list of strings, each representing an academic year's name.
     */
    public List<String> getAcademicYearNames() {
        return academicYearRepository.findAll().stream()
                .map(AcademicYear::getYearName)
                .collect(Collectors.toList());
    }
}