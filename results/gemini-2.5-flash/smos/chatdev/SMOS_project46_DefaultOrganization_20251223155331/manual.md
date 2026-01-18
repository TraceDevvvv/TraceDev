```markdown
# Report Card Management System (Administrator)

This document provides a user manual for the Report Card Management System, a Java Swing application designed for administrators to manage student report cards.

## 🧐 What is this?

The Report Card Management System is a simplified application that simulates an administrator's workflow for inserting and updating student report cards. It covers the essential steps from selecting a class and student to inputting grades and saving the report. The application demonstrates core functionalities like navigating through different views, data management (in-memory simulation), and handling user interactions.

**Key Features:**

*   **Administrator Dashboard:** Starting point with an "Online Reports" button.
*   **Class Selection:** Displays a list of academic classes for the current year.
*   **Student Selection:** Shows students within a chosen class, allowing selection for report card entry.
*   **Report Card Input:** A form to enter or modify grades for various subjects for a selected student.
*   **Data Persistence (Simulated):** Grades are managed in-memory but simulate saving to a backend system.
*   **Error Handling (Basic):** Includes input validation for grades and a simulation of server connection interruption.

## 🛠️ How to Install Environment Dependencies

To run this application, you need a Java Development Kit (JDK) installed on your system.
This project uses standard Java libraries (primarily Swing for the GUI), so no additional external dependencies are required beyond the JDK.

**1. Install Java Development Kit (JDK)**

*   **Recommended Version:** Java 11 or newer (e.g., JDK 17).
*   **Windows/macOS/Linux:**
    1.  Download the appropriate JDK installer from Oracle's website ([https://www.oracle.com/java/technologies/downloads/](https://www.oracle.com/java/technologies/downloads/)) or use an open-source distribution like Adoptium (Eclipse Temurin) ([https://adoptium.net/](https://adoptium.net/)).
    2.  Follow the installation instructions for your operating system.
    3.  **Verify Installation:** Open a terminal or command prompt and run:
        ```bash
        java -version
        javac -version
        ```
        You should see the installed JDK version printed. If not, ensure your `PATH` environment variable is correctly configured to include the JDK's `bin` directory.

**2. No External Libraries/Frameworks**

*   This application uses only standard Java libraries, so you do not need build tools like Maven or Gradle, nor do you need to install any external `.jar` files.

## 🚀 How to Use/Play It

Once you have the JDK installed, you can compile and run the application.

**1. Save the Code Files**

*   Save all the provided `.java` files into a single directory (e.g., `ReportCardApp`). Ensure each file has its original name (e.g., `Main.java`, `ReportCardSystem.java`, `SchoolClass.java`, `Student.java`, `ReportCard.java`, `AdminDashboard.java`, `ClassSelectionPanel.java`, `StudentSelectionPanel.java`, `ReportCardInputPanel.java`).

**2. Compile the Application**

*   Open a terminal or command prompt.
*   Navigate to the directory where you saved the `.java` files:
    ```bash
    cd path/to/your/ReportCardApp
    ```
*   Compile all Java source files:
    ```bash
    javac *.java
    ```
    If there are no compilation errors, `.class` files will be generated for each `.java` file in the same directory.

**3. Run the Application**

*   From the same terminal or command prompt, run the `Main` class:
    ```bash
    java Main
    ```
*   A Swing GUI window titled "Report Card Management System - Administrator" should appear.

---

### **Step-by-Step Usage Guide:**

**1. Administrator Dashboard**

*   Upon launching the application, you'll see the "Administrator Dashboard".
*   Click the **"Online Reports"** button to proceed.

**2. Class Selection**

*   The system will display a list of classes, each with a "Manage Report Cards" button.
    *   *Example:* You'll see "Class: 10A (2023-2024)" with a button next to it.
*   Choose the desired class by clicking the **"Manage Report Cards"** button next to it.
*   To return to the dashboard, click **"Back to Dashboard"**.

**3. Student Selection**

*   After selecting a class, a list of students in that class will be displayed.
*   Each student has an **"Insert Report Card"** button next to their name.
*   Click the **"Insert Report Card"** button for the student whose report you wish to modify.
*   To go back to class selection, click **"Back to Class Selection"**.

**4. Report Card Input**

*   This panel allows you to enter or change grades for the selected student.
*   **Student Information:** The student's name and ID are displayed at the top.
*   **Grade Entry Fields:** For each subject (e.g., Math, Science, History, English, Art), there's a text field.
    *   Enter numerical grades (e.g., `85.5`, `92`).
    *   **Validation:** The system expects numeric values between 0 and 100. Entering non-numeric text or values outside this range will result in an error message upon saving.
    *   **Partial Entry:** You can enter grades for all subjects, or just a part of them. Leaving a field blank will remove/keep the grade unset for that subject.
*   **Actions:**
    *   **"Save Report Card"**: Click this button to save the entered grades. If successful, you'll see a "Report card saved successfully!" message, and the application will return to the Student Selection page for the current class.
    *   **"Cancel"**: Click this button to discard any changes and return to the Student Selection page without saving. This simulates the user interrupting the operation.
    *   **"Simulate SMOS Server Error"**: Click this button to trigger a pop-up message simulating a "Connection to the SMOS server interrupted" error. This demonstrates an edge case without affecting the application's state.

**5. Navigation and Flow**

*   The application flow follows the sequence:
    `Admin Dashboard -> Class Selection -> Student Selection -> Report Card Input`
*   "Back" buttons are available on Class Selection and Student Selection panels to navigate backwards.

This concludes the user manual for the Report Card Management System.
```