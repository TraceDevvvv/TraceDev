```markdown
# DeleteBanner Software - User Manual

## Overview

DeleteBanner is a Java-based desktop application designed for agency operators to manage banner advertisements associated with refreshment points on computer systems. The application provides an intuitive graphical interface for viewing, selecting, and deleting banner ads while handling server connection management with ETOUR servers.

## System Requirements

### Minimum Requirements
- **Operating System**: Windows 10/11, macOS 10.15+, or Linux (Ubuntu 18.04+)
- **Java Runtime**: Java 8 or higher (JDK/JRE 11 recommended)
- **RAM**: Minimum 512 MB
- **Storage**: 100 MB free space
- **Screen Resolution**: 1024x768 or higher

### Recommended Requirements
- **Java Runtime**: Java 11 or higher
- **RAM**: 1 GB or more
- **Storage**: 200 MB free space
- **Internet Connection**: Required for server connectivity

## Installation Guide

### Step 1: Install Java Runtime Environment
If you don't have Java installed, follow these steps:

#### Windows
1. Visit [Oracle Java Downloads](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://adoptium.net/)
2. Download the latest JRE for Windows
3. Run the installer and follow the setup wizard
4. Verify installation by opening Command Prompt and typing:
```bash
java -version
```

#### macOS
1. Install using Homebrew:
```bash
brew install openjdk
```
2. Or download from [Oracle's website](https://www.oracle.com/java/technologies/downloads/)
3. Verify installation in Terminal:
```bash
java -version
```

#### Linux (Ubuntu/Debian)
```bash
sudo apt update
sudo apt install default-jre
```

### Step 2: Set Up Application Files

1. **Create application directory**:
```bash
mkdir DeleteBannerApp
cd DeleteBannerApp
```

2. **Save the provided Java files** in the directory:
   - DeleteBannerFrame.java
   - BannerService.java

3. **Compile the application**:
```bash
javac DeleteBannerFrame.java BannerService.java
```

4. **Run the application**:
```bash
java DeleteBannerFrame
```

## File Structure

```
DeleteBannerApp/
├── DeleteBannerFrame.java    # Main GUI application
├── BannerService.java        # Data service and persistence layer
├── banners.dat              # Data persistence file (auto-generated)
└── manual.md                # This user manual
```

## Main Features

### 1. Refreshment Point Selection
- **Function**: Select from available refreshment points (Point A, B, C by default)
- **Purpose**: Access specific locations for banner management
- **Visual Indicator**: Dropdown selector showing all available points

### 2. Banner Display and Selection
- **Function**: View all banners associated with selected refreshment point
- **Purpose**: Review available banners before deletion
- **Visual Indicator**: Scrollable list with selection highlight
- **Edge Case Handling**: Displays "No banners available" message when empty

### 3. Server Connection Management
- **Function**: Automatic server connectivity checks to ETOUR
- **Status Indicators**:
  - 🟢 Green: Server connected
  - 🟠 Orange: Connection issues
  - ❌ Dialog: Connection failure with retry logic
- **Purpose**: Ensure data synchronization and handle interruptions gracefully

### 4. Banner Deletion Process
- **Multi-step Confirmation**:
  1. Select banner from list
  2. Click "Delete Selected Banner"
  3. Confirm deletion in dialog
  4. Success notification
- **Data Persistence**: Changes saved immediately to local storage
- **Undo Protection**: Requires explicit confirmation to prevent accidental deletions

### 5. Messaging and Status Updates
- **Real-time Feedback**: Message area at bottom of window
- **Operation Logging**: All actions and server status displayed
- **Error Messages**: Clear, user-friendly error notifications

## How to Use the Application

### Starting the Application
1. Navigate to the application directory
2. Run the compiled Java file:
```bash
java DeleteBannerFrame
```
3. The main window will appear with the title "Delete Banner - Agency Operator Console"

### Basic Workflow

#### Step 1: Select Refreshment Point
1. Locate the "Select Refreshment Point:" dropdown menu
2. Click the dropdown arrow
3. Choose your desired refreshment point (Point A, B, or C)
4. The system automatically loads associated banners

#### Step 2: View Available Banners
1. The banner list updates automatically when you select a point
2. Scroll through the list to view all banners
3. Each banner is displayed as text (e.g., "Banner 1 at Point A")
4. If no banners are available, you'll see a placeholder message

#### Step 3: Select Banner for Deletion
1. Click on a banner in the list to select it
2. The selected banner will be highlighted
3. Verify this is the correct banner you want to delete

#### Step 4: Initiate Deletion
1. Click the "Delete Selected Banner" button
2. The system will check server connectivity
3. If connection is successful, proceed to confirmation
4. If connection fails, you'll see an error message with retry option

#### Step 5: Confirm Deletion
1. A confirmation dialog will appear showing the banner text
2. Read the confirmation message carefully
3. Choose:
   - **YES**: Proceed with deletion
   - **NO**: Cancel the operation

#### Step 6: View Results
1. Successful deletion: Green success message appears
2. List automatically updates to show remaining banners
3. Operation details logged in the message area

## Visual Interface Guide

### Main Window Layout
```
┌─────────────────────────────────────────────────────────┐
│  Delete Banner - Agency Operator Console (Title)        │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  [Select Refreshment Point:] [Dropdown Menu]           │
│                                                         │
│  [Associated Banners:]                                 │
│  ┌─────────────────────────────────────────┐          │
│  │ • Banner 1 at Point A                   │          │
│  │ • Banner 2 at Point A                   │          │
│  │ • Banner 3 at Point A                   │          │
│  └─────────────────────────────────────────┘          │
│                                                         │
│  [Delete Selected Banner] Button                       │
│                                                         │
├─────────────────────────────────────────────────────────┤
│  Messages:                                              │
│  ┌─────────────────────────────────────────┐          │
│  │ Banner list updated for Point A.        │          │
│  │ Successfully deleted banner: Banner 1...│          │
│  └─────────────────────────────────────────┘          │
├─────────────────────────────────────────────────────────┤
│  Server: Connected 🟢 (Status Bar)                    │
└─────────────────────────────────────────────────────────┘
```

### Interface Components

#### 1. Title Section
- Application name and purpose
- Always visible at the top of the window

#### 2. Control Panel
**Refreshment Point Selector**
- Type: Dropdown menu
- Action: Updates banner list automatically
- Default: Point A (first available)

**Banner Display List**
- Type: Scrollable text list
- Selection: Single selection only
- Empty state: Shows placeholder message
- Refresh: Auto-updates on point selection

**Action Button**
- Label: "Delete Selected Banner"
- State:
  - Enabled: When a valid banner is selected
  - Disabled: When list is empty
- Color: Standard system button styling

#### 3. Message Area
- Purpose: Operation feedback and logging
- Content: Status updates, errors, confirmations
- Format: Timestamp-free, sequential messages
- Scrolling: Auto-scrolls to latest message

#### 4. Status Bar
- Location: Bottom of window
- Content: Server connection status
- Colors:
  - Green: Connected
  - Orange: Connection issues
- Real-time updates

## Server Connectivity Features

### Automatic Connection Monitoring
1. **Pre-operation Checks**: Before every deletion, system checks ETOUR server
2. **Retry Logic**: Three automatic retry attempts on failure
3. **Graceful Degradation**: Continue with local data if server unavailable
4. **Status Updates**: Real-time connectivity indicator

### Connection Scenarios

#### 🟢 Normal Operation
- Server reachable
- Data synchronized
- Full functionality available

#### 🟠 Connection Issues
- Intermittent connectivity
- Working with potentially outdated data
- Warning messages displayed
- Background retry attempts

#### ❌ Server Unavailable
- Connection attempts exhausted
- Operation blocked until connection restored
- Clear error message with troubleshooting steps

## Data Management

### Local Storage
- **File**: `banners.dat` (auto-generated)
- **Location**: Same directory as application
- **Format**: Serialized Java objects
- **Backup**: Not automatically created (user responsibility)

### Data Structure
```
Point A:
  - Banner 1 at Point A
  - Banner 2 at Point A

