```markdown
# Agency Password Management System User Manual

## 🚀 Introduction

This application, "Agency Password Management System," is a Java Swing-based graphical user interface (GUI) program designed to simulate the process of an Agency Operator logging in and changing their account password. It demonstrates key functionalities such as user authentication, secure password handling, and interaction with a "backend" service, including the simulation of server connection issues.

## ✨ Main Functions

The system provides the following core functionalities:

*   **Operator Login**: An interactive login panel where an Agency Operator can authenticate using a username and password.
*   **Password Change**: A dedicated panel for authenticated operators to change their password, requiring the current password, a new password, and confirmation of the new password.
*   **Secure Password Handling**: Passwords are handled as `char[]` arrays internally to minimize their exposure in memory, a common security practice.
*   **Input Validation**: Basic validation for password fields (e.g., matching new passwords, minimum length) is included.
*   **Simulated Backend Service**: A `UserService` class simulates backend operations like user authentication and password updates.
*   **Server Connection Simulation**: A toggle option to simulate an "Interruption of the connection to the server," allowing users to observe how the application handles network errors.
*   **User Feedback**: Provides visual feedback on login attempts, password change outcomes, and connection status.

## 🛠️ Environment Prerequisites

To compile and run this application, you will need:

1.  **Java Development Kit (JDK)**: Version 8 or higher is required. You can download it from the official Oracle website or use an open-source distribution like OpenJDK.
    *   **Verification**: Open your terminal or command prompt and type `java -version` and `javac -version`. You should see output indicating your installed Java versions.

## 📦 How to Install and Run

Follow these steps to get the application running on your system:

1.  **Save the Code**:
    *   Create a folder on your computer, for example, `PasswordApp`.
    *   Inside this folder, save each of the provided Java code blocks (`User.java`, `UserService.java`, `LoginPanel.java`, `ChangePasswordPanel.java`, `PasswordChangeApp.java`) as separate `.java` files with their respective names.

2.  **Compile the Code**:
    *   Open your terminal or command prompt.
    *   Navigate to the `PasswordApp` directory using the `cd` command (e.g., `cd C:\path\to\PasswordApp` or `cd ~/path/to/PasswordApp`).
    *   Compile all Java files using the Java compiler:
        ```bash
        javac *.java
        ```
        If there are no errors, this command will generate `.class` files for each `.java` file in the directory.

3.  **Run the Application**:
    *   From the same terminal window in the `PasswordApp` directory, execute the main application class:
        ```bash
        java PasswordChangeApp
        ```
    *   The GUI application window should now appear.

## 🎮 How to Use the Application

Once the application window appears, follow these steps to interact with it:

### 1. Login as Agency Operator

*   **Default Credentials**: The system is pre-configured with a default agency operator.
    *   **Username**: `agency_operator`
    *   **Password**: `password123`
*   **Enter Credentials**: Type `agency_operator` into the "Username" field and `password123` into the "Password" field.
*   **Click Login**: Press the "Login" button.
*   **Success**: If login is successful, you will see a "Login successful! Redirecting..." message, and after a short delay, the interface will switch to the "Change Password" panel.
*   **Failure**: If credentials are incorrect, you will see "Invalid username or password."
*   **Empty Fields**: Attempting to log in with empty fields will display "Username and password cannot be empty."

### 2. Change Your Password

*   **Access the Panel**: After a successful login, you will be on the "Change Password for agency_operator" panel.
*   **Provide Current Password**: Enter the operator's current password (`password123`) into the "Current Password" field.
*   **Enter New Password**: Input your desired new password into the "New Password" field.
    *   **Recommendation**: Use a password of at least 6 characters for demonstration purposes.
*   **Confirm New Password**: Re-enter the same new password into the "Confirm New Password" field.
*   **Click "Change Password"**: Press the "Change Password" button.
*   **Success**: If the current password is correct, and the new passwords match and meet basic criteria (e.g., not identical to current, minimum length), you will see "Password changed successfully!" and a confirmation dialog.
*   **Failure/Validation**:
    *   If any password field is empty: "All password fields must be filled."
    *   If new and confirm passwords don't match: "New password and confirm password do not match."
    *   If new password is the same as current: "New password cannot be the same as the current password."
    *   If new password is too short: "New password must be at least 6 characters long."
    *   If the current password provided is incorrect: "Failed to change password. Current password may be incorrect."

### 3. Simulate Server Disconnection

*   **Locate Checkbox**: At the bottom of the application window, you will find a checkbox labeled "Simulate Server Disconnection".
*   **Activate Disconnection**: Check this box. A warning message will confirm that the "Server connection set to DISCONNECTED."
*   **Observe Errors**: While disconnected, attempt to log in or change the password. You will receive "Connection error: Server connection interrupted..." messages.
*   **Restore Connection**: Uncheck the box to re-establish the simulated connection. An information message will confirm "Server connection set to CONNECTED."

### 4. Logout

*   **Click "Logout"**: On the "Change Password" panel, click the "Logout" button.
*   **Return to Login**: The application will clear all fields and return you to the initial "Agency Operator Login" panel.

## ⚠️ Important Notes

*   **Simulation Only**: This application is a simplified simulation for educational and demonstration purposes. It uses an in-memory `UserService` and does not connect to a real database or implement robust enterprise-grade security measures like password hashing (e.g., bcrypt) or strong validation for production environments.
*   **Secure Coding Pract**: The code demonstrates secure pract for handling passwords in memory (using `char[]` and clearing arrays) and comparing them (using `Arrays.equals`). These are crucial for building secure applications.
*   **Swing GUI**: The user interface is built using Java Swing, a standard GUI toolkit for Java desktop applications.
```