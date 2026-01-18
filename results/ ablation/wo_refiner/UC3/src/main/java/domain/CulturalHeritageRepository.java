package domain;

/**
 * Repository interface for CulturalHeritage persistence.
 * Part of the Domain layer (interface defined here).
 */
public interface CulturalHeritageRepository {
    CulturalHeritage findById(String id);
    void save(CulturalHeritage culturalHeritage);
}