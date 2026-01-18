```
# Tourist Account Management Application

This documentation provides a guide for using the Tourist Account Management Application, which allows an Agency Operator to enable or disable tourist accounts.

## 🤔 What is this?

The Tourist Account Management Application is a Java-based desktop application designed for an Agency Operator to manage the activation and deactivation status of tourist accounts. It features a simple graphical user interface (GUI) to facilitate these operations, including confirmation steps and outcome notifications. The application also simulates potential external server connection interruptions to demonstrate robust error handling.

### Main Functions:

*   **View Tourist Accounts**: Displays a list of all registered tourist accounts with their IDs and names.
*   **Check Account Status**: Upon selecting a tourist, the application shows their current active/disabled status.
*   **Activate Account**: Allows an operator to change a disabled tourist account to an active status.
*   **Deactivate Account**: Allows an operator to change an active tourist account to a disabled status.
*   **Confirmation Prompt**: Requires explicit confirmation from the operator before executing an activation or deactivation.
*   **Outcome Notification**: Provides clear messages whether an operation was successful or failed.
*   **Simulated Error Handling**: Demonstrates how the system would react to an interruption in connection to an external 'ETOUR' server.

## 💻 Environment Dependencies

To compile and run this application, you will need:

*   **Java Development Kit (JDK)**: Version 8 or higher. You can download it from the [Oracle website](https://www.oracle.com/java/technologies/downloads/) or use an OpenJDK distribution like [Adoptium Temurin](https://adoptium.net/).
*   **No external libraries**: The application uses standard Java Swing for its GUI and core Java APIs, so no additional dependencies (like Maven or Gradle) are required beyond the JDK.
*   **Text Editor or Integrated Development Environment (IDE)**: Any text editor (e.g., Notepad++, VS Code) or IDE (e.g., IntelliJ IDEA, Eclipse) capable of writing and compiling Java code can be used.

## 🚀 How to Install and Run

Follow these steps to get the application up and running:

1.  **Save the Source Code**:
    *   Create a new directory (e.g., `TouristAccountApp`).
    *   Save the provided Java code files (`AccountManagementApp.java`, `TouristAccount.java`, `TouristAccountService.java`) into this directory. Ensure all files are in the same folder for correct compilation.

2.  **Open a Terminal or Command Prompt**:
    *   Navigate to the directory where you saved the Java files using the `cd` command.
        ```bash
        cd path/to/your/TouristAccountApp
        ```

3.  **Compile the Java Code**:
    *   Use the Java compiler (`javac`) to compile all `.java` files.
        ```bash
        javac *.java
        ```
    *   If compilation is successful, `.class` files will be generated in the same directory. If there are errors, ensure your JDK is correctly installed and configured in your system's PATH.

4.  **Run the Application**:
    *   Execute the main application class using the `java` command.
        ```bash
        java AccountManagementApp
        ```
    *   The GUI window for the Tourist Account Management Application should now appear.

## 🎮 How to Use the Application

Once the application is running, an Agency Operator can perform the following actions:

1.  **Launch the Application**: After running the `java AccountManagementApp` command, a window titled "Tourist Account Management (ACTIVE / DISATTIVAACCOUNTTURISTA)" will appear.

2.  **Select a Tourist Account**:
    *   On the left-hand side of the window, there is a list of "Select Tourist Account" featuring sample tourist accounts (e.g., "Alice Smith (ID: TR001)").
    *   Click on any tourist's name in this list to select their account.

3.  **View Account Details and Status**:
    *   Upon selecting an account, the right-hand panel ("Account Details and Actions") will update:
        *   "Selected Tourist:" will show the name and ID of the chosen tourist.
        *   "Current Status:" will display whether the account is `ACTIVE` or `DISABLED`.
    *   The "Activate Account" and "Deactivate Account" buttons will enable/disable based on the selected tourist's current status (e.g., "Activate Account" is enabled if the account is `DISABLED`).

4.  **Activate a Tourist Account**:
    *   Select a tourist account that is currently `DISABLED`.
    *   Click the **"Activate Account"** button.
    *   A confirmation dialog will appear, asking, "Are you sure you want to activate account for [Tourist Name]? Current status: DISABLED."
    *   Click **"Yes"** to confirm or "No" to cancel.
    *   If confirmed and successful, an "Operation Successful" message will appear, and the account's status will update to `ACTIVE` in the display.

5.  **Deactivate a Tourist Account**:
    *   Select a tourist account that is currently `ACTIVE`.
    *   Click the **"Deactivate Account"** button.
    *   A confirmation dialog will appear, asking, "Are you sure you want to deactivate account for [Tourist Name]? Current status: ACTIVE."
    *   Click **"Yes"** to confirm or "No" to cancel.
    *   If confirmed and successful, an "Operation Successful" message will appear, and the account's status will update to `DISABLED` in the display.

6.  **Handle Simulated Connection Errors**:
    *   The application includes a simulated "ETOUR server connection interruption" that has a 15% chance of occurring when attempting to activate or deactivate an account.
    *   If this simulation triggers, an "Connection Error" message will pop up, indicating the simulated interruption. This demonstrates how the system would handle communication failures with an external backend.

7.  **Exit the Application**:
    *   Close the application window by clicking the 'X' button in the top right corner (or equivalent for your operating system).