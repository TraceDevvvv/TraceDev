package com.example.repositories;

import com.example.entities.Student;
import com.example.data.DataContext;
import java.util.List;

/**
 * Concrete implementation of IStudentRepository.
 * Corresponds to StudentRepository in the class diagram.
 */
public class StudentRepository implements IStudentRepository {
    private DataContext dataContext;

    public StudentRepository(DataContext dataContext) {
        this.dataContext = dataContext;
    }

    public DataContext getDataContext() {
        return dataContext;
    }

    @Override
    public List<Student> findByIds(List<Integer> ids) {
        // Delegates to DataContext to fetch student data.
        return dataContext.getStudents(ids);
    }
}