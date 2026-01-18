```markdown
# ETOUR Search Preference Modification System - User Manual

## 🌟 Overview

The ETOUR Search Preference Modification System is a desktop application that allows registered tourists to view, edit, and manage their travel search preferences. The system follows a secure authentication flow and provides an intuitive graphical interface for preference management.

## ✨ Main Features

### 1. **Authentication System**
- Secure user login with username and password validation
- Server connection monitoring and automatic retry capability
- Mock database with pre-configured test users

### 2. **Search Preference Management**
- **Destination Type**: Choose from Beach, Mountain, City, Countryside, Historical, or Island
- **Budget Control**: Set maximum travel budget (from $100 to $10,000)
- **Travel Distance**: Adjust preferred travel distance with slider control (0-2000km)
- **Accommodation**: Select accommodation type (Hotel, Hostel, Airbnb, Resort, Camping)
- **Preferences**: Enable family-friendly options and adventure activities
- **Food Preferences**: Choose from Vegetarian, Non-vegetarian, or Vegan diets

### 3. **Advanced Features**
- **Change Confirmation**: Visual comparison of current vs. proposed changes before saving
- **Data Persistence**: Preferences are saved and can be retrieved in future sessions
- **Error Handling**: Graceful handling of server connection interruptions
- **Default Reset**: Option to reset all preferences to default values

## 🚀 System Requirements

### Hardware Requirements
- Processor: 1 GHz or faster
- RAM: 512 MB minimum
- Disk Space: 10 MB free space

### Software Requirements
- **Operating System**: Windows, macOS, or Linux
- **Java Runtime**: Java 8 or higher (Java SE 8+)
- **Display**: 1024×768 minimum resolution

## 📦 Installation Guide

### Step 1: Install Java Runtime Environment (JRE)

**Windows:**
1. Visit [Oracle Java Downloads](https://www.oracle.com/java/technologies/downloads/)
2. Download the latest JRE for Windows
3. Run the installer and follow the installation wizard
4. Add Java to your system PATH if not done automatically

**macOS:**
```bash
# Using Homebrew
brew install --cask oracle-jdk

# Or manually download from Oracle website
```

**Linux (Ubuntu/Debian):**
```bash
sudo apt update
sudo apt install default-jre
```

### Step 2: Install Java Development Kit (JDK) for Development

If you plan to modify or compile the code:
```bash
# Windows: Download from Oracle website
# macOS: brew install openjdk
# Linux: sudo apt install default-jdk
```

### Step 3: Verify Java Installation

Open terminal/command prompt and run:
```bash
java -version
```

You should see output similar to:
```
java version "1.8.0_301"
Java(TM) SE Runtime Environment (build 1.8.0_301-b09)
Java HotSpot(TM) 64-Bit Server VM (build 25.301-b09, mixed mode)
```

## 🔧 Setup and Configuration

### Option 1: Using Pre-compiled JAR (Recommended)

1. **Download the application files:**
   ```
   ETOUR_App.jar
   README.txt
   ```

2. **Run the application:**
   ```bash
   java -jar ETOUR_App.jar
   ```

### Option 2: Compile from Source

1. **Download all source files:**
   ```
   Main.java
   AuthenticationFrame.java
   SearchPreference.java
   DatabaseSimulator.java
   SearchPreferenceManager.java
   PreferenceChangeDialog.java
   ```

2. **Compile all Java files:**
   ```bash
   javac *.java
   ```

3. **Run the application:**
   ```bash
   java Main
   ```

## 👤 Getting Started: First-Time Users

### Available Test Accounts
For demonstration purposes, three test accounts are pre-configured:

| Username   | Password      | Pre-configured Preferences                     |
|------------|---------------|-----------------------------------------------|
| tourist1   | password123   | Mountain, Budget $1500, Vegetarian            |
| tourist2   | welcome2023   | City, Budget $3000, Family-friendly           |
| john       | travel123     | Default values (Beach, Budget $2000, Hotel)   |

### Step-by-Step Usage Guide

#### Step 1: Login Screen
1. Launch the application
2. Enter your username and password
3. Click "Login" or press Enter in the password field
4. For testing, use one of the pre-configured accounts above

![Login Screen](login_screenshot.png)

#### Step 2: Modify Preferences
Once authenticated, the main preference modification screen appears with your current preferences loaded.

1. **Destination Type**: Select your preferred destination from the dropdown
2. **Max Budget**: Use the spinner to set your maximum travel budget
3. **Travel Distance**: Drag the slider to set your preferred travel distance
4. **Accommodation**: Choose your preferred accommodation type
5. **Checkboxes**: 
   - ☑️ "Include family-friendly options" if traveling with family
   - ☑️ "Include adventure activities" if interested in adventure sports
6. **Food Preference**: Select your dietary preference using radio buttons

#### Step 3: Save Changes
1. Click "Save Changes" button
2. A confirmation dialog appears showing **Current vs New** preferences
3. Review the changes carefully
4. Click "Confirm Changes" to save or "Cancel" to go back

![Confirmation Dialog](confirmation_screenshot.png)

#### Step 4: Additional Options
- **Cancel**: Click to cancel all changes and return to login screen
- **Reset to Defaults**: Restore all preferences to system defaults
- **Logout**: The system automatically logs out after saving or canceling

## 🎯 Use Case Flow Demonstration

This application follows the exact use case flow described in requirements:

### Flow of Events
1. **Access functionality**: User logs in to access preference modification
2. **Display preferences**: Current preferences loaded and displayed in form format
3. **Edit and submit**: User modifies fields and clicks "Save Changes"
4. **Ask confirmation**: System shows side-by-side comparison dialog
5. **Confirm changes**: User reviews and confirms the changes
6. **Memorize preferences**: System saves changes to database

### Exit Conditions (All Implemented)
- ✅ **Success**: "Search preferences have been successfully updated!" message
- ✅ **Cancellation**: User can cancel at any time with confirmation prompt
- ✅ **Server Interruption**: Automatic retry mechanism with status display

## ⚠️ Troubleshooting

### Common Issues and Solutions

#### 1. "Java not found" Error
**Problem**: Application won't start
**Solution**: 
```bash
# Verify Java installation
java -version

