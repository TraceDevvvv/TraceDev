```markdown
# ATA Staff - Class Viewer Application

This document serves as a user manual for the ATA Staff - Class Viewer application.

## 🤔 What is this?

The "ATA Staff - Class Viewer" is a simple desktop application designed for staff members of ATA to view a list of available classes (referred to as courses internally). Upon successful login as a staff member, the system displays a comprehensive list of all classes stored in its database. Users can then select a specific class from the list to view its detailed registry information.

**Key Features:**

*   **Secure Login:** Staff users must authenticate with a username and password.
*   **Class Listing:** Displays all available classes with brief details (ID, Name, Instructor, Schedule, Description).
*   **Detailed Class View:** Allows staff to access and view the full details of any selected class by double-clicking on it.

## ⚙️ Environment Setup

To run this application, you need a Java Development Kit (JDK) installed on your system.

### Dependencies

*   **Java Development Kit (JDK) 8 or higher:** The application is written in Java and uses Swing for its graphical user interface. A compatible JDK is required to compile and run the code.

### Installation Steps for JDK

1.  **Download JDK:** Visit the Oracle website or OpenJDK website to download the appropriate JDK version for your operating system.
2.  **Install JDK:** Follow the installation instructions provided by the JDK installer.
3.  **Set `JAVA_HOME` (Optional but Recommended):**
    *   **Windows:** Set the `JAVA_HOME` environment variable to the path where your JDK is installed (e.g., `C:\Program Files\Java\jdk-17`). Add `%JAVA_HOME%\bin` to your `Path` environment variable.
    *   **macOS/Linux:** Set `JAVA_HOME` in your shell profile (e.g., `.bashrc`, `.zshrc`) to your JDK path (e.g., `/Library/Java/JavaVirtualMachines/jdk-17.0.1.jdk/Contents/Home`). Add `$JAVA_HOME/bin` to your `PATH`.
    *   Restart your terminal or command prompt after setting environment variables.
4.  **Verify Installation:** Open a terminal or command prompt and run:
    ```bash
    java -version
    javac -version
    ```
    You should see the installed Java and Javac compiler versions.

## ▶️ How to Use/Play It

Follow these steps to compile and run the ATA Staff - Class Viewer application.

### 1. Save the Code Files

1.  Create a new directory for your project (e.g., `ATAManagement`).
2.  Inside this directory, save each provided `.java` file into its own file with the respective name (e.g., `User.java`, `Class.java` becomes `Course.java`, `AuthService.java`, `ClassService.java`, `LoginFrame.java`, `ClassListFrame.java`, `MainApp.java`, `Course.java`).
    *   **Important Note:** The original `Class.java` file was renamed to `Course.java` in the provided code to avoid conflict with Java's reserved `Class` keyword. Ensure you rename `Class.java` to `Course.java` when saving and update any import statements or class references if you integrate manual fixes (though the provided code already reflects this change across files).

    Your project directory structure should look something like this:

    ```
    ATAManagement/
    ├── Course.java
    ├── User.java
    ├── AuthService.java
    ├── ClassService.java
    ├── LoginFrame.java
    ├── ClassListFrame.java
    └── MainApp.java
    ```

### 2. Compile the Application

Navigate to your project directory in a terminal or command prompt and compile all `.java` files:

```bash
cd ATAManagement
javac *.java
```

If compilation is successful, you will see `.class` files generated for each `.java` file in the same directory. No output messages usually indicate success.

### 3. Run the Application

Execute the `MainApp` class to start the application:

```bash
java MainApp
```

### 4. Using the Application

Once you run the application, the `LoginFrame` will appear:

#### A. Login Screen

1.  **Enter Credentials:**
    *   **Username:** `staff`
    *   **Password:** `password`
    *   *Note: In a real application, passwords would be hashed and stored securely. These are for demonstration purposes.*
2.  **Click "Login"** or press `Enter`.

#### B. Class List Screen

1.  If the login is successful, the `LoginFrame` will close, and the "ATA Staff - View Classes" window (`ClassListFrame`) will appear.
2.  This window displays a table listing all available classes with their ID, Name, Instructor, Schedule, and Description.
3.  **View Class Details:** To see the full registry details for a specific class, **double-click** on any row in the table. A pop-up dialog will appear with detailed information about the selected class.

#### C. Exit

To exit the application, simply close the "ATA Staff - View Classes" window.