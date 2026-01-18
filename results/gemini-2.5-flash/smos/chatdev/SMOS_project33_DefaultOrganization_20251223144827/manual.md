```
# Administrator Delay Management System User Manual

## 📖 Introduction

The Administrator Delay Management System is a Java-based application designed to assist administrators in managing and eliminating student delay records. This system allows authorized users to log in, view student delays on specific dates, mark individual delay entries for removal, and permanently delete them from a simulated archive. It provides a straightforward graphical user interface (GUI) to streamline the process of record management.

## ✨ Main Functions

This application provides the following key functionalities for administrators:

*   **Administrator Login**: Secure access to the system using pre-defined administrator credentials. Only authenticated users can proceed to manage delays.
*   **Select Date**: Allows the administrator to choose a specific date from a dropdown list to view all recorded student delays for that day. The system automatically updates the display based on the selection.
*   **View Delays**: Displays a table listing all student delays for the currently selected date, including student name, delay ID, date, and reason.
*   **Remove Selected Delay**: Enables the administrator to mark a specific delay entry (by selecting its row in the table) for deletion. This action removes the entry from the *current view* but does not permanently delete it from the archive until changes are saved.
*   **Save Changes**: Commits all identified and marked delay entries for permanent removal from the system's archive. This action requires a simulated connection to an external "SMOS server."
*   **Simulate SMOS Server Connection**: A toggle switch (`JCheckBox`) that allows simulating the connection status to an external SMOS server. If the server connection is simulated as interrupted, deletion operations cannot be saved.
*   **Logout (Interrupt Operation)**: Allows the administrator to log out of the system. This action also explicitly simulates the interruption of the SMOS server connection, reflecting a postcondition where an administrator interrupts the operation.

## 🛠️ Installation and Environment Setup

To run this application, you need to have a Java Development Kit (JDK) installed on your system.

### Dependencies

*   **Java Development Kit (JDK)**: Version 8 or newer is recommended (the application uses `java.time` classes).

### Compilation and Execution

1.  **Save the files**: Ensure all provided Java files (`LoginScreen.java`, `Delay.java`, `DelayArchive.java`, `AdminDelayManagementApp.java`) are saved in the same directory.
2.  **Compile**: Open a terminal or command prompt, navigate to the directory where you saved the files, and compile them using the Java compiler:
    ```bash
    javac *.java
    ```
    If compilation is successful, `.class` files will be generated for each `.java` file.
3.  **Run the application**: Execute the main `LoginScreen` class:
    ```bash
    java LoginScreen
    ```

### Using an Integrated Development Environment (IDE)

For easier development and execution, you can use an IDE like IntelliJ IDEA, Eclipse, or VS Code with Java extensions:

1.  **Create a new Java project**: In your IDE, create a new Java project (e.g., a "Java Application" or "Simple Java Project").
2.  **Add source files**: Copy all the `.java` files into the `src` folder (or equivalent source folder) of your project.
3.  **Run `LoginScreen.java`**: Locate `LoginScreen.java` in your IDE's project explorer, right-click, and select "Run as Java Application" or similar option. The IDE will automatically compile and run the application.

## ▶️ How to Use the Application

Follow these steps to interact with the Administrator Delay Management System:

### Step 1: Launch the Application

1.  After compiling and running, a "Administrator Login" window will appear.

### Step 2: Log In

1.  **Enter Credentials**:
    *   **Username**: `admin`
    *   **Password**: `admin123`
2.  **Click "Login"**: A successful login will close the login screen and open the "Administrator Delay Management" window. An incorrect login will display an error message.

### Step 3: Select a Date

1.  In the "Administrator Delay Management" window, you will see a "Select Date" panel at the top.
2.  **Choose a Date**: Use the dropdown menu labeled "Date:" to select a date for which you want to view delays. The system comes pre-populated with some sample data.
3.  **Click "Display Delays"**: This will populate the main table with all student delays recorded for the selected date. The status bar at the bottom will confirm the displayed date.

### Step 4: View Delays

1.  The central part of the application window displays a table with columns: "ID", "Student Name", "Date", and "Reason".
2.  All delays associated with the selected date will be listed here.

### Step 5: Mark a Delay for Removal

1.  **Select a Delay**: Click on any row in the delay table to select a specific delay entry you wish to eliminate.
2.  **Click "Remove Selected Delay"**: The selected row will disappear from the table. The status bar will update to indicate that the delay has been "marked for removal."
    *   **Note**: At this stage, the delay is only removed from the *displayed list* and is *not* permanently deleted from the system archive. You can mark multiple delays for removal this way.

### Step 6: Confirm Deletion (Save Changes)

1.  After marking one or more delays for removal, click the "Save Changes" button.
2.  **SMOS Server Connection Check**:
    *   If the "SMOS Server Connected (Simulated)" checkbox is *checked*, the marked delays will be permanently deleted from the archive. A success message will appear, and the table and date selector will refresh to reflect the changes. The status bar will confirm the deletion.
    *   If the "SMOS Server Connected (Simulated)" checkbox is *unchecked* (simulating an interrupted connection), an error message ("Connection to the SMOS server interrupted. Cannot save changes.") will appear, and the deletions will *not* be saved.
3.  **Postconditions**: Upon successful deletion, the system eliminates the delay, and the application remains on the registry screen. If a date no longer has any delays after deletion, the system will adjust the display accordingly (e.g., select another date or show no delays).

### Step 7: Simulate Connection Interruption

1.  You can toggle the "SMOS Server Connected (Simulated)" checkbox at any time.
2.  **Unchecking** it simulates an interruption in the connection to the external SMOS server. This will prevent you from saving any changes.
3.  **Checking** it restores the simulated connection, allowing "Save Changes" functionality to proceed.
4.  The status bar will reflect the current simulated connection status.

### Step 8: Interrupt Operation / Logout

1.  To log out of the system, click the "Logout (Interrupt)" button in the right panel.
2.  This action simulates the administrator interrupting the operation. As a consequence, the "SMOS Server Connected (Simulated)" status will automatically be set to *unchecked* (interrupted).
3.  You will be returned to the "Administrator Login" screen.

## ⚠️ Edge Cases and Important Notes

*   **No Delays for Selected Date**: If you select a date with no recorded delays, the table will be empty, and the status bar will indicate "No delays found for [Date]."
*   **No Date Selected**: If the date selector is empty or no date is initially selected, the table will remain empty.
*   **"Remove Selected Delay" without Selection**: If you click "Remove Selected Delay" without selecting a row, a warning message will appear.
*   **`DelayArchive` Sample Data**: The `DelayArchive` class is pre-populated with sample delay data for demonstration purposes. This data is in-memory and will reset each time the application is restarted.
*   **Postcondition: Administrator Interruption**: The `Logout (Interrupt)` button directly implements the postcondition where the administrator interrupts the operation, and the SMOS server connection is considered interrupted.