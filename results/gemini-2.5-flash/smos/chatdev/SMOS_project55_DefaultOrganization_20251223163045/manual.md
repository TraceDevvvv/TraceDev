```markdown
# Insert Delay ATA - User Manual

This manual provides a comprehensive guide to understanding, setting up, and using the "Insert Delay ATA" application. This tool is designed to assist ATA staff in efficiently recording student delays for a selected class.

## 🤔 What is this application?

The "Insert Delay ATA" application is a desktop tool built in Java Swing that allows ATA (Academy Training Assistant) staff to register and record attendance delays for students within a specific class. It provides a user-friendly interface to quickly mark students who are delayed, specify the duration of their delay, and submit this information to a simulated server.

## 🚀 Main Functions

The application facilitates the following core functionalities:

*   **Display Student List**: Presents a clear list of all students enrolled in the currently selected class (simulated with a predefined list).
*   **Record Delays**: For each student, the system provides a checkbox to indicate whether they are delayed.
*   **Specify Delay Duration**: When a student is marked as delayed, an adjacent spinner control becomes active, allowing the staff to precisely enter the delay duration in minutes (e.g., 5, 10, 15 minutes).
*   **Confirm Data Submission**: A "Confirm" button allows the ATA staff to submit all recorded delay data for the class to a backend system (simulated for this application).
*   **Cancel Operation**: A "Cancel" button provides the option to discard current entries, interrupt the process, and return to an initial state.
*   **Real-time Feedback**: Provides immediate visual feedback when ticking a delay checkbox, activating the delay input field.
*   **Data Persistence (Simulated)**: After confirmation, the application simulates sending the data to a server, providing a console output of the recorded delays.

## 🛠️ Environment Dependencies

This application is developed in Java and relies on standard Java libraries for its functionality, particularly `javax.swing` for the Graphical User Interface (GUI).

**No external third-party libraries or dependency management tools (like Maven or Gradle) are required for this specific project.**

The only dependency to compile and run this application is a **Java Development Kit (JDK)**.

*   **Java Development Kit (JDK)**:
    *   **Version**: JDK 8 or higher is recommended.
    *   **Purpose**: The JDK includes the Java Runtime Environment (JRE), compilers, and other tools necessary to build and run Java applications.

### How to Install JDK

If you do not have a JDK installed, you can download and install it from one of the following sources:

1.  **Oracle JDK**: Visit the official Oracle website and download the appropriate JDK version for your operating system: [https://www.oracle.com/java/technologies/downloads/](https://www.oracle.com/java/technologies/downloads/)
2.  **OpenJDK (Recommended for open-source projects)**: Various distributions of OpenJDK are available, such as Adoptium Temurin, Amazon Corretto, or OpenJDK builds from other vendors.
    *   Adoptium Temurin: [https://adoptium.net/](https://adoptium.net/)

Follow the installation instructions provided by your chosen JDK provider. Ensure that after installation, the `java` and `javac` commands are accessible from your system's command line (this usually involves setting up the `PATH` environment variable, which many installers do automatically).

To verify your JDK installation, open a terminal or command prompt and type:

```bash
java -version
javac -version
```

You should see output indicating the installed Java version.

## 🚀 How to Use/Play

Follow these steps to compile and run the "Insert Delay ATA" application:

### Step 1: Save the Source Code

Ensure all the provided Java files (`Student.java`, `ServerSimulator.java`, `DelayEntryScreen.java`, `Main.java`) are saved in the same directory (e.g., `InsertDelayAtaApp`).

### Step 2: Compile the Java Files

Open a terminal or command prompt, navigate to the directory where you saved the Java files, and compile them using the Java compiler (`javac`).

```bash
cd path/to/InsertDelayAtaApp
javac *.java
```

This command compiles all `.java` files in the current directory. If there are no compilation errors, it will generate corresponding `.class` files.

### Step 3: Run the Application

After successful compilation, you can run the application by executing the `Main` class.

```bash
java Main
```

### Step 4: Interact with the Application

Upon running `java Main`, a GUI window titled "Insert Delay - ATA Staff" will appear.

1.  **View Student List**: The screen will display a list of students, each with their name, a "Delay?" checkbox, and a "Delay (minutes)" spinner.
2.  **Marking Delays**:
    *   To mark a student as delayed, click the **"Delay?" checkbox** next to their name.
    *   Once checked, the adjacent **"Delay (minutes)" spinner** will become enabled.
3.  **Entering Delay Duration**:
    *   Use the enabled spinner to select the duration of the delay in minutes. The spinner allows increments/decrements of 5 minutes, with a default value of 0, and a maximum of 300 minutes.
    *   If you uncheck the "Delay?" checkbox, the spinner will automatically reset to 0 and become disabled.
4.  **Confirming Data**:
    *   After making all necessary selections for student delays, click the **"Confirm"** button at the bottom right of the window.
    *   The application will simulate sending this data to a server. You will see output in your terminal/command prompt indicating the data being sent.
    *   A confirmation dialog "Delay data successfully recorded!" will appear. Click "OK".
    *   The screen will then reset to its initial state, clearing all selected delays and spinner values, ready for the next entry.
5.  **Canceling Operation**:
    *   If you wish to discard your current selections and not submit any data, click the **"Cancel"** button.
    *   The application will simulate interrupting the connection to the SMOS server. You will see output in your terminal/command prompt.
    *   A cancellation dialog "Operation cancelled. Returning to initial screen." will appear. Click "OK".
    *   The screen will reset, clearing all selected delays and spinner values.

### Step 5: Close the Application

You can close the application by clicking the 'X' button on the window title bar, or by closing the terminal/command prompt where it's running (which might require pressing `Ctrl+C`).

This manual should provide all the necessary information to effectively use the "Insert Delay ATA" application.
```