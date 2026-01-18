package com.example.entities;

import java.util.List;

/**
 * Represents a registry (class register).
 */
public class Registry {
    private String academicYear;
    private String className;
    private List<RegistryEntry> registryEntries;

    public Registry(String academicYear, String className, List<RegistryEntry> registryEntries) {
        this.academicYear = academicYear;
        this.className = className;
        this.registryEntries = registryEntries;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public String getClassName() {
        return className;
    }

    public List<RegistryEntry> getRegistryEntries() {
        return registryEntries;
    }

    public void addRegistryEntry(RegistryEntry entry) {
        registryEntries.add(entry);
    }

    public void updateRegistryEntry(RegistryEntry entry) {
        // Simplified update - remove old and add new
        registryEntries.removeIf(e -> e.getDate().equals(entry.getDate()));
        registryEntries.add(entry);
    }
}