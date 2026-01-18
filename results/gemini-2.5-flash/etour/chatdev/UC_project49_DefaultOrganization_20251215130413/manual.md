```
# Personal Favorites Viewer (VISUALIZZASITIPREFERITI)

## Introduction

The "Personal Favorites Viewer" is a simple JavaFX application designed to demonstrative the `VISUALIZZASITIPREFERITI` (View Personal Favorites) use case. This application allows a simulated "Tourist" user to retrieve and display a list of their personal favorite sites from a mock `ETOUR` server. It showcases basic UI interaction, background task execution to maintain responsiveness, and error handling for common issues like server connection interruptions.

## Main Functions

The application's core functionality is to:

*   **Display Personal Favorites**: Upon user request, it fetches a list of favorite sites (with names and URLs) from a simulated backend service.
*   **User Authentication (Simulated)**: It includes a simulated authentication step, ensuring the user is "logged in" before attempting to fetch data.
*   **Responsive User Interface**: Data fetching is performed on a background thread using JavaFX's `Task` API, preventing the UI from freezing.
*   **Status Feedback**: Provides real-time feedback to the user on the status of the operation (e.g., "Loading...", "Successfully loaded...", "Error: Connection interrupted.").
*   **Error Handling**: Specifically handles simulated `ConnectionException` errors from the `ETOUR` server, informing the user about connectivity issues.

## Setup and Installation

To run this application, you will need to have a Java Development Kit (JDK) installed, preferably version 17 or higher, and the JavaFX SDK.

### Prerequisites

1.  **Java Development Kit (JDK)**: Install JDK 17 or newer. You can download it from Oracle, OpenJDK, AdoptOpenJDK, etc.
    *   Verify installation:
        ```bash
        java -version
        javac -version
        ```
2.  **JavaFX SDK**: This application uses JavaFX for its graphical user interface. Since JavaFX is no longer bundled with the JDK, you need to download the SDK separately.
    *   Download the JavaFX SDK from the official OpenJFX website: [https://gluonhq.com/products/javafx/](https://gluonhq.com/products/javafx/)
    *   Choose the SDK for your operating system and Java version.
    *   Unzip the downloaded archive to a convenient location (e.g., `C:\javafx-sdk-21` on Windows, `/opt/javafx-sdk-21` on Linux/macOS). We will refer to this path as `PATH_TO_FX`.

### Compiling and Running the Application

Given the structure of the provided code, you can compile and run it directly from the command line.

1.  **Save the files**:
    Save all provided `.java` files (`ConnectionException.java`, `Favorite.java`, `ETOURService.java`, `FavoritesViewerApp.java`) into a single directory, for example, `FavoritesApp`.

2.  **Compile the Java files**:
    Open a terminal or command prompt, navigate to the `FavoritesApp` directory, and compile the source files. You need to specify the JavaFX modules.

    ```bash
    # On Windows:
    # Set the path to your JavaFX SDK lib directory
    SET PATH_TO_FX="C:\path\to\javafx-sdk-21\lib"
    javac --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml *.java

    # On Linux/macOS:
    # Set the path to your JavaFX SDK lib directory
    export PATH_TO_FX=/path/to/javafx-sdk-21/lib
    javac --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml *.java
    ```
    *Replace `/path/to/javafx-sdk-21/lib` (or `C:\path\to\javafx-sdk-21\lib`) with the actual path to your JavaFX SDK's `lib` directory.*

3.  **Run the application**:
    After successful compilation, run the application, again specifying the JavaFX modules.

    ```bash
    # On Windows:
    java --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml FavoritesViewerApp

    # On Linux/macOS:
    java --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml FavoritesViewerApp
    ```

## How to Use the Application

Once the application is running, a window titled "Personal Favorites Viewer" will appear.

1.  **Initial State**:
    *   The message "Ready to display favorites." will be shown at the bottom.
    *   The list area will display "No favorites loaded. Click 'Display Favorites'."

2.  **Displaying Favorites**:
    *   Click the "Display Favorites" button.
    *   The status message will change to "Loading favorites..." while the application fetches data from the simulated `ETOURService`.
    *   After a short delay (simulating network latency), one of two things will happen:
        *   **Success**: If the connection is successful, a list of favorite sites (e.g., "Eiffel Tower (https://www.toureiffel.paris)") will appear in the main list area, and the status message will update to "Successfully loaded X favorites."
        *   **Connection Error**: There is a simulated 20% chance of a `ConnectionException`. If this occurs, the status message will display "Error: Interruption of the connection to the server ETOUR." or a similar connection-related error, and the list will remain empty or be cleared.

    **Note**: You can click the "Display Favorites" button multiple times. Each click will attempt to fetch the list again, potentially encountering a connection error due to the random simulation.

3.  **Exiting the Application**:
    *   Close the application window using the standard close button for your operating system. This will properly shut down the application and its background threads.

This application provides a clear demonstration of the `VISUALIZZASITIPREFERITI` use case, displaying personal favorites and gracefully handling potential server connection issues.
```