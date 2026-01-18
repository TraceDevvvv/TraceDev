```markdown
# Student Monitoring System User Manual

## 🤔 What is this?

The Student Monitoring System is a Java-based application designed for "Direction" users (e.g., school administrators, faculty leads) to efficiently identify students who require attention due to a high number of disciplinary notes and absences. It provides a graphical user interface (GUI) to input custom thresholds for both notes and absences, and then displays a list of all students whose records exceed *both* these user-defined limits.

This tool aims to simplify the process of identifying at-risk students, making the monitoring process more agile and data-driven.

## ✨ Main Features

*   **Dynamic Thresholds**: Users can set custom numerical thresholds for both student notes and absences.
*   **Student Filtering**: The system processes a simulated student database and filters students who exceed *both* the notes and absences thresholds.
*   **Clear Display**: Recovered student information (name, notes count, absences count) is displayed in an easy-to-read format.
*   **Input Validation**: The application handles invalid user input, such as non-numeric characters or negative values for thresholds, providing informative error messages.
*   **Simulated Environment**: For demonstration purposes, student data and SMOS (Student Monitoring Operational System) server status are simulated. In a real-world scenario, this would connect to actual databases and serv.
*   **User-Friendly Interface**: Built with Java Swing, it offers an intuitive graphical interface for ease of use.

## 🚀 System Requirements

To compile and run this Java application, you will need:

*   **Java Development Kit (JDK)**: Version 8 or higher. You can download it from the official Oracle website or adopt an open-source distribution like OpenJDK.

## 📦 Installation and Setup

Follow these steps to get the Student Monitoring System up and running on your local machine:

1.  **Save the Source Code**:
    *   Create a new directory (e.g., `StudentMonitoring`) on your computer.
    *   Inside this directory, save the provided Java code into three separate files:
        *   `Student.java`
        *   `StudentService.java`
        *   `StudentMonitoringApp.java`
    *   Ensure the file names and class names match exactly (case-sensitive).

2.  **Open a Terminal/Command Prompt**:
    *   Navigate to the directory where you saved the `.java` files using the `cd` command.
        ```bash
        cd path/to/your/StudentMonitoring
        ```

3.  **Compile the Java Code**:
    *   Use the Java compiler (`javac`) to compile all `.java` files.
        ```bash
        javac Student.java StudentService.java StudentMonitoringApp.java
        ```
    *   If compilation is successful, `.class` files will be generated in the same directory. If there are errors, ensure your JDK is correctly installed and your files are saved correctly.

## ▶️ How to Use (Play)

Once the code is compiled, you can run the application and interact with its GUI.

1.  **Run the Application**:
    *   From the same terminal/command prompt, execute the main application class.
        ```bash
        java StudentMonitoringApp
        ```
    *   A window titled "Student Monitoring System" should appear.

2.  **Interacting with the GUI**:

    *   **Set Monitoring Thresholds**:
        *   Locate the "Notes Threshold:" and "Absences Threshold:" input fields.
        *   Enter integer values into these fields. For example, you might enter `3` for Notes Threshold and `5` for Absences Threshold. This means you are looking for students with *more than* 3 notes AND *more than* 5 absences.
            *   *Initial values are set to `0` by default, but you should input your desired thresholds.*

    *   **Monitor Students**:
        *   Click the "Monitor Students" button.
        *   The application will process the data and display the results in the "Monitoring Results" area below.

    *   **Interpreting Results**:
        *   If students match your criteria, their names, notes count, and absences count will be listed.
        *   If no students match, a message indicating "No students found exceeding the specified thresholds" will be displayed.
        *   A simulated SMOS server status message will also appear at the bottom of the results area, confirming a simulated connection.

    *   **Handling Invalid Input**:
        *   Try entering non-numeric text (e.g., "abc") or negative numbers (e.g., "-1") into the threshold fields.
        *   Click "Monitor Students".
        *   You will see an error message popup instructing you about the correct input format, and the results area will be cleared.

3.  **Example Usage**:

    *   Enter `4` for "Notes Threshold".
    *   Enter `6` for "Absences Threshold".
    *   Click "Monitor Students".
    *   The output will show students like "Bob Johnson" (5 notes, 7 absences) and "Frank White" (6 notes, 8 absences) because their counts are higher than 4 and 6 respectively. "Diana Miller" (8 notes, 10 absences) would also appear.

    *   Change the "Notes threshold" to `7` and "Absences Threshold" to `9`.
    *   Click "Monitor Students" again.
    *   Now, only "Diana Miller" (8 notes, 10 absences) will be displayed.

    *   Set both thresholds to `10`. You will likely see "No students found..." with the current simulated data.

4.  **Close the Application**:
    *   Simply close the application window to exit.

This manual provides a comprehensive guide to using the Student Monitoring System effectively.
```