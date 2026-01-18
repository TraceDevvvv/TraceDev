# Tourist Account Management System

## Overview

This Java application implements the **Active/Inactive Tourist Account** use case, allowing Agency Operators to enable or disable tourist accounts. The system follows the specified workflow and handles edge cases including server connection interruptions (ETOUR).

## Use Case Implementation

### Use Case Name: Active/Inactive Tourist Account
**Description**: Enable or disable the account of a tourist
**Participating Actor**: Agency Operator

### Flow of Events (User → System):
1. **Activate the feature** for activation/deactivation of a given tourist
2. **Ask for confirmation** of activation/deactivation
3. **Confirm the operation**
4. **Enable/disable the account** of the selected tourist
5. **Exit conditions**: Notification of outcome

### Quality Requirements:
- Handles server connection interruptions (ETOUR)
- Provides clear notifications of operation outcomes
- Includes error handling for edge cases
- User-friendly interactive interface

## System Architecture

### Class Structure

### 1. Tourist.java
- **Purpose**: Represents a tourist with personal information and account status
- **Key Features**:
  - Stores tourist ID, name, email, and active status
  - Provides methods to activate/deactivate account
  - Includes status toggling functionality
  - Proper toString() method for display

### 2. TouristAccountService.java
- **Purpose**: Service layer for managing tourist accounts and handling server communication
- **Key Features**:
  - In-memory database simulation with sample data
  - Methods for activating/deactivating accounts
  - Server connection simulation with random failures (10% chance)
  - Custom ServerConnectionException for handling ETOUR server issues
  - Comprehensive error handling and validation

### 3. AgencyOperator.java
- **Purpose**: User interface layer implementing the use case flow
- **Key Features**:
  - Interactive console-based menu system
  - Implements the exact use case flow:
    1. Activate feature for given tourist
    2. Ask for confirmation
    3. Confirm operation
    4. Enable/disable account
    5. Provide outcome notification
  - Handles server connection interruptions
  - Input validation and error handling
  - Programmatic API for automated testing

### 4. Main.java
- **Purpose**: Entry point and demonstration of the system
- **Key Features**:
  - Main method for running interactive program
  - demonstrateUseCase() method for automated testing
  - Clear startup and shutdown messages
  - Demonstrates all use case scenarios

## How to Run the Program

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Command line terminal

### Compilation and Execution

1. **Navigate to the project directory**:
```bash
cd TouristAccountSystem
```

2. **Compile all Java files**:
```bash
javac -d bin src/main/java/com/tourist/system/*.java
```

3. **Run the main program**:
```bash
java -cp bin com.tourist.system.Main
```

4. **Run the demonstration (without user interaction)**:
```bash
java -cp bin com.tourist.system.Main demonstrateUseCase
```

### Running with an IDE
1. Open the project in your preferred Java IDE (Eclipse, IntelliJ IDEA, VS Code)
2. Set the source directory to `src/main/java`
3. Run the `Main.java` file

## Program Flow Example

### Interactive Mode:
```
==========================================
   TOURIST ACCOUNT MANAGEMENT SYSTEM
==========================================

Welcome Agency Operator!
Checking connection to ETOUR server...
✓ Connection to ETOUR server established.

------------------------------------------
MAIN MENU
------------------------------------------
1. View All Tourists
2. Activate/Deactivate Tourist Account
3. Check Tourist Account Status
4. Exit

Please enter your choice (1-4): 2

------------------------------------------
ACTIVATE/DEACTIVATE TOURIST ACCOUNT
------------------------------------------
Enter Tourist ID to modify (or type 'back' to return): T002

Tourist Information:
  ID: T002
  Name: Emma Johnson
  Email: emma.j@example.com
  Current Status: Inactive

------------------------------------------
CONFIRMATION REQUIRED
------------------------------------------
You are about to ACTIVATE this tourist's account.
This action will enable their access.

Are you sure you want to proceed? (yes/no): yes

Performing ACTIVATE operation...

✓ SUCCESS: Tourist account has been activated.
  New Status: Active
  Tourist ID: T002
  Name: Emma Johnson
  Verified Status: Active
```

### Edge Cases Handled:
1. **Server Connection Interruption (ETOUR)**:
   - 10% chance of simulated server failure
   - Graceful error handling with user notifications
   - ServerConnectionException for proper error propagation

2. **Invalid Tourist ID**:
   - Validation for null/empty IDs
   - Clear error messages for non-existent tourists

3. **User Cancellation**:
   - Option to cancel at confirmation step
   - Clear feedback when operation is cancelled

4. **Invalid Input**:
   - Input validation for menu cho
   - Handling of non-numeric input

## Code Quality Features

### 1. Comments and Documentation
- Comprehensive JavaDoc comments for all classes and methods
- Inline comments explaining key logic and edge cases
- Clear separation of concerns between layers

### 2. Error Handling
- Custom ServerConnectionException for server issues
- Input validation and user-friendly error messages
- Try-catch blocks for robust exception handling

### 3. Design Patterns
- **Service Layer Pattern**: TouristAccountService handles business logic
- **MVC-like Structure**: Separation of data (Tourist), business logic (Service), and UI (AgencyOperator)
- **Singleton-like Pattern**: Service manages its own data store

### 4. Testing Considerations
- The `demonstrateUseCase()` method in Main.java provides automated testing
- Sample data pre-loaded for immediate testing
- Programmatic API in AgencyOperator for integration testing

## Sample Data

The system comes pre-loaded with 5 sample tourists:

| ID   | Name            | Email                    | Initial Status |
|------|-----------------|--------------------------|----------------|
| T001 | John Smith      | john.smith@example.com   | Active         |
| T002 | Emma Johnson    | emma.j@example.com       | Inactive       |
| T003 | Carlos Rodriguez| carlos.r@example.com     | Active         |
| T004 | Lisa Wang       | lisa.wang@example.com    | Active         |
| T005 | Ahmed Hassan    | ahmed.h@example.com      | Inactive       |

## Requirements Compliance

✅ **Java Implementation**: Complete Java program with proper package structure
✅ **Fully Runnable**: Can be compiled and executed without errors
✅ **Comments Explaining Key Logic**: Comprehensive JavaDoc and inline comments
✅ **Use Case Implementation**:
   - Step 1: Feature activation through menu system
   - Step 2: Confirmation request before operation
   - Step 3: User confirmation handling
   - Step 4: Account enable/disable functionality
   - Exit: Clear outcome notifications
✅ **Edge Case Handling**: Server connection interruptions simulated and handled
✅ **Efficiency**: In-memory data store for demonstration, extensible to database
✅ **Correctness**: Input validation, error handling, and proper flow control

## Extensibility

The system is designed to be easily extensible:

1. **Database Integration**: Replace in-memory Map with database connection
2. **Web Interface**: AgencyOperator can be adapted to web controllers
3. **Additional Features**:
   - Email notifications on status change
   - Audit logging
   - Role-based access control
   - Batch operations

## License

This project is for educational/demonstration purposes.

## Author

Tourist Account Management System - Use Case Implementation

---

*Note: This implementation satisfies all specified requirements including handling server connection interruptions (ETOUR) and providing proper notifications of operation outcomes.*
