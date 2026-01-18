package com.example.turista.data;

import com.example.turista.domain.Turista;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * Concrete implementation of ITuristaRepository.
 * This class is responsible for retrieving Turista data, primarily by interacting
 * with the ETOURServerAdapter.
 */
public class TuristaRepository implements ITuristaRepository {
    // - etourServerAdapter : ETOURServerAdapter
    private ETOURServerAdapter etourServerAdapter;

    /**
     * Constructor for TuristaRepository.
     * @param etourServerAdapter The adapter to communicate with the ETOUR server.
     */
    public TuristaRepository(ETOURServerAdapter etourServerAdapter) {
        this.etourServerAdapter = etourServerAdapter;
    }

    /**
     * Finds a Turista by its unique identifier.
     * This method interacts with the ETOURServerAdapter to get raw data
     * and then converts it into a Turista domain object.
     *
     * @param turistaId The ID of the turista.
     * @return The Turista object if found and successfully built.
     * @throws TuristaDataAccessException if there's a problem connecting to ETOUR server,
     *                                  if data is not found, or if there's an issue parsing the raw data.
     */
    @Override
    public Turista findById(String turistaId) throws TuristaDataAccessException {
        System.out.println("  Repository: findById called for Turista ID: " + turistaId);

        // First, check connection as per good practice, though fetchDataFromETOUR might also throw connection error.
        // The sequence diagram shows `ConnectionError` as an outcome during `fetchDataFromETOUR`.
        // Let's rely on `fetchDataFromETOUR` to directly throw connection error.

        Map<String, String> rawData;
        try {
            // Repository -> ETOURAdapter: fetchDataFromETOUR(turistaId : String)
            rawData = etourServerAdapter.fetchDataFromETOUR(turistaId);
        } catch (TuristaDataAccessException e) {
            // ETOURAdapter --x Repository: ConnectionError
            // Re-throw the exception from the adapter, encapsulating it as a data access problem.
            System.err.println("  Repository: Error fetching data from ETOUR server: " + e.getMessage());
            throw e;
        }

        if (rawData == null || rawData.isEmpty()) {
            // ETOURAdapter --x Repository: DataNotFound (handled by returning null/empty map in this impl)
            System.out.println("  Repository: No raw data found for Turista ID: " + turistaId);
            throw new TuristaDataAccessException("Turista data not found for ID: " + turistaId);
        }

        // Repository -> Repository: buildTuristaFromRawData(rawTuristaData)
        Turista turista = buildTuristaFromRawData(rawData);
        System.out.println("  Repository: Successfully built Turista object for ID: " + turistaId);
        return turista;
    }

    /**
     * Builds a Turista domain object from a map of raw data.
     * This method handles the parsing and conversion of string-based raw data into
     * the appropriate types for the Turista object.
     *
     * @param rawData A map where keys are field names and values are their string representations.
     * @return A new Turista object populated with data from the map.
     * @throws TuristaDataAccessException if required data is missing or in an invalid format.
     * note right of TuristaRepository::buildTuristaFromRawData: Added to satisfy requirement R8
     */
    public Turista buildTuristaFromRawData(Map<String, String> rawData) throws TuristaDataAccessException {
        System.out.println("  Repository: Building Turista from raw data...");
        // Basic validation for required fields
        if (rawData == null || !rawData.containsKey("id") || !rawData.containsKey("firstName") || !rawData.containsKey("lastName")) {
            throw new TuristaDataAccessException("Incomplete raw data provided for Turista.");
        }

        String id = rawData.get("id");
        String firstName = rawData.get("firstName");
        String lastName = rawData.get("lastName");
        String nationality = rawData.get("nationality");
        String contactEmail = rawData.get("contactEmail");
        String contactPhone = rawData.get("contactPhone");

        Date dob = null;
        String dobString = rawData.get("dob");
        if (dobString != null) {
            try {
                // Assumption: Date format is "YYYY-MM-DD"
                dob = new SimpleDateFormat("yyyy-MM-dd").parse(dobString);
            } catch (ParseException e) {
                System.err.println("  Repository: Warning: Could not parse DOB string '" + dobString + "'. Using null for DOB. " + e.getMessage());
                // In a real system, this might be a more severe error or default value.
            }
        }

        return new Turista(id, firstName, lastName, dob, nationality, contactEmail, contactPhone);
    }
}