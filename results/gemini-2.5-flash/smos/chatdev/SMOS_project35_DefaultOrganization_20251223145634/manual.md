```
# Student Note Viewer for Administrators

This document serves as a user manual for the Student Note Viewer application, designed to help administrators easily access and review notes concerning specific students.

## 🤔 What is this?

The Student Note Viewer is a desktop application developed in Java Swing, intended for school administrators. Its primary function is to provide a user-friendly interface to view all recorded notes for a selected student during the school year. This streamlines the process of accessing student-specific information, contributing to better student management and oversight.

**Main Functions:**

*   **Administrator Login Simulation:** The application begins with a simulated administrator login to ensure access control.
*   **Student Selection:** Administrators can select a student from a dropdown list populated with available student records.
*   **Note Display:** Upon selecting a student, the application displays a comprehensive list of all notes associated with that student, showcasing the date and content of each note.
*   **Data Simulation:** For demonstration purposes, the application includes a `DataStore` with pre-populated sample student and note data.

## 📖 How to Install Environment Dependencies

This application is built entirely using standard Java libraries, primarily Java Swing for the graphical user interface. Therefore, the only dependency required is a Java Development Kit (JDK).

1.  **Install Java Development Kit (JDK):**
    *   You need JDK 8 or a newer version installed on your system.
    *   **For Windows/macOS:**
        *   Download the latest JDK from Oracle's official website or Adoptium (Eclipse Temurin) for an open-source alternative.
        *   Follow the installation wizard instructions.
    *   **For Linux (e.g., Ubuntu/Debian):**
        *   Open your terminal and run:
            ```bash
            sudo apt update
            sudo apt install default-jdk # Installs the default JDK
            # Or for a specific version, e.g., OpenJDK 17:
            # sudo apt install openjdk-17-jdk
            ```
    *   **Verify Installation:**
        *   After installation, open a command prompt or terminal and type:
            ```bash
            java -version
            javac -version
            ```
        *   You should see output similar to `openjdk version "17.0.x" ...` or `java version "1.8.0_x" ...`. If these commands are not found, you may need to configure your `PATH` environment variable to include the JDK's `bin` directory.

## 🚀 How to Use/Play

Follow these steps to compile and run the Student Note Viewer application:

1.  **Save the Source Code:**
    *   Create a new directory (e.g., `StudentNoteViewer`).
    *   Save all the provided `.java` files (`Student.java`, `Note.java`, `DataStore.java`, `NoteViewerApp.java`) into this directory. Ensure each file name matches its public class name exactly (e.g., `Student.java` contains the `Student` class).

2.  **Open a Terminal or Command Prompt:**
    *   Navigate to the directory where you saved your Java files using the `cd` command.
        ```bash
        cd path/to/your/StudentNoteViewer
        ```

3.  **Compile the Java Files:**
    *   Compile all `.java` files using the Java compiler (`javac`).
        ```bash
        javac *.java
        ```
    *   If there are no compilation errors, this command will create corresponding `.class` files in the same directory. If you encounter errors, double-check that all `.java` files are present and match their class names, and that your JDK is correctly installed.

4.  **Run the Application:**
    *   Execute the main application class `NoteViewerApp`.
        ```bash
        java NoteViewerApp
        ```

5.  **Application Interaction:**

    *   **Administrator Login:**
        *   Upon running, a dialog box will appear asking for an "Administrator Password".
        *   For this demonstration, any non-empty input will be accepted as a valid password. Type anything (e.g., `admin123`) and click "OK". If you click "Cancel" or leave it empty, the application will exit.

    *   **Main Application Window:**
        *   After successful login, the "Administrator - Student Note Viewer" window will appear.
        *   **Select Student:** At the top, you'll see a dropdown menu labeled "Select Student:". Click on it to see a list of sample students (e.g., "Alice Smith", "Bob Johnson").
        *   **View Notes:**
            *   Select a student from the dropdown. The notes for that student will automatically populate in the large text area below.
            *   Alternatively, you can select a student and then click the "View Notes" button.
            *   The text area will display notes formatted with their dates and content.
        *   **No Notes Found:** If a student has no notes in the `DataStore`, a message indicating "No notes found for [Student Name]..." will be displayed.

    *   **Exiting the Application:**
        *   You can close the application by clicking the 'X' button on the window title bar. This simulates the administrator interrupting the connection to the system as described in the postconditions.

This interactive manual should guide any administrator through the use of the Student Note Viewer application efficiently.
```