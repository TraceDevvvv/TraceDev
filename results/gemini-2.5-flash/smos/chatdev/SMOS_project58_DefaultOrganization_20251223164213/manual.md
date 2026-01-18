# Teacher Report Card System

An application for teachers to view student report cards online.

## 🤔 What is this?

The "Teacher Report Card System" is a Java Swing-based desktop application designed for teachers to easily access and display student report cards. It simulates an online system where a logged-in teacher can navigate through academic years, classes, and students to view specific academic reports for a given quadrimestre. The application demonstrates the event flow and data presentation as outlined in the `DisplayOfAPageTeacher` use case.

## 🚀 Main Functions

This application provides a user-friendly interface for teachers to perform the following actions:

1.  **View Academic Years**: Upon launching, the system displays a list of academic years in which the logged-in teacher has taught (simulated as "Professor Rossi" in the backend).
2.  **Select Academic Year**: The teacher can choose an academic year from a dropdown menu.
3.  **View Associated Classes**: After selecting an academic year, the system automatically updates to show only the classes associated with that year and taught by the teacher.
4.  **Select a Class**: The teacher can then select a specific class from the dropdown menu.
5.  **Display Class Students**: Once a class is selected, the list of students enrolled in that class is displayed in another dropdown.
6.  **Select Pupil and Quadrimestre**: The teacher can pick a specific student and a quadrimestre (e.g., "1st Quadrimestre", "2nd Quadrimestre").
7.  **Display Report Card**: By clicking the "Display Report Card" button, the system fetches and presents the report card for the chosen student and quadrimestre in a dedicated text area. The report includes grades for various subjects and general comments.

## 🛠️ Environment Dependencies

To run this application, you will need:

*   **Java Development Kit (JDK)**: Version 8 or higher is required. The application uses standard Java features and Swing for the graphical user interface, which are included with the JDK.
    *   You can download the latest JDK from the official Oracle website or use an open-source distribution like OpenJDK.

No external libraries or complex configurations are needed as all necessary classes are provided in the source code.

## ▶️ How to Use/Play It

Follow these steps to compile and run the Teacher Report Card System:

1.  **Save the Code**:
    *   Save all the provided Java code snippets into individual `.java` files in the same directory.
    *   Ensure the filenames match the class names (e.g., `academicyear.java`, `classroom.java`, `student.java`, `reportcard.java`, `smosserversimulator.java`, `teacher.java`, `teacherreportcardapp.java`).

2.  **Compile the Code**:
    *   Open a terminal or command prompt.
    *   Navigate to the directory where you saved the Java files.
    *   Compile all `.java` files using the Java compiler:
        ```bash
        javac *.java
        ```
    *   If compilation is successful, you will see `.class` files generated for each Java source file.

3.  **Run the Application**:
    *   From the same directory in your terminal, execute the main application class:
        ```bash
        java TeacherReportCardApp
        ```

4.  **Interact with the Application**:
    *   A GUI window titled "Teacher Report Card System" will appear.
    *   **Step 1: Select Academic Year**: The "Select Academic Year" dropdown will be populated with available academic years (e.g., "2022-2023", "2023-2024") that "Professor Rossi" teaches in. Select one.
    *   **Step 2: Select Class**: The "Select Class" dropdown will automatically update to show classes taught by "Professor Rossi" in the chosen academic year. Select a class (e.g., "5A").
    *   **Step 3: Select Student**: The "Select Student" dropdown will then display the students enrolled in the selected class. Choose a student.
    *   **Step 4: Select Quadrimestre**: Select a quadrimestre from the "Select Quadrimestre" dropdown (e.g., "1st Quadrimestre", "2nd Quadrimestre").
    *   **Step 5: Display Report Card**: Click the "Display Report Card" button.
    *   The "Student Report Card Details" area on the right will then display the comprehensive report card for the selected student and quadrimestre, or a message if no report card is found for that selection.

    *Note:* The application uses a `SMOSServerSimulator` to provide mock data. All data displayed belongs to a predefined teacher named "Professor Rossi" (ID: T001) for demonstration purposes.