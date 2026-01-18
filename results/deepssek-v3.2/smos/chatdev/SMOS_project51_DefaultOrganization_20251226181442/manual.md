```markdown
# Student Registration Request Rejection System - User Manual

## 📋 Overview

The Student Registration Request Rejection System is a Java-based desktop application that allows administrators to manage and reject student registration requests. This system implements the "RejectEnrollmentStudent" use case, providing an intuitive graphical interface for administrators to view pending requests and reject them as needed.

### Key Features
- **View Registration Requests**: See all pending student registration requests in a clear tabular format
- **Reject Requests**: Single-click rejection with confirmation dialog
- **Real-time Updates**: Automatic refresh after rejecting requests
- **SMOS Server Integration**: Handles server interruptions gracefully
- **Data Persistence**: In-memory storage simulating database operations
- **Error Handling**: Comprehensive error management and user feedback

## 🛠 System Requirements

### Hardware Requirements
- Processor: 1 GHz or faster
- RAM: 2 GB minimum (4 GB recommended)
- Disk Space: 50 MB free space
- Display: 1024x768 minimum resolution

### Software Requirements
- **Operating System**: Windows 7+, macOS 10.12+, or Linux with GUI support
- **Java Runtime Environment**: Java 8 or later (Java 11 recommended)
- **Permissions**: Standard user permissions required

## 📦 Installation

### 1. Install Java Runtime Environment (JRE)
If you don't have Java installed, download it from:

**Windows/macOS/Linux**: [Oracle Java Downloads](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)

Verify installation by opening a terminal/command prompt and typing:
```bash
java -version
```

You should see output similar to:
```
java version "11.0.15" 2022-04-19 LTS
Java(TM) SE Runtime Environment 18.9 (build 11.0.15+8-LTS-149)
Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.15+8-LTS-149, mixed mode)
```

### 2. Download Application Files
Download all required Java files to a single directory:
```
StudentRegistrationSystem/
├── RejectEnrollmentApp.java
├── AdminGUI.java
├── RegistrationRequest.java
├── RequestDAO.java
└── SMOSException.java
```

### 3. Compile the Application
Open a terminal/command prompt in the application directory and run:

```bash
# For all platforms
javac RejectEnrollmentApp.java AdminGUI.java RegistrationRequest.java RequestDAO.java SMOSException.java
```

This will create compiled `.class` files that can be executed.

## 🚀 Running the Application

### Starting the Application
Run the following command from the application directory:

```bash
java RejectEnrollmentApp
```

The application window will appear with the title "Student Registration Request Manager".

## 📖 User Guide

### Application Interface

#### Main Window Layout
```
┌─────────────────────────────────────────────────────────────────┐
│                Student Registration Request Manager             │
│  ┌─────────────────────────────────────────────────────────┐  │
│  │ Pending Student Registration Requests                   │  │
│  └─────────────────────────────────────────────────────────┘  │
│  ┌─────────────────────────────────────────────────────────┐  │
│  │ Request ID | Student Name | Student ID | Email | Date   │Reject││
│  │ REQ001     | John Smith   | S1001      | ...   | ...    │[Red Button]│
│  │ REQ002     | Jane Doe     | S1002      | ...   | ...    │[Red Button]│
│  │ ...        | ...          | ...        | ...   | ...    │[Red Button]│
│  └─────────────────────────────────────────────────────────┘  │
│  ┌─────────────────────────────────────────────────────────┐  │
│  │              [Refresh Requests]      [Exit]             │  │
│  └─────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────┘
```

### 1. View Registration Requests (Precondition)

When you start the application, the system automatically:
- Loads all pending registration requests
- Displays them in a table with the following columns:
  - **Request ID**: Unique identifier for each request
  - **Student Name**: Full name of the student
  - **Student ID**: Student's identification number
  - **Email**: Student's email address
  - **Request Date**: Date and time when the request was submitted
  - **Action**: Red "Reject" button for each request

**Note**: If no pending requests exist, you'll see an information message.

### 2. Reject a Registration Request

To reject a student's registration request:

1. **Locate the request** in the table
2. **Click the red "Reject" button** in the Action column for that student
3. **Confirm the rejection** in the dialog that appears:
   ```
   ┌─────────────────────────────────────┐
   │         Confirm Rejection           │
   ├─────────────────────────────────────┤
   │ Are you sure you want to reject     │
   │ registration request REQ001?        │
   │ This action cannot be undone.       │
   │                                     │
   │           [Yes]       [No]          │
   └─────────────────────────────────────┘
   ```
4. **Click "Yes"** to proceed with rejection or "No" to cancel

### 3. System Behavior After Rejection

When you reject a request, the system:

1. **Processes the rejection** (removes from pending requests)
2. **Attempts to sync with SMOS server** (External Student Management System)
3. **Displays the updated list** of pending requests

#### Successful Rejection
You'll see a success message:
```
┌──────────────────────┐
│       Success        │
├──────────────────────┤
│ Successfully rejected│
│ registration request │
│ REQ001               │
│                      │
│        [OK]          │
└──────────────────────┘
```

#### SMOS Server Interruption
Sometimes the SMOS server connection may be interrupted. In this case:
```
┌──────────────────────────────────────┐
│      SMOS Server Warning             │
├──────────────────────────────────────┤
│ Request rejected but SMOS server     │
│ connection was interrupted.          │
│ The rejection was successful, but    │
│ SMOS synchronization failed.         │
│ Error: [error message]               │
│                                      │
│              [OK]                    │
└──────────────────────────────────────┘
```

**Important**: The rejection is still recorded locally even if SMOS sync fails.

### 4. Additional Functions

#### Refresh Requests
- Click the **"Refresh Requests"** button to reload the list
- Useful if requests have been added externally
- Blue button at the bottom of the window

#### Exit Application
- Click the **"Exit"** button to close the application
- Red button at the bottom of the window
- All unsaved data will be lost (sample data reloads on restart)

### 5. Sample Data

The application comes with 5 sample pending requests for demonstration:

| Request ID | Student Name    | Student ID | Email                  | Status  |
|------------|-----------------|------------|------------------------|---------|
| REQ001     | John Smith      | S1001      | john@university.edu    | PENDING |
| REQ002     | Jane Doe        | S1002      | jane@university.edu    | PENDING |
| REQ003     | Bob Johnson     | S1003      | bob@university.edu     | PENDING |
| REQ004     | Alice Brown     | S1004      | alice@university.edu   | PENDING |
| REQ005     | Charlie Wilson  | S1005      | charlie@university.edu | PENDING |

**Note**: All rejected requests will disappear from the pending list but remain in the system with "REJECTED" status.

## ⚠️ Troubleshooting

### Common Issues and Solutions

#### 1. Application Won't Start
**Error**: "Could not find or load main class RejectEnrollmentApp"
- **Solution**: Ensure you're in the correct directory with all `.class` files
- **Solution**: Recompile all Java files: `javac *.java`

#### 2. Compilation Errors
**Error**: "Package javax.swing does not exist"
- **Solution**: Make sure you have Java Development Kit (JDK) installed, not just JRE

#### 3. GUI Display Issues
**Problem**: Buttons or text not displaying properly
- **Solution**: Update Java to the latest version
- **Solution**: Check display scaling settings on high-DPI monitors

#### 4. Button Not Responding
**Problem**: Clicking "Reject" button does nothing
- **Solution**: Ensure you're clicking the red button in the Action column
- **Solution**: Try refreshing the table with "Refresh Requests" button

### Error Messages

| Error Message | Meaning | Action Required |
|---------------|---------|-----------------|
| "Failed to start application" | Critical startup error | Check Java installation and file permissions |
| "Error loading requests" | Cannot load data | Check if all files are in the same directory |
| "Request not found" | Invalid request ID | Refresh the list or restart application |
| "Request already processed" | Trying to reject already processed request | No action needed, request is already handled |

## 🔧 Technical Details

### System Architecture
```
User Interface (AdminGUI)
       ↓
