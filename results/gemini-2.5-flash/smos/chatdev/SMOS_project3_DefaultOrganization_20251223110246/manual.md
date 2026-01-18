```
# User Manual: Basic Login/Logout System (Java Swing)

## Introduction

This document serves as a user manual for a simple Java application that demonstrates a basic user login and logout flow. Developed using Java Swing for the graphical user interface (GUI), this application simulates a common pattern found in many systems: a user logging in to access application features and then logging out when finished.

This system is designed for educational or demonstration purposes to illustrate core concepts of session management and UI navigation in a desktop application context.

## Main Functions

The application provides the following core functionalities:

1.  **User Login**:
    *   Allows a registered user to enter a username and password to gain access to the main application.
    *   Includes a simple, hardcoded authentication mechanism for demonstration.
    *   Upon successful login, a "Main Application" screen is displayed.

2.  **Main Application View**:
    *   This is the primary screen displayed after a successful login.
    *   It welcomes the logged-in user and provides a "Logout" button.
    *   It represents the typical dashboard or main interface a user would interact with in a real application.

3.  **User Logout**:
    *   Enables a logged-in user to exit the system and terminate their current session.
    *   Upon clicking the "Logout" button, the system returns to the login form, allowing the user to re-log in or for another user to log in.

## Environment Setup

To compile and run this Java application, you need to have the Java Development Kit (JDK) installed on your system.

### 1. Install Java Development Kit (JDK)

*   **Download**: If you don't have Java installed, download the latest stable version of Oracle JDK or OpenJDK from their official websites.
    *   Oracle JDK: [https://www.oracle.com/java/technologies/downloads/](https://www.oracle.com/java/technologies/downloads/)
    *   OpenJDK: [https://openjdk.java.net/install/](https://openjdk.java.net/install/)
*   **Installation**: Follow the installation instructions provided for your operating system (Windows, macOS, Linux). This typically involves running an installer and accepting default settings.
*   **Set up Environment Variables (if necessary)**: Ensure that your `JAVA_HOME` environment variable is set to the JDK installation directory and that the JDK's `bin` directory is included in your system's `PATH`.

### 2. Verify Installation

Open a terminal or command prompt and type the following commands to verify that Java and `javac` (the Java compiler) are correctly installed and configured:

```bash
java -version
javac -version
```

You should see output indicating the installed Java version.

## How to Use/Play

Follow these steps to compile and run the Basic Login/Logout System:

### 1. Save the Code Files

Save each of the provided Java code blocks into separate `.java` files with the exact names as specified (e.g., `UserSession.java`, `LoginFrame.java`, `MainApplicationFrame.java`, `LogoutApp.java`). Ensure all files are in the same directory.

For example, your directory structure should look like this:

```
my_app_directory/
├── UserSession.java
├── LoginFrame.java
├── MainApplicationFrame.java
└── LogoutApp.java
```

### 2. Compile the Application

Navigate to the directory where you saved your Java files using your terminal or command prompt. Then, compile all the Java source files:

```bash
cd path/to/my_app_directory
javac UserSession.java LoginFrame.java MainApplicationFrame.java LogoutApp.java
```

If the compilation is successful, no output will be displayed, and `.class` files will be generated in the same directory (e.g., `UserSession.class`, `LoginFrame.class`, etc.).

### 3. Run the Application

After successful compilation, run the main application using the `java` command, specifying the class containing the `main` method (`LogoutApp`):

```bash
java LogoutApp
```

### 4. Interact with the Application

Upon running, a "Login to System" window will appear.

*   **Login**:
    *   **Dummy Credentials**: The application uses hardcoded credentials for demonstration.
        *   **Username**: `user`
        *   **Password**: `password`
    *   Enter `user` in the Username field and `password` in the Password field.
    *   Click the "Login" button or press Enter.
    *   If successful, a "Login Successful!" message will appear, and then the "Main Application - Logged In" window will be displayed.
    *   If unsuccessful, an "Invalid Username or Password!" message will appear on the login form.

*   **Main Application**:
    *   Once logged in, you will see a welcome message, e.g., "Welcome, user!".
    *   There will be a "Logout" button at the bottom of the window.

*   **Logout**:
    *   Click the "Logout" button.
    *   A "You have been successfully logged out." message will appear.
    *   The "Main Application" window will close, and the "Login to System" window will reappear, allowing you to log in again or close the application.

### 5. Exit the Application

You can close the application at any time by clicking the `X` button on the window title bar.
```