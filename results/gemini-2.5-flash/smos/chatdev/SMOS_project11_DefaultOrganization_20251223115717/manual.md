```markdown
# Teacher Teaching Management System

## Introduction

This application, developed in Java using the Swing framework, is designed to assist school administrators in managing the teaching assignments for individual teachers. It provides a user-friendly interface to assign new teachings (courses/subjects) to a specific teacher or remove existing ones, organized by academic year and class. This system simulates a scenario where an administrator is viewing a teacher's details and wishes to modify their teaching responsibilities.

## Main Functions

The core functionality of the "Teacher Teaching Management System" is centered around the `Assign/RemoveTeachersToATeacher` use case. As an administrator, you can perform the following actions:

1.  **View Teacher Details**: Upon launching the application, it will display the name of the teacher whose teachings are currently being managed. (In this simulated environment, a mock teacher "John Doe" is pre-selected.)
2.  **Select Academic Year**: Choose a specific academic year from a dropdown list to view and manage teachings relevant to that period.
3.  **Select Class**: After selecting an academic year, a list of classes available for that year will be displayed. You can then select a specific class to see the available teachings associated with it.
4.  **Manage Teachings**:
    *   **View Available Teachings**: See a list of teachings that are offered for the selected class and *not yet* assigned to the teacher for the selected academic year.
    *   **View Assigned Teachings**: See a list of teachings that *are already* assigned to the teacher for the selected academic year and class.
    *   **Assign Teachings**: Select one or more teachings from the "Available Teachings" list and click the "Assign Selected >>" button to move them to the "Assigned Teachings" list.
    *   **Remove Teachings**: Select one or more teachings from the "Assigned Teachings" list and click the "<< Remove Selected" button to remove them from the teacher's assignments.
5.  **Confirmation and Notifications**: The system provides pop-up messages to confirm successful assignments/removals or to inform about errors or missing selections.

## Environment Setup

To run this application, you need a Java Development Kit (JDK) installed on your system.

### 1. Install Java Development Kit (JDK)

*   **Check for Existing JDK**: Open your terminal or command prompt and type:
    ```bash
    java -version
    javac -version
    ```
    If you see version information (e.g., `openjdk version "17.0.x"` or `java version "1.8.0_x"`), you likely have Java installed. Ensure it's a JDK (indicated by `javac` command working), not just a JRE.
*   **Download and Install JDK**: If Java is not installed or `javac` is not found, you can download the latest JDK from Oracle's website or an open-source distribution like OpenJDK (e.g., Eclipse Adoptium, Amazon Corretto). Follow the installation instructions for your operating system.
*   **Set up JAVA_HOME (Optional but Recommended)**: Configure the `JAVA_HOME` environment variable to point to your JDK installation directory. This is often done automatically by installers but can be useful for various tools.

### 2. Integrated Development Environment (IDE) - Recommended

While not strictly required, using an IDE like IntelliJ IDEA or Eclipse will simplify compiling and running the Java code.

*   **IntelliJ IDEA**: Download from [JetBrains website](https://www.jetbrains.com/idea/download/).
*   **Eclipse**: Download from [Eclipse Foundation website](https://www.eclipse.org/downloads/).

## How to Use/Play

Follow these steps to compile and run the Teacher Teaching Management System application.

### 1. Save the Code Files

Create a new directory (e.g., `TeacherManagementApp`) on your computer. Save all the provided `.java` files (`TeacherManagementApp.java`, `Teacher.java`, `AcademicYear.java`, `ClassEntity.java`, `Teaching.java`, `TeacherService.java`, `TeachingAssignmentPanel.java`) into this new directory.

Make sure that `ClassEntity.java` is named exactly that to avoid conflicts with Java's built-in `Class` type.

### 2. Compile the Java Code

#### Using a Command Prompt/Terminal:

1.  Open your terminal or command prompt.
2.  Navigate to the directory where you saved your `.java` files:
    ```bash
    cd path/to/TeacherManagementApp
    ```
    (Replace `path/to/TeacherManagementApp` with the actual path to your directory.)
3.  Compile all the Java source files:
    ```bash
    javac *.java
    ```
    This command compiles all `.java` files in the current directory, creating corresponding `.class` files. If there are no errors, you will return to the command prompt without any output.

#### Using an IDE (e.g., IntelliJ IDEA, Eclipse):

1.  Open your IDE.
2.  Create a new Java project.
3.  Import or add all the `.java` files to the project's source folder.
4.  The IDE will typically compile the code automatically. If not, look for a "Build Project" or "Compile" option.

### 3. Run the Application

#### Using a Command Prompt/Terminal:

1.  After successful compilation, in the same directory, run the main application class:
    ```bash
    java TeacherManagementApp
    ```
2.  A new Swing window titled "Teacher Teaching Management" will appear.

#### Using an IDE:

1.  Locate `TeacherManagementApp.java` in your project.
2.  Right-click on `TeacherManagementApp.java` and select "Run 'TeacherManagementApp.main()'" or similar option.
3.  The application window will open.

### 4. Interacting with the Application (GUI Walkthrough)

Once the application window appears, you will see the following layout:

*   **Top Bar**:
    *   **"Managing Teachings for: John Doe (ID: 1)"**: This label indicates the teacher whose assignments you are currently managing.
    *   **"Academic Year:" Dropdown**: This is where you select the academic year.
        *   **Action**: Click the dropdown and select an academic year (e.g., "2023-2024"). This simulates **User Event 2: Select the academic year** and triggers **System Event 3: View the list of classes available for the selected year**.
    *   **"Class:" Dropdown**: This dropdown will list classes available for the selected academic year. It will be disabled until an academic year is chosen.
        *   **Action**: After selecting an academic year, click this dropdown and select a class (e.g., "Grade 1A"). This simulates **User Event 4: Select the desired class** and triggers **System Event 5: Displays the list of teachings associated with that class**.

*   **Center Panel**:
    *   **Left List ("Available Teachings")**: Displays teachings associated with the *selected class* that are *not currently assigned* to the teacher for the *selected academic year*.
    *   **Middle Buttons**:
        *   **"Assign Selected >>" Button**:
            *   **Action**: Select one or more teachings from the "Available Teachings" list (you can hold `Ctrl`/`Cmd` to select multiple) and click this button. This simulates **User Event 6: Select the teachings to associate** and triggers **System Event 7: Assigns the teaching teachings**.
            *   **Result**: The selected teachings will move from the "Available Teachings" list to the "Assigned Teachings" list. A success message will appear.
        *   **"<< Remove Selected" Button**:
            *   **Action**: Select one or more teachings from the "Assigned Teachings" list and click this button. This simulates **User Event 6: Select the teachings to remove** and triggers **System Event 7: Removes the teaching teachings**.
            *   **Result**: The selected teachings will move from the "Assigned Teachings" list back to the "Available Teachings" list. A success message will appear.
    *   **Right List ("Assigned Teachings")**: Displays teachings currently assigned to the teacher for the *selected class* and *selected academic year*.

**Example Flow**:

1.  Launch the application.
2.  Select "2023-2024" from the Academic Year dropdown.
3.  Select "Grade 1A" from the Class dropdown.
4.  Observe the "Available Teachings" list (e.g., Mathematics (G1A), Reading (G1A), Art (G1A), Science (G1A)) and the "Assigned Teachings" list (initially empty).
5.  Select "Mathematics (G1A)" and "Reading (G1A)" from the "Available Teachings" list.
6.  Click "Assign Selected >>". The items will move to the "Assigned Teachings" list, and a "Teachings assigned successfully!" message will pop up.
7.  Now, select "Reading (G1A)" from the "Assigned Teachings" list.
8.  Click "<< Remove Selected". "Reading (G1A)" will move back to the "Available Teachings" list, and a "Teachings removed successfully!" message will appear.

This interactive process allows you to fully test the assignment and removal of teachings as described in the use case. All data simulated by `TeacherService` is in-memory and will reset each time the application is closed and reopened.
```