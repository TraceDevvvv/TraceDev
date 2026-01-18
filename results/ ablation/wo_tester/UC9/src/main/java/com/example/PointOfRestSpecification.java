package com.example;

/**
 * Specification interface for evaluating PointOfRest objects.
 */
public interface PointOfRestSpecification {
    /**
     * Checks if the given PointOfRest satisfies the specification.
     * @param pointOfRest the point of rest to evaluate
     * @return true if satisfied, false otherwise
     */
    boolean isSatisfiedBy(PointOfRest pointOfRest);

    /**
     * Evaluates criteria against the PointOfRest.
     * @param pointOfRest the point of rest to evaluate
     * @return true if criteria match, false otherwise
     */
    boolean evaluateCriteria(PointOfRest pointOfRest);
}