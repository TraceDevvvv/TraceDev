```markdown
# EnterAbsencesAdmin - User Manual

## Overview

EnterAbsencesAdmin is a Java Swing desktop application designed for school administrators to efficiently record and manage student attendance data. The application provides an intuitive interface for marking student absences, saving attendance records, and automatically notifying parents via email when their child is absent.

## System Requirements

### Hardware Requirements
- Processor: 1 GHz or faster
- RAM: 1 GB minimum (2 GB recommended)
- Storage: 50 MB of free disk space
- Display: 1024×768 screen resolution or higher

### Software Requirements
- **Operating System**: Windows 7+, macOS 10.10+, or Linux with GUI support
- **Java Runtime**: Java 8 or later (Java 11 recommended)
- **Optional**: Internet connection for email notifications

## Installation Guide

### Step 1: Install Java Runtime Environment (JRE)
If you don't have Java installed, follow these steps:

**Windows:**
1. Visit [Oracle Java Downloads](https://www.oracle.com/java/technologies/downloads/)
2. Download the latest JRE for Windows
3. Run the installer and follow the setup wizard
4. Verify installation by opening Command Prompt and typing: `java -version`

**macOS:**
1. Open Terminal
2. Install using Homebrew: `brew install --cask java`
3. Or download from Oracle website and install manually

**Linux (Ubuntu/Debian):**
```bash
sudo apt update
sudo apt install openjdk-11-jre
```

### Step 2: Download the Application
1. Download the following Java files:
   - `Main.java` (application entry point)
   - `AttendanceGUI.java` (main interface)
   - `Student.java` (student data model)
   - `EmailSender.java` (email notification handler)

2. Place all files in the same directory

### Step 3: Compile the Application
1. Open a terminal/command prompt
2. Navigate to the directory containing the Java files
3. Compile all Java files:
```bash
javac *.java
```

### Step 4: Run the Application
```bash
java Main
```

## Application Features

### 1. Main Interface
The main window consists of three main sections:
- **Date Selection Panel**: Top panel for selecting the attendance date
- **Student Attendance Table**: Central panel showing student list with attendance status
- **Control Panel**: Bottom panel with save button and status display

### 2. Date Selection
Choose from predefined date options:
- **Today**: Current system date
- **Yesterday**: Previous day
- **Specific Dates**: Pre-set dates (e.g., 2023-10-01, 2023-10-02)

**Note**: When you select a date, the system automatically:
- Loads any previously saved attendance data for that date
- Displays the student list with their attendance status

### 3. Student Attendance Management
The attendance table displays:
- **Student ID**: Unique identifier for each student
- **Student Name**: Full name of the student
- **Status**: Attendance status (editable)

To mark attendance:
1. Locate the student in the table
2. Click on the "Status" cell for that student
3. Type "Present" or "Absent" (case-sensitive)
4. Press Enter to confirm

### 4. Saving Attendance Data
To save attendance records:

1. Fill out the attendance status for all students
2. Click the **"Save Attendance"** button
3. The application will:
   - Save data locally
   - Attempt to send data to the server
   - Send email notifications to parents of absent students
   - Display success or error messages

### 5. Email Notifications
When students are marked as absent:
- The system automatically generates email notifications
- Emails are sent to the parent email address on file
- Notifications include:
  - Student name
  - Date of absence
  - Contact information for follow-up

**Feature Demonstration**: The application includes a simulation mode that displays email content in the console when actual email sending fails.

## User Guide

### Starting the Application
1. Ensure Java is properly installed (Step 1)
2. Navigate to the application directory
3. Run: `java Main`
4. The main attendance window will appear

### Recording Daily Attendance
1. **Select Date**: Choose the appropriate date from the dropdown menu
2. **Review Students**: The system loads the student list for that date
3. **Mark Attendance**: For each student, set status to "Present" or "Absent"
4. **Save Data**: Click "Save Attendance" to record the data
5. **Verify**: Check the status message at the bottom

### Handling Server Issues
If server connection issues occur:
1. The system displays warning: "Connection to the server was interrupted"
2. Attendance data is saved locally
3. You can retry by clicking "Save Attendance" again
4. Email notifications may be queued for later delivery

### Managing Multiple Dates
To work with different dates:
1. Simply select a different date from the dropdown
2. The system automatically switches contexts
3. Previously saved data for each date is retained

## Troubleshooting

### Common Issues

1. **Application won't start:**
   - Check Java installation: `java -version`
   - Ensure all Java files are in the same directory
   - Verify compilation: Run `javac *.java` before `java Main`

2. **Cannot edit attendance status:**
   - Ensure you're clicking in the "Status" column (third column)
   - Type exactly "Present" or "Absent"
   - Press Enter after typing

3. **Email notifications not working:**
   - Check internet connection
   - Verify email settings in EmailSender.java
   - Check console for error messages
   - The system will show simulated emails in console as fallback

4. **Date selection issues:**
   - Ensure system date is correct
   - Yesterday's date calculation is based on system time

### Error Messages and Solutions

- **"Error: SMOS server connection interrupted."**
  - The server connection failed
  - Data is saved locally - try saving again later

- **No emails appearing in console**
  - Check if any students are marked "Absent"
  - Verify Java has console output permissions

## Data Management

### Student Information
The application includes sample student data. To modify:

1. Open `AttendanceGUI.java`
2. Locate the `initializeStudents()` method
3. Edit or add new Student objects:
```java
studentList.add(new Student("S001", "Alice Johnson", "alice.parent@example.com"));
```

### Email Configuration
To configure real email sending:

1. Open `EmailSender.java`
2. Update SMTP settings:
```java
this.smtpHost = "your.smtp.server.com";
this.smtpPort = "587";
this.username = "your-email@domain.com";
this.password = "your-password";
this.useSSL = true; // or false based on server requirements
```

### Attendance Data Storage
- Data is stored in memory during the session
- Each save updates the internal attendance map
- To persist across sessions, implement file or database storage

## Best Pract

1. **Daily Routine**
   - Open application at start of school day
   - Select today's date
   - Record attendance throughout the day
   - Save before closing

2. **Data Verification**
   - Double-check marked absences
   - Verify email addresses are current
   - Review status messages after saving

3. **Backup Strategy**
   - Note that data is currently session-based
   - Consider implementing export functionality
   - Keep manual backup records if needed

## Security Considerations

1. **Access Control**
   - The application assumes administrator authentication is handled externally
   - In production, implement login system

2. **Data Privacy**
   - Student information should be protected
   - Email communications should be secure
   - Consider GDPR/COPPA compliance for email notifications

3. **Network Security**
   - Ensure secure SMTP connections
   - Use TLS/SSL for email transmission
   - Regular updates to Java security patches

## Extending the Application

### Integration with School Systems
To connect with existing systems:

1. **Database Integration**: Replace sample student list with database queries
2. **Authentication**: Add login screen with role-based access
3. **Real Email Service**: Configure with school's email server
4. **Report Generation**: Add attendance reporting features

### Additional Features
Potential enhancements:
- Export attendance reports (PDF/Excel)
- Bulk attendance updates
- Attendance history viewing
- Multiple class/grade support
- Automated daily email summaries

## Support and Contact

For technical support:
1. Check the README.md file for basic troubleshooting
2. Review Java documentation for Swing components
3. Consult with IT department for server integration

**Remember**: This is a demonstration application. For production use, additional security, data persistence, and integration features should be implemented.

---

*Last Updated: November 2023*
*Version: 1.0.0*
*Compatibility: Java 8+*
```