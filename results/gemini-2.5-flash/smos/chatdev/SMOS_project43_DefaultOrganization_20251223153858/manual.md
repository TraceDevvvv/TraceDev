```
# Disciplinary Note Management System User Manual

## 📖 Introduction

The "Disciplinary Note Management System" is a simple Java-based desktop application designed for school administrators to manage disciplinary notes issued to students. This system allows administrators to view a registry of all disciplinary notes, inspect the details of a specific note, and securely delete notes from the archive. A key feature of the system is the automated notification to parents when a disciplinary note is canceled and its simulated synchronization with an external SMOS server.

### ✨ Main Functions:

*   **Administrator Login**: Secure access to the system, restricting sensitive operations to authorized personnel.
*   **Disciplinary Notes Registry**: View a comprehensive list of all recorded disciplinary incidents.
*   **Note Details Viewing**: Access detailed information for any selected disciplinary note.
*   **Note Deletion**: Administrators can remove disciplinary notes from the system, which triggers:
    *   An automated notification to the student's parents regarding the cancellation.
    *   The removal of the note's data from the internal archive.
    *   A simulated attempt to synchronize the deletion with an external SMOS server.
*   **Logout**: Securely end the administrative session.

## 🛠️ Environment Setup and Installation

This application is developed in Java and uses Java Swing for its graphical user interface. To run this application, you will need a Java Development Kit (JDK) installed on your system.

### Prerequisites:

*   **Java Development Kit (JDK)**: Version 8 or higher. You can download it from Oracle's website or use an open-source distribution like OpenJDK.

### Installation Steps:

1.  **Save the Code**:
    *   Create a new directory (e.g., `DisciplinaryNotesApp`) on your computer.
    *   Save all the provided Java source files (`MainApplication.java`, `User.java`, `DisciplinaryNote.java`, `AuthService.java`, `NotificationService.java`, `ArchiveService.java`, `SMOSServerConnection.java`, `LoginScreen.java`, `RegistryScreen.java`, `NoteDetailScreen.java`) into this directory. Ensure all files are in the same folder.

2.  **Compile the Code**:
    *   Open your command prompt or terminal.
    *   Navigate to the directory where you saved the Java files:
        ```bash
        cd path/to/DisciplinaryNotesApp
        ```
    *   Compile all the Java files. If you have multiple files, you can compile them all at once:
        ```bash
        javac *.java
        ```
        If compilation is successful, no output will be shown, and `.class` files will be generated for each `.java` file in the same directory. If there are errors, ensure your JDK is correctly installed and configured in your system's PATH.

3.  **Run the Application**:
    *   After successful compilation, run the main application class:
        ```bash
        java MainApplication
        ```
    *   The application window should now appear on your screen.

## 🚀 How to Use the Application

Follow these steps to navigate and utilize the Disciplinary Note Management System:

### 1. Starting the Application

*   Execute the `java MainApplication` command as described in the "Environment Setup and Installation" section.
*   The application will launch and present the **Login Screen**.

### 2. Login as Administrator

*   **Login Screen**:
    *   The system comes with pre-configured demo user accounts.
    *   To perform "Delete Note" operations, you *must* log in as an **Administrator**.
    *   Enter the following credentials:
        *   **Username**: `admin`
        *   **Password**: `admin`
    *   Click the "Login" button.
    *   If successful, you will be directed to the **Disciplinary Notes Registry** screen. An attempt to log in with a non-admin user (e.g., `teacher`/`pass` or `parent`/`pass`) will show a success message but will deny access to admin-specific functions and log out the user.

### 3. Viewing the Disciplinary Notes Registry

*   **Registry Screen**:
    *   This screen displays a list of all existing disciplinary notes. The application initializes with some dummy data for demonstration.
    *   You can scroll through the list to see available notes.
    *   To view detailed information about a specific note, select a note from the list by clicking on it.
    *   Click the "View Note Details" button. This will take you to the **Note Details Screen**.
    *   You can also click the "Logout" button to return to the Login Screen.

### 4. Viewing Note Details and Deleting a Note

*   **Note Details Screen**:
    *   This screen shows comprehensive information for the selected note, including its ID, student ID, parent contact information, and the full disciplinary details.
    *   **Deleting a Note (Core Use Case)**:
        1.  **Preconditions Met**: You are logged in as an administrator, and you are currently viewing the details of a note.
        2.  **Click "Delete Note"**: Locate and click the "Delete Note" button at the bottom of the screen.
        3.  **Confirmation**: A confirmation dialog will appear, asking if you are sure you want to delete the note.
            *   **Confirm Deletion (YES)**:
                *   The system will attempt to send a notification to the student's parents (printed to console).
                *   The system will attempt to connect to the (simulated) SMOS server, synchronize the deletion, and then disconnect.
                *   The note data will be eliminated from the archive.
                *   A success message will be displayed, indicating if the parent notification was successful.
                *   The system will then return to the Registry Screen, and the deleted note will no longer appear in the list.
            *   **Interrupt Operation (NO)**:
                *   If you click "No" (or close the dialog), the deletion operation is canceled.
                *   A message will confirm that the operation was interrupted.
                *   The system will remain on the Note Details Screen.
    *   **Back to Registry**: Click the "Back to Registry" button to return to the list of notes without making any changes.

### 5. Logging Out

*   From the **Registry Screen**, click the "Logout" button.
*   You will be returned to the **Login Screen**.

This manual covers the essential functionalities and how to interact with the Disciplinary Note Management System. In a real-world scenario, the simulated serv (notification, SMOS connection) would integrate with actual external systems.