# Point of Restaurant Statistics Viewer - User Manual

## 📋 Overview

The Point of Restaurant Statistics Viewer is a Java-based desktop application designed for restaurant operators to view detailed performance statistics for their establishment. The application provides secure authentication, intuitive navigation, and comprehensive reporting with handling of server connectivity issues.

### Key Features

- **Secure Authentication**: Role-based login system for restaurant operators
- **Personal Statistics Dashboard**: Detailed metrics including sales, orders, and customer satisfaction
- **Real-time Data Simulation**: Mock data generation with realistic business metrics
- **Connection Resilience**: Built-in handling for server connectivity interruptions
- **Responsive GUI**: User-friendly interface built with Java Swing

## 🚀 Quick Start Guide

### Prerequisites

#### System Requirements
- **Operating System**: Windows 10/11, macOS 10.15+, or Linux (any modern distribution)
- **Java Runtime**: Java 8 or higher (JDK 1.8+ recommended)
- **Memory**: Minimum 512MB RAM, 1GB recommended
- **Disk Space**: 10MB for application files
- **Display**: Minimum resolution 800×600 pixels, graphical desktop environment

#### Software Dependencies
The application requires Java with Swing/AWT support. No additional libraries are needed as all components use standard Java packages:
- `java.base` (included by default)
- `java.desktop` (for GUI - AWT/Swing)
- `java.util` (for data structures and utilities)

### Installation Steps

