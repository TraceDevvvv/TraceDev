```markdown
# ActiveConvention System - User Manual

## Overview

The ActiveConvention System is a Java-based application that implements the "ActiveConvention" use case for activating convention requests by refreshment points. This system provides an intuitive graphical user interface (GUI) that guides Agency Operators through the complete workflow of enabling, loading, checking, confirming, and processing convention activation requests. The application handles server communication and interruption scenarios gracefully.

## Main Features

### 1. **Sequential Workflow Management**
- Step-by-step activation process following the specified use case
- State management ensuring proper sequence of operations
- Prevents skipping steps through logical workflow constraints

### 2. **Server Communication Simulation**
- Simulates communication with ETOUR server
- Handles server connection interruptions gracefully
- Real-time status updates on server connectivity

### 3. **Data Form Display**
- Displays loaded convention data in a structured form
- Shows key information: Convention ID, Refreshment Point, Dates, and Status
- Simulated data presentation for demonstration purposes

### 4. **Validation and Confirmation**
- Agreement checking before proceeding with activation
- Confirmation dialogs to prevent accidental activations
- Option to cancel at critical decision points

### 5. **Comprehensive Logging**
- Real-time status updates in GUI text area
- Console logging with Java's built-in logging framework
- Detailed step-by-step progress tracking

### 6. **Error Handling**
- Server connection interruption detection
- Automatic system reset on cancellations
- User-friendly error messages

## System Requirements

### Software Requirements
- **Java Runtime Environment (JRE)**: Version 8 or higher
- **Java Development Kit (JDK)**: Required for compilation (if source code modification needed)
- **Operating System**: Windows, macOS, or Linux with GUI support

### Hardware Requirements
- **Processor**: 1 GHz or faster
- **RAM**: 512 MB minimum (1 GB recommended)
- **Storage**: 100 MB free disk space
- **Display**: 1024×768 screen resolution or higher

## Installation Guide

### Option 1: Running the Compiled Application

1. **Download the JAR file** from the distribution package
2. **Open terminal or command prompt** in the directory containing the JAR file
3. **Execute the application**:
   ```bash
   java -jar ActiveConvention.jar
   ```

### Option 2: Compiling from Source Code

1. **Install JDK** (if not already installed):
   - Download from [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)
   - Follow installation instructions for your operating system
   - Verify installation: `java -version` and `javac -version`

2. **Download the source code**:
   ```bash
   git clone https://github.com/your-repo/ActiveConvention.git
   cd ActiveConvention
   ```

3. **Compile the application**:
   ```bash
   javac ActiveConvention.java
   ```

4. **Run the application**:
   ```bash
   java ActiveConvention
   ```

### Dependencies
The application uses only standard Java libraries:
- `javax.swing.*` - For GUI components
- `java.awt.*` - For layout management
- `java.util.concurrent.*` - For background task management
- `java.util.logging.*` - For logging functionality

No external libraries or frameworks are required.

## Usage Guide

### 1. Starting the Application
When you launch the ActiveConvention System, you'll see:
- Main window with title "ActiveConvention System"
- Five sequentially numbered buttons (only first button enabled initially)
- Status text area at the bottom showing system messages

### 2. Step-by-Step Operation

#### **Step 1: Enable Activation**
1. Click the **"1. Enable Activation"** button
2. System will:
   - Enable the "Load Convention Data" button
   - Disable the Enable button (prevents multiple activations)
   - Log: "Activation function enabled. Proceed to load data."

#### **Step 2: Load Convention Data**
1. Click the **"2. Load Convention Data"** button
2. If server connection is active:
   - A data form window will appear with convention details
   - Data includes Convention ID, Refreshment Point, Start/End Dates, and Status
   - The "Check Agreement" button becomes enabled
   - Log: "Convention data loaded successfully. Displaying form."
3. If server connection is interrupted:
   - Error message displayed in status area
   - Log: "Error: Connection to server ETOUR interrupted. Cannot load data."

**Note**: Click "Close" button on the data form window to proceed.

#### **Step 3: Check Agreement**
1. Click the **"3. Check Agreement"** button
2. A confirmation dialog appears: "Do you agree to activate the convention based on the loaded data?"
3. Options:
   - **Yes**: Proceeds to next step, enables "Confirm Activation" button
   - **No**: Cancels activation, resets system to initial state
4. Log (if Yes): "Agreement checked and approved. Ready for confirmation."

#### **Step 4: Confirm Activation**
1. Click the **"4. Confirm Activation"** button
2. A second confirmation dialog appears: "Are you sure you want to activate the convention?"
3. Options:
   - **Yes**: Proceeds to processing, enables "Process Request" button
   - **No**: Cancels activation, resets system
4. Log (if Yes): "Activation confirmed. Proceed to process request."

#### **Step 5: Process Request**
1. Click the **"5. Process Request"** button
2. System will:
   - Display "Processing request..." message
   - Simulate 2-second processing delay
   - Check server connection status
   - **If server connected**: Shows success message, notifies activation
   - **If server disconnected**: Shows error message, processing fails
3. After completion, system automatically resets for next operation

### 3. System Reset
The system automatically resets when:
- User selects "No" in agreement or confirmation dialogs
- Processing completes (successfully or with error)
- Server interruption occurs during critical operations

Manual reset can be triggered by closing and reopening the application.

### 4. Server Interruption Simulation
For demonstration purposes, the system includes a simulated server interruption:
- Occurs automatically 30 seconds after application start
- Can be observed as "!!! SERVER CONNECTION INTERRUPTED !!!" in status area
- Affects all operations requiring server communication

## Troubleshooting

### Common Issues and Solutions

#### **1. Application Won't Start**
- **Issue**: "Error: Could not find or load main class"
- **Solution**: Ensure Java is properly installed (`java -version` should show version 8 or higher)

#### **2. GUI Doesn't Appear**
- **Issue**: Application runs but no window appears
- **Solution**: Check system requirements for GUI support; run with `java -Djava.awt.headless=false ActiveConvention`

#### **3. Buttons Remain Disabled**
- **Issue**: Cannot proceed past a certain step
- **Solution**: Ensure you're following the sequence correctly; check status messages for error information

#### **4. Server Connection Errors**
- **Issue**: Persistent "Connection to server ETOUR interrupted" messages
- **Solution**: Verify network connectivity; the demo simulates this after 30 seconds - restart application if needed

### Logs and Debugging
- **Status Area**: Real-time operational messages in the GUI
- **Console Logs**: Detailed logs with timestamps and log levels
- **Log Level**: Default set to INFO; can be modified in source code for debugging

## Best Pract

### For Agency Operators
1. **Follow the sequence**: Complete steps in order as buttons become enabled
2. **Review data carefully**: Check all information in the data form before agreement
3. **Confirm intentionally**: Use confirmation dialogs as intended safety checks
4. **Monitor status area**: Watch for system messages and error notifications

### For System Administrators
1. **Regular updates**: Ensure Java runtime is up to date
2. **Network monitoring**: Maintain stable connection to ETOUR server
3. **Log management**: Archive console logs for audit purposes
4. **User training**: Ensure operators understand the workflow sequence

## Technical Details

### Architecture
- **Programming Language**: Java (Standard Edition)
- **GUI Framework**: Swing (built-in Java GUI toolkit)
- **Concurrency**: `ScheduledExecutorService` for background tasks
- **Logging**: `java.util.logging` framework

### Code Structure
```
ActiveConvention.java
├── GUI Components (JFrame, JButton, JTextArea)
├── Event Listeners (ActionListener)
├── Business Logic Methods
│   ├── enableActivation()
│   ├── loadData()
│   ├── checkAgreement()
│   ├── confirmActivation()
│   └── processRequest()
├── Helper Methods
│   ├── showDataForm()
│   ├── logStatus()
│   └── resetSystem()
└── Main Method
```

### State Management
The application maintains state through boolean flags:
- `isEnabled`: Activation function status
- `isDataLoaded`: Data loading completion
- `isAgreementChecked`: User agreement status
- `isConfirmed`: Final confirmation status
- `isServerConnected`: Server connectivity status

## Support and Maintenance

### Getting Help
- **Documentation**: Refer to this manual for operational guidance
- **Error Messages**: Check status area for detailed error information
- **Logs**: Review console output for technical details

### Updates and Patches
- Check with system administrator for application updates
- Ensure compatibility with Java version requirements
- Report bugs or issues through designated channels

### System Integration
For production deployment:
1. Replace simulated server communication with actual ETOUR API calls
2. Implement database connectivity for persistent data storage
3. Add user authentication and authorization
4. Configure logging to file system or centralized log management

## Frequently Asked Questions

**Q: Can I skip steps in the activation process?**
A: No, the system enforces sequential workflow. Each step must be completed before the next becomes available.

**Q: What happens if I lose internet connection?**
A: The system detects server interruptions and displays appropriate error messages. You'll need to restart the process once connection is restored.

**Q: Can I process multiple conventions simultaneously?**
A: The current version supports single operation at a time. Complete or cancel one process before starting another.

**Q: How do I know if the activation was successful?**
A: Successful activation shows: "Request processed successfully. Convention activated." and a notification dialog.

**Q: Is my data saved automatically?**
A: This is a demonstration version. In production, data persistence would be implemented based on organizational requirements.

---

*Version: 1.0.0 | Last Updated: October 2023 | For internal use only*
```