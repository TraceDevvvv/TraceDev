package com.example.repository;

import com.example.model.StudentDelay;
import java.util.Date;
import java.util.List;

/**
 * Repository interface for delay persistence.
 */
public interface IDelayRepository {
    void save(StudentDelay delay);
    List<StudentDelay> findByDate(Date date);
}