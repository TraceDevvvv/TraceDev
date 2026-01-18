```markdown
# AssignRemoveTeachings - User Manual

## Overview

The **AssignRemoveTeachings** software is a Java-based application designed for administrators to manage teaching assignments for addresses within an educational institution or organization. This system allows administrators to assign or remove multiple teachings (courses/lessons) from specific addresses, following a structured workflow that includes authentication, address selection, and teaching management.

## Main Features

### 1. **Secure Administrator Authentication**
- Role-based access control requiring administrator credentials
- Secure login dialog with username/password validation
- Prevents unauthorized access to the system

### 2. **Address Management**
- View detailed address information (ID, street, city, zip code)
- Browse multiple addresses in the system
- See current teaching assignments for each address

### 3. **Teaching Assignment System**
- **Add Teachings**: Assign available teachings to selected addresses
- **Remove Teachings**: Remove existing teachings from addresses
- **Batch Operations**: Select multiple teachings for assignment/removal simultaneously
- **Real-time Updates**: See changes reflected immediately in both lists

### 4. **Simulated Database & Server Integration**
- Simulated database operations with logging
- SMOS server communication simulation (with interruption handling)
- Persistent operation tracking and error handling

### 5. **User-Friendly GUI Interface**
- Two-panel design for clear workflow separation
- Visual distinction between current and available teachings
- Intuitive drag-and-drop like functionality with buttons
- Responsive design with clear visual feedback

## System Requirements

### Hardware Requirements
- Minimum: 2GB RAM, 500MB disk space
- Recommended: 4GB RAM, 1GB disk space

### Software Requirements
- **Java Development Kit (JDK)**: Version 8 or higher
- **Operating System**: Windows 10+, macOS 10.13+, or Linux (Ubuntu 18.04+)
- **Screen Resolution**: 1024×768 minimum, 1920×1080 recommended

## Installation Guide

### Step 1: Install Java

#### Windows:
1. Download JDK from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html)
2. Run the installer and follow the installation wizard
3. Set JAVA_HOME environment variable:
   - Open System Properties → Advanced → Environment Variables
   - Create new system variable: `JAVA_HOME = C:\Program Files\Java\jdk-version`
   - Add to PATH: `%JAVA_HOME%\bin`

#### macOS:
```bash
# Using Homebrew:
brew install --cask oracle-jdk

# Verify installation:
java -version
```

#### Linux (Ubuntu/Debian):
```bash
# Update package list
sudo apt update

# Install OpenJDK
sudo apt install openjdk-11-jdk

# Verify installation
java -version
```

### Step 2: Download the Application Files

Download all the following Java files into a single directory (e.g., `AssignRemoveTeachingsApp`):
- `Main.java`
- `Address.java`
- `Teaching.java`
- `AddressTeachingManager.java`
- `DatabaseSimulator.java`
- `AssignRemoveTeachingsGUI.java`
- `LoginDialog.java`

### Step 3: Compile the Application

Open terminal/command prompt and navigate to the directory containing the Java files:

```bash
# Compile all Java files
javac *.java

