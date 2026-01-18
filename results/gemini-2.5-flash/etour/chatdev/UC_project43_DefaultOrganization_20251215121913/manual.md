```
# MODIFICADATIPUNTODIRISTORO - Refreshment Point Editor

This application, named "MODIFICADATIPUNTODIRISTORO" (Change data point associated refreshment), provides a graphical user interface (GUI) for operators to manage and update information for various refreshment points. It acts as a system where an authenticated operator can select a refreshment point, view its details, modify them, and save the changes. The system includes data validation and confirmation steps to ensure data integrity.

## 🤔 What is this?

This is a simple Java Swing application designed to:
*   **Enable operators** to manage refreshment point data.
*   **Load details** of an existing refreshment point into an editable form.
*   **Allow modification** of properties such as name, address, contact phone, and description.
*   **Validate input data** before saving changes.
*   **Provide confirmation** for saving operations.
*   **Handle scenarios** like invalid data, operator cancellation, and simulated server connection interruptions.

## 🚀 Main Functions

The application offers the following core functionalities:

1.  **Select and Load Refreshment Point**: Operators can choose an existing refreshment point ID from a dropdown list and load its current data into the form for editing.
2.  **Display and Modify Data**: Once loaded, the refreshment point's name, address, contact phone, and description are displayed in editable fields. The operator can then make necessary changes.
3.  **Data Validation**: Upon submitting changes, the system performs validation checks on the entered data (e.g., ensuring required fields are not empty, phone number format). If validation fails, appropriate error messages are displayed.
4.  **Confirmation of Changes**: Before saving, the operator is prompted to confirm the update operation.
5.  **Save Changes**: If confirmed and valid, the modified data is saved back into the system.
6.  **Cancel Operation**: Operators can cancel an ongoing edit operation, discarding any unsaved changes and reverting the form to its initial state.
7.  **Error Handling**: The application handles various error conditions, such as invalid input, inability to load data, or simulated server connection issues, providing user-friendly feedback.

## 🛠️ Installation Guide

To run this application, you need to have a Java Development Kit (JDK) installed on your system.

### Environment Dependencies

*   **Java Development Kit (JDK)**: Version 8 (1.8) or higher.

### Steps to Install and Set up JDK (if not already done)

1.  **Download JDK**: Visit the Oracle website or adoptium.net (for OpenJDK) to download the appropriate JDK installer for your operating system.
2.  **Install JDK**: Follow the instructions provided by the installer.
3.  **Set JAVA_HOME (Optional but Recommended)**:
    *   **Windows**: Set `JAVA_HOME` environment variable to your JDK installation path (e.g., `C:\Program Files\Java\jdk1.8.0_XXX`). Add `%JAVA_HOME%\bin` to your system's `Path` variable.
    *   **macOS/Linux**: Set `JAVA_HOME` to your JDK installation path (e.g., `/Library/Java/JavaVirtualMachines/jdk1.8.0_XXX.jdk/Contents/Home` or `/usr/lib/jvm/java-8-openjdk`). Add `$JAVA_HOME/bin` to your `PATH` variable in your shell configuration file (e.g., `.bashrc`, `.zshrc`).
4.  **Verify Installation**: Open a command prompt or terminal and type:
    ```bash
    java -version
    javac -version
    ```
    You should see output indicating the installed Java version.

## 🚀 How to Use/Play

Once you have the JDK set up and the application's source code, follow these steps to compile and run the application.

### Prerequisites

*   JDK 8 or higher installed and configured.
*   The entire project structure (folders `dao`, `gui`, `model`, `service`, and `Main.java` in the root) should be available.

### Running the Application

1.  **Navigate to the Project Root**: Open your command line interface (CMD, PowerShell, Terminal) and change your current directory to the folder containing `Main.java` and the `dao`, `gui`, `model`, `service` subdirectories.

    ```bash
    cd path/to/your/project/folder
    ```

2.  **Compile the Source Code**: Compile all Java files using `javac`. The `-d .` flag tells the compiler to place the compiled `.class` files in the current directory and its subdirectories, preserving the package structure.

    ```bash
    javac -d . */*.java Main.java
    ```
    *Note: If you encounter issues, ensure your `CLASSPATH` is correctly set or compile from the root directory.*

3.  **Run the Application**: After successful compilation, run the `Main` class.

    ```bash
    java Main
    ```

### Step-by-step Usage

1.  **Launch**: A window titled "MODIFICADATIPUNTODIRISTORO - Change Refreshment Point Data" will appear.
2.  **Select Refreshment Point**:
    *   In the "Select Point ID:" dropdown at the top, you will see a list of pre-loaded sample refreshment points (e.g., "RP001", "RP002", "RP003").
    *   Choose an ID from the list.
3.  **Load Data**:
    *   Click the "Load Data" button.
    *   The form fields (Name, Address, Contact Phone, Description) will populate with the data of the selected refreshment point. The "Save Changes" and "Cancel" buttons will also become enabled.
4.  **Modify Data**:
    *   Click on any of the text fields (Name, Address, Contact Phone) or the text area (Description) and change their content.
5.  **Save Changes**:
    *   After making your modifications, click the "Save Changes" button.
    *   **Validation**: If any required fields are empty or the contact phone number format is invalid, an "Error" dialog will appear listing the validation issues. You will need to correct them and click "Save Changes" again.
    *   **Confirmation**: If the data is valid, a "Confirm Changes" dialog will ask for your confirmation. Click "Yes" to proceed with saving or "No" to return to the form without saving.
    *   **Success/Error Notification**: Upon successful save, an "Information" dialog will confirm the update. If a simulated server error occurs, an "Error" dialog will inform you.
    *   After a successful save, the form will clear, and the buttons will be disabled, indicating the completion of the operation.
6.  **Cancel Operation**:
    *   If you have made changes but decide not to save them, click the "Cancel" button.
    *   A "Confirm Cancellation" dialog will ask for confirmation. Click "Yes" to discard changes and clear the form, or "No" to continue editing.
7.  **Exit**: Close the application window to exit.

Enjoy managing your refreshment points!
```