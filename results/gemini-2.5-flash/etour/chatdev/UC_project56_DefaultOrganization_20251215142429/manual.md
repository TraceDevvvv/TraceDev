```markdown
# Tourist Localization System

## Introduction

The Tourist Localization System is a Java-based application designed to simulate the process of determining a tourist's current geographical position using a simulated GPS module. This application demonstrates how a system might request position data, wait for GPS calculation, and display the resulting coordinates or a failure message. It accounts for various scenarios, including successful localization, GPS inability to detect a position, and time-outs if the GPS takes too long.

The primary goal of this application is to illustrate the "LOCALIZATION" use case, where a system provides the current position of a tourist, respecting specific quality requirements such as response time.

## Main Functions

*   **Initiate Localization**: Allows the user to start the process of requesting the tourist's position.
*   **Simulated GPS Calculation**: Employs a `GPSModule` to simulate GPS behavior, including:
    *   **Variable Delays**: Simulates processing time that can vary from 1 to 7 seconds.
    *   **Success/Failure Scenarios**: Randomly determines if the GPS successfully detects a position or fails.
    *   **Quality Check**: Enforces a requirement that localization must complete within 5 seconds; otherwise, it reports a timeout.
*   **Display Results**: Presents the localization outcome, including latitude, longitude (if successful), or detailed error messages (if failed or timed out).
*   **Responsive User Interface**: Uses Java Swing to provide a simple graphical user interface (GUI) that remains responsive during the background GPS calculation.

## System Requirements

This project is developed in Java and relies solely on standard Java libraries.
To run this application, you will need:

*   **Java Development Kit (JDK)** or **Java Runtime Environment (JRE)** installed on your system.
*   **Version**: Java 8 or higher is recommended.
    *   You can check your Java version by opening a terminal or command prompt and typing:
        ```bash
        java -version
        ```

No third-party libraries or external dependencies are required.

## Installation

Since there are no external dependencies, "installation" primarily involves ensuring you have a compatible Java environment set up.

1.  **Download Java (if not already installed)**:
    *   Visit the official Oracle Java website or AdoptOpenJDK/Adoptium for a free, open-source JDK distribution.
    *   Follow their instructions to download and install the JDK for your operating system.
    *   Ensure the `JAVA_HOME` environment variable is set and that `java` and `javac` commands are accessible in your system's PATH.

## How to Use

Follow these steps to compile and run the Tourist Localization System:

1.  **Save the Files**:
    *   Create a directory on your computer, for example, `TouristLocalization`.
    *   Save the provided Java code into separate files within this directory:
        *   `Position.java` (for the `Position` class)
        *   `GPSModule.java` (for the `GPSModule` class)
        *   `LocalizationApp.java` (for the `LocalizationApp` class)

    Your directory structure should look like this:

    ```
    TouristLocalization/
    ├── Position.java
    ├── GPSModule.java
    └── LocalizationApp.java
    ```

2.  **Compile the Java Code**:
    *   Open a terminal or command prompt.
    *   Navigate to the `TouristLocalization` directory using the `cd` command:
        ```bash
        cd path/to/TouristLocalization
        ```
    *   Compile all Java source files:
        ```bash
        javac Position.java GPSModule.java LocalizationApp.java
        ```
    *   If the compilation is successful, you will see no output. If there are errors, they will be displayed in the console.

3.  **Run the Application**:
    *   From the same terminal or command prompt window, run the main application class:
        ```bash
        java LocalizationApp
        ```

4.  **Interact with the GUI**:
    *   A GUI window titled "Tourist Localization System" will appear.
    *   Click the **"Start Localization"** button.
    *   The status label will update to indicate that the system is requesting data and that GPS is calculating the position.
    *   The application will simulate a delay (1 to 7 seconds) during which the GPS "calculates" the position.
    *   Once the calculation is complete (or times out), the `resultArea` at the bottom of the window will display the outcome:
        *   **Successful Localization**: Shows "Status: Success", Latitude, Longitude, and a "Localization successful." message.
        *   **Failed Localization (Not Detectable)**: Shows "Status: Failed" and "Position not detectable by GPS."
        *   **Failed Localization (Timeout)**: Shows "Status: Failed" and "Localization timed out (> 5 seconds)."
    *   The "Start Localization" button will be re-enabled, allowing you to try again.

## Understanding the Output

The `resultArea` provides detailed feedback on the localization attempt:

*   **Status**: Indicates whether the attempt was `Success` or `Failed`.
*   **Latitude / Longitude**: Present only for successful localizations, showing the simulated coordinates.
*   **Message**: Provides context for the outcome, explaining why it succeeded, failed, or timed out.

This system effectively demonstrates a simplified, yet illustrative, implementation of a localization service with a GUI.

```