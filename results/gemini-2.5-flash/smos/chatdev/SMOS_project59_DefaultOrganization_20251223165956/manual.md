```markdown
# Student Report Viewer

View your academic reports with ease. This application provides a simulation of a student portal where you can access your report cards.

## 🤔 What is this?

The **Student Report Viewer** is a desktop application developed in Java Swing, designed to fulfill the a customer use case for displaying student report cards. It simulates a student being logged into a system and accessing their "Online report" section. The application connects to a simulated SMOS (Simple Management and Operations System) server to retrieve and display academic reports.

## ✨ Main Functions

This application provides the following core functionalities:

*   **Display Student Reports:** Upon launch, the system automatically retrieves and displays a list of all available report cards for a simulated logged-in student (Student ID: `S101`) from an archive.
*   **Select Report Card:** Users can easily select any report card from the displayed list by clicking on it.
*   **View Detailed Report:** Once a report card is selected, the application presents a detailed view of that specific report, including individual subject grades for the respective academic period.
*   **Simulated Backend Interaction:** It incorporates a `SMOSServerSimulator` to mimic a real backend system, demonstrating how report data would be fetched and displayed from a server.
*   **Connection Management:** As per the system's postconditions, the application simulates the interruption of the connection to the SMOS server upon exiting, ensuring proper resource disengagement.

## 🛠️ Environment Dependencies (Installation)

To compile and run the **Student Report Viewer** application, you will need:

*   **Java Development Kit (JDK) 8 or higher:** The application is written in Java and uses standard Java libraries. You can download the latest JDK from the official Oracle website or adoptium.net (for OpenJDK builds).

    *   _Verification:_ Open your terminal or command prompt and type `java -version`. You should see output indicating your installed Java version.

*   **No external build tools or libraries are required.** This is a pure Java SE application utilizing the built-in Swing framework for its graphical user interface.

## 🚀 How to Use/Play It

Follow these steps to get the **Student Report Viewer** up and running:

### 1. Save the Source Files

Create a new directory (e.g., `StudentReportViewer`) on your computer. Inside this directory, save all the provided Java code snippets as individual `.java` files:

*   `Student.java`
*   `SubjectGrade.java`
*   `ReportCard.java`
*   `SMOSServerSimulator.java`
*   `StudentReportApp.java`

Ensure each file name exactly matches its class name (case-sensitive).

### 2. Compile the Application

Open your terminal or command prompt, navigate to the directory where you saved the `.java` files, and compile them using the Java compiler:

```bash
cd path/to/StudentReportViewer
javac *.java
```

If the compilation is successful, you will see `.class` files generated for each `.java` file in the same directory. If there are any compilation errors, double-check that all files are correctly saved and that your JDK is properly installed.

### 3. Run the Application

After successful compilation, you can run the application from the same terminal or command prompt:

```bash
java StudentReportApp
```

### 4. Interact with the Application

Upon running, a "Student Report Viewer" window will appear:

*   **Initial Display:** The left panel will show a list of "Available Report Cards" (e.g., "Report: RC001 - Fall 2023 Semester"). The right panel will initially show "Loading student reports..." and then "Select a report card from the list to view its details."
*   **View Details:** The application is designed to automatically select and display the details of the first report card upon loading.
*   **Select a Different Report:** Click on any other report card in the "Available Report Cards" list on the left. The "Report Details" area on the right will update to show the detailed grades for the newly selected report card.
*   **Exit:** To close the application, click the "Exit" button located at the bottom right of the window. This action will also trigger the simulated interruption of the connection to the SMOS server, as per the system's postcondition.

Enjoy viewing your simulated student reports!
```