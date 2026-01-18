package com.example.external;

import com.example.dto.AbsenceRecord;
import com.example.dto.DelayRecord;
import com.example.dto.DisciplinaryNote;
import java.util.List;

/**
 * Represents external student data from SMOS server.
 */
public class ExternalStudentData {
    private String data;
    private List<AbsenceRecord> absenceRecords;
    private List<DisciplinaryNote> disciplinaryNotes;
    private List<DelayRecord> delayRecords;
    private String justification;
    
    public String getData() {
        return data;
    }
    
    public void setData(String data) {
        this.data = data;
    }
    
    public List<AbsenceRecord> getAbsenceRecords() {
        return absenceRecords;
    }
    
    public void setAbsenceRecords(List<AbsenceRecord> absenceRecords) {
        this.absenceRecords = absenceRecords;
    }
    
    public List<DisciplinaryNote> getDisciplinaryNotes() {
        return disciplinaryNotes;
    }
    
    public void setDisciplinaryNotes(List<DisciplinaryNote> disciplinaryNotes) {
        this.disciplinaryNotes = disciplinaryNotes;
    }
    
    public List<DelayRecord> getDelayRecords() {
        return delayRecords;
    }
    
    public void setDelayRecords(List<DelayRecord> delayRecords) {
        this.delayRecords = delayRecords;
    }
    
    public String getJustification() {
        return justification;
    }
    
    public void setJustification(String justification) {
        this.justification = justification;
    }
}