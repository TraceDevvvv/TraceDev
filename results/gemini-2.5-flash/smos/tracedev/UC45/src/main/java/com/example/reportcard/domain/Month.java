package com.example.reportcard.domain;

import java.util.Objects;

/**
 * Represents a month in the domain model, used for selecting report periods.
 */
public class Month {
    public String name; // e.g., "Jan", "Feb"
    public int number;  // e.g., 1 for January, 2 for February

    /**
     * Constructs a new Month.
     *
     * @param name The name of the month (e.g., "Jan").
     * @param number The numerical representation of the month (1-12).
     */
    public Month(String name, int number) {
        this.name = name;
        this.number = number;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return name; // Simple representation for display
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Month month = (Month) o;
        return number == month.number && Objects.equals(name, month.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, number);
    }
}