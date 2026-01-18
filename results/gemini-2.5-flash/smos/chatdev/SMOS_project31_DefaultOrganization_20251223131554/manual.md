```markdown
# Absence Management System (Administrator Dashboard)

This document serves as a user manual for the "Absence Management System," a JavaFX application designed for administrators to manage student absences. The system allows an administrator to view, add, and delete student absence records for a specific date, and to save these changes, which triggers simulated server updates and parent notifications.

## 🤔 What is this?

The Absence Management System is a prototype application fulfilling the "EditAbsence" use case. It provides an intuitive graphical user interface (GUI) for school administrators to efficiently manage student attendance. The application simulates interactions with a backend server (SMOS) and an email notification system, demonstrating key functionalities like data persistence and communication with external serv.

## ✨ Main Functions

The administrator dashboard offers the following core functionalities:

*   **View Absences by Date**: Select any calendar date to display all recorded student absences for that specific day in a table.
*   **Add New Absence**:
    *   Click the "Add Absence" button to open a dialog.
    *   Enter the `Student ID` (must be an existing student in the system's dummy data), the `Date` of absence (pre-filled with the selected dashboard date), and a `Reason` for the absence.
    *   Upon successful addition, the new absence will appear in the table with a `NEW` status, indicating it's pending save.
*   **Delete Absence**:
    *   Select an existing absence record from the table.
    *   Click the "Delete Selected" button.
    *   If the selected absence is newly added (status `NEW`), it will be immediately removed from the table.
    *   If the selected absence is an existing record (status `EXISTING`), it will be marked with a `DELETED` status in the table (red text, strikethrough), indicating it's pending removal.
*   **Save Changes**:
    *   After adding or marking absences for deletion, an "Unsaved Changes" label will appear, and the "Save Changes" button will become active.
    *   Click "Save Changes" to commit all pending additions and deletions.
    *   The system will simulate sending this data to a hypothetical SMOS server and sending email notifications to the parents of affected students.
    *   Upon successful save, the `NEW` and `DELETED` statuses will revert to `EXISTING` (for newly added items) or be removed from the table (for deleted items).
*   **Cancel Changes**:
    *   If unsaved changes are present, click "Cancel" to revert the table to its state before any modifications were made on the current date. All pending additions and deletions will be discarded.
*   **Status Indicators**: A status label at the bottom provides real-time feedback on operations (loading, saving, errors). An "Unsaved Changes" label indicates pending modifications.

## 🛠️ Installation & Setup

This application is built with Java and JavaFX. You'll need a Java Development Kit (JDK) and the JavaFX SDK.

### Prerequisites

*   **Java Development Kit (JDK) 21**: Make sure you have JDK 21 installed. You can download it from [Oracle](https://www.oracle.com/java/technologies/downloads/) or use an OpenJDK distribution like [Temurin](https://adoptium.net/temurin/releases/).
*   **JavaFX SDK 21**: JavaFX is no longer bundled with the JDK. You'll need to download it separately from [Gluon OpenJFX](https://gluonhq.com/products/javafx/). Download the SDK for your operating system.

### Steps to Install and Configure

1.  **Install JDK 21**:
    *   Download the appropriate installer for your OS from the links above.
    *   Follow the installation instructions.
    *   Verify installation by opening a terminal/command prompt and typing:
        ```bash
        java -version
        javac -version
        ```
        Both should report version 21.

2.  **Download JavaFX SDK 21**:
    *   Go to the [Gluon OpenJFX download page](https://gluonhq.com/products/javafx/).
    *   Download the "SDK" package for Java 21 and your operating system (e.g., `openjfx-21_windows-x64_bin-sdk.zip` for Windows).
    *   Unzip the downloaded archive to a convenient location on your system (e.g., `C:\javafx-sdk-21` on Windows, `/opt/javafx-sdk-21` on Linux/macOS). We'll refer to this path as `PATH_TO_FX`.

3.  **Set up in an IDE (Recommended - e.g., IntelliJ IDEA)**:
    *   **Open Project**: Open the provided project folder (`ChatDev` folder containing `absence.java`, `student.java`, etc.) in your IDE as a Java project.
    *   **Configure Project SDK**: Ensure your project is configured to use JDK 21. Go to `File > Project Structure > Project` and set the `Project SDK` to your JDK 21 installation.
    *   **Add JavaFX SDK to Libraries**:
        *   Go to `File > Project Structure > Libraries`.
        *   Click the `+` button, select `Java`.
        *   Navigate to your `PATH_TO_FX/lib` directory and select all the `.jar` files within it. Click `OK`.
        *   Confirm that these libraries are added to your module(s) (if applicable).
    *   **Configure Run Configuration**:
        *   Go to `Run > Edit Configurations...`.
        *   Find or create a new "Application" configuration for `AbsenceManagerApp`.
        *   In the `VM options` field, add the following (replace `PATH_TO_FX` with your actual path):
            ```
            --module-path PATH_TO_FX/lib --add-modules javafx.controls,javafx.fxml
            ```
        *   Ensure `Main class` is set to `AbsenceManagerApp`.

## ▶️ How to Use/Play

### Running the Application

**Using an IDE (Recommended):**

1.  After completing the IDE setup steps above, navigate to the `AbsenceManagerApp.java` file in your project explorer.
2.  Right-click on `AbsenceManagerApp.java` and select `Run 'AbsenceManagerApp.main()'`.
3.  The main Administrator Dashboard window should appear.

**From the Command Line (Advanced):**

Assuming your project structure keeps `src/` for Java files and `resources/` for FXML files:

1.  **Compile**:
    Open your terminal/command prompt in the root directory of your project where `src` and `resources` folders are located.
    ```bash
    javac --module-path "PATH_TO_FX/lib" --add-modules javafx.controls,javafx.fxml -d out src/module/Absence.java src/module/Student.java src/module/Parent.java src/service/SMOSServerConnector.java src/service/EmailSender.java src/service/AbsenceService.java src/controller/AbsenceDialogController.java src/controller/AdministratorDashboardController.java src/AbsenceManagerApp.java
    ```
    This command is quite long and assumes a very specific direct compilation. It's usually better to compile all `.java` files and put FXML in the classpath.
    A more robust compilation and packaging might involve a build tool like Maven or Gradle. For this direct runnable setup:

    *   Create `out` directory: `mkdir out`
    *   Copy FXML files to `out` directory: `cp resources/*.fxml out/`
    *   Compile all `.java` files, placing `.class` files in `out`:
        ```bash
        javac --module-path "PATH_TO_FX/lib" --add-modules javafx.controls,javafx.fxml -d out $(find src -name "*.java")
        ```

2.  **Run**:
    ```bash
    java --module-path "PATH_TO_FX/lib" --add-modules javafx.controls,javafx.fxml -cp out AbsenceManagerApp
    ```
    The application window should launch.

### Application Walkthrough

1.  **Initial Load**: When the application starts, it will default to showing today's date and any dummy absences predefined in the `AbsenceService` for that date. You'll see a status message like "Absences loaded for YYYY-MM-DD."
    *   *Dummy Data*: Pre-loaded data includes several students and parents, and some absences for yesterday and today (relative to when you run the app).

2.  **Selecting a Date**:
    *   Click on the `DatePicker` control in the top-left to open a calendar.
    *   Select a different date (e.g., yesterday or tomorrow, or any other date).
    *   The `TableView` will update to show absences for the newly selected date. If there are no absences, the table will be empty.

3.  **Adding an Absence**:
    *   Ensure a date is selected in the `DatePicker`.
    *   Click the "Add Absence" button.
    *   A dialog will appear:
        *   **Student ID**: Try entering `1` for "John Doe" or `3` for "Mike Johnson". If you enter an invalid ID (e.g., `999`), an error message will display.
        *   **Date**: This will be pre-filled with your selected date.
        *   **Reason**: Enter a reason (e.g., "Field Trip", "Dental Appointment").
        *   Click "OK". If validation passes, the new absence will appear in the table with `NEW` status (green text).
    *   Notice the "Unsaved Changes" label appears, and the "Save Changes" button becomes active.

4.  **Deleting an Absence**:
    *   Select an absence record in the table by clicking on it.
    *   Click the "Delete Selected" button.
    *   **For a `NEW` absence**: It will be removed from the table immediately.
    *   **For an `EXISTING` absence**: It will remain in the table but its status will change to `DELETED` (red text, strikethrough).
    *   Again, the "Unsaved Changes" label and "Save Changes" button will reflect this modification.

5.  **Saving Changes**:
    *   Once you have pending changes (additions or deletions), click the "Save Changes" button.
    *   The status message will indicate "Saving changes... Please wait." The button will disable temporarily.
    *   The console will show output from `SMOSServerConnector` (simulating server communication) and `EmailSender` (simulating sending emails).
    *   If successful, the status of `NEW` items will change to `EXISTING`, `DELETED` items will be removed, and the "Unsaved Changes" label will disappear.
    *   *Simulated Errors*: Both SMOS server connection and email sending have a small chance of failing. If a `SMOSServerConnectionException` occurs, an error message will appear, and the changes will *not* be saved to the application's internal state. If an `EmailSendingException` occurs, a warning will appear, but changes will likely still be saved (simulating a scenario where data is updated but notifications fail).

6.  **Cancelling Changes**:
    *   Make some changes (add or delete).
    *   Click the "Cancel" button.
    *   A confirmation dialog will appear. Click "OK" to confirm.
    *   All pending changes for the current date will be reverted, and the table will return to its last saved state for that date.

Enjoy using the Absence Management System!