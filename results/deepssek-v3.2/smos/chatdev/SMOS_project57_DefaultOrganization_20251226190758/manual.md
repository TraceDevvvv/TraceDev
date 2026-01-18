```markdown
# Class Registry View System - User Manual

## Overview

The Class Registry View System is a Java-based application designed for professors to view and manage class register information. It simulates a real-world scenario where a logged-in professor can select a class and view detailed registry entries including date, absences, disciplinary notes, delays, and justifications.

### Main Features

1. **Professor Authentication Simulation**: Simulates a logged-in professor session
2. **Class Selection**: Allows selection from available classes taught by the professor
3. **Register View**: Displays comprehensive register information for selected class registers
4. **Data Management**: Handles register entries with structured data organization
5. **Connection Simulation**: Simulates SMOS server connection interruption handling
6. **Error Handling**: Gracefully handles invalid inputs and system errors

## System Requirements

### Hardware Requirements
- Any modern computer with at least 2GB RAM
- 100MB free disk space
- Processor: 1GHz or faster

### Software Requirements
- Java Development Kit (JDK) 8 or later
- Command-line interface or terminal access
- Text editor or IDE (optional for viewing code)

## Installation Guide

### Step 1: Install Java Development Kit (JDK)

#### Windows Users:
1. Download JDK from [Oracle's official website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
2. Run the installer and follow the installation wizard
3. Set JAVA_HOME environment variable:
   - Right-click "This PC" → Properties → Advanced System Settings
   - Click "Environment Variables"
   - Under System Variables, click "New"
   - Variable name: `JAVA_HOME`
   - Variable value: Path to your JDK installation (e.g., `C:\Program Files\Java\jdk-11.0.15`)
   - Add JDK to PATH: Edit Path variable and add `%JAVA_HOME%\bin`

#### macOS Users:
1. Using Homebrew (recommended):
   ```bash
   brew install openjdk@11
   ```
2. Or download from [Oracle's website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
3. Set JAVA_HOME in your shell configuration:
   ```bash
   echo 'export JAVA_HOME="/usr/local/opt/openjdk@11"' >> ~/.zshrc
   echo 'export PATH="$JAVA_HOME/bin:$PATH"' >> ~/.zshrc
   source ~/.zshrc
   ```

#### Linux Users:
1. For Ubuntu/Debian:
   ```bash
   sudo apt update
   sudo apt install openjdk-11-jdk
   ```
2. For Fedora/RHEL:
   ```bash
   sudo dnf install java-11-openjdk-devel
   ```

### Step 2: Verify Installation
Check if Java is properly installed:
```bash
java -version
```

You should see output similar to:
```
java version "11.0.15" 2022-04-19 LTS
Java(TM) SE Runtime Environment (build 11.0.15+8-LTS-149)
Java HotSpot(TM) 64-Bit Server VM (build 11.0.15+8-LTS-149, mixed mode)
```

## How to Use the System

### Getting the Source Code

1. Download the provided `ViewRegister.java` file to your computer
2. Create a new directory for the project:
   ```bash
   mkdir ClassRegistrySystem
   cd ClassRegistrySystem
   ```

### Compilation

Navigate to the directory containing the `ViewRegister.java` file and compile it:
```bash
javac ViewRegister.java
```

This will create a `ViewRegister.class` file (and other class files for inner classes) in the same directory.

### Running the Application

Execute the compiled program:
```bash
java ViewRegister
```

### User Interface Walkthrough

1. **System Startup**:
   ```
   Starting Class Registry View System...
   ```

2. **Authentication & Login** (simulated):
   ```
   === Professor Class Registry View ===
   Status: Logged in as Professor.
   Available Classes:
   ```

3. **Class Selection Interface**:
   ```
   1. Mathematics 101 [Register]
   2. Physics 201 [Register]
   3. Chemistry 301 [Register]
   ```

4. **Class Selection Prompt**:
   ```
   Enter the number of the class to view register (1-3):
   ```
   *Type the number corresponding to your desired class and press Enter*

5. **Register Information Display**:
   After selecting a class, the system will display a formatted table with register details:
   ```
   === Loading Register for: Mathematics 101 ===
   
   Class Register Details:
   ================================================================================
   Date         Absences   Disciplinary Notes    Delays   Justification         
   ================================================================================
   2023-10-01   2          None                 1        Traffic               
   2023-10-02   0          Disruptive behavior  0        N/A                   
   2023-10-03   1          None                 3        Public transport delay
   2023-10-04   0          Late submission      2        Family emergency      
   2023-10-05   3          None                 0        Medical appointment   
   ================================================================================
   ```

6. **System Postconditions**:
   ```
   === Postconditions ===
   ✓ Class registry information has been shown.
    ⚠  Connection to SMOS server interrupted.
      (Simulated: Network timeout after displaying data)
   ```

7. **System Completion**:
   ```
   === System Operation Complete ===
   ```

## Data Structure

### RegisterEntry Class
Each register entry contains the following information:
- **Date**: The date of the registry entry (format: YYYY-MM-DD)
- **Absences**: Number of student absences for that day
- **Disciplinary Notes**: Any disciplinary incidents recorded
- **Delays**: Number of student delays for that day
- **Justification**: Justification for absences/delays

### Sample Data
The system is pre-populated with sample data for demonstration purposes:

| Date | Absences | Disciplinary Notes | Delays | Justification |
|------|----------|--------------------|--------|---------------|
| 2023-10-01 | 2 | None | 1 | Traffic |
| 2023-10-02 | 0 | Disruptive behavior | 0 | N/A |
| 2023-10-03 | 1 | None | 3 | Public transport delay |
| 2023-10-04 | 0 | Late submission | 2 | Family emergency |
| 2023-10-05 | 3 | None | 0 | Medical appointment |

## Troubleshooting

### Common Issues and Solutions

#### Issue 1: "Command 'javac' not found"
**Solution**: JDK is not installed or PATH is not set correctly. Reinstall JDK and verify PATH.

#### Issue 2: "Error: Could not find or load main class ViewRegister"
**Solution**: Ensure you're in the correct directory where ViewRegister.class exists and run:
```bash
java -cp . ViewRegister
```

#### Issue 3: Compilation errors
**Solution**: Check Java version compatibility:
```bash
java -version
```
Ensure you have at least Java 8 installed.

#### Issue 4: Input handling issues
**Solution**: The system uses Scanner for input. If you encounter issues:
- Enter only numbers (1, 2, or 3)
- Do not enter any other characters
- Press Enter after typing your selection

#### Issue 5: Program terminates unexpectedly
**Solution**: The system includes proper error handling and will display:
```
Error occurred: [error message]
Simulating graceful shutdown...
```

## System Limitations

### Known Limitations
1. **Data Storage**: Currently uses in-memory sample data only
2. **User Interface**: Command-line interface only (no GUI)
3. **Authentication**: Simulated login process (no real authentication)
4. **Data Persistency**: No database or file storage capability
5. **Real-time Updates**: Static sample data only

### Workarounds
- To modify the sample data, edit the `initializeSampleData()` method in the code
- To add more classes, modify the `classes` array in the `viewClassRegister()` method

## Security Considerations

### Current Security Features
- Input validation for user selections
- Error handling for unexpected inputs
- Graceful program termination

### Recommended Security Enhancements (for production)
1. Implement actual user authentication
2. Add database security layers
3. Implement SSL/TLS for data transmission
3. Add user session management
4. Implement access control and authorization

## Code Organization

The system uses a modular approach with three main components:

### 1. `ViewRegister` (Main Class)
- Entry point of the application
- Handles user interaction flow
- Coordinates between different components

### 2. `ClassRegistry` (Inner Class)
- Manages register entries for a specific class
- Contains sample data initialization
- Provides methods to access registry data

### 3. `RegisterEntry` (Inner Class)
- Data model for individual register entries
- Provides formatted display methods
- Includes getter methods for data access

## Extending the System

### Adding New Features
To extend the system functionality, you can:

1. **Add More Classes**:
   ```java
   String[] classes = {"Mathematics 101", "Physics 201", "Chemistry 301", "Biology 401"};
   ```

2. **Modify Sample Data**:
   ```java
   private void initializeSampleData() {
       // Add more entries or modify existing ones
   }
   ```

3. **Add New Registry Fields**:
   Modify the `RegisterEntry` class to include additional fields and update display methods accordingly.

## Support and Contact

For technical support or questions about the Class Registry View System:

### Documentation Updates
Check this manual for the latest updates and information.

### System Status
The system is designed for educational and demonstration purposes. It showcases:
- Use case implementation in Java
- Object-oriented programming principles
- Console-based user interfaces
- Error handling and validation

## Version History

### Version 1.0 (Current)
- Initial release
- Basic class selection functionality
- Register information display
- Sample data implementation
- Error handling and input validation

### Future Enhancements (Planned)
- Database integration
- Graphical user interface
- Real-time data updates
- Export functionality (CSV, PDF)
- Multi-user support
- Advanced search and filtering

## Conclusion

The Class Registry View System provides a practical implementation of the ViewRegister use case. It demonstrates how professors can access and view class register information through a console-based interface. The system includes error handling, data validation, and simulates real-world scenarios including server connection management.

For best results, ensure you have the correct Java version installed and follow the installation instructions carefully. The system is designed to be educational, demonstrating software engineering principles while providing a functional application.

Happy Teaching!
```