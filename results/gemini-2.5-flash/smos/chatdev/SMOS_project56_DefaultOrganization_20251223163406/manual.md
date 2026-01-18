```markdown
# Teacher Class Viewer Application

This application is designed to simulate a "Digital Log" functionality for a teacher within a school management system (SMOS). It allows a logged-in teacher to view a list of classes they teach, organized by academic year.

## 🤔 What is this?

The Teacher Class Viewer is a simple, standalone Java application that demonstrates the `ViewClassListTeacher` use case. It provides a graphical user interface (GUI) where a teacher (Professor Smith, in this simulation) can see which academic years they have classes in, select an academic year, and then view the specific classes assigned to them for that year.

## ✨ Main Functions

*   **Teacher Login Simulation**: The application starts by simulating a teacher being logged in (defaulting to "Professor Smith" with ID "T001").
*   **Display Academic Years**: Upon launch, the system automatically fetches and displays a list of all academic years in which the simulated teacher teaches at least one class.
*   **Academic Year Selection**: The teacher can select a specific academic year from a dropdown menu.
*   **View Classes**: Once an academic year is selected, the application displays a list of all classes taught by the teacher for that chosen academic year.
*   **Status Updates**: A status bar at the bottom provides feedback on current operations (e.g., "Fetching academic years...", "Classes loaded...").

## 🛠️ Environment Setup & Installation

This project is written in Java and requires a Java Development Kit (JDK) to compile and run.

1.  **Install Java Development Kit (JDK)**:
    *   Ensure you have JDK 8 or higher installed on your system. You can download it from Oracle's website or use OpenJDK distributions.
    *   To verify your Java installation, open a terminal or command prompt and run:
        ```bash
        java -version
        javac -version
        ```
        You should see output indicating your Java version (e.g., `openjdk version "17.0.8"`).

2.  **Save the Source Code**:
    *   Save each of the provided `.java` files into appropriate directories reflecting their package structure.
    *   Create a root directory for your project, for example, `TeacherApp`.
    *   Inside `TeacherApp`, create a `src` directory.
    *   Inside `src`, create `model`, `service`, and `gui` directories.
    *   Place `teacher.java`, `academicyear.java`, `schoolclass.java` into `src/model`.
    *   Place `smos_server.java` into `src/service`.
    *   Place `teacherclassviewerapp.java` into `src/gui`.

    Your directory structure should look like this:
    ```
    TeacherApp/
    └── src/
        ├── model/
        │   ├── AcademicYear.java
        │   ├── SchoolClass.java
        │   └── Teacher.java
        ├── service/
        │   └── SMOS_Server.java
        └── gui/
            └── TeacherClassViewerApp.java
    ```

3.  **Compile the Code**:
    *   Open your terminal or command prompt.
    *   Navigate to the `src` directory within your `TeacherApp` project folder.
        ```bash
        cd TeacherApp/src
        ```
    *   Compile all Java source files. The `-d ../bin` option specifies that compiled `.class` files should be placed in a `bin` directory one level up (relative to `src`).
        ```bash
        javac -d ../bin model/*.java service/*.java gui/*.java
        ```
    *   If compilation is successful, a new `bin` directory will be created inside `TeacherApp`, containing the compiled `.class` files, preserving the package structure.

## ▶️ How to Use/Play It

After successfully compiling the code:

1.  **Navigate to the Compiled Directory**:
    *   In your terminal, navigate to the `TeacherApp` directory (the parent of `src` and `bin`).
        ```bash
        cd TeacherApp
        ```

2.  **Run the Application**:
    *   Execute the `TeacherClassViewerApp` class. The `-classpath bin` part tells Java where to find the compiled classes.
        ```bash
        java -classpath bin gui.TeacherClassViewerApp
        ```

3.  **Interact with the GUI**:
    *   A window titled "Teacher Class Viewer - Digital Log" will appear.
    *   **Header**: It will display "Logged in as: Professor Smith (ID: T001)", indicating the simulated user.
    *   **Academic Year Selection**: Below the header, you will see a dropdown menu labeled "Select Academic Year:". This dropdown will be populated with academic years in which Professor Smith teaches (e.g., "Academic Year 2022-2023", "Academic Year 2023-2024", "Academic Year 2024-2025").
    *   **Class List**: Initially, the class list area will show classes for the currently selected year (typically the first one in the list).
    *   **Change Year**: Click on the dropdown menu and select a different academic year.
    *   **View Updated Classes**: The "Classes Taught" list will automatically update to show all classes taught by Professor Smith in the newly selected academic year.
    *   **Status Bar**: Observe the status bar at the bottom for messages indicating the application's state and data loading activities.

4.  **Exit**: Close the application window to terminate the program. The simulated "connection to the interrupted SMOS server" for each data request automatically handles the closing of conceptual connections after each data retrieval, aligning with the postcondition.
```