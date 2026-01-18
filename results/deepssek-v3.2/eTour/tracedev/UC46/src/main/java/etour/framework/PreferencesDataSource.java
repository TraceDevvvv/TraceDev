package etour.framework;

import etour.domain.SearchPreferences;

/**
 * Data source interface for preferences storage.
 */
public interface PreferencesDataSource {
    SearchPreferences fetchPreferences(String touristId);
    boolean persistPreferences(SearchPreferences preferences);
}