package com.example.mapper;

import com.example.entity.Site;
import com.example.dto.SiteDTO;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for Site entities to DTOs.
 */
public class SiteMapper {
    public static SiteDTO toDTO(Site site) {
        if (site == null) return null;
        // Use FeedbackMapper to convert feedback list
        return new SiteDTO(
                site.getSiteId(),
                site.getName(),
                site.getLocation(),
                FeedbackMapper.toDTOList(site.getFeedbackList())
        );
    }

    public static List<SiteDTO> toDTOList(List<Site> sites) {
        if (sites == null) return null;
        return sites.stream()
                .map(SiteMapper::toDTO)
                .collect(Collectors.toList());
    }
}