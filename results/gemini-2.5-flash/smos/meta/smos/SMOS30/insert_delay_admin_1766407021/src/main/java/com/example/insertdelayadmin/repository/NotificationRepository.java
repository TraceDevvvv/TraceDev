package com.example.insertdelayadmin.repository;

import com.example.insertdelayadmin.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link Notification} entities.
 * Extends JpaRepository to provide standard CRUD operations.
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, String> {
    // No custom methods needed for now, JpaRepository provides basic CRUD.
}