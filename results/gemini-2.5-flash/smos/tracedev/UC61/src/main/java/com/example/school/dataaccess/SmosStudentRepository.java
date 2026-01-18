package com.example.school.dataaccess;

import com.example.school.domain.Student;
import com.example.school.domain.StudentAcademicRecord;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation of IStudentRepository that interacts with the SMOSClient.
 * This class is responsible for converting raw data from SmosClient into domain objects.
 */
public class SmosStudentRepository implements IStudentRepository {
    private SmosClient smosClient;

    /**
     * Constructs a new SmosStudentRepository.
     * @param smosClient The SmosClient instance to use for data access.
     */
    public SmosStudentRepository(SmosClient smosClient) {
        this.smosClient = smosClient;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Student getStudentById(String studentId) {
        System.out.println("SmosStudentRepository: Getting student by ID: " + studentId);
        Connection connection = null;
        try {
            connection = smosClient.establishConnection(); // Updated call
            String query = String.format("SELECT * FROM Students WHERE id = '%s'", studentId);
            List<Map<String, Object>> rawStudentData = smosClient.fetchData(query, connection);
            return mapRawDataToStudent(rawStudentData);
        } finally {
            if (connection != null) {
                smosClient.closeConnection(connection); // Updated call
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<StudentAcademicRecord> getStudentAcademicRecords(String studentId) {
        System.out.println("SmosStudentRepository: Getting academic records for student ID: " + studentId);
        Connection connection = null;
        try {
            connection = smosClient.establishConnection(); // Updated call
            String query = String.format("SELECT * FROM AcademicRecords WHERE studentId = '%s'", studentId);
            List<Map<String, Object>> rawAcademicRecordsData = smosClient.fetchData(query, connection);
            return mapRawDataToAcademicRecord(rawAcademicRecordsData);
        } finally {
            if (connection != null) {
                smosClient.closeConnection(connection); // Updated call
            }
        }
    }

    /**
     * Maps raw student data (from SmosClient) into a Student domain object.
     * Assumes the rawDataList contains at most one student record.
     *
     * @param rawDataList A list of maps, where each map represents a raw student record.
     * @return A Student object, or null if no student data is found.
     */
    protected Student mapRawDataToStudent(List<Map<String, Object>> rawDataList) {
        System.out.println("SmosStudentRepository: Mapping raw student data to Student object.");
        return rawDataList.stream()
                .findFirst()
                .map(data -> new Student(
                        (String) data.get("studentId"),
                        (String) data.get("name"),
                        (String) data.get("parentId")
                ))
                .orElse(null);
    }

    /**
     * Maps raw academic record data (from SmosClient) into a list of StudentAcademicRecord domain objects.
     *
     * @param rawDataList A list of maps, where each map represents a raw academic record.
     * @return A list of StudentAcademicRecord objects.
     */
    protected List<StudentAcademicRecord> mapRawDataToAcademicRecord(List<Map<String, Object>> rawDataList) {
        System.out.println("SmosStudentRepository: Mapping raw academic records data to StudentAcademicRecord objects.");
        List<StudentAcademicRecord> academicRecords = new ArrayList<>();
        for (Map<String, Object> data : rawDataList) {
            academicRecords.add(new StudentAcademicRecord(
                    (String) data.get("recordId"),
                    (String) data.get("studentId"),
                    (LocalDate) data.get("recordDate"),
                    (Integer) data.get("absences"),
                    (String) data.get("disciplinaryNotes"),
                    (Integer) data.get("delayCount"),
                    (String) data.get("justification")
            ));
        }
        return academicRecords;
    }
}