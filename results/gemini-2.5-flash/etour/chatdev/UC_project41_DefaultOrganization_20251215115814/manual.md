```markdown
# Restaurant Daily Menu Editor (MODIFICAMENÙ)

## 📝 Introduction

The "Restaurant Daily Menu Editor" is a desktop application designed to streamline the process of managing daily menus for a restaurant. Developed in Java using Swing, this application provides a user-friendly interface for restaurant operators to view, edit, and save menu details for each day of the week.

This system addresses the `MODIFICAMENÙ` use case, allowing authenticated operators to efficiently update their restaurant's daily offerings. It incorporates robust handling for data loading, saving, input validation, and user confirmation, while ensuring a responsive user experience through asynchronous operations.

## 🚀 Main Features

*   **Daily Menu Management**: Dedicated tabs for each day of the week (Monday to Sunday) allow for focused editing.
*   **Asynchronous Data Operations**: Menu data loading and saving are performed in the background using `SwingWorker` to prevent UI freezing, even with simulated network latencies or interruptions.
*   **Unsaved Changes Detection**: The system intelligently detects unsaved changes when switching between tabs or attempting to close the application, prompting the user for action (save, discard, or cancel).
*   **Input Validation**: Ensures that menu content is not empty before saving.
*   **User Confirmation**: Requests confirmation for critical actions like saving changes or discarding unsaved edits.
*   **Simulated Backend**: Includes a `RestaurantDataStore` that simulates data storage, retrieval delays, and potential connection interruptions (ETOUR condition) to demonstrate realistic application behavior.
*   **Clear Status Indicators**: Displays real-time "Loading..." and "Saving..." messages.

## 🛠️ Installation and Setup

To run the Restaurant Daily Menu Editor, you need a Java Development Kit (JDK) installed on your system.

### Prerequisites

*   **Java Development Kit (JDK)**: Ensure you have Java 8 or a newer version installed. You can download it from the official Oracle website or adoptium.net (OpenJDK).
    *   Verify your installation by opening a terminal or command prompt and typing:
        ```bash
        java -version
        javac -version
        ```
        You should see output indicating your Java version.

### Compilation

1.  **Save the files**: Place all provided `.java` files (`MenuManagerApp.java`, `RestaurantDataStore.java`, `DayOfWeekPanel.java`) into a single directory (e.g., `RestaurantMenuEditor`).
2.  **Open a terminal/command prompt**: Navigate to the directory where you saved the files.
    ```bash
    cd path/to/your/RestaurantMenuEditor
    ```
3.  **Compile the Java source code**: Use the Java compiler (`javac`) to compile all `.java` files.
    ```bash
    javac MenuManagerApp.java RestaurantDataStore.java DayOfWeekPanel.java
    ```
    If compilation is successful, `.class` files will be generated in the same directory.

### Running the Application

1.  **Execute the main class**: From the same terminal/command prompt, run the `MenuManagerApp` class.
    ```bash
    java MenuManagerApp
    ```

The application window for the "Restaurant Daily Menu Editor" should now appear.

## 🖥️ How to Use the Application

The application simulates the scenario where a "Point Of Restaurant Operator" has already authenticated to the system.

### 1. Launching the Editor

Upon running `java MenuManagerApp`, the main application window will open. It will display a tabbed interface with days of the week (Monday to Sunday). By default, the "MONDAY" tab will be selected, and its menu data will begin loading.

### 2. Navigating Daily Menus

*   **Switching Tabs**: To view or edit the menu for a different day, simply click on its corresponding tab (e.g., "TUESDAY", "WEDNESDAY").
*   **Unsaved Changes Prompt**: If you have made changes to the current day's menu and attempt to switch to another tab without saving, the system will prompt you with three options:
    *   **Save Changes**: Saves the current menu and then switches to the new tab.
    *   **Discard Changes**: Discards any edits made to the current menu and then switches to the new tab.
    *   **Stay on [Current Day]**: Cancels the tab switch and keeps you on the current day's panel.

### 3. Editing a Menu

1.  **Select a Day**: Click on the tab for the day whose menu you wish to edit.
2.  **Load Menu Data**: The system will display "Loading menu..." and then populate the central text area with the menu content for that day. This process may be subject to a brief, simulated delay.
3.  **Edit Content**: Modify the menu text within the large editable text area. You can type, delete, or paste new menu items.

### 4. Saving Changes

1.  **Verify Edits**: After making changes, click the "Save Changes" button located at the bottom right of the panel.
2.  **Validation**: If the text area is left empty, a "Validation Error" message will appear, requiring you to enter some content. If no changes have been made, an "No Changes" message will be displayed.
3.  **Confirmation**: The system will ask for confirmation: "Are you sure you want to save these changes for [Day] menu?".
    *   Click "Yes" to proceed with saving.
    *   Click "No" to cancel the save operation.
4.  **Saving Process**: If confirmed, a "Saving changes..." message will appear. The system simulates a backend save operation with potential delays and occasional connection errors.
    *   **Success**: On successful save, an "Menu for [Day] successfully modified!" message will be displayed.
    *   **Error**: In case of a simulated connection interruption or server error, an "Failed to save menu for [Day]: Connection interrupted or server error." message will appear. This requires you to resolve the (simulated) issue and try saving again.

### 5. Discarding Changes

1.  **Cancel Button**: If you have made changes and wish to revert them, click the "Cancel" button at the bottom left of the panel.
2.  **Confirmation**: If there are unsaved changes, the system will ask for confirmation: "Discard all unsaved changes for [Day] menu?".
    *   Click "Yes" to discard the changes, and the text area will revert to the last saved/loaded menu content.
    *   Click "No" to keep your current edits.
3.  **No Changes**: If no changes have been made, a "No Changes to discard" message will be displayed.

### 6. Exiting the Application

*   **Close Window**: To close the application, click the 'X' button in the title bar of the main window.
*   **Unsaved Changes Across All Tabs**: The system will iterate through all daily menu tabs. If any tab has unsaved changes, it will prompt you for each one: "Menu for [Day] has unsaved changes. Exit anyway?".
    *   Click "Yes" to acknowledge the unsaved changes and continue with the exit process.
    *   Click "No" to abort the exit process and return to the application to save your work.
*   **Final Exit Confirmation**: After addressing all unsaved changes (or if there were none), a final "Are you sure you want to exit the application?" dialog will appear.
    *   Click "Yes" to shut down the application.
    *   Click "No" to continue using the application.

This manual should help you effectively use the Restaurant Daily Menu Editor to manage your restaurant's daily menus.
```