Point B:
  - Banner 1 at Point B
  - Banner 2 at Point B
  - Banner 3 at Point B

Point C:
  - Banner 1 at Point C
```

### Persistence Behavior
- **Auto-save**: After every successful deletion
- **Load on startup**: From `banners.dat` file
- **Default data**: Created if no data file exists
- **Corruption handling**: Reverts to defaults with warning

## Error Handling Guide

### Common Error Messages

#### "Please select a banner to delete."
- **Cause**: No banner selected before clicking delete button
- **Solution**: Click on a banner in the list to select it

#### "Cannot connect to ETOUR server. Please check your connection."
- **Cause**: Server unreachable after retry attempts
- **Solution**: 
  1. Check internet connection
  2. Verify server is running
  3. Wait 1 minute and try again
  4. Contact system administrator

#### "Invalid refreshment point selection."
- **Cause**: No refreshment point selected
- **Solution**: Select a point from the dropdown menu

#### "Failed to delete banner. Please try again."
- **Cause**: Internal data processing error
- **Solution**: 
  1. Try the operation again
  2. Restart the application
  3. Check disk space availability

### Connection Troubleshooting

#### Step 1: Basic Checks
```bash
# Check internet connectivity
ping 8.8.8.8

# Check if Java has network access
java -version
```

#### Step 2: Application-level Checks
1. Verify status bar shows "Server: Connected" in green
2. Check message area for connection-related messages
3. Try selecting different refreshment point

#### Step 3: File System Checks
1. Ensure `banners.dat` file exists and is not corrupted
2. Check file permissions (read/write access)
3. Verify available disk space

#### Step 4: Advanced Troubleshooting
1. Temporary files: Delete and restart application
2. Java cache: Clear Java runtime cache
3. Firewall: Ensure Java processes can access network

## Best Pract

### For Daily Use
1. **Startup**: Always verify server connection status (green indicator)
2. **Selection**: Double-check banner selection before deletion
3. **Confirmation**: Read confirmation dialog carefully before proceeding
4. **Verification**: Check message area for operation success confirmation
5. **Exit**: Close application using window close button (not force quit)

### Data Safety
1. **Regular Backups**: Manually copy `banners.dat` to backup location
2. **Power Outages**: Avoid unplugging system during operations
3. **Simultaneous Use**: Only one operator should use the application per system
4. **Update Strategy**: Back up data before updating Java or application

### Performance Optimization
1. **Memory**: Close other applications when using DeleteBanner
2. **Storage**: Keep at least 500MB free on application drive
3. **Network**: Use wired connection for better server reliability
4. **Updates**: Keep Java Runtime updated for security and performance

## Customization Options

### Adding New Refreshment Points
Currently requires code modification. Future versions may include GUI for this.

### Modifying Default Banners
Edit the `initializeDefaultBanners()` method in `BannerService.java`:
```java
banners.put("Your Point Name", 
    new ArrayList<>(Arrays.asList("Banner 1", "Banner 2")));
