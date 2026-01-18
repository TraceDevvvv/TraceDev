package com.example.reportcard.presentation;

import com.example.reportcard.dto.AcademicYearDTO;
import com.example.reportcard.dto.ClassDTO;
import com.example.reportcard.dto.ReportCardDTO;
import com.example.reportcard.dto.StudentDTO;

import java.util.List;

/**
 * ViewModel for the Report Card feature.
 * Holds the presentation state and data that the View displays.
 * It's observed by the View or provides data to it directly.
 */
public class ReportCardViewModel {
    private List<AcademicYearDTO> academicYears;
    private List<ClassDTO> classes;
    private List<StudentDTO> students;
    private ReportCardDTO selectedReport;

    // In a real application, this would typically involve PropertyChangeListeners
    // or observable patterns to notify the View of changes.
    // For this example, the View directly accesses the data or the Controller pushes updates.

    /**
     * Updates the list of academic years in the ViewModel.
     * @param years The new list of AcademicYearDTOs.
     */
    public void updateAcademicYears(List<AcademicYearDTO> years) {
        this.academicYears = years;
        System.out.println("[ViewModel] Academic years updated. Count: " + years.size());
    }

    /**
     * Updates the list of classes in the ViewModel.
     * @param classes The new list of ClassDTOs.
     */
    public void updateClasses(List<ClassDTO> classes) {
        this.classes = classes;
        System.out.println("[ViewModel] Classes updated. Count: " + classes.size());
    }

    /**
     * Updates the list of students in the ViewModel.
     * @param students The new list of StudentDTOs.
     */
    public void updateStudents(List<StudentDTO> students) {
        this.students = students;
        System.out.println("[ViewModel] Students updated. Count: " + students.size());
    }

    /**
     * Updates the selected report card in the ViewModel.
     * @param report The new ReportCardDTO.
     */
    public void updateReportCard(ReportCardDTO report) {
        this.selectedReport = report;
        System.out.println("[ViewModel] Report card updated.");
    }

    // Getters for the View to retrieve data from the ViewModel (if direct binding is used)
    public List<AcademicYearDTO> getAcademicYears() {
        return academicYears;
    }

    public List<ClassDTO> getClasses() {
        return classes;
    }

    public List<StudentDTO> getStudents() {
        return students;
    }

    public ReportCardDTO getSelectedReport() {
        return selectedReport;
    }
}