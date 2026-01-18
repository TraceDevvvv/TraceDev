package com.example.repositories;

import com.example.entities.Parent;
import com.example.data.DataContext;

/**
 * Concrete implementation of IParentRepository.
 * Corresponds to ParentRepository in the class diagram.
 */
public class ParentRepository implements IParentRepository {
    private DataContext dataContext;

    public ParentRepository(DataContext dataContext) {
        this.dataContext = dataContext;
    }

    public DataContext getDataContext() {
        return dataContext;
    }

    @Override
    public Parent findById(int id) {
        // Delegates to DataContext to fetch parent data.
        // In a real implementation, this would involve SQL or ORM queries.
        return dataContext.getParent(id);
    }

    @Override
    public void update(Parent parent) {
        // In this simplified implementation, we assume update is handled via UnitOfWork.
        // The actual persistence is deferred until commit.
        // This method might register the update in the UnitOfWork.
        // For simplicity, we just print a log.
        System.out.println("Parent update registered for parent ID: " + parent.getId());
        // Typically, we would call unitOfWork.registerUpdate(parent) here.
        // However, the sequence diagram shows the controller does that after calling update.
        // So we leave it to the controller to register.
    }
}