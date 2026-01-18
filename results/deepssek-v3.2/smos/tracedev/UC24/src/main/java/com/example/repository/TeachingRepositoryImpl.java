package com.example.repository;

import com.example.database.SMOSDatabase;
import com.example.domain.Teaching;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementation of TeachingRepository.
 */
public class TeachingRepositoryImpl implements TeachingRepository {
    private SMOSDatabase dataSource;

    public TeachingRepositoryImpl(SMOSDatabase dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Teaching findById(int teachingId) {
        // Check SMOS connection interruption (as per sequence diagram)
        if (dataSource.isConnectionInterrupted()) {
            boolean reconnected = dataSource.reconnect();
            if (!reconnected) {
                System.err.println("Connection to SMOS server interrupted and reconnection failed.");
                return null; // connection error
            }
        }

        // Execute query
        String sql = "SELECT * FROM teachings WHERE id = " + teachingId;
        ResultSet resultSet = dataSource.query(sql);
        if (resultSet == null) {
            return null;
        }

        try {
            if (resultSet.next()) {
                // sequence diagram message: map ResultSet to Teaching object
                return mapResultSetToTeaching(resultSet);
            } else {
                return null; // teaching not found
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // sequence diagram message: map ResultSet to Teaching object
    private Teaching mapResultSetToTeaching(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String courseName = resultSet.getString("course_name");
        String instructorName = resultSet.getString("instructor_name");
        String schedule = resultSet.getString("schedule");
        String location = resultSet.getString("location");
        int studentCount = resultSet.getInt("student_count");
        return new Teaching(id, courseName, instructorName, schedule, location, studentCount);
    }

    // The sequence diagram shows a return message "Teaching object" from Repository to UseCase.
    // This is already handled by the return value of findById.
}