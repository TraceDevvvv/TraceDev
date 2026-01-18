Here's the detailed user manual for the InsertTag application:

```markdown
# InsertTag Application - User Manual

## Overview
The InsertTag application is a Java-based system designed for agency operators to manage tag searches. It implements the InsertTag use case, allowing operators to insert new tags into the system with proper validation, duplicate checking, and error handling. The application simulates server connections and provides comprehensive error messages for various scenarios.

## Main Features

### Core Functionality
1. **Tag Insertion**: Insert new tags into the system with automatic validation
2. **Duplicate Prevention**: Check for existing tags before insertion
3. **Validation**: Ensure tags meet business rules (1-100 characters, alphanumeric/underscore only)
4. **Error Handling**: Comprehensive error handling for invalid inputs and server interruptions
5. **Case Insensitivity**: Tags are stored in lowercase for consistent searching

### Business Rules
- Tag names must be 1-100 characters long
- Only letters, numbers, and underscores are allowed
- Duplicate tags are rejected
- Server interruptions (ETOUR) are properly handled
- Tags are case-insensitive (converted to lowercase for storage)

## System Requirements

### Hardware Requirements
- Minimum 1GB RAM
- 100MB free disk space
- Java Development Kit (JDK) 8 or higher

### Software Dependencies
1. **Java Development Kit (JDK) 8+**
2. **System Requirements**:
   - Windows: Windows 7 or higher
   - macOS: OS X 10.10 or higher
   - Linux: Most modern distributions

## Installation Instructions

### Step 1: Install Java Development Kit (JDK)

#### Windows
1. Download JDK 11 or higher from [Oracle's website](https://www.oracle.com/java/technologies/downloads/)
2. Run the installer and follow the prompts
3. Set JAVA_HOME environment variable:
   - Right-click "This PC" → Properties → Advanced System Settings
   - Click "Environment Variables"
   - Under System Variables, click "New"
   - Variable name: `JAVA_HOME`
   - Variable value: Path to your JDK installation (e.g., `C:\Program Files\Java\jdk-11`)
   - Click "OK" to save

#### macOS
1. Download JDK from [Oracle](https://www.oracle.com/java/technologies/downloads/)
2. Open the .dmg file and run the installer
3. Alternatively, use Homebrew:
   ```bash
   brew install openjdk@11
   ```

#### Linux (Ubuntu/Debian)
```bash
sudo apt update
sudo apt install openjdk-11-jdk
```

### Step 2: Verify Installation
Open a terminal/command prompt and run:
```bash
java -version
javac -version
```
You should see version information for both commands.

### Step 3: Download the Application Files
Download all the Java files included in the package:
1. `Main.java`
2. `TagService.java`
3. `TagFormUI.java`

### Step 4: Compile the Application
Navigate to the directory containing the Java files and run:
```bash
javac *.java
```
This will create `.class` files for all components.

## How to Use the Application

### Starting the Application
Run the main program:
```bash
java Main
```

### Application Workflow

#### Step 1: Initial Connection
When you start the application, it will attempt to connect to the server:
```
Connecting to server...
Welcome, Agency Operator!
```

**Note**: There's a 10% chance of simulated server interruption (ETOUR). If this occurs, the application will display an error and exit.

#### Step 2: Access Insert Tag Functionality
The system automatically presents the insert tag interface:
```
--- Insert New Tag Search ---
Enter tag name: 
```

#### Step 3: Enter Tag Information
Type the tag name you want to insert and press Enter.

**Valid Tag Examples**:
- `java_programming`
- `web_development`
- `database_2024`
- `AI_ML`

**Invalid Tag Examples**:
- `tag with spaces` (contains spaces)
- `tag-with-dashes` (contains hyphens)
- `tag@special` (contains special characters)
- `a` (single character if it violates business rules)

#### Step 4: Submission and Processing
The system will:
1. Validate the tag format
2. Check for duplicates in the system
3. Attempt to insert the tag
4. Display the result

### Expected Responses

#### Success Case
```
Enter tag name: java_tutorial
Success: Tag 'java_tutorial' has been added to the system.
```

#### Error Cases

1. **Invalid Tag Format**:
   ```
   Enter tag name: invalid tag!
   Error: Invalid tag name. Tag must be 1-100 characters and contain only letters, numbers, and underscores.
   ```

2. **Duplicate Tag**:
   ```
   Enter tag name: java
   Error: Tag 'java' already exists in the system.
   ```

3. **Server Interruption (ETOUR)**:
   ```
   Error: Server connection interrupted (ETOUR).
   ```

### Advanced Usage with TagService and TagFormUI

The application provides separate components for advanced users:

#### Using TagService Directly
```java
// Example usage of TagService for programmatic access
TagService service = new TagService();
boolean isValid = service.validateTag("new_tag");
boolean exists = service.tagExists("new_tag");
try {
    boolean inserted = service.insertTag("new_tag");
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

#### Using TagFormUI for Multiple Operations
```java
// Create a tag service instance
TagService tagService = new TagService();

// Create the UI with the service
TagFormUI ui = new TagFormUI(tagService);

// Show the form multiple times
ui.showForm();  // First tag
ui.showForm();  // Second tag

// Close resources
ui.close();
```

## Sample Usage Scenarios

### Scenario 1: Normal Tag Insertion
```
Connecting to server...
Welcome, Agency Operator!

--- Insert New Tag Search ---
Enter tag name: new_technology
Success: Tag 'new_technology' has been added to the system.
```

### Scenario 2: Handling Duplicate Tags
```
Enter tag name: programming
Error: Tag 'programming' already exists in the system.
```

### Scenario 3: Invalid Tag Name
```
Enter tag name: tag with spaces and special characters!
Error: Invalid tag name. Tag must be 1-100 characters and contain only letters, numbers, and underscores.
```

### Scenario 4: Server Disconnection
```
Connecting to server...
Error: Server connection interrupted (ETOUR). Exiting.
```

## Troubleshooting

### Common Issues

#### Issue 1: "Command not found" when running java or javac
**Solution**: Ensure Java JDK is properly installed and PATH environment variable is set correctly.

#### Issue 2: Compilation errors
**Solution**: 
1. Ensure all files are in the same directory
2. Use `javac *.java` to compile all files at once
3. Check for any syntax errors in the source files

#### Issue 3: Server interruptions occur frequently
**Solution**: The current simulation has a 10% chance of server interruption. This is intentional for testing purposes. In a production environment, this would be replaced with actual network connectivity checks.

#### Issue 4: Application closes immediately
**Solution**: Run the application from command line to see any error messages. The application may have encountered a fatal error or server disconnection.

### Debug Mode
For development purposes, you can modify the `TagService` class to reduce simulation probabilities:

```java
private boolean simulateServerInterruption() {
    // Change to 0% for testing
    return Math.random() < 0.0;
}
```

## Extending the Application

### For Developers

#### Adding Database Persistence
1. Replace the `HashSet` in TagService with a database connection
2. Implement a DAO (Data Access Object) pattern
3. Use JDBC or JPA for database operations

#### Adding User Interface
1. Create a Swing or JavaFX GUI
2. Implement a web interface using Spring Boot
3. Add REST API endpoints for remote access

#### Adding Logging
1. Integrate Log4j or SLF4J for logging
2. Implement audit trails for tag operations
3. Add performance monitoring

#### Adding Authentication
1. Implement user login functionality
2. Add role-based access control
3. Secure all endpoints with authentication

### Current Architecture
```
Main (Entry Point)
    |
    ├── TagFormUI (User Interface Layer)
    |       |
    |       └── TagService (Business Logic Layer)
    |               |
    |               └── HashSet (Data Storage Layer)
    |
    └── Direct TagService Access (For Programmatic Use)
```

### Future Enhancements
1. **Database Integration**: Replace in-memory storage with MySQL/PostgreSQL
2. **Web Interface**: Create a web-based administration panel
3. **Batch Import**: Allow CSV import of tags
4. **Tag Categories**: Support hierarchical tag organization
5. **Search Functionality**: Add tag search capabilities
6. **Reporting**: Generate usage reports and statistics

## Best Pract for Tag Creation

1. **Be Descriptive**: Use clear, descriptive names that indicate the tag's purpose
2. **Use Underscores**: Separate words with underscores for readability
3. **Keep it Brief**: While up to 100 characters are allowed, aim for 3-20 characters
4. **Be Consistent**: Follow a naming convention throughout your tagging system
5. **Avoid Special Characters**: Stick to letters, numbers, and underscores only
6. **Check for Duplicates**: Always verify a tag doesn't exist before creating it

## Support and Maintenance

### Getting Help
For issues with the application:
1. Check the error messages displayed
2. Verify Java installation and PATH settings
3. Ensure all files are properly compiled

### Regular Maintenance
- Monitor for duplicate tags
- Clean up unused tags periodically
- Backup tag data regularly (when database integration is implemented)

### Version Information
This manual applies to InsertTag Application Version 1.0

## License and Copyright
This application is a demonstration of use case implementation in Java. For production use, additional licensing and compliance considerations may apply.

---
*For more information or feature requests, please contact the development team.*
```

This manual provides comprehensive guidance for installing, using, and troubleshooting the InsertTag application. It's structured to help both technical and non-technical users understand and utilize the system effectively.