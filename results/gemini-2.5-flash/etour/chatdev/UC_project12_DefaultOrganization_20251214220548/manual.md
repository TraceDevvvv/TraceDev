```markdown
# ETOUR Agency Tourist Viewer

This application, developed by ChatDev, provides an Agency Operator with the ability to view details of registered tourists. It simulates interaction with an external "ETOUR" server to retrieve tourist data and demonstrates key functionalities like user authentication, displaying lists, and showing detailed records, while also handling potential connection interruptions.

## Main Functions

The ETOUR Agency Tourist Viewer offers the following core functionalities:

*   **Secure Operator Login**: An agency operator can log in using predefined credentials to access the system.
*   **Tourist List View (`RicercaTurista`)**: Upon successful login, the operator is presented with a list of available tourists. This list simulates the results of a "RicercaTurista" (Search Tourist) use case.
*   **View Tourist Details (`VISUALIZZASCHEDATURISTA`)**: From the tourist list, the operator can select a specific tourist and view their comprehensive details, often referred to as their "card" or profile.
*   **Connection Error Simulation**: The application includes a feature to simulate an interruption of the connection to the ETOUR server, allowing for testing error handling and user feedback.
*   **User-Friendly Interface**: Built using Java Swing, providing a graphical user interface for easy interaction.

## Environment Dependencies

To run this application, you need to have a Java Development Kit (JDK) installed on your system.

*   **Java Development Kit (JDK)**: Version 8 or higher is recommended.
    *   You can download the latest JDK from the official Oracle website or adoptium.net (for OpenJDK).
    *   Ensure that `java` and `javac` commands are accessible from your system's command line (i.e., added to your `PATH` environment variable).

This application does not rely on any external libraries beyond what is provided by a standard JDK installation.

## Installation and Setup

Follow these steps to set up and run the ETOUR Agency Tourist Viewer:

1.  **Save the Code**:
    *   Create a new directory (e.g., `ETOUR_Viewer`) on your computer.
    *   Save all the provided Java code files (`ETOURConnectionException.java`, `Tourist.java`, `ETOURService.java`, `AgencyOperatorSession.java`, `LoginDialog.java`, `TouristListPanel.java`, `TouristDetailPanel.java`, `TouristApp.java`) into this directory. Ensure each file has its exact name as provided.

2.  **Compile the Code**:
    *   Open your command line or terminal.
    *   Navigate to the directory where you saved the Java files:
        ```bash
        cd path/to/your/ETOUR_Viewer
        ```
    *   Compile all `.java` files using the Java compiler:
        ```bash
        javac *.java
        ```
    *   If the compilation is successful, `.class` files will be generated for each `.java` file in the same directory. If there are compilation errors, ensure all files are correctly saved and your JDK is properly set up.

3.  **Run the Application**:
    *   From the same directory in your command line, execute the main application class:
        ```bash
        java TouristApp
        ```
    *   A new Swing window titled "ETOUR Agency Tourist Viewer" should appear.

## How to Use/Play

Here’s a step-by-step guide on how to interact with the application:

### Step 1: Launch the Application

*   Start the application as described in the "Run the Application" section above.

### Step 2: Operator Login

*   Upon launching, a "Operator Login" dialog will appear.
*   **Entry Condition**: The agency operator must be logged in.
*   The application includes mock credentials for demonstration:
    *   **Username**: `admin`
    *   **Password**: `admin`
*   Enter these credentials into the respective fields.
*   Click the **"Login"** button.
*   If login is successful, the dialog will close, and the main application window will transition to the "Tourist List" view.
*   If login fails (e.g., incorrect credentials), an error message will be displayed, and you can try again.

### Step 3: View Tourist List (`RicercaTurista`)

*   After successful login, you will see a panel titled "Tourist List".
*   The system will automatically attempt to load a list of tourists from the simulated ETOUR service.
*   A "Loading tourists..." message might appear briefly.
*   Once loaded, a list of tourist names (e.g., "Mario Rossi (ID: T001)") will be displayed.
*   **Refresh List**: You can click the **"Refresh List"** button to reload the list of tourists. This is useful for testing the connection error simulation feature.

### Step 4: Display Tourist Card (`VISUALIZZASCHEDATURISTA`)

*   From the "Tourist List", click on any tourist name in the list to select it.
*   The **"View Tourist Details"** button will become enabled once a tourist is selected.
*   Click the **"View Tourist Details"** button.
*   **Flow of Events**:
    1.  The system will fetch data for the selected tourist from the ETOUR service.
    2.  The application will switch to the "Tourist Details (Card)" view.
    3.  **Exit Conditions**: The system displays the selected tourist's card, showing details like ID, First Name, Last Name, Email, Phone, and Address.
*   To return to the tourist list, click the **"Back to List"** button.

### Step 5: Simulate ETOUR Connection Error

The application provides a feature to simulate connection problems with the ETOUR server:

1.  From the application's menu bar at the top of the window, click on **"Options"**.
2.  Select the **"Simulate ETOUR Connection Error"** checkbox. A confirmation message will appear.
3.  Now, try to **"Refresh List"** from the Tourist List panel or **"View Tourist Details"** for a selected tourist.
    *   You should now see error messages (e.g., "Connection to ETOUR server interrupted...") indicating the simulated connection failure.
    *   The list or detail panel will show appropriate error states.
4.  To disable the simulation, go back to **"Options"** and uncheck **"Simulate ETOUR Connection Error"**. Then, try refreshing the list or viewing details again, and it should work normally.

### Step 6: Logout and Exit

*   **Logout**:
    *   Go to the **"Options"** menu.
    *   Click **"Logout"**.
    *   A confirmation dialog will appear. Confirm to log out.
    *   Logging out will disable the menu options and bring you back to the login dialog.
*   **Exit Application**:
    *   You can close the application window using the standard window close button (X).
    *   If you are logged in when attempting to close, the system will ask if you wish to log out first. You can choose to log out and exit, exit directly, or cancel the exit.

This completes the user manual for the ETOUR Agency Tourist Viewer.
```