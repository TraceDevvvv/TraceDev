package com.example.infrastructure.persistence;

import com.example.domain.Justification;
import com.example.external.SMOSServer;
import com.example.infrastructure.exception.PersistenceException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * In-memory implementation of JustificationRepository.
 * Depends on SMOSServer for connectivity checks.
 */
public class JustificationRepositoryImpl implements JustificationRepository {
    private Map<UUID, Justification> dataStore = new HashMap<>();
    private SMOSServer smosServer;

    public JustificationRepositoryImpl(SMOSServer smosServer) {
        this.smosServer = smosServer;
    }

    @Override
    public Justification save(Justification justification) {
        // Simulate dependency on external SMOS server
        if (!smosServer.isAvailable()) {
            throw new PersistenceException("SMOS server connection interrupted", null);
        }
        dataStore.put(justification.getId(), justification);
        return justification;
    }

    @Override
    public Optional<Justification> findById(UUID id) {
        return Optional.ofNullable(dataStore.get(id));
    }

    @Override
    public List<Justification> findByAbsenceId(UUID absenceId) {
        return dataStore.values().stream()
                .filter(j -> j.getAbsenceId().equals(absenceId))
                .collect(Collectors.toList());
    }
}