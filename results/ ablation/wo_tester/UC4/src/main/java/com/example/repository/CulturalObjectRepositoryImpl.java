package com.example.repository;

import com.example.database.Database;
import com.example.domain.CulturalObject;
import com.example.domain.DateRange;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the CulturalObjectRepository interface.
 * Uses a Database helper to execute queries.
 */
public class CulturalObjectRepositoryImpl implements CulturalObjectRepository {
    private Database database;

    public CulturalObjectRepositoryImpl() {
        this.database = new Database();
    }

    @Override
    public List<CulturalObject> findByCriteria(String criteria, String filterType, DateRange dateRange) {
        // Build a SQL query based on the criteria.
        // This is a simplified example; in a real application, you'd use a query builder or an ORM.
        String query = buildQuery(criteria, filterType, dateRange);
        ResultSet resultSet = database.executeQuery(query);
        return convertResultSetToEntities(resultSet);
    }

    private String buildQuery(String criteria, String filterType, DateRange dateRange) {
        StringBuilder sb = new StringBuilder("SELECT * FROM cultural_objects WHERE 1=1");
        if (criteria != null && !criteria.trim().isEmpty()) {
            sb.append(" AND (title LIKE '%").append(criteria).append("%' OR description LIKE '%").append(criteria).append("%')");
        }
        if (filterType != null && !filterType.trim().isEmpty()) {
            sb.append(" AND cultural_period = '").append(filterType).append("'");
        }
        if (dateRange != null) {
            if (dateRange.getStartDate() != null) {
                sb.append(" AND creation_date >= '").append(dateRange.getStartDate()).append("'");
            }
            if (dateRange.getEndDate() != null) {
                sb.append(" AND creation_date <= '").append(dateRange.getEndDate()).append("'");
            }
        }
        return sb.toString();
    }

    private List<CulturalObject> convertResultSetToEntities(ResultSet resultSet) {
        List<CulturalObject> objects = new ArrayList<>();
        try {
            while (resultSet != null && resultSet.next()) {
                CulturalObject obj = new CulturalObject(
                    resultSet.getLong("id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getDate("creation_date"),
                    resultSet.getString("cultural_period"),
                    resultSet.getString("location")
                );
                objects.add(obj);
            }
        } catch (SQLException e) {
            // Log the exception; in a real app, you'd handle it appropriately.
            e.printStackTrace();
        }
        return objects;
    }
}