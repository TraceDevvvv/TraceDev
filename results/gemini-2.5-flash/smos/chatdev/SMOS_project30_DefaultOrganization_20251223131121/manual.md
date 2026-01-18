```
# Administrator Delay Registration System Manual

## Introduction

Welcome to the Administrator Delay Registration System! This application empowers school administrators to efficiently register student delays for a specific date, manage the data entry process, and ensure communication with parents. Built with Java, this system provides a graphical user interface (GUI) for easy interaction and simulates backend server operations for a realistic experience.

### Main Functions

The system provides the following key functionalities:

*   **Date-Specific Registration**: Administrators can register student delays for a pre-selected date, which is displayed prominently on the interface.
*   **Student Delay Entry**: A user-friendly text area allows administrators to input student identifiers (e.g., names or IDs) for those who are delayed, with each student listed on a new line.
*   **Data Saving and Server Interaction**: Upon saving, the application simulates sending the entered delay data to a backend "SMOS" server. This includes simulating network latency and potential server errors.
*   **Parent Notification Simulation**: After successful data entry, the system simulates sending notifications to the parents of the delayed students.
*   **System Activity Logging**: A real-time log area displays all system actions, server communications, and any encountered messages or errors, providing transparency and feedback to the administrator.
*   **Operation Interruption**: Administrators have the option to interrupt the ongoing operation, which simulates terminating the connection to the SMOS server. This feature allows for handling unexpected situations or canceling an in-progress registration.

## Environment Setup and Installation

This application is written entirely in Java and does not require any external third-party libraries beyond what's included in a standard Java Development Kit (JDK).

### Dependencies

*   **Java Development Kit (JDK)**: You will need a JDK installed on your system. Version 11 or newer is recommended.
    *   You can download OpenJDK from Adoptium (Eclipse Temurin) or various other providers.
    *   Ensure that `JAVA_HOME` is set and the `java` and `javac` commands are accessible from your system's PATH.

### Installation Steps

1.  **Save the Source Files**: Save the provided `.java` files (`Main.java`, `AdminDelayRegistrationApp.java`, `ServerSimulator.java`, `ServerConnectionException.java`) into a single directory (e.g., `delay_registration`).

2.  **Open a Terminal/Command Prompt**: Navigate to the directory where you saved the `.java` files using your terminal or command prompt.

3.  **Compile the Java Code**: Use the Java compiler (`javac`) to compile all the source files.
    ```bash
    javac *.java
    ```
    If compilation is successful, `.class` files will be generated in the same directory for each `.java` file. If there are any errors, ensure your JDK is correctly installed and configured.

4.  **Run the Application**: Execute the `Main` class using the Java Virtual Machine (`java`).
    ```bash
    java Main
    ```
    This command will launch the Administrator Delay Registration GUI application.

## How to Use the Application

Once the application is running, you will see a window titled "Delay Registration for YYYY-MM-DD" (with the current date).

### User Interface Overview

The application window is divided into several areas:

1.  **Date Display**: At the top, you'll see the specific date for which you are registering delays. This date is automatically set to the current date when the application starts.
2.  **Student Input Area**: This is a large text area labeled "Enter Student IDs/Names with Delay (one per line)". Here, you will type in the identifiers for delayed students.
3.  **Buttons**:
    *   **Save Delay Data**: Click this to submit the entered student delay information to the simulated server.
    *   **Cancel Operation**: Click this to interrupt the current operation, which simulates disconnecting from the SMOS server.
4.  **System Log**: A read-only text area labeled "System Log" at the bottom displays all application activities, server communications, and status updates.

### Step-by-Step Usage Guide

#### 1. Registering Student Delays

1.  **Verify the Date**: Ensure the date displayed at the top of the window is the one for which you intend to register delays. (In this simulation, it defaults to the current date).
2.  **Enter Student Information**:
    *   Click inside the "Enter Student IDs/Names with Delay" text area.
    *   Type the identifier (name or ID) of each delayed student.
    *   **Important**: Press `Enter` after each student to place them on a new line. Each non-empty line will be treated as a separate student entry.
    *   *Example:*
        ```
        John Doe (ID: 101)
        Jane Smith (ID: 102)
        Alice Johnson
        ```
3.  **Save the Data**: Once you have entered all delayed students, click the **"Save Delay Data"** button.
    *   The input area, save button, and cancel button will temporarily disable while the data is being processed to prevent multiple submissions.
    *   Observe the "System Log" area:
        *   You will see messages indicating the data being sent to the server.
        *   A simulated delay (`SIMULATED_LATENCY_MS`) will occur.
        *   If successful, you'll see messages about notifications being sent to parents.
        *   The input area will clear, and controls will re-enable, ready for new entries.
    *   **Simulated Errors**: The `ServerSimulator` includes a small chance of simulated server errors (10% for data saving, 5% for notifications). If an error occurs, you will see corresponding error messages in the log, and the input area will *not* clear, allowing you to retry.

#### 2. Interrupting an Operation

1.  At any point *before* data submission completes (e.g., while entering data, or right after clicking "Save" but before completion messages appear), you can click the **"Cancel Operation"** button.
2.  Observe the "System Log" area:
    *   Messages will indicate that the administrator initiated an interruption.
    *   The system will attempt to "interrupt" the connection to the simulated SMOS server.
    *   If successful, the log will confirm the connection interruption.
    *   **Post-Interruption**: The "Save Delay Data" button and the "Student Input Area" will be permanently disabled, as the simulated server connection is now down. The "Cancel Operation" button will also disable itself as no further interruption is possible in this state. The log will confirm that further data entry or saving will not be possible for this session.
    *   **Simulated Interruption Errors**: If there's an issue during the simulated interruption process (e.g., if you try to interrupt an already interrupted connection in a way that the simulator doesn't fully handle), logs will reflect this.

#### 3. Analyzing the System Log

The "System Log" is crucial for understanding the application's behavior.
*   Monitor it for confirmation of actions (data saved, notifications sent).
*   Pay attention to error messages (simulated server errors, connection interruptions) to troubleshoot or understand why an action failed.
*   Each log entry is prefixed with `[APP]` for clarity.

**Example Log Flow (Successful Save):**

```
[APP] Application started. Ready to register delays for 2023-10-27.
[APP] Attempting to save delay data...
[APP]   - Sending delay data to server...
ServerSimulator: Received delay data for 2023-10-27: 2 students.
ServerSimulator: Delay data processed successfully.
[APP]   - Delay data sent successfully. Initiating parent notifications...
ServerSimulator: Sending notifications for 2 parents.
ServerSimulator: Notifications sent successfully.
[APP]   - Notifications sent to parents successfully.
[APP] Delay data entry complete for 2 students.
```

**Example Log Flow (Interruption):**

```
[APP] Application started. Ready to register delays for 2023-10-27.
[APP] Administrator initiated operation interruption.
[APP]   - Attempting to interrupt connection to SMOS server...
ServerSimulator: Simulating connection interruption to SMOS server.
ServerSimulator: SMOS server connection has been interrupted.
[APP] SMOS Server connection successfully terminated.
[APP] Operation interrupted. SMOS Server connection successfully terminated. Further data entry or saving will not be possible for this session.
```

## Troubleshooting

*   **Application Not Starting**: Ensure you have successfully compiled all `.java` files without errors (`javac *.java`) and are running `java Main` from the correct directory. Verify your JDK installation.
*   **Server Errors/Connection Issues**: These are often simulated within the `ServerSimulator`. Check the "System Log" for specific messages like "Simulated server error" or "Connection to SMOS server interrupted." This is expected behavior for testing edge cases.
*   **UI Frozen**: During "Save" or "Cancel" operations, the UI controls are temporarily disabled to prevent concurrent actions. Wait for the operation to complete or for an error message in the log. If the UI remains unresponsive indefinitely, there might be a rare unhandled exception (check your terminal for Java stack traces).
*   **Input Not Clearing**: If a "Save" operation fails (due to a simulated server error), the input area will not clear, allowing the administrator to correct the input or retry.

## Copyright and License

ChatDev - Changing the digital world through programming.
All rights reserved.
```