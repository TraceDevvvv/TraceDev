package com.example.presentation;

import com.example.application.StatisticsUseCase;
import com.example.dto.StatisticsReportDTO;

/**
 * Controller for statistics presentation.
 */
public class StatisticsController {
    private StatisticsUseCase statisticsUseCase;

    public StatisticsController(StatisticsUseCase useCase) {
        this.statisticsUseCase = useCase;
    }

    public StatisticsReportDTO displayStatistics(String pointId) {
        // Delegates to use case
        return statisticsUseCase.getStatisticsForPoint(pointId);
    }
}