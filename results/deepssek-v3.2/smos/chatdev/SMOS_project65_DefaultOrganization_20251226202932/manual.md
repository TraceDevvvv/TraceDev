```markdown
# Class Registry System - User Manual

## Overview

The Class Registry System is a Java-based desktop application designed for school administrators (Direction) to view and manage class attendance records. The system displays registry information organized by date, showing student attendance status (present/absent/delayed), justifications, and disciplinary notes. It includes simulation of SMOS server connectivity for demonstration purposes.

## System Requirements

### Hardware Requirements
- **Operating System**: Windows 7+, macOS 10.10+, or Linux with GUI
- **Processor**: 1 GHz or faster
- **Memory**: 512 MB RAM minimum (1 GB recommended)
- **Storage**: 10 MB available disk space
- **Display**: 1024x768 screen resolution minimum

### Software Dependencies
- **Java Runtime Environment (JRE)**: Version 8 or higher
- No additional libraries required - all dependencies are included with standard Java

## Installation

### Method 1: Using JAR File (Easiest)
1. Download the `ClassRegistrySystem.jar` file
2. Ensure Java is installed on your system (run `java -version` in terminal to verify)
3. Double-click the JAR file or run from terminal:
   ```bash
   java -jar ClassRegistrySystem.jar
   ```

### Method 2: Running from Source Code
1. Ensure Java Development Kit (JDK) 8+ is installed
2. Download all Java source files:
   - `ClassRegistrySystem.java` (main file)
   - `Student.java`
   - `AttendanceRecord.java`
   - `ClassRegistryModel.java`
   - `RegistryController.java`
   - `RegistryGUI.java`

3. Compile the application:
   ```bash
   javac ClassRegistrySystem.java
   ```

4. Run the application:
   ```bash
   java ClassRegistrySystem
   ```

### Verifying Installation
After launching, you should see a window titled "Class Registry System - Director View" with these components:
- Date selection dropdown
- Load Registry button
- SMOS connection buttons
- Attendance table with student data
- Update button
- Connection status indicator

## Application Interface

### Main Window Components
```
┌─────────────────────────────────────────────────────────────┐
│ Class Registry System - Director View                       │
├─────────────────────────────────────────────────────────────┤
│ Select Date: [MMM dd, yyyy ▼] [Load Registry]              │
│ [Simulate SMOS Interruption] [Reconnect to SMOS]            │
├─────────────────────────────────────────────────────────────┤
│ Student ID | Student Name | Status | Justification | Notes │
├─────────────────────────────────────────────────────────────┤
│ ST001      | Alice Johnson | Present | Sick         |       │
│ ST002      | Bob Smith     | Absent  |              | Note  │
│ ...        | ...          | ...     | ...          | ...   │
├─────────────────────────────────────────────────────────────┤
│ [Update Selected Record]                    SMOS: Connected │
└─────────────────────────────────────────────────────────────┘
```

### Interface Elements Explained

1. **Date Selector** (Top-left)
   - Dropdown showing available dates in "MMM dd, yyyy" format
   - Automatically loads data when date is changed

2. **Action Buttons** (Top-right)
   - **Load Registry**: Manually refresh data for selected date
   - **Simulate SMOS Interruption**: Demonstrates server disconnection
   - **Reconnect to SMOS**: Restores simulated connection

3. **Attendance Table** (Center)
   - Displays student records for selected date
   - Columns: Student ID, Name, Status, Justification, Disciplinary Notes
   - Green highlight for "Present", orange for "Delayed", red for "Absent"

4. **Control Area** (Bottom)
   - **Update Selected Record**: Save changes to selected student
   - **Status Indicator**: Shows current SMOS connection state

## Core Functions

### 1. Viewing Attendance Records
1. Select a date from the dropdown menu
2. The system automatically loads attendance data for that date
3. View student attendance status, justifications, and disciplinary notes

### 2. Updating Student Records
1. Select a student row in the table
2. Double-click or use inline editing to modify:
   - Status: Click the Status cell to choose from dropdown (Present/Absent/Delayed)
   - Justification: Click and type directly in the cell
   - Disciplinary Notes: Click and type directly in the cell
3. Click "Update Selected Record" to save changes
4. Confirm changes with the success dialog

### 3. Managing SMOS Connection
**Simulating Server Issues:**
1. Click "Simulate SMOS Interruption"
2. Observe:
   - Status changes to "SMOS: Disconnected" (red)
   - Action buttons become disabled
   - Error messages when attempting operations
3. Attempt to load or update data to see error handling

**Restoring Connection:**
1. Click "Reconnect to SMOS"
2. Observe:
   - Status returns to "SMOS: Connected" (green)
   - All functions are re-enabled
   - Success confirmation appears

## Sample Data

The application comes pre-loaded with demonstration data:

### Sample Students (5 records)
- Alice Johnson (ST001)
- Bob Smith (ST002)
- Carol Williams (ST003)
- David Brown (ST004)
- Eva Davis (ST005)

### Sample Dates (3 dates)
- Today's date
- Yesterday
- Day before yesterday

### Attendance Status Distribution
- **~70%**: Present (default)
- **~20%**: Absent with "Sick" justification
- **~10%**: Delayed with "Traffic" justification
- **~20%**: Random disciplinary notes

## Workflow Examples

### Example 1: Daily Attendance Review
1. Launch the application
2. Select today's date from dropdown
3. Review student attendance status
4. For absent students, add justifications by double-clicking justification column
5. Add disciplinary notes where necessary
6. Update any incorrect statuses
7. Click "Update Selected Record" for each modified entry

### Example 2: Historical Data Analysis
1. Select previous dates from dropdown
2. Compare attendance patterns across dates
3. Identify frequently absent or delayed students
4. Review justifications and notes history

### Example 3: SMOS Connectivity Testing
1. Click "Simulate SMOS Interruption"
2. Attempt to load data (error should appear)
3. Try updating a record (error should appear)
4. Click "Reconnect to SMOS"
5. Verify normal operations resume

## Features and Capabilities

### ✅ Complete Features
- **Multi-date Support**: View records for multiple dates
- **Student Management**: Pre-loaded with 5 sample students
- **Status Tracking**: Present, Absent, Delayed with color coding
- **Justification Management**: Track reasons for absences/delays
- **Disciplinary Notes**: Record behavior observations
- **SMOS Simulation**: Realistic server connectivity simulation
- **Data Persistence**: In-memory data management
- **Error Handling**: Graceful handling of connection issues

### 🔧 Technical Capabilities
- **MVC Architecture**: Clean separation of concerns
- **Swing GUI**: Native-looking interface
- **Thread Safety**: Event Dispatch Thread compliance
- **Input Validation**: Status validation and data integrity
- **Sample Data Generation**: Realistic demonstration data

## Troubleshooting

### Common Issues and Solutions

**Issue: Application won't start**
- **Solution**: Verify Java installation: `java -version`
- **Solution**: Ensure all source files are in the same directory
- **Solution**: Check for compilation errors

**Issue: Buttons are disabled**
- **Solution**: Check SMOS connection status (should be green)
- **Solution**: Click "Reconnect to SMOS" if disconnected

**Issue: Data not appearing**
- **Solution**: Verify date is selected
- **Solution**: Click "Load Registry" button
- **Solution**: Check SMOS connection status

**Issue: Updates not saving**
- **Solution**: Ensure you click "Update Selected Record" after editing
- **Solution**: Verify a student row is selected
- **Solution**: Check connection status

### Error Messages
- **"Cannot load data: Connection to SMOS server interrupted."**
  - SMOS connection is simulated as disconnected
  - Click "Reconnect to SMOS" to restore

- **"Please select a student record to update."**
  - No row is selected in the table
  - Click on a student row before updating

- **"Student not found."**
  - Internal data inconsistency
  - Restart the application

## Data Model

### Student Class
```java
Student {
    String id;      // Unique student identifier
    String name;    // Student's full name
}
```

### AttendanceRecord Class
```java
AttendanceRecord {
    Student student;            // Associated student
    Date date;                  // Attendance date
    String status;             // Present/Absent/Delayed
    String justification;      // Reason for absence/delay
    String disciplinaryNotes;  // Behavior observations
}
```

## Application Architecture

### Model-View-Controller (MVC) Pattern
- **Model**: `ClassRegistryModel` - Manages data and business logic
- **View**: `RegistryGUI` - Handles user interface display
- **Controller**: `RegistryController` - Mediates between Model and View

### Key Classes
1. **ClassRegistrySystem** - Main entry point
2. **ClassRegistryModel** - Data management and SMOS simulation
3. **RegistryController** - Business logic and coordination
4. **RegistryGUI** - User interface implementation
5. **Student** - Student entity representation
6. **AttendanceRecord** - Attendance data structure

## Security and Data Considerations

### Data Storage
- **Current**: In-memory storage only (data lost on exit)
- **Production**: Would integrate with database/SMOS server

### Privacy
- Sample data only - no real student information
- Production version would require authentication
- Data encryption for real deployments

### Limitations
- No persistent storage between sessions
- No user authentication/authorization
- No data export capabilities
- Limited to 5 sample students

## Customization and Extension

### Adding More Students
To add more sample students, modify the `main` method in `ClassRegistrySystem.java`:
```java
students.add(new Student("ST006", "New Student Name"));
```

### Adding More Dates
To extend date range:
```java
cal.add(Calendar.DAY_OF_MONTH, -1);
dates.add(cal.getTime());
```

### Modifying Sample Data Generation
Edit the `initializeAttendanceRecords()` method in `ClassRegistryModel.java` to change:
- Attendance status distribution
- Default justifications
- Disciplinary note frequency

## Support and Feedback

### Getting Help
- Review this manual first
- Check console for error messages
- Verify Java version compatibility

### Reporting Issues
If you encounter bugs or have suggestions:
1. Note the exact error message
2. Record steps to reproduce
3. Check Java version
4. Verify all source files are present

### Future Enhancements
Planned features for future versions:
- Database integration
- Multiple class support
- Data import/export
- User authentication
- Reporting capabilities
- Cloud synchronization

## Quick Reference Guide

### Keyboard Shortcuts
- **Enter**: Load selected date
- **Double-click**: Edit table cell
- **Ctrl+S**: Simulate SMOS interruption (when focused)
- **Ctrl+R**: Reconnect to SMOS (when focused)

### Status Color Codes
- **🟢 Green**: SMOS connected, operations enabled
- **🔴 Red**: SMOS disconnected, operations disabled
- **🟢 Present**: Normal attendance
- **🟠 Delayed**: Late arrival
- **🔴 Absent**: Student absent

### Button States
- **Enabled**: Normal operation available
- **Disabled**: Function unavailable
- **Red text**: Warning/critical action

## Conclusion

The Class Registry System provides a comprehensive solution for viewing and managing class attendance records. With its intuitive interface, realistic SMOS server simulation, and complete set of features, it serves as both a functional prototype and a demonstration of proper software architecture principles.

For production deployment, additional features like persistent storage, user authentication, and real SMOS server integration would be implemented based on institutional requirements.

---

*Note: This is a demonstration application. All student data is fictional and regenerated on each launch.*
```