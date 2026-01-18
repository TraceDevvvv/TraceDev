package com.example.view;

import com.example.dto.AcademicYearDTO;
import com.example.dto.PupilClassDTO;
import com.example.dto.ReportCardDTO;
import com.example.dto.StudentDTO;

import java.util.List;

public interface ReportCardView {
    void displayAcademicYears(List<AcademicYearDTO> years);
    void displayClasses(List<PupilClassDTO> classes);
    void displayStudents(List<StudentDTO> students);
    void displayReportCard(ReportCardDTO reportCard);
}