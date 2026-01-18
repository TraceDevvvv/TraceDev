```markdown
# ETOUR - Edit Generic Personal Preferences

## Introduction

The "ETOUR - Edit Generic Personal Preferences" application is a demonstration of a user interface (UI) for managing a tourist's personal preferences within a simulated tourism system. This software allows an authenticated tourist to view their existing preferences and make changes to them, such as
their favorite travel destination, preferred activities, and whether they wish to receive general notifications.

The application is built using Java with Swing for the graphical user interface. It simulates backend interactions, including fetching and updating preferences, and includes mechanisms to handle potential network connection interruptions.

### Main Functions:

*   **View Preferences**: Displays an authenticated tourist's existing generic personal preferences.
*   **Edit Preferences**: Allows the user to modify fields like "Favorite Destination", "Preferred Activities", and "Receive Email Notifications".
*   **Confirmation & Saving**: Guides the user through a confirmation step before saving any changes to their preferences.
*   **Cancellation**: Provides an option to cancel the modification process at any point, reverting to the original preferences.
*   **Simulated Backend**: Interacts with a simulated backend service that stores tourist data and can simulate connection errors.

## Environment Setup

To compile and run this application, you need to have a Java Development Kit (JDK) installed on your system.

### Prerequisites:

*   **Java Development Kit (JDK)**: Version 8 or higher is recommended. You can download it from the official Oracle website or adoptium.net.

### How to Compile:

1.  **Save the files**: Ensure all the provided Java source files (`.java`) are saved in the same directory.
    *   `ServiceConnectionException.java`
    *   `Tourist.java`
    *   `PreferenceService.java`
    *   `PreferencesPanel.java`
    *   `MainFrame.java`
    *   `ModificaPreferenzeApp.java`
2.  **Open a Terminal/Command Prompt**: Navigate to the directory where you saved the `.java` files.
3.  **Compile the source files**: Use the Java compiler (`javac`) to compile all files.
    ```bash
    javac *.java
    ```
    This command will generate `.class` files for each `.java` file in the directory.

## How to Use/Play

### 1. Launching the Application

After successful compilation, you can run the application from your terminal.

1.  **Execute the main class**:
    ```bash
    java ModificaPreferenzeApp
    ```

### 2. Understanding Authentication

The application simulates user authentication. In
`ModificaPreferenzeApp.java`, there's a hardcoded username used for
demonstration:

```java
String authenticatedUsername = "JohnDoe"; // Try "JohnDoe" (has preferences) or "NewUser" (null preferences)
```

*   **"JohnDoe"**: This user has existing preferences pre-loaded in the `PreferenceService`, allowing you to see and modify them.
*   **"NewUser"**: This user has no existing preferences in the `PreferenceService`. When you authenticate as "NewUser", the application will display an error and close because, per the use case, it expects preferences to *exist* to be modified. If the requirement were to *create* preferences from scratch, the `MainFrame`'s error handling would be different.
*   **"JaneSmith"**: This user also has existing preferences pre-loaded.

You can modify the `authenticatedUsername` variable in `ModificaPreferenzeApp.java` before recompiling and running to switch between these simulated users.

### 3. Application Flow

Upon launching:

1.  **Authentication Simulation**: The application first simulates an authentication process for the hardcoded user (e.g., "JohnDoe"). If authentication fails, an error message appears, and the application exits.
2.  **Loading Preferences**: If authentication is successful, the application attempts to load the generic personal preferences for that user from the simulated `PreferenceService`.
    *   A "Loading Error" dialog will appear, and the application might close if preferences cannot be loaded (e.g., connection error or no preferences found for the authenticated user, as per the `MainFrame`'s logic for the "Edit Preferences" use case).
    *   A busy cursor might appear during this simulated network operation.
3.  **Preferences Form Display**: If preferences are successfully loaded, the main window will appear, displaying a form pre-filled with the user's current settings:
    *   **Favorite Destination**: A text field.
    *   **Preferred Activities**: A larger text area.
    *   **Receive Email Notifications**: A checkbox.

### 4. Editing Preferences

1.  **Edit Fields**: You can directly type into the "Favorite Destination" and "Preferred Activities" fields, and check/uncheck the "Receive Email Notifications" checkbox.
2.  **Submit Changes**: Click the "Submit Changes" button.
    *   A "Confirm Preference Update" dialog will appear, asking if you are sure you want to save.
    *   **Click "Yes"**: The application attempts to save the modified preferences via the `PreferenceService`.
        *   A "Success" message will be displayed upon successful modification.
        *   If a simulated "Connection Error" occurs during saving, an error message will be shown, prompting you to try again. The form will retain its unsaved changes.
    *   **Click "No"**: The "Confirm Preference Update" dialog closes, and an "Information" dialog confirms the cancellation. The form fields will revert to their state immediately before you attempted to save (original loaded preferences).
3.  **Cancel Operation**: Click the "Cancel" button at any time.
    *   An "Information" dialog confirms the cancellation.
    *   The form fields will immediately revert to the last loaded or saved preferences.

### 5. Simulating Connection Errors

The `PreferenceService` class has a built-in random chance (20%) to throw a `ServiceConnectionException` when fetching or updating preferences. This simulates network issues with the ETOUR server. You might encounter these errors when the application first loads preferences or when you attempt to save them. If this happens, an error dialog will inform you of the connection issue.

Enjoy modifying your (simulated) ETOUR preferences!

```