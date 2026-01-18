```markdown
# Student Report Card System - User Manual

## Overview
The Student Report Card System is a Java-based desktop application designed for parents to securely access and view their children's academic report cards. The system simulates a database environment with predefined credentials and report data, and includes simulated SMOS server connections as part of the workflow.

## Main Features

### 1. **Secure Parent Login**
- Username and password authentication
- Input validation to prevent empty submissions
- Error handling for invalid credentials

### 2. **Child Selection Interface**
- Clean, button-based interface for child selection
- Each child is represented with an individual "Magine" button
- Responsive layout with appropriate spacing between buttons

### 3. **SMOS Server Integration**
- Simulated server connection with 20% chance of interruption
- User notifications about connection status
- Graceful handling of server interruptions

### 4. **Report Card Display**
- Detailed presentation of student information
- Formatted grade display with clear subject breakdown
- Read-only interface to prevent accidental modifications

## System Requirements

### Minimum Requirements:
- **Operating System**: Windows 10+, macOS 10.13+, Linux (any modern distribution)
- **Java Runtime Environment**: Version 8 or higher
- **RAM**: 2GB minimum
- **Disk Space**: 10MB free space

### Development Requirements:
- **Java Development Kit**: JDK 8 or higher
- **IDE**: Any Java IDE (Eclipse, IntelliJ IDEA, NetBeans) or text editor
- **Git**: For version control (optional)

## Installation Instructions

### Option 1: Using Git (Recommended)

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/student-report-system.git
   cd student-report-system
   ```

2. **Compile the Java Files**
   ```bash
   javac *.java
   ```

3. **Run the Application**
   ```bash
   java Main
   ```

### Option 2: Manual Setup

1. **Create a Project Folder**
   ```
   StudentReportSystem/
   ├── DatabaseSimulator.java
   ├── Main.java
   ├── ParentLoginFrame.java
   ├── StudentSelectionFrame.java
   ├── ReportDisplayFrame.java
   └── README.md
   ```

2. **Copy all the provided Java source code files** into the project folder

3. **Compile all Java files**:
   ```bash
   javac *.java
   ```

4. **Launch the application**:
   ```bash
   java Main
   ```

### Option 3: Using an IDE