# Or compile individually if needed:
javac Main.java Address.java Teaching.java AddressTeachingManager.java \
DatabaseSimulator.java AssignRemoveTeachingsGUI.java LoginDialog.java
```

### Step 4: Run the Application

```bash
# Run the compiled application
java Main
```

## How to Use the Software

### Step 1: Login as Administrator

1. **Launch the Application**: Run `java Main` from the command line
2. **Login Screen**:
   - Username: `admin` (default)
   - Password: `password` (default)
3. **Press Login** or press Enter to authenticate
   - *Tip: Press Tab to navigate between fields*

### Step 2: View Address Details

1. **Address Selection Panel** appears after successful login
2. **Select an Address**: Use the dropdown menu to choose an address
3. **View Details**: Address information appears including:
   - Address ID
   - Street, City, Zip Code
   - Current assigned teachings (with count)
4. **Proceed**: Click "Manage Teachings for this Address" button

### Step 3: Manage Teachings for Selected Address

#### Panel Layout:
- **Left Panel**: "Current Teachings (To Remove)"
  - Shows teachings currently assigned to the address
  - Select teachings you want to remove
- **Middle Panel**: Control buttons
  - "Back to Address Details": Returns to address view
  - "SEND CHANGES": Saves all modifications
- **Right Panel**: "Available Teachings (To Assign)"
  - Shows teachings available for assignment
  - Select teachings you want to assign

#### To Assign Teachings:
1. From the **Available Teachings** panel (right):
   - Select one or multiple teachings using:
     - **Click**: Select single item
     - **Ctrl+Click**: Select multiple individual items
     - **Shift+Click**: Select range of items
     - **Ctrl+A**: Select all items
2. Click "**Assign Selected Teachings >>**" button
3. Selected teachings move to Current Teachings panel

#### To Remove Teachings:
1. From the **Current Teachings** panel (left):
   - Select teachings to remove (multiple selection supported)
2. Click "**<< Remove Selected Teachings**" button
3. Selected teachings move to Available Teachings panel

#### Teaching Information Display:
Each teaching shows:
- Course name (bold)
- Teaching ID
- Instructor name
- Duration in hours
- *Hover over item for detailed description*

### Step 4: Save Changes

1. After making all assignments/removals:
   - **Review selections** in both panels
   - Ensure correct teachings are in Current panel (will be saved)
2. Click "**SEND CHANGES**" button (green button in middle)
3. **Confirmation Dialog** shows:
   - Number of teachings assigned
   - Number of teachings removed
   - Success message
4. Click OK to return to Address Details view

### Step 5: View Updated Address Details

1. System automatically returns to Address Details panel
2. Updated teaching list reflects changes made
3. To manage another address:
   - Select different address from dropdown
   - Click "Manage Teachings for this Address" again

## Workflow Example

**Scenario**: Administrator needs to update teaching assignments for "123 Main St" address

1. **Login** with admin credentials
2. **Select** "ADDR001 - 123 Main St, New York 10001" from dropdown
3. **Click** "Manage Teachings for this Address"
4. **Assign** "Physics" and "Chemistry" from Available → Current panel
5. **Remove** "Mathematics 101" from Current → Available panel
6. **Click** "SEND CHANGES"
7. **View** updated address showing Physics, Chemistry, and Computer Science

## Troubleshooting

### Common Issues and Solutions:

#### 1. "Cannot find or load main class Main"
```bash
# Ensure you're in correct directory
cd /path/to/AssignRemoveTeachingsApp

# Recompile all files
javac *.java

# Check compilation success
ls *.class
```

#### 2. "Login failed" error
- Default credentials: username=`admin`, password=`password`
- Check caps lock is off
- Ensure no extra spaces in fields

#### 3. "SMOS Server Error" message
- This is a simulated server interruption (10% chance)
- Simply click "SEND CHANGES" again to retry
- System automatically resets connection

#### 4. Blank or unresponsive GUI
```bash
# Check Java version compatibility
java -version

# Ensure all files compiled properly
javac -version
```

#### 5. Teaching lists not updating
- Click "Back to Address Details" then re-enter management panel
- Selections are only saved when "SEND CHANGES" is clicked

## Security Notes

### Default Credentials:
- **Username**: `admin`
- **Password**: `password`
- *For production use, modify `LoginDialog.java` to implement proper authentication*

### Data Persistence:
- This is a demonstration version
- Teaching assignments reset on application restart
- For persistent storage, modify `AddressTeachingManager.java`

## Customization Options

### Modify Sample Data:
Edit `initializeSampleData()` method in `AddressTeachingManager.java` to:
- Add/remove addresses
- Change teaching offerings
- Set initial assignments

### Change Authentication:
Edit `authenticateUser()` method in `LoginDialog.java` to:
- Implement database authentication
- Add multiple user roles
- Enhance password security

### SMOS Server Simulation:
Adjust `DatabaseSimulator.java` to:
- Change interruption probability
- Modify communication delay
- Implement actual server integration

## Keyboard Shortcuts

| Action | Shortcut |
|--------|----------|
| Login | Enter in any field |
| Logout | Close application |
| Select Multiple | Ctrl+Click |
| Select Range | Shift+Click |
| Select All | Ctrl+A |
| Navigate Fields | Tab |
| Send Changes | Click SEND button |

## Best Pract

1. **Always review** selections before clicking "SEND CHANGES"
2. **Use multiple selection** for batch operations
3. **Check address details** after modifications
4. **Log out** properly by closing the application
5. **Keep credentials** secure in production environments

## Support

For issues or questions:
1. Check the troubleshooting section above
2. Verify Java installation and compilation
3. Review the provided code comments
4. Ensure all files are in the same directory

## Version Information

- **Current Version**: 1.0
- **Release Date**: October 2024
- **Java Version**: Compatible with JDK 8+
- **License**: Demonstration/Educational Use

---

*Note: This software is designed for educational and demonstration purposes. For production use, implement proper database integration, security measures, and error handling.*
```