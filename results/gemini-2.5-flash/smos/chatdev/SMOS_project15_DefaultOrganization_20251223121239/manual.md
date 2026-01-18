```
# ViewClassDetails Desktop Application

## Introduction

The "ViewClassDetails" application is a simple desktop tool designed for administrators to view detailed information about specific school classes. This software simulates a common scenario in educational management systems where an administrator, after logging in and viewing a list of classes, can select a particular class to inspect its details such as its Name, Address, and the School Year it belongs to.

The application is built using Java Swing for its graphical user interface (GUI) and demonstrates a basic client-server interaction pattern, where a `ClassService` simulates data retrieval from a backend "SMOS server". A key feature of this simulation is the "SMOS server interrupted" postcondition, meaning after displaying the class details, the simulated connection to the backend is intentionally severed for the current session.

## Main Functions

*   **Class Selection**: Allows the administrator to choose a class from a predefined list presented in a dropdown menu.
*   **Detail Display**: Upon selection and confirmation, the application retrieves and displays the Name, Address, and School Year for the chosen class.
*   **Simulated Backend Interaction**: Uses a `ClassService` to simulate fetching class data, mimicking a real-world data retrieval process that might involve network latency.
*   **Connection Interruption**: Demonstrates a specific system postcondition where the simulated connection to the "SMOS server" is interrupted after a class's details are successfully displayed, preventing further data fetches within the same session.

## Environment Dependencies

To run this application, you need to have a Java Development Kit (JDK) installed on your system.

*   **Java Development Kit (JDK)**: Version 1.8 or newer.

You can download the latest JDK from Oracle's official website or use an open-source distribution like OpenJDK.

## How to Install and Run

Follow these steps to set up and run the "ViewClassDetails" application:

1.  **Create Project Directory**: Create a new directory for your project, for example, `ViewClassDetailsApp`.

2.  **Recreate Package Structure**: Inside `ViewClassDetailsApp`, create the following directory structure to match the Java packages:
    *   `src/com/chatdev/smos/model`
    *   `src/com/chatdev/smos/service`
    *   `src/com/chatdev/smos/gui`
    *   `src/com/chatdev/smos`

3.  **Save Source Files**:
    *   Save the content of `classdetails.java` into `src/com/chatdev/smos/model/ClassDetails.java`.
    *   Save the content of `classservice.java` into `src/com/chatdev/smos/service/ClassService.java`.
    *   Save the content of `viewclassdetailsgui.java` into `src/com/chatdev/smos/gui/ViewClassDetailsGUI.java`.
    *   Save the content of `main.java` into `src/com/chatdev/smos/Main.java`.

    Your directory structure should look like this:
    ```
    ViewClassDetailsApp/
    ├── src/
    │   └── com/
    │       └── chatdev/
    │           └── smos/
    │               ├── model/
    │               │   └── ClassDetails.java
    │               ├── service/
    │               │   └── ClassService.java
    │               ├── gui/
    │               │   └── ViewClassDetailsGUI.java
    │               └── Main.java
    ```

4.  **Compile the Code**:
    Open a terminal or command prompt, navigate to the `ViewClassDetailsApp` directory, and compile the Java source files.

    ```bash
    cd ViewClassDetailsApp
    javac -d bin src/com/chatdev/smos/Main.java src/com/chatdev/smos/model/*.java src/com/chatdev/smos/service/*.java src/com/chatdev/smos/gui/*.java
    ```
    This command compiles all `.java` files and places the compiled `.class` files into a `bin` directory.

5.  **Run the Application**:
    After successful compilation, run the application from the `ViewClassDetailsApp` directory:

    ```bash
    java -cp bin com.chatdev.smos.Main
    ```

## How to Use

1.  **Launch the Application**: Once started, a "Class Details Viewer" window will appear.
2.  **Select a Class**: Use the dropdown menu labeled "Select Class:" to choose a class ID (e.g., "CLASS\_5B\_2023", "CLASS\_6A\_2023", "CLASS\_4C\_2024").
3.  **View Details**: Click the "Show Class Details" button.
4.  **Observe Details**: The application will then display the "Class Name", "Address", and "School Year" corresponding to your selection. A small pop-up message will confirm "Class details loaded successfully!".
5.  **SMOS Connection Interruption**: Immediately after displaying the details and confirming successful load, another pop-up message will appear stating: "Connection to the SMOS server has been interrupted. No further class details can be viewed." At this point, the dropdown and the "Show Class Details" button will become disabled, preventing any further actions in the current session.
6.  **Exit**: To view details for another class, you would need to close the application window and restart it.

This workflow demonstrates the "ViewClassDetails" use case, culminating in the simulated interruption of the backend service connection as specified in the postconditions.