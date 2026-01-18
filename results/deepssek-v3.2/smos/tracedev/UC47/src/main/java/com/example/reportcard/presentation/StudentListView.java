package com.example.reportcard.presentation;

/**
 * Boundary class for displaying the student list (previous use case).
 */
public class StudentListView {
    public void displayStudentList() {
        System.out.println("Displaying student list...");
    }
    
    public void displayFormWithListOfStudents() {
        System.out.println("Displaying form with list of students");
    }

    public void onStudentSelected(String studentId) {
        System.out.println("Student selected: " + studentId);
        // In a real system, this would navigate to the edit view.
    }
}