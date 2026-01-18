```markdown
# InsertNewClass Application User Manual

## Overview

The InsertNewClass application is a Java-based GUI program designed for school administrators to manage class records in the school database. This application implements the specific use case where administrators can insert new classes into the archive while viewing existing classes for a particular academic year.

## Main Features

### 1. **New Class Form Interface**
   - Clean, intuitive form-based interface for entering class details
   - Three required fields: Class Name, Address, and Academic Year
   - Real-time SMOS server connection status monitoring
   - Form validation with detailed error messages

### 2. **SMOS Server Integration**
   - Real-time connection status to SMOS server
   - Automatic connection checking on startup
   - Manual reconnection capability when disconnected
   - Server status visual indicators (green for connected, red for disconnected)

### 3. **Data Validation**
   - Comprehensive field validation ensuring data quality:
     - Non-empty field checking
     - Class name format validation (letters, numbers, spaces, hyphens)
     - Address length restrictions
     - Academic year format validation (YYYY-YYYY)
     - Consecutive year validation
     - Year range validation (1900 to current year + 10)
   - Duplicate class name prevention for same academic year

### 4. **Database Operations**
   - Secure database connection with transaction support
   - Prepared statements to prevent SQL injection
   - Automatic commit/rollback for data integrity
   - Resource cleanup after database operations

### 5. **Error Handling**
   - Clear error messages for data validation failures
   - Database connection error handling
   - SMOS server interruption handling
   - Graceful failure recovery mechanisms

## System Requirements

### Hardware Requirements
- Minimum 2GB RAM
- 500MB available disk space
- Network connection for SMOS server access

### Software Requirements
- **Operating System**: Windows 10+, macOS 10.14+, Linux (any modern distribution)
- **Java Runtime**: Java JDK 8 or higher
- **Database**: MySQL 5.7 or higher with school_db database
- **MySQL Connector**: MySQL JDBC Driver (mysql-connector-java-8.0.23 or higher)

## Installation Guide

### Step 1: Install Java Development Kit (JDK)
1. Download JDK from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html)
2. Run the installer and follow the prompts
3. Set JAVA_HOME environment variable:
   - **Windows**: `set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_311`
   - **Linux/macOS**: `export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64`
4. Add Java to PATH:
   - **Windows**: Add `%JAVA_HOME%\bin` to PATH
   - **Linux/macOS**: Add `$JAVA_HOME/bin` to PATH

### Step 2: Install and Configure MySQL Database
1. Install MySQL Server from [MySQL website](https://dev.mysql.com/downloads/mysql/)
2. Start MySQL service:
   - **Windows**: Use Serv manager or `net start mysql`
   - **Linux**: `sudo systemctl start mysql`
   - **macOS**: `brew serv start mysql`
3. Create the school_db database:
   ```sql
   CREATE DATABASE school_db;
   USE school_db;
   CREATE TABLE classes (
     id INT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(100) NOT NULL,
     address VARCHAR(200) NOT NULL,
     academic_year VARCHAR(9) NOT NULL,
     created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
   );
   ```

### Step 3: Download MySQL JDBC Driver
1. Download from [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)
2. Extract the JAR file (e.g., `mysql-connector-java-8.0.23.jar`)

## Running the Application

### Method 1: Compile and Run from Command Line
1. Ensure you have the application file `InsertNewClassApp.java`
2. Compile the Java file with MySQL connector:
   ```bash
   javac -cp ".;mysql-connector-java-8.0.23.jar" InsertNewClassApp.java
   ```
3. Run the application:
   ```bash
   java -cp ".;mysql-connector-java-8.0.23.jar" InsertNewClassApp
   ```

### Method 2: Using an IDE (Recommended)
1. Open your preferred Java IDE (Eclipse, IntelliJ IDEA, NetBeans)
2. Create a new Java project
3. Add the MySQL connector JAR to project libraries
4. Copy `InsertNewClassApp.java` into the source folder
5. Run the main class

## Configuration

### Database Connection Settings
Edit the following constants in the code if needed:
```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/school_db";
private static final String DB_USER = "admin";
private static final String DB_PASS = "password";
```

**Note**: In production environments, these should be externalized to configuration files.

### SMOS Server Simulation
The application simulates SMOS server connection with an 80% success rate. Modify the simulation in the `checkSMOSServerConnection()` method if needed.

## User Interface Guide

### Main Form Components
1. **Class Name Field**
   - Required field
   - Maximum 100 characters
   - Accepts: letters, numbers, spaces, hyphens

2. **Address Field**
   - Required field
   - Maximum 200 characters
   - Accepts any valid address format

3. **Academic Year Field**
   - Required field
   - Format: YYYY-YYYY (e.g., 2023-2024)
   - Must be consecutive years
   - Valid range: 1900 to current year + 10

4. **SMOS Server Status Panel**
   - Displays connection status
   - Green label: Connected
   - Red label: Disconnected
   - Reconnect button for manual connection attempts

5. **Action Buttons**
   - **Save**: Insert class into database (enabled only when SMOS server is connected)
   - **Cancel**: Close the form and return to class list view

### Step-by-Step Usage

#### Step 1: Launch the Application
- Start the application using one of the methods described above
- The application will check SMOS server connection automatically

#### Step 2: Prepare to Create New Class
- Ensure you are viewing the class list for the desired academic year (precondition)
- Click "New Class" button (simulated by opening this application)

#### Step 3: Fill Out the Form
1. Enter **Class Name** (e.g., "Mathematics 101")
2. Enter **Address** (e.g., "Room 101, Science Building")
3. Enter **Academic Year** (e.g., "2023-2024")

#### Step 4: Check SMOS Server Status
- Verify SMOS Server status is "Connected" (green label)
- If "Disconnected" (red label), click "Reconnect" button
- Wait for connection attempt to complete

#### Step 5: Save the Class
- Click the "Save" button
- If validation passes, class will be inserted into database
- Success message will appear with form cleared for next entry

## Troubleshooting

### Common Issues and Solutions

#### 1. "Database driver not found" Error
**Problem**: MySQL JDBC driver not in classpath
**Solution**: Ensure MySQL connector JAR is included in compilation and execution

#### 2. "Cannot save: Connection to SMOS server interrupted"
**Problem**: SMOS server simulation shows disconnected
**Solution**: Click "Reconnect" button and try again

#### 3. "Database connection interrupted"
**Problem**: MySQL database is not running or network issue
**Solution**: 
   - Check MySQL service is running
   - Verify database credentials in code
   - Check network connectivity to database server

#### 4. Data Validation Errors
**Common validation errors**:
   - Empty fields: Fill all required fields
   - Invalid academic year format: Use YYYY-YYYY format
   - Non-consecutive years: Ensure second year is exactly one year after first
   - Duplicate class name: Choose unique name for the academic year

### Database Connection Test
To test database connectivity separately:
```bash
mysql -u admin -p school_db
```

### SMOS Server Simulation
The SMOS server connection is random (80% success). If testing, you may need multiple attempts.

## Error Handling

The application handles various error conditions gracefully:

### 1. **Data Validation Errors**
- Shows specific error messages with guidance
- Highlights which validation rule was violated
- Allows immediate correction without losing entered data

### 2. **Database Errors**
- Connection errors trigger SMOS server status update
- Duplicate entries show warning with details
- Transaction failures automatically rollback

### 3. **Network/SMOS Server Errors**
- Reconnection mechanism with user feedback
- Clear status indicators
- Operation blocking until connection restored

### 4. **User Interruption**
- Cancel confirmation dialog
- Graceful form closure
- Simulated return to class list view

## Best Pract

### For Users
1. Always verify SMOS server connection before attempting to save
2. Use descriptive but concise class names
3. Follow the academic year format strictly
4. Check for duplicates using the class viewing feature first
5. Keep the software updated with the latest JDBC drivers

### For Administrators
1. Regular database backups
2. Monitor application logs for error patterns
3. Keep Java Runtime Environment updated
4. Secure database credentials appropriately
5. Regular testing of SMOS server connectivity

## Security Considerations

### Current Implementation
- Database credentials are hardcoded (for demonstration only)
- Basic SQL injection prevention via prepared statements
- No user authentication in this standalone version

### Production Recommendations
1. **Externalize credentials** to secure configuration files
2. **Implement proper authentication** for administrators
3. **Use connection pooling** for database connections
4. **Encrypt sensitive data** in the database
5. **Implement audit logging** for all insert operations
6. **Add session management** for multi-user environments

## Extending the Application

### Possible Enhancements
1. Add class editing and deletion capabilities
2. Implement bulk class import feature
3. Add search and filter functionality
4. Create reports and statistics
5. Add teacher assignment to classes
6. Implement student enrollment management

### Integration Points
- Integrate with existing school management systems
- Connect to student information systems
- Interface with attendance tracking systems
- Link to academic performance databases

## Support and Contact

### Getting Help
For issues related to:
- **Installation**: Check system requirements and dependency installation
- **Database**: Verify MySQL installation and connectivity
- **Runtime**: Check Java installation and classpath configuration

### Reporting Issues
When reporting issues, please include:
1. Operating system and version
2. Java version (`java -version`)
3. MySQL version
4. Exact error messages
5. Steps to reproduce the issue

## License and Acknowledgments

### License
This application is provided for educational and demonstration purposes.

### Dependencies
- Java Swing (included with Java SE)
- MySQL Connector/J (GPL license)
- MySQL Database (various licenses available)

### Acknowledgments
- Use case based on standard school administration workflows
- GUI design following Java Swing best pract
- Database operations using industry-standard patterns

---

**Note**: This application is a demonstration implementation. For production use, additional security measures, error handling, and performance optimizations should be implemented.
```