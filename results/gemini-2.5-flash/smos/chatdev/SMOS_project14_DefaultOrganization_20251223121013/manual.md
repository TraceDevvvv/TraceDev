```
# Class Management System

A simple Java application for administrators to insert new academic classes into a system archive.

## 🤔 What is this?

This "Class Management System" is a small-scale application designed to demonstrate the process of inserting new class data. It provides a graphical user interface (GUI) where an administrator can input details for a new class, such as its name, physical address, and academic year. The system includes basic validation to ensure data integrity before archiving the new class.

**Core Functions:**

*   **Class Data Entry**: Provides an interactive form for administrators to input class details.
*   **Data Validation**: Checks the entered data for completeness and correctness (e.g., ensuring fields are not empty, academic year is a valid number within a reasonable range).
*   **Class Archiving**: Stores successfully validated class data in an in-memory archive.
*   **User Feedback**: Notifies the administrator of success or any data errors through pop-up messages.

## 📖 Installation & Environment Setup

To run this application, you will need a Java Development Kit (JDK) installed on your system.

### Dependencies

*   **Java Development Kit (JDK)**: Version 8 or higher is recommended.
    *   You can download the JDK from Oracle's website or use an open-source distribution like OpenJDK.
    *   Ensure `JAVA_HOME` is set and `java` and `javac` commands are accessible from your terminal.

### Setting up the Project

1.  **Create Project Directory**: Create a new folder for your project, e.g., `ClassManagementSystem`.
2.  **Create Source Files**: Inside the `ClassManagementSystem` directory, create the following Java files with the provided code:
    *   `src/Class.java` (Rename this to `SchoolClass.java` to match `schoolclass.java` in the provided code snippet, or adjust `ClassFormPanel.java` and `ClassArchive.java` to use `Class` instead of `SchoolClass`. For consistency with the provided code, assume `Class.java` is intended to be `SchoolClass.java` and adjust `classarchive.java` and `classformpanel.java` to use `SchoolClass` where `Class` is currently used, OR simplify by using only `SchoolClass` and deleting the `Class.java` from the original input if it's redundant.)
        *   **Correction**: Looking at the provided files, there are `class.java` and `schoolclass.java` which are identical in structure and purpose. For clarity and to avoid naming conflicts, I will assume `schoolclass.java` is the intended class definition for a "school class" and `class.java` is a rename error or a duplicate. The `ClassFormPanel` and `ClassArchive` already use `SchoolClass`. So, ensure `schoolclass.java` contains the content for `SchoolClass` and `class.java` should be ignored or removed to prevent confusion.
    *   `src/SchoolClass.java` (Contains the `SchoolClass` class definition)
    *   `src/ClassArchive.java`
    *   `src/ClassFormPanel.java`
    *   `src/ClassManagementApp.java`

    A typical directory structure would look like this:

    ```
    ClassManagementSystem/
    ├── src/
    │   ├── SchoolClass.java
    │   ├── ClassArchive.java
    │   ├── ClassFormPanel.java
    │   └── ClassManagementApp.java
    ```

### Compiling and Running (Command Line)

1.  **Navigate to Project Root**: Open your terminal or command prompt and navigate to the `ClassManagementSystem` directory.
    ```bash
    cd ClassManagementSystem
    ```
2.  **Compile the Java Files**: Use the `javac` command to compile all `.java` files.
    ```bash
    javac src/*.java
    ```
    If compilation is successful, `.class` files will be generated in the `src` directory.
3.  **Run the Application**: Execute the main application class using the `java` command.
    ```bash
    java -cp src ClassManagementApp
    ```

### Compiling and Running (IDE - e.g., IntelliJ IDEA, Eclipse, VS Code)

1.  **Import Project**: Open your preferred Java IDE.
    *   **IntelliJ IDEA**: "Open" the `ClassManagementSystem` directory.
    *   **Eclipse**: "File" -> "Import" -> "Existing Projects into Workspace", then browse to `ClassManagementSystem`.
    *   **VS Code**: "File" -> "Open Folder", then select `ClassManagementSystem`. Ensure you have the Java Extension Pack installed.
2.  **Build Project**: The IDE should automatically recognize and build the Java files. If not, trigger a build (e.g., "Build" -> "Build Project" in IntelliJ).
3.  **Run `ClassManagementApp`**: Locate `ClassManagementApp.java` in your project explorer, right-click on it, and select "Run 'ClassManagementApp.main()'" or similar.

## ▶️ How to Use

This application simulates the "InsertNewClass" use case for an administrator.

### Prerequisites (Conceptual)

*   The user is assumed to be an administrator and already "logged in" to the system.
*   It's assumed that the administrator has previously viewed a list of classes for a particular academic year and has decided to add a new one, thus clicking a "New Class" button (which directly launches this form in our simplified application).

### Steps to Insert a New Class

1.  **Launch the Application**: Start the `ClassManagementApp` as described in the "Installation & Environment Setup" section.
    *   A window titled "Class Management System - Administrator View" will appear, displaying a form with fields for "Class Name", "Address", and "Academic Year".

2.  **Fill out the Form**:
    *   **Class Name**: Enter the name for the new class (e.g., "Mathematics A", "Biology 101").
    *   **Address**: Enter the physical address associated with this class (e.g., "Room 305, Main Building", "Science Lab 2").
    *   **Academic Year**: Enter the starting year for the academic year (e.g., "2023" for the 2023-2024 academic year).

3.  **Click "Save Class"**: After filling in all the details, click the "Save Class" button.

### System Responses

*   **Successful Insertion**:
    *   If all data is valid, a success message will pop up: "Class '\[Your Class Name]' inserted successfully!".
    *   The form fields will then clear, ready for another entry.
    *   (Optional: Check your console for a "DEBUG: Class added to archive:..." message, indicating it was stored in the in-memory archive).

*   **Data Validation Errors ("Errodati" Use Case)**:
    *   If any required field (Name, Address) is left empty, an "Input Error" message will appear (e.g., "Class Name cannot be empty.").
    *   If the "Academic Year" is not a valid number or is outside a reasonable range (currently 1900-2100), an "Input Error" message will appear (e.g., "Academic Year must be a valid number.", "Academic Year must be a realistic year (e.g., between 1900 and 2100).").
    *   In case of an error, the form will *not* clear, allowing you to correct the invalid input and try saving again.

This application provides a basic but runnable example of data input, validation, and in-memory storage for a class management system.
```