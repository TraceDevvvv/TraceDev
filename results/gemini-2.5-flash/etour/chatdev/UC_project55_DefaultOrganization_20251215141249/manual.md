```
# Feedback System Simulation (FEEDBACKGIA'RILASCIATO Use Case)

This document provides a user manual for the `FeedbackApp` Java application, which simulates a specific scenario in a feedback system: when a user attempts to submit feedback for a site for which feedback has already been provided.

## 🤔 What is this application for?

The `FeedbackApp` is a simple Java Swing application designed to demonstrate the "FEEDBACKGIA'RILASCIATO" (Feedback Already Issued) use case. In many systems, users should only be able to provide feedback for a particular item or site once. This application simulates a system that checks for pre-existing feedback. If feedback is found, it notifies the user, cancels the new submission attempt, and restores the application to its previous state.

It serves as a runnable example showcasing the interaction flow described in the use case:
*   **Entry Condition**: Feedback has already been issued for the selected site.
*   **Flow**:
    1.  The system notifies the user that feedback has already been issued and cancels the operation.
    2.  The user confirms reading the notification.
    3.  The system recovers its previous state, returning control to the user.

## 🚀 Main Functions

The application's core functionality revolves around simulating the feedback submission process under the specific "already issued" condition:

*   **Pre-check for Existing Feedback**: Internally, the application is pre-configured to simulate that feedback *has already been issued* for the `Site-A` (this is the entry condition for the use case).
*   **User Notification**: When the user attempts to provide feedback, a pop-up dialog (`JOptionPane`) informs them that feedback for the selected site has already been submitted.
*   **Operation Cancellation**: The attempt to submit new feedback is implicitly cancelled upon showing the notification.
*   **State Recovery**: After the user acknowledges the notification, the application returns to its idle state, ready for further user interaction. The main window remains open and interactive.

## 🛠️ Environment Dependencies

To compile and run this Java application, you will need:

*   **Java Development Kit (JDK)**: Version 8 or higher is required. You can download it from Oracle's website or use an open-source distribution like OpenJDK.
    *   You can check if Java is installed and its version by opening a terminal or command prompt and typing:
        ```bash
        java -version
        javac -version
        ```

This application uses standard Java libraries (`javax.swing`, `java.awt`, `java.awt.event`), so no additional external dependencies are needed.

## 🎮 How to Use/Play

Follow these steps to compile and run the `FeedbackApp`:

### Step 1: Save the Code

Save the provided Java code as `FeedbackApp.java` in a directory of your choice.

### Step 2: Compile the Application

Open a terminal or command prompt, navigate to the directory where you saved `FeedbackApp.java`, and compile the source code using the Java compiler:

```bash
javac FeedbackApp.java
```
If there are no errors, this command will create a `FeedbackApp.class` file in the same directory.

### Step 3: Run the Application

Execute the compiled Java application from the same terminal or command prompt:

```bash
java FeedbackApp
```

### Step 4: Interact with the Application

1.  **Welcome Screen**: A window titled "Feedback System Simulation" will appear. It will display a message like: "Click 'Provide Feedback' to simulate the process for Site-A."
2.  **Trigger Feedback Attempt**: Click the **"Provide Feedback for Site-A"** button.
3.  **Observe Notification**: A new dialog box will pop up with the title "Feedback Already Submitted". The message will state: "You have already provided feedback for Site-A. Cannot submit new feedback at this time. Operation cancelled."
4.  **Acknowledge and Recover**: Click the **"OK"** button on the notification dialog.
5.  **Previous State Restored**: Observe the status label in the main window. It will update to "Previous state recovered. Ready for further interaction.", indicating that the system has returned control to the user. You can click the button again to see the same notification.
6.  **Close Application**: Close the window by clicking the 'X' button in the title bar.

This simulation precisely follows the "FEEDBACKGIA'RILASCIATO" use case, demonstrating the system's behavior when a user attempts to provide redundant feedback.

---
**Note:** The application is hardcoded with `hasFeedbackAlreadyBeenIssued = true` to specifically fulfill the entry condition of this use case. In a real-world scenario, this flag would be determined dynamically (e.g., from a database query). If you were to change `hasFeedbackAlreadyBeenIssued` to `false` in the code, upon the first click, it would display a "Feedback Submitted Successfully" message, and then subsequent clicks would show the "Feedback Already Submitted" message (as the internal flag would be set to `true` after the first successful submission).
```