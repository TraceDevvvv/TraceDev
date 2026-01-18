package com.etour.mapper;

import com.etour.domain.Site;
import com.etour.dto.SiteDTO;

/**
 * Mapper class to convert between Site entity and SiteDTO.
 */
public class SiteMapper {
    /**
     * Converts a Site entity to a SiteDTO.
     *
     * @param site the Site entity
     * @return the corresponding SiteDTO
     */
    public SiteDTO toDTO(Site site) {
        return SiteDTO.fromEntity(site);
    }
}