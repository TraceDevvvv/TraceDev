```
# ATA Absence Management System

## Register Digitized Absence Data for ATA Classes

This application is designed for ATA (Administrative, Technical, and Auxiliary) staff to efficiently register and manage student absences. It streamlines the data collection process by allowing staff to mark students as present or absent for a selected class, send notifications to parents of absent students, and log the data within the system.

## 🤔 What does this application do?

The ATA Absence Management System facilitates the complete workflow for recording student absences:

*   **Secure Login**: Provides a mock login interface for ATA staff to access the system.
*   **Class Selection**: Allows staff to select a specific class from a predefined list to record attendance.
*   **Absence Registration**: Presents a list of students in the selected class with user-friendly radio buttons to mark each student as either present or absent. By default, all students are marked as present.
*   **Data Saving & Server Communication**: Upon saving, the system compiles the attendance data and simulates sending it to a remote server.
*   **Parent Notifications**: For each student marked absent, the system simulates sending an email notification to their parent.
*   **Local Data Logging**: After successful server communication, absence data is logged locally within the system, fulfilling the requirement of data entry.
*   **Operation Interruption**: Users can interrupt the operation at any time, returning to the class selection screen.

## 📖 Environment Setup and Installation

To run this Java application, you need to have a Java Development Kit (JDK) installed on your system.

### Dependencies

*   **Java Development Kit (JDK)**: Version 8 or higher is recommended.
    *   You can download it from the [Oracle website](https://www.oracle.com/java/technologies/downloads/) or use an open-source distribution like [OpenJDK](https://openjdk.java.net/install/index.html).

### Compilation and Execution

1.  **Save all files**: Ensure all provided `.java` files (`Main.java`, `LoginScreen.java`, `ClassSelectionScreen.java`, `Student.java`, `ParentNotifier.java`, `ServerCommunicator.java`, `AbsenceLogger.java`, `AbsenceTrackerApp.java`) are saved in the same directory (e.g., `ATAAbsenceSystem`).
2.  **Open a Terminal or Command Prompt**: Navigate to the directory where you saved the files.
    ```bash
    cd path/to/ATAAbsenceSystem
    ```
3.  **Compile the Java files**: Use the Java compiler (`javac`) to compile all source files.
    ```bash
    javac *.java
    ```
    If compilation is successful, `.class` files will be generated for each `.java` file.
4.  **Run the application**: Execute the `Main` class using the Java Virtual Machine (`java`).
    ```bash
    java Main
    ```

## ▶️ How to Use the Application

Follow these steps to navigate and use the ATA Absence Management System:

1.  **Launch the Application**: Run the `java Main` command as described in the "Environment Setup and Installation" section.
2.  **Login Screen**:
    *   A "ATA Staff Login" window will appear.
    *   **Username**: You can use the default "ata_staff" or any non-empty string.
    *   **Password**: You can use the default "password" or any non-empty string.
    *   Click the "Login" button.
    *   *Note*: This is a mock login; any non-empty credentials will be considered successful.
3.  **Class Selection Screen**:
    *   Upon successful login, a "Select Class" window will appear.
    *   Use the dropdown menu to choose a class (e.g., "1A - Science", "1B - Math").
    *   Click "Select Class".
4.  **Absence Registration Screen**:
    *   A new window titled "Register Absences for [Selected Class Name]" will open, displaying a list of students for that class.
    *   Each student entry has two radio buttons: "Present" (selected by default) and "Absent".
    *   **Mark Absences**: For each student you wish to mark as absent, click the "Absent" radio button next to their name. Students who are present can be left as is.
    *   **Save Data**: Once you have marked all necessary absences, click the "Save Absences" button at the bottom right.
5.  **System Actions (Simulated)**:
    *   **Server Communication**: The system will simulate sending the absence data to a server. Watch the terminal output for messages like "--- SIMULATING SERVER DATA TRANSMISSION ---".
        *   *Simulated Server Error*: Occasionally (about 1 in 10 times), the server communication will simulate a failure, triggering an error message. If this happens, the data will not be processed further, and you'll stay on the absence registration screen.
    *   **Local Data Logging**: After successful server communication, the data will be logged locally. You'll see "--- LOGGING ABSENCE DATA TO LOCAL SYSTEM ---" in the terminal.
    *   **Parent Notifications**: For every student marked "Absent", a simulated email notification will be displayed in the terminal output, showing the recipient, subject, and body of the email.
    *   **Confirmation**: A "Save Successful" pop-up will confirm the operation and whether notifications were sent.
6.  **Return to Class Selection**: After confirming the save, the absence registration window will close, and you will be returned to the "Select Class" screen, ready to select another class or exit.
7.  **Interrupting Operation**:
    *   While on the "Absence Registration Screen", you can close the window using the 'X' button.
    *   A confirmation dialog "Are you sure you want to interrupt this operation?" will appear.
    *   Click "Yes" to interrupt, which will print "USER INTERRUPTED OPERATION." to the console and return you to the previous "Select Class" screen.
    *   Click "No" to continue with the absence registration.
8.  **Exit Application**: To fully close the application, close the "Select Class" window.

This manual covers the core functionality and usage of the ATA Absence Management System, guiding you from setup to daily operation.
```