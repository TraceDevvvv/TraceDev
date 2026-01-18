```markdown
# ViewClassesList - ATA Staff Class Management System

## 📋 Overview

ViewClassesList is a Java-based desktop application designed for ATA (Assistive Technology Academy) staff to view and manage class information. The system provides a secure login interface for staff members and displays a comprehensive list of all classes stored in the database, with functionality to access detailed registry information for each class.

## ✨ Main Features

1. **Secure Staff Authentication**
   - Role-based login system for ATA staff
   - Password-protected access
   - Input validation and error handling

2. **Class Information Display**
   - View all classes in a tabular format
   - Display class ID, name, instructor, and schedule
   - Clean, intuitive graphical user interface

3. **Class Registry Access**
   - Select any class from the list
   - Access detailed registry information
   - Unique key-based access to each class's registry

4. **Database Integration**
   - Embedded H2 database for portability
   - Automatic database initialization
   - Sample data pre-loaded for demonstration

## 🛠️ Installation & Dependencies

### Prerequisites

1. **Java Development Kit (JDK)**
   - Version 8 or higher
   - Download from [Oracle](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or use OpenJDK

2. **H2 Database Driver**
   - The application includes H2 database support out of the box
   - No additional installation required for the embedded database

### Installation Steps

#### Option 1: Using Command Line (Simple)

1. **Save the Java file**
   ```bash
   # Save the code as ViewClassesList.java
   ```

2. **Compile the application**
   ```bash
   # Navigate to the directory containing ViewClassesList.java
   cd /path/to/your/directory
   
   # Compile the Java file
   javac ViewClassesList.java
   ```

3. **Run the application**
   ```bash
   # Run the compiled program
   java ViewClassesList
   ```

#### Option 2: Using IDE (Recommended for Development)

1. **Create a new Java project** in your preferred IDE (Eclipse, IntelliJ IDEA, NetBeans)

2. **Add H2 Database Driver** (if not automatically included):
   - Download H2 database jar from [https://www.h2database.com](https://www.h2database.com)
   - Add `h2-*.jar` to your project's classpath

3. **Copy the Java code** into your project's main class

4. **Run the project** using your IDE's run configuration

### Dependencies Management

The application requires the following dependencies:

```xml
<!-- If using Maven, add to pom.xml -->
<dependencies>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <version>2.1.214</version>
    </dependency>
</dependencies>
```

## 🚀 Getting Started

### First-Time Setup

1. **Launch the Application**
   ```bash
   java ViewClassesList
   ```
   You should see the ATA Staff Login window appear.

2. **Database Initialization**
   - On first run, the application automatically:
     - Creates necessary database tables
     - Inserts sample staff credentials
     - Adds example class data
   - This requires no manual intervention

### Default Login Credentials

The system comes pre-loaded with sample staff accounts:

| Username | Password | Role |
|----------|----------|------|
| `staff1` | `pass1`  | staff |
| `admin`  | `admin123` | staff |

**Note:** In a production environment, these default credentials should be changed immediately.

## 🖥️ User Guide

### Step 1: Login

1. **Open the Application**
   - Launch the program to see the login screen
   - The window title reads "ATA Staff Login"

2. **Enter Credentials**
   - **Username:** Enter your staff username
   - **Password:** Enter your password
   
   ![Login Screen](login_screenshot.png)

3. **Authenticate**
   - Click the "Login" button
   - Successful login displays a confirmation message
   - Failed attempts show appropriate error messages

### Step 2: View Class List

1. **Main Dashboard**
   - After successful login, the Class List window opens
   - This displays all available classes in a table format

2. **Table Columns**
   - **ID:** Unique identifier for each class
   - **Class Name:** Name of the class/course
   - **Instructor:** Name of the instructor teaching the class
   - **Schedule:** Class timing and days

3. **Sample Data Displayed**
   - Mathematics 101 - Dr. Smith - Mon 10:00-12:00
   - Physics 201 - Prof. Johnson - Tue 14:00-16:00
   - Chemistry 150 - Dr. Lee - Wed 09:00-11:00
   - Biology 300 - Prof. Davis - Thu 13:00-15:00

### Step 3: Access Class Registry

1. **Select a Class**
   - Click on any row in the table to select a class
   - The selected row will be highlighted

2. **View Registry**
   - Click the "View Registry" button at the bottom
   - A message box shows which class registry would be opened
   
   ![Registry Access](registry_screenshot.png)

3. **Registry Details**
   - The current version displays a confirmation message
   - In a full implementation, this would open a new window with:
     - Student enrollment list
     - Attendance records
     - Grades and assessments
     - Class-specific details

### Step 4: Navigation

- **Table Navigation:**
  - Use scroll bars to view all classes
  - Click column headers to sort data
  - Resize columns by dragging headers

- **Application Controls:**
  - Close the application using the window close button (X)
  - All unsaved changes are automatically handled

## 🛡️ Security Features

1. **Authentication**
   - Password-protected login
   - Role-based access control (staff-only)
   - Session management

2. **Data Protection**
   - In-memory database (H2) for demo purposes
   - SQL injection prevention using Prepared Statements
   - Input validation on all fields

3. **Best Pract**
   - **Warning:** This demo uses plain text passwords. In production:
     - Implement password hashing (bcrypt, PBKDF2)
     - Use environment variables for database credentials
     - Implement SSL/TLS for database connections

## 🔧 Technical Details

### Database Schema

```sql
-- Staff table for authentication
CREATE TABLE staff (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    role VARCHAR(20) DEFAULT 'staff'
);

