# Report Card Deletion System - User Manual

## Overview

The Report Card Deletion System is a Java-based application designed for educational administrators to manage student report cards. This system allows administrators to view, delete, and manage report cards while handling user authentication and server connections according to specified use cases.

## Main Functions

The system provides the following key features:

1. **Administrator Authentication**
   - Secure login system with hardcoded credentials (admin/admin123)
   - Session management simulation

2. **Report Card Management**
   - View all available report cards
   - Display individual report cards with student details and grades
   - Delete specific report cards from the system

3. **SMOS Server Integration**
   - Simulated connection to SMOS (School Management Operating System) server
   - Proper connection handling as per post-conditions

4. **User Workflow**
   - Complete implementation of the "DeleteOfOnePage" use case
   - Interactive console interface with confirmation prompts
   - Form-based class listing post-deletion

5. **Data Persistence**
   - In-memory storage of report cards with sample data
   - Singleton pattern for data consistency

## System Requirements

### Hardware Requirements
- Any standard computer capable of running Java applications
- Minimum 512MB RAM
- 10MB free disk space

### Software Requirements
- **Java SE Development Kit (JDK) 8 or later**
- **Operating System:** Windows, macOS, Linux, or any Java-supported platform
- **Terminal/Command Prompt** for running console applications

**Note:** No additional external libraries or database systems are required. All components are self-contained within the Java application.

## Installation Instructions

### Step 1: Install Java Development Kit (JDK)

#### Windows/MacOS/Linux:
1. Download the latest JDK from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html) or use OpenJDK
2. Run the installer and follow the setup wizard
3. Verify installation by opening a terminal and typing:
   ```bash
   java -version
   ```
   You should see Java version information displayed.

### Step 2: Set Up Environment Variables (Optional but recommended)

#### Windows:
1. Open System Properties (Win + Pause/Break)
2. Click "Advanced system settings"
3. Click "Environment Variables"
4. Under System Variables, find and edit PATH
5. Add the path to your JDK's bin folder (e.g., `C:\Program Files\Java\jdk1.8.0_301\bin`)
6. Create new variable `JAVA_HOME` pointing to your JDK folder

#### MacOS/Linux:
1. Edit your shell profile file (`~/.bashrc`, `~/.bash_profile`, or `~/.zshrc`)
2. Add the following lines:
   ```bash
   export JAVA_HOME=/path/to/your/jdk
   export PATH=$JAVA_HOME/bin:$PATH
   ```
3. Run `source ~/.bashrc` (or equivalent) to apply changes

### Step 3: Get the Application Code

1. **Option A: Copy from the provided code sections**
   - Copy the complete `DeleteOfOnePage.java` file content
   - Create a new file named `DeleteOfOnePage.java` in a directory of your choice

2. **Option B: Separate class files** (for modular development)
   - Create individual files for each class:
     - `AdminAuthenticator.java`
     - `ReportCard.java`
     - `ReportCardManager.java`
     - `SMOSServerConnector.java`
     - `DeleteReportCardApp.java`
   - Copy the respective code into each file

## How to Use the Application

### Running the Application

1. **Compile the Java program:**
   ```bash
   javac DeleteOfOnePage.java
   ```
   *Or for separate files:*
   ```bash
   javac *.java
   ```

2. **Run the application:**
   ```bash
   java DeleteOfOnePage
   ```
   *Or for separate files (if using DeleteReportCardApp):*
   ```bash
   java DeleteReportCardApp
   ```

### User Walkthrough

#### Starting the Application
```
=== Report Card Deletion System ===

System Status: SMOS server connection established.
Connected to SMOS server.
Administrator session active.
(Note: The administrator is already logged in as per preconditions.)
```

#### Step 1: View Available Report Cards
The system displays all existing report cards with sample data:
```
=== Available Report Cards ===
All Report Cards:
ReportCard{studentId=101, studentName='Alice Johnson', className='Class 10A', grades={Math=95.5, History=92.0, Science=88.0}}
ReportCard{studentId=102, studentName='Bob Smith', className='Class 10B', grades={Math=78.0, History=90.0, Science=85.5}}
ReportCard{studentId=103, studentName='Charlie Brown', className='Class 10A', grades={Math=88.5, History=87.5, Science=92.0}}
```

#### Step 2: Select Report Card for Deletion
```
Enter Student ID to delete report card (or 0 to exit): 101
```
*Enter the student ID of the report card you want to delete*

#### Step 3: View Report Card Details
```
--- Displaying Report Card ---
Student ID: 101
Student Name: Alice Johnson
Class: Class 10A
Grades: {Math=95.5, History=92.0, Science=88.0}
--- End of Report Card ---
```

