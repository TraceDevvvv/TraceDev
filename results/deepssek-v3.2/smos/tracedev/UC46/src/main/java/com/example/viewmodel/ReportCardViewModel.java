package com.example.viewmodel;

import com.example.controller.InsertReportCardController;
import com.example.dto.ClassDTO;
import com.example.dto.ReportCardDTO;
import com.example.dto.StudentDTO;
import java.util.List;

/**
 * ViewModel for Report Card operations.
 */
public class ReportCardViewModel {
    private InsertReportCardController insertReportCardController;
    private ClassDTO selectedClass;
    private StudentDTO selectedStudent;

    public ReportCardViewModel(InsertReportCardController insertReportCardController) {
        this.insertReportCardController = insertReportCardController;
    }

    /**
     * Loads classes for the current academic year.
     * @return list of ClassDTO objects
     */
    public List<ClassDTO> loadClasses() {
        // Assume current year is 2024 for demonstration
        int currentYear = 2024;
        return insertReportCardController.getClassesByAcademicYear(currentYear);
    }

    /**
     * Selects a class by its ID.
     * @param classId the class ID
     * @return true if selection successful, false otherwise
     */
    public boolean selectClass(int classId) {
        List<ClassDTO> classes = loadClasses();
        for (ClassDTO classDto : classes) {
            if (classDto.id == classId) {
                selectedClass = classDto;
                return true;
            }
        }
        return false;
    }

    /**
     * Loads students for the selected class.
     * @return list of StudentDTO objects
     */
    public List<StudentDTO> loadStudents() {
        if (selectedClass == null) {
            throw new IllegalStateException("No class selected.");
        }
        return insertReportCardController.getStudentsByClass(selectedClass.id);
    }

    /**
     * Selects a student by its ID.
     * @param studentId the student ID
     * @return true if selection successful, false otherwise
     */
    public boolean selectStudent(int studentId) {
        List<StudentDTO> students = loadStudents();
        for (StudentDTO studentDto : students) {
            if (studentDto.id == studentId) {
                selectedStudent = studentDto;
                return true;
            }
        }
        return false;
    }

    /**
     * Saves a report card.
     * @param data the report card data
     * @return true if save successful, false otherwise
     */
    public boolean saveReportCard(ReportCardDTO data) {
        if (selectedClass == null || selectedStudent == null) {
            throw new IllegalStateException("Class or student not selected.");
        }
        // Convert ReportCardDTO to InsertReportCardCommand
        // For now, assume academic year is taken from selectedClass
        com.example.dto.InsertReportCardCommand command = new com.example.dto.InsertReportCardCommand(
                selectedStudent.id,
                selectedClass.id,
                selectedClass.academicYear,
                data.grades
        );
        return insertReportCardController.insertReportCard(command);
    }

    /**
     * Cancels the current operation.
     */
    public void cancelOperation() {
        // Clean up the view model and notify the controller to cleanup
        insertReportCardController.cleanup();
        selectedClass = null;
        selectedStudent = null;
    }

    // Getters and setters for selectedClass and selectedStudent (if needed)
    public ClassDTO getSelectedClass() {
        return selectedClass;
    }

    public void setSelectedClass(ClassDTO selectedClass) {
        this.selectedClass = selectedClass;
    }

    public StudentDTO getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(StudentDTO selectedStudent) {
        this.selectedStudent = selectedStudent;
    }
}