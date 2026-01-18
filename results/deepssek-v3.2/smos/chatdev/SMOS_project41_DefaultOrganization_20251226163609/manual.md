```markdown
# EditNote Application - User Manual

## Overview
The EditNote application is a Java-based desktop software designed for educational administrators to manage disciplinary records. The application allows authorized administrators to view, edit, and save changes to disciplinary notes for students.

## Key Features
2. **Multi-Database Support**: Works with H2 (embedded), MySQL, or mock data storage
3. **Form Validation**: Ensures data integrity with comprehensive field validation
4. **Connection Recovery**: Handles database connection interruptions gracefully
5. **Simple GUI**: User-friendly interface built with Java Swing

## Prerequisites

### Java Requirements
- Java Development Kit (JDK) 8 or higher
- Java Runtime Environment (JRE) 8 or higher

### Database Options (Choose One)

#### Option 1: H2 Database (Recommended for Quick Start)
- No additional setup required - H2 is embedded
- Automatically creates database files in application directory

#### Option 2: MySQL Database
- MySQL Server 5.7 or higher
- Download MySQL Connector JAR from https://dev.mysql.com/downloads/connector/j/
- Place mysql-connector-java-8.0.33.jar (or similar) in project directory

#### Option 3: Mock Data Mode
- No database required
- Uses in-memory data for demonstration purposes

## Installation

### Step 1: Download Application Files
1. Download `EditNoteApp.java` to your computer
2. Create a project folder and place the Java file inside

### Step 2: Setup Database (Optional)
**For MySQL Users:**
```bash
# Run the database setup script
mysql -u username -p < databasesetup.sql
```

### Step 3: Compile the Application
Open terminal/command prompt in your project directory and run:

```bash
# Basic compilation (for H2 or Mock mode)
javac EditNoteApp.java

# For MySQL support (include connector JAR)
javac -cp ".;mysql-connector-java-8.0.33.jar" EditNoteApp.java
```

## Running the Application

### Starting the Application
```bash
# Basic run (H2/Mock mode)
java EditNoteApp

# With MySQL connector
java -cp ".;mysql-connector-java-8.0.33.jar" EditNoteApp
```

### First-Time Setup
1. **Database Selection**: On first run, the application will prompt for database mode:
   ```
   Select database mode:
   1. H2 (embedded database)
   2. MySQL
   3. Mock Data (no database)
   ```

2. **MySQL Configuration** (if selected):
   - Enter database URL (default: `jdbc:mysql://localhost:3306/discipline_db`)
   - Enter username (default: `root`)
   - Enter password

3. **Note ID Entry**:
   - Application prompts for the note ID to edit
   - Enter an existing ID or use the default test data

## User Interface Guide

### Main Application Window
The main window consists of the following sections:

**Top Section:**
- **Database Mode Indicator**: Shows current database (H2/MySQL/MOCK)
- **Note ID Display**: Shows the ID of the note being edited

**Form Fields:**
1. **Student Field**: 
   - Input: Student's full name
   - Validation: Letters, spaces, hyphens, and apostrophes only
   - Required field

2. **Description Field**:
   - Text area for disciplinary details
   - Maximum: 1000 characters
   - Required field

3. **Teacher Field**:
   - Input: Teacher's full name
   - Validation: Letters, spaces, hyphens, and apostrophes only
   - Required field

4. **Date Field**:
   - Input: Date of incident in YYYY-MM-DD format
   - Validation: Valid date, not in future
   - Example: 2023-10-15
   - Required field

**Control Buttons:**
- **Save Button**: Validates and saves changes to the database
- **Cancel Button**: Discards changes and returns to registry screen
- **Exit Button**: Closes the application

## Common Operations

### Editing a Disciplinary Note
1. **Login**: Ensure you're logged in as administrator (simulated in current version)
2. **Load Note**: Enter note ID when prompted
3. **Edit Fields**: Modify any field as needed:
   - Click in the field you want to edit
   - Type new information
   - Follow validation rules shown in tooltips
4. **Save Changes**:
   - Click "Save" button
   - Application validates all fields
   - If validation passes, changes are saved
   - Success message appears
   - Application returns to registry (simulated)

### Handling Errors
- **Validation Errors**: Red border appears on invalid fields with error message
- **Database Errors**: Connection issues automatically switch to mock mode
- **Save Errors**: Error dialog shows specific problem and solution

### Canceling Changes
- Click "Cancel" button to discard all changes
- Returns to registry screen without saving
- No confirmation dialog (immediate action)

## Database Setup Details

