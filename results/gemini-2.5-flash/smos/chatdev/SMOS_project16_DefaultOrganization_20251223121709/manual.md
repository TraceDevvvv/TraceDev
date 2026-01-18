```
# Administrator - Class Management Application

This manual provides an overview of the "Administrator - Class Management" application, including its core functionalities, setup instructions, and how to use it.

## 🤔 What is this?

The "Administrator - Class Management" is a Java-based desktop application designed for administrators to manage an archive of classes. It provides a graphical user interface (GUI) built with Swing, allowing users to view a list of classes, inspect their details, and perform deletion operations. The application simulates database interactions and server disconnections as per the given use case.

## 📖 Main Functions

The application offers the following key functionalities:

*   **View Class List:** Upon launching, the application displays a comprehensive list of all classes present in
    the archive within a sortable table. This list includes the Class ID and Name.
*   **View Class Details:** By selecting a class from the list, an administrator can view its detailed information,
    including the Class ID, Name, and Description. This simulates the `viewdettagliSlasse` (view class details)
    use case.
*   **Delete Class:** Administrators can select a specific class from the list and initiate its deletion from the archive.
    A confirmation prompt ensures that accidental deletions are minimized. After successful deletion, the class list is
    automatically updated.
*   **Responsive UI:** Long-running operations, such as fetching all classes or performing a deletion, are executed in a
    background thread using `SwingWorker`. This design choice ensures that the application's user interface remains
    responsive and does not freeze during these operations, providing a smoother user experience.
*   **Simulated SMOS Disconnect:** As a postcondition of the delete operation, the application simulates an "interrupted
    connection to the SMOS server" by printing a message to the console. In a real-world scenario, this might involve
    releasing system resources or logging events.

## 📦 Installation & Dependencies

This application is built entirely using standard Java features and Swing components. There are no external third-party libraries required.

*   **Java Development Kit (JDK):** To compile and run this application, you need a Java Development Kit (JDK) installed on
    your system. It is recommended to use JDK 8 or higher.

To check if Java is installed and configured correctly, open a terminal or command prompt and run:

```bash
java -version
javac -version
```

If these commands return version information, your environment is ready.

## 🚀 How to Use/Play

Follow these steps to compile and run the "Administrator - Class Management" application:

1.  **Save Source Files:**
    Save all provided Java source files (`Main.java`, `ClassModel.java`, `ClassService.java`, `AdminDashboard.java`)
    into a single directory on your local machine (e.g., `~/admin_app/src`).

2.  **Compile the Application:**
    *   Open your terminal or command prompt.
    *   Navigate to the directory where you saved your source files.
    *   Compile the Java sources using the `javac` command:
        ```bash
        javac Main.java ClassModel.java ClassService.java AdminDashboard.java
        ```
        Alternatively, if all `.java` files are in the same directory, you can compile them all at once:
        ```bash
        javac *.java
        ```
    *   Review the output for any compilation errors. If successful, `.class` files will be generated in the same directory.

3.  **Run the Application:**
    *   From the same terminal or command prompt, execute the main class:
        ```bash
        java Main
        ```
    *   The "Administrator - Class Management" GUI window should appear.

### Interacting with the GUI:

*   **Initial View:** Upon launch, the application will display a list of sample classes in the left-hand table. The status bar at the bottom will indicate "Loading classes..." followed by "X classes loaded. Select a class to delete."
*   **Viewing Class Details:**
    *   Click on any row in the "Class List" table.
    *   The "Selected Class Details" area on the right will update, showing the ID, Name, and Description of the chosen class.
    *   The "Delete Selected Class" button will become enabled.
*   **Deleting a Class:**
    *   Select the class you wish to delete from the table.
    *   Click the "Delete Selected Class" button.
    *   A confirmation dialog titled "Confirm Deletion" will appear, asking you to confirm your action.
    *   Click "Yes" to proceed with the deletion or "No" to cancel.
    *   If you click "Yes":
        *   A "Deletion Successful" message will appear.
        *   The class will be removed from the table, and the list will refresh.
        *   The system's console (where you ran the `java Main` command) will display the message "SMOS Server: Connection interrupted after delete operation."
        *   The "Selected Class Details" area will update to reflect that the class has been deleted.
*   **Status Bar:** Pay attention to the status bar at the bottom of the window for real-time feedback on operations (e.g., "Loading classes...", "Class ID: ... deleted.", "Deletion failed...").
*   **Responsiveness:** Observe how the application remains responsive even during "long" operations (sim_delays are introduced in `ClassService.java` for demonstration purposes). You can still move or resize the window while an operation is in progress.
*   **Exit:** To close the application, simply click the 'X' button on the window's title bar.
```