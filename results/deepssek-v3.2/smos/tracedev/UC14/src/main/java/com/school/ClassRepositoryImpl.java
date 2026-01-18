package com.school;

/**
 * Implementation of ClassRepository.
 */
public class ClassRepositoryImpl implements ClassRepository {
    private ClassArchive dataSource;

    public ClassRepositoryImpl(ClassArchive archive) {
        this.dataSource = archive;
    }

    @Override
    public Class save(Class classObj) {
        // Check network availability as per sequence diagram.
        if (!dataSource.isConnectionAvailable()) {
            // We cannot return NETWORK_ERROR from this method due to signature.
            // Instead, we throw a custom exception; the controller will translate it.
            throw new RuntimeException("NETWORK_ERROR");
        }
        boolean success = dataSource.insertClassRecord(classObj);
        if (success) {
            return classObj;
        } else {
            throw new RuntimeException("PERSISTENCE_ERROR");
        }
    }
}