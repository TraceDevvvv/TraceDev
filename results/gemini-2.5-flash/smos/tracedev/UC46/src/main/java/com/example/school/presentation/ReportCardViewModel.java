package com.example.school.presentation;

import com.example.school.domain.Class;
import com.example.school.domain.Student;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * ViewModel for the Report Card UI, holding data to be displayed or collected.
 * It's responsible for preparing data for the view and holding UI state.
 */
public class ReportCardViewModel {
    public String currentAcademicYearId;
    public List<Class> classes;
    public List<Student> students;
    public String selectedClassId;
    public String selectedStudentId;
    public Map<String, Object> reportCardData; // Holds data for the report card entry form

    public ReportCardViewModel() {
        // Initialize with dummy current academic year and empty lists/maps
        this.currentAcademicYearId = "AY2023-2024"; // Assume current academic year for now
        this.classes = new ArrayList<>();
        this.students = new ArrayList<>();
        this.reportCardData = new HashMap<>();
    }

    public String getCurrentAcademicYearId() {
        return currentAcademicYearId;
    }

    public void setCurrentAcademicYearId(String currentAcademicYearId) {
        this.currentAcademicYearId = currentAcademicYearId;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getSelectedClassId() {
        return selectedClassId;
    }

    public void setSelectedClassId(String selectedClassId) {
        this.selectedClassId = selectedClassId;
    }

    public String getSelectedStudentId() {
        return selectedStudentId;
    }

    public void setSelectedStudentId(String selectedStudentId) {
        this.selectedStudentId = selectedStudentId;
    }

    public Map<String, Object> getReportCardData() {
        return reportCardData;
    }

    public void setReportCardData(Map<String, Object> reportCardData) {
        this.reportCardData = reportCardData;
    }

    /**
     * Resets the view model state, typically after an action or to prepare for a new flow.
     */
    public void reset() {
        this.classes.clear();
        this.students.clear();
        this.selectedClassId = null;
        this.selectedStudentId = null;
        this.reportCardData = new HashMap<>();
        System.out.println("ViewModel: State reset.");
    }
}