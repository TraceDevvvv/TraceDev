package com.example.application;

import com.example.domain.Tourist;
import java.util.List;

/**
 * Repository port for tourist data access.
 */
public interface ITouristRepository {
    List<Tourist> findByCriteria(SearchCriteria criteria);
}