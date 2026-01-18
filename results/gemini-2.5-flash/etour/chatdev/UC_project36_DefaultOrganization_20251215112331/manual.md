```
# ChatDev Login System (LOGINERRATO Use Case)

This document provides a user manual for the ChatDev Login System, which is specifically designed to demonstrate the "LOGINERRATO" use case – handling incorrect login credentials.

## 🤔 What is this?

This application is a simple Java Swing-based GUI program that simulates a login process. Its core purpose, based on the `LOGINERRATO` use case, is to illustrate how a system responds when a user attempts to log in with invalid data.

**Main Functions:**

*   **Login Interface:** Provides fields for username and password, and a login button.
*   **Error Handling (LOGINERRATO):** When incorrect login credentials are provided, the system:
    1.  Notifies the user that the entered data is invalid via a clear error message.
    2.  Requires the user to acknowledge (confirm) this notification.
    3.  Recovers to its previous state (the login screen), allowing the user to re-attempt login with corrected information, typically by clearing the password field for security and convenience.
*   **Simulated Authentication:** Uses a hardcoded username ("user") and password ("pass") for demonstration purposes.

## 🛠️ Environment Dependencies

To compile and run this Java application, you will need:

*   **Java Development Kit (JDK):** Version 8 or higher is recommended. You can download it from Oracle or use an OpenJDK distribution (e.g., Adoptium OpenJDK).
    *   Verify your JDK installation by running `java -version` and `javac -version` in your terminal or command prompt.
*   **Operating System:** Any operating system that supports Java (Windows, macOS, Linux).
*   **Optional: Integrated Development Environment (IDE):** While not strictly required, an IDE like IntelliJ IDEA, Eclipse, or Visual Studio Code with Java extensions can simplify the process of compiling and running Java applications.

## 🚀 How to Use/Play It

Follow these steps to get the ChatDev Login System up and running and to experience the `LOGINERRATO` use case.

### Step 1: Save the Code Files

1.  Create a new directory (folder) on your computer, for example, `ChatDevLogin`.
2.  Inside this directory, create the following five Java files and copy the corresponding code into them:
    *   `Main.java`
    *   `LoginFrame.java`
    *   `LoginPanel.java`
    *   `LoginService.java`
    *   `ErrorDialog.java`

    Ensure the file names match exactly, including capitalization.

### Step 2: Compile the Code

Open your terminal or command prompt and navigate to the `ChatDevLogin` directory you created.

Then, compile all the Java files using the Java compiler (`javac`):

```bash
javac *.java
```

If there are no errors, this command will create `.class` files for each Java file (e.g., `Main.class`, `LoginFrame.class`, etc.) in the same directory.

### Step 3: Run the Application

After successful compilation, run the application using the Java Virtual Machine (`java`):

```bash
java Main
```

A login window titled "ChatDev Login System" will appear.

### Step 4: Testing the LOGINERRATO Use Case

The system's correct credentials for demonstration are:
*   **Username:** `user`
*   **Password:** `pass`

#### Scenario 1: Triggering LOGINERRATO (Incorrect Login)

1.  In the login window, enter an **incorrect** username or password (e.g., `wronguser` for username and `wrongpass` for password). You can also leave fields empty.
2.  Click the "Login" button.
3.  **Expected Outcome (Flow of Events):**
    *   An "Error" dialog box will appear with a message like "Incorrect username or password. Please try again." or "Username and password cannot be empty." (Step 1: Notice data is invalid, asks for confirmation).
    *   Click "OK" on the error dialog. (Step 2: Confirmation of reading notification).
    *   The error dialog will close, the password field on the login screen will be cleared, and focus will return to it, ready for a new attempt. The username field remains for convenience. (Step 3: Recovers previous state. Exit condition: System returns control to user interaction).

#### Scenario 2: Successful Login (for demonstration, not part of LOGINERRATO use case)

1.  In the login window, enter the **correct** username: `user`
2.  Enter the **correct** password: `pass`
3.  Click the "Login" button.
4.  **Expected Outcome:**
    *   A "Success" dialog box will appear with the message "Login Successful! (Not part of LOGINERRATO use case, but for demonstration)".
    *   Click "OK".
    *   The login fields will be cleared. In a real application, this would lead to a new main application screen, but for this demo, it simply resets.

You can repeat these steps to observe the `LOGINERRATO` behavior multiple times.
```