```

### Changing Server Connection Settings
Modify connection parameters in `BannerService.java`:
```java
// Current simulation: 20% failure chance
serverConnected = Math.random() > 0.2;

// Retry wait time (currently 1000ms)
Thread.sleep(1000);
```

## Keyboard Shortcuts

### Navigation
- **Tab**: Move between controls
- **Up/Down Arrows**: Navigate banner list
- **Space/Enter**: Select item in dropdown or list
- **Alt + Underlined Letter**: Access menu shortcuts (if implemented)

### Actions
- **Ctrl + D**: Quick delete (still requires confirmation)
- **Escape**: Cancel current operation/close dialog
- **F5**: Refresh banner list (future version)

## Limitations

### Known Issues
1. **Single-user Only**: No multi-user concurrency support
2. **No Audit Trail**: Deletion operations not logged for auditing
3. **Limited Undo**: No undo functionality after confirmation
4. **No Export**: Cannot export banner lists to external formats
5. **Manual Backup**: No built-in backup system

### System Constraints
1. **File-based Storage**: Performance degrades with very large datasets (>10,000 banners)
2. **Memory**: All data loaded into memory on startup
3. **Network**: Requires continuous connectivity for real-time data
4. **Platform**: Java dependency limits some system integrations

## Support and Contact

### Getting Help
1. **Application Status**: Check message area for clues
2. **Documentation**: Refer to this manual
3. **Error Codes**: Note exact error messages for support

### System Requirements Validation
Run this verification script if experiencing issues:
```bash
# Save as verify_environment.sh
#!/bin/bash
echo "Java Version:"
java -version
echo -e "\nDisk Space:"
df -h .
echo -e "\nMemory:"
free -h
echo -e "\nNetwork:"
ping -c 2 google.com
```

## Frequently Asked Questions

### Q: The application won't start. What should I do?
**A**: 
1. Verify Java is installed: `java -version`
2. Ensure you're in the correct directory
3. Check that both `.java` files are present
4. Try recompiling: `javac *.java`

### Q: My deleted banner reappeared after restarting the application.
**A**: 
1. Check if `banners.dat` file was modified by another process
2. Verify you have write permissions to the file
3. Look for backup/restore processes that might be interfering

### Q: The server status is always orange, even with good internet.
**A**: This is likely the simulated connection failure. The simulation includes random failures for testing. In production, this would be removed.

### Q: Can I recover a deleted banner?
**A**: Not through the application interface. You would need to:
1. Restore from backup file
2. Manually edit `banners.dat` (advanced users only)
3. Re-add the banner through code modification

### Q: How do I add more refreshment points?
**A**: Currently requires modifying the source code in `BannerService.java` and recompiling. Contact development team for custom builds.

## Troubleshooting Checklist

### Application Won't Start
- [ ] Java Runtime installed and in PATH
- [ ] Sufficient disk space (>100MB free)
- [ ] Read/Write permissions in current directory
- [ ] Both `.java` files present
- [ ] Successful compilation with `javac`

### Connection Issues
- [ ] Internet connection active
- [ ] No firewall blocking Java
- [ ] Server status indicator visible
- [ ] Message area showing connection attempts
- [ ] Retry after 1-2 minutes

### Data Issues
- [ ] `banners.dat` file exists
- [ ] File not corrupted or locked
- [ ] Default data loads on first run
- [ ] Changes persist between runs
- [ ] No permission errors in console

### Performance Problems
- [ ] Sufficient RAM available
- [ ] Not too many banners (>1000 may slow performance)
- [ ] Regular application restarts
- [ ] Disk not nearly full
- [ ] No other Java applications competing for resources

## Glossary

### Technical Terms
- **ETOUR Server**: The backend server system for banner management
- **Refreshment Point**: A specific location/computer with associated banners
- **Banner**: Advertisement display element managed by the system
- **Persistence**: Saving data between application sessions
- **Serialization**: Java's method of saving objects to files

### Application Terms
- **Message Area**: Bottom section showing operation feedback
- **Status Bar**: Server connectivity indicator
- **Control Panel**: Main interaction area with buttons and lists
- **Confirmation Dialog**: Pop-up window requiring user approval

## Version History

### Current Version: 1.0
- Initial release
- Basic banner deletion functionality
- File-based persistence
- Server connection simulation
- GUI interface with Swing

### Planned Features (Future Versions)
- Multi-user support with authentication
- Network-based server communication
- Enhanced reporting and analytics
- Bulk operations
- Import/Export capabilities
- Cloud backup integration

## Legal and Compliance

### Data Handling
- All data stored locally on user's machine
- No external data transmission in current version
- User responsible for data backup and security

### License
Proprietary software for internal agency use only. Distribution requires written permission.

### Compliance
Current version does not handle personally identifiable information (PII). If banner data includes PII in future implementations, additional compliance measures will be required.

---

## Quick Reference Card

### Startup Sequence
1. Open terminal/command prompt
2. Navigate to application directory
3. Run: `java DeleteBannerFrame`
4. Verify green "Server: Connected" status

### Daily Operations
1. Select point from dropdown
2. Click banner to select
3. Click "Delete Selected Banner"
4. Confirm in dialog
5. Check success message

### Emergency Procedures
**Data Corruption**:
```bash
# Backup corrupted file
cp banners.dat banners.dat.backup
# Delete corrupted file
rm banners.dat
# Restart application (will create new default)
```

**Application Freeze**:
1. Close window normally if possible
2. Use system task manager to kill Java process
3. Restart application

**Persistent Connection Issues**:
1. Check network cables/connections
2. Restart router if applicable
3. Contact IT support for server status
4. Work offline with local data if critical

---

*End of User Manual*

For additional support or feature requests, contact the ChatDev development team.
```

This user manual provides comprehensive documentation for the DeleteBanner application, covering installation, usage, troubleshooting, and best pract. The manual is structured to be accessible to both technical and non-technical users, with clear sections for quick reference and detailed explanations for complex scenarios.