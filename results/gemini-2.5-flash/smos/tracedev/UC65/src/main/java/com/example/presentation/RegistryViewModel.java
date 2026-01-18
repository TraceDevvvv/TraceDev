package com.example.presentation;

import java.util.List;

/**
 * ViewModel for displaying class registry data.
 * Contains aggregated and formatted data suitable for the UI.
 */
public class RegistryViewModel {
    public String className;
    public String academicYear;
    public List<RegistryEntryViewModel> registryEntries;

    /**
     * Constructs a RegistryViewModel.
     * @param className The name of the class.
     * @param academicYear The academic year.
     * @param registryEntries A list of registry entries for the class.
     */
    public RegistryViewModel(String className, String academicYear, List<RegistryEntryViewModel> registryEntries) {
        this.className = className;
        this.academicYear = academicYear;
        this.registryEntries = registryEntries;
    }

    // Getters for ViewModel properties
    public String getClassName() {
        return className;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public List<RegistryEntryViewModel> getRegistryEntries() {
        return registryEntries;
    }
}