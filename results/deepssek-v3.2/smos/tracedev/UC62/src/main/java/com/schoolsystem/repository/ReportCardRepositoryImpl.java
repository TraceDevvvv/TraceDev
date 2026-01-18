package com.schoolsystem.repository;

import com.schoolsystem.domain.ReportCard;
import com.schoolsystem.infrastructure.SMOSServerClient;
import com.schoolsystem.cache.Cache;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Concrete implementation of IReportCardRepository.
 * Uses Cache and SMOSServerClient to fetch data.
 */
public class ReportCardRepositoryImpl implements IReportCardRepository {
    private SMOSServerClient smosServerClient;
    private Cache cache;
    private DataSource dataSource;

    public ReportCardRepositoryImpl(SMOSServerClient smosServerClient, Cache cache, DataSource dataSource) {
        this.smosServerClient = smosServerClient;
        this.cache = cache;
        this.dataSource = dataSource;
    }

    @Override
    public List<ReportCard> findAllByStudentId(String studentId) {
        // Check cache first
        if (cache.isCacheAvailable("list_" + studentId)) {
            List<ReportCard> cached = cache.getCachedReportList(studentId);
            if (cached != null) {
                return cached;
            }
        }

        // If not in cache, fetch from server
        if (!smosServerClient.connect()) {
            throw new RuntimeException("Server unavailable");
        }
        String rawData = smosServerClient.fetchReportData(studentId);
        List<ReportCard> reportCards = parseDataToReportCards(rawData, studentId);
        // Cache the result
        cache.cacheReportList(studentId, reportCards);
        return reportCards;
    }

    @Override
    public ReportCard findById(String reportId) {
        // Check cache first
        if (cache.isCacheAvailable("report_" + reportId)) {
            ReportCard cached = cache.getCachedReport(reportId);
            if (cached != null) {
                return cached;
            }
        }

        // If not in cache, fetch from server
        if (!smosServerClient.connect()) {
            throw new RuntimeException("Cannot fetch report details");
        }
        String rawData = smosServerClient.fetchDetailedReport(reportId);
        ReportCard reportCard = parseDataToReportCard(rawData);
        // Cache the result
        cache.cacheReport(reportId, reportCard);
        return reportCard;
    }

    /**
     * Parses raw data (e.g., JSON/XML) into a list of ReportCard objects.
     * For simplicity, we simulate parsing.
     */
    private List<ReportCard> parseDataToReportCards(String rawData, String studentId) {
        List<ReportCard> list = new ArrayList<>();
        // Simulated parsing: create dummy report cards
        Map<String, String> grades = new HashMap<>();
        grades.put("Math", "85");
        grades.put("Science", "92");
        list.add(new ReportCard("R001", studentId, "Fall", 2024, grades));
        list.add(new ReportCard("R002", studentId, "Spring", 2024, grades));
        return list;
    }

    /**
     * Parses raw detailed data into a ReportCard object.
     */
    private ReportCard parseDataToReportCard(String rawData) {
        // Simulated parsing
        Map<String, String> grades = new HashMap<>();
        grades.put("Math", "85");
        grades.put("Science", "92");
        grades.put("History", "78");
        grades.put("English", "88");
        return new ReportCard("R001", "S123", "Fall", 2024, grades);
    }
}