package com.example.reportcard.infrastructure;

import com.example.reportcard.domain.ReportCard;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

// Simulating a DataSource, assuming it's provided elsewhere.
// For simplicity, we use an in‑memory map.
public class ReportCardRepositoryImpl implements ReportCardRepository {
    // Mock database: maps studentId to ReportCard
    private static final Map<String, ReportCard> inMemoryDb = new HashMap<>();
    // Simulate a DataSource field
    // private DataSource dataSource;

    @Override
    public Optional<ReportCard> findById(String studentId) {
        // Simulate database SELECT: return the report card if present
        ReportCard card = inMemoryDb.get(studentId);
        // For demo, if not found, we create a dummy one with empty grades
        if (card == null) {
            // This simulates "Report Card Not Found" scenario
            return Optional.empty();
        }
        return Optional.of(card);
    }
    
    public ReportCard selectReportCardWhereStudentId(String studentId) {
        return inMemoryDb.get(studentId);
    }
    
    public void updateReportCard(ReportCard reportCard) {
        inMemoryDb.put(reportCard.getStudentId(), reportCard);
    }

    @Override
    public ReportCard save(ReportCard reportCard) {
        // Simulate database UPDATE/INSERT
        inMemoryDb.put(reportCard.getStudentId(), reportCard);
        return reportCard;
    }
}