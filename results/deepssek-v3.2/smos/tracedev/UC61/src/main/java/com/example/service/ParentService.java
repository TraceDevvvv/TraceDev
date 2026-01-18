package com.example.service;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import com.example.dto.ChildSummaryDTO;
import com.example.model.Child;
import com.example.model.AttendanceRecord;
import com.example.model.DisciplinaryNote;
import com.example.model.DelayRecord;
import com.example.model.Justification;
import com.example.repository.ChildRepository;

/**
 * Main service for parent operations.
 */
public class ParentService {
    private ChildRepository childRepository;
    private AttendanceService attendanceService;

    public ParentService(ChildRepository childRepository, AttendanceService attendanceService) {
        this.childRepository = childRepository;
        this.attendanceService = attendanceService;
    }

    public ChildSummaryDTO getChildSummary(int parentId, int childId) {
        // 1. Retrieve child information
        Child child = childRepository.findChildByParentAndId(parentId, childId);
        if (child == null) {
            throw new IllegalArgumentException("Child not found for parent");
        }
        
        // 2. Retrieve attendance records (including absences)
        Date currentDate = new Date();
        List<AttendanceRecord> attendanceRecords = attendanceService.getAttendanceRecords(childId, currentDate);
        
        // Filter absences: only records with type "absence"
        List<AttendanceRecord> absences = attendanceRecords.stream()
                .filter(record -> "absence".equals(record.getType()))
                .collect(Collectors.toList());
        
        // 3. Retrieve disciplinary notes
        List<DisciplinaryNote> disciplinaryNotes = attendanceService.getDisciplinaryNotes(childId);
        
        // 4. Retrieve delay records
        List<DelayRecord> delayRecords = attendanceService.getDelayRecords(childId);
        
        // 5. Retrieve justifications
        List<Justification> justifications = attendanceService.getJustifications(childId);
        
        // 6. Build and return DTO
        return buildChildSummaryDTO(child, absences, disciplinaryNotes, delayRecords, justifications);
    }

    public ChildSummaryDTO buildChildSummaryDTO(Child child, List<AttendanceRecord> attendanceRecords, 
                                                List<DisciplinaryNote> disciplinaryNotes, 
                                                List<DelayRecord> delayRecords, 
                                                List<Justification> justifications) {
        ChildSummaryDTO dto = new ChildSummaryDTO(child.getName(), child.getChildId(), new Date());
        dto.setAbsences(attendanceRecords);
        dto.setDisciplinaryNotes(disciplinaryNotes);
        dto.setDelays(delayRecords);
        dto.setJustifications(justifications);
        return dto;
    }
}