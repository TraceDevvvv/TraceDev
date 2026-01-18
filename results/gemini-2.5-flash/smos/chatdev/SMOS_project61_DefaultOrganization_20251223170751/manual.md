```
# ViewStudentData Application

A desktop application for parents to view their children's academic and behavioral records.

## 🤔 What does this application do?

This application, named "ViewStudentData", provides a simple graphical interface for parents to access a summary of their children's key information. The main functionalities include:

*   **Displaying Child Information**: Parents can select one of their registered children from a dropdown list.
*   **Viewing Student Records**: Once a child is selected, a detailed table is displayed showing their records, including:
    *   **Date**: The specific date for the record entry.
    *   **Absent**: Indicates if the child was absent on that date.
    *   **Disciplinary Notes**: Any disciplinary remarks for the day.
    *   **Delays**: The number of times the child was delayed.
    *   **Justification**: Explanations for absences or delays.
*   **Simulated Environment**: The application simulates a parent being logged in and interacts with a mock "SMOS server" for data retrieval.
*   **Connection Status**: It informs the user about the connection status to the "SMOS server", indicating when it's connected and subsequently interrupted after data retrieval, as per system design.

This tool aims to provide parents with a quick and clear overview of their children's performance and attendance.

## ⚙️ Environment Dependencies

To run this Java application, you need to have the Java Development Kit (JDK) installed on your system.

*   **Java SE Development Kit (JDK) 8 or later**:
    *   You can check your installed Java version by opening a terminal or command prompt and typing:
        ```bash
        java -version
        ```
    *   If Java is not installed or the version is older than 8, you will need to download and install a suitable JDK. You can find official downloads from Oracle or open-source alternatives like OpenJDK (e.g., from [Eclipse Adoptium](https://adoptium.net/temurin/releases/)).
    *   **Recommendation**: JDK 11 or 17 are good cho for long-term support.

## ▶️ How to Use the Application

Follow these steps to compile and run the `ViewStudentData` application:

### 1. Save the Source Code

Ensure you have the following directory structure and place the corresponding Java code files into them:

```
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── gui
│   │   │   │   └── StudentDataViewer.java
│   │   │   ├── model
│   │   │   │   ├── ParentUser.java
│   │   │   │   ├── Student.java
│   │   │   │   └── StudentRecord.java
│   │   │   └── service
│   │   │       └── StudentDataService.java
```

### 2. Compile the Code

Open your terminal or command prompt. Navigate to the `src/main/java` directory (the parent directory of `gui`, `model`, and `service` packages).

Then, compile all Java files using the `javac` command:

```bash
# Navigate to the root of your source package (e.g., where 'gui', 'model', 'service' folders are)
cd path/to/your/project/src/main/java

# Compile all Java files
javac gui/*.java model/*.java service/*.java
```

If compilation is successful, `.class` files will be generated in their respective package directories.

### 3. Run the Application

The main entry point for the application is the `StudentDataViewer` class, located within the `gui` package. From the same `src/main/java` directory where you compiled the files, execute the application using the `java` command:

```bash
# Run the main application
java gui.StudentDataViewer
```

### 4. Interacting with the Application

Upon running the command, a graphical user interface (GUI) window titled "View Student Data" will appear:

*   **Child Selection**: At the top of the window, you will see a dropdown combo box labeled "Select Child:". This combo box will be pre-populated with the names of the children associated with the mock parent user (e.g., "Alice Smith", "Bob Smith").
*   **View Student Data**:
    *   The application automatically loads and displays the records for the first child in the list upon startup.
    *   To view data for a different child, simply select their name from the "Select Child:" dropdown list.
    *   The table in the center of the window will automatically update to show the date, absence status, disciplinary notes, delays, and justification for the newly selected child.
*   **SMOS Server Status**: Observe the status label located in the bottom right corner of the window. This label, "SMOS Server Status:", will display the current connection state.
    *   Initially, it might show "Connected".
    *   **Important**: After you select a child and their data is loaded and displayed in the table, this status will consistently change to "Disconnected (after data retrieval)". This behavior specifically fulfills a postcondition of the use case, indicating that the "Connection to the interrupted SMOS server" has occurred after data processing.

You can close the application window at any time to exit the program.
```