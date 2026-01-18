package com.example.criteria;

/**
 * Value object representing monitoring threshold criteria.
 */
public class MonitoringThresholdCriteria {
    private int maxAllowedAbsences;
    private int maxAllowedNotes;

    public MonitoringThresholdCriteria(int maxAbsences, int maxNotes) {
        this.maxAllowedAbsences = maxAbsences;
        this.maxAllowedNotes = maxNotes;
    }

    public int getMaxAllowedAbsences() {
        return maxAllowedAbsences;
    }

    public int getMaxAllowedNotes() {
        return maxAllowedNotes;
    }
}