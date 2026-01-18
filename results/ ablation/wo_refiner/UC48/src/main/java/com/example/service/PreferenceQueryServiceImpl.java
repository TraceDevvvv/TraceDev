package com.example.service;

import com.example.model.GenericPreference;
import com.example.repository.PreferenceRepository;
import java.util.Map;
import java.util.HashMap;

/**
 * Implementation of PreferenceQueryService.
 */
public class PreferenceQueryServiceImpl implements PreferenceQueryService {
    private PreferenceRepository preferenceRepository;
    private ETOURConnection connection;
    private QualityMonitor qualityMonitor;

    public PreferenceQueryServiceImpl(PreferenceRepository preferenceRepository, ETOURConnection connection, QualityMonitor qualityMonitor) {
        this.preferenceRepository = preferenceRepository;
        this.connection = connection;
        this.qualityMonitor = qualityMonitor;
    }

    @Override
    public GenericPreference fetchPreferences(String touristId) {
        Map<String, String> details = new HashMap<>();
        details.put("touristId", touristId);
        details.put("operation", "fetch_preferences");
        qualityMonitor.logOperation("fetch_preferences", details);

        // Check connection as per requirement 14 and sequence message m8.
        if (!connection.checkConnection()) {
            System.out.println("PreferenceQueryServiceImpl: Connection check failed.");
            // Sequence m8: return connection status (false)
            // Sequence m12: repository returns ConnectionError
            // Sequence m13: queryService returns Connection error
            // For simplicity, we return null to indicate error.
            return null;
        }

        GenericPreference preference = preferenceRepository.findById(touristId);
        qualityMonitor.monitorPerformance("fetch_operation");
        return preference;
    }

    // Additional method to handle connection error flow as per sequence diagram.
    public String checkConnection() {
        boolean connected = connection.checkConnection();
        return connected ? "CONNECTED" : "DISCONNECTED";
    }
}