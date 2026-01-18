```markdown
# Class Management System - DeleteClass Use Case

## Overview
The Class Management System is a Java-based desktop application that implements the "DeleteClass" use case for school administrators. The application allows administrators to log in, view detailed class information, delete classes from the archive, and see the updated class list—all while simulating connection interruptions to an external SMOS server after deletions.

## Main Features

### 1. **Administrator Authentication**
- Secure login system for authorized administrators only
- Hardcoded credentials for demo purposes (admin/admin123)
- Prevents unauthorized access to class management functions

### 2. **Class Details View**
- Displays comprehensive information for each class:
  - Class ID and Name
  - Description and Teacher
  - Room assignment and Maximum student capacity
- Implements the "viewdettagliSlasse" precondition
- Clear, organized layout for easy readability

### 3. **Class Deletion Functionality**
- Secure delete operation with confirmation dialog
- Handles the "Delete" button click as per use case
- Validates class existence before deletion
- Provides success/failure feedback to users

### 4. **Updated Class List Display**
- Real-time table showing all classes after deletion
- Sortable columns for better organization
- Double-click functionality to view class details
- Manual refresh option for updated data

### 5. **Database Simulation**
- In-memory database simulation without external dependencies
- Sample data pre-loaded for immediate testing
- CRUD operations (Create, Read, Delete) for class management

## Installation & Environment Setup

### Prerequisites
1. **Java Development Kit (JDK) 8 or higher**
   - Download from [Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html) or [OpenJDK](https://openjdk.java.net/)
   - Verify installation: `java -version` and `javac -version`

2. **Command Line Terminal**
   - Windows: Command Prompt or PowerShell
   - macOS/Linux: Terminal or Bash

### Installation Steps

1. **Download the Application Files**
   Save all Java files in a single directory:
   ```
   ClassManagementSystem/
   ├── Main.java
   ├── DatabaseSimulator.java
   ├── Class.java
   ├── LoginFrame.java
   ├── ClassDetailsFrame.java
   ├── ClassListFrame.java
   └── ClassManager.java
   ```

2. **Compile the Application**
   Open terminal in the application directory and run:
   ```bash
   javac *.java
   ```
   This creates `.class` files for each Java source file.

3. **Run the Application**
   Execute the compiled program:
   ```bash
   java Main
   ```

## How to Use the Application

### Step 1: Login
1. Launch the application using `java Main`
2. The login window appears with fields for username and password
3. Enter the following credentials:
   - **Username:** `admin`
   - **Password:** `admin123`
4. Click "Login" to proceed

### Step 2: View Class Details
1. After successful login, you'll see detailed information about the first class
2. The display includes:
   - Class identification
   - Subject information
   - Teacher assignment
   - Location details
   - Capacity information

### Step 3: Delete a Class
1. Click the "Delete Class" button (red button at the bottom)
2. A confirmation dialog appears asking if you're sure
3. Click "Yes" to proceed with deletion
4. The system will:
   - Remove the class from the archive
   - Simulate interruption to SMOS server connection
   - Display success message
   - Show the updated class list

### Step 4: Review Updated Class List
1. After deletion, you'll see a table with all remaining classes
2. The table shows:
   - All class attributes in columns
   - Number of classes after deletion
   - Postcondition verification message
3. Options available:
   - Double-click any class row to view its details
   - Click "Refresh List" to update the display
   - Click "Exit System" to close the application

## User Workflow

### Normal Flow
1. **Login** → Administrator authentication
2. **View Details** → Examine specific class information
3. **Delete** → Remove selected class with confirmation
4. **View List** → See updated class roster
5. **Repeat** → View/delete other classes as needed

### Alternative Flows
- **Invalid Login**: Error message, retry option
- **No Classes Available**: Informational message, application exit
- **Class Not Found**: Error message, return to class list
- **Deletion Cancelled**: Return to class details view

## Application Architecture

### Core Components
1. **DatabaseSimulator** - Manages class data storage and retrieval
2. **ClassManager** - Business logic for class operations
3. **GUI Frames** - User interface components:
   - LoginFrame: Authentication interface
   - ClassDetailsFrame: Detailed class view
   - ClassListFrame: Tabular class display

### Key Technical Features
- **Swing GUI**: Modern, responsive user interface
- **Event-Driven Programming**: Button clicks trigger specific actions
- **Data Validation**: Prevents errors from invalid inputs
- **Error Handling**: Graceful handling of edge cases
- **Thread Safety**: SwingUtilities.invokeLater for GUI updates

## Testing the Application

### Test Credentials
- Username: `admin`
- Password: `admin123`

### Sample Data
The system initializes with four sample classes:
1. Mathematics 101 - Basic Mathematics
2. Physics 201 - Advanced Physics  
3. Chemistry 301 - Organic Chemistry
4. Biology 401 - Molecular Biology

### Expected Outcomes
1. Successful deletion reduces class count from 4 to 3
2. Each deletion triggers SMOS server interruption simulation
3. Updated list reflects current class roster
4. Double-click navigation works between list and details views

## Troubleshooting

### Common Issues

**Issue**: "Error: Could not find or load main class Main"
**Solution**: Ensure you're in the correct directory and compiled all files with `javac *.java`

**Issue**: Login fails even with correct credentials
**Solution**: Check for typos; use exactly `admin` and `admin123`

**Issue**: GUI appears but buttons don't work
**Solution**: Ensure Java version is 8+ and all files compiled successfully

**Issue**: Application closes immediately
**Solution**: Run from command line to see error messages

### System Requirements Verification
```bash
# Check Java version
java -version

# Check Java compiler
javac -version

# List all Java files
dir *.java   # Windows
ls *.java    # macOS/Linux
```

## Security Notes

### Demo Application Limitations
- Hardcoded credentials for demonstration only
- In production, implement proper authentication
- Add password encryption and secure storage
- Consider database connectivity for persistence

### Best Pract for Production
1. Replace hardcoded credentials with database authentication
2. Implement session management
3. Add audit logging for deletions
4. Consider role-based access control
5. Add data backup before deletions

## Use Case Compliance

### Preconditions Met
- ✓ User logged in as administrator
- ✓ Class details viewed (viewdettagliSlasse)
- ✓ Detailed information displayed
- ✓ Delete button available

### Event Sequence Implemented
1. ✓ Class deleted from archive
2. ✓ Updated class list displayed

### Postconditions Satisfied
- ✓ User has deleted a class
- ✓ Connection to SMOS server interrupted

## Support & Contact

### For Technical Issues
- Verify Java installation
- Check file compilation
- Review error messages in terminal

### Application Customization
The application is designed for extensibility:
- Modify `DatabaseSimulator` for real database connectivity
- Extend `Class` entity with additional attributes
- Add new GUI features as needed
- Implement additional use cases

### Learning Resources
- Java Swing Documentation
- Event-Driven Programming Concepts
- Use Case Implementation Patterns
- Software Testing Methodologies

---

*Note: This is a demonstration application for the DeleteClass use case. In production environments, additional security, persistence, and error handling features should be implemented.*
```