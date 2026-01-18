package com.example.repository;

import com.example.model.StudentDelay;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of IDelayRepository.
 * Simulates database operations.
 */
public class DelayRepositoryImpl implements IDelayRepository {
    private List<StudentDelay> database = new ArrayList<>(); // Simulated data source
    
    public DelayRepositoryImpl() {
        // Placeholder initialization; actual data source would be injected
    }
    
    @Override
    public void save(StudentDelay delay) {
        database.add(delay);
        System.out.println("Saved delay for student: " + delay.getStudentName());
    }
    
    @Override
    public List<StudentDelay> findByDate(Date date) {
        return database.stream()
                .filter(d -> d.getDate().equals(date))
                .collect(Collectors.toList());
    }
}