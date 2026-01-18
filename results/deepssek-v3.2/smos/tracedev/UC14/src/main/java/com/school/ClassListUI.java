package com.school;

/**
 * UI for listing classes, from which the new class form is launched.
 * Precondition: User HAS performed 'ViewingLancoclasses' use case.
 */
public class ClassListUI {
    private int currentAcademicYear;
    private SessionManager sessionManager;
    private ClassFormUI classFormUI;

    public ClassListUI(SessionManager sessionManager, int currentAcademicYear) {
        this.sessionManager = sessionManager;
        this.currentAcademicYear = currentAcademicYear;
        // The ClassFormUI would be created when navigating to it.
    }

    public void displayClasses(int academicYear) {
        System.out.println("ClassListUI: Displaying classes for academic year " + academicYear);
        // Simulate displaying classes.
    }

    /**
     * Navigates to the new class form.
     * Called when the administrator clicks "New Class" button.
     */
    public void navigateToNewClassForm() {
        // Check authentication as per diagram note.
        if (!sessionManager.isAuthenticated()) {
            System.out.println("ClassListUI: User not authenticated.");
            return;
        }
        System.out.println("ClassListUI: Navigating to new class form.");
        // In a real system, this would launch the form UI.
        // For simulation, we assume the form UI is set elsewhere.
    }

    public int getCurrentAcademicYear() {
        return currentAcademicYear;
    }

    public void setClassFormUI(ClassFormUI classFormUI) {
        this.classFormUI = classFormUI;
    }
}