```markdown
# Restaurant Menu Editor - User Manual

## Overview
The Restaurant Menu Editor is a Java-based desktop application designed for restaurant operators to manage their weekly menu schedules. The system provides a secure login interface with full menu editing capabilities for each day of the week, including validation, persistence, and server connection management.

## System Requirements

### Hardware Requirements:
- Processor: 1 GHz or faster
- RAM: 512 MB minimum
- Storage: 100 MB free disk space

### Software Requirements:
- **Operating System**: Windows 7+, macOS 10.12+, or Linux with GUI
- **Java Runtime Environment**: JRE 8 or higher
- **Screen Resolution**: 1024x768 minimum

## Installation

### Step 1: Install Java
1. Download and install Java Development Kit (JDK) 8 or higher from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html) or use OpenJDK
2. Verify installation by opening a terminal/command prompt and typing:
   ```bash
   java -version
   ```
   You should see Java version information.

### Step 2: Download Application Files
Create a folder named `RestaurantMenuEditor` and download these files:
```
RestaurantMenuEditor/
├── Authentication.java
├── DayMenu.java
├── MenuEditorFrame.java
├── MenuManager.java
└── README.md
```

### Step 3: Compile the Application
Open a terminal/command prompt in the `RestaurantMenuEditor` folder and run:
```bash
javac *.java
```

### Step 4: Run the Application
Start the application with:
```bash
java Authentication
```

## Main Features

### 1. Secure Authentication
- Username: `operator`
- Password: `admin123`
- Encrypted password field input
- Automatic menu editor launch upon successful login

### 2. Weekly Menu Management
- View and edit menus for all 7 days of the week
- Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
- Individual menu management per day

### 3. Menu Editing Features
- Load existing menus for editing
- Add/remove/update dishes
- Real-time input validation
- Dish name validation (2-100 characters)

### 4. Data Validation
- Empty menu prevention
- Dish name length validation
- Server connection verification
- Data integrity checks

### 5. Data Persistence
- Automatic menu data saving to `weekly_menu.dat`
- Persistent storage across application sessions
- File-based backup system

### 6. Server Connection Management
- Simulated server connection checks
- Graceful handling of connection interruptions
- Automatic retry mechanisms

## User Guide

### Getting Started

1. **Launch the Application**
   ```bash
   java Authentication
   ```

2. **Login**
   - Enter username: `operator`
   - Enter password: `admin123`
   - Click "Login" button

3. **Main Editor Interface**
   Once logged in, you'll see the main menu editor window with:
   - Day selection dropdown (top)
   - Menu editing text area (center)
   - Action buttons (bottom)

### Editing a Menu

#### Step 1: Select a Day
- Click the dropdown menu labeled "Select Day"
- Choose the day you want to edit (Monday through Sunday)

#### Step 2: Load Existing Menu
- Click the "Load Menu" button
- The current menu for the selected day will appear in the text area
- If no menu exists, the area will be empty

#### Step 3: Edit the Menu
- Each line in the text area represents one dish
- Add new dishes by typing them on new lines
- Remove dishes by deleting their lines
- Modify dishes by editing the text
- Example format:
  ```
  Spaghetti Carbonara
  Caesar Salad
  Grilled Salmon
  Tiramisu
  ```

#### Step 4: Validate Changes
- Click "Verify & Confirm" button
- The system will check:
  - Menu is not empty
  - Each dish name is 2-100 characters
  - Server connection is available
- If validation fails, error messages will guide corrections

#### Step 5: Save Changes
- Click "Save Changes" button
- The system will:
  1. Validate the data
  2. Confirm server connection
  3. Save to persistent storage
  4. Display success confirmation

### Important Notes

#### Menu Validation Rules:
- Each dish must be 2-100 characters
- Empty dishes (blank lines) are ignored
- Duplicate dishes are allowed (representing multiple servings)
- Special characters are permitted in dish names

#### Connection Handling:
- The application checks server connection automatically
- If connection fails, you'll see an error message
- Try again when connection is restored
- Menu data is saved locally and will sync when connection returns

#### Data Safety:
- Changes are saved to `weekly_menu.dat` file
- Keep this file backed up
- The file is automatically created on first save
- Do not modify the file manually

### Shortcut Keys
- **Enter**: Add new line in text area
- **Tab**: Move between form elements
- **Ctrl+C/V**: Copy/paste (standard shortcuts)
- **Esc**: Close dialog windows

## Troubleshooting

### Common Issues:

#### 1. "Cannot connect to server"
- Check your internet connection
- Try again in a few minutes
- The application uses simulated server checks

#### 2. "Invalid credentials"
- Ensure you're using: username=`operator`, password=`admin123`
- Passwords are case-sensitive

#### 3. "Menu cannot be empty"
- Add at least one dish to the menu
- Each dish should be on its own line

#### 4. "Dish name too short/long"
- Ensure each dish name is 2-100 characters
- Remove extra spaces

#### 5. Application won't start
- Verify Java is installed: `java -version`
- Ensure all .java files are in the same folder
- Try recompiling: `javac *.java`

### Data Recovery:
If `weekly_menu.dat` becomes corrupted:
1. Rename or delete the corrupted file
2. Restart the application
3. Menus will be initialized as empty

## Security Notes

### Account Security:
- Default credentials: operator/admin123
- For production use, change the credentials in Authentication.java
- Consider implementing password encryption

### Data Security:
- Menu data is stored locally
- No external data transmission in current version
- Add network security if implementing real server connections

## Customization

### Changing Login Credentials:
Edit `Authentication.java`:
```java
private static final String VALID_USERNAME = "your_username";
private static final String VALID_PASSWORD = "your_password";
```

### Modifying Validation Rules:
Edit `DayMenu.java`:
```java
private boolean isValidDishName(String dish) {
    // Change 2 and 100 to your preferred limits
    return trimmed.length() >= 2 && trimmed.length() <= 100;
}
```

### Changing Days of Week:
Edit `MenuEditorFrame.java`:
```java
String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
```

## Support

### Getting Help:
1. Check this manual for solutions
2. Verify Java installation
3. Ensure correct file structure

### Known Limitations:
- Single user system
- No role-based access control
- Local storage only (no cloud sync)
- Simulated server connections

### Future Enhancements:
Planned features (not implemented):
- Multiple user accounts
- Cloud synchronization
- Menu templates
- Print functionality
- Export to PDF/Excel

## License and Copyright

This application is provided as-is for educational and demonstration purposes. Commercial use may require modifications and proper licensing.

---

**Note**: Always backup your `weekly_menu.dat` file before making major changes or reinstalling the application.

For additional questions or customization needs, consult with your system administrator or Java developer.
```