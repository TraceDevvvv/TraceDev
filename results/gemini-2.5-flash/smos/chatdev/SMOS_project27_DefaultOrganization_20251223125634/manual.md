```markdown
# Digital Register System - Administrator View

## 📝 Introduction

The Digital Register System is a simplified application designed for educational administrators to view digital academic registers for specific academic years. This application simulates interaction with a backend "SMOS server" to retrieve and display register data, focusing on the core functionality of selecting an academic year and listing associated records.

## 🚀 Main Functions

This system provides the administrator with the following key capabilities:

1.  **SMOS Server Connection Management:**
    *   **Connect to SMOS:** Establishes a simulated connection to the backend SMOS server, which is necessary before viewing any records.
    *   **Disconnect from SMOS:** Terminates the simulated connection, clearing any displayed data and preventing further data retrieval until reconnected.
    *   **Connection Status Display:** Provides real-time feedback on the current connection state (Connected/Disconnected).

2.  **Academic Year Selection:**
    *   **Select Year Interface:** Presents a dialog allowing the administrator to choose an academic year from a predefined range (e.g., current year +/- 5 years).
    *   **Prerequisite for Selection:** Requires an active connection to the SMOS server before an academic year can be selected.

3.  **View Digital Registers:**
    *   **Data Retrieval:** Upon selecting an academic year (and being connected to SMOS), the system fetches all digital register entries pertinent to that year.
    *   **Tabular Display:** Presents the retrieved registers in a clear, organized table format. Each register entry includes details such as ID, Academic Year, Class Name, Subject, Date, and Content.
    *   **Empty Data Handling:** Notifies the administrator if no registers are found for the selected academic year.

## 🛠️ Environment Dependencies

To compile and run this Java application, you will need:

*   **Java Development Kit (JDK):** Version 8 or newer is required. You can download it from the official Oracle website or adopt an OpenJDK distribution like Adoptium (Temurin).
    *   Verify your JDK installation by opening a terminal or command prompt and typing:
        ```bash
        java -version
        javac -version
        ```
        Ensure both commands execute successfully and report a version of 1.8 or higher.

## 🚀 How to Use / Play It

Follow these steps to compile, run, and interact with the Digital Register System:

### 1. Project Setup

1.  **Save the Code:** Ensure all the provided Java files (`RegisterApp.java`, `DigitalRegisterPanel.java`, `AcademicYearSelectionDialog.java`, `Register.java`, `RegisterService.java`) are saved in the *same directory* on your computer.

### 2. Compilation

1.  **Open Terminal/Command Prompt:** Navigate to the directory where you saved your Java files using the `cd` command.
    ```bash
    cd path/to/your/java/files
    ```
2.  **Compile the Java Files:** Use the `javac` compiler to compile all `.java` files.
    ```bash
    javac *.java
    ```
    If there are no errors, this command will generate corresponding `.class` files for each Java source file in the same directory.

### 3. Running the Application

1.  **Execute the Main Application:** Run the `RegisterApp` class, which contains the `main` method.
    ```bash
    java RegisterApp
    ```
    A new window titled "Digital Register System - Administrator View" will appear.

### 4. Interacting with the System

Upon launching, observe the initial state:

*   The "SMOS Connection" status will be "Disconnected" (red text).
*   The "Select Academic Year" button will be disabled.
*   The register table will be empty.

Now, follow these steps to use the application:

1.  **Connect to SMOS Server:**
    *   Click the **"Connect to SMOS"** button.
    *   Observe the "SMOS Connection" status change to "Connected" (blue text). The "Select Academic Year" button will also become enabled.

2.  **Select an Academic Year:**
    *   Click the **"Select Academic Year"** button.
    *   A small dialog box will appear titled "Select Academic Year."
    *   Choose an academic year from the dropdown list (e.g., `2023`, `2022`).
    *   Click **"OK"**.
    *   The dialog will close, and the main window's "Selected Academic Year" label will update. The table will populate with digital register entries for the chosen year.

3.  **Explore Various Years:**
    *   Try selecting different academic years:
        *   **2023:** You should see several records for classes like "10A", "11B".
        *   **2022:** You should see records for "9C", "12A".
        *   **2021:** You should see one record for "8D".
        *   **2020:** The system will display a "No digital registers found" message as there is no mock data for this year, and the table will remain empty.

4.  **Disconnect and Observe:**
    *   Click the **"Disconnect from SMOS"** button.
    *   The "SMOS Connection" status will revert to "Disconnected".
    *   The register table will clear all displayed data.
    *   An informational message "SMOS server connection interrupted" will appear.
    *   The "Selected Academic Year" label will reset to "N/A", and the "Select Academic Year" button will be disabled again.

5.  **Attempt Selection While Disconnected (Error Handling):**
    *   While disconnected, try clicking **"Select Academic Year"**.
    *   A warning message "Please connect to the SMOS server first" will be displayed, demonstrating the precondition handling.

This interactive process allows you to fully test the application's functionality as described in the use case.
```