```markdown
# ETOUR: View Site Details Application

Welcome to the ETOUR: View Site Details application! This tool allows tourists to view detailed information about various sites, simulating a real-world scenario where data is fetched from a database.

## 🤔 What is this?

This application is a simple Java Swing-based graphical user interface (GUI) designed to demonstrate the `VISUALIZZASCHEDASITO` use case from a tourist management system. It enables a user (simulated as a tourist) to select a site from a predefined list and view its comprehensive details, such as name, location, rating, and description. A key feature is the ability to simulate a database connection interruption, showcasing how the system handles such edge cases.

## ✨ Main Functions

*   **Site Selection**: Choose a site from a dropdown list of available tourist destinations.
*   **Detail Display**: Upon selection, the application fetches and neatly displays the chosen site's ID, Name, Location, Rating, and Description.
*   **Connection Simulation**: A toggle button allows users to simulate the interruption and restoration of the connection to the ETOUR server (database). This helps in understanding how the system reacts to network unavailability.
*   **Error Handling**: Provides clear messages for successful data loading, site not found, or database connection errors.

## 🛠️ Environment Dependencies

To compile and run this Java application, you will need the following installed on your system:

*   **Java Development Kit (JDK)**: Version 8 or newer is recommended.
    *   You can download the latest JDK from the official Oracle website or adoptium.net (for OpenJDK).
    *   Ensure that the `JAVA_HOME` environment variable is set and `java` and `javac` commands are accessible from your terminal/command prompt.

## 🚀 How to Use/Play

Follow these steps to get the application up and running:

### 1. Save the Code

1.  Create a new directory (e.g., `ETOUR_App`) on your computer.
2.  Save each of the provided Java code blocks (e.g., `Site.java`, `SiteNotFoundException.java`, `DatabaseConnectionException.java`, `SiteDatabase.java`, `SiteDetailPanel.java`, `TouristApp.java`) into separate files with their respective `.java` extensions within this `ETOUR_App` directory. Ensure all files are in the same folder.

### 2. Compile the Code

1.  Open your terminal or command prompt.
2.  Navigate to the `ETOUR_App` directory where you saved the Java files.
    ```bash
    cd path/to/ETOUR_App
    ```
3.  Compile all the Java source files. This creates `.class` files for each Java file.
    ```bash
    javac *.java
    ```
    *   If there are no errors, the compilation will complete silently. If you encounter errors, ensure your JDK is correctly installed and configured, and the `.java` files are saved correctly.

### 3. Run the Application

1.  After successful compilation, run the main application class, `TouristApp`.
    ```bash
    java TouristApp
    ```
2.  A new GUI window titled "ETOUR: View Site Details" will appear.

### 4. Interact with the Application

*   **Select a Site**:
    1.  At the top of the window, locate the "Select Site:" dropdown menu.
    2.  Click on the dropdown and choose one of the available site IDs (e.g., "S001 - Eiffel Tower", "S002 - Colosseum", etc.).
    3.  The `Site Detail Panel` below will then display the comprehensive information for the selected site. A green "Details loaded successfully." message will confirm.

*   **Simulate Connection Interruption**:
    1.  Click the "Simulate Server Connection ON" toggle button. It will change to "Simulate Server Connection OFF" and its color will turn red, indicating a simulated connection loss.
    2.  Try selecting a site from the dropdown now. The application will display an error message like "Error: Connection to server ETOUR interrupted." in red.
    3.  Click the toggle button again to restore the connection (it will turn green).
    4.  Select a site again to see its details load successfully.

*   **Simulate Site Not Found (Developer Note)**:
    *   While there isn't a direct UI element to trigger a "Site Not Found" error for *valid* site IDs, the internal `SiteDatabase` logic would throw this if an invalid `siteId` were passed. In this UI, all IDs in the dropdown are valid.
    *   However, if you quickly switch the connection OFF *while* a site is being loaded (or immediately before), you'll see the connection error.

This application provides a hands-on experience for understanding basic GUI interaction, simulated database access, and error handling in a Java Swing application.
```