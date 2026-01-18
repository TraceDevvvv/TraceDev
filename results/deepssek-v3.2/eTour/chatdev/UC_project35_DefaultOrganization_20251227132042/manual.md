# Login System - User Manual

## Overview

The Login System is a secure, standalone Java application that provides a complete authentication solution. It implements a user-friendly graphical interface for secure login with robust error handling and follows best pract for password security.

## Key Features

### 🔒 Security
- **Password Hashing**: Uses SHA-256 hashing for secure password storage
- **Input Validation**: Comprehensive validation for username and password formats
- **Secure Password Handling**: Clears password character arrays after use

### 🖥️ User Interface
- **Intuitive GUI**: Clean, organized login form with clear labels
- **Real-time Feedback**: Immediate feedback for user actions
- **Responsive Design**: Proper event handling and threading

### ⚙️ System Architecture
- **Singleton Pattern**: Single instance of LoginService for consistency
- **Custom Exceptions**: Detailed error categorization for better debugging
- **Modular Design**: Separated concerns with dedicated classes

## Installation and Setup

### Prerequisites

1. **Java Development Kit (JDK)**
   - Version 8 or higher
   - Download from [Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
   - or [OpenJDK](https://openjdk.java.net/install/)

2. **System Requirements**
   - Minimum RAM: 512 MB
   - Disk Space: 5 MB free
   - Operating System: Windows, macOS, or Linux

### Installation Steps

#### Option 1: Using Command Line (Recommended)

1. **Compile the Application**
   ```bash
   # Navigate to the directory containing all .java files
   cd /path/to/login-system/
   
   # Compile all Java files
   javac *.java
   ```

2. **Run the Application**
   ```bash
   # Start the login system
   java app
   ```

#### Option 2: Using IDE (Eclipse/IntelliJ IDEA)

1. **Create New Project**
   - Open your IDE
   - Create a new Java Project
   - Name it "LoginSystem"

2. **Import Source Files**
   - Copy all provided `.java` files into the `src` folder:
     - `app.java`
     - `LoginException.java`
     - `LoginErrorType.java`
     - `User.java`
     - `LoginService.java`
     - `PasswordHasher.java`

3. **Set Project Structure**
   - Ensure all files are in the same package (default package is fine)
   - Configure JDK to version 8 or higher

4. **Run Configuration**
   - Set `app` class as the main class
   - Run the application

### Verification

After successful compilation and launch, you should see:
1. A window titled "Login System"
2. A welcome message "Welcome to the Login System"
3. Username and password fields
4. Login and Cancel buttons

## Usage Guide

### First Time Setup

The system comes pre-loaded with three sample users:

| Username | Password | Status |
|----------|----------|--------|
| admin    | admin123 | Active |
| user1    | password1 | Active |
| john_doe | securepass | Active |

### Login Process

1. **Launch the Application**
   - Run the application using methods described above
   - The login window will appear

2. **Enter Credentials**
   - **Username Field**: Enter your registered username
     - Valid usernames: 3-20 characters, letters, numbers, underscores only
     - Example: "john_doe", "user123"
   
   - **Password Field**: Enter your password
     - Minimum 6 characters
     - Must contain at least one letter and one number
     - Example: "securepass123"

3. **Submit**
   - Click the **Login** button
   - Or press **Enter** while focused in the password field

4. **Successful Login**
   - You'll see "Login successful! Redirecting..."
   - After 1 second, you'll be redirected to the Work Area
   - The Work Area displays a welcome message with your username

### Alternative Actions

#### Cancel Operation
- Click the **Cancel** button to:
  - Clear all form fields
  - Reset any error messages
  - Return to initial state

#### Exit Application
- **Standard Exit**:
  - Click the "X" button on window corner
  - Go to File → Exit (if using system menu)
  
- **After Successful Login**:
  - Close the Work Area window using standard window controls

### Error Handling

The system provides specific feedback for different error scenarios:

#### Common Error Messages

1. **"Please fill in both username and password."**
   - **Cause**: One or both fields are empty
   - **Solution**: Enter both username and password

2. **"Username must be 3-20 characters long...**
   - **Cause**: Invalid username format
   - **Solution**: Use only letters, numbers, underscores (3-20 chars)

3. **"Password must be at least 6 characters...**
   - **Cause**: Password doesn't meet complexity requirements
   - **Solution**: Enter password with min 6 chars, at least 1 letter and 1 number

4. **"Invalid username or password."**
   - **Cause**: Incorrect credentials or user doesn't exist
   - **Solution**: Check username/password or contact administrator

5. **"Error: Connection to server interrupted (ETOUR)."**
   - **Cause**: Simulated server connection failure
   - **Solution**: Try again, system automatically retries

6. **"Account is locked."**
   - **Cause**: User account has been locked
   - **Solution**: Contact system administrator

### Format Requirements

#### Username Requirements
- Length: 3 to 20 characters
- Allowed characters: a-z, A-Z, 0-9, underscore (_)
- No spaces or special characters
- Case-sensitive

#### Password Requirements
- Minimum length: 6 characters
- Must contain at least:
  - One letter (a-z or A-Z)
  - One number (0-9)
- Optional special characters: @ $ ! % * # ? &
- Case-sensitive

## Technical Details

### File Structure
```
LoginSystem/
├── app.java              # Main application class (GUI + controller)
├── LoginService.java     # Authentication logic
├── LoginException.java   # Custom exception handling
├── LoginErrorType.java   # Error type enumeration
├── User.java            # User data model
└── PasswordHasher.java  # Password security utility
```

### Important Classes

#### `app.java` - Main Application
**Responsibilities**:
- GUI setup and management
- Event handling for user interactions
- Navigation between login and work area

**Key Methods**:
- `createAndShowGUI()`: Initializes the login interface
- `handleLogin()`: Processes login attempts
- `handleCancel()`: Clears form and resets state
- `showWorkArea()`: Displays post-login application

#### `LoginService.java` - Authentication Service
**Key Features**:
- Singleton pattern for single service instance
- Input validation with regex patterns
- Server connection simulation

**Security Functions**:
- Username/Password format validation
- Password hash verification
- Account lock status checking

#### `User.java` - User Data Model
**Attributes**:
- `username`: User's identifier
- `passwordHash`: Securely stored password (SHA-256)
- `isLocked`: Account status flag

#### `PasswordHasher.java` - Security Utility
**Purpose**: Converts plain text passwords to secure hashes
**Algorithm**: SHA-256
**Output**: Hexadecimal string representation

## Troubleshooting

### Common Issues

#### "Cannot find symbol" Error
**Symptom**: Compilation fails with symbol errors
**Solution**: 
1. Ensure all files are in the same directory
2. Compile all files together: `javac *.java`
3. Check for typos in class names

#### Application Won't Start
**Symptom**: `java app` command does nothing
**Solution**:
1. Verify Java installation: `java -version`
2. Check if class files were compiled
3. Ensure you're in the correct directory

#### GUI Doesn't Appear
**Symptom**: No window shows up
**Solution**:
1. Check Event Dispatch Thread usage
2. Verify no exceptions are being swallowed
3. Check console for error messages

#### Authentication Always Fails
**Symptom**: Correct credentials don't work
**Solution**:
1. Check password case sensitivity
2. Verify username meets format requirements
3. Ensure all files compiled correctly

### Error Codes and Meanings

| Error Type | Meaning | User Action |
|------------|---------|-------------|
| EMPTY_FIELDS | Missing credentials | Fill both fields |
| INVALID_INPUT_FORMAT | Bad username/password format | Follow format rules |
| INVALID_CREDENTIALS | Wrong username/password | Re-enter carefully |
| SERVER_CONNECTION_FAILED | Network/server issue | Try again later |
| ACCOUNT_LOCKED | Security restriction | Contact admin |

## Best Pract

### Security
1. **Never Share Passwords**: The system administrator cannot retrieve your password
2. **Use Strong Passwords**: Mix letters, numbers, and symbols
3. **Log Out Properly**: Close application when done
4. **Report Issues**: Notify administrator of any suspicious activity

### Usage
1. **Remember Credentials**: Keep username/password in a secure location
2. **Regular Updates**: Update password periodically
3. **Clear Workstation**: Always close application on shared computers
4. **Backup**: Keep copies of important work outside the system

## Support and Contact

### Getting Help
- **Administrative Support**: Contact your system administrator
- **Technical Issues**: Report to IT department
- **Feature Requests**: Submit through proper channels

### Documentation Updates
This manual is maintained by the product team. Updates are distributed with new software releases.

## Version Information
- **Current Version**: 1.0
- **Release Date**: October 2023
- **Compatibility**: Java 8+
- **License**: Proprietary - Custom Development

---

*Note: This is a demonstration application for educational purposes. For production use, additional security measures and database integration would be required.*