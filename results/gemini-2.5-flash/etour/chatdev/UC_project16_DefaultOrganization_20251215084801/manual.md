```markdown
# Tourist Account Management System - Agency Operator App

## Introduction

This document provides a user manual for the "Tourist Account Management System - Agency Operator App," a Java-based application designed to facilitate the deletion of tourist accounts by an Agency Operator. The core functionality revolves around the `ELIMINAACCOUNTTURISTA` (Delete Tourist Account) use case, allowing operators to efficiently remove selected tourist entries from the system.

Built using Java Swing for the graphical user interface, this application demonstrates a typical client-server interaction where the client (Agency Operator App) interacts with a simulated database (`TouristDatabase`) to perform data operations. It also includes mechanisms to handle potential server connection issues.

## Main Functions

The application provides the following key functionalities:

1.  **View Tourist List (Simulating `RicercaTurista`)**:
    *   Upon startup or by clicking the "Refresh Tourists" button, the application fetches and displays a list of all registered tourist accounts.
    *   Each tourist is shown with their unique ID and name.
    *   Error messages are displayed if there's a problem connecting to the tourist database during retrieval.

2.  **Delete Tourist Account (`ELIMINAACCOUNTTURISTA`)**:
    *   **Selection**: The Agency Operator can select a single tourist from the displayed list.
    *   **Confirmation**: After selecting a tourist and clicking "Delete Selected Tourist", the system will prompt the operator for confirmation before proceeding with the deletion. This is a crucial step to prevent accidental removals.
    *   **Deletion Execution**: If confirmed, the application attempts to delete the selected tourist's data from the simulated database.
    *   **Notification**:
        *   Upon successful deletion, a confirmation message is displayed, and the tourist list is automatically reloaded to reflect the change.
        *   If the deletion fails (e.g., due to the account not being found or a server connection error), appropriate error messages are displayed.
    *   **Server Connection Interruption Handling**: The system is designed to simulate and report server connection interruptions during both tourist data retrieval and deletion operations, fulfilling the `ETOUR` server connection interruption exit condition.

## How to Install Environment Dependencies

To compile and run the "Tourist Account Management System - Agency Operator App," you will need a Java Development Kit (JDK).

1.  **Install Java Development Kit (JDK)**:
    *   We recommend using **JDK 8 or a newer version** (e.g., OpenJDK 11, OpenJDK 17).
    *   Download the appropriate JDK installer for your operating system from the official Oracle website or an OpenJDK distribution like Adoptium (Eclipse Temurin).
    *   Follow the installation instructions for your specific OS.
    *   **Verify Installation**: Open a command prompt or terminal and type:
        ```bash
        java -version
        javac -version
        ```
        You should see output indicating your installed Java version.

## How to Use/Play the Application

Follow these steps to compile and run the application:

1.  **Save the Source Code**:
    *   Create a new directory on your computer, for example, `tourist_app`.
    *   Save all provided Java files (`Tourist.java`, `ServerConnectionException.java`, `TouristDatabase.java`, `AgencyOperatorApp.java`) into this `tourist_app` directory.

2.  **Open a Command Prompt or Terminal**:
    *   Navigate to the `tourist_app` directory using the `cd` command. For example:
        ```bash
        cd path/to/tourist_app
        ```

3.  **Compile the Java Source Files**:
    *   Use the Java compiler (`javac`) to compile all `.java` files.
        ```bash
        javac *.java
        ```
    *   If compilation is successful, `.class` files will be generated in the same directory. If there are any errors, ensure your JDK is correctly installed and configured, and that all files are saved correctly.

4.  **Run the Application**:
    *   Execute the main application class `AgencyOperatorApp`.
        ```bash
        java AgencyOperatorApp
        ```
    *   A GUI window titled "Tourist Account Management - Agency Operator" should appear.

5.  **Interacting with the Application**:

    *   **Initial Load**: Upon launching, the application will attempt to load a predefined set of tourist accounts. You will see these in the central list.
    *   **Refresh Tourist List**: Click the "Refresh Tourists (RicercaTurista)" button to reload the list of tourists from the database. This is useful if you suspect the list is out of sync or to see if a deleted account is truly gone.
        *   *Simulated Error*: Occasionally, clicking this button might trigger a "Connection Error" due to a simulated server connection interruption (5% chance).
    *   **Delete a Tourist Account**:
        1.  **Select a Tourist**: Click on an entry in the tourist list (e.g., "T001 - Alice Smith"). The "Delete Selected Tourist" button will become enabled.
        2.  **Initiate Deletion**: Click the "Delete Selected Tourist" button.
        3.  **Confirm Deletion**: A confirmation dialog will appear, showing the name and ID of the tourist you are about to delete.
            *   Click "Yes" to proceed with the deletion.
            *   Click "No" to cancel the operation.
        4.  **Observe Result**:
            *   If deletion is successful, a "Deletion Successful" message will appear, and the list will automatically refresh, showing the tourist removed.
            *   *Simulated Error*: There's a 15% chance that deletion will trigger a "Connection Error" due to a simulated server connection interruption. In this case, the account will not be deleted, and an error message will be displayed.
    *   **Status Label**: The label at the top of the window will provide feedback on the current operation's status or any errors encountered.

    The application demonstrates the full flow of selecting, confirming, and deleting a tourist account, including handling simulated network-related edge cases.