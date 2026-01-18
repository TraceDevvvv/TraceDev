```
# ELIMINABANNER - Restaurant Banner Management System

This document serves as a user manual for the ELIMINABANNER system, an application designed for Point Of Restaurant Operators to manage and delete banner advertisements.

## 🤔 What is ELIMINABANNER?

ELIMINABANNER is a simple, standalone Java Swing application that provides a graphical interface for restaurant operators to remove banner ads associated with their establishment. It simulates a backend system that handles banner data, operator authentication, and the deletion process, including potential server connection interruptions.

## 🚀 Main Functions

The application facilitates the following core functionalities:

*   **Operator Authentication**: Allows a Point Of Restaurant Operator to log in to the system with their credentials. This is an essential entry condition for accessing banner management features.
*   **View Banners**: Upon successful authentication, the operator can view a comprehensive list of all banner advertisements currently associated with their restaurant.
*   **Select Banner for Deletion**: Operators can select a specific banner from the displayed list that they wish to remove.
*   **Confirm Deletion**: Before final removal, the system prompts the operator for a confirmation, ensuring accidental deletions are minimized.
*   **Execute Banner Removal**: Once confirmed, the system processes the request to delete the selected banner.
*   **System Notifications**: The application provides clear feedback on the outcome of the deletion attempt, including:
    *   Successful elimination of the banner.
    *   Notification if the operator cancels the operation.
    *   Warning if an interruption of the connection to the server occurs (simulated).
*   **Logout**: Provides an option for the operator to log out of the system.

## 🛠️ How to Install Environment Dependencies

This application is written in Java and uses standard Swing libraries, which are part of the Java Development Kit (JDK).

1.  **Install Java Development Kit (JDK)**:
    *   You need to have a JDK installed on your system. A recommended version is **Java 8 or higher**.
    *   You can download the latest JDK from Oracle's official website or use an open-source distribution like OpenJDK.
    *   Follow the installation instructions for your operating system.
    *   Verify your installation by opening a command prompt or terminal and typing:
        ```bash
        java -version
        javac -version
        ```
        You should see output indicating the installed Java version.

2.  **No external libraries are required** as this application uses only built-in Java functionalities and Swing components.

## ▶️ How to Use/Play it

Follow these steps to compile and run the ELIMINABANNER application:

1.  **Save the Code Files**:
    *   Create a dedicated directory for the project (e.g., `eliminabanner_app`).
    *   Save each of the provided Java code snippets (`Banner.java`, `RestaurantOperatorSystem.java`, `EliminaBannerGUI.java`, `Main.java`) into this directory.

2.  **Compile the Application**:
    *   Open a command prompt or terminal.
    *   Navigate to the directory where you saved the Java files.
    *   Compile the Java source files using the `javac` command:
        ```bash
        javac Banner.java RestaurantOperatorSystem.java EliminaBannerGUI.java Main.java
        ```
        If there are no errors, this command will create `.class` files for each `.java` file in the same directory.

3.  **Run the Application**:
    *   From the same command prompt or terminal, run the application using the `java` command, specifying the `Main` class:
        ```bash
        java Main
        ```
    *   A GUI window titled "EliminaBanner - Restaurant Operator System" will appear.

4.  **Log in as Operator**:
    *   The application will first present a login screen.
    *   **Username**: `operator` (pre-filled for convenience)
    *   **Password**: `pass` (pre-filled for convenience)
    *   Click the "Login" button.
    *   A confirmation message "Authentication successful!" will be displayed, and the interface will transition to the main banner management screen.

5.  **Manage Banners**:
    *   **View List**: You will see a list of available banners (e.g., "B001: Summer Special Ad", "B002: New Dessert Menu", etc.)
    *   **Select a Banner**: Click on any banner in the list to select it. The "Remove Selected Banner" button will become active.
    *   **Remove a Banner**:
        *   With a banner selected, click the "Remove Selected Banner" button.
        *   A confirmation dialog will appear, asking "Are you sure you want to remove the banner: '...'?".
        *   **To Confirm**: Click "Yes". The system will attempt to delete the banner.
            *   If successful, a "Deletion Successful" message will appear, and the banner will be removed from the list.
            *   **Simulated Connection Error**: There's an approximate 10% chance that the deletion might fail due to a simulated "Server connection interrupted" error. If this happens, try again.
        *   **To Cancel**: Click "No". A message "Banner removal operation cancelled." will appear, and the banner will remain in the list.
    *   **Logout**: Click the "Logout" button to return to the login screen. Note that the password field will be cleared for security.

Enjoy managing your restaurant's banner ads!
```