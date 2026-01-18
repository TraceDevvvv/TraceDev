package com.example.service;

import com.example.entity.Absence;
import com.example.enums.JustificationStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service containing business logic for processing absences.
 */
public class JustificationLogic {
    /**
     * Separates a list of absences into two lists based on justification status.
     * @param absenceList the list of absences
     * @return a map with keys JUSTIFIED and TO_JUSTIFY, each mapping to a list of absences
     */
    public Map<JustificationStatus, List<Absence>> separateAbsencesByStatus(List<Absence> absenceList) {
        Map<JustificationStatus, List<Absence>> map = new HashMap<>();
        map.put(JustificationStatus.JUSTIFIED, new ArrayList<>());
        map.put(JustificationStatus.TO_JUSTIFY, new ArrayList<>());

        if (absenceList != null) {
            for (Absence absence : absenceList) {
                JustificationStatus status = absence.getStatus();
                map.get(status).add(absence);
            }
        }
        return map;
    }

    /**
     * Method corresponding to sequence return m13: Map<Status, List<Absence>>.
     * This is essentially the same as separateAbsencesByStatus but returns a Map with proper generics.
     */
    public Map<JustificationStatus, List<Absence>> returnMapStatusListAbsence(List<Absence> absenceList) {
        return separateAbsencesByStatus(absenceList);
    }
}