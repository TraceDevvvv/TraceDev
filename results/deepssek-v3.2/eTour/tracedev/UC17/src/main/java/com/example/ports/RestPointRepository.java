package com.example.ports;

import com.example.domain.RestPoint;
import java.util.List;

/**
 * Repository interface for RestPoint entity.
 */
public interface RestPointRepository {
    List<RestPoint> findAll();
    RestPoint findById(String restPointId);
}