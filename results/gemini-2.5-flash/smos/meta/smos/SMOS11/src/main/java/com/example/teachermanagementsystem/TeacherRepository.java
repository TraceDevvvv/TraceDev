package com.example.teachermanagementsystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;

/**
 * Simulated repository for Teacher data.
 * Provides methods to access and manage Teacher objects.
 * This class mimics a database interaction layer for Teacher entities.
 */
public class TeacherRepository {
    // A map to store Teacher objects, simulating a database table.
    // Key: Teacher ID, Value: Teacher object.
    private final Map<String, Teacher> teachers = new HashMap<>();

    /**
     * Constructs a new TeacherRepository and populates it with initial dummy data.
     * This data serves for demonstration purposes in a simulated environment.
     * It includes teachers with some pre-assigned teachings to simulate existing relationships.
     */
    public TeacherRepository() {
        // Populate with dummy data for demonstration
        // Create some dummy teachings to assign to teachers.
        // These teachings should correspond to existing ones in TeachingRepository for consistency.
        Teaching mathC101 = new Teaching("T001", "Mathematics", "C101");
        Teaching physicsC101 = new Teaching("T002", "Physics", "C101");
        Teaching literatureC102 = new Teaching("T004", "Literature", "C102");
        Teaching historyC102 = new Teaching("T005", "History", "C102");
        Teaching advancedMathC103 = new Teaching("T006", "Advanced Math", "C103");
        Teaching biologyC201 = new Teaching("T007", "Biology", "C201");

        // Teacher 1: Alice Smith
        Teacher teacher1 = new Teacher("TH001", "Alice Smith");
        teacher1.assignTeaching(mathC101);
        teacher1.assignTeaching(physicsC101);
        teachers.put(teacher1.getId(), teacher1);

        // Teacher 2: Bob Johnson
        Teacher teacher2 = new Teacher("TH002", "Bob Johnson");
        teacher2.assignTeaching(literatureC102);
        teachers.put(teacher2.getId(), teacher2);

        // Teacher 3: Charlie Brown
        Teacher teacher3 = new Teacher("TH003", "Charlie Brown");
        teacher3.assignTeaching(historyC102);
        teacher3.assignTeaching(advancedMathC103);
        teachers.put(teacher3.getId(), teacher3);

        // Teacher 4: Diana Prince (no initial teachings)
        Teacher teacher4 = new Teacher("TH004", "Diana Prince");
        teachers.put(teacher4.getId(), teacher4);

        // Teacher 5: Eve Adams
        Teacher teacher5 = new Teacher("TH005", "Eve Adams");
        teacher5.assignTeaching(biologyC201);
        teachers.put(teacher5.getId(), teacher5);
    }

    /**
     * Finds a teacher by their unique identifier.
     * @param id The unique ID of the teacher to find.
     * @return An {@code Optional} containing the {@code Teacher} if found,
     *         or an empty {@code Optional} if no teacher with the given ID exists.
     */
    public Optional<Teacher> findById(String id) {
        return Optional.ofNullable(teachers.get(id));
    }

    /**
     * Retrieves all teachers currently stored in the repository.
     * @return A new {@code ArrayList} containing all {@code Teacher} objects.
     *         Returns an empty list if no teachers are present.
     */
    public List<Teacher> findAll() {
        return new ArrayList<>(teachers.values());
    }

    /**
     * Saves a teacher's state to the repository.
     * In this simulated repository, it updates an existing teacher or adds a new one.
     * For the purpose of this use case (assign/remove teachings), this method is crucial
     * for persisting changes to a teacher's assigned teachings.
     * @param teacher The {@code Teacher} object to save.
     */
    public void save(Teacher teacher) {
        if (teacher != null) {
            teachers.put(teacher.getId(), teacher);
        }
    }
}