package etour.ports;

import etour.domain.SearchPreferences;

/**
 * Repository port for SearchPreferences persistence.
 */
public interface SearchPreferencesRepository {
    SearchPreferences findByTouristId(String touristId);
    SearchPreferences save(SearchPreferences preferences);
}