package com.example.infrastructure;

import com.example.domain.Class;
import com.example.infrastructure.exceptions.DataSourceException;
import java.util.Map;

/**
 * Implementation of ClassRepository.
 * Uses SMOSDataSource to fetch raw class data and converts it to a Class object.
 */
public class ClassRepositoryImpl implements ClassRepository {
    private SMOSDataSource smosDataSource;

    /**
     * Constructor for ClassRepositoryImpl.
     * @param smosDataSource The data source to fetch class data from.
     */
    public ClassRepositoryImpl(SMOSDataSource smosDataSource) {
        this.smosDataSource = smosDataSource;
    }

    /**
     * Finds a class by its ID.
     * @param id The ID of the class to find.
     * @return The Class object if found.
     * @throws DataSourceException if there is a connection error or data retrieval fails.
     */
    @Override
    public Class findById(String id) throws DataSourceException {
        try {
            Map<String, String> rawClassData = smosDataSource.fetchClassData(id);
            // Assuming the map contains keys: "id", "name", "address", "schoolYear"
            String name = rawClassData.get("name");
            String address = rawClassData.get("address");
            String schoolYear = rawClassData.get("schoolYear");
            return createClassEntity(id, name, address, schoolYear);
        } catch (Exception e) {
            throw new DataSourceException("Connection failed to SMOS Server");
        }
    }

    /**
     * Creates a Class entity.
     * @param id The class ID.
     * @param name The class name.
     * @param address The class address.
     * @param schoolYear The school year.
     * @return The created Class object.
     */
    public Class createClassEntity(String id, String name, String address, String schoolYear) {
        return new Class(id, name, address, schoolYear);
    }
}