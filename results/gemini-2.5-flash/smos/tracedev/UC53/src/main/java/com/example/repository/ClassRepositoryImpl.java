package com.example.repository;

import com.example.model.ClassEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of IClassRepository.
 * This class simulates data access to a database for ClassEntity objects.
 */
public class ClassRepositoryImpl implements IClassRepository {

    /**
     * Simulates fetching all class entities from a database.
     * In a real application, this would interact with a database,
     * but here it returns a hardcoded list.
     * Corresponds to the "ClassRepository -> DB : query data for classes" interaction.
     *
     * @return A list of simulated ClassEntity objects.
     */
    @Override
    public List<ClassEntity> findAll() {
        // Simulate database access delay or complexity
        System.out.println("  ClassRepository: Querying data for classes from simulated database (m5).");
        List<ClassEntity> classes = new ArrayList<>();
        classes.add(new ClassEntity("C001", "Introduction to Java", "Fundamentals of Java programming.", "JAVA-INTRO"));
        classes.add(new ClassEntity("C002", "Advanced Algorithms", "Complex data structures and algorithms.", "ALGO-ADV"));
        classes.add(new ClassEntity("C003", "Web Development with Spring", "Building web applications using Spring Boot.", "WEB-SPRING"));
        System.out.println("  ClassRepository: Retrieved " + classes.size() + " class entities.");
        return classes;
    }
}