package com.system.repository;

import com.system.domain.CulturalGood;

/**
 * Repository interface for CulturalGood persistence operations.
 */
public interface ICulturalGoodRepository {
    CulturalGood findById(String id);
    CulturalGood save(CulturalGood culturalGood);
}