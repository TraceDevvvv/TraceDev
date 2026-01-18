package com.example;

/**
 * Abstract class representing threshold criteria for student monitoring.
 */
public abstract class ThresholdCriteria {
    protected String schoolYear;
    protected int absenceThreshold;
    protected int noteThreshold;

    /**
     * Constructor for ThresholdCriteria.
     * @param schoolYear the school year to filter by
     * @param absenceThreshold the minimum absence count to exceed
     * @param noteThreshold the minimum note count to exceed
     */
    public ThresholdCriteria(String schoolYear, int absenceThreshold, int noteThreshold) {
        this.schoolYear = schoolYear;
        this.absenceThreshold = absenceThreshold;
        this.noteThreshold = noteThreshold;
    }

    /**
     * Validates that thresholds are positive numbers.
     * @return true if thresholds are valid
     */
    protected boolean validateThresholds() {
        return absenceThreshold >= 0 && noteThreshold >= 0;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public int getAbsenceThreshold() {
        return absenceThreshold;
    }

    public void setAbsenceThreshold(int absenceThreshold) {
        this.absenceThreshold = absenceThreshold;
    }

    public int getNoteThreshold() {
        return noteThreshold;
    }

    public void setNoteThreshold(int noteThreshold) {
        this.noteThreshold = noteThreshold;
    }
}