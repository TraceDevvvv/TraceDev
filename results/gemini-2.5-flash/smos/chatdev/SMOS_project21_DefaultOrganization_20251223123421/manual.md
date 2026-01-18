```markdown
# Administrator Teaching Management System

## Assign/Remove Teachings

This application provides a user interface for administrators to manage the assignment and removal of "teachings" (e.g., courses, subjects) to specific "addresses" (e.g., classrooms, labs, virtual spaces). It simulates common administrative tasks in an educational or resource management system.

## 🤔 What is this?

The "Administrator Teaching Management System" is a simple Java Swing application designed to demonstrate the `AssignRemoveTeachings` use case. It allows an authenticated administrator (simulated by running the app) to select an address, view its currently assigned teachings, and then modify these assignments by adding new teachings or removing existing ones. The system features a simulated backend to store and retrieve data, including a mock "SMOS server" with a chance of connection interruption to demonstrate error handling.

## 🚀 Main Functions

*   **View Addresses**: Browse a list of available addresses in the system.
*   **View Address Details**: Display detailed information for a selected address, including its ID, description, and currently assigned teachings.
*   **Manage Teachings**: Access a dedicated form to assign new teachings or remove existing ones from a specific address.
*   **Update Assignments**: Save changes to teaching assignments back to the system.
*   **Error Handling**: Simulate and handle potential connection interruptions with the backend server during data operations.

## 🛠️ Environment Setup

This project is a standard Java application and requires a Java Development Kit (JDK) to compile and run.

### Prerequisites

*   **Java Development Kit (JDK) 8 or higher.**

### Checking Java Installation

1.  Open your terminal or command prompt.
2.  Type `java -version` and press Enter.
3.  Type `javac -version` and press Enter.

    You should see output indicating your installed Java version (e.g., `openjdk version "1.8.0_..."` or `javac 17.0.2`). If these commands are not found or report a version older than 8, you need to install or update your JDK.

### Installing JDK

1.  Visit the official Oracle JDK download page or OpenJDK distribution sites (e.g., Adoptium, OpenLogic).
2.  Download the appropriate JDK installer for your operating system.
3.  Follow the installation instructions provided by the installer.
4.  Ensure that the `JAVA_HOME` environment variable is set and that the JDK's `bin` directory is included in your system's `PATH`.

## 🕹️ How to Use/Play

### Step 1: Obtain the Source Code

Ensure you have access to all the provided Java source files:
*   `Teaching.java`
*   `Address.java`
*   `TeachingRepository.java`
*   `TeachingAssignmentDialog.java`
*   `AddressDetailPanel.java`
*   `MainApplication.java`

Place all these files in a single directory (e.g., `teachings_app`).

### Step 2: Compile the Java Code

1.  Open your terminal or command prompt.
2.  Navigate to the directory where you saved the Java files:
    ```bash
    cd path/to/teachings_app
    ```
3.  Compile all the Java source files:
    ```bash
    javac *.java
    ```
    If compilation is successful, `.class` files will be generated for each `.java` file in the same directory.

### Step 3: Run the Application

1.  From the same directory in your terminal, run the main application:
    ```bash
    java MainApplication
    ```
2.  A Swing GUI window titled "Administrator Teaching Management System" will appear.

### Step 4: Interact with the Application

1.  **Select an Address**: At the top of the window, use the "Select Address" dropdown menu to choose an address from the pre-populated list (e.g., "Main Campus Building A - Room 101").
    *   Upon selection, the details of the address, including its ID, description, and currently assigned teachings, will be displayed in the central panel. This simulates the "viewdettaglizzizzo" pre-condition.

2.  **Manage Teachings**: To assign or remove teachings for the selected address, click the "Teachings Address" button located at the bottom of the central panel.
    *   This will open a new modal dialog titled "Assign/Remove Teachings for: [Address Description]".

3.  **Assign or Remove Teachings in the Dialog**:
    *   The left list ("Available Teachings") shows all teachings not currently assigned to the address.
    *   The right list ("Assigned Teachings") shows teachings currently linked to the address.
    *   **To Assign**: Select one or more teachings from the "Available Teachings" list and click the "Add Selected ->" button. The selected teachings will move to the "Assigned Teachings" list.
    *   **To Remove**: Select one or more teachings from the "Assigned Teachings" list and click the "<- Remove Selected" button. The selected teachings will move back to the "Available Teachings" list.
    *   You can select multiple items by holding `Ctrl` (or `Cmd` on Mac) and clicking, or by holding `Shift` and clicking to select a range.

4.  **Save or Cancel Changes**:
    *   **Send**: After making your desired changes, click the "Send" button in the dialog to save the new assignments for the address. A confirmation message will appear if successful. This also simulates the "system has changed the teachings relating to the address" postcondition.
    *   **Cancel**: If you wish to discard your changes, click the "Cancel" button. The dialog will close without saving any modifications. This simulates the "administrator interrupts the operation" postcondition.

5.  **Observe Connection Interruptions**: The `TeachingRepository` is designed to simulate random "Connection to SMOS server interrupted" errors during `getAddressById` and `updateAddressTeachings` operations. If such an event occurs, an error message dialog will be displayed, indicating that the operation could not be completed and the "connection to the SMOS server interrupted" postcondition is met.

6.  **Refresh Display**: After the "Assign/Remove Teachings" dialog closes (either by "Send" or "Cancel"), the main application's address detail panel will refresh to show the updated (or unchanged) list of teachings for the selected address.

## 📖 Simulated Data

The application uses in-memory data for demonstration purposes, initialized within the `TeachingRepository.java` file. This includes a few sample `Teaching` objects (e.g., "Mathematics 101", "Physics Fundamentals") and `Address` objects (e.g., "Main Campus Building A - Room 101", "Online Virtual Classroom") with some initial teaching assignments. Changes made through the GUI are persisted within this in-memory data for the lifetime of the application run.

```