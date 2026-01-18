```
# ELIMINABANNER Application Manual

## Changing the Digital World Through Programming

Welcome to the `ELIMINABANNER` application! This software is designed to streamline the process of managing and deleting banner ads associated with various refreshment bars within the ETOUR system. Developed in Java, it provides a user-friendly graphical interface for agency operators to efficiently maintain their digital ad presence.

## 🤔 What is ELIMINABANNER?

The `ELIMINABANNER` application is a desktop tool for agency operators to easily remove unwanted or outdated banner advertisements. It allows users to:
1.  **Select a specific refreshment point** from a list.
2.  **View all active banners** associated with that refreshment point.
3.  **Choose a banner** and proceed with its deletion.

The application ensures clear communication of operation status and handles potential issues like server connectivity problems.

## 🚀 Main Functions

The `ELIMINABANNER` application is built around a straightforward workflow, mirroring the provided use case:

*   **Refreshment Point Selection (Step 1)**: Upon launching the application, a list of available refreshment points is loaded. The operator can choose one from a dropdown menu.
*   **Banner Listing (Step 2)**: Once a refreshment point is selected, the application automatically fetches and displays all banners currently linked to it in a separate dropdown.
*   **Banner Deletion (Steps 3, 4, 5, 6)**:
    *   The operator selects a banner from the displayed list.
    *   By clicking the "Delete Selected Banner" button, the system prompts for confirmation.
    *   Upon confirmation, the selected banner is removed from the system.
*   **Status and Feedback**:
    *   A status bar at the bottom of the window provides real-time updates on ongoing operations (e.g., loading, deleting) and their outcomes.
    *   Popup messages confirm successful deletions or inform about errors.
*   **Error Handling**: The application is designed to catch and inform the user about critical issues like:
    *   **Server Connection Interruption**: If the connection to the simulated ETOUR server is lost, the application will display an error message and disable interactive elements.
    *   **Banner Not Found**: If a banner slated for deletion is no longer present (perhaps already deleted by another operator or an external system), the application will notify the user.
*   **Simulated Connection Interruption**: For testing and demonstration purposes, a checkbox allows operators to deliberately simulate a server connection error, showcasing the application's robust error handling.

## 🛠️ How to Install Environment Dependencies

To run the `ELIMINABANNER` application, you need a Java Development Kit (JDK) installed on your system.

### 1. Java Development Kit (JDK)

*   **Requirement**: Java 8 or newer (LTS versions like Java 11, 17, 21 are recommended).
*   **Installation**:
    *   Download the appropriate JDK installer for your operating system from Oracle's website or OpenJDK distributions (e.g., Adoptium, Amazon Corretto, Azul Zulu).
    *   Follow the installation wizard's instructions.
    *   Verify installation by opening a command prompt or terminal and typing:
        ```bash
        java -version
        javac -version
        ```
        You should see output indicating your installed Java version.

### 2. Project Setup (Source Code Organization)

The provided Java code consists of several files organized into packages. You'll need to replicate this structure in your development environment.

Create a root directory for your project (e.g., `EliminaBannerApp`). Inside this, create the following subdirectories for packages:

*   `src/main/java/` (or just `src/` if you prefer a simpler structure)
    *   `exception/`: Place `ServerConnectionException.java` and `BannerNotFoundException.java` here.
    *   `gui/`: Place `EliminaBannerApp.java` here.
    *   `model/`: Place `RefreshmentPoint.java` and `Banner.java` here.
    *   `service/`: Place `ETOURServer.java` here.
*   The `Main.java` file should reside directly in `src/main/java/` or `src/`.

**Example Project Structure:**

```
EliminaBannerApp/
├── src/
│   └── main/         (or directly into src if not using Maven/Gradle standard)
│       └── java/
│           ├── exception/
│           │   ├── BannerNotFoundException.java
│           │   └── ServerConnectionException.java
│           ├── gui/
│           │   └── EliminaBannerApp.java
│           ├── model/
│           │   ├── Banner.java
│           │   └── RefreshmentPoint.java
│           ├── service/
│           │   └── ETOURServer.java
│           └── Main.java
```

## 🎮 How to Use/Play ELIMINABANNER

Once you have the Java JDK installed and the project files organized, you can compile and run the application.

### 1. Compilation

#### Using a Command Line (manual compilation)

1.  Open your command prompt or terminal.
2.  Navigate to the directory containing your `src` folder (e.g., `EliminaBannerApp/`).
3.  Compile all Java files. If `Main.java` is in the root of `src/main/java/`, and other files are in subdirectories as described, you can use:
    ```bash
    javac -d bin src/main/java/Main.java src/main/java/gui/*.java src/main/java/model/*.java src/main/java/service/*.java src/main/java/exception/*.java
    ```
    This command compiles all `.java` files and places the compiled `.class` files into a new `bin` directory.
    *(Adjust the source paths if your `src` directory structure differs, for example, if `Main.java` is directly in `src/`)*

#### Using an Integrated Development Environment (IDE)

Most IDEs (like IntelliJ IDEA, Eclipse, VS Code with Java extensions) will automatically compile your code as you write it.
1.  Open your IDE.
2.  Import the `EliminaBannerApp` project. You might need to set up a new Java project and then add the existing files to their respective packages.
3.  The IDE's build system will handle compilation.

### 2. Running the Application

#### Using a Command Line

1.  After successful compilation, navigate to the `bin` directory (created in the compilation step).
2.  Run the main class:
    ```bash
    java -cp . Main
    ```
    If you compiled into a `bin` directory, you might need to adjust the classpath to include the `bin` directory from the root of your project:
    ```bash
    java -cp bin Main
    ```
    *(Adjust `Main` to `src.main.java.Main` or similar if your package structure isn't flattened for execution)*. If `Main.java` is under `src/main/java` and compiled output is in `bin`, from `EliminaBannerApp` directory:
    ```bash
    java -cp bin Main
    ```
    This will launch the `ELIMINABANNER` GUI.

#### Using an Integrated Development Environment (IDE)

1.  In your IDE, locate the `Main.java` file.
2.  Right-click on `Main.java` and select "Run 'Main.main()'" or similar option.
3.  The GUI application window will appear.

### 3. Interacting with the GUI (Flow of Events)

Once the application is running, follow these steps:

1.  **Initial Load**: The application will start by attempting to load the list of refreshment points. You will see "Status: Loading refreshment points..." in the status bar.
    *   If successful, the **"1. Select Refreshment Point:"** dropdown will populate, and the status will update.
    *   If a "Connection Error" occurs (e.g., if "Simulate Server Connection Interruption" was checked at startup), an error dialog will appear, and the application's controls will be disabled.

2.  **Select a Refreshment Point**:
    *   Click on the **"1. Select Refreshment Point:"** dropdown.
    *   Choose a refreshment point from the list (e.g., "Coffee Corner").
    *   The application will then automatically attempt to load banners for the selected point. The status bar will show "Status: Loading banners for [Selected Point Name]...".

3.  **View Banners**:
    *   After banners are loaded (or if there are none), the **"2. Select Banner to Delete:"** dropdown will update.
    *   If banners exist, they will be listed here (e.g., "Morning Coffee Deal").

4.  **Select a Banner to Delete**:
    *   Click on the **"2. Select Banner to Delete:"** dropdown.
    *   Choose the specific banner ad you wish to remove.

5.  **Initiate Deletion**:
    *   Click the **"Delete Selected Banner"** button. This button will only be enabled if both a refreshment point and a banner are selected.

6.  **Confirm Deletion**:
    *   A confirmation dialog will pop up, displaying the description of the banner you selected.
    *   Click **"Yes"** to proceed with the deletion or **"No"** to cancel.

7.  **Deletion Process**:
    *   If you confirmed, the status bar will show "Status: Deleting banner ID [ID number]...".
    *   The application will attempt to remove the banner from the simulated ETOUR server. This operation has a simulated delay.

8.  **Deletion Outcome**:
    *   **Success**: A "Deletion Successful" message box will appear, and the status bar will update (e.g., "Status: Successfully deleted banner: [Banner Description]"). The banner list for the current refreshment point will then automatically reload, reflecting the removal of the deleted banner.
    *   **Server Connection Error**: If a `ServerConnectionException` occurs during deletion, an error dialog will appear, and interactive elements will be disabled.
    *   **Banner Not Found Error**: If the banner could not be found (e.g., it was already deleted), a "Deletion Error" message will appear, and the application will reload the banner list to resynchronize the UI.

### 4. Simulating Server Connection Interruption

*   At any point, you can check or uncheck the **"Simulate Server Connection Interruption"** checkbox.
*   Checking this box will instruct the simulated ETOUR server to throw `ServerConnectionException` for subsequent data retrieval or deletion attempts.
*   Unchecking it will restore normal simulated server operation.
*   After toggling the checkbox, the application will attempt to reload the refreshment points, allowing you to immediately observe the effect of the simulated connection state.

This manual should help you get started with and understand the `ELIMINABANNER` application. Enjoy managing your banner ads efficiently!
```