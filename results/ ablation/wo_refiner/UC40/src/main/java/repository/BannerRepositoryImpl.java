package repository;

import entity.Banner;
import datasource.BannerDataSource;
import exceptions.DataSourceAccessException;

/**
 * Repository implementation that delegates to a data source.
 * Handles DataSourceAccessException and translates to domain exceptions if needed.
 */
public class BannerRepositoryImpl implements BannerRepository {
    private BannerDataSource dataSource;

    public BannerRepositoryImpl(BannerDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Banner findById(String bannerId) {
        try {
            return dataSource.getBanner(bannerId);
        } catch (DataSourceAccessException e) {
            // Log and re‑throw – the interactor will handle it
            throw e;
        }
    }

    @Override
    public Banner save(Banner banner) {
        try {
            boolean success = dataSource.updateBanner(banner);
            if (success) {
                return banner;
            } else {
                throw new RuntimeException("Update failed");
            }
        } catch (DataSourceAccessException e) {
            throw e;
        }
    }

    @Override
    public void deleteById(String bannerId) {
        try {
            boolean success = dataSource.removeBanner(bannerId);
            if (!success) {
                throw new RuntimeException("Delete failed");
            }
        } catch (DataSourceAccessException e) {
            throw e;
        }
    }
}