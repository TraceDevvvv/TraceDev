package com.example.application.presenter;

import com.example.application.dto.ClassDTO;
import java.util.List;

/**
 * Presenter for displaying a list of classes.
 */
public class ClassListPresenter {
    private List<ClassDTO> classDTOs;

    /**
     * Displays the list of classes.
     */
    public void displayClassList(List<ClassDTO> classDTOs) {
        this.classDTOs = classDTOs;
        System.out.println("=== Class List ===");
        for (ClassDTO dto : classDTOs) {
            System.out.println("ID: " + dto.getId() + ", Name: " + dto.getName() + ", Archived: " + dto.isArchived());
        }
        System.out.println("==================");
    }
}