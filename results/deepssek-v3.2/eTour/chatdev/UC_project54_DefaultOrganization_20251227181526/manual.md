```markdown
# ModifyComment System - User Manual

## Overview
The ModifyComment System is a Java-based application that allows tourists to edit previously posted comments on tourist sites. The system implements the complete "ModifyComment" use case with full validation, error handling, and server connection management.

## Main Features

### 1. Comment Modification
- Edit existing comments for specific tourist sites
- Real-time validation of comment content
- Timestamp tracking for all modifications

### 2. Error Handling
- Comprehensive validation for empty comments
- Character limit enforcement (500 characters max)
- Connection error handling with retry mechanism

### 3. Server Connectivity
- Built-in connection management to ETOUR server
- Automatic retry logic for temporary network issues
- Graceful degradation when connection fails

### 4. User Experience
- Interactive command-line interface
- Confirmation prompts before making changes
- Clear notifications for all operations

## System Requirements

### Software Dependencies
- **Java Development Kit (JDK)**: Version 8 or higher
- **Java Runtime Environment (JRE)**: For running compiled applications
- **Terminal/Command Prompt**: For executing Java commands

### Hardware Requirements
- **Processor**: 1 GHz or faster
- **RAM**: 512 MB minimum, 1 GB recommended
- **Storage**: 50 MB free space
- **Network**: Internet connection for server communication

## Installation Guide

### Step 1: Install Java
1. **Windows/Mac/Linux**: Download and install JDK from [Oracle's website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or use OpenJDK
   
2. **Verify Installation**:
   ```bash
   java -version
   javac -version
   ```

### Step 2: Download the Application
1. Create a new directory for the application:
   ```bash
   mkdir ModifyCommentSystem
   cd ModifyCommentSystem
   ```

2. Save all Java files in the directory:
   - `ModifyComment.java`
   - `ErroredUseCase.java`
   - `Comment.java`
   - `Site.java`

## How to Use

### Running the Application

#### Method 1: Compile and Run
1. **Compile all Java files**:
   ```bash
   javac *.java
   ```

2. **Run the main program**:
   ```bash
   java ModifyComment
   ```

#### Method 2: Using an IDE (Optional)
1. Open the project in your preferred Java IDE (Eclipse, IntelliJ IDEA, or NetBeans)
2. Import all Java files
3. Set the main class to `ModifyComment`
4. Run the application

### Application Flow

#### Step 1: Initialization
1. The system initializes with a sample tourist:
   - Tourist ID: `TOURIST_001`
   - Site ID: `SITE_456`
   - Initial comment: "Original comment: Great site!"

2. The application displays the initial state:
   ```
   === Modify Comment Use Case ===
   Tourist is viewing details of site: SITE_456
   ```

#### Step 2: Enter New Comment
1. The system prompts for a new comment:
   ```
   1. Choose to change the comment
   Enter new comment: [Type your comment here]
   ```

2. **Valid Input Examples**:
   - "This site was amazing! The views were breathtaking."
   - "Great experience, would recommend to friends"
   
3. **Invalid Input Examples**:
   - Empty comment (triggers error)
   - Comments longer than 500 characters (triggers error)

#### Step 3: Validation
1. The system automatically validates your input:
   ```
   2. Verifying data...
   Checking connection to ETOUR server...
   ```

2. **Server Connection Status**:
   - **Connected**: "Successfully connected to ETOUR server."
   - **Disconnected**: Automatic retry (3 attempts with 2-second delays)
   - **Permanently Disconnected**: Graceful exit with error message

#### Step 4: Confirmation
1. Review your comment:
   ```
   New comment will be: [Your entered comment]
   3. Confirm change? (yes/no):
   ```

2. **Response Options**:
   - Type `yes` or `y` to proceed with the change
   - Type `no`, `n`, or any other input to cancel

#### Step 5: Final Processing
1. If confirmed, the system updates the comment:
   ```
   4. Editing commentary...
   Comment successfully updated in the database.
   ```

2. **Notification Display**:
   ```
   === Notification ===
   Tourist ID: TOURIST_001
   Site ID: SITE_456
   Previous comment: [Old comment]
   Updated comment: [New comment]
   Timestamp: [Current date and time]
   ===================
   ```

## Error Handling Guide

### Common Error Scenarios

#### 1. Empty Comment Error
```
Enter new comment: 
2. Verifying data...
Errored use case triggered: Comment cannot be empty
Validation failed. Exiting.
```

**Solution**: Enter a valid comment with at least one non-space character.

#### 2. Comment Too Long Error
```
Enter new comment: [Comment exceeding 500 characters]
2. Verifying data...
Errored use case triggered: Comment exceeds 500 characters
Validation failed. Exiting.
```

**Solution**: Shorten your comment to 500 characters or less.

#### 3. Server Connection Error
```
Checking connection to ETOUR server...
Connection to ETOUR server failed. Attempt 1 of 3
Retrying in 2 seconds...
[After 3 attempts]
Errored use case triggered: Connection to server ETOUR interrupted after 3 attempts
Cannot proceed without server connection. Please try again later.
```

**Solutions**:
1. Check your internet connection
2. Wait a few minutes and try again
3. Contact system administrator if issue persists

#### 4. Confirmation Cancelled
```
3. Confirm change? (yes/no): no
Change cancelled by user.
```

**Solution**: This is a user-initiated cancellation. Simply re-run the application if you want to try again.

## Testing and Debugging

### Manual Testing Scenarios

#### Test 1: Normal Operation
1. Enter a valid comment (e.g., "Excellent tour guide!")
2. Confirm with "yes"
3. Verify notification displays correctly

#### Test 2: Error Handling
1. Leave comment empty and press Enter
2. Verify error message appears
3. Verify application exits gracefully

#### Test 3: Server Disconnection
**Before running the application**, add this line in the main method:
```java
disconnectServer();
```
Then run the program to test the retry mechanism.

### Testing with Different Inputs

| Test Case | Input | Expected Result |
|-----------|-------|-----------------|
| Valid comment | "Wonderful experience" | Success update |
| Empty comment | "" | Error, exit |
| Long comment | 501 characters | Error, exit |
| Spaces only | "     " | Error, exit |
| Cancel | Enter "no" on confirmation | Graceful exit |
| Server down | disconnectServer() | Retry then exit |

## File Structure

```
ModifyCommentSystem/
├── ModifyComment.java    # Main application with full use case implementation
├── ErroredUseCase.java   # Error handling module
├── Comment.java          # Comment entity class
├── Site.java            # Site entity class
└── manual.md           # This user manual
```

## Class Descriptions

### ModifyComment.java
**Main class** that orchestrates the entire use case flow. Contains:
- Main method with user interaction
- Database simulation using HashMap
- Validation logic
- Server connection management
- Notification system

### Comment.java
**Entity class** representing a comment with:
- Tourist ID and Site ID
- Comment text
- Timestamp for creation and updates

### Site.java
**Entity class** representing a tourist site with:
- Site ID, name, and description
- Getters for accessing properties

### ErroredUseCase.java
**Error handling class** that:
- Logs error messages
- Can be extended for more sophisticated error handling

## Troubleshooting

### Common Issues

#### Issue 1: "javac: command not found"
**Cause**: Java Development Kit not installed or not in PATH
**Solution**: Install JDK and add it to your system PATH

#### Issue 2: "Main class not found"
**Cause**: Incorrect compilation or class naming
**Solution**: 
```bash
javac ModifyComment.java
java ModifyComment
```

#### Issue 3: Application exits immediately
**Cause**: Input validation failing
**Solution**: Ensure you're entering valid comments (non-empty, ≤500 characters)

#### Issue 4: No server connection messages
**Cause**: Default server state is connected
**Solution**: For testing disconnection, call `disconnectServer()` before main logic

## Advanced Features

### Customizing the Application

#### Modify Initial Configuration
Edit the main method in `ModifyComment.java`:
```java
// Change tourist and site IDs
Tourist tourist = new Tourist("YOUR_TOURIST_ID", "YOUR_SITE_ID");

