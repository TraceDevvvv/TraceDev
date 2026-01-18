package com.example.controller;

import com.example.dto.StudentDto;
import com.example.exception.StudentNotFoundException;
import com.example.model.ClassRegister;
import com.example.model.Student;
import com.example.client.SmosServerClient;
import com.example.external.ExternalStudentData;
import com.example.repository.StudentRepository;
import com.example.security.AuthenticationService;

import java.util.Date;

/**
 * Controller class for handling school data requests.
 * Implements business logic as per sequence diagram.
 */
public class SchoolDataController {
    private StudentRepository studentRepository;
    private SmosServerClient smosServerClient;
    private AuthenticationService authenticationService;

    public SchoolDataController(StudentRepository repo, SmosServerClient client, AuthenticationService auth) {
        this.studentRepository = repo;
        this.smosServerClient = client;
        this.authenticationService = auth;
    }

    /**
     * Retrieves complete school data for a student.
     * Implements the main flow from sequence diagram.
     */
    public StudentDto getStudentSchoolData(String studentId) throws StudentNotFoundException {
        // Step 2: Search archive for class register data
        ClassRegister classRegister = studentRepository.findClassRegisterData(studentId);
        
        // Step 3: Verify student is in class register
        if (classRegister == null || !classRegister.containsStudent(studentId)) {
            handleStudentNotFound(studentId);
            throw new StudentNotFoundException(studentId);
        }
        
        // Step 4: Retrieve student details
        Student student = studentRepository.findStudentData(studentId);
        
        // Step 5: Fetch external data from SMOS
        ExternalStudentData externalData = smosServerClient.fetchData(studentId);
        
        // Step 6: Disconnect from SMOS (immediately after fetch as per sequence diagram)
        smosServerClient.disconnect();
        
        // Step 7: Map and aggregate data into DTO
        StudentDto dto = mapToStudentDto(student, classRegister, externalData);
        
        // Data validation step
        validateStudentData(dto);
        
        return dto;
    }
    
    /**
     * Handles student not found scenario.
     * Satisfies requirement REQ-ERROR-001.
     */
    public void handleStudentNotFound(String studentId) {
        // Log the error or perform any necessary cleanup
        System.err.println("Student not found: " + studentId);
    }
    
    /**
     * Maps data from various sources to StudentDto.
     * Implements mapping described in sequence diagram note.
     */
    private StudentDto mapToStudentDto(Student student, ClassRegister classRegister, ExternalStudentData externalData) {
        StudentDto dto = new StudentDto();
        
        dto.setStudentId(student.getStudentId());
        dto.setClassName(classRegister.getClassId());
        dto.setDate(new Date()); // Current date
        
        // Map data from ExternalStudentData
        dto.setAbsences(externalData.getAbsenceRecords());
        dto.setDisciplinaryNotes(externalData.getDisciplinaryNotes());
        dto.setDelays(externalData.getDelayRecords());
        dto.setJustification(externalData.getJustification());
        
        return dto;
    }
    
    /**
     * Validates the student data in the DTO.
     * Ensures all required fields are complete.
     */
    private void validateStudentData(StudentDto dto) {
        if (dto.getStudentId() == null || dto.getStudentId().isEmpty()) {
            throw new IllegalArgumentException("Student ID is required");
        }
        if (dto.getClassName() == null || dto.getClassName().isEmpty()) {
            throw new IllegalArgumentException("Class name is required");
        }
        // Add more validation as needed
    }
}