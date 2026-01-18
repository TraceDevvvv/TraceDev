package com.etour.infrastructure;

import com.etour.domain.Favorite;
import java.util.List;

/**
 * Repository interface for Favorite entities.
 * Part of Infrastructure Layer.
 */
public interface IFavoriteRepository {
    List<Favorite> findAllByTouristId(String touristId) throws Exception;
}