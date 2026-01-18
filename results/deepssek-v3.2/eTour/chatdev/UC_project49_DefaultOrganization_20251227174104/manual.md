```markdown
# ETOUR Tourist Favorites System User Manual

## Overview
The ETOUR Tourist Favorites System is a Java Swing-based desktop application that allows authenticated tourists to view their bookmarked favorite sites. The application simulates a tourist booking system with server connection capabilities and handles potential connection interruptions gracefully.

## Main Features

### 1. **Authentication System**
- Secure login interface for tourist authentication
- Mock validation (accepts any non-empty username and password for demonstration)
- Clean, user-friendly login screen

### 2. **Dashboard Interface**
- Personalized welcome message with username
- Easy access to favorites viewing functionality
- Logout capability with confirmation prompt

### 3. **Favorites Management**
- View personal bookmarks in a scrollable list
- Automatic loading of bookmarks upon login
- Manual refresh capability
- Connection status indicator in window title

### 4. **Server Connection Management**
- Mock server simulation with 20% chance of connection interruption
- Automatic bookmark fetching based on username patterns
- Graceful handling of disconnections
- Reconnection capability with background processing

### 5. **Error Handling**
- Network interruption detection and user notification
- Retry option for failed connections
- Non-blocking user interface during reconnection attempts

## System Requirements

### Minimum Requirements
- **Operating System**: Windows 10+, macOS 10.14+, or Linux with X11
- **Java Runtime**: Java SE 11 or higher
- **RAM**: Minimum 512MB
- **Disk Space**: 10MB free space

### Recommended Requirements
- **Java Runtime**: Java SE 17
- **RAM**: 1GB or more
- **Screen Resolution**: 1024x768 or higher

## Installation Instructions

### Step 1: Install Java Development Kit (JDK)
1. Download JDK 11 or higher from [Oracle's website](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://adoptium.net/)
2. Run the installer and follow the installation wizard
3. Set up JAVA_HOME environment variable:
   - **Windows**: Add `JAVA_HOME=C:\Program Files\Java\jdk-xx.x.x` to System Variables
   - **macOS/Linux**: Add `export JAVA_HOME=/path/to/jdk` to ~/.bashrc or ~/.zshrc
4. Add Java to PATH:
   - **Windows**: Add `%JAVA_HOME%\bin` to Path variable
   - **macOS/Linux**: Add `export PATH=$JAVA_HOME/bin:$PATH` to shell configuration

### Step 2: Verify Java Installation
Open terminal/command prompt and run:
```bash
java -version
javac -version
```
Both commands should display the installed Java version.

### Step 3: Install IDE (Optional but Recommended)
For easier development and execution, install one of these IDEs:
- **IntelliJ IDEA** (Community Edition): [Download here](https://www.jetbrains.com/idea/download/)
- **Eclipse**: [Download here](https://www.eclipse.org/downloads/)
- **VS Code** with Java Extension Pack: [Download here](https://code.visualstudio.com/)

### Step 4: Set Up the Project
1. Create a new directory for the project:
```bash
mkdir etour-favorites-system
cd etour-favorites-system
```

2. Create the package structure:
```bash
mkdir -p project
```

3. Create the following Java files in the `project` directory:
   - `Main.java`
   - `LoginFrame.java`
   - `MainFrame.java`
   - `ETOURServerMock.java`
   - `FavoritesViewer.java`

4. Copy the provided source code into the respective files.

5. Your directory structure should look like:
```
etour-favorites-system/
└── project/
    ├── Main.java
    ├── LoginFrame.java
    ├── MainFrame.java
    ├── ETOURServerMock.java
    └── FavoritesViewer.java
