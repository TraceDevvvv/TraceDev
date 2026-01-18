```markdown
# User Manual: Role Management Application

## Table of Contents
- [Introduction](#introduction)
- [System Requirements](#system-requirements)
- [Installation Instructions](#installation-instructions)
- [Application Features](#application-features)
- [Getting Started](#getting-started)
- [Detailed Usage Guide](#detailed-usage-guide)
- [Troubleshooting](#troubleshooting)
- [Technical Details](#technical-details)

## Introduction

The Role Management Application is a Java-based desktop software designed for system administrators to manage user roles within an organization. This application provides an intuitive graphical interface for assigning and removing roles to/from users, simulating a real-world user management system with SMOS server integration.

### Key Benefits
- **User-Friendly Interface**: Simple GUI that requires minimal training
- **Efficient Role Management**: Quickly assign or remove multiple roles simultaneously
- **Data Persistence**: Maintains user and role data throughout the session
- **Server Simulation**: Realistic SMOS server connection handling
- **Input Validation**: Prevents assignment of invalid roles

## System Requirements

### Minimum Requirements
- **Operating System**: Windows 10/11, macOS 10.15+, or Linux
- **Java Runtime**: Java 8 or higher
- **Memory**: 2 GB RAM minimum
- **Storage**: 100 MB free disk space
- **Screen Resolution**: 1024×768 minimum

### Recommended Requirements
- **Operating System**: Windows 11, macOS 12+, or Ubuntu 20.04+
- **Java Runtime**: Java 11 or higher
- **Memory**: 4 GB RAM or more
- **Storage**: 200 MB free disk space
- **Screen Resolution**: 1920×1080 or higher

## Installation Instructions

### Method 1: Using Pre-compiled JAR File

1. **Download the Application**
   ```bash
   # Download the latest release from your organization's repository
   wget https://your-company.com/releases/RoleManagementApp.jar
   ```

2. **Verify Java Installation**
   ```bash
   # Check if Java is installed
   java -version
   
   # If not installed, install Java:
   # Windows: Download from https://java.com
   # macOS: brew install openjdk
   # Ubuntu/Debian: sudo apt install default-jre
   ```

3. **Run the Application**
   ```bash
   java -jar RoleManagementApp.jar
   ```

### Method 2: Building from Source

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-org/role-management-app.git
   cd role-management-app
   ```

2. **Compile the Application**
   ```bash
   # Compile all Java files
   javac -d bin src/*.java
   
   # Create executable JAR
   jar cfe RoleManagementApp.jar RoleManagementApp -C bin .
   ```

3. **Run the Application**
   ```bash
   java -jar RoleManagementApp.jar
   ```

## Application Features

### Core Features
1. **User Selection**: Choose from existing users in the system
2. **Role Management**: View and modify user roles through checkboxes
3. **Real-time Updates**: Immediate feedback on role changes
4. **Server Connection Simulation**: Realistic SMOS server behavior
5. **Data Validation**: Ensures only valid roles can be assigned

### User Interface Components
- **Top Panel**: User selection dropdown
- **Center Panel**: Role management form with checkboxes
- **Bottom Panel**: Action buttons (Send, Reset, Exit)
- **Status Messages**: Informative popup dialogs

### Available Roles
The application includes five predefined roles:
- **Admin**: Full system access and configuration privileges
- **Editor**: Content creation and modification capabilities
- **Viewer**: Read-only access to system resources
- **Moderator**: Content moderation and user management
- **User**: Basic authenticated access

## Getting Started

### First Launch
1. **Start the Application**
   - Double-click `RoleManagementApp.jar` or run from command line
   - The main window will open automatically

2. **Initial Setup**
   - The application comes with two sample users:
     - `User123`: Initially has "Viewer" role
     - `AdminUser`: Initially has "Admin" and "Editor" roles

3. **Application Layout**
   ```
   ┌─────────────────────────────────┐
   │        Select User: [Dropdown]  │  ← Top panel
   ├─────────────────────────────────┤
   │ Managing roles for user: [User] │
   │                                 │
   │  [✓] Admin                      │
   │  [ ] Editor                     │
   │  [✓] Viewer                     │  ← Center panel
   │  [ ] Moderator                  │
   │  [ ] User                       │
   │                                 │
   ├─────────────────────────────────┤
   │  [Send]  [Reset]  [Exit]       │  ← Bottom panel
   └─────────────────────────────────┘
   ```

## Detailed Usage Guide

### Selecting a User
1. **Locate the User Selection Dropdown**
   - Found at the top of the application window
   - Shows all available users in the system

2. **Choose a User**
   - Click the dropdown arrow
   - Select the user you want to manage
   - The role checkboxes will automatically update to show current roles

### Managing Roles
1. **View Current Roles**
   - After selecting a user, checkboxes show current role assignments
   - Checked boxes = currently assigned roles
   - Unchecked boxes = not assigned

