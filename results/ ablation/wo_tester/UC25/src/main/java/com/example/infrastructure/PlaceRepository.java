package com.example.infrastructure;

import com.example.domain.Place;
import java.util.List;

/**
 * Interface for place repository.
 * Added findAll() to satisfy requirement Flow of Events 2.
 */
public interface PlaceRepository {
    List<Place> findAll();
}