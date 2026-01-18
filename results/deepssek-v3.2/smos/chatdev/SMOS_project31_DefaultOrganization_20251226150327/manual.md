```markdown
# EditAbsence System - User Manual

## Overview

EditAbsence is a Java-based administrative system designed for managing student absence records in educational institutions. The system allows administrators to view, insert, and delete absence records while automatically notifying parents via email about any changes.

### Key Features
- **Administrator Authentication**: Simulated login system for secure access
- **Date-based Absence Viewing**: View all absence records for a specific date
- **Absence Modification**: Insert new absence records or modify existing ones
- **Parent Notification**: Automatic email notifications for absence changes
- **Server Integration**: Simulated server communication for data persistence
- **Error Handling**: Comprehensive error handling for server and email failures

## Installation Requirements

### Prerequisites
1. **Java Development Kit (JDK)**: Version 8 or higher
2. **Development Environment**:
   - Eclipse IDE
   - IntelliJ IDEA
   - Visual Studio Code with Java extension
   - Any other Java-compatible IDE

### Setup Instructions

1. **Install Java**:
   - Download and install JDK from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html)
   - Verify installation by running in terminal/command prompt:
     ```bash
     java -version
     javac -version
     ```

2. **Set Up Project Structure**:
   - Create a new Java project in your IDE
   - Add all the Java files from the provided code:
     - `Student.java`
     - `Absence.java`
     - `EmailService.java`
     - `ServerConnection.java`
     - `AbsenceManager.java`
     - `CustomException.java`
     - `EditAbsenceApp.java`

## Running the Application

### Compilation
1. Open terminal/command prompt in your project directory
2. Compile all Java files:
   ```bash
   javac *.java
   ```

### Execution
Run the main application:
```bash
java EditAbsenceApp
```

## How to Use the System

### Starting the Application
When you run `EditAbsenceApp`, you'll see:
```
=== Edit Absence System ===

Administrator logged in successfully.
```

### Step-by-Step Usage Guide

#### 1. **Login Phase**
- The system automatically simulates administrator login
- No credentials required for demonstration (production would require authentication)

#### 2. **Select Date Screen**
```
Enter date to view absences (YYYY-MM-DD): 
```
- **Input**: Enter a date in `YYYY-MM-DD` format (e.g., `2024-01-15`)
- **Default**: If invalid format entered, system uses current date
- **Output**: Displays all absence records for the selected date

#### 3. **View Absences Screen**
Example output:
```
=== Absences for 2024-01-15 ===
1. John Doe (S001): Absent
2. Jane Smith (S002): Present
```

#### 4. **Absence Modification Options**

**Scenario A: No Existing Absences**
```
No absences found for selected date.
Adding new absence record...
Enter student ID: 
Enter student name: 
Enter parent email: 
```
- **Process**: Enter new student information
- **Status Selection**: Choose (1) Present or (2) Absent

**Scenario B: Modify Existing Absence**
```
Select student number to modify (1-2): 
Change to (1) Present or (2) Absent? 
```
- Select the student number from the displayed list
- Choose new attendance status

#### 5. **Save and Notification**
- System automatically:
  1. Saves data to server
  2. Sends email notification to parent
  3. Displays success message

#### 6. **Return to Registry**
```
=== System remains on registry screen ===
Ready for next operation...
```
- System remains active for next operation
- Can restart process with new date

## File Structure and Components

### Core Classes

1. **Student.java**
   - Stores student information (ID, name, parent email)
   - Used for identification and notification

2. **Absence.java**
   - Represents individual absence records
   - Contains date, student reference, and attendance status

3. **AbsenceManager.java**
   - Main business logic component
   - Handles absence operations and coordination

4. **EmailService.java**
   - Simulates email notification system
   - Configurable SMTP settings (commented out for simulation)

5. **ServerConnection.java**
   - Simulates server communication
   - Includes simulated server interruption (10% probability)

6. **CustomException.java**
   - Custom exception class for error handling
   - Used primarily for email-related errors

7. **EditAbsenceApp.java**
   - Main application entry point
   - User interface and workflow controller

## Configuration Options

### Email Service Configuration
In `EmailService.java` constructor:
```java
// For production use, uncomment the SMTP implementation
public EmailService(String smtpHost, String smtpPort, 
                   String username, String password) {
    this.smtpHost = smtpHost;
    this.smtpPort = smtpPort;
    this.username = username;
    this.password = password;
}
```

### Server Behavior
In `ServerConnection.java`:
- 10% probability of server interruption (for testing)
- Can be modified in the `saveAbsenceData` method

## Error Handling

### Common Error Scenarios

1. **Invalid Date Format**
   ```
   Invalid date format. Using today's date.
   ```

2. **Server Connection Failure**
   ```
   Server error: Connection to the SMOS server interrupted
   Operation interrupted: Connection to the SMOS server interrupted
   Returning to registry screen...
   ```

3. **Email Notification Failure**
   ```
   Email error: [error message] (notification not sent)
   ```
   - Note: System continues even if email fails

4. **Invalid Input**
   ```
   Invalid selection.
   Invalid input format. Please enter a valid number.
   ```

### Error Recovery
- System maintains registry screen after errors
- Allows administrator to retry operations
- Partial failures don't affect overall system stability

## Sample Usage Session

```
=== Edit Absence System ===

