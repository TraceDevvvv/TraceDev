```markdown
# ChatDev Password Changer Application

This document provides a user manual for the ChatDev Password Changer application. This application demonstrates a common feature in user management systems: allowing a registered user to securely change their password.

## 🤔 What is this?

The ChatDev Password Changer is a stand-alone Java Swing application designed to illustrate the process of changing a user's password within a simulated system. It includes core functionalities such as:

*   **User Input**: A graphical form for users to enter their old password, new password, and confirm the new password.
*   **Password Validation**: Checks if the old password matches the stored password, if the new passwords match each other, and if the new password is different from the old one.
*   **Password Hashing**: Utilizes SHA-256 with a salt for basic password hashing, demonstrating a fundamental security practice (though real-world applications would use stronger, adaptive hashing algorithms like BCrypt).
*   **User Service Simulation**: A backend service that manages users in memory and handles the password change logic.
*   **Error Handling**: Provides feedback to the user for various scenarios, including incorrect old password, new password mismatch, empty passwords, and simulated connection interruptions.

This application acts as a blueprint for implementing similar password management features in larger Java-based systems.

## 📖 Main Functions

The primary function of this software is to enable a logged-in user to securely update their account password.

*   **`User` Model**: Defines a user with a username and a securely hashed password. It includes methods for hashing passwords and verifying plain-text passwords against the stored hash.
*   **`UserService`**: Manages user accounts. It includes functionalities to authenticate users, retrieve the current logged-in user, and an asynchronous method to change a user's password, incorporating validation rules and simulating potential server errors.
*   **`ChangePasswordApp` (GUI)**: Provides the user interface for password change. It captures user input, interacts with the `UserService` to process the change request, and displays real-time feedback to the user on the outcome.

## 💻 Environment Dependencies (Installation)

This project is written entirely in Java and relies solely on the standard Java Development Kit (JDK) libraries. No external third-party dependencies (like Maven or Gradle specific libraries) are required.

### **JDK Requirement**

To compile and run this application, you will need a **Java Development Kit (JDK)** installed on your system.
*   **Recommended JDK Version**: Java 11 or newer (e.g., JDK 11, 17, 21, etc.).

### **How to Check/Install JDK**

1.  **Check if Java is installed**:
    Open your terminal or command prompt and type:
    ```bash
    java -version
    javac -version
    ```
    If these commands return version information, Java and `javac` (the Java compiler) are likely installed and configured correctly.

2.  **Install JDK (if not installed)**:
    If Java is not installed or `javac` is not found, you will need to install a JDK. You can download a suitable JDK from:
    *   **Oracle JDK**: [https://www.oracle.com/java/technologies/downloads/](https://www.oracle.com/java/technologies/downloads/)
    *   **OpenJDK (e.g., Adoptium OpenJDK)**: [https://adoptium.net/temurin/releases/](https://adoptium.net/temurin/releases/)

    Follow the installation instructions for your specific operating system. After installation, ensure that your `JAVA_HOME` environment variable is set and that the JDK's `bin` directory is included in your system's `PATH`.

## ▶️ How to Use/Play

Follow these steps to compile and run the ChatDev Password Changer application:

### **1. Save the Code Files**

Create a directory structure on your local machine that matches the package declarations in the Java files:

```
your_project_directory/
├── src/
│   ├── chatdev/
│   │   ├── app/
│   │   │   └── ChangePasswordApp.java
│   │   ├── service/
│   │   │   └── UserService.java
│   │   └── user/
│   │       └── User.java
```

Place `User.java` into `your_project_directory/src/chatdev/user/`, `UserService.java` into `your_project_directory/src/chatdev/service/`, and `ChangePasswordApp.java` into `your_project_directory/src/chatdev/app/`.

### **2. Compile the Java Source Files**

Open your terminal or command prompt and navigate to `your_project_directory`.
Then, compile the Java source files using the `javac` command:

```bash
cd your_project_directory
javac -d bin src/chatdev/user/User.java src/chatdev/service/UserService.java src/chatdev/app/ChangePasswordApp.java
```
*   `javac`: The Java compiler.
*   `-d bin`: This flag tells the compiler to place the compiled `.class` files into a directory named `bin`. If `bin` doesn't exist, `javac` will create it.
*   `src/...`: Specifies the paths to your source `.java` files.

After successful compilation, you should find the `.class` files in the `bin/chatdev/...` directory.

### **3. Run the Application**

From `your_project_directory`, execute the application using the `java` command. Make sure to set the classpath to include the `bin` directory where your compiled classes reside.

```bash
java -cp bin chatdev.app.ChangePasswordApp
```
*   `java`: The Java Virtual Machine (JVM) launcher.
*   `-cp bin`: This flag adds the `bin` directory to the classpath, so the JVM can find your compiled `.class` files.
*   `chatdev.app.ChangePasswordApp`: This is the fully qualified name of the main class containing the `main` method that starts the application.

### **4. Interact with the GUI**

Upon running the command, a "Change Password" GUI window will appear.

1.  **Simulated Login**: The application automatically simulates a logged-in user with the username "**testuser**". The initial password is "**oldPassword123**". This user is pre-loaded into the `UserService`.
2.  **Fill the Form**:
    *   **Old Password**: Enter the current password for `testuser`, which is `oldPassword123`.
    *   **New Password**: Enter your desired new password.
    *   **Confirm New Password**: Re-enter your desired new password to confirm.
3.  **Click "Change Password"**:
    *   If all conditions are met (correct old password, new passwords match, new password is not the same as old), a "Success: Your password has been changed!" message will appear in green.
    *   If there are validation errors (e.g., incorrect old password, mismatching new passwords, empty fields), an appropriate error message will appear in red.
    *   **Simulated Server Interruption**: There's a small (5%) chance that the `UserService` will simulate a "Connection interruption to the server", demonstrating an edge case.
4.  **Test with new password**: After a successful change, you can attempt to change the password again using the *newly set password* as the "Old Password".

This application provides a clear, runnable example of a password change mechanism, including secure (for demonstration) password handling and user feedback.