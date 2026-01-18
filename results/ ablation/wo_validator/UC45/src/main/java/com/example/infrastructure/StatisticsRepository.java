package com.example.infrastructure;

import com.example.domain.RefreshmentPoint;
import com.example.domain.Transaction;
import java.util.Date;
import java.util.List;

/**
 * Repository interface for statistics data access.
 */
public interface StatisticsRepository {
    List<Transaction> findTransactionsByPointId(String pointId, Date startDate, Date endDate);
    RefreshmentPoint findRefreshmentPointById(String pointId);
}