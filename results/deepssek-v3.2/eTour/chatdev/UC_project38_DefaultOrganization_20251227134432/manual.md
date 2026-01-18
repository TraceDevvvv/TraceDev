```markdown
# Banner Insertion System - User Manual

## Overview

The Banner Insertion System is a Java-based desktop application that enables restaurant point operators to manage promotional banners associated with their location. This application implements the complete "InsertBanner" use case as specified in the requirements, providing authentication, image validation, banner management, and error handling.

## System Architecture

The application follows a structured MVC (Model-View-Controller) pattern with the following components:

- **Model Classes**: `Banner`, `RestaurantPoint`
- **Service Layer**: `BannerService`, `ImageValidator`
- **GUI Application**: `BannerInsertionApp`
- **Main Entry Point**: `Main`

## Main Features

### 1. Operator Authentication
- Secure login system for restaurant point operators
- Authentication status tracking
- Session management

### 2. Image Selection and Validation
- Graphical file browser for image selection
- Comprehensive image validation including:
  - File existence checks
  - Size limitations (max 5MB)
  - Format validation (JPG, PNG, GIF)
  - Dimension constraints (100x100 to 2000x2000)
  - Image preview functionality

### 3. Banner Management
- Track banners per restaurant point
- Maximum banner limit enforcement (default: 5 banners per location)
- Real-time banner count display
- Successful insertion notifications

### 4. Error Handling
- Invalid image handling with descriptive error messages
- Maximum banner limit notifications
- Server connection simulation and error handling
- Operation cancellation support

### 5. User Interface
- Intuitive GUI with clear workflow
- Real-time status updates
- Visual feedback for all operations
- Responsive controls that adapt to system state

## System Requirements

### Hardware Requirements
- Minimum 2GB RAM
- 100MB available disk space
- Screen resolution: 1024x768 or higher

### Software Dependencies

#### Required Java Version
- **Java Runtime Environment (JRE) 8 or higher**
- **Java Development Kit (JDK) 8 or higher** (for compilation)

#### Required Libraries (Included in standard Java)
- Java Swing (GUI framework)
- Java AWT (Graphics)
- Java ImageIO (Image processing)

## Installation Guide

### Step 1: Install Java

#### Windows:
1. Download JDK 8 or higher from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html)
2. Run the installer and follow prompts
3. Set JAVA_HOME environment variable:
   - Right-click "This PC" → Properties → Advanced system settings
   - Click "Environment Variables"
   - Under System variables, click "New"
   - Variable name: `JAVA_HOME`
   - Variable value: `C:\Program Files\Java\jdk-{version}` (your JDK path)
   - Add to PATH: Edit PATH variable, add `%JAVA_HOME%\bin`

#### macOS:
```bash
# Install using Homebrew
brew install openjdk@11

# Configure Java Home
sudo ln -sfn /usr/local/opt/openjdk/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk.jdk
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

### Step 2: Download Application Files

Create a project directory and download all Java files:
```
BannerInsertionSystem/
├── Main.java
├── Banner.java
├── RestaurantPoint.java
├── ImageValidator.java
├── BannerService.java
└── BannerInsertionApp.java
```

### Step 3: Compile the Application

Open terminal/command prompt in the project directory:

**Windows Command Prompt:**
```cmd
javac *.java
```

**macOS/Linux Terminal:**
```bash
javac *.java
```

This will create `.class` files for all Java classes.

### Step 4: Run the Application

**Windows:**
```cmd
java Main
```

**macOS/Linux:**
```bash
java Main
```

## How to Use the Application

### Step 1: Launch the Application
1. Compile all Java files as described above
2. Run `java Main` from the project directory
3. The main application window will appear

### Step 2: Authenticate as Operator
1. Click the "Authenticate" button in the top panel
2. Enter credentials in the authentication dialog:
   - Username: `admin`
   - Password: `password` (default credentials for demo)
3. Click "OK"
4. Success message will appear upon successful authentication

### Step 3: Select Banner Image
1. Click "Select Image File" button
2. Browse to find your banner image file
3. Supported formats: JPG, JPEG, PNG, GIF
4. Requirements:
   - Size: ≤ 5MB
   - Dimensions: 100x100 to 2000x2000 pixels
5. Selected image will appear in the preview area

