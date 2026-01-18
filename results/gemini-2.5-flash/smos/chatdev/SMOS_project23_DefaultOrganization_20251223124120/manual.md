```
# Teaching Archive Administrator Tool

## Introduction

The "Teaching Archive Administrator Tool" is a simple Java Swing desktop application designed to allow an administrator to manage a list of academic teachings. This tool facilitates the insertion of new teaching subjects into an archive, providing immediate feedback on the operation's success or any encountered issues, such as invalid data, duplicate entries, or simulated connection problems to an external "SMOS Server."

This tool implements the "InsertNewTeaching" use case, allowing administrators to add new teaching subjects after reviewing the existing ones.

## Main Functions and Features

*   **View Existing Teachings:** Upon launch, the application displays a list of all currently archived teachings, fulfilling the precondition of "viewing technology."
*   **Add New Teaching:** Administrators can click the "New Teaching" button to open a dedicated form for entering a new teaching subject.
*   **Teaching Name Input:** A user-friendly form allows the administrator to input the name of the new teaching.
*   **Data Validation:** The system performs checks on the entered teaching name:
    *   Ensures the name is not empty.
    *   Prevents the addition of teaching names that already exist in the archive.
*   **SMOS Server Connection Simulation:** To mimic real-world scenarios, the application simulates a connection attempt to an external "SMOS server." This simulation includes a chance of connection failure.
*   **Error Handling and User Feedback:**
    *   Provides clear messages for invalid data entry (e.g., empty name).
    *   Notifies the administrator if a duplicate teaching name is entered.
    *   Displays a critical error message via a pop-up and an in-form message if the simulated "SMOS server" connection fails.
    *   Confirms successful teaching insertions.
*   **Cancel Operation:** The administrator can interrupt the operation at any time by closing the "Insert New Teaching" form or clicking the "Cancel" button.
*   **Real-time List Updates:** After successfully adding a new teaching or closing the insert form, the main teaching list view automatically refreshes to reflect any changes.

## System Requirements

To run this application, you will need:

*   **Java Development Kit (JDK):** Version 8 or higher is recommended. You can download it from Oracle's website or use OpenJDK.

## How to Install Environment Dependencies

As this is a standalone Java application, the primary dependency is the Java Development Kit (JDK). There are no external libraries or package managers (like Maven or Gradle) required for this specific setup as all classes are provided as `.java` files.

1.  **Verify Java Installation (or Install JDK):**
    *   Open your command prompt (Windows) or terminal (macOS/Linux).
    *   Type `java -version` and press Enter.
    *   If Java is installed, you will see version information.
    *   If not installed, or if the version is too old, download and install the latest LTS version of OpenJDK (e.g., from Adoptium, Oracle, or your OS package manager). Follow the installation instructions for your operating system.
    *   Ensure that the `JAVA_HOME` environment variable is set and that `java` and `javac` commands are accessible in your system's PATH.

## How to Compile and Run the Application

Follow these steps to compile and execute the Teaching Archive Administrator Tool:

1.  **Save the Code:**
    *   Create a new directory (e.g., `TeachingApp`).
    *   Save all the provided `.java` files (`Teaching.java`, `TeachingArchive.java`, `Main.java`, `TeachingListView.java`, `InsertNewTeachingGUI.java`) into this `TeachingApp` directory.

2.  **Compile the Java Source Files:**
    *   Open your command prompt or terminal.
    *   Navigate to the directory where you saved your Java files:
        ```bash
        cd path/to/TeachingApp
        ```
    *   Compile all `.java` files using the Java compiler (`javac`):
        ```bash
        javac *.java
        ```
    *   If compilation is successful, `.class` files will be generated for each Java source file in the same directory. If there are errors, ensure your JDK is correctly installed and that the code snippets were copied accurately.

3.  **Run the Application:**
    *   After successful compilation, run the application by executing the `Main` class:
        ```bash
        java Main
        ```

## How to Use/Play It

Once you run the `java Main` command, the application will launch:

1.  **View Initial Teaching List:**
    *   A window titled "Available Teachings" will appear.
    *   This window displays a list of pre-existing teachings (e.g., Mathematics, Physics, Chemistry). This simulates the "viewing technology" precondition.

2.  **Open the "Insert New Teaching" Form:**
    *   Click the "New Teaching" button located at the bottom right of the "Available Teachings" window.
    *   A new window titled "Insert New Teaching" will open.

3.  **Fill Out the Form:**
    *   **Successful Insertion:**
        *   In the "Teaching Name:" field, type a new, unique teaching name (e.g., "Biology").
        *   Click the "Save" button.
        *   You will see a success message like "Teaching 'Biology' added successfully!" in green text. The input field will clear.
        *   Close the "Insert New Teaching" window. Observe that "Biology" now appears in the "Available Teachings" list.
    *   **Invalid Data (Empty Name):**
        *   Leave the "Teaching Name:" field empty.
        *   Click the "Save" button.
        *   An error message "Error: Teaching name cannot be empty or invalid." will appear in red.
    *   **Duplicate Teaching Name:**
        *   Type the name of an existing teaching (e.g., "Mathematics").
        *   Click the "Save" button.
        *   An error message "Error: A teaching with the name 'Mathematics' already exists." will appear in red.
    *   **"SMOS Server Interrupted" Error (Simulated):**
        *   The application randomly simulates a connection error (approximately a 20% chance) when you try to save.
        *   Enter a valid, unique teaching name.
        *   Click the "Save" button multiple times.
        *   Eventually, a "SMOS Server Error - Critical" dialog box might pop up, stating "Error: Connection to the SMOS server interrupted. Teaching could not be saved."
        *   The status label on the form will also show "Error: SMOS connection failed. See pop-up for details." in red.
        *   You must click "OK" on the pop-up to continue.

4.  **Cancel the Operation:**
    *   While the "Insert New Teaching" form is open, you can click the "Cancel" button or simply close the window using your operating system's close button (e.g., the 'X' button).
    *   This action simulates the administrator interrupting the operation. The main "Available Teachings" list will refresh, but no new teaching will have been added if the form was cancelled before a successful save.

### Experiencing the Full Workflow

1.  Start the application.
2.  Observe the initially displayed list of teachings.
3.  Click "New Teaching."
4.  Enter "History" and click "Save." Observe the success message.
5.  Close the "Insert New Teaching" window.
6.  See "History" added to the main list.
7.  Click "New Teaching" again.
8.  Try entering an empty name, then "Mathematics" (a duplicate), and observe the error messages.
9.  Attempt to save a new valid name multiple times until you trigger the "SMOS connection error" pop-up.

This completes the intended user experience for the "InsertNewTeaching" use case.
```