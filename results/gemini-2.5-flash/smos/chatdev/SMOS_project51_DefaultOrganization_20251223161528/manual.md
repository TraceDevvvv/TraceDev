# Administrator Dashboard - Student Enrollment Rejection

This application provides an administrative interface for managing student registration requests. Its primary function is to enable administrators to review pending student registrations and reject specific requests.

## 🤔 What is this?

This is a standalone Java Swing application designed for administrators. It addresses the "RejectEnrollmentStudent" use case, allowing an administrator to refuse a student's registration to the system. The application demonstrates a simple GUI for displaying registration requests and processing rejection actions.

## 🌟 Main Functions

The Administrator Dashboard offers the following key functionalities:

*   **View Pending Registration Requests**: Upon launching, the dashboard automatically displays a table of all pending student registration requests. Each request includes an ID, student name, and current status.
*   **Reject Student Enrollment**: For each pending registration, there is an associated "Reject" button. Clicking this button initiates the process of refusing and eliminating that specific student's registration request from the system.
*   **Refresh Pending Requests**: A "Refresh Pending Requests" button allows the administrator to manually update the displayed list of pending requests, ensuring the most current information is shown after actions or external changes.

## 💾 Installation Guide

To run this application, you need to have the Java Development Kit (JDK) installed on your system.

### Prerequisites

*   **JDK (Java Development Kit)**: Version 8 or higher (`JDK>=8`).
    *   You can download and install the latest LTS version of OpenJDK from adoptium.net or Oracle's official website.
    *   Ensure your `JAVA_HOME` environment variable is set correctly and `java` and `javac` commands are accessible from your terminal.

### Compiling the Source Code

1.  **Save the files**: Save all the provided `.java` files (`Main.java`, `StudentRegistration.java`, `RegistrationService.java`, `AdminDashboardFrame.java`) into a single directory (e.g., `ChatDevAdminApp`).
2.  **Open a terminal/command prompt**: Navigate to the directory where you saved the files.
    ```bash
    cd path/to/ChatDevAdminApp
    ```
3.  **Compile**: Use the `javac` command to compile all Java source files.
    ```bash
    javac *.java
    ```
    If compilation is successful, `.class` files will be generated in the same directory for each `.java` file.

## ▶️ How to Use/Play

After successfully compiling the application, you can run it from your terminal.

1.  **Run the application**: Execute the `Main` class using the `java` command.
    ```bash
    java Main
    ```
2.  **Administrator Dashboard Appears**: A new window titled "Administrator Dashboard - Reject Enrollment" will appear. This window will initially display a list of sample pending student registration requests.
    *   Each row represents a student, showing their ID, Name, and Status.
    *   The last column will contain "Reject" buttons for each pending request.

3.  **Reject a Student Registration**:
    *   Locate the student registration you wish to reject in the table.
    *   Click the "Reject" button in the "Action" column corresponding to that student's row.
    *   A confirmation message will appear if the rejection is successful, indicating the request has been eliminated.
    *   The rejected student's entry will automatically disappear from the table as the list is refreshed to show only remaining pending requests.

4.  **Refresh the List**:
    *   If you want to manually update the list of pending requests (e.g., if you expect changes from another source or simply want to re-fetch), click the "Refresh Pending Requests" button at the bottom of the window.

5.  **Exiting the Application**:
    *   Close the application window by clicking the standard close button (X) in the top right (or left, depending on your OS) corner of the frame. This will terminate the application.

This application provides a direct and simple way for an administrator to manage and reduce the queue of pending student registrations.