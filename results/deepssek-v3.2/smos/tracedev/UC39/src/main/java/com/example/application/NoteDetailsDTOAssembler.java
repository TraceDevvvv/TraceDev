package com.example.application;

import com.example.domain.Note;
import com.example.domain.Student;
import com.example.domain.Teacher;
import java.util.Date;

/**
 * Assembles a Note domain object into a NoteDetailsDTO.
 */
public class NoteDetailsDTOAssembler {
    /**
     * Converts a Note domain object to a NoteDetailsDTO.
     * @param note the Note domain object
     * @return the assembled DTO
     */
    public NoteDetailsDTO toDTO(Note note) {
        System.out.println("Assembler: Converting Note to DTO.");
        Student student = note.getStudent();
        Teacher teacher = note.getTeacher();
        String studentName = (student != null) ? student.getName() : "Unknown";
        String teacherName = (teacher != null) ? teacher.getName() : "Unknown";
        String description = note.getDescription();
        Date date = note.getDate();
        return new NoteDetailsDTO(studentName, description, teacherName, date);
    }
}