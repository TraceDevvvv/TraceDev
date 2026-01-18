```markdown
# DeleteDelay Administrator System - User Manual

## Overview

DeleteDelay is a Java Swing desktop application designed for administrators to manage student late entry records. The system allows administrators to view late entries by date, delete specific delays, and handle server connection interruptions while maintaining system stability.

## Main Features

### 1. **Secure Administrator Login**
- Authentication system with admin credentials
- Session management for secure operations
- Clear separation between user roles

### 2. **Date-Based Late Entry Management**
- View late entries organized by date
- Dynamic date selection from available records
- Visual display of student information including name, course, date, and reason

### 3. **Delay Deletion Operations**
- Select and delete individual late entries
- Confirmation dialogs to prevent accidental deletions
- Real-time UI updates after deletion

### 4. **Server Connection Management**
- Visual server status indicator (Connected/Disconnected)
- Automatic handling of SMOS server connection interruptions
- Manual reconnection capability
- Graceful degradation when server is offline

### 5. **Operation Interruption Support**
- Administrator-initiated operation interruption
- System remains on registry screen during interruptions (as per requirements)
- Clear feedback about interruption status

### 6. **Robust Error Handling**
- Comprehensive error messages
- Server connection failure recovery
- Input validation and user guidance

## System Requirements

### Software Prerequisites
1. **Java Development Kit (JDK) 8 or higher**
   - Download from: https://adoptium.net/
   - Verify installation: `java -version`

2. **Development Environment (Optional)**
   - IntelliJ IDEA, Eclipse, or VS Code with Java extensions
   - Or any text editor with Java support

### Hardware Requirements
- Minimum 2GB RAM
- 500MB free disk space
- Screen resolution: 1024x768 or higher

## Installation Guide

### Step 1: Install Java
```bash
# For Ubuntu/Debian
sudo apt update
sudo apt install openjdk-11-jdk

# For macOS (using Homebrew)
brew install openjdk@11

# For Windows
# Download and run the installer from https://adoptium.net/
```

### Step 2: Verify Java Installation
```bash
java -version
javac -version
```

### Step 3: Download the Application Files
Create a project directory and download the following Java files:
```
DeleteDelay/
├── main.java
├── databasesimulator.java
└── lateentry.java
```

### Step 4: Compile the Application
```bash
# Navigate to project directory
cd DeleteDelay

# Compile all Java files
javac main.java databasesimulator.java lateentry.java
```

### Step 5: Run the Application
```bash
# Run the main class
java Main
```

## How to Use the Application

### 1. **Login Screen**
- **Username**: Enter "admin" (default administrator username)
- **Password**: Enter "admin123" (default password)
- **Login Button**: Click to authenticate and access the main system

### 2. **Main Registry Screen**

#### Top Panel
- **Date Selection Dropdown**: Select a date to view corresponding late entries
- **Server Status Indicator**:
  - Green: SMOS server connected
  - Red: SMOS server disconnected

#### Center Panel - Late Entries List
- Displays late entries for selected date
- Each entry shows:
  - Student Name
  - Course
  - Date
  - Reason for delay
- **Placeholder Message**: Shows when no entries exist for selected date

#### Bottom Panel - Action Buttons

**First Row:**
- **Delete Selected Delay**: Removes selected late entry (requires selection)
- **Reconnect to SMOS Server**: Attempts to reconnect when server is offline (only enabled when disconnected)

**Second Row:**
- **Interrupt Operation**: Manually interrupts current operation (simulates administrator interruption)
- **Logout**: Logs out current administrator and returns to login screen

### 3. **Performing Operations**

#### Viewing Late Entries
1. Select a date from the dropdown menu
2. The list automatically updates with entries for that date
3. Scroll through entries using the scrollbar

#### Deleting a Delay
1. Select a late entry from the list by clicking on it
2. Click the "Delete Selected Delay" button
3. Confirm the deletion in the dialog box
4. The entry will be removed from both UI and simulated database

#### Handling Server Disconnections
When SMOS server connection is lost:
1. Server status indicator turns red
2. "Reconnect to SMOS Server" button becomes enabled
3. Delete operations are disabled
4. Click "Reconnect to SMOS Server" to attempt reconnection

#### Interrupting Operations
1. Click "Interrupt Operation" during any ongoing process
2. Confirm the interruption
3. System remains on registry screen (as per requirements)
4. All current operations are halted

## Sample Data

The application comes pre-loaded with sample late entries for testing:

| Student | Course | Date | Reason |
|---------|---------|------|---------|
| John Doe | Mathematics | 2023-10-01 | Traffic jam |
| Jane Smith | Physics | 2023-10-01 | Overslept |
| Bob Johnson | Chemistry | 2023-10-02 | Family emergency |
| Alice Brown | Mathematics | 2023-10-02 | Public transport delay |
| Charlie Wilson | History | 2023-10-03 | Car breakdown |
| David Lee | Computer Science | 2023-10-03 | Software bug |
| Emma Watson | Literature | 2023-10-04 | Book reading overtime |

## Troubleshooting

### Common Issues

#### 1. Application Won't Start
**Problem**: "Error: Could not find or load main class"
**Solution**: 
```bash
# Ensure you're in the correct directory
cd [your-project-folder]

