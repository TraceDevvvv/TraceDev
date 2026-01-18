```markdown
# Class Register Details Viewer - Administrator Manual

## Overview
The **Class Register Details Viewer** is a comprehensive Java Swing application designed for school administrators to view and manage class attendance records with detailed individual student information. The application allows administrators to view attendance details organized by date, manage student status updates, submit justifications, and add disciplinary notes.

## Main Features

### 1. **Administrator Authentication**
- Secure login system with username/password validation
- Simulated authentication for demonstration purposes

### 2. **Class Register Navigation**
- Multiple class register selection (simulating the "ViewingElCoregistri" use case)
- Date-based view organization with automatic sorting (most recent first)

### 3. **Attendance Detail View**
- Tabular display of student attendance information including:
  - Student name
  - Attendance status (Present/Absent/Late)
  - Existing justifications
  - Disciplinary notes
- Focus on today's date by default

### 4. **Student Information Management**
- Update justification for student absences
- Add disciplinary notes for behavioral incidents
- Real-time data updates with immediate display refresh

### 5. **Session Management**
- Secure logout functionality
- SIMOS server connection interruption simulation (postcondition requirement)

## System Requirements

### Hardware Requirements
- Minimum: 2GB RAM
- Recommended: 4GB RAM or higher
- 500MB free disk space

### Software Requirements
- **Operating System**: Windows 10/11, macOS 10.14+, Linux with X11/Wayland
- **Java Runtime Environment**: Java 8 or later (JDK 11 recommended)
- **Display**: Minimum 1024x768 resolution

## Installation & Setup

### Step 1: Java Installation
1. Verify Java is installed by opening terminal/command prompt:
   ```bash
   java -version
   ```

2. If Java is not installed, download and install from:
   - [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) (for commercial use)
   - [OpenJDK](https://adoptium.net/) (open source)

### Step 2: Download Application
1. Create a project directory:
   ```bash
   mkdir ClassRegisterViewer
   cd ClassRegisterViewer
   ```

2. Save the provided Java file as `ViewDetailsSingleRegister.java`

### Step 3: Compilation
1. Compile the Java file:
   ```bash
   javac ViewDetailsSingleRegister.java
   ```

2. This will generate several class files:
   - `ViewDetailsSingleRegister.class`
   - `ClassRegister.class`
   - `StudentAttendance.class`

### Step 4: Run the Application
```bash
java ViewDetailsSingleRegister
```

## User Guide

### Starting the Application

1. **Launch the Program**
   ```bash
   java ViewDetailsSingleRegister
   ```

2. **Administrator Login**
   - Username: `admin`
   - Password: `admin123`
   - *Note: These are demonstration credentials only*

   ![Login Screen](image.png)

### Main Interface Tour

#### Section 1: Header
- **Title**: Displays selected class register name
- **Logout Button**: Top-right corner for session termination

#### Section 2: Date Selection
- **Date Dropdown**: Shows all available dates for the selected register
- Automatic sorting (newest dates at the top)
- Today's date selected by default (if available)

#### Section 3: Attendance Display
- Tabular view showing:
  ```
  Student            Status    Justification          Disciplinary Note
  ---------------------------------------------------------------------
  John Doe          present                           (empty)
  Jane Smith        late      traffic                 (empty)
  Bob Johnson       present                           (empty)
  ```

#### Section 4: Student Management Form
- **Student Dropdown**: Select student from class roster
- **Status Dropdown**: Current attendance status (Present/Absent/Late)
- **Justification Field**: Enter reason for absence/lateness
- **Disciplinary Note Field**: Add behavioral notes
- **Submit Buttons**: Apply changes to selected student

### Workflow Examples

#### Example 1: View Today's Attendance
1. Select today's date from dropdown
2. Review attendance status of all students
3. Identify any absences or late arrivals

#### Example 2: Update Student Justification
1. Select student from dropdown (e.g., "Jane Smith")
2. Enter justification in text field: "Doctor's appointment, 9:30-10:30 AM"
3. Click **Submit Justification**
4. Verify update in display table

#### Example 3: Add Disciplinary Note
1. Select student from dropdown
2. Enter note in disciplinary field: "Talking during test - warning issued"
3. Click **Submit Disciplinary Note**
4. Confirmation message appears

#### Example 4: Review Historical Data
1. Select different date from dropdown
2. Compare attendance patterns over time
3. Track student behavior trends

### Data Initialization
The application comes with pre-loaded sample data:

**Class A Roster:**
- John Doe
- Jane Smith  
- Bob Johnson

**Sample Dates:**
- Today
- Yesterday
- Two days ago

## Advanced Features

### 1. **Date Management**
- Dates automatically sorted in descending order
- Today's date highlighted by default
- Graceful handling of missing date data

### 2. **Form Validation**
- Student selection required before submission
- Empty field handling
- Invalid date prevention

### 3. **Real-time Updates**
- Data model updates immediately after form submission
- Display refresh without page reload
- Visual confirmation of changes

### 4. **Connection Management**
- Server connection state monitoring
- Proper logout with connection termination
- User confirmation for disconnection

## Troubleshooting

### Common Issues

**Issue 1: Java Not Found**
```
Error: Could not find or load main class ViewDetailsSingleRegister
```
**Solution:**
- Verify Java installation: `java -version`
- Ensure you're in the correct directory where .class files exist

**Issue 2: GUI Not Displaying**
```
No X11 DISPLAY variable was set
```
**Solution:**
- For Linux: Run with `-Djava.awt.headless=false`
- For remote sessions: Use X11 forwarding or VNC

**Issue 3: Login Failures**
```
Invalid username or password
```
**Solution:**
- Use demonstration credentials: admin/admin123
- Verify caps lock is off

### Debug Mode
Run with verbose output:
```bash
java -verbose:class ViewDetailsSingleRegister
```

## Security Notes

### Demo Environment Only
⚠️ **Important**: This is a demonstration application with:
- Hardcoded login credentials
- In-memory data storage (not persistent)
- No encryption for sensitive data

### Production Considerations
For production deployment, implement:
- Database persistence (MySQL/PostgreSQL)
- Encrypted password storage
- Session management
- Role-based access control
- HTTPS for network communication
- Audit logging

## Postconditions

The application satisfies the following use case postconditions:

1. ✅ **System displays details of a class register**
   - Complete attendance information
   - Organized by date
   - Detailed student status tracking

2. ✅ **Connection to interrupted SMOS server**
   - Logout function simulates server disconnection
   - User confirmation before termination
   - Connection state monitoring

## Best Pract

### Data Entry Tips
1. **Justifications**: Be specific and include supporting details
2. **Disciplinary Notes**: Include date, time, and specific behaviors
3. **Status Updates**: Update immediately after receiving information
4. **Regular Review**: Check all classes at least once per day

### Workflow Optimization
1. Start with today's date automatically selected
2. Use dropdown menus for consistency
3. Submit changes immediately after entry
4. Regularly logout to maintain security

## Support & Maintenance

### Regular Maintenance
- **Daily**: Check all class registers
- **Weekly**: Review historical data for patterns
- **Monthly**: Export data for backup

### Application Updates
1. Backup existing data before updates
2. Test new versions in development environment
3. Document all changes to workflow

### Technical Support
For technical issues, provide:
1. Java version information
2. Operating System details
3. Error messages or screenshots
4. Steps to reproduce the issue

## License & Attribution
This software is provided for educational and demonstration purposes. In a production environment, consult with legal counsel regarding data privacy regulations (FERPA, GDPR, etc.).

---

*Last Updated: Version 1.0 | For demonstration purposes only*
```