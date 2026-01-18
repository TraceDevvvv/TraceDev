```markdown
# Teaching Details Viewer: Administrator Manual

## 👋 Introduction

Welcome to the **Teaching Details Viewer** application! This tool is designed for administrators to quickly and conveniently access the detailed information of specific teachings within a simulated educational system. It provides a simple graphical interface to select a teaching and display its attributes, such as ID, title, description, instructor, duration, and start date. The application also simulates real-world scenarios like server interruptions for robust error handling demonstrations.

## 🚀 Main Functions

The application offers the following primary functions:

1.  **Select Teaching:** An administrator can choose a teaching ID from a dropdown list. This list is pre-populated with sample teaching IDs and includes a mock "Not Found" entry for demonstration purposes.
2.  **View Details:** Upon selecting a teaching ID and clicking the "View Details" button, the application attempts to retrieve and display the comprehensive details of that teaching.
3.  **Display Teaching Information:** If successfully retrieved, the application renders the teaching's ID, Title, Instructor, Description, Duration, and Start Date in a dedicated display panel.
4.  **Progress Indicator:** A progress bar is shown while the system is fetching details, providing visual feedback during simulated network delays.
5.  **Error Handling (SMOS Server Interruption):** The system simulates potential connection issues to a backend "SMOS server." If an interruption occurs during data retrieval, an informative error message will be displayed to the administrator.
6.  **"Teaching Not Found" Scenario:** The application gracefully handles cases where a selected teaching ID does not correspond to any available teaching, informing the user that the teaching was not found.

## 💻 Environment Setup (Installation)

This application is built entirely in **Java** using the standard Swing library for its graphical user interface. It has no external third-party dependencies outside of the Java Development Kit (JDK).

### Required Software

*   **Java Development Kit (JDK):** Version 1.8 or higher.

### How to Check Your Java Installation

To verify if Java is installed on your system and to check its version, open a terminal or command prompt and type:

```bash
java -version
javac -version
```

If Java is installed, you should see output similar to:

```
openjdk version "1.8.0_302"
OpenJDK Runtime Environment (build 1.8.0_302-b08)
OpenJDK 64-Bit Server VM (build 25.302-b08, mixed mode)
```

And for `javac`:

```
javac version "1.8.0_302"
```

If you receive a "command not found" error or the version is older than 1.8, you will need to install or update your JDK.

### How to Install/Update Java (if needed)

You can download the latest JDK from Oracle's website or use open-source distributions like OpenJDK.

*   **Oracle JDK:** Visit [oracle.com/java/technologies/downloads/](https://www.oracle.com/java/technologies/downloads/)
*   **OpenJDK:** Visit [jdk.java.net](https://jdk.java.net/) or use a package manager (e.g., `sudo apt install openjdk-17-jdk` on Ubuntu, `brew install openjdk@17` on macOS with Homebrew).

Follow the installation instructions for your operating system. Ensure that the `JAVA_HOME` environment variable is set and that `java` and `javac` commands are accessible in your system's PATH.

## ▶️ How to Use/Play

Follow these steps to compile and run the application:

### 1. Save the Source Files

Save the provided Java code into separate `.java` files in the same directory:

*   `Teaching.java`
*   `TeachingService.java`
*   `TeachingDetailsView.java`
*   `MainApplication.java`

For example, your directory structure might look like this:

```
/TeachingApp
├── Teaching.java
├── TeachingService.java
├── TeachingDetailsView.java
└── MainApplication.java
```

### 2. Compile the Application

Open your terminal or command prompt, navigate to the directory where you saved the `.java` files, and compile them using the Java compiler (`javac`):

```bash
cd /path/to/TeachingApp
javac Teaching.java TeachingService.java TeachingDetailsView.java MainApplication.java
```

If the compilation is successful, you will see `.class` files generated in the same directory.

### 3. Run the Application

Once compiled, you can run the application by executing the `MainApplication` class:

```bash
java MainApplication
```

### 4. Interacting with the Application

Upon running, a window titled "Teaching Details Viewer (Administrator)" will appear.

*   **Select a Teaching ID:** Use the dropdown menu labeled "Teaching ID" to choose one of the available teaching identifiers (e.g., "T001", "T002", "T003", or "T004 (Not Found)").
*   **View Details:** Click the "View Details" button.
*   **Observe Progress:** A small progress bar will temporarily appear at the bottom as the application simulates fetching data.
*   **Check Display Panel:**
    *   If a valid teaching ID was selected and no simulated server interruption occurred, the "Teaching Details" panel in the center of the window will populate with the teaching's information.
    *   If you select "T004 (Not Found)", an error message dialog will appear stating "Teaching with ID 'T004' not found," and the details panel will show "No teaching selected or an error occurred."
    *   **Simulated Server Interruption:** The `TeachingService` has a built-in ~20% chance to simulate a "Connection to SMOS server interrupted" error. If this occurs, an error message dialog will pop up, and the details panel will be cleared. Simply try clicking "View Details" again with the same or a different ID until it succeeds or another interruption is simulated.

This application provides a hands-on demonstration of fetching and displaying data with robust error handling, simulating common backend service interactions.
```