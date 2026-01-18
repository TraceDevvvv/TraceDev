
package com.example.repository;

import com.example.model.SearchPreference;
import javax.persistence.EntityManager;
import java.util.Optional;

/**
 * JPA implementation of PreferenceRepository.
 * Since we are not using an actual JPA context, we simulate the behavior.
 */
public class JpaPreferenceRepository implements PreferenceRepository {
    // In a real application, this would be injected via @PersistenceContext
    private EntityManager entityManager;

    public JpaPreferenceRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<SearchPreference> findById(String id) {
        // Simulate JPA find
        SearchPreference pref = entityManager.find(SearchPreference.class, id);
        return Optional.ofNullable(pref);
    }

    @Override
    public SearchPreference save(SearchPreference preference) {
        // Simulate JPA persist/merge
        if (preference.getId() == null) {
            entityManager.persist(preference);
            return preference;
        } else {
            return entityManager.merge(preference);
        }
    }
}
