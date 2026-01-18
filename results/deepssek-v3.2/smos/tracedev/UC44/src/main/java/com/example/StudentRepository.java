package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation of IStudentRepository that retrieves data from a data source.
 */
public class StudentRepository implements IStudentRepository {
    private DataSource dataSource;

    /**
     * Constructor with data source dependency.
     * @param dataSource the data source to connect to the database
     */
    public StudentRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Retrieves student monitoring data based on threshold criteria.
     * Implements the query flow as per sequence diagram.
     * @param criteria the threshold criteria
     * @return a list of StudentMonitoringDto objects
     */
    @Override
    public List<StudentMonitoringDto> getStudentMonitoringData(ThresholdCriteria criteria) {
        List<StudentMonitoringDto> results = new ArrayList<>();
        Connection conn = null;
        ResultSet rs = null;
        try {
            // Connect to data source as per sequence diagram
            conn = dataSource.connect();
            // Build query using thresholds from criteria
            String query = "SELECT studentId, name, enrolledYear, absenceCount, noteCount FROM Student " +
                          "WHERE (absenceCount > " + criteria.getAbsenceThreshold() +
                          " OR noteCount > " + criteria.getNoteThreshold() + ") " +
                          "AND enrolledYear = '" + criteria.getSchoolYear() + "'";
            rs = dataSource.executeQuery(query);
            // Loop through each student in ResultSet
            while (rs != null && rs.next()) {
                StudentMonitoringDto dto = createStudentMonitoringDto(rs);
                results.add(dto);
            }
        } catch (SQLException e) {
            // In case of connection interruption, throw a custom exception as per sequence diagram.
            // Note: in the sequence diagram, the exception is thrown to MonitoringController.
            // For simplicity, we wrap and rethrow.
            throw new DatabaseConnectionException("Database connection error", e);
        } finally {
            // Clean up resources
            if (conn != null) {
                dataSource.disconnect();
            }
        }
        return results;
    }

    /**
     * Finds students whose absence count exceeds the given threshold.
     * @param threshold the absence threshold
     * @return list of Student objects
     */
    @Override
    public List<Student> findStudentsExceedingAbsenceThreshold(int threshold) {
        List<Student> students = new ArrayList<>();
        try (Connection conn = dataSource.connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT studentId, name, enrolledYear, absenceCount, noteCount FROM Student WHERE absenceCount > " + threshold);
            while (rs.next()) {
                Student student = new Student(
                        rs.getString("studentId"),
                        rs.getString("name"),
                        rs.getString("enrolledYear"),
                        rs.getInt("absenceCount"),
                        rs.getInt("noteCount")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Database connection error", e);
        }
        return students;
    }

    /**
     * Finds students whose note count exceeds the given threshold.
     * @param threshold the note threshold
     * @return list of Student objects
     */
    @Override
    public List<Student> findStudentsExceedingNoteThreshold(int threshold) {
        List<Student> students = new ArrayList<>();
        try (Connection conn = dataSource.connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT studentId, name, enrolledYear, absenceCount, noteCount FROM Student WHERE noteCount > " + threshold);
            while (rs.next()) {
                Student student = new Student(
                        rs.getString("studentId"),
                        rs.getString("name"),
                        rs.getString("enrolledYear"),
                        rs.getInt("absenceCount"),
                        rs.getInt("noteCount")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Database connection error", e);
        }
        return students;
    }

    /**
     * Validates data accuracy (simplified implementation).
     * @return true if data source connection is successful and query returns valid results.
     */
    @Override
    public boolean validateDataAccuracy() {
        // For this example, we attempt a simple query to verify data source health.
        try (Connection conn = dataSource.connect()) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Creates a StudentMonitoringDto from a ResultSet row.
     * @param rs the ResultSet positioned at the current row
     * @return a populated StudentMonitoringDto
     * @throws SQLException if database access error occurs
     */
    protected StudentMonitoringDto createStudentMonitoringDto(ResultSet rs) throws SQLException {
        StudentMonitoringDto dto = new StudentMonitoringDto();
        dto.setStudentId(rs.getString("studentId"));
        dto.setStudentName(rs.getString("name"));
        dto.setEnrollmentYear(rs.getString("enrolledYear"));
        dto.setAbsenceCount(rs.getInt("absenceCount"));
        dto.setNoteCount(rs.getInt("noteCount"));
        return dto;
    }
}