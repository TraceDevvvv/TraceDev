# Registration System User Manual

## Overview

The Registration System is a Java-based desktop application that provides a complete user registration solution following the Registration use case specification. The system enables guest users to create new accounts with validation, confirmation, and error handling capabilities.

## System Requirements

### Hardware Requirements
- Processor: 1 GHz or faster
- RAM: 512 MB minimum (1 GB recommended)
- Storage: 100 MB available space
- Display: 1024x768 resolution minimum
- Input: Keyboard and mouse

### Software Requirements
- **Operating System**: Windows 7+, macOS 10.10+, or Linux with GUI support
- **Java Runtime Environment (JRE)**: Version 8 or higher
- **Java Development Kit (JDK)**: Version 8 or higher (for development/compilation)

## Installation Guide

### Method 1: Using Pre-compiled JAR (Recommended for End Users)

1. **Verify Java Installation**
   ```bash
   java -version
   ```
   If Java is not installed, download and install JRE from [Oracle Java](https://www.oracle.com/java/technologies/javase-downloads.html) or [OpenJDK](https://openjdk.org/)

2. **Download the Application**
   - Obtain the `registration-system.jar` file

3. **Run the Application**
   ```bash
   java -jar registration-system.jar
   ```

### Method 2: Manual Compilation (For Developers)

1. **Clone or Download Source Code**
   - Download all `.java` source files

2. **Compile the Application**
   ```bash
   javac *.java
   ```

3. **Run the Application**
   ```bash
   java Registration
   ```

### Method 3: Using Maven (Advanced Users)

1. **Ensure Maven is installed**
   ```bash
   mvn --version
   ```

2. **Build the Project**
   ```bash
   mvn clean compile
   ```

3. **Package the Application**
   ```bash
   mvn package
   ```

4. **Run the Application**
   ```bash
   java -jar target/registration-system-1.0.0.jar
   ```

## Main Features

### 1. Registration Form Interface
- **User-Friendly GUI**: Clean, intuitive form layout with proper spacing
- **Input Fields**:
  - Username (required)
  - Password (required, minimum 6 characters)
  - Email (required, validated format)
  - Phone number (optional, validated format)
- **Action Buttons**:
  - Submit: Processes registration
  - Cancel: Aborts registration process

### 2. Data Validation System
- **Real-time Validation**: Checks input data as users type
- **Comprehensive Validation Rules**:
  - Username: Non-empty field
  - Password: Minimum 6 characters
  - Email: Valid email format (regex validation)
  - Phone: Optional, but must be at least 10 digits if provided
- **Informative Error Messages**: Clear feedback for validation failures

### 3. Confirmation Workflow
- **Review Screen**: Displays entered data before submission
- **Confirmation Dialog**: Double-check mechanism to prevent errors
- **User Control**: Option to modify data before final submission

### 4. Account Creation Process
- **Simulated Server Communication**: Realistic delay for account creation
- **Error Handling**: Graceful handling of server interruptions
- **Success Notification**: Clear confirmation of successful registration

### 5. Logging System
- **Comprehensive Logging**: Tracks all user actions and system events
- **Error Tracking**: Logs validation failures and server errors
- **Audit Trail**: Maintains record of all registration attempts

## How to Use

### Step 1: Launch the Application
1. Double-click the JAR file or run from command line
2. The Registration Form window will appear

### Step 2: Fill Registration Form
1. **Enter Username**: Choose a unique username
   - Must not be empty
   - Can contain letters, numbers, and symbols

2. **Enter Password**: Create a secure password
   - Minimum 6 characters
   - Consider using a mix of letters, numbers, and symbols

3. **Enter Email**: Provide valid email address
   - Must follow standard email format (user@domain.com)
   - Will be validated automatically

4. **Enter Phone (Optional)**: Provide phone number if desired
   - Must contain only digits
   - Must be at least 10 digits if provided

### Step 3: Submit Registration
1. Click the **Submit** button
2. The system validates your data
   - If errors exist, you'll see error messages with correction guidance
   - If valid, proceeds to confirmation

3. **Review Confirmation Dialog**
   - Verify all information is correct
   - Click **Yes** to proceed
   - Click **No** to make changes

### Step 4: Account Creation
1. After confirmation, the system processes registration
2. **Processing Indicators**:
   - Submit button becomes disabled
   - Cancel button becomes disabled
   - System shows processing state

3. **Expected Outcomes**:
   - **Success**: Green success message with account details
   - **Server Error**: Red error message with retry suggestion
   - **Network Interruption**: Warning message about connection issues

### Step 5: Completion
- **Successful Registration**: Window closes automatically
- **Failed Registration**: Returns to form for correction
- **User Cancellation**: Application closes with confirmation

## Troubleshooting

### Common Issues and Solutions

#### 1. Java Not Found Error
```
Error: Could not find or load main class
```
**Solution**:
- Install Java from official sources
- Add Java to system PATH

#### 2. GUI Display Issues
```
Application appears with incorrect size or fonts
```
**Solution**:
- Update Java to latest version
- Check system DPI settings
- Try running with specific JVM options: `java -Dswing.defaultlaf=com.sun.java.swing.plaf.windows.WindowsLookAndFeel -jar registration-system.jar`

#### 3. Logging Configuration Errors
```
Logging system failed to initialize
```
**Solution**:
- Check Java permissions
- Verify write access to log directory
- Run as administrator/root if necessary

#### 4. Network Simulation Issues
```
Server connection errors during registration
```
**Solution**:
- This is a simulation feature (20% chance of server interruption)
- Try clicking Submit again
- Check internet connection (for real implementations)

### Advanced Configuration

#### 1. Logging Settings
To change logging levels, modify the `setupLogging()` method in `Registration.java`:
```java
rootLogger.setLevel(Level.WARNING); // Change from INFO to WARNING
```

#### 2. Server Simulation
To modify server connection simulation, change in `RegistrationForm.java`:
```java
if (Math.random() < 0.2) { // Change 0.2 to desired probability
```

#### 3. Validation Rules
To modify validation criteria, update `UserAccount.validate()` method:
```java
// Current: minimum 6 character password
if (password.length() < 6) {  
    return false;
}
// Change to: minimum 8 character password
if (password.length() < 8) {
    return false;
}
```

## Security Considerations

### Data Protection
- **Local Processing**: All data processing occurs locally
- **No Network Transmission**: Application is self-contained
- **No Data Storage**: Registration data is not persisted after application closes

### Password Security
- **In-memory Storage**: Passwords stored in memory only during session
- **Automatic Clearing**: Password fields cleared after submission
- **Masked Display**: Password shown as asterisks in form

### Privacy Features
- **Optional Phone**: Phone number is optional
- **Minimal Data Collection**: Only essential information requested
- **Transparent Processing**: Users can review all data before submission

## Frequently Asked Questions

### Q1: Can I use special characters in my username?
**A**: Yes, the system allows most special characters in usernames.

### Q2: What happens if the server connection is interrupted?
**A**: The system displays an error message and allows you to retry the registration. This simulates real-world network conditions.

### Q3: Is my registration data saved anywhere?
**A**: No, this is a demonstration application. Registration data is not permanently stored.

### Q4: Can I customize the validation rules?
**A**: Yes, developers can modify the validation logic in the `UserAccount.java` file.

### Q5: How do I change the application appearance?
**A**: The application uses the system's default look and feel. You can modify the appearance in the `Registration.java` file.

## Technical Architecture

### Component Structure
```
Registration System
├── Registration.java (Main application class)
├── RegistrationForm.java (GUI implementation)
├── UserAccount.java (Data model and validation)
└── ErrorDialog.java (Error handling UI)
```

### Key Design Patterns
- **MVC Architecture**: Separation of concerns between Model, View, and Controller
- **Singleton Pattern**: Logging system implementation
- **Observer Pattern**: Event-driven GUI components
- **Factory Pattern**: Dialog creation patterns

### Logging System
The application uses Java's built-in `java.util.logging` framework with:
- **Console Output**: All logs displayed in console
- **Hierarchical Loggers**: Different loggers for different components
- **Custom Formatter**: Formatted timestamps and log levels
- **Configurable Levels**: INFO, WARNING, SEVERE logging levels

## Support and Contact

### Documentation Updates
- Latest documentation available with application distribution
- Check for updates in the `manual.md` file

### Issue Reporting
For technical issues or suggestions:
1. Check existing documentation and FAQs
2. Review error messages and logs
3. Contact development team with detailed error information

### Developer Resources
- **API Documentation**: JavaDoc comments in source code
- **Source Code**: Available for customization and extension
- **Test Data**: Sample data for testing validation rules

## License and Compliance

### Software License
This software is provided for demonstration and educational purposes. For production use, appropriate licensing must be obtained.

### Data Compliance
- **GDPR Compliant**: Minimal data collection and no storage
- **Privacy by Design**: Built-in privacy features
- **Transparency**: Clear user consent and data review

---

**Version**: 1.0.0  
**Last Updated**: Current Date  
**Compatibility**: Java 8+  
**Support**: Contact development team