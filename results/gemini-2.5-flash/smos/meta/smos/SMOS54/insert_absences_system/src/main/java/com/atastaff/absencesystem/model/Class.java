package com.atastaff.absencesystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.ArrayList;

/**
 * JPA Entity for Class, representing a class in the system.
 * This class maps to a 'classes' table in the database.
 */
@Entity
@Table(name = "classes")
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
public class Class {

    /**
     * Unique identifier for the class.
     * Marked as the primary key.
     */
    @Id
    private String classId;

    /**
     * The name of the class (e.g., "Math 101", "Grade 5A").
     */
    @Column(nullable = false)
    private String className;

    /**
     * A list of students belonging to this class.
     * One-to-many relationship: one class can have many students.
     * Mapped by the 'currentClass' field in the Student entity.
     * CascadeType.ALL ensures that if a Class is deleted, its associated Students are also deleted.
     * OrphanRemoval ensures that if a Student is removed from the 'students' list, it's also removed from the database.
     */
    @OneToMany(mappedBy = "currentClass", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<>();
}