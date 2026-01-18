```markdown
# LoginError Demo Application

A Java Swing application demonstrating a use-case-driven approach to error handling in authentication systems. This educational application implements the "LoginError" use case, showing proper error notification, user confirmation, and state recovery workflows.

## 🌟 Main Features

### 1. Interactive Login Interface
1. **User-friendly GUI** built with Java Swing
2. **Username & password** input fields with proper security masking
3. **Responsive design** with centered layout and appropriate spacing
4. **Accessibility-focused** with proper field focus management

### 2. Error Handling Workflow
The application implements the exact LoginError use case flow:
- **Step 1:** Detects invalid login data and displays notification
- **Step 2:** Requests user confirmation of reading the error
- **Step 3:** Recovers previous state (clears form)
- **Exit:** Returns control to user for new login attempt

### 3. Authentication Simulation
1. **Demo credentials**: `admin` / `password123`
2. **Input validation** for empty fields
3. **Basic format validation** helpers (username format, password length)
4. **Processing simulation** with realistic timing

### 4. Educational Design
1. **Well-documented code** with detailed JavaDoc comments
2. **Separation of concerns** with dedicated handler classes
3. **Architecture demonstration** of GUI, service, and handler patterns
4. **Use case implementation** showing practical application of requirements

## 🚀 Quick Start

### Prerequisites
- **Java Development Kit (JDK)** version 8 or higher
- **Any Java IDE** (IntelliJ IDEA, Eclipse, NetBeans) or command line tools
- **No external dependencies required** - uses standard Java libraries only

### Installation Steps

1. **Install Java**
   ```bash
   # On macOS with Homebrew:
   brew install openjdk@17
   
   # On Ubuntu/Debian:
   sudo apt update
   sudo apt install openjdk-17-jdk
   
   # On Windows:
   # Download and install from https://adoptium.net/
   ```

2. **Verify Installation**
   ```bash
   java -version
   javac -version
   ```

3. **Clone or Create Project Files**
   Copy the four provided Java files into a new directory:
   - `LoginErrorApp.java` - Main application entry point
   - `LoginFrame.java` - Main GUI window
   - `LoginErrorHandler.java` - Error handling logic
   - `AuthService.java` - Authentication service

## 🎮 How to Use

### Running the Application

#### Option 1: Using an IDE (Recommended)
1. Open your Java IDE (IntelliJ IDEA, Eclipse, etc.)
2. Create a new Java project
3. Add all four Java files to the `src` directory
4. Locate `LoginErrorApp.java` and run it
   - In IntelliJ: Right-click → "Run 'LoginErrorApp.main()'"
   - In Eclipse: Right-click → Run As → Java Application

#### Option 2: Command Line Compilation
```bash
# Navigate to your project directory
cd /path/to/project

# Compile all Java files
javac *.java

# Run the application
java LoginErrorApp
```

### Application Walkthrough

#### Initial Launch
1. After running the application, you'll see a **400x300 pixel window** with:
   - Title: "Login System - LoginError Use Case Demo"
   - Centered "Login System" heading
   - Username input field
   - Password field (masked with dots)
   - Login button
   - Window centered on screen

#### Step-by-Step Usage

**Test Case 1: Valid Credentials**
1. Enter username: `admin`
2. Enter password: `password123`
3. Click **Login** button
4. Result: Success message appears
   ```
   "Login successful! Welcome, admin."
   ```

**Test Case 2: Empty Fields** 
1. Leave username or password empty
2. Click **Login** button
3. Error flow begins:
   - **Notification appears**: "Username and password cannot be empty."
   - **Confirmation required**: Dialog asks to confirm reading
   - **Click "Yes"**: Form clears, focus returns to username field
   - **Click "No"**: Dialog closes, no further action

**Test Case 3: Invalid Credentials**
1. Enter any username/password except `admin`/`password123`
2. Click **Login** button
3. Error flow begins:
   - **Notification appears**: "Invalid username or password. Please try again."
   - **Confirmation required**: Dialog asks to confirm reading
   - **Click "Yes"**: Form clears, ready for new attempt
   - Console shows: "Control returned to user interaction after error recovery."

### Expected User Experience

#### During Normal Operation:
1. Type credentials into fields
2. Press Login or hit Enter
3. Either:
   - See success message (valid credentials)
   - Trigger error flow (invalid credentials)

#### During Error Flow:
1. **Notification Dialog** appears with error details
2. **User reads message** and decides response
3. **User confirms reading** by clicking "Yes"
4. **System recovers** by clearing form
5. **Focus returns** to username field
6. **Control restored** to user for new attempt

### Keyboard Shortcuts
- **Enter**: Submit login (when focus is in password field)
- **Tab**: Navigate between fields
- **Escape**: Close error dialog
- **Alt+F4**: Close application window

## 📁 Project Structure

```
LoginErrorDemo/
│
├── LoginErrorApp.java          # Application entry point
├── LoginFrame.java            # Main GUI implementation
├── LoginErrorHandler.java     # Error handling logic
├── AuthService.java           # Authentication service
└── README.md                  # This documentation
```

### File Descriptions

**LoginErrorApp.java**
- **Purpose**: Main class, sets up Swing environment
- **Key feature**: Thread-safe GUI initialization
- **Execution point**: Contains `main()` method

**LoginFrame.java** 
- **Purpose**: Primary user interface
- **Key features**: 
  - GridBagLayout for responsive design
  - Event handling for login button
  - Integration with error handler
  - State recovery implementation

**LoginErrorHandler.java**
- **Purpose**: Implements use case error flow
- **Key features**:
  - Combines notification and confirmation (Steps 1 & 2)
  - Proper dialog presentation with error styling
  - Recovery coordination (Step 3)

**AuthService.java**
- **Purpose**: Simulates authentication backend
- **Key features**:
  - Hardcoded valid credentials for demo
  - Input format validation methods
  - Processing delay simulation

## 🔧 Configuration & Customization

### Changing Demo Credentials
Edit `AuthService.java`:
```java
// Current values:
private static final String VALID_USERNAME = "admin";
private static final String VALID_PASSWORD = "password123";

