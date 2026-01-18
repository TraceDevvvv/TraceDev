package com.example.viewmodel;

import com.example.service.StatisticsService;
import com.example.dto.StatisticsDTO;

/**
 * ViewModel for StatisticsView, handles business logic and state.
 * Depends on StatisticsService to fetch data.
 */
public class StatisticsViewModel {
    private String currentOperatorId; // Added per REQ-004, REQ-007
    private StatisticsService statisticsService;
    private StatisticsView view; // Assuming view reference for callbacks

    public StatisticsViewModel(String currentOperatorId, StatisticsService statisticsService) {
        this.currentOperatorId = currentOperatorId;
        this.statisticsService = statisticsService;
    }

    public void setView(StatisticsView view) {
        this.view = view;
    }

    /**
     * Trigger loading of personal statistics for the current operator.
     * Called by the view.
     * This corresponds to sequence diagram message "1. Select personal statistics feature"
     * which goes from Operator to View, but view calls this method.
     */
    public void loadPersonalStatistics() {
        if (view != null) {
            view.showLoadingIndicator();
        }
        new Thread(() -> {
            try {
                StatisticsDTO dto = statisticsService.getPersonalStatistics(currentOperatorId);
                onStatisticsLoaded(dto);
            } catch (Exception e) {
                onError("Failed to load statistics: " + e.getMessage());
            }
        }).start();
    }

    /**
     * Called when statistics are successfully loaded.
     */
    public void onStatisticsLoaded(StatisticsDTO statisticsDto) {
        if (view != null) {
            view.onStatisticsLoaded(statisticsDto);
        }
    }

    /**
     * Called when an error occurs during loading.
     */
    public void onError(String errorMessage) {
        if (view != null) {
            view.onError(errorMessage);
        }
    }
    
    public interface StatisticsView {
        void showLoadingIndicator();
        void onStatisticsLoaded(StatisticsDTO statisticsDto);
        void onError(String errorMessage);
    }
}