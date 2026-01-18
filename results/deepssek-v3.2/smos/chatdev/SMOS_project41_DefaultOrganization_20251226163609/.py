### Application Interface
1. **Database Mode Indicator**: Shows which database is being used (H2, MySQL, or MOCK)
2. **Form Fields**:
   - Student: Name of the student (letters, spaces, hyphens, apostrophes only)
   - Description: Details of the disciplinary note (max 1000 characters)
   - Teacher: Name of the reporting teacher
   - Date: Date of incident in YYYY-MM-DD format
3. **Buttons**:
   - Save: Validate and save changes
   - Cancel: Discard changes and return to registry
### Form Validation
- **Required Fields**: All fields must be completed
- **Name Validation**: Proper name format for students and teachers
- **Date Validation**: Must be valid date and not in the future
- **Description Length**: Limited to 1000 characters
## File Structure