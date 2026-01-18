# ModifyBanner Application User Manual

## Overview

The ModifyBanner Application is a Java-based GUI tool designed for agency operators to manage and update banner advertisements. The application simulates a real-world banner management system where users can select rest points, choose associated banners, and update their images. This manual provides comprehensive guidance for installing, configuring, and using the application.

## System Requirements

### Minimum Requirements
- **Operating System**: Windows 10+, macOS 10.14+, or Linux with X11/Wayland
- **Java Runtime**: Java 8 or later (JRE 1.8+)
- **RAM**: 512 MB minimum
- **Disk Space**: 50 MB for application and dependencies

### Recommended Requirements
- **Java Runtime**: Java 11 or later
- **RAM**: 1 GB or more
- **Display**: 1280x720 resolution or higher
- **Internet Connection**: For simulated server operations

## Installation

### Step 1: Install Java Runtime Environment (JRE)

1. **Check if Java is installed**:
   Open terminal/command prompt and type:
   ```
   java -version
   ```
   If you see version information (1.8 or higher), Java is already installed.

2. **Download Java** (if not installed):
   - Visit [Oracle Java Downloads](https://www.oracle.com/java/technologies/downloads/) or
   - [OpenJDK Downloads](https://openjdk.org/install/)
   - Download the appropriate version for your operating system
   - Follow installation instructions for your platform

### Step 2: Download the Application

Download the `ModifyBannerApplication.java` file to your local system.

### Step 3: Compile the Application

1. Open terminal/command prompt
2. Navigate to the directory containing `ModifyBannerApplication.java`
3. Compile the application:
   ```bash
   javac ModifyBannerApplication.java
   ```
   This will generate `.class` files in the same directory.

### Step 4: Run the Application

Execute the compiled application:
```bash
java ModifyBannerApplication
```

## Application Features

### Core Functions
1. **Rest Point Selection**: Browse and select from available rest points
2. **Banner Management**: View and select banners associated with selected rest points
3. **Image Selection**: Choose new images for banners with preview capability
4. **Image Validation**: Automatic validation of selected image files
5. **Banner Update**: Confirm and apply image changes to banners
6. **Server Simulation**: Simulate server connection/disconnection scenarios

### Key Components
- **Main Window**: Central interface for all operations
- **Rest Point Dropdown**: List of available rest points
- **Banner Dropdown**: Banners associated with selected rest point
- **Image Preview Panel**: Visual preview of selected image
- **Image Information Display**: Detailed info about selected image
- **Status Bar**: Real-time status updates and server connection state
- **Control Buttons**: Image selection, confirmation, and simulation controls

## User Guide

### Launching the Application
1. Follow the installation steps above
2. The application window will appear with the title "Modify Banner Application"
3. You'll see a 800x600 window with various controls

### Step-by-Step Usage

#### Step 1: Select a Rest Point
1. Locate the "Select Rest Point:" dropdown in the top-left section
2. Click the dropdown to view available rest points (e.g., "Rest Point A", "Rest Point B")
3. Select a rest point
4. **Note**: The banner dropdown will become enabled after selecting a rest point

#### Step 2: Select a Banner
1. Locate the "Select Banner:" dropdown next to the rest point selector
2. Click to view banners associated with the selected rest point
3. Select a banner from the list
4. **Status Update**: The status bar will indicate "Banner selected. You can now select a new image."

#### Step 3: Select an Image
1. Click the "Select Image" button
2. A file dialog will open
3. Browse to locate an image file on your system
4. **Supported Formats**: JPG, JPEG, PNG, GIF, BMP
5. **Validation**: The application automatically validates the selected image
   - Valid images: Preview appears, image information is displayed
   - Invalid images: Error message appears, try selecting a different file

#### Step 4: Review Image Details
After selecting a valid image:
1. **Image Preview**: The selected image appears in the center panel
2. **Image Information**:
   - File name and path
   - File size in bytes
   - Confirmation prompt appears
3. **Confirmation Button**: The "Confirm Change" button becomes enabled

#### Step 5: Confirm Banner Update
1. Click the "Confirm Change" button
2. **Success**: A success message appears, confirming the banner update
3. **Status Update**: Status bar shows "Banner successfully updated with new image!"
4. **Reset**: The interface resets for the next operation

### Simulation Features

#### Server Disconnection Simulation
1. Click "Simulate Server Disconnection" button
2. **Effect**:
   - All interactive controls become disabled
   - Status bar shows "Server connection interrupted!"
   - Warning message appears
3. **Purpose**: Tests application behavior during network/server issues

#### Server Reconnection Simulation
1. Click "Simulate Server Reconnection" button
2. **Effect**:
   - All controls become re-enabled based on previous state
   - Status bar updates to show reconnection
   - Information message appears
3. **Purpose**: Tests application recovery after network/server restoration

### Status Indicators

The status bar at the bottom shows:
- **Application Status**: Current operation state
- **Server Status**: Connection state (Connected/Disconnected)
- **Operation Guidance**: Next steps or current limitations

Common status messages:
- "Ready": Application loaded and ready
- "Rest point selected. Choose a banner.": Ready for banner selection
- "Banner selected. You can now select a new image.": Ready for image selection
- "Valid image selected. Ready to confirm change.": Ready for confirmation
- "Banner successfully updated with new image!": Operation completed

## Error Handling

### Common Errors and Solutions

1. **"Invalid image file"**
   - **Cause**: Selected file is not a supported image format
   - **Solution**: Use JPG, PNG, GIF, or BMP format files only

2. **"Cannot select rest point: Server connection interrupted."**
   - **Cause**: Server simulation is in disconnected state
   - **Solution**: Click "Simulate Server Reconnection" or restart application

3. **"No banners found for selected rest point."**
   - **Cause**: Internal data inconsistency
   - **Solution**: Try selecting a different rest point or restart application

4. **"No banner or image selected."**
   - **Cause**: Trying to confirm without proper selections
   - **Solution**: Complete all steps before confirming

### The "Errored" Use Case
When an invalid image is selected, the application simulates the "Errored" use case by:
- Displaying an error dialog
- Clearing the invalid selection
- Maintaining application state for retry

## Troubleshooting

### Application Won't Start
1. **Check Java installation**: Run `java -version` in terminal
2. **Check compilation**: Ensure `javac` command succeeded without errors
3. **File permissions**: Ensure you have read/write permissions in the directory

### GUI Display Issues
1. **Window too small**: Application requires minimum 800x600 resolution
2. **Missing components**: Ensure all `.class` files were generated during compilation
3. **Font rendering issues**: Check Java font configuration or try different Java version

### Functional Issues
1. **Buttons not working**: Check server connection state (simulated or actual)
2. **No images in preview**: Verify image file permissions and format
3. **Dropdowns empty**: Restart application to reload sample data

## Best Pract

### Image Selection
- Use compressed images (under 5MB) for better performance
- Recommended dimensions: 300x200 pixels for optimal preview
- Save images in common directories for easy access

### Workflow Optimization
1. Complete all selection steps before confirming
2. Use the status bar for guidance
3. Reset between operations using the automatic reset feature

### Data Management
- The application uses simulated data for demonstration
- In production, this would connect to a real database
- All changes are in-memory and reset on application restart

## Security Considerations

### Current Limitations
- **In-memory storage**: No persistent data storage
- **Local files only**: Uses local file system for image selection
- **No authentication**: Simulated agency operator login only

### Production Recommendations
For production deployment, consider:
1. Implementing proper user authentication
2. Adding database persistence
3. Implementing server-side validation
4. Adding HTTPS support
5. Implementing proper error logging

## Advanced Features

### Customization Options
The application can be extended by modifying the source code:
1. **Add more rest points**: Edit the `initializeSampleData()` method
2. **Add image formats**: Modify `validateImage()` method
3. **Change UI layout**: Adjust GUI components in `createAndShowGUI()`
4. **Add persistence**: Implement file or database storage

### Integration Possibilities
Potential integration points:
1. **Real database**: Replace simulated data with SQL/NoSQL database
2. **Cloud storage**: Integrate with serv like AWS S3 or Google Cloud Storage
3. **REST API**: Connect to external banner management serv
4. **Authentication service**: Integrate with LDAP, OAuth, or similar

## Support and Resources

### Getting Help
For issues with the application:
1. Check this manual for troubleshooting steps
2. Verify Java installation and version
3. Check system requirements are met
4. Review error messages in the application

### Development
For developers interested in extending the application:
- Source code is available in `ModifyBannerApplication.java`
- Built using standard Java Swing components
- Follows MVC pattern with separation of concerns
- Includes comprehensive inline comments

## License and Attribution

This application is provided as a demonstration tool. For production use, appropriate licensing and customization would be required.

### Dependencies
- **Java Swing**: Built-in Java GUI framework
- **No external libraries**: Pure Java implementation

## Changelog

### Version 1.0 (Current)
- Initial release with core functionality
- Simulated data and operations
- Server connection simulation
- Basic error handling
- Complete user interface

### Planned Features
1. Persistent data storage
2. Multi-user support
3. Advanced image editing
4. Batch operations
5. Export/import functionality

---

**Note**: This application is designed for demonstration purposes. All data is simulated and reset when the application is closed. For production environments, significant modifications and security enhancements would be required.