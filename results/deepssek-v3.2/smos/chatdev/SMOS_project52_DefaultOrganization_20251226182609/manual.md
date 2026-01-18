```markdown
# Administrator Entity Search System - User Manual

## Overview

The **Administrator Entity Search System** is a Java-based console application that allows system administrators to search through various entities in an educational institution management system. The application simulates a real-world search functionality where administrators can look up classes, teachings, addresses, and users based on keywords.

### Key Features

- **Secure Administrator Authentication**: Role-based access control ensuring only administrators can perform searches
- **Multi-Entity Search**: Search across four different entity types simultaneously
- **Case-Insensitive Search**: Intelligent search that matches keywords regardless of case
- **Server Connection Simulation**: Demonstrates proper connection management and error handling
- **Interactive Console Interface**: User-friendly menu-driven interaction
- **Comprehensive Error Handling**: Graceful handling of edge cases and invalid inputs

## System Architecture

The application follows a modular design with three main components:

1. **Entity** - Data model representing system entities
2. **EntityService** - Business logic layer for search operations
3. **AdministratorSearchApp** - Main application controller and user interface

## Installation and Environment Setup

### Prerequisites

- **Java Development Kit (JDK)**: Version 8 or higher
- **Java Runtime Environment (JRE)**: If you only want to run the compiled application
- **Text Editor or IDE**: For viewing and modifying code (optional)

### Step-by-Step Installation

#### Option 1: Compile and Run from Source

1. **Download or Copy the Code**
   - Create a directory for the project
   - Save all three Java files in the same directory:
     - `Entity.java`
     - `EntityService.java`
     - `AdministratorSearchApp.java`

2. **Open Terminal/Command Prompt**
   - Navigate to the directory containing the Java files

3. **Compile the Java Files**
   ```bash
   javac Entity.java EntityService.java AdministratorSearchApp.java
   ```
   This creates `.class` files for each Java file.

4. **Run the Application**
   ```bash
   java AdministratorSearchApp
   ```

#### Option 2: Using an IDE (Eclipse/IntelliJ/VS Code)

1. **Create a New Java Project**
2. **Add the three Java files to the project**
3. **Set the main class** to `AdministratorSearchApp`
4. **Run the project** from the IDE's run menu

### Verifying Installation

After running the application, you should see:
```
=== Administrator Entity Search System ===
Logging in as administrator...
Enter username:
```
This indicates successful installation and proper execution environment.

## How to Use the Application

### Step 1: Login Process

1. **Launch the Application**
   - Run the `AdministratorSearchApp` class
   - The system will display the welcome banner

2. **Enter Credentials**
   - **Username**: `admin`
   - **Password**: `admin123`
   - These are the demo credentials hardcoded for testing purposes

3. **Successful Login**
   - If credentials are correct:
     ```
     Login successful. Server connection established.
     ```
   - If credentials are incorrect:
     ```
     Login failed. Invalid credentials.
     ```
     (You'll need to restart the application to try again)

### Step 2: Using the Search Functionality

Once logged in, you'll see the main menu:

```
=== Entity Search Menu ===
1. Search entities
2. Stop operation (interrupts server)
3. Logout and exit
Enter choice (1-3):
```

#### Performing a Search (Option 1)

1. **Select Option 1** from the menu
2. **Enter Search Keywords**
   - Type any keyword related to entities you want to find
   - Examples: "CS101", "Smith", "University", "2023", "admin"

3. **View Results**
   - The system displays matching entities in an "active list" format
   - Each result shows: `[Type] Name - Description`

Example search flow:
```
Enter search keywords: CS101

Searching for: 'CS101'

