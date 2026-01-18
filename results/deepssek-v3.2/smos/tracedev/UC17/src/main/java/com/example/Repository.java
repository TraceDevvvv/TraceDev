package com.example;

import java.util.List;

/**
 * Generic repository interface.
 */
public interface Repository<T> {
    List<T> findAll();
}