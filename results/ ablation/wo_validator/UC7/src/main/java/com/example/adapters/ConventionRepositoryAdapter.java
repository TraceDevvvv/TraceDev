
package com.example.adapters;

import com.example.domain.Convention;
import com.example.ports.ConventionRepository;
import java.util.Optional;

/**
 * Adapter implementing ConventionRepository port using JPA.
 * Note: ConventionEntity is assumed to be a JPA entity mapping to Convention domain.
 * For simplicity, we assume a method to convert between entity and domain.
 */
public class ConventionRepositoryAdapter implements ConventionRepository {

    private final ConventionJpaRepository jpaRepository;

    public ConventionRepositoryAdapter(ConventionJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Convention> findById(String conventionId) {
        Optional<ConventionEntity> entity = jpaRepository.findById(conventionId);
        return entity.map(this::toDomain);
    }

    @Override
    public Convention save(Convention convention) {
        ConventionEntity entity = toEntity(convention);
        ConventionEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    private Convention toDomain(ConventionEntity entity) {
        // Conversion logic (simplified)
        Convention domain = new Convention(entity.getId());
        domain.loadData(entity.getData());
        // Status setting omitted for brevity
        return domain;
    }

    private ConventionEntity toEntity(Convention domain) {
        // Conversion logic (simplified)
        return new ConventionEntity(domain.getId(), domain.getData());
    }

    // Interface representing JPA repository
    interface ConventionJpaRepository {
        Optional<ConventionEntity> findById(String id);
        ConventionEntity save(ConventionEntity entity);
    }

    // Inner class representing JPA entity (assumed)
    static class ConventionEntity {
        private String id;
        private Object data;

        public ConventionEntity(String id, Object data) {
            this.id = id;
            this.data = data;
        }

        public String getId() {
            return id;
        }

        public Object getData() {
            return data;
        }
    }
}
