```
# Justification Insertion System

A simple Java Swing application designed for administrators to manage and justify employee absences.

## 🤔 What is this?

The Justification Insertion System is a desktop application built with Java Swing that provides a user interface for administrators to view a list of employee absences and insert justifications for those that are currently marked as unjustified. It simulates interaction with a backend service (e.g., SMOS server) for saving justification data.

## 🌟 Main Functions

### 1. View Employee Absences (Registry Screen)

*   **Display Absence Records:** Presents a list of all recorded employee absences, including the employee's name, the date of absence, and its current justification status.
*   **Highlight Unjustified Absences:** Absences that are not yet justified are prominently displayed in `red` to draw the administrator's attention.
*   **Select Absence for Justification:** Administrators can select an unjustified absence from the list to proceed with the justification process.

### 2. Justify an Absence (Justification Form)

*   **Detailed Absence Information:** Once an absence is selected, a form is displayed showing details of the absence to be justified.
*   **Input Justification Date:** An input field allows the administrator to enter the specific date of justification (e.g., the date the justification document was received or approved).
*   **Save Justification:** Upon entering the date, the administrator can save the justification, which attempts to record the data via a simulated service.
*   **Cancel Operation:** The administrator has the option to cancel the justification process at any time, returning to the registry screen without saving changes.

### 3. System Interaction and Feedback

*   **Data Persistence Simulation:** The system simulates the saving of justification data to a backend.
*   **SMOS Server Connection Check:** It includes a simulated check for a "SMOS server" connection, demonstrating how external system availability can affect operations. If the server is "disconnected," justification cannot be saved.
*   **Real-time Updates:** After a successful justification, the registry screen automatically updates to reflect the new justified status of the absence.
*   **Error Handling:** Provides user-friendly messages for invalid date inputs, server connection issues, or other simulated saving failures.

## 🛠 Environment Dependencies

To compile and run this application, you will need:

*   **Java Development Kit (JDK) 11 or higher:** The application uses features available in modern Java versions, particularly `java.time` for date handling.
    *   You can download the JDK from Oracle or Adoptium (OpenJDK). Ensure `JAVA_HOME` is set and `java` and `javac` commands are accessible in your system's PATH.

*   **No external libraries are required** beyond the standard Java platform (Swing, AWT).

## 🚀 How to Use/Play it

Follow these steps to get the Justification Insertion System up and running:

### Step 1: Download the Code

1.  Save all the provided Java code files (`MainApp.java`, `Absence.java`, `Justification.java`, `JustificationService.java`, `JustificationFormPanel.java`, `RegistryScreenPanel.java`) into a single directory on your computer (e.g., `C:\JustificationSystem` or `~/JustificationSystem`).

### Step 2: Compile the Application

1.  Open your command line interface (Terminal on macOS/Linux, Command Prompt or PowerShell on Windows).
2.  Navigate to the directory where you saved the Java files using the `cd` command. For example:
    ```bash
    cd C:\JustificationSystem
    ```
    or
    ```bash
    cd ~/JustificationSystem
    ```
3.  Compile all the Java source files:
    ```bash
    javac *.java
    ```
    If compilation is successful, you will see no output. If there are errors, ensure your JDK is correctly installed and configured.

### Step 3: Run the Application

1.  From the same command line window, run the main application:
    ```bash
    java MainApp
    ```
2.  A new Swing window titled "Justification System - Registry Screen" will appear.

### Step 4: Interact with the Application

#### Viewing Absences

*   The initial screen displays a list of employee absences.
*   Notice that some absences are displayed in `red` and bold text. These are the *unjustified* absences that an administrator needs to address. Other absences are justified and shown in black.

#### Justifying an Absence

1.  **Select an Unjustified Absence:**
    *   **Option A (Double-Click):** Double-click on any absence entry that is displayed in `red`.
    *   **Option B (Button):** Single-click on a `red` absence to select it, then click the "Justify Selected Absence" button at the bottom.
    *   **Note:** If you try to select an already justified (black) absence, the system will prevent you from justifying it again.

2.  **Fill Out the Justification Form:**
    *   After selecting an unjustified absence, the screen will switch to the "Justification Form".
    *   The form will display information about the selected absence.
    *   There will be a text field labeled "Justification Date (YYYY-MM-DD):" which is pre-filled with the current date.
    *   You can edit this date. **Ensure you use the `YYYY-MM-DD` format (e.g., `2023-10-27`).** Incorrect formats will result in an error message.

3.  **Save the Justification:**
    *   Click the "Save Justification" button.
    *   The system will attempt to save the justification.
    *   **Success:** If successful, you will see a "Justification saved successfully!" message, and the application will return to the Registry Screen. The absence you just justified will now appear in black.
    *   **Failure (Simulated):**
        *   The `JustificationService` has a simulated 10% chance of failure, mimicking real-world database errors. If this occurs, you'll get a "Failed to save justification." message, and the system will still return to the registry screen (with the absence still unjustified).
        *   **SMOS Server Interruption (Simulated Precondition):** If the simulated "SMOS server connection" is interrupted (this can be controlled by modifying `JustificationService.smosServerConnected` in the code for testing purposes, but is `true` by default), you will receive a "SMOS server connection interrupted. Cannot save justification." error, and the operation will not proceed.

4.  **Cancel the Operation:**
    *   Click the "Cancel" button on the Justification Form.
    *   The system will return to the Registry Screen without saving any changes. A message "Operation cancelled by administrator" will be displayed.

### Step 5: Exit the Application

*   Close the application window using the standard window close button (X icon).

This manual provides a detailed guide for administrators to effectively use the Justification Insertion System, encompassing its features, setup, and operational instructions.