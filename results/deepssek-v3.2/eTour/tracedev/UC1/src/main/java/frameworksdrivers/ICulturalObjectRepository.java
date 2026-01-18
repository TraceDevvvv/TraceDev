package frameworksdrivers;

import enterprisebusinessrules.CulturalObject;

/**
 * Repository interface for cultural object persistence operations.
 */
public interface ICulturalObjectRepository {
    CulturalObject findById(String id);
    boolean delete(String id);
}