package com.example.data;

import com.example.domain.TouristAccount;
import java.util.List;
import java.util.Map;

/**
 * Generic data source interface for tourist accounts.
 */
public interface IDataSource {
    List<TouristAccount> findAllTourists();
    List<TouristAccount> findTouristsByCriteria(Map<String, Object> criteria);
}