// Change initial comment
commentDatabase.put(tourist.getCombinedKey(), "Your initial comment");
```

#### Adjusting Validation Rules
Edit the `validateComment` method:
```java
// Change character limit
if (newComment.length() > 1000) {  // Changed from 500 to 1000
    ErroredUseCase.handleError("Comment exceeds 1000 characters");
    return false;
}
```

#### Modifying Retry Logic
Edit the retry parameters in the main method:
```java
int maxRetries = 5;  // Changed from 3 to 5
// and
Thread.sleep(3000);  // Changed from 2000 to 3000 milliseconds
```

## Best Pract

1. **Backup Important Data**: The current implementation uses in-memory storage. Consider implementing file or database persistence for production use.

2. **Network Considerations**: The retry mechanism helps with temporary connectivity issues. Ensure your network is stable for the best experience.

3. **Input Validation**: Always review your comments before submission to avoid unnecessary validations.

4. **Confirmation Step**: Use the confirmation step to double-check your changes before they're saved.

## Support and Contact

### Getting Help
- **Documentation Issues**: Review this manual and code comments
- **Technical Problems**: Check Java installation and system requirements
- **Feature Requests**: Contact the development team

### Reporting Bugs
Please include the following information when reporting issues:
1. Java version (`java -version`)
2. Operating system and version
3. Exact steps to reproduce the issue
4. Error messages (if any)
5. Expected vs actual behavior

---

**Note**: This application is designed for demonstration purposes and uses in-memory storage. For production use, implement proper database integration and security measures.

**Version**: 1.0.0
**Last Updated**: [Current Date]
**Compatibility**: Java 8+
```