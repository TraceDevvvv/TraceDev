# VISUALIZZASTORICOCONVENZIONI - View Convention History

## 🤔 What is this?

The "VISUALIZZASTORICOCONVENZIONI" (View Convention History) application is a Java-based desktop tool designed for an Agency Operator. Its primary function is to allow the operator to view the historical conventions (agreements) associated with a specific "Point of Rest" (such as a restaurant, hotel, or conference center) to which their agency is designated.

This application provides a graphical user interface (GUI) to access and display past agreements, making it easy to track historical commitments and offers relevant to a selected venue. It simulates interaction with a backend service, including potential connection interruptions.

## 🌟 Main Functions

The application focuses on the following core functionalities, addressing the use case requirements:

*   **Access Convention History Features**: The operator accesses the application's main window, which is dedicated to displaying historical conventions.
*   **Designated Point of Rest Display**: Upon launching, the application immediately identifies and displays the name of the "Point of Rest" to which the Agency Operator is currently designated (e.g., "Restaurant A - The Great Food Place"). This fulfills the operator's condition of operating as a designated point of rest.
*   **Upload Data on Conventions**: By clicking the "Refresh History" button, the operator triggers the system to fetch and "upload" (load) historical convention data from the simulated backend for the designated point of rest.
*   **Tabular Data Display**: All fetched conventions are presented in an organized table, showing key details for each convention, including:
    *   **ID**: A unique identifier for the convention.
    *   **Name**: A descriptive name of the convention.
    *   **Start Date**: The date when the convention officially began.
    *   **End Date**: The date when the convention concluded or expires.
    *   **Terms**: A brief summary or description of the convention's terms and conditions.
*   **Real-time Status Updates**: The application provides real-time status messages at the bottom of the window, informing the user about the data loading process, successful completion, or any encountered errors.
*   **Connection Interruption Handling**: The system is designed to detect and inform the user if there's an interruption in the connection to the simulated ETOUR server, adhering to the specified exit conditions of the use case.
*   **User-Friendly Error Messages**: In case of errors, including simulated connection issues or other unexpected problems during data retrieval, the application displays clear error messages to the user in the status bar and via pop-up dialogs.

## 🛠️ Environment Dependencies

To compile and run this Java application, you will need the following:

*   **Java Development Kit (JDK)**: Version 11 or higher. This includes the Java Runtime Environment (JRE) required to execute the application, and the development tools (like `javac`) needed to compile the source code. You can download the latest JDK from official sources like Oracle or Adoptium (OpenJDK).

No additional third-party libraries or frameworks are explicitly required beyond the standard Java SDK and Swing GUI toolkit.

## 🚀 How to Install and Run

Follow these steps to compile and run the `VISUALIZZASTORICOCONVENZIONI` application locally:

1.  **Save the Source Code**:
    *   Create a base directory for your project, for example, `ConventionViewer`.
    *   Inside this directory, create the package structure: `src/com/chatdev/viscon/`.
    *   Save each provided `.java` file (`ConnectionInterruptionException.java`, `Convention.java`, `ConventionHistoryGUI.java`, `ConventionService.java`, `ConventionTableModel.java`, `PointOfRest.java`) into the `ConventionViewer/src/com/chatdev/viscon/` directory.

    Your directory structure should look like this:
    ```
    ConventionViewer/
    └── src/
        └── com/
            └── chatdev/
                └── viscon/
                    ├── ConnectionInterruptionException.java
                    ├── Convention.java
                    ├── ConventionHistoryGUI.java
                    ├── ConventionService.java
                    ├── ConventionTableModel.java
                    └── PointOfRest.java
    ```

2.  **Open a Terminal or Command Prompt**:
    *   Navigate to the `ConventionViewer` directory (the parent directory of `src`) using your terminal or command prompt.
        ```bash
        cd path/to/ConventionViewer
        ```

3.  **Compile the Java Source Files**:
    *   Use the Java compiler (`javac`) to compile all `.java` files. The `-d .` option ensures that the compiled `.class` files are placed into the corresponding package structure starting from the current directory.
        ```bash
        javac -d . src/com/chatdev/viscon/*.java
        ```
    *   If compilation is successful, you will find a `com/chatdev/viscon/` directory containing all `.class` files inside your `ConventionViewer` directory.

4.  **Run the Application**:
    *   Execute the compiled application using the Java Virtual Machine (`java`). The main class is `ConventionHistoryGUI`.
        ```bash
        java com.chatdev.viscon.ConventionHistoryGUI
        ```

    A new graphical window titled "VISUALIZZASTORICOCONVENZIONI - View Convention History" will appear on your screen, displaying the application interface.

## 💡 Usage

Once the application window appears:

1.  **Designated Point of Rest**: At the top of the window, you will see a label indicating the "Designated Point of Rest". By default, this is set to "Restaurant A - The Great Food Place", simulating the operator's pre-assigned agency.
2.  **Initial Data Load**: Upon starting, the application automatically attempts to load the convention history for this designated point of rest.
3.  **View Table Data**: The central area of the application displays a table. Once conventions are loaded, this table will be populated with the ID, Name, Start Date, End Date, and Terms for each historical convention associated with the designated point of rest.
4.  **Refresh History**: To re-fetch the data (e.g., if you want to check for updates or retry after an error), click the "Refresh History" button.
5.  **Status Bar**: The status bar at the bottom of the window provides real-time feedback. It will show messages like "Loading conventions...", "Successfully loaded X conventions...", or error messages if an issue occurs.
6.  **Error Handling**: If a simulated "Connection to ETOUR server interrupted" error occurs, an error message will appear in the status bar (in red), and a pop-up dialog will inform you of the issue. The table data will be cleared in this case. You can click "Refresh History" again to retry loading the data.

The application is now running and ready for use by the Agency Operator to view the history of conventions.