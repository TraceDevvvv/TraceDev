```markdown
# Disciplinary Note Management System - User Manual

## Overview
The Disciplinary Note Management System is a Java-based desktop application designed for educational administrators to manage and delete disciplinary notes. The system follows a structured multi-window interface with secure authentication, note viewing, and deletion functionality that includes automatic parent notification.

## System Requirements
### Software Prerequisites
- **Java Development Kit (JDK)**: Version 8 or higher
- **Operating System**: Windows, macOS, or Linux
- **Memory**: Minimum 512MB RAM
- **Storage**: 10MB free disk space

### Java Environment Setup
1. **Check Java Installation**:
   ```bash
   java -version
   ```

2. **Install Java if needed**:
   - **Windows**: Download from [Oracle Java](https://www.oracle.com/java/technologies/downloads/)
   - **macOS**: Use Homebrew: `brew install openjdk`
   - **Linux**: Use package manager: `sudo apt-get install openjdk-11-jdk`

## Installation Steps

### Option 1: Using Compiled JAR File
1. Download the `DisciplinarySystem.jar` file
2. Run the application:
   ```bash
   java -jar DisciplinarySystem.jar
   ```

### Option 2: Compiling from Source
1. Ensure all Java files are in the same directory:
   - `Main.java`
   - `LoginFrame.java`
   - `RegistryFrame.java`
   - `NoteSystem.java`
   - `Note.java`

2. Compile all files:
   ```bash
   javac *.java
   ```

3. Run the application:
   ```bash
   java Main
   ```

## Application Features

### 1. Secure Administrator Login
**Purpose**: Implements the precondition that users must be logged in as administrators before performing any note operations.

**Key Features**:
- Username/password authentication
- Session management
- Automatic logout on system exit

**Default Credentials**:
- Username: `admin`
- Password: `admin123`

### 2. Note Registry Interface
**Purpose**: Main operational screen for viewing and managing disciplinary notes.

**Components**:
- **Note ID Input Field**: Enter the ID of the note to view
- **Load Button**: Load note details for review
- **Note Details Display**: Shows comprehensive note information
- **Delete Button**: Delete loaded note with parent notification
- **Logout Button**: Secure session termination

### 3. Note Management Operations
**Core Functions**:
- **Load Note Details**: View complete information about a disciplinary note
- **Delete with Notification**: Delete notes while automatically notifying parents
- **Note Archive**: Simulated database of disciplinary records

### 4. Parent Notification System
**Automated Process**: When a note is deleted:
1. System sends notification to parent's email and phone
2. Note is permanently removed from the archive
3. Success/failure feedback provided

## Step-by-Step Usage Guide

### Step 1: Launch Application
1. Run the compiled application
2. The login screen appears with username and password fields

### Step 2: Administrator Login
1. Enter `admin` in the username field
2. Enter `admin123` in the password field
3. Click "Login"
4. Successful login displays the Registry screen

### Step 3: View Note Details
1. In the Registry screen, enter a Note ID (e.g., `NOTE001`)
2. Click "Load Note Details"
3. System displays:
   - Note ID
   - Student Name
   - Description of incident
   - Date of incident
   - Parent contact information

### Step 4: Delete a Note
1. After loading a note, the "Delete Note" button becomes active
2. Click "Delete Note"
3. Confirmation dialog appears
4. If confirmed:
   - System sends notification to parents
   - Note is permanently deleted
   - Interface resets for next operation

### Step 5: Handle Interruptions
The system simulates real-world scenarios:
- **Administrator Interruption**: Operation may be canceled
- **Server Disconnection**: SMOS server connectivity issues
- Both scenarios are handled gracefully with user notification

### Step 6: Logout
1. Click "Logout" button
2. System terminates current session
3. Returns to login screen

## Error Handling

### Common Errors and Solutions
1. **Login Failed**:
   - Verify username/password (admin/admin123)
   - Check for caps lock

2. **Note Not Found**:
   - Verify Note ID exists (NOTE001 or NOTE002 in sample data)
   - Check for typos

3. **Delete Operation Failed**:
   - Ensure a note is loaded first
   - Check network connectivity simulation
   - Parent notification might have failed

4. **Session Expired**:
   - Re-login to continue operations
   - System maintains session validation

## Sample Data Structure

### Preloaded Notes
For testing purposes, the system includes:
- **NOTE001**: John Doe, "Late submission" (October 15, 2023)
- **NOTE002**: Jane Smith, "Class disruption" (October 20, 2023)

### Note Information Displayed
Each note shows:
- Unique identifier
- Student information
- Incident description
- Date of occurrence
- Parent contact details (email and phone)

## Technical Features

### Security Implementation
- Password masking in login screen
- Session-based authentication
- Operation authorization checks

### User Interface Design
- Clean, responsive layout
- Clear visual feedback
- Intuitive button placement
- Consistent dialog messaging

### System Architecture
- Model-View-Controller pattern
- Separation of data, business logic, and presentation
- Modular class design for maintainability

## Troubleshooting

### Compilation Issues
**Problem**: "Class not found" error
**Solution**: Ensure all .java files are in the same directory before compiling

**Problem**: "Unsupported class version" error
**Solution**: Update to Java 8 or higher

### Runtime Issues
**Problem**: Application won't start
**Solution**: Check Java installation with `java -version`

**Problem**: Buttons not responding
**Solution**: Ensure all components are properly compiled

### Functional Issues
**Problem**: Note deletion not working
**Solution**: 
1. Verify note is loaded (Delete button should be enabled)
2. Check parent notification simulation (90% success rate)

**Problem**: Can't find notes
**Solution**: Use exactly "NOTE001" or "NOTE002" (case-sensitive)

## Best Pract

### For Administrators
1. **Always log out** after completing operations
2. **Verify note details** before deletion
3. **Keep credentials secure** and change default password in production
4. **Check parent notifications** were sent successfully

### For System Maintenance
1. **Regular backups** of note archive
2. **Update contact information** for accurate notifications
3. **Monitor system logs** for error patterns
4. **Test interruption scenarios** regularly

## Support and Contact

### Getting Help
1. **Documentation**: Refer to this manual
2. **Console Output**: Check Java console for detailed logs
3. **Error Messages**: System provides descriptive error dialogs

### Features Not Included
- **Note creation/modification** (focus is on deletion use case)
- **Database persistence** (in-memory storage for demonstration)
- **Real SMOS server integration** (simulated notification system)
- **Advanced search capabilities** (basic ID lookup only)

## Future Enhancements
Potential improvements include:
- Database integration (MySQL, PostgreSQL)
- Real email/SMS notification serv
- Advanced search and filtering
- Report generation
- Multi-administrator support
- Audit logging

## Conclusion
The Disciplinary Note Management System provides a robust solution for educational institutions to manage disciplinary records securely and efficiently. By following this manual, administrators can effectively use all system features while maintaining proper data management pract.

---

**Note**: This is a demonstration system. For production use, implement proper security measures, database persistence, and real notification serv.
```