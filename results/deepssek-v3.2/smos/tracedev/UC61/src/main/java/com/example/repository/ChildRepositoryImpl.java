package com.example.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.sql.DataSource;
import com.example.model.Child;

/**
 * Implementation of ChildRepository.
 * Simulates database access.
 */
public class ChildRepositoryImpl implements ChildRepository {
    // Simulating a database reference
    private DataSource dataSource;

    public ChildRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Child findChildByParentAndId(int parentId, int childId) {
        // Simulate database query
        // For demonstration, return a mock child if IDs match
        if (parentId > 0 && childId == 101) {
            return new Child(childId, "John Doe", parentId, new Date());
        }
        return null;
    }

    @Override
    public List<Child> findChildrenByParent(int parentId) {
        // Simulate database query returning multiple children
        List<Child> children = new ArrayList<>();
        if (parentId > 0) {
            children.add(new Child(101, "John Doe", parentId, new Date()));
            children.add(new Child(102, "Jane Smith", parentId, new Date()));
        }
        return children;
    }
}