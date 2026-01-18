package com.example.repository;

import com.example.model.ReportCard;
import java.util.List;

/**
 * Repository interface for ReportCard entities.
 */
public interface ReportCardRepository {
    ReportCard findReport(String studentId, List<String> months);
}