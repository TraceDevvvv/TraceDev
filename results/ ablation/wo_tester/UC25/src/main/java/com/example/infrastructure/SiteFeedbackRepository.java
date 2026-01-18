package com.example.infrastructure;

import com.example.domain.Location;
import com.example.domain.SiteFeedback;
import java.util.List;

/**
 * Interface for site feedback repository.
 * Added findByLocation() to satisfy requirement Flow of Events 6.
 * Throws DataSourceException for database connection errors.
 */
public interface SiteFeedbackRepository {
    List<SiteFeedback> findByLocation(Location location);
}