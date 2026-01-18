```markdown
# InsertDelayAdmin Application - User Manual

## Overview
InsertDelayAdmin is a Java-based desktop application designed for educational administrators to manage and record student delay information. The application provides a secure, user-friendly interface that follows the workflow specified in the use case documentation, including authentication, data entry, server communication, and parent notifications.

## Main Features
- **Secure Administrator Authentication**: Role-based access control requiring administrator credentials
- **Intuitive GUI Interface**: Clean, organized layout with clear data entry forms
- **Date-Based Data Management**: Select specific dates for delay record entry and viewing
- **Real-Time Data Display**: Table view showing all entered records for the selected date
- **Server Communication**: Automated data transmission to server with status feedback
- **Parent Notification System**: Simulated parent notification for each delay entry
- **Comprehensive Logging**: Detailed activity logs showing all system operations
- **Error Handling**: Built-in validation and error reporting for data integrity

## System Requirements
### Minimum Requirements
- Operating System: Windows 10+, macOS 10.14+, or Linux with X11
- Java Runtime Environment (JRE): Version 8 or higher
- Memory: 512 MB RAM minimum
- Disk Space: 10 MB free space

### Recommended Specifications
- Operating System: Latest version of Windows, macOS, or Linux
- Java Development Kit (JDK): Version 11 or higher
- Memory: 1 GB RAM or more
- Display: 1024x768 resolution minimum

## Installation Guide

### Step 1: Install Java
1. **Check if Java is installed**:
   - Open terminal/command prompt
   - Type: `java -version`
   - If you see version information, Java is installed

2. **Download Java (if needed)**:
   - Visit [Oracle Java Downloads](https://www.oracle.com/java/technologies/downloads/) or
   - Visit [OpenJDK](https://openjdk.java.net/install/)
   - Download the appropriate JDK for your operating system
   - Follow installation instructions for your platform

### Step 2: Download Application Files
1. Create a project directory:
   ```bash
   mkdir InsertDelayAdmin
   cd InsertDelayAdmin
   ```

2. Download or create the following files in your project directory:
   - `Main.java` - Application entry point
   - `LoginDialog.java` - Authentication dialog
   - `MainFrame.java` - Main application window
   - `StudentDelay.java` - Data model class
   - `MockServer.java` - Server simulation
   - `README.md` - Documentation (optional)

### Step 3: Compile the Application
Open your terminal/command prompt in the project directory and run:

```bash
javac *.java
```

This will compile all Java files and create corresponding `.class` files.

## How to Use the Application

### 1. Launching the Application
Run the application using:
```bash
java Main
```

### 2. Administrator Login
When the application starts, you'll see the login screen:

**Default Administrator Credentials:**
- **Username**: `admin`
- **Password**: `admin123`

**Login Steps:**
1. Enter "admin" in the Username field
2. Enter "admin123" in the Password field
3. Click "Login" button

**Note**: If login fails, ensure you're using the correct credentials. The application will show appropriate error messages for incorrect credentials or empty fields.

### 3. Main Application Interface
After successful login, the main application window appears with three main sections:

#### **Top Section (Date Selection)**
- **Date Field**: Shows current date in YYYY-MM-DD format
- **Update Display Button**: Refreshes the display for the selected date

#### **Center Section (Data Management)**
- **Data Entry Form**:
  - Student ID: Enter unique student identifier
  - Student Name: Enter student's full name
  - Delay Reason: Enter reason for delay (e.g., "Late arrival", "Missed class")
- **Data Table**: Displays all recorded delay entries for the selected date
- **Save Button**: Click to save the entered data

#### **Bottom Section (Logs & Status)**
- **Log Area**: Shows real-time system messages including:
  - Server connection status
  - Data transmission results
  - Parent notification status
  - Error messages (if any)

### 4. Complete Workflow Example

**Scenario**: Recording a student delay for October 26, 2023

1. **Set Date** (if different from current date):
   - Modify the date field to: `2023-10-26`
   - Click "Update Display" button
   - Log shows: `Display updated for date: 2023-10-26`

2. **Enter Delay Information**:
   - Student ID: `S12345`
   - Student Name: `John Smith`
   - Delay Reason: `Late arrival - traffic`

3. **Save the Record**:
   - Click "Save" button
   - System automatically:
     - Validates all fields are filled
     - Saves record to local list
     - Sends data to simulated server
     - Records appear in the data table
     - Log shows transmission status

4. **Observe Results**:
   - Successful transmission log:
     ```
     Data saved and sent to server
     Notification sent to parents
     ```
   - Record appears in table with columns:
     - Student ID, Name, Delay Reason, Date

### 5. Working with Multiple Records
You can enter multiple delay records without restarting:
1. After each save, form fields automatically clear
2. Continue entering new records
3. All records for the selected date display in the table
4. Logs accumulate for all operations

## Understanding Server Simulation

### Normal Operation
- Most data transmissions succeed
- Parent notifications are sent
- Log shows green status messages

### Simulated Interruptions
The application simulates real-world server issues:
- Every 3rd request fails (SMOS server interruption simulation)
- Failed transmissions show error messages in logs
- System continues to operate locally

**Example Log During Interruption:**
```
Error: Server connection failed for student: Jane Doe
Server connection interrupted (SMOS server)
```

### Handling Interruptions
1. **Data Persistence**: Records are still saved locally
2. **User Notification**: Logs clearly indicate server issues
3. **Continued Operation**: You can continue entering data
4. **Automatic Recovery**: Server reconnects automatically after interruption

## Troubleshooting

### Common Issues and Solutions

#### Issue 1: Application Won't Start
**Symptoms**: Nothing happens when running `java Main`
**Solutions**:
1. Verify Java installation:
   ```bash
   java -version
   ```

2. Check compilation:
   ```bash
   javac *.java
   ls *.class
   ```

3. Ensure you're in the correct directory containing all `.java` files

#### Issue 2: Login Fails
**Symptoms**: "Authentication failed" message
**Solutions**:
1. Verify correct credentials:
   - Username: `admin`
   - Password: `admin123`

2. Check for spaces in input fields
3. Restart application if persistent

#### Issue 3: Save Button Not Working
**Symptoms**: Clicking Save does nothing
**Solutions**:
1. Check all form fields are filled
2. Verify date format is YYYY-MM-DD
3. Look for error messages in bottom log area

#### Issue 4: Server Connection Errors
**Symptoms**: Frequent "Server connection failed" messages
**Solutions**:
This is simulated behavior (every 3rd request intentionally fails)
1. Review logs for pattern
2. Continue operation - system will recover
3. All data is preserved locally

### Error Messages Reference

| Message | Meaning | Action Required |
|---------|---------|----------------|
| "All fields must be filled!" | Form validation failed | Fill all fields before saving |
| "Authentication failed" | Login credentials incorrect | Verify username/password |
| "Server connection interrupted" | Simulated server failure | Continue working - automatic recovery |
| "Notification failed" | Parent notification simulation failed | No action needed - simulated behavior |

## Best Pract

### Data Entry Tips
1. **Use Consistent Formats**:
   - Student ID: Use consistent identification system
   - Dates: Always use YYYY-MM-DD format
   - Names: Include first and last names

2. **Descriptive Delay Reasons**:
   - Be specific: "Late arrival - bus delay" not just "Late"
   - Include time if relevant: "Missed first period - doctor appointment"

### Application Usage Tips
1. **Regular Updates**: Click "Update Display" when changing dates
2. **Log Monitoring**: Check bottom panel regularly for system status
3. **Batch Operations**: Enter all delays for a day before changing date
4. **Error Review**: Review logs at end of session for any issues

### Security Considerations
1. **Default Credentials**: Change hardcoded credentials for production use
2. **Session Management**: Application doesn't support multiple simultaneous users
3. **Data Storage**: Current implementation stores data in memory only

## For System Administrators

### Customization Options
To adapt this application for your environment:

1. **Change Default Credentials**:
   - Edit `LoginDialog.java`
   - Modify the authentication logic in `authenticateUser()` method

2. **Modify Server Behavior**:
   - Edit `MockServer.java`
   - Adjust `interruptionCounter` logic for different failure patterns

3. **Add Database Integration**:
   - Replace `ArrayList<StudentDelay>` with database connection
   - Implement persistent storage in `MainFrame.java`

### Building for Distribution
Create an executable JAR file:
```bash
jar cfe InsertDelayAdmin.jar Main *.class
```

Run the JAR:
```bash
java -jar InsertDelayAdmin.jar
```

## Support

### Getting Help
1. **Check Logs**: Most issues are logged in the application's bottom panel
2. **Review This Manual**: Common solutions are documented above
3. **System Requirements**: Verify your environment meets minimum specifications

### Known Limitations
- Data is stored in memory only (loses data on application close)
- Single-user only (no concurrent access support)
- Server simulation only (requires integration with real server)
- No data export/import functionality

### Future Development
Planned enhancements include:
- Database integration for persistent storage
- Multi-user support with role management
- Report generation
- Email notification system
- Mobile companion application

---

**Version**: 1.0  
**Last Updated**: October 2023  
**Compatibility**: Java 8+  
**License**: For internal educational use only

For additional support or customization requests, contact your system administrator.
```