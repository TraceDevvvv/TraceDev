```markdown
# Menu Deletion System

A simple application designed for restaurant operators to delete daily menus from their system.

## 🤔 What is this?

The Menu Deletion System, also known by its use case name `ELIMINAMENÙ`, is a desktop application that provides a straightforward graphical user interface (GUI) for restaurant operators to manage their daily menus. Its primary function is to allow authenticated operators to select a specific day of the week and remove its associated menu from the system.

This tool is particularly useful for:
*   Quickly updating and maintaining daily menu offerings.
*   Removing outdated or unavailable menus for specific days.
*   Providing a clear and confirmed deletion process to prevent errors.

## ✨ Main Features

*   **User-Friendly Interface**: An intuitive Swing-based GUI allows for easy navigation and interaction.
*   **Day Selection**: Operators can select any day of the week (Monday to Sunday) from a dropdown list.
*   **Confirmation Prompt**: Before deletion, the system prompts the operator for confirmation, acting as a safeguard against accidental data loss.
*   **Status Notifications**: Provides clear feedback to the user regarding the success or failure of the deletion operation, or if the operation was cancelled.
*   **Simulated Backend Interaction**: Includes a `MenuManager` class that simulates backend database operations and potential server connection interruptions, mimicking real-world scenarios.

## 🚀 Quick Install & Environment Setup

To run this application, you need a Java Development Kit (JDK) installed on your system.

### Prerequisites

*   **Java Development Kit (JDK)**: Version 8 or newer is recommended. You can download it from Oracle or use an open-source distribution like OpenJDK.

### Installation Steps

1.  **Save the files**: Save the provided `MenuDeletionApp.java` and `MenuManager.java` files into the same directory on your local machine.

    ```
    /your_project_directory/MenuDeletionApp.java
    /your_project_directory/MenuManager.java
    ```

2.  **Open Terminal/Command Prompt**: Navigate to the directory where you saved the files using your terminal or command prompt.

    ```bash
    cd /path/to/your_project_directory
    ```

## 🛠️ How to Compile and Run

Once the environment is set up and files are saved, you can compile and run the application.

### Compiling the Code

Use the `javac` command to compile the Java source files.

```bash
javac MenuDeletionApp.java MenuManager.java
```
*   If compilation is successful, `.class` files (e.g., `MenuDeletionApp.class`, `MenuManager.class`) will be generated in the same directory.
*   If there are any compilation errors, ensure your JDK is correctly installed and configured in your system's PATH.

### Running the Application

After successful compilation, run the application using the `java` command.

```bash
java MenuDeletionApp
```

## 🎮 How to Use the Application

Follow these steps to delete a daily menu using the application:

1.  **Start the Application**: After running the `java MenuDeletionApp` command, a window titled "Menu Deletion System - ELIMINAMENÙ" will appear.
    *   **Entry Condition**: This assumes the "Point Of Restaurant Operator has successfully authenticated to the system."

2.  **Select a Day**: You will see a dropdown box labeled "Select Day to Delete Menu:". Click on the dropdown and select the day of the week whose menu you wish to delete (e.g., "Wednesday").

3.  **Initiate Deletion**: Click the "Delete Daily Menu" button.

4.  **Confirm Deletion**: A confirmation dialog box will appear, asking "Are you sure you want to delete the menu for [Selected Day]? This action cannot be undone."
    *   You have two options:
        *   **Click "Yes"**: To proceed with the deletion.
        *   **Click "No"**: To cancel the deletion operation.

5.  **Observe Outcome**:
    *   **Successful Deletion**: If you clicked "Yes" and the simulated backend operation is successful, a message box will appear stating "Menu for [Selected Day] successfully deleted!".
    *   **Operation Cancelled**: If you clicked "No", a message box will appear stating "Menu deletion for [Selected Day] cancelled by operator.".
    *   **Simulated Failure**: Occasionally, the system is designed to simulate a backend failure (e.g., "Interruption of the connection to the server ETOUR"). In this case, a message box will appear stating "Failed to delete menu for [Selected Day]. Please try again later or check server connection."

6.  **Repeat or Exit**: You can repeat the process for other days or close the application window when you are finished.

## ⚠️ Important Notes

*   **Simulated Backend**: The `MenuManager` class provides a simulated backend. In a real-world application, this component would interact with a database, external API, or other persistent storage mechanisms.
*   **Error Handling**: The `MenuManager` includes a simulated 20% chance of failure to demonstrate potential backend issues, aligning with the use case's "Interruption of the connection to the server ETOUR" exit condition.
*   **Authentication**: The current application assumes the operator is already authenticated as per the use case's entry condition. Real-world applications would include a login system.
```