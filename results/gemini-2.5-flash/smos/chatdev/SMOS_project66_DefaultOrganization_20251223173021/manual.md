```markdown
# SMOS - Online Report Card System User Manual

## Introduction

The "SMOS - Online Report Card System" is a Java-based desktop application designed to provide "Direction" (e.g., school administrators, faculty leads) with an intuitive interface to view student report cards. This system simulates the process of accessing a student's academic performance by allowing users to select an academic year, a specific class, a student, and a particular quadrimestre (semester) to display their detailed report card.

**Main Functions:**

*   **Academic Year Selection:** Easily switch between different academic years to access historical or current data.
*   **Class Listing:** View all classes available for the selected academic year.
*   **Student Listing:** See a list of students enrolled in the chosen class.
*   **Quadrimestre Selection:** Specify the period (e.g., Q1, Q2) for which the report card is desired.
*   **Report Card Display:** Presents a detailed report card including grades for various subjects and general comments for the selected student and quadrimestre.

## Environment Dependencies Installation

This project is developed in Java and relies solely on the Java Development Kit (JDK) for compilation and execution. No external libraries or build tools (like Maven or Gradle) are required as it uses standard `javax.swing` and `java.awt` components.

To run this application, you need to have a **Java Development Kit (JDK)** installed on your system.

1.  **Download JDK:**
    *   Visit the official Oracle JDK download page or OpenJDK page (e.g., Adoptium OpenJDK, Amazon Corretto, Azul Zulu) to download the appropriate JDK version for your operating system (Java 8 or newer is recommended, Java 11 or 17 are common LTS versions).

2.  **Install JDK:**
    *   Follow the installation instructions provided by the JDK installer for your operating system. This typically involves running an executable (.exe for Windows), a package manager (.deb/.rpm for Linux), or a .dmg for macOS.

3.  **Set Up Environment Variables (if needed):**
    *   After installation, it's good practice to ensure `JAVA_HOME` is set to your JDK installation directory and that the `bin` directory of your JDK is added to your system's `PATH` environment variable. This allows you to run Java commands from any directory in your terminal.
        *   **Windows:** Search for "Environment Variables" -> "Edit the system environment variables" -> "Environment Variables..." -> Under "System variables", create a new variable `JAVA_HOME` pointing to your JDK root directory (e.g., `C:\Program Files\Java\jdk-17`). Then, edit the `Path` variable and add `%JAVA_HOME%\bin` to the list.
        *   **macOS/Linux:** Open your shell configuration file (`~/.bash_profile`, `~/.zshrc`, etc.) and add:
            ```bash
            export JAVA_HOME=/path/to/your/jdk # e.g., /usr/lib/jvm/jdk-17 or /Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home
            export PATH=$JAVA_HOME/bin:$PATH
            ```
            Then run `source ~/.bash_profile` (or your respective file) to apply changes.

4.  **Verify Installation:**
    *   Open a new terminal or command prompt and type:
        ```bash
        java -version
        javac -version
        ```
    *   You should see output similar to `openjdk version "17.0.x"` or `java version "17.0.x"`, indicating that Java is correctly installed and configured.

## How to Compile and Run the Application

Follow these steps to compile the source code and run the Report Card Viewer application:

1.  **Organize Source Files:**
    Place the provided `.java` files into the following directory structure:

    ```
    .
    ├── Main.java
    ├── data/
    │   └── DataManager.java
    ├── gui/
    │   ├── LoginFrame.java
    │   └── ReportCardViewFrame.java
    └── models/
        ├── AcademicYear.java
        ├── ReportCard.java
        ├── Student.java
        └── StudentClass.java
    ```
    Make sure the `data`, `gui`, and `models` subdirectories are created under the same root directory as `Main.java`.

2.  **Compile the Code:**
    Open a terminal or command prompt, navigate to the root directory where `Main.java` is located (the parent directory of `data`, `gui`, `models`), and execute the Java compiler:

    ```bash
    javac data/*.java gui/*.java models/*.java Main.java
    ```
    This command compiles all `.java` files in the specified packages and the main class. If compilation is successful, it will generate `.class` files in the respective directories.

3.  **Run the Application:**
    From the same root directory, run the main application:

    ```bash
    java Main
    ```

## How to Use the Application

Once the application is running, you will interact with two main windows:

1.  **Login Simulation (`SMOS - Report Card System`):**
    *   The application starts with a simple "Login Frame". As per the use case's precondition, the user is already logged in as "Direction".
    *   **Action:** Click the **"Online Report Cards"** button.
    *   **Result:** The login window will close, and the main "SMOS - View Student Report Card" window will open.

2.  **Report Card Viewer (`SMOS - View Student Report Card`):**
    This is the main interaction window, guiding you through the steps to find a report card.

    *   **Step 1: Select Academic Year**
        *   At the top left, use the dropdown menu labeled "1. Select Academic Year:".
        *   **Action:** Click on the dropdown and choose an academic year (e.g., "2023-2024", "2022-2023").
        *   **Result:** The "2. Choose Pupil Class" list will populate with classes relevant to the selected year.

    *   **Step 2: Choose Pupil Class**
        *   In the middle-left panel, you'll see a list of classes.
        *   **Action:** Click on a class name in the list (e.g., "10A", "11B").
        *   **Result:** The "3. Select Student" list will populate with students enrolled in the chosen class for that academic year.

    *   **Step 3: Select Student**
        *   In the middle panel, you'll see a list of students. Students are displayed as "LastName, FirstName".
        *   **Action:** Click on a student's name in the list (e.g., "Smith, Alice").
        *   **Result:** The application will now have enough information to potentially display a report card.

    *   **Step 4: Select Quadrimestre & View Report**
        *   In the right panel, there's a dropdown labeled "Quadrimestre:" and a large text area below it.
        *   **Action:** Click on the dropdown and choose a quadrimestre (e.g., "Q1", "Q2").
        *   **Result:** The large text area will display the report card for the selected student for the chosen academic year and quadrimestre. If no report card is available for that specific combination, a message indicating this will be displayed.

    *   **Viewing Multiple Reports:**
        *   You can change the quadrimestre to view different period reports for the same student.
        *   You can select a different student to view their report card.
        *   You can select a different class to see students in that class.
        *   You can select a different academic year to start the process over for another year.

    *   **Closing the Application:**
        *   **Action:** Close the "SMOS - View Student Report Card" window by clicking the 'X' button in the title bar.
        *   **Result:** The application will terminate.

This manual should help users effectively navigate and utilize the SMOS Online Report Card System.
```