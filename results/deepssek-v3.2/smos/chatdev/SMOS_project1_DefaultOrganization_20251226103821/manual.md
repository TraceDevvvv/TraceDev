```markdown
# Login System - User Manual

## Overview
The Login System is a Java-based graphical user interface application that provides a secure authentication mechanism for registered users. It validates user credentials against a pre-defined user archive and grants access to a personalized workspace upon successful login.

## Main Features
- **User Authentication**: Validates username and password against stored credentials
- **Input Validation**: Ensures username and password meet minimum length requirements (≥5 characters)
- **Secure Password Handling**: Uses password fields to mask input
- **Real-time Feedback**: Provides immediate visual feedback on login attempts
- **Workspace Access**: Opens a personalized workspace upon successful authentication
- **Connection Management**: Simulates server connection interruption as per specified postconditions

## System Requirements
- **Java Runtime Environment (JRE)**: Version 8 or higher
- **Disk Space**: Minimum 10MB free space
- **Memory**: 512MB RAM minimum

## Installation

### Step 1: Install Java
1. Download the latest Java Runtime Environment from [Oracle's website](https://www.oracle.com/java/technologies/javase-jre8-downloads.html)
2. Run the installer and follow the installation wizard
3. Verify installation by opening a terminal/command prompt and typing:
```bash
java -version
```

### Step 2: Download the Application
1. Download the `LoginSystem.java` file to your local directory
2. Ensure the file is saved with proper Java extension (*.java)

### Step 3: Compile the Application
1. Open a terminal/command prompt
2. Navigate to the directory containing `LoginSystem.java`
3. Compile the application using:
```bash
javac LoginSystem.java
```
4. This will generate necessary class files for execution

## How to Use

### Starting the Application
1. After successful compilation, run the application using:
```bash
java LoginSystem
```
2. The login window will appear with username and password fields

### Pre-configured Test Accounts
For testing purposes, the following accounts are pre-configured:
- **Username**: admin12 | **Password**: adminpass123
- **Username**: user007 | **Password**: password007
- **Username**: testuser | **Password**: testpass12345

### Login Process
1. **Enter Credentials**:
   - Type your username in the "Username" field
   - Enter your password in the "Password" field
   - Note: Both fields require at least 5 characters

2. **Submit Login**:
   - Click the "Login" button
   - The system will validate your input

3. **Possible Outcomes**:
   - **Successful Login**: Green message appears, temporary delay, then workspace opens
   - **Length Error**: Red error message if username/password is too short
   - **Invalid Credentials**: Red error message if credentials don't match stored data

### Workspace Access
Upon successful login:
1. The login window closes automatically after 2 seconds
2. A new workspace window opens with personalized welcome message
3. The system console displays "[DEBUG] Interruption of connection to SMOS server"
4. You can close the workspace window using the standard window controls

## Error Handling
The system provides clear error messages for common issues:

### "Error: username and password must be at least 5 characters"
- **Cause**: Either username or password contains fewer than 5 characters
- **Solution**: Ensure both fields contain at least 5 characters

### "Invalid username or password"
- **Cause**: Credentials don't match any pre-configured user accounts
- **Solution**: Check username/password spelling or contact administrator

## Customization
### Modifying User Accounts
To add or modify user accounts, edit the `initializeUserArchive()` method in the source code:
```java
private static void initializeUserArchive() {
    // Add your custom users here
    userArchive.put("yourUsername", "yourPassword123");
}
```

### Changing Interface Settings
- Modify `frame.setSize(400, 200)` to adjust login window size
- Change `timer.setDelay(2000)` to adjust delay before workspace opens
- Edit workspace window properties in `showWorkspace()` method

## Troubleshooting

### Common Issues

**Application won't start**
```bash
Error: Could not find or load main class LoginSystem
```
- **Solution**: Ensure you're in the correct directory and compiled successfully

**ClassNotFoundException**
- **Solution**: Recompile the application: `javac LoginSystem.java`

**GUI doesn't appear**
- **Solution**: Check if Java GUI libraries are properly installed
- **Alternative**: Run with `java -Djava.awt.headless=false LoginSystem`

**Insufficient memory**
- **Solution**: Run with increased heap size:
```bash
java -Xmx512m LoginSystem
```

## Security Notes
⚠️ **Important Security Considerations**:
- This is a demonstration application with hardcoded credentials
- Do not use for production without implementing proper security measures
- In production environments:
  - Implement password encryption/hashing
  - Use secure connection protocols
  - Store credentials in secure databases
  - Implement account lockout policies

## Uninstallation
1. Close the application window
2. Delete the following files from your directory:
   - `LoginSystem.java`
   - `LoginSystem.class`
   - Any other generated *.class files
3. Optional: Uninstall Java if no longer needed

## Support
For issues or questions:
1. Check the troubleshooting section above
2. Ensure Java is properly installed and updated
3. Verify file permissions in your working directory

## Version Information
- **Application Version**: 1.0
- **Java Compatibility**: Java 8+
- **Last Updated**: October 2023
- **License**: Demonstration/Educational Use

---

*Note: This application is for educational purposes and demonstrates basic authentication concepts. It should not be used in production environments without significant security enhancements.*
```