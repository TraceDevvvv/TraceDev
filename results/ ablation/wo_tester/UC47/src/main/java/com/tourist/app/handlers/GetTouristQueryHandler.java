package com.tourist.app.handlers;

import com.tourist.app.dtos.TouristDto;
import com.tourist.app.interfaces.IQueryHandler;
import com.tourist.app.interfaces.ITouristRepository;
import com.tourist.app.queries.GetTouristQuery;
import com.tourist.domain.Tourist;

/**
 * Handler for GetTouristQuery.
 */
public class GetTouristQueryHandler implements IQueryHandler<GetTouristQuery, TouristDto> {
    private ITouristRepository touristRepository;

    /**
     * Constructor.
     * @param repo the tourist repository
     */
    public GetTouristQueryHandler(ITouristRepository repo) {
        this.touristRepository = repo;
    }

    @Override
    public TouristDto Handle(GetTouristQuery query) {
        // Retrieve the tourist from repository.
        Tourist tourist = touristRepository.GetById(query.getTouristId());
        if (tourist == null) {
            throw new IllegalArgumentException("Tourist not found with id: " + query.getTouristId());
        }

        // Map domain entity to DTO (as per sequence diagram message "Map to TouristDto").
        return mapToDto(tourist);
    }

    /**
     * Maps a Tourist domain entity to TouristDto.
     * @param tourist the domain entity
     * @return the DTO
     */
    private TouristDto mapToDto(Tourist tourist) {
        return new TouristDto(
            tourist.getTouristId(),
            tourist.getName(),
            tourist.getEmail(),
            tourist.getPhoneNumber()
        );
    }
}