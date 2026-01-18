# Tourist Data Modification Application User Guide

## Overview

The Tourist Data Modification application provides a comprehensive solution for users to update their personal information in an ETOUR tourism system. This desktop application implements the full "Modify Tourist Data" use case with a user-friendly interface, server simulation, and robust data validation.

## Main Features

1. **User Authentication Simulation**
   - Secure username-based data loading (simulated authentication)
   - Session-based interaction flow

2. **Data Viewing & Editing**
   - View current tourist information
   - Edit personal details with real-time validation
   - Optional and required field differentiation

3. **Validation System**
   - Email format validation (must contain @ symbol)
   - Phone number validation (minimum 10 digits)
   - Required field checking
   - Clear error messages for invalid inputs

4. **Server Simulation**
   - Realistic network delay simulation
   - Server connection interruption handling
   - In-memory data persistence

5. **Security Features**
   - Username locking after data loading
   - Change confirmation dialog
   - Operation state management

## System Requirements

### Hardware Requirements
- Minimum: 2GB RAM, 1GHz processor
- Recommended: 4GB RAM, 2GHz processor or higher
- 50MB free disk space

### Software Requirements
- **Operating System**: Windows 7+, macOS 10.9+, Linux (with GUI support)
- **Java Runtime Environment**: Java 8 or higher
- **Display**: Minimum 1024x768 resolution

## Installation

### Step 1: Install Java Runtime Environment

