package com.example;

import java.util.List;

/**
 * Data Transfer Object for register details including UI state.
 */
public class RegisterDetailsDTO {
    public ClassRegisterDTO registerData;
    public List<RecordByDateDTO> organizedRecords;
    public boolean justificationFormVisible;
    public boolean disciplinaryNoteFormVisible;

    public RegisterDetailsDTO(ClassRegisterDTO registerData, List<RecordByDateDTO> organizedRecords, boolean justificationFormVisible, boolean disciplinaryNoteFormVisible) {
        this.registerData = registerData;
        this.organizedRecords = organizedRecords;
        this.justificationFormVisible = justificationFormVisible;
        this.disciplinaryNoteFormVisible = disciplinaryNoteFormVisible;
    }
}