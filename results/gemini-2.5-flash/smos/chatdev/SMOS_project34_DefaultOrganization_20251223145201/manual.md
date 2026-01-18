```markdown
# Student Absence Justification Viewer

## Introduction

The Student Absence Justification Viewer is a desktop application designed for administrators within an educational system (simulated as "SMOS"). Its primary function is to allow administrators to easily view a list of a specific student's absences throughout the academic year, clearly distinguishing between those that have been justified and those that still require justification. This tool streamlines the process of managing student attendance records and their associated justifications.

## Main Functions

This application provides the following key functionality:

*   **View Student Absences**: Displays a comprehensive list of all recorded absences for a predefined student.
*   **Justification Status Visualization**: Each absence is visually marked:
    *   **Light Green**: Indicates the absence has been *justified*.
    *   **Light Red**: Indicates the absence is *to be justified*.
*   **Admin-Focused Display**: Presents important details for each absence, including the date, reason, and its current justification status, in an organized table format.
*   **Simulated Server Interaction**: Demonstrates connection to and disconnection from a simulated SMOS server, reflecting real-world backend interactions.
*   **Responsive User Interface**: Utilizes Java Swing for the graphical interface, ensuring responsiveness during data loading through asynchronous operations.

## Environment Dependencies

This project is built purely on standard Java Development Kit (JDK) libraries. No external third-party libraries or frameworks are required.

To compile and run this application, you will need:

*   **Java Development Kit (JDK)**: Version 8 or higher.

### Installing JDK

If you do not have Java JDK installed, please follow these steps:

1.  **Download JDK**: Visit the official Oracle website or adoptium.net (for OpenJDK) to download the appropriate JDK version for your operating system.
    *   For Oracle JDK: [https://www.oracle.com/java/technologies/downloads/](https://www.oracle.com/java/technologies/downloads/)
    *   For OpenJDK (Adoptium Temurin): [https://adoptium.net/temurin/releases/](https://adoptium.net/temurin/releases/)
2.  **Installation**: Run the installer and follow the on-screen instructions.
3.  **Set Environment Variables (if necessary)**:
    *   **Windows**:
        *   Search for "Environment Variables" in the Start Menu and select "Edit the system environment variables".
        *   Click "Environment Variables..."
        *   Under "System variables", find `Path` and click "Edit...".
        *   Add a new entry pointing to `C:\Program Files\Java\jdk-<version>\bin` (replace `<version>` with your JDK version).
        *   Create or edit `JAVA_HOME` variable to point to `C:\Program Files\Java\jdk-<version>`.
    *   **macOS/Linux**:
        *   Open your terminal.
        *   Edit your shell's profile file (e.g., `~/.bashrc`, `~/.zshrc`, or `~/.profile`).
        *   Add the following lines (adjust path if needed):
            ```bash
            export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-<version>.jdk/Contents/Home
            export PATH=$JAVA_HOME/bin:$PATH
            ```
        *   Save the file and run `source ~/.bashrc` (or your respective profile file) to apply changes.
4.  **Verify Installation**: Open a command prompt or terminal and type:
    ```bash
    java -version
    javac -version
    ```
    You should see output indicating the installed Java version.

## How to Use/Play It

Follow these steps to compile and run the Student Absence Justification Viewer application:

1.  **Save the Code**: Ensure all provided Java files ( `Absence.java`, `AbsenceService.java`, `AbsenceStatusCellRenderer.java`, `JustificationTableModel.java`, `JustificationViewerApp.java`) are saved in a directory structure that matches their package declarations (`com/chatdev/smos/model`, `com/chatdev/smos/service`, `com/chatdev/smos/ui`, `com/chatdev/smos/app`).

    For example, if your project root directory is `SMOS_Viewer`:
    ```
    SMOS_Viewer/
    ├── src/
    │   └── com/
    │       └── chatdev/
    │           └── smos/
    │               ├── app/
    │               │   └── JustificationViewerApp.java
    │               ├── model/
    │               │   └── Absence.java
    │               ├── service/
    │               │   └── AbsenceService.java
    │               └── ui/
    │                   ├── AbsenceStatusCellRenderer.java
    │                   └── JustificationTableModel.java
    ```

2.  **Navigate to Project Root**: Open a command prompt or terminal and navigate to the `SMOS_Viewer` directory (or wherever your `src` folder is located).

    ```bash
    cd /path/to/SMOS_Viewer
    ```

3.  **Compile the Java Files**: Use the `javac` command to compile all the Java source files. The `-d .` flag tells the compiler to place the compiled `.class` files in the current directory, maintaining the package structure.

    ```bash
    javac src/com/chatdev/smos/app/*.java src/com/chatdev/smos/model/*.java src/com/chatdev/smos/service/*.java src/com/chatdev/smos/ui/*.java
    ```

    Alternatively, to compile all files recursively from the `src` directory:

    ```bash
    javac -d . src/com/chatdev/smos/app/JustificationViewerApp.java src/com/chatdev/smos/model/Absence.java src/com/chatdev/smos/service/AbsenceService.java src/com/chatdev/smos/ui/AbsenceStatusCellRenderer.java src/com/chatdev/smos/ui/JustificationTableModel.java
    ```

    Or, move into the `src` directory first and compile:

    ```bash
    cd src
    javac com/chatdev/smos/app/JustificationViewerApp.java com/chatdev/smos/model/Absence.java com/chatdev/smos/service/AbsenceService.java com/chatdev/smos/ui/AbsenceStatusCellRenderer.java com/chatdev/smos/ui/JustificationTableModel.java
    ```
    If you compiled from the `src` directory, you'll need to navigate back to the root (`cd ..`) before running.

4.  **Run the Application**: Execute the main class (`JustificationViewerApp`) using the `java` command. Make sure you are in the directory *above* the `com` folder (your `SMOS_Viewer` directory).

    ```bash
    java com.chatdev.smos.app.JustificationViewerApp
    ```

### Expected Outcome

Upon running the application, a new Swing window titled "Student Absences Justification Viewer - S12345" will appear.

1.  **Loading Screen**: Initially, you will see a status message at the bottom indicating that the application is connecting to the SMOS server and loading absences. There might be a brief delay (simulating network latency).
2.  **Display Table**: After a short pause, the table will populate with a list of absences for student "S12345".
    *   Absences with `isJustified = true` will be displayed with a **light green background**.
    *   Absences with `isJustified = false` will be displayed with a **light red background**.
3.  **Status Message**: The status bar at the bottom will update to confirm "Absences loaded successfully...".
4.  **Server Disconnection**: In the console where you ran the command, you will also see a message indicating "SMOS server connection interrupted (simulated and explicitly disconnected).", fulfilling a postcondition of the use case.

You can then review the list of justifications presented in the table. Close the window to exit the application.
```