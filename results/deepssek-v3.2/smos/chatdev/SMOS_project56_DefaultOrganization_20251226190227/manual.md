```markdown
# Teacher Class List Viewer - User Manual

## Overview

The Teacher Class List Viewer is a Java-based application that implements the "ViewClassListTeacher" use case. It enables teachers to view the classes they teach across different academic years through a simple command-line interface. The application simulates data retrieval and includes SMOS server connection functionality as specified in the requirements.

## Main Features

### 1. Academic Year Selection
- Displays all academic years in which the teacher has teaching assignments
- Automatically sorts academic years in descending order (newest first)
- Validates user input to ensure proper selection

### 2. Class Listing
- Shows all classes taught by the teacher for the selected academic year
- Displays classes in a numbered list format
- Provides total count of classes for quick reference

### 3. SMOS Server Integration
- Simulates connection to the SMOS (School Management Operating System) server
- Handles server interruption scenarios with status reporting
- Provides connection status feedback to the user

### 4. Data Simulation
- Includes sample teaching data for demonstration purposes
- Easily extensible for real database integration
- Covers multiple academic years with varied course loads

## System Requirements

### Software Prerequisites
- **Java Development Kit (JDK)**: Version 8 or higher
- **Operating System**: Windows, macOS, or Linux
- **Command Line Interface**: Terminal or Command Prompt

### Hardware Requirements
- **Processor**: 1 GHz or faster
- **Memory**: 512 MB RAM minimum
- **Storage**: 10 MB available disk space

## Installation Guide

### Step 1: Install Java Development Kit
1. **Windows/macOS/Linux**:
   - Download JDK from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html) or use OpenJDK
   - Follow the installation instructions for your operating system

2. **Verify Installation**:
   ```bash
   java -version
   javac -version
   ```
   Both commands should display version information

### Step 2: Download Application Files
Create a project directory and download the following files:
```
TeacherClassList/
├── TeacherDataService.java
├── Main.java
└── run.bat (Windows) or run.sh (Linux/macOS)
```

### For Windows Users:
1. Create a new directory for the project
2. Save all Java files in this directory
3. Save `run.bat` in the same directory

### For Linux/macOS Users:
1. Create a new directory for the project
2. Save all Java files in this directory
3. Create a `run.sh` script with execute permissions

## How to Use the Application

### Starting the Application

#### Windows:
1. Open Command Prompt
2. Navigate to the project directory:
   ```cmd
   cd C:\path\to\TeacherClassList
   ```
3. Run the batch file:
   ```cmd
   run.bat
   ```

#### Linux/macOS:
1. Open Terminal
2. Navigate to the project directory:
   ```bash
   cd /path/to/TeacherClassList
   ```
3. Make the script executable (if not already):
   ```bash
   chmod +x run.sh
   ```
4. Run the script:
   ```bash
   ./run.sh
   ```

#### Manual Compilation and Execution:
1. Compile the Java files:
   ```bash
   javac TeacherDataService.java Main.java
   ```
2. Run the main class:
   ```bash
   java Main
   ```

### Application Workflow

#### Step 1: Application Startup
```
=== Teacher Class List Viewer ===
Preconditions:
1. User is logged in as teacher
2. User clicked 'Digital Log' button
```

The application begins by verifying the preconditions are met (teacher logged in and clicked Digital Log button).

#### Step 2: Academic Year Selection
```
1. System displays available academic years:
   1. 2024-2025
   2. 2023-2024
   3. 2022-2023
2. Select academic year (enter number 1-3): 
```

1. The system displays all academic years where the teacher has classes
2. Enter the number corresponding to your desired academic year
3. Press Enter to confirm your selection

#### Step 3: View Classes
```
Selected year: 2023-2024
3. Classes for academic year 2023-2024:
   1. Introduction to Computer Science
   2. Data Structures and Algorithms
   3. Software Engineering
   Total classes: 3
```

1. The system displays all classes for the selected academic year
2. Classes are listed with numbers for easy reference
3. Total count of classes is shown

#### Step 4: SMOS Server Connection
```
=== Postconditions ===
1. List of all classes shown: ✓ (3 classes)
2. Connection to the interrupted SMOS server:
   Connecting to SMOS server...
   Successfully connected to SMOS server.
   Status: Active
```

1. The application verifies all classes are displayed
2. Automatically attempts to connect to the SMOS server
3. Displays connection status
4. Application terminates automatically

## Data Customization

To modify the sample data, edit the `TeacherDataService.java` file:

### Adding Academic Years:
```java
teacherClassesData.put("2025-2026", Arrays.asList(
    "New Course 1",
    "New Course 2"
));
```

### Modifying Classes:
```java
teacherClassesData.put("2023-2024", Arrays.asList(
    "Updated Course 1",
    "Updated Course 2",
    "New Course 3"
));
```

### Changing Year Order:
The application automatically sorts years in descending order. Modify the `getAcademicYearsForTeacher()` method if different sorting is needed.

## Troubleshooting

### Common Issues and Solutions:

#### 1. "Command not found: javac"
**Solution**: Ensure JDK is installed (not just JRE). Verify installation and add to system PATH.

#### 2. "Compilation failed"
**Solution**: 
- Check all files are in the same directory
- Ensure no syntax errors in Java files
- Verify Java version compatibility

#### 3. "Error: Could not find or load main class Main"
**Solution**:
- Ensure files are compiled successfully
- Check that Main.class exists in the directory
- Run from the correct directory

#### 4. Application closes immediately
**Solution**: Run from command line directly instead of double-clicking executable.

### Logging and Debugging:
The application provides clear console output. For detailed debugging:
1. Run with verbose output: `java -verbose:class Main`
2. Check system console for any error messages
3. Verify file permissions on all Java files

## Advanced Configuration

### Extending Data Source:
To connect to a real database:
1. Replace `TeacherDataService` class methods with actual database calls
2. Add database connection parameters
3. Implement proper error handling and connection pooling

### Adding Authentication:
For real-world implementation:
1. Add user authentication before displaying data
2. Implement session management
3. Add role-based access control

### Enhancing SMOS Connection:
To implement actual SMOS server connection:
1. Replace simulated connection with actual network calls
2. Add SSL/TLS security
3. Implement retry logic and error recovery

## Security Considerations

### Data Protection:
- This demo version uses in-memory data
- Production version should encrypt sensitive data
- Implement secure authentication mechanisms

### Server Communication:
- Use HTTPS for SMOS server connections
- Implement proper certificate validation
- Add timeout and retry mechanisms

## Support and Maintenance

### Getting Help:
- Check the application console for error messages
- Review the code comments for implementation details
- Ensure all prerequisites are properly installed

### Updates:
- Monitor for Java updates
- Regularly review and update sample data as needed
- Test with different Java versions for compatibility

### Backup:
- Regularly backup any custom data configurations
- Maintain version control for code modifications

## Best Pract

1. **Regular Testing**: Test the application at the start of each academic year
2. **Data Validation**: Ensure class data is accurate before each semester
3. **System Updates**: Keep Java updated for security and performance
4. **Documentation**: Maintain records of any custom modifications
5. **User Training**: Train teachers on proper application usage

## License and Credits

This application is provided as a demonstration of the ViewClassListTeacher use case. For production use, additional security, error handling, and database integration would be required.

---

*Note: This application simulates functionality for demonstration purposes. In a production environment, proper database integration, user authentication, and security measures must be implemented.*
```