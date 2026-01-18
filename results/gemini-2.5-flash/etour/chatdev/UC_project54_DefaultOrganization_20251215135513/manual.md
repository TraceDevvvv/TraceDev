```
# ETour Comment Editor

A desktop application designed to simulate the process of editing tourist feedback comments for sites managed by an ETOUR server. This tool demonstrates a typical "Edit Comment" (MODIFICACOMMENTO) use case, including user interaction, data validation, and simulated server communication with error handling.

## 🤔 What is this?

The ETour Comment Editor is a Java Swing-based application that provides a user interface for modifying pre-existing comments made by tourists about various sites. It features a simulated backend server (`ETourServer`) to manage site and feedback data, allowing for a fully runnable demonstration of the comment editing workflow. This includes functionalities like loading a specific feedback, entering a new comment, confirming changes, and handling potential issues like invalid input or simulated server connection interruptions.

## 📖 Main Functions

The application's core functionality revolves around the "MODIFICACOMMENTO" use case:

*   **View Original Feedback**: Upon startup (or loading a specific feedback), the application displays the details of a tourist site and the original comment associated with a particular feedback entry.
*   **Enter Edit Mode**: Users can click the "Edit Comment" button to enable the input area for modifications.
*   **Modify Comment**: The user can type their desired new comment into the provided text area.
*   **Validate Input**: Before confirming, the system validates the new comment. For example, it checks if the comment is empty or if it falls within specific length constraints (e.g., minimum 10 characters, maximum 500 characters). Invalid data triggers an error message, preventing the update.
*   **Confirm Change**: After entering a valid comment, the user clicks "Confirm Change". A confirmation dialog appears, allowing the user to review their changes before proceeding.
*   **Update Comment on Server**: Upon confirmation, the application attempts to update the comment on the simulated `ETourServer`.
*   **Notification of Alterations**: The system notifies the user of the success or failure of the comment update.
*   **Cancel Editing**: Users can click "Cancel" at any time during the editing process to discard their changes and revert to the original comment.
*   **Simulated Server Errors**: The `ETourServer` includes a simulation for server connection interruptions, demonstrating how the application handles such edge cases by notifying the user.

## 🛠️ Installation and Setup

This project is developed in Java and uses standard Java SE libraries (Swing for GUI). No external dependencies like Maven or Gradle are required, making the setup straightforward.

### Prerequisites

*   **Java Development Kit (JDK)**: You need JDK 8 or a newer version installed on your system. You can download it from Oracle's website or use OpenJDK.

    To check if Java is installed and its version, open a terminal or command prompt and type:
    ```bash
    java -version
    javac -version
    ```
    If these commands return version information, you're good to go.

### Steps to Run

1.  **Save the Code**: Ensure all the provided `.java` files (`MainApp.java`, `CommentEditorPanel.java`, `ETourServer.java`, `Site.java`, `Feedback.java`) are saved in the same directory.

2.  **Open Terminal/Command Prompt**: Navigate to the directory where you saved the `.java` files using your terminal or command prompt.

    ```bash
    cd /path/to/your/java/files
    ```
    (Replace `/path/to/your/java/files` with the actual path.)

3.  **Compile the Code**: Compile all the Java source files.

    ```bash
    javac *.java
    ```
    If there are no compilation errors, this command will generate `.class` files for each `.java` file in the same directory.

4.  **Run the Application**: Execute the main application class.

    ```bash
    java MainApp
    ```

## 🎮 How to Use the ETour Comment Editor

Once you've run the `MainApp`, a GUI window titled "ETour Comment Editor" will appear.

1.  **Initial View (Feedback Loaded)**:
    *   The application automatically loads a sample feedback (Feedback ID 1, for Eiffel Tower by Alice).
    *   You will see "Site: Eiffel Tower | Tourist: Alice" and "Original Comment: Absolutely breathtaking! A must-visit." displayed.
    *   The "Enter New Comment:" text area will be pre-filled with the original comment.
    *   Buttons: "Edit Comment" (enabled), "Confirm Change" (disabled), "Cancel" (disabled).

2.  **Entering Edit Mode**:
    *   Click the **"Edit Comment"** button.
    *   A message dialog "Now you can edit the comment..." will appear.
    *   The "Enter New Comment:" text area will become enabled, allowing you to type.
    *   Buttons: "Edit Comment" (disabled), "Confirm Change" (enabled), "Cancel" (enabled).

3.  **Modifying the Comment**:
    *   Type your desired new comment into the enabled "Enter New Comment:" text area.
    *   **Try entering an invalid comment first**:
        *   Clear the text area and leave it empty.
        *   Type a very short comment (e.g., "Good").
        *   Type a very long comment (over 500 characters, just type a lot of gibberish).

4.  **Confirming Changes**:
    *   After typing a valid new comment (e.g., "The Eiffel Tower is truly a majestic landmark that offers unparalleled views of Paris. Absolutely loved every moment of it! Highly recommend visiting during sunset."), click the **"Confirm Change"** button.
    *   A confirmation dialog will appear, asking if you are sure you want to change the comment to your new text.
    *   Click **"Yes"** to proceed or "No" to go back to editing.

5.  **Handling Success and Errors**:
    *   If you confirmed and the update was successful (most likely scenario):
        *   You'll see a "Comment successfully updated!" message.
        *   The panel will refresh, displaying the new comment as the "Original Comment".
        *   The controls will revert to the initial view state (text area disabled, "Edit Comment" enabled).
    *   **Simulated Connection Error**: The `ETourServer` has a 10% chance to simulate a connection interruption during the update. If this happens:
        *   You'll see an "Connection Error" message box.
        *   The application will revert to edit mode, allowing you to try again.
    *   **Validation Errors**: If you tried to confirm an empty, too short, or too long comment, an "Invalid Input" error message will appear, and you will remain in edit mode to correct your input.

6.  **Cancelling Changes**:
    *   If you are in edit mode and decide not to save your changes, click the **"Cancel"** button.
    *   A message "Comment editing cancelled. No changes were made." will appear.
    *   The text area will revert to the comment it contained when you entered edit mode, and the controls will go back to the initial view state.

This application provides a comprehensive simulation of the comment editing process, allowing you to interact with each part of the use case.
```