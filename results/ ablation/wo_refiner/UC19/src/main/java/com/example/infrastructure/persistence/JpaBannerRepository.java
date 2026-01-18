package com.example.infrastructure.persistence;

import com.example.domain.Banner;
import com.example.domain.exceptions.ETOURConnectionException;
import com.example.domain.repository.BannerRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * JPA implementation of BannerRepository.
 * Implements REQ-013 (throws ETOURConnectionException) and REQ-014 performance requirement.
 */
public class JpaBannerRepository implements BannerRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Optional<Banner> findById(String id) {
        try {
            Banner banner = entityManager.find(Banner.class, id);
            return Optional.ofNullable(banner);
        } catch (Exception e) {
            // Simulate connection exception as per REQ-013
            if (e.getMessage().contains("Connection") || e.getMessage().contains("ETOUR")) {
                throw new ETOURConnectionException("ETOUR Server Connection Lost: " + e.getMessage());
            }
            throw new RuntimeException("Database error", e);
        }
    }
    
    @Override
    @Transactional
    public void delete(Banner banner) {
        try {
            Banner managedBanner = entityManager.find(Banner.class, banner.getId());
            if (managedBanner != null) {
                entityManager.remove(managedBanner);
            }
        } catch (Exception e) {
            if (e.getMessage().contains("Connection") || e.getMessage().contains("ETOUR")) {
                throw new ETOURConnectionException("ETOUR Server Connection Lost during deletion: " + e.getMessage());
            }
            throw new RuntimeException("Database error during deletion", e);
        }
    }
    
    @Override
    public List<Banner> findByRefreshmentPointId(String refreshmentPointId) {
        try {
            TypedQuery<Banner> query = entityManager.createQuery(
                "SELECT b FROM Banner b WHERE b.refreshmentPointId = :rpId", Banner.class);
            query.setParameter("rpId", refreshmentPointId);
            return query.getResultList();
        } catch (Exception e) {
            if (e.getMessage().contains("Connection") || e.getMessage().contains("ETOUR")) {
                throw new ETOURConnectionException("ETOUR Server Connection Lost during query: " + e.getMessage());
            }
            throw new RuntimeException("Database error during query", e);
        }
    }
}