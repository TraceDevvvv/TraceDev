# Teaching Management System

Simplifying teaching records for administrators.

## 🤔 What is this?

This application is a simple Java Swing-based demonstration designed to showcase an administrator's ability to view a list of "teachings" (e.g., courses, lectures, or educational programs) within a simulated system. It's built to fulfill a specific use case, including mock administrator login, navigation to a management screen, and displaying archived teaching data. A key aspect is the simulation of an external server connection (SMOS) that is established upon login and then intentionally interrupted after the teaching list is displayed, simulating real-world system interactions and postconditions.

## ✨ Main Functions

The system provides a clear flow for an administrator to access relevant information:

1.  **Administrator Login (Simulated)**: A simplified login screen allows for an immediate simulated login as an administrator, fulfilling a critical precondition.
2.  **Administrator Dashboard**: A central dashboard provides navigation. The main entry point for the use case is the "Management Management" button.
3.  **View Teachings List**: Upon navigating from the dashboard, the core functionality is to display a tabular list of teachings retrieved from a simulated archive. Each teaching includes an ID, title, and description.
4.  **SMOS Server Connection Management**: The application simulates connecting to a hypothetical "SMOS server" upon successful administrator login and explicitly disconnects from it once the teaching list is presented, demonstrating system-level side effects.

## 🛠️ Installation & Environment Dependencies

This project is developed in Java and uses standard Java libraries (specifically Java Swing for the GUI). It does not require any external third-party dependencies outside of the Java Development Kit (JDK).

**Requirements:**

*   **Java Development Kit (JDK)**: Version 8 or higher is recommended. You can download it from Oracle's website or use an open-source distribution like OpenJDK.

**Steps to Prepare Your Environment:**

1.  **Install JDK**: If you don't have Java installed, download and install the latest stable JDK version. Ensure `JAVA_HOME` is set and `java` and `javac` commands are accessible from your terminal/command prompt.
2.  **Save the files**: Save all the provided `.java` files (`Teaching.java`, `TeachingService.java`, `LoginFrame.java`, `AdminDashboardFrame.java`, `TeachingsListFrame.java`, `Main.java`) into a single directory on your computer.

## ▶️ How to Use/Play

Follow these steps to compile and run the application:

1.  **Open a Terminal or Command Prompt**: Navigate to the directory where you saved your `.java` files.

2.  **Compile the Java Code**:
    Execute the Java compiler command. This will compile all `.java` files into `.class` bytecode files.

    ```bash
    javac *.java
    ```

    If successful, you will see several `.class` files appear in your directory.

3.  **Run the Application**:
    Execute the `Main` class using the Java Virtual Machine (JVM).

    ```bash
    java Main
    ```

4.  **Application Walkthrough**:

    *   **Login Screen**: A `Login - Administrator Access` window will appear.
        *   Click the **"Login as Administrator"** button.
        *   A message dialog will confirm "Administrator Login Successful!". Click "OK".
        *   Observe the console output: `Simulated: Connected to SMOS server.`

    *   **Administrator Dashboard**: The `Administrator Dashboard` window will appear.
        *   Click on the prominent **"Management Management"** button.
        *   Observe the console output: `User clicked 'Management Management' button.`

    *   **Teachings List View**: The `Teachings Management - List View` window will open, displaying a table with a list of simulated teachings.
        *   Initially, the `SMOS Connection Status: Active` label will be visible at the bottom.
        *   After a brief delay (simulating processing), you will see:
            *   An `SMOS server connection has been interrupted.` warning message box. Click "OK".
            *   The `SMOS Connection Status` label at the bottom right will change to `SMOS Connection Status: Interrupted` and turn red.
            *   The console will output: `System: Displayed list of X teachings.` (where X is the number of teachings) and `Simulated: Disconnected from SMOS server. (SMOS server connection interrupted)`.

This completes the intended use case flow, demonstrating the administrator's ability to view the list of teachings and the simulated SMOS server interaction.