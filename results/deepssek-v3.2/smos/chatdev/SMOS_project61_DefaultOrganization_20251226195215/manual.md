```markdown
# School Parent Portal - ViewStudentData System

## Overview

The **ViewStudentData System** is a Java-based application designed for parents to view comprehensive information about their children's school records. The system provides a secure interface for parents to access important data including attendance, disciplinary notes, delays, and justifications, while maintaining connection to the school's SMOS server infrastructure.

## Main Features

### 1. **Secure Authentication**
- Parent login with username and password validation
- Security mechanism preventing unauthorized access
- Simulated password management system

### 2. **Child Information Management**
- Multiple child support per parent account
- Organized display of all registered children
- Individual child selection interface

### 3. **Comprehensive Data Display**
    - **Date**: Record date in YYYY-MM-DD format
    - **Absences**: Number of absences per day
    - **Disciplinary Notes**: Behavioral observations from teachers
    - **Delays**: Tardy occurrences and duration
    - **Justification**: Reasons provided for absences/delays

### 4. **SMOS Server Integration**
- Robust connection handling with retry logic
- Automatic reconnection attempts on interruption
- Reliable data fetching with error handling

### 5. **Statistical Analysis**
- Total absence calculation
- Delay frequency tracking
- Disciplinary note trends
- Daily averages and summaries

## System Requirements

### Minimum Requirements
 also

### Development Requirements
- Java Development Kit (JDK) 8 or higher
- Text editor or IDE (recommended: IntelliJ IDEA, Eclipse, or VS Code)
- Terminal/Command prompt access

## Installation & Setup

### Step 1: Install Java
**Windows Users:**
1. Download JDK from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html)
2. Run the installer and follow setup instructions
3. Set JAVA_HOME environment variable:
```bash
setx JAVA_HOME "C:\Program Files\Java\jdk-xx.x.x"
```
4. Add Java to PATH:
```bash
setx PATH "%PATH%;%JAVA_HOME%\bin"
```

**macOS Users:**
```bash
brew install openjdk
```

**Linux Users (Ubuntu/Debian):**
```bash
sudo apt update
sudo apt install default-jdk
```

### Step 2: Verify Installation
Open terminal and run:
```bash
java -version
javac -version
```
Both commands should display version information.

### Step 3: Prepare Application Files
Create a directory for the application:
```bash
mkdir SchoolParentPortal
cd SchoolParentPortal
```

Place the following files in this directory:
1. `ViewStudentData.java` - Main application class
2. `SMOSConnector.java` - Server connection handler
3. `ChildRecord.java` - Data record class

### Step 4: Compile the Application
```bash
javac *.java
```
Successful compilation will create `.class` files for each Java class.

## How to Use

### Step 1: Launch the Application
```bash
java ViewStudentData
```

### Step 2: Login Process
You will see the welcome screen:
```
==========================================
     SCHOOL PARENT PORTAL - ViewStudentData
==========================================

[LOGIN SCREEN]
Enter parent username: 
Enter password: 
```

**Available Test Accounts:**
- Username: `john_parent` | Password: `parent123`
- Username: `sarah_parent` | Password: `parent123`
- Username: `mike_parent` | Password: `parent123`

### Step 3: View Your Children
After successful login:
```
[YOUR CHILDREN]
You have 2 child(ren):
--------------------------------
1. child_001
2. child_002
--------------------------------
```

### Step 4: Select a Child
Enter the number corresponding to the child whose data you want to view:
```
[CHILD SELECTION]
Click 'Register' button for a child to view their data:
Enter child number (1-2): 1
```

### Step 5: Data Retrieval Process
The system will attempt to connect to the SMOS server:
```
[SERVER CONNECTION]
Attempting SMOS server connection (attempt 1)...
```

Connection attempts include built-in retry logic (up to 3 attempts) to handle server interruptions.

### Step 6: View Child Information
Once connected successfully:
```
==========================================
     CHILD INFORMATION SUMMARY TABLE
==========================================
Child ID: child_001
Date: 2024-01-15 14:30:00

+--------------+-----------+---------------------------+-----------------+----------------------+
| Date         | Absences  | Disciplinary Notes        | Delays          | Justification        |
+--------------+-----------+---------------------------+-----------------+----------------------+
| 2024-01-15   | 0         | Good behavior             | None            | N/A                  |
| 2024-01-16   | 1         | Late submission           | 15 minutes      | Traffic              |
| 2024-01-17   | 0         | Excellent participation   | None            | N/A                  |
| 2024-01-18   | 2         | Disturbed class           | 5 minutes       | Forgot materials     |
| 2024-01-19   | 0         | Helped classmates         | None            | N/A                  |
+--------------+-----------+---------------------------+-----------------+----------------------+