-- Classes table for class information
CREATE TABLE classes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    class_name VARCHAR(100) NOT NULL,
    instructor VARCHAR(100) NOT NULL,
    schedule VARCHAR(50) NOT NULL
);
```

### File Structure

```
ViewClassesList.java           # Main application file
├── ViewClassesList class      # Entry point
├── ClassRecord class          # Data model for classes
├── DatabaseConnection class   # Database operations
├── LoginFrame class           # Login GUI
└── MainFrame class           # Main application GUI
```

### Error Handling

The application handles various error scenarios:

1. **Database Errors:**
   - Connection failures
   - Query execution errors
   - Data retrieval issues

2. **User Input Errors:**
   - Empty username/password
   - Invalid credentials
   - No class selection

3. **System Errors:**
   - Missing H2 database driver
   - Memory allocation issues
   - GUI rendering problems

## 📊 Customization

### Adding More Classes

To add more classes to the system, modify the `initializeDatabase()` method in the `DatabaseConnection` class:

```java
String insertClasses = "INSERT INTO classes (class_name, instructor, schedule) VALUES " +
                      "('Mathematics 101', 'Dr. Smith', 'Mon 10:00-12:00'), " +
                      "('Physics 201', 'Prof. Johnson', 'Tue 14:00-16:00'), " +
                      // Add more classes here
                      "('New Class Name', 'Instructor Name', 'Schedule Details');";
```

### Modifying GUI

The GUI uses Java Swing components. Key areas for customization:

1. **Colors and Fonts:** Modify in LoginFrame and MainFrame constructors
2. **Layout:** Adjust GridBagConstraints parameters
3. **Window Size:** Change setSize() method parameters

## 🚨 Troubleshooting

### Common Issues and Solutions

1. **"H2 Database Driver not found" Error**
   ```
   Solution: Download h2-*.jar and add to classpath
   Command: javac -cp ".:h2-2.1.214.jar" ViewClassesList.java
   ```

2. **Application Won't Start**
   ```
   Check: Java version (java -version)
   Ensure: Proper JDK installation
   Verify: File is named ViewClassesList.java
   ```

3. **Login Fails Even with Correct Credentials**
   ```
   Check: Database initialization completed
   Reset: Delete database files and restart
   Verify: Username/password case sensitivity
   ```

4. **No Data Displayed**
   ```
   Check: Database connection
   Verify: classes table exists
   Restart: Application to reinitialize database
   ```

### System Requirements Verification

```bash
# Check Java installation
java -version

# Check Java compiler
javac -version

# Check available memory
java -XshowSettings:vm
```

## 🔄 Extending the Application

### Future Enhancements

1. **Registry Implementation:**
   - Add Student class with details
   - Implement enrollment management
   - Add attendance tracking

2. **Additional Features:**
   - Class creation/editing
   - Search and filter functionality
   - Export to CSV/PDF
   - Reports generation

3. **Advanced Security:**
   - Password encryption
   - Audit logging
   - User activity tracking

### Integration Possibilities

- Connect to external student information systems
- Integrate with scheduling software
- Add calendar syncing functionality
- Implement REST API for web access

## 📞 Support & Resources

### Getting Help

For assistance with the ViewClassesList system:

1. **Application Logs:**
   - Check console output for error messages
   - Database initialization messages appear on startup

2. **Common Solutions:**
   - Restart the application
   - Verify database connection
   - Check file permissions

### Additional Resources

- **Java Documentation:** [Oracle Java Docs](https://docs.oracle.com/javase/)
- **H2 Database:** [Official Documentation](https://www.h2database.com)
- **Swing Tutorial:** [Oracle GUI Tutorial](https://docs.oracle.com/javase/tutorial/uiswing/)

---

**Version:** 1.0  
**Last Updated:** October 2023  
**Developed By:** ChatDev Software Solutions  
**License:** For demonstration and educational use

**Note:** This is a demonstration application. For production use, implement additional security measures, error handling, and database persistence strategies.
```