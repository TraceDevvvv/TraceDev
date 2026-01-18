```markdown
# Cultural Heritage Search Application

## Research and Cultural Heritage for All

This Java-based application provides a user-friendly interface for guest users to search for cultural heritage sites. Designed with simplicity in mind, it allows users to discover sites based on various criteria, simulating a real-world interaction with a cultural heritage database (referred to as ETOUR server in the use case).

## 🤔 What is this?

The Cultural Heritage Search Application is a standalone Java Swing application that enables a "Guest User" to search for cultural sites. It demonstrates key functionalities like:

*   **Search Activation**: The application presents a search form upon startup, simulating a user logging on and activating search.
*   **Form Submission**: Users can input keywords, specify a location, and select a category to narrow down their search.
*   **Location-Aware Search**: The system intelligently uses the user's current (simulated) geographical location if no specific location is provided in the search form, enhancing relevance.
*   **Results Display**: Matching cultural sites are displayed clearly in the application interface.
*   **ETOUR Server Interaction**: The application simulates connecting to an external server (ETOUR) for data retrieval, including handling potential connection interruptions.

## 📖 Main Functions

The application's core functionalities are centered around the search and display of cultural heritage information:

1.  **Intuitive Search Form**: A dedicated panel at the top allows users to enter:
    *   **Keyword**: For general searches across site names and descriptions.
    *   **Location**: To find sites in a specific city or region.
    *   **Category**: To filter by types such as "Museum", "Archaeological Site", "Historical Building", or "All".
2.  **Dynamic Search Logic**: When a search is performed, the system:
    *   Retrieves the user's current (simulated) location.
    *   Prioritizes the location provided in the search form; otherwise, defaults to the user's actual location for the search.
    *   Filters a predefined list of cultural sites based on all provided criteria.
3.  **Clear Results Display**: A spacious results panel at the bottom of the window shows:
    *   A list of all cultural sites that match the user's criteria.
    *   Informative messages, like "No results found" or "Searching...".
4.  **Robust Error Handling**:
    *   If a simulated connection interruption to the ETOUR server occurs (there's a built-in chance of this happening for demonstration), an error message is clearly displayed to the user.
    *   General unexpected errors are also caught and reported.

## 🛠️ Installation and Environment Dependencies

To run this application, you will need a Java Development Kit (JDK) installed on your system.

**Prerequisites:**

*   **Java Development Kit (JDK) 8 or higher**: Ensure you have a compatible JDK installed. You can download it from the official Oracle website or adoptium.net (for OpenJDK).
    *   To check if Java is installed, open your terminal or command prompt and type:
        ```bash
        java -version
        javac -version
        ```
        If these commands return version information, Java is correctly installed.

**Installation Steps:**

1.  **Save the Code**: Save all the provided `.java` files into a single directory (e.g., `CulturalHeritageApp`). The files are:
    *   `CulturalHeritageApp.java`
    *   `CulturalSite.java`
    *   `SearchCriteria.java`
    *   `ETOURConnectionException.java`
    *   `LocationService.java`
    *   `SearchService.java`
    *   `SearchListener.java`
    *   `SearchPanel.java`
    *   `ResultPanel.java`

2.  **Compile the Code**: Open a terminal or command prompt, navigate to the directory where you saved the files, and compile all Java source files using the Java compiler:
    ```bash
    javac *.java
    ```
    If compilation is successful, several `.class` files will be created in the same directory.

## 🚀 How to Use/Play

Once the code is compiled, you can run the application.

1.  **Run the Application**: From the same terminal or command prompt, execute the main application class:
    ```bash
    java CulturalHeritageApp
    ```

2.  **Application Window Appears**: A new window titled "Cultural Heritage Search" will appear. This is the main interface. You will see two main sections:
    *   **"Search Cultural Heritage Sites" (Top Panel)**: This is your search form.
    *   **"Search Results" (Bottom Panel)**: This area will display messages and search results. Initially, it will show "Welcome, Guest! Please enter your search criteria."

3.  **Fill Out the Search Form**:
    *   **Keyword**: Type any word or phrase you want to search for, e.g., "museum", "ancient", "tower".
    *   **Location**: Enter a desired geographical location, e.g., "Rome, Italy", "Paris, France". If this field is left empty, the search will default to using the user's simulated current location ("Rome, Italy" in this demo).
    *   **Category**: Select a category from the dropdown list (e.g., "All", "Museum", "Archaeological Site", "Historical Building").

4.  **Perform Search**: Click the "Search" button.

5.  **View Results**:
    *   The "Search Results" panel will first show "Searching..." as it processes your request.
    *   After a short delay (simulating network activity), it will display a list of cultural sites that match your criteria. Each site is listed with its name, category, and location.
    *   If no sites match, a "No cultural sites found..." message will appear.

6.  **Simulated ETOUR Server Interruption**:
    *   Periodically, when you click "Search" (approximately 20% of the time, as simulated in `SearchService.java`), you might encounter an error message like "Connection to ETOUR server interrupted...". This demonstrates the system's handling of external service failures. Simply try searching again if this happens.

7.  **Experiment**: Try different combinations of keywords, locations, and categories to explore the dummy data. For example:
    *   Search for "museum" in "Paris, France".
    *   Search for "ancient" with no specific location.
    *   Search for "Archaeological Site" in "Rome, Italy".

8.  **Exit the Application**: Close the window using the standard 'X' button or `Alt+F4` (Windows) / `Cmd+Q` (macOS).

Enjoy exploring the simulated world of cultural heritage!
```