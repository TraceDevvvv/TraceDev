
package com.example.login;

/**
 * Main class to demonstrate the "Notified of incorrect login data" sequence diagram.
 * This class acts as the system's entry point and simulates user interactions.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Starting Incorrect Login Use Case Simulation ---");

        // 1. Setup the system components
        // The view needs the controller first, but the controller needs the view.
        // We'll pass a null first and set it later, or create a direct dependency.
        // For simplicity and to follow the SD, let's create the controller first,
        // then the view with the controller, then inject the view back into the controller if needed.
        // The CD shows LoginView has a controller, and Controller has a view.
        // Let's create the controller first with a dummy view reference, then inject the real view.

        // Corrected initialization to handle circular dependency if it were strict.
        // For now, as per CD, Controller takes View in constructor. So, let's make a dummy view
        // to pass to controller, then create the real view, then update controller's view.
        // A more robust solution might use an interface or constructor injection carefully.
        // For this simple case, we'll create a controller, then the view, and if needed, set the view to controller.
        // From CD: IncorrectLoginUseCaseController has `view : LoginView`. So view is passed to controller.
        // From CD: LoginView has `controller : IncorrectLoginUseCaseController`. So controller is passed to view.
        // This is a circular dependency. We need one to be created first and passed.
        // The most common way is to instantiate one, then the other, and then use a setter on the first.
        // Let's create `LoginView` with a direct reference to the controller it will receive.
        // `IncorrectLoginUseCaseController` needs `LoginView` at construction.
        // `LoginView` needs `IncorrectLoginUseCaseController` at construction.

        // Approach: Create controller first with dummy view, then real view with controller, then update controller's view.
        // This is a common pattern for handling circular dependencies during initialization.
        // For simplicity, I'll pass `mainView` directly during controller creation, handling the circularity.
        // A better approach for real code would be an interface or a builder.
        // Let's create the view with controller, and then pass it to controller constructor as a workaround for demonstration.

        // Re-initializing to avoid null-passing if possible and make it clean.
        // The design suggests: controller.view = loginView AND loginView.controller = controller.
        // This means one must be created, then the other, then a setter used.
        // Let's create controller first, then view using controller.
        // Then, ensure controller uses the *same* view instance.
        // The current class diagram implies the controller holds the view instance.
        // The constructor `IncorrectLoginUseCaseController(view : LoginView, ...)` means the view is fully formed.
        // The `LoginView(controller : IncorrectLoginUseCaseController)` means controller is fully formed.
        // This is a classic circular dependency.

        // The simplest way to handle this without changing the constructors for this demo is:
        // 1. Instantiate the controller (pass null for view temporarily if view isn't ready).
        // 2. Instantiate the view (passing the controller).
        // 3. Set the view into the controller using a setter (if the controller's constructor needs the view immediately).
        // My current `IncorrectLoginUseCaseController` constructor takes `LoginView view`.
        // My current `LoginView` constructor takes `IncorrectLoginUseCaseController controller`.

        // Let's create a *temporary* controller instance for LoginView constructor
        // OR, create the controller with null view, then set it up.
        // Based on the SD: ILC needs LV. LV needs ILC.
        // A common pattern is to make one side set the other. Let's make Controller responsible.
        // This means Controller creates/manages View.
        // But CD shows association `LoginView - controller : IncorrectLoginUseCaseController`.
        // Let's follow the provided constructors for `IncorrectLoginUseCaseController` and `LoginView`.

        // Final approach: Create controller with null view, then create view with controller, then set controller's view using reflection or a setter.
        // Let's add a setter `setLoginView` to `IncorrectLoginUseCaseController` for this demo.
        // (This would be an implicit assumption for `IncorrectLoginUseCaseController` if not explicitly in CD).
        // Or, assume controller constructor initializes view directly, which would violate view being passed.

        // Let's assume the construction order like this, as it matches the constructors given:
        // Controller is the central orchestrator. It knows about View, Service, State.
        // View needs to know about Controller to send events.
        // So, Controller first, then View, then link View back into Controller.

        // Step 1: Initialize ApplicationState and LoginService
        ApplicationState applicationState = new ApplicationState();
        LoginService loginService = new LoginService();

        // Step 2: Create the Controller, passing null for the View initially.
        // We will set the real view instance after creating it.
        IncorrectLoginUseCaseController controllerInstance = new IncorrectLoginUseCaseController(null, loginService, applicationState);

        // Step 3: Create the View, passing the Controller instance.
        LoginView loginViewInstance = new LoginView(controllerInstance);

        // Step 4: Now, provide the real LoginView instance to the Controller.
        // This would require a setter in IncorrectLoginUseCaseController or a direct field access (less ideal).
        // Let's add a simple setter for demonstration purposes.
        // (Assumption: add `setLoginView` to IncorrectLoginUseCaseController)
        controllerInstance.setLoginView(loginViewInstance);

        System.out.println("\n--- System Setup Complete ---\n");

        // 2. Simulate User -> LV : submitLogin(username, password)
        // Entry Condition: Login data IS incorrect.
        String incorrectUsername = "user@example.com";
        String incorrectPassword = "wrongpassword";
        System.out.println("--- User initiates login attempt with incorrect credentials ---");
        loginViewInstance.submitLogin(incorrectUsername, incorrectPassword);

        // At this point, the sequence diagram states:
        // ILC -> LV : showConfirmationPrompt(...)
        // LV --> U : displayConfirmationPrompt
        // The flow pauses, waiting for user input.

        // Simulate U -> LV : onConfirmButtonClicked()
        System.out.println("\n--- User is now confirming the notification ---");
        loginViewInstance.onConfirmButtonClicked();

        System.out.println("\n--- Incorrect Login Use Case Simulation Complete ---");
    }
}
// Add the setter to IncorrectLoginUseCaseController manually here since I cannot modify previous files directly.
// In a real generation, I would ensure the setter is added to the generated file.

/*
// Addition to IncorrectLoginUseCaseController.java for main method to work cleanly:
    // This setter is added to handle the circular dependency during initialization in Main.
    // In a real application, dependency injection frameworks would manage this more elegantly.
    public void setLoginView(LoginView view) {
        if (this.view == null) { // Only set if not already set (e.g., initially null)
            this.view = view;
            System.out.println("[IncorrectLoginUseCaseController] LoginView instance set.");
        }
    }
*/