# If not installed, download and install Java from:
# https://www.java.com/en/download/
```

#### 2. "Could not find main class" Error
**Problem**: Compilation issues
**Solution**:
```bash
# Ensure all files are in the same directory
javac *.java
java Main
```

#### 3. Server Connection Issues
**Problem**: "ETOUR Server connection interrupted" message
**Solution**:
- Wait a few moments
- Click "Retry Connection" button
- Check your internet connection

#### 4. Authentication Failures
**Problem**: "Invalid credentials" error
**Solution**:
- Use one of the test accounts listed above
- Ensure correct username and password combination
- Clear the password field and re-enter

#### 5. GUI Display Issues
**Problem**: Text appears too small or layout is broken
**Solution**:
- Check your screen resolution (minimum 1024×768)
- Try running in fullscreen mode
- Restart the application

## 🔒 Security Features

### Authentication Security
- Password fields mask input with asterisks (*)
- Credentials validated against secure database simulation
- Automatic logout after session completion

### Data Protection
- Preferences are user-specific and isolated
- Changes are not committed until explicit confirmation
- Server interruptions don't cause data corruption

## 📊 Database Information

### Simulated Database Structure
The system uses an in-memory database simulation with these data structures:

```java
// User credentials storage
Map<String, String> userCredentials = {
    "tourist1": "password123",
    "tourist2": "welcome2023",
    "john": "travel123"
}

// User preferences storage
Map<String, SearchPreference> userPreferences = {
    "tourist1": {destination: "Mountain", budget: 1500, ...},
    "tourist2": {destination: "City", budget: 3000, ...},
    "john": {destination: "Beach", budget: 2000, ...}
}
```

## 🔧 Advanced Features

### Server Connection Simulation
The system simulates server interruptions for testing:
- 10% chance of interruption during save operations
- Visual status panel shows connection status
- Manual retry button for reconnection attempts

### Preference Comparison
The confirmation dialog provides:
- Side-by-side comparison of old vs. new preferences
- Color-coded highlighting (green for new preferences)
- Scrollable view for detailed review

## 📱 Keyboard Shortcuts

| Action                    | Shortcut Key          |
|---------------------------|-----------------------|
| Login                    | Enter (in password field) |
| Select next field        | Tab                   |
| Select previous field    | Shift + Tab           |
| Toggle checkbox          | Space                 |
| Select radio button      | Arrow Keys            |
| Open dropdown            | Alt + Down Arrow      |
| Save changes             | Ctrl + S (when focused) |
| Cancel                   | Esc                   |

## 🆘 Support and Contact

### For Technical Issues
- Check the troubleshooting guide above
- Verify Java installation and version
- Ensure all application files are in the same directory

### Reporting Bugs
If you encounter bugs:
1. Note the exact error message
2. Record steps to reproduce the issue
3. Check if it's listed in the troubleshooting section

### Additional Resources
- **Java Official Documentation**: https://docs.oracle.com/javase/
- **Swing GUI Tutorial**: https://docs.oracle.com/javase/tutorial/uiswing/
- **ETOUR System Documentation**: Contact system administrator

## ♻️ Environmental Considerations

### Default Settings (Eco-Friendly)
The system encourages eco-friendly travel preferences by default:
- Moderate travel distances (default: 500km)
- Reasonable budgets to prevent over-tourism
- Family-friendly options prioritized

### Reset to Eco-Defaults
Use "Reset to Defaults" to restore these eco-friendly settings.

## 🔄 Version History

### Version 1.0 (Current)
- Initial release
- Complete implementation of ModifySearchPreference use case
- All requirements met including authentication, confirmation, and error handling
- Mock database with three test users
- Responsive GUI with modern Java Swing interface

## 📄 License and Usage

This software is provided for demonstration purposes of the ETOUR system's search preference modification functionality. 

### Usage Guidelines
1. For personal, non-commercial use only
2. Do not modify and redistribute without permission
3. Test accounts are for demonstration purposes
4. All data is stored locally in memory and not persisted

---

**Happy Travel Planning with ETOUR!** ✈️🏖️🏔️

*This application demonstrates a complete implementation of the ModifySearchPreference use case with full authentication flow, data validation, confirmation dialogs, and error handling as specified in the requirements.*
```