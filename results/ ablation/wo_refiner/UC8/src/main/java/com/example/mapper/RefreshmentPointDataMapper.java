package com.example.mapper;

import com.example.dto.RefreshmentPointFormDTO;
import com.example.model.RefreshmentPoint;
import com.example.model.Location;
import com.example.model.LocationDTO;
import com.example.model.PointStatus;

import java.util.Map;

/**
 * Data mapper for RefreshmentPoint.
 */
public class RefreshmentPointDataMapper {
    public RefreshmentPoint toEntity(RefreshmentPointFormDTO dto) {
        // Convert DTO to entity
        Location location = null;
        if (dto.getLocationData() != null) {
            LocationDTO locDto = dto.getLocationData();
            location = new Location(locDto.getLatitude(), locDto.getLongitude(), locDto.getAddress());
        }
        // Assuming default status and version; id would be generated or passed separately
        return new RefreshmentPoint(
                null, // id would be set elsewhere
                dto.getName(),
                location,
                PointStatus.ACTIVE,
                dto.getAttributes(),
                0
        );
    }

    public RefreshmentPointFormDTO toFormDTO(RefreshmentPoint entity) {
        LocationDTO locDto = null;
        if (entity.getLocation() != null) {
            Location loc = entity.getLocation();
            locDto = new LocationDTO(loc.getLatitude(), loc.getLongitude(), loc.getAddress());
        }
        return new RefreshmentPointFormDTO(
                entity.getName(),
                locDto,
                entity.getAttributes(),
                "Operator notes placeholder"
        );
    }

    public void updateEntity(RefreshmentPoint entity, RefreshmentPointFormDTO dto) {
        // Update entity fields from DTO
        entity.setName(dto.getName());
        if (dto.getLocationData() != null) {
            LocationDTO locDto = dto.getLocationData();
            entity.setLocation(new Location(locDto.getLatitude(), locDto.getLongitude(), locDto.getAddress()));
        }
        entity.setAttributes(dto.getAttributes());
    }
}