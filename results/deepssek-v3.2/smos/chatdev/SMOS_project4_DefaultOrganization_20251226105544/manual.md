# Change Password Application User Manual

## Overview

The Change Password Application is a Java-based desktop application that provides a secure and user-friendly interface for registered users to change their passwords. The application follows a multi-tier architecture with proper validation, error handling, and simulated server connectivity.

## Main Features

### 1. **User Authentication Simulation**
- Simulated user database with secure password storage
- Session management for authenticated users
- Password validation and verification

### 2. **Password Change Functionality**
- Secure password change process with multiple validations:
  - Old password verification
  - New password confirmation matching
  - Password strength requirements (minimum 8 characters, at least one letter and one number)
  - Prevention of using the same old and new password

### 3. **User Interface**
- Main application window with menu navigation
- Dedicated change password form with intuitive layout
- Real-time validation feedback
- Status indicators for user actions

### 4. **Error Handling**
- Connection error simulation (10% chance for demonstration)
- Form validation errors
- Server connectivity exceptions
- User-friendly error messages

### 5. **Security Features**
- Password fields with masked input
- Secure password comparison
- Session-based authentication
- Simulated server communication

## System Requirements

### Hardware Requirements
- Minimum 2GB RAM
- 100MB free disk space
- Display resolution: 1024x768 or higher

### Software Requirements
- **Java Development Kit (JDK) 11 or higher**
- Operating System:
  - Windows 7/8/10/11
  - macOS 10.12 or higher
  - Linux with GUI support (Ubuntu, Fedora, etc.)

## Installation Instructions

### Step 1: Install Java Development Kit (JDK)

