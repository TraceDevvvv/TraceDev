package com.example.model;

import java.util.List;
import java.util.Map;

/**
 * Summary of feedback metrics and charts.
 * Satisfies requirement REQ-010.
 */
public class FeedbackSummary {
    private Map<String, Object> metrics;
    private List<String> charts;

    public FeedbackSummary(Map<String, Object> metrics, List<String> charts) {
        this.metrics = metrics;
        this.charts = charts;
    }

    public Map<String, Object> getMetrics() {
        return metrics;
    }

    public List<String> getCharts() {
        return charts;
    }
}