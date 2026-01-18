```markdown
# View List of Justifications - Administrator Software Manual

## Overview

The **View List of Justifications** software is a Java-based application designed for school administrators to view and manage student absence justifications. This application implements the "ViewListofJustifications" use case where administrators can visualize student absences with clear color-coding (green for justified, red for unjustified) and manage server connections to the SMOS (Student Management Operation System) server.

## Main Features

### 1. **Administrator Authentication**
- Secure login system requiring administrator credentials
- Hardcoded demo credentials (Username: `admin`, Password: `admin123`)
- Modal login dialog preventing access without proper authentication

### 2. **Student Absence Visualization**
- Tabular display of all student absences
- Color-coded rows:
  - **Green**: Justified absences
  - **Red**: Unjustified absences
- Display of absence details including:
  - Date of absence
  - Reason for absence
  - Justification status

### 3. **SMOS Server Management**
- Real-time connection status display
- One-click disconnection functionality
- Visual indication of connection status (green for connected, red for disconnected)

### 4. **Data Management**
- Sample data preloaded for demonstration
- Clear separation of justified vs unjustified absences
- Statistics display showing counts of each absence type

## System Requirements

### Software Requirements
- **Java Development Kit (JDK)**: Version 8 or higher
- **Operating System**: Windows, macOS, or Linux
- **Memory**: Minimum 512MB RAM
- **Display**: Minimum 1024x768 resolution

### Dependencies
The application uses standard Java Swing libraries, which are included in all standard Java installations:
- `javax.swing.*`
- `java.awt.*`
- Standard Java Collections Framework

## Installation Guide

### Step 1: Install Java
1. **Windows/macOS/Linux**:
   - Download JDK from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html)
   - Or use OpenJDK from [Adoptium](https://adoptium.net/)
   
2. **Verify Installation**:
   Open terminal/command prompt and type:
   ```bash
   java -version
   ```
   You should see Java version information.

### Step 2: Get the Application Files
1. Save the `ViewListofJustifications.java` file to your computer
2. Ensure you have the complete file structure:
   ```
   ViewListofJustifications/
   ├── ViewListofJustifications.java
   ```

### Step 3: Compile the Application
1. Open terminal/command prompt
2. Navigate to the directory containing `ViewListofJustifications.java`
3. Compile the Java file:
   ```bash
   javac ViewListofJustifications.java
   ```
   This will create several `.class` files:
   - `ViewListofJustifications.class`
   - `LoginDialog.class`
   - `JustificationFrame.class`
   - `AbsenceRecord.class`

### Step 4: Run the Application
Execute the compiled application:
```bash
java ViewListofJustifications
```

## How to Use the Application

### Step 1: Administrator Login
1. Launch the application using the command above
2. The login window will appear:
   ![Login Screen](data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMzUwIiBoZWlnaHQ9IjIwMCI+CiAgPHJlY3Qgd2lkdGg9IjM1MCIgaGVpZ2h0PSIyMDAiIGZpbGw9IiNmZmZmZmYiLz4KICA8dGV4dCB4PSIxNzUiIHk9IjMwIiB0ZXh0LWFuY2hvcj0ibWlkZGxlIiBmb250LWZhbWlseT0iQXJpYWwiIGZvbnQtc2l6ZT0iMTQiPkFkbWluaXN0cmF0b3IgTG9naW48L3RleHQ+CiAgPHRleHQgeD0iNTAiIHk9IjgwIiBmb250LWZhbWlseT0iQXJpYWwiIGZvbnQtc2l6ZT0iMTIiPlVzZXJuYW1lOjwvdGV4dD4KICA8dGV4dCB4PSI1MCIgeT0iMTIwIiBmb250LWZhbWlseT0iQXJpYWwiIGZvbnQtc2l6ZT0iMTIiPlBhc3N3b3JkOjwvdGV4dD4KPC9zdmc+)

3. Enter the administrator credentials:
   - **Username**: `admin`
   - **Password**: `admin123`
   
   *Note: These are demo credentials. In a production environment, these would be validated against a secure database.*

4. Click "Login" or press Enter

### Step 2: View Student Absences
Once logged in, the main application window opens showing:
1. **Header Section**:
   - Application title
   - Logged-in user information
   - Server connection status (green for connected)

2. **Main Table**:
   - List of student absences organized by date
   - Color coding:
     - **Light Green rows**: Justified absences
     - **Light Red rows**: Unjustified absences
   - Columns:
     - **Date**: Absence date in YYYY-MM-DD format
     - **Reason**: Description of absence reason
     - **Status**: "Justified" or "Unjustified"

3. **Status Bar**:
   - Shows total absences count
   - Shows justified vs unjustified counts
   - Current connection status

### Step 3: Manage SMOS Server Connection
1. **View Current Status**:
   - Connection status shown in top-right corner
   - Green text: Connected to SMOS Server
   - Red text: Disconnected from SMOS Server

2. **Disconnect from Server**:
   - Click "Disconnect from SMOS Server" button
   - Confirmation dialog appears
   - Connection status changes to red
   - Button becomes disabled

   *Note: This simulates the postcondition where the administrator interrupts the connection to the SMOS server.*

### Step 4: Application Navigation
- **Scroll**: Use mouse wheel or scroll bars to navigate through absences
- **Select Rows**: Click on any row to select it
- **Column Resizing**: Drag column borders to adjust widths
- **Window Management**: Resize the window as needed

## Sample Data Overview

The application includes demonstration data for a student's absences throughout the academic year:

### Justified Absences (Displayed in Green):
- 2023-01-10: Flu
- 2023-03-22: Family Event
- 2023-05-18: Medical Appointment
- 2023-09-12: Sports Competition
- 2023-11-14: Dental Appointment

### Unjustified Absences (Displayed in Red):
- 2023-02-15: Unknown
- 2023-04-05: Unknown
- 2023-06-01: Unknown
- 2023-10-30: Unknown
- 2023-12-05: Unknown

## Troubleshooting

### Common Issues and Solutions:

1. **"Java not found" error**:
   ```
   Solution: Ensure Java is installed and added to system PATH
   ```

2. **"Could not find or load main class"**:
   ```
   Solution: Compile the application first using javac
   ```

3. **Login fails even with correct credentials**:
   ```
   Solution: Make sure you're using exact credentials:
   Username: admin
   Password: admin123
   ```

4. **Application window doesn't appear**:
   ```
   Solution: Check if you're running in a GUI-enabled environment
   ```

5. **Colors not displaying correctly**:
   ```
   Solution: This usually indicates a Java Swing rendering issue.
   Try updating your Java version or running on a different OS.
   ```

### Performance Optimization:
- Close other applications if experiencing slowdowns
- Ensure adequate RAM availability
- For large datasets, consider increasing JVM heap space:
  ```bash
  java -Xmx512m ViewListofJustifications
  ```

## Security Considerations

⚠️ **Important Security Notes**:

1. **Demo Credentials**: The current version uses hardcoded credentials for demonstration only. In production:
   - Implement secure password storage
   - Use database authentication
   - Implement password hashing

2. **Data Storage**: Current data is hardcoded. For production:
   - Connect to a real database
   - Implement data encryption
   - Add user session management

3. **Network Security**: The SMOS server disconnection is simulated. In production:
   - Implement proper network protocols
   - Add SSL/TLS encryption
   - Include connection retry logic

## Extending the Application

### Adding Real Data Sources:
1. Modify the `loadSampleData()` method in `JustificationFrame.java`
2. Replace with database connection code
3. Implement proper data models

### Customizing the Interface:
1. Modify colors in `JustificationRenderer` class
2. Adjust table columns in `initializeComponents()` method
3. Change fonts and sizes in UI components

### Adding Features:
1. **Search/Filter**: Add search functionality above the table
2. **Export**: Add export to CSV/PDF functionality
3. **Print**: Implement print preview and printing
4. **Multiple Students**: Add student selection dropdown

## Best Pract for Administrators

1. **Regular Review**: Check student absences weekly
2. **Documentation**: Document all justification decisions
3. **Follow-up**: Investigate repeated unjustified absences
4. **Server Management**: Only disconnect from SMOS when necessary
5. **Data Backup**: Ensure regular backups of absence records

## Support and Maintenance

### Log Files:
The application currently doesn't generate log files. For production:
- Implement logging using Log4j or similar
- Log authentication attempts
- Track absence viewing activities

### Updates:
- Regularly update Java runtime
- Monitor for security updates
- Test with new Java versions

## Related Use Cases

This application implements the "ViewListofJustifications" use case. Related use cases that could extend this system:

1. **SplitTaTtAlTeloregistration**: The predecessor use case
2. **AddJustification**: For adding new justifications
3. **EditJustification**: For modifying existing justifications
4. **GenerateReports**: For creating absence reports

## Conclusion

The View List of Justifications application provides administrators with a user-friendly interface to monitor and manage student absence justifications. The color-coded system allows for quick visual assessment, while the server management functionality helps maintain system integrity.

Remember that this is a demonstration version. For production deployment, additional security measures, data persistence, and user management features should be implemented.

For questions or support, please consult your system administrator or refer to the source code documentation.
```

This manual provides comprehensive documentation for the View List of Justifications application, including installation instructions, usage guidelines, troubleshooting tips, and considerations for production deployment.