Found 2 matching entities:
==========================================
[Class] CS101 - Introduction to Computer Science
[Teaching] Fall 2023 CS101 - Taught by Dr. Smith
==========================================
```

### Step 3: Available Demo Data

The system includes pre-loaded demo entities for testing:

#### Classes
- CS101: "Introduction to Computer Science"
- MATH202: "Advanced Calculus"

#### Teachings
- Fall 2023 CS101: "Taught by Dr. Smith"
- Spring 2024 MATH202: "Taught by Prof. Johnson"

#### Addresses
- 123 University Ave: "Main Campus Building"
- 456 College St: "Science Department"

#### Users
- admin: "System Administrator"
- john_doe: "Student - Computer Science"
- jane_smith: "Professor - Mathematics"

### Step 4: Exiting the Application

#### Option 2: Stop Operation
- Interrupts the server connection immediately
- Exits the application
- Simulates the "connection to the interrupted SMOS server" postcondition

#### Option 3: Logout and Exit
- Properly logs out the administrator
- Interrupts server connection
- Cleanly exits the application

## Search Capabilities and Examples

### Search Modes

The system searches in three fields:
1. **Entity Type** (Class, Teaching, Address, User)
2. **Entity Name** (e.g., "CS101", "john_doe")
3. **Entity Description** (e.g., "Introduction to Computer Science")

### Example Searches

| Search Keyword | Expected Results | Why They Match |
|----------------|-----------------|----------------|
| `CS101` | 2 results | Matches name in Class and Teaching |
| `smith` | 2 results | Case-insensitive match in descriptions |
| `202` | 3 results | Partial match in names and descriptions |
| `professor` | 1 result | Matches User description |
| `building` | 1 result | Matches Address description |
| `science` | 2 results | Matches in Class and User descriptions |

### Advanced Search Tips

1. **Partial Matching**: "math" will match "MATH202"
2. **Whitespace Handling**: Leading/trailing spaces are automatically trimmed
3. **Empty Search**: Returns an empty list with appropriate message
4. **No Results**: System informs when no matches are found

## Error Handling and Troubleshooting

### Common Issues and Solutions

#### 1. Login Issues
- **Problem**: "Login failed. Invalid credentials"
- **Solution**: Use exact credentials: username=`admin`, password=`admin123`

#### 2. Server Connection Issues
- **Problem**: "Error: SMOS server connection interrupted"
- **Solution**: Restart the application to re-establish connection

#### 3. Search Not Working
- **Problem**: No results or "User is not logged in as administrator"
- **Solution**: Ensure you're logged in before searching

#### 4. Compilation Errors
- **Problem**: `javac: file not found`
- **Solution**: Verify all three Java files are in the same directory

### Application States

The system manages three critical states:

1. **Authentication State**: `isLoggedInAsAdmin`
   - Prevents unauthorized access to search functionality

2. **Server Connection State**: `serverConnected`
   - Simulates real server connectivity
   - Managed according to use case postconditions

3. **Session State**: Clean session management with proper logout

## Customization and Extension

### Adding More Entities

To add more demo data, modify the `initializeMockData()` method in `EntityService.java`:

```java
mockEntities.add(new Entity("Type", "Name", "Description"));
```

### Changing Authentication

To implement real authentication:
1. Modify the `loginAsAdministrator()` method
2. Add database connection logic
3. Implement secure password handling

### Connecting to Real Database

Replace the mock data in `EntityService` with:
1. Database connection logic
2. SQL queries with LIKE clauses for search
3. Connection pooling for production use

## Technical Specifications

### System Requirements

- **Minimum Java Version**: Java 8 (1.8)
- **Memory**: 128MB RAM minimum
- **Storage**: Less than 1MB for compiled code
- **Dependencies**: None (pure Java)

### Keyboard Shortcuts

| Action | Key |
|--------|-----|
| Submit input | Enter |
| Menu selection | Number keys (1-3) |
| Exit search | 2 or 3 |

### Performance Characteristics

- **Search Time**: O(n) for mock data; would be optimized with database indexing
- **Memory Usage**: Minimal - only stores active search results
- **Scalability**: Service layer ready for database integration

## Best Pract for Administrators

1. **Use Specific Keywords**: Be specific to get accurate results
2. **Logout Properly**: Always use option 3 to ensure clean exit
3. **Test Server Connection**: Verify connection before important searches
4. **Review All Results**: Active list displays all matches - review carefully

## Support and Maintenance

### Log Files
The current console version displays all logs directly. For production:
- Implement logging framework (Log4j, SLF4J)
- Configure log levels and rotation
- Add audit trails for security compliance

### Monitoring
Key metrics to monitor in production:
- Search frequency and patterns
- Login success/failure rates
- Server connection stability
- Search result accuracy

## Security Considerations

### Current Implementation
- Simple authentication for demonstration
- No persistent session storage
- Console-based (no network exposure)

### Production Recommendations
1. Implement HTTPS for network communication
2. Add password hashing (bcrypt, Argon2)
3. Implement session management with timeouts
4. Add role-based access control beyond simple admin check
5. Audit all search queries for security compliance

## Frequently Asked Questions

### Q: Can I search multiple keywords at once?
A: Currently, the system searches for the entire phrase. For multiple keyword support, you would need to modify the `searchEntities()` method to split and process individual keywords.

### Q: How do I add new entity types?
A: Modify the `Entity` class and `EntityService` to support additional types. The search functionality is type-agnostic and will automatically include new types.

### Q: Is there a limit on search result size?
A: In the current implementation, no. In production, consider adding pagination for large result sets.

### Q: Can I save search results?
A: Not in the current version. You would need to add export functionality (CSV, PDF, etc.).

## Troubleshooting Guide

| Symptom | Possible Cause | Resolution |
|---------|---------------|-----------|
| "java.lang.ClassNotFoundException" | Files not compiled together | Compile all three files: `javac *.java` |
| Blank screen after login | System waiting for input | Press Enter or type menu choice |
| Search returns no results | No matches in demo data | Try: "cs101", "math", "smith", "2023" |
| Can't type in terminal | Input buffering issue | Restart application or clear terminal |

## Conclusion

The Administrator Entity Search System provides a robust foundation for entity search functionality in educational management systems. While the current implementation uses mock data, the architecture is designed for easy integration with real databases and production environments.

For production deployment, consider:
1. Replacing mock data with database integration
2. Adding a web-based GUI
3. Implementing proper security measures
4. Adding advanced search filters and sorting

The code is well-commented and follows Java best pract, making it easy to understand, modify, and extend for specific institutional requirements.

**Happy Searching!**
```