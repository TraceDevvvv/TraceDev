package com.example.app.application;

import com.example.app.Application;
import com.example.app.domain.*;
import com.example.app.dto.*;
import com.example.app.infrastructure.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Service class for handling business logic related to class registers.
 * Acts as the application layer, orchestrating domain and infrastructure components.
 */
public class RegisterService implements Application {
    private final IRegisterRepository registerRepository;
    private final IStudentRepository studentRepository;
    private final IAttendanceRepository attendanceRepository;
    private final IJustificationRepository justificationRepository;
    private final IDisciplinaryNoteRepository disciplinaryNoteRepository;

    // Assuming a way to simulate external service interruption
    private boolean simulateSMOSInterruption = false;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    /**
     * Constructs a RegisterService with necessary repository dependencies.
     * @param registerRepository The repository for ClassRegister entities.
     * @param studentRepository The repository for Student entities.
     * @param attendanceRepository The repository for AttendanceRecord entities.
     * @param justificationRepository The repository for Justification entities.
     * @param disciplinaryNoteRepository The repository for DisciplinaryNote entities.
     */
    public RegisterService(IRegisterRepository registerRepository,
                           IStudentRepository studentRepository,
                           IAttendanceRepository attendanceRepository,
                           IJustificationRepository justificationRepository,
                           IDisciplinaryNoteRepository disciplinaryNoteRepository) {
        this.registerRepository = registerRepository;
        this.studentRepository = studentRepository;
        this.attendanceRepository = attendanceRepository;
        this.justificationRepository = justificationRepository;
        this.disciplinaryNoteRepository = disciplinaryNoteRepository;
    }


    public void setSimulateSMOSInterruption(boolean simulate) {
        this.simulateSMOSInterruption = simulate;
    }



