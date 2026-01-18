This file provides instructions on how to run the AcceptEnrollmentStudent application.
It also explains the design and key features.
ACCEPT ENROLLMENT STUDENT - ADMINISTRATOR APPLICATION
======================================================
Description:
------------
This is a Java Swing application that simulates the "AcceptEnrollmentStudent" use case.
An administrator can view pending student registration requests and accept them by
clicking an "Accept" button. The application updates the list of pending requests
after each acceptance.
The application is a simulation and uses an in-memory list of registration requests.
In a real system, it would connect to a database server (like SMOS).
How to Run:
-----------
1. Ensure you have Java JDK 8 or later installed.
2. Compile all the Java files:
   javac Main.java EnrollmentGUI.java RegistrationManager.java RegistrationRequest.java
3. Run the Main class:
   java Main
Alternatively, you can use an IDE (like Eclipse, IntelliJ IDEA, or NetBeans) to import
the project and run the Main class.
Features:
---------
- Graphical user interface (GUI) built with Java Swing.
- Displays a table of pending registration requests with columns: Student ID, Name, Email, Status.
- Allows selection of a request and acceptance via an "Accept" button.
- Confirmation dialog before accepting.
- Success and error messages.
- Simulates server connection interruptions (randomly, 10% chance) to demonstrate error handling.
- Fully runnable with no external dependencies (pure Java).
Architecture:
-------------
1. Main.java - Entry point. Launches the GUI.
2. EnrollmentGUI.java - Main GUI window. Handles user interaction.
3. RegistrationManager.java - Manages the list of registration requests and business logic.
4. RegistrationRequest.java - Data model representing a single registration request.
Edge Cases Handled:
-------------------
- No selection when clicking "Accept" -> shows a warning.
- Student ID not found (though unlikely in this simulation) -> throws an error.
- Simulated SMOS server interruption -> shows an error message.
- Empty list of pending requests -> updates status label accordingly.
Note:
-----
This application is for demonstration purposes only. In a production environment, you would:
- Replace the in-memory list with a database connection.
- Implement proper authentication and authorization for the administrator.
- Handle network connectivity more robustly.
- Add logging, unit tests, and more comprehensive error handling.
Author: ChatDev Software Company
Version: 1.0
Date: October 2023