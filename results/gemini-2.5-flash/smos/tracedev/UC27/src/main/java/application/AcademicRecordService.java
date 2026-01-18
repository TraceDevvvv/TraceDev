package application;

import dataaccess.IAcademicRecordRepository;
import domain.AcademicYear;
import domain.DigitalRegister;

import java.util.List;

/**
 * The Application Layer service for managing academic records.
 * It orchestrates data access operations and can include business logic.
 */
public class AcademicRecordService {

    private IAcademicRecordRepository academicRecordRepository;

    /**
     * Constructs an AcademicRecordService with a given repository.
     *
     * @param academicRecordRepository The repository to use for data access.
     */
    public AcademicRecordService(IAcademicRecordRepository academicRecordRepository) {
        this.academicRecordRepository = academicRecordRepository;
    }

    /**
     * Retrieves a list of digital registers for a specific academic year.
     * Delegates the call to the underlying repository.
     *
     * @param academicYearId The ID of the academic year.
     * @return A list of DigitalRegister objects.
     */
    public List<DigitalRegister> retrieveDigitalRegisters(String academicYearId) {
        System.out.println("[Service] Retrieving digital registers for year: " + academicYearId);
        // Business logic could be applied here before returning, e.g., filtering, sorting, validation.
        return academicRecordRepository.findRegistersByAcademicYear(academicYearId);
    }

    /**
     * Retrieves all available academic years.
     * Delegates the call to the underlying repository.
     *
     * @return A list of AcademicYear objects.
     */
    public List<AcademicYear> getAvailableAcademicYears() {
        System.out.println("[Service] Getting available academic years.");
        return academicRecordRepository.findAllAcademicYears();
    }
}