#### For Windows:
1. Download JDK 11+ from [Oracle's official website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
2. Run the installer and follow the instructions
3. Set JAVA_HOME environment variable:
   - Open System Properties → Advanced → Environment Variables
   - Add new variable: `JAVA_HOME` with value like `C:\Program Files\Java\jdk-11.0.x`
   - Add to PATH: `%JAVA_HOME%\bin`

#### For macOS:
```bash
# Using Homebrew
brew install openjdk@11
```

#### For Linux (Ubuntu/Debian):
```bash
sudo apt update
sudo apt install openjdk-11-jdk
```

### Step 2: Verify Java Installation
Open terminal/command prompt and run:
```bash
java -version
javac -version
```
You should see Java version 11 or higher.

### Step 3: Download Application Files
Download all Java source files to a directory:
- `ChangePasswordUI.java`
- `MainApplication.java`
- `UserSession.java`
- `ServerConnectionException.java`

## How to Compile and Run

### Compilation

1. Open terminal/command prompt
2. Navigate to the directory containing the Java files
3. Compile all Java files:
```bash
javac *.java
```

This will create `.class` files for each Java source file.

### Running the Application

Run the main application:
```bash
java MainApplication
```

Alternatively, you can run the change password form directly for testing:
```bash
java ChangePasswordUI
```

## Using the Application

### Starting the Application

1. The application starts with a main window titled "Main Application - Welcome testUser"
2. The current user is "testUser" with initial password "oldPass123"

### Main Application Window

The main window includes:
- **Menu Bar**: File menu with options
- **Welcome Message**: Displayed in the center
- **Change Password Button**: Large button in the center
- **Status Label**: Shows current application status at the bottom

#### Menu Options:
- **File → Change Password**: Opens the change password form
- **File → Exit**: Closes the application

### Changing Your Password

1. Click "Change Password" button or use the File menu

2. The Change Password form opens with three fields:
   - **Old Password**: Enter your current password
   - **New Password**: Enter your desired new password
   - **Confirm New Password**: Re-enter the new password

3. Click "Submit" after filling all fields

#### Password Requirements:
- Minimum 8 characters
- At least one letter (a-z or A-Z)
- At least one number (0-9)
- Cannot be the same as old password
- New password and confirmation must match

#### Successful Password Change:
- Confirmation message: "Password changed successfully"
- Form closes automatically
- Password is updated in the system

#### Failed Password Change Scenarios:
- **Empty fields**: "All fields are required"
- **Password mismatch**: "New password and confirmation do not match"
- **Same password**: "New password must be different from old password"
- **Weak password**: "New password must be at least 8 characters long..."
- **Wrong old password**: "Old password is incorrect"
- **Connection error**: "Connection error: Unable to connect to SMOS server"

### Testing Features

#### Connection Error Simulation
The application simulates server connectivity with a 10% chance of failure. If you encounter a connection error, simply try the password change again.

#### Demo Credentials
- Username: `testUser`
- Initial Password: `oldPass123`

## Troubleshooting

### Common Issues and Solutions

1. **"javac" is not recognized**
   - Ensure Java JDK is installed, not just JRE
   - Check JAVA_HOME and PATH environment variables

2. **Class Not Found Error**
   - Make sure all `.class` files are in the same directory
   - Recompile all Java files: `javac *.java`

3. **GUI Doesn't Appear**
   - Ensure your system has a GUI environment
   - For servers, install X11 forwarding or use a desktop environment

4. **Application Runs But Buttons Don't Work**
   - Check console for exception messages
   - Ensure all files are compiled with the same Java version

### Debug Information

The application provides console output for debugging:
- Current password for testing
- Password change success/failure messages
- Connection error details

## Application Architecture

### Classes Overview

1. **`MainApplication.java`**
   - Main application window
   - Entry point for the application
   - Menu and button handlers

2. **`ChangePasswordUI.java`**
   - Password change form
   - Input validation and processing
   - User interface components

3. **`UserSession.java`**
   - User authentication and session management
   - Password change logic
   - Simulated user database

4. **`ServerConnectionException.java`**
   - Custom exception for connection errors
   - Extends standard Java Exception class

### Data Flow
1. User initiates password change from MainApplication
2. ChangePasswordUI opens and collects credentials
3. Validations check input requirements
4. UserSession verifies old password and updates new password
5. Simulated server connectivity check
6. Success/failure feedback to user

## Security Notes

### Important Security Considerations

1. **This is a demonstration application**
   - Not suitable for production use
   - Uses simulated authentication
   - Does not connect to real servers

2. **Password Handling**
   - Passwords are stored in memory for demonstration only
   - In production, use secure password hashing algorithms
   - Never store plain text passwords

3. **Network Security**
   - Real applications should use HTTPS/TLS
   - Implement proper authentication tokens
   - Add rate limiting and brute force protection

## Testing Scenarios

Try these test cases:

### Valid Password Change
- Old Password: `oldPass123`
- New Password: `newPassword123`
  - ✅ 8+ characters
  - ✅ Contains letters
  - ✅ Contains numbers
- Confirm: `newPassword123`
  - ✅ Matches new password

### Invalid Scenarios
- **Same password**: `oldPass123` → `oldPass123`
- **Weak password**: `oldPass123` → `weak`
- **Mismatch**: New: `password123`, Confirm: `password124`
- **Wrong old password**: `wrongpass` → `newPassword123`

## Support and Feedback

For issues or questions:
1. Check console output for error messages
2. Verify Java installation and version
3. Ensure all files are in the same directory
4. Recompile if changes are made to source files

## Future Enhancements

Potential improvements for production use:
1. Database integration with SQL/NoSQL
2. Real server connectivity with REST API
3. Password encryption and hashing
4. Two-factor authentication
5. Password policy configuration
6. Password history tracking
7. Account lockout after failed attempts

## License and Usage

This software is provided for educational and demonstration purposes. Users are responsible for implementing proper security measures in production environments.

---

**Note**: This application simulates password change functionality for demonstration purposes. Always implement proper security measures, including password hashing, secure network communication, and compliance with security regulations when developing production applications.