
package com.example.repository;

import com.example.domain.TouristAccount;
import java.util.List;

/**
 * Repository interface for tourist account persistence.
 */
public interface ITouristAccountRepository {
    List<TouristAccount> findByCriteria(String criteria);
    String buildQueryFromCriteria(String criteria);
}