# Recompile all files
javac *.java

# Run with correct class name
java Main
```

#### 2. Login Fails
**Problem**: Invalid credentials error
**Solution**:
- Use default credentials: username="admin", password="admin123"
- Check for typos
- Ensure Caps Lock is off

#### 3. No Data Displayed
**Problem**: Empty list or "No dates available"
**Solution**:
- Verify database simulator is populated
- Check server connection status
- Select different dates from dropdown

#### 4. Server Connection Issues
**Problem**: SMOS server shows as disconnected
**Solution**:
- Click "Reconnect to SMOS Server" button
- Wait for reconnection attempt (90% success rate simulated)
- If persistent, restart application

### Error Messages Guide

| Error Message | Cause | Solution |
|---------------|-------|----------|
| "Cannot load data: SMOS server disconnected" | Server connection lost | Click reconnect button |
| "Please select a late entry to delete" | No entry selected | Click on an entry before deleting |
| "Failed to delete delay. SMOS server connection interrupted" | Server disconnected during operation | Reconnect server and retry |
| "Operation interrupted by administrator" | Manual interruption triggered | Continue with other operations |

## Code Structure

### Main Classes

1. **Main** (`main.java`)
   - Primary application class
   - Manages GUI components and user flow
   - Handles login, session management, and screen transitions

2. **DatabaseSimulator** (`databasesimulator.java`)
   - Simulates database operations
   - Manages late entry storage and retrieval
   - Simulates SMOS server connection behavior

3. **LateEntry** (`lateentry.java`)
   - Data model for late entry records
   - Immutable object with getters
   - Contains: ID, student name, course, date, reason

### Key Components

#### GUI Layers
- **LoginPanel**: Authentication interface
- **RegistryPanel**: Main operational interface
- **LateEntryCellRenderer**: Custom display for late entries

#### Event Handlers
- Date selection changes
- Delete operations
- Server reconnection attempts
- Operation interruptions
- Logout functionality

## Security Notes

### Default Credentials
- **Username**: `admin`
- **Password**: `admin123`
- For production use, implement secure authentication system

### Session Management
- Single session per login
- Automatic logout on application close
- No persistent session storage

## Extending the Application

### Adding Real Database Connectivity
Replace `DatabaseSimulator` with:
1. JDBC connection to real database
2. Connection pooling
3. Prepared statements for security

### Adding New Features
1. **Batch Operations**: Multiple entry selection and deletion
2. **Search Functionality**: Filter entries by student name or course
3. **Statistics**: Reports on late entry patterns
4. **Export**: Export data to CSV or PDF

### Custom Authentication
Implement:
1. Database-backed user management
2. Password encryption
3. Role-based access control
4. Session timeout

## Support and Contact

### Getting Help
- Refer to this manual first
- Check error messages in the application
- Review the code comments for implementation details

### Reporting Issues
If you encounter bugs or have suggestions:
1. Note the exact error message
2. Document steps to reproduce
3. Check Java version compatibility

## License and Attribution

This application is a demonstration system for managing delayed entries. The code includes comprehensive error handling and simulates real-world scenarios including server connection interruptions.

---

**Note**: This is a demonstration application. For production use, implement proper security measures, database connectivity, and error logging.
```