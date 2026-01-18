package com.example.repository;

import com.example.entities.Convention;
import java.util.List;

/**
 * Interface for Convention Repository as per class diagram.
 */
public interface ConventionRepository {
    List<Convention> findByRestaurantId(String restaurantId);
}