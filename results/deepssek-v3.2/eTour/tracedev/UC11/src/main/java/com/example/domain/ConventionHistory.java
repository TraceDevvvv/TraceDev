package com.example.domain;

import java.util.List;

/**
 * Domain entity representing a collection of conventions.
 */
public class ConventionHistory {

    private final List<Convention> conventions;
    private final PointOfRest derivedFromPointOfRest;

    public ConventionHistory(List<Convention> conventions, PointOfRest derivedFromPointOfRest) {
        this.conventions = conventions;
        this.derivedFromPointOfRest = derivedFromPointOfRest;
    }

    public List<Convention> getConventions() {
        return conventions;
    }

    public PointOfRest getDerivedFromPointOfRest() {
        return derivedFromPointOfRest;
    }
}