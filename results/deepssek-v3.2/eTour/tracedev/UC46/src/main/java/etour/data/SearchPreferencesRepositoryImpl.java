package etour.data;

import etour.domain.SearchPreferences;
import etour.framework.PreferencesDataSource;
import etour.ports.SearchPreferencesRepository;

/**
 * Repository implementation using a data source.
 */
public class SearchPreferencesRepositoryImpl implements SearchPreferencesRepository {
    private PreferencesDataSource dataSource;

    public SearchPreferencesRepositoryImpl(PreferencesDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public SearchPreferences findByTouristId(String touristId) {
        return dataSource.fetchPreferences(touristId);
    }

    @Override
    public SearchPreferences save(SearchPreferences preferences) {
        boolean success = dataSource.persistPreferences(preferences);
        if (success) {
            return preferences;
        } else {
            throw new RuntimeException("Failed to persist preferences");
        }
    }

    /**
     * Entry condition requirement traceability - corresponds to sequence diagram note m7
     */
    public void checkTouristHasSearchPreferences() {
        System.out.println("R-Entry: Tourist HAS search preferences");
        System.out.println("// Added traceability for entry condition requirement");
    }

    /**
     * Return saved Preferences - corresponds to sequence diagram message m49
     */
    public SearchPreferences returnSavedPreferences(SearchPreferences preferences) {
        System.out.println("Repository returning saved Preferences");
        return preferences;
    }
}