Total records displayed: 5
```

### Step 7: Review Statistics
Additional insights are provided:
```
[SUMMARY STATISTICS]
--------------------------------
Total absences: 3
Days with delays: 2
Days with disciplinary notes: 3
Total days tracked: 5
Average absences per day: 0.60
--------------------------------
```

## User Interface Guide

### Login Screen
- **Username**: Enter parent username (case-sensitive)
- **Password**: Enter associated password
- **Error Handling**: Invalid credentials will display error message

### Navigation
1. **Main Menu**: Displayed after login showing registered children
2. **Selection**: Use number keys (1, 2, 3...) to choose a child
3. **Exit**: Program terminates automatically after data display

### Data Interpretation
- **Absences**: Whole numbers (0, 1, 2...)
- **Delays**: "None" or duration (e.g., "15 minutes")
- **Disciplinary Notes**: Descriptive text from teachers
- **Justification**: "N/A" or reason provided

## Troubleshooting

### Common Issues and Solutions

**1. Connection Issues:**
```
Failed to connect to SMOS server after 3 attempts
```
- Check your internet connection
- Ensure the application can access external networks
- Wait a few minutes and try again

**2. Compilation Errors:**
```
error: cannot find symbol
```
- Ensure all three Java files are in the same directory
- Check Java version compatibility
- Verify file names match class names

**3. Runtime Errors:**
```
Exception in thread "main" java.lang.ClassNotFoundException
```
- Recompile all classes: `javac *.java`
- Ensure you're running from the correct directory
- Check case sensitivity in file names

**4. No Data Displayed:**
- Verify server connection was successful
- Ensure child selection was valid
- Check if there are any server interruption warnings

### Debug Mode
For detailed debugging information, you can modify the SMOSConnector:
```java
// In SMOSConnector.java, modify connection simulation:
boolean success = Math.random() > 0.2;  // 80% success rate (instead of 50%)
```

## Security Notes

### Current Implementation
- **Authentication**: Simple username/password verification
- **Data Storage**: In-memory simulation only
- **Session Management**: Single session per execution

### Production Considerations
**⚠️ IMPORTANT**: This is a demonstration system. For production use:
1. Implement proper password hashing (bcrypt, PBKDF2)
2. Use HTTPS for all communications
3. Implement session tokens and expiration
4. Add database persistence for user data
5. Implement role-based access control
6. Add audit logging for data access
7. Use prepared statements to prevent SQL injection
8. Implement rate limiting for login attempts

## Advanced Features

### Custom Data Setup
You can modify the sample data in `SMOSConnector.java`:
```java
// Add custom records
records.add(new ChildRecord("2024-01-20", 0, "Perfect attendance", 
                           "None", "N/A"));
```

### Connection Retry Configuration
Configure retry logic in `SMOSConnector.java`:
```java
private static final int MAX_RETRY_ATTEMPTS = 5;    // Increase attempts
private static final int RETRY_DELAY_MS = 2000;     // Increase delay
```

## Support

### For Issues
1. Check the troubleshooting guide above
2. Verify Java installation and PATH settings
3. Ensure all files are in the correct directory

### Reporting Bugs
Please document:
1. Error message received
2. Steps to reproduce
3. Java version (`java -version`)
4. Operating System details

## License & Attribution

### Educational Use
This system is designed for educational purposes to demonstrate:
- Java application structure
- Use case implementation
- User authentication workflows
- External system integration
- Error handling patterns

### Modification Rights
Users are permitted to:
- Modify code for educational purposes
- Extend functionality
- Integrate with other systems
- Share modified versions with attribution

## Version History

- **Version 1.0** (Current)
  - Initial release
  - Basic authentication
  - SMOS server integration
  - Summary table display
  - Statistical analysis

## Future Enhancements

Planned features for future versions:
1. Graphical User Interface (GUI)
2. Data export (CSV, PDF)
3. Email notifications for new records
4. Mobile application support
5. Real-time data updates
6. Multi-language support
7. Accessibility features
8. Parent-teacher messaging system

## Contact

For questions or support regarding the ViewStudentData system, please contact your school's IT department or the application administrator.

---

**Note**: This application simulates a real-world school information system. All data shown is for demonstration purposes only and does not represent actual student records. Always follow your institution's data privacy policies when handling sensitive student information.
```