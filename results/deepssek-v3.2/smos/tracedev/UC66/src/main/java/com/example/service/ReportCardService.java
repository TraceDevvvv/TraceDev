package com.example.service;

import com.example.domain.AcademicYear;
import com.example.domain.PupilClass;
import com.example.domain.Student;
import com.example.dto.ReportCardDTO;

import java.util.List;

public interface ReportCardService {
    List<AcademicYear> getAvailableAcademicYears();
    List<PupilClass> getClassesForYear(String academicYearId);
    List<Student> getStudentsInClass(String classId);
    ReportCardDTO getReportCard(String studentId, String semesterId);
}