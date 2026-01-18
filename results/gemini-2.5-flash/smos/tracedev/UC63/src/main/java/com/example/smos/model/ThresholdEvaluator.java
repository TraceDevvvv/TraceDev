package com.example.smos.model;

/**
 * Evaluates a student against specified absence and note thresholds.
 * This class corresponds to the 'ThresholdEvaluator' class in the UML diagram.
 * It determines if a student meets certain monitoring criteria.
 */
public class ThresholdEvaluator {

    /**
     * Evaluates if a student meets the criteria for monitoring based on absence and note thresholds.
     * A student is considered to meet the monitoring criteria if they have high absences OR high notes.
     * The term "High Performance Students" in the service refers to students who meet these monitoring criteria,
     * not necessarily academically high-performing students, but rather students needing attention.
     *
     * @param student The Student object to evaluate.
     * @param absenceThreshold The threshold for high absences.
     * @param noteThreshold The threshold for high notes.
     * @return true if the student has high absences or high notes, false otherwise.
     */
    public boolean evaluate(Student student, int absenceThreshold, int noteThreshold) {
        // A student meets monitoring criteria if they have either high absences OR high notes.
        // This is interpreted as "students requiring attention" for monitoring.
        return student.hasHighAbsences(absenceThreshold) || student.hasHighNotes(noteThreshold);
    }
}