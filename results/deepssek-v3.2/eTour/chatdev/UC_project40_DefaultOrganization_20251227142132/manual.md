# DeleteBanner Application - User Manual

## Overview

The DeleteBanner application is a Java-based software solution designed for Point of Restaurant Operators to manage and delete banner advertisements associated with their restaurants. This application implements the complete flow for the DeleteBanner use case, providing an intuitive interface for viewing, selecting, and removing banner ads from the system.

## Main Features

1. **Authentication Simulation**: Automatically assumes successful operator authentication
2. **Banner Management**: View all banners associated with the restaurant
3. **Smart Search**: Find banners by name (case-insensitive)
4. **Safe Deletion Process**: Confirmation prompts prevent accidental deletions
5. **Error Handling**: Robust handling of server interruptions and edge cases
6. **Exit Conditions Support**: Supports operation cancellation and server disconnection scenarios

## System Requirements

### Hardware Requirements
- Minimum: 2GB RAM
- 1GHz processor or faster
- 500MB available disk space

### Software Requirements
- **Java Development Kit (JDK)**: Version 8 or higher
- **Command Line Interface**: Terminal or Command Prompt
- **Internet Connection**: For server ETOUR connectivity simulation

## Installation Steps

### Step 1: Install Java Development Kit
1. **Windows/Mac/Linux**: Download and install JDK from [Oracle's official website](https://www.oracle.com/java/technologies/javase-downloads.html)
2. Verify installation by opening terminal/command prompt and typing:
   ```bash
   java -version
   ```
3. You should see Java version information

### Step 2: Download the Application Files
1. Ensure you have these two Java files in the same directory:
   - `Banner.java` - Banner class definition
   - `DeleteBannerApp.java` - Main application class

### Step 3: Compile the Application
1. Open terminal/command prompt
2. Navigate to the directory containing the Java files
3. Compile both Java files:
   ```bash
   javac Banner.java DeleteBannerApp.java
   ```
4. This will create `.class` files ready for execution

## How to Use the Application

### Starting the Application
1. After compilation, run the application:
   ```bash
   java DeleteBannerApp
   ```

### Application Flow

#### Step 1: Authentication
- The application starts with an authentication success message
- Note: Authentication is simulated as per the use case entry condition

#### Step 2: View Available Banners
- The system displays all banners associated with your restaurant
- Each banner shows:
  - Name
  - Status (Active/Inactive)
  - Index number

#### Step 3: Select Banner to Delete
1. Enter the name of the banner you want to delete
2. Available commands:
   - Enter banner name: Selects a specific banner
   - Type `exit`: Exit the application completely
   - Type `cancel`: Cancel current operation and return to main menu

#### Step 4: Deletion Confirmation
1. Review the banner details displayed:
   - Banner name
   - Image URL
   - Current status
2. Confirm deletion by typing `yes`
3. Cancel by typing `no` or anything else

#### Step 5: Operation Results
- **Success**: Green success message with banner name confirmation
- **Failure**: Error message with reason and troubleshooting tips
- **Server Interruption**: Automatic reconnection prompt with 10% simulated failure chance

### Keyboard Commands Reference

| Command | Action | Description |
|---------|--------|-------------|
| Banner name | Select banner | Enter exact banner name to select |
| `exit` | Exit application | Terminate the entire program |
| `cancel` | Cancel operation | Return to banner selection screen |
| `yes` | Confirm action | Proceed with deletion |
| `no` | Cancel action | Abort current operation |

## Sample Banners

The application comes preloaded with four sample banners:

1. **Summer Special** - Active status
2. **Winter Promotion** - Active status
3. **Grand Opening** - Inactive status
4. **Happy Hour** - Active status

## Error Handling

### Common Errors and Solutions

1. **"Banner not found" Error**
   - **Cause**: Typo in banner name
   - **Solution**: Check exact banner name from the list

2. **Server Connection Interrupted**
   - **Cause**: Simulated server failure (10% chance)
   - **Solution**: Retry the operation or check network connection

3. **Operation Interrupted**
   - **Cause**: System-level interruption
   - **Solution**: Restart the application

## Best Pract

1. **Always Confirm**: Use the confirmation prompt to prevent accidental deletions
2. **Check Status**: Review banner status before deletion
3. **Save Work**: If deleting multiple banners, note which ones have been removed
4. **Server Connectivity**: Ensure stable internet connection during operations
5. **Regular Backups**: Maintain backup of important banners

## Exit Conditions

The application supports all specified exit conditions:

1. **Successful Deletion**: "SUCCESS" message appears
2. **Operator Cancellation**: Type `cancel` or `no` during confirmation
3. **Server Interruption**: Automatic error handling with reconnection prompts

## Troubleshooting

### Issue: Application won't start
- **Check**: Java installation with `java -version`
- **Fix**: Reinstall JDK if version shows error

### Issue: Banner not appearing in list
- **Check**: Banner name spelling
- **Fix**: Use exact name including spaces and capitalization

### Issue: Server connection errors persistent
- **Check**: Internet connection
- **Fix**: Restart application and network connection

## Support

For additional support:
1. Review error messages carefully
2. Check system requirements are met
3. Verify all files are in the same directory
4. Contact system administrator if issues persist

## License and Disclaimer

This application is for demonstration purposes. Ensure you have proper authorization before deleting any actual banner advertisements from production systems.

---

**Note**: This is a simulated application. Server ETOUR operations are simulated with random interruption scenarios for demonstration purposes.