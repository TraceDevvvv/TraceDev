```markdown
# Teaching Management System - Administrator User Manual

## 🎯 Introduction

The "Teaching Management System" is a simple desktop application designed for educational administrators to manage teaching details within a system. This document outlines how to set up, operate, and interact with the application. It allows administrators to view a list of all courses ("teachings"), add new ones, modify existing course information, and remove courses.

**Key Features:**

*   **Display Teaching List:** View all registered teachings in a tabular format.
*   **Add New Teaching:** Create a new teaching record by providing its details.
*   **Edit Existing Teaching:** Modify the information of an already registered teaching.
*   **Delete Teaching:** Remove a teaching record from the system.
*   **Data Validation:** Automatic checks on entered data to ensure accuracy and completeness.
*   **Error Notifications:** Clear messages for invalid data or system issues (e.g., simulated server interruptions).

## 🚀 Environment Dependencies & Setup

To run this Java application, you will need a Java Development Kit (JDK) and an Integrated Development Environment (IDE) to compile and execute the code.

### 1. Java Development Kit (JDK)

*   **Requirement:** JDK 8 or a newer version (e.g., JDK 11, JDK 17, JDK 21).
*   **Installation:**
    1.  Download the appropriate JDK installer for your operating system from the official Oracle website or an OpenJDK distribution like Adoptium (Eclipse Temurin).
    2.  Follow the installation instructions.
    3.  Verify the installation by opening a command prompt or terminal and typing:
        ```bash
        java -version
        javac -version
        ```
        This should display the installed Java version.

### 2. Integrated Development Environment (IDE)

While you can compile and run Java code from the command line, an IDE is highly recommended for ease of use. Popular cho include:

*   **IntelliJ IDEA** (Community Edition is free)
*   **Eclipse IDE**
*   **VS Code** (with the Java Extension Pack)

**Setup Steps in IDE (Example with IntelliJ IDEA/Eclipse):**

1.  **Create a New Java Project:**
    *   Open your chosen IDE.
    *   Select "File" -> "New" -> "Project..." (or similar).
    *   Choose a "Java" or "Maven/Gradle" project (if you intend to add external dependencies later, but for this project, a simple Java project is sufficient).
    *   Select your installed JDK as the Project SDK.
    *   Give your project a name (e.g., "TeachingManagementSystem") and choose a location.
2.  **Add Source Code Files:**
    *   Locate the project folder created by your IDE.
    *   Place all the provided `.java` files (`Teaching.java`, `TeachingService.java`, `Validator.java`, `ErrorDialog.java`, `OperationResult.java`, `TeachingListPanel.java`, `EditTeachingPanel.java`, `TeachingApp.java`) into the `src` (or `src/main/java`) directory of your new project.
    *   The IDE should automatically detect and compile these files. If not, refresh your project or manually trigger a build.

## 🕹️ How to Use the Application

Once the project is set up and compiled in your IDE, you can run the application.

### 1. Running the Application

*   Navigate to the `TeachingApp.java` file in your IDE.
*   Right-click on `TeachingApp.java` and select "Run 'TeachingApp.main()'" (or similar option to run the main method).
*   A new Swing window titled "Teaching Management System (Administrator)" will appear.

### 2. Main Screen: Teaching List

Upon launching, you will see the main screen, which displays a list of pre-populated teachings in a table.

*   **Table Columns:** ID, Name, Instructor, Credits.
*   **Buttons at the bottom:**
    *   `Add New Teaching`: Used to create a new teaching record.
    *   `Edit Selected Teaching`: Used to modify details of a selected teaching.
    *   `Delete Selected Teaching`: Used to remove a selected teaching.
    *   `Refresh List`: Updates the table to reflect any changes.

### 3. Adding a New Teaching

1.  **Click `Add New Teaching` button.**
    *   The application will switch to the "Edit Teaching" form. The "ID" field will show "New ID (auto-generated)".
2.  **Fill in the Teaching Details:**
    *   **Name:** Enter the name of the teaching (e.g., "Software Engineering").
    *   **Description:** Provide a brief description.
    *   **Instructor:** Enter the instructor's name.
    *   **Credits:** Enter the number of credits (must be a positive whole number).
3.  **Click `Save`:**
    *   **Validation:** The system will check the entered data.
        *   If any required field is empty or "Credits" is not a valid positive number, an "Data Entry Error (Errodati)" dialog will appear, listing the validation failures. You must correct these errors to proceed.
    *   **Success:** If valid, a "Success" message will confirm the teaching has been added. The application will then return to the "Teaching List" screen, and the new teaching will appear in the table.
    *   **Simulated Error:** In some rare cases, a "Simulated: Connection to the SMOS server interrupted" error might occur, preventing the save.
4.  **Click `Cancel`:**
    *   Discards any unsaved changes and returns to the "Teaching List" screen without adding the new teaching.

### 4. Editing an Existing Teaching

1.  **Select a Teaching from the list:**
    *   Click on any row in the teaching table to select it.
2.  **Click `Edit Selected Teaching` button.**
    *   If no teaching is selected, an error dialog will prompt you to select one.
    *   The application will switch to the "Edit Teaching" form, pre-filled with the details of the selected teaching. The "ID" field will display the teaching's unique identifier and will be non-editable.
3.  **Modify the Teaching Details:**
    *   You can change the Name, Description, Instructor, and Credits.
4.  **Click `Save`:**
    *   **Validation:** Similar to adding, data validation will occur. Correct any errors displayed in the "Data Entry Error (Errodati)" dialog.
    *   **Success:** If valid, a "Success" message will confirm the teaching has been updated. The application will return to the "Teaching List" screen, and the changes will be reflected in the table.
    *   **Simulated Error:** There is a chance of a "Simulated: Connection to the SMOS server interrupted" error during save, preventing the update.
5.  **Click `Cancel`:**
    *   Discards any changes made and returns to the "Teaching List" screen, leaving the teaching's original information unchanged.

### 5. Deleting a Teaching

1.  **Select a Teaching from the list:**
    *   Click on any row in the teaching table to select it.
2.  **Click `Delete Selected Teaching` button.**
    *   If no teaching is selected, an error dialog will prompt you to select one.
    *   A confirmation dialog will appear: "Are you sure you want to delete this teaching?".
3.  **Confirm Deletion:**
    *   **Click `Yes`:** The selected teaching will be removed from the system. A success message will be displayed, and the "Teaching List" will automatically refresh to reflect the deletion.
    *   **Click `No`:** The teaching will not be deleted, and you will return to the "Teaching List" screen.

### 6. Refreshing the List

*   The `Refresh List` button can be clicked at any time on the main "Teaching List" screen to explicitly reload all teachings from the underlying data store (in this application, the in-memory `TeachingService`). This ensures the displayed list is up-to-date.

---