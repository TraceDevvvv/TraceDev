package com.example.repository;

import com.example.model.Banner;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Banner data access.
 */
public interface BannerRepository {
    List<Banner> findByRefreshmentPointId(String pointId);
    boolean deleteById(String bannerId);
    Optional<Banner> findById(String bannerId);
}