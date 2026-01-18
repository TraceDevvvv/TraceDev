```markdown
# Teaching Management System - Manual

## Overview
The Teaching Management System is a Java application designed for administrators to manage teaching records in an archive. It simulates the "Delete teaching" use case where an administrator can view teaching details and delete teachings from the system, with automatic interruption of the SMOS server connection after deletion.

## Main Features
- **Teaching Archive Management**: Store and manage teaching records with unique IDs
- **Detailed View**: Display comprehensive information about specific teachings
- **Teaching Deletion**: Remove teachings from the archive with proper validation
- **SMOS Server Integration**: Simulated connection management with automatic interruption after operations
- **Administrator Authentication**: Role-based access control for administrative functions
- **Real-time Updates**: View updated teaching lists immediately after modifications

## System Requirements
- Java Development Kit (JDK) 8 or higher
- Minimum 512MB RAM
- 100MB free disk space
- Command-line interface or terminal access

## Installation

### Step 1: Install Java
1. **Windows/Mac/Linux**: Download and install JDK from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html)
2. Verify installation by opening terminal/command prompt and typing:
   ```bash
   java -version
   ```
   You should see Java version information.

### Step 2: Download the Application
1. Save the Java file as `delete_teaching.java` to your desired directory
2. Ensure all classes are in the same directory (Teaching, TeachingArchive, SMOSConnection, Administrator)

### Step 3: Compile the Program
Navigate to the directory containing the Java file and run:
```bash
javac delete_teaching.java
```
This will create `.class` files for each class in the program.

### Step 4: Run the Program
Execute the compiled program with:
```bash
java delete_teaching
```

## How to Use the Software

### Starting the Application
1. Open your terminal or command prompt
2. Navigate to the directory containing the compiled files
3. Run `java delete_teaching`
4. The system will display:
   ```
   === Teaching Management System ===
   Connected to SMOS server.
   Administrator logged in: admin_user
   ```

### Viewing Current Teachings
When the program starts, you'll see a list of pre-loaded teachings:
```
Current teachings in archive:
Teaching ID: T001, Name: Mathematics, Description: Basic algebra and calculus
Teaching ID: T002, Name: Physics, Description: Mechanics and thermodynamics
Teaching ID: T003, Name: Chemistry, Description: Organic and inorganic chemistry
```

### Viewing Teaching Details
1. The system will prompt: `Enter the Teaching ID to view details: `
2. Enter a valid teaching ID (e.g., `T001`)
3. The system displays detailed information:
   ```
   === Detailed Information ===
   Teaching ID: T001, Name: Mathematics, Description: Basic algebra and calculus
   ============================
   ```

### Deleting a Teaching
1. After viewing details, you'll see: `Click 'Delete' button to proceed with deletion.`
2. Enter the Teaching ID to delete: `
3. Enter the ID of the teaching you want to delete
4. The system will process the deletion and show results:
   ```
   Administrator 'admin_user' deleted teaching with ID: T001
   Connection to SMOS server interrupted.
   ```

### Viewing Updated Archive
After deletion, the system automatically displays the updated teaching list:
```
Updated teachings in archive:
Teaching ID: T002, Name: Physics, Description: Mechanics and thermodynamics
Teaching ID: T003, Name: Chemistry, Description: Organic and inorganic chemistry
```

### Postconditions Verification
The system verifies all postconditions automatically:
```
=== Postconditions ===
Teaching deleted: true
SMOS connection interrupted: true
```

## Sample Workflow

```
=== Teaching Management System ===

Administrator logged in: admin_user

Current teachings in archive:
Teaching ID: T001, Name: Mathematics, Description: Basic algebra and calculus
Teaching ID: T002, Name: Physics, Description: Mechanics and thermodynamics
Teaching ID: T003, Name: Chemistry, Description: Organic and inorganic chemistry

Enter the Teaching ID to view details: T002

=== Detailed Information ===
Teaching ID: T002, Name: Physics, Description: Mechanics and thermodynamics
============================

Click 'Delete' button to proceed with deletion.
Enter the Teaching ID to delete: T002
Administrator 'admin_user' deleted teaching with ID: T002
Connection to SMOS server interrupted.

Updated teachings in archive:
Teaching ID: T001, Name: Mathematics, Description: Basic algebra and calculus
Teaching ID: T003, Name: Chemistry, Description: Organic and inorganic chemistry

=== Postconditions ===
Teaching deleted: true
SMOS connection interrupted: true

Program completed.
```

## Error Handling

The system includes robust error handling:

1. **Invalid Teaching ID**: If you enter a non-existent teaching ID for viewing or deletion, the system will display appropriate error messages
2. **Empty Archive**: If all teachings are deleted, the system displays "No teachings available"
3. **SMOS Connection**: Connection status is properly tracked and reported

## Customization Options

### Adding New Teachings
To add initial sample teachings, modify the `initializeSampleTeachings()` method in the `TeachingArchive` class:

```java
private void initializeSampleTeachings() {
    teachings.put("T001", new Teaching("T001", "Mathematics", "Basic algebra and calculus"));
    teachings.put("T002", new Teaching("T002", "Physics", "Mechanics and thermodynamics"));
    teachings.put("T003", new Teaching("T003", "Chemistry", "Organic and inorganic chemistry"));
    // Add more teachings here
}
```

### Changing Administrator Credentials
Modify the Administrator instantiation in the main method:
```java
Administrator admin = new Administrator("your_admin_username");
```

## Troubleshooting

### Common Issues

1. **"Command not found: javac"**
   - Solution: Ensure JDK is installed, not just JRE
   - Check your PATH environment variable includes Java bin directory

2. **"Class not found" error**
   - Solution: Ensure all class files are in the same directory
   - Recompile with `javac delete_teaching.java`

3. **Compilation errors**
   - Solution: Check for syntax errors in the Java file
   - Ensure you're using Java 8 or higher

4. **Input not working**
   - Solution: Ensure terminal/command prompt supports standard input
   - Try running in a different terminal application

### Debug Mode
For debugging purposes, you can add print statements to track program flow in various methods.

## Security Considerations

- The system simulates administrator authentication - in a production environment, implement proper authentication mechanisms
- SMOS server interruption is simulated - real implementations would include proper resource cleanup
- Teaching data is stored in memory - consider persistent storage for production use

## Support
For additional support or to report issues, please contact your system administrator or the development team.

---

**Version**: 1.0  
**Last Updated**: October 2023  
**Compatibility**: Java 8+  
**License**: Proprietary - For educational use only
```