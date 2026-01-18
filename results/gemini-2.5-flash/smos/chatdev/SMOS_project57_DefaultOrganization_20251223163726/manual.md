```markdown
# Professor Class Register Viewer

A Java Swing application for professors to view detailed class registers.

## 🤔 What is this?

The "Professor Class Register Viewer" is an application designed to assist university professors in managing class attendance and student records. It provides a user-friendly interface to display a list of courses taught by the professor and allows them to access detailed register information for each class, including attendance, disciplinary notes, and justifications.

## ✨ Main Features

*   **Class List Display**: Presents a comprehensive list of all classes currently taught by the logged-in professor.
*   **Detailed Register View**: Upon selecting a class, the application opens a new window displaying granular details for that class's register, including:
    *   **Date**: The date of the register entry.
    *   **Absence Status**: Indicates whether a student was absent.
    *   **Disciplinary Notes**: Records any disciplinary actions or observations.
    *   **Delay Status**: Notes if a student was late for class.
    *   **Justification**: Provides explanations for absences or delays.
*   **Simulated Data Service**: The application simulates a connection to a backend academic management system (referred to as SMOS server in the use case) for retrieving class and register data.
*   **User-Friendly Interface**: Built with Java Swing, offering a standard graphical user interface for easy navigation and interaction.

## 🛠️ How to Install Environment Dependencies

This application requires a Java Runtime Environment (JRE) or Java Development Kit (JDK) to be installed on your system.

### 1. Java Runtime Environment (JRE)

*   **Requirement**: `java_runtime_environment>=8`
*   **Check Installation**:
    To check if Java is installed and determine its version, open a terminal or command prompt and type:
    ```bash
    java -version
    ```
    You should see output similar to this:
    ```
    openjdk version "11.0.10" 2021-01-19
    OpenJDK Runtime Environment (build 11.0.10+9)
    OpenJDK 64-Bit Server VM (build 11.0.10+9, mixed mode)
    ```
    Ensure the version number is 8 or higher.
*   **Installation**:
    If Java is not installed or if your version is older than Java 8, you will need to install a compatible JRE or JDK.
    You can download the latest version of OpenJDK or Oracle JDK from their official websites. For example:
    *   [Oracle JDK Downloads](https://www.oracle.com/java/technologies/downloads/)
    *   [OpenJDK Downloads](https://adoptium.net/temurin/releases/) (Recommended for open-source alternatives)
    Follow the installation instructions provided on their respective sites for your operating system.

## 🚀 How to Use/Play It

Once you have the necessary Java environment set up, follow these steps to compile and run the application:

### 1. Save the Code

Save all the provided Java code files (e.g., `RegisterViewApp.java`, `ClassInfo.java`, `StudentRegisterEntry.java`, `DataService.java`, `ClassRegisterTableModel.java`, `ClassListTableModel.java`, `ClassRegisterDetailDialog.java`, `RegisterViewFrame.java`) into a single directory on your computer.

### 2. Compile the Application

1.  Open a terminal or command prompt.
2.  Navigate to the directory where you saved your Java files using the `cd` command. For example:
    ```bash
    cd path/to/your/java/files
    ```
3.  Compile all the Java source files using the Java compiler:
    ```bash
    javac *.java
    ```
    If successful, this command will generate `.class` files for each `.java` file in the directory. If there are compilation errors, ensure all files were saved correctly and reflect the provided code precisely.

### 3. Run the Application

1.  After successful compilation, run the main application using the `java` command, specifying the main class (`RegisterViewApp`):
    ```bash
    java RegisterViewApp
    ```

### 4. Interact with the GUI

Once the application starts, you will see the "Professor Class Register Viewer" main window:

1.  **View Classes**: The main window will display a table listing all the classes the professor teaches, including "Class ID", "Class Name", "Subject", and "Semester".
2.  **Select a Class**: Click on any row in the `Classes You Teach` table to select a class. The "View Register" button at the bottom right corner will become enabled once a class is selected.
3.  **View Register Details**: Click the **"View Register"** button.
    *   A new modal dialog window titled "Class Register for [Class Name]" will appear. This dialog displays the detailed register information for the selected class, showing columns for "Date", "Student Name", "Absence", "Disciplinary Notes", "Delay", and "Justification".
    *   In your terminal/command prompt, you will see a message: `--- SMOS server connection simulated as interrupted/closed. ---`, indicating that the simulated backend connection has been "interrupted" after data retrieval, fulfilling a postcondition of the use case.
4.  **Close Register Dialog**: Click the **"Close"** button within the detailed register dialog to return to the main class list.
5.  **Exit Application**: Close the main "Professor Class Register Viewer" window (e.g., by clicking the 'X' button) to exit the application.

Enjoy using the Professor Class Register Viewer!
```