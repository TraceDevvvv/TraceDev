package com.example;

import java.util.List;

/**
 * Service layer for academic operations.
 * Handles business logic, interacts with the repository and caches.
 */
public class AcademicService {
    private IAcademicRepository academicRepository;
    private AcademicYearCache academicYearCache; // Added for QR1
    private ClassCache classCache;             // Added for QR1

    public AcademicService(IAcademicRepository academicRepository, AcademicYearCache academicYearCache, ClassCache classCache) {
        this.academicRepository = academicRepository;
        this.academicYearCache = academicYearCache;
        this.classCache = classCache;
        System.out.println("AcademicService initialized.");
    }

    /**
     * Retrieves academic years for a professor, utilizing a cache.
     *
     * @param professorId The ID of the professor.
     * @return A list of AcademicYear objects.
     * @throws ServiceException if an error occurs during data retrieval.
     */
    public List<AcademicYear> getAcademicYearsForProfessor(String professorId) throws ServiceException {
        System.out.println("AcademicService: getAcademicYearsForProfessor(" + professorId + ")");

        // Try to get from cache first (QR1)
        List<AcademicYear> academicYearsFromCache = academicYearCache.getAcademicYears(professorId);
        if (academicYearsFromCache != null) {
            System.out.println("AcademicService: Using cached academic years.");
            return academicYearsFromCache;
        }

        // Cache miss, fetch from repository
        System.out.println("AcademicService: Cache miss for academic years. Fetching from repository.");
        try {
            List<AcademicYear> academicYearsFromRepo = academicRepository.findAcademicYearsByProfessor(professorId);
            // Put into cache (QR1)
            academicYearCache.putAcademicYears(professorId, academicYearsFromRepo);
            return academicYearsFromRepo;
        } catch (DataAccessException e) {
            System.err.println("AcademicService: Error fetching academic years from repository: " + e.getMessage());
            throw new ServiceException("Failed to retrieve academic years for professor: " + professorId, e);
        }
    }

    /**
     * Retrieves classes for a specific academic year and professor, utilizing a cache.
     *
     * @param professorId The ID of the professor.
     * @param academicYearId The ID of the academic year.
     * @return A list of Class objects.
     * @throws ServiceException if an error occurs during data retrieval.
     */
    public List<Class> getClassesForAcademicYear(String professorId, String academicYearId) throws ServiceException {
        System.out.println("AcademicService: getClassesForAcademicYear(" + professorId + ", " + academicYearId + ")");

        // Try to get from cache first (QR1)
        List<Class> classesFromCache = classCache.getClasses(professorId, academicYearId);
        if (classesFromCache != null) {
            System.out.println("AcademicService: Using cached classes.");
            return classesFromCache;
        }

        // Cache miss, fetch from repository
        System.out.println("AcademicService: Cache miss for classes. Fetching from repository.");
        try {
            List<Class> classesFromRepo = academicRepository.findClassesByAcademicYearAndProfessor(professorId, academicYearId);
            // Put into cache (QR1)
            classCache.putClasses(professorId, academicYearId, classesFromRepo);
            return classesFromRepo;
        } catch (DataAccessException e) {
            System.err.println("AcademicService: Error fetching classes from repository: " + e.getMessage());
            throw new ServiceException("Failed to retrieve classes for professor " + professorId + " and year " + academicYearId, e);
        }
    }
}