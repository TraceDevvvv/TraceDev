```markdown
# Statistical Report Viewer Application

## Overview

The Statistical Report Viewer is a Java Swing application designed for an "Agency Operator" to view statistical reports related to specific geographical locations. This tool simulates the `VISUALIZZAREPORTSTATISTICI` use case, allowing operators to select a location and immediately generate a report based on simulated feedback data, emulating a `RicercaSito` (Site Search) service.

## Main Functions

*   **Operator Login Simulation:** The application automatically simulates an agency operator login, fulfilling the initial entry condition for accessing the feature.
*   **Location Loading:** Provides functionality to load a list of available locations from a simulated backend service.
*   **Location Selection:** Allows the operator to select a specific location from the loaded list using a dropdown menu.
*   **Statistical Report Generation:** Fetches and displays a statistical report for the chosen location, including details such as average rating, total feedback count, and the most common feedback type.
*   **Connection Error Simulation:** Includes a feature to deliberately simulate network connection interruptions, demonstrating how the application handles such edge cases specified in the use case.
*   **User-Friendly Interface:** Built with Java Swing, offering a simple graphical user interface for ease of use.

## System Requirements

To compile and run this application, you need:

*   **Java Development Kit (JDK) 8 or higher:** The application is written in Java and requires a compatible JDK installed on your system.

## Setup and Installation

Follow these steps to set up and run the Statistical Report Viewer application:

1.  **Save the Source Files:**
    Save the provided Java code into three separate files in the same directory:
    *   `Location.java`
    *   `StatisticalReport.java`
    *   `RicercaSitoService.java`
    *   `StatisticalReportApp.java`

    For example, create a folder named `StatisticalReportApp` and place all `.java` files inside it.

2.  **Compile the Java Code:**
    Open a terminal or command prompt, navigate to the directory where you saved the `.java` files (e.g., `cd /path/to/StatisticalReportApp`), and compile the source code using the Java Compiler (`javac`):

    ```bash
    javac *.java
    ```
    If compilation is successful, `.class` files will be generated for each Java source file.

3.  **Run the Application:**
    After successful compilation, run the main application class (`StatisticalReportApp`):

    ```bash
    java StatisticalReportApp
    ```

## How to Use the Application

Once you run the `StatisticalReportApp`, a new window will appear, resembling the main interface of the Statistical Report Viewer.

1.  **Initial State:**
    *   Upon launching, the `Agency operator logged in successfully. Please load locations to begin.` message will be displayed in the status bar at the bottom.
    *   The "Select Location" dropdown will be empty and disabled, as no locations have been loaded yet.
    *   The "Generate Report" button will also be disabled.

2.  **Load Locations:**
    *   Click the **"1. Load Locations"** button.
    *   The application will simulate fetching a list of locations from the `RicercaSitoService`. This process includes a simulated network delay.
    *   A status message like `Loading locations...` will appear.
    *   Once loaded, the dropdown will be populated with locations (e.g., "Colosseum, Rome", "Eiffel Tower, Paris"). The status message will update to `Locations loaded successfully. Please select a location.`.
    *   The "Select Location" dropdown and "Generate Report" button will become enabled.

3.  **Select a Location:**
    *   From the **"2. Select Location"** dropdown, choose the location for which you want to view a report.

4.  **Generate Report:**
    *   Click the **"3. Generate Report"** button.
    *   The application will simulate fetching the statistical data for the selected location (this also includes a simulated network delay).
    *   A status message like `Generating report for [Selected Location Name]...` will appear.
    *   Upon successful generation, the "Statistical Report" text area in the center of the window will display the detailed report for the selected location. The status message will confirm `Statistical report generated successfully...`.

5.  **Simulate Connection Error (Optional):**
    *   Before clicking "Load Locations" or "Generate Report", you can check the **"Simulate Connection Error"** checkbox.
    *   If enabled, the next network operation (loading locations or generating a report) has a significantly higher chance of failing with a simulated `IOException` (Interruption of the connection to the server).
    *   The status bar will display an error message in red, and the report area might show an error prompt. This demonstrates the application's handling of network connectivity issues. Uncheck it to resume normal operation.

By following these steps, you can effectively use the Statistical Report Viewer to access and display statistical information for various simulated locations.