```markdown
# News Management System: Modify News (MODIFICANEWS)

This document provides a user manual for the News Management System application, focusing on the functionality to modify existing news items.

## 🤔 What is this application?

The News Management System is a JavaFX application designed to help Agency Operators manage news content. This particular version implements the "MODIFICANEWS" use case, allowing operators to view a list of news articles, select one, and update its details such as title, content, author, and publication date. It includes features like data validation, transaction confirmation, and simulated error handling for server interruptions.

## 🚀 Main Functions

The application provides the following core functionalities:

1.  **Operator Login**: A simple login screen to access the application. Any non-empty username and password will grant access for demonstration purposes.
2.  **View All News**: After logging in, the system displays a comprehensive list of all available news articles in a table format. Each news item shows its ID, Title, Author, and Date.
3.  **Select News for Editing**: Operators can select a specific news item from the list to initiate the editing process. This can be done by clicking "Edit Selected News" or by double-clicking a row.
4.  **Edit News Details**: Upon selection, the application loads the details of the chosen news item into an editable form. The News ID is read-only.
5.  **Modify and Submit Data**: Operators can change the title, content, author, and date of the news. After making changes, they can submit the updated information.
6.  **Data Validation and Confirmation**: Before saving, the system validates the modified data (e.g., checks for empty fields, proper date format). If valid, it presents a confirmation dialog.
7.  **Store Modified News**: Once confirmed, the system saves the updated news data.
8.  **Cancel Operation**: Operators can cancel the editing process at any point, discarding all unsaved changes and returning to the news list.
9.  **Simulated Server Interruption (ETOUR)**: A special toggle button is provided in the edit view to simulate a server connection interruption. This allows testing the application's behavior when network issues prevent data saving.

## 🛠️ Installation Guide

To run this JavaFX application, you need to have a Java Development Kit (JDK) and the JavaFX SDK configured.

### Prerequisites

*   **Java Development Kit (JDK) 17**: Ensure you have JDK 17 installed on your system. You can download it from Oracle or use an OpenJDK distribution like Adoptium.
    *   Verify installation: Open a terminal or command prompt and type `java -version`. It should show "Java 17".
*   **JavaFX SDK 17**: Since JavaFX is no longer bundled with the JDK since Java 11, you need to download the JavaFX SDK separately.
    *   Download from OpenJFX: [https://openjfx.io/](https://openjfx.io/)
    *   Extract the downloaded SDK to a convenient location (e.g., `C:\javafx-sdk-17` on Windows or `/opt/javafx-sdk-17` on Linux/macOS).

### Setting up the Project

1.  **Project Structure**: The provided code assumes a standard Maven or Gradle project structure, or a simple flat directory structure for sources.
    *   `src/main/java/com/chatdev/newsapp/` for `.java` files.
    *   `src/main/resources/com/chatdev/newsapp/views/` for `.fxml` files.

    If you're using an IDE like IntelliJ IDEA or Eclipse, import the project and ensure the JavaFX SDK is correctly linked as a library or module.

2.  **Manual Compilation and Execution (Command Line)**:
    If you're compiling and running from the command line, you need to specify the JavaFX modules.

    *   **Create the `mods` directory**: Create a directory named `mods` inside your project root.
    *   **Copy JavaFX modules**: From your downloaded JavaFX SDK (e.g., `javafx-sdk-17/lib`), copy all the JAR files (e.g., `javafx.controls.jar`, `javafx.fxml.jar`, `javafx.graphics.jar`, etc.) into your project's `mods` directory.

    Here's a general approach using command line (adapt paths as needed):

    Replace `PATH_TO_FX` with the path to your JavaFX SDK `lib` directory (e.g., `C:\javafx-sdk-17\lib` or `/opt/javafx-sdk-17/lib`).

    **Compile:**
    ```bash
    javac --module-path PATH_TO_FX --add-modules javafx.controls,javafx.fxml,javafx.graphics -d out src/main/java/com/chatdev/newsapp/*.java src/main/java/com/chatdev/newsapp/controllers/*.java src/main/java/com/chatdev/newsapp/models/*.java src/main/java/com/chatdev/newsapp/serv/*.java
    ```
    *Note: You might need to adjust the `javac` command to correctly include all packages and resource files (like FXML files). For FXML, you usually copy them into the `out` directory to maintain the resource path hierarchy.*

    **Run:**
    ```bash
    java --module-path PATH_TO_FX --add-modules javafx.controls,javafx.fxml,javafx.graphics -cp out -Djavafx.fxml.debug=true com.chatdev.newsapp.Main
    ```
    *Note: For a more robust setup, especially with resources, consider using a build tool like Maven or Gradle, or packaging the application as a self-contained JAR/Installer.*

## 🎮 How to Use/Play

Follow these steps to interact with the News Management System:

### 1. Launch the Application

*   Run the `Main` class from your IDE, or execute the compiled application from the command line (as shown in the installation section).
*   The **Login** view will appear.

### 2. Login as an Agency Operator

*   In the Login view, enter any non-empty **Username** (e.g., `operator`) and **Password** (e.g., `secure`).
*   Click the "**Login**" button.
*   Upon successful login, you will be directed to the **Available News Items** list.

### 3. View the News List

*   The News List view displays existing news articles in a table.
*   You can click the "**Refresh News List**" button to reload the data.

### 4. Select and Edit a News Item

*   **To select a news item**: Click on any row in the table to highlight it.
*   **To edit**: With a news item selected, click the "**Edit Selected News**" button. Alternatively, you can simply **double-click** on any row in the table to directly open its edit form.
*   The **Edit News Item** view will open, pre-filled with the details of the selected news. The "News ID" field is disabled and cannot be changed.

### 5. Modify News Data

*   Change the values in the "**Title**", "**Content**", "**Author**", and "**Date (YYYY-MM-DD)**" fields as desired.
*   **Important**: The date needs to be in `YYYY-MM-DD` format (e.g., `2023-11-01`).

### 6. Save or Cancel Changes

#### 6.1. Save Changes

*   Click the "**Save Changes**" button.
*   **Validation**: The system will first check if all fields are filled and if the date format is correct.
    *   If there are validation errors, an "Validation Error" alert will appear, informing you what needs to be corrected (Use case "Errored").
    *   If data is valid, a **confirmation dialog** will appear, summarizing your changes and asking "Are you sure you want to save the changes?".
*   **Confirm Save**: If you click "**OK**" in the confirmation dialog:
    *   The changes will be saved to the system.
    *   A "Success" alert will appear: "News updated successfully!".
    *   You will be automatically returned to the News List view, where you can refresh to see the updated news.
*   **Cancel Confirmation**: If you click "**Cancel**" in the confirmation dialog, the save operation is aborted, and you remain on the edit screen.

#### 6.2. Cancel Editing

*   To discard all changes and return to the News List view without saving, click the "**Cancel**" button.
*   You will be returned to the News List view. (This corresponds to "The Operator Agency cancels the operation" exit condition).

### 7. Simulate Server Connection Interruption (ETOUR)

*   In the **Edit News Item** view, you will see a button labeled "**Simulate Server Failure**".
*   Clicking this button will toggle the simulation of a server connection problem.
    *   If it says "Simulate Server Failure", clicking it will activate the simulation. An alert "Simulation Activated" will confirm this.
    *   If it says "Deactivate Server Failure", clicking it will deactivate the simulation. An alert "Simulation Deactivated" will confirm this.
*   **Testing ETOUR**:
    1.  Click "Simulate Server Failure" to activate it.
    2.  Try to save changes to the news item.
    3.  You will receive an "Connection Error (ETOUR)" alert, indicating that the save failed due to the simulated interruption (This corresponds to "Interruption of the connection to the server ETOUR" exit condition).
    4.  You can then click "Deactivate Server Failure" and try saving again, which should now succeed.

This manual covers the essential steps to navigate and utilize the News Management System for modifying news items, including its error handling mechanisms.
```