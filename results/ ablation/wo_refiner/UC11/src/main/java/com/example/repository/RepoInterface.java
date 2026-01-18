package com.example.repository;

import com.example.model.Convention;
import java.util.List;

/**
 * Repository interface for convention history (aliased as RepoInterface in sequence diagram).
 */
public interface RepoInterface {
    /**
     * Finds conventions by point of rest ID.
     * @param porId the point of rest ID.
     * @return list of conventions.
     * @throws ServerConnectionException if server connection fails.
     */
    List<Convention> findByPointOfRestId(String porId) throws ServerConnectionException;
}