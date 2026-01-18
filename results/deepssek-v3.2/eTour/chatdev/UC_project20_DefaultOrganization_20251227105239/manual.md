```markdown
# Insert Banner Application - User Manual

## Overview

The Insert Banner Application is a Java-based desktop application designed for Agency Operators to manage banners associated with refreshment points (e.g., restaurants, cafes, snack bars). The application follows a specific use case flow that includes selecting refreshment points, choosing banner images, validating inputs, and managing database operations with robust error handling.

## Main Features

### 1. Refreshment Point Management
- Display all available refreshment points from the database
- Automatic loading of sample data if database is empty
- Clear visual selection interface

### 2. Banner Image Selection
- File browser for selecting image files (JPG, JPEG, PNG, GIF formats)
- Image preview with automatic scaling
- Real-time validation of image file formats

### 3. Validation and Error Handling
- Maximum banner count enforcement per refreshment point
- Image file format validation
- Database connection monitoring and automatic reconnection
- Comprehensive error messages for all failure scenarios

### 4. Database Operations
- Embedded H2 database for data persistence
- Automatic table creation on first run
- Transaction-safe operations
- Connection interruption handling as per use case requirements

### 5. User Interface
- Intuitive graphical interface
- Status messaging for all operations
- Confirmation dialogs for critical actions
- Responsive button states

## System Requirements

### Minimum Requirements
- Java Runtime Environment (JRE) 8 or higher
- 500 MB available disk space
- 2 GB RAM minimum
- Screen resolution: 1024x768 or higher

### Recommended Requirements
- Java Development Kit (JDK) 11 or higher
- 1 GB available disk space
- 4 GB RAM
- Screen resolution: 1280x800 or higher

## Installation Guide

### Step 1: Install Java
1. **Windows:**
   - Download and install Java from [Oracle's website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or use [AdoptOpenJDK](https://adoptopenjdk.net/)
   - Set JAVA_HOME environment variable
   - Add Java to PATH

2. **macOS:**
   ```bash
   # Using Homebrew (recommended)
   brew install openjdk@11
   sudo ln -sfn /usr/local/opt/openjdk@11/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk-11.jdk
   
   # Add to shell profile
   echo 'export JAVA_HOME="/usr/local/opt/openjdk@11"' >> ~/.zshrc
   echo 'export PATH="$JAVA_HOME/bin:$PATH"' >> ~/.zshrc
   source ~/.zshrc
   ```

3. **Linux (Ubuntu/Debian):**
   ```bash
   sudo apt update
   sudo apt install openjdk-11-jdk
   
   # Set JAVA_HOME
   echo 'export JAVA_HOME="/usr/lib/jvm/java-11-openjdk-amd64"' >> ~/.bashrc
   echo 'export PATH="$JAVA_HOME/bin:$PATH"' >> ~/.bashrc
   source ~/.bashrc
   ```

### Step 2: Install Database Driver (H2)
**Automatic Installation:**
The application includes H2 database functionality and will create a database file automatically on first run. No manual installation is needed.

**Manual Installation (Optional):**
If you want to connect to an existing H2 database:
```bash
# Download H2 database
wget https://repo1.maven.org/maven2/com/h2database/h2/2.1.214/h2-2.1.214.jar

# Or for manual file placement
# Copy the JAR file to your Java classpath
```

## Running the Application

### Method 1: Command Line Execution
```bash
# Navigate to the directory containing InsertBannerApp.java
cd /path/to/application

# Compile the application
javac -cp ".:h2-*.jar" InsertBannerApp.java