    public RegisterDetailsDTO getRegisterDetails(String registerId, String date) {
        if (simulateSMOSInterruption) {
            // Simulating REQ-R14: "SMOS server connection IS interrupted"
            throw new ServiceUnavailableException("SMOS server connection IS interrupted. Please try again later.");
        }

        // 1. Retrieve core register information
        System.out.println("Service: Retrieving core register information...");
        ClassRegister classRegister = registerRepository.findById(registerId);
        if (classRegister == null) {
            // Handle case where register is not found
            System.err.println("Register with ID " + registerId + " not found.");
            // For now, return an empty DTO or throw an exception indicating not found
            return new RegisterDetailsDTO(registerId, "N/A", "Register not found", date, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        }

        // 2. Retrieve student list
        System.out.println("Service: Retrieving student list...");
        List<Student> students = studentRepository.findByRegisterId(registerId);
        // Map students by ID for easy lookup
        Map<String, Student> studentMap = students.stream()
                                                  .collect(Collectors.toMap(Student::getId, Function.identity()));

        // Retrieve attendance records for the specified date
        System.out.println("Service: Retrieving attendance records...");
        List<AttendanceRecord> attendanceRecords = attendanceRepository.findByRegisterIdAndDate(registerId, date);
        // Map attendance records by student ID for easy lookup
        Map<String, AttendanceRecord> studentAttendanceMap = attendanceRecords.stream()
                                                                              .collect(Collectors.toMap(AttendanceRecord::getStudentId, Function.identity()));

        // 6. Retrieve justifications for today's date
        System.out.println("Service: Retrieving justifications...");
        List<Justification> justifications = justificationRepository.findByRegisterIdAndDate(registerId, date);
        // Map justifications by student ID for easy lookup
        Map<String, Justification> studentJustificationMap = justifications.stream()
                                                                           .collect(Collectors.toMap(Justification::getStudentId, Function.identity()));


        // 7. Retrieve disciplinary notes for today's date
        System.out.println("Service: Retrieving disciplinary notes...");
        List<DisciplinaryNote> disciplinaryNotes = disciplinaryNoteRepository.findByRegisterIdAndDate(registerId, date);
        // Map disciplinary notes by student ID for easy lookup
        Map<String, List<DisciplinaryNote>> studentDisciplinaryNotesMap = disciplinaryNotes.stream()
                .collect(Collectors.groupingBy(DisciplinaryNote::getStudentId));


        // 3-5. Aggregate student details with absent/present/late status into StudentAttendanceDTOs
        System.out.println("Service: Aggregating student attendance details...");
        List<StudentAttendanceDTO> studentAttendanceDTOs = students.stream().map(student -> {
            AttendanceRecord record = studentAttendanceMap.get(student.getId());
            AttendanceStatus status = (record != null) ? record.getStatus() : AttendanceStatus.ABSENT; // Assume absent if no record
            boolean isLate = (record != null) && record.isLate();
            boolean hasJustification = studentJustificationMap.containsKey(student.getId());
            return new StudentAttendanceDTO(student.getId(), student.getName(), status, isLate, hasJustification);
        }).collect(Collectors.toList());

        // Assemble Justification DTOs
        System.out.println("Service: Assembling justification DTOs...");
        List<JustificationDTO> justificationDTOs = justifications.stream()
                .map(j -> new JustificationDTO(j.getId(), studentMap.getOrDefault(j.getStudentId(), new Student("N/A", "Unknown Student", null)).getName(), j.getReason(), j.getStatus()))
                .collect(Collectors.toList());

        // Assemble Disciplinary Note DTOs
        System.out.println("Service: Assembling disciplinary note DTOs...");
        List<DisciplinaryNoteDTO> disciplinaryNoteDTOs = disciplinaryNotes.stream()
                .map(dn -> new DisciplinaryNoteDTO(dn.getId(), studentMap.getOrDefault(dn.getStudentId(), new Student("N/A", "Unknown Student", null)).getName(), dn.getDescription(), dn.getType()))
                .collect(Collectors.toList());


        // Assemble all data into a single RegisterDetailsDTO
        System.out.println("Service: Assembling final RegisterDetailsDTO...");
        return new RegisterDetailsDTO(
                classRegister.getId(),
                classRegister.getName(),
                classRegister.getDescription(),
                date,
                studentAttendanceDTOs,
                justificationDTOs,
                disciplinaryNoteDTOs
        );
    }

    /**
     * Adds a new justification for a student's absence.
     * @param justificationDTO The DTO containing justification details.
     */
    public void addJustification(JustificationDTO justificationDTO) {
        
        System.out.println("Service: Adding justification...");
        try {
     
            Date today = new Date(); // Or parse a date from DTO if it had one.
            String studentId = UUID.randomUUID().toString(); // Placeholder
            String registerId = UUID.randomUUID().toString(); // Placeholder

            Justification newJustification = new Justification(
                    UUID.randomUUID().toString(), // New ID
                    studentId, // Missing from current DTO, assumed
                    registerId, // Missing from current DTO, assumed
                    today, // Missing from current DTO, assumed
                    justificationDTO.getReason(),
                    JustificationStatus.PENDING // Newly added justifications typically start as PENDING
            );
            justificationRepository.save(newJustification);
            System.out.println("Justification added for " + justificationDTO.getStudentName() + ".");
        } catch (Exception e) {
            System.err.println("Error adding justification: " + e.getMessage());
        }
    }

    /**
     * Updates the status of an existing justification.
     * @param justificationId The ID of the justification to update.
     * @param status The new status for the justification.
     */
    public void updateJustificationStatus(String justificationId, JustificationStatus status) {
        System.out.println("Service: Updating justification status...");
        justificationRepository.updateStatus(justificationId, status);
        System.out.println("Justification " + justificationId + " status updated to " + status + ".");
    }

    /**
     * Adds a new disciplinary note for a student.
     * @param disciplinaryNoteDTO The DTO containing disciplinary note details.
     */
    public void addDisciplinaryNote(DisciplinaryNoteDTO disciplinaryNoteDTO) {
        // Similar assumption as addJustification: disciplinaryNoteDTO would need studentId, registerId, date
        System.out.println("Service: Adding disciplinary note...");
        try {
            Date today = new Date();
            String studentId = UUID.randomUUID().toString(); // Placeholder
            String registerId = UUID.randomUUID().toString(); // Placeholder

            DisciplinaryNote newNote = new DisciplinaryNote(
                    UUID.randomUUID().toString(), // New ID
                    studentId, // Missing from current DTO, assumed
                    registerId, // Missing from current DTO, assumed
                    today, // Missing from current DTO, assumed
                    disciplinaryNoteDTO.getDescription(),
                    disciplinaryNoteDTO.getType()
            );
            disciplinaryNoteRepository.save(newNote);
            System.out.println("Disciplinary note added for " + disciplinaryNoteDTO.getStudentName() + ".");
        } catch (Exception e) {
            System.err.println("Error adding disciplinary note: " + e.getMessage());
        }
    }


    public void updateDisciplinaryNote(String disciplinaryNoteId, String description, String type) {
        System.out.println("Service: Updating disciplinary note...");
        disciplinaryNoteRepository.update(disciplinaryNoteId, description, type);
        System.out.println("Disciplinary note " + disciplinaryNoteId + " updated.");
    }
}