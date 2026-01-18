# News Deletion System - User Manual

## Overview
The News Deletion System is a Java-based desktop application designed for agency operators to manage and delete news articles. The system implements a complete workflow based on the "DeleteNews" use case, including authentication, news viewing, selection, confirmation, and deletion operations.

## System Requirements
- **Java Development Kit (JDK)**: Version 8 or higher
- **Memory**: Minimum 512MB RAM
- **Disk Space**: 50MB free space
- **Operating System**: Windows, macOS, or Linux

## Installation Instructions

### Step 1: Install Java
1. Download and install the latest JDK from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html) or use [OpenJDK](https://openjdk.java.net/)
2. Verify installation by opening a terminal/command prompt and typing:
   ```bash
   java -version
   ```
3. Ensure the output shows Java version 8 or higher

### Step 2: Set Up Project Structure
Create the following directory structure:
```
NewsDeletionSystem/
├── src/
│   └── com/
│       └── chatdev/
│           └── newsapp/
│               ├── NewsApp.java
│               ├── News.java
│               ├── DatabaseManager.java
│               ├── NewsService.java
│               ├── NewsDeletionGUI.java
│               ├── ServerConnectionException.java
│               ├── AuthenticationService.java
│               ├── SessionManager.java
│               └── LoginGUI.java
└── README.md
```

### Step 3: Compile the Application
1. Navigate to the `NewsDeletionSystem/` directory in your terminal
2. Compile all Java files:
   ```bash
   javac -d bin src/com/chatdev/newsapp/*.java
   ```

### Step 4: Run the Application
1. From the `NewsDeletionSystem/` directory, run:
   ```bash
   java -cp bin com.chatdev.newsapp.NewsApp
   ```

## Main Features

### 1. Authentication System
- **Secure Login**: Agency operator login with validation
- **Session Management**: Automatic session handling
- **Auto-logout**: Security enforcement on session expiration

### 2. News Management Interface
- **News Listing**: View all news articles in a tabular format
- **News Details**: Display complete information for selected news
- **Real-time Updates**: Refresh news list functionality

### 3. Deletion Workflow
- **Selection**: Choose news from the list
- **Confirmation**: Double-confirmation before deletion
- **Status Feedback**: Real-time operation status
- **Error Handling**: Connection failure and validation handling

### 4. System Operations
- **Operation Cancellation**: Cancel deletion at any point
- **Logout Function**: Secure logout with confirmation
- **Exit Management**: Proper application termination

## How to Use the Application

### Starting the Application
1. Launch the application by running `NewsApp`
2. The login screen will appear automatically

### Login Process
1. **Enter Credentials**:
   - Username: `agency_operator`
   - Password: `secure123`
2. Click "Login" button
3. If credentials are incorrect, error messages will guide you
4. Successful login redirects to the main news deletion interface

### Main Interface Navigation

#### Top Panel
- **User Information**: Displays logged-in username
- **Logout Button**: Secure logout option (top-right)

#### News List Panel (Left)
- **Table View**: Shows all news articles with ID, Title, Author, and Date
- **Selection**: Click any row to select news
- **Refresh Button**: Updates the list with current data

#### News Details Panel (Right)
- **Details Display**: Shows complete information of selected news
- **Status Bar**: Shows operation status and messages

#### Control Panel (Bottom)
- **Delete Button**: Activates deletion workflow (enabled after selection)
- **Refresh Button**: Reloads news list
- **Cancel Button**: Cancels current operation

### Deleting News - Step-by-Step

#### Step 1: Activate Deletion Function
The deletion function is always available when logged in.

#### Step 2: View All News
- All news articles automatically load in the table
- Browse through the list to find the news to delete

#### Step 3: Select News
1. Click on any news item in the table
2. News details automatically appear in the right panel
3. The "Delete Selected News" button becomes enabled

#### Step 4: Confirm Selection
1. Review the selected news details in the right panel
2. Ensure this is the correct news to delete
3. Click the "Delete Selected News" button

#### Step 5: Confirmation Dialog
1. A confirmation dialog appears with news details:
   - Shows ID and Title of selected news
   - Warns that the action cannot be undone
2. Options:
   - **Yes**: Proceed with deletion
   - **No**: Cancel deletion

#### Step 6: Deletion Process
1. If confirmed, the system attempts to delete the news
2. Possible outcomes:
   - **Success**: News is removed from the system
   - **Failure**: Error message with reason
   - **Connection Error**: Server connection interruption message

### Operation Cancellation
At any point, you can:
1. Click "Cancel Operation" button to abort current action
2. Deselect news from the table
3. Press ESC key in confirmation dialogs

### Logout Procedure
1. Click "Logout" button in top-right corner
2. Confirm logout in the dialog
3. System returns to login screen

### Exit Application
1. Click the window close button (X)
2. Confirm exit in the dialog
3. System performs clean logout before closing

## Database Information

### Sample News Data
The system comes pre-loaded with 5 sample news articles:
1. System Maintenance (ID: 1)
2. New Features Released (ID: 2)
3. Security Update (ID: 3)
4. Holiday Schedule (ID: 4)
5. Training Session (ID: 5)

### Connection Simulation
The system simulates:
- **Server Connection**: 10% chance of simulated connection failure
- **Real-time Updates**: Immediate reflection of deletions
- **Data Persistence**: Data resets on application restart

## Error Handling

### Common Errors and Solutions

#### 1. Connection Error
**Message**: "Connection to server ETOUR interrupted"
**Solution**: 
- Check network connection
- Wait 30 seconds and retry
- Contact system administrator

#### 2. Authentication Error
**Message**: "Access denied: Agency operator must be logged in"
**Solution**:
- Return to login screen
- Re-enter credentials
- Check session timeout

#### 3. News Not Found
**Message**: "News with ID X does not exist"
**Solution**:
- Refresh the news list
- Verify the news ID
- Check if news was already deleted

#### 4. Invalid Selection
**Message**: "Please select a news item to delete"
**Solution**:
- Click on a news item in the table
- Ensure a row is highlighted

## Keyboard Shortcuts
- **Enter**: Default button action in dialogs
- **ESC**: Cancel dialog or operation
- **Tab**: Navigate between components
- **F5**: Refresh news list (indirect via button)

## Security Features

### Authentication
- Hardcoded agency operator credentials
- Input validation for username/password
- Session-based authentication

### Session Management
- Automatic session validation
- Session expiration handling
- Secure logout with cleanup

### Operation Security
- Confirmation for all deletions
- Operation cancellation option
- Clear audit trail through status messages

## Troubleshooting

### Application Won't Start
1. Verify Java installation: `java -version`
2. Check compilation: Ensure `bin` directory contains .class files
3. Verify classpath: Ensure running from correct directory

### GUI Display Issues
1. Check screen resolution: Minimum 1024x768 required
2. Verify Java version: Swing components require Java 8+
3. Restart application

### Performance Issues
1. Close unnecessary applications
2. Check available memory
3. Reduce number of concurrent operations

### Data Issues
1. Note: Data resets on application restart
2. Deleted news cannot be recovered
3. Sample data reloads automatically

## Quality Requirements Met

### Functional Requirements
- ✓ Complete deletion workflow implementation
- ✓ User authentication and session management
- ✓ News viewing and selection
- ✓ Confirmation mechanism
- ✓ Error handling for connection issues

### Usability Requirements
- ✓ Intuitive graphical interface
- ✓ Clear status messages
- ✓ Confirmation dialogs
- ✓ Operation feedback

### Security Requirements
- ✓ Authentication required
- ✓ Session validation
- ✓ Operation confirmation
- ✓ Secure logout

### Reliability Requirements
- ✓ Error handling for all operations
- ✓ Connection failure management
- ✓ Data validation
- ✓ Graceful degradation

## Technical Support
For technical support, contact:
- System Administrator: admin@etoursystem.com
- Technical Support: support@etoursystem.com
- Emergency Contact: +1-800-ETOUR-SYS

## Version Information
- **Version**: 1.0.0
- **Release Date**: October 2024
- **Java Version**: Compatible with JDK 8+
- **Author**: ChatDev Systems
- **License**: Proprietary

---

**Note**: This is a demonstration system. In production, modify authentication to use a secure database, implement proper encryption for passwords, and connect to a real database server instead of the in-memory simulation.