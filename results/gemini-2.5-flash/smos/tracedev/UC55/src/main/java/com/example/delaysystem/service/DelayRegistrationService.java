package com.example.delaysystem.service;

import com.example.delaysystem.model.DelayData;
import com.example.delaysystem.model.Student;
import com.example.delaysystem.repository.IClassRepository;
import com.example.delaysystem.repository.IDelayRepository;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Service layer for delay registration business logic.
 * Orchestrates interactions between repositories and handles data transformation.
 */
public class DelayRegistrationService {
    // Repository for saving delay data
    protected IDelayRepository delayRepository;
    // Repository for accessing class and student data
    protected IClassRepository classRepository;

    /**
     * Constructs a new DelayRegistrationService.
     *
     * @param delayRepository The repository for delay data operations.
     * @param classRepository The repository for class data operations.
     */
    public DelayRegistrationService(IDelayRepository delayRepository, IClassRepository classRepository) {
        this.delayRepository = delayRepository;
        this.classRepository = classRepository;
    }

    /**
     * Retrieves a list of students enrolled in a specified class.
     * Implements part of the "Load Students" sequence.
     *
     * @param classId The unique identifier of the class.
     * @return A list of Student objects.
     */
    public List<Student> getStudentsInClass(String classId) {
        System.out.println("[Service] Getting students for class: " + classId);
        // As per sequence diagram: Service -> ClassRepo : getStudentsForClass()
        return classRepository.getStudentsForClass(classId);
    }

    /**
     * Registers delays for a given class based on student delay inputs.
     * This involves transforming raw input into DelayData objects and saving them.
     * Implements the "Register Delays" sequence.
     *
     * @param classId The ID of the class.
     * @param studentDelayInputs A map where keys are student IDs and values are their delay durations.
     * @return true if all delays were successfully registered, false otherwise.
     */
    public boolean registerDelays(String classId, Map<String, Duration> studentDelayInputs) {
        System.out.println("[Service] Registering delays for class '" + classId + "' with inputs: " + studentDelayInputs.size() + " entries.");

        if (studentDelayInputs == null || studentDelayInputs.isEmpty()) {
            System.out.println("[Service] No student delay inputs provided. Aborting save.");
            return false;
        }

        List<DelayData> delayDataList = new ArrayList<>();
        // Note: As mentioned in sequence diagram, transformation and validation (REQ-016) would happen here.
        // For now, simple transformation.
        for (Map.Entry<String, Duration> entry : studentDelayInputs.entrySet()) {
            String studentId = entry.getKey();
            Duration delayTime = entry.getValue();

            // Basic validation: ensure delay time is positive and not null
            if (delayTime == null || delayTime.isNegative() || delayTime.isZero()) {
                System.err.println("[Service] Invalid delay time for student " + studentId + ": " + delayTime + ". Skipping.");
                // In a real app, this might throw an exception or collect errors.
                continue;
            }
            delayDataList.add(new DelayData(studentId, classId, delayTime));
        }

        if (delayDataList.isEmpty()) {
            System.err.println("[Service] No valid DelayData objects to save after processing inputs.");
            return false;
        }

        System.out.println("[Service] Transformed inputs into " + delayDataList.size() + " DelayData objects.");
        // As per sequence diagram: Service -> DelayRepo : saveDelayData()
        boolean success = delayRepository.saveDelayData(delayDataList);

        if (!success) {
            System.err.println("[Service] Failed to save delay data to repository.");
        }
        return success;
    }
}