#### For Windows Users
1. **Verify Java Installation**
   ```
   java -version
   ```
   If Java is not installed, download and install from [Oracle Java](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://adoptium.net/)

2. **Download Application Files**
   - Create a new folder for the application (e.g., `RestaurantStatsViewer`)
   - Save all Java source files in this folder

3. **Compile the Application**
   ```
   cd RestaurantStatsViewer
   javac *.java
   ```

#### For macOS Users
1. **Install Java (if needed)**
   ```
   brew install openjdk@17
   ```

2. **Compile the Application**
   ```
   javac -d . *.java
   ```

#### For Linux Users
1. **Install Java Development Kit**
   ```
   sudo apt update
   sudo apt install openjdk-17-jdk
   ```

2. **Compile the Application**
   ```
   javac *.java
   ```

## 🎮 Using the Application

### Starting the Application
Run the main class from your terminal or command prompt:
```
java ViewPersonalStatisticApp
```

### 1. Authentication Screen

**Login Information**
- **Username**: `operator1`
- **Password**: `password123`
- **Restaurant**: City Bistro

**Procedure**:
1. Launch the application
2. Enter your credentials in the login form
3. Click "Login" to authenticate
4. If credentials are correct, you'll proceed to the main menu

### 2. Main Menu Navigation

The main menu provides access to all application features:

| Button | Function |
|--------|----------|
| **View Personal Statistics** | Opens the statistics dashboard |
| **Manage Menu** | Placeholder for future feature |
| **View Orders** | Placeholder for future feature |
| **Logout** | Returns to login screen |

**To View Statistics**:
1. Click "View Personal Statistics" button

### 3. Statistics Dashboard

The dashboard displays comprehensive restaurant metrics organized in clear sections:

#### Performance Metrics Section:
- **Total Orders**: Number of orders processed
- **Total Revenue**: Income generated in USD
- **Average Order Value**: Revenue per order
- **Peak Business Hour**: Busiest time of day (24-hour format)
- **Most Popular Item**: Best-selling menu item

#### Quality Metrics Section:
- **Customer Satisfaction**: Score from 1-100 with rating (Excellent/Good/Fair/Needs Improvement)

#### Summary Section:
- **Business Insights**: Contextual recommendations based on performance data
- **Timestamps**: Report generation and last update times

### 4. Dashboard Controls

#### Refresh Button
- Updates statistics with new data
- Simulates fetching fresh data from server
- Shows real-time loading status

#### Back Button
- Returns to main menu
- Statistics window closes without affecting session

#### Status Indicator
- Shows current state: "Loading statistics...", "Ready", or error messages
- Located at the bottom of the dashboard

## ⚙️ Advanced Features

### Server Connection Handling

The application simulates real-world server interactions with built-in error handling:

#### Connection Error Scenarios
1. **Server Disconnection** (10% chance on refresh)
   - Automatic error detection
   - User notification with details
   - Reconnection prompt

2. **Manual Reconnection**
   - Click "Yes" when reconnection prompt appears
   - Automatic reconnection attempt with 80% success rate
   - Status updates throughout the process

3. **Retry Mechanism**
   - Continue using application after failed reconnection
   - Return to functional state when reconnection succeeds

### Data Simulation

The application generates realistic mock data:
- **Order Volume**: 100-300 orders (variable each refresh)
- **Revenue Calculation**: Based on order volume and variable pricing
- **Peak Hours**: Typically between 12:00-17:00
- **Customer Scores**: Random between 70-100 with contextual ratings

## 🔧 Troubleshooting

### Common Issues and Solutions

| Issue | Solution |
|-------|----------|
| **Java not found** | Install Java JDK/JRE and verify installation |
| **Compilation errors** | Ensure all files are in same directory with proper naming |
| **Application won't start** | Check console for error messages, verify Java version |
| **Blank statistics** | Server connection failed - click refresh or wait |
| **Slow loading** | Application simulates 1-second network delay (by design) |
| **Login fails** | Use exact credentials: operator1/password123 |

### Connection Troubleshooting
1. **Persistent Connection Failures**:
   - Check internet connectivity
   - Retry multiple times (simulated failure rate is random)
   - Restart application if needed

2. **Data Display Issues**:
   - Click Refresh button
   - Ensure proper screen resolution
   - Verify Java Swing is properly supported

## 📊 Understanding the Statistics

### Performance Categories

#### Business Volume
- **Excellent**: >200 orders
- **Good**: 100-200 orders
- **Below Target**: <100 orders

#### Customer Satisfaction
- **Excellent**: 90-100 points
- **Good**: 75-89 points
- **Fair**: 60-74 points
- **Needs Improvement**: <60 points

#### Peak Hours Interpretation
- **12:00-14:00**: Lunch rush
- **17:00-19:00**: Dinner rush
- **Other hours**: Could indicate special events or delivery patterns

## 🛠️ Development Notes

### Application Architecture

```
ViewPersonalStatisticApp (Main)
    ├── LoginFrame (Authentication)
    ├── MainMenuFrame (Navigation)
    └── StatisticsFrame (Dashboard)
        ├── StatisticsService (Data Layer)
        └── StatisticsData (Model)
```

### Key Design Patterns
1. **Model-View-Service (MVS)**: Separation of data, presentation, and business logic
2. **SwingWorker**: Non-blocking UI operations for better user experience
3. **Custom Exception Handling**: Specialized error management for server connections

### Extensibility Points
1. **Database Integration**: Replace `StatisticsService` with real database calls
2. **Additional Features**: Expand menu options in `MainMenuFrame`
3. **Enhanced Security**: Implement stronger authentication mechanisms

## 📝 Best Pract

### For Optimal Use
1. **Regular Refreshes**: Click refresh periodically for updated statistics
2. **Session Management**: Log out properly when finished
3. **Data Interpretation**: Use summary insights for operational decisions
4. **Connection Awareness**: Note that server interruptions are simulated features

### Security Guidelines
1. **Password Protection**: Always logout on shared computers
2. **Data Confidentiality**: Application uses mock data for demonstration
3. **System Security**: Ensure antivirus software doesn't block Java applications

## 🔄 Application Flow

### Standard Workflow
1. **Start** → Login Screen
2. **Authenticate** → Main Menu
3. **Select** → Statistics Dashboard
4. **View** → Analyze Metrics
5. **Refresh** → Update Data
6. **Back** → Main Menu
7. **Logout** → Return to Start

### Error Recovery Workflow
1. **Connection Error Detected**
2. **User Notified** → Reconnection Prompt
3. **User Choice** → Yes/No
4. **If Yes** → Automatic Reconnection Attempt
5. **If Successful** → Continue Session
6. **If Failed** → Manual Retry Option

## 🎯 Tips for Effective Use

1. **Peak Hours Analysis**: Use peak hour data to optimize staffing schedules
2. **Popular Items**: Feature best-selling items in promotions
3. **Satisfaction Trends**: Monitor customer satisfaction over multiple refreshes
4. **Revenue Patterns**: Identify high-revenue periods for targeted marketing
5. **Comparative Analysis**: Refresh multiple times to see performance variations

## 📞 Support and Feedback

### Getting Help
- **Installation Issues**: Check Java version and compilation errors in console
- **Usage Questions**: Refer to this manual and on-screen instructions
- **Technical Problems**: Verify system meets minimum requirements

### Reporting Issues
If you encounter persistent problems:
1. Note the exact error message
2. Record steps to reproduce the issue
3. Check system meets all requirements
4. Try the troubleshooting steps above

## 📚 Additional Resources

### Recommended Reading
- Java Swing Documentation
- Restaurant Management Best Pract
- Customer Experience Metrics

### Future Enhancements
Planned features (not implemented in current version):
- Real database integration
- Export statistics to PDF/Excel
- Historical trend analysis
- Multi-operator support
- Mobile companion application

---

*Note: This application simulates restaurant statistics for demonstration purposes. All data shown is randomly generated and does not represent actual business metrics.*