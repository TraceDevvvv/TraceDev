package com.newsagency.repository;

import com.newsagency.database.DatabaseConnection;
import com.newsagency.model.News;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Database implementation of NewsRepository.
 */
public class DatabaseNewsRepository implements NewsRepository {
    private DatabaseConnection databaseConnection;
    
    public DatabaseNewsRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }
    
    public DatabaseConnection getDatabaseConnection() {
        return databaseConnection;
    }
    
    public void setDatabaseConnection(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }
    
    @Override
    public News save(News news) {
        if (news == null || databaseConnection == null) {
            return null;
        }
        
        try {
            // Check connection (sequence message m42)
            boolean isConnected = databaseConnection.checkConnection();
            if (!isConnected) {
                return null;
            }
            
            // Create insert query
            String insertQuery = String.format(
                "INSERT INTO news (id, title, content, author, publication_date, category, status, created_at, updated_at) " +
                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",