Business Logic (RequestDAO)
       ↓
Data Models (RegistrationRequest)
       ↓
Exception Handling (SMOSException)
```

### Data Flow
1. **Startup**: Application loads sample data into memory
2. **Display**: GUI shows pending requests from RequestDAO
3. **Action**: User clicks reject button
4. **Processing**: RequestDAO updates request status
5. **SMOS Sync**: Attempts to communicate with external server
6. **Update**: GUI refreshes to show remaining pending requests

### Persistence
- Uses in-memory storage (simulated database)
- Data is lost when application closes
- In a production environment, this would connect to a real database

## 📝 Use Case Implementation

The application follows the exact sequence specified in the requirements:

### Preconditions Met:
✅ The user has already done "View Registration Requests"  
✅ The user clicks on the "Reject" button associated with a registration request

### Events Sequence:
1. **System eliminates the registration request** - Request status changed to "REJECTED"
2. **System displays the list of registrations yet to be activated** - GUI refreshes automatically

### Postconditions Achieved:
✅ The user refused a request for registration to the system  
✅ The user interrupts the connection operation to the interrupted SMOS server (handled via SMOSException)

## 🔒 Security Considerations

### Data Protection
- All operations are local (no network transmission of sensitive data)
- No external database connections in demo version
- Sample data contains no real personal information

### User Authentication
- Demo version has no authentication (for demonstration purposes)
- Production version should include proper administrator authentication

## 📊 Logging and Monitoring

The system provides several types of feedback:

### Console Output
- Rejection processing messages
- Error details for debugging
- SMOS server interaction status

### User Interface Feedback
- Success/error dialogs
- Confirmation prompts
- Status messages
- Visual button states

## 🚨 Emergency Procedures

### Force Quit
If the application becomes unresponsive:
1. **Windows**: Ctrl+Alt+Delete → Task Manager → End Task
2. **macOS**: Command+Option+Esc → Force Quit
3. **Linux**: Ctrl+Alt+Delete or use system monitor

### Data Recovery
- No data recovery needed (sample data reloads on restart)
- Production version would require database backup procedures

## 🤝 Support

For assistance with this application:

### Quick Help
1. **Check this manual** for troubleshooting steps
2. **Verify Java installation** with `java -version`
3. **Ensure all files** are in the same directory

### Known Limitations
- Demo data resets on application restart
- No undo functionality for rejections
- No export/import features
- Limited to 5 sample requests in demo mode

## 🔄 Updates and Maintenance

### Checking for Updates
- Currently a standalone application
- Future versions may include auto-update features
- Check with system administrator for new versions

### Backup Recommendations
- No backup required for demo version
- Production version should have regular database backups

## 📄 License and Usage

This is a demonstration application built for the "RejectEnrollmentStudent" use case implementation. It's intended for educational and demonstration purposes.

### Usage Rights
- Free to use for educational purposes
- Modify and extend as needed
- Not for production use without proper security enhancements

---

*For additional questions or support, contact your system administrator or development team.*
```