```markdown
# Administrator Report Card Viewer

## Introduction

The "Administrator Report Card Viewer" is a Java-based application designed to assist school administrators in efficiently viewing student report cards. Following a multi-step guided process, administrators can select an academic year, a specific pupil class, a student, and up to four months to generate and display a detailed report card. The application features a graphical user interface (GUI) built with Swing, making it user-friendly and intuitive. Data is provided through a simulated in-memory data store, allowing for easy demonstration without external database dependencies.

## Features

*   **Academic Year Selection:** Administrators can choose from a list of available academic years.
*   **Class Selection:** After selecting a year, a list of classes for that year is displayed for selection.
*   **Student and Month Selection:** Once a class is chosen, a list of students in that class appears, along with checkboxes to select exactly four months for the report.
*   **Detailed Report Display:** The system generates and displays a comprehensive report card for the selected student and months, including course names and grades.
*   **Interactive Workflow:** The application guides the user through the report generation process using a step-by-step panel navigation.
*   **Simulated Data:** All academic data (years, classes, students, grades) are provided by an in-memory mock data store, allowing the application to be fully runnable without external configuration.
*   **Simulated Error Handling:** Includes a mechanism to simulate a "SMOS server interrupted" error, demonstrating graceful error recovery and user feedback.

## Environment Setup

### Prerequisites

To compile and run this Java application, you need to have the following installed on your system:

*   **Java Development Kit (JDK)**: Version 8 or higher. You can download it from the official Oracle website or adoptium.net (for OpenJDK).

### Installation

#### Using an Integrated Development Environment (IDE) - Recommended

1.  **Download and Install an IDE:**
    *   **IntelliJ IDEA**: Community Edition is free and excellent for Java development.
    *   **Eclipse IDE for Java Developers**: Another popular free option.
    *   **Apache NetBeans**: Also a good choice for Java Swing applications.
2.  **Create a New Java Project:**
    *   Open your chosen IDE.
    *   Create a new "Java Project" or "Maven Project" (if you want to manage dependencies, though none are strictly external for this project beyond standard Java libs).
    *   Ensure the project's JDK is set to Java 8 or higher.
3.  **Organize Source Files:**
    *   Recreate the package structure: `model`, `data`, and `gui`.
    *   Place `Student.java`, `CourseGrade.java`, and `ReportCard.java` into the `src/model` directory.
    *   Place `DataStore.java` into the `src/data` directory.
    *   Place `AcademicYearPanel.java`, `ClassSelectionPanel.java`, `StudentSelectionPanel.java`, `ReportDisplayPanel.java`, and `ReportRequest.java` into the `src/gui` directory.
    *   Place `ReportCardApp.java` directly into the `src` directory (or default package).
4.  **Build and Run:**
    *   The IDE should automatically compile the `.java` files.
    *   Locate `ReportCardApp.java` (which contains the `main` method).
    *   Right-click on `ReportCardApp.java` and select "Run 'ReportCardApp.main()'" or a similar option.

#### From Command Line

1.  **Save Files:**
    *   Create a root directory for your project (e.g., `ReportCardViewer`).
    *   Inside this directory, create subdirectories `model`, `data`, and `gui`.
    *   Place the `.java` files into their respective subdirectories as described above. `ReportCardApp.java` should be directly in the `ReportCardViewer` directory.
    *   Example structure:
        ```
        ReportCardViewer/
        ├── model/
        │   ├── Student.java
        │   ├── CourseGrade.java
        │   └── ReportCard.java
        ├── data/
        │   └── DataStore.java
        ├── gui/
        │   ├── AcademicYearPanel.java
        │   ├── ClassSelectionPanel.java
        │   ├── ReportDisplayPanel.java
        │   ├── ReportRequest.java
        │   └── StudentSelectionPanel.java
        └── ReportCardApp.java
        ```
2.  **Compile:**
    *   Open a terminal or command prompt.
    *   Navigate to the `ReportCardViewer` directory.
    *   Compile all Java files using the Java compiler (`javac`):
        ```bash
        javac ReportCardApp.java model/*.java data/*.java gui/*.java
        ```
        *(Note: You might need to adjust the classpath if the default compilation doesn't find all classes. A simpler approach is to compile from the root directory ensuring packages are correctly specified, or compile one by one ensuring dependencies are met, but `javac` is usually smart enough.)*
        Alternatively, to compile properly treating packages:
        ```bash
        javac -d . ReportCardApp.java model/*.java data/*.java gui/*.java
        ```
        This command compiles all `.java` files and places the `.class` files into their respective package directories starting from the current directory.
3.  **Run:**
    *   From the `ReportCardViewer` directory, run the main application:
        ```bash
        java ReportCardApp
        ```

## How to Use

Follow these steps to generate and view a student's report card:

1.  **Start the Application**
    *   Execute `ReportCardApp`. A window titled "Administrator Report Card Viewer" will appear.

2.  **Select Academic Year**
    *   The first panel displayed will be "Select Academic Year".
    *   From the "Academic Year" dropdown box, choose the desired academic year (e.g., "2023-2024").
    *   Click the "Next" button.

3.  **Choose Pupil Class**
    *   The application will transition to the "Choose Pupil Class" panel.
    *   A list of classes available for the selected academic year will be displayed.
    *   Click on a class from the list to select it (e.g., "Grade 11A").
    *   Click the "View Students" button. This button will only become active once a class is selected.

4.  **Select Student and Months**
    *   You will now be on the "Select Student and Months" panel.
    *   **Select Student:** On the left, a list of students belonging to the chosen class will be shown. Click on the student whose report card you wish to view (e.g., "Jack Red").
    *   **Select Four Months:** On the right, a list of all months will be displayed with checkboxes. You **must select exactly four** months for the report period.
    *   Once a student is selected and exactly four months are checked, the "View Report" button will become active.
    *   Click the "View Report" button.

5.  **View the Report Card**
    *   The final panel, "Student Report Card," will display the generated report.
    *   It will show the student's name and ID, the academic year, the four selected months, and a detailed breakdown of course grades for each of those months.

6.  **Start a New Report**
    *   After viewing a report, you can click the "View New Report" button at the bottom of the "Student Report Card" panel. This will reset the application and take you back to the "Select Academic Year" panel to begin the process anew.

## Simulated Error Handling

The application includes a simulated server connection error for demonstration purposes.

*   In the `ReportCardApp.java` file, you can uncomment the line `DataStore.setSimulateError(true);` (around line 52 inside the `ReportCardApp()` constructor).
*   If this line is uncommented, there's a chance that when you click "View Report" in step 4, instead of displaying the report, a "Server Connection Error" dialog box will appear.
*   This dialog will inform you that the "Connection to SMOS server interrupted."
*   After acknowledging the error, the application will automatically return to the "Select Academic Year" panel, allowing you to try generating the report again. This simulates a real-world scenario where a network or server issue might temporarily prevent data retrieval.

```