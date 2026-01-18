package com.example.infrastructure;

import com.example.domain.ReportCard;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Concrete implementation of ReportCardRepository.
 * Uses in-memory cache and delegates to SMOS service for actual deletion.
 */
public class ReportCardRepositoryImpl implements ReportCardRepository {
    private SMOSService smosService;
    // In-memory cache simulation
    private Map<String, ReportCard> localCache = new HashMap<>();

    public ReportCardRepositoryImpl(SMOSService smosService) {
        this.smosService = smosService;
        // Initialize with some dummy data
        initializeSampleData();
    }

    private void initializeSampleData() {
        // Add a few sample report cards for demonstration
        localCache.put("1", new ReportCard("1", "S001", "Math", Map.of("Midterm", "A"), new java.util.Date()));
        localCache.put("2", new ReportCard("2", "S002", "Science", Map.of("Midterm", "B+"), new java.util.Date()));
    }

    @Override
    public Optional<ReportCard> findById(String id) {
        return Optional.ofNullable(localCache.get(id));
    }

    @Override
    public boolean delete(String id) {
        // First delete from SMOS server
        boolean serverSuccess = smosService.deleteReportCard(id);
        if (serverSuccess) {
            // Then remove from local cache
            localCache.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public List<ReportCard> findAll() {
        return new ArrayList<>(localCache.values());
    }
}