# Run the application
java -cp ".:h2-*.jar" InsertBannerApp
```

### Method 2: Using an IDE (Recommended)
1. **IntelliJ IDEA:**
   - Open the project folder
   - Right-click on InsertBannerApp.java
   - Select "Run InsertBannerApp.main()"

2. **Eclipse:**
   - Import the project
   - Right-click on InsertBannerApp.java
   - Select Run As → Java Application

3. **Visual Studio Code:**
   - Install Java Extension Pack
   - Open the folder
   - Click the Run button or press F5

## First-Time Setup

### Initial Database Setup
1. **First Run:**
   - The application will automatically create a `banners_db.mv.db` file in the current directory
   - Sample refreshment points (Restaurant A, Cafe B, Snack Bar C) will be added automatically

2. **Database Location:**
   ```
   Application Directory/
   ├── InsertBannerApp.java
   ├── banners_db.mv.db    (created automatically)
   └── banners_db.trace.db (log file)
   ```

3. **Database Configuration:**
   - Location: File-based (`./banners_db`)
   - Username: `sa`
   - Password: (empty)
   - Maximum banners per refreshment point: 5

## How to Use the Application

### Starting the Application
1. Launch the application using any of the methods above
2. The main window will appear with the following components:
   - Top: Refreshment point selection dropdown
   - Center: Image display area and action buttons
   - Bottom: Status bar showing current operation state

### Step-by-Step Workflow

#### Step 1: Select Refreshment Point
1. Click the dropdown menu labeled "Select Refreshment Point"
2. Choose from the available refreshment points
3. The status bar will show "Ready" when selection is complete

#### Step 2: Select Banner Image
1. Click the "Select Image" button
2. Navigate to your image file (supported formats: JPG, JPEG, PNG, GIF)
3. Select the image and click "Open"
4. The image will appear in the preview area
5. The "Insert Banner" button will become enabled

**Note:** Image files should be:
- Common image formats
- Preferably under 5MB for optimal performance
- Clear and readable for banner display purposes

#### Step 3: Insert Banner
1. Click the "Insert Banner" button
2. The application will perform several validations:
   - Check if a refreshment point is selected
   - Validate the image file format
   - Verify database connection is active
   - Count existing banners for the selected point
   - Check if maximum banner limit (5) is reached

3. If all validations pass:
   - A confirmation dialog will appear
   - Click "Yes" to proceed or "No" to cancel

4. Successful insertion will show:
   - Success message dialog
   - Status update in the status bar
   - Form reset for next operation

### Managing Operations

#### Canceling an Operation
1. Click the "Cancel" button at any time
2. Confirm the cancellation when prompted
3. The form will reset to its initial state

#### Handling Errors
The application handles various error scenarios:

1. **Invalid Image File:**
   - Error: "The selected image is not valid"
   - Solution: Select a different image file with supported format

2. **Maximum Banners Reached:**
   - Error: "Maximum banners (5) exceeded"
   - Solution: Remove existing banners or select a different refreshment point

3. **Database Connection Lost:**
   - Error: "Connection to server ETOUR interrupted"
   - Solution: Application will attempt automatic reconnection

4. **No Refreshment Point Selected:**
   - Error: "Please select a refreshment point"
   - Solution: Select a refreshment point from the dropdown

### Status Messages
Monitor the status bar for real-time feedback:
- **Ready:** Application is waiting for input
- **Loading refreshment points...:** Retrieving data from database
- **Selecting image...:** File chooser is active
- **Validating input...:** Checking user selections
- **Checking banner count...:** Counting existing banners
- **Inserting banner...:** Database operation in progress
- **Banner inserted successfully:** Operation completed

## Database Management

### Database Files
- **banners_db.mv.db:** Main database file (DO NOT DELETE)
- **banners_db.trace.db:** Log file (can be deleted if too large)

### Data Persistence
- All data persists between application sessions
- Database is stored in the application directory
- Backup: Copy `banners_db.mv.db` to safe location

### Modifying Sample Data
To add or remove refreshment points manually:
1. Stop the application
2. Install [H2 Console](http://www.h2database.com/html/tutorial.html#console)
3. Connect to `jdbc:h2:./banners_db` with username `sa` and no password
4. Use SQL commands to modify the `refreshment_points` table
5. Restart the application

## Troubleshooting

### Common Issues and Solutions

#### Issue: "H2 database driver not found"
**Solution:**
```bash
# Download H2 driver
wget https://repo1.maven.org/maven2/com/h2database/h2/2.1.214/h2-2.1.214.jar

