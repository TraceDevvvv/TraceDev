```markdown
# ModifyPassword Application - User Manual

## Overview
The ModifyPassword application is a Java-based software solution designed for Agency Operators to securely change their account passwords. This application implements the complete "ModifyPassword" use case with multiple interface options, comprehensive validation, and robust error handling.

## Main Functions
The application provides the following core functionalities:
- **User Authentication**: Verifies the agency operator's identity through current password validation
- **Password Validation**: Enforces security rules for new passwords
- **Password Update**: Securely updates passwords in the system
- **Error Handling**: Manages various error scenarios including server interruptions
- **Multiple Interfaces**: Offers both console-based and GUI-based options

## System Requirements
- **Operating System**: Windows, macOS, or Linux
- **Java Runtime**: Java SE 8 or higher
- **Memory**: Minimum 512MB RAM
- **Storage**: 10MB free space

## Environment Setup

### 1. Java Installation
If Java is not already installed on your system:

**Windows:**
1. Visit [Oracle Java Downloads](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)
2. Download the appropriate installer for your system (x64 or x86)
3. Run the installer and follow the setup wizard
4. Verify installation by opening Command Prompt and typing:
   ```
   java -version
   ```

**macOS:**
1. Install via Homebrew:
   ```
   brew install openjdk@8
   ```
2. Or download from Oracle website
3. Set JAVA_HOME environment variable if needed

**Linux (Ubuntu/Debian):**
```bash
sudo apt update
sudo apt install openjdk-8-jdk
```

### 2. Development Setup (For Developers)
To compile and run from source:

1. Ensure Java Development Kit (JDK) is installed:
   ```bash
   javac -version
   ```

2. Install a text editor or IDE:
   - **Visual Studio Code** with Java Extension Pack
   - **IntelliJ IDEA** Community Edition
   - **Eclipse IDE** for Java Developers

3. Clone or download the application files to your local directory

## How to Use/Play

### Option 1: Using the Console Application

#### Step 1: Locate the Console Application File
Find the file named `ModifyPasswordConsole.java` in your application directory.

#### Step 2: Compile the Application
Open your terminal/command prompt and navigate to the directory containing the file:
```bash
cd path/to/application/folder
javac ModifyPasswordConsole.java
```

#### Step 3: Run the Application
```bash
java ModifyPasswordConsole
```

#### Step 4: Follow the On-Screen Instructions
1. **Enter Username**: Type `agency_op` (default test user)
2. **Enter Current Password**: Type `old_pass123`
3. **Enter New Password**: Choose a new password meeting these requirements:
   - At least 8 characters long
   - Contains both letters and digits
4. **Confirm New Password**: Re-enter the new password exactly
5. **View Results**: The system will confirm success or show appropriate error messages

#### Console Application Demo Example:
```
=== Modify Password for Agency Operator ===
Enter your username (e.g., 'agency_op'): agency_op
Enter your current password: old_pass123
Enter your new password: NewPass2024
Confirm new password: NewPass2024
Success: Password updated successfully! (Exit condition: operation success)
```

### Option 2: Using the GUI Application

#### Step 1: Locate the GUI Application File
Find the file named `ModifyPassword_GUI.java` or `GuiBasedPasswordChanger.java`.

#### Step 2: Compile the GUI Application
```bash
javac ModifyPassword_GUI.java
```

#### Step 3: Run the GUI Application
```bash
java ModifyPassword_GUI
```

#### Step 4: Using the Graphical Interface
1. **Launch**: The application opens in a new window titled "Modify Password - Agency Operator"
2. **Fill the Form**:
   - **Username**: Enter `agency_op` (pre-filled for testing)
   - **Current Password**: Enter `old_pass123` (pre-filled for testing)
   - **New Password**: Enter your desired new password
   - **Confirm New Password**: Re-enter the new password

3. **Password Requirements** (visible in error messages):
   - Minimum 8 characters
   - Must contain at least one letter (a-z, A-Z)
   - Must contain at least one digit (0-9)

4. **Click "Change Password"**: 
   - Green success message appears if all validations pass
   - Red error messages appear for any issues

5. **Cancel**: Click "Cancel" to exit the application

## Additional Features

### Enhanced GUI Version (GuiBasedPasswordChanger)
This version offers additional features:
- **Improved Layout**: Better organized form with padding and alignment
- **Additional Validation**: More detailed error messages
- **Status Area**: Clear feedback area at the top of the window
- **Reset Functionality**: Automatic field clearing after successful update

### Compile and Run Enhanced GUI:
```bash
javac GuiBasedPasswordChanger.java
java GuiBasedPasswordChanger
```

## Default Test Credentials
The application comes with pre-configured test credentials for demonstration:
- **Username**: `agency_op`
- **Current Password**: `old_pass123`
- **Agency Name**: Global Logistics

## Error Scenarios and Solutions

### 1. Incorrect Current Password
**Error Message**: "Error: Incorrect current password."
**Solution**: Ensure you're entering the correct current password `old_pass123`

### 2. Weak New Password
**Error Message**: "Error: New password must be at least 8 characters and contain a letter and a digit."
**Solution**: Choose a stronger password like `Secure1234` or `MyPass2024`

### 3. Password Mismatch
**Error Message**: "Error: New password and confirmation do not match."
**Solution**: Carefully re-type the same password in both "New Password" and "Confirm" fields

### 4. Server Interruption (Simulated)
**Error Message**: "Error: Server interruption. Password not updated."
**Solution**: Try the operation again; this is a random simulation for testing error handling

## Troubleshooting

### Common Issues and Solutions:

#### 1. "Command not found: javac"
**Solution**: Install Java Development Kit (JDK) instead of just Java Runtime Environment (JRE)

#### 2. "Error: Could not find or load main class"
**Solution**: Ensure you're in the correct directory and the .class file was created:
```bash
ls *.class
```

#### 3. GUI Doesn't Open
**Solution**: Make sure you have GUI support and run with the correct command:
```bash
java -Djava.awt.headless=false ModifyPassword_GUI
```

#### 4. Application Runs but No Response
**Solution**: Check if your antivirus or firewall is blocking Java applications

## Security Notes

### Important Security Considerations:
1. **Test Environment Only**: This application uses a mock database for demonstration
2. **Plain Text Storage**: Passwords are stored in plain text for testing purposes only
3. **Real-World Use**: For production, implement:
   - Password hashing (BCrypt, Argon2)
   - Secure database connections
   - HTTPS encryption
   - Session management
   - Rate limiting on login attempts

### Password Security Best Pract:
- Use at least 12 characters
- Include uppercase, lowercase, numbers, and symbols
- Avoid common words and personal information
- Change passwords every 90 days
- Use unique passwords for different systems

## Advanced Configuration

### Modifying Test Credentials
To change default users, edit the initialization code in the Java files:
```java
static {
    users.put("agency_op", new String[]{"old_pass123", "Global Logistics"});
    // Add more users as needed
    users.put("admin", new String[]{"admin123", "Administration"});
}
```

### Changing Password Requirements
Modify the `validateNewPassword` method to adjust requirements:
```java
// For stronger requirements (12+ chars, special characters)
if (newPassword.length() < 12) return false;
```

## Performance Tips

1. **Compilation**: First-time compilation might take a few seconds
2. **Memory**: The application uses minimal memory (50-100MB)
3. **Startup**: GUI applications may take 2-3 seconds to initialize
4. **Responsiveness**: All operations complete within 1 second

## Support and Feedback

### Getting Help
1. **Check Documentation**: Review this manual and code comments
2. **Console Errors**: Read error messages carefully in the terminal
3. **GUI Status**: Check the status label for operation feedback

### Reporting Issues
If you encounter bugs or have suggestions:
1. Note the exact error message
2. Record steps to reproduce the issue
3. Check Java version with `java -version`
4. Verify file permissions and paths

### System Logs
For debugging, check system logs:
- **Windows**: Event Viewer → Windows Logs → Application
- **macOS/Linux**: System logs in `/var/log/`

## Updates and Maintenance

### Regular Updates
- Keep Java updated to the latest stable version
- Check for application updates if distributed
- Review security patches regularly

### System Maintenance
- Clear compiled .class files when updating source code
- Restart applications after system updates
- Monitor system resources during operation

## License and Legal
This is a demonstration application for educational purposes. All code is provided as-is without warranty. For production use, consult with security professionals and ensure compliance with your organization's IT policies and relevant data protection regulations.

---

**Note**: This application simulates a password change system. In a real production environment, consult with your IT department for proper security implementation and compliance with organizational policies.
```