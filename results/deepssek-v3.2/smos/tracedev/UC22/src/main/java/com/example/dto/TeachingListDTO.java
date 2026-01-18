package com.example.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object that contains a list of TeachingDTO.
 * Returned by the controller to the UI.
 */
public class TeachingListDTO {
    private List<TeachingDTO> teachings;

    public TeachingListDTO() {
        this.teachings = new ArrayList<>();
    }

    public TeachingListDTO(List<TeachingDTO> teachings) {
        this.teachings = teachings;
    }

    public List<TeachingDTO> getTeachings() {
        return teachings;
    }

    public void setTeachings(List<TeachingDTO> teachings) {
        this.teachings = teachings;
    }

    public void addTeaching(TeachingDTO teaching) {
        if (teachings == null) {
            teachings = new ArrayList<>();
        }
        teachings.add(teaching);
    }
}