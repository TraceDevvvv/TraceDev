```
# Tag Management System User Manual

## Introduction

Welcome to the Tag Management System! This application is designed for "Agency Operators" to efficiently manage and organize search tags. It provides a simple graphical user interface (GUI) to insert new tags, validate their data, and maintain a list of existing tags.

The core functionality revolves around the `INSERISCITAG` use case, which allows users to add new tags to the system while handling various validation and system conditions.

## Environment Setup

To run this Java application, you need to have a Java Development Kit (JDK) installed on your system.

### 1. Java Development Kit (JDK) Installation

This application requires **Java 8 or higher**.

*   **Check Java Version:** Open a terminal or command prompt and type:
    ```bash
    java -version
    ```
    If Java is installed, you will see output indicating your Java version. Ensure it is 1.8.0 or newer.

*   **Install Java (if not present or too old):**
    If Java is not installed or your version is older than 8, you will need to download and install a suitable JDK.
    *   You can download the latest OpenJDK distributions from Adoptium (formerly AdoptOpenJDK) at [https://adoptium.net/](https://adoptium.net/) or Oracle JDK from [https://www.oracle.com/java/technologies/downloads/](https://www.oracle.com/java/technologies/downloads/).
    *   Follow the installation instructions for your operating system.

### 2. Compiling and Running the Application

Assuming you have all the provided `.java` files in a single directory:

*   **Save Files:** Save all the provided Java code files (`Main.java`, `Tag.java`, `TagAlreadyExistsException.java`, `InvalidTagDataException.java`, `TagService.java`, `TagInsertionDialog.java`, `TagManagerFrame.java`, `ServerConnectionException.java`) into a folder, for example, `TagManagerApp`.

*   **Open Terminal/Command Prompt:** Navigate to the directory where you saved the files.

*   **Compile the Code:** Use the Java compiler (`javac`) to compile all source files.
    ```bash
    javac *.java
    ```
    If there are no errors, this command will generate `.class` files for each Java source file.

*   **Run the Application:** Execute the `Main` class using the Java Virtual Machine (`java`).
    ```bash
    java Main
    ```

    The application window should now appear.

## How to Use the Software

Once you run the `java Main` command, the "Tag Management System" window will appear.

### 1. Main Application Window

The main window (`TagManagerFrame`) displays:

*   A list of "Existing Tags" in the center. Initially, it will show some pre-loaded tags like "Java", "Python", and "Spring".
*   An "Insert New Tag" button at the top-left.

### 2. Inserting a New Tag (INSERISCITAG Use Case)

Follow these steps to add a new tag to the system:

*   **Step 1: Access the functionality.**
    Click the **"Insert New Tag"** button in the main window.
    This will open a new dialog window titled "Insert New Tag".

*   **Step 2: Show the form for entering a tag.**
    The "Insert New Tag" dialog presents a simple form with two fields:
    *   **Tag Name:** A text field for the unique name of the tag. (e.g., "AI", "Cloud Computing")
    *   **Description:** A text area for an optional description of the tag. (e.g., "Artificial Intelligence related topics", "Topics related to cloud serv like AWS, Azure, GCP")

*   **Step 3: Fill out the form and submit.**
    *   Enter the desired **Tag Name**.
    *   Enter an optional **Description**.
    *   Click the **"Add Tag"** button.
    *   Alternatively, click **"Cancel"** to close the dialog without adding a tag.

*   **Step 4: System Verification and Feedback.**
    Upon clicking "Add Tag", the system performs checks and provides feedback:

    *   **Successful Addition:**
        If the tag name is valid and does not already exist, a "Success" message dialog will appear: "Tag 'YourTagName' successfully added!".
        After acknowledging, the dialog will close, and the new tag will appear in the "Existing Tags" list in the main window.

    *   **Invalid Tag Data (`Errored` Use Case):**
        If the "Tag Name" field is left empty or contains only spaces, an "Error" message dialog will appear: "Invalid input: Tag name cannot be empty."
        The dialog will remain open, allowing you to correct the input.

    *   **Tag Already Exists (`ErroreTagEsistente` Use Case):**
        If you try to add a tag with a name that is already present in the "Existing Tags" list (case-insensitive), a "Duplicate Tag" message dialog will appear: "Tag already exists: Tag 'YourTagName' already exists in the system."
        The dialog will remain open, allowing you to enter a different tag name.

    *   **Server Connection Interruption (`ETOUR` Use Case):**
        There is a small chance (5%) that the system will simulate a connection error. If this happens, an "Server Disconnected (ETOUR)" message dialog will appear: "Connection Error: Connection to server interrupted (ETOUR simulation)."
        This demonstrates how the system handles unforeseen communication issues. The dialog will close, and the tag will not be added.

### 3. Viewing Existing Tags

Any tags successfully added (or the initial pre-loaded tags) will be displayed in the "Existing Tags" list in the main application window. The list is automatically refreshed and sorted alphabetically by tag name whenever a new tag is successfully added through the dialog.

## Main Functions

The application provides the following key functionalities:

*   **Tag Insertion (`INSERISCITAG`):**
    *   Allows operators to input new tag names and descriptions.
    *   Conducts immediate validation for provided tag data (e.g., ensuring the tag name is not empty).
    *   Checks for the uniqueness of tag names to prevent duplicates.
    *   Notifies the user upon successful tag inclusion.
    *   Simulates and handles potential server connection interruptions (`ETOUR`).

*   **Tag Listing:**
    *   Displays all currently managed tags in a clear, sortable list format within the main application window.
    *   Automatically updates the list after a successful tag insertion.

*   **Robust Error Handling:**
    *   **`InvalidTagDataException` (Errored):** Catches and reports issues where the user's input for a tag is incomplete or malformed.
    *   **`TagAlreadyExistsException` (ErroreTagEsistente):** Informs the user when they attempt to add a tag with a name that is already in use, prompting them to choose a unique name.
    *   **`ServerConnectionException` (ETOUR):** Simulates and handles scenarios where communication with the backend system is interrupted, providing appropriate feedback to the operator. This ensures the application is robust to network issues.

This user manual should help "Agency Operators" effectively use the Tag Management System.
```