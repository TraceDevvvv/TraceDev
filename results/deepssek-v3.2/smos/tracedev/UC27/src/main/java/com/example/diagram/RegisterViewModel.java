package com.example.diagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ViewModel for the register view, handling data and business logic.
 */
public class RegisterViewModel {
    private RegisterUseCaseController controller;
    private String selectedYear;

    public RegisterViewModel(RegisterUseCaseController controller) {
        this.controller = controller;
    }

    public RegisterUseCaseController getController() {
        return controller;
    }

    public void setController(RegisterUseCaseController controller) {
        this.controller = controller;
    }

    public String getSelectedYear() {
        return selectedYear;
    }

    public void setSelectedYear(String selectedYear) {
        this.selectedYear = selectedYear;
    }

    /**
     * Gets the list of available academic years.
     * @return list of academic years.
     * Assumption: returns a hardcoded list for demo.
     */
    public List<String> getAcademicYears() {
        List<String> years = new ArrayList<>();
        years.add("2023-2024");
        years.add("2024-2025");
        years.add("2025-2026");
        return years;
    }

    /**
     * Selects an academic year and stores it.
     * @param year the academic year to select.
     */
    public void selectAcademicYear(String year) {
        this.selectedYear = year;
        System.out.println("Academic year selected: " + year);
    }

    /**
     * Loads registers for the currently selected academic year.
     * @return list of RegisterDTO objects.
     */
    public List<RegisterDTO> loadRegistersForYear() {
        if (selectedYear == null) {
            throw new IllegalStateException("No academic year selected.");
        }
        return controller.getRegistersByAcademicYear(selectedYear);
    }

    /**
     * Organizes registers by class to satisfy requirement REQ-005.
     * This corresponds to sequence message m22: organize by class.
     * @param registers the list of RegisterDTO objects.
     * @return a map grouping registers by class name.
     */
    public Map<String, List<RegisterDTO>> organizeRegistersByClass(List<RegisterDTO> registers) {
        System.out.println("Organizing registers by class");
        Map<String, List<RegisterDTO>> grouped = new HashMap<>();
        for (RegisterDTO reg : registers) {
            grouped.computeIfAbsent(reg.getClassName(), k -> new ArrayList<>()).add(reg);
        }
        return grouped;
    }
}