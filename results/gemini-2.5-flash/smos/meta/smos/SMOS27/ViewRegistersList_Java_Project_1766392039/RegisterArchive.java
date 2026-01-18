import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

/**
 * Manages the storage and retrieval of digital registers.
 * This class acts as an archive for all digital records, allowing them to be
 * searched and retrieved based on the academic year they belong to.
 */
public class RegisterArchive {
    // A map to store digital registers, keyed by AcademicYear.
    // Each AcademicYear can have multiple DigitalRegister entries.
    private final Map<AcademicYear, List<DigitalRegister>> archive;

    /**
     * Constructs a new RegisterArchive.
     * Initializes the internal storage for digital registers.
     */
    public RegisterArchive() {
        this.archive = new HashMap<>();
    }

    /**
     * Adds a digital register to the archive.
     * If the academic year of the register is not yet present in the archive,
     * a new list for that year is created.
     *
     * @param register The DigitalRegister object to be added.
     * @throws IllegalArgumentException if the provided register is null.
     */
    public void addRegister(DigitalRegister register) {
        if (register == null) {
            throw new IllegalArgumentException("Cannot add a null register to the archive.");
        }
        AcademicYear year = register.getAcademicYear();
        // Use computeIfAbsent to add the list if the year is not already a key
        archive.computeIfAbsent(year, k -> new ArrayList<>()).add(register);
        System.out.println("Added register: " + register.getRegisterName() + " for " + year);
    }

    /**
     * Retrieves all digital registers associated with a specific academic year.
     *
     * @param academicYear The AcademicYear object to search for.
     * @return A list of DigitalRegister objects for the specified academic year.
     *         Returns an empty list if no registers are found for that year or if academicYear is null.
     */
    public List<DigitalRegister> getRegistersByAcademicYear(AcademicYear academicYear) {
        if (academicYear == null) {
            System.out.println("Academic year cannot be null for retrieval.");
            return Collections.emptyList(); // Return an empty list for null input
        }
        // Return an unmodifiable list to prevent external modification of the archive's internal state
        return Collections.unmodifiableList(archive.getOrDefault(academicYear, Collections.emptyList()));
    }

    /**
     * Checks if the archive contains any registers for a given academic year.
     *
     * @param academicYear The academic year to check.
     * @return true if registers exist for the year, false otherwise.
     */
    public boolean hasRegistersForAcademicYear(AcademicYear academicYear) {
        if (academicYear == null) {
            return false;
        }
        return archive.containsKey(academicYear) && !archive.get(academicYear).isEmpty();
    }

    /**
     * Returns a list of all unique academic years for which registers are stored.
     *
     * @return A list of AcademicYear objects.
     */
    public List<AcademicYear> getAllAcademicYears() {
        // Return a new ArrayList to prevent external modification of the internal set of keys
        return new ArrayList<>(archive.keySet());
    }

    /**
     * Clears all registers from the archive.
     * This method is primarily for testing or system reset purposes.
     */
    public void clearArchive() {
        archive.clear();
        System.out.println("Register archive cleared.");
    }
}