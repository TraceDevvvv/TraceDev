```markdown
# Convention History Viewer - User Manual

## 1. Introduction

### 1.1 Welcome
Welcome to the **Convention History Viewer**, a Java desktop application designed for Agency Operators to view historical conventions derived from selected restaurants (points of rest). This tool helps tourism and hospitality professionals track event histories at various dining establishments.

### 1.2 Key Features
- **Complete Runability**: Works out-of-the-box with included H2 in-memory database
- **Intuitive GUI**: User-friendly Swing interface with clear controls and feedback
- **Dual Database Support**: Automatically uses H2 and can connect to MySQL
- **Data Persistence**: Sample conventions pre-loaded for immediate use
- **Error Handling**: Graceful handling of connection issues and invalid inputs
- **Sortable Table**: Click column headers to sort convention data
- **Connection Management**: Visual server status indicators and connection controls

### 1.3 Use Case Implementation
This application addresses the "ViewConventionHistory" use case by providing:
- Access to historical conventions display features
- Ability to upload/view convention data from selected restaurants
- Clear display of convention history for specific dining points
- Handling of ETOUR server connection interruptions

## 2. System Requirements

### 2.1 Minimum Requirements
- **Operating System**: Windows 7+, macOS 10.12+, or Linux with GUI support
- **Java**: Java Runtime Environment (JRE) 8 or higher
- **RAM**: Minimum 512 MB available
- **Disk Space**: 50 MB free space

### 2.2 Recommended Specifications
- **Java**: Java Development Kit (JDK) 11 or higher
- **RAM**: 1 GB or more
- **Screen Resolution**: 1024×768 or higher

### 2.3 Optional Components
- **MySQL Database** (Optional): To use external database instead of H2
  - MySQL 5.7 or higher
  - JDBC connector in classpath

## 3. Installation Guide

### 3.1 Quick Start (Recommended)
For immediate use without compilation, download and run the pre-packaged JAR:

1. **Download Application**:
   - Obtain `ConventionHistoryViewer.jar` file

2. **Run Application**:
   ```bash
   java -jar ConventionHistoryViewer.jar
   ```

### 3.2 Building from Source
If you have source code files (Main.java, ConventionHistoryModel.java, ConventionHistoryView.java, ConventionHistoryController.java, DatabaseInitializer.java):

1. **Compile the Application**:
   ```bash
   javac -cp ".;*" *.java
   ```

2. **Create JAR File** (Optional):
   ```bash
   jar cvfe ConventionHistoryViewer.jar Main *.class
   ```

3. **Run Compiled Application**:
   ```bash
   java -cp ".;*" Main
   ```

### 3.3 Database Dependencies Setup

#### H2 Database (Default - Automatic)
No setup required! The application automatically:
- Downloads required H2 driver (if not in classpath)
- Creates in-memory database on startup
- Populates with sample convention data
- Manages all database operations transparently

#### MySQL Database (Optional)
To use MySQL instead of H2:

1. **Prepare MySQL Server**:
   ```bash
   # Install MySQL (if not installed)
   # Create database:
   CREATE DATABASE etour_db;
   # Or use your existing database
   ```

2. **Configure Application**:
   - Modify `ConventionHistoryModel.java` to use MySQL configuration
   - Add MySQL JDBC driver to classpath

## 4. Getting Started

### 4.1 First Launch
Upon first launch, you'll see:
1. **Welcome Message**: Introduction and usage tips
2. **Auto-Connection**: Application automatically connects to H2 database
3. **Pre-loaded Data**: Sample conventions for 8 different restaurants

### 4.2 Main Interface Overview
The main window consists of three main areas:

#### Control Panel (Top)
- **Restaurant Field**: Enter restaurant name (default: "The Gourmet Hub")
- **Load Conventions Button**: Fetch history for entered restaurant
- **Connect/Disconnect Buttons**: Manage database connection
- **Database Info**: Shows current database type

#### Data Display Area (Center)
- **Convention Table**: Shows ID, Name, Restaurant, Date, and Description
- **Sortable Columns**: Click column headers to sort
- **Scrollable View**: Navigate through potentially large result sets

#### Status Bar (Bottom)
- **Connection Status**: Green = Connected, Red = Disconnected
- **Usage Instructions**: Context-sensitive guidance

## 5. How to Use

### 5.1 Basic Workflow
Follow these steps to view convention history:

1. **Enter Restaurant Name**
   - Type restaurant name in the text field
   - Use sample names: "The Gourmet Hub", "Vineyard Restaurant", etc.
   - Press Enter or click "Load Conventions"

2. **View Results**
   - Conventions appear in the table
   - Sort by clicking column headers
   - Scroll to view all details

3. **Try Different Restaurants**
   - Change restaurant name
   - Click "Load Conventions" again
   - Compare histories across establishments

### 5.2 Advanced Features

#### Connection Management
- **Connect**: Click green "Connect to Server" button
- **Disconnect**: Click red "Disconnect" button
- **Auto-reconnect**: Application prompts to reconnect if needed

#### Sample Restaurant Suggestions
Try these pre-loaded restaurants:
- The Gourmet Hub (3 conventions)
- Vineyard Restaurant (1 convention)
- Green Bistro (1 convention)
- Dragon Palace (1 convention)
- ...and 4 more with sample data

#### Table Interaction
- **Sort**: Click any column header
- **Resize**: Drag column borders
- **Scroll**: Use mouse wheel or scrollbars

### 5.3 Error Scenarios and Solutions

#### "No Conventions Found"
**Cause**: Restaurant name not in database or misspelled
**Solution**:
- Check spelling
- Try sample restaurant names
- Verify connection is active (green status)

#### "Server Disconnected"
**Cause**: Database connection lost or not established
**Solution**:
1. Click "Connect to Server" button
2. If fails, application automatically falls back to H2
3. Check Internet/MySQL server if using external database

#### "Database Driver Not Found"
**Cause**: Missing H2 or MySQL JDBC driver
**Solution**:
- Application automatically downloads H2
- For MySQL, ensure `mysql-connector-java-x.x.x.jar` is in classpath

## 6. Data Management

### 6.1 Sample Data Structure
The application includes 10 sample conventions across 8 restaurants:

| Field | Description | Example |
|-------|-------------|---------|
| ID | Unique identifier | 1, 2, 3... |
| Name | Convention name | "Annual Food Expo 2024" |
| Restaurant | Point of rest | "The Gourmet Hub" |
| Date | Event date | "2024-01-15" |
| Description | Event details | "Largest annual food industry..." |

### 6.2 Adding Custom Data
To add your own convention data:

#### Using H2 Database (Runtime)
While application is running, data is in memory. To persist:
1. Modify `DatabaseInitializer.java`
2. Add your data to `insertSampleData()` method
3. Recompile and run

#### Using MySQL Database
1. Connect to your MySQL server:
   ```sql
   INSERT INTO conventions (name, point_of_rest, date, description)
   VALUES ('Your Convention', 'Your Restaurant', '2024-12-31', 'Your Description');
   ```
2. Use application with MySQL configuration

### 6.3 Data Persistence
- **H2 Database**: Data exists only while application runs (in-memory)
- **MySQL Database**: Data persists between sessions
- **Sample Data**: Always reloaded on H2 startup

## 7. Troubleshooting

### 7.1 Common Issues

#### Application Won't Start
**Symptoms**: No window appears, console shows errors
**Solutions**:
1. Verify Java installation: `java -version`
2. Check file permissions: Ensure you can read all .java/.class files
3. Verify no other application using same port

#### "Class Not Found" Errors
**Solution**:
```bash
# Ensure H2 driver is included
java -cp ".;h2-2.1.214.jar" Main
```

#### GUI Looks Different
**Cause**: Different Look and Feel on your OS
**Solution**: This is normal - application uses system's native look

### 7.2 Performance Tips
- **Large Datasets**: Application handles up to thousands of conventions
- **Memory Usage**: H2 database uses ~10-50MB RAM
- **Startup Time**: First launch may take 2-3 seconds for H2 initialization

### 7.3 Logging and Debugging
The application provides console output:
- **Connection messages**: "Connected to database successfully"
- **Error messages**: "Error loading conventions: [details]"
- **Data operations**: "Loaded X conventions for: Restaurant Name"

To enable detailed logging, run with:
```bash
java -Ddebug=true -jar ConventionHistoryViewer.jar
```

## 8. Technical Architecture

### 8.1 MVC Design Pattern
- **Model** (`ConventionHistoryModel.java`): Data management and database operations
- **View** (`ConventionHistoryView.java`): GUI presentation and user interaction
- **Controller** (`ConventionHistoryController.java`): Business logic and coordination

### 8.2 Database Layers
1. **Primary**: H2 in-memory database (auto-configured)
2. **Fallback**: Automatic switch to H2 if MySQL unavailable
3. **Configuration**: Easy switching between database types

### 8.3 Key Classes
- **Main**: Application entry point and initialization
- **DatabaseInitializer**: Database setup and sample data
- **Schema/Data SQL**: Database structure definitions

## 9. Security Considerations

### 9.1 Data Security
- **In-memory Data**: H2 database data exists only in RAM during runtime
- **No External Storage**: By default, no files are written to disk
- **Connection Security**: MySQL connections use standard JDBC security

### 9.2 Safe Usage Guidelines
1. **Backup Important Data**: Export convention data if using MySQL
2. **Network Security**: If using MySQL over network, ensure secure connection
3. **Access Control**: Restrict application access as needed for your organization

### 9.3 Privacy
- Application does not collect usage statistics
- No network calls except database connections
- All processing occurs locally

## 10. Support and Resources

### 10.1 Getting Help
For assistance with:
- **Installation Issues**: Check Java version and file permissions
- **Usage Questions**: Review Section 5 (How to Use)
- **Technical Problems**: Check console output for error messages

### 10.2 Sample Use Cases
1. **Tourism Agency**: Track event histories at partner restaurants
2. **Event Planner**: Review past conventions at potential venues
3. **Hospitality Student**: Study convention trends in food industry
4. **Restaurant Manager**: View establishment's event history

### 10.3 Extending the Application
Developers can extend functionality by:
1. Adding new database fields in `Convention` class
2. Implementing search/filter capabilities
3. Adding export functionality (CSV, PDF)
4. Integrating with external event APIs

## 11. Frequently Asked Questions

### Q: Do I need to install MySQL?
**A**: No! The application uses H2 in-memory database by default. MySQL is optional.

### Q: Can I add my own convention data?
**A**: Yes, modify the `DatabaseInitializer.java` class to include your data.

### Q: Is there a limit to how many conventions I can view?
**A**: No hard limit, but very large datasets may affect performance.

### Q: Can I run this on Linux without GUI?
**A**: The application requires a graphical interface (Swing).

### Q: How do I update the application?
**A**: Replace the JAR file with newer version, preserving any MySQL configuration.

### Q: Is this application multiuser?
**A**: The H2 version is single-user. For multiuser access, use MySQL backend.

## 12. Conclusion

The Convention History Viewer provides a complete, runnable solution for viewing restaurant-based convention histories. With its automatic database setup, intuitive interface, and robust error handling, it delivers immediate value without complex installation procedures.

Whether you're an Agency Operator tracking event histories or a student learning about hospitality management systems, this application offers a practical tool for convention data management.

---

*Enjoy using Convention History Viewer! For feature requests or issues, please contact your system administrator.*
```

This comprehensive user manual provides complete guidance for using the Convention History Viewer application. It covers installation, usage, troubleshooting, and technical details while maintaining a user-friendly tone appropriate for both technical and non-technical users.