// Change to:
private static final String VALID_USERNAME = "yourusername";
private static final String VALID_PASSWORD = "yourpassword";
```

### Modifying Error Messages
Edit `LoginFrame.java`:
```java
// Around line 95-102:
handleLoginError("Username and password cannot be empty.");
// Change message as needed

handleLoginError("Invalid username or password. Please try again.");
// Change message as needed
```

### Window Customization
Edit `LoginFrame.java` constructor:
```java
// Change window size:
setSize(500, 400); // Width, Height in pixels

// Change window title:
setTitle("Your Custom Title");

// Change fonts/sizes in createUIComponents()
titleLabel.setFont(new Font("Your Font", Font.BOLD, 20));
```

## 🧪 Testing Scenarios

### Expected Behavior Matrix
| Input | Expected Result | Error Flow Triggered |
|-------|----------------|---------------------|
| `admin` / `password123` | Success message | No |
| Empty username | Error: "Cannot be empty" | Yes |
| Empty password | Error: "Cannot be empty" | Yes |
| Both empty | Error: "Cannot be empty" | Yes |
| Wrong username | Error: "Invalid credentials" | Yes |
| Wrong password | Error: "Invalid credentials" | Yes |
| Both wrong | Error: "Invalid credentials" | Yes |

### Edge Cases Handled
1. **Null input protection** in AuthService
2. **Whitespace trimming** for all inputs
3. **Thread interruption handling** during authentication
4. **Button state management** during error recovery
5. **Focus management** after state recovery

## 📚 Learning Objectives

This application demonstrates:
1. **Use Case Implementation**: Translating requirements to working code
2. **MVC-like Architecture**: Separating GUI, business logic, and serv
3. **Error Handling Patterns**: Structured approach to user notification
4. **Swing GUI Development**: Building desktop applications in Java
5. **State Management**: Proper recovery to previous application state

## 🔍 Troubleshooting

### Common Issues

**"Cannot find symbol" errors during compilation**
```bash
# Ensure all files are in same directory and properly named:
ls -la *.java
```

**No window appears when running**
```bash
# Check if Java is properly installed:
java -version
# Should show Java 8 or higher

# Ensure compilation succeeded:
javac *.java 2>&1
```

**Application freezes during login**
- Expected: 100ms delay simulates authentication processing
- Wait briefly for authentication to complete

**Dialog appears off-screen**
- Application uses `setLocationRelativeTo(null)` to center
- May be affected by multi-monitor setups
- Try moving other windows or adjusting display settings

### Debug Mode
Add to `LoginErrorApp.main()`:
```java
public static void main(String[] args) {
    System.out.println("Starting LoginError Demo...");
    // Existing code...
}
```

## 🤝 Contributing

This is a demonstration application. For educational purposes:
1. **Extend authentication**: Connect to real database
2. **Add features**: Remember me, password reset
3. **Improve UI**: Add icons, better styling
4. **Add tests**: JUnit tests for each component
5. **Internationalize**: Support multiple languages

## 📄 License & Credits

**Educational Use Only**
- Created to demonstrate use case implementation
- Based on standard Java Swing patterns
- Free to modify and adapt for learning purposes

**Best Pract Shown**
- Code documentation with JavaDoc
- Separation of concerns
- Proper error handling
- User-centered design
- Use case compliance

## 🚀 Next Steps

After exploring this demo:
1. **Study the code** - Read through comments to understand flow
2. **Modify and experiment** - Try adding new features
3. **Extend the use case** - Add "Forgot Password" or "Create Account"
4. **Convert to production** - Replace AuthService with database connection
5. **Learn more** - Explore Java Swing documentation and patterns

Happy coding!
```