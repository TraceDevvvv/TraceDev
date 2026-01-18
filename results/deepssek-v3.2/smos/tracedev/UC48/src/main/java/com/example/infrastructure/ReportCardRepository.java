package com.example.infrastructure;

import com.example.domain.ReportCard;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for report card persistence.
 */
public interface ReportCardRepository {
    Optional<ReportCard> findById(String id);
    boolean delete(String id);
    List<ReportCard> findAll();
}