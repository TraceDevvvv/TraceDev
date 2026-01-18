```markdown
# Justification Management System

An example application simulating the administrative management of justification records.

## 🤔 What is this?

This application is a simple Java Swing-based desktop tool designed for administrators to view, select, and edit justification records. It demonstrates a common administrative workflow: logging in (simulated), viewing a list of items, selecting an item, editing its details, saving the changes, and returning to the list view.

## 🌟 Main Functions

*   **Administrator Access (Simulated):** The application assumes an administrator is always logged in, fulfilling the necessary precondition for editing.
*   **View Justification List:** Displays a list of existing justification records, each with an ID, date, and description.
*   **Select Justification for Editing:** Allows the administrator to choose a specific justification from the list to modify its details.
*   **Edit Justification Details:** Provides a dedicated form where the administrator can change the "date justification" and the descriptive text of a selected justification.
*   **Save Changes:** Persists the modifications made to a justification.
*   **Return to Registry Screen:** After saving, the system automatically returns to the main list view, reflecting the updated information.

## 🛠️ How to Install Environment Dependencies

To run this Java application, you'll need the following:

1.  **Java Development Kit (JDK):**
    *   Version: Java 11 or newer is recommended.
    *   Installation: Download and install the JDK from Oracle or adoptium.net (Eclipse Temurin). Ensure `JAVA_HOME` is set and `java` and `javac` commands are accessible from your terminal.

2.  **Integrated Development Environment (IDE):**
    *   Recommended: IntelliJ IDEA, Eclipse, or Apache NetBeans. These IDEs provide excellent support for Java Swing applications, including project setup, compilation, and execution.

## 🚀 How to Use/Play It

### 1. Project Setup in an IDE (e.g., IntelliJ IDEA)

1.  **Create a New Project:**
    *   Open your IDE and select "New Project".
    *   Choose "Java" or "Swing Application" (if available as a template).
    *   Select your installed JDK.
    *   Do *not* create from a template if prompted for "Command Line App" etc. Just a simple Java project.
2.  **Structure the Project (if not automatically done):**
    *   Create a `src` directory at the root of your project.
    *   Inside `src`, create `main` -> `java`.
    *   Inside `java`, create the packages: `gui`, `model`, and `service`.
3.  **Add the Provided Code:**
    *   Place `MainApplication.java` directly under `src/main/java`.
    *   Place `Justification.java` under `src/main/java/model`.
    *   Place `JustificationService.java` and `LoginService.java` under `src/main/java/service`.
    *   Place `JustificationUpdateListener.java`, `JustificationListFrame.java`, and `EditJustificationFrame.java` under `src/main/java/gui`.
    *   Ensure the package declarations at the top of each `.java` file match their directory structure (e.g., `package model;` for `Justification.java`).

### 2. Run the Application

1.  **Locate `MainApplication.java`:** In your IDE's project explorer, find the `MainApplication.java` file.
2.  **Run:** Right-click on `MainApplication.java` and select "Run 'MainApplication.main()'".

### 3. Application Workflow

Once launched, follow these steps:

1.  **Initial Screen:** You will see a window titled "Justification Registry - Administrator View" displaying a list of sample justifications. This is the "registry screen" from the use case.
    *   *Precondition met:* The system assumes you are logged in as an administrator.
    *   *Precondition met:* The system is "viewing the details of a justice" by presenting the list, fulfilling the prerequisite to select one for editing.

2.  **Select a Justification:**
    *   Click on any of the justifications listed in the main window. For example, select "ID: 1 | Date: 2023-10-26 | Description: Original justification for annual leave."

3.  **View/Edit Selected Justification:**
    *   Click the "View/Edit Selected Justification" button at the bottom of the window.
    *   A new modal dialog titled "Edit Justification (ID: X)" will appear, showing the details of the selected justification.

4.  **Change Fields (Event Sequence - User 1):**
    *   In the "Edit Justification" dialog, you can modify:
        *   **Date (YYYY-MM-DD):** Type a new date (e.g., `2023-10-27`).
        *   **Description:** Edit the justification text (e.g., change "Original" to "Revised").
    *   The "Justification ID" field is read-only and cannot be changed.

5.  **Click "Save Changes" (Event Sequence - User 2):**
    *   After making your desired changes, click the "Save Changes" button.
    *   *Input Validation:* If you leave fields empty or provide an invalid date format, an error message will be shown.
    *   *System Action (Event Sequence - System 3):* The application will process your changes.

6.  **Observe Postconditions:**
    *   **Justification Modified:** If save is successful, the "Edit Justification" dialog will close, and an "Update Successful" message will appear.
    *   **Return to Registry Screen:** The main "Justification Registry" window will automatically refresh, and you will see your updated justification reflected in the list.
    *   **SMOS Server Interrupted (Conceptual):** A console message will indicate: `Conceptual: Administrator interrupted connection to SMOS server (post-update cleanup).` This simulates the final conceptual step mentioned in the use case.

7.  **Cancel:** If you open the "Edit Justification" dialog but decide not to make changes, click the "Cancel" button to close the dialog without saving.