# ExistingErrorTag Use Case Application
## Description
This Java application simulates the "ExistingErrorTag" use case described in the requirements. The program provides a graphical user interface where users can enter search tags into a system, with error handling for duplicate tags and state recovery.
## Use Case Flow
1. **Entry Condition**: User is asked to enter a search tag that already exists in the system
2. **Step 1**: System displays an error message and asks for confirmation of reading
3. **Step 2**: User confirms reading of the notification
4. **Step 3**: System recovers the previous state
5. **Exit Condition**: System returns control to user interaction
## Features
- GUI built with Java Swing
- Input validation for empty tags
- Case-insensitive duplicate tag detection
- Error confirmation dialog
- State recovery after error
- Display of all existing tags
## How to Run
1. Compile: `javac Main.java`
2. Run: `java Main`
## Pre-loaded Tags
The system starts with these tags (all stored in lowercase):
- java
- programming
- gui
- swing
## Requirements
- Java 8 or higher
- No external libraries required (uses standard Java Swing)
## Recent Changes
- Fixed state recovery mechanism: The previous state is now captured before duplicate check, ensuring proper recovery to the state before erroneous tag entry.
- Removed problematic FocusListener implementation.