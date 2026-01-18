package com.example;

import java.util.List;

/**
 * Data Transfer Object for ClassRegister.
 */
public class ClassRegisterDTO {
    public String registerId;
    public String className;
    public List<RecordByDateDTO> records;

    public ClassRegisterDTO(ClassRegister register) {
        this.registerId = register.getRegisterId();
        this.className = "Sample Class"; // Assuming class name is derived; adjust as needed.
        // Note: Records conversion would require mapping; simplified here.
        this.records = null; // In a full implementation, convert List<RecordByDate> to List<RecordByDateDTO>.
    }

    public String getRegisterId() {
        return registerId;
    }
}