#### Step 4: Initiate Deletion
```
Click 'Delete' button to proceed.
Press Enter to click Delete...
```
*Press Enter to simulate clicking the Delete button*

#### Step 5: Confirm Deletion
```
=== Confirmation Required ===
Are you sure you want to delete the report card for:
Student: Alice Johnson (ID: 101)
This action cannot be undone.
Type 'yes' to confirm deletion, or 'no' to cancel: yes
```

#### Step 6: Successful Deletion Process
```
✓ SUCCESS: Report card deleted successfully.
Postcondition: Report for student Alice Johnson has been canceled.
Postcondition: SMOS server connection interrupted.
Disconnected from SMOS server.
```

#### Step 7: View Updated Class List
```
=== Form with List of Classes ===
Classes:
 - Class 10A
   Report Cards in this class: 1
     Charlie Brown (ID: 103)
 - Class 10B
   Report Cards in this class: 1
     Bob Smith (ID: 102)
```

#### Step 8: Application Termination
```
=== Application terminated ===
```

## Sample Data Included

The system comes pre-loaded with three sample report cards:

1. **Alice Johnson** (ID: 101)
   - Class: Class 10A
   - Grades: Math (95.5), Science (88.0), History (92.0)

2. **Bob Smith** (ID: 102)
   - Class: Class 10B
   - Grades: Math (78.0), Science (85.5), History (90.0)

3. **Charlie Brown** (ID: 103)
   - Class: Class 10A
   - Grades: Math (88.5), Science (92.0), History (87.5)

## Administrator Credentials

**Default Administrator Login:**
- Username: `admin`
- Password: `admin123`

**Note:** In the current implementation, the administrator is automatically logged in as per the use case preconditions. The authentication system is simulated for demonstration purposes.

## Use Case Implementation Details

The application implements the complete "DeleteOfOnePage" use case:

### Preconditions Met:
1. ✅ User logged in as administrator (simulated)
2. ✅ "Displaying a report card" use case completed
3. ✅ User clicked the "Delete" button (simulated)

### Event Sequence:
1. **System displays confirmation form** - Implemented with console prompts
2. **User accepts cancellation** - Implemented with "yes/no" confirmation
3. **System displays success message** - Shows deletion confirmation
4. **System displays form with class list** - Shows updated class information

### Postconditions Met:
1. ✅ Report relative to a student is canceled
2. ✅ Connection to SMOS server is interrupted

## Error Handling and Edge Cases

The application handles various edge cases:

1. **Invalid Student ID**: System displays "Report card not found" message
2. **Non-numeric Input**: Catches InputMismatchException and displays error
3. **Invalid Confirmation**: Accepts only "yes" or "no", shows cancellation for other inputs
4. **Exit Option**: Option to exit without deletion by entering 0
5. **Server Connection**: Properly manages SMOS server connection state

## Troubleshooting

### Common Issues and Solutions:

1. **"javac: command not found"**
   - Ensure JDK is installed and PATH is set correctly
   - Verify installation with `java -version`

2. **"Error: Could not find or load main class"**
   - Ensure you're in the correct directory
   - Run `javac` first to compile the program
   - Check that class name matches file name

3. **Compilation errors**
   - Ensure all required class files are in the same directory
   - Check for missing semicolons or syntax errors
   - Verify Java version compatibility

4. **Input issues**
   - Use numeric values for Student ID
   - Type exact text "yes" or "no" for confirmation
   - Press Enter after each input

### Testing the System:

To verify the system is working correctly:
1. Run the application
2. Try deleting each sample report card
3. Cancel a deletion to test the cancellation flow
4. Test invalid inputs to see error handling

## Future Enhancements

The current implementation is a simulation. For production use, consider:

1. **Database Integration**: Replace in-memory storage with a database
2. **GUI Interface**: Replace console with graphical user interface
3. **Enhanced Security**: Implement proper authentication and authorization
4. **SMOS Server API**: Connect to real SMOS server with proper API
5. **Data Validation**: Add comprehensive input validation
6. **Logging**: Implement detailed audit logging
7. **Multi-user Support**: Support multiple administrators with different permissions

## Support

For issues with the application:
1. Check the troubleshooting section above
2. Verify Java installation and environment variables
3. Ensure you're using the correct file names and compilation steps
4. Review error messages for specific guidance

## License and Copyright

This software is provided as-is for educational and demonstration purposes. Modify and extend as needed for your specific requirements.

---

**Note:** This is a simulation system designed to demonstrate the "DeleteOfOnePage" use case. All server connections, authentications, and data storage are simulated for educational purposes.