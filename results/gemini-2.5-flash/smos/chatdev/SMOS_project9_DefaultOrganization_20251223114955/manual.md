```markdown
# User Management System - Delete User Manual

## Introduction

This document serves as a comprehensive manual for the "User Management System - Delete User" application. This Java application is designed for administrators to manage user accounts efficiently by providing a graphical interface to view user details and delete specific users from the system. It simulates a common administrative task within a larger system, encapsulating the "DeleteUser" use case.

## Core Features

The application facilitates the following key functionalities:

1.  **User Listing**: Displays a tabular overview of all registered users in the system, including their ID, Name, and Email.
2.  **User Detail Viewing**: Upon selecting a user from the list, their detailed information is displayed, simulating the "viewdetTailsente" (view user details) use case.
3.  **User Deletion**: Allows an administrator to select a user and permanently delete their record from the system after a confirmation step.
4.  **SMOS Server Interaction Simulation**: Upon successful user deletion, the application simulates the interruption of a connection to an external "SMOS server" for the canceled user, reflecting real-world integration with external serv.

## Environment Setup

To compile and run this application, you will need a Java Development Kit (JDK) and the JavaFX SDK.

### 1. Java Development Kit (JDK) 17

The application is built using Java 17.

*   **Download & Install**:
    *   Visit the official Oracle JDK download page or a reputable OpenJDK distribution like Adoptium (formerly AdoptOpenJDK). Download and install **JDK 17** for your operating system.
    *   Follow the installation instructions specific to your operating system.
*   **Set Environment Variables (Optional but Recommended)**:
    *   **`JAVA_HOME`**: Set this environment variable to the root directory of your JDK 17 installation (e.g., `C:\Program Files\Java\jdk-17` on Windows or `/usr/lib/jvm/jdk-17` on Linux/macOS).
    *   **`Path`**: Add the `bin` directory of your JDK to your system's `Path` environment variable (e.g., `%JAVA_HOME%\bin` or `$JAVA_HOME/bin`).
*   **Verify Installation**:
    *   Open a command prompt or terminal and run:
        ```bash
        java -version
        javac -version
        ```
    *   Both commands should output `17` or a similar version string, confirming JDK 17 is installed and accessible.

### 2. JavaFX 17 SDK

This application uses JavaFX for its graphical user interface. Since JDK 9 and later, JavaFX is no longer included in the standard JDK distribution and must be added as a separate module.

*   **Download JavaFX SDK**:
    *   Go to the official Gluon JavaFX website: [https://gluonhq.com/products/javafx/](https://gluonhq.com/products/javafx/)
    *   Download the **JavaFX SDK** (e.g., `javafx-sdk-17.0.10`) for your operating system.
*   **Extract the SDK**:
    *   Extract the contents of the downloaded archive to a well-known location on your system.
    *   For example:
        *   **Windows**: `C:\javafx-sdk-17`
        *   **macOS/Linux**: `/opt/javafx-sdk-17` or `~/javafx-sdk-17`
    *   Let's refer to this path as `PATH_TO_FX` in the following instructions.

## How to Compile and Run the Application

This section provides instructions for compiling and running the application from the command line.

### 1. Obtain the Code

Ensure you have all the provided Java source files (`User.java`, `UserManager.java`, `DeleteUserApp.java`, `SMOSAdapter.java`, `MockSMOSAdapter.java`).

*   **Create Project Structure**:
    *   Create a root directory for your project, e.g., `C:\DeleteUserApp` (Windows) or `~/DeleteUserApp` (Linux/macOS).
    *   Inside this root directory, create the package structure: `com/chatdev/deleteuser`.
    *   Place all `.java` files within the `com/chatdev/deleteuser` directory.

    Your directory structure should look like this:
    ```
    DeleteUserApp/
    ├── com/
    │   └── chatdev/
    │       └── deleteuser/
    │           ├── User.java
    │           ├── UserManager.java
    │           ├── DeleteUserApp.java
    │           ├── SMOSAdapter.java
    │           └── MockSMOSAdapter.java
    ```

### 2. Compile the Java Files

1.  Open a terminal or command prompt.
2.  Navigate to your `DeleteUserApp` root directory:
    ```bash
    cd DeleteUserApp
    ```
3.  Set the `PATH_TO_FX` variable to your JavaFX SDK's `lib` directory.
    *   **Windows Example**:
        ```bash
        set PATH_TO_FX="C:\javafx-sdk-17\lib"
        ```
    *   **Linux/macOS Example**:
        ```bash
        export PATH_TO_FX="/opt/javafx-sdk-17/lib"
        ```
4.  Compile all the Java source files, specifying the JavaFX module path and required modules:
    *   **Windows Example**:
        ```bash
        javac --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.graphics com/chatdev/deleteuser/*.java
        ```
    *   **Linux/macOS Example**:
        ```bash
        javac --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.graphics com/chatdev/deleteuser/*.java
        ```
    If successful, `.class` files will be generated in the respective package directories.

### 3. Run the Application

1.  From the `DeleteUserApp` root directory in your terminal:
2.  Run the application, again specifying the JavaFX module path and required modules, and pointing to the main class:
    *   **Windows Example**:
        ```bash
        java --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.graphics -classpath . com.chatdev.deleteuser.DeleteUserApp
        ```
    *   **Linux/macOS Example**:
        ```bash
        java --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.graphics -classpath . com.chatdev.deleteuser.DeleteUserApp
        ```
    The application GUI window should now appear.

## Using the Application

Follow these steps to interact with the "User Management System - Delete User" application:

### 1. Launching the Application

*   After successfully executing the run command (as described above), a window titled "User Management System - Delete User" will appear.
*   You will see an "Administrator: Logged In" status, simulating the required precondition of being logged in as an administrator.

### 2. Viewing Users

*   The main area of the application displays a table titled "User ID", "Name", and "Email". This table is populated with a predefined list of users.
*   To view details of a specific user, **click on any row** in the user table.
*   The "User Details" section below the table will update to show the ID, Name, and Email of the selected user.
*   The "Delete Selected User" button will become enabled.

### 3. Deleting a User

*   **Preconditions**:
    *   You are running the application (logged in as an administrator).
    *   You have selected a user from the table, and their details are displayed.
    *   The "Delete Selected User" button is enabled.
*   **Steps to Delete**:
    1.  **Select a User**: Click on a user's row in the table (e.g., "U001 Alice Smith").
    2.  **Click "Delete"**: Click the "Delete Selected User" button.
    3.  **Confirm Deletion**: A confirmation dialog will appear, asking if you are sure you want to delete the user. This is an important step to prevent accidental deletions.
    4.  **Confirm (OK)**: Click "OK" to proceed with the deletion.
*   **Outcome**:
    *   If confirmed, an "Deletion Successful" alert will pop up, informing you that the user has been deleted.
    *   The selected user will be removed from the user table, and the table will automatically update to reflect the change (Postcondition: `Displays the list of updated users`).
    *   The "User Details" section will show "User deleted. Select another user."
    *   An additional alert titled "SMOS Connection Status" will appear, indicating that the "Connection to SMOS server for [Username] (ID: [UserID]) interrupted." (Postcondition: `Connection to the SMOS server interrupted`).
    *   The "Delete Selected User" button will become disabled until another user is selected.

### Edge Cases and Behavior

*   **No User Selected**: If you try to click the "Delete Selected User" button when no user is selected, a "No User Selected" warning alert will appear.
*   **Cancel Deletion**: If you click "Cancel" in the confirmation dialog, the user will not be deleted, and the table will remain unchanged.

## Troubleshooting

*   **"Error: Could not find or load main class com.chatdev.deleteuser.DeleteUserApp"**:
    *   Ensure your `com` directory is directly under the `DeleteUserApp` directory.
    *   Verify the class name `DeleteUserApp` is spelled correctly and matches the file name.
    *   Check your `classpath` (`-classpath .`) to ensure the JVM can find your compiled classes.
*   **"Error: JavaFX runtime components are missing" or similar JavaFX errors**:
    *   Ensure you have correctly set the `--module-path` to the `lib` directory of your JavaFX SDK.
    *   Verify that `PATH_TO_FX` contains the correct path to your JavaFX SDK.
    *   Make sure you include `--add-modules javafx.controls,javafx.graphics` when running the application.
*   **Application window does not appear/hangs**:
    *   Check your terminal for any error messages that might give clues.
    *   Ensure your JDK and JavaFX versions are compatible (both version 17).
*   **`javac` shows "package javafx.application does not exist"**:
    *   This typically means `javac` cannot find the JavaFX modules. Double-check your `--module-path` and `--add-modules` in the `javac` command.

```