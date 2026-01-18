package com.example.presentation;

import java.util.Arrays;
import java.util.List;

/**
 * ViewModel for displaying available academic year registries.
 * Added to satisfy requirement R5.
 */
public class AcademicYearRegistryList {

    /**
     * Retrieves a list of available class IDs for the current academic year.
     *
     * @return A list of strings representing class IDs.
     */
    public List<String> getAvailableClassIds() {
        // --- Assumption: For simulation purposes, return a dummy list of class IDs. ---
        System.out.println("[AcademicYearRegistryList] Retrieving available class IDs... [class101, class102] (R5).");
        return Arrays.asList("class101", "class102");
    }
}