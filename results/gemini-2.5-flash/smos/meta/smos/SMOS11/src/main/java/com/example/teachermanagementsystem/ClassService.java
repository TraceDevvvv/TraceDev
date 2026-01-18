package com.example.teachermanagementsystem;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service layer for managing Class data.
 * This class provides business logic and acts as an intermediary between the UI/controller
 * and the ClassRepository. It encapsulates operations related to classes.
 */
public class ClassService {
    private final ClassRepository classRepository;

    /**
     * Constructs a new ClassService with a given ClassRepository.
     * @param classRepository The repository to use for data access.
     */
    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    /**
     * Retrieves a list of classes available for a specific academic year.
     * @param academicYearId The ID of the academic year to filter classes by.
     * @return A list of {@code Class} objects. Returns an empty list if no classes are found for the given academic year.
     */
    public List<Class> getClassesByAcademicYear(String academicYearId) {
        return classRepository.findByAcademicYearId(academicYearId);
    }

    /**
     * Finds a class by its unique identifier.
     * @param classId The ID of the class to find.
     * @return An {@code Optional} containing the {@code Class} if found,
     *         or an empty {@code Optional} if no class with the given ID exists.
     */
    public Optional<Class> getClassById(String classId) {
        return classRepository.findById(classId);
    }

    /**
     * Retrieves a list of class names for a specific academic year.
     * This can be useful for displaying options in a user interface.
     * @param academicYearId The ID of the academic year.
     * @return A list of strings, each representing a class's name for the specified academic year.
     */
    public List<String> getClassNamesByAcademicYear(String academicYearId) {
        return classRepository.findByAcademicYearId(academicYearId).stream()
                .map(Class::getName)
                .collect(Collectors.toList());
    }
}