```
# Password Change Application

## Introduction

The Password Change Application is a simple Java Swing-based graphical user interface (GUI) designed to simulate a user's attempt to change their password. Its primary purpose, as per the `ERROREMODIFICAPASSWORD` (Password Change Error) use case, is to demonstrate the error handling when a user enters a new password and its confirmation incorrectly (i.e., they do not match).

This application allows users to:
*   Input a desired new password.
*   Confirm the new password by re-entering it.
*   Attempt to change the password.
*   Receive an error notification if the new password and confirmation do not match or if fields are left empty.
*   Receive a success notification if the passwords match (simulated scenario).

## Environment Dependencies

To compile and run this application, you will need a Java Development Kit (JDK) installed on your system.

*   **Java Development Kit (JDK)**: Version 8 or newer is recommended.
    *   You can download the latest JDK from Oracle's official website or use an open-source distribution like OpenJDK.
    *   No additional libraries or dependencies are required, as Java Swing is included as part of the standard JDK.

## Installation and Setup

Since this is a single `.java` file, there's no complex installation process.

1.  **Save the Code**: Save the provided Java code (from `passwordchangeapp.java`) into a file named `PasswordChangeApp.java` in a directory of your choice.
2.  **Open Terminal/Command Prompt**: Navigate to the directory where you saved `PasswordChangeApp.java` using your terminal or command prompt.

## How to Use/Play

Follow these steps to compile and run the Password Change Application:

### 1. Compile the Application

Execute the following command in your terminal/command prompt to compile the Java source file:

```bash
javac PasswordChangeApp.java
```

If the compilation is successful, a `PasswordChangeApp.class` file will be generated in the same directory.

### 2. Run the Application

After successful compilation, run the application using the following command:

```bash
java PasswordChangeApp
```

A graphical window titled "Password Change" will appear.

### 3. Application Interface

The application window consists of:
*   A "New Password:" label followed by a password input field.
*   A "Confirm Password:" label followed by a password input field.
*   A "Confirm Change" button.

### 4. Demonstrating the `ERROREMODIFICAPASSWORD` Use Case (Password Mismatch Error)

This is the primary scenario demonstrated by the application:

1.  **Enter Mismatched Passwords**: In the "New Password:" field, type a password (e.g., `password123`).
2.  In the "Confirm Password:" field, type a *different* password (e.g., `passwordabc`).
3.  **Click "Confirm Change"**: Press the "Confirm Change" button.
4.  **Observe Error Message**: An error dialog box will appear with the title "Password Mismatch Error" and the message: "The new password and confirmation do not match. Please try again." This fulfills the "Notify an error message" event.
5.  **Acknowledge Error**: Click the "OK" button on the error dialog. This simulates the "Confirmation of the reading of the notification."
6.  **Return to Input**: The application will remain on the password change screen, allowing you to correct your input. This fulfills the "Back to change your password" event.

### 5. Demonstrating Empty Password Fields Error

1.  **Leave Fields Empty**: Leave both the "New Password:" and "Confirm Password:" fields empty, or fill only one.
2.  **Click "Confirm Change"**: Press the "Confirm Change" button.
3.  **Observe Error Message**: An error dialog box will appear with the title "Input Error" and the message: "Password fields cannot be empty."
4.  **Acknowledge Error**: Click the "OK" button on the error dialog.
5.  **Return to Input**: The application will remain on the password change screen.

### 6. Demonstrating Successful Password Change (Simulated)

For completeness, this scenario shows what happens if the passwords *do* match:

1.  **Enter Matching Passwords**: In the "New Password:" field, type a password (e.g., `MySecret123`).
2.  In the "Confirm Password:" field, type the *exact same* password (`MySecret123`).
3.  **Click "Confirm Change"**: Press the "Confirm Change" button.
4.  **Observe Success Message**: A success dialog box will appear with the title "Success" and the message: "Password successfully changed!"
5.  **Acknowledge Success**: Click the "OK" button on the success dialog. The password fields will then be cleared, and the system returns control to the user interaction.

To exit the application, simply close the window.
```