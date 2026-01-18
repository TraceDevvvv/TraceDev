package com.example.controller;

import com.example.model.CulturalHeritageDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for searching cultural heritage.
 * Added to satisfy requirement for Flow of Events #1.
 */
public class SearchCulturalHeritageController {
    
    /**
     * Gets list of cultural heritage items.
     * @return list of CulturalHeritageDTO objects
     */
    public List<CulturalHeritageDTO> getList() {
        // In real implementation, this would query the database
        // For demonstration, returning a sample list
        
        List<CulturalHeritageDTO> list = new ArrayList<>();
        
        // Sample data
        list.add(new CulturalHeritageDTO("1", "Colosseum", 
                "Ancient Roman amphitheater", "Rome, Italy", "Roman Empire"));
        list.add(new CulturalHeritageDTO("2", "Great Wall of China", 
                "Series of fortifications", "Northern China", "Various dynasties"));
        list.add(new CulturalHeritageDTO("3", "Machu Picchu", 
                "15th-century Inca citadel", "Peru", "Inca Empire"));
        
        return list;
    }
}