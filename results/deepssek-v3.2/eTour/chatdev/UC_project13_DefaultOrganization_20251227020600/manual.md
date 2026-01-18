```markdown
# Tourist Account Manager - User Manual

## Overview

Tourist Account Manager is a Java Swing-based desktop application that allows agency operators to manage tourist accounts by activating or deactivating them. The application simulates interactions with an ETOUR server, providing a realistic experience of handling account status changes while maintaining connection awareness.

## Main Features

### 1. **Account Status Management**
- View current status of tourist accounts (Active/Inactive)
- Activate inactive tourist accounts
- Deactivate active tourist accounts
- Real-time status updates with color-coded indicators

### 2. **Server Connection Management**
- Connect to simulated ETOUR server
- Disconnect from server (disables account management features)
- Automatic disconnection handling during server errors
- Manual reconnection capability

### 3. **Operation Logging**
- Detailed timestamped logs of all operations
- Success and failure notifications
- Connection status updates
- User operation confirmations

### 4. **Data Persistence**
- Simulated tourist database with sample data
- In-memory storage of tourist accounts
- Real-time status updates without external database dependencies

## System Requirements

### Hardware Requirements
- 1 GHz or faster processor
- 512 MB RAM minimum (1 GB recommended)
- 100 MB available disk space

### Software Requirements
- **Operating System**: Windows 10+, macOS 10.13+, or Linux (any modern distribution)
- **Java Runtime Environment**: Java 8 or higher
- **Screen Resolution**: 1024x768 minimum

## Installation Guide

### Step 1: Install Java Runtime Environment
1. Visit the official Oracle Java website: https://www.oracle.com/java/technologies/javase-downloads.html
2. Download the appropriate Java Development Kit (JDK) for your operating system
3. Install the JDK following the platform-specific instructions
4. Verify installation by opening a terminal/command prompt and typing:
   ```bash
   java -version
   ```
   You should see Java version information displayed.

### Step 2: Download the Application
1. Ensure you have both source code files:
   - `TouristAccountGUI.java`
   - `Tourist.java`
2. Place both files in the same directory
3. Create a package directory structure (optional but recommended):
   ```
   com/agency/touristmanager/
   └── TouristAccountGUI.java
   └── Tourist.java
   ```

### Step 3: Compile the Application
Open a terminal/command prompt, navigate to the source directory, and run:

**For Windows:**
```cmd
javac com/agency/touristmanager/TouristAccountGUI.java com/agency/touristmanager/Tourist.java
```

**For macOS/Linux:**
```bash
javac com/agency/touristmanager/TouristAccountGUI.java com/agency/touristmanager/Tourist.java
```

### Step 4: Run the Application
```bash
java com.agency.touristmanager.TouristAccountGUI
```

## Quick Start Guide

### 1. Launch the Application
After successful compilation, run the application. You'll see the main window with:
- Tourist selection dropdown (top-left)
- Current status display (top-right)
- Four action buttons
- Operation log area (bottom)

### 2. Initial Setup
The application comes pre-loaded with 5 sample tourist accounts:
- John Doe (T001) - Active
- Jane Smith (T002) - Inactive
- Alice Johnson (T003) - Active
- Bob Brown (T004) - Active
- Charlie Davis (T005) - Inactive

### 3. Select a Tourist
1. Click the dropdown menu labeled "Select Tourist:"
2. Choose a tourist from the list (e.g., "Jane Smith")
3. Observe the current status changes to reflect the selected tourist's account state
   - **Green text** indicates an active account
   - **Red text** indicates an inactive account

### 4. Change Account Status
#### To Activate an Account:
1. Select an inactive tourist (status shown in red)
2. Click the "Activate Account" button
3. Confirm the action in the dialog box
4. Watch for success confirmation in the log area

#### To Deactivate an Account:
1. Select an active tourist (status shown in green)
2. Click the "Deactivate Account" button
3. Confirm the action in the dialog box
4. Watch for success confirmation in the log area

### 5. Manage Server Connection
#### Disconnect from Server:
1. Click "Disconnect from Server"
2. Confirm disconnection
3. Observe that account management buttons become disabled
4. Note the disconnection message in logs

#### Reconnect to Server:
1. Click "Reconnect to Server"
2. Wait for connection establishment
3. Observe re-enabling of all controls
4. Note reconnection success message

## Detailed Usage Instructions

### Understanding the Interface

#### Main Window Components:
1. **Tourist Selection Area** (Top)
   - Dropdown: Select different tourist accounts
   - Status Display: Shows current account status with color coding

2. **Control Buttons** (Center)
   - **Activate Account**: Changes inactive account to active
   - **Deactivate Account**: Changes active account to inactive
   - **Disconnect from Server**: Ends connection to ETOUR server
   - **Reconnect to Server**: Restores connection to ETOUR server

3. **Operation Log** (Bottom)
   - Timestamped records of all actions
   - Success and error messages
   - Connection status updates

### Step-by-Step Workflow

#### Standard Operation:
1. **Select a Tourist**
   - Click on the dropdown and choose a tourist name
   - Verify the current status updates correctly

2. **Review Current Status**
   - Check if the account is Active (green) or Inactive (red)
   - Ensure this is the account you intend to modify

3. **Choose Action**
   - For inactive accounts: Click "Activate Account"
   - For active accounts: Click "Deactivate Account"

4. **Confirm Action**
   - Read the confirmation dialog carefully
   - Click "Yes" to proceed or "No" to cancel

5. **Verify Results**
   - Check the status display for updated status
   - Review the operation log for success/error messages

#### Server Connection Management:
1. **When to Disconnect**
   - Before system maintenance
   - When troubleshooting connectivity issues
   - When leaving the workstation unattended

2. **When to Reconnect**
   - After system maintenance is complete
   - When connectivity issues are resolved
   - When returning to active work

### Error Handling

#### Common Error Scenarios:

1. **Server Connection Lost During Operation**
   - Symptoms: Operation fails, controls become disabled
   - Solution: Click "Reconnect to Server" and retry the operation

2. **Attempting Operation While Disconnected**
   - Symptoms: Error message "Cannot perform operation. Disconnected from server."
   - Solution: Reconnect first, then perform the operation

3. **Operation on Already Desired State**
   - Symptoms: Log message "account is already active/inactive"
   - Solution: No action needed - account is already in desired state

4. **Reconnection Failure**
   - Symptoms: Error message "Failed to reconnect"
   - Solution: Wait and retry connection; check network if persistent

### Best Pract

#### For Agency Operators:
1. **Always verify** the tourist account before making changes
2. **Double-check** the current status before activating/deactivating
3. **Confirm actions** only when certain of the operation
4. **Monitor the log** for any error messages or warnings
5. **Disconnect** when away from the workstation for security

#### For System Administrators:
1. **Regularly back up** the tourist database
2. **Monitor connection logs** for unusual patterns
3. **Train operators** on proper disconnection procedures
4. **Test reconnection** processes periodically
5. **Update sample data** to reflect current tourist information

## Troubleshooting Guide

### Issue: Application Won't Start
**Symptoms:**
- Java not found error
- Class not found error
- Graphics initialization failure

**Solutions:**
1. Verify Java installation: `java -version`
2. Ensure both source files are in the package directory
3. Recompile both files: `javac *.java`
4. Check for graphics dependencies (should be included with Java)

### Issue: Buttons Don't Work
**Symptoms:**
- Controls are grayed out
- No response to clicks
- Error messages in console

**Solutions:**
1. Check if disconnected from server (reconnect if needed)
2. Verify tourist is selected from dropdown
3. Restart the application
4. Check Java version compatibility

### Issue: No Status Updates
**Symptoms:**
- Status remains "Unknown"
- No color changes in status display
- Dropdown selections don't reflect changes

**Solutions:**
1. Reselect a tourist from the dropdown
2. Check the operation log for error messages
3. Verify the simulated database initialization
4. Restart the application

### Issue: Connection Problems
**Symptoms:**
- Frequent disconnections
- Failed reconnection attempts
- Operation timeouts

**Solutions:**
1. Check network connectivity
2. Increase simulated delay times in code if needed
3. Modify failure probability constants in the source code
4. Ensure system meets minimum requirements

## Customization Options

### Modifying Sample Data
To change the default tourist accounts, edit the `initializeDatabase()` method in TouristAccountGUI.java:

```java
touristDatabase.put("T006", new Tourist("T006", "New Tourist Name", true));
```

### Adjusting Connection Settings
To modify server simulation behavior:

1. **Change delay times** (in milliseconds):
   - Operation delay: `Thread.sleep(500)` in `performStatusChange()`
   - Disconnection delay: `Thread.sleep(300)` in `disconnectFromServer()`
   - Reconnection delay: `Thread.sleep(500)` in `reconnectToServer()`

2. **Modify failure probabilities**:
   - Operation failure: `Math.random() < 0.1` in `performStatusChange()`
   - Reconnection failure: `Math.random() < 0.15` in `reconnectToServer()`

### UI Customization
To change the appearance:

1. **Colors**: Modify `Color.GREEN.darker()` and `Color.RED.darker()` in `updateStatusDisplay()`
2. **Fonts**: Adjust font settings in GUI component initializations
3. **Window Size**: Change parameters in `setSize(700, 450)`
4. **Layout**: Modify `GridLayout` and `BorderLayout` parameters

## Security Considerations

### Data Protection
1. The application uses simulated in-memory data (not persistent)
2. No sensitive tourist information is stored permanently
3. All operations are logged with timestamps for audit trails
4. Connection states are clearly indicated to prevent unintended operations

### Access Control
1. Only one operator session at a time (single instance application)
2. Explicit confirmation required for all status changes
3. Automatic disconnection on server errors prevents incomplete operations
4. Manual reconnection required after intentional disconnections

### Best Security Pract
1. **Always disconnect** when leaving the workstation
2. **Never share** login credentials (if authentication were implemented)
3. **Regularly review** operation logs for unauthorized actions
4. **Keep Java updated** to latest security patches

## Performance Tips

### Optimal Usage
1. **Batch operations**: Process multiple accounts during single connection sessions
2. **Monitor connections**: Reconnect only when necessary to reduce overhead
3. **Use logs effectively**: Filter and search logs for specific operations
4. **Regular maintenance**: Restart application periodically for fresh sessions

### Resource Management
1. The application is memory-efficient for up to thousands of tourist accounts
2. Log area auto-scrolls and doesn't store unlimited history
3. Connection simulations are lightweight and won't impact system performance
4. GUI updates are optimized to prevent flickering and ensure smooth operation

## Frequently Asked Questions

### Q1: Can I use this with a real database?
**A**: The current version uses simulated data. To connect to a real database, you would need to:
1. Replace the `HashMap` with a database connection
2. Implement proper database CRUD operations
3. Add connection pooling and error handling
4. Implement data backup procedures

### Q2: How do I add more tourists?
**A**: You can:
1. Modify the source code to add more sample data
2. Implement a file import feature (requires code modification)
3. Add a GUI for manual tourist entry (requires significant code changes)

### Q3: Is this application network-ready?
**A**: The application simulates network operations but isn't designed for actual network deployment. For production use with real servers, you would need to:
1. Replace simulated calls with actual HTTP/REST calls
2. Implement proper network error handling
3. Add authentication and authorization
4. Consider security measures like SSL/TLS

### Q4: Can multiple operators use this simultaneously?
**A**: The current design supports single-operator use. For multi-user support, you would need:
1. A client-server architecture
2. User authentication system
3. Concurrent access controls
4. Transaction management

### Q5: How do I backup the operation logs?
**A**: Currently, logs are only in-memory. To save logs:
1. Add a "Save Log" button to the interface
2. Implement file writing in the `log()` method
3. Consider automatic log rotation
4. Add log export functionality

## Support and Contact

For issues, questions, or suggestions:

1. **Check the logs** for error details
2. **Review this manual** for troubleshooting steps
3. **Examine source code** comments for implementation details
4. **Contact development team** for critical issues

## Version History

- **Version 1.0** (Current)
  - Initial release with complete tourist account management
  - Simulated ETOUR server interactions
  - Comprehensive logging system
  - Connection management features
  - Java Swing GUI interface

## License and Terms

This software is provided for demonstration and educational purposes. Users are free to modify and extend the code according to their needs, subject to standard Java licensing terms.

---

**Note**: This is a demonstration application. For production use, additional features like persistent storage, user authentication, audit trails, and real server integration would be necessary.
```