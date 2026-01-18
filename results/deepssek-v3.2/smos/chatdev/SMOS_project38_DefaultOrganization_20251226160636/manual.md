```markdown
# Justice Management System - User Manual

## Overview

The Justice Management System is a Java-based desktop application designed for administrators to manage justification records (also called "just"). The system implements the "ViewJustificationDetails" use case and provides a complete solution for viewing, modifying, deleting justification records, and managing SMOS server connections.

### Key Features

1. **Secure Administrator Authentication**
   - Role-based access control
   - Session management
   - Secure login/logout functionality

2. **Justice Record Management**
   - View detailed justification information
   - Modify existing justice records
   - Delete unnecessary records
   - Real-time form validation

3. **SMOS Server Integration**
   - Server connection monitoring
   - Manual server connection interruption
   - Connection status display

4. **User-Friendly Interface**
   - Tabbed navigation
   - Intuitive form design
   - Clear status messages
   - Confirmation dialogs for destructive actions

## System Requirements

### Hardware Requirements
- **Processor**: 1 GHz or faster
- **RAM**: Minimum 512 MB
- **Storage**: 50 MB free disk space
- **Display**: 1024x768 resolution or higher

### Software Requirements
- **Operating System**: Windows 7+, macOS 10.10+, or Linux (any modern distribution)
- **Java Runtime Environment (JRE)**: Version 11 or higher
- **Java Development Kit (JDK)**: Required for compilation (if modifying source code)

## Installation Guide

### Step 1: Verify Java Installation

Open a terminal/command prompt and run:
```bash
java -version
```

You should see output similar to:
```
java version "11.0.0" 2018-10-16 LTS
Java(TM) SE Runtime Environment 18.9 (build 11.0.0+28-LTS)
Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.0+28-LTS, mixed mode)
```

If Java is not installed or is older than version 11:
- **Windows**: Download from [Oracle Java Downloads](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- **macOS**: Use Homebrew: `brew install openjdk@11`
- **Linux**: Use package manager, e.g., `sudo apt install openjdk-11-jdk`

### Step 2: Download Application Files

Download all required Java files into a single directory:
```
JusticeManagementSystem/
├── JusticeData.java
├── JusticeService.java
├── ServerConnectionException.java
├── Authentication.java
├── JusticeForm.java
└── MainApplication.java
```

### Step 3: Compile the Application

Open a terminal/command prompt in the application directory and run:

```bash
javac *.java
```

Successful compilation will create `.class` files for each Java file:
```
JusticeData.class
JusticeService.class
ServerConnectionException.class
Authentication.class
JusticeForm.class
MainApplication.class
```

### Step 4: Run the Application

Execute the following command:

```bash
java MainApplication
```

The main application window should appear with a welcome message.

## Application Tour

### 1. Login Screen

**Features:**
- Username and password fields
- Pre-filled demo credentials
- Login button
- Status indicator

**Default Credentials:**
- **Username**: `admin`
- **Password**: `admin123`

**Usage:**
1. Enter credentials (or use defaults)
2. Click "Login as Administrator"
3. Successful login enables the "View Justice Details" tab

### 2. Justice Details Screen

**Navigation:**
After successful login, switch to the "View Justice Details" tab.

**Features:**
- Justice ID input field
- View Justice Details button
- Logout button
- Sample Justice IDs provided (J001, J002, J003)

**Usage:**
1. Enter a Justice ID (e.g., "J001")
2. Click "View Justice Details"
3. A new window opens with detailed information

### 3. Justice Details Form

**Form Components:**
1. **Justice ID**: Read-only field displaying the unique identifier
2. **Employee Name**: Editable field for employee name
3. **Date**: Editable field (format: YYYY-MM-DD)
4. **Reason**: Editable text area for justification reason
5. **Status**: Dropdown selector (Pending/Approved/Rejected)

**Action Buttons:**
1. **Modify Justice**: Saves changes to the current record
2. **Delete Justice**: Permanently removes the justice record
3. **Interrupt SMOS Connection**: Simulates server connection interruption

**Field Validation:**
- Employee Name and Date are required fields
- Error messages appear in red
- Success messages appear in blue

## Detailed Feature Guide

### A. Viewing Justice Details

**Prerequisites:**
- Administrator must be logged in
- Valid Justice ID must be provided

**Procedure:**
1. From the main application, enter Justice ID
2. Click "View Justice Details"
3. System displays all justice information in a form
4. Fields are pre-populated with current data

**Expected Outcome:**
- Form window opens
- All fields display current justice data
- Message confirms successful loading

### B. Modifying Justice Records

**Procedure:**
1. Edit any of the editable fields (Employee Name, Date, Reason, Status)
2. Click "Modify Justice" button
3. System validates inputs
4. Confirmation message appears upon success

**Validation Rules:**
- Employee Name cannot be empty
- Date cannot be empty
- Date format should be YYYY-MM-DD
- Reason can be any text

**Error Handling:**
- Missing required fields show error message
- Server connection issues display appropriate error
- System maintains data if modification fails

### C. Deleting Justice Records

**Safety Features:**
- Confirmation dialog prevents accidental deletion
- Double-check mechanism
- Form automatically closes after deletion

**Procedure:**
1. Click "Delete Justice" button
2. Confirm deletion in the dialog
3. System permanently removes the record
4. Form closes automatically

**Note:** Deletion cannot be undone. Always verify the justice record before confirming.

### D. Managing Server Connections

**Connection States:**
- **Connected**: Normal operation (default)
- **Interrupted**: Manual disconnection by administrator

**Interrupting Connection:**
1. Click "Interrupt SMOS Connection"
2. Confirm the action
3. All action buttons become disabled
4. Message confirms interruption

**Impact of Connection Interruption:**
- Modification operations fail with server error
- Deletion operations fail with server error
- View operations fail with server error
- Form remains open but actions are blocked

**Note:** In this simulation, the server connection remains interrupted until the application restarts. A real implementation would include reconnection functionality.

## Sample Data

The system includes three sample justice records for demonstration:

### Record J001
- **Employee**: John Doe
- **Date**: 2023-10-15
- **Reason**: Medical Appointment
- **Status**: Approved

### Record J002
- **Employee**: Jane Smith
- **Date**: 2023-10-16
- **Reason**: Family Emergency
- **Status**: Pending

### Record J003
- **Employee**: Robert Johnson
- **Date**: 2023-10-17
- **Reason**: Car Breakdown
- **Status**: Rejected

## Security Guidelines

### Authentication
- Always log out when leaving the workstation unattended
- Never share administrator credentials
- Use strong passwords in production environments

### Data Protection
- Justice records contain sensitive employee information
- Ensure physical security of the running application
- Follow organizational data handling policies

### Session Management
 maximiseSession timeout is not implemented in this demo version
- Manual logout is required to end sessions
- Multiple concurrent sessions are not supported

## Troubleshooting

### Common Issues and Solutions

#### Issue 1: "Java not found" error
**Solution:**
- Verify Java installation: `java -version`
- Ensure Java is in system PATH
- Reinstall Java if necessary

#### Issue 2: Compilation errors
**Solution:**
- Ensure all Java files are in the same directory
- Check for syntax errors in the source files
- Verify JDK version is 11 or higher

#### Issue 3: Application doesn't start
**Solution:**
- Check console for error messages
- Verify all `.class` files are present
- Ensure sufficient system resources

#### Issue 4: Login fails
**Solution:**
- Verify username is "admin"
- Verify password is "admin123"
- Check caps lock key
- Restart application if session issues occur

#### Issue 5: Server connection errors
**Solution:**
- This is a simulation feature
- Restart application to restore connection
- Check if "Interrupt SMOS Connection" was clicked

### Error Messages

#### Authentication Errors
- **"Login failed: Invalid credentials."**: Wrong username/password
- **"You must be logged in as administrator..."**: Session expired or not logged in

#### Justice Operation Errors
- **"Justice not found."**: Invalid Justice ID
- **"Employee name and date are required."**: Missing required fields
- **"Server Error: SMOS server connection interrupted"**: Server is disconnected

#### Server Errors
- **"SMOS server connection interrupted"**: Manual disconnection active
- **"Further operations may fail."**: Server unavailable warning

## Best Pract

### For Administrators
1. **Verify Information**: Always double-check justice details before modification
2. **Backup Data**: Maintain backups of justice records (not implemented in demo)
3. **Log Management**: Keep records of all modifications and deletions
4. **Session Control**: Log out after completing tasks
5. **Server Management**: Only interrupt server connections during maintenance windows

### For Developers (Modifying Source Code)
1. **Code Structure**: Follow existing patterns for consistency
2. **Error Handling**: Always implement proper exception handling
3. **Validation**: Add input validation for all user inputs
4. **Testing**: Test all modifications with sample data
5. **Documentation**: Update comments when modifying code

## Future Enhancements

Potential improvements for production deployment:

### Security Enhancements
- Password encryption
- Session timeout
- Audit logging
- Multi-factor authentication

### Functional Improvements
- Search functionality
- Bulk operations
- Report generation
- Data import/export

### Technical Enhancements
- Database integration
- Web service API
- Mobile compatibility
- Cloud deployment options

## Support and Contact

### Technical Support
For technical issues with the demo application:
- Check the troubleshooting section
- Review source code comments
- Contact your system administrator

### Development Support
For customization or enhancement requests:
- Java developers familiar with Swing
- Database integration specialists
- Security implementation experts

### Emergency Procedures
In case of critical system failure:
1. Document the error message
2. Restart the application
3. If problem persists, revert to backup
4. Contact technical support

## Glossary

- **Justice**: A justification record for employee absence
- **SMOS**: System Management and Operations Server
- **Administrator**: Authorized user with full access privileges
- **Justice ID**: Unique identifier for each justification record
- **Status**: Current state (Pending, Approved, Rejected)

## Appendix

### Keyboard Shortcuts
- **Tab**: Navigate between form fields
- **Enter**: Activate default button
- **Escape**: Close dialog windows
- **Ctrl+C**: Copy selected text
- **Ctrl+V**: Paste text

### File Locations
- **Source Code**: User-specified directory
- **Compiled Classes**: Same directory as source
- **Runtime Files**: No external files created

### System Limitations
- Demo version uses in-memory storage only
- No persistent database implementation
- Limited to three sample records
- Single-user only
- No network communication (simulated only)

---

**Version**: 1.0.0  
**Last Updated**: October 2023  
**Compatibility**: Java 11+  
**License**: Demo/Educational Use  

*Note: This is a demonstration application. For production use, additional security, persistence, and validation features must be implemented.*
```