```markdown
# InsertJustification System - User Manual

## Overview

The InsertJustification System is a Java Swing application designed for administrators to manage and justify employee absences. The system provides a user-friendly graphical interface for logging in, viewing unjustified absences, and inserting justifications with proper validation and error handling.

## System Requirements

### Software Requirements
- **Java Runtime Environment (JRE)**: Version 8 or higher
- **Operating System**: Windows, macOS, or Linux
- **Memory**: Minimum 512MB RAM
- **Disk Space**: 10MB free space

### Development Requirements (For Developers)
- **Java Development Kit (JDK)**: Version 8 or higher
- **IDE**: Optional (Eclipse, IntelliJ IDEA, or NetBeans)

## Installation

### Step 1: Install Java
1. Download and install the latest Java Runtime Environment (JRE) from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html)
2. Verify installation by opening a terminal/command prompt and typing:
```bash
java -version
```

### Step 2: Download the Application
1. Download all Java source files:
   - `Main.java`
   - `LoginFrame.java`
   - `Absence.java`
   - `Justification.java`
   - `DatabaseSimulator.java`
   - `RegistryScreen.java`
   - `InsertJustificationForm.java`

### Step 3: Compile the Application
1. Open a terminal/command prompt
2. Navigate to the directory containing the source files
3. Compile all Java files:
```bash
javac *.java
```

### Step 4: Run the Application
```bash
java Main
```

## Getting Started

### Login Screen
1. Launch the application using the command above
2. The Login screen will appear with the following fields:
   - **Username**: Enter "admin"
   - **Password**: Enter "admin123"
3. Click "Login" to access the system

**Note**: The system comes with pre-configured sample data including three unjustified absences for testing purposes.

## Main Features

### 1. Absence Registry
- **Purpose**: View all unjustified absences in red
- **Access**: Automatically appears after successful login
- **Features**:
  - Displays absentees in a table format
  - Unjustified absences are highlighted in red
  - Each row shows: ID, Employee Name, Absence Date, Reason, and Status
  - Double-click any red-row absence to justify it

### 2. Insert Justification
- **Purpose**: Add justification for selected absences
- **Access**: Double-click an unjustified absence (red row) or click "Insert Justification" button
- **Features**:
  - Form with date and details fields
  - Date validation (cannot be in the future)
  - Required field validation
  - Save and cancel options

### 3. Database Simulation
- **Purpose**: Simulates database operations without requiring external databases
- **Features**:
  - Pre-loaded sample data
  - 10% chance of simulated server interruption for testing
  - In-memory storage of justifications

### 4. Session Management
- **Logout**: Ends current session and returns to login screen
- **Refresh**: Updates the absence registry view

## Using the Application

### Step-by-Step Workflow

#### Step 1: View Absences
1. After login, you'll see the Absence Registry screen
2. The table shows all unjustified absences marked in red
3. Review the list to identify absences requiring justification

#### Step 2: Select Absence
1. Locate the absence you want to justify (marked in red)
2. **Method 1**: Double-click the row
3. **Method 2**: Select the row and click "Insert Justification" button

#### Step 3: Fill Justification Form
1. The form opens with pre-filled current date
2. **Justification Date**: Enter date in YYYY-MM-DD format (cannot be future date)
3. **Justification Details**: Provide detailed explanation for the absence
4. **Validation**: Both fields are required

#### Step 4: Save Justification
1. Click "Save" to submit the justification
2. If successful:
   - Confirmation message appears
   - Returns to registry screen
   - Absence status changes from red to justified
3. If unsuccessful:
   - Error message explains the issue
   - Common issues: server interruption, invalid date, empty fields

#### Step 5: Return to Registry
1. After saving, the registry screen refreshes automatically
2. Justified absences no longer appear in red
3. You can:
   - Insert more justifications
   - Refresh the view
   - Logout

## Error Handling

### Common Error Messages and Solutions

1. **"Connection to the SMOS server interrupted"**
   - **Cause**: Simulated 10% chance of server interruption
   - **Solution**: Click "Save" again or try later

2. **"Invalid date format"**
   - **Cause**: Incorrect date format entered
   - **Solution**: Use YYYY-MM-DD format (e.g., 2024-01-15)

3. **"Please select an absence (in red) to justify"**
   - **Cause**: No row selected before clicking Insert Justification
   - **Solution**: Select an unjustified absence first

4. **"This absence is already justified"**
   - **Cause**: Trying to justify an already justified absence
   - **Solution**: Select a different unjustified absence

5. **"Justification date cannot be in the future"**
   - **Cause**: Entered a future date
   - **Solution**: Enter a past or current date

## Keyboard Shortcuts

| Action | Shortcut |
|--------|----------|
| Login | Enter (while in password field) |
| Cancel | Escape |
| Save Justification | Ctrl+S |
| Cancel Justification | Escape |
| Refresh Registry | F5 |
| Logout | Ctrl+L |

## Troubleshooting

### Application Won't Start
1. **Error**: "Could not find or load main class"
   - Solution: Ensure you're in the correct directory and compiled all files
2. **Error**: "UnsupportedClassVersionError"
   - Solution: Update to Java 8 or higher

### Login Issues
1. **Error**: "Invalid credentials"
   - Solution: Use username "admin" and password "admin123"
2. **Login successful but can't see absences**
   - Solution: The system includes sample data - if no absences appear, check compilation

### Form Issues
1. **Date field not accepting input**
   - Solution: Use format YYYY-MM-DD only
2. **Cannot click Save button**
   - Solution: Ensure all required fields are filled

### Database Issues
1. **Data not saving**
   - Solution: This is simulated - try again (10% failure rate is intentional)
2. **No absences visible**
   - Solution: Sample data loads on startup - restart the application

## Security Notes

1. **Authentication**: Simple hardcoded credentials for demonstration only
2. **Data Storage**: All data is stored in memory and lost on application close
3. **Session Management**: Logout required to end session
4. **Input Validation**: All user inputs are validated before processing

## Customization

### For Developers
1. **Database Integration**: Replace `DatabaseSimulator.java` with actual database connections
2. **Authentication**: Implement proper authentication system
3. **Data Sources**: Modify sample data in `DatabaseSimulator` constructor
4. **UI Changes**: Modify Swing components in respective form classes

### Configuration Options
- Modify server interruption probability in `DatabaseSimulator.java`
- Change login credentials in `LoginFrame.java`
- Adjust sample absence data in `DatabaseSimulator.java`

## Support

For issues with this application:
1. Check the Troubleshooting section above
2. Verify Java installation
3. Ensure all files are properly compiled
4. Contact your system administrator for production deployment

## Version Information

- **Version**: 1.0.0
- **Release Date**: [Current Date]
- **Java Version**: Compatible with Java 8+
- **License**: Proprietary

## Best Pract

1. **Regular Backups**: Export data regularly in production environments
2. **User Training**: Train administrators on proper justification procedures
3. **Maintenance**: Regular system updates and security patches
4. **Documentation**: Keep justification records for audit purposes

---

*Note: This is a demonstration application. For production use, implement proper database integration, security measures, and authentication systems.*
```