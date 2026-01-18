```markdown
# InsertPreferenceSite User Manual

## Overview
InsertPreferenceSite is a Java-based application that allows tourists to bookmark their favorite sites while managing connections to the ETOUR server. This application implements a complete use case for managing site preferences with robust server connectivity handling.

## Main Features

### 1. Server Connection Management
- **ServerConnection Class**: Handles connectivity to the ETOUR server
- **Connection Monitoring**: Automatically checks for server interruptions
- **Simulated Interruptions**: 20% chance of simulated disconnection for testing

### 2. Bookmark Management
- **Add Bookmarks**: Insert selected sites into your bookmark list
- **Duplicate Prevention**: Prevents adding the same site twice
- **Bookmark Display**: View all bookmarked sites with numbering

### 3. User Interaction Flow
- **Step-by-Step Process**: Follows the exact use case flow:
  1. Feature activation
  2. Site name prompting
  3. User confirmation
  4. Bookmark insertion
- **Input Validation**: Ensures site names are not empty
- **Confirmation System**: Double-checks before adding bookmarks

### 4. Error Handling
- **Server Connection Checks**: Validates connectivity at critical points
- **Graceful Failure**: Provides clear error messages when connections fail
- **Resource Management**: Properly closes all system resources

## System Requirements

### Software Requirements
- **Java Development Kit (JDK)**: Version 8 or higher
- **Java Runtime Environment (JRE)**: For running the application
- **Operating System**: Windows, macOS, or Linux

### Hardware Requirements
- **Minimum RAM**: 256 MB
- **Disk Space**: 10 MB for installation
- **Processor**: Any modern processor (Intel/AMD/Apple Silicon)

## Installation Guide

### Step 1: Install Java
1. **Download JDK**: Visit [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)
2. **Install JDK**: Follow the installation instructions for your OS
3. **Verify Installation**: Open terminal/command prompt and type:
   ```bash
   java -version
   javac -version
   ```

### Step 2: Download the Application Files
You need three Java files:
1. `InsertPreferenceSite.java` - Main application file
2. `ServerConnection.java` - Server connectivity module
3. `manual.md` - This user manual (optional)

### Step 3: Compile the Application
1. Open terminal/command prompt in the directory containing the Java files
2. Compile both Java files:
   ```bash
   javac InsertPreferenceSite.java ServerConnection.java
   ```
3. Verify successful compilation - you should see `.class` files created

## How to Use

### Running the Application
1. Navigate to the directory containing the compiled `.class` files
2. Run the application:
   ```bash
   java InsertPreferenceSite
   ```

### Application Flow

#### Step 1: Application Start
When you run the application, you'll see:
1. Initialization message showing the tourist card is at a particular site
2. Server connection information (ETOUR server)
3. Bookmark feature activation message

#### Step 2: Enter Site Name
The system will prompt:
```bash
Enter the site name to bookmark:
```
- Enter any site name (e.g., "Eiffel Tower", "Colosseum")
- Site name must not be empty
- Press Enter to submit

#### Step 3: Confirmation
The system asks for confirmation:
```bash
Confirm bookmarking '[Site Name]'? (yes/no):
```
- Type `yes` to confirm or `no` to cancel
- Only `yes` (case-insensitive) proceeds

#### Step 4: Processing
The application:
1. Checks server connection
2. If connected, adds the site to bookmarks
3. If disconnected, shows error message and aborts

#### Step 5: Results
Successful outcome:
```bash
Success: Site '[Site Name]' has been added to your bookmarks!
Total bookmarks: [Count]
=== Your Bookmarked Sites ===
1. [Site Name]
```

### Sample Usage Session
```
Tourist card is at a particular site.
Server: ETOUR
=== Bookmark Insertion Feature Activated ===
Enter the site name to bookmark: Louvre Museum
Confirm bookmarking 'Louvre Museum'? (yes/no): yes
Success: Site 'Louvre Museum' has been added to your bookmarks!
Total bookmarks: 1
=== Your Bookmarked Sites ===
1. Louvre Museum
```

## Features in Detail

### Server Connection Handling
- **Automatic Checks**: System checks connectivity before:
  - Prompting for site name
  - Confirming input
  - Performing insertion
- **Error Messages**: Clear notifications when connection is lost
- **Simulation**: Random interruptions simulate real-world scenarios

### Bookmark Management
- **Storage**: Bookmarks are stored in memory during session
- **Display**: View all bookmarks with sequential numbering
- **Duplicates**: System prevents adding duplicate sites
  ```bash
  Site 'Eiffel Tower' is already bookmarked.
  ```

### User Interface
- **Console-Based**: Simple text interface
- **Clear Prompts**: Each step is clearly explained
- **Confirmation**: Reduces accidental additions
- **Feedback**: Success and error messages are descriptive

## Error Scenarios and Solutions

### 1. Server Connection Lost
**Symptom**: "Error: Connection to server ETOUR interrupted. Operation aborted."
**Solution**:
- Check internet connection
- Wait and try again
- Contact ETOUR support if persistent

### 2. Empty Site Name
**Symptom**: "Error: Site name cannot be empty."
**Solution**: Enter a valid site name (non-empty string)

### 3. Duplicate Site Entry
**Symptom**: "Site '[Name]' is already bookmarked."
**Solution**: Choose a different site or use existing bookmark

### 4. Compilation Errors
**Symptom**: `javac` command fails
**Solution**:
- Ensure JDK is properly installed
- Check Java file paths are correct
- Verify no syntax errors in source files

## Best Pract

### For Users
1. **Clear Site Names**: Use descriptive names for easy identification
2. **Regular Updates**: Bookmark sites as you visit them
3. **Connection Awareness**: Avoid using in areas with poor connectivity
4. **Save Preferences**: For permanent storage, export bookmarks periodically

### For Developers
1. **Extend ServerConnection**: Modify to integrate with actual ETOUR API
2. **Add Persistence**: Implement file/database storage for bookmarks
3. **Enhance UI**: Create GUI version for better user experience
4. **Add Features**: Consider categories, ratings, or descriptions for sites

## Testing the Application

### Test Scenarios
1. **Normal Operation**: Enter site name → confirm → success
2. **Cancel Operation**: Enter site name → type "no" when confirming
3. **Server Disconnection**: System may simulate disconnection randomly
4. **Empty Input**: Press Enter without typing a site name
5. **Duplicate Entry**: Try to add the same site twice

### Automated Testing
To test with specific scenarios, modify the `ServerConnection` class:
```java
// Set guaranteed connection
serverConnection.setConnected(true);

// Or simulate disconnection
serverConnection.setConnected(false);
```

## Troubleshooting

### Common Issues
1. **"Command not found: java"**
   - Solution: Add Java to system PATH or use full path to Java executable

2. **"Class not found" errors**
   - Solution: Ensure you're in the correct directory with compiled `.class` files

3. **Application runs but no output**
   - Solution: Check if console supports UTF-8 encoding

4. **Memory issues with many bookmarks**
   - Solution: Implement persistent storage for large bookmark lists

### Contact Support
For technical support:
- Email: support@insertpreferencesite.com
- Website: www.insertpreferencesite.com/support
- Phone: +1-800-ETOUR-APP

## Release Notes

### Version 1.0 (Current)
- Initial release
- Basic bookmark functionality
- Server connection simulation
- Console-based interface
- Error handling for common scenarios

### Planned Features
- Cloud synchronization
- Mobile app version
- Photo attachment for sites
- Sharing bookmarks with other tourists
- Integration with actual ETOUR booking system

## License and Terms
This software is provided as-is for personal use. Commercial use requires licensing. All trademarks (ETOUR) are property of their respective owners.

---

*Thank you for using InsertPreferenceSite! We hope this application enhances your travel experience by helping you remember and revisit your favorite destinations.*
```