### H2 Database
- Location: Creates `disciplinedb.mv.db` in application directory
- Auto-creates disciplinary_notes table on first run
- Includes sample data for testing

### MySQL Database Schema
```sql
CREATE DATABASE IF NOT EXISTS discipline_db;
USE discipline_db;

CREATE TABLE disciplinary_notes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    teacher VARCHAR(100) NOT NULL,
    date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

## Validation Rules

### Field Requirements
1. **Student Name**:
   - Required field
   - 2-100 characters
   - Only letters, spaces, hyphens, apostrophes
   - Example: "John Smith" or "Mary-Jane O'Connor"

2. **Description**:
   - Required field
   - 10-1000 characters
   - Can contain any text
   - Minimum 10 characters ensures meaningful content

3. **Teacher Name**:
   - Required field
   - 2-100 characters
   - Only letters, spaces, hyphens, apostrophes
   - Example: "Dr. Johnson"

4. **Date**:
   - Required field
   - Format: YYYY-MM-DD
   - Must be valid calendar date
   - Cannot be future date
   - Example: "2023-10-15"

## Troubleshooting

### Common Issues

**1. "ClassNotFoundException" for H2 or MySQL**
```bash
# For H2: Download H2 JAR and compile with it
javac -cp ".;h2-2.1.214.jar" EditNoteApp.java
java -cp ".;h2-2.1.214.jar" EditNoteApp

# For MySQL: Ensure connector JAR is in classpath
javac -cp ".;mysql-connector-java-8.0.33.jar" EditNoteApp.java
java -cp ".;mysql-connector-java-8.0.33.jar" EditNoteApp
```

**2. MySQL Connection Failed**
- Check MySQL service is running
- Verify database URL, username, and password
- Ensure network connectivity to MySQL server
- Check firewall settings

**3. Date Format Errors**
- Always use YYYY-MM-DD format
- Ensure date is valid (e.g., not February 30)
- Date cannot be in the future
- Example correct format: "2023-12-31"

**4. GUI Display Issues**
- Ensure Java Swing is properly installed
- Check system DPI settings if text appears too small/large
- Try running with different Java versions if layout problems occur

### Debug Mode
To troubleshoot, check the console output:
1. Database initialization messages
2. Connection status information
3. Error messages and validation feedback

## Security Considerations

**Current Version:**
- Simulated authentication (hardcoded check)
- Database credentials stored in plaintext (for demonstration only)

**Production Recommendations:**
1. Implement real authentication system
2. Use encrypted database connections
3. Store credentials securely
4. Implement session management
5. Add audit logging

## Data Management

### Backup and Recovery
**For H2 Database:**
- Backup the `disciplinedb.mv.db` file regularly
- Store backups in secure location

**For MySQL Database:**
```bash
# Backup command
mysqldump -u username -p discipline_db > backup_YYYYMMDD.sql

# Restore command
mysql -u username -p discipline_db < backup_YYYYMMDD.sql
```

### Sample Data
The application includes sample notes for testing:
- ID 1: John Smith, "Class disruption", Mr. Johnson, 2023-10-10
- ID 2: Sarah Miller, "Late submission", Dr. Williams, 2023-10-12

## Performance Tips

1. **For Large Databases**: Use MySQL for better performance
2. **Network Issues**: Switch to H2 mode for offline use
3. **Memory Issues**: Close application when not in use
4. **Form Validation**: Validation occurs client-side for immediate feedback

## Extending the Application

### Adding Authentication
1. Implement `isAdminLoggedIn()` method with real authentication
2. Add user roles and permissions
3. Implement session timeout

### Additional Features
1. **Search Functionality**: Add search panel to find notes
2. **Reporting**: Generate disciplinary reports
3. **Export**: Export notes to CSV/PDF
4. **Bulk Operations**: Edit multiple notes at once
5. **Audit Trail**: Track all changes to notes

## Support

### Getting Help
1. Check console output for error messages
2. Verify database connectivity
3. Ensure all prerequisites are installed
4. Review validation rules for input errors

### Known Limitations
1. Single-note editing only (no bulk operations)
2. Basic simulated authentication
3. No advanced reporting features
4. No audit trail in current version

## Version Information
- **Version**: 1.1
- **Release Date**: October 2023
- **Java Compatibility**: JDK 8+
- **Database Support**: H2, MySQL, Mock Data

## License and Usage
This application is provided for educational and demonstration purposes. For production use, implement proper security measures and database backup strategies.

---

*Note: This manual covers the basic functionality of the EditNote application. For specific implementation details or customization needs, refer to the source code comments and documentation.*
```