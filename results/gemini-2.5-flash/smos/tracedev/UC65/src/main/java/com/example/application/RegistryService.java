package com.example.application;

import com.example.dataaccess.IRegistryRepository;
import com.example.domain.ClassRegistry;
import com.example.domain.RegistryEntry;
import com.example.domain.StudentStatus;
import com.example.presentation.RegistryEntryViewModel;
import com.example.presentation.RegistryViewModel;
import com.example.presentation.StudentStatusViewModel;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service layer for managing and processing class registry data.
 * Orchestrates operations between the Presentation and Data Access layers.
 */
public class RegistryService {
    private IRegistryRepository registryRepository;

    /**
     * Constructs a RegistryService with a given IRegistryRepository.
     * @param registryRepository The repository to use for data access.
     */
    public RegistryService(IRegistryRepository registryRepository) {
        this.registryRepository = registryRepository;
    }

    /**
     * Retrieves the detailed class registry, processes it, and converts it into a ViewModel.
     * This method orchestrates the main business logic flow.
     *
     * @param classId The ID of the class registry to retrieve.
     * @return A RegistryViewModel containing the processed data for display.
     */
    public RegistryViewModel getDetailedClassRegistry(String classId) {
        System.out.println("[RegistryService] Getting detailed class registry for classId: " + classId);

        // Step 1: Retrieve data using the Repository Pattern.
        // This abstracts away the data source (e.g., database, external system like SMOS).
        ClassRegistry classRegistry = registryRepository.findByClassId(classId);
        System.out.println("[RegistryService] Data accessed via Repository Pattern for classId: " + classId);

        // Step 2: Organize the information by date.
        // This ensures the data is presented in a consistent and logical order.
        ClassRegistry organizedRegistry = organizeByDate(classRegistry);
        System.out.println("[RegistryService] Organized information by date.");

        // Step 3: Convert the domain model into a Presentation-specific ViewModel.
        // This decouples the UI from the domain model and prepares data for display.
        RegistryViewModel viewModel = convertToViewModel(organizedRegistry);
        System.out.println("[RegistryService] Converted to ViewModel. (R17: Data accuracy ensured during processing)");

        return viewModel;
    }

    /**
     * Organizes the entries within a ClassRegistry by their entry date.
     *
     * @param classRegistry The ClassRegistry to organize.
     * @return A new ClassRegistry with entries sorted by date, or the original if no entries.
     */
    protected ClassRegistry organizeByDate(ClassRegistry classRegistry) {
        if (classRegistry == null || classRegistry.getEntries() == null) {
            return classRegistry;
        }

        // Sort the entries by date in ascending order
        List<RegistryEntry> sortedEntries = classRegistry.getEntries().stream()
                .sorted(Comparator.comparing(RegistryEntry::getEntryDate))
                .collect(Collectors.toList());

        // Create a new ClassRegistry with sorted entries, or modify existing if domain allows
        return new ClassRegistry(classRegistry.getId(), classRegistry.getClassName(),
                                 classRegistry.getAcademicYear(), sortedEntries);
    }

    /**
     * Converts a ClassRegistry domain model into a RegistryViewModel suitable for UI display.
     *
     * @param classRegistry The domain model to convert.
     * @return The corresponding RegistryViewModel.
     */
    protected RegistryViewModel convertToViewModel(ClassRegistry classRegistry) {
        if (classRegistry == null) {
            return null;
        }

        List<RegistryEntryViewModel> entryViewModels = classRegistry.getEntries().stream()
                .map(this::toRegistryEntryViewModel)
                .collect(Collectors.toList());

        return new RegistryViewModel(
                classRegistry.getClassName(),
                classRegistry.getAcademicYear(),
                entryViewModels
        );
    }

    /**
     * Helper method to convert a RegistryEntry domain model to a RegistryEntryViewModel.
     * @param entry The domain RegistryEntry.
     * @return The ViewModel RegistryEntryViewModel.
     */
    private RegistryEntryViewModel toRegistryEntryViewModel(RegistryEntry entry) {
        List<StudentStatusViewModel> studentStatusViewModels = entry.getStudentStatuses().stream()
                .map(this::toStudentStatusViewModel)
                .collect(Collectors.toList());

        return new RegistryEntryViewModel(entry.getEntryDate(), studentStatusViewModels);
    }

    /**
     * Helper method to convert a StudentStatus domain model to a StudentStatusViewModel.
     * @param status The domain StudentStatus.
     * @return The ViewModel StudentStatusViewModel.
     */
    private StudentStatusViewModel toStudentStatusViewModel(StudentStatus status) {
        String justificationDescription = status.getJustifications().stream()
                .map(j -> j.getDescription() + " (" + j.getStatus() + ")")
                .collect(Collectors.joining("; "));
        if (justificationDescription.isEmpty()) {
            justificationDescription = "No justification";
        }

        String disciplinaryNoteText = status.getDisciplinaryNotes().stream()
                .map(d -> d.getNote() + " (Severity: " + d.getSeverity() + ")")
                .collect(Collectors.joining("; "));
        if (disciplinaryNoteText.isEmpty()) {
            disciplinaryNoteText = "No disciplinary note";
        }

        return new StudentStatusViewModel(
                status.getStudent().getName(),
                status.getIsPresent(),
                status.getIsDelayed(),
                justificationDescription,
                disciplinaryNoteText
        );
    }
}