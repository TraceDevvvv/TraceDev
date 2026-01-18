package com.example.teachermanagementsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Simulated repository for Teaching data.
 * Provides methods to access and manage Teaching objects.
 * This class mimics a database interaction layer for Teaching entities.
 */
public class TeachingRepository {
    // A map to store Teaching objects, simulating a database table.
    // Key: Teaching ID, Value: Teaching object.
    private final Map<String, Teaching> teachings = new HashMap<>();

    /**
     * Constructs a new TeachingRepository and populates it with initial dummy data.
     * This data serves for demonstration purposes in a simulated environment.
     */
    public TeachingRepository() {
        // Populate with dummy data for demonstration
        // Ensure classIds match those in ClassRepository
        teachings.put("T001", new Teaching("T001", "Mathematics", "C101"));
        teachings.put("T002", new Teaching("T002", "Physics", "C101"));
        teachings.put("T003", new Teaching("T003", "Chemistry", "C101"));
        teachings.put("T004", new Teaching("T004", "Literature", "C102"));
        teachings.put("T005", new Teaching("T005", "History", "C102"));
        teachings.put("T006", new Teaching("T006", "Advanced Math", "C103"));
        teachings.put("T007", new Teaching("T007", "Biology", "C201"));
        teachings.put("T008", new Teaching("T008", "Computer Science", "C202"));
        teachings.put("T009", new Teaching("T009", "Art History", "C301"));
        teachings.put("T010", new Teaching("T010", "Economics", "C302"));
    }

    /**
     * Finds teachings associated with a specific class ID.
     * @param classId The ID of the class to filter teachings by.
     * @return A new {@code ArrayList} containing {@code Teaching} objects that belong to the specified class.
     *         Returns an empty list if no teachings are found for the given class ID.
     */
    public List<Teaching> findByClassId(String classId) {
        return teachings.values().stream()
                .filter(t -> t.getClassId().equals(classId))
                .collect(Collectors.toList());
    }

    /**
     * Finds a teaching by its unique identifier.
     * @param id The unique ID of the teaching to find.
     * @return An {@code Optional} containing the {@code Teaching} if found,
     *         or an empty {@code Optional} if no teaching with the given ID exists.
     */
    public Optional<Teaching> findById(String id) {
        return Optional.ofNullable(teachings.get(id));
    }
}