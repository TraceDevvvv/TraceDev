package com.example.banner.domain.port;

import com.example.banner.domain.model.Banner;
import java.util.List;
import java.util.Optional;

/**
 * Repository port for Banner persistence operations.
 */
public interface BannerRepository {
    List<Banner> findByPointOfRestId(String pointOfRestId);
    Optional<Banner> findById(String id);
    void delete(Banner banner);
    void deleteById(String id);
}