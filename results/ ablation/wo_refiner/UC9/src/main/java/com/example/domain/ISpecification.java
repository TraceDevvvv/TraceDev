package com.example.domain;

/**
 * Generic specification interface used for domain-driven design criteria.
 */
public interface ISpecification<T> {
    /**
     * Determines if the entity satisfies the specification.
     * @param entity the domain entity to test
     * @return true if satisfied, false otherwise
     */
    boolean isSatisfiedBy(T entity);
}