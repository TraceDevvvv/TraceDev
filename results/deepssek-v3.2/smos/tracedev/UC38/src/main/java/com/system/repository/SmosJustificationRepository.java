package com.system.repository;

import com.system.entity.Justification;
import com.system.connection.SmosConnection;
import java.util.Optional;
import java.util.Date;
import java.util.Map;

/**
 * Implementation of JustificationRepository that uses a SMOS server connection.
 */
public class SmosJustificationRepository implements JustificationRepository {
    private SmosConnection serverConnection;

    public SmosJustificationRepository(SmosConnection serverConnection) {
        this.serverConnection = serverConnection;
    }

    @Override
    public Optional<Justification> findById(String justificationId) {
        if (serverConnection == null || !serverConnection.isConnected()) {
            // Attempt to connect if not already connected (assumed from sequence diagram flow)
            serverConnection.connect();
        }
        Optional<Map<String, Object>> data = serverConnection.fetchJustification(justificationId);
        if (data.isPresent()) {
            Justification justification = mapToJustificationEntity(data.get());
            return Optional.of(justification);
        }
        return Optional.empty();
    }

    @Override
    public Justification save(Justification justification) {
        if (serverConnection == null || !serverConnection.isConnected()) {
            serverConnection.connect();
        }
        Map<String, Object> data = new java.util.HashMap<>();
        data.put("justificationId", justification.getJustificationId());
        data.put("absenceId", justification.getAbsenceId());
        data.put("description", justification.getDescription());
        data.put("status", justification.getStatus());
        data.put("createdAt", justification.getCreatedAt());
        data.put("updatedAt", justification.getUpdatedAt());
        boolean success = serverConnection.updateJustification(justification.getJustificationId(), data);
        if (success) {
            return justification;
        } else {
            // In a real scenario, we might throw an exception or return null.
            // For simplicity, we return the original justification.
            return justification;
        }
    }

    @Override
    public boolean deleteById(String justificationId) {
        if (serverConnection == null || !serverConnection.isConnected()) {
            serverConnection.connect();
        }
        return serverConnection.deleteJustification(justificationId);
    }

    /**
     * Maps raw data from SMOS to a Justification entity.
     * @param data The raw data map.
     * @return A Justification object.
     */
    protected Justification mapToJustificationEntity(Map<String, Object> data) {
        // Assumes the map contains the necessary fields.
        // In a real implementation, you would have more robust parsing.
        String justificationId = (String) data.getOrDefault("justificationId", "");
        String absenceId = (String) data.getOrDefault("absenceId", "");
        String description = (String) data.getOrDefault("description", "");
        String status = (String) data.getOrDefault("status", "");
        Date createdAt = (Date) data.getOrDefault("createdAt", new Date());
        Date updatedAt = (Date) data.getOrDefault("updatedAt", new Date());
        return new Justification(justificationId, absenceId, description, status, createdAt, updatedAt);
    }
}