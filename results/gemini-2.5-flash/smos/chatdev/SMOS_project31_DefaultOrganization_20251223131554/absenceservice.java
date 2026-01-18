/*
 * DOCSTRING: Provides the core business logic for managing absences.
 * This service class handles data retrieval, storage (simulated),
 * interaction with the SMOS server, and email notifications.
 */
package service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Absence;
import model.Parent;
import model.Student;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
public class AbsenceService {
    // Simulates a database for absences, keyed by date
    private final Map<LocalDate, ObservableList<Absence>> absencesByDate;
    // Simulates a student and parent registry
    private final Map<Integer, Student> allStudents;
    private final Map<Integer, Parent> allParents;
    private final SMOSServerConnector smosServerConnector;
    private final EmailSender emailSender;
    public AbsenceService() {
        this.absencesByDate = new HashMap<>();
        this.allStudents = new HashMap<>();
        this.allParents = new HashMap<>();
        this.smosServerConnector = new SMOSServerConnector();
        this.emailSender = new EmailSender();
        initializeDummyData();
    }
    /**
     * Initializes dummy data for students, parents, and some absences.
     * This simulates a pre-existing system state.
     */
    private void initializeDummyData() {
        // Dummy Parents
        Parent parent1 = new Parent(101, "Alice Smith", "alice.smith@example.com");
        Parent parent2 = new Parent(102, "Bob Johnson", "bob.j@example.com");
        Parent parent3 = new Parent(103, "Charlie Davis", "charlie.d@example.com");
        allParents.put(parent1.getId(), parent1);
        allParents.put(parent2.getId(), parent2);
        allParents.put(parent3.getId(), parent3);
        // Dummy Students
        Student student1 = new Student(1, "John Doe", parent1.getId());
        Student student2 = new Student(2, "Jane Smith", parent1.getId());
        Student student3 = new Student(3, "Mike Johnson", parent2.getId());
        Student student4 = new Student(4, "Emily Davis", parent3.getId());
        allStudents.put(student1.getId(), student1);
        allStudents.put(student2.getId(), student2);
        allStudents.put(student3.getId(), student3);
        allStudents.put(student4.getId(), student4);
        // Dummy Absences for different dates
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        LocalDate tomorrow = today.plusDays(1);
        absencesByDate.put(yesterday, FXCollections.observableArrayList(
                new Absence(UUID.randomUUID(), student1.getId(), student1.getName(), yesterday, "Sick leave", parent1.getEmail(), Absence.Status.EXISTING),
                new Absence(UUID.randomUUID(), student3.getId(), student3.getName(), yesterday, "Family emergency", parent2.getEmail(), Absence.Status.EXISTING)
        ));
        absencesByDate.put(today, FXCollections.observableArrayList(
                new Absence(UUID.randomUUID(), student2.getId(), student2.getName(), today, "Doctor's appointment", parent1.getEmail(), Absence.Status.EXISTING)
        ));
        System.out.println("AbsenceService: Dummy data initialized.");
    }
    /**
     * Retrieves all students registered in the system.
     * @return A collection of all Student objects.
     */
    public Collection<Student> getAllStudents() {
        return allStudents.values();
    }
    /**
     * Retrieves a student by their ID.
     * @param studentId The ID of the student.
     * @return The Student object if found, otherwise null.
     */
    public Student getStudentById(int studentId) {
        return allStudents.get(studentId);
    }
    /**
     * Retrieves a parent by their ID.
     * @param parentId The ID of the parent.
     * @return The Parent object if found, otherwise null.
     */
    public Parent getParentById(int parentId) {
        return allParents.get(parentId);
    }
    /**
     * Fetches existing absences for a given date.
     * Returns an ObservableList that can be used directly by TableView.
     *
     * @param date The date for which to retrieve absences.
     * @return An ObservableList of Absence objects for the specified date.
     */
    public ObservableList<Absence> getAbsencesForDate(LocalDate date) {
        ObservableList<Absence> absences = absencesByDate.getOrDefault(date, FXCollections.observableArrayList());
        // Return a new ObservableList containing copies to prevent direct modification of the service's internal state
        // and to allow for tracking of changes (NEW, DELETED) independently in the UI.
        return absences.stream()
                .map(abs -> new Absence(abs.getId(), abs.getStudentId(), abs.getStudentName(), abs.getDate(), abs.getReason(), abs.getOriginalParentEmail(), Absence.Status.EXISTING))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    /**
     * Saves changes to absences (additions, deletions) for a specific date.
     * This method interacts with the simulated SMOS server and sends email notifications.
     *
     * @param date The date for which absences are being saved.
     * @param modifiedAbsences A list of absences that were existing and are being kept (potentially with minor modifications, though this example GUI doesn't allow in-place edits yet).
     * @param addedAbsences A list of new absences to be added.
     * @param deletedAbsences A list of absences to be deleted.
     * @throws SMOSServerConnector.SMOSServerConnectionException If connection to SMOS server fails.
     * @throws EmailSender.EmailSendingException If an email notification fails to send.
     */
    public void saveAbsences(LocalDate date,
                             List<Absence> modifiedAbsences,
                             List<Absence> addedAbsences,
                             List<Absence> deletedAbsences)
            throws SMOSServerConnector.SMOSServerConnectionException, EmailSender.EmailSendingException {
        // 1. Prepare data for SMOS server (all changes)
        List<Absence> allAbsencesToProcessBySMOS = new ArrayList<>();
        // Add new absences - SMOS needs to know about these additions
        addedAbsences.forEach(abs -> {
            Absence copy = new Absence(abs.getId(), abs.getStudentId(), abs.getStudentName(), abs.getDate(), abs.getReason(), abs.getOriginalParentEmail(), Absence.Status.NEW);
            allAbsencesToProcessBySMOS.add(copy); // Explicitly mark as NEW for SMOS
        });
        // Add deleted absences - SMOS needs to know about these deletions
        deletedAbsences.forEach(abs -> {
            Absence copy = new Absence(abs.getId(), abs.getStudentId(), abs.getStudentName(), abs.getDate(), abs.getReason(), abs.getOriginalParentEmail(), Absence.Status.DELETED);
            allAbsencesToProcessBySMOS.add(copy); // Explicitly mark as DELETED for SMOS
        });
        // 2. Simulate sending data to SMOS server
        if (!allAbsencesToProcessBySMOS.isEmpty()) {
            smosServerConnector.simulateSaveAbsences(allAbsencesToProcessBySMOS);
        }
        // 3. Update internal "database" (absencesByDate map) after successful SMOS server update
        ObservableList<Absence> currentAbsences = absencesByDate.computeIfAbsent(date, k -> FXCollections.observableArrayList());
        currentAbsences.clear(); // Clear existing entries for this date
        // Add back existing (not deleted) and newly added absences
        modifiedAbsences.forEach(abs -> {
            Absence copy = new Absence(abs.getId(), abs.getStudentId(), abs.getStudentName(), abs.getDate(), abs.getReason(), abs.getOriginalParentEmail(), Absence.Status.EXISTING);
            currentAbsences.add(copy); // Ensure status is EXISTING after saving
        });
        addedAbsences.forEach(abs -> {
            Absence copy = new Absence(abs.getId(), abs.getStudentId(), abs.getStudentName(), abs.getDate(), abs.getReason(), abs.getOriginalParentEmail(), Absence.Status.EXISTING);
            currentAbsences.add(copy); // Ensure status is EXISTING after saving
        });
        // 4. Send email notifications
        for (Absence absence : addedAbsences) {
            emailSender.sendAbsenceNotification(absence.getOriginalParentEmail(), absence.getStudentName(), absence.getDate(), absence.getReason(), "added");
        }
        for (Absence absence : deletedAbsences) {
            emailSender.sendAbsenceNotification(absence.getOriginalParentEmail(), absence.getStudentName(), absence.getDate(), absence.getReason(), "cancelled");
        }
        System.out.println("AbsenceService: Changes for " + date + " successfully saved and notifications sent.");
    }
}