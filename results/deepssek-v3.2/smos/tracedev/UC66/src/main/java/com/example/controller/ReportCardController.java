package com.example.controller;

import com.example.domain.AcademicYear;
import com.example.domain.PupilClass;
import com.example.domain.Student;
import com.example.dto.AcademicYearDTO;
import com.example.dto.PupilClassDTO;
import com.example.dto.StudentDTO;
import com.example.service.ReportCardService;
import com.example.view.ReportCardView;

import java.util.List;
import java.util.stream.Collectors;

public class ReportCardController {
    private final ReportCardService reportCardService;
    private final ReportCardView reportCardView;

    public ReportCardController(ReportCardService reportCardService, ReportCardView reportCardView) {
        this.reportCardService = reportCardService;
        this.reportCardView = reportCardView;
    }

    public Object showAcademicYears() {
        List<AcademicYear> years = reportCardService.getAvailableAcademicYears();
        List<AcademicYearDTO> yearDTOs = years.stream()
                .map(y -> new AcademicYearDTO(y.getYearId(), y.getYearId()))
                .collect(Collectors.toList());
        reportCardView.displayAcademicYears(yearDTOs);
        return new Object();
    }

    public Object showClassesForYear(String academicYearId) {
        List<PupilClass> classes = reportCardService.getClassesForYear(academicYearId);
        List<PupilClassDTO> classDTOs = classes.stream()
                .map(c -> new PupilClassDTO(c.getClassId(), c.getClassName()))
                .collect(Collectors.toList());
        reportCardView.displayClasses(classDTOs);
        return new Object();
    }

    public Object showStudentsInClass(String classId) {
        List<Student> students = reportCardService.getStudentsInClass(classId);
        List<StudentDTO> studentDTOs = students.stream()
                .map(s -> new StudentDTO(s.getStudentId(), s.getFullName()))
                .collect(Collectors.toList());
        reportCardView.displayStudents(studentDTOs);
        return new Object();
    }

    public Object showReportCard(String studentId, String semesterId) {
        com.example.dto.ReportCardDTO reportCard = reportCardService.getReportCard(studentId, semesterId);
        reportCardView.displayReportCard(reportCard);
        return new Object();
    }
}