```markdown
# Disciplinary Note Insertion System - User Manual

## Overview

The Disciplinary Note Insertion System is a Java-based desktop application designed for school administrators to manage and record disciplinary incidents. The system allows administrators to create disciplinary notes for students and automatically send email notifications to parents while maintaining proper workflow prerequisites.

### Key Features
- **Secure Administrator Login**: Protected access with credential validation
- **Prerequisite Verification**: Ensures proper workflow completion before note insertion
- **Disciplinary Note Creation**: Create comprehensive notes with student, teacher, date, and description fields
- **Automatic Email Notifications**: Sends notifications to parents via simulated email service
- **Database Integration**: Stores notes in an in-memory database for demonstration
- **Workflow Management**: Guides users through required prerequisite use cases

## System Requirements

### Hardware Requirements
- Minimum: 2GB RAM
- Recommended: 4GB RAM or more
- Storage: 100MB available disk space

### Software Dependencies
- **Java Development Kit (JDK)**: Version 11 or higher
- **Java Runtime Environment (JRE)**: For running the compiled application
- **Operating System**: Windows 10/11, macOS 10.15+, or Linux with graphical desktop environment

## Installation Guide

### Step 1: Install Java
1. Download and install JDK 11 or higher from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html) or use OpenJDK
2. Verify installation by opening terminal/command prompt and typing:
   ```bash
   java -version
   ```
   You should see version information for Java.

### Step 2: Download Application Files
1. Download all Java files from the project:
   - `Main.java` - Main application class
   - `Note.java` - Note data model
   - `EmailService.java` - Email notification service
   - `DatabaseService.java` - Database service
   - `UserAuthentication.java` - Authentication service
   - `RegistryScreen.java` - Registry screen simulation

2. Place all files in the same directory on your computer.

### Step 3: Compile the Application
1. Open terminal/command prompt in the directory containing the Java files
2. Compile all Java files:
   ```bash
   javac *.java
   ```
3. This will create `.class` files for each Java source file.

### Step 4: Run the Application
1. In the same directory, execute:
   ```bash
   java Main
   ```
2. The application will launch with a login screen.

## Using the Application

### 1. Administrator Login
- Launch the application to see the login dialog
- **Default Credentials**:
  - Username: `admin`
  - Password: `admin123`
- Enter credentials and click OK to proceed
- If credentials are invalid, you'll be prompted to retry or exit

### 2. Main Interface Components

#### Left Panel: Prerequisite Section
- **Status Display**: Shows required prerequisite use cases
- **SviewTetTingloregister Button**: Complete this use case first
- **ViewElonconote Button**: Complete this use case second
- Status indicators change from "Complete X" to "✓ X Completed" when done

#### Right Panel: Note Insertion Form
- **Student Name Field**: Enter the student's full name
- **Date Field**: Automatically populated with current date
- **Teacher Field**: Enter the teacher's name
- **Description Area**: Detailed description of the disciplinary incident
- **New Note Button**: Enabled after prerequisites are completed
- **Save Button**: Saves the note and triggers email notification

### 3. Workflow Steps

#### Step 1: Complete Prerequisites
1. Click **"Complete SviewTetTingloregister"**
   - A confirmation dialog appears
   - Button becomes disabled and shows checkmark
   
2. Click **"Complete ViewElonconote"**
   - A confirmation dialog appears
   - Button becomes disabled and shows checkmark
   
3. Success message appears: "All prerequisites completed!"

#### Step 2: Create New Note
1. Click **"New Note"** button
2. Form fields become enabled for input
3. Enter required information:
   - **Student Name** (required): Full name of the student
   - **Teacher** (required): Name of the reporting teacher
   - **Description** (required): Detailed incident description
   - **Date**: Pre-filled, cannot be modified

#### Step 3: Save Note and Send Notification
1. Click **"Save"** button
2. System validates all fields
3. If validation passes:
   - Note is saved to database
   - Email notification is sent to parent
   - Success message appears
   - System returns to registry screen
   - Form fields are cleared and disabled

### 4. Completion and Reset
- After saving a note, prerequisites are automatically reset
- You must complete prerequisites again for subsequent notes
- This ensures proper workflow for each disciplinary incident

## Application Components Explained

### Main Components

#### 1. Authentication System
- Validates administrator credentials
- Prevents unauthorized access
- Maintains login state during session

#### 2. Prerequisite Manager
- Ensures proper workflow sequence
- Prevents skipping required steps
- Provides clear status indicators

#### 3. Note Management
- **Note Class**: Data model storing student, date, teacher, and description
- **Database Service**: Simulated database for storing notes
- **Email Service**: Simulates sending notifications to parents

#### 4. GUI Interface
- User-friendly Swing-based interface
- Clear visual feedback and status indicators
- Form validation and error messaging

## Troubleshooting

### Common Issues

#### 1. Application Won't Start
**Symptoms**: No GUI appears, console shows errors
**Solutions**:
- Verify Java is installed: `java -version`
- Check all Java files are in the same directory
- Ensure compilation was successful: Look for `.class` files

#### 2. Login Fails
**Symptoms**: "Invalid credentials" message appears
**Solutions**:
- Use default credentials: username `admin`, password `admin123`
- Check for typos in username/password
- Credentials are case-sensitive

#### 3. Buttons Remain Disabled
**Symptoms**: Can't click "New Note" or input fields
**Solutions**:
- Complete both prerequisite use cases first
- Click both buttons in left panel until they show checkmarks
- Look for "All prerequisites completed!" message

#### 4. Save Button Doesn't Work
**Symptoms**: Clicking Save does nothing or shows error
**Solutions**:
- Fill all required fields (Student, Teacher, Description)
- Ensure fields aren't empty or contain only spaces
- Check for validation error messages

### Console Output Messages
The application writes status messages to console:
- `Database connection initialized` - Startup successful
- `Administrator 'admin' logged in successfully` - Login successful
- `Note saved to database` - Note persistence confirmed
- `Email notification sent to:` - Email delivery confirmation
- `Navigating back to registry screen...` - Workflow completion

## Design Principles

### Security
- Authentication required for all operations
- Simulated password validation
- Session management

### Usability
- Clear workflow guidance
- Visual status indicators
- Intuitive form layout
- Immediate feedback for actions

### Data Integrity
- Form validation before submission
- Required field enforcement
- Consistent date handling
- Simulated persistence

### Workflow Compliance
- Enforces prerequisite completion
- Maintains proper sequence
- Prevents skipping required steps
- Resets workflow for new operations

## Configuration Options

### Default Settings

| Setting | Value | Description |
|---------|-------|-------------|
| Admin Username | `admin` | Default login username |
| Admin Password | `admin123` | Default login password |
| Email Domain | `school.edu` | Parent email domain |
| Date Format | `yyyy-MM-dd` | Standard date display |

### Modifying Defaults
To modify default settings, edit the source code:

1. **Change Admin Credentials**:
   ```java
   // In UserAuthentication.java, modify login() method
   boolean isValidAdmin = "your_username".equals(username.trim()) && 
                         "your_password".equals(password);
   ```

2. **Change Email Domain**:
   ```java
   // In Note.java, modify getParentEmail() method
   return student.toLowerCase().replace(" ", ".") + ".parent@yourdomain.edu";
   ```

## Best Pract

### For Administrators
- Always complete prerequisites before creating notes
- Double-check student information before sending notifications
- Use clear, concise descriptions in notes
- Verify email addresses are accurate for parents

### For System Administrators
- Backup .java files before modifications
- Test all prerequisites after code changes
- Monitor console output for troubleshooting
- Ensure Java version compatibility

## Support

### Technical Support
- Check console error messages for diagnostics
- Verify Java installation and version
- Ensure sufficient system resources

### Development Support
- Source code is self-contained
- Clear comments explain key functionality
- Modular design for easy modification
- Simulated serv can be replaced with real implementations

## Future Enhancements

### Planned Features
- Real database integration (MySQL/PostgreSQL)
- Actual email sending via SMTP
- Multiple administrator accounts
- Note history and search functionality
- Report generation
- Parent email verification
- SMS notification options
- Mobile application interface

### Customization Points
- Database backend replacement
- Email service implementation
- Authentication provider integration
- UI theme customization
- Localization support
- Accessibility features

## Conclusion

The Disciplinary Note Insertion System provides a robust, user-friendly solution for managing school disciplinary incidents. By guiding administrators through a proper workflow and automating notification processes, it ensures consistent, professional handling of disciplinary matters while maintaining communication with parents.

The system's modular design allows for easy expansion and integration with existing school management systems, making it a valuable tool for educational institutions seeking to modernize their disciplinary tracking processes.

---

**Version**: 1.0  
**Last Updated**: [Current Date]  
**Contact**: School Administration Department  
**Support Hours**: Monday-Friday, 8:00 AM - 4:00 PM  
**Emergency Support**: Available for critical system issues
```