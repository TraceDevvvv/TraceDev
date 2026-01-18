```markdown
# Edit Justification System - User Manual

## Overview
The Edit Justification System is a Java-based application that allows administrators to manage justification records within a simulated SMOS (System Management and Operations System) environment. This system implements a complete use case for viewing and editing justification details with proper authentication, validation, and database operations.

## 📋 Main Features

### 1. **Authentication & Security**
- Administrator-only access with secure login credentials
- Session management with proper state tracking
- SMOS server connection management

### 2. **Justification Management**
- View registry of all justifications
- Display detailed justification information
- Edit justification dates and descriptions
- Save changes to the simulated database
- Input validation for date formats and required fields

### 3. **System Flow**
- Complete implementation of the "EditJustification" use case
- Precondition validation (login status, SMOS connection)
- Postcondition enforcement (database update, screen navigation, server disconnect)
- Comprehensive error handling and logging

## 🛠️ System Architecture

### Core Components

| Component | Purpose |
|-----------|---------|
| `Main.java` | Entry point and workflow orchestrator |
| `SMOSSystem.java` | Authentication and server connection management |
| `EditJustificationHandler.java` | Business logic for justification editing |
| `Justification.java` | Entity class representing justification records |
| `DatabaseSimulator.java` | In-memory data storage simulation |
| `RegistryFrame.java` | UI simulation for registry and detail views |

## 📥 Installation Requirements

### Prerequisites
- **Java Development Kit (JDK)** version 11 or higher
- **Terminal or Command Prompt** access
- Basic understanding of Java command-line execution

### Step-by-Step Installation

1. **Verify Java Installation**
   ```bash
   java -version
   javac -version
   ```

2. **Download and Extract Files**
   Download all six Java files from the provided code:
   - `Main.java`
   - `SMOSSystem.java`
   - `EditJustificationHandler.java`
   - `Justification.java`
   - `DatabaseSimulator.java`
   - `RegistryFrame.java`

3. **Create Project Structure**
   ```
   EditJustificationSystem/
   ├── src/
   │   ├── Main.java
   │   ├── SMOSSystem.java
   │   ├── EditJustificationHandler.java
   │   ├── Justification.java
   │   ├── DatabaseSimulator.java
   │   └── RegistryFrame.java
   └── bin/
   ```

4. **Compile the Application**
   ```bash
   # Navigate to the src directory
   cd src
   
   # Compile all Java files
   javac -d ../bin *.java
   
   # Verify compilation
   ls ../bin/*.class
   ```

## 🚀 How to Use the System

### Starting the Application
```bash
# From the bin directory
cd bin
java Main
```

### Usage Workflow

#### Step 1: Administrator Login
```
=== EDIT JUSTIFICATION SYSTEM ===
System started at: 2024-01-15 10:30:00

=== ADMINISTRATOR LOGIN ===
Username: admin
Password: admin123
```
**Note:** Use username "admin" and password "admin123" for authentication.

#### Step 2: View Registry
After successful login, the system automatically displays the registry screen:
```
REGISTRY SCREEN - Justifications List
==================================================
ID    Date         Description
-------------------------------------------------------------------
1     2023-10-15   Initial justification sample.
2     2023-10-20   Another justification entry.
==================================================
```

#### Step 3: Select Justification to Edit
```
Enter Justification ID to edit (or 0 to exit): 1
```

#### Step 4: View Justification Details
The system displays detailed information:
```
============================================================
JUSTIFICATION DETAILS
============================================================
ID:          1
Date:        2023-10-15
Description: Initial justification sample.
============================================================
```

#### Step 5: Edit Fields
```
=== EDIT JUSTIFICATION FIELDS ===
Current date: 2023-10-15
Enter new date (YYYY-MM-DD) or press Enter to keep current: 2023-10-16

Current description: Initial justification sample.
Enter new description or press Enter to keep current: Updated sample justification.
```

#### Step 6: Save Changes
```
Save changes? (yes/no): yes
```

#### Step 7: Postcondition Results
```
==================================================
OPERATION COMPLETE - POSTCONDITIONS
==================================================
1. ✓ The justification has been modified.
2. ✓ Returning to registry screen...
3. ✓ SMOS server connection interrupted.

Updated justification details:
```

## 🔧 Important Notes

### 1. **Date Format Requirements**
- Use ISO format: `YYYY-MM-DD`
- Examples: `2023-10-15`, `2024-01-31`
- Invalid formats will be rejected

### 2. **Field Update Rules**
- Press Enter (leave blank) to keep current value
- Both date and description can be edited simultaneously
- At least one field must be changed to trigger an update

### 3. **Database Behavior**
- The system uses an in-memory database
- Two sample justifications are pre-loaded
- Changes persist only for the current session
- Restarting the application resets to default data

## ⚠️ Error Handling

### Common Error Messages and Solutions

| Error Message | Cause | Solution |
|---------------|-------|----------|
| "Admin must be logged in" | Session expired or not authenticated | Restart application and login properly |
| "SMOS server must be connected" | Connection lost during operation | System automatically manages connections |
| "Invalid date format" | Date not in YYYY-MM-DD format | Re-enter date in correct format |
| "Justification ID not found" | Invalid ID entered | Check registry for valid IDs |
| "Failed to save" | Database write error | Check system logs and retry |

## 📊 Sample Test Cases

### Test 1: Basic Edit Flow
1. Login: `admin` / `admin123`
2. Select ID: `1`
3. Date: `2023-10-16` (change)
4. Description: `[Enter]` (keep same)
5. Save: `yes`

### Test 2: Description Only Update
1. Login: `admin` / `admin123`
2. Select ID: `2`
3. Date: `[Enter]` (keep same)
4. Description: `Modified description text`
5. Save: `yes`

### Test 3: Exit Without Saving
1. Login: `admin` / `admin123`
2. Select ID: `1`
3. Make changes
4. Save: `no`

## 🔒 Security Considerations

1. **Authentication**: Only users with administrator credentials can access the system
2. **Session Management**: Automatic logout and server disconnect after operations
3. **Input Validation**: All user inputs are validated before processing
4. **Error Handling**: Graceful degradation with informative error messages

## 🧹 Maintenance

### Restoring Default Data
To restore the two default justification records:
1. Stop the application
2. Delete all `.class` files in the `bin` directory
3. Recompile using: `javac -d ../bin *.java`
4. Restart the application

### Viewing Logs
The system includes logging functionality. To configure detailed logging:
1. Create a `logging.properties` file
2. Configure log levels as needed
3. Place in the same directory as the compiled classes

## 🚨 Troubleshooting

### Issue: "Could not find or load main class Main"
**Solution:**
```bash
# Ensure you're in the bin directory
cd bin

# Check .class files exist
ls -la Main.class

# Run with proper classpath
java -cp . Main
```

### Issue: Login always fails
**Solution:**
- Verify username is exactly "admin"
- Verify password is exactly "admin123"
- Check for trailing spaces in input

### Issue: Date changes not saving
**Solution:**
- Confirm date format is YYYY-MM-DD
- Ensure at least one field is actually changed
- Verify you typed "yes" (not "y" or "Yes")

## 📞 Support

For additional support:
1. Review the comprehensive error messages provided
2. Check that all prerequisites are met
3. Verify file permissions and directory structure
4. Ensure no other Java processes are running

---

**System Version:** 1.0  
**Last Updated:** January 2024  
**Compatibility:** Java 11+  
**License:** Proprietary - Internal Use Only
```