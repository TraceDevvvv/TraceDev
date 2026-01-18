package com.etour.banner;

import java.util.List;

/**
 * BannerRepository - interface for persisting and retrieving banners.
 */
public interface BannerRepository {
    void save(Banner banner);
    List<Banner> findByRestPointId(String restPointId);
    int countByRestPointId(String restPointId);
}