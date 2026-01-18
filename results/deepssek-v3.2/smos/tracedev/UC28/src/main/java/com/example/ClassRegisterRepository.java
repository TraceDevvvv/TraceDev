package com.example;

/**
 * Repository implementation for ClassRegister.
 */
public class ClassRegisterRepository implements IClassRegisterRepository {
    private ISmosDataSource smosDataSource;

    public ClassRegisterRepository(ISmosDataSource smosDataSource) {
        this.smosDataSource = smosDataSource;
    }

    @Override
    public ClassRegister findRegisterById(String registerId) throws ConnectionException {
        // Fetch data from SMOS and convert to domain entity.
        ClassRegisterDTO dto = smosDataSource.fetchRegisterData(registerId);
        // Simplified conversion; in reality, map DTO to domain object.
        return new ClassRegister(dto.registerId, dto.className, null, null, null, null, null);
    }

    public ISmosDataSource getSmosDataSource() {
        return smosDataSource;
    }

    public void setSmosDataSource(ISmosDataSource smosDataSource) {
        this.smosDataSource = smosDataSource;
    }
}