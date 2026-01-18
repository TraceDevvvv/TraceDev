```markdown
# User Management System - Administrator Manual

## Overview
The User Management System is a Java-based desktop application designed for administrators to manage user accounts efficiently. It provides a graphical interface for viewing existing users and adding new users with comprehensive validation.

## Main Features
1. **Administrator Authentication** - Simulated login verification
2. **User List Display** - View all current users in the system
3. **New User Creation** - Add new users with full validation
4. **Data Validation** - Comprehensive input validation including:
   - Required field checking
   - Email format validation
   - Phone number validation
   - Password strength requirements
   - Unique login verification
5. **Error Handling** - Robust error management with server connection simulation

## System Requirements
- **Java Development Kit (JDK)**: Version 8 or higher
- **Operating System**: Windows, macOS, or Linux
- **Memory**: Minimum 512MB RAM
- **Storage**: 50MB free space

## Installation

### Step 1: Install Java
1. **Windows/Mac**:
   - Download JDK from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html)
   - Follow the installation wizard
   - Set JAVA_HOME environment variable

2. **Linux**:
   ```bash
   sudo apt update
   sudo apt install openjdk-11-jdk
   ```

### Step 2: Verify Java Installation
```bash
java -version
javac -version
```
Both commands should display Java version information.

### Step 3: Download and Compile the Application

#### Option A: Using Command Line
1. **Save the Java files**:
   - Create a directory for the application
   - Save `Main.java` and `User.java` (if separate) in this directory

2. **Compile**:
   ```bash
   cd /path/to/application
   javac Main.java User.java
   ```
   This creates `Main.class` and `User.class` files

#### Option B: Using an IDE
1. **Install an IDE** (recommended for beginners):
   - Download IntelliJ IDEA Community Edition or Eclipse
   
2. **Create a new Java project**
   
3. **Copy the provided code** into your project
   
4. **Build the project**

## Running the Application

### Command Line Method
1. Navigate to the compiled files directory
2. Execute:
   ```bash
   java Main
   ```

### IDE Method
1. Open the project in your IDE
2. Locate the `Main` class
3. Click the "Run" button (usually a green play button)

## Usage Instructions

### Starting the Application
1. Launch the application using one of the methods above
2. The system automatically checks administrative privileges
   - If not an administrator, you'll see an error message and the application closes
   - Otherwise, proceed to the main interface

### Main Interface
The main window displays:
- **Title**: "User Management System - Administrator"
- **User List**: Shows existing users in the system
- **New User Button**: Bottom-center button to add new users

### Adding a New User

#### Step 1: Open New User Form
Click the "New User" button to open the data entry form.

#### Step 2: Fill in User Details
Complete all required fields:

1. **Name**: User's first name (text only)
2. **Surname**: User's last name (text only)
3. **E-mail**: Valid email address (format: name@domain.com)
4. **Cell**: Phone number (minimum 10 digits, numbers only)
5. **Login**: Unique username for the system
6. **Password**: Secure password meeting requirements
7. **Confirm Password**: Re-enter password for verification

#### Step 3: Save the User
Click "Save" to submit the form.

### Validation Requirements

#### Email Format
- Must contain "@" symbol
- Must have valid domain (example.com)
- Example: `user@example.com`

#### Cell Phone Number
- Minimum 10 digits
- Numbers only
- No spaces or special characters
- Example: `1234567890`

#### Password Requirements
- Minimum 8 characters
- Must contain both letters and numbers
- Case-sensitive
- Must match confirmation field
- Example: `Password123`

#### Login Requirements
- Must be unique (not used by other users)
-.Cannot be empty

### Error Handling

#### Common Errors and Solutions
1. **"All fields are required"**
   - Check that no field is left empty

2. **"Invalid email format"**
   - Ensure email follows `name@domain.com` format

3. **"Cell number must contain at least 10 digits"**
   - Enter only numbers (no dashes or spaces)
   - Ensure at least 10 digits

4. **"Passwords do not match"**
   - Re-type password carefully in both fields

5. **"Password must contain both letters and numbers"**
   - Include at least one letter and one number

6. **"Login already exists"**
   - Choose a different login name

7. **"SMOS server connection interrupted"**
   - This occurs after multiple validation errors
   - Restart the application

#### Data Error Protocol
When invalid data is entered:
1. Error message appears explaining the issue
2. Correct the highlighted field
3. Server connection simulates interruption after repeated errors
4. Application may need restarting

### Exiting the Application
- Click the close (X) button on the main window
- Press `Alt+F4` (Windows/Linux) or `⌘+Q` (Mac)
- The application saves changes automatically

## Troubleshooting

### Common Issues

1. **"Error: Could not find or load main class Main"**
   ```
   Solution: Ensure you're in the correct directory and files are compiled
   ```

2. **Application doesn't start**
   ```
   Solution: Check Java installation with 'java -version'
   ```

3. **GUI elements appear distorted**
   ```
   Solution: Check screen resolution and Java version compatibility
   ```

4. **Cannot add new users**
   ```
   Solution: Ensure all fields meet validation requirements
   ```

### Advanced Configuration

To modify the application:

1. **Change default users**:
   Edit the `listModel.addElement()` lines in `createAndShowGUI()` method

2. **Adjust validation rules**:
   Modify the `validateUserData()` method parameters

3. **Change server simulation**:
   Adjust the `isConnected` variable behavior

## Security Notes

### Simulated Features
⚠️ **IMPORTANT**: This is a demonstration application

1. **Authentication is simulated** - Real applications require proper authentication
2. **Data is stored in memory** - Changes are lost when application closes
3. **Server connection is simulated** - No actual network operations occur

### For Production Use
If deploying in production:
1. Implement proper database persistence
2. Add real authentication system
3. Include network connectivity
4. Add data encryption
5. Implement logging and auditing

## Support

### Getting Help
For issues with:
- **Installation**: Check Java documentation
- **Usage**: Review this manual
- **Code**: Consult Java Swing documentation

### Reporting Bugs
Log issues with:
1. Java version
2. Operating system
3. Steps to reproduce
4. Error messages

## License and Acknowledgments
This application is for educational and demonstration purposes.

### Built With
- **Java Swing** - GUI framework
- **Standard Java Libraries** - Core functionality
- **Regular Expressions** - Input validation

### Version Information
- **Current Version**: 1.0
- **Last Updated**: [Current Date]
- **Compatibility**: Java 8+

---

**Note**: This software demonstrates user management concepts. For production environments, consult with security experts and implement appropriate safeguards.
```