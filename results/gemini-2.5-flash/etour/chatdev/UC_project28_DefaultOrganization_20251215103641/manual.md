```markdown
# ELIMINATAG - Search Tag Management System

The ELIMINATAG application is a tool designed for Agency Operators to efficiently manage and remove search tags within a system. This document provides a comprehensive guide on its functionality, setup, and usage.

## 🤔 What is ELIMINATAG?

ELIMINATAG is an intuitive desktop application that empowers Agency Operators to easily view, select, and delete one or more existing search tags. It provides immediate feedback on the success of deletion operations and includes a crucial feature for simulating server connection failures (ETOUR) to aid in system testing and understanding error handling.

This application simplifies the process of cleaning up or refining the tag system by offering a graphical interface for tag management.

## ✨ Core Features

*   **Tag Display**: Presents a comprehensive list of all currently available search tags in the system.
*   **Multi-Select Deletion**: Allows users to select one or multiple tags from the displayed list for simultaneous deletion.
*   **Deletion Confirmation**: Provides clear notifications upon successful deletion of selected tags.
*   **Simulated Error Handling**: Features a togglable option to simulate an "ETOUR" (Interruption of the connection to the server) scenario, demonstrating how the system handles network failures during deletion attempts.
*   **Responsive UI**: Built with Java Swing, ensuring a native desktop application experience.

## 🚀 Getting Started

To run the ELIMINATAG application, you'll need a Java Development Kit (JDK) installed on your system.

### Prerequisites

*   **Java Development Kit (JDK)**: Version 8 or higher is recommended.
    *   You can download the latest JDK from the official Oracle website or use an open-source distribution like OpenJDK.
    *   Ensure that `java` and `javac` commands are accessible from your system's command line (i.e., added to your PATH environment variable).

### Installation (Compilation and Execution)

The ELIMINATAG application consists of two Java source files: `TagManager.java` and `EliminateTagApp.java`, both residing in the `com.chatdev.tagmanager` package.

1.  **Create Project Directory**: Create a root folder for your project, e.g., `ELIMINATAG_App`.
2.  **Create Package Structure**: Inside `ELIMINATAG_App`, create the directory structure `com/chatdev/tagmanager`.
3.  **Place Source Files**:
    *   Save the `tagmanager.java` code as `TagManager.java` inside the `com/chatdev/tagmanager` directory.
    *   Save the `eliminatetagapp.java` code as `EliminateTagApp.java` inside the `com/chatdev/tagmanager` directory.
    Your directory structure should look like this:
    ```
    ELIMINATAG_App/
    └── com/
        └── chatdev/
            └── tagmanager/
                ├── TagManager.java
                └── EliminateTagApp.java
    ```
4.  **Open Terminal/Command Prompt**: Navigate to the `ELIMINATAG_App` directory in your terminal or command prompt.

5.  **Compile the Source Code**: Use the Java compiler (`javac`) to compile both `.java` files.
    ```bash
    javac com/chatdev/tagmanager/*.java
    ```
    This command will generate `.class` files for `TagManager` and `EliminateTagApp` within their respective package directory.

6.  **Run the Application**: Execute the `EliminateTagApp` class using the Java Virtual Machine (`java`).
    ```bash
    java com.chatdev.tagmanager.EliminateTagApp
    ```

    Upon successful execution, the ELIMINATAG application window will appear.

## 🎮 How to Use ELIMINATAG

Once the application is running, you, as an Agency Operator, can begin managing tags:

1.  **Access the Functionality**: The application window, titled "ELIMINATAG - Remove Search Tags," will open, immediately displaying a list of "Available Tags" in the central panel. This fulfills the "Access the functionality" and "Research in the existing system, the tags and displays them in a form" steps.

2.  **View Available Tags**: All predefined tags will be listed in the scrollable area.

3.  **Select Tags for Deletion**:
    *   To select a single tag, click on it.
    *   To select multiple contiguous tags, click the first tag, then hold `Shift` and click the last desired tag.
    *   To select multiple non-contiguous tags, hold `Ctrl` (or `Cmd` on macOS) and click each desired tag.
    *   The selected tags will be highlighted.

4.  **Initiate Deletion**:
    *   After selecting the desired tags, click the **"Delete Selected Tags"** button located at the bottom of the window.
    *   If no tags are selected, a message "Please select one or more tags to delete." will be displayed.

5.  **Observe Deletion Outcome**:
    *   **Successful Elimination**: If the deletion is successful, a confirmation message "Successfully deleted X tag(s)." will appear. The list of available tags will then automatically refresh, showing the updated list without the deleted items.
    *   **Connection Interruption (ETOUR)**: If the "Simulate Server Connection Failure (ETOUR)" checkbox is checked, a message "Deletion failed: Interruption of the connection to the server ETOUR." will appear, indicating a simulated server error. The tags will not be deleted in this scenario.

6.  **Simulate Server Connection Failure (ETOUR Test)**:
    *   Locate the **"Simulate Server Connection Failure (ETOUR)"** checkbox at the bottom of the window.
    *   Check this box to enable the simulation of a network interruption during tag deletion.
    *   Attempt to delete tags while this box is checked to observe the system's response to an ETOUR condition.
    *   Uncheck the box to return to normal operation.

### `manual.md`
```