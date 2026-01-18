```
# Login Application User Manual

## 🚀 Introduction

This document provides instructions for using the `LoginError` application. This simple Java Swing application demonstrates a basic login form that simulates a login error scenario, as per the specified use case: "LoginError occurs when the user has entered incorrect authentication data." The application is designed to always simulate a failed login attempt, displaying an error message to the user.

## 🌟 Main Functions

The `LoginError` application offers the following primary function:

*   **Display Login Form**: Presents a graphical user interface (GUI) with fields for a username and password, and a login button.
*   **Simulate Login Error**: When the user attempts to log in by clicking the "Login" button, the system will always display an error message indicating invalid credentials, fulfilling the use case's precondition that the provided login data is incorrect.

## 🛠️ Environment Setup

To run this Java application, you need to have a Java Development Kit (JDK) installed on your system.

### 1. Install Java Development Kit (JDK)

*   **Check for Java**: Open your terminal or command prompt and type `java -version` and `javac -version`. If Java is installed, you will see version information.
*   **Install JDK**: If Java is not installed or you have an older version, download and install a recent JDK (e.g., OpenJDK, Oracle JDK) from their official websites. Ensure you install the JDK (which includes the Java compiler `javac`), not just the JRE (Java Runtime Environment).
*   **Set JAVA_HOME (Optional but Recommended)**: It's good practice to set the `JAVA_HOME` environment variable to your JDK installation directory and add `%JAVA_HOME%\bin` (Windows) or `$JAVA_HOME/bin` (Linux/macOS) to your system's `PATH`.

### 2. No External Dependencies

This application uses standard Java Swing libraries, which are part of the JDK. There are no external libraries or `requirements.txt` to install.

## 🎮 How to Use/Play

Follow these steps to compile and run the `LoginError` application:

### 1. Save the Source Code

Save the provided Java code into two separate files in the same directory:

*   `LoginApp.java`
*   `LoginForm.java`

For example, create a folder named `LoginErrorApp` and place both `.java` files inside it.

### 2. Compile the Java Source Files

Open your terminal or command prompt and navigate to the directory where you saved the Java files. Then, compile the code using the Java compiler (`javac`):

```bash
javac LoginApp.java LoginForm.java
```

If the compilation is successful, `.class` files (e.g., `LoginApp.class`, `LoginForm.class`) will be generated in the same directory.

### 3. Run the Application

Execute the compiled application by running the `LoginApp` class:

```bash
java LoginApp
```

A login form window will appear.

### 4. Interact with the Login Form

1.  **Enter Credentials**: You can type anything into the "Username" and "Password" fields. For example, `user` for username and `pass` for password.
2.  **Click Login**: Click the "Login" button.
3.  **Observe Error**: As per the use case's precondition, the application will always simulate an incorrect login. A red error message "Invalid username or password. Please try again." will be displayed below the login button.
4.  **Retry**: The password field will be cleared, and you can try logging in again. The application is designed to allow continuous login attempts, as stated in the postcondition: "The user can try to log in."

This demonstrates the "LoginError" scenario where the system correctly handles and displays feedback for incorrect authentication data.

```