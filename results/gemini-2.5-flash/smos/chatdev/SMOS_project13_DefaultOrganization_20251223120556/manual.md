```markdown
# Class Viewer Application User Manual

## Introduction

The Class Viewer Application is a simple Java Swing-based tool designed for administrators to view lists of academic classes within a school or university system. It simulates the process of logging into a system, selecting an academic year, and retrieving the corresponding class schedules from an archive, mimicking interaction with a backend "SMOS server".

**Key Features:**

*   **Administrator Login:** Secure access control ensures only authorized administrators can view class information.
*   **Academic Year Selection:** Easily select specific academic years to filter and view relevant classes.
*   **Class List Display:** Presents class details (ID, Name, Academic Year, Instructor) in a clear, tabular format.
*   **Simulated Data Service:** Mimics a backend data source, including the ability to simulate connection interruptions to demonstrate error handling.

## System Requirements & Environment Setup

This application is developed in Java and relies solely on standard Java SE libraries (Swing, AWT, java.util). No external third-party dependencies are required.

*   **Java Development Kit (JDK):** Version 8 or higher. The JDK includes the Java Runtime Environment (JRE) necessary for running Java applications, as well as the tools to compile Java source code.

**How to Install Java JDK:**

1.  **Download:** Visit the [Oracle Java SE Downloads](https://www.oracle.com/java/technologies/downloads/) page or an OpenJDK distribution like [Adoptium Temurin](https://adoptium.net/temurin/releases/).
2.  **Installation:** Follow the instructions for your operating system (Windows, macOS, Linux).
3.  **Verify Installation:** Open your command line or terminal and run the following commands:
    ```bash
    java -version
    javac -version
    ```
    You should see output indicating the installed Java version (e.g., `openjdk version "17.0.2"`). If these commands are not recognized, ensure your system's `PATH` environment variable includes the `bin` directory of your Java installation.

## Installation & Setup

1.  **Obtain Source Code:** Ensure you have all the Java source files (`.java`) in a single directory. The provided files are:
    *   `ClassViewerApp.java`
    *   `User.java`
    *   `AuthService.java`
    *   `AcademicClass.java`
    *   `ClassDataService.java`
    *   `LoginDialog.java`
    *   `ClassManagementFrame.java`
    *   `ClassManagementPanel.java`

2.  **Compile the Application:**
    *   Open your command line or terminal.
    *   Navigate to the directory where you saved the `.java` files.
    *   Compile all source files using the Java compiler (`javac`):
        ```bash
        javac *.java
        ```
    *   This command will compile all Java files into `.class` bytecode files.

3.  **Run the Application:**
    *   After successful compilation, run the main application class using the `java` command:
        ```bash
        java ClassViewerApp
        ```

## How to Use the Application

This guide walks you through the typical workflow of using the Class Viewer application as an administrator.

### 1. Launching the Application

*   Once you execute `java ClassViewerApp` in your terminal, the application will start by attempting to set the system's look and feel for a native appearance.
*   Immediately, a "Administrator Login" dialog box will appear.

### 2. Administrator Login

*   **Precondition:** The system requires the user to be logged in with an administrator role.
*   The login dialog will prompt you for a Username and Password.
*   **Default Administrator Credentials (for demonstration):**
    *   **Username:** `admin`
    *   **Password:** `adminpass`
*   Enter these credentials into the respective fields.
*   Click the **"Login"** button to proceed.
    *   If login is successful, the login dialog will close, and the main "Class Management System" window will appear.
    *   If login fails (e.g., incorrect credentials), an error message will be displayed, and the password field will be cleared. You can try again or click "Cancel" to exit the application.
*   **Note:** Clicking **"Cancel"** on the login dialog will exit the application.

### 3. Class Management Screen

After a successful administrator login, you will be presented with the main Class Management System window.

*   **Implicit Precondition:** The login process fulfills the "user click on the 'Class Management' button" precondition by directly displaying this screen upon successful authentication.

#### A. Select Academic Year

1.  At the top left of the screen, you will see a dropdown box labeled **"Select Academic Year:"** and a `JComboBox` next to it.
2.  Click on the dropdown box. It will display a list of available academic years (e.g., "2022-2023", "2023-2024", "2024-2025").
3.  **Select the academic year of interest** by clicking on one of the options.

#### B. View Class List

*   Once an academic year is selected from the dropdown, the system will:
    *   **Search for classes in the archive:** It queries the simulated `ClassDataService` for classes associated with the selected year.
    *   **Display the class management screen:** The `JTable` in the center of the window will be populated with the list of classes for the chosen academic year.
    *   The table columns include: "ID", "Name", "Academic Year", and "Instructor".
    *   The `statusLabel` at the bottom of the window will update to show "Displayed X classes for [Selected Academic Year]."

#### C. Simulate SMOS Server Disconnect / Restore Connection

*   To the right of the academic year dropdown, there is a button initially labeled **"Simulate SMOS Server Disconnect"**.
*   **Clicking this button** will:
    1.  Change its text to **"Restore SMOS Server Connection"**.
    2.  Simulate a connection interruption to the backend "SMOS server" (represented by `ClassDataService`).
    3.  Clear the displayed class list in the table.
    4.  Update the status label to "SMOS server connection interrupted."
    5.  Any attempt to select a different academic year while the connection is interrupted will result in an error message indicating the connection issue.
*   **Clicking the button again** (when it reads "Restore SMOS Server Connection") will:
    1.  Change its text back to **"Simulate SMOS Server Disconnect"**.
    2.  Restore the simulated connection.
    3.  Update the status label to "SMOS server connection restored."
    4.  If an academic year was previously selected, the application will automatically try to reload the classes for that year.

#### Postconditions:

*   The system consistently shows the list of classes of the selected academic year.
*   If the administrator interrupts operations (e.g., by simulating a disconnect), the system reflects the "Connection to the SMOS server interrupted" state, clearing the table and preventing further data loads until the connection is restored.

### 4. Exiting the Application

*   To close the Class Management System, simply click the standard "Close" button (e.g., the 'X' button on the window title bar) provided by your operating system.

---

This manual should help you effectively use the Class Viewer application.
```