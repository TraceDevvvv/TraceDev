package com.example.school.repository;

import com.example.school.connector.SMOSServerConnector;
import com.example.school.model.ClassRegistryEntry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;

/**
 * Implementation of {@link StudentRepository} that fetches class registry data
 * from an archived source via the {@link SMOSServerConnector}.
 */
public class ArchiveStudentRepository implements StudentRepository {

    private final SMOSServerConnector smosServerConnector;

    /**
     * Constructs an ArchiveStudentRepository with a specific SMOSServerConnector.
     *
     * @param smosServerConnector The connector to the SMOS server.
     */
    public ArchiveStudentRepository(SMOSServerConnector smosServerConnector) {
        this.smosServerConnector = smosServerConnector;
    }

    /**
     * {@inheritDoc}
     *
     * This implementation interacts with the {@link SMOSServerConnector} to
     * retrieve raw data and maps it to {@link ClassRegistryEntry} objects.
     */
    @Override
    public List<ClassRegistryEntry> findClassRegistryEntries(String studentId) {
        List<ClassRegistryEntry> entries = new ArrayList<>();
        System.out.println("ArchiveStudentRepository: Attempting to find class registry entries for student " + studentId);

        // SD: Repository -> SMOS : isAvailable()
        if (!smosServerConnector.isAvailable()) {
            System.err.println("ArchiveStudentRepository: SMOS Server is not available. Cannot fetch data. (Trace: m14)");
            // SD: "SMOS Server Interrupted" (m14 implies this exit condition) -> Repository --> Service : Error: "SMOS Server Unavailable" (m15)
            // For simplicity, we'll return an empty list and let the service layer handle the "error" aspect
            // by checking if the list is empty or by throwing a custom exception in a more robust system.
            return entries; // This return of an empty list signifies "SMOS Server Unavailable" for m15.
        }

        // SD: Repository -> SMOS : fetchClassRegistryData(studentId)
        List<Map<String, Object>> rawDataList = smosServerConnector.fetchClassRegistryData(studentId);

        // SD: Repository -> Repository : map rawDataList to List<ClassRegistryEntry> (m10)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (Map<String, Object> rawData : rawDataList) {
            try {
                String id = (String) rawData.get("id");
                String sId = (String) rawData.get("studentId");
                Date date = dateFormat.parse((String) rawData.get("date"));
                int absences = (Integer) rawData.get("absences");
                String disciplinaryNotes = (String) rawData.get("disciplinaryNotes");
                int delays = (Integer) rawData.get("delays");
                String justification = (String) rawData.get("justification");

                ClassRegistryEntry entry = new ClassRegistryEntry(id, sId, date, absences, disciplinaryNotes, delays, justification);
                entries.add(entry);
                System.out.println("ArchiveStudentRepository: Mapped raw data to entry: " + entry.getId() + " (Trace: m10)");
            } catch (ParseException e) {
                System.err.println("ArchiveStudentRepository: Error parsing date for entry: " + rawData.get("id") + " - " + e.getMessage());
                // Continue to the next entry or handle error as appropriate
            } catch (Exception e) {
                System.err.println("ArchiveStudentRepository: Error mapping raw data to ClassRegistryEntry: " + e.getMessage() + " Data: " + rawData);
            }
        }

        System.out.println("ArchiveStudentRepository: Found " + entries.size() + " class registry entries.");
        return entries;
    }
}