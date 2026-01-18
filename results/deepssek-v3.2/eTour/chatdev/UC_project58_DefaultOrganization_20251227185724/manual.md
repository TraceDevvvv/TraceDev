```md
# Site Card Viewer - User Manual

## Overview

Site Card Viewer is a Java Swing application designed for tourists to view detailed information about historical and cultural sites. The application implements the "ViewSiteCard" use case, allowing authenticated tourists to browse site information from various areas including Research Results, Visited Sites, and Favorites. It includes robust error handling for server connection interruptions and provides an intuitive graphical user interface.

## Main Features

### 1. Site Selection Interface
- **Dropdown Menu**: Select from a curated list of popular tourist sites including Eiffel Tower, Colosseum, Great Wall of China, Machu Picchu, and Taj Mahal
- **Real-time Feedback**: Visual indicators show when a valid site is selected
- **Status Updates**: Clear messaging informs users of application state

### 2. Site Details Display
- **Comprehensive Information**: View detailed site cards with structured information including:
  - Site name and basic identification
  - Geographic location and coordinates
  - Historical construction details
  - Architectural specifications
  - Visitor statistics (where applicable)
  - Cultural significance descriptions
- **Formatted Display**: Clean, monospaced text formatting for easy reading
- **Scrollable Interface**: View extensive details without space constraints

### 3. Database Simulation
- **Mock Server Connection**: Simulates real database interactions with configurable delays
- **Error Simulation**: Built-in interruption handling for testing connection resilience
- **Multiple Data Sources**: Demonstrates data retrieval from different site categories

### 4. Connection Management
- **Timeout Handling**: Automatic cancellation of stalled connections (5-second timeout)
- **Error Messages**: User-friendly error descriptions for network issues
- **Recovery Options**: Clear guidance on resolving connection problems

### 5. Performance Features
- **Asynchronous Operations**: Background data fetching prevents UI freezing
- **Thread Safety**: Proper Swing EDT management for stable GUI performance
- **Resource Management**: Efficient cleanup of database connections and threads

## System Requirements

### Minimum Requirements
- **Operating System**: Windows 10/11, macOS 10.14+, or Linux with GUI support
- **Java Version**: Java SE 8 (JDK 1.8) or higher
- **RAM**: 256 MB minimum
- **Storage**: 10 MB free space

### Recommended Requirements
- **Java Version**: Java SE 11 or higher
- **RAM**: 512 MB or more
- **CPU**: Dual-core processor
- **Screen Resolution**: 1024×768 or higher

## Installation Instructions

### Step 1: Install Java Development Kit (JDK)

#### For Windows:
1. Visit the [Oracle JDK download page](https://www.oracle.com/java/technologies/downloads/)
2. Download the appropriate version (JDK 8 or higher)
3. Run the installer and follow the setup wizard
4. Set JAVA_HOME environment variable:
   ```
   System Properties > Advanced > Environment Variables
   New System Variable:
   - Variable name: JAVA_HOME
   - Variable value: C:\Program Files\Java\jdk-[version]
   ```

#### For macOS:
```bash
# Using Homebrew (recommended)
brew install openjdk@11

# Alternative: Download from Oracle website
# Visit https://www.oracle.com/java/technologies/downloads/
```

#### For Linux (Ubuntu/Debian):
```bash
sudo apt update
sudo apt install openjdk-11-jdk
```

### Step 2: Verify Java Installation
```bash
java -version
javac -version
```

### Step 3: Download and Compile the Application

1. **Save the Java code**:
   - Create a new file named `SiteCardViewer.java`
   - Copy the provided Java code into the file
   - Save it in a dedicated project folder

2. **Compile the application**:
   ```bash
   cd /path/to/project/folder
   javac SiteCardViewer.java
   ```

3. **Verify compilation**:
   - Ensure these class files are generated:
     - `SiteCardViewer.class`
     - `AppWindow.class`
     - `SiteDatabase.class`

### Step 4: Run the Application

```bash
java SiteCardViewer
```

## Usage Guide

### Starting the Application
1. Open a terminal or command prompt
2. Navigate to the project directory
3. Execute: `java SiteCardViewer`
4. The main window will appear, titled "Tourist Site Viewer - ETOUR System"

### Basic Operations

#### Selecting a Site
1. **Locate the dropdown menu** labeled "Choose Site:" at the top of the window
2. **Click the dropdown** to see available sites:
   - Eiffel Tower
   - Colosseum
   - Great Wall
   - Machu Picchu
   - Taj Mahal
3. **Select a site** from the list
4. **Verify selection**: The "View Site Card" button becomes enabled
5. **Check status**: Bottom status bar confirms your selection

#### Viewing Site Details
1. **Click "View Site Card"** button after selecting a site
2. **Observe status changes**:
   - Status bar: "Fetching site details..."
   - Button becomes temporarily disabled
3. **Wait for loading** (approximately 1 second simulation)
4. **Review the detailed site card** displayed in the main text area

### Understanding the Display

#### Site Card Information Layout
Each site card includes the following sections:

1. **Header**:
```
Site Card: [Site Name]
========================
```
2. **Key Details** (varies by site):
   - Location: City, Country
   - Coordinates: Latitude, Longitude
   - Construction Timeline
   - Architectural Features
   - Historical Context
   
3. **Detailed Description**:
   - Multi-paragraph explanation
   - Cultural significance
   - Visitor information

#### Status Indicators
- **Ready**: Application loaded and waiting for input
- **Site selected**: Valid site chosen from dropdown
- **Fetching site details**: Database operation in progress
- **Details loaded**: Site card successfully displayed
- **Error messages**: Connection issues or fetch failures

### Error Handling

#### Connection Timeout
If the server connection takes longer than 5 seconds:
```
Error: Connection to ETOUR server timed out.
Please check your network connection and try again.
```

**Actions to take**:
1. Wait a moment and try again
2. Verify your internet connection
3. Contact system administrator if problem persists

#### Database Errors
If the database fetch fails:
```
Error: Could not fetch site details.
[Specific error message]
```

**Actions to take**:
1. Try selecting a different site
2. Restart the application
3. Report the error if it continues

#### Server Interruption
If the ETOUR server becomes unavailable:
```
Error: Database operation interrupted due to server connection issue.
```

**Actions to take**:
1. Check if other users are experiencing similar issues
2. Try again after a few minutes
3. Contact technical support

### Testing Features

#### Simulated Network Conditions
The application includes built-in testing features:
- **Normal operation**: 1-second delay simulates typical network latency
- **Timeout testing**: 50% chance of extended delay to trigger timeout handling
- **Error simulation**: Various error conditions to test robustness

#### To Test Timeout Handling:
1. Use the application normally
2. Observe occasional extended loading times
3. Verify that timeout messages appear correctly
4. Confirm application remains responsive during delays

## Troubleshooting

### Common Issues and Solutions

#### 1. "Error: Could not find or load main class"
**Solution**:
```bash
# Ensure you're in the correct directory
cd /path/to/project

