package com.example.teachingapp.data;

import com.example.teachingapp.model.Teaching;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of TeachingDAO that simulates data retrieval from a data source.
 * This class provides mock data for teachings, allowing the application to run
 * without a real database connection. It's useful for development and testing.
 */
public class TeachingDAOImpl implements TeachingDAO {

    // A static list to simulate a database table of teachings.
    // This ensures that all instances of TeachingDAOImpl share the same data.
    private static final List<Teaching> teachings = new ArrayList<>();

    /**
     * Static initializer block to populate the mock data.
     * This data represents various teachings that an administrator might view.
     */
    static {
        teachings.add(new Teaching(
                "T001",
                "Introduction to Java Programming",
                "A beginner-friendly course covering the fundamentals of Java.",
                "Dr. Alice Smith",
                LocalDate.of(2023, 9, 1),
                LocalDate.of(2023, 12, 15),
                "Online",
                50,
                45
        ));
        teachings.add(new Teaching(
                "T002",
                "Advanced Data Structures",
                "Deep dive into complex data structures and algorithms.",
                "Prof. Bob Johnson",
                LocalDate.of(2023, 10, 1),
                LocalDate.of(2024, 1, 30),
                "Room 301, Main Campus",
                30,
                28
        ));
        teachings.add(new Teaching(
                "T003",
                "Web Development with Spring Boot",
                "Build robust web applications using the Spring Boot framework.",
                "Ms. Carol White",
                LocalDate.of(2024, 1, 10),
                LocalDate.of(2024, 4, 20),
                "Online",
                40,
                30
        ));
        teachings.add(new Teaching(
                "T004",
                "Database Management Systems",
                "Learn about relational databases, SQL, and database design.",
                "Dr. David Green",
                LocalDate.of(2023, 11, 5),
                LocalDate.of(2024, 2, 28),
                "Lab 105, Engineering Building",
                25,
                25 // Fully enrolled
        ));
    }

    /**
     * Retrieves a list of all available teachings from the mock data source.
     *
     * @return A list of Teaching objects. Returns an empty list if no teachings are found (though
     *         with static data, this should not happen unless the static block is empty).
     */
    @Override
    public List<Teaching> getAllTeachings() {
        // Return a new ArrayList to prevent external modification of the static list.
        return new ArrayList<>(teachings);
    }

    /**
     * Retrieves a single teaching by its unique identifier from the mock data source.
     * This method simulates fetching a specific teaching's details, which is central
     * to the "ViewTeachingDetails" use case.
     *
     * @param teachingId The unique ID of the teaching to retrieve.
     * @return An Optional containing the Teaching object if found, or an empty Optional if not found.
     */
    @Override
    public Optional<Teaching> findTeachingById(String teachingId) {
        // Use Java Stream API to efficiently find the teaching by its ID.
        // Optional is used to gracefully handle cases where no teaching matches the ID.
        return teachings.stream()
                .filter(teaching -> teaching.getTeachingId().equals(teachingId))
                .findFirst();
    }
}