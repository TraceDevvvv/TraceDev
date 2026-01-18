```markdown
# Tourist Site Bookmark Manager - User Manual

## Overview

The Tourist Site Bookmark Manager is a Java-based desktop application designed to help tourists manage their favorite travel destinations. The application allows users to view, manage, and remove sites from their bookmarks, following the "DeleteSiteFromPreferences" use case specification. This application simulates a tourism system where users can manage their preferred sites with proper validation, confirmation dialogs, and error handling.

## Main Features

### Complete Bookmark Management System
- **View Bookmarked Sites**: Display all saved tourist sites in a clear, organized list
- **Remove Individual Sites**: Select and remove specific sites from bookmarks
- **Real-time Updates**: Automatic refresh of the site list after operations
- **Connection Simulation**: Test handling of server connection disruptions

### User-Friendly Interface
- **Modern GUI**: Clean, intuitive Swing-based interface with proper visual feedback
- **Clear Instructions**: Step-by-step guidance displayed prominently
- **Color-coded Status**: Visual indicators for different system states (normal, warning, error)
- **Responsive Buttons**: Clearly labeled buttons with appropriate visual cues

### Robust Error Handling
- **Connection Management**: Simulate and handle ETOUR server connection issues
- **User Cancellation**: Properly handle user-initiated operation cancellations
- **Input Validation**: Prevent invalid operations with informative error messages
- **Edge Case Management**: Handle empty lists and invalid selections gracefully

## System Requirements

### Software Requirements
- **Java Runtime Environment (JRE)**: Version 8 or higher
- **Operating System**: Windows, macOS, or Linux
- **Display**: Minimum 1024×768 resolution recommended

### Java Dependencies
The application uses standard Java libraries:
- `javax.swing` - For graphical user interface components
- `java.awt` - For layout management and events
- No external dependencies required

## Installation and Setup

### Step 1: Install Java
1. **Check if Java is installed**:
   - Open command prompt/terminal
   - Type: `java -version`
   - If you see version information, Java is installed. If not, proceed to Step 2

2. **Download and install Java**:
   - Visit [Oracle Java Downloads](https://www.oracle.com/java/technologies/javase-downloads.html) or use OpenJDK
   - Download the appropriate version for your operating system
   - Run the installer and follow the prompts
   - Verify installation by running `java -version` again

### Step 2: Obtain the Application Files
The application consists of four Java files that must be in the same directory:

1. **TouristPreferenceManager.java** - Main GUI application
2. **PreferenceManager.java** - Core logic for bookmark management
3. **Site.java** - Data model for tourist sites
4. **Manual.md** - This documentation file

### Step 3: Compile the Application
1. Open command prompt/terminal
2. Navigate to the directory containing the Java files
3. Compile all files using:
   ```bash
   javac *.java
   ```
4. Verify that four `.class` files were created:
   - TouristPreferenceManager.class
   - PreferenceManager.class
   - Site.class
   - Any other generated class files

## How to Use the Application

### Starting the Application
1. **From command line**:
   ```bash
   java TouristPreferenceManager
   ```
2. The application window will open with sample data loaded automatically

### Application Layout
The interface is divided into three main sections:

1. **Top Section - Instructions**:
   - Clear, step-by-step instructions for using the application
   - Blue-bordered panel for easy visibility

2. **Middle Section - Site List**:
   - Shows all currently bookmarked tourist sites
   - Each site displays: ID, Name, and Location
   - Scrollable area to accommodate many sites
   - Gray border for clean separation

3. **Bottom Section - Controls**:
   - Three action buttons with distinct colors
   - Status display area showing system messages
   - Real-time feedback on operations

### Managing Sites

#### Viewing Bookmarks
- The main list automatically displays all bookmarked sites
- Sample sites are loaded on startup for demonstration
- Each site shows: `ID: Site Name (Location)`

#### Removing a Site (Main Function)

**Step 1: Select a Site**
1. Click on any site in the list
2. The selected site will be highlighted
3. For demonstration, the application includes 8 sample sites from famous locations worldwide

**Step 2: Initiate Removal**
1. Click the **"Remove Selected Site"** button (Red button)
2. A confirmation dialog will appear showing:
   - Site details (Name, Location, ID)
   - Confirmation question

**Step 3: Confirm or Cancel**
1. To proceed with removal:
   - Click **"Yes"** in the confirmation dialog
   - The system will check server connection
   - If successful, site will be removed
   - Success message will appear

2. To cancel removal:
   - Click **"No"** in the confirmation dialog
   - Operation will be cancelled
   - Status will show "Removal cancelled by user"

**Step 4: Verify Removal**
- The site list will automatically update
- Removed site will no longer appear
- Status bar shows removal confirmation

#### Refreshing the List
- Click the **"Refresh List"** button (Blue button)
- The list will reload from the backend
- Useful if you suspect display issues
- Status shows "List refreshed successfully"

### Testing Connection Issues

#### Simulating Connection Disruption
1. Click the **"Simulate Connection Disruption"** button (Orange button)
2. The connection to the ETOUR server will be disabled
3. Status bar turns red and shows "Connection to ETOUR server LOST"
4. Button text changes to "Restore Connection"

#### Testing Removal with Broken Connection
1. With connection disrupted (red status):
2. Try to remove a site
3. The system will detect the broken connection
4. Show error message: "Unable to connect to ETOUR server"
5. Removal will be blocked until connection is restored

#### Restoring Connection
1. Click the **"Restore Connection"** button (Green button)
2. Connection status returns to normal
3. Status bar turns green and shows connection restored
4. Button text returns to "Simulate Connection Disruption"

## Application States and Indicators

### Status Bar Colors
- **Blue**: Normal operation, welcome messages, user cancellations
- **Green**: Successful operations, connection restored, list refreshed
- **Orange**: Warnings, selections needed
- **Red**: Errors, connection lost, removal failures

### Button States
- **Red Button**: Remove Selected Site - Always active when site selected
- **Blue Button**: Refresh List - Always active
- **Orange/Green Button**: Toggles between simulating disruption and restoring connection

### Dialog Types
- **Information Dialogs**: Green icons - Successful operations
- **Warning Dialogs**: Yellow icons - User guidance needed
- **Error Dialogs**: Red icons - System errors or validation failures
- **Confirmation Dialogs**: Question icons - User decisions required

## Best Pract

### Before Removing Sites
1. Always confirm you've selected the correct site
2. Review the site details in the confirmation dialog
3. Ensure connection is active (green status)

### Handling Errors
1. If removal fails, check connection status
2. Try refreshing the list
3. If connection is lost, restore it before trying again
4. Ensure you're selecting an actual site (not the placeholder)

### Data Management
- The application starts with 8 sample sites
- All changes are in-memory only (not persistent between sessions)
- For production use, data persistence would be implemented

## Troubleshooting

### Common Issues and Solutions

#### Issue: "Please select a site first" warning
- **Cause**: No site selected in the list
- **Solution**: Click on a site before clicking "Remove Selected Site"

#### Issue: "Cannot remove placeholder item" error
- **Cause**: Trying to remove the "No Sites" placeholder when list is empty
- **Solution**: Add sites first before attempting removal

#### Issue: "Connection to server ETOUR interrupted" error
- **Cause**: Simulated connection disruption is active
- **Solution**: Click "Restore Connection" button to re-establish connection

#### Issue: Application won't start
- **Cause 1**: Java not installed or configured properly
- **Solution**: Verify Java installation with `java -version`
- **Cause 2**: Files not compiled
- **Solution**: Run `javac *.java` in the correct directory
- **Cause 3**: Running from wrong directory
- **Solution**: Navigate to directory containing `.class` files

#### Issue: "Removal failed" with no error details
- **Cause**: Index out of bounds or concurrent modification
- **Solution**: Refresh the list and try again

### Performance Tips
1. The application is lightweight and should run smoothly on any modern computer
2. For large numbers of sites, use the scroll bar in the list area
3. Regular refreshing ensures you're viewing current data

## Behind the Scenes

### Technical Architecture
The application follows a clean architecture with separation of concerns:

1. **Presentation Layer**: TouristPreferenceManager.java
   - Handles all user interface components
   - Manages user interactions and visual feedback
   - Implements the use case flow step-by-step

2. **Business Logic Layer**: PreferenceManager.java
   - Manages the bookmark data structure
   - Handles connection state simulation
   - Provides all bookmark operations (add, remove, query)

3. **Data Model Layer**: Site.java
   - Defines the structure of tourist site data
   - Provides proper encapsulation and validation
   - Implements equality and string representation

### Use Case Implementation
The application exactly follows the specified use case:

1. **Entry Condition**: Tourist selects a site (implemented via list selection)
2. **Flow of Events**:
   - Step 1: User chooses site via GUI selection
   - Step 2: System prompts removal via confirmation dialog
   - Step 3: User confirms or cancels
   - Step 4: System removes site and updates display
3. **Exit Conditions**:
   - Success: Site removed, notification shown
   - Cancellation: User cancels operation
   - Connection Error: Server interruption handled

### Error Handling Strategy
The application implements comprehensive error handling:

1. **User Input Validation**: Prevents invalid operations
2. **Connection Management**: Handles server disruptions gracefully
3. **State Management**: Prevents inconsistent application states
4. **User Feedback**: Clear, informative messages for all scenarios

## Future Enhancements

Potential improvements for production deployment:

1. **Data Persistence**: Save bookmarks to file or database
2. **Server Integration**: Real connection to ETOUR server
3. **Advanced Features**: 
   - Add new sites
   - Edit existing sites
   - Search and filter capabilities
   - Categories and tags for sites
4. **User Authentication**: Multiple user support
5. **Localization**: Multiple language support
6. **Export Functionality**: Share bookmarks with others

## Support

For assistance with the Tourist Site Bookmark Manager:

### Common Support Scenarios
1. Installation issues - Verify Java installation
2. Runtime errors - Check system messages and status bar
3. Unexpected behavior - Restart the application

### Getting Help
1. Review this manual thoroughly
2. Check the status bar messages for clues
3. Ensure all prerequisites are met
4. For persistent issues, consult Java documentation

## License and Attribution

This application is developed as a demonstration of proper use case implementation in Java. It follows best pract for GUI development, error handling, and user experience design. The sample data includes famous tourist sites from around the world for demonstration purposes.

## Conclusion

The Tourist Site Bookmark Manager provides a complete, runnable implementation of the "DeleteSiteFromPreferences" use case. With its intuitive interface, robust error handling, and clear user feedback, it demonstrates professional software development pract while meeting all specified requirements. Whether for educational purposes or as a foundation for a production system, this application serves as an excellent example of Java desktop application development.

---
*Version 1.0 - Tourist Site Bookmark Manager*
*Last Updated: [Current Date]*
```