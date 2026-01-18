package com.ata.usecase;

import com.ata.service.AuthenticationService;
import com.ata.service.ClassQueryService;
import com.ata.view.ClassListView;
import com.ata.entity.Class;
import java.util.List;

/**
 * Controller for the display classes use case. Implements the flow defined in the sequence diagram.
 * Entry conditions: user must be logged in and be an ATA staff member.
 */
public class DisplayClassesController {
    private ClassQueryService classQueryService;
    private AuthenticationService authenticationService;

    public DisplayClassesController(ClassQueryService classQueryService, AuthenticationService authenticationService) {
        this.classQueryService = classQueryService;
        this.authenticationService = authenticationService;
    }

    /**
     * Displays all classes after checking authentication.
     * Returns a ClassListView with classes and their registry keys.
     * Implements the sequence diagram interactions.
     */
    public ClassListView displayClasses() {
        // Check entry conditions as per sequence diagram
        if (!authenticationService.isLoggedIn()) {
            throw new SecurityException("User must be logged in.");
        }
        if (!authenticationService.isATAStaff()) {
            throw new SecurityException("User must be ATA staff.");
        }

        // Retrieve classes from service
        List<Class> classes = classQueryService.getAllClasses();

        // Create and return the view model
        return new ClassListView(classes);
    }

    /**
     * Create ClassListView as per sequence diagram message m9.
     */
    public ClassListView createClassListView(List<Class> classes) {
        return new ClassListView(classes);
    }
}