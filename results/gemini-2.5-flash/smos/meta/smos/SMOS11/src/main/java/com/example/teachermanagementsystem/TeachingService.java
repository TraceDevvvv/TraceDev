package com.example.teachermanagementsystem;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service layer for managing Teaching data.
 * This class provides business logic and acts as an intermediary between the UI/controller
 * and the TeachingRepository. It encapsulates operations related to teachings.
 */
public class TeachingService {
    private final TeachingRepository teachingRepository;

    /**
     * Constructs a new TeachingService with a given TeachingRepository.
     * @param teachingRepository The repository to use for data access.
     */
    public TeachingService(TeachingRepository teachingRepository) {
        this.teachingRepository = teachingRepository;
    }

    /**
     * Retrieves a list of teachings available for a specific class.
     * @param classId The ID of the class to filter teachings by.
     * @return A list of {@code Teaching} objects. Returns an empty list if no teachings are found for the given class.
     */
    public List<Teaching> getTeachingsByClass(String classId) {
        return teachingRepository.findByClassId(classId);
    }

    /**
     * Finds a teaching by its unique identifier.
     * @param teachingId The ID of the teaching to find.
     * @return An {@code Optional} containing the {@code Teaching} if found,
     *         or an empty {@code Optional} if no teaching with the given ID exists.
     */
    public Optional<Teaching> getTeachingById(String teachingId) {
        return teachingRepository.findById(teachingId);
    }

    /**
     * Retrieves a list of teaching names for a specific class.
     * This can be useful for displaying options in a user interface.
     * @param classId The ID of the class.
     * @return A list of strings, each representing a teaching's name for the specified class.
     */
    public List<String> getTeachingNamesByClass(String classId) {
        return teachingRepository.findByClassId(classId).stream()
                .map(Teaching::getName)
                .collect(Collectors.toList());
    }
}