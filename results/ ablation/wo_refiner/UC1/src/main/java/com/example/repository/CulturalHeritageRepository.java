package com.example.repository;

import com.example.model.CulturalHeritage;
import com.example.exception.ETOURConnectionException;

/**
 * Repository interface for cultural heritage data access.
 */
public interface CulturalHeritageRepository {
    CulturalHeritage findById(int id);
    boolean delete(int id);
    // Added to satisfy requirement REQ-012
    boolean checkConnection() throws ETOURConnectionException;

    // Added methods to satisfy sequence diagram messages
    CulturalHeritage getCulturalHeritage(int id);
    void queryCulturalHeritage(int id);
    void deleteCulturalHeritage(int id);
    CulturalHeritage queryCulturalHeritageData(int id);
}