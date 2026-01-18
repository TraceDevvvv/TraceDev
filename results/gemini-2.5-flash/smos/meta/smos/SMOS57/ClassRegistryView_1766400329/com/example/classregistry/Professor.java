package com.example.classregistry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a Professor actor in the system.
 * A professor has an ID, a name, and a list of classes they teach.
 */
public class Professor {
    private String professorId;
    private String name;
    private List<ClassInfo> taughtClasses;

    /**
     * Constructs a new Professor instance.
     * @param professorId The unique ID of the professor.
     * @param name The name of the professor.
     */
    public Professor(String professorId, String name) {
        this.professorId = professorId;
        this.name = name;
        this.taughtClasses = new ArrayList<>();
    }

    /**
     * Returns the professor's ID.
     * @return The professor's ID.
     */
    public String getProfessorId() {
        return professorId;
    }

    /**
     * Returns the professor's name.
     * @return The professor's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Adds a class to the list of classes taught by this professor.
     * @param classInfo The ClassInfo object representing the class to add.
     */
    public void addTaughtClass(ClassInfo classInfo) {
        if (classInfo != null && !taughtClasses.contains(classInfo)) {
            this.taughtClasses.add(classInfo);
        }
    }

    /**
     * Returns an unmodifiable list of classes taught by this professor.
     * @return A list of ClassInfo objects.
     */
    public List<ClassInfo> getTaughtClasses() {
        return Collections.unmodifiableList(taughtClasses);
    }

    @Override
    public String toString() {
        return "Professor{" +
               "professorId='" + professorId + '\'' +
               ", name='" + name + '\'' +
               ", taughtClasses=" + taughtClasses.size() + " classes" +
               '}';
    }
}