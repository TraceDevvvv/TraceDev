package com.example.domain;

/**
 * Represents a numeric range with min and max values.
 */
public class Range {
    private double min;
    private double max;

    public Range(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    // Checks if the range is valid (min <= max)
    public boolean isValid() {
        return min <= max;
    }
}