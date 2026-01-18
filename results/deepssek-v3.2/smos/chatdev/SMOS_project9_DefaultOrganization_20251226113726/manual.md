# User Management System - DeleteUser Feature

## Overview

The User Management System is a Java application that implements the complete DeleteUser use case. It allows administrators to delete users from the system while maintaining proper access control and following the specified event sequence and postconditions.

## Main Features

1. **User Management**:
   - Create and store user profiles with username, email, role, and admin status
   - View user details before deletion
   - Delete users from the system archive

2. **Access Control**:
   - Administrator authentication and authorization
   - Role-based permissions for deletion operations

3. **SMOS Server Integration**:
   - Automatic connection interruption after user deletion (as per requirements)
   - Server connection status monitoring

4. **Complete Workflow**:
   - Administrator login verification
   - User details viewing (simulating "viewdetTailsente" use case)
   - Confirmation-based deletion
   - Updated user list display
   - Post-deletion verification

## System Requirements

### Software Dependencies:
- **Java Development Kit (JDK)**: Version 8 or higher
- **Java Runtime Environment (JRE)**: For running the compiled application

### Recommended Development Environment:
- Any Java IDE (Eclipse, IntelliJ IDEA, NetBeans, or VS Code)
- Command-line tools for compilation and execution

## Installation Guide

### Step 1: Install Java
1. Download and install JDK from [Oracle's official website](https://www.oracle.com/java/technologies/javase-downloads.html) or use OpenJDK
2. Verify installation by opening terminal/command prompt and typing:
   ```bash
   java -version
   javac -version
   ```

### Step 2: Set Up the Project

**Option A: Using an IDE**
1. Create a new Java project
2. Create three Java files in your project:
   - `User.java`
   - `UserManager.java`
   - `Main.java`
3. Copy the provided code into their respective files

**Option B: Using Command Line**
1. Create a new directory for the project:
   ```bash
   mkdir UserManagementSystem
   cd UserManagementSystem
   ```
2. Create the three Java files:
   ```bash
   touch User.java UserManager.java Main.java
   ```
3. Copy the code into each file using your preferred text editor

## How to Use the Application

### Running the Application

**Using IDE**:
1. Open the project in your Java IDE
2. Set `Main.java` as the main class
3. Click "Run" or use the keyboard shortcut for your IDE

**Using Command Line**:
1. Compile all Java files:
   ```bash
   javac User.java UserManager.java Main.java
   ```
2. Run the application:
   ```bash
   java Main
   ```

### User Workflow

#### Step 1: Administrator Login
- When prompted, enter an administrator username
- The system will verify if the user has administrator privileges
- **Note**: The system comes pre-loaded with sample users including:
  - `admin` (Administrator)
  - `john` (Regular User)
  - `jane` (Regular User)
  - `bob` (Moderator)

#### Step 2: View User Details
- Enter the username of the user you want to view
- The system will display all user details (simulating "viewdetTailsente" use case)

#### Step 3: Confirm Deletion
- You will be asked to confirm the deletion
- Type `yes` to proceed or `no` to cancel

#### Step 4: Review Results
1. **Successful Deletion**:
   - Confirmation message displayed
   - SMOS server connection interrupted (as per requirements)
   - Updated user list shown
   - Verification that user is no longer in the system

2. **Failed Deletion**:
   - Error message displayed with details
   - System returns to initial state

### Sample Usage Scenario

```
=== User Management System ===
Enter administrator username: admin
Administrator admin logged in successfully.

Current Users:
Updated User List:
==================
User{username='admin', email='admin@example.com', role='Administrator', isAdmin=true}
User{username='john', email='john@example.com', role='User', isAdmin=false}
User{username='jane', email='jane@example.com', role='User', isAdmin=false}
User{username='bob', email='bob@example.com', role='Moderator', isAdmin=false}
==================

Enter username to view details (simulating viewdetTailsente): john
User Details:
-------------
Username: john
Email: john@example.com
Role: User
Is Admin: false
-------------

Do you want to delete user 'john'? (yes/no): yes

Deleting user 'john'...
User 'john' deleted successfully.
User cancellation confirmed.
Note: Connection to SMOS server has been interrupted.
SMOS server connection status: Interrupted

Displaying updated user list...
Updated User List:
==================
User{username='admin', email='admin@example.com', role='Administrator', isAdmin=true}
User{username='jane', email='jane@example.com', role='User', isAdmin=false}
User{username='bob', email='bob@example.com', role='Moderator', isAdmin=false}
==================
Verification: User 'john' is no longer in the system.

=== Program Completed ===
```

## Code Structure

The application consists of three main classes:

### 1. User.java
- **Purpose**: Represents a user entity in the system
- **Attributes**: username, email, role, admin status
- **Methods**: Getters and toString() for display

### 2. UserManager.java
- **Purpose**: Core business logic and user operations
- **Key Methods**:
  - `isAdministrator()`: Validates admin access
  - `findUserByUsername()`: Finds user for viewing details
  - `deleteUser()`: Main deletion functionality
  - `displayUpdatedUserList()`: Shows user list after changes
  - `interruptSMOSConnection()`: Handles server disconnection

### 3. Main.java
- **Purpose**: User interface and workflow orchestration
- **Features**:
  - Full DeleteUser use case implementation
  - Input handling via Scanner
  - Error handling and validation
  - Step-by-step workflow following requirements

## Key Design Considerations

1. **Security**: Only administrators can perform deletions
2. **Data Integrity**: Users are permanently removed from the archive
3. **System Compliance**: SMOS server connection interruption occurs as specified
4. **User Experience**: Clear prompts, confirmations, and feedback at each step
5. **Error Handling**: Graceful handling of invalid inputs and edge cases

## Testing and Verification

### Preloaded Test Data
The system includes four sample users for testing:
- `admin`: Administrator account for testing deletion permissions
- `john`, `jane`: Regular users for deletion testing
- `bob`: Moderator account for testing different roles

### Edge Cases Handled
- Non-administrator login attempts
- Attempts to delete non-existent users
- Deletion cancellation
- Unexpected input handling

## Troubleshooting

### Common Issues:
1. **"Command not found: javac"**
   - Solution: Ensure JDK is installed, not just JRE
   - Check Java installation and PATH environment variable

2. **"You must be logged in as an administrator"**
   - Solution: Use `admin` as username for initial testing
   - This user is pre-configured with administrator privileges

3. **Compilation errors**
   - Ensure all three Java files are in the same directory
   - Check for syntax errors in code copy/paste

### Logging and Debugging:
- The system provides detailed console output for each operation
- Error messages are descriptive and help identify issues
- Unexpected exceptions are caught and displayed with stack traces

## Maintenance and Extensions

### Adding New Users
Modify the `initializeSampleUsers()` method in `UserManager.java` to add more test users.

### Modifying User Roles
The `User` class constructor accepts parameters for:
- Username
- Email
- Role (any string)
- Admin status (boolean)

### Extending Functionality
The modular design allows easy addition of:
- Database persistence
- Network operations for SMOS server
- Additional user operations (edit, search, filter)
- GUI interface

## Compliance with Requirements

This implementation fully satisfies all specified requirements:
- ✅ Java implementation
- ✅ Fully runnable code
- ✅ Complete DeleteUser use case
- ✅ Administrator authentication
- ✅ Precondition validation
- ✅ Event sequence following
- ✅ Postcondition enforcement (user deletion and SMOS disconnection)
- ✅ Comments explaining key logic
- ✅ Edge case handling

## Support

For questions or issues with this implementation, please refer to the code comments or contact the development team. The application is designed to be self-explanatory through its console interface and error messages.