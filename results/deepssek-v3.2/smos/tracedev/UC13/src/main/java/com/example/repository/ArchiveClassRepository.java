package com.example.repository;

import com.example.model.Class;
import com.example.exception.ConnectionException;
import com.example.archive.ArchiveConnection;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * Concrete repository that retrieves classes from the archive.
 */
public class ArchiveClassRepository implements ClassRepository {
    private ArchiveConnection archiveConnection;

    public ArchiveClassRepository(ArchiveConnection archiveConnection) {
        this.archiveConnection = archiveConnection;
    }

    /**
     * Finds classes by academic year from the archive.
     * @param academicYear the academic year to search for
     * @return list of classes for the given academic year
     * @throws ConnectionException if connection to archive fails
     */
    @Override
    public List<Class> findByAcademicYear(String academicYear) throws ConnectionException {
        // Connect to archive
        boolean connected = archiveConnection.connect();
        if (!connected) {
            // Connection failed - throw ConnectionException (REQ-EC2)
            archiveConnection.connectionFailed();
            throw new ConnectionException("Connection to SMOS server interrupted.", 500);
        }

        // Prepare search criteria
        Map<String, String> criteria = new HashMap<>();
        criteria.put("academicYear", academicYear);

        // Search archive for classes
        List<Class> classes = archiveConnection.searchClasses(criteria);

        // Disconnect after operation
        archiveConnection.disconnect();

        return classes;
    }

    /**
     * Returns connection established status.
     * @return true if connection is established
     */
    public boolean connectionEstablished() {
        return archiveConnection.isConnected();
    }

    /**
     * Returns a list of Class objects from the archive.
     * @param academicYear the academic year
     * @return list of Class objects
     * @throws ConnectionException if connection fails
     */
    public List<Class> listOfClassObjects(String academicYear) throws ConnectionException {
        return findByAcademicYear(academicYear);
    }

    public ArchiveConnection getArchiveConnection() {
        return archiveConnection;
    }

    public void setArchiveConnection(ArchiveConnection archiveConnection) {
        this.archiveConnection = archiveConnection;
    }
}