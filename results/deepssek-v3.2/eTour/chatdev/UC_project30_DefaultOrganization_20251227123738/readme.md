# InsertTag Application
## Overview
A complete, runnable Java program that implements the InsertTag use case for an agency operator to insert new tag searches into a system.
## Use Case Implementation
The application follows exactly the specified use case flow:
### Entry Condition
- Agency operator has logged in (simulated by welcome message)
### Flow of Events
1. **Access functionality** - System provides access to tag insertion feature
2. **Show form** - Console-based form displayed for entering tag
3. **Fill and submit** - User enters tag name and submits via console
4. **Verify data** - System validates input and checks for duplicates
   - If duplicate exists: Activates ExistingErrorTag use case
   - If data invalid: Activates Errored use case
   - If server interruption: Handles ETOUR scenario
### Exit Condition
- Notification about successful inclusion of the tag
### Quality Requirements Handled
- Server interruption (ETOUR) simulated during connection and insertion
- Proper validation and error handling
- Edge cases covered (null, empty, long strings, special characters)
## How to Run
### Compilation