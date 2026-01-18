package com.schoolsystem.cache;

import com.schoolsystem.domain.ReportCard;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple in-memory cache for ReportCard objects.
 */
public class Cache {
    private Map<String, Object> cacheStore = new HashMap<>();

    public Cache() {
        // Initialization
    }

    public List<ReportCard> getCachedReportList(String studentId) {
        return (List<ReportCard>) cacheStore.get("list_" + studentId);
    }

    public ReportCard getCachedReport(String reportId) {
        return (ReportCard) cacheStore.get("report_" + reportId);
    }

    public void cacheReportList(String studentId, List<ReportCard> list) {
        cacheStore.put("list_" + studentId, list);
    }

    public void cacheReport(String reportId, ReportCard reportCard) {
        cacheStore.put("report_" + reportId, reportCard);
    }

    public boolean isCacheAvailable(String key) {
        return cacheStore.containsKey(key);
    }
}