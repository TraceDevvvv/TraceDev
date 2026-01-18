```markdown
# Parent's Student Report Viewer

A desktop application designed to simulate how parents can view their children's academic report cards. This application demonstrates interaction with a mock School Management Online System (SMOS) to fetch and display student and report card data through a user-friendly graphical interface.

## 🤔 What is this?

The Parent's Student Report Viewer is a Java Swing-based application that allows a simulated parent user to:
*   View a list of their enrolled children.
*   Select a child to see their available report cards for different periods.
*   Select a specific report card to view its detailed breakdown of subjects and grades.

This application acts as a clear demonstration of the `ViewOnePage` use case, illustrating the flow of a parent accessing student reports.

## Main Functions and Features

### 1. **Parent Login (Simulated)**
   *   The application starts with a pre-configured parent ID ("parent123"), simulating a successful login. There is no actual login screen in this demo.

### 2. **View Children List**
   *   Upon launching, the application fetches and displays a list of students associated with the logged-in parent in the "Your Children" panel. This represents clicking on a "Magine button" (or similar selection mechanism) associated with one of their children in a real system.

### 3. **View Student Report Cards**
   *   When a parent selects a student from the "Your Children" list, the application automatically loads and displays a list of report cards available for that student in the "Report Cards" panel. If a student has no reports, an informative message will be displayed.

### 4. **Display Detailed Report Card**
   *   After selecting a specific report card from the "Report Cards" list, the parent can click the "View Report Card Details" button. This action opens a new modal dialog showing a comprehensive view of the selected report card, including grades for each subject.

### 5. **Application Status**
   *   A status bar at the bottom provides real-time feedback on operations, such as data fetching, loading, and server connection status.

### 6. **Simulated SMOS Server Interaction**
   *   The application includes a mock `SMOSServer` to simulate network delays and data retrieval, mimicking a backend system without requiring an actual database or network calls.
   *   Critically, it simulates a "Connection to the interrupted SMOS server" postcondition after a report card's details have been viewed, serving as a functional demonstration of the specified use case requirement.

## Installation and Setup

To run this application, you need to have a Java Development Kit (JDK) installed on your system.

### Prerequisites

*   **Java Development Kit (JDK)**: Version 8 or higher. You can download it from the official Oracle website or adopt an OpenJDK distribution like Adoptium.

### Steps to Run

1.  **Save the Code Files**:
    Save the provided Java code snippets into separate `.java` files in a single directory. The files should be named exactly as follows:
    *   `Student.java`
    *   `ReportCard.java`
    *   `SMOSServer.java`
    *   `ReportCardViewerDialog.java`
    *   `ParentViewApp.java`

    Make sure all these files are in the same directory.

2.  **Open a Terminal or Command Prompt**:
    Navigate to the directory where you saved the `.java` files using your terminal or command prompt.

    ```bash
    cd /path/to/your/java/files
    ```

3.  **Compile the Java Code**:
    Use the Java compiler (`javac`) to compile all the `.java` files.

    ```bash
    javac Student.java ReportCard.java SMOSServer.java ReportCardViewerDialog.java ParentViewApp.java
    ```
    If compilation is successful, several `.class` files will be generated in the same directory.

4.  **Run the Application**:
    Execute the main application class `ParentViewApp` using the Java Virtual Machine (`java`).

    ```bash
    java ParentViewApp
    ```

## How to Use/Play

Once the application starts, a window titled "Parent's Student Report Viewer" will appear.

1.  **View Your Children**:
    *   The left panel, labeled "Your Children," will display a list of students like "Alice Smith" and "Bob Johnson." These are the children associated with the simulated parent "parent123."
    *   Observe the "Status" label at the bottom of the window, which will indicate "Students loaded."

2.  **Select a Student to View Report Cards**:
    *   Click on one of the student names in the "Your Children" list (e.g., "Alice Smith").
    *   The center panel, labeled "Report Cards," will update to show the report cards available for the selected student (e.g., "Report Card: Fall 2023," "Report Card: Spring 2024").
    *   If you select a student with no available reports (e.g., "Charlie Brown" if added to the mock server data), the center panel will display "No reports available for Charlie Brown."
    *   The "Status" label will update to confirm the successful loading of report cards.

3.  **View Detailed Report Card**:
    *   From the "Report Cards" panel, select a specific report card (e.g., "Report Card: Fall 2023").
    *   The "View Report Card Details" button at the bottom right will become enabled.
    *   Click the **"View Report Card Details"** button.
    *   A new modal dialog titled "Report Card Details" will open, displaying the student's name, the report date, and a detailed list of subjects with their corresponding grades.
    *   The "Status" label in the main window will update to "Status: Report card details viewed. SMOS connection interrupted." This simulates the postcondition.

4.  **Close Report Card Details**:
    *   Click the "Close" button within the "Report Card Details" dialog to dismiss it.

5.  **Continue or Exit**:
    *   You can select another student or report card to view, or close the main application window to exit the program.

This application provides a hands-on experience of navigating a simplified parent portal to access essential student academic information.
```