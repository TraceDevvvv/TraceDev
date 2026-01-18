# InsertDelayAdmin Application
## Overview
This Java application implements the "InsertDelayAdmin" use case for administrators to register student delay information. The application includes a secure login system that requires administrator authentication before accessing the main functionality. It features a GUI built with Swing and follows the sequence of events specified in the use case, including date selection, form entry, server communication, and logging.
## Security Features
- **Administrator Authentication**: Only users with valid administrator credentials can access the application
- **Secure Login Dialog**: Username and password authentication before main application launch
- **Role-based Access**: Application verifies user has administrator privileges
## Requirements
- Java Development Kit (JDK) 8 or higher
- Any Java IDE or command-line compiler
## Default Administrator Credentials
For demonstration purposes:
- **Username**: admin
- **Password**: admin123
**Note**: In a production environment, these credentials should be stored securely in a database or integrated with an existing authentication system.
## How to Run
### Option 1: Using Command Line
1. Make sure you have JDK installed and `javac` and `java` are in your PATH
2. Compile all Java files: