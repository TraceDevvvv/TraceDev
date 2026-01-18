```markdown
# Address Management System - Admin Panel

This document serves as a user manual for the "Address Management System - Admin Panel" application. This Java-based desktop application allows an administrator to view, select, and delete addresses from an archive, with specific validation rules regarding associated classes.

## 🤔 What is this?

The "Address Management System - Admin Panel" is a graphical user interface (GUI) application designed for administrators. Its primary function is to manage address records. Administrators can browse a list of addresses, view detailed information for any selected address, and, crucially, delete addresses. The system enforces a business rule: an address cannot be deleted if it has "associated classes." This ensures data integrity by preventing the removal of addresses that are dependencies for other parts of the system.

**Main Functions:**
*   **View All Addresses:** Display a scrollable list of all addresses currently in the system.
*   **View Address Details:** Upon selecting an address from the list, its detailed information (ID, Street, City, and whether it has associated classes) is shown in a separate panel.
*   **Delete Address:** Provides a button to initiate the deletion of the currently selected address.
    *   **Pre-deletion Check:** Automatically checks if the selected address has any associated classes.
    *   **Deletion Prevention:** If associated classes are found, the system will prevent deletion and display an informative error message.
    *   **Successful Deletion:** If no associated classes exist, the address will be removed, and the list of addresses will be updated. A simulated message regarding an interrupted SMOS server connection will also be displayed.

## 📖 Environment Dependencies

To run this application, you will need the following:

*   **Java Development Kit (JDK):** Version 8 or higher is required. This includes the Java Runtime Environment (JRE) and the necessary tools for compiling Java source code (`javac`).
*   **Command Line Interface (CLI):** A terminal or command prompt (e.g., PowerShell, Command Prompt on Windows, Bash on Linux/macOS) is needed to compile and run the Java files.
*   **(Optional) Integrated Development Environment (IDE):** An IDE like IntelliJ IDEA, Eclipse, or VS Code with Java extensions can simplify the compilation and execution process, but it is not strictly necessary.

**Note:** The application uses standard Java Swing for the GUI and does not have any external library dependencies beyond the JDK itself.

## ⚙️ How to Install Environment Dependencies

### 1. Install Java Development Kit (JDK)

If you don't already have Java installed, you can download and install a JDK:

*   **Oracle JDK:** Visit the [Oracle Java website](https://www.oracle.com/java/technologies/downloads/).
*   **OpenJDK (e.g., Adoptium Temurin):** Visit the [Adoptium website](https://adoptium.net/temurin/releases/).

Follow the installation instructions provided by your chosen JDK vendor.
Ensure that the `JAVA_HOME` environment variable is set to the JDK installation directory and that `java` and `javac` commands are accessible from your system's PATH. You can verify your installation by opening a terminal and typing:

```bash
java -version
javac -version
```

This should display the installed Java and Java compiler versions.

## 🚀 How to Use/Play It

Follow these steps to compile and run the Address Management System:

### 1. Save the Source Code

1.  Create a new directory (e.g., `AddressApp`) on your computer.
2.  Save the following three Java files into this directory:
    *   `Address.java`
    *   `AddressService.java`
    *   `AddressApp.java`

Ensure the file names match exactly, including capitalization.

### 2. Compile the Application

1.  Open your terminal or command prompt.
2.  Navigate to the directory where you saved the `.java` files using the `cd` command. For example:
    ```bash
    cd path/to/your/AddressApp
    ```
3.  Compile the Java source files using the `javac` command:
    ```bash
    javac Address.java AddressService.java AddressApp.java
    ```
    If successful, this command will create `.class` files for each Java source file in the same directory. If there are any errors, ensure your JDK is correctly installed and your files are saved correctly.

### 3. Run the Application

1.  From the same terminal window, run the compiled application using the `java` command:
    ```bash
    java AddressApp
    ```
2.  A new window titled "Address Management System - Admin Panel" will appear.

### 4. Interacting with the Application (Administrator Workflow)

Once the application is running, you can perform the following actions:

#### **4.1. View Addresses**

*   Upon launching, the left panel ("Available Addresses") will automatically populate with a list of sample addresses. Each address will be displayed in the format: `ID - Street, City`.
*   The status bar at the bottom will show "Addresses loaded successfully."

#### **4.2. View Detailed Information of an Address**

*   **Select an address:** Click on any address in the "Available Addresses" list.
*   **Details Display:** The central panel ("Address Details and Actions") will update to show:
    *   `Address ID:`
    *   `Street:`
    *   `City:`
    *   `Has Associated Classes:` (either `Yes` or `No`)
*   The status bar will indicate "Selected address details are displayed."
*   If you deselect an address (e.g., by clicking outside the list or if the list refreshes), the details panel will clear.

#### **4.3. Delete an Address**

This section describes the core use case for the administrator, including handling prerequisites and outcomes.

1.  **Select an address:** Choose an address from the "Available Addresses" list that you wish to delete.
    *   **Example Addresses:**
        *   Addresses like `ADDR001`, `ADDR003`, `ADDR005` in the sample data **do not** have associated classes and can potentially be deleted.
        *   Addresses like `ADDR002`, `ADDR004` in the sample data **do** have associated classes and **cannot** be deleted.

2.  **Click the "Delete Address" button:** This button is located in the bottom-right corner of the central panel.

3.  **System Response based on Associated Classes:**

    *   **Scenario A: Address has Associated Classes (Deletion Blocked)**
        *   If the selected address (e.g., `ADDR002`) shows "Has Associated Classes: Yes" in its details, clicking "Delete Address" will display an error message dialog:
            ```
            "Unable to delete the address, delete the associated classes and try again."
            ```
        *   The address will remain in the list, and the status bar will update to "Deletion failed: Address has associated classes."
        *   You must dismiss the error dialog to continue.

    *   **Scenario B: Address Does NOT Have Associated Classes (Deletion Possible)**
        *   If the selected address (e.g., `ADDR001`) shows "Has Associated Classes: No" in its details, clicking "Delete Address" will bring up a confirmation dialog:
            ```
            "Are you sure you want to delete address: [Address Details]?"
            ```
        *   **Confirm Deletion:**
            *   Click "Yes".
            *   The address will be removed from the "Available Addresses" list.
            *   The details panel will clear.
            *   The status bar will update to "Address '[Address Details]' successfully deleted."
            *   A success message dialog will appear, stating:
                ```
                "Address deleted. Connection to SMOS server interrupted (simulated)."
                ```
                (Note: "Connection to SMOS server interrupted" is a simulated postcondition.)
            *   In the console where you ran `java AddressApp`, you will also see:
                ```
                SIMULATION: Connection to SMOS server interrupted for deletion process.
                ```
        *   **Cancel Deletion:**
            *   Click "No".
            *   The address will remain in the list.
            *   The status bar will update to "Address deletion cancelled by user."

#### **4.4. Closing the Application**

*   You can close the application window by clicking the 'X' button (or equivalent) in the title bar of the window.

## 💡 Important Notes

*   **Administrator Preconditions:** The application implicitly assumes the user is logged in as an administrator and has "viewed dettaclizzazione" (viewed detailed information) by presenting the addresses and their details.
*   **Data Persistence:** The address data is simulated and stored only in memory (`AddressService`). Any changes (deletions) will be lost when the application is closed and restarted.
*   **SMOS Server:** The "Connection to the interrupted SMOS server" is a simulated event, represented by a print statement in the console and a message in the success dialog. In a real-world application, this would involve actual backend logic or external system interaction.
```