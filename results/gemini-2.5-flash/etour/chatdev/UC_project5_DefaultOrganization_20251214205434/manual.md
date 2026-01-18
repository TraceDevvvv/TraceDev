```markdown
# Cultural Goods Viewer Application

## 🧐 Introduction

The "Cultural Goods Viewer" application (codenamed `VISUALIZZASCHEDABENECULTURALE`) is a desktop tool designed for an Agency Operator to efficiently browse and view detailed information about various cultural goods. This application simulates interaction with an external "ETOUR" server to load a list of cultural items and, upon selection, display their complete details. It's built with a user-friendly graphical interface in Java Swing.

### Key Functions:

*   **List Cultural Goods:** Displays a comprehensive list of available cultural items in a `JList`, mimicking the output of a "RicercaBeneCulturale" (Cultural Good Search) use case.
*   **View Cultural Good Details:** Upon selecting an item from the list, the user can activate a function (via a button click) to display its specific details, including ID, name, description, location, and type, in a dedicated details panel.
*   **Simulated ETOUR Server Interaction:** The application includes a mock service (`ETOURService`) that simulates data retrieval from an external server. This service incorporates network latency and can be configured to simulate connection errors.
*   **Robust Error Handling:** The system is designed to catch and report `ETOURConnectionException`s, providing clear feedback to the user via a status bar if the simulated connection to the ETOUR server is interrupted or if a selected item's details cannot be retrieved.

## 🛠️ Environment Dependencies

To compile and run this application, you will need:

*   **Java Development Kit (JDK):** Version 8 or newer. The application is written in Java and uses standard Java libraries and Swing for its graphical user interface.

## 🚀 How to Use/Play It

Follow these steps to get the Cultural Goods Viewer application up and running.

### 1. Compile the Source Code

Ensure you have a Java Development Kit (JDK) installed and configured correctly on your system. You can verify this by running `java -version` and `javac -version` in your terminal or command prompt.

1.  **Organize Files:** Place all the provided `.java` files into a directory structure that matches their package declarations. For example, if you create a main project folder, `src` should contain:
    *   `src/com/chatdev/culturalviewer/model/BeneCulturale.java`
    *   `src/com/chatdev/culturalviewer/service/ETOURConnectionException.java`
    *   `src/com/chatdev/culturalviewer/service/ETOURService.java`
    *   `src/com/chatdev/culturalviewer/gui/CulturalGoodDetailPanel.java`
    *   `src/com/chatdev/culturalviewer/gui/CulturalGoodViewerApp.java`

2.  **Navigate to Project Root:** Open your terminal or command prompt and navigate to the directory that directly contains your `src` folder (e.g., if `src` is in `my_app/`, navigate to `my_app/`).

3.  **Compile:** Use the Java compiler (`javac`) to compile all source files.

    ```bash
    javac src/com/chatdev/culturalviewer/model/*.java \
          src/com/chatdev/culturalviewer/service/*.java \
          src/com/chatdev/culturalviewer/gui/*.java
    ```
    *Alternatively, you can compile all Java files recursively from the `src` directory:*
    ```bash
    javac src/com/chatdev/culturalviewer/**/*.java
    ```

### 2. Run the Application

After successful compilation, you can execute the main application class.

1.  **Navigate to Project Root:** Ensure your terminal is still in the root directory that contains your `src` folder.

2.  **Run:** Execute the `CulturalGoodViewerApp` class.

    ```bash
    java -cp src com.chatdev.culturalviewer.gui.CulturalGoodViewerApp
    ```

### 3. Interacting with the Application

Upon launching, the "Cultural Goods Viewer" application window will appear. Here's how to interact with its components:

*   **Left Panel:**
    *   **Cultural Goods List:** This list will automatically populate with cultural goods upon application startup, simulating a successful `RicercaBeneCulturale` operation.
    *   **"View Details of Selected Cultural Good" Button:** Click this button after you have selected a cultural good from the list to load and display its detailed information.
    *   **"Toggle ETOUR Server Connection Error (OFF)" Button:** This special button allows you to simulate network connection issues with the ETOUR server. When clicked, its text will change to `(ON)`, indicating that subsequent data retrieval attempts (both list loading and detail viewing) may result in a `ETOURConnectionException`. Click it again to turn off error simulation.

*   **Right Panel:**
    *   **Cultural Good Details:** This panel initially displays "N/A" for all fields. Once you select an item from the list and click "View Details", it will populate with the ID, Name, Description, Location, and Type of the selected cultural good.

*   **Status Bar (Bottom of the window):**
    *   This bar provides real-time feedback on application actions. Messages here will indicate loading progress, successful operations, or any errors (especially `ETOURConnectionException`s) that occur during data fetching.

#### Usage Flow:

1.  **Initial Application Load:** The application will begin by attempting to fetch the list of cultural goods. You will see a "Loading cultural goods list..." message in the status bar.
2.  **Select a Cultural Good:** Once the list is loaded, click on any item in the left list (e.g., "Colosseum", "Mona Lisa") to highlight it.
3.  **View Details:** Click the "View Details of Selected Cultural Good" button. The right-hand panel will then update to display the comprehensive details of the chosen cultural good. The status bar will confirm success or report any issues.
4.  **Simulate Connection Interruption (Optional):**
    *   To test error handling, click the "Toggle ETOUR Server Connection Error (OFF)" button. Its text will change to "Toggle ETOUR Server Connection Error (ON)".
    *   Now, try selecting another cultural good and clicking "View Details". You should observe an "ETOUR Connection Error" message in the status bar, and the details panel will likely remain blank or clear, demonstrating how the system handles `ETOURConnectionException` (as per the "Interruption of the connection to the server ETOUR" exit condition in the use case).
    *   You can click the "Toggle ETOUR Server Connection Error (ON)" button again to resume normal, error-free operation.
5.  **Observe Status Messages:** Always keep an eye on the status bar for important messages regarding loading, success, or errors.

This manual provides a complete guide to installing, running, and interacting with the Cultural Goods Viewer application.
```