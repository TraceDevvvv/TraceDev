```markdown
# ModifyGenericPreference Application - User Manual

## Overview

The ModifyGenericPreference application is a Java-based desktop software designed for tourism systems that allows authenticated tourists to modify their generic personal preferences. This application implements the complete "ModifyGenericPreference" use case with a user-friendly graphical interface, proper data persistence, and error handling.

## Main Functions

### 1. User Authentication & Entry
- Simulated authentication with a predefined tourist user
- Entry condition validation (user must be authenticated)
- Welcome screen displaying user information

### 2. Preference Management
- View current generic personal preferences
- Modify preferences through an intuitive form interface
- Support for multiple preference categories:
  - Language preferences (6 supported languages)
  - Theme selection (light, dark, auto)
  - Notification settings (email and push notifications)
  - Currency selection (6 major currencies)
  - Date format customization (4 formats)
  - Results per page configuration (5-100)
  - Accessibility mode toggle

### 3. Data Persistence
- Automatic saving of preferences to local storage
- Load preferences from previous sessions
- Simulated server connection (ETOUR server) with fault tolerance

### 4. Error Handling
- Connection interruption detection and recovery
- Input validation and data integrity checks
- User-friendly error messages
- Graceful failure handling

## System Requirements

### Hardware Requirements
- **Processor**: 1 GHz or faster
- **Memory**: 512 MB RAM minimum (1 GB recommended)
- **Storage**: 10 MB available disk space
- **Display**: 1024x768 screen resolution or higher

### Software Requirements
- **Operating System**: Windows 7+, macOS 10.12+, or Linux with Java support
- **Java Runtime**: Java 8 or later (Java 11 recommended)
- **Window Manager**: Any compatible windowing system for Swing support

## Installation

### Step 1: Install Java Runtime Environment (JRE)

#### Windows
1. Visit [Oracle Java Downloads](https://www.oracle.com/java/technologies/downloads/)
2. Download JRE 8 or later for Windows
3. Run the installer and follow the installation wizard
4. Verify installation by opening Command Prompt and typing:
   ```cmd
   java -version
   ```

#### macOS
1. Install using Homebrew:
   ```bash
   brew install --cask temurin
   ```
2. Or download from [Adoptium](https://adoptium.net/)
3. Verify installation:
   ```bash
   java -version
   ```

#### Linux (Ubuntu/Debian)
```bash
sudo apt update
sudo apt install openjdk-11-jre
java -version
```

### Step 2: Download and Compile the Application

1. **Create a project directory**:
   ```bash
   mkdir ModifyGenericPreference
   cd ModifyGenericPreference
   ```

2. **Create the package structure**:
   ```bash
   mkdir -p modifygenericpreference
   ```

3. **Copy all Java files** into the `modifygenericpreference` directory. You need the following files:
   - `ModifyGenericPreferenceApp.java`
   - `Tourist.java`
   - `Preferences.java`
   - `PreferenceService.java`
   - `MainFrame.java`
   - `PreferenceForm.java`

4. **Compile all Java files**:
   ```bash
   javac modifygenericpreference/*.java
   ```

### Step 3: Run the Application

From the parent directory (where `modifygenericpreference` folder exists), run:
```bash
java modifygenericpreference.ModifyGenericPreferenceApp
```

## How to Use

### Starting the Application
1. Launch the application using the command above
2. The system starts with a simulated authenticated user "tourist123"
3. You'll see the main welcome screen with the user's name and instructions

### Main Interface

#### Welcome Screen
- Displays welcome message with authenticated user's name
- Shows entry conditions that have been met
- Provides step-by-step instructions for preference modification
- Contains two buttons:
  - **Modify Generic Preferences** - Opens preference modification form
  - **Exit Application** - Closes the application with confirmation

### Modifying Preferences

#### Step 1: Access Preference Form
1. Click the **"Modify Generic Preferences"** button on the main screen
2. The system checks connection to the ETOUR server
3. If connection is successful, the preference form opens

#### Step 2: View Current Preferences
The form displays all current preference settings in editable fields:
- **Preferred Language**: Dropdown with 6 language options
- **Theme**: Light, Dark, or Auto theme selection
- **Email Notifications**: Checkbox to enable/disable email notifications
- **Push Notifications**: Checkbox to enable/disable push notifications
- **Currency**: Dropdown with 6 currency options
- **Date Format**: Dropdown with 4 date format options
- **Results Per Page**: Spinner control (5-100, increment by 5)
- **Accessibility Mode**: Checkbox to enable accessibility features

#### Step 3: Edit Preferences
1. Modify any field as needed:
   - Select new values from dropdowns
   - Check/uncheck notification options
   - Adjust results per page using up/down arrows
   - Enable/disable accessibility mode

#### Step 4: Submit Changes
1. Click **"Submit Changes"** button
2. A confirmation dialog appears showing all changes to be made
3. Review the changes and click:
   - **Yes** to confirm and save changes
   - **No** to return to the form without saving

#### Step 5: Save Process
1. After confirmation, a progress dialog appears
2. The system simulates connection to ETOUR server
3. Preferences are saved to local storage
4. Success or error notification is displayed

### Exit Conditions

#### Successful Modification
- System displays success notification
- Preferences are saved to `tourist_preferences.dat` file
- Form closes automatically
- User returns to main screen

#### User Cancellation
Two ways to cancel:
1. **Cancel button** on preference form - closes form without saving
2. **Exit Application** button on main screen - closes entire application with confirmation

#### Server Connection Interruption
- If ETOUR server connection fails (5% chance simulated), system displays error
- Preferences are not saved
- User can retry when connection is restored

## Troubleshooting

### Common Issues

#### 1. Java Not Found Error
```
Error: Could not find or load main class modifygenericpreference.ModifyGenericPreferenceApp
```
**Solution**:
- Verify Java is installed: `java -version`
- Ensure you're running from the correct directory
- Check that all files are in the `modifygenericpreference` folder

#### 2. Compilation Errors
```
error: package javax.swing does not exist
```
**Solution**:
- Ensure you have Java SDK installed (not just JRE)
- On Linux, install development package: `sudo apt install openjdk-11-jdk`

#### 3. Window Not Displaying
**Solution**:
- Check if you have a window manager running
- For headless systems, add `-Djava.awt.headless=false` to Java command

#### 4. Permission Denied for File Access
```
java.io.FileNotFoundException: tourist_preferences.dat (Permission denied)
```
**Solution**:
- Run application with appropriate file permissions
- Check write permissions in current directory

### Data Files

#### Preference Storage
- Location: `tourist_preferences.dat` in application directory
- Format: Java serialized object
- Contains: All tourist preferences keyed by username

#### Clearing Preferences
To reset all preferences, delete the `tourist_preferences.dat` file before starting the application.

### Server Simulation
The application simulates ETOUR server connection with:
- 5% chance of connection interruption
- 10% chance of connection check failure
- 500ms simulated connection delay

## Best Pract

1. **Regular Backups**: The `tourist_preferences.dat` file is automatically backed up, but you should maintain your own backups
2. **Network Connection**: Ensure stable internet connection for optimal server communication
3. **Screen Resolution**: Use at least 1024x768 resolution for best form display
4. **Java Updates**: Keep Java updated to latest stable version for security and performance

## Support

For additional support:
1. Check that all system requirements are met
2. Verify Java installation and version
3. Ensure sufficient disk space and permissions
4. Contact system administrator for ETOUR server connection issues

## Security Notes

- This demonstration application uses simulated authentication
- In production, implement proper authentication mechanisms
- Preferences are stored locally in serialized format
- Consider encryption for sensitive preference data
- Regular security updates for Java runtime are recommended

## Version Information

- **Current Version**: 1.0
- **Java Version**: Compatible with Java 8+
- **Last Updated**: [Current Date]
- **Use Case Coverage**: Implements full ModifyGenericPreference use case flow

---

*Note: This is a demonstration application for the ModifyGenericPreference use case. For production deployment, additional security, logging, and error handling should be implemented.*
```