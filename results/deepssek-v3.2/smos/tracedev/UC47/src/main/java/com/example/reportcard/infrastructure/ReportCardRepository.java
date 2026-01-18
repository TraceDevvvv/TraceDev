package com.example.reportcard.infrastructure;

import com.example.reportcard.domain.ReportCard;
import java.util.Optional;

/**
 * Repository interface for persisting report cards.
 */
public interface ReportCardRepository {
    Optional<ReportCard> findById(String studentId);
    ReportCard save(ReportCard reportCard);
}