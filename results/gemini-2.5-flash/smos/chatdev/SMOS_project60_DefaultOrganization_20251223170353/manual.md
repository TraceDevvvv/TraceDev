```
# Student Digital Log Viewer

"Changing the digital world through programming."

## 🤔 What is this?

The "Student Digital Log Viewer" is a simple Java Swing application designed to simulate how a student can view their school's class registry data. This application acts as a client-side interface for students to access information such as their daily attendance records, disciplinary notes, delays, and justifications, as if retrieved from a School Management Operating System (SMOS) server.

## 🌟 Main Functions

1.  **Simulated Login**: The application assumes the user is already logged in as a student.
2.  **"Digital Log" Button**: A primary button that, when clicked, initiates the process of fetching student data.
3.  **Data Retrieval Simulation**: Internally, the application connects to a simulated SMOS server (`SMOSService`) to fetch the student's class registry data. This simulation includes a small delay to mimic network latency.
4.  **Data Display**: Fetched data (date, absences, disciplinary notes, delays, justification) is displayed in a clear, tabular format within the application's graphical user interface.
5.  **Connection Management**: After data retrieval, the simulated connection to the SMOS server is interrupted, adhering to the use case's postconditions.
6.  **Real-time Status Updates**: A status bar at the bottom provides feedback on the data retrieval process (e.g., "Searching archive...", "Error retrieving data...", "Student's class registry data was shown.").
7.  **Error Handling**: Basic error handling is in place to notify the user if data retrieval fails.

## 📖 Environment Dependencies Installation

This project is a pure Java Swing application. All necessary libraries (Swing, AWT, etc.) are part of the standard Java Development Kit (JDK).

### Prerequisites:

*   **Java Development Kit (JDK) 8 or higher**: Required for compiling and running the application.

### Installation Steps:

1.  **Verify Java Installation**:
    Open a terminal or command prompt and type:
    ```bash
    java -version
    javac -version
    ```
    If Java is installed, you should see version information for both `java` (Java Runtime Environment) and `javac` (Java Compiler). If not, proceed to step 2.

2.  **Install Java Development Kit (JDK)**:
    Download and install the latest stable version of JDK from Oracle's official website or an open-source distribution like AdoptOpenJDK/Eclipse Temurin. Follow the installation instructions for your operating system.
    *   **Windows**: Download the installer and run it. Set `JAVA_HOME` environment variable and add `%JAVA_HOME%\bin` to your `Path`.
    *   **macOS**: Use Homebrew (`brew install openjdk`) or download the DMG installer.
    *   **Linux**: Use your distribution's package manager (e.g., `sudo apt install openjdk-11-jdk` for Debian/Ubuntu, `sudo yum install java-11-openjdk-devel` for Fedora/RHEL) or download the tar.gz archive.

## ▶️ How to Use/Play

Follow these steps to compile and run the "Student Digital Log Viewer" application.

### 1. Save the Code Files

Save the provided Java code into three separate files in the same directory:
*   `StudentData.java`
*   `SMOSService.java`
*   `StudentApp.java`

For example, you might create a folder named `StudentViewer` and place all three `.java` files inside it.

### 2. Compile the Java Code

Open your terminal or command prompt, navigate to the directory where you saved the `.java` files (e.g., `cd StudentViewer`), and compile the source files using the Java compiler:

```bash
javac StudentData.java SMOSService.java StudentApp.java
```

If the compilation is successful, you will see no output, and `.class` files (e.g., `StudentData.class`, `SMOSService.class`, `StudentApp.class`) will be created in the same directory.

### 3. Run the Application

Execute the main application class (`StudentApp`) using the Java Virtual Machine:

```bash
java StudentApp
```

### 4. Interact with the Application

*   A GUI window titled "Student Digital Log - View School Data" will appear.
*   Initially, a status message will prompt you to "Click 'Digital Log' to view your class registry data."
*   **Click the "Digital Log" button**:
    *   The status message will change to "Searching archive for your data..."
    *   After a short delay (simulating network latency), the table will populate with sample student data (absences, notes, delays, justifications).
    *   The status message will update to "Student's class registry data was shown. Connection to SMOS server interrupted."
*   You can click the "Digital Log" button again to re-fetch and display the data.

This application provides a clear demonstration of how a basic student information viewer can be implemented in Java Swing, showcasing data fetching from a simulated backend and its presentation to the user.
```