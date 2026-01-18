```markdown
# Java Login System - User Manual

## 🚀 Introduction

This document serves as a user manual for the "Java Login System" application. This is a basic, runnable Java Swing application designed to demonstrate a user authentication flow. It simulates a login process, including credential validation, and transitions to a user workspace upon successful login.

The primary goal of this application is to illustrate core concepts of building a simple graphical user interface (GUI) for user login in Java, adhering to certain security best pract (like handling passwords as `char[]`) and application flow requirements.

## ✨ Main Features

The application provides the following key functionalities:

*   **User Authentication:** Allows registered users to log in with a username and password.
*   **Input Validation:** Enforces a minimum length of 5 characters for both username and password during the login attempt.
*   **Credential Verification:** Checks provided credentials against an internal, simulated user archive.
*   **User Workspace Display:** Upon successful login, the system displays a new window representing the user's personalized workspace.
*   **Password Security:** Passwords are handled as `char[]` arrays instead of `String` objects to reduce memory exposure of sensitive data.
*   **Simulated Backend Interaction:** Includes a simulated "interruption of connection to SMOS server" as per the requirements, representing a common post-login action.

## 📋 System Requirements

To compile and run this Java application, you will need:

*   **Java Development Kit (JDK):** Version 8 or newer is recommended. You can download it from Oracle's website or use an open-source distribution like OpenJDK.
    *   **Verification:** Open your command line or terminal and type `java -version` and `javac -version`. You should see output indicating an installed JDK version.

## 🛠️ Installation & Setup

This section guides you through setting up the project and making it runnable.

### 1. Obtain the Source Code

Assume you have the provided Java files: `Main.java`, `User.java`, `AuthService.java`, `LoginFrame.java`, `LoginPanel.java`, and `WorkspaceFrame.java`. These files should be placed in a single directory (e.g., `LoginSystem`).

### 2. Set Up Your Environment

#### Option A: Using an Integrated Development Environment (IDE) - Recommended

For ease of use and development, using an IDE like IntelliJ IDEA or Eclipse is recommended.

1.  **Open IDE:** Launch your preferred Java IDE.
2.  **Create New Project:** Select "Create New Project" or "Import Project".
3.  **Import as Java Project:** If creating, choose a "Java" or "Empty" project type. If importing, point to the directory where you saved the `.java` files.
4.  **Add Files:** Place all the `.java` files into the `src` (source) folder of your new project.
5.  **Build Project:** The IDE should automatically recognize and compile the Java files. If not, trigger a "Build Project" action.

#### Option B: Using Command Line

If you prefer to compile and run from the command line:

1.  **Navigate to Directory:** Open your command line or terminal and navigate to the directory where you saved your `.java` files (e.g., `cd path/to/LoginSystem`).
2.  **Compile:** Compile all the Java source files using the Java compiler:
    ```bash
    javac *.java
    ```
    This command will generate `.class` files for each `.java` file in the same directory.

## ▶️ How to Use the Application

Once the project is set up and compiled, you can run the application.

### 1. Running the Application

#### Option A: From IDE

1.  **Locate `Main.java`:** Find the `Main.java` file in your project structure.
2.  **Run `main` method:** Right-click on `Main.java` and select "Run 'Main.main()'" or click the run button in the IDE's toolbar.

#### Option B: From Command Line

1.  **Navigate to Directory:** Ensure you are in the directory containing the compiled `.class` files.
2.  **Run:** Execute the `Main` class using the Java Virtual Machine:
    ```bash
    java Main
    ```

### 2. Interacting with the Login Interface

A "Login System" window will appear. It contains:

*   `Username:` input field.
*   `Password:` input field.
*   `Login` button.
*   A message label at the bottom to display feedback.

#### Provided Test Credentials:

The application is pre-populated with a few users for testing.
You can use the following credentials:

*   **Username:** `admin`
    **Password:** `password`

*   **Username:** `user123`
    **Password:** `securepass`

*   **Username:** `testuser`
    **Password:** `test12345`

#### Login Steps:

1.  **Enter Username:** Type one of the above usernames into the "Username:" field.
2.  **Enter Password:** Type the corresponding password into the "Password:" field.
3.  **Click Login:** Click the "Login" button.

#### Application Flow:

*   **Invalid Input Length:** If either the username or password has fewer than 5 characters, an error message "Username and password must be at least 5 characters long." will be displayed.
*   **Invalid Credentials:** If the username and password do not match any registered users, an error message "Invalid username or password." will be displayed.
*   **Successful Login:**
    *   The message label will show "Login successful!".
    *   The login window will close.
    *   A new window titled "Welcome to Your Workspace - [username]" will appear, displaying a welcome message.
    *   Messages will be printed to the console indicating successful login, displaying the workspace, and simulating the "interruption of connection to SMOS server."

### 3. Exiting the Application

*   To close the login window (before successful login), click the 'X' button.
*   To close the workspace window (after successful login), click the 'X' button on the workspace window. This will terminate the application.

## 🔒 Security Considerations

The application demonstrates a fundamental security practice by handling passwords as `char[]` arrays using `JPasswordField.getPassword()`. This helps mitigate risks of password exposure in memory compared to `String` objects, which are immutable and potentially stay in memory longer. The `char[]` arrays are explicitly cleared (`Arrays.fill()`) after use. While this is a good practice, a real-world application would require more robust security measures like password hashing, secure storage, and encrypted communication.
```