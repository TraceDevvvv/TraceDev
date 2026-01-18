package com.example.infrastructure;

import com.example.domain.Banner;
import java.util.List;

/**
 * Implementation of BannerRepository.
 */
public class BannerRepositoryImpl implements BannerRepository {
    private BannerDataSource dataSource;
    
    public BannerRepositoryImpl(BannerDataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Override
    public Banner findBannerByIdAndPointOfRestaurantId(String bannerId, String pointOfRestaurantId) {
        return dataSource.findByIdAndPointOfRestaurantId(bannerId, pointOfRestaurantId);
    }
    
    @Override
    public List<Banner> findBannersByPointOfRestaurantId(String pointOfRestaurantId) {
        return dataSource.findByPointOfRestaurantId(pointOfRestaurantId);
    }
    
    @Override
    public void save(Banner banner) throws ConnectionInterruptedException {
        dataSource.update(banner);
    }
    
    /**
     * persists changes via data source
     * As per m39: sync from Repository to Repository
     */
    public void persistChangesViaDataSource(Banner banner) throws ConnectionInterruptedException {
        save(banner);
    }
    
    /**
     * throw ConnectionInterruptedException
     * As per m40: sync from Repository to Repository
     */
    public void throwConnectionInterruptedException() throws ConnectionInterruptedException {
        throw new ConnectionInterruptedException("Connection interrupted");
    }
    
    /**
     * Connection failed
     * As per m41: return from Repository to UseCase
     */
    public void connectionFailed() throws ConnectionInterruptedException {
        throw new ConnectionInterruptedException("Connection failed");
    }
}