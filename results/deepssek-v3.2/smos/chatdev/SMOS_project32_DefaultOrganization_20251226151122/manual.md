```markdown
# EditDelay Application - User Manual

## Overview

EditDelay is a Java-based desktop application designed for system administrators to manage and modify delay settings for specific dates. The application simulates a real-world scenario where administrators need to adjust timing parameters in a server system, with proper authentication, prerequisite verification, and server communication.

### Main Features

1. **Secure Authentication System**
   - Administrator-only access with username/password verification
   - Role-based authorization

2. **Prerequisite Verification**
   - Validates completion of required use cases before allowing edits
   - Prevents unauthorized or premature modifications

3. **Date-Based Delay Management**
   - Select from a list of available dates
   - Edit delay values in minutes for selected dates
   - Real-time screen updates when dates are selected

4. **Comprehensive Validation**
   - Input validation for delay values
   - Range checking (0-1440 minutes)
   - Integer format verification

5. **Server Communication Simulation**
   - Simulated server data storage and retrieval
   - Progress monitoring for server operations
   - Connection interruption handling

6. **Audit Logging**
   - Detailed activity logging with timestamps
   - Success/failure tracking of all operations

7. **User-Friendly Interface**
   - Clean, intuitive GUI design
   - Clear navigation and feedback mechanisms

## System Requirements

### Software Requirements
- **Java Runtime Environment (JRE)**: Version 8 or higher
- **Operating System**:
  - Windows 7/8/10/11
  - macOS 10.10 or later
  - Linux with X11 or Wayland display server
- **RAM**: Minimum 512MB, Recommended 1GB
- **Storage**: 50MB free disk space

### Hardware Requirements
- **Processor**: 1GHz or faster
- **Display**: 1024x768 screen resolution minimum
- **Input Dev**: Keyboard and mouse

## Installation Guide

### Step 1: Install Java Runtime Environment

#### Windows/Mac/Linux:
1. Visit [Oracle Java Downloads](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://adoptium.net/)
2. Download the appropriate JRE for your operating system
3. Run the installer and follow the setup wizard
4. Verify installation by opening a terminal/command prompt and typing:
   ```bash
   java -version
   ```

### Step 2: Download the Application

1. Download the `EditDelayApp.java` file from your distribution source
2. Save it to a convenient location (e.g., `C:\EditDelay` or `~/EditDelay`)

### Step 3: Compile the Application

1. Open a terminal/command prompt
2. Navigate to the directory containing `EditDelayApp.java`
3. Compile the Java file:
   ```bash
   javac EditDelayApp.java
   ```
4. Verify that a `EditDelayApp.class` file was created

## Running the Application

### Method 1: Command Line Execution

1. Navigate to the application directory
2. Run the application:
   ```bash
   java EditDelayApp
   ```

### Method 2: Creating a Desktop Shortcut (Windows)

1. Create a new shortcut on your desktop
2. Enter the target as:
   ```
   cmd /c start javaw EditDelayApp
   ```
3. Name the shortcut "EditDelay Administrator"
4. Set the working directory to where EditDelayApp.class is located

### Method 3: Creating a Desktop Launcher (Linux/Mac)

1. Create a new file named `editdelay.desktop` (Linux) or `EditDelay.command` (Mac)
2. Add executable permissions:
   ```bash
   chmod +x EditDelay.command
   ```
3. Edit the file with the following content:
   - **Linux**:
     ```
     [Desktop Entry]
     Type=Application
     Name=EditDelay Administrator
     Exec=java EditDelayApp
     Path=/path/to/application
     Terminal=false
     ```
   - **Mac**:
     ```bash
     #!/bin/bash
     cd /path/to/application
     java EditDelayApp
     ```

## Application Walkthrough

### Step 1: Authentication Screen
Upon launching the application, you will see the authentication screen:
1. Enter **Username**: `admin`
2. Enter **Password**: `admin123`
3. Click **OK** to proceed

>  ⚠️ **Note**: These are default credentials. In a production environment, these would be configured through a proper user management system.

### Step 2: Prerequisite Verification
After successful authentication, you'll be prompted to verify completion of prerequisites:
1. A dialog will ask: "Have you completed the 'View Test Timing Registration' use case?"
2. Click **Yes** to proceed or **No** to exit the application

### Step 3: Main Application Interface

#### A. Top Panel
- **User Information**: Displays logged-in username and role
- **Logout Button**: Red button to safely exit the application

#### B. Center Panel
1. **Date Selection**
   - Dropdown menu with available dates
   - Select any date to view/edit its delay

2. **Delay Input Field**
   - Enter delay value in minutes (0-1440)
   - Shows current delay for selected date
   - Automatically updates when date changes

3. **Action Buttons**
   - **Save Changes**: Blue button to save modifications
   - **Cancel**: Gray button to discard changes

#### C. Bottom Panel - Activity Log
- Real-time logging of all activities
- Timestamps for each action
- Success/Failure notifications
- Scrollable text area

### Step 4: Editing a Delay

1. **Select a Date**: Choose from the date dropdown
2. **View Current Delay**: The delay field shows the existing value
3. **Edit the Delay**: Type the new delay value in minutes
4. **Save Changes**: Click the "Save Changes" button

#### Save Process Flow:
1. **Validation**: Application checks:
   - Date is selected
   - Delay field is not empty
   - Delay is a valid integer
   - Delay is within 0-1440 minutes range

2. **Confirmation**: Shows confirmation dialog with old/new values

3. **Server Communication**: 
   - Shows progress monitor
   - Simulates network communication
   - Handles connection interruptions
   - Provides feedback on success/failure

### Step 5: Canceling Operations

If you need to cancel an edit:
1. Click the **Cancel** button
2. If changes were made, you'll be prompted to confirm cancellation
3. System will restore the original delay value

### Step 6: Logging Out

To safely exit the application:
1. Click the **Logout** button in the top-right corner
2. Confirm logout when prompted
3. Application will close gracefully

## File Structure

```
EditDelay/
├── EditDelayApp.java    # Main source code
├── EditDelayApp.class   # Compiled bytecode
└── manual.md           # This user manual
```

## Error Handling and Troubleshooting

### Common Issues and Solutions

#### 1. "Java not found" error
**Symptom**: Command line shows "java: command not found"
**Solution**: 
- Ensure Java is installed: `java -version`
- Add Java to system PATH if needed

#### 2. "Class not found" error
**Symptom**: "Could not find or load main class EditDelayApp"
**Solution**:
- Ensure you're in the correct directory
- Verify `EditDelayApp.class` exists
- Recompile if needed: `javac EditDelayApp.java`

#### 3. GUI display issues
**Symptom**: Buttons/text not displaying properly
**Solution**:
- Verify Java version compatibility
- Check system display settings
- Run with default look and feel

#### 4. Server communication failures
**Symptom**: Progress monitor shows connection interruption
**Solution**:
- Wait and retry (simulated 15% failure rate)
- Check simulated network conditions

### Log Interpretation
The activity log provides detailed information:
- `[HH:MM:SS]`: Timestamp of each action
- Success messages indicate completed operations
- Error messages provide specific failure reasons
- Server communication status updates

## Best Pract

### For Administrators:
1. **Always verify dates** before making changes
2. **Double-check delay values** before saving
3. **Review the activity log** periodically for audit trails
4. **Log out properly** when finished
5. **Keep credentials secure**

### For System Administrators (Deployment):
1. **Change default credentials** in production
2. **Implement real authentication** mechanisms
3. **Connect to actual server APIs** for live data
4. **Add proper encryption** for sensitive data
5. **Implement backup mechanisms** for delay data

## Security Considerations

### Current Security Features:
- Basic authentication gateway
- Role-based access control
- Input validation and sanitization
- Session management (logout functionality)

### Recommended Enhancements for Production:
1. **Authentication**: Integrate with LDAP/Active Directory
2. **Encryption**: SSL/TLS for server communication
3. **Audit Trail**: Database logging of all changes
4. **Access Control**: Granular permission system
5. **Session Management**: Timeout and token-based sessions

## Limitations and Future Enhancements

### Current Limitations:
1. **Simulated Data**: Uses in-memory HashMap instead of real database
2. **Fixed Dates**: Pre-populated dates in code
3. **Basic Authentication**: Simple user/password check
4. **Local Logging**: Activity logs not persisted

### Planned Enhancements:
1. **Database Integration**: Connect to SQL/NoSQL databases
2. **REST API Support**: Real server communication
3. **User Management**: Add/edit/delete administrators
4. **Export Features**: CSV/PDF reports of delay changes
5. **Multi-language Support**: Internationalization

## Support and Contact

### For Technical Support:
- Check the activity log for error details
- Verify Java installation and configuration
- Ensure proper file permissions

### For Feature Requests:
Contact your system administrator or development team with:
1. Specific use case requirements
2. Current limitations encountered
3. Suggested improvements

### Emergency Procedures:
If the application becomes unresponsive:
1. Close the application window
2. Check system resources (Task Manager/Activity Monitor)
3. Restart the application
4. If issues persist, restart your computer

## Release Notes

### Version 1.0.0 (Initial Release)
- Basic authentication system
- Date selection and delay editing
- Server communication simulation
- Activity logging
- Input validation
- Cancel/undo functionality

### Version 1.1.0 (Updated)
- Added prerequisite verification
- Enhanced error handling
- Improved UI/UX
- Better input validation
- More robust cancellation

## Glossary

- **Delay**: Time offset in minutes for a specific date
- **Administrator**: Authorized user with edit permissions
- **SMOS Server**: Simulated Management and Operations Server
- **Activity Log**: Record of all user actions and system responses
- **Prerequisite Use Case**: Required preceding action (View Test Timing Registration)

---

## Quick Reference Card

### Login Credentials
- Username: `admin`
- Password: `admin123`

### Valid Delay Range
- Minimum: 0 minutes
- Maximum: 1440 minutes (24 hours)

### Shortcuts
- **F5**: Refresh date list (simulation only)
- **Ctrl+S**: Quick save (when delay field is focused)
- **Ctrl+Q**: Quick logout
- **Esc**: Cancel operation

### Common Operations
1. **Edit Delay**: Select date → Enter value → Save
2. **Cancel Edit**: Click Cancel → Confirm
3. **View Log**: Scroll through activity log panel
4. **Logout**: Top-right logout button → Confirm

---

*This application is designed for training and demonstration purposes. In production environments, additional security measures, data persistence, and error handling should be implemented.*
```