package com.example.repository;

import com.example.model.Position;

/**
 * Interface for GPS repositories.
 */
public interface GPSRepository {
    Position getCurrentPosition();
    boolean isAvailable();
    void setTimeout(long durationMs);
    void setMaxRetries(int count);
}