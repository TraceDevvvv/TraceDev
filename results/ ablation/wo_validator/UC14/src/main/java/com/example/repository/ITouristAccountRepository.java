package com.example.repository;

import com.example.entity.TouristAccount;
import com.example.dto.TouristAccountSearchCriteriaDTO;
import java.util.List;

/**
 * Repository interface for tourist account data access.
 */
public interface ITouristAccountRepository {
    /**
     * Finds all tourist accounts matching the given search criteria.
     * @param searchCriteria the search criteria
     * @return list of matching tourist accounts
     */
    List<TouristAccount> findAll(TouristAccountSearchCriteriaDTO searchCriteria);
}