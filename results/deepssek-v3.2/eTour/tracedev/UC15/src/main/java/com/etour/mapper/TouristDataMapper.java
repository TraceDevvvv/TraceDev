package com.etour.mapper;

import com.etour.domain.Tourist;
import com.etour.dto.TouristDTO;

/**
 * Maps between Tourist entity and TouristDTO.
 */
public class TouristDataMapper {
    public Tourist toEntity(TouristDTO dto) {
        if (dto == null) return null;
        Tourist tourist = new Tourist();
        tourist.setId(dto.getTouristId());
        tourist.setName(dto.getName());
        tourist.setEmail(dto.getEmail());
        tourist.setPhoneNumber(dto.getPhoneNumber());
        tourist.setOtherAttributes(dto.getOtherAttributes());
        return tourist;
    }

    public TouristDTO toDto(Tourist tourist) {
        if (tourist == null) return null;
        TouristDTO dto = new TouristDTO();
        dto.setTouristId(tourist.getId());
        dto.setName(tourist.getName());
        dto.setEmail(tourist.getEmail());
        dto.setPhoneNumber(tourist.getPhoneNumber());
        dto.setOtherAttributes(tourist.getOtherAttributes());
        return dto;
    }
}