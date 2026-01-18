package com.etour.external;

import com.etour.adapters.SearchQuery;
import com.etour.entities.CulturalObject;
import java.util.List;

/**
 * Port for external data sources (e.g., ETour server).
 * Part of the External/Infrastructure layer.
 */
public interface ExternalDataSource {
    /**
     * Fetches cultural objects from the external source.
     * @param query the search criteria
     * @return list of cultural objects
     */
    List<CulturalObject> fetchCulturalObjects(SearchQuery query);

    /**
     * Counts cultural objects in the external source.
     * @param query the search criteria
     * @return count of matching objects
     */
    int countCulturalObjects(SearchQuery query);
}