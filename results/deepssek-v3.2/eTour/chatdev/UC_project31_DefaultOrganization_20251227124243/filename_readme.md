# ModifyPassword Application
## Overview
This project provides multiple implementations of the **ModifyPassword** use case for Agency Operators to change their account passwords.
## Implementations
### 1. Console Application (`ModifyPasswordConsole.java`)
A command-line interface that guides the user through the password change process with detailed prompts and validation.
### 2. GUI Application (`ModifyPassword_GUI.java`)
A simple Swing-based graphical user interface with basic form elements and validation.
### 3. Enhanced GUI Application (`GuiBasedPasswordChanger.java`)
An advanced GUI with additional features like:
- Reset functionality
- Detailed status area
- Improved layout and user experience
- Additional validation rules
## Features
- **User Authentication**: Verifies current password matches stored credentials
- **Password Validation**: 
  - Minimum 8 characters
  - Must contain both letters and digits
  - New password must differ from current password
  - Confirmation must match
- **Error Handling**: Comprehensive error messages for all failure cases
- **Mock Database**: In-memory HashMap simulating user storage
- **Server Simulation**: Random server interruption simulation (10% chance)
## Default Test Credentials
- **Username**: `agency_op`
- **Current Password**: `old_pass123`
## How to Compile and Run
### Console Version: