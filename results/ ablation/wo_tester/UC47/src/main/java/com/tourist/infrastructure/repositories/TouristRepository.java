package com.tourist.infrastructure.repositories;

import com.tourist.app.interfaces.ITouristRepository;
import com.tourist.domain.Tourist;
import java.util.List;

/**
 * Repository implementation for Tourist.
 */
public class TouristRepository implements ITouristRepository {
    private DatabaseContext dbContext;

    /**
     * Constructor.
     * @param context the database context
     */
    public TouristRepository(DatabaseContext context) {
        this.dbContext = context;
    }

    @Override
    public Tourist GetById(String id) {
        // REQ-Q-001: response time constraint.
        long startTime = System.currentTimeMillis();

        List<Tourist> tourists = dbContext.getTourists();
        for (Tourist t : tourists) {
            if (t.getTouristId().equals(id)) {
                long endTime = System.currentTimeMillis();
                if ((endTime - startTime) > 5000) {
                    System.err.println("Warning: TouristRepository.GetById took more than 5 seconds.");
                }
                return t;
            }
        }

        long endTime = System.currentTimeMillis();
        if ((endTime - startTime) > 5000) {
            System.err.println("Warning: TouristRepository.GetById took more than 5 seconds.");
        }
        return null;
    }

    @Override
    public void Update(Tourist entity) {
        // REQ-Q-001: response time constraint.
        long startTime = System.currentTimeMillis();

        // In a real implementation, the entity would be updated in the database.
        // For simplicity, we assume the entity is already in the list and gets updated automatically.
        // We call SaveChanges to simulate persisting changes.
        dbContext.SaveChanges();

        long endTime = System.currentTimeMillis();
        if ((endTime - startTime) > 5000) {
            System.err.println("Warning: TouristRepository.Update took more than 5 seconds.");
        }
    }
}