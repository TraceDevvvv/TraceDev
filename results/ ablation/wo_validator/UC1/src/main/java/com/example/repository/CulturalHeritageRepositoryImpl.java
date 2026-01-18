package com.example.repository;

import com.example.domain.CulturalHeritage;
import com.example.dto.HeritageDTO;
import com.example.exceptions.ConnectionException;
import com.example.infrastructure.ETOURDataSource;
import java.sql.*;
import java.util.*;

/**
 * Implementation of Cultural Heritage Repository
 */
public class CulturalHeritageRepositoryImpl implements CulturalHeritageRepository {
    private ETOURDataSource dataSource;

    public CulturalHeritageRepositoryImpl(ETOURDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<CulturalHeritage> findAll() {
        List<CulturalHeritage> heritageList = new ArrayList<>();
        try {
            ResultSet rs = dataSource.executeQuery("SELECT * FROM cultural_heritage");
            while (rs.next()) {
                CulturalHeritage heritage = extractCulturalHeritage(rs);
                heritageList.add(heritage);
            }
        } catch (SQLException e) {
            throw new ConnectionException("Failed to retrieve cultural heritage list", 2001, e);
        }
        return heritageList;
    }

    @Override
    public Optional<CulturalHeritage> findById(String id) {
        try {
            ResultSet rs = dataSource.executeQuery("SELECT * FROM cultural_heritage WHERE id = '" + id + "'");
            if (rs.next()) {
                return Optional.of(extractCulturalHeritage(rs));
            }
        } catch (SQLException e) {
            throw new ConnectionException("Failed to find cultural heritage by id", 2002, e);
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(String id) {
        try {
            // First check if item exists
            Optional<CulturalHeritage> item = findById(id);
            if (!item.isPresent()) {
                return false;
            }
            
            // Delete the item
            int rowsAffected = dataSource.executeUpdate("DELETE FROM cultural_heritage WHERE id = '" + id + "'");
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new ConnectionException("Failed to delete cultural heritage", 2003, e);
        }
    }

    @Override
    public CulturalHeritage save(CulturalHeritage entity) {
        // Implementation for save operation
        // Note: This is a simplified implementation
        try {
            String sql;
            if (findById(entity.getId()).isPresent()) {
                // Update existing
                sql = String.format("UPDATE cultural_heritage SET name='%s', description='%s', " +
                                   "location='%s', historical_period='%s', last_modified=CURRENT_TIMESTAMP " +
                                   "WHERE id='%s'",
                                   entity.getName(), entity.getDescription(), 
                                   entity.getLocation(), entity.getHistoricalPeriod(),
                                   entity.getId());
            } else {
                // Insert new
                sql = String.format("INSERT INTO cultural_heritage VALUES ('%s', '%s', '%s', '%s', '%s', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)",
                                   entity.getId(), entity.getName(), entity.getDescription(),
                                   entity.getLocation(), entity.getHistoricalPeriod());
            }
            dataSource.executeUpdate(sql);
            return entity;
        } catch (SQLException e) {
            throw new ConnectionException("Failed to save cultural heritage", 2004, e);
        }
    }

    /**
     * Convert CulturalHeritage to HeritageDTO (as per class diagram)
     */
    public HeritageDTO convertToDTO(CulturalHeritage heritage) {
        return new HeritageDTO(
            heritage.getId(),
            heritage.getName(),
            heritage.getDescription(),
            heritage.getLocation(),
            heritage.getHistoricalPeriod()
        );
    }

    /**
     * Helper method to extract CulturalHeritage from ResultSet
     */
    private CulturalHeritage extractCulturalHeritage(ResultSet rs) throws SQLException {
        return new CulturalHeritage(
            rs.getString("id"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getString("location"),
            rs.getString("historical_period"),
            rs.getTimestamp("creation_date")
        );
    }
}