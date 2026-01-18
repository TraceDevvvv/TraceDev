```markdown
# User Management System - Administrator's Manual

## Overview
The User Management System is a Java-based desktop application designed for administrators to view and manage user lists within an organization. This application implements the **ViewUserList** use case, allowing authenticated administrators to search for and display user information from the system archive with proper SMOS server connection handling.

## System Requirements

### Software Dependencies
- **Java Development Kit (JDK)**: Version 8 or higher
- **Java Runtime Environment (JRE)**: Version 8 or higher

### Hardware Requirements
- Minimum 2GB RAM
- 100MB available disk space
- Screen resolution: 1024x768 or higher

## Installation Instructions

### Step 1: Install Java
1. Download and install the latest JDK from [Oracle's website](https://www.oracle.com/java/technologies/downloads/)
2. Verify installation by opening a terminal/command prompt and typing:
   ```bash
   java -version
   ```
3. Ensure the JAVA_HOME environment variable is set correctly

### Step 2: Prepare the Application Files
1. Create a new directory for the application:
   ```bash
   mkdir UserManagementSystem
   cd UserManagementSystem
   ```

2. Save all the provided Java files in this directory:
   - `Main.java`
   - `ViewUserListApp.java`
   - `UserService.java`
   - `User.java`
   - `AdminSession.java`

## Running the Application

### Compilation
1. Open a terminal/command prompt in the application directory
2. Compile all Java files:
   ```bash
   javac *.java
   ```

### Execution
Run the application:
```bash
java Main
```

## Using the Application

### Initial Screen
Upon launching, you'll see the main application window with:
- Application title: "User Management System"
- Control buttons: User Manager, Login as Admin, Refresh, Logout
- Status bar at the bottom showing current login status
- Empty user table area

### Login Process
1. Click the **"Login as Admin"** button
2. Enter credentials in the login dialog:
   - Username: `admin`
   - Password: `admin123`
3. Click OK to authenticate
4. Successful login will show a confirmation message

### Viewing User List
1. Ensure you're logged in as administrator (status bar should show "Logged in as administrator")
2. Click the **"User Manager"** button
3. The system will:
   - Check SMOS server connection status
   - Search for users in the archive
   - Display the list in the table

### User Table Features
The displayed user list includes:
- **ID**: Unique user identifier
- **Username**: User's login name
- **Email**: User's email address
- **Role**: User's role in the system
- **Created Date**: Date when user account was created

**Table Interactions:**
- Click column headers to sort data
- Scroll vertically to view all users
- Table is read-only (no editing allowed)

### SMOS Server Connection Handling

#### Normal Operation
- Green status indicates active SMOS server connection
- User data loads successfully when clicking "User Manager"

#### Connection Issues
If SMOS server connection is interrupted:
1. Red status message appears
2. Warning dialog prompts for reconnection attempt
3. Options:
   - **YES**: Attempt automatic reconnection (70% success rate in simulation)
   - **NO**: Abort user list loading

#### Reconnection
- Automatic reconnection attempts when connection is detected as interrupted
- Manual reconnection via Refresh button
- Success/failure messages provide clear feedback

### Additional Functions

#### Refresh Data
- Click **"Refresh"** button to reload user list
- Useful after reconnecting to SMOS server
- Requires administrator login

#### Logout
- Click **"Logout"** button to end administrator session
- Confirmation message appears
- Status returns to "Not logged in"
- User Manager button becomes inaccessible

## Security Features

### Authentication
- Administrator-only access to user management functions
- Credential validation before granting access
- Session tracking prevents unauthorized access

### Data Protection
- Read-only user table prevents accidental modifications
- Secure session management
- Proper error handling prevents information leaks

## Troubleshooting

### Common Issues

#### 1. Application Won't Start
**Symptoms:**
- "java not recognized" error
- Class not found errors

**Solutions:**
- Verify Java installation: `java -version`
- Ensure all Java files are in same directory
- Recompile all files: `javac *.java`

#### 2. Login Fails
**Symptoms:**
- "Invalid administrator credentials" message

**Solutions:**
- Use correct credentials: username=`admin`, password=`admin123`
- Check for typos in login dialog
- Ensure Caps Lock is off

#### 3. User List Not Loading
**Symptoms:**
- SMOS connection error messages
- Empty user table

**Solutions:**
- Click YES when prompted to reconnect
- Use Refresh button after reconnection
- Wait a moment and try again (simulated intermittent connections)

#### 4. GUI Appearance Issues
**Symptoms:**
- Unusual colors or fonts
- Misaligned components

**Solutions:**
- Application uses system default look and feel
- Different operating systems may display slightly differently
- This is normal and doesn't affect functionality

### Error Messages

| Message | Meaning | Action |
|---------|---------|--------|
| "Please log in as administrator first" | User clicked User Manager without login | Login using Login as Admin button |
| "SMOS server connection interrupted" | Backend server connection lost | Attempt reconnection or try later |
| "Cannot load user list without SMOS server connection" | Persistent connection issue | Check network connection and retry |
| "Error loading user list" | General data retrieval error | Refresh or restart application |

## Application Architecture

### Key Components
1. **Main**: Application entry point
2. **ViewUserListApp**: Main GUI and controller
3. **UserService**: Data access and SMOS simulation
4. **User**: Data model class
5. **AdminSession**: Authentication manager

### Data Flow
1. User authenticates via AdminSession
2. User clicks User Manager button
3. ViewUserListApp checks SMOS connection via UserService
4. UserService retrieves simulated user data
5. Data displays in JTable with proper formatting

## Best Pract

### For Administrators
1. Always log out when finished
2. Use Refresh button after reconnection attempts
3. Report persistent SMOS connection issues to IT support
4. Keep login credentials secure

### For Development
1. Modify UserService to integrate with real database
2. Extend User class for additional attributes
3. Enhance authentication with real backend
4. Add export functionality for user lists

## Support

### Getting Help
For technical support:
1. Check troubleshooting section above
2. Verify system requirements are met
3. Contact system administrator for persistent issues

### Reporting Bugs
Please report any issues with:
1. Steps to reproduce
2. Expected behavior
3. Actual behavior
4. Screenshots if applicable

---

**Version:** 1.0  
**Last Updated:** 2023  
**Compatibility:** Java 8+  
**License:** Proprietary - ChatDev Software
```