package datasource;

import entity.Banner;
import exceptions.DataSourceAccessException;

/**
 * Data source interface for low‑level banner data access.
 * May throw DataSourceAccessException on connection failures (REQ‑014, REQ‑015).
 */
public interface BannerDataSource {
    Banner getBanner(String bannerId) throws DataSourceAccessException;
    boolean updateBanner(Banner banner) throws DataSourceAccessException;
    boolean removeBanner(String bannerId) throws DataSourceAccessException;
}