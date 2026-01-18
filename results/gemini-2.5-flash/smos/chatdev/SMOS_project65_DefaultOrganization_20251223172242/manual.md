```markdown
# Class Registry Viewer

This application provides a graphical user interface (GUI) for school administration or faculty (`Direction`) to manage the daily register of a specific class for a given academic year. It allows users to review student attendance (present, absent, delayed), manage justifications for absences/delays, and add disciplinary notes.

## 🚀 Main Functions

The Class Registry Viewer offers the following key functionalities:

1.  **View Class Register by Date:** Browse attendance records for different dates within a selected academic year.
2.  **Attendance Tracking:** See the attendance status (Present, Absent, Delayed) for each student on a given day.
3.  **Manage Justifications:**
    *   Add new justifications for a student's absence or delay.
    *   Edit existing justifications, including updating the reason and approval status.
4.  **Add Disciplinary Notes:** Attach disciplinary notes to a student's record for a specific date.
5.  **Update Attendance Status:** Change a student's attendance status directly from the main view.
6.  **Initialize Daily Register:** Easily create a new daily register for "today" which pre-fills all students as "Present", if one doesn't already exist for that day.

## 📦 Environment Setup (Installation)

To run this application, you need to have the Java Development Kit (JDK) installed on your system, specifically **JDK 11 or newer**.

### Step 1: Install JDK 11 (or newer)

If you do not have JDK 11 installed, follow these instructions:

*   **Windows / macOS / Linux:**
    1.  Download the appropriate JDK 11 installer from the official Oracle website or an open-source distribution like AdoptOpenJDK/Eclipse Temurin.
    2.  Follow the installation instructions provided by the installer.
    3.  Set up your `JAVA_HOME` environment variable to point to your JDK installation directory.
    4.  Add `%JAVA_HOME%\bin` (Windows) or `$JAVA_HOME/bin` (macOS/Linux) to your system's `PATH` environment variable.
    5.  Verify the installation by opening a terminal or command prompt and typing:
        ```bash
        java -version
        javac -version
        ```
        You should see output indicating Java version 11 or higher.

### Step 2: Prepare the Source Code

1.  Create a directory for your project, for example, `ClassRegistryApp`.
2.  Save all the provided Java code files (`Student.java`, `AttendanceStatus.java`, `Justification.java`, `DisciplinaryNote.java`, `RegisterEntry.java`, `ClassRegister.java`, `RegisterService.java`, `RegisterTableModel.java`, `JustificationDialog.java`, `DisciplinaryNoteDialog.java`, `RegisterApp.java`) into this `ClassRegistryApp` directory.

## 🚀 How to Use/Play

### Step 1: Compile the Java Source Code

1.  Open your terminal or command prompt.
2.  Navigate to the `ClassRegistryApp` directory where you saved the `.java` files.
    ```bash
    cd path/to/ClassRegistryApp
    ```
3.  Compile all the Java files using the Java compiler:
    ```bash
    javac Student.java AttendanceStatus.java Justification.java DisciplinaryNote.java RegisterEntry.java ClassRegister.java RegisterService.java RegisterTableModel.java JustificationDialog.java DisciplinaryNoteDialog.java RegisterApp.java
    ```
    If compilation is successful, you will see `.class` files generated for each `.java` file in the same directory. If there are errors, ensure your JDK is correctly installed and configured.

### Step 2: Run the Application

From the same directory in your terminal, execute the main application class:

```bash
java RegisterApp
```

### Step 3: Interacting with the Application

Upon running, a "Class Registry Viewer" window will appear.

1.  **Initial Load & SMOS Server Message:**
    *   The application will attempt to load sample data.
    *   You might immediately see a pop-up message: "Connection to the SMOS server interrupted." This simulates a post-condition from the use case and is expected behavior upon startup, not an actual error in data loading for this demo. Click "OK" to dismiss it.
    *   The table will display register entries for the *latest* available date in the sample data (e.g., Oct 30, 2023).

2.  **Select a Date:**
    *   At the top left of the window, you'll see a "Select Date:" dropdown (`JComboBox`).
    *   Click on the dropdown to view available dates with register entries (e.g., 2023-10-30, 2023-10-27, 2023-10-26).
    *   Select a date to view its corresponding class register in the table below.

3.  **Add Today's Register:**
    *   If no register exists for the current date or you wish to create one for "today", click the "Add Today's Register" button.
    *   A confirmation dialog will ask if you want to create a register for today, marking all students as "Present".
    *   Click "Yes" to create it. The current date will be added to the date dropdown, and its register will be displayed. If a register for today already exists, you'll be informed, and the display will shift to today's register.

4.  **Manage Justification:**
    *   **Select a student:** Click on any row in the table to select a student.
    *   Click the "Manage Justification" button.
    *   A dialog will appear:
        *   If the selected student already has a justification for the selected date, its details will be pre-filled for editing.
        *   If not, it will be an empty form for adding a new justification.
    *   Enter or edit the "Reason" and check/uncheck "Approved" status.
    *   Click "Save" to apply changes or "Cancel" to discard.
    *   The table will refresh, showing the updated justification status in the "Justification" column.

5.  **Add Disciplinary Note:**
    *   **Select a student:** Click on any row in the table to select a student.
    *   Click the "Add Disciplinary Note" button.
    *   A dialog will appear for adding a new disciplinary note.
    *   Enter a "Description" for the note.
    *   Click "Save" to add the note or "Cancel" to discard.
    *   The table will refresh, showing the new disciplinary note in the "Disciplinary Notes" column. Multiple notes for a student on the same day will be semicolon-separated.

6.  **Update Attendance Status:**
    *   **Select a student:** Click on any row in the table to select a student.
    *   Click the "Update Attendance Status" button.
    *   A dropdown will appear, allowing you to choose a new status from "PRESENT", "ABSENT", or "DELAYED".
    *   Select the desired status and click "OK".
    *   The table will immediately update to reflect the new attendance status for the selected student.

Feel free to experiment with different dates, add various justifications, and disciplinary notes to understand the system's full capabilities. The data is stored in memory (`RegisterService`) for the duration of the application run and will reset upon closing and restarting the application.

```