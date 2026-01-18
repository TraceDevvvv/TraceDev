# Class Management System - Administrator Manual

## Overview

The Class Management System is a Java-based application that allows system administrators to view and manage academic class lists from a simulated SMOS server archive. This application enforces role-based authentication, provides a graphical user interface for class exploration, and handles various operational scenarios including user interruptions and server connectivity issues.

## System Requirements

### Software Dependencies
- **Java Runtime Environment**: Java 8 or higher
- **Operating System**: Windows, macOS, or Linux with GUI support
- **No additional libraries required**: Uses standard Java Swing components

### Hardware Requirements
- Minimum 512MB RAM
- 100MB free disk space
- Display resolution: 1024x768 or higher

## Installation

### Option 1: Using Pre-compiled JAR
1. Download the application package containing:
   - `ViewClassesList.class`
   - `AdminLogin.class`
   - `manual.md` (this file)

2. Run the following command in your terminal/command prompt:
   ```bash
   java AdminLogin
   ```

### Option 2: Compiling from Source
1. Ensure you have Java Development Kit (JDK) installed
2. Save the two Java files:
   - `ViewClassesList.java`
   - `AdminLogin.java`

3. Compile the application:
   ```bash
   javac ViewClassesList.java AdminLogin.java
   ```

4. Run the application:
   ```bash
   java AdminLogin
   ```

## Main Functions

### Authentication System
- **Role-based Access Control**: Only users with "Administrator" role can access the class management features
- **Session Management**: Tracks user login time and maintains session information
- **Credential Verification**: Validates against a simulated user database

### Class Management Features
1. **Academic Year Selection**: Dropdown menu for selecting target academic years (2020-2025)
2. **Archive Search**: Simulated search through SMOS server archives for class data
3. **Class List Display**: Formatted presentation of courses found for selected academic years
4. **Operation Control**: Ability to cancel ongoing searches and interrupt operations

### Error Handling
  - SMOS server connection interruption simulation
  - Network timeout handling
  - User permission validation
  - Operation cancellation management

## How to Use

### Step 1: Launch the Application
```bash
java AdminLogin
```

### Step 2: Login
1. Enter your credentials in the login screen:
   - **Username**: admin1, admin2, or user1
   - **Password**: 
     - admin1: pass123
     - admin2: mypass
     - user1: userpass

2. Select "Administrator" from the role dropdown
3. Click "Login" or press Enter

*Note: Only Administrator role users can access the class management system*

### Step 3: Class Management Screen
Upon successful authentication, you'll see the main interface with:
- **Session Information**: Top panel showing username and session start time
- **Academic Year Selection**: Dropdown with available academic years
- **Control Buttons**: View Classes, Cancel Operation, and Logout
- **Instruction Panel**: Detailed usage guidelines
- **Status Bar**: Operation feedback and system messages

### Step 4: View Classes List
1. Select an academic year from the dropdown (e.g., "2023-2024")
2. Click "View Classes" button
3. Observe the status bar: "Searching archive for classes..."
4. Wait approximately 2 seconds for the search simulation
5. View results in the main display area showing:
   - Academic year
   - Search time
   - Number of classes found
   - Detailed class list

### Step 5: Operation Management
- **Cancel Operation**: Click "Cancel Operation" during an active search to interrupt
- **Logout**: Click "Logout" to terminate session and return to login screen
- **Session Information**: Real-time display of user and session status

## User Scenarios

### Successful Class Viewing
1. Login as administrator
2. Select academic year "2022-2023"
3. Click "View Classes"
4. View displayed classes: CS201, MATH301, ENG201, BIO101

### Interrupted Operation
1. Start a class search
2. Immediately click "Cancel Operation"
3. System displays "OPERATION INTERRUPTED" message
4. Search is terminated without results

### Server Connection Issues
- The system simulates SMOS server interruptions with 10% probability
- If connection fails, an error message shows system status
- User can retry the operation

### Role Violation Attempt
1. Login with "Teacher" role (user1/userpass)
2. System denies access to class management
3. Message: "Access denied: Administrator role required"

## Troubleshooting

### Common Issues and Solutions

**Issue**: "Launch Error - This application requires login authentication"
**Solution**: Run `java AdminLogin` instead of `java ViewClassesList`

**Issue**: Blank screen or GUI not appearing
**Solution**: 
1. Verify Java installation: `java -version`
2. Ensure display settings support Java Swing
3. Check console for error messages

**Issue**: Login fails even with correct credentials
**Solution**: 
- Verify username/password combinations exactly as listed
- Ensure "Administrator" role is selected
- Check for extra spaces in input fields

**Issue**: Search operation hangs indefinitely
**Solution**: 
1. Click "Cancel Operation" button
2. If unresponsive, close application and restart
3. Check system resources and Java memory settings

### Log Files
The application does not create persistent log files. All status information is displayed in:
1. Application status bar
2. Main text display area
3. Dialog messages during critical events

## Security Features

### Authentication Security
- Password fields use masked input
- Role verification before access
- Session-based authorization

### Data Protection
- No persistent credential storage
- Session information only held in memory
- Simulated server connection (no actual network calls)

### Access Control
- Mandatory Administrator role for class management
- Automatic session termination on logout
- Operation interruption capability

## Best Pract

### For Administrators
1. **Always logout** when finishing work sessions
2. **Use Cancel Operation** for unnecessary searches to save system resources
3. **Verify academic year** before initiating searches
4. **Monitor status bar** for system feedback

### For System Management
1. Regularly update user credentials in the source code if deploying custom versions
2. Modify academic year ranges and class data according to institutional needs
3. Consider implementing logging for audit trails in production deployments

## Customization

### Modifying Academic Years
Edit the `ACADEMIC_YEARS` array in `ViewClassesList.java`:
```java
private static final String[] ACADEMIC_YEARS = {
    "2020-2021", "2021-2022", "2022-2023", "2023-2024", "2024-2025"
};
```

### Updating Class Data
Modify the `CLASSES_BY_YEAR` array to match your institution's data:
```java
private static final String[][] CLASSES_BY_YEAR = {
    {"CS101", "MATH201", "ENG102", "PHY101"},
    // ... other years
};
```

### Adding User Credentials
Update the `VALID_USERS` array in `AdminLogin.java`:
```java
private static final String[][] VALID_USERS = {
    {"admin1", "pass123", "Administrator"},
    // Add new users here
};
```

## Support and Contact

### Getting Help
1. Review this manual thoroughly
2. Check the in-application instructions
3. Examine source code comments for technical details

### Reporting Issues
When reporting problems, please include:
1. Java version (`java -version`)
2. Operating system and version
3. Exact steps to reproduce the issue
4. Screenshots of error messages if possible

### Development Notes
This is a demonstration application showing:
- Java Swing GUI development
- Multi-threaded operations with cancellation
- Role-based authentication patterns
- Use case implementation following software engineering pract

---

*Note: This application simulates SMOS server connectivity and uses mock data for demonstration purposes. In production environments, integrate with actual data sources and implement proper security measures.*