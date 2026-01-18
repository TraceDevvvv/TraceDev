package com.tourmanagement.repository;

import com.tourmanagement.domain.Tourist;
import java.util.List;

public interface TouristRepository {
    Tourist findById(String touristId);
    void delete(String touristId);
    List<Tourist> findAll();
}