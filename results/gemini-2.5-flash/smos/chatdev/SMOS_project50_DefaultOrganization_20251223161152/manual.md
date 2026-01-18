```
# Student Enrollment Acceptance System

**Empowering administrators to streamline student registration activation.**

This application provides a simple yet effective Graphical User Interface (GUI) for administrators to manage student enrollment requests. It allows for the acceptance of pending student registrations, thereby activating them within the system.

## 🤔 What is this?

The "Student Enrollment Acceptance System" is a standalone Java application designed for administrators. It addresses the `AcceptEnrollmentStudent` use case, enabling efficient management of student registration requests. The application simulates typical backend interactions, providing a clear interface to view and process pending enrollments.

### Core Features:

*   **View Pending Registrations**: Displays a list of all students whose registrations are awaiting approval.
*   **Accept Enrollment**: Allows an administrator to select a pending student and activate their enrollment in the system.
*   **Real-time Updates**: The list of pending registrations is automatically refreshed after an enrollment is accepted, providing an up-to-date view.
*   **Status Feedback**: Provides clear messages to the user regarding the success or failure of an acceptance operation.

## 💻 Environment Setup and Installation

To run this application, you will need a Java Development Kit (JDK) installed on your system.

### Prerequisites:

*   **Java Development Kit (JDK) 8 or higher**:
    *   You can download the latest JDK from Oracle's official website or use open-source distributions like OpenJDK.
    *   Ensure that `java` and `javac` commands are accessible from your terminal/command prompt. You can verify this by running `java -version` and `javac -version`.

### Installation and Compilation:

1.  **Save the files**: Place all provided `.java` files (`AcceptEnrollmentStudentApp.java`, `Student.java`, `RegistrationService.java`, `EnrollmentView.java`) into a single directory (e.g., `StudentEnrollmentApp`).

2.  **Open a terminal or command prompt**: Navigate to the directory where you saved the files.

    ```bash
    cd path/to/StudentEnrollmentApp
    ```

3.  **Compile the Java source code**: Use the Java compiler (`javac`) to compile all `.java` files.

    ```bash
    javac *.java
    ```

    *   If compilation is successful, several `.class` files will be generated in the same directory.
    *   If you encounter errors, ensure your JDK is correctly installed and configured, and that all `.java` files are in the correct directory.

## ▶️ How to Use the Application

Once the application is compiled, you can run it from the command line.

1.  **Run the application**: Execute the main class (`AcceptEnrollmentStudentApp`) using the Java Virtual Machine (`java`).

    ```bash
    java AcceptEnrollmentStudentApp
    ```

2.  **Application Window**: A GUI window titled "Student Enrollment Acceptance" will appear.
    *   Initially, you will see a list of "Pending Registration Requests," which includes dummy student data (e.g., Alice Smith, Bob Johnson, Charlie Brown) that are in a `PENDING` state.

3.  **Accepting an Enrollment**:
    *   **Select a student**: Click on one of the student entries in the list (e.g., "ID: S001 - Name: Alice Smith (PENDING)"). The selected entry will be highlighted.
    *   **Click the 'Accept Selected Enrollment' button**: This button is located at the bottom of the window.
    *   **Confirmation**: A "Enrollment Accepted" message box will pop up, confirming the successful activation of the selected student. Click "OK".
    *   **List Update**: The accepted student will be removed from the "Pending Registration Requests" list, and the list will refresh, showing only the remaining pending students. The status bar at the bottom will also show an update.

4.  **Handling Edge Cases and Errors**:
    *   **No student selected**: If you click the 'Accept Selected Enrollment' button without selecting any student from the list, a "No Student Selected" warning message will appear, prompting you to select a student.
    *   **No pending registrations**: Once all dummy students have been accepted, the list will be empty, and the "Accept Selected Enrollment" button will be disabled, indicating that there are no more pending requests. The status label will reflect this.

5.  **Exiting the Application**:
    *   To close the application, simply click the 'X' button in the top-right corner of the window (standard window close action). This simulates the user interrupting the connection to the server.

This manual should provide a clear guide for anyone needing to use or test the Student Enrollment Acceptance System.
```