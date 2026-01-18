package com.example.controllers;

import com.example.dtos.RegistrySummaryDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for listing registries.
 * Entry Condition: The system IS displaying the list of all registers.
 */
public class RegistryListController {
    public List<RegistrySummaryDTO> getRegistriesForAcademicYear(String academicYear) {
        // Simplified implementation - returns dummy data
        List<RegistrySummaryDTO> list = new ArrayList<>();
        list.add(new RegistrySummaryDTO("Class A", "Teacher Smith", 25));
        list.add(new RegistrySummaryDTO("Class B", "Teacher Johnson", 30));
        return list;
    }
}