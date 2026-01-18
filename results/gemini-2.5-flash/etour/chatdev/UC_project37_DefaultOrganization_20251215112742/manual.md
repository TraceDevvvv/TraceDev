# ChatDev Logout Application

A simple demonstration of a user session logout functionality with a graphical user interface.

## 🤔 What is this?

This application serves as a minimal, runnable example demonstrating a basic user session management, specifically focusing on the logout process, within a Java Swing application context. It's built following a Model-View-Controller (MVC) pattern to separate concerns and ensure maintainability.

**Main functionalities include:**

*   **User Simulation:** The `User` class models a user, while `UserStore` simulates a simple in-memory database for user registration and retrieval. For demonstration purposes, it pre-populates with a `testuser` (`testuser/password123`) and an `admin` user (`admin/adminpass`).
*   **Session Management:** The `SessionManager` class handles the core logic for user login and logout. It tracks the currently logged-in user and provides methods to check the login status. The `LoginResult` enum provides clear outcomes for login attempts.
*   **Logout Workflow:** The `LogoutView` provides a simple graphical interface for the user, displaying the current login status and a "Logout" button. The `LogoutController` orchestrates the logout process, handling user interaction, asking for confirmation, and invoking the `SessionManager` to perform the actual logout.
*   **MVC Architecture:** The code showcases a clear separation between data models (`User`), data access (`UserStore`), session logic (`SessionManager`), presentation (`LogoutView`), and control logic (`LogoutController`), all brought together by the main `Application` class.

Upon launching, the `Application` class automatically simulates a successful login for `testuser` to satisfy the use case's entry condition ("A registered user has previously made a successful Login."). It then displays a simple Java Swing window that allows the user to initiate the logout process.

## 💻 Environment Dependencies

To compile and run this Java application, you will need:

*   **Java Development Kit (JDK) 8 or higher**: Ensure you have a JDK installed and configured in your system's `PATH` environment variable. You can download it from official sources like Oracle or Adoptium.
    *   To verify your Java installation, open a terminal or command prompt and type:
        ```bash
        java -version
        javac -version
        ```
    *   Both commands should display version information.

## 🚀 How to Use/Play it

Follow these steps to set up, compile, and run the ChatDev Logout Application:

### 1. Project Setup

1.  **Create a Project Directory**: Create a new directory for your project, for example, `ChatDevLogoutApp`.
2.  **Create Package Structure**: Inside `ChatDevLogoutApp`, create the necessary subdirectories to match the Java package structure:
    `com/chatdev/model`
    `com/chatdev/store`
    `com/chatdev/session`
    `com/chatdev/gui`
    `com/chatdev/controller`
    And a `com/chatdev` directory for the main application class.
3.  **Place Source Files**: Save each provided `.java` file into its corresponding directory based on its `package` declaration:
    *   `user.java` → `ChatDevLogoutApp/com/chatdev/model/User.java`
    *   `userstore.java` → `ChatDevLogoutApp/com/chatdev/store/UserStore.java`
    *   `loginresult.java` → `ChatDevLogoutApp/com/chatdev/session/LoginResult.java`
    *   `sessionmanager.java` → `ChatDevLogoutApp/com/chatdev/session/SessionManager.java`
    *   `logoutview.java` → `ChatDevLogoutApp/com/chatdev/gui/LogoutView.java`
    *   `logoutcontroller.java` → `ChatDevLogoutApp/com/chatdev/controller/LogoutController.java`
    *   `application.java` → `ChatDevLogoutApp/com/chatdev/Application.java`

Your final directory structure should resemble this:

```
ChatDevLogoutApp/
├── com/
    └── chatdev/
        ├── controller/
        │   └── LogoutController.java
        ├── gui/
        │   └── LogoutView.java
        ├── model/
        │   └── User.java
        ├── session/
        │   ├── LoginResult.java
        │   └── SessionManager.java
        ├── store/
        │   └── UserStore.java
        └── Application.java
```

### 2. Compile the Application

1.  **Open Terminal/Command Prompt**: Navigate to the `ChatDevLogoutApp` directory (the root of your project) in your terminal or command prompt.
2.  **Create Output Directory**: Create a directory where the compiled `.class` files will be stored:
    ```bash
    mkdir out
    ```
3.  **Compile Source Files**: Compile all the Java source files. The `-d out` flag tells the `javac` compiler to place the compiled `.class` files into the `out` directory, maintaining the package structure.
    ```bash
    javac -d out com/chatdev/model/User.java \
                  com/chatdev/store/UserStore.java \
                  com/chatdev/session/LoginResult.java \
                  com/chatdev/session/SessionManager.java \
                  com/chatdev/gui/LogoutView.java \
                  com/chatdev/controller/LogoutController.java \
                  com/chatdev/Application.java
    ```
    *   *(Note: For Windows, you might need to use `^` instead of `\` for line continuation)*.
    *   If compilation is successful, you will not see any output, and the `out` directory will now contain the compiled `.class` files mirroring the `com/chatdev/...` package structure.

### 3. Run the Application

1.  **Execute Main Class**: From the `ChatDevLogoutApp` directory, run the main `Application` class using the `java` command. The `-cp out` flag adds your `out` directory to the classpath, allowing the Java Virtual Machine (JVM) to find the compiled classes.
    ```bash
    java -cp out com.chatdev.Application
    ```

### 4. Interact with the Application

Upon running the command, a small Java Swing window titled "ChatDev Logout Application" will appear on your screen.

1.  **Initial State**: The application automatically performs a simulated login using `testuser` and `password123`.
    *   The `statusLabel` in the window will display: "Status: Logged in as testuser".
    *   The "Logout" button will be enabled, indicating a user is currently logged in and can perform the logout action.
2.  **Initiate Logout**: Click the "Logout" button.
3.  **Confirmation Dialog**: A confirmation dialog titled "Confirm Logout" will appear, asking: "Are you sure you want to log out?".
    *   **To Confirm Logout**: Click the "Yes" button.
    *   **To Cancel Logout**: Click the "No" button or close the dialog.
4.  **Observe Results**:
    *   **If "Yes" was clicked**: An "Information" dialog titled "Logout" will appear, stating "Logout Successful.". Click "OK". The main window's `statusLabel` will update to "Status: Not Logged In", and the "Logout" button will become disabled because no user is currently logged in.
    *   **If "No" was clicked**: An "Information" dialog titled "Logout" will appear, stating "Logout cancelled.". Click "OK". The main window's `statusLabel` will remain "Status: Logged in as testuser", and the "Logout" button will stay enabled.
5.  **Exit**: Close the application window when you are finished.