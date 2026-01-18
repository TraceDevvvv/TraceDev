```markdown
# Student-Parent Assignment System (SMOS) - User Manual

## Overview

The Student-Parent Assignment System (SMOS) is a Java-based desktop application designed for school administrators to manage relationships between parents and students. This system allows administrators to easily associate (assign) or dissociate (remove) students from specific parent accounts, providing a streamlined interface for managing family relationships within educational institutions.

## Key Features

- **Administrator Login**: Secure login interface (simplified for demo)
- **Parent Selection**: Browse and select from available parent records
- **Student Management**: 
  - View all available students
  - View currently assigned students
  - Assign multiple students to a parent
  - Remove students from parent associations
  - Double-click support for quick operations
- **Keyboard Shortcuts**: Enhanced accessibility through keyboard navigation
- **Assignment Summary**: Confirmation and summary of changes made
- **Session Management**: Proper login/logout flow

## System Requirements

### Software Requirements
- **Java Runtime Environment (JRE)**: Version 8 or higher
- **Java Development Kit (JDK)**: Version 8 or higher (for running from source)
- **Operating System**: Windows, macOS, or Linux

### Hardware Requirements
- **Memory**: Minimum 512MB RAM
- **Storage**: 10MB free disk space
- **Processor**: Any modern processor (1GHz or faster)

## Installation Instructions

### Option 1: Running from Source Code

1. **Install Java Development Kit (JDK)**
   ```bash
   # Check if Java is installed
   java -version
   
   # If not installed, download JDK from:
   # https://www.oracle.com/java/technologies/downloads/
   # or
   # https://adoptium.net/
   ```

2. **Save the Application Code**
   - Copy the provided Java code into a file named `Main.java`
   - Save it in your preferred directory

3. **Compile the Application**
   ```bash
   # Navigate to the directory containing Main.java
   cd /path/to/directory
   
   # Compile the Java file
   javac Main.java
   ```

4. **Run the Application**
   ```bash
   # Run the compiled program
   java Main
   ```

### Option 2: Running Pre-compiled JAR (If Available)

1. **Ensure JRE is installed** (JRE 8 or higher)
2. **Download the SMOS.jar file** (if provided)
3. **Run the application**
   ```bash
   java -jar SMOS.jar
   ```

## Getting Started

### First Launch
1. When you start the application, you'll see the **Login Screen**
2. Enter any username and password (for demo purposes, any input is accepted)
3. Click "Login" or press Enter

### Main Components

#### 1. Login Screen
- **Username Field**: Enter administrator username
- **Password Field**: Enter password
- **Login Button**: Authenticate and proceed
- **Note**: In this demo version, any non-empty username and password will be accepted

#### 2. Parent Selection Screen
- **Parent Dropdown**: Select from available parents
  - John Doe (john@example.com)
  - Jane Smith (jane@example.com)
  - Robert Johnson (robert@example.com)
- **View Details Button**: Select a parent and proceed to assignment screen
- **Logout Button**: Exit the application
- **Keyboard Shortcut**: Press ESC to logout

#### 3. Assignment Management Screen
This is the main interface where you can manage student-parent relationships.

**Left Panel - Available Students**
- Lists all students not currently assigned to the selected parent
- Can select multiple students using Ctrl+Click or Shift+Click
- Double-click any student to assign them immediately

**Right Panel - Assigned Students**
- Lists all students currently associated with the selected parent
- Can select multiple students for removal
- Double-click any student to remove them immediately

**Center Panel - Action Buttons**
- **>> Assign >>**: Assign selected available students to parent
- **<< Remove <<**: Remove selected assigned students from parent
- **Shortcuts**: 
  - Alt+A: Assign
  - Alt+R: Remove

**Bottom Panel - Control Buttons**
- **Send**: Save all assignments and exit the application
- **Back**: Return to parent selection screen
- **Shortcuts**:
  - Alt+S: Send
  - Alt+B: Back
  - Ctrl+Enter: Alternative to Send
  - ESC: Close and exit

## Usage Guide

### Step-by-Step Workflow

1. **Login to System**
   ```
   Enter credentials → Click Login
   ```

2. **Select Parent**
   ```
   Choose parent from dropdown → Click View Details
   ```

3. **Manage Assignments**
   ```
   Available Options:
   a) Assign single student: Double-click student in Available list
   b) Assign multiple students: Select students → Click Assign button
   c) Remove single student: Double-click student in Assigned list
   d) Remove multiple students: Select students → Click Remove button
   ```

4. **Save Changes**
   ```
   After making desired changes → Click Send
   - System displays summary of assignments
   - Application exits as per postconditions
   ```

### Keyboard Navigation Tips

- **Tab**: Navigate between form elements
- **Up/Down Arrows**: Navigate student lists
- **Space**: Select/deselect student in list
- **Ctrl+A**: Select all students in current list
- **Enter**: Activate default button
- **ESC**: Logout or exit current screen

## Sample Use Cases

### Use Case 1: Assign New Students
1. John Doe adopts two new children: Alice Brown and Bob Green
2. Administrator logs in and selects John Doe
3. From Available Students, selects Alice Brown and Bob Green
4. Clicks "Assign" button
5. Clicks "Send" to save changes
6. System confirms: "2 students assigned to parent John Doe"

### Use Case 2: Remove Existing Assignment
1. Jane Smith's child Charlie White moves to another guardian
2. Administrator selects Jane Smith
3. From Assigned Students, selects Charlie White
4. Clicks "Remove" button
5. Clicks "Send" to save changes
6. System confirms: "1 student removed from parent Jane Smith"

### Use Case 3: Mixed Operations
1. Robert Johnson adopts Eve Gray and Diana Black moves out
2. Administrator selects Robert Johnson
3. Assigns Eve Gray from Available list
4. Removes Diana Black from Assigned list
5. Reviews final assignments and clicks "Send"
6. System shows summary of both operations

## Error Handling

The application includes basic error handling:

1. **Empty Login**: Displays error if username or password is empty
2. **No Selection**: Operations requiring selection will do nothing if no students are selected
3. **Duplicate Assignment**: System prevents assigning the same student twice
4. **Duplicate Removal**: System prevents removing non-assigned students

## Data Model

### Parent Entity
- **ID**: Unique identifier
- **Name**: Full name of parent
- **Email**: Contact email address

### Student Entity
- **ID**: Unique identifier
- **Name**: Full name of student
- **Age**: Current age of student

### Relationships
- One parent can have multiple students
- One student can be assigned to multiple parents (though the demo shows one-to-many)

## Troubleshooting

### Common Issues

1. **Application won't start**
   ```
   Solution: Verify Java installation
   java -version
   ```

2. **Compilation errors**
   ```
   Solution: Ensure all classes are in the same directory
   javac *.java
   ```

3. **GUI looks distorted**
   ```
   Solution: Check screen resolution and DPI settings
   ```

4. **No students appear in lists**
   ```
   Solution: Check console for initialization errors
   ```

### Console Messages
The application outputs status messages to the console:
- Assignment operations
- Removal operations
- Server disconnection messages (postcondition simulation)

## Security Notes

⚠️ **Demo Version Limitations**
- This is a demonstration version with simplified authentication
- In a production system, implement:
  - Proper user authentication
  - Database encryption
  - Session management
  - Audit logging
  - Role-based access control

## Best Pract

1. **Regular Backups**: Always backup data before making bulk changes
2. **Verification**: Double-check assignments before clicking "Send"
3. **Session Management**: Always logout properly using the logout button
4. **Data Integrity**: Review assignment summaries before exiting

## Postconditions

As specified in the use case, after clicking "Send":
1. All assignments are processed
2. Changes are saved (simulated)
3. Connection to SMOS server is terminated
4. Application exits

## Support

For issues with this demo application:
1. Check the console for error messages
2. Verify Java version compatibility
3. Ensure sufficient system permissions

## Future Enhancements

Planned features for production version:
- Database integration (MySQL/PostgreSQL)
- Network/server connectivity
- Advanced search and filtering
- Batch operations
- Reporting and analytics
- Multi-language support
- Cloud deployment options

---

**Version**: 1.0 Demo  
**Last Updated**: Current Date  
**Compatibility**: Java SE 8+  
**License**: Demo/Educational Use Only

*Note: This manual is for the demonstration version of SMOS. Production features may vary.*
```