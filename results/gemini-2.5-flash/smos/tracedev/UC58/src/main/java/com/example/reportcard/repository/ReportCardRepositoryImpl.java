package com.example.reportcard.repository;

import com.example.reportcard.domain.ReportCard;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Mock implementation of IReportCardRepository for demonstration purposes.
 * Simulates an in-memory database.
 */
public class ReportCardRepositoryImpl implements IReportCardRepository {
    private Map<String, ReportCard> reportCards = new HashMap<>();

    public ReportCardRepositoryImpl() {
        // Seed data: ReportCard ID, Student ID, Academic Year ID, Quarter, Grades Map
        reportCards.put("RC001", new ReportCard("RC001", "S101", "AY2023", "Q1",
                new HashMap<>(Map.of("Math", "A", "Physics", "B+"))));
        reportCards.put("RC002", new ReportCard("RC002", "S101", "AY2023", "Q2",
                new HashMap<>(Map.of("Math", "A-", "Physics", "A"))));
        reportCards.put("RC003", new ReportCard("RC003", "S102", "AY2023", "Q1",
                new HashMap<>(Map.of("Math", "B", "Physics", "C+"))));
        reportCards.put("RC004", new ReportCard("RC004", "S103", "AY2024", "Q1",
                new HashMap<>(Map.of("Chemistry", "B", "Biology", "A-"))));
    }

    @Override
    public ReportCard findByStudentYearAndQuarter(String studentId, String academicYearId, String quarter) {
        // Simulate a composite key lookup
        return reportCards.values().stream()
                .filter(rc -> rc.getStudentId().equals(studentId) &&
                               rc.getAcademicYearId().equals(academicYearId) &&
                               rc.getQuarter().equals(quarter))
                .findFirst()
                .orElse(null);
    }
}