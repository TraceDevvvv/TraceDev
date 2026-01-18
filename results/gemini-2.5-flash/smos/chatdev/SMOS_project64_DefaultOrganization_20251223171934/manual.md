```
# Digital Register Viewer - User Manual

## Introduction

The Digital Register Viewer is a simple, intuitive application designed for school "Direction" users (e.g., administrators, management) to easily access and view academic registers for specific academic years. This tool streamlines the process of reviewing digital records, providing a clear overview of registers associated with different classes and subjects.

## Main Functions

The application provides the following core functionalities:

*   **Academic Year Selection**: Users can choose a specific academic year from a dropdown list to filter the registers. The system automatically populates this list with available years from the archive.
*   **View Registers**: Upon selecting an academic year and clicking the "View Registers" button, the system searches its archive for all digital registers pertinent to that year.
*   **Display Registers List**: The search results are presented in a clear, tabular format. Each row in the table represents an academic register and includes details such as:
    *   **Register ID**: A unique identifier for the register.
    *   **Academic Year**: The year the register belongs to.
    *   **Class Name**: The specific class (e.g., "10A", "11B") to which the register is associated.
    *   **Subject**: The subject of the register (e.g., "Mathematics", "History").
    *   **Teacher**: The name of the teacher associated with the register.
*   **Status and Error Reporting**: A dedicated status area at the bottom of the window provides real-time feedback, indicating when a search is in progress, if registers were found, or if an error occurred (e.g., a simulated connection interruption to the SMOS server).

## Environment Setup

To run the Digital Register Viewer, you need to have a Java Development Kit (JDK) installed on your system.

### Requirements

*   **JDK (Java Development Kit)**: Version 8 or higher.

### Installation Steps

1.  **Check for existing JDK**:
    Open a terminal or command prompt and type:
    ```bash
    java -version
    ```
    If Java is installed, you will see version information. Ensure it's JDK 8 or later.

2.  **Download and Install JDK**:
    If JDK 8 or higher is not installed, or you need to update:
    *   Visit the official [Oracle Java website](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.java.net/install/index.html).
    *   Download the appropriate JDK installer for your operating system.
    *   Follow the installation instructions provided by the installer.

3.  **Set up Environment Variables (if necessary)**:
    *   **`JAVA_HOME`**: It's good practice to set an environment variable named `JAVA_HOME` pointing to your JDK installation directory (e.g., `C:\Program Files\Java\jdk-17`).
    *   **`PATH`**: Add the `bin` directory of your JDK to your system's `PATH` environment variable (e.g., `%JAVA_HOME%\bin` on Windows or `$JAVA_HOME/bin` on Linux/macOS). This allows you to run `java` and `javac` commands from any directory.

## How to Use the Application

This section guides you through compiling and running the Digital Register Viewer application.

### 1. Save the Source Files

Ensure all provided Java source files (`MainApplication.java`, `AcademicRegister.java`, `SMOSConnectionException.java`, `RegisterService.java`, `DigitalRegisterViewer.java`) are saved in the *same directory* on your computer.

### 2. Compile the Application

1.  **Open a Terminal/Command Prompt**: Navigate to the directory where you saved your Java files using the `cd` command.
    ```bash
    cd /path/to/your/java/files
    ```
    (Replace `/path/to/your/java/files` with the actual path to your directory.)

2.  **Compile the Java files**: Use the Java compiler (`javac`) to compile all `.java` files.
    ```bash
    javac *.java
    ```
    If there are no errors, this command will generate corresponding `.class` files for each `.java` file in the same directory.

### 3. Run the Application

Once compiled, you can run the application using the `java` command.

1.  **Execute the Main Application**:
    ```bash
    java MainApplication
    ```

    This will launch the "Digital Register Viewer - Direction Access" graphical user interface.

### 4. Interacting with the Viewer

1.  **Simulated Login**: The application automatically simulates a "Direction" user login upon startup (as per the use case precondition). You will see a console message `User 'Direction' successfully logged in.`
2.  **Select Academic Year**: In the top left corner, you will see a dropdown box labeled "Select Academic Year:". Click on it to choose an academic year (e.g., 2022, 2023, 2024). By default, the latest available year is pre-selected.
3.  **View Registers**: Click the "View Registers" button next to the dropdown.
4.  **Observe Results**:
    *   The table in the center of the window will populate with the digital registers corresponding to the selected academic year.
    *   The "Status/Messages" area at the bottom will provide feedback, like "Searching for registers for academic year XXXX..." and then "Successfully displayed X registers for academic year XXXX." or "No digital registers found...".
    *   **Simulated Connection Interruptions**: Occasionally (approximately 15% chance), clicking "View Registers" might result in a "Connection to the SMOS server interrupted" error message in the status area. This is a simulated error demonstrating how the system handles such edge cases, as specified in the requirements. The table will be cleared in this scenario.

You can repeat steps 2-4 to view registers for different academic years. To close the application, simply close the main window.
```