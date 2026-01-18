package com.example.application;

import com.example.dto.StatisticsReportDTO;

/**
 * Interface for the statistics use case.
 */
public interface StatisticsUseCase {
    StatisticsReportDTO getStatisticsForPoint(String pointId);
}