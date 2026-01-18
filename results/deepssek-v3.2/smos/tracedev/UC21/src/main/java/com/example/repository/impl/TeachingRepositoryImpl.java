package com.example.repository.impl;

import com.example.model.Teaching;
import com.example.repository.TeachingRepository;
import com.example.database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of TeachingRepository.
 * Based on the UML class diagram.
 */
public class TeachingRepositoryImpl implements TeachingRepository {
    private Database dataSource;

    public TeachingRepositoryImpl(Database dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Teaching findById(String teachingId) {
        String sql = "SELECT * FROM teaching WHERE teachingId = '" + teachingId + "'";
        try {
            ResultSet rs = dataSource.executeQuery(sql);
            if (rs.next()) {
                return new Teaching(
                    rs.getString("teachingId"),
                    rs.getString("code"),
                    rs.getString("title"),
                    rs.getString("description")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Teaching> findAll() {
        List<Teaching> teachings = new ArrayList<>();
        String sql = "SELECT * FROM teaching";
        try {
            ResultSet rs = dataSource.executeQuery(sql);
            while (rs.next()) {
                Teaching teaching = new Teaching(
                    rs.getString("teachingId"),
                    rs.getString("code"),
                    rs.getString("title"),
                    rs.getString("description")
                );
                teachings.add(teaching);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachings;
    }

    @Override
    public boolean save(Teaching teaching) {
        String sql = "INSERT INTO teaching (teachingId, code, title, description) VALUES ('" +
                teaching.getTeachingId() + "', '" +
                teaching.getCode() + "', '" +
                teaching.getTitle() + "', '" +
                teaching.getDescription() + "') ON DUPLICATE KEY UPDATE code = VALUES(code), title = VALUES(title), description = VALUES(description)";
        int rows = dataSource.executeUpdate(sql);
        return rows > 0;
    }
}