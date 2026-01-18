```markdown
# Teaching Management System

## 🤔 What is this?

The Teaching Management System is a simple Java Swing desktop application designed for administrators to manage teaching records. It provides functionalities for secure login, displaying a list of available teachings, viewing detailed information for a selected teaching, and critically, allowing administrators to delete a teaching from the system archive.

This application simulates administrative tasks and demonstrates a basic UI flow for managing data, including handling specific postconditions such as an "SMOS server interruption" upon deletion, as per the use case requirements.

## 🚀 Main Functions

*   **Administrator Login:** Secure access to the system using predefined administrator credentials.
*   **View Teachings List:** Display a dynamic list of all available teaching records.
*   **View Teaching Details:** Show comprehensive information for any selected teaching from the list.
*   **Delete Teaching:** Permanently remove a teaching from the system after administrator confirmation. This action also triggers a simulated "SMOS server interruption" event.

## 🛠️ Environment Setup

To compile and run this Java application, you will need to have the following installed:

*   **Java Development Kit (JDK):** Version 8 or higher. You can download it from Oracle's website or use an open-source distribution like OpenJDK.

### Installation Steps:

1.  **Save the Code:** Save all the provided `.java` files (e.g., `Teaching.java`, `TeachingService.java`, `LoginPanel.java`, `TeachingListPanel.java`, `TeachingDetailsPanel.java`, `MainFrame.java`, `TeachingManagementApp.java`) into a single directory (e.g., `TeachingApp`).

2.  **Open a Terminal/Command Prompt:** Navigate to the directory where you saved the Java files.

3.  **Compile the Code:** Use the `javac` command to compile all the Java source files.

    ```bash
    javac Teaching.java TeachingService.java LoginPanel.java TeachingListPanel.java TeachingDetailsPanel.java MainFrame.java TeachingManagementApp.java
    ```

    *If successful, this will generate `.class` files for each `.java` file in the same directory.*

4.  **Run the Application:** Execute the main application class using the `java` command.

    ```bash
    java TeachingManagementApp
    ```

    *This will launch the graphical user interface (GUI) of the Teaching Management System.*

## 🎮 How to Use/Play

Follow these steps to interact with the Teaching Management System:

### 1. Launch the Application

*   After running the `java TeachingManagementApp` command, a window titled "Teaching Management System - Login" will appear.

### 2. Administrator Login

*   **Login Panel:** You will be presented with a login screen.
*   **Enter Credentials:**
    *   **Username:** `admin`
    *   **Password:** `password`
*   **Click "Login":** If the credentials are correct, a "Login Success" message will pop up, and the application will transition to the "Teachings List" view. If incorrect, an error message will be displayed on the login panel.

### 3. View Teachings List

*   **Available Teachings:** After successful login, you will see a list of available teachings (e.g., "Introduction to Programming", "Data Structures & Algorithms").
*   **Select a Teaching:** Click on any teaching in the list to select it.

### 4. View Teaching Details

*   **Display Details:** Once a teaching is selected from the list, the application will automatically switch to the "Teaching Details" view.
*   **Information Displayed:** This panel shows the `ID`, `Name`, and `Description` of the selected teaching.
*   **"Delete Teaching" Button:** A red "Delete Teaching" button will be enabled, allowing you to remove this specific teaching.

### 5. Delete a Teaching

*   **Confirm Deletion:** After viewing the details of the teaching you wish to delete, click the "Delete Teaching" button.
*   **Confirmation Dialog:** A confirmation dialog will appear, asking "Are you sure you want to delete '[Teaching Name]'?". This is a critical step to prevent accidental deletions.
*   **Proceed/Cancel:**
    *   Click **"Yes"** to confirm the deletion.
    *   Click **"No"** or close the dialog to cancel the deletion.
*   **Deletion Outcome:**
    *   If deletion is confirmed and successful, a "Teaching deleted successfully!" message will be displayed.
    *   **SMOS Server Interruption:** You will also see a message printed to your terminal/console indicating: `ALERT: Connection to the SMOS server interrupted due to teaching deletion.` This simulates the postcondition defined in the use case.
    *   The application will then return to the "Teachings List" view, which will be updated to reflect the removal of the deleted teaching.
    *   If deletion fails (e.g., teaching already gone), an error message will pop up, and the list will also refresh.

### 6. Continue Managing Teachings

*   You can now select another teaching from the updated list to view its details or delete it, or you can simply close the application window to exit.

---
*Note: This application is a simplified example for demonstration purposes. In a real-world scenario, data persistence would typically involve a database, and the SMOS server interaction would be a concrete network call.*
```