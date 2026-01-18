package com.example.service;

import com.example.dto.ClassDTO;
import com.example.dto.GradeDTO;
import com.example.dto.ReportCardDTO;
import com.example.dto.ReportRequestDTO;
import com.example.dto.StudentDTO;
import com.example.model.Class;
import com.example.model.Grade;
import com.example.model.ReportCard;
import com.example.model.Student;
import com.example.repository.ClassRepository;
import com.example.repository.ReportCardRepository;
import com.example.repository.StudentRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of ReportService.
 */
public class ReportServiceImpl implements ReportService {
    private ClassRepository classRepository;
    private StudentRepository studentRepository;
    private ReportCardRepository reportCardRepository;

    public ReportServiceImpl(ClassRepository classRepository,
                            StudentRepository studentRepository,
                            ReportCardRepository reportCardRepository) {
        this.classRepository = classRepository;
        this.studentRepository = studentRepository;
        this.reportCardRepository = reportCardRepository;
    }

    @Override
    public List<ClassDTO> getClassesByYear(String academicYear) {
        // Fetch classes from repository
        List<Class> classes = classRepository.findByAcademicYear(academicYear);
        // Convert to DTOs
        return convertToClassDTOs(classes);
    }

    @Override
    public List<StudentDTO> getStudentsByClass(String classId) {
        // Fetch students from repository
        List<Student> students = studentRepository.findByClassId(classId);
        // Convert to DTOs
        return convertToStudentDTOs(students);
    }

    @Override
    public ReportCardDTO generateStudentReport(ReportRequestDTO request) {
        // Fetch report card from repository
        ReportCard reportCard = reportCardRepository.findReport(request.getStudentId(), request.getMonths());
        // Convert to DTO
        return convertToReportCardDTO(reportCard);
    }

    // Helper method to convert Class entities to ClassDTOs
    public List<ClassDTO> convertToClassDTOs(List<Class> classes) {
        if (classes == null) return new ArrayList<>();
        return classes.stream()
                .map(c -> new ClassDTO(c.getClassId(), c.getClassName()))
                .collect(Collectors.toList());
    }

    // Helper method to convert Student entities to StudentDTOs
    public List<StudentDTO> convertToStudentDTOs(List<Student> students) {
        if (students == null) return new ArrayList<>();
        return students.stream()
                .map(s -> new StudentDTO(s.getStudentId(), s.getName()))
                .collect(Collectors.toList());
    }

    // Helper method to convert ReportCard entity to ReportCardDTO
    public ReportCardDTO convertToReportCardDTO(ReportCard reportCard) {
        if (reportCard == null) {
            return null;
        }
        Map<String, GradeDTO> gradeDTOMap = new HashMap<>();
        if (reportCard.getGrades() != null) {
            for (Map.Entry<String, Grade> entry : reportCard.getGrades().entrySet()) {
                Grade g = entry.getValue();
                gradeDTOMap.put(entry.getKey(), new GradeDTO(g.getSubject(), g.getScore(), g.getComment()));
            }
        }
        return new ReportCardDTO(
                reportCard.getStudentId(),
                reportCard.getStudentName(),
                reportCard.getMonths(),
                gradeDTOMap,
                reportCard.getAttendance()
        );
    }
}