package com.example.repository;

import com.example.model.TouristCard;
import java.util.HashMap;
import java.util.Map;

/**
 * Concrete implementation of ITouristCardRepository.
 */
public class TouristCardRepositoryImpl implements ITouristCardRepository {
    private Map<String, TouristCard> cardMap = new HashMap<>();

    public TouristCardRepositoryImpl() {
        // Prepopulate with some data for simulation
        cardMap.put("T001", new TouristCard("C001", "T001"));
        cardMap.put("T002", new TouristCard("C002", "T002"));
    }

    @Override
    public TouristCard findByTouristId(String touristId) {
        return cardMap.get(touristId);
    }
}