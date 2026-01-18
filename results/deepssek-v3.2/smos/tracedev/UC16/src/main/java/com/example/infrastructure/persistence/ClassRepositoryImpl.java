package com.example.infrastructure.persistence;

import com.example.domain.model.Class;
import com.example.domain.repository.ClassRepository;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ClassRepository.
 * Note: DataSource is not fully implemented; simplified for demonstration.
 */
public class ClassRepositoryImpl implements ClassRepository {
    private DataSource dataSource;

    public ClassRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Class findById(String id) {
        // Simplified: in real implementation, query database using dataSource
        // Returning a dummy Class for demonstration
        return new Class(id, "Sample Class", false);
    }

    @Override
    public void delete(Class classEntity) {
        // Simplified: actual deletion logic would use dataSource
        System.out.println("Deleting class with id: " + classEntity.getId());
    }

    @Override
    public List<Class> findAll() {
        // Simplified: return a dummy list
        List<Class> classes = new ArrayList<>();
        classes.add(new Class("1", "Class A", false));
        classes.add(new Class("2", "Class B", true));
        return classes;
    }
}