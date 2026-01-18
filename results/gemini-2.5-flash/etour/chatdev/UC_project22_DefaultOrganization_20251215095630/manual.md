```
# ELIMINANEWS - News Deletion System

## 🤔 What is this?

ELIMINANEWS is a simple Java application designed for an Agency Operator to manage news items in a system. Its primary function is to allow operators to view a list of existing news items, select one, and securely delete it. The system includes features for user confirmation, real-time feedback on deletion status, and simulates potential server connection interruptions to demonstrate robustness.

**Main Functions:**

*   **View All News**: Displays a list of all currently available news items with their titles and a snippet of their content.
*   **Select News for Deletion**: Allows the operator to choose a specific news item from the list.
*   **Confirm Deletion**: Prompts the operator for confirmation before proceeding with the deletion to prevent accidental data loss.
*   **Delete News**: Removes the selected news item from the system.
*   **Status Notifications**: Provides clear feedback on the outcome of the deletion operation, including success, user cancellation, or simulated connection issues.
*   **Refresh News List**: Enables convenient updating of the displayed news list.

## 📖 Installation and Setup

To run the ELIMINANEWS application, you will need a Java Development Kit (JDK) installed on your system.

### Prerequisites

*   **Java Development Kit (JDK) 8 or higher**: If you don't have it installed, you can download it from the official Oracle website or adoptium.net (for OpenJDK).

### Steps to Install and Run

1.  **Save the Code**: Ensure you have downloaded or copied all three Java files (`News.java`, `NewsService.java`, `NewsDeletionApp.java`) into the same directory on your computer.

2.  **Open a Terminal/Command Prompt**: Navigate to the directory where you saved the Java files.

    ```bash
    cd path/to/your/java/files
    ```

3.  **Compile the Java Files**: Use the Java compiler (`javac`) to compile all three source files.

    ```bash
    javac News.java NewsService.java NewsDeletionApp.java
    ```
    If compilation is successful, you will see new `.class` files created in your directory (e.g., `News.class`, `NewsService.class`, `NewsDeletionApp.class`).

4.  **Run the Application**: Execute the `NewsDeletionApp` class, which contains the `main` method.

    ```bash
    java NewsDeletionApp
    ```

    This will launch the graphical user interface (GUI) of the ELIMINANEWS application.

## ▶️ How to Use the Application

Once the application is running, an Agency Operator can follow these steps to delete a news item:

1.  **Launch the Application**: After running the `java NewsDeletionApp` command, a window titled "ELIMINANEWS - News Deletion System" will appear.
    *   **Entry Condition**: The application assumes the "Agency Operator has logged" by simply launching it.

2.  **View All News**:
    *   The application will automatically load and display a list of all available news items in the central panel.
    *   Each item shows its title and a brief content snippet.
    *   A status message at the bottom will confirm the number of news items loaded.

3.  **Select a News Item**:
    *   Click on any news item in the list to select it. The selected item will be highlighted.

4.  **Activate Deletion Function**:
    *   Click the **"Delete Selected News"** button located on the right side of the window.

5.  **Confirmation of Transaction**:
    *   A confirmation dialog box will appear, asking: "Are you sure you want to delete News ID: '[ID]' titled '[Title]'?".
    *   This step serves as a safeguard against accidental deletions.

6.  **Confirm or Cancel Deletion**:
    *   **To Confirm**: Click the **"Yes"** button in the confirmation dialog.
        *   The system will attempt to delete the news data.
        *   Upon **successful deletion**, the news item will disappear from the list, a "Deletion Successful" message will pop up, and the status bar will update to confirm the successful elimination.
    *   **To Cancel**: Click the **"No"** button in the confirmation dialog.
        *   The news item will not be deleted, and the status bar will display "Deletion cancelled by operator." An "Operation Cancelled" pop-up will also appear. This fulfills the "Operator Agency cancels the operation" exit condition.

7.  **Handle Deletion Outcomes**:
    *   **Successful Deletion**: The news item is removed, the list refreshes, and a success message is displayed.
    *   **Connection Interruption (ETOUR)**: In some cases (simulated with a 25% chance), the system might encounter a "Server connection interrupted (ETOUR)" error during deletion. A detailed error message will be shown, and the status bar will update accordingly. This fulfills the "Interruption of the connection to the server ETOUR" exit condition.
    *   **News Not Found**: If the selected news item was already deleted externally (e.g., by another operator), the system will notify that the news was not found and refresh the list.

8.  **Refresh News List**:
    *   At any time, you can click the **"Refresh News List"** button to reload all news items from the service. This is useful to see if there have been any external changes to the news database.

This manual covers the complete flow for an Agency Operator using the ELIMINANEWS application to delete news items, including expected system responses and error handling.
```