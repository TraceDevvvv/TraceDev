package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ViewModel for the Class List screen.
 * Holds the state and data to be displayed by the ProfessorView.
 */
public class ClassListViewModel {
    private String professorId;
    private List<AcademicYear> academicYears = new ArrayList<>();
    private AcademicYear selectedAcademicYear;
    private List<Class> classes = new ArrayList<>();

    public ClassListViewModel() {
        System.out.println("ClassListViewModel initialized.");
    }

    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String id) {
        System.out.println("ClassListViewModel: Setting professorId to " + id);
        this.professorId = id;
    }

    public List<AcademicYear> getAcademicYears() {
        return Collections.unmodifiableList(academicYears); // Return immutable list
    }

    public void setAcademicYears(List<AcademicYear> years) {
        System.out.println("ClassListViewModel: Setting " + years.size() + " academic years.");
        this.academicYears.clear();
        if (years != null) {
            this.academicYears.addAll(years);
        }
    }

    public AcademicYear getSelectedAcademicYear() {
        return selectedAcademicYear;
    }

    public void setSelectedAcademicYear(AcademicYear year) {
        System.out.println("ClassListViewModel: Setting selectedAcademicYear to " + (year != null ? year.getId() : "null"));
        this.selectedAcademicYear = year;
    }

    public List<Class> getClasses() {
        return Collections.unmodifiableList(classes); // Return immutable list
    }

    public void setClasses(List<Class> classes) {
        System.out.println("ClassListViewModel: Setting " + classes.size() + " classes.");
        this.classes.clear();
        if (classes != null) {
            this.classes.addAll(classes);
        }
    }
}