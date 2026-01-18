package com.example.application;

/**
 * Use case interface for viewing personal statistics.
 */
public interface IViewPersonalStatisticsUseCase {
    com.example.presentation.StatisticsViewModel execute(String operatorId);
}