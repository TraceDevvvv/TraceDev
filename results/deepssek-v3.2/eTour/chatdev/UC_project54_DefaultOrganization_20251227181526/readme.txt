ModifyComment System - Complete Runnable Java Program
HOW TO RUN:
1. Compile all Java files:
   javac *.java
2. Run the main program:
   java ModifyComment
PROGRAM FLOW:
1. Tourist is initialized viewing a specific site
2. Tourist chooses to change an existing comment
3. System validates the new comment (triggers ErroredUseCase if invalid)
4. System checks server connection with retry mechanism (handles ETOUR server interruption)
5. Tourist confirms the change
6. System updates the comment in the database
7. System notifies about the alteration (exit condition)
IMPROVED SERVER CONNECTION HANDLING:
- Added retry logic with 3 attempts for temporary network issues
- Provides user feedback during connection attempts
- Handles "Interruption of the connection to the server ETOUR" as specified in the use case
- Graceful fallback when connection cannot be established
TESTING SCENARIOS:
- Valid comment: Should complete successfully
- Empty comment: Triggers ErroredUseCase
- Very long comment: Triggers ErroredUseCase
- Server disconnection: Use ModifyComment.disconnectServer() before running
- User cancellation: Enter anything except "yes" when confirming
- Simulated connection issues: System will retry up to 3 times
CLASSES:
1. ModifyComment - Main class with complete use case implementation
2. ErroredUseCase - Error handling module
3. Comment - Comment entity class
4. Site - Site entity class
DESIGN NOTES:
- Uses HashMap for in-memory comment storage
- Implements server connection retry mechanism to handle interruptions
- Implements all steps from the use case specification including interruption handling
- Handles edge cases through validation and error handling
- Provides clear user notifications for connection issues