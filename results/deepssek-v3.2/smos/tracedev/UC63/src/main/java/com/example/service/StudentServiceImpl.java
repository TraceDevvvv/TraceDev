package com.example.service;

import com.example.model.Student;
import com.example.repository.StudentRepository;
import com.example.criteria.MonitoringThresholdCriteria;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of StudentService.
 */
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findStudentsExceedingThreshold(MonitoringThresholdCriteria criteria) {
        List<Student> allStudents = studentRepository.findAll();
        List<Student> filtered = new ArrayList<>();

        for (Student student : allStudents) {
            int absences = studentRepository.countAbsencesByStudent(student.getId());
            int notes = studentRepository.countNotesByStudent(student.getId());

            if (absences > criteria.getMaxAllowedAbsences() && notes > criteria.getMaxAllowedNotes()) {
                filtered.add(student);
            }
        }
        return filtered;
    }
}