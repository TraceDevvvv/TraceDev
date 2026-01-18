package com.example.domain.repository;

import com.example.domain.Banner;
import java.util.List;
import java.util.Optional;

/**
 * Repository port for Banner entities.
 */
public interface BannerRepository {
    Optional<Banner> findById(String id);
    void delete(Banner banner);
    List<Banner> findByRefreshmentPointId(String refreshmentPointId);
}