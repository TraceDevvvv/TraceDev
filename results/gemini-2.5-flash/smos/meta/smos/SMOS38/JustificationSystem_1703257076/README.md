# Justification System Administrator Portal

This project implements a simple console-based Java application that simulates an Administrator's interaction with a Justification System. The primary use case demonstrated is "View Justification Details," allowing an administrator to log in, select an absence with an associated justification, view its details, and then modify or delete that justification.

## Use Case: ViewJustificationDetails

**Actors:** Administrator

**Description:** View the details of a justification.

**Preconditions:**
*   The user must be logged in to the system as an administrator.
*   The user has navigated to a list of "green absences" (absences with pending or reviewed justifications) and clicks on one.

**Events Sequence:**
1.  **System:** Shows a form with the details of the justification and the possibility of modifying or eliminating the justification.

**Postconditions:**
*   The system is viewing the details of a justification.
*   The administrator interrupts the connection to the SMOS server (simulated by application exit).

## Project Structure

The project consists of the following Java classes:

*   `Justification.java`: Represents a justification entity with properties like ID, absence ID, reason, status, and submission date.
*   `JustificationService.java`: Manages the business logic for justifications, including retrieving, updating, and deleting them. It uses an in-memory `HashMap` as a data store for demonstration.
*   `Administrator.java`: A simplified model for an administrator user, primarily for simulating login.
*   `JustificationApp.java`: The main application entry point. It orchestrates the user login, absence selection, and justification detail viewing/modification/deletion process.

## How to Compile

1.  **Navigate to the project directory:**
    Open your terminal or command prompt and change the directory to where you saved the Java files:
    ```bash
    cd JustificationSystem_1703257076
    ```

2.  **Compile the Java source files:**
    Use the Java compiler (`javac`) to compile all `.java` files.
    ```bash
    javac Justification.java JustificationService.java Administrator.java JustificationApp.java
    ```
    If compilation is successful, this command will generate `.class` files for each Java source file in the same directory.

## How to Run

1.  **Execute the main application:**
    After successful compilation, run the `JustificationApp` class using the Java Virtual Machine (`java` command):
    ```bash
    java JustificationApp
    ```

## Example Interaction

Upon running the application, you will be prompted to log in:

1.  **Login:**
    *   **Username:** `admin`
    *   **Password:** `password123`

2.  **Absence Selection:**
    The system will display a list of "Available Absences with Justifications" (e.g., `A001`, `A002`, `A003`, `A004`). You will be prompted to enter an Absence ID.
    *   Example: Enter `A001`

3.  **View Justification Details:**
    The details for the justification associated with `A001` (e.g., `J001`) will be displayed.

4.  **Options:**
    You will then be presented with options to:
    *   `1. Modify Justification`: Change the reason or status.
    *   `2. Delete Justification`: Remove the justification from the system.
    *   `3. Back to Main Menu / Exit`: Exit the current view.

Follow the prompts to interact with the system. The application will simulate the administrator's session and then gracefully exit, representing the interruption of the connection to the SMOS server.