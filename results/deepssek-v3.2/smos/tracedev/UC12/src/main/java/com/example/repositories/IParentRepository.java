package com.example.repositories;

import com.example.entities.Parent;

/**
 * Interface for Parent repository operations.
 * Corresponds to IParentRepository in the class diagram.
 */
public interface IParentRepository {
    Parent findById(int id);
    void update(Parent parent);
}