package com.etour.banner.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for RefreshmentPoint information.
 * This class is used to transfer basic refreshment point data,
 * typically for displaying a list of available refreshment points
 * to the Agency Operator.
 */
@Data
public class RefreshmentPointDTO {
    /**
     * The unique identifier of the refreshment point.
     */
    private String id;

    /**
     * The name of the refreshment point.
     */
    private String name;
}