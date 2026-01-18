```
# Admin Delay Editor

The Admin Delay Editor is a desktop application designed for administrators to manage and update delay settings within a system for specific dates. This tool provides a user-friendly interface to view existing delays, modify them, and save the changes back to the system. It simulates interaction with a backend service, including network delays and potential connection interruptions.

## 🤔 What does it do?

Upon launching the application, an administrator can:

1.  **Specify a Date**: Enter a date in `YYYY-MM-DD` format to view or edit its associated delay.
2.  **View Delays**: After selecting a date, the system fetches and displays the current delay value (in milliseconds) for that date. If no delay is found, it will default to 0.
3.  **Edit Delays**: Modify the displayed delay value as needed. The delay must be a non-negative integer.
4.  **Save Changes**: Submit the edited delay value to be updated in the system.
5.  **Monitor Status**: Receive real-time feedback through a status message about the progress and outcome of operations (fetching, saving, or errors).
6.  **Cancel Operations**: Interrupt any ongoing fetch or save operations if necessary, preventing the UI from freezing during simulated network delays or system unresponsiveness.

This application is crucial for administrative tasks that involve configuring time-sensitive parameters, such as scheduling adjustments, processing throttles, or system task delays based on specific calendar days.

## 📖 Installation and Dependencies

This project is a pure Java application developed using Swing for its graphical user interface.

### Prerequisites

*   **Java Development Kit (JDK)**: You need Java 8 or a newer version installed on your system. This project utilizes features like `java.time` APIs introduced in Java 8.
    *   You can download the latest JDK from Oracle's website or use an open-source distribution like OpenJDK.
    *   Verify your Java installation by running `java -version` and `javac -version` in your terminal or command prompt.

### Compilation and Execution

Since this application uses only standard JDK libraries, it does not require any external package managers or dependency management tools (like Maven or Gradle) beyond the JDK itself.

1.  **Save the files**: Ensure all provided `.java` files (`AdminDelayEditorApp.java`, `DelayService.java`, `EditDelayPanel.java`, including `DelayServiceException` within `DelayService.java`) are saved in the same directory.

2.  **Compile the source code**: Open a terminal or command prompt, navigate to the directory where you saved the `.java` files, and compile them using the `javac` command:

    ```bash
    javac AdminDelayEditorApp.java DelayService.java EditDelayPanel.java
    ```

    *If `DelayServiceException` is defined in its own `.java` file:*
    ```bash
    javac AdminDelayEditorApp.java DelayService.java DelayServiceException.java EditDelayPanel.java
    ```
    *(Note: In the provided code, `DelayServiceException` is an inner class at the bottom of `DelayService.java`, so the first command should suffice.)*

3.  **Run the application**: After successful compilation, run the main application class `AdminDelayEditorApp` using the `java` command:

    ```bash
    java AdminDelayEditorApp
    ```

    A new window titled "Admin Delay Editor" should appear, which is the application's GUI.

## 🚀 How to Use

Follow these steps to effectively use the Admin Delay Editor:

### 1. Launch the Application

*   Execute the `java AdminDelayEditorApp` command as instructed in the "Installation and Dependencies" section.
*   The application window will open. Initially, it will attempt to load the delay for today's date. A status message like "Fetching delay for YYYY-MM-DD..." will appear at the bottom.

### 2. Select a Date

*   **Input the Date**: In the "Date (yyyy-MM-dd):" field, enter the date for which you want to view or change the delay. Use the `YYYY-MM-DD` format (e.g., `2023-10-27`).
*   **Click "Select Date"**: After typing the date, click the "Select Date" button.
*   **System Response**:
    *   The status message will update to "Fetching delay for [Your Date]...".
    *   Once fetched, the "Delay (milliseconds):" field will populate with the delay value for that date (or `0` if no delay was previously set).
    *   The status message will change to "Delay loaded. Edit and Save."

### 3. Edit the Delay Value

*   **Modify Delay**: In the "Delay (milliseconds):" field, you can now enter a new delay value. This value must be an integer and non-negative.
*   **Validation**: If you enter non-numeric characters or a negative number, the system will not allow you to save and will display an error message.

### 4. Save the New Delay

*   **Click "Save Delay"**: After making your changes, click the "Save Delay" button.
*   **System Response**:
    *   The status message will update to "Saving delay for [Your Date]...".
    *   Upon successful saving, the status message will read "Delay for [Your Date] updated successfully!".
    *   If a simulated connection interruption or an invalid input occurs, an error message will be displayed in red (e.g., "Error: Connection to SMOS server interrupted during save.").

### 5. Cancel an Operation

*   **During Fetch/Save**: When the application is actively fetching or saving data (and the status label indicates "Fetching..." or "Saving..."), a "Cancel" button will become visible and enabled.
*   **Click "Cancel"**: Click this button to interrupt the ongoing operation. This is useful if the operation appears to be taking too long due to simulated network delays, or if you change your mind.
*   **System Response**:
    *   The status message will update to "Cancellation requested...".
    *   Once the cancellation is processed by the background task, the status will show "Operation cancelled by administrator.", and the UI will reset its state, potentially clearing the delay input and disabling relevant buttons.

### 6. Understanding the Status Label

*   The text at the bottom of the window is the **Status Label**.
*   It provides feedback on the current operation (e.g., fetching, saving, loading).
*   **Blue text** indicates an informational or success message.
*   **Red text** indicates an error or an issue that requires attention.

### Example Workflow:

1.  Launch the app. Today's date delay (e.g., `2023-10-27`) is loaded, say `500`.
2.  Type `2023-11-15` into the date field.
3.  Click "Select Date". Status changes to "Fetching delay for 2023-11-15...".
4.  If a delay exists for `2023-11-15` (e.g., `1000`), it appears in the Delay field. Status: "Delay loaded. Edit and Save."
5.  Change the delay value to `750`.
6.  Click "Save Delay". Status changes to "Saving delay for 2023-11-15...".
7.  Upon success, Status: "Delay for 2023-11-15 updated successfully!".
8.  Now, try to select an invalid date like `2023-13-01`. Status: "Error: Invalid date format. Please use yyyy-MM-dd." and input fields reset.