2. **Assigning New Roles**
   - Click on the checkbox next to any unassigned role
   - The checkbox will be marked with a check (✓)
   - Example: To assign "Editor" role, click the checkbox next to "Editor"

3. **Removing Existing Roles**
   - Click on the checkbox next to any currently assigned role
   - The check will be removed from the checkbox
   - Example: To remove "Viewer" role, click the checkbox next to "Viewer"

### Applying Changes (Send Button)
1. **Review Selected Roles**
   - Ensure all desired roles are checked
   - Verify no unwanted roles are selected

2. **Click the Send Button**
   - This applies all role changes to the selected user
   - A confirmation dialog appears showing:
     - Success message
     - Updated role list
     - SMOS server status

3. **Understand the Process**
   - The application validates all selected roles
   - User's role set is updated in memory
   - SMOS server connection is automatically interrupted (simulated)
   - Success message confirms the update

### Using the Reset Button
1. **Reset to Current State**
   - Clicking Reset reverts all checkboxes to the user's current roles
   - Use this to undo unsent changes

2. **Server Reconnection**
   - If the server was disconnected, Reset will reconnect it
   - A message confirms server reconnection

### Exiting the Application
1. **Safe Exit**
   - Click the Exit button to close the application
   - All changes sent via Send button are preserved
   - Unsent changes are discarded

2. **Alternative Exit Methods**
   - Click the close (X) button on the window title bar
   - Press `Alt+F4` (Windows/Linux) or `Cmd+Q` (macOS)

## Step-by-Step Examples

### Example 1: Assigning Multiple Roles to User123
1. Select `User123` from the dropdown
2. Current roles: Viewer (checked)
3. Click checkboxes for:
   - Admin
   - Editor
   - Moderator
4. Click **Send** button
5. Result: User123 now has Admin, Editor, Moderator, and Viewer roles
6. Confirmation dialog appears

### Example 2: Removing All Roles Except Viewer
1. Select `AdminUser` from the dropdown
2. Current roles: Admin, Editor (both checked)
3. Uncheck the Admin checkbox
4. Uncheck the Editor checkbox
5. Ensure Viewer checkbox is checked (if not, check it)
6. Click **Send** button
7. Result: AdminUser now has only Viewer role

### Example 3: Using Reset to Cancel Changes
1. Select any user and make several role changes
2. Before clicking Send, decide to cancel
3. Click **Reset** button
4. All checkboxes return to user's current roles
5. You can now make new changes or exit

## Troubleshooting

### Common Issues and Solutions

#### Issue: Application Won't Start
**Symptoms**: No window appears, error messages in console
**Solutions**:
1. Verify Java installation: `java -version`
2. Check JAR file integrity: Download fresh copy
3. Ensure sufficient memory: `java -Xmx1g -jar RoleManagementApp.jar`

#### Issue: No Users in Dropdown
**Symptoms**: Dropdown is empty or shows no options
**Solutions**:
1. Check data initialization in code
2. Restart application
3. Verify user data wasn't corrupted

#### Issue: Changes Not Saving
**Symptoms**: Roles revert after Send button
**Solutions**:
1. Ensure you selected a user first
2. Check for error messages in dialog boxes
3. Verify all selected roles are valid

#### Issue: Unexpected Server Messages
**Symptoms**: Server connection errors or unusual behavior
**Solutions**:
1. This is normal simulation - server interruption is expected behavior
2. Use Reset button to restore connection
3. Contact IT if real server issues occur

### Error Messages
- **"Please select a user first"**: No user selected from dropdown
- **"Failed to update roles"**: Invalid role selected or user not found
- **"SMOS server connection interrupted"**: Normal post-operation message

## Technical Details

### Architecture Overview
```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   GUI Layer     │───▶│  Service Layer  │───▶│   Data Layer    │
│   (Swing UI)    │    │  (Business Logic)│    │  (User/Role Models)│
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

### Key Classes
1. **RoleManagementApp**: Main application class
2. **User**: Represents a system user with role collection
3. **RoleManagementService**: Core business logic handler
4. **SMOSServerConnection**: Server simulation component

### Data Persistence
- In-memory storage during session
- Sample users preloaded at startup
- Role changes persist until application exit

### Security Notes
- This is a simulation application
- No real authentication or authorization
- For production use, implement proper security measures

## Support and Resources

### Getting Help
- **Internal Documentation**: Check company wiki
- **Technical Support**: Submit ticket to IT department
- **Training Materials**: Contact HR for administrator training

### Future Enhancements
Planned features for future releases:
- Database integration for persistent storage
- Role hierarchies and inheritance
- Bulk user operations
- Audit trail logging
- Enhanced security features

### Version Information
- Current Version: 1.0
- Release Date: October 2023
- Java Version: Compatible with JDK 8+
- License: Company Internal Use Only

---

**Note**: This application is for simulation and training purposes. Always follow your organization's security policies and procedures when managing real user roles in production systems.
```