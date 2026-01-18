package com.example.application;

import com.example.domain.RefreshmentPoint;
import com.example.domain.StatisticsReport;
import com.example.domain.Transaction;
import com.example.dto.StatisticsReportDTO;
import com.example.infrastructure.StatisticsRepository;
import java.util.Date;
import java.util.List;

/**
 * Service implementing the statistics use case.
 */
public class StatisticsService implements StatisticsUseCase {
    private StatisticsRepository statisticsRepository;

    public StatisticsService(StatisticsRepository repository) {
        this.statisticsRepository = repository;
    }

    @Override
    public StatisticsReportDTO getStatisticsForPoint(String pointId) {
        // Retrieve refreshment point
        RefreshmentPoint point = statisticsRepository.findRefreshmentPointById(pointId);
        if (point == null) {
            // If point not found, return DTO with default values
            // (Alternative flow: no data available)
            StatisticsReportDTO dto = new StatisticsReportDTO();
            dto.setPointName("Unknown Point");
            dto.setPeriodStart(new Date());
            dto.setPeriodEnd(new Date());
            dto.setTotalSales(0.0);
            dto.setAverageRating(0.0);
            dto.setPopularItems(java.util.Collections.emptyList());
            return dto;
        }

        // Determine period (last 30 days)
        Date endDate = new Date();
        Date startDate = new Date(endDate.getTime() - 30L * 24 * 60 * 60 * 1000);

        // Retrieve transactions
        List<Transaction> transactions = statisticsRepository.findTransactionsByPointId(pointId, startDate, endDate);

        // Create and calculate statistics
        StatisticsReport report = new StatisticsReport(pointId);
        report.calculateStatistics(transactions);

        // Create DTO and populate
        StatisticsReportDTO dto = new StatisticsReportDTO();
        dto.setPointName(point.getName());
        dto.setPeriodStart(report.getPeriodStart());
        dto.setPeriodEnd(report.getPeriodEnd());
        dto.setTotalSales(report.getTotalSales());
        dto.setAverageRating(report.getAverageRating());
        dto.setPopularItems(report.getPopularItems());

        return dto;
    }
}