Administrator logged in successfully.

Enter date to view absences (YYYY-MM-DD): 2024-01-15

=== Absences for 2024-01-15 ===
1. John Doe (S001): Absent
2. Jane Smith (S002): Present

Select student number to modify (1-2): 1

Change to (1) Present or (2) Absent? 1
Saving absence data for student John Doe on 2024-01-15 to server...
Data saved successfully.

[EMAIL SIMULATION]
To: parent1@example.com
Subject: Absence Update for John Doe
Body: Dear Parent,
This is to notify you that the absence record for John Doe on 2024-01-15 has been marked as present.
Sincerely,
School Administration
[END EMAIL SIMULATION]

Absence updated successfully.

=== System remains on registry screen ===
Ready for next operation...
```

## Best Pract

### For Administrators
1. **Date Selection**: Always use YYYY-MM-DD format
2. **Verification**: Double-check student information before saving
3. **Notification**: Ensure parent email addresses are current
4. **Error Handling**: Note any error messages and retry if needed

### For Developers
1. **Testing**: Test email service with actual SMTP in production
2. **Logging**: Implement proper logging for production use
3. **Security**: Add actual authentication system
4. **Database**: Replace simulated data with actual database connection

## Troubleshooting

### Common Issues

1. **Compilation Errors**
   - Ensure all Java files are in the same directory
   - Check Java version compatibility
   - Verify class names match file names exactly

2. **Runtime Errors**
   - Check date format when entering
   - Verify input numbers are within valid range
   - Ensure no typos in student information

3. **Email Notifications Not Working**
   - Current implementation uses console simulation
   - For actual emails, configure SMTP settings in `EmailService.java`

4. **Server Connection Issues**
   - Simulated interruptions are random (10% chance)
   - In production, implement proper retry mechanisms

### Support
For issues not covered in this manual, please:
1. Check console error messages
2. Verify all prerequisites are installed
3. Review the code comments for specific implementations

## Future Enhancements

Potential improvements for production deployment:
1. **Database Integration**: Replace in-memory storage with SQL database
2. **Web Interface**: Create web-based user interface
3. **Authentication**: Implement proper user authentication
4. **Reporting**: Add absence reporting features
5. **Batch Operations**: Support for bulk absence updates
6. **Email Templates**: Customizable email templates for notifications

---

**Version**: 1.0  
**Last Updated**: [Current Date]  
**Support**: For technical support, contact your system administrator
```