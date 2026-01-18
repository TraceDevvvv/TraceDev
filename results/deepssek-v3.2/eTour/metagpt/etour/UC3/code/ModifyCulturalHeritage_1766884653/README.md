# Modify Cultural Heritage System - Java Implementation

## Overview
This Java program implements the "ModifyCulturalHeritage" use case for a cultural heritage management system. The program allows an Agency Operator to modify cultural heritage data in the system following a specific workflow with input validation, confirmation mechanisms, and protection against multiple submissions.

## Use Case Description
**ModifyCulturalHeritage**: Change data of a cultural object in the system.

**Participating Actor**: Agency Operator

**Entry Conditions**: The agency has logged in

**Flow of Events**:
1. View the list of cultural goods (from SearchCulturalHeritage use case)
2. Select and activate change function
3. Load cultural data and display edit form
4. Change data in form and submit
5. Verify data and ask for confirmation (activate Errored use case if invalid/insufficient data)
6. Confirm the operation
7. Store the modified data

**Exit Conditions**:
- Notification system has changed the data
- Operator cancels the operation
- Interruption of the connection to the server

**Quality Requirement**: Blocks input controls after confirmation to avoid multiple submissions before the end of the operation.

## Prerequisites
- Java Development Kit (JDK) 8 or higher
- Command-line interface (Terminal/Command Prompt)

## File Structure