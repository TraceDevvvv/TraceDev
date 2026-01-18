package com.example.monitoring.domain;

import java.time.LocalDate;

/**
 * Domain Layer: Represents a Value Object for a school year.
 * This object ensures a consistent representation of a school year throughout the domain.
 * It is immutable.
 */
public class SchoolYear {
    private final int year;

    /**
     * Constructor for SchoolYear.
     * @param year The calendar year representing the school year (e.g., 2023 for 2023-2024).
     */
    public SchoolYear(int year) {
        // Basic validation: A school year should typically be a reasonable positive number.
        if (year <= 1900 || year > 2100) { // Arbitrary reasonable range
            throw new IllegalArgumentException("Invalid school year: " + year);
        }
        this.year = year;
    }

    /**
     * Static method to get the current school year.
     * Assumption: For simplicity, the current year is considered the current school year.
     * In a real system, school years might span across calendar years (e.g., Sept 2023 - June 2024).
     * This method would then contain more complex logic to determine the current academic year.
     *
     * @return The current calendar year as an integer.
     */
    public static int getCurrentYear() {
        System.out.println("SchoolYear: Getting current year.");
        return LocalDate.now().getYear(); // Returns the current calendar year
    }

    /**
     * @return The integer value of the school year.
     */
    public int getYear() {
        return year;
    }

    // As a Value Object, it should ideally override equals() and hashCode() for proper comparison.
    // This is omitted for brevity but is a good practice for Value Objects.
}