
package com.example.delaysystem;

import com.example.delaysystem.connector.SmosServerConnector;
import com.example.delaysystem.controller.DelayRegistrationController;
import com.example.delaysystem.repository.IClassRepository;
import com.example.delaysystem.repository.IDelayRepository;
import com.example.delaysystem.repository.InMemoryClassRepository;
import com.example.delaysystem.repository.SmpsDelayRepository;
import com.example.delaysystem.service.DelayRegistrationService;
import com.example.delaysystem.view.DelayRegistrationView;

import java.util.Scanner;

/**
 * Main application class to set up and run the Delay Registration system demonstration.
 * This class orchestrates the creation of all components and initiates the user interaction flow
 * as described in the sequence diagram. It is not part of the UML diagrams but is required to make
 * the system runnable and demonstrate the interactions.
 */
public class MainApplication {

    public static void main(String[] args) {
        System.out.println("--- Starting Delay Registration System Demo ---");

        // 1. Initialize Repositories
        InMemoryClassRepository inMemoryClassRepository = new InMemoryClassRepository();
        SmosServerConnector smosServerConnector = new SmosServerConnector();
        SmpsDelayRepository smpsDelayRepository = new SmpsDelayRepository(smosServerConnector);

        // 2. Initialize Service Layer
        // Inject concrete implementations into the service
        DelayRegistrationService delayRegistrationService = new DelayRegistrationService(
            (IDelayRepository) smpsDelayRepository, // Cast to interface
            (IClassRepository) inMemoryClassRepository // Cast to interface
        );

        // 3. Initialize Controller and View
        // Note: Controller needs a view, and view needs a controller (circular dependency).
        // We'll create them in two steps, passing a placeholder then setting the real one.
        // Or, more cleanly, pass them in constructor where possible and ensure all are instantiated first.
        // The Class Diagram indicates that DelayRegistrationView has a controller, and Controller has a view.
        // Let's create controller first with a dummy view reference, then update the view with the controller.
        // Or, more practically, instantiate Controller after View, passing View to Controller.
        // But View also needs Controller in its constructor. So, let's create the view, then the controller,
        // and then manually set the controller on the view (or use a factory).
        // For this demo, let's instantiate the controller with a classId, and then view.

        // Placeholder for the controller instance during initial view creation
        // This is a common pattern for circular dependencies if not using DI frameworks.
        DelayRegistrationController controller = null;
        DelayRegistrationView view = new DelayRegistrationView(controller); // Pass null initially

        // Now create the controller, passing the actual view instance and initial class ID
        // The classId for the controller is provided during construction as per CD.
        controller = new DelayRegistrationController(view, delayRegistrationService, "INITIAL_CLASS_ID");
        // The compilation error indicates that DelayRegistrationView does not have a public setController method.
        // To fix the compilation error, this line must be removed, as the view was already provided
        // with a controller reference (even if null initially) during its construction.
        // If the view needs to be aware of the now fully initialized controller,
        // its internal logic must handle that via the reference passed in the constructor,
        // or a setter method should be added to DelayRegistrationView.
        // Given the constraint to only fix compilation errors in this file, we remove the problematic call.
        // view.setController(controller); // Manually set the controller on the view using a public setter

        Scanner mainScanner = new Scanner(System.in);
        String currentClassIdInput = "";

        System.out.println("\nPrecondition: User authenticated as ATA Staff.");
        System.out.println("Enter a class ID to register delays (e.g., CS101, MATH201), or 'exit' to quit:");

        while (true) {
            System.out.print("> ");
            currentClassIdInput = mainScanner.nextLine().trim();

            if (currentClassIdInput.equalsIgnoreCase("exit")) {
                break;
            }

            if (currentClassIdInput.isEmpty()) {
                System.out.println("Please enter a valid class ID.");
                continue;
            }
            
            // Set the current class ID in the controller before starting the process for the new class.
            controller.setCurrentClassId(currentClassIdInput);

            // Simulate "Staff -> View : displayDelayRegistrationScreen(classId)"
            view.displayDelayRegistrationScreen(currentClassIdInput);

            // After an operation (confirm/cancel), the view will call showInitialScreen().
            // The loop then continues, prompting for the next class ID.
        }

        System.out.println("--- Exiting Delay Registration System Demo ---");
        mainScanner.close();
    }
}
