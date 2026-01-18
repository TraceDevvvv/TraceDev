package com.example.service;

import com.example.dto.ClassDTO;
import com.example.dto.ReportCardDTO;
import com.example.dto.ReportRequestDTO;
import com.example.dto.StudentDTO;
import java.util.List;

/**
 * Service interface for report operations.
 */
public interface ReportService {
    List<ClassDTO> getClassesByYear(String academicYear);
    List<StudentDTO> getStudentsByClass(String classId);
    ReportCardDTO generateStudentReport(ReportRequestDTO request);
}