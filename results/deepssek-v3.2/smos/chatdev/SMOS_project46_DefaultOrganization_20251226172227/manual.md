# Report Card Insertion System User Manual

## Overview

The Report Card Insertion System is a Java-based desktop application that enables school administrators to efficiently manage and insert student report cards. This system follows a secure, step-by-step process for entering academic grades, ensuring data integrity and validation.

## System Features

### Core Functionalities:
1. **Secure Administrator Login** - Role-based authentication system
2. **Academic Class Management** - View and select classes for current academic year
3. **Student Selection** - Browse and select students within chosen classes
4. **Report Card Insertion** - Comprehensive grade entry with validation
5. **Data Persistence** - Simulated database storage with error handling

### Key Technical Features:
- Full Java Swing GUI implementation
- Input validation for grades (0-100 range)
- Connection error simulation (10% failure rate to test error handling)
- Modular design with separation of concerns
- Simulated database backend

## Installation Requirements

### Prerequisites:

1. **Java Development Kit (JDK)**
   - Version: JDK 8 or higher
   - Download from: [Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html) or [OpenJDK](https://openjdk.org/)

2. **Development Environment** (Optional):
   - IntelliJ IDEA, Eclipse, or VS Code
   - Any text editor can be used for compilation

### System Requirements:
- Operating System: Windows 10+, macOS 10.14+, or Linux Ubuntu 16.04+
- RAM: Minimum 4GB (8GB recommended)
- Disk Space: 100MB free space

## Installation Steps

### Step 1: Download and Extract Files

1. Download the complete source code package
2. Extract to a directory of your choice
3. Ensure the following directory structure is maintained:
   ```
   ReportCardSystem/
   ├── models/
   │   ├── Student.java
   │   ├── SchoolClass.java
   │   └── ReportCard.java
   ├── Main.java
   ├── LoginFrame.java
   ├── MainAdminFrame.java
   ├── ClassSelectionFrame.java
   ├── StudentListFrame.java
   ├── InsertReportCardFrame.java
   └── DatabaseSimulator.java
   ```

### Step 2: Verify Java Installation

Open a terminal/command prompt and verify Java is installed:
```bash
java -version
javac -version
```

### Step 3: Compile the Application

Navigate to the project directory and compile all Java files:

#### On Windows:
```cmd
cd path\to\ReportCardSystem
javac models/*.java
javac *.java
```

#### On macOS/Linux:
```bash
cd /path/to/ReportCardSystem
javac models/*.java
javac *.java
```

### Step 4: Run the Application

Execute the main class:
```bash
java Main
```

## How to Use the System

### 1. Login Process

**Launch the Application:**
- Run `java Main` from command line
- The Login window will appear

**Administrator Credentials:**
- Username: `admin`
- Password: `admin123`

**Steps:**
1. Enter your credentials in the login form
2. Click "Login" button
3. Successful login will open the Admin Dashboard
4. Invalid credentials will show an error message

### 2. Navigating the Dashboard

The Admin Dashboard provides the main navigation hub:

**Main Options:**
- **Online Reports** - Start the report card insertion process (highlighted in green)
- **Manage Classes** - Placeholder for future class management features
- **Manage Students** - Placeholder for future student management features
- **Logout** - Securely exit the system

**Getting Started:**
1. Click the green "Online Reports" button to begin report card insertion
2. This will take you to the Class Selection screen

### 3. Selecting a Class

The Class Selection screen displays all available classes for the current academic year (2023-2024 in the demo).

**Displayed Information:**
- Class name (e.g., "Grade 10A")
- Assigned teacher
- Academic year

**Actions:**
1. Review the list of available classes
2. Click the blue "Report Cards" button next to any class
3. This opens the Student List for that class
4. Use "Back" button to return to Dashboard

### 4. Selecting a Student

The Student List screen displays all students enrolled in the selected class.

**Student Information Displayed:**
- Student ID
- Full name (First + Last)
- Date of birth
- Action button

**Actions:**
1. Review the student list in tabular format
2. Click the blue "Select" button next to any student
3. This opens the Report Card Insertion form
4. Use "Back to Classes" to return to class selection

### 5. Inserting Report Card Grades

The Report Card Insertion form is where you enter academic grades for the selected student.

**Form Sections:**

**A. Student Information**
- Automatically displays selected student name and class
- Read-only information for verification

**B. Grade Entry Fields**
- 8 predefined subjects:
  1. Mathematics
  2. Science
  3. English
  4. History
  5. Geography
  6. Art
  7. Physical Education
  8. Computer Science

**C. Comments Section**
- Optional field for teacher comments
- Multi-line text area with scrolling

**Grade Entry Rules:**
- Each subject accepts grades from 0-100
- Grades must be whole numbers
- Empty fields are allowed (for incomplete report cards)
- Decimal values are not accepted

**Validation Features:**
- Real-time validation of input format
- Range checking (0-100)
- Error highlighting and descriptive messages

**Form Actions:**

1. **Cancel Button:**
   - Aborts the operation
   - Returns to Student List screen
   - No data is saved

2. **Save Report Card Button (Green):**
   - Validates all entered grades
   - Saves data to the system
   - Shows success/failure message
   - Returns to Student List on success

**Save Process:**
1. Click "Save Report Card" when all entries are complete
2. System validates all entered grades
3. Valid data triggers database save
4. Success message appears (or error if connection fails)
5. Automatic return to Student List

### 6. Error Handling

**Connection Failures:**
- The system simulates a 10% chance of connection failure
- If connection to SMOS server is interrupted, an error appears
- Option to retry the save operation

**Invalid Inputs:**
- Non-numeric values flagged immediately
- Values outside 0-100 range rejected
- Specific error messages guide correction

## Best Pract for Grade Entry

### 1. Data Accuracy
- Double-check student selection before entering grades
- Verify grades before saving
- Use the comments field for special circumstances

### 2. Workflow Efficiency
- Complete all available grades before saving
- Use tab key to navigate between fields
- Save frequently to avoid data loss

### 3. Data Validation
- Enter whole numbers only (no fractions)
- Leave blank for subjects not taken
- Use the full 0-100 scale for consistency

## Database Simulation Details

The system uses an in-memory database simulation with pre-populated data:

### Sample Classes (2023-2024):
1. Grade 10A - Mr. Johnson
2. Grade 10B - Ms. Smith
3. Grade 11A - Dr. Williams
4. Grade 11B - Mrs. Brown

### Sample Students:
- Class 10A: John Doe, Jane Smith, Bob Johnson
- Class 10B: Alice Williams, Charlie Brown
- Class 11A: Eva Davis, Frank Miller
- Class 11B: Grace Wilson

## Troubleshooting

### Common Issues:

**1. "SMOS Server Connection Interrupted" Error**
- This is a simulated error (10% occurrence)
- Simply click Save again to retry
- No data is lost during retry

**2. Application Won't Start**
- Verify Java installation: `java -version`
- Check compilation: All `.java` files must be compiled
- Ensure classpath includes current directory

**3. Login Fails**
- Default credentials: admin/admin123
- Check caps lock is off
- Ensure no extra spaces in username/password

**4. Grade Entry Issues**
- Only whole numbers accepted
- Range must be 0-100
- Empty fields are allowed (skip subjects)

### Reset Options:

**To Clear All Data:**
1. Close the application
2. Restart to initialize fresh simulated database
3. All previous report cards will be lost (simulation only)

## Security Notes

### Current Implementation:
- Hard-coded administrator credentials
- Local simulation only (no network authentication)
- Data stored in memory (not persistent across sessions)

### Production Considerations:
⚠️ **Warning:** This is a demonstration system. For production use:
- Implement proper user management
- Use secure password hashing
- Add database encryption
- Implement network security
- Add audit logging

## Future Enhancements

Planned features for future versions:
1. Actual database integration (MySQL/PostgreSQL)
2. Multiple user roles (Teacher, Principal)
3. Report card printing functionality
4. Grade analytics and statistics
5. Bulk import/export features
6. Academic year management
7. Parent portal integration

## Support Information

### System Requirements Verification:
- Java: `java -version` should show version 1.8 or higher
- Memory: Application uses approximately 50-100MB RAM
- Display: Minimum 1024x768 screen resolution recommended

### Known Limitations:
- Data does not persist between application restarts
- Single administrator account only
- No report card editing or deletion features
- Limited to 8 predefined subjects
- No grade history tracking

## Quick Reference Guide

### Keyboard Shortcuts:
- **Tab**: Navigate forward through form fields
- **Shift+Tab**: Navigate backward
- **Enter**: Click focused button
- **Escape**: Cancel current operation

### Status Indicators:
- ✅ Blue buttons: Navigation/selection actions
- ✅ Green buttons: Save/commit actions
- ⚠️ Red text: Error messages requiring attention
- ℹ️ Gray text: Informational/guidance messages

### Process Flow:
Login → Dashboard → Online Reports → Select Class → Select Student → Enter Grades → Save → Return to Students

This completes the User Manual for the Report Card Insertion System. For additional assistance, please contact your system administrator.