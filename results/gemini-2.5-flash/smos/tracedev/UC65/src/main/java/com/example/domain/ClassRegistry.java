package com.example.domain;

import java.util.List;
import java.util.ArrayList;

/**
 * Domain model for a Class Registry.
 * Represents the complete attendance and status records for a class over an academic period.
 */
public class ClassRegistry {
    private String id;
    private String className;
    private String academicYear;
    private List<RegistryEntry> entries;

    /**
     * Constructs a ClassRegistry object.
     * @param id The unique ID of the class registry.
     * @param className The name of the class.
     * @param academicYear The academic year for this registry.
     * @param entries A list of registry entries.
     */
    public ClassRegistry(String id, String className, String academicYear, List<RegistryEntry> entries) {
        this.id = id;
        this.className = className;
        this.academicYear = academicYear;
        this.entries = entries != null ? entries : new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public List<RegistryEntry> getEntries() {
        return entries;
    }

    // Methods from UML diagram
    public String getClassId() {
        return id; // Assuming 'id' is the 'classId' in the context of the diagram.
    }

    public void addEntry(RegistryEntry entry) {
        this.entries.add(entry);
    }
}