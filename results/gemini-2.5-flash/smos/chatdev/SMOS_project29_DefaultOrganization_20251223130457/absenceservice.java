'''
Service class for managing absence records and triggering notifications.
This class acts as the interface between the GUI and the underlying data storage
and notification mechanisms for absence management.
'''
package com.chatdev.absencetracker.service;
import com.chatdev.absencetracker.model.AbsenceEntry;
import com.chatdev.absencetracker.model.Student;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
public class AbsenceService {
    // In-memory storage for absence records, simulating a database.
    // Key: LocalDate, Value: List of AbsenceEntry for that date.
    // Using ConcurrentHashMap for thread-safety, although this single-threaded GUI example doesn't strictly need it.
    private final Map<LocalDate, List<AbsenceEntry>> absenceRecords = new ConcurrentHashMap<>();
    private final StudentService studentService;
    private final NotificationService notificationService;
    '''
    Constructs an AbsenceService with dependencies on StudentService and NotificationService.
    @param studentService The service to retrieve student information.
    @param notificationService The service to send notifications to parents.
    '''
    public AbsenceService(StudentService studentService, NotificationService notificationService) {
        this.studentService = studentService;
        this.notificationService = notificationService;
    }
    '''
    Saves or updates a single absence/delay entry.
    If an entry for the same student and date already exists, it updates that entry.
    If the student is marked absent or delayed, a notification is sent to the parent.
    @param entry The AbsenceEntry to save.
    @throws IllegalArgumentException If the student associated with the entry is not found,
                                      which would prevent notification sending.
    '''
    private void saveSingleAbsenceEntry(AbsenceEntry entry) throws IllegalArgumentException {
        // First, verify student existence before attempting to save or notify.
        // If the student doesn't exist, this absence entry is invalid and should not be stored.
        Optional<Student> studentOpt = studentService.getStudentById(entry.getStudentId());
        if (studentOpt.isEmpty()) {
            throw new IllegalArgumentException("Student with ID " + entry.getStudentId() + " not found. Cannot save absence entry or send notification.");
        }
        Student student = studentOpt.get(); // We now have a valid student object.
        // Retrieve the list of entries for the given date, or create a new one.
        List<AbsenceEntry> entriesForDate = absenceRecords.computeIfAbsent(entry.getDate(), k -> new ArrayList<>());
        // Check if an entry for this student and date already exists
        Optional<AbsenceEntry> existingEntry = entriesForDate.stream()
                .filter(e -> e.getStudentId().equals(entry.getStudentId()))
                .findFirst();
        if (existingEntry.isPresent()) {
            // Update existing entry directly within the list
            AbsenceEntry oldEntry = existingEntry.get();
            oldEntry.setAbsent(entry.isAbsent());
            oldEntry.setDelayed(entry.isDelayed());
            System.out.println("Updated absence/delay entry for student " + entry.getStudentId() + " on " + entry.getDate());
        } else {
            // Add new entry to the list
            entriesForDate.add(entry);
            System.out.println("Added new absence/delay entry for student " + entry.getStudentId() + " on " + entry.getDate());
        }
        // Send notification if student is absent or delayed.
        // This is done after saving to ensure the record is persistent,
        // and only if the student exists (checked at the beginning of the method).
        if (entry.isAbsent() || entry.isDelayed()) {
            notificationService.sendNotification(student, entry.getDate(), entry.isAbsent(), entry.isDelayed());
        }
    }
    '''
    Processes a list of absence entries for a specific date, saving them individually
    and sending notifications for absent/delayed students.
    This method effectively replaces the attendance records for the given date with the provided list.
    @param date The date for which absences are being recorded.
    @param entriesToProcess A list of AbsenceEntry objects, representing the *current* state of
                           absent/delayed students for the selected date. This list should only
                           include students who are actually absent or delayed.
    @return A list of successfully saved absence/delay entries that caused a notification to be sent.
    '''
    public List<AbsenceEntry> processAbsenceEntries(LocalDate date, List<AbsenceEntry> entriesToProcess) {
        List<AbsenceEntry> savedAndNotified = new ArrayList<>();
        // Remove all previous entries for this date. We assume the GUI sends a fresh set of states.
        // This approach effectively means the submitted list is the 'source of truth' for the date.
        // The list re-population will happen when saveSingleAbsenceEntry is called for each entry.
        absenceRecords.remove(date); // Removes the entire list if present
        for (AbsenceEntry entry : entriesToProcess) {
            // Ensure the entry's date matches the processing date provided.
            if (!entry.getDate().equals(date)) {
                System.err.printf("Warning: Attempted to process an entry for a different date (%s) in batch for date (%s). Skipping entry for student %s.%n",
                        entry.getDate(), date, entry.getStudentId());
                continue;
            }
            try {
                // Delegate to saveSingleAbsenceEntry, which handles updating/adding and notification logic.
                saveSingleAbsenceEntry(entry);
                if (entry.isAbsent() || entry.isDelayed()) {
                    savedAndNotified.add(entry);
                }
            } catch (IllegalArgumentException e) {
                // This catch block will now specifically indicate that a student was not found
                // and thus the absence entry was not saved, which is a correct behavior.
                System.err.println("Error processing entry for student " + entry.getStudentId() + ": " + e.getMessage());
            }
        }
        return savedAndNotified;
    }
    '''
    Retrieves existing absence (and delay) entries for a specific date.
    If no entries exist for the date, an empty list is returned.
    @param date The date for which to retrieve entries.
    @return An unmodifiable list of AbsenceEntry objects for the given date.
    '''
    public List<AbsenceEntry> getAbsenceEntriesForDate(LocalDate date) {
        return Collections.unmodifiableList(absenceRecords.getOrDefault(date, new ArrayList<>()));
    }
    '''
    Simulates a connection interruption to the SMOS (School Management Operating System) server.
    In a real system, this would involve handling exceptions from network calls,
    database failures, or external API timeouts. For this example, it prints an error message.
    '''
    public void simulateSMOSServerInterruption() {
        // In a production environment, this would involve throwing a specific exception
        // that indicates a a server communication error or timeout.
        System.err.println("SIMULATING SMOS SERVER INTERRUPTION: Data might not be fully sent/processed.");
        // We could also throw a custom runtime exception here, e.g., throw new ServerConnectionException("SMOS server unavailable");
    }
}