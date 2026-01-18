```markdown
# Login Application User Manual

This document provides a comprehensive guide for using the ChatDev Login Application. This application demonstrates a secure and robust user authentication system, handling various scenarios including successful login, incorrect credentials, connection interruptions, and user cancellations.

## 🤔 What is this?

The ChatDev Login Application is a standalone Java Swing application designed to facilitate user authentication. It simulates a real-world login process, where a registered user enters credentials to access a secure "work area." The application showcases best pract in GUI development, concurrent programming (using `SwingWorker` for authentication), and error handling.

## 🌟 Key Features

*   **User Authentication**: Allows a registered user to log in with a predefined username and password.
*   **Secure Password Handling**: Uses `JPasswordField` and retrieves passwords as `char[]` arrays to mitigate security risks associated with `String` objects.
*   **Error Handling**:
    *   **Incorrect Credentials (`LoginErrato` Use Case)**: Informs the user if the username or password is incorrect, allowing them to retry.
    *   **Connection Interruption (`ETOUR` Use Case)**: Simulates potential network or server issues during authentication, alerting the user to check their connection.
    *   **Input Validation**: Checks for empty username or password fields before attempting authentication.
*   **User Cancellation**: Provides an option for the user to cancel the login process and exit the application.
*   **Main Work Area**: Upon successful login, the system displays a "work area" specific to the authenticated user, fulfilling the main goal of the login process.
*   **Responsive UI**: Uses `SwingWorker` to perform authentication on a background thread, ensuring the GUI remains responsive during network delays or processing.

## 🛠️ System Requirements

To run this application, you need:

*   **Java Development Kit (JDK) 8 or higher**: Ensures you have the Java Runtime Environment (JRE) to run the compiled code and the Java compiler (`javac`) to build the application from source.

## 📦 Installation & Setup

Follow these steps to set up and run the Login Application:

1.  **Ensure Java JDK is Installed**:
    *   Open a terminal or command prompt.
    *   Run `java -version`. If Java is installed, you will see version information. If not, download and install a suitable JDK from Oracle or AdoptOpenJDK.
    *   Verify `javac` by running `javac -version`.

2.  **Obtain the Source Code**:
    *   You should have received the following Java source files:
        *   `LoginApp.java`
        *   `AuthService.java`
        *   `ConnectionInterruptionException.java`
        *   `LoginFrame.java`
        *   `MainApplicationFrame.java`
    *   Place all these files in the same directory (e.g., `login_app`).

3.  **Compile the Source Code**:
    *   Navigate to the directory where you saved the `.java` files using your terminal or command prompt.
    *   Compile all Java files using the Java compiler:
        ```bash
        javac *.java
        ```
    *   If compilation is successful, you will see no output, and `.class` files will be generated in the same directory. If there are errors, ensure your JDK is correctly installed and configured.

4.  **Run the Application**:
    *   From the same directory, run the main application class:
        ```bash
        java LoginApp
        ```
    *   The Login Application window should appear on your screen.

## 🚀 How to Use

The application follows a standard login flow:

1.  **Activate the Login Feature**: The application launches directly into the login screen (`LoginFrame`) upon execution.

2.  **Fill Out the Form**:
    *   **Username Field**: Enter `user`
    *   **Password Field**: Enter `password`

    *(Note: These are the hardcoded correct credentials for this demonstration. In a real system, credentials would be stored securely and managed dynamically.)*

3.  **Attempting Login**:
    *   Click the "Login" button to submit your credentials.
    *   Alternatively, you can press Enter when one of the input fields has focus.

4.  **Login Outcomes**:

    *   **Successful Login**:
        *   If you enter `user` (username) and `password` (password), you will see a "Login Successful" message.
        *   The login window will close, and the "ChatDev Work Area" (MainApplicationFrame) will open, welcoming you. This fulfills the exit condition: "The system displays the area of work registered."
        *   To log out, click the "Logout" button in the work area, which will exit the application. You can also close the window using the 'X' button.

    *   **Incorrect Credentials**:
        *   If you enter any other username/password combination, an "Incorrect username or password. Please try again." message will appear. This corresponds to the `LoginErrato` use case.
        *   The password field will be cleared, and you can try again.

    *   **Empty Fields**:
        *   If you try to log in with an empty username or password, an immediate warning message ("Username/Password cannot be empty.") will appear.

    *   **Connection Interruption (Simulated ETOUR)**:
        *   There is a **20% chance** that any login attempt will simulate a connection error.
        *   If this occurs, an "Connection Error" message will pop up, stating "Connection to authentication server interrupted (ETOUR). Please check your connection and try again." This fulfills the exit condition: "Interruption of the connection to the server ETOUR .."
        *   You can then close the error message and retry the login.

5.  **Canceling the Operation**:
    *   Click the "Cancel" button on the login screen.
    *   A confirmation dialog will appear: "Are you sure you want to cancel login and exit the application?"
    *   If you click "Yes", the application will close. This fulfills the exit condition: "The registered user cancels the operation."
    *   If you click "No", you will return to the login screen.
    *   Closing the login window using the 'X' button on the title bar will trigger the same cancellation confirmation dialog.

Enjoy using the ChatDev Login Application!
```