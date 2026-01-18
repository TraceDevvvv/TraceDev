```
# ETOUR Tourist Data Modification Application

This document provides a user manual for the ETOUR Tourist Data Modification Application, outlining its functionalities, environment setup, and instructions on how to use it.

## 🚀 Introduction & Main Functions

The ETOUR Tourist Data Modification Application is a demonstration of a system allowing authenticated tourists to manage their personal account data. It simulates a typical user flow for updating profile information within an online service.

The application's core functionalities include:

*   **User Authentication**: Ensures that only legitimate users can access their data.
*   **Data Retrieval**: Loads and displays the specific tourist's current account details.
*   **Data Modification**: Provides a form for the user to edit their first name, last name, and email address.
*   **Input Validation**: Checks the modified data for correctness and sufficiency (e.g., non-empty fields, valid email format).
*   **Confirmation Step**: Asks the user for explicit confirmation before saving any changes.
*   **Data Persistence (Simulated)**: Stores the updated data in an in-memory "database" (for demonstration purposes).
*   **Error Handling**: Notifies the user of validation errors, data loading failures, and simulated connection interruptions.
*   **Connection Interruption Simulation**: Allows testing of network error scenarios which mimics an interruption to the ETOUR server.

This application directly implements the "MODIFICADATITURISTA" use case, covering the entire flow from initial access to data storage and managing various exit conditions.

## 🛠️ Environment Setup

To compile and run this Java application, you need to have a Java Development Kit (JDK) installed on your system.

### Dependencies:

*   **Java Development Kit (JDK) 11 or higher**: The application is written in standard Java and uses Swing for its graphical user interface. No external libraries beyond the standard JDK are required.

### Installation Steps:

1.  **Install Java Development Kit (JDK)**:
    *   If you don't have JDK installed, download and install it from Oracle's website or use an open-source distribution like OpenJDK. Ensure you have version 11 or newer.
    *   Verify your installation by opening a terminal or command prompt and typing:
        ```bash
        java -version
        javac -version
        ```
        You should see output indicating your Java version.

## 🎮 How to Use/Play

Follow these steps to compile, run, and interact with the ETOUR Tourist Data Modification Application.

### Step 1: Save the Source Code

Create a directory (e.g., `ETOUR_App`) and save the following Java files in it:

*   `Tourist.java`
*   `TouristService.java`
*   `AuthDialog.java`
*   `TouristDataModificationFrame.java`
*   `MainApp.java`

Ensure the `.java` files are saved exactly as provided, with their respective names and extensions.

### Step 2: Compile the Java Code

Open a terminal or command prompt, navigate to the directory where you saved the files (`ETOUR_App`), and compile the source code:

```bash
javac *.java
```

If compilation is successful, several `.class` files will be generated in the same directory.

### Step 3: Run the Application

From the same terminal or command prompt, run the `MainApp`:

```bash
java MainApp
```

This will launch the application's main window, followed immediately by the authentication dialog.

### Step 4: User Authentication

1.  Upon launching, a **"Login" dialog** will appear. This dialog simulates the "Tourism has successfully authenticated to the system" entry condition.
2.  **Enter Credentials**: You can use the following pre-defined credentials:
    *   **Username**: `user1`, **Password**: `password` (or any non-empty string)
    *   **Username**: `user2`, **Password**: `password` (or any non-empty string)
    *   *Note*: The password is not actually validated beyond checking if it's empty, due to the simplified nature of the `TouristService`.
3.  **Login**:
    *   Click the "Login" button.
    *   If successful, a "Login Success" message will appear, and the "Modify Tourist Data" frame will load.
    *   If authentication fails (e.g., wrong username or empty fields), an error message will be displayed, and you can retry.
4.  **Cancel**:
    *   Click the "Cancel" button on the login dialog, or close the dialog directly. This will terminate the application, as per the "Tourist cancels the operation" exit condition for authentication.

### Step 5: Modify Tourist Data

After successful authentication, the "Modify Tourist Data" frame will appear.

1.  **View Current Data**: The form will automatically load and display the authenticated user's current data (First Name, Last Name, Email). The Username is read-only.
2.  **Edit Fields**:
    *   Click on the `First Name`, `Last Name`, or `Email` text fields and modify their values.
3.  **Submit Changes**:
    *   Once you've made your desired changes, click the "Submit Changes" button.
    *   **Validation**: The system will first validate your input.
        *   If any fields are left empty or the email format is invalid, an "Validation Error" message will pop up, activating the "Errored" use case. You will need to correct the errors before proceeding.
    *   **Confirmation**: If validation passes, a "Confirm Data Modification" dialog will appear, showing the proposed changes and asking for your confirmation. This corresponds to the user confirming the transaction change.
        *   Click "Yes" to proceed with saving.
        *   Click "No" (or close the dialog) to cancel the submission.
    *   **Successful Update**: If confirmed and saved successfully, a "Success" message ("Data successfully updated!") will be displayed, and the form will refresh with the new data. This fulfills the "system shall notify the successful modification of data" exit condition.
4.  **Cancel Modification**:
    *   Click the "Cancel" button on the "Modify Tourist Data" frame, or close the window directly.
    *   This will display an "Modification operation cancelled." message and return you to the main application window (which in this case is just a placeholder `JFrame`), fulfilling the "Tourist cancels the operation" exit condition.

### Step 6: Test Connection Interruption (Quality Requirement / Edge Case)

The application includes a feature to simulate network errors:

1.  **Enable Simulation**: On the "Modify Tourist Data" frame, check the **"Simulate ETOUR Connection Interruption"** checkbox.
    *   A warning message will appear confirming that the simulation is ON.
2.  **Attempt Submission**: After enabling the simulation, try submitting changes to your data.
3.  **Observe Random Failures**: Due to the simulation, data updates (`getTouristData` and `updateTouristData` in `TouristService`) may randomly fail with a "Connection Error" message (e.g., "Data update failed: Connection to ETOUR server interrupted.").
    *   This demonstrates the "Interruption of the connection to the server ETOUR" exit condition.
4.  **Disable Simulation**: Uncheck the checkbox to stop simulating connection issues. Future submissions should then process normally (assuming valid input and confirmation).

This completes the user manual for the ETOUR Tourist Data Modification Application.
```