**Windows:**
1. Visit [Oracle Java Download](https://www.oracle.com/java/technologies/javase-downloads.html)
2. Download the latest Java Runtime Environment (JRE) for your Windows version
3. Run the installer and follow the prompts
4. Verify installation by opening Command Prompt and typing:
   ```
   java -version
   ```

**macOS:**
1. Open Terminal
2. Install using Homebrew:
   ```
   brew install openjdk
   ```
3. Or download from [AdoptOpenJDK](https://adoptopenjdk.net/)
4. Verify installation:
   ```
   java -version
   ```

**Linux (Ubuntu/Debian):**
```bash
sudo apt update
sudo apt install default-jre
java -version
```

### Step 2: Download the Application

**Option A: Using Pre-compiled JAR (Recommended)**
1. Contact your system administrator for the latest JAR file
2. Save the file to your preferred location (e.g., Downloads or Desktop)
3. Ensure the file has the `.jar` extension

**Option B: Compile from Source**
1. Download all three Java files:
   - `ModifyTouristDataGUI.java`
   - `Tourist.java`
   - `DataManager.java`
2. Open Command Prompt/Terminal in the folder containing the files
3. Compile the application:
   ```bash
   javac ModifyTouristDataGUI.java
   ```
4. This will create `.class` files for all components

## How to Use the Application

### Starting the Application

**Using JAR File:**
```bash
java -jar ModifyTouristDataGUI.jar
```

**Using Compiled Source:**
```bash
java ModifyTouristDataGUI
```

### Application Workflow

#### 1. Initial Launch
- Application window appears (500x400 pixels)
- Status message: "Enter username and click 'Load Data' to start."

#### 2. Loading Data
1. Enter a valid username in the Username field
2. Available demo usernames:
   - `jdoe` (John Doe)
   - `asmith` (Alice Smith)
   - `bwilson` (Bob Wilson)

3. Click "Load Data" button
4. Observe:
   - Username field becomes read-only
   - Form fields populate with existing data
   - Submit button becomes enabled
   - Status updates: "Data loaded successfully. You can now edit the fields."

#### 3. Editing Information
1. Modify any field:
   - **First Name**: Required field
   - **Last Name**: Required field
   - **Email**: Required field (must be valid format)
   - **Phone Number**: Required field (minimum 10 digits)
   - **Address**: Optional field

2. Validation occurs automatically
   - Invalid email: Must contain @ symbol
   - Invalid phone: Minimum 10 digits required
   - Missing required fields: Clearly indicated with asterisks (*)

#### 4. Submitting Changes
1. After editing, click "Submit"
2. System checks all fields (Step 4 of use case)
   - If invalid: Error dialog explains what needs correction
   - **"Errored" use case**: Invalid/insufficient data triggers validation error

3. If valid: Confirmation dialog appears (Step 5)
   - Review all changes
   - Click "Yes" to confirm or "No" to cancel

4. Successful submission (Step 6):
   - Status: "Data modified successfully!"
   - Success dialog appears
   - Data saved to server

### Troubleshooting Common Issues

#### Connection Errors
**Problem**: "Connection to server ETOUR interrupted"
**Solution**:
1. Wait a few seconds and try again
2. The system simulates 10% failure rate for demonstration
3. Click "Load Data" or "Submit" again

#### Validation Errors
**Problem**: "Invalid or insufficient data" error
**Check**:
1. All required fields (marked with *) must have values
2. Email must contain @ symbol (e.g., user@example.com)
3. Phone number must have at least 10 digits (spaces/dashes accepted)

#### Username Issues
**Problem**: "Tourist not found" message
**Solution**:
1. Use one of the demo usernames: jdoe, asmith, bwilson
2. Check for typos
3. Username field should be enabled for new entries

#### Application Not Starting
**Check**:
1. Java is installed: `java -version` in terminal
2. File permissions allow execution
3. If using JAR: verify it's a valid Java archive

### Application Controls

#### Buttons:
- **Load Data**: Fetches user data from server
- **Submit**: Saves modified data (enabled only after successful load)
- **Cancel**: Discards changes and resets form

#### Fields:
- **Username**: Initial input (becomes read-only after loading)
- **First/Last Name**: Required text fields
- **Email**: Required with format validation
- **Phone**: Required with length validation
- **Address**: Optional text field

### Demo Data

The system comes with sample users for testing:

| Username | First Name | Last Name | Email | Phone | Address |
|----------|------------|-----------|-------|-------|---------|
| jdoe | John | Doe | john.doe@example.com | +1234567890 | 123 Main St, City |
| asmith | Alice | Smith | alice.smith@example.com | +1987654321 | 456 Oak Ave, Town |
| bwilson | Bob | Wilson | bob.wilson@example.com | +1555123456 | 789 Pine Rd, Village |

### Security Notes

1. **Authentication**: Username-based access (simplified for demo)
2. **Data Protection**: Username cannot be changed during modification
3. **Confirmation**: All changes require explicit user confirmation
4. **Session Safety**: Cancel option available at any time

## Technical Notes for Administrators

### Database Management

The application uses in-memory storage with these features:
- Data persists while application is running
- Changes are saved per-user
- Sample data loads on startup
- Real server implementation would replace the `DataManager` class

### Extension Points

1. **Real Database Integration**: Replace `touristDatabase` HashMap with JDBC/SQL
2. **Enhanced Validation**: Add custom validation rules to `Tourist.validateFields()`
3. **Network Configuration**: Modify `checkServerConnection()` for real server monitoring
4. **UI Customization**: Modify `ModifyTouristDataGUI.initializeUI()` for different layouts

### Code Structure

```
ModifyTouristDataGUI.java   # Main GUI and controller
Tourist.java                # Data model with validation
DataManager.java            # Data persistence layer
```

## Quality Requirements Met

The application implements all quality requirements from the use case:

- ✅ **Comprehensive Validation**: All field types validated
- ✅ **Error Handling**: Clear messages for invalid data
- ✅ **User Confirmation**: Explicit confirmation required
- ✅ **Connection Handling**: Server interruption scenarios managed
- ✅ **User-friendly Interface**: Intuitive form-based interaction

## Support

For technical support or additional features:
1. Check application status messages
2. Review error dialogs for specific issues
3. Contact your ETOUR system administrator
4. Developers: Review inline code comments for customization guidance

---

**Version**: 1.0  
**Last Updated**: October 2024  
**System**: ETOUR Tourist Management  
**Use Case**: Modify Tourist Data (UC-TOUR-004)