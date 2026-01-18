package com.culturalheritage.repository;

import com.culturalheritage.model.CulturalHeritageObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory implementation of CulturalHeritageRepository for data storage.
 * This class uses a ConcurrentHashMap to store CulturalHeritageObject instances,
 * simulating a database for demonstration purposes.
 */
public class InMemoryCulturalHeritageRepository implements CulturalHeritageRepository {

    // Using ConcurrentHashMap to simulate a thread-safe data store
    private final Map<String, CulturalHeritageObject> dataStore;

    /**
     * Constructs a new InMemoryCulturalHeritageRepository and initializes the data store.
     * Populates the data store with some initial cultural heritage objects.
     */
    public InMemoryCulturalHeritageRepository() {
        this.dataStore = new ConcurrentHashMap<>();
        // Initialize with some dummy data
        dataStore.put("CH001", new CulturalHeritageObject("CH001", "Mona Lisa", "A half-length portrait painting by Italian artist Leonardo da Vinci.", "France", 1503));
        dataStore.put("CH002", new CulturalHeritageObject("CH002", "Great Wall of China", "A series of fortifications made of stone, brick, tamped earth, wood, and other materials.", "China", -700)); // Year 700 BC
        dataStore.put("CH003", new CulturalHeritageObject("CH003", "Pyramids of Giza", "Ancient pyramids located in Egypt.", "Egypt", -2580)); // Year 2580 BC
    }

    /**
     * Finds a cultural heritage object by its unique identifier.
     *
     * @param id The unique identifier of the cultural heritage object.
     * @return An Optional containing the CulturalHeritageObject if found,
     *         or an empty Optional if no object with the given ID exists.
     */
    @Override
    public Optional<CulturalHeritageObject> findById(String id) {
        return Optional.ofNullable(dataStore.get(id));
    }

    /**
     * Saves a cultural heritage object to the data store.
     * If an object with the same ID already exists, it is updated.
     * If not, a new object is added.
     *
     * @param object The CulturalHeritageObject to be saved.
     */
    @Override
    public void save(CulturalHeritageObject object) {
        if (object != null && object.getId() != null) {
            dataStore.put(object.getId(), object);
            System.out.println("Repository: Saved/Updated object with ID: " + object.getId());
        }
    }

    /**
     * Retrieves all cultural heritage objects from the data store.
     *
     * @return A List of all CulturalHeritageObject entities.
     */
    @Override
    public List<CulturalHeritageObject> findAll() {
        return new ArrayList<>(dataStore.values());
    }
}