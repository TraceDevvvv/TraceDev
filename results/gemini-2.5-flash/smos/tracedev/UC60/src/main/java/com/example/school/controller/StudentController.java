package com.example.school.controller;

import com.example.school.model.ClassRegistryDTO;
import com.example.school.service.AuthenticationService;
import com.example.school.service.SecurityService;
import com.example.school.service.StudentService;
import com.example.school.view.StudentView;

import java.util.List;

/**
 * The controller handles requests related to student information.
 * It orchestrates interactions between the service layer and the view layer,
 * and incorporates authentication and security checks.
 */
public class StudentController {

    private final StudentService studentService;
    private final StudentView studentView;
    private final AuthenticationService authenticationService; // Added as per CD and SD
    private final SecurityService securityService;             // Added as per CD and SD

    /**
     * Constructs a StudentController with dependencies injected.
     *
     * @param studentService The service for retrieving student data.
     * @param studentView The view for displaying student data and messages.\n     * @param authenticationService The service for checking student authentication.\n     * @param securityService The service for checking access and ensuring secure operations.
     */
    public StudentController(StudentService studentService, StudentView studentView,
                             AuthenticationService authenticationService, SecurityService securityService) {
        this.studentService = studentService;
        this.studentView = studentView;
        this.authenticationService = authenticationService;
        this.securityService = securityService;
    }

    /**
     * Displays the class registry for a given student. This is the main entry point
     * for the "View Student School Information" use case as per the sequence diagram.
     * It performs authentication, access control, data retrieval, and presentation.
     *
     * @param studentId The ID of the student whose class registry is to be displayed.
     */
    public void displayStudentClassRegistry(String studentId) {
        System.out.println("StudentController: Received request to display class registry for student: " + studentId);

        // SD: Controller -> AuthSvc : isAuthenticated(studentId)
        if (!authenticationService.isAuthenticated(studentId)) {
            // SD: Student -> System : "Error: Could not display student information." (via View)
            studentView.showErrorMessage("Authentication failed for student " + studentId + ". Please log in.");
            System.out.println("Controller: Error message displayed confirmation from View. (Trace: m25)"); // Explicit trace for m25
            System.out.println("System: display error (Trace: m26)");
            System.out.println("Student: Error: Could not display student information. (Trace: m27)");
            return; // Exit condition for unauthenticated user
        }

        // SD: Controller -> SecSvc : checkAccess(studentId, "classRegistry")
        // alt Access Granted
        if (!securityService.checkAccess(studentId, "classRegistry")) {
            // SD: Student -> System : "Error: Access Denied to student information." (via View)
            studentView.showErrorMessage("Access denied for student " + studentId + " to class registry.");
            System.out.println("Controller: Error message displayed confirmation from View. (Trace: m25)"); // Explicit trace for m25
            System.out.println("System: display error (Trace: m26)");
            System.out.println("Student: Error: Could not display student information. (Trace: m27)");
            return; // Exit condition for unauthorized user
        }

        // SD: Controller -> Service : getStudentClassRegistry(studentId)
        List<ClassRegistryDTO> classRegistryDTOs = studentService.getStudentClassRegistry(studentId); // This method's return is m18

        // SD: alt Successful Data Retrieval / else Error/Failure
        if (classRegistryDTOs != null) { // A null DTO list indicates an internal service error (m18 error path)
            // SD: Controller -> View : renderClassRegistry(classRegistryDTOs)
            studentView.renderClassRegistry(classRegistryDTOs);
            System.out.println("Controller: Rendering complete confirmation from View. (Trace: m20)"); // Explicit trace for m20
            System.out.println("System: display success confirmation (Trace: m21)");
            System.out.println("Student: The student's class registry data was shown (Trace: m22)");
            System.out.println("Note: Exit Condition: The student's class registry data was shown (Trace: m23)");
        } else {
            // SD: Controller -> View : showErrorMessage("Failed to retrieve student data.")
            studentView.showErrorMessage("Failed to retrieve student data for " + studentId + ". Please try again later.");
            System.out.println("Controller: Error message displayed confirmation from View. (Trace: m25)"); // Explicit trace for m25
            System.out.println("System: display error (Trace: m26)");
            System.out.println("Student: Error: Could not display student information. (Trace: m27)");
        }
    }
}