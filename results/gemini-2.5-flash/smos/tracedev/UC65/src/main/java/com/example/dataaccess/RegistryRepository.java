package com.example.dataaccess;

import com.example.domain.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Concrete implementation of IRegistryRepository.
 * Interacts with SMOSAdapter to fetch raw data and transforms it into domain objects.
 */
public class RegistryRepository implements IRegistryRepository {
    private SMOSAdapter smosAdapter;

    /**
     * Constructs a RegistryRepository with a given SMOSAdapter.
     * @param smosAdapter The adapter to use for fetching data from SMOS.
     */
    public RegistryRepository(SMOSAdapter smosAdapter) {
        this.smosAdapter = smosAdapter;
    }

    /**
     * Finds a ClassRegistry by its class ID by fetching raw data from SMOS and parsing it.
     *
     * @param classId The ID of the class registry to find.
     * @return The ClassRegistry object.
     * @throws SMOSConnectionException if the SMOSAdapter encounters a connection error.
     */
    @Override
    public ClassRegistry findByClassId(String classId) {
        System.out.println("[RegistryRepository] Finding class registry by ID: " + classId);
        SMOSData smosData;
        try {
            smosData = smosAdapter.fetchRegistryData(classId);
        } catch (SMOSConnectionException e) {
            System.err.println("[RegistryRepository] Error fetching data from SMOS: " + e.getMessage());
            // Re-throw the specific exception to be handled by the controller
            throw e;
        }

        // --- Assumption: Parse SMOSData into ClassRegistry domain model.
        // In a real application, this parsing logic would be more robust and complex.
        // For now, we simulate with dummy data creation.
        System.out.println("[RegistryRepository] Processing SMOSData into ClassRegistry.");

        // Simulate parsing rawRegistryInfo
        String className = "Unknown Class";
        String academicYear = "N/A";
        if (smosData.getRawRegistryInfo().contains("Class:")) {
            className = smosData.getRawRegistryInfo().split("Class: ")[1].split(",")[0].trim();
        }
        if (smosData.getRawRegistryInfo().contains("Academic Year:")) {
            academicYear = smosData.getRawRegistryInfo().split("Academic Year: ")[1].trim();
        }

        // Simulate creating dummy students and their statuses
        Student studentA = new Student("s001", "Alice Smith");
        Student studentB = new Student("s002", "Bob Johnson");
        Student studentC = new Student("s003", "Charlie Brown");

        // Example status for a specific date (today)
        List<StudentStatus> day1Statuses = new ArrayList<>();

        StudentStatus statusA = new StudentStatus(studentA, true, false); // Present
        day1Statuses.add(statusA);

        StudentStatus statusB = new StudentStatus(studentB, true, true); // Delayed
        statusB.addJustification(new Justification("j001", "Medical appointment", "Approved"));
        statusB.addDisciplinaryNote(new DisciplinaryNote("d001", "Late to class (5 min)", "Mild"));
        day1Statuses.add(statusB);

        StudentStatus statusC = new StudentStatus(studentC, false, false); // Absent
        // No justification by default, as per SMOSData string
        day1Statuses.add(statusC);

        RegistryEntry entry1 = new RegistryEntry(new Date(), day1Statuses); // Current date

        // Example status for an older date
        List<StudentStatus> day2Statuses = new ArrayList<>();
        Student studentD = new Student("s004", "Diana Prince");
        day2Statuses.add(new StudentStatus(studentA, true, false));
        day2Statuses.add(new StudentStatus(studentD, false, false)); // Diana absent
        RegistryEntry entry2 = new RegistryEntry(new Date(System.currentTimeMillis() - 86400000L * 7), day2Statuses); // 7 days ago

        List<RegistryEntry> entries = new ArrayList<>();
        entries.add(entry1);
        entries.add(entry2);

        // Create the ClassRegistry domain object
        ClassRegistry classRegistry = new ClassRegistry(classId, className, academicYear, entries);
        System.out.println("[RegistryRepository] Successfully created ClassRegistry for " + classId);
        return classRegistry;
    }
}