1. **Create a new Java project** in your preferred IDE (Eclipse, IntelliJ IDEA, etc.)
2. **Import all the Java source files** into the project's src folder
3. **Configure project properties**:
   - Set Java version to 8 or higher
   - Ensure Swing/AWT libraries are included (they're part of standard Java)
4. **Run the Main.java file** as a Java application

## How to Use the Application

### Step 1: Launch the Application
Run the `Main.java` file or execute `java Main` from the command line. You will see the login window titled "Student Report System - Parent Login".

### Step 2: Login
- **Available Test Credentials**:
  - Username: `john_doe`, Password: `password123`
  - Username: `jane_smith`, Password: `secure456`

1. Enter your username in the "Username" field
2. Enter your password in the "Password" field
3. Click the "Login" button

### Step 3: Select a Child
After successful login:
1. The system displays a list of your children's names as clickable buttons
2. Click on the name (Magine button) of the child whose report you want to view

### Step 4: View Report Card
Upon selecting a child:
1. The system attempts to connect to the SMOS server
2. If connection is successful (80% chance), you'll see a success message in the console
3. If connection is interrupted (20% chance), you'll see a warning dialog
4. The report card window displays with:
   - Student name
   - Grade level
   - Semester
   - Detailed subject grades

### Step 5: Navigation
- **To view another child's report**: Use the operating system's back button or restart from the login screen (the current implementation closes previous windows for simplicity)
- **To exit the application**: Click the "Close" button on any window or the standard window close button

## Test User Accounts and Sample Data

### Parent Account 1:
- **Username**: john_doe
- **Password**: password123
- **Children**: 
  - Alice Doe (Grade 5, Spring 2024)
  - Bob Doe (Grade 3, Spring 2024)

### Parent Account 2:
- **Username**: jane_smith
- **Password**: secure456
- **Children**:
  - Charlie Smith (Grade 7, Spring 2024)

## Troubleshooting

### Common Issues and Solutions:

1. **"Could not find or load main class Main"**
   - Ensure you've compiled all Java files first: `javac *.java`
   - Check that Main.class exists in your directory

2. **"Package javax.swing does not exist"**
   - You're using an incomplete Java installation
   - Install the full Java Development Kit (JDK), not just JRE

3. **Application window doesn't appear**
   - Check if there are any error messages in the console
   - Ensure you're running on a system with GUI support (not headless server)

4. **Login fails with correct credentials**
   - Ensure you're using the exact test credentials (case-sensitive)
   - Try restarting the application

### SMOS Server Connection Issues:
- The system simulates occasional server interruptions (20% probability)
- When interruptions occur, the application displays cached data with a warning
- This is intentional behavior for demonstration purposes

## Architecture Overview

### Key Components:
1. **DatabaseSimulator.java**: Simulates database operations and SMOS server connections
2. **Main.java**: Application entry point
3. **ParentLoginFrame.java**: Login interface
4. **StudentSelectionFrame.java**: Child selection interface
5. **ReportDisplayFrame.java**: Report card display interface

### Design Patterns:
- **MVC Pattern**: Separation between data (DatabaseSimulator), view (GUI frames), and control (action listeners)
- **Singleton-like Database**: Single instance of database simulator throughout application
- **Observer Pattern**: Event-driven GUI responses using ActionListener

## Security Considerations

### Current Implementation:
- Credentials are stored in memory (for demonstration only)
- No encryption of passwords in this demo version
- UI validation to prevent empty submissions

### For Production Deployment (Recommended Enhancements):
1. Implement password hashing (bcrypt, Argon2)
2. Use SSL/TLS for network communications
3. Implement session management with timeouts
4. Add audit logging
5. Use a real database with proper security configuration

## Customization Options

### To Modify Test Data:
Edit the `initializeSampleData()` method in `DatabaseSimulator.java`:
```java
// Add new parent credentials
parentCredentials.put("new_parent", "new_password");

// Add children for new parent
Map<String, ReportCard> newChildren = new HashMap<>();
newChildren.put("Child Name", new ReportCard("Child Name", "Grade X", "Semester Y", 
    new String[]{"Subject1: Grade", "Subject2: Grade"}));
studentReports.put("new_parent", newChildren);
```

### To Adjust SMOS Server Connection Behavior:
Modify the probability in `connectToSMOSServer()` method:
```java
// Current: 20% chance of interruption
if (random < 0.2) { ... }

// Change to 10% chance:
if (random < 0.1) { ... }
```

## Reporting Issues

If you encounter any bugs or have suggestions:

1. Check if the issue is already documented in this manual
2. Verify you're using the correct Java version
3. Try recompiling the application: `javac *.java`
4. Create an issue on the GitHub repository with:
   - Error message
   - Steps to reproduce
   - Your Java version (`java -version`)
   - Operating system information

## Future Enhancements (Roadmap)

Planned features for future releases:
1. **Multiple Language Support**: Internationalization for different languages
2. **Print Functionality**: Ability to print report cards
3. **Historical Reports**: View multiple semesters
4. **Teacher Comments Section**
5. **Export Options**: PDF, Excel, CSV exports
6. **Mobile Application Version**
7. **Real Database Integration**: MySQL, PostgreSQL support
8. **Automated Email Reports**

## License and Usage

This software is provided for educational and demonstration purposes. You are free to:
- Use the software for learning and testing
- Modify the source code for educational purposes
- Share the software with proper attribution

## Credits

**ChatDev Software Company**
- Chief Technology Officer: System architecture and implementation
- Chief Product Officer: User experience and documentation
- Quality Assurance Team: Testing and validation

**Special Thanks:**
- Java Swing framework for GUI components
- Open source community for best pract

## Support

For additional support:
- Review this documentation thoroughly
- Check the code comments for implementation details
- Refer to Java Swing documentation for GUI questions
- Contact: product@chatdev.com

---

*Last Updated: October 2024*
*Version: 1.0.0*
*Compatibility: Java 8+*
```