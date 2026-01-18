```markdown
# Login/Logout System - User Manual

## Overview

This software implements a complete login/logout system with a graphical user interface (GUI) built using Java Swing. The application allows registered users to securely log in and log out, demonstrating the full logout use case as specified in the requirements.

## Main Features

- **User Authentication**: Secure login with username and password validation
- **Multiple User Support**: Pre-registered users (admin, alice, bob, charlie) with unique passwords
- **Intuitive GUI**: Clean, user-friendly interface with clear visual feedback
- **Session Management**: Proper session handling with current user tracking
- **Logout Confirmation**: Interactive logout confirmation dialog
- **Keyboard Support**: Enter key for login, Alt+L shortcut for logout
- **Focus Management**: Automatic focus setting for better user experience
- **Error Handling**: Comprehensive validation and error messages
- **Responsive Design**: Proper layout and visual feedback for all operations

## System Requirements

### Software Requirements
- Java Development Kit (JDK) 8 or higher
- Any operating system that supports Java SE (Windows, macOS, Linux)
- No external libraries required - uses standard Java SE libraries

### Hardware Requirements
- Minimum 512MB RAM
- 100MB free disk space
- Any modern processor
- Display with minimum 800x600 resolution

## Installation

### Option 1: Using Command Line
1. **Verify Java Installation**:
   ```bash
   java -version
   javac -version
   ```

2. **Create Project Directory**:
   ```bash
   mkdir LogoutSystem
   cd LogoutSystem
   ```

3. **Create Java File**:
   Copy the provided `main.java` code into a file named `Main.java` (note the capital M):
   ```bash
   # Create Main.java file with the provided code
   ```

4. **Compile the Program**:
   ```bash
   javac Main.java
   ```
5. **Run the Application**:
   ```bash
   java Main
   ```

### Option 2: Using IDE (Eclipse/IntelliJ IDEA)
1. **Create New Java Project**
2. **Create a new class named `Main`**
3. **Copy the provided code into the Main.java file**
4. **Run the application using IDE's run button**

### Option 3: Using Online Java Compiler
For quick testing without local installation:
1. Visit any online Java compiler (like JDoodle, OnlineGDB)
2. Copy and paste the provided code
3. Execute to see the application in action

## User Guide

### Starting the Application
When you launch the application, you'll see the main login window titled "Login/Logout System".

### Login Process
1. **Enter Credentials**:
   - Username: Enter one of the registered usernames
     - admin (password: password123)
     - alice (password: alice123)
     - bob (password: bob456)
     - charlie (password: charlie789)
   - Password: Enter the corresponding password

2. **Login Methods**:
   - Click the "Login" button with mouse
   - Press Enter key after typing in either field

3. **Successful Login**:
   - You'll be redirected to the dashboard
   - Welcome message displays: "Welcome [username]! You are logged in."

4. **Login Errors**:
   - Empty fields: "Username and password cannot be empty."
   - Invalid username: "Username not found. Please use a registered username."
   - Invalid password: "Invalid password for user: [username]"

### Dashboard Features
- **Welcome Message**: Displays current logged-in user
- **Logout Button**: Located at the bottom center
  - Click "Logout" button
  - Press Alt+L keyboard shortcut

### Logout Process
1. **Initiate Logout**:
   - Click the "Logout" button on dashboard
   - Press Alt+L keyboard shortcut

2. **Confirmation**:
   - A dialog appears asking: "Are you sure you want to logout?"
   - Options: Yes / No
   - Select Yes to proceed

3. **Successful Logout**:
   - Success message: "Goodbye [username]! You have been logged out successfully."
   - Returns to login form
   - Focus automatically set to username field
   - Welcome message cleared

4. **Cancel Logout**:
   - Select No in confirmation dialog
   - Stay on dashboard without logging out

### Navigation Tips
- **Tab Navigation**: Use Tab key to navigate between fields
- **Field Focus**: Username field gets focus on application start and after logout
- **Status Messages**: Red text for errors, blue text for information
- **Tooltips**: Hover over fields to see hints

## Troubleshooting

### Common Issues and Solutions

1. **"Could not find or load main class"**:
   - Ensure file is named `Main.java` (with capital M)
   - Compile with `javac Main.java`
   - Run with `java Main`

2. **GUI doesn't appear**:
   - Check Java installation with `java -version`
   - Ensure you're using JDK 8 or higher
   - Try running from command line first

3. **Login not working**:
   - Verify using correct username/password combinations
   - Check for typos (passwords are case-sensitive)
   - Usernames: admin, alice, bob, charlie

4. **Application freezes**:
   - Close and restart the application
   - Check system resources
   - Ensure no other Java processes are interfering

### Error Messages Guide
- **Red status text**: Login-related errors
- **Blue info text**: Help information about registered users
- **Dialog messages**: Confirmation and success notifications

## Security Notes
- Passwords are stored in memory only during runtime
- No persistent storage of credentials
- Application is for demonstration purposes
- Do not use for production systems without proper security measures

## Code Structure
The application follows object-oriented principles:
- Single Main class with GUI components
- CardLayout for panel switching
- Separate methods for login/logout handling
- Clear separation of concerns between UI and logic

## Extending the Application
To add more users, modify the `registeredUsers` HashMap in the Main constructor:
```java
registeredUsers.put("newuser", "newpassword");
```

## Support
For issues with this software:
1. Verify Java installation meets requirements
2. Check user credentials are correct
3. Ensure proper compilation steps
4. Review the code comments for understanding functionality

## License
This is a demonstration application for educational purposes. Free to use and modify.

---

**Happy Testing!** The logout use case is fully implemented and ready for demonstration.
```