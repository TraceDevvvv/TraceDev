```
# Ricerca Bene Culturale - Cultural Object Search Application

## Introduction

The "Ricerca Bene Culturale" (Cultural Object Search) application is a lightweight desktop tool designed to help users search and retrieve information about various cultural objects. This application provides a user-friendly interface where users can input specific criteria such as the object's name, type, or location, and then view a list of cultural goods that match their query. It simulates interaction with a backend repository, complete with potential connection interruptions to demonstrate robust error handling.

## Main Functions

*   **Search Cultural Objects**: Users can search for cultural objects based on multiple parameters:
    *   **Name**: Search by the name or part of the name of the cultural object.
    *   **Type**: Filter results by the type or category of the cultural object (e.g., "Painting", "Sculpture").
    *   **Location**: Find objects based on their physical location.
*   **Display Search Results**: Matching cultural objects are displayed in a clear, tabular format, showing their ID, Name, Type, Location, and Description.
*   **Dynamic UI Updates**: The application uses SwingWorker for background processing, ensuring the user interface remains responsive during search operations.
*   **Error Handling**: It includes a simulated mechanism for server connection interruptions (e.g., to an "ETour Server") and provides informative alert messages to the user if such an error occurs.
*   **No Results Feedback**: Clearly informs the user if no cultural objects are found for the given search criteria.

## System Requirements

To run this application, you need:

*   **Java Development Kit (JDK) 11 or higher**: The application is written in Java and requires a compatible JDK environment for compilation and execution.

## Installation and Setup Guide

Follow these steps to set up and run the Ricerca Bene Culturale application on your system.

### 1. Install Java Development Kit (JDK)

If you don't have Java JDK 11 or a later version installed, you'll need to install it first.

*   **Download JDK**: Visit the official Oracle website or OpenJDK website to download the appropriate JDK installer for your operating system (Windows, macOS, Linux).
    *   For example, search for "JDK 11 download" or "OpenJDK 17 download".
*   **Install JDK**: Follow the installation wizard instructions.
*   **Verify Installation**: Open your command line or terminal and type:
    ```bash
    java -version
    javac -version
    ```
    You should see output indicating your installed Java version (e.g., `openjdk version "17.0.x"`).

### 2. Obtain the Source Code

You will need the Java source files (`.java` files) provided:

*   `RicercaBeneCulturaleApp.java`
*   `CulturalObjectSearchPanel.java`
*   `CulturalObject.java`
*   `CulturalObjectRepository.java`
*   `EtourConnectionException.java`

Place all these `.java` files into a single directory (e.g., `RicercaBeneCulturale`).

### 3. Compile the Source Code

Navigate to the directory where you saved the `.java` files using your command line or terminal.

```bash
cd path/to/RicercaBeneCulturale
```

Once inside the directory, compile all the Java files:

```bash
javac *.java
```

If the compilation is successful, you will see `.class` files generated in the same directory for each `.java` file. If there are any errors, ensure your JDK is correctly installed and configured in your system's PATH.

### 4. Run the Application

After successful compilation, you can run the application from the command line:

```bash
java RicercaBeneCulturaleApp
```

## How to use the Application

Once you run the command in the previous step, a window titled "Ricerca Bene Culturale - Cultural Object Search" will appear.

1.  **Activate Search Functionality**: The application window opens directly to the search interface.
2.  **Show the Form for Research**: The main panel displays three text fields for search parameters: "Name", "Type", and "Location", along with a "Search Cultural Objects" button and an empty table for results.
3.  **Fill in the Search Form and Submit**:
    *   **Enter Search Criteria**: You can type values into one or more of the `Name`, `Type`, or `Location` fields. These fields support partial, case-insensitive matching.
        *   Example:
            *   To find "Mona Lisa", you can type "Mona" in `Name`.
            *   To find all paintings, type "Painting" in `Type`.
            *   To find objects in Paris, type "Paris" in `Location`.
            *   You can combine criteria (e.g., "Mona" in `Name` and "Painting" in `Type`).
    *   **Submit Your Search**: Click the "**Search Cultural Objects**" button.
4.  **Processing the Request**:
    *   The application will process your request. During this time, the "Search Cultural Objects" button will be temporarily disabled to prevent multiple simultaneous searches.
    *   There's a simulated delay (100-300ms) to mimic network latency or database query time, fulfilling the "return results within a set time" quality requirement.
    *   **Simulated Connection Error**: Approximately 10% of the time, the application will simulate an "ETour Server Connection Interruption". If this happens, an "Connection Error" dialog box will appear, informing you about the issue. You can then close the error dialog and try searching again.
5.  **View Results**:
    *   **Cultural Objects Found**: If cultural objects match your search criteria, they will be listed in the table at the bottom of the window. Each row will display the object's ID, Name, Type, Location, and Description.
    *   **No Cultural Objects Found**: If no objects match your criteria, an "Information" dialog box will appear stating "No cultural objects found matching the criteria."
    *   **Unexpected Error**: In rare cases of unexpected system errors, an "Error" dialog will appear.

You can perform new searches by changing the input fields and clicking the search button again.
To exit the application, simply close the main window.

Enjoy exploring the cultural objects!
```