package dataaccess;

import domain.Teaching;
import dtos.SmosTeachingData;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation of ITeachingRepository.
 * Handles data access for Teaching entities, interacting with SmosServerProxy.
 */
public class TeachingRepository implements ITeachingRepository {
    // - smosServerProxy : SmosServerProxy
    private SmosServerProxy smosServerProxy;
    private boolean simulateSmosConnectionFailure = false; // Flag for simulation

    /**
     * Constructor for TeachingRepository.
     * Initializes the SmosServerProxy dependency.
     */
    public TeachingRepository() {
        this.smosServerProxy = new SmosServerProxy();
        System.out.println("TeachingRepository: Initialized with SmosServerProxy.");
    }

    /**
     * Constructor for TeachingRepository allowing simulation of SMOS connection failure.
     *
     * @param simulateSmosConnectionFailure If true, fetchTeachingsFromSmos will simulate a failure.
     */
    public TeachingRepository(boolean simulateSmosConnectionFailure) {
        this(); // Call default constructor
        this.simulateSmosConnectionFailure = simulateSmosConnectionFailure;
        System.out.println("TeachingRepository: Simulating SMOS connection failure set to " + simulateSmosConnectionFailure);
    }

    /**
     * Retrieves all teaching entities by fetching data from SMOS and converting it.
     * Implements the error handling logic from the sequence diagram for SMOS connection.
     *
     * @return A list of Teaching domain entities. Returns null if the SMOS connection is interrupted.
     */
    @Override
    public List<Teaching> findAll() {
        System.out.println("TeachingRepository: Finding all teachings.");
        List<SmosTeachingData> teachingDataList = null;

        // alt Successful SMOS connection
        if (!simulateSmosConnectionFailure) {
            // Repo -> Smos : fetchTeachingsFromSmos()
            System.out.println("TeachingRepository: Calling SmosServerProxy to fetch teachings from SMOS.");
            teachingDataList = smosServerProxy.fetchTeachingsFromSmos();
            System.out.println("TeachingRepository: Received " + (teachingDataList != null ? teachingDataList.size() : 0) + " SmosTeachingData entries.");
        }
        // else Connection to the SMOS server IS interrupted
        else {
            // Repo ->x Smos : fetchTeachingsFromSmos() (simulated failure)
            System.err.println("TeachingRepository: SIMULATING SMOS CONNECTION INTERRUPTED!");
            // note right of Repo: Handle connection error.
            System.err.println("TeachingRepository: Handling connection error, returning null to service.");
            // Repo --> Service : error
            return null; // Indicate error to the service layer
        }

        if (teachingDataList == null || teachingDataList.isEmpty()) {
            System.out.println("TeachingRepository: No SMOS teaching data retrieved or received empty list.");
            return new ArrayList<>(); // Return empty list if no data, but no connection error
        }

        // Repo -> Repo : convertToTeachingEntities(teachingDataList)
        System.out.println("TeachingRepository: Converting SmosTeachingData to Teaching entities.");
        List<Teaching> teachingEntities = convertToTeachingEntities(teachingDataList);
        System.out.println("TeachingRepository: Converted to " + teachingEntities.size() + " Teaching entities.");

        // Repo --> Service : teachingEntities : List<Teaching>
        return teachingEntities;
    }

    /**
     * Converts a list of SmosTeachingData objects to a list of Teaching domain entities.
     *
     * @param smosTeachingDataList A list of SmosTeachingData objects.
     * @return A list of Teaching domain entities.
     */
    private List<Teaching> convertToTeachingEntities(List<SmosTeachingData> smosTeachingDataList) {
        System.out.println("TeachingRepository: Executing convertToTeachingEntities...");
        List<Teaching> teachings = new ArrayList<>();
        if (smosTeachingDataList != null) {
            for (SmosTeachingData smosData : smosTeachingDataList) {
                // Mapping SmosTeachingData to Teaching
                // Teaching: id, name, description, teacherName
                // SmosTeachingData: smosId, courseCode, courseTitle, lecturerName, details
                teachings.add(new Teaching(
                        smosData.smosId,      // Assuming smosId maps to Teaching's id
                        smosData.courseTitle, // Assuming courseTitle maps to Teaching's name
                        smosData.details,     // Assuming details maps to Teaching's description
                        smosData.lecturerName // Assuming lecturerName maps to Teaching's teacherName
                ));
            }
        }
        System.out.println("TeachingRepository: Converted " + (smosTeachingDataList != null ? smosTeachingDataList.size() : 0) + " SMOS data entries to " + teachings.size() + " Teaching entities.");
        return teachings;
    }

    // Setter for SmosServerProxy, primarily for testing purposes or dynamic configuration
    public void setSmosServerProxy(SmosServerProxy smosServerProxy) {
        this.smosServerProxy = smosServerProxy;
    }
}