package com.example.app;

import java.util.List;

/**
 * Controller for managing the display of class lists.
 * This class corresponds to the 'ClassListController' class in the UML Class Diagram (R5).
 */
public class ClassListController {
    private ClassService classService;
    private ClassListView classListView;

    /**
     * Constructs a new ClassListController.
     *
     * @param service The service responsible for class data.
     * @param view The view responsible for displaying the class list.
     */
    public ClassListController(ClassService service, ClassListView view) {
        this.classService = service;
        this.classListView = view;
    }

    /**
     * Displays the list of classes for a given academic year.
     * This method is part of requirement R5, which is a precondition for "View Class Details".
     *
     * @param academicYear The academic year to filter classes by.
     */
    public void showClassList(String academicYear) {
        System.out.println("Controller: showClassList(" + academicYear + ") - Requesting all classes for year.");
        try {
            List<ClassDetailsDTO> classList = classService.getAllClassesForYear(academicYear);
            classListView.displayClassList(classList);
        } catch (ConnectionException e) {
            System.err.println("Controller: Failed to retrieve class list due to connection error: " + e.getMessage());
            // In a real app, this would use a dedicated error display for ClassListView.
        }
    }
}