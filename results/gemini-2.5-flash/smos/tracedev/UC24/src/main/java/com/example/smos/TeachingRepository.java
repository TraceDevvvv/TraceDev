package com.example.smos;

import java.util.Map;

/**
 * Implementation of ITeachingRepository using SMOSDatabase.
 * Handles the mapping from raw database records to domain entities.
 */
public class TeachingRepository implements ITeachingRepository {
    // Dependency on SMOSDatabase
    private SMOSDatabase smosDatabase;

    /**
     * Constructs a TeachingRepository with a specific SMOSDatabase instance.
     * @param smosDatabase The database access object.
     */
    public TeachingRepository(SMOSDatabase smosDatabase) {
        this.smosDatabase = smosDatabase;
    }

    /**
     * Finds a Teaching entity by its ID by fetching data from the SMOSDatabase.
     *
     * @param teachingId The ID of the teaching to find.
     * @return A Teaching object if found, otherwise null.
     * @throws ConnectionException Propagates the exception from SMOSDatabase if connection fails.
     */
    @Override
    public Teaching findById(String teachingId) {
        System.out.println("TeachingRepository: Attempting to find teaching with ID: " + teachingId);
        // Call to SMOSDatabase as per sequence diagram
        Map<String, Object> teachingRecord = smosDatabase.fetchTeachingRecord(teachingId);

        if (teachingRecord == null) {
            return null; // Teaching not found in database
        }

        // Map the database record (Map<String, Object>) to a Teaching entity
        // We'll assume teacherName from DB is needed, but the Teaching entity stores teacherId.
        // For simplicity, we'll use a dummy mapping for teacherName to teacherId here.
        // In a real system, this might involve another lookup or a more complex DTO.
        // For this example, we'll extract teacherId as specified in Teaching class.
        String teacherIdFromDb = (String) teachingRecord.get("teacherId");
        if (teacherIdFromDb == null && teachingRecord.containsKey("teacherName")) {
             // Assumption: if teacherId is not directly in the map, perhaps teacherName can be used as a simple ID for this example
             teacherIdFromDb = (String) teachingRecord.get("teacherName");
        } else if (teacherIdFromDb == null) {
            teacherIdFromDb = "UNKNOWN_TEACHER"; // Default if neither is found
        }

        return new Teaching(
                (String) teachingRecord.get("id"),
                (String) teachingRecord.get("name"),
                (String) teachingRecord.get("courseCode"),
                teacherIdFromDb, // Use teacherId from record
                (String) teachingRecord.get("semester")
        );
    }
}