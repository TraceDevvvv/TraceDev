```markdown
# Agency Operator: Punto Di Ristoro Management Application

This document provides a user manual for the "Agency Operator: Punto Di Ristoro Management Application," a Java-based desktop application designed to allow agency operators to efficiently edit data pertaining to designated "Points of Rest" (Punto Di Ristoro).

## 🤔 What is this software?

This software provides a graphical user interface (GUI) for an agency operator to manage information about various `PuntoDiRistoro` entities. It enables the operator to view a list of existing points of rest, select one for detailed editing, modify its attributes (such as name, address, type, active status, and functional status), validate these changes, confirm them, and finally save them to the system. It also includes mechanisms for handling invalid input and system errors.

## 🚀 Main Functions

The application focuses on the `MODIFICADATIPUNTODIRISTOROAGENZIA` (Edit Data for Point of Rest Agency) use case, offering the following core functionalities:

1.  **View List of Points of Rest**: Upon launching, the application displays a comprehensive list of all registered points of rest. This list is the entry point for selecting an item to edit.
2.  **Select and Edit Data**: The operator can select a specific point of rest from the list. Only points of rest marked as *active* and *functional* can be selected for editing, ensuring business rule compliance.
3.  **Display Editing Form**: Once a point of rest is selected, the application navigates to an editing form. This form is pre-populated with the current data of the chosen point of rest, allowing the operator to make necessary modifications.
4.  **Modify and Submit Data**: The operator can change fields such as name, address, type, active status, and functional status. After making changes, the operator submits the updated data.
5.  **Data Validation**: The system performs real-time validation on the entered data. If any data is invalid (e.g., empty name, invalid address format, unselected type) or insufficient, the system will alert the operator with a clear error message.
6.  **Confirmation of Transaction**: Before saving, the system presents a summary of the proposed changes and asks the operator for explicit confirmation to proceed with the update.
7.  **Store Modified Data**: Upon confirmation, the system attempts to save the modified data. If successful, the changes are persisted.
8.  **Cancel Operation**: At any point during the editing process (before saving), the operator can choose to cancel the operation, discarding all unsaved changes and returning to the list view.
9.  **Error Handling**: The application gracefully handles various errors, including:
    *   **Invalid/Insufficient Data**: Displays an error dialog (`Errored` use case) with details.
    *   **Service Interruption**: Notifies the user if there's a simulated connection issue to the backend service during data retrieval or saving.
    *   **Non-Editable Status**: Prevents editing of points of rest that are not active or functional.
10. **Input Blocking**: To prevent accidental multiple submissions, the input controls in the editing form are temporarily blocked once the operator confirms the changes, remaining blocked until the save operation is completed (successfully or with an error).

## 🛠️ Environment Setup

To run this Java application, you need to have a Java Development Kit (JDK) installed on your system.

**Prerequisites:**

*   **Java Development Kit (JDK) 8 or higher**: This includes the Java Runtime Environment (JRE) required to run Java applications, along with the development tools (like `javac` for compiling) needed to build the application from source.

**How to check if Java is installed:**

1.  Open your terminal or command prompt.
2.  Type `java -version` and press Enter. You should see output similar to this:
    ```
    openjdk version "11.0.12" 2021-07-20
    OpenJDK Runtime Environment (build 11.0.12+7-Ubuntu-0ubuntu2.20.04)
    OpenJDK 64-Bit Server VM (build 11.0.12+7-Ubuntu-0ubuntu2.20.04, mixed mode, sharing)
    ```
3.  Type `javac -version` and press Enter. You should see output similar to this:
    ```
    javac 11.0.12
    ```
    If these commands return "command not found" or an older version, you'll need to install or update your JDK.

**How to install JDK:**

*   You can download the latest JDK from Oracle's website (requires an Oracle account) or from OpenJDK providers (e.g., Adoptium Temurin, Azul Zulu, Amazon Corretto).
*   Follow the installation instructions specific to your operating system. Ensure that the `JAVA_HOME` environment variable is set and that `java` and `javac` are included in your system's `PATH`.

## ▶️ How to Use/Play It

Once you have the JDK set up, you can compile and run the application.

### 1. Source Code Overview

The application consists of several Java files, each with a specific responsibility:

*   `PuntoDiRistoro.java`: Defines the data structure for a "Point of Rest" with its properties (ID, name, address, type, active/functional status, last modified timestamp).
*   `ServiceException.java`: A custom exception class used to signal errors originating from the service layer, particularly simulated connection issues.
*   `PuntoDiRistoroService.java`: Manages the business logic for `PuntoDiRistoro` objects. It simulates a data store, provides methods to retrieve, validate, and save points of rest, and includes simulated service interruptions.
*   `ErroredDialog.java`: A utility class to display modal error messages to the user. It represents the "Errored" use case.
*   `PuntoDiRistoroListPanel.java`: This Swing `JPanel` displays the list of `PuntoDiRistoro` objects. It allows the operator to view entries and select one for editing.
*   `PuntoDiRistoroEditPanel.java`: This Swing `JPanel` provides the form for editing a specific `PuntoDiRistoro`. It handles data input, validation, confirmation, and save/cancel operations.
*   `PuntoDiRistoroApp.java`: The main application class (`JFrame`). It orchestrates the entire application, switches between the list and edit panels using a `CardLayout`, and acts as a central listener for actions from both panels.

### 2. Compilation and Execution

Follow these steps to get the application running:

1.  **Save the files**: Create a new directory (e.g., `PuntoDiRistoroApp`) on your computer. Save all the provided `.java` files (`PuntoDiRistoro.java`, `ServiceException.java`, `PuntoDiRistoroService.java`, `ErroredDialog.java`, `PuntoDiRistoroListPanel.java`, `PuntoDiRistoroEditPanel.java`, `PuntoDiRistoroApp.java`) into this directory.

2.  **Open a terminal/command prompt**: Navigate to the directory where you saved the files.
    ```bash
    cd path/to/your/PuntoDiRistoroApp
    ```

3.  **Compile the Java source files**: Use the Java compiler (`javac`) to compile all `.java` files.
    ```bash
    javac *.java
    ```
    If there are no errors, this command will create corresponding `.class` files for each `.java` file in the same directory.

4.  **Run the application**: Execute the main application class (`PuntoDiRistoroApp`) using the Java Virtual Machine (`java`).
    ```bash
    java PuntoDiRistoroApp
    ```

### 3. Interacting with the Application GUI

Once the application starts, a window titled "Agency Operator: Edit Points of Rest" will appear.

**Initial View (List Panel):**

*   You will see a list of predefined "Points of Rest" (e.g., "Ristorante Sole", "Caffè Centrale").
*   Each item displays its ID, Name, Address, and whether it's Active and Functional.
*   **Refresh List Button**: Click this to reload the list of points of rest. This is useful if you suspect the underlying data has changed.
*   **Edit Selected Button**: This button will be initially disabled.

**Selecting and Editing a Point of Rest:**

1.  **Select an item**: Click on an item in the list. The "Edit Selected" button will become enabled.
    *   **Note**: The system only allows editing of points of rest that are *Active* and *Functional*. If you select an item that is not both (e.g., "Caffè Centrale" is not functional, "Hotel Bellevue Bar" is not active) and click "Edit Selected," an error dialog will inform you that "Only active and functional Points of Rest can be edited."
2.  **Click "Edit Selected"**: This will switch the view to the "Edit Point of Rest Data" form.

**Editing Form View:**

*   **ID**: This field is read-only, displaying the unique identifier of the point of rest.
*   **Name**: Edit the name of the point of rest.
*   **Address**: Edit the address.
*   **Type**: Select a type from the dropdown list (e.g., Restaurant, Cafe).
*   **Active**: Check or uncheck to change the active status.
*   **Functional**: Check or uncheck to change the functional status.
*   **Last Modified**: This read-only field shows the last time any data for this entry was modified.
*   **Save Changes Button**: Click this to save your modifications.
*   **Cancel Button**: Click this to discard any changes and return to the list view.

**Saving Changes (Example Flow):**

1.  **Edit Form**: Make a change, for example, change the name of "Ristorante Sole" to "Ristorante del Sole" and its type to "Hotel".
2.  **Click "Save Changes"**:
    *   **Validation**: If you leave a required field empty or enter invalid data (e.g., Name less than 3 characters), an "Errored" dialog will pop up with a validation error message. You'll need to correct the input before proceeding.
    *   **Confirmation**: After successful validation, a confirmation dialog will appear, summarizing your changes and asking "Are you sure you want to save these changes?".
    *   **Confirm**: Click "Yes".
    *   **Saving Progress**: The "Save Changes" button will momentarily change to "Saving..." and the form fields will be disabled.
    *   **Success**: If the save is successful, a "Success" message will be displayed, and the application will automatically return to the list view. The list will now reflect your updated data.
    *   **Service Error**: There's a small chance of a simulated "Interruption of the connection to the server ETOUR" error during save. If this happens, an "Errored" dialog will appear, and the form will be re-enabled for you to try again or cancel.

**Cancelling Changes:**

1.  **Edit Form**: While in the editing form, make some changes.
2.  **Click "Cancel"**: This will immediately discard all your changes and switch you back to the list view without saving anything.

This manual should help agency operators effectively use the Punto Di Ristoro Management Application.
```