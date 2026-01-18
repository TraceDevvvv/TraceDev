package com.example.uml;

import java.util.List;

/**
 * Repository interface for banner data access.
 */
public interface IBannerRepository {
    Banner findById(String bannerId);
    List<Banner> findByRefreshmentPointId(String refreshmentPointId);
    boolean delete(String bannerId);
}