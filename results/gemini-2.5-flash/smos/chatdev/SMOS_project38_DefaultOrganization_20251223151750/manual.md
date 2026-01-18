```markdown
# Justification Details Viewer (Administrator)

This application provides an administrative interface for viewing, modifying, and deleting staff justification records for absences. It simulates a crucial part of an HR or attendance management system, allowing administrators to manage justification details efficiently.

## 🤔 What is this?

This is a demonstration Java Swing application designed to showcase basic CRUD (Create, Read, Update, Delete) operations on "justification" data, specifically from an administrator's perspective. It simulates a use case where an administrator needs to interact with individual justification records after selecting them from a list. The application features a simple graphical user interface (GUI) for ease of use.

## 🚀 Main Functions

The application offers the following core functionalities to an administrator:

1.  **View Justification Details**: Upon selection from an initial list, the system displays all pertinent details of a specific justification, such as ID, employee name, absence date, reason, and status.
2.  **Modify Justification**: Administrators can edit the details of an existing justification. This includes updating the employee name, the date of absence, the reason provided, and the justification's status (e.g., "Pending", "Approved", "Rejected"). Changes must be explicitly saved.
3.  **Delete Justification**: The application allows administrators to permanently remove a justification record from the system after a confirmation prompt.
4.  **Simulated Administrator Access**: The initial selection process simulates an administrator viewing a list of "green absences" (representing pending or approved justifications) and selecting one to manage.

## 🛠️ Environment Dependencies

To run this Java application, you will need:

*   **Java Development Kit (JDK) 8 or higher**: This includes the Java Runtime Environment (JRE) and necessary development tools like the Java compiler (`javac`).
    *   You can download the latest JDK from the official Oracle website or adoptium.net (for OpenJDK).
*   **No external third-party libraries are required**: The application uses only standard Java APIs, primarily `javax.swing` for the GUI and `java.time` for date handling.

## 📦 How to Install and Run

Follow these steps to set up and run the application:

### 1. Save the Code Files

Create a new directory for the project (e.g., `JustificationApp`). Inside this directory, save each provided code block into its respective `.java` file:

*   Save the content of `main.java` into `Main.java`.
*   Save the content of `justification.java` into `Justification.java`.
*   Save the content of `justificationservice.java` into `JustificationService.java`.
*   Save the content of `justificationdetailsform.java` into `JustificationDetailsForm.java`.

Ensure all files are in the same directory.

### 2. Compile the Java Code

Open a terminal or command prompt, navigate to the `JustificationApp` directory where you saved the `.java` files, and compile them using the Java compiler:

```bash
javac *.java
```

If the compilation is successful, you will see `.class` files generated for each `.java` file in the directory. If there are errors, ensure your JDK is correctly installed and configured in your system's PATH.

### 3. Run the Application

After successful compilation, run the main application using the Java Virtual Machine:

```bash
java Main
```

## ▶️ How to Use/Play

Once you run the application, follow these steps to interact with it:

### Step 1: Select a Justification

1.  A small pop-up window titled "Select Justification to View" will appear.
2.  This window contains a dropdown combobox (`JComboBox`) listing sample justifications. These represent the "green absences" or pending/approved items that an administrator would typically review.
    *   **"J001 - Alice Smith (2023-10-26) - Status: Pending"** (Colored green)
    *   **"J002 - Bob Johnson (2023-10-27) - Status: Approved"** (Colored green)
    *   **"J003 - Charlie Brown (N/A) - Status: Rejected"** (Default color)
3.  Select one of the justifications from the list, preferably one of the green-colored ones to align with the use case.
4.  Click the "OK" button.

### Step 2: View Justification Details

1.  A new window titled "Justification Details (Administrator)" will open.
2.  This form displays the selected justification's details: ID, Employee Name, Absence Date, Reason, and Status.
3.  Initially, all input fields (`Employee Name`, `Absence Date`, `Reason`, `Status`) are non-editable.
4.  You will see four buttons at the bottom: "Modify", "Delete", "Save", and "Cancel". Only "Modify" and "Delete" are initially visible.

### Step 3: Modify a Justification

1.  Click the **"Modify"** button.
2.  The "Employee Name", "Absence Date", "Reason", and "Status" fields will become editable. The "Modify" and "Delete" buttons will disappear, and "Save" and "Cancel" buttons will become visible.
3.  **Enter new values** into the text fields.
    *   **Important**: For "Absence Date", use the `YYYY-MM-DD` format (e.g., `2023-11-15`). Invalid date formats will trigger an error message.
4.  To apply your changes, click the **"Save"** button.
    *   If successful, a "Justification updated successfully." message will appear, and the form will revert to view-only mode, displaying the updated details.
    *   If validation fails (e.g., empty fields, invalid date format), an error message will be displayed.
5.  To discard your changes and revert to the original details, click the **"Cancel"** button. The form will revert to view-only mode.

### Step 4: Delete a Justification

1.  Ensure the form is in view-only mode (if you just modified, click "Cancel" or "Save" and then it will be).
2.  Click the **"Delete"** button.
3.  A confirmation dialog "Confirm Deletion" will appear asking if you are sure.
4.  Click "Yes" to confirm deletion.
    *   A "Justification deleted successfully." message will be displayed, and the "Justification Details (Administrator)" form will **close**. This simulates the administrator completing the task and the system's connection to that specific record being "interrupted" as described in the postconditions.
5.  Click "No" to cancel the deletion. The form will remain open.

### Important Notes:

*   **In-Memory Data**: This application uses an in-memory data store (`JustificationService`). This means any modifications or deletions you make will only persist for the current session. Once the application is closed, the data will reset to its initial sample state when you run it again.
*   **Administrator Access**: The "preconditions" regarding administrator login and viewing were simulated by jumping directly into the justification selection and details screen. In a real application, these would involve proper authentication and navigation flows.
*   **"SMOS Server Interrupted"**: The post-condition "The administrator interrupts the connection to the SMOS server interrupted" is conceptually fulfilled by the closing of the details form after a delete operation, or implicitly by the administrator dismissing the form or the application when their task is done. The in-memory service acts as a stand-in for the "SMOS server" in this demo.

This manual provides a comprehensive guide to understanding and interacting with the Justification Details Viewer application.
```