### Step 4: Insert the Banner
1. Click "Insert Banner" button (enabled only after authentication and image selection)
2. System will validate the image and check banner limits
3. A confirmation dialog will appear:
   - Shows selected image name
   - Displays current banner count
   - Asks to confirm insertion
4. Click "Yes" to proceed or "No" to cancel
5. Success/failure notification will appear

### Step 5: Monitor Banner Status
- Top panel shows authentication status
- Center panel displays selected image preview
- Bottom panel shows:
  - Current banner count (e.g., "Banners: 2/5")
  - System status messages
  - Control buttons

## Workflow Overview

The application follows the exact use case flow:

1. **Authentication**: Operator logs into the system
2. **Feature Selection**: Operator selects banner insertion feature
3. **Image Selection**: System displays image selection form
4. **Validation**: System validates image and checks banner limits
5. **Confirmation**: Operator confirms/rejects insertion
6. **Processing**: System processes the request
7. **Notification**: System provides insertion result

## Troubleshooting

### Common Issues and Solutions

#### 1. "Invalid Image" Error
- **Cause**: Image doesn't meet requirements
- **Solution**: Ensure image meets size, format, and dimension requirements

#### 2. Authentication Failed
- **Cause**: Incorrect credentials
- **Solution**: Use default credentials (admin/password) or check authentication logic

#### 3. Maximum Banners Reached
- **Cause**: Restaurant point already has maximum allowed banners (5)
- **Solution**: Remove existing banners or increase MAX_BANNERS constant

#### 4. Server Connection Issues
- **Cause**: Simulated server disconnection
- **Solution**: Click "Reconnect Server" button

#### 5. Application Won't Start
- **Potential Causes**:
  - Java not installed or PATH not set
  - Missing `.class` files
  - Incorrect compilation

- **Solutions**:
  ```bash
  # Check Java installation
  java -version
  
  # Recompile all files
  javac *.java
  
  # Check for compilation errors
  ```

### Error Messages Reference

- **"File does not exist"**: Selected file path is invalid
- **"Image size exceeds 5MB limit"**: Image file is too large
- **"Invalid image format"**: File is not JPG, PNG, or GIF
- **"Cannot read image file"**: Corrupted or unsupported image
- **"Image dimensions too small/large"**: Image size outside allowed range
- **"Maximum banners reached"**: Restaurant point has reached capacity
- **"Server connection interrupted"**: ETOUR server is unavailable

## Advanced Configuration

### Modifying Maximum Banners
To change the maximum banners per restaurant point:
1. Open `RestaurantPoint.java`
2. Find the line: `private final int MAX_BANNERS = 5;`
3. Change the value as needed

### Custom Authentication
To implement custom authentication:
1. Modify `authenticate()` method in `RestaurantPoint.java`
2. Replace the hardcoded check with your authentication logic

### Image Requirements Adjustment
To modify image validation rules:
1. Edit `validateImage()` method in `ImageValidator.java`
2. Adjust size limits, dimensions, or format requirements

## Security Considerations

### Current Implementation
- Demo authentication uses hardcoded credentials (admin/password)
- Image files are validated for format and size
- System prevents buffer overflow attacks through proper validation

### Recommended Enhancements for Production
- Implement database-backed authentication
- Encrypt sensitive data
- Add session timeout
- Implement secure file uploads
- Add logging and auditing

## Best Pract

### For Operators:
1. Always authenticate before operations
2. Use appropriately sized images (optimized for web)
3. Monitor banner count to avoid reaching limits
4. Regularly check system status

### For Developers:
1. Keep Java updated to latest stable version
2. Test with various image formats
3. Follow the established MVC pattern for extensions
4. Add unit tests for new functionality

## Support and Contact

### For Technical Support:
- Ensure you have followed installation instructions
- Check the FAQ section above
- Review the source code comments for specific functionality

### Reporting Issues:
When reporting issues, please include:
- Java version (`java -version`)
- Operating system and version
- Steps to reproduce the issue
- Error messages received

## Future Enhancements

Planned features for future releases:
1. Database integration for persistent storage
2. Multiple restaurant point management
3. Banner scheduling and rotation
4. Advanced image editing tools
5. User role management
6. Reporting and analytics dashboard

## License and Credits

This application is developed as a demonstration of:
- Java Swing GUI development
- Image processing and validation
- Use case implementation
- Error handling and workflow management

For educational and demonstration purposes only.

---

**Note**: This is a standalone desktop application. All data is stored in memory during runtime and will be lost when the application closes.
```