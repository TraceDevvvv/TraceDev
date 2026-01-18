package com.example.school.infrastructure;

import com.example.school.domain.Class;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Concrete implementation of IClassRepository using a DatabaseContext.
 */
public class ClassRepository implements IClassRepository {
    private DatabaseContext dbContext;

    /**
     * Constructs a ClassRepository with a given DatabaseContext.
     * @param dbContext The database context to use for persistence.
     */
    public ClassRepository(DatabaseContext dbContext) {
        this.dbContext = dbContext;
    }

    /**
     * Finds all classes associated with a specific academic year.
     * Simulates a database query.
     * @param yearId The ID of the academic year.
     * @return A list of Class objects.
     */
    @Override
    public List<Class> findClassesByAcademicYear(String yearId) {
        // Simulate SQL query: SELECT * FROM Classes WHERE academicYear = yearId
        String sql = "SELECT * FROM Classes WHERE academicYearId = '" + yearId + "'";
        dbContext.executeQuery(sql); // Log the query

        return dbContext.classesTable.stream()
                .filter(c -> yearId.equals(c.get("academicYearId")))
                .map(this::mapToClass)
                .collect(Collectors.toList());
    }

    /**
     * Helper method to map a database row (Map<String, Object>) to a Class object.
     * @param row The map representing a database row.
     * @return A Class object.
     */
    private Class mapToClass(Map<String, Object> row) {
        return new Class(
            (String) row.get("id"),
            (String) row.get("name"),
            (String) row.get("academicYearId")
        );
    }
}