# Run with explicit classpath
java -cp ".:h2-2.1.214.jar" InsertBannerApp
```

#### Issue: Application won't start
**Solution:**
1. Check Java installation:
   ```bash
   java -version
   ```
2. Verify file permissions
3. Check for existing application instances running

#### Issue: "Cannot load points - no database connection"
**Solution:**
1. Check if you have write permissions in the current directory
2. Verify no other process is using the database
3. Delete `banners_db.lock.db` if it exists
4. Restart the application

#### Issue: Images not displaying properly
**Solution:**
1. Ensure image files are valid
2. Check file permissions
3. Try different image formats
4. Ensure sufficient memory is available

### Error Codes and Meanings
- **ERR_DB_CONNECTION:** Database connection failed
- **ERR_MAX_BANNERS:** Maximum banner limit reached
- **ERR_INVALID_IMAGE:** Unsupported image format
- **ERR_NO_POINT:** No refreshment point selected
- **ERR_OPERATION_CANCELED:** User canceled the operation

## Advanced Configuration

### Customizing Maximum Banners
To change the maximum banners per refreshment point:
1. Open `InsertBannerApp.java` in a text editor
2. Locate line: `private static final int MAX_BANNERS = 5;`
3. Change the value to your desired maximum
4. Recompile and restart the application

### Changing Database Location
To use a different database location:
1. Modify line: `connection = DriverManager.getConnection("jdbc:h2:./banners_db;AUTO_SERVER=TRUE", "sa", "");`
2. Replace `./banners_db` with your preferred path
3. Recompile the application

### Adding Custom Image Formats
To support additional image formats:
1. Locate the `isImageValid` method
2. Add your format to the `validExtensions` array
   ```java
   String[] validExtensions = {".jpg", ".jpeg", ".png", ".gif", ".bmp", ".webp"};
   ```
3. Update the `FileNameExtensionFilter` in `selectImage()` method

## Security Considerations

### Data Protection
- Local database with no external connections by default
- No sensitive data stored (only image paths and point names)
- All database operations are local to the machine

### Best Pract
1. **Regular Backups:**
   - Periodically copy `banners_db.mv.db` to backup location
   - Consider automated backup scripts

2. **Access Control:**
   - Run application with appropriate user permissions
   - Restrict write access to database directory

3. **Image Security:**
   - Only use trusted image sources
   - Scan images for malware if from unknown sources
   - Consider implementing image size limits

## Performance Tips

### Optimization Suggestions
1. **Large Image Collections:**
   - Keep images under 2MB for faster loading
   - Consider compressing images before selection
   - Store images in a dedicated directory structure

2. **Database Performance:**
   - Regularly check database file size
   - Archive old banners if needed
   - Consider indexing for large datasets

3. **Application Performance:**
   - Close other memory-intensive applications
   - Ensure adequate disk space
   - Monitor application memory usage

### Memory Management
- Minimum heap size: 256MB
- Recommended heap size: 512MB
- To increase heap size:
  ```bash
  java -Xmx512m -Xms256m InsertBannerApp
  ```

## Support and Maintenance

### Getting Help
If you encounter issues not covered in this manual:
1. Check the status bar for specific error messages
2. Review application logs in `banners_db.trace.db`
3. Verify Java and database requirements are met

### Application Updates
To update the application:
1. Backup your `banners_db.mv.db` file
2. Replace `InsertBannerApp.java` with new version
3. Recompile the application
4. Restart the application

### System Maintenance
**Weekly:**
- Check database file size
- Backup important data
- Verify application functionality

**Monthly:**
- Review and clean up old banner images
- Archive old database entries if needed
- Update Java if newer security patches available

## Glossary

- **Refreshment Point:** A location where banners can be displayed (restaurant, cafe, etc.)
- **Banner:** An image associated with a refreshment point
- **Agency Operator:** The user who manages banners through this application
- **H2 Database:** Embedded SQL database used for data storage
- **ETOUR:** The server system mentioned in the use case (handled as connection interruptions)

## Appendix

### File Structure
```
InsertBannerApp/
├── InsertBannerApp.java      # Main application file
├── banners_db.mv.db          # Main database (auto-created)
├── banners_db.trace.db       # Database logs
├── images/                   # Optional: Store banner images here
│   ├── banner1.jpg
│   ├── banner2.png
│   └── ...
└── backups/                  # Recommended: For database backups
    ├── backup_2024_01_01.mv.db
    └── ...
```

### SQL Schema
```sql
-- Refreshment Points Table
CREATE TABLE refreshment_points (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Banners Table
CREATE TABLE banners (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    refreshment_point_id INTEGER,
    image_path VARCHAR(1024) NOT NULL,
    FOREIGN KEY (refreshment_point_id) REFERENCES refreshment_points(id)
);
```

### Keyboard Shortcuts
- **Ctrl+Q:** Quit application (via window close)
- **Enter:** Accept dialog selections
- **Escape:** Cancel dialogs
- **Tab:** Navigate between controls

### Version Information
- Application Version: 1.0
- Java Version Required: 8+
- Database: H2 2.1.214
- Last Updated: [Current Date]

---

**Note:** This application is designed for single-user desktop use. For multi-user environments, consider implementing proper database server configuration and user authentication systems.
```