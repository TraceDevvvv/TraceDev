package repository;

import domain.Teacher;

import java.util.HashMap;
import java.util.Map;

/**
 * Concrete implementation of ITeacherRepository.
 * This class simulates a data store for Teacher entities.
 */
public class TeacherRepository implements ITeacherRepository {

    // A simple in-memory map to simulate a database for demonstration purposes.
    private final Map<String, Teacher> teachersStore = new HashMap<>();

    public TeacherRepository() {
        // Initialize with some dummy data
        teachersStore.put("teacher201", new Teacher("teacher201", "Ms. Davis"));
        teachersStore.put("teacher202", new Teacher("teacher202", "Mr. Green"));
        teachersStore.put("teacher203", new Teacher("teacher203", "Dr. White"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Teacher getTeacherById(String teacherId) {
        System.out.println("[TeacherRepository] Retrieving teacher with ID: " + teacherId);
        return teachersStore.get(teacherId);
    }
}