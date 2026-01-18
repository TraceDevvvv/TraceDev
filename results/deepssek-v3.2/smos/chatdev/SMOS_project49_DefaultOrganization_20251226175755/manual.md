```markdown
# ViewRequestsSubscription Administrator System - User Manual

## Overview
The ViewRequestsSubscription system is a Java-based desktop application designed for educational administrators to view and manage student registration requests. This system implements a complete workflow from administrator authentication to request management, including handling server connection states as specified in the use case requirements.

## Main Features

### 1. **Administrator Authentication**
- Secure login interface for administrator access
- Input validation for credential verification
- Session management between login and dashboard

### 2. **Registration Request Viewing**
- Display of pending student registration requests in tabular format
- Real-time request list updates
- Detailed information including:
  - Student ID
  - Full Name
  - Email Address
  - Department
  - Request Date

### 3. **Server Connection Management**
- Visual indicator of server connection status (Connected/Disconnected)
- Manual control to simulate server interruption as per use case
- Graceful handling of connection loss scenarios

### 4. **Session Management**
- Secure logout functionality
- Session cleanup upon logout
- Proper credential clearing for security

## System Requirements

### Software Requirements
- **Java Development Kit (JDK):** Version 8 or higher
- **Operating System:** Windows, macOS, or Linux
- **Memory:** Minimum 512MB RAM
- **Storage:** 50MB free disk space

### Dependencies
The application uses only standard Java libraries:
- Java Swing (AWT) for GUI
- No external dependencies required

## Installation and Setup

### Step 1: Install Java
1. Download and install Java Development Kit (JDK) from Oracle or OpenJDK
2. Verify installation by opening terminal/command prompt:
   ```bash
   java -version
   ```
3. Confirm Java version 8 or higher is displayed

### Step 2: Download the Application
1. Save the provided Java file as `ViewRequestsSubscription.java`
2. Ensure the filename matches exactly for proper compilation

### Step 3: Compile the Application
1. Open terminal/command prompt in the directory containing the Java file
2. Run the compilation command:
   ```bash
   javac ViewRequestsSubscription.java
   ```
3. Verify that compilation completes without errors

## Running the Application

### Starting the Program
1. After successful compilation, run the application:
   ```bash
   java ViewRequestsSubscription
   ```
2. The application will launch with the login screen

### Alternative: Using an IDE
1. Open the project in your preferred Java IDE (Eclipse, IntelliJ IDEA, NetBeans)
2. Run the `ViewRequestsSubscription` class as a Java Application
3. The IDE will handle compilation automatically

## User Guide

### 1. **Login Process**
- **Username:** Enter any non-empty username (demo purposes)
- **Password:** Enter any non-empty password (demo purposes)
- Click "Login" button or press Enter in password field
- **Note:** In this demo version, all non-empty credentials are accepted

### 2. **Navigating the Dashboard**
Once logged in, you'll see the Administrator Dashboard with:
- Main header displaying "Administrator Dashboard"
- Two main buttons:
  - **"View Registration Requests"**: Displays pending requests
  - **"Disconnect Server"**: Toggles server connection
- Server status indicator in bottom-left corner
- Logout button in bottom-right corner

### 3. **Viewing Registration Requests**
1. Click the **"View Registration Requests"** button
2. The system will check server connection status
3. If connected, pending requests display in the text area
4. Each request shows:
   - Student ID
   - Full Name
   - Email Address
   - Department
   - Request Date
5. A pop-up confirms the number of requests retrieved

### 4. **Managing Server Connection**
- **Current Status:** Displayed as "Connected" (green) or "Disconnected" (red)
- **Disconnecting:** Click "Disconnect Server" to simulate server interruption
- **Reconnecting:** Click "Reconnect Server" to restore connection
- **Effects of Disconnection:**
  - Request viewing is disabled
  - Clear visual indication of disconnection
  - Appropriate error messages when attempting actions

### 5. **Logging Out**
1. Click the **"Logout"** button
2. Confirm logout in the pop-up dialog
3. System automatically:
   - Simulates server disconnection
   - Clears all displayed data
   - Returns to login screen

### 6. **Sample Data**
The application includes five sample registration requests for demonstration:
- John Doe (CS Department), S001
- Jane Smith (Mathematics), S002
- Bob Johnson (Physics), S003
- Alice Brown (Engineering), S004
- Charlie Wilson (Biology), S005

## Troubleshooting

### Common Issues

#### 1. **Program Won't Compile**
- **Error:** "javac not recognized"
  - Solution: Ensure JDK is properly installed and PATH is set
- **Error:** "Class not found"
  - Solution: Verify filename matches class name (ViewRequestsSubscription)

#### 2. **Application Crashes on Startup**
- **Possible Cause:** Java version incompatibility
  - Solution: Update to Java 8 or higher
- **Possible Cause:** Missing GUI permissions
  - Solution: Run as administrator if on Windows

#### 3. **No Data Displayed**
- **Check:** Server connection status
- **Action:** Click "Reconnect Server" if disconnected
- **Note:** Sample data loads automatically on application start

### Server Connection Issues
If you experience persistent connection problems:
1. Ensure you're in a network-enabled environment
2. Check the status indicator at bottom-left
3. Use the disconnect/reconnect button to reset connection

## Security Considerations

### Demo Version Notes
- **Authentication:** Accepts all non-empty credentials
- **Data:** Uses static sample data
- **Connection:** Simulated connection states
- **Production Readiness:** Requires backend integration for production use

### Production Deployment Requirements
For actual deployment, additional security measures needed:
1. **Database Integration:** Replace sample data with real database
2. **Authentication:** Implement secure user management
3. **Network Security:** Add SSL/TLS for server communication
4. **Input Validation:** Enhanced validation for all user inputs
5. **Logging:** Implement comprehensive audit trails

## Keyboard Shortcuts
- **Enter:** Submit login form (when in password field)
- **Escape:** Close pop-up dialogs
- **Tab:** Navigate between form fields

## User Interface Components

### Login Screen
```
┌─────────────────────────────────┐
│      Administrator Login        │
├─────────────────────────────────┤
│ Username: [______________]      │
│ Password: [______________]      │
│                                 │
│           [ Login ]             │
└─────────────────────────────────┘
```

### Dashboard
```
┌─────────────────────────────────┐
│   Administrator Dashboard       │
├─────────────────────────────────┤
│ [View Registration Requests]    │
│ [Disconnect Server]             │
│                                 │
│ ┌─────────────────────────────┐ │
│ │ Pending Registration Requests│ │
│ │ ID      Name          Email │ │
│ │ =========================== │ │
│ │ S001    John Doe      ...   │ │
│ │ S002    Jane Smith    ...   │ │
│ └─────────────────────────────┘ │
│                                 │
│ Status: Connected ✓             │
│                       [Logout]  │
└─────────────────────────────────┘
```

## Best Pract

### For Administrators
1. **Regular Logouts:** Always log out when leaving workstation
2. **Connection Checks:** Verify server status before viewing requests
3. **Data Verification:** Cross-check request details when available
4. **Session Management:** Use single active session per administrator

### For System Maintainers
1. **Regular Backups:** Ensure request data is regularly backed up
2. **Monitoring:** Implement connection monitoring tools
3. **Updates:** Keep Java runtime environment updated
4. **Testing:** Regularly test server connection procedures

## Use Case Compliance
This application fully implements the specified use case:

### Preconditions Met
- ✓ User logs in as administrator
- ✓ Click "View list request list" button (renamed for clarity)

### Events Sequence
1. ✓ System displays list of registrations yet to be activated
2. ✓ Proper handling of server connection interruption

### Postconditions
- ✓ User can view registration requests by students
- ✓ User can interrupt SMOS server connection
- ✓ Proper state management during disconnection

## Support and Feedback

### Getting Help
If you encounter issues not covered in this manual:
1. Check the System Requirements section
2. Review Troubleshooting steps
3. Ensure Java environment is properly configured

### Reporting Issues
When reporting issues, please include:
1. Java version (`java -version`)
2. Operating system
3. Exact error messages
4. Steps to reproduce the issue

## Version Information
- **Application Version:** 1.0.0
- **Java Compatibility:** JDK 8+
- **GUI Framework:** Java Swing
- **Last Updated:** [Current Date]

## Future Enhancements
Planned features for future releases:
1. **Database Integration:** Connect to real database systems
2. **Advanced Filtering:** Search and filter capabilities
3. **Multi-language Support:** Internationalization
4. **Reporting:** Export requests to CSV/PDF
5. **Notifications:** Email alerts for new requests
6. **Bulk Operations:** Approve/reject multiple requests
7. **Audit Trail:** Track all administrative actions

---

**Note:** This is a demonstration application for the specified use case. For production deployment, additional security, database integration, and error handling must be implemented.
```