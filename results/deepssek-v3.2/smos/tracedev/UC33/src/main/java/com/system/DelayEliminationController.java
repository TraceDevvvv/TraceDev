package com.system;

import java.util.Date;
import java.util.List;

/**
 * Main controller for the delay elimination use case.
 * Handles case selection, date handling, late input removal, and saving.
 */
public class DelayEliminationController {
    private ArchiveRepository archiveRepository;
    private StudentRepository studentRepository;
    private SMOSServer smosServer;
    private Date currentDate;
    private String currentCase;
    private ScreenState screenState;
    private Student currentStudent; // Tracks student whose late input is being removed.

    public DelayEliminationController(ArchiveRepository archiveRepository,
                                      StudentRepository studentRepository,
                                      SMOSServer smosServer) {
        this.archiveRepository = archiveRepository;
        this.studentRepository = studentRepository;
        this.smosServer = smosServer;
        this.screenState = new ScreenState();
    }

    /**
     * Entry condition: a case must be selected before operations.
     * @param caseName the name of the case.
     */
    public void selectCase(String caseName) {
        this.currentCase = caseName;
        System.out.println("Controller: Case selected: " + caseName);
    }

    /**
     * Handles date selection by the administrator.
     * @param selectedDate the date selected.
     * @return the updated screen state.
     */
    public ScreenState handleDateSelection(Date selectedDate) {
        this.currentDate = selectedDate; // Store date for later use (m4)
        screenState.setSelectedDate(selectedDate);
        List<Student> studentList = studentRepository.findAllByDate(selectedDate);
        screenState.update(studentList);
        return screenState;
    }

    /**
     * Removes the late input flag for a specific student.
     * @param studentId the student ID.
     */
    public void removeLateInput(String studentId) {
        Student student = studentRepository.findById(studentId);
        if (student != null) {
            student.setHasLateEntry(false);
            currentStudent = student;
            System.out.println("Controller: Late input removed for student " + studentId);
            ArchiveEntry lateEntry = archiveRepository.findLateEntry(studentId, currentDate);
            if (lateEntry != null) {
                archiveRepository.deleteLateEntry(lateEntry);
                System.out.println("Controller: Late entry removed");
            } else {
                // m24: No late entry to delete
                System.out.println("Controller: No late entry to delete");
            }
        } else {
            System.out.println("Controller: Student not found: " + studentId);
        }
    }

    /**
     * Saves changes to student and archive, verifies SMOS connection.
     * @return true if save successful.
     * @throws SaveFailedException if SMOS server is not connected.
     */
    public boolean saveChanges() throws SaveFailedException {
        // Verify SMOS server connection as per exit condition.
        if (!verifyConnection()) {
            // m26: Connection to SMOS server lost
            // m29: SaveFailedException added exception handling
            throw new SaveFailedException("Connection to SMOS server lost. Save failed.");
        }

        // Save student change.
        if (currentStudent != null) {
            studentRepository.save(currentStudent);
        }

        // Delete late entry from archive.
        deleteLateEntryFromArchive(currentStudent.getStudentId(), currentDate);

        // Notify screen.
        screenState.notifyDelayRemoved(currentStudent.getStudentId());

        System.out.println("Controller: Delay removed and archive updated.");
        return true;
    }

    /**
     * Cancels the current operation as per exit condition.
     * Called when administrator interrupts the operation (m31, m32).
     */
    public void cancelOperation() {
        // m32: operation cancelled
        System.out.println("Controller: Operation cancelled by administrator.");
        // Reset state if needed.
        currentStudent = null;
    }

    /**
     * Deletes a late entry from the archive.
     * Private helper method.
     * @param studentId the student ID.
     * @param date the date.
     */
    private void deleteLateEntryFromArchive(String studentId, Date date) {
        ArchiveEntry entry = archiveRepository.findLateEntry(studentId, date);
        if (entry != null) {
            archiveRepository.deleteLateEntry(entry);
            System.out.println("Controller: Deleted late entry from archive for student " + studentId);
            // m18: return archiveEntry (implied)
        } else {
            System.out.println("Controller: No late entry found to delete for student " + studentId);
        }
    }

    /**
     * Verifies connection to SMOS server.
     * @return true if connected.
     */
    private boolean verifyConnection() {
        boolean connected = smosServer.isConnected();
        System.out.println("Controller: SMOS server connection verified: " + connected);
        return connected;
    }

    /**
     * Returns screen updated (m9).
     * This method is called after handleDateSelection to notify admin.
     * @return the updated screen state.
     */
    public ScreenState returnScreenUpdated() {
        // m9: return screen updated
        System.out.println("Controller: Returning screen updated");
        return screenState;
    }

    /**
     * Returns late input removed (m13).
     * Called after removeLateInput to notify admin.
     */
    public void returnLateInputRemoved() {
        // m13: late input removed
        System.out.println("Controller: Late input removed");
    }

    // Getters for testing.
    public Date getCurrentDate() {
        return currentDate;
    }

    public String getCurrentCase() {
        return currentCase;
    }

    public ScreenState getScreenState() {
        return screenState;
    }

    public Student getCurrentStudent() {
        return currentStudent;
    }

    public void setCurrentStudent(Student student) {
        this.currentStudent = student;
    }
}