```

## Running the Application

### Method 1: Using Command Line
1. Navigate to the project root directory:
```bash
cd etour-favorites-system
```

2. Compile all Java files:
```bash
javac project/*.java
```

3. Run the application:
```bash
java project.Main
```

### Method 2: Using an IDE
1. Open your IDE (IntelliJ IDEA, Eclipse, or VS Code)
2. Import the project folder
3. Set up the project structure with `src` folder containing the Java files
4. Locate `Main.java` and run it directly from the IDE
5. The IDE will automatically compile and execute the program

## How to Use the Application

### Step 1: Login
1. When the application starts, you'll see the ETOUR Login window
2. Enter any username and password (both must be non-empty)
3. Click "Login" button
   - **Example credentials**: 
     - Username: `john`
     - Password: `password123`

### Step 2: Access the Dashboard
After successful login:
1. You'll see the main dashboard with a welcome message
2. The window title shows "ETOUR - Tourist Dashboard"
3. Two buttons are available:
   - **View Favorites**: Opens your bookmarks
   - **Logout**: Returns to login screen

### Step 3: View Your Favorites
1. Click "View Favorites" button
2. A new window opens showing your bookmarks
3. The application automatically fetches bookmarks based on your username:
   - **Username containing "john"**: Shows Paris attractions
   - **Username containing "mary"**: Shows US national parks
   - **Other usernames**: Shows Italian attractions

### Step 4: Refresh Bookmarks
1. Click "Refresh" button to reload bookmarks from server
2. Note: There's a 20% chance of simulated server disconnection
3. If disconnected, the title shows "(Disconnected)" status

### Step 5: Handle Connection Issues
If a server disconnection occurs:
1. An error message appears in the list
2. A dialog prompts you to reconnect
3. Click "Yes" to attempt reconnection
4. The system shows "Reconnecting..." status
5. Upon successful reconnection, bookmarks reload automatically

### Step 6: Logout
1. Return to the main dashboard
2. Click "Logout" button
3. Confirm logout when prompted
4. You'll be returned to the login screen

## Features in Detail

### Authentication Flow
```
Start → Login Screen → Enter Credentials → Validate → Dashboard
```

### Bookmark Viewing Flow
```
Dashboard → View Favorites → Fetch from Server → Display List
                                   ↓
                           Connection Check
                                   ↓
                    Success → Show Bookmarks
                    Failure → Show Error → Reconnect Option
```

### Mock Server Behavior
- **Bookmark Data**: Predefined based on username patterns
- **Connection Simulation**: 20% chance of intentional disconnection
- **Reconnection**: Simulated 500ms network delay
- **Thread Safety**: Uses SwingWorker for background operations

## Troubleshooting

### Common Issues and Solutions

#### Issue 1: "Error: Could not find or load main class"
**Solution**:
- Ensure you're in the correct directory
- Check package declaration matches directory structure
- Use: `java -cp . project.Main`

#### Issue 2: Compilation Errors
**Solution**:
- Ensure all Java files are in the `project` directory
- Check for missing imports
- Verify Java version compatibility

#### Issue 3: Application Doesn't Start
**Solution**:
- Check if any other Java process is running
- Ensure you have proper GUI permissions
- Try running from terminal with `java -Djava.awt.headless=false project.Main`

#### Issue 4: GUI Elements Not Displaying Properly
**Solution**:
- Update Java version
- Check system theme compatibility
- Resize window if elements seem hidden

### Network Simulation Issues
- The 20% disconnection chance is intentional for demonstration
- To modify disconnection probability, edit `ETOURServerMock.java`, line with `random.nextDouble() < 0.2`
- Change `0.2` to different value (0.0 to 1.0)

## Development Notes

### Code Structure
```
project/
├── Main.java              # Application entry point
├── LoginFrame.java        # Authentication interface
├── MainFrame.java         # Main dashboard
├── ETOURServerMock.java   # Server simulation
└── FavoritesViewer.java   # Bookmarks display
```

### Key Design Patterns Used
1. **Model-View-Controller (MVC)**: Separates GUI from business logic
2. **Singleton Pattern**: Server mock instance management
3. **Observer Pattern**: Event handling in Swing
4. **Factory Pattern**: Window creation patterns

### Thread Safety
- All GUI updates happen on Event Dispatch Thread (EDT)
- Network operations use SwingWorker for background processing
- No direct Thread.sleep() calls on EDT

## Customization

### Changing Mock Data
Edit `ETOURServerMock.java` in the `fetchBookmarks` method:
```java
// Add new username patterns and corresponding bookmarks
if (username.toLowerCase().contains("alice")) {
    return Arrays.asList("Tokyo Tower", "Mount Fuji", "Kyoto Temple");
}
```

### Modifying GUI Appearance
Edit individual frame classes:
- Change window titles in constructors
- Modify colors using `setBackground()` and `setForeground()`
- Adjust layouts in `setLayout()` calls

### Changing Connection Behavior
- Adjust disconnection probability in `ETOURServerMock.java`
- Modify reconnection delay in `reconnect()` method
- Customize error messages in exception handling

## Best Pract for Users

1. **Regular Refresh**: Click Refresh button periodically to sync with server
2. **Save Work**: Although bookmarks are auto-saved in mock, in production always confirm saves
3. **Connection Awareness**: Monitor window title for connection status
4. **Proper Logout**: Always use Logout button instead of closing window directly

## Security Considerations (For Production)

**Note**: This is a demonstration application. For production use:

1. **Replace mock authentication** with real authentication system
2. **Implement proper password hashing** (bcrypt, PBKDF2)
3. **Add SSL/TLS** for network communications
4. **Implement session management** with timeout
5. **Add input validation** and SQL injection prevention
6. **Use environment variables** for sensitive configuration

## Support and Feedback

For issues with this demonstration application:
1. Check the troubleshooting section above
2. Verify Java installation and version
3. Ensure all source files are properly placed
4. Check system compatibility

## Uninstalling
To remove the application:
1. Delete the project directory
2. Java runtime can remain installed for other applications

## License and Credits
This is a demonstration application for educational purposes. The code follows standard Java Swing patterns and includes appropriate error handling and user experience considerations.

---

**Happy Exploring!** Use this application to understand how tourist booking systems can implement favorites management with robust error handling and user-friendly interfaces.
```