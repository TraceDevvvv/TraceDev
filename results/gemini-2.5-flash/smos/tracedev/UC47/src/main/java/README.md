# Report Card Management System

This project implements a simplified Report Card Management system based on the provided UML Class and Sequence Diagrams. It demonstrates the interaction between different layers of an application (Presentation, Application, Domain, Infrastructure) using Java.

## Project Structure

The project follows a layered architecture, with packages corresponding to the layers defined in the Class Diagram:

*   **`application`**: Contains application serv (`AuthenticationService`, `WorkflowService`, `ReportCardModificationService`) that encapsulate business logic and orchestrate domain objects and infrastructure components.
*   **`domain`**: Contains core business entities (`ReportCard`, `Student`, `Subject`, `User`) and enums (`Role`). These are pure Java objects representing the problem domain.
*   **`infrastructure`**: Contains components responsible for data persistence (`Database`, `IReportCardRepository`, `ReportCardRepository`) and custom exceptions (`PersistenceException`).
*   **`presentation`**: Contains UI-related components (`ReportCardController`, `ReportCardEditView`). The `ReportCardController` handles user input and orchestrates calls to application serv, while `ReportCardEditView` is a mock view for displaying information and receiving input.
*   **`Main.java`**: An executable class that sets up the application context and simulates the interactions described in the Sequence Diagram.

## How to Run

1.  **Save the files**: Save each code block into its respective file path (e.g., `src/main/java/domain/Role.java`, `src/main/java/Main.java`).
2.  **Compile**: Open a terminal or command prompt, navigate to the `src/main/java` directory (or the parent directory if you use an IDE), and compile the Java files.
    ```bash
    javac Main.java application/*.java domain/*.java infrastructure/*.java presentation/*.java
    ```
    If using an IDE like IntelliJ IDEA or Eclipse, you can simply open the project and compile it.
3.  **Run**: Execute the `Main` class.
    ```bash
    java Main
    ```

## Demonstration Details

The `Main.java` class simulates the following scenarios:

*   **Scenario 1: Successful Update**: An administrator successfully edits and updates a student's report card. This follows the happy path of the sequence diagram, including authentication, workflow checks, data retrieval, grade modification, and persistence.
*   **Scenario 2: Update Failure (Persistence Error)**: An administrator attempts to update a report card, but the persistence layer encounters an error (simulated by `database.setSimulatePersistenceError(true)`), leading to a failed update and an error message being displayed. This demonstrates the `alt` flow for error handling in the sequence diagram.
*   **Scenario 3: Access Denied (Non-Admin User)**: A non-administrator user (student) attempts to edit a report card. The `AuthenticationService` denies access, and an error message is displayed, fulfilling the `REQ-001` precondition.

## Assumptions Made

*   **User and Authentication**: A `currentUser` object is directly set in the `ReportCardController` for simulation. In a real application, this would come from a session management system.
*   **Workflow Service**: The `WorkflowService.hasUseCaseBeenCarriedOut()` method is mocked to always return `true` for the specified use case name to allow the main flow to proceed.
*   **Subject List**: The list of `Subject` objects passed to `ReportCardEditView.displayEditForm()` is hardcoded for simplicity. In a real application, this would be retrieved from a `SubjectService` or repository.
*   **`ReportCardEditView.getFormData()`**: This method returns a hardcoded `Map<String, Integer>` to simulate user input for new grades.
*   **Database**: The `Database` class is an in-memory mock using `HashMap`s to simulate storage and allows for toggling persistence errors.
*   **Student's Report Card ID**: For `Student.getReportCardId()`, it is assumed to be a direct attribute of the `Student` object, or derivable from the `studentId` itself, aligning with `findByStudentId` in the repository.
*   **Traceability (REQ-XXX)**: Comments are added in the code to indicate which requirements (`REQ-001`, `REQ-003`, `REQ-005`, `REQ-006`, `REQ-007`, `REQ-008`, `REQ-009`) are addressed by specific code elements.