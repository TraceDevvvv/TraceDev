manual.md
```markdown
# Administrator Absence & Delay Entry System

## Introduction

The "Administrator Absence & Delay Entry System" is a Java-based desktop application designed for school administrators to efficiently digitize and manage student attendance records. This tool streamlines the process of marking students as absent or delayed for a specific date, ensuring data accuracy and enabling timely notifications to parents. It supports registering attendance data, updating existing entries, and providing system feedback through an integrated log.

## Main Functions

This application provides the following core functionalities for administrators:

1.  **Date Selection**: Administrators can select a specific date for which they intend to enter or review attendance information. The system will then display the relevant student data for that chosen date.
2.  **Student Roster Display**: A comprehensive list of all registered students is displayed in a table format, showing their ID, name, and parent's email.
3.  **Attendance Entry**: For each student, administrators can easily mark them as "Absent" or "Delayed" using interactive checkboxes directly within the table. Both statuses can be applied simultaneously if needed.
4.  **Save Data**: After marking absences and delays, the administrator can save the entered data. The system processes this information, updating the attendance records.
5.  **Automated Parent Email Notifications (Simulated)**: Upon saving, for every student marked as absent or delayed, the system (via a simulated server interaction) dispatches a notification email to their respective parent.
6.  **System Log**: An integrated log area provides real-time feedback on system actions, including selected dates, data loading, save operations, notification dispatches, and any encountered errors or warnings.
7.  **Data Persistence (In-memory Simulation)**: While in-memory for this example, the system simulates the persistence of absence and delay data, allowing entries for different dates to be retrieved and displayed.
8.  **Simulated Server Interruption Handling**: The application includes a simulation of an interrupted connection to an external "SMOS server" during the save operation, demonstrating how the system would inform the user of such a critical issue.

## Environment Dependencies

This project is developed in Java and relies on standard Java Development Kit (JDK) libraries. No additional third-party dependencies or external JARs are required beyond a standard Java environment.

*   **Java Development Kit (JDK)**: You will need a Java Development Kit (JDK) installed on your system. Version **8 or higher** is recommended (e.g., JDK 11, 17, or 21).
    *   **Verify JDK Installation**: You can check your installed Java version by opening a terminal or command prompt and typing:
        ```bash
        java -version
        ```
        If Java is not installed or the version is too old, please download and install a suitable JDK from Oracle, OpenJDK, or other providers.

*   **Integrated Development Environment (IDE)**: While you can compile and run Java code from the command line, using an IDE significantly simplifies the process. Recommended IDEs with Java support include:
    *   **IntelliJ IDEA** (Community or Ultimate Edition)
    *   **Eclipse IDE for Java Developers**
    *   **Visual Studio Code** (with the Java Extension Pack installed)

## How to Use/Play It

Follow these steps to set up, run, and interact with the Administrator Absence & Delay Entry System.

### 1. Setup and Compilation

1.  **Download the Code**: Obtain all the provided `.java` files (`student.java`, `absenceentry.java`, `studentservice.java`, `notificationservice.java`, `absenceservice.java`, `studentstablemodel.java`, `enterabsencesadmingui.java`).
2.  **Organize Files**:
    *   Create a root directory for your project (e.g., `AbsenceTracker`).
    *   Inside this directory, create a subdirectory structure corresponding to the package names: `src/com/chatdev/absencetracker/model`, `src/com/chatdev/absencetracker/service`, `src/com/chatdev/absencetracker/gui`.
    *   Place each `.java` file into its respective package directory based on its `package` declaration (e.g., `student.java` goes into `src/com/chatdev/absencetracker/model`).
3.  **Open in an IDE (Recommended)**:
    *   Open your chosen IDE (IntelliJ IDEA, Eclipse, VS Code).
    *   Import the `AbsenceTracker` directory as a new Java project. The IDE should automatically recognize the package structure and dependencies between the files.
    *   Ensure the project is configured to use a JDK (Java 8 or higher).
4.  **Manual Compilation (Optional, Command Line)**:
    *   Navigate to your project's `src` directory in the terminal or command prompt.
    *   Compile all Java files:
        ```bash
        javac com/chatdev/absencetracker/model/*.java com/chatdev/absencetracker/service/*.java com/chatdev/absencetracker/gui/*.java
        ```
    *   This will create `.class` files in the respective directories.

### 2. Running the Application

*   **From an IDE (Recommended)**:
    *   Locate the `EnterAbsencesAdminGUI.java` file in your IDE's project explorer.
    *   Right-click on `EnterAbsencesAdminGUI.java` and select "Run 'main()'" or similar option.
*   **From the Command Line (If compiled manually)**:
    *   Navigate to the directory *above* your `com` folder (typically the `src` directory from where you compiled).
    *   Run the main class:
        ```bash
        java com.chatdev.absencetracker.gui.EnterAbsencesAdminGUI
        ```

    A new "Administrator Absence/Delay Entry System" window should appear.

### 3. Interacting with the GUI

Upon launching, the application will display a GUI with several sections:

1.  **Select Attendance Date (Top Panel)**:
    *   **Date Input**: You will see a field labeled "Date (YYYY-MM-DD):" pre-filled with the current date. You can enter or change the date here.
        *   **Format**: Ensure the date is entered in `YYYY-MM-DD` format (e.g., `2023-10-27`). The input field includes a mask to guide you.
        *   **Incomplete Date Warning**: If you try to load data with an incomplete date (e.g., `2023-10-__`), the system will warn you.
    *   **Load Data for Date Button**: Click this button after entering a date. The student attendance table will update to show existing records for that specific date.

2.  **Mark Absences and Delays for Students (Center Panel)**:
    *   This is the main table displaying student information.
    *   **Columns**:
        *   `Student ID`
        *   `Student Name`
        *   `Parent Email`
        *   `Absent` (Checkbox): Check this box to mark a student absent.
        *   `Delayed` (Checkbox): Check this box to mark a student delayed.
    *   **Editing**: Click on the `Absent` or `Delayed` checkboxes in the table to toggle their status for each student.
    *   **Pre-filled Status**: When you load data for a date, any previously saved absence/delay statuses for that date will be pre-checked in the table.

3.  **Save Absences/Delays (Bottom Panel)**:
    *   **Save Button**: After making your changes in the student table, click the "Save Absences/Delays" button.
    *   **Process**: The system will process your entries. For each student marked as absent or delayed, it will simulate sending an email notification to their parent.
    *   **Feedback**: A success or error message will appear in the log area, and a pop-up dialog will confirm the save operation or report issues.

4.  **System Log and Notifications (Right Panel)**:
    *   This `JTextArea` displays a running log of all significant actions performed by the application, including:
        *   Date selections.
        *   Data loading events.
        *   Save attempts and their outcomes.
        *   Simulated email notifications (the content of the email usually gets printed here).
        *   Error messages (e.g., invalid date format, simulated server interruptions).
    *   The log automatically scrolls to show the latest messages.

### 4. Important Considerations

*   **Simulated Backend**: The current implementation uses in-memory data storage and simulates server communication and email notifications by printing to the console/log. In a real-world application, these would interact with actual databases and email serv.
*   **SMOS Server Interruption**: There is a random chance (approximately 15%) for the simulated SMOS server connection to "interrupt" during the save operation. If this happens, you will see an error message in the log and a pop-up, and the save will not complete, demonstrating error handling. You can retry the save operation.
*   **Pre-loaded Student Data**: The application starts with a set of dummy student data for demonstration purposes, defined within the `StudentService`.
```