# Check if class files exist
ls *.class

# If no class files, compile first
javac SiteCardViewer.java

# Run with full classpath
java -cp . SiteCardViewer
```

#### 2. GUI Displays Incorrectly
**Solution**:
- Verify Java version is 8 or higher
- Check system theme compatibility
- Try running with different look-and-feel:
  ```bash
  java -Dswing.defaultlaf=com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel SiteCardViewer
  ```

#### 3. Slow Performance
**Solution**:
- Close unnecessary applications
- Increase Java heap size:
  ```bash
  java -Xmx512m SiteCardViewer
  ```
- Update to latest Java version

#### 4. No Sites Loaded
**Solution**:
- Verify database simulation is running
- Check for any error messages in terminal
- Restart the application

### Debug Mode
For advanced troubleshooting, run with verbose logging:
```bash
java -Djava.util.logging.config.file=logging.properties SiteCardViewer
```

## Application Architecture

### Key Components

#### 1. SiteCardViewer (Main Class)
- Entry point of the application
- Initializes Swing Event Dispatch Thread
- Ensures thread-safe GUI operations

#### 2. AppWindow (GUI Class)
- Main application window (JFrame)
- Manages all user interface components
- Handles event listeners and user interactions
- Implements responsive design principles

#### 3. SiteDatabase (Data Layer)
- Simulates database connectivity
- Provides mock site data
- Implements error and timeout scenarios
- Demonstrates proper resource management

### Design Patterns Used

1. **Model-View-Controller (MVC)**:
   - View: AppWindow GUI components
   - Controller: Event listeners in AppWindow
   - Model: SiteDatabase with data logic

2. **Singleton Pattern**: Database connection management
3. **Observer Pattern**: Event-driven UI updates
4. **Factory Pattern**: GUI component creation

## Security Considerations

### Authentication Simulation
- The application assumes prior tourist authentication
- No sensitive data is transmitted or stored
- All operations are local and contained

### Data Privacy
- No personal information is collected
- Site data is static and publicly available
- No tracking or analytics implemented

### Network Security
- Simulated connections only
- No actual network traffic generated
- Safe for use in restricted environments

## Performance Tips

### For Optimal Experience:
1. **Use Latest Java Version**: Better performance and security
2. **Allocate Sufficient Memory**: Adjust -Xmx parameter if needed
3. **Close Other Applications**: Especially memory-intensive programs
4. **Regular Updates**: Keep Java and system updated

### Memory Management:
- Default: 256MB heap
- Recommended: 512MB for smoother operation
- Maximum: Adjust based on system capacity

## Support and Contact

### Documentation
- This user manual
- In-application help tooltips
- Status messages with actionable information

### Reporting Issues
If you encounter problems:
1. Note the exact error message
2. Record the steps to reproduce
3. Check system logs for details
4. Contact: support@etour-system.example.com

### Future Enhancements
Planned features for upcoming versions:
- Real database integration
- Multiple language support
- Image galleries for sites
- User review system
- Offline mode

## License and Legal

### Usage Rights
- Free for personal and educational use
- Commercial use requires licensing
- Source code modifications allowed with attribution

### Disclaimer
This software is provided "as-is" without warranty of any kind. The developers are not responsible for:
- Data accuracy of site information
- System compatibility issues
- Any damages resulting from use

---

*Last Updated: October 2024*  
*Version: 1.0*  
*ETOUR System - Enhancing Tourist Experiences Worldwide*
```

This manual provides comprehensive documentation for users to install, configure, and effectively use the Site Card Viewer application. It covers all aspects from system requirements to troubleshooting, ensuring users can successfully implement the ViewSiteCard use case in their environment.