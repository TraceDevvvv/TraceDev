package com.example.application;

import com.example.domain.Tourist;
import java.util.List;

/**
 * Interface for Tourist Repository abstraction.
 */
public interface ITouristRepository {
    Tourist getById(int id);
    List<Tourist> getAll();
    void delete(Tourist tourist);
    void update(Tourist tourist);
}