package com.example.service;

import com.example.domain.Banner;
import com.example.domain.PointOfRestaurant;
import com.example.repository.BannerRepository;
import com.example.repository.PointOfRestRepository;
import com.example.web.BannerDTO;
import com.example.client.ETOURClient;
import com.example.exceptions.ConnectionException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.UUID;

/**
 * Implementation of BannerService.
 * Implements Use Case Controller pattern, coordinating validation and persistence.
 */
public class BannerServiceImpl implements BannerService {
    private BannerRepository bannerRepository;
    private PointOfRestRepository pointOfRestRepository;
    private ETOURClient etourClient;

    public BannerServiceImpl(BannerRepository bannerRepository,
                             PointOfRestRepository pointOfRestRepository,
                             ETOURClient etourClient) {
        this.bannerRepository = bannerRepository;
        this.pointOfRestRepository = pointOfRestRepository;
        this.etourClient = etourClient;
    }

    @Override
    public BannerDTO insertBanner(BannerDTO bannerDTO) {
        // Send data to external system (requirement REQ-15)
        boolean sent = etourClient.sendBannerData(bannerDTO);
        if (!sent) {
            throw new ConnectionException("Failed to send banner data to ETOUR");
        }

        Banner banner = toEntity(bannerDTO);
        Banner saved = bannerRepository.save(banner);
        // Remember banners (requirement REQ-11)
        bannerRepository.rememberBanners(saved.getPointOfRestId());
        return toDTO(saved);
    }

    @Override
    public List<BannerDTO> getBannersForPointOfRest(String pointOfRestId) {
        List<Banner> banners = bannerRepository.findByPointOfRestId(pointOfRestId);
        return banners.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public int getBannerCountForPointOfRest(String pointOfRestId) {
        return bannerRepository.countByPointOfRestId(pointOfRestId);
    }

    @Override
    public void rememberBanners(String pointOfRestId) {
        bannerRepository.rememberBanners(pointOfRestId);
    }

    private Banner toEntity(BannerDTO dto) {
        // Generate a new ID if not present
        String id = dto.getId() != null ? dto.getId() : UUID.randomUUID().toString();
        return new Banner(id, dto.getPointOfRestId(), dto.getImageData(), dto.getImageFormat());
    }

    private BannerDTO toDTO(Banner banner) {
        return new BannerDTO(banner.getId(), banner.getPointOfRestId(),
                banner.getImageData(), banner.getImageFormat());
    }
}