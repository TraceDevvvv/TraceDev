package com.example.repository;

import com.example.model.Convention;
import com.example.enums.ConventionStatus;
import javax.sql.DataSource;

/**
 * Infrastructure adapter implementing ConventionRepository.
 */
public class ConventionRepositoryImpl implements ConventionRepository {
    private DataSource dataSource;
    
    public ConventionRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Override
    public Convention findById(String id) {
        // Simulate database lookup
        if (id == null || id.isEmpty()) {
            return null;
        }
        
        // Return a mock convention
        Convention convention = new Convention();
        convention.setId(id);
        convention.setName("Convention " + id);
        convention.setStatus(ConventionStatus.PENDING);
        
        return convention;
    }
    
    @Override
    public Convention save(Convention convention) {
        // In a real implementation, this would persist to database
        // For demonstration, just return the convention as if saved
        System.out.println("Saving convention: " + convention.getId());
        return convention;
    }
}