```
# User Manual: Visualizza Scheda Punto di Ristoro

## 🚀 Introduction

This application, "Visualizza Scheda Punto di Ristoro" (View Point of Rest Details), is designed for an Agency Operator to easily access and view comprehensive details of various "Points of Rest" (e.g., restaurants, cafes, gelaterias). It simulates a scenario where an operator, having searched for points of rest, can select one from a list and retrieve its specific information. The application also includes functionality to simulate a server connection interruption, adhering to specified quality requirements.

## ✨ Core Features

*   **View List of Points of Rest**: Displays a dropdown list of available points of rest, similar to results from a "RicercaPuntoDiRistoro" (Search Point of Rest) use case.
*   **Detailed Information Display**: Upon selection, the application uploads and presents extensive details for the chosen point of rest, including ID, name, address, description, type, capacity, contact phone, and website.
*   **Server Connection Error Simulation**: Allows the user to toggle a simulated connection error to the ETOUR server, demonstrating how the system handles such interruptions.
*   **Clear User Interface**: Provides a user-friendly graphical interface (GUI) built with Java Swing.

## 🛠️ Environment Setup & Installation

To run this Java application, you need to have a Java Development Kit (JDK) installed on your system.

### Prerequisites

*   **Java Development Kit (JDK)**: Version 8 or higher is recommended.
    *   You can download the latest JDK from Oracle's website or use an open-source distribution like OpenJDK.
    *   Ensure that the `JAVA_HOME` environment variable is set and `java` and `javac` commands are accessible from your system's PATH.

### Installation Steps

1.  **Save the Code**: Save the provided Java code into three separate `.java` files in the same directory:
    *   `PuntoDiRistoro.java`
    *   `PuntoDiRistoroService.java`
    *   `DettaglioPuntoDiRistoroUI.java`
    *   `MainApplication.java`

    Make sure all files are in the same folder.

2.  **Compile the Code**: Open a terminal or command prompt, navigate to the directory where you saved the files, and compile them using the Java compiler:
    ```bash
    javac PuntoDiRistoro.java PuntoDiRistoroService.java DettaglioPuntoDiRistoroUI.java MainApplication.java
    ```
    If compilation is successful, several `.class` files will be generated in the same directory.

3.  **Run the Application**: After successful compilation, execute the main application:
    ```bash
    java MainApplication
    ```

### Using an Integrated Development Environment (IDE)

Alternatively, you can import this project into an IDE like IntelliJ IDEA, Eclipse, or VS Code with Java extensions.
1.  Create a new Java project.
2.  Place all `.java` files into the `src` directory (or equivalent source folder) of your project.
3.  The IDE will automatically compile the code.
4.  Run `MainApplication.java` directly from the IDE.

## 🎮 How to Use the Application

Once the application is running, a window titled "ChatDev - Visualizza Scheda Punto di Ristoro" will appear.

1.  **View List of Points of Rest**:
    *   At the top of the window, you will see a dropdown menu labeled "Seleziona Punto Ristoro:". This list is automatically populated on startup with sample points of rest.
    *   By default, the first item in the list is selected, and its details are displayed.

2.  **Displaying Detailed Information**:
    *   Select a point of rest from the dropdown menu.
    *   Click the **"Visualizza Dettagli"** (View Details) button.
    *   The center panel, titled "Dettagli Punto di Ristoro", will update to show all the available information for the selected item. This includes its ID, name, type, address, description, capacity, contact phone, and website.
    *   The `Description` field is a scrollable text area for longer descriptions.

3.  **Understanding the Status Bar**:
    *   At the bottom of the window, a status bar provides feedback on the application's operations, such as "Stato: Elenco punti di ristoro caricato." or success messages after viewing details.
    *   Error messages, such as connection issues, will also appear here in red.

4.  **Simulating Server Connection Errors**:
    *   To test how the application handles server disconnections, check the **"Simula Errore Server ETOUR"** (Simulate ETOUR Server Error) checkbox.
    *   When checked:
        *   The status will update to indicate that error simulation is active.
        *   If you try to "Visualizza Dettagli", a `ServerConnectionException` will be thrown, an error dialog will appear, and the details panel will clear.
        *   The dropdown list might not load if the error is active during startup or a refresh attempt.
    *   Uncheck the checkbox to disable error simulation. The system will then attempt to refresh the details for the currently selected item.

This application provides a clear demonstration of the "VISUALIZZASCHEDAPUNTODIRISTORO" use case, allowing an operator to interact with and view detailed information for points of rest, while also showcasing error handling for server connectivity issues.

```