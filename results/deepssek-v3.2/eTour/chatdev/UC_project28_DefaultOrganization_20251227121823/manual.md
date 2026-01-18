```markdown
# DeleteTag System - User Manual

## Overview
The DeleteTag System is a Java-based application designed for agency operators to manage search tags within an organization. This application allows authorized users to view existing search tags and delete one or multiple tags efficiently while ensuring data integrity and security.

## Main Features

### 1. Secure Authentication System
- **Role-based access**: Only authenticated agency operators can access the system
- **Encrypted credentials**: Password hashing using SHA-256 algorithm
- **Multiple user support**: Pre-configured accounts for agency, admin, and operator roles

### 2. Tag Management Interface
- **Visual tag display**: Clear tabular view of all existing tags
- **Tag selection**: Checkbox-based selection for batch operations
- **Tag information**: Comprehensive view including ID, name, description, and creation date

### 3. Bulk Operations
- **Multi-select deletion**: Delete one or multiple tags simultaneously
- **Deletion confirmation**: Safety confirmation before permanent deletion
- **Progress tracking**: Visual progress bar during tag deletion operations

### 4. System Reliability
- **Error handling**: Graceful handling of network interruptions
- **Connection monitoring**: Automatic server connection status checking
- **Data persistence**: Simulated database integration

## System Requirements

### Hardware Requirements
- **Processor**: 1 GHz or faster processor
- **Memory**: Minimum 512 MB RAM (1 GB recommended)
- **Storage**: 10 MB available disk space
- **Display**: 1024x768 screen resolution or higher

### Software Requirements
```
Operating System: Windows 7+, macOS 10.10+, or Linux (any modern distribution)
Java Runtime: Java 8 or higher (JRE 1.8+)
Required Java Packages: javax.swing, java.awt, java.util, java.security
```

## Installation Guide

### Step 1: Verify Java Installation
Before installing the DeleteTag System, ensure you have Java installed:

1. Open a command prompt or terminal
2. Type the following command and press Enter:
   ```
   java -version
   ```
3. Verify the output shows Java version 8 or higher
4. If Java is not installed, download it from:
   - [Oracle Java Downloads](https://www.oracle.com/java/technologies/javase-downloads.html) OR
   - [OpenJDK Downloads](https://openjdk.java.net/install/)

### Step 2: Download the Application
1. Obtain the DeleteTag application files:
   - Main.java
   - LoginFrame.java
   - DeleteTagFrame.java
   - TagService.java
   - DatabaseConnection.java
   - Tag.java

2. Create a project directory:
   ```bash
   mkdir DeleteTagSystem
   cd DeleteTagSystem
   ```

### Step 3: Compile the Application
1. Place all Java files in the same directory
2. Compile all Java files using:
   ```bash
   javac *.java
   ```
3. Verify compilation creates `.class` files for each Java file

## How to Use the DeleteTag System

### Starting the Application

**Method 1: Command Line**
1. Open a terminal or command prompt
2. Navigate to the application directory
3. Run the command:
   ```bash
   java Main
   ```

**Method 2: Using an IDE**
1. Open your Java IDE (Eclipse, IntelliJ IDEA, NetBeans)
2. Import the DeleteTag project
3. Run the `Main` class

### Step-by-Step Usage Guide

#### Step 1: Login to the System
1. Launch the application to see the login screen
2. Enter your credentials:
   - **Available Test Accounts:**
     - Username: `agency`, Password: `securePassword123`
     - Username: `admin`, Password: `adminPass456`
     - Username: `operator`, Password: `operatorPass789`
3. Click the **Login** button
4. Successful login will automatically open the main DeleteTag interface

#### Step 2: View Existing Tags
1. Upon successful login, you'll see the main interface showing:
   - A table displaying all existing search tags
   - Each tag shows: Selection checkbox, ID, Name, Description, and Creation Date
   - Status bar showing the number of loaded tags

2. If no tags are displayed, click the **Refresh Tags** button to reload

#### Step 3: Select Tags for Deletion
1. **Individual Selection:**
   - Click the checkbox in the first column next to any tag you want to delete
   
2. **Multiple Selection:**
   - Check multiple checkboxes to select several tags simultaneously
   - Use Shift+Click for range selection
   - Use Ctrl+Click (Cmd+Click on macOS) for non-contiguous selection

#### Step 4: Delete Selected Tags
1. After selecting tags, click the **Delete Selected Tags** button
2. A confirmation dialog will appear showing the number of selected tags
3. Click **Yes** to proceed with deletion or **No** to cancel
4. During deletion:
   - A progress bar shows the deletion status
   - Buttons are disabled to prevent concurrent operations
   - Status updates show current progress

#### Step 5: Confirm Successful Deletion
1. After completion, a success notification appears showing:
   - Number of successfully deleted tags
   - Confirmation message
2. The table automatically refreshes to show remaining tags
3. Status bar updates to reflect the completed operation

### Advanced Features

#### 1. Handling Connection Issues
The system is designed to handle server interruptions gracefully:

- **Automatic detection**: System detects when server connection is lost
- **User notification**: Clear error messages explain connection issues
- **Recovery option**: Use the **Refresh Tags** button to retry after network restoration

#### 2. Tag Information Display
- **Comprehensive view**: All tag attributes are visible in the table
- **Sortable columns**: Click column headers to sort alphabetically
- **Adjustable columns**: Drag column borders to resize as needed

#### 3. Session Management
- **Single session**: Only one login session at a time
- **Automatic logout**: Closing the main window ends the session
- **Secure exit**: Proper resource cleanup on application closure

## Troubleshooting Guide

### Common Issues and Solutions

#### Issue 1: "Java not found" error
**Solution:**
- Ensure Java is properly installed
- Check that Java is in your system PATH
- For Windows, restart the command prompt after installing Java

#### Issue 2: Login fails even with correct credentials
**Solution:**
- Verify username and password are entered correctly
- Check for caps lock or keyboard layout issues
- Ensure no extra spaces in the username/password fields

#### Issue 3: No tags displayed in the table
**Solution:**
- Click the **Refresh Tags** button
- Check internet connection (if using real database backend)
- Verify simulator is functioning correctly

#### Issue 4: "Connection to server ETOUR interrupted" error
**Solution:**
- This is a simulated interruption for demonstration
- Wait a moment and click **Refresh Tags** again
- The system automatically reconnects

#### Issue 5: Slow performance during bulk deletion
**Solution:**
- This is by design to demonstrate progress tracking
- Real-world implementations would be much faster
- Progress bar shows current status during operation

### Error Messages Reference

| Error Message | Meaning | Action Required |
|--------------|---------|-----------------|
| "Validation Error" | Missing username or password | Enter both credentials |
| "Login Failed" | Incorrect username or password | Verify credentials and retry |
| "System Error" | Internal authentication error | Restart application |
| "Connection Error" | Cannot connect to server | Check network connection |
| "No Selection" | No tags selected for deletion | Select at least one tag |
| "Deletion Error" | Error during tag deletion | System will retry automatically |

## Security Considerations

### Authentication Security
- Passwords are hashed using SHA-256 (production should use bcrypt)
- Secure comparison method prevents timing attacks
- No plaintext passwords stored in memory

### Session Security
- Single authenticated session per login
- Automatic logout on window close
- No session persistence between runs

### Data Protection
- Simulated data for demonstration purposes
- Production implementation should include proper database security
- Connection interruption simulation for robustness testing

## Simulator Information

### Simulated Components
The DeleteTag System includes several simulated components for demonstration:

1. **Database Simulator**: Simulates connection to "server ETOUR"
   - Returns simulated tag data
   - Simulates connection interruptions periodically
   - Demonstrates error handling capabilities

2. **Authentication Simulator**: Simulates user authentication
   - Three test accounts by default
   - Secure password hashing demonstration
   - Can be extended for real authentication

### Simulator Behavior
- **Connection interruptions**: Every 10th connection attempt fails
- **Sample data**: 8 pre-defined sample tags
- **Deletion simulation**: Console output shows deletion progress

## Extending the System

### Adding Real Database Connectivity
To convert this from simulation to production:

1. Replace `DatabaseConnection` class with real JDBC connection
2. Update `TagService.getAllTags()` to query real database
3. Implement proper transaction handling in `deleteTag()`
4. Add connection pooling for performance

### Enhancing Authentication
For production deployment:

1. Replace SHA-256 with bcrypt for password hashing
2. Implement real user database/schema
3. Add password complexity requirements
4. Implement session tokens and timeouts

## Best Pract for Users

### 1. Tag Selection
- Review tags carefully before deletion
- Use the description field to verify each tag
- Consider making backups before bulk deletions

### 2. System Maintenance
- Close the application properly when finished
- Restart application if experiencing unusual behavior
- Keep Java runtime updated for security

### 3. Security Pract
- Never share login credentials
- Log out when leaving workstation unattended
- Report any suspicious system behavior

## Performance Tips

### Optimizing Operations
- For large tag sets, delete in smaller batches if needed
- Use the refresh function sparingly with large datasets
- Close other resource-intensive applications when using DeleteTag

### Memory Management
- Application designed for minimal memory footprint
- Automatic cleanup of authentication data
- Efficient GUI updates to prevent memory leaks

## Support and Feedback

### Getting Help
If you encounter issues not covered in this manual:

1. Check the troubleshooting section above
2. Verify all system requirements are met
3. Contact your system administrator

### Reporting Issues
When reporting issues, include:
- Exact error messages
- Steps to reproduce the issue
- Java version information
- Operating system details

## Version Information

**Current Version**: 1.0
**Release Date**: October 2024
**Java Compatibility**: Java 8 and higher
**License**: Demonstration/Educational Use

---

**Note**: This is a demonstration application. For production use, additional security measures, proper database integration, and comprehensive testing are required. The simulated components are for educational purposes to demonstrate the application flow and error handling capabilities.
```