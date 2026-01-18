```markdown
# ETOUR Advanced Search Application User Manual

## Introduction

Welcome to the ETOUR Advanced Search Application User Manual! This software provides a simulated advanced search functionality for tourists looking for points of interest. Acting as a front-end interface, it allows users to specify various criteria to find sites, mimicking a real-world tourist discovery platform. The application is designed to demonstrate key aspects of an advanced search system, including user interaction, data processing, and handling of asynchronous operations.

## Main Functions

The ETOUR Advanced Search Application offers the following core functionalities:

1.  **Personal Area (Simulated Authentication Entry)**
    *   Serves as the initial entry point after a tourist has "authenticated" to the system (authentication itself is simulated and not implemented).
    *   Provides a welcoming interface and a clear option to proceed to the advanced search feature.

2.  **Advanced Search Form**
    *   Presents a comprehensive form where tourists can input their search criteria.
    *   **Keyword:** A general term to search for (e.g., "beach", "historical", "art").
    *   **Category:** Specific type of site (e.g., "Museum", "Park", "Historical", "Monument").
    *   **Max Price Level:** A simulated price scale (0 for Free, 1 for Cheap, 2 for Moderate, 3 for Expensive).
    *   **Your Current Location:** A crucial field where the tourist's current geographical location is entered, which the system uses to process the request contextually.
    *   **Submission:** A dedicated button to submit the filled form and initiate the search.

3.  **Search Results Display**
    *   After submission, the system processes the request (simulating network delays and potential server interactions).
    *   **Results List:** Displays a formatted list of sites matching the search criteria.
    *   **Status/Error Messages:** Provides feedback on the search progress, informs if no results were found, or displays error messages if the simulated connection to the ETOUR server is interrupted.
    *   **Performance Indication:** If the simulated search processing time exceeds 15 seconds, a warning message is displayed, highlighting a key quality requirement from the use case.
    *   **Navigation:** Includes a "Back to Search Form" button to allow users to refine their search or start a new one.

## Environment Dependencies

This project is developed in Java and relies on standard Java SE libraries. To run this application, you will need:

*   **Java Development Kit (JDK) 8 or higher:** The JDK includes the Java Runtime Environment (JRE) and development tools necessary to compile and run Java applications.

### Installation Steps for Environment Dependencies:

1.  **Check if Java is installed:**
    *   Open your command prompt (Windows) or terminal (macOS/Linux).
    *   Type `java -version` and press Enter.
    *   If Java is installed, you will see version information. Ensure it's JDK 8 or newer.
    *   If you see an error like "command not found" or an old version, proceed to step 2.

2.  **Download and Install JDK:**
    *   Visit the official Oracle Java SE Downloads page or adoptium.net (for an OpenJDK distribution like Eclipse Temurin).
    *   Download the appropriate JDK installer for your operating system (Windows, macOS, Linux).
    *   Follow the installation instructions provided by the installer.
    *   After installation, you might need to set the `JAVA_HOME` environment variable and add the JDK's `bin` directory to your system's `PATH`. Instructions for this are usually provided during or after installation by Oracle or can be easily found online for your specific OS.

3.  **Verify Installation:**
    *   After installation and setting up environment variables (if necessary), open a **new** command prompt or terminal.
    *   Type `java -version` and `javac -version` (for the Java compiler) again. You should now see the installed JDK version.

## How to Use/Play the Application

Follow these steps to compile and run the ETOUR Advanced Search Application:

1.  **Save the Code:**
    *   Create a root directory for the project, for example, `ETOUR_Search_App`.
    *   Inside `ETOUR_Search_App`, create the following package directories: `src/main/java/model`, `src/main/java/service`, `src/main/java/gui`.
    *   Place `Main.java` directly under `src/main/java/`.
    *   Place `Site.java`, `SearchCriteria.java` under `src/main/java/model/`.
    *   Place `SearchService.java` under `src/main/java/service/`.
    *   Place `AdvancedSearchApp.java`, `AdvancedSearchFormPanel.java`, `PersonalAreaPanel.java`, `SearchResultsPanel.java`, `SearchSubmitListener.java` under `src/main/java/gui/`.

    Your directory structure should look something like this:
    ```
    ETOUR_Search_App/
    ├── src/
    │   └── main/
    │       └── java/
    │           ├── Main.java
    │           ├── model/
    │           │   ├── Site.java
    │           │   └── SearchCriteria.java
    │           ├── service/
    │           │   └── SearchService.java
    │           └── gui/
    │               ├── AdvancedSearchApp.java
    │               ├── AdvancedSearchFormPanel.java
    │               ├── PersonalAreaPanel.java
    │               ├── SearchResultsPanel.java
    │               └── SearchSubmitListener.java
    ```

2.  **Compile the Application:**
    *   Open your command prompt or terminal.
    *   Navigate to the `ETOUR_Search_App` directory:
        ```bash
        cd ETOUR_Search_App
        ```
    *   Compile all Java source files. The `-d classes` flag tells the compiler to put the compiled `.class` files into a `classes` directory (you might need to create this first, or the compiler will create it):
        ```bash
        mkdir classes
        javac -d classes src/main/java/Main.java \
              src/main/java/model/*.java \
              src/main/java/service/*.java \
              src/main/java/gui/*.java
        ```
        *Self-correction*: A simpler way to compile all is to use `find` or just specify the base `src` directory if your `CLASSPATH` is set correctly, but for simplicity and guaranteeing all files are found, specifying them explicitly or using a wildcard in the package path is more robust for a small project. Let's use `src/main/java/**/*.java` with `jar` command or compile from `src/main/java` directory. The above specific command is correct.

3.  **Run the Application:**
    *   From the `ETOUR_Search_App` directory, run the `Main` class. The `-cp classes` flag tells the Java Virtual Machine where to find the compiled classes:
        ```bash
        java -cp classes Main
        ```

4.  **Application Flow:**

    *   **Step 1: Personal Area:**
        *   Upon launching, you will see the "ETOUR Advanced Search" window displaying the "Personal Area" panel.
        *   This panel simulates the tourist's authenticated state.
        *   Click the **"Enable Advanced Search"** button.

    *   **Step 2: Advanced Search Form:**
        *   The application will switch to the "Advanced Search Criteria" form.
        *   You can now fill in your desired search parameters:
            *   **Keyword:** Try "museum", "park", "tower", or leave blank for a broader search.
            *   **Category:** Select from "Any", "Museum", "Park", "Historical", "Monument".
            *   **Max Price Level:** Use the slider. The default is 'Moderate' (2). Note that mock data might not include "Free" (0) sites, so selecting 0 might yield no results.
            *   **Your Current Location:** **This field is required.** The system uses this to contextualize your search. For example, try "Paris", "New York", "Rome", "Barcelona", or "Japan". The mock data includes sites in these locations. If you leave it empty and try to submit, you'll receive a warning dialog.
        *   Once satisfied with your criteria, click the **"Search Sites"** button.

    *   **Step 3: Search Results:**
        *   The application will switch to the "Search Results / Status" panel.
        *   You will initially see a "Searching..." message. The application simulates network delays and processing time, which can range from 5 to 20 seconds.
        *   **Observe Simulated Events:**
            *   **Successful Search:** If successful, a list of matching tourist sites will be displayed.
            *   **Performance Warning:** If the search takes longer than 15 seconds (a simulated outcome, approximately 1 in 4 searches might exceed this), a warning will be appended to the results indicating the longer processing time.
            *   **Connection Interruption:** Approximately 1 in 5 searches will simulate a "Connection to ETOUR server interrupted" error, demonstrating an exit condition from the use case.
            *   **No Results:** If no sites match your specific criteria, a message indicating "No results found" will be displayed.

    *   **Step 4: Continue or Exit:**
        *   After viewing the results, you can click the **"Back to Search Form"** button to perform another search with different criteria.
        *   You can close the application window at any time to exit.

This completes your tour of the ETOUR Advanced Search Application. Enjoy exploring the simulated world of tourist site discovery!
```