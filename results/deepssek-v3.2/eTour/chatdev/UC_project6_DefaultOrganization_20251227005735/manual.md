```markdown
# Refreshment Point Deletion System - User Manual

## Overview

The Refreshment Point Deletion System is a Java-based desktop application designed for agency operators to manage and delete refreshment points from the ETOUR cultural heritage management system. This application implements the "DeleteRefreshmentPoint" use case, providing a secure and intuitive interface for agency operators to perform deletion operations after proper authentication.

## Main Functions

### 1. Secure Operator Authentication
- **Purpose**: Ensures only authorized agency operators can access the system
- **Features**:
  - Username and password authentication
  - Secure credential validation
  - Session management

### 2. Refreshment Point Management
- **Purpose**: View and manage all refreshment points retrieved from the SearchCulturalHeritage use case
- **Features**:
  - Display refreshment points in a tabular format
  - Show comprehensive details (ID, Name, Location, Type, Description)
  - Single-point selection capability

### 3. Point Deletion Workflow
- **Purpose**: Safely delete refreshment points following the specified use case flow
- **Features**:
  - **Step 1**: View list and select point for deletion
  - **Step 2**: Confirmation dialog with point details
  - **Step 3**: Deletion confirmation with 'Yes/No' option
  - **Step 4**: Actual deletion with system validation
  - **Exit Condition**: Success notification upon completion

### 4. System Validation
- **Purpose**: Ensure system integrity and handle edge cases
- **Features**:
  - Server connection verification (ETOUR interruption handling)
  - Operation cancellation support
  - Error handling and user feedback
  - Graceful failure recovery

### 5. User Interface
- **Purpose**: Provide an intuitive and responsive GUI
- **Features**:
  - Modern, system-native look and feel
  - Real-time status updates
  - Clear visual indicators
  - Responsive table with selection highlighting

## System Requirements

### Hardware Requirements
- **Processor**: 1 GHz or faster
- **Memory**: 512 MB RAM (1 GB recommended)
- **Storage**: 50 MB available space
- **Display**: 1024x768 resolution or higher

### Software Requirements
- **Operating System**: Windows 7+, macOS 10.10+, or Linux with GUI
- **Java Runtime**: Java 8 or later
- **Network**: Internet connection for server communication (simulated)

## Installation Guide

### Step 1: Install Java Development Kit (JDK)
1. Visit [Oracle JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or [OpenJDK](https://openjdk.java.net/)
2. Download the appropriate JDK for your operating system
3. Follow the installation instructions for your platform
4. Verify installation by opening a terminal/command prompt and typing:
   ```bash
   java -version
   ```

### Step 2: Download the Application Files
Create a project directory and save all provided Java files:
```
refreshment-point-deletion-system/
├── Main.java
├── RefreshmentPoint.java
├── RefreshmentPointDAO.java
├── MainFrame.java
├── LoginDialog.java
└── AuthenticationService.java
```

### Step 3: Compile the Application
1. Open a terminal/command prompt
2. Navigate to the project directory:
   ```bash
   cd path/to/refreshment-point-deletion-system
   ```
3. Compile all Java files:
   ```bash
   javac *.java
   ```

This will generate class files for each Java source file.

## How to Use the Application

### Starting the Application
1. In your terminal, navigate to the project directory
2. Run the application:
   ```bash
   java Main
   ```

### Login Procedure
1. **Login Dialog** appears on startup
2. Enter credentials:
   - **Username**: `agency_operator`
   - **Password**: `secure_pass_123`
3. Click "Login" button
4. Successful login displays welcome message
5. Cancel button exits the application

### Main Application Interface

#### Understanding the Layout
- **Top**: Application title and header
- **Center**: Refreshment points table displaying:
  - ID (unique identifier)
  - Name (refreshment point name)
  - Location (physical address)
  - Type (cafe, restaurant, kiosk, etc.)
  - Description (additional details)
- **Bottom**: Control buttons and status area

#### Navigation Shortcuts
- **Tab**: Navigate between controls
- **Enter**: Select/activate focused button
- **Arrow Keys**: Navigate table selection
- **Space**: Select/deselect rows

### Performing Deletions

#### Step-by-Step Deletion Process

1. **Select a Refreshment Point**:
   - Click on any row in the table
   - Selected row highlights in blue
   - Delete button becomes enabled

2. **Initiate Deletion**:
   - Click "Delete Selected Point" button
   - Or press Enter when button is focused

3. **Confirmation Dialog**:
   - System displays point details for verification
   - Shows ID and name of selected point
   - Warning message about irreversible action

4. **Confirm or Cancel**:
   - **Yes**: Proceed with deletion
   - **No**: Cancel the operation
   - **X/Close**: Cancel the operation

5. **Server Validation**:
   - System checks server connection
   - If connection fails: Error message displayed
   - If connection succeeds: Proceed to deletion

6. **Deletion Result**:
   - **Success**: Green confirmation message
   - **Failure**: Red error message with details
   - Table automatically refreshes to show updated list

### Additional Operations

#### Canceling an Operation
1. At any point, click "Cancel Operation" button
2. Clears current selection
3. Resets to ready state

#### Viewing Status Updates
- Status bar at bottom shows current operation state
- Messages include:
  - "Ready - Please select a refreshment point to delete"
  - "Point selected. Click 'Delete Selected Point' to proceed"
  - "Operation cancelled by operator"
  - "Deletion failed: Server connection interrupted"
  - "Refreshment point deleted successfully"

### Sample Refreshment Points

The system comes pre-loaded with sample data:
1. **City Cafe** - Main Street 123 (Cafe)
2. **Museum Restaurant** - Cultural District 45 (Restaurant)
3. **Park Refreshment Stand** - Central Park (Kiosk)
4. **Heritage Snack Bar** - Old Town Square (Snack Bar)
5. **Tourist Center Cafe** - Tourist Info Center (Cafe)

## Troubleshooting

### Common Issues and Solutions

#### 1. Application Won't Start
**Problem**: "Error: Could not find or load main class Main"
- **Solution**: Ensure you're in the correct directory with all .class files
- Command: `dir *.class` (Windows) or `ls *.class` (Mac/Linux)

#### 2. GUI Looks Strange
**Problem**: Interface uses old-style widgets
- **Solution**: System Look & Feel failed to load
- Application still functions correctly
- Check Java version compatibility

#### 3. Login Fails
**Problem**: "Invalid username or password" appears
- **Solution**: Verify credentials:
  - Username: `agency_operator` (exact)
  - Password: `secure_pass_123` (exact)
- Check for extra spaces in input

#### 4. Deletion Fails Randomly
**Problem**: "Connection Error" appears sometimes
- **Solution**: This is simulated server interruption
- Click OK and try the operation again
- Designed to test ETOUR interruption handling

#### 5. Table Not Showing Data
**Problem**: Empty table after login
- **Solution**: Close and restart application
- Verify RefreshmentPointDAO.java compiled correctly
- Check console for error messages

### Error Messages Reference

| Message | Meaning | Action Required |
|---------|---------|-----------------|
| "Cannot connect to server" | Simulated ETOUR interruption | Retry operation |
| "Point not found in system" | Data consistency issue | Restart application |
| "System error occurred" | Unexpected exception | Check console for details |

## Security Features

### Authentication System
- **Hardened Credentials**: Pre-configured operator credentials
- **Session Isolation**: Each instance requires fresh login
- **Secure Disposal**: Password field cleared on failed attempts

### Data Protection
- **Read-Only Display**: Table prevents data modification
- **Validation**: All operations validated before execution
- **Confirmation Required**: No silent deletions

## Best Pract

### For Operators
1. **Verify Selection**: Always double-check selected point before deletion
2. **Read Confirmation**: Review point details in confirmation dialog
3. **Monitor Status**: Watch status bar for operation feedback
4. **Use Cancel**: Use cancel button to abort unintended operations

### For System Administrators
1. **Regular Backups**: Ensure data backups before bulk operations
2. **Access Control**: Restrict application to authorized personnel only
3. **Log Review**: Check console output for system errors
4. **Java Updates**: Keep Java runtime updated for security

## Technical Details

### Architecture
- **Model**: RefreshmentPoint (data representation)
- **DAO**: RefreshmentPointDAO (data access layer)
- **View**: Swing GUI components
- **Controller**: MainFrame action handlers

### Flow Implementation
The application follows the exact use case flow:
1. **Entry Condition**: Login authentication
2. **Step 1**: View list from SearchCulturalHeritage
3. **Step 2**: Confirmation request
4. **Step 3**: Deletion confirmation
5. **Step 4**: Point deletion
6. **Exit Condition**: Success notification
7. **Interruption Handling**: ETOUR connection checks
8. **Cancellation Support**: Operator cancellation option

### Network Simulation
- Server connectivity simulated with 90% success rate
- Random failures demonstrate interruption handling
- Real implementation would use actual network calls

## Support and Maintenance

### Getting Help
- Review this manual for common solutions
- Check console output for detailed error information
- Verify Java installation and version

### Application Updates
To update the application:
1. Replace individual Java files as needed
2. Recompile: `javac *.java`
3. Restart application

### Data Management
- Sample data resets on each application restart
- Production implementation would persist to database
- No permanent data modification in current version

## License and Compliance

### Usage Rights
- Intended for authorized agency operators only
- Requires proper authentication
- Logs should be maintained for audit purposes

### Compliance Notes
- Follows specified use case requirements
- Implements all quality requirements
- Handles edge cases as specified
- Maintains data integrity throughout operations

---

**Note**: This is a demonstration system. In production, replace hardcoded credentials with secure authentication and connect to actual ETOUR database serv.

For additional support or feature requests, contact your system administrator or development team.
```