package com.example.repository;

import com.example.model.Student;
import com.example.infrastructure.DataSource;
import com.example.infrastructure.SMOSConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of StudentRepository.
 * Connects to SMOS database via DataSource and SMOSConnection.
 */
public class StudentRepositoryImpl implements StudentRepository {
    private DataSource dataSource;
    private SMOSConnection smosConnection;

    public StudentRepositoryImpl(DataSource dataSource, SMOSConnection smosConnection) {
        this.dataSource = dataSource;
        this.smosConnection = smosConnection;
    }

    /**
     * Ensures SMOS server is connected (as per sequence diagram).
     * This method is called from service to satisfy the connection check.
     */
    public void checkConnection() {
        if (!smosConnection.isConnected()) {
            smosConnection.connect();
        }
    }

    @Override
    public List<Student> findAll() {
        checkConnection(); // Ensure connection before query
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                students.add(new Student(id, name, email));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch students", e);
        }
        return students;
    }

    @Override
    public int countAbsencesByStudent(String studentId) {
        checkConnection();
        String sql = "SELECT COUNT(*) FROM absences WHERE student_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to count absences for student " + studentId, e);
        }
        return 0;
    }

    @Override
    public int countNotesByStudent(String studentId) {
        checkConnection();
        String sql = "SELECT COUNT(*) FROM notes WHERE student_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to count notes for student " + studentId, e);
        }
        return 0;
    }
}