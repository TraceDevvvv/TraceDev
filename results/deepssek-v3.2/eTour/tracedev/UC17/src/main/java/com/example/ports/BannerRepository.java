package com.example.ports;

import com.example.domain.Banner;
import java.util.List;

/**
 * Repository interface for Banner entity.
 */
public interface BannerRepository {
    List<Banner> findByRestPointId(String restPointId);
    Banner findById(String bannerId);
    Banner save(Banner banner);
}