# User Manual: Modifica Bene Culturale (Cultural Object Modification)

## 📖 Introduction

The "Modifica Bene Culturale" application is a Java Swing-based desktop tool designed for agency operators to manage and update information about cultural objects within a system. This application facilitates the process of viewing existing cultural objects, modifying their details, and persisting these changes, while ensuring data integrity and handling potential connection issues.

## ✨ Main Features

This application implements the `MODIFICABENECULTURALE` use case, providing the following functionalities:

*   **View Cultural Objects**: Displays a list of all cultural objects currently in the system. (Simulates `RicercaBeneCulturale`).
*   **Select and Load**: Allows the operator to select a cultural object from the list to load its current data into an editable form.
*   **Edit Cultural Object Data**: Provides fields to modify details such as Name, Description, Location, and Value of the selected cultural object.
*   **Data Validation**: Performs client-side and server-side (service layer) validation to ensure the submitted data is valid and complete. Invalid data triggers an "Errored" state.
*   **Transaction Confirmation**: Prompts the operator for confirmation before committing any changes to the system.
*   **Store Modified Data**: Upon confirmation, the system attempts to save the updated cultural object information.
*   **Connection Interruption Simulation (ETOUR)**: Includes a feature to simulate network connection interruptions, demonstrating how the system handles such scenarios and prevents data loss or inconsistent states.
*   **UI Responsiveness**: Utilizes `SwingWorker` to perform long-running operations (like loading or saving data) in the background, keeping the user interface interactive.
*   **Input Blocking**: Temporarily blocks input controls in the form during submission to prevent multiple accidental submissions and ensure operation completion.

## ⚙️ Environment Setup

To run this Java application, you need to have a Java Development Kit (JDK) installed on your system.

### Prerequisites:

*   **Java Development Kit (JDK) 8 or higher**: This includes the Java Runtime Environment (JRE) and necessary development tools like the Java compiler (`javac`) and the Java  マシン (`java`).

### Steps to Install JDK:

1.  **Download JDK**: Visit the official Oracle or OpenJDK website to download the appropriate JDK version for your operating system (Windows, macOS, Linux).
2.  **Install JDK**: Follow the installation instructions provided by the installer.
3.  **Verify Installation**: Open a command prompt or terminal and type the following commands to check if Java is correctly installed and configured:
    ```bash
    java -version
    javac -version
    ```
    You should see output indicating the installed Java version.

## 🚀 How to Build and Run the Application

This section guides you through compiling and running the application from source code.

### Project Structure:

The application is organized into the following packages:

*   `com.chatdev.modifybeneconaturale.model`: Contains the `CulturalObject` entity class.
*   `com.chatdev.modifybeneconaturale.dao`: Contains `CulturalObjectDAO` for data access, simulating an in-memory database and connection interruptions.
*   `com.chatdev.modifybeneconaturale.service`: Contains `CulturalObjectService` for business logic and validation.
*   `com.chatdev.modifybeneconaturale.gui`: Contains `ModifyCulturalObjectApp`, the main GUI class.

### 📦 Source Code Files:

Ensure you have the following Java files in their respective directories (packages):

```
.
└── com
    └── chatdev
        └── modifybeneconaturale
            ├── dao
            │   └── CulturalObjectDAO.java
            ├── gui
            │   └── ModifyCulturalObjectApp.java
            ├── model
            │   └── CulturalObject.java
            └── service
                └── CulturalObjectService.java
```

### Building from Command Line:

1.  **Navigate to the project root**: Open your command line interface and navigate to the directory containing the `com` folder.
2.  **Compile the source files**: Use the `javac` command to compile all Java source files.
    ```bash
    javac com/chatdev/modifybeneconaturale/*/*.java com/chatdev/modifybeneconaturale/*/*/*.java
    ```
    *Alternatively, if you prefer to compile package by package:*
    ```bash
    javac com/chatdev/modifybeneconaturale/model/*.java
    javac com/chatdev/modifybeneconaturale/dao/*.java
    javac com/chatdev/modifybeneconaturale/service/*.java
    javac com/chatdev/modifybeneconaturale/gui/*.java
    ```

### Running the Application:

After successful compilation, you can run the main application class.

1.  **Run the main class**: From the project root directory, execute the `java` command.
    ```bash
    java com.chatdev.modifybeneconaturale.gui.ModifyCulturalObjectApp
    ```

The application window named "Modifica Bene Culturale (Cultural Object Modification)" should now appear.

## ▶️ How to Use the Application

Follow these steps to interact with the "Modifica Bene Culturale" application:

### 1. Launch the Application

Upon launching, the application window will display a list panel on the left and a detailed editing form on the right. Initially, the form fields will be disabled, and the application will attempt to load the list of cultural objects.

### 2. Load Cultural Objects (Use Case: RicercaBeneCulturale)

*   **Initial Load**: The application automatically tries to load all cultural objects when it starts. The `Status Label` at the bottom will indicate "Loading cultural objects..."
*   **Reload List**: If the list is empty, or you wish to refresh the list of objects (e.g., after a simulated connection error or if data might have changed externally), click the **"Reload List"** button.
*   **ETOUR Simulation**: You can check the **"Simulate Connection Interruption (ETOUR)"** checkbox to toggle a simulated network error for data loading and saving operations. When active, there's a chance that fetching the list or updating an object might throw a `ConnectionInterruptionException`.

### 3. Select a Cultural Object

*   **View List**: The left panel displays a list of cultural objects with their ID and Name.
*   **Select Item**: Click on an item in the list (e.g., "Mona Lisa", "Colosseum").
*   **Load Data**: Upon selection, the application will automatically populate the form fields on the right with the details of the selected cultural object. The `Status Label` will update to confirm your selection.
*   **Enable Form**: Once an object is selected, the form fields and the "Submit Changes" / "Cancel" buttons will become enabled, allowing for modification.

### 4. Edit Details

*   **Modify Fields**: You can now edit the `Name`, `Description`, `Location`, and `Value` fields.
    *   **ID**: The `ID` field is read-only as it's a unique identifier.
    *   **Value**: This is a numerical field. Ensure you enter valid numbers; the application uses `NumberFormat` to handle locale-specific number parsing (e.g., using commas or periods for decimals).
*   **Client-Side Validation**: As you change data, the application will perform validation when you attempt to submit. Invalid inputs will be highlighted.

### 5. Submit Changes

*   **Click "Submit Changes"**: Once you have made your desired edits, click the **"Submit Changes"** button.
*   **Preliminary Validation**: The application performs an initial validation of the data you entered.
    *   If there are validation errors (e.g., empty name, invalid value, description too short), an "Invalid Data - Errored" dialog will appear listing the issues. You will need to correct these in the form.
*   **Confirm Transaction**: If the data passes validation, a "Confirm Update" dialog will appear, asking you to confirm the operation. This is **Step 5** of the use case.
    *   Click **"Yes"** to proceed with saving the changes.
    *   Click **"No"** or close the dialog to cancel the submission and return to editing.

### 6. Handle Outcomes

After confirming the transaction, the application attempts to save the changes. The `Status Label` will indicate "Saving changes..." during this period.

*   **Success**: If the update is successful, a "Success" message box will appear, and the `Status Label` will show a green "Update successful!". The application will then reload the list of cultural objects to reflect the changes and clear the form, ready for a new selection.
*   **Validation Error (Errored Use Case)**: If deeper business logic validation (in the service layer) fails or an `IllegalArgumentException` occurs during update, an error message will be displayed, and the form fields will be re-enabled for correction.
*   **Connection Interruption (ETOUR Use Case)**: If the "Simulate Connection Interruption (ETOUR)" checkbox is active and a connection error occurs during the save operation, an "Error" message will inform you about the interruption (e.g., "Update failed due to connection interruption (ETOUR)"). The `Status Label` will show a red error message. The form fields will remain enabled for you to retry.
*   **Cultural Object Not Found**: If the cultural object to be updated is unexpectedly not found in the system (e.g., deleted by another operator concurrenty), an error message will inform you. The application will then reload the list to reflect the current state of the database.
*   **Unexpected Error**: For any other unforeseen errors, an "Error" message will be displayed, and the console might show a stack trace for debugging purposes.

### 7. Cancel Operation

*   **Click "Cancel"**: At any point while editing a cultural object, you can click the **"Cancel"** button. This will:
    *   Clear all data from the form fields.
    *   Disable the form fields.
    *   Deselect any item in the cultural object list.
    *   The `Status Label` will show "Operation cancelled by operator."
*   This fulfills the "Operator Agency cancels the operation" exit condition.

### Quality Considerations in Action:

*   **Input Blocking**: During the "Saving changes..." phase, notice that the form fields, "Submit Changes" button, and even the list selection are disabled. This prevents you from submitting multiple times or selecting a different object in the middle of an ongoing update, ensuring the system processes one transaction at a time.
*   **UI Responsiveness**: You will observe that even during delays introduced by the DAO (simulating network latency for 0.5s for reads, 1s for writes), the GUI remains responsive (e.g., you can still drag the window, but interactive elements related to the specific operation are locked). This is achieved through `SwingWorker`.

This concludes the user manual for the "Modifica Bene Culturale" application.