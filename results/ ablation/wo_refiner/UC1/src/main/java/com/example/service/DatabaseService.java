package com.example.service;

/**
 * Represents the database or ETOUR server in the sequence diagram.
 */
public class DatabaseService {
    public void queryCulturalHeritage(int culturalHeritageId) {
        System.out.println("DatabaseService: Querying cultural heritage with ID " + culturalHeritageId);
    }

    public CulturalHeritageData getCulturalHeritageData(int culturalHeritageId) {
        System.out.println("DatabaseService: Returning cultural heritage data for ID " + culturalHeritageId);
        return new CulturalHeritageData(culturalHeritageId, "Sample Cultural Heritage", "Sample");
    }
    
    public class CulturalHeritageData {
        private int id;
        private String name;
        private String description;
        
        public CulturalHeritageData(int id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }
    }
}