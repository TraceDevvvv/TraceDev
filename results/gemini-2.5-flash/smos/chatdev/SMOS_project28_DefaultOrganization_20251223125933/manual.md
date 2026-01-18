```markdown
# Class Register Details Viewer (Admin Mode)

This application provides administrators with a detailed view of class registers, allowing them to review attendance, lateness, justifications, and disciplinary notes for individual students on a specific date. It is designed to simulate a module within a larger school management system, focusing on the "ViewDetailsSingleRegister" use case.

## 🤔 What is this?

The Class Register Details Viewer is a standalone Java Swing application that allows an administrator to:
*   View a class register's attendance details organized by date.
*   See a list of students for a selected date, indicating their attendance status (present, absent, late).
*   Access a form to manage justifications for absences/lateness and disciplinary notes for each student.
*   Update attendance status, lateness, justification, and disciplinary notes, with simulated persistence.

The application adheres to the use case "ViewDetailsSingleRegister", where an administrator, after having selected a register from a list, can delve into its specific details for different dates.

## 🚀 Main Functions

*   **Date-based Register Viewing**: Navigate attendance records across different dates using a date picker.
*   **Student Attendance Overview**: Clearly displays each student's ID, name, attendance status, and whether they were late.
*   **Justification Management**: Edit and save justifications for student absences or lateness.
*   **Disciplinary Note Management**: Add, update, and save disciplinary notes associated with students for a particular day.
*   **Simulated Backend Interaction**: The application includes a `RegisterService` that mimics interaction with a backend system (e.g., SMOS), including fetching and updating data, and simulating connection interruptions as per the requirements.

## 💾 Environment Setup

To compile and run this application, you will need a Java Development Kit (JDK) installed on your system. This application uses standard Java libraries (Swing for GUI), so no external dependencies beyond a JDK are required.

### Prerequisites

*   **Java Development Kit (JDK)**: Version 11 or newer is recommended.

### Installation Steps

1.  **Save the Code**: Save all the provided `.java` files into a directory structure that matches their package declarations. For instance, create a root directory (e.g., `ClassRegisterApp`), then `ClassRegisterApp/com/chatdev/register/model`, `ClassRegisterApp/com/chatdev/register/service`, and `ClassRegisterApp/com/chatdev/register/gui`.
    *   `main.java` goes directly into the `ClassRegisterApp` root.
    *   `student.java`, `attendancestatus.java`, `attendanceentry.java`, `classregisterdetails.java` go into `ClassRegisterApp/com/chatdev/register/model`.
    *   `registerservice.java` goes into `ClassRegisterApp/com/chatdev/register/service`.
    *   `registerdetailsapp.java` goes into `ClassRegisterApp/com/chatdev/register/gui`.

2.  **Compile the Code**: Open your terminal or command prompt, navigate to the `ClassRegisterApp` root directory, and compile the Java source files.

    ```bash
    javac com/chatdev/register/model/*.java com/chatdev/register/service/*.java com/chatdev/register/gui/*.java Main.java
    ```
    *If you encounter issues with this command due to package structure, you might need to compile from the root directory specifying the source path.*
    ```bash
    javac -d . com/chatdev/register/model/*.java com/chatdev/register/service/*.java com/chatdev/register/gui/*.java Main.java
    ```
    This will create `.class` files in the appropriate package directories.

## ▶️ How to Use/Play

1.  **Run the Application**: From the `ClassRegisterApp` root directory in your terminal:

    ```bash
    java Main
    ```

2.  **Initial Launch**:
    *   The application window titled "Class Register Details - Admin View" will appear.
    *   It will automatically load simulated details for the class register `CLASS_5B_MATH_2023` for the current date.
    *   A table will display a list of students with their attendance status, lateness, justification, and disciplinary notes.
    *   Upon successful loading, an informational message will pop up, stating that "Connection to SMOS server is now interrupted (as per use case postcondition)." This is a simulated behavior as specified in the use case.

3.  **Navigate Dates**:
    *   At the top of the window, you'll find a "Select Date" `JComboBox`.
    *   Use this dropdown to select different dates. The application is pre-populated with data for today and yesterday as examples.
    *   Selecting a new date will automatically refresh the attendance table with records for that date.

4.  **View Student Details**:
    *   Click on any row in the attendance table.
    *   The "Manage Student Records" panel at the bottom of the window will populate with the details of the selected student.
    *   This panel shows the student's name, ID, current attendance status, lateness, justification, and disciplinary notes.

5.  **Manage Student Records**:
    *   **Update Attendance Status/Lateness**:
        *   In the "Manage Student Records" panel, you can change the `Attendance Status` using the dropdown and toggle the `Late` checkbox.
        *   Click the "Update Attendance" button to save these changes. The table will reflect the updates immediately, and a confirmation message will appear after simulated persistence.
    *   **Manage Justification**:
        *   Edit the text in the `Justification` textarea.
        *   Click the "Save Justification" button to save the text. The table's justification column will update, and a confirmation message will appear.
    *   **Manage Disciplinary Notes**:
        *   Edit the text in the `Disciplinary Notes` textarea.
        *   Click the "Save Disciplinary Note" button to save the text. The table's disciplinary note column will update, and a confirmation message will appear.

6.  **Error Handling**:
    *   If there are no records for a selected date, the table will be empty, and a "No Data" message will be displayed.
    *   Any errors during data loading or saving will be shown via `JOptionPane` error messages.

The application simulates the core functionality of viewing and managing a class register's daily details, providing a clear demonstration of the